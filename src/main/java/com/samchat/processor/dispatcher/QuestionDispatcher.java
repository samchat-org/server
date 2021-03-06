package com.samchat.processor.dispatcher;

import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.amazonaws.services.sqs.model.Message;
import com.samchat.common.beans.auto.db.entitybeans.TUserUsers;
import com.samchat.common.beans.auto.json.appserver.question.DispatchQuestion_req;
import com.samchat.common.beans.auto.json.ni.msg.SendAttachMsg_req;
import com.samchat.common.beans.manual.json.sqs.QuestionSqs;
import com.samchat.common.enums.db.AdsDbEnum;
import com.samchat.common.enums.db.QstDbEnum;
import com.samchat.common.enums.db.SysParamCodeDbEnum;
import com.samchat.common.enums.db.UserDbEnum;
import com.samchat.common.utils.CommonUtil;
import com.samchat.common.utils.ThreadLocalUtil;
import com.samchat.common.utils.niUtils.NiUtil;
import com.samchat.processor.dispatcher.base.DispatcherBase;
import com.samchat.service.interfaces.IQuestionSrvs;

public class QuestionDispatcher extends DispatcherBase {

	private static Logger log = Logger.getLogger(QuestionDispatcher.class);

	@Autowired
	private IQuestionSrvs questionSrv;

	private DispatchQuestion_req getRequest(TUserUsers destUser, QuestionSqs reqSqs) {

		DispatchQuestion_req req = new DispatchQuestion_req();
		DispatchQuestion_req.Header header = new DispatchQuestion_req.Header();
		DispatchQuestion_req.Body body = new DispatchQuestion_req.Body();
		DispatchQuestion_req.User user = new DispatchQuestion_req.User();

		req.setHeader(header);
		req.setBody(body);
		body.setUser(user);

		header.setCategory("1");

		body.setDatetime(reqSqs.getTime());
		body.setQuestion_id(reqSqs.getQuestion_id());
		body.setQuestion(reqSqs.getQuestion());
		body.setOpt(reqSqs.getOpt());
		body.setAddress(reqSqs.getAddress());
		user.setId(reqSqs.getUser_id());

		TUserUsers userSrc = usersSrv.queryUser(reqSqs.getUser_id());
		user.setUsername(userSrc.getUser_name());
		user.setLastupdate(userSrc.getState_date().getTime());

		body.setDest_id(destUser.getUser_id());

		return req;
	}

	public void process(Message message) throws Exception {
		Timestamp sysdate = commonSrv.querySysdate();
		String body = message.getBody();
		QuestionSqs req = ThreadLocalUtil.getAppObjectMapper().readValue(body, QuestionSqs.class);
		String sender = req.getUser_id() + "";
		long qstId = req.getQuestion_id();
		int shardingFlag = req.getShardingFlag();
		
		boolean ret = questionSrv.updateQuestionSendingState(qstId, shardingFlag);
		if(!ret){
			log.info("updateQuestionSendingState- failed: ads_id, shardingFlag" + qstId + "--" + shardingFlag);
			return;
		}
		QstDbEnum.ContentState cstate = QstDbEnum.ContentState.SEND_SUCCESS;
		try {
			List<TUserUsers> users = usersSrv.queryUsers();
			for (TUserUsers user : users) {
				long userIdPro = user.getUser_id();
				if (userIdPro != req.getUser_id()) {
					QstDbEnum.SendLogState state = null;
					try {
						String dispatchReq = ThreadLocalUtil.getAppObjectMapper().writeValueAsString(getRequest(user, req));
						String dispatchReqContent = "{\"id\":3,\"content\":" + dispatchReq + "}";
						SendAttachMsg_req msg = new SendAttachMsg_req();
						msg.setFrom(sender);
						msg.setTo(String.valueOf(userIdPro));
						msg.setAttach(dispatchReqContent);
						log.info("from:" + sender + "--to:" + user.getUser_id());
						if (user.getQuestion_notify() == UserDbEnum.QuestionNotify.NOTIFY.val()) {
							msg.setPushcontent("a new message");
						}
						NiUtil.sendAttachMsg(msg, sysdate);
						state = QstDbEnum.SendLogState.SEND_SUCCESS;
					} catch (Exception e) {
						log.error(e.getMessage(), e);
						state = QstDbEnum.SendLogState.ERROR;
						cstate = QstDbEnum.ContentState.ERROR;
					}finally{
						questionSrv.saveQuestionSendLog(qstId, userIdPro, state.val(), sysdate, shardingFlag, state.name());
					}
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			cstate = QstDbEnum.ContentState.ERROR;
		}finally{
			questionSrv.updateQuestionState(qstId, cstate.val(), shardingFlag);
		}
	}

	protected void init() {
		String sqsUrlName = SysParamCodeDbEnum.SQS_QUESTION_URL.getParamCode();
		int threadCount = CommonUtil
				.getSysConfigInt(SysParamCodeDbEnum.DISPATCHER_QUESTION_THREAD_COUNT.getParamCode());
		this.setSqsUrlName(sqsUrlName);
		this.setThreadCount(threadCount);
	}

	public static void main(String args[]) {

	}
}
