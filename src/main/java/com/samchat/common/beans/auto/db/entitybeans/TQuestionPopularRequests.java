package com.samchat.common.beans.auto.db.entitybeans;

import com.samchat.common.beans.auto.db.BaseEntity;
import java.util.Date;

public class TQuestionPopularRequests extends BaseEntity {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_question_popular_requests.popular_id
     *
     * @mbggenerated
     */
    private Long popular_id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_question_popular_requests.content
     *
     * @mbggenerated
     */
    private String content;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_question_popular_requests.priority
     *
     * @mbggenerated
     */
    private Integer priority;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_question_popular_requests.state
     *
     * @mbggenerated
     */
    private Byte state;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_question_popular_requests.state_date
     *
     * @mbggenerated
     */
    private Date state_date;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_question_popular_requests.create_date
     *
     * @mbggenerated
     */
    private Date create_date;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_question_popular_requests.popular_id
     *
     * @return the value of t_question_popular_requests.popular_id
     *
     * @mbggenerated
     */
    public Long getPopular_id() {
        return popular_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_question_popular_requests.popular_id
     *
     * @param popular_id the value for t_question_popular_requests.popular_id
     *
     * @mbggenerated
     */
    public void setPopular_id(Long popular_id) {
        this.popular_id = popular_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_question_popular_requests.content
     *
     * @return the value of t_question_popular_requests.content
     *
     * @mbggenerated
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_question_popular_requests.content
     *
     * @param content the value for t_question_popular_requests.content
     *
     * @mbggenerated
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_question_popular_requests.priority
     *
     * @return the value of t_question_popular_requests.priority
     *
     * @mbggenerated
     */
    public Integer getPriority() {
        return priority;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_question_popular_requests.priority
     *
     * @param priority the value for t_question_popular_requests.priority
     *
     * @mbggenerated
     */
    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_question_popular_requests.state
     *
     * @return the value of t_question_popular_requests.state
     *
     * @mbggenerated
     */
    public Byte getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_question_popular_requests.state
     *
     * @param state the value for t_question_popular_requests.state
     *
     * @mbggenerated
     */
    public void setState(Byte state) {
        this.state = state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_question_popular_requests.state_date
     *
     * @return the value of t_question_popular_requests.state_date
     *
     * @mbggenerated
     */
    public Date getState_date() {
        return state_date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_question_popular_requests.state_date
     *
     * @param state_date the value for t_question_popular_requests.state_date
     *
     * @mbggenerated
     */
    public void setState_date(Date state_date) {
        this.state_date = state_date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_question_popular_requests.create_date
     *
     * @return the value of t_question_popular_requests.create_date
     *
     * @mbggenerated
     */
    public Date getCreate_date() {
        return create_date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_question_popular_requests.create_date
     *
     * @param create_date the value for t_question_popular_requests.create_date
     *
     * @mbggenerated
     */
    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }
}