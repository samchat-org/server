package com.skyworld.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import com.skyworld.common.AppException;
import com.skyworld.common.Constant;
import com.skyworld.utils.CommonUtil;
import com.skyworld.utils.StrUtils;

public abstract class BaseAction extends ToolAction {

	private Logger log = Logger.getLogger(this.getClass());

	private static final String JSON_CLASS_BASE_PATH = "com.skyworld.beans.auto.json";

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
			String parts[] = req.getRequestURI()
					.substring(req.getContextPath().length() + 1, req.getRequestURI().length() - 3).split("_");
			if (parts.length != 4) {
				throw new Exception("url error, 4 part");
			}
			String tplClassPrefix = JSON_CLASS_BASE_PATH + "." + parts[2] + "Tpl."
					+ StrUtils.firstToUpperCase(parts[3]) + "_";
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
				log.error(e.getMessage(), e);
				throw new AppException(Constant.ERROR_PARAM_NONSUPPORT, "read error (json object), data:" + data);
			}
			// 获取action
			String action = null;
			try {
				Object head = CommonUtil.methodInvoke(dataObj, tplclazzReq, "getHeader");
				action = CommonUtil.methodInvoke(head, head.getClass(), "getAction") + "";
			} catch (Exception e) {
				throw new AppException(Constant.ERROR_PARAM_NONSUPPORT);
			}
			try {
				retObj = CommonUtil.methodInvoke(this, this.getClass(), action, new Class[] { dataObj.getClass() },
						new Object[] { dataObj });
			} catch (Exception e) {
				throw new AppException(Constant.ERROR_ACTION_NONSUPPORT);
			}
			retJson = om.writeValueAsString(retObj);

		} catch (AppException a) {
			log.error(a.getMessage(), a);
			retJson = sysErrorRet(a.getErrorCode());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			retJson = sysErrorRet(Constant.ERROR_DATA_PARSE);
		} finally {
			try {
				resp.setCharacterEncoding("utf8");
				resp.setContentType("application/json");
				resp.getWriter().write(retJson);
				resp.flushBuffer();
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
		}

	}
}
