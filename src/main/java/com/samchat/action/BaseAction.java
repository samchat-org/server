package com.samchat.action;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.samchat.common.Constant;
import com.samchat.common.exceptions.AppException;
import com.samchat.common.utils.CommonUtil;
import com.samchat.common.utils.StrUtils;
import com.samchat.service.interfaces.IUsersSrv;

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
				throw new AppException(Constant.ERROR_ACTION_NONSUPPORT, "reqClass is not found:" + classpath);
			}

			try {
				classpath = tplClassPrefix + "res";
				tplclazzRes = Class.forName(classpath);
			} catch (ClassNotFoundException e1) {
				throw new AppException(Constant.ERROR_ACTION_NONSUPPORT, "resClass is not found:" + classpath);
			}

			// json转化成object
			Object dataObj = null;
			try {
				dataObj = om.readValue(data, tplclazzReq);
			} catch (Exception e) {
				log.error("read error (json object), data:" + data);
				throw new AppException(Constant.ERROR_PARAM_NONSUPPORT, e);
			}
			// 获取action
			String action = null;
			try {
				Object head = CommonUtil.methodInvoke(dataObj, tplclazzReq, "getHeader");
				action = CommonUtil.methodInvoke(head, head.getClass(), "getAction") + "";
			} catch (Exception e) {
				throw new AppException(Constant.ERROR_PARAM_NONSUPPORT);
			}

			action = StrUtils.firstToUpperCase(action, "-");
			int dev = CommonUtil.getSysConfigInt("dev_" + action); // 1 开发模式

			if (dev == Constant.DEV_MODE) {
				String tplDataRes = CommonUtil.getSysConfigStr("json_dev_position") + "/" + parts[2] + "/" + parts[3]
						+ "_res.json";
				retJson = FileUtils.readFileToString(new File(tplDataRes), Constant.CHARSET);
			} else {
				try {
					Object vaildRetObj = CommonUtil.methodInvoke(this, this.getClass(), action + "Validate",
							new Class[] { dataObj.getClass() }, new Object[] { dataObj });

					Class[] actionParamType = null;
					Object[] actionParamValue = null;

					if (vaildRetObj == null) {
						actionParamType = new Class[] { dataObj.getClass() };
						actionParamValue = new Object[] { dataObj };

					} else {
						actionParamType = new Class[] { dataObj.getClass(), vaildRetObj.getClass() };
						actionParamValue = new Object[] { dataObj, vaildRetObj };

					}
					retObj = CommonUtil.methodInvoke(this, this.getClass(), action, actionParamType, actionParamValue);

				} catch (NoSuchMethodException e) {
					throw new AppException(Constant.ERROR_ACTION_NONSUPPORT);
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
			log.error(cause.getMessage(), e);
			if (cause instanceof AppException) {
				retJson = sysErrorRet(((AppException) cause).getErrorCode());
			} else {
				retJson = sysErrorRet(Constant.ERROR_INNER);
			}

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			retJson = sysErrorRet(Constant.ERROR_INNER);

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
}