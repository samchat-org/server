package com.samchat.common.beans.auto.db.mapper;

import com.samchat.common.beans.auto.db.entitybeans.TQuestionQuestions;
import com.samchat.common.beans.auto.db.entitybeans.TQuestionQuestionsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TQuestionQuestionsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_question_questions
     *
     * @mbggenerated
     */
    int countByExample(TQuestionQuestionsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_question_questions
     *
     * @mbggenerated
     */
    int deleteByExample(TQuestionQuestionsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_question_questions
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long question_id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_question_questions
     *
     * @mbggenerated
     */
    int insert(TQuestionQuestions record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_question_questions
     *
     * @mbggenerated
     */
    int insertSelective(TQuestionQuestions record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_question_questions
     *
     * @mbggenerated
     */
    List<TQuestionQuestions> selectByExample(TQuestionQuestionsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_question_questions
     *
     * @mbggenerated
     */
    TQuestionQuestions selectByPrimaryKey(Long question_id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_question_questions
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") TQuestionQuestions record, @Param("example") TQuestionQuestionsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_question_questions
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") TQuestionQuestions record, @Param("example") TQuestionQuestionsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_question_questions
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(TQuestionQuestions record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_question_questions
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TQuestionQuestions record);
}