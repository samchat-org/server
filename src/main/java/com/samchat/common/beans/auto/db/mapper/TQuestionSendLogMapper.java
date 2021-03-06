package com.samchat.common.beans.auto.db.mapper;

import com.samchat.common.beans.auto.db.entitybeans.TQuestionSendLog;
import com.samchat.common.beans.auto.db.entitybeans.TQuestionSendLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TQuestionSendLogMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_question_send_log
     *
     * @mbggenerated
     */
    int countByExample(TQuestionSendLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_question_send_log
     *
     * @mbggenerated
     */
    int deleteByExample(TQuestionSendLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_question_send_log
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long qst_log_id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_question_send_log
     *
     * @mbggenerated
     */
    int insert(TQuestionSendLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_question_send_log
     *
     * @mbggenerated
     */
    int insertSelective(TQuestionSendLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_question_send_log
     *
     * @mbggenerated
     */
    List<TQuestionSendLog> selectByExample(TQuestionSendLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_question_send_log
     *
     * @mbggenerated
     */
    TQuestionSendLog selectByPrimaryKey(Long qst_log_id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_question_send_log
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") TQuestionSendLog record, @Param("example") TQuestionSendLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_question_send_log
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") TQuestionSendLog record, @Param("example") TQuestionSendLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_question_send_log
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(TQuestionSendLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_question_send_log
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TQuestionSendLog record);
}