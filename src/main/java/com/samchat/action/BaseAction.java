package com.samchat.action;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import com.samchat.common.Constant;
import com.samchat.common.beans.manual.json.redis.TokenRds;
import com.samchat.common.exceptions.AppException;
import com.samchat.common.utils.CommonUtil;
import com.samchat.common.utils.StrUtils;

public abstract class BaseAction extends ToolAction {

	private static Logger log = Logger.getLogger(BaseAction.class);

	private static final String JSON_CLASS_BASE_PATH = "com.samchat.common.beans.auto.json.appserver";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	/**
	 * 消息处理流程
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Object retObj = null;
		String retJson = null;
		ObjectMapper om = new ObjectMapper();

		try {
			String data = req.getParameter("data");
			if (data == null) {
				throw new Exception("data null");
			}
			log.info("data recv:" + data);

			String parts[] = req.getRequestURI()
					.substring(req.getContextPath().length() + 1, req.getRequestURI().length() - 3).split("_");
			if (parts.length != 4) {
				throw new Exception("url error, 4 part");
			}

			String tplClassPrefix = JSON_CLASS_BASE_PATH + "." + parts[2] + "." + StrUtils.firstToUpperCase(parts[3])
					+ "_";

			Class tplclazzReq = null;
			Class tplclazzRes = null;
			// 找请求模板
			String classpath = "";
			try {
				classpath = tplClassPrefix + "req";
				tplclazzReq = Class.forName(classpath);
			} catch (ClassNotFoundException e1) {
				throw new AppException(Constant.ERROR.ACTION_NONSUPPORT, "reqClass is not found:" + classpath);
			}

			try {
				classpath = tplClassPrefix + "res";
				tplclazzRes = Class.forName(classpath);
			} catch (ClassNotFoundException e1) {
				throw new AppException(Constant.ERROR.ACTION_NONSUPPORT, "resClass is not found:" + classpath);
			}

			// json转化成object
			Object dataObj = null;
			try {
				dataObj = om.readValue(data, tplclazzReq);
			} catch (Exception e) {
				log.error("read error (json object), data:" + data);
				throw new AppException(Constant.ERROR.PARAM_NONSUPPORT, e);
			}
			// 获取action
			Object head = null;
			String action = null;
			try {
				head = CommonUtil.methodInvoke(dataObj, "getHeader");
				action = CommonUtil.methodInvoke(head, "getAction") + "";
			} catch (Exception e) {
				throw new AppException(Constant.ERROR.PARAM_NONSUPPORT);
			}
			action = StrUtils.firstToUpperCase(action, "-");

			int dev = 0;//CommonUtil.getSysConfigInt("dev_" + action); // 1 开发模式
			if (dev == Constant.DEV_MODE) {
				String tplDataRes = CommonUtil.getSysConfigStr("json_dev_position") + "/" + parts[2] + "/" + parts[3]
						+ "_res.json";
				retJson = FileUtils.readFileToString(new File(tplDataRes), Constant.CHARSET);

			} else {
				
				try {
					List<Object> objlist = new ArrayList<Object>();
					objlist.add(dataObj);

					TokenRds tokenrds = identifyToken(head);
					if (tokenrds != null) {
						objlist.add(tokenrds);
					}
					Object vaildRetObj = CommonUtil.methodInvoke(this, action + "Validate", objlist);
					if (vaildRetObj != null) {
						objlist.add(vaildRetObj);
					}
					retObj = CommonUtil.methodInvoke(this, action, objlist);
					CommonUtil.methodInvoke(retObj, "setRet", new Object[]{new Long(Constant.SUCCESS)}, new Class[]{long.class});

				} catch (NoSuchMethodException e) {
					throw new AppException(Constant.ERROR.ACTION_NONSUPPORT);
				}

				try {
					retJson = om.writeValueAsString(retObj);
				} catch (Exception e) {
					log.error(" error, objectMapper writeValueAsString, retObj:" + retObj);
					throw e;
				}
			}
		} catch (AppException a) {

			log.error(a);
			retJson = sysErrorRet(a.getErrorCode());

		} catch (InvocationTargetException e) {
			Throwable cause = e.getCause();
			if (cause instanceof AppException) {
				log.error(cause);
				retJson = sysErrorRet(((AppException) cause).getErrorCode());
			} else {
				log.error(cause.getMessage(), cause);
				retJson = sysErrorRet(Constant.ERROR.INNER);
			}

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			retJson = sysErrorRet(Constant.ERROR.INNER);

		} finally {
			try {
				log.info("data ret:" + retJson);
				resp.setCharacterEncoding(Constant.CHARSET);
				resp.setContentType(Constant.RES_FMT);
				resp.getWriter().write(retJson);
				resp.flushBuffer();
				log.info("ret successful");

			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
			log.info("\r\n");
		}
	}

	protected TokenRds identifyToken(Object head) throws Exception {
		Field[] fields = head.getClass().getDeclaredFields();
		TokenRds tokenObj = null;
		for (Field field : fields) {
			if ("token".equals(field.getName())) {
				String token = CommonUtil.methodInvoke(head, "getToken") + "";
				log.info("get token:" + token);
				tokenObj = usersSrv.getTokenObj(token);
				if (tokenObj == null) {
					throw new AppException(Constant.ERROR.TOKEN_ILLEGAL);
				}
			}
		}
		return tokenObj;
	}
}
