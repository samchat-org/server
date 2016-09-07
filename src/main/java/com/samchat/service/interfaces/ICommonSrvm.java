package com.samchat.service.interfaces;

import java.sql.Timestamp;

public interface ICommonSrvm {

	public Timestamp querySysdate();

	public Long querySeqId(String seqName);

}
