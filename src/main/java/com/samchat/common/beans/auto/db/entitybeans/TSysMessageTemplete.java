package com.samchat.common.beans.auto.db.entitybeans;

import com.samchat.common.beans.auto.db.BaseEntity;
import java.util.Date;

public class TSysMessageTemplete extends BaseEntity {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_message_templete.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_message_templete.action_code
     *
     * @mbggenerated
     */
    private String action_code;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_message_templete.action_type
     *
     * @mbggenerated
     */
    private Short action_type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_message_templete.templete
     *
     * @mbggenerated
     */
    private String templete;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_message_templete.state
     *
     * @mbggenerated
     */
    private Byte state;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_message_templete.state_date
     *
     * @mbggenerated
     */
    private Date state_date;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_message_templete.description
     *
     * @mbggenerated
     */
    private String description;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sys_message_templete.id
     *
     * @return the value of t_sys_message_templete.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sys_message_templete.id
     *
     * @param id the value for t_sys_message_templete.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sys_message_templete.action_code
     *
     * @return the value of t_sys_message_templete.action_code
     *
     * @mbggenerated
     */
    public String getAction_code() {
        return action_code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sys_message_templete.action_code
     *
     * @param action_code the value for t_sys_message_templete.action_code
     *
     * @mbggenerated
     */
    public void setAction_code(String action_code) {
        this.action_code = action_code == null ? null : action_code.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sys_message_templete.action_type
     *
     * @return the value of t_sys_message_templete.action_type
     *
     * @mbggenerated
     */
    public Short getAction_type() {
        return action_type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sys_message_templete.action_type
     *
     * @param action_type the value for t_sys_message_templete.action_type
     *
     * @mbggenerated
     */
    public void setAction_type(Short action_type) {
        this.action_type = action_type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sys_message_templete.templete
     *
     * @return the value of t_sys_message_templete.templete
     *
     * @mbggenerated
     */
    public String getTemplete() {
        return templete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sys_message_templete.templete
     *
     * @param templete the value for t_sys_message_templete.templete
     *
     * @mbggenerated
     */
    public void setTemplete(String templete) {
        this.templete = templete == null ? null : templete.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sys_message_templete.state
     *
     * @return the value of t_sys_message_templete.state
     *
     * @mbggenerated
     */
    public Byte getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sys_message_templete.state
     *
     * @param state the value for t_sys_message_templete.state
     *
     * @mbggenerated
     */
    public void setState(Byte state) {
        this.state = state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sys_message_templete.state_date
     *
     * @return the value of t_sys_message_templete.state_date
     *
     * @mbggenerated
     */
    public Date getState_date() {
        return state_date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sys_message_templete.state_date
     *
     * @param state_date the value for t_sys_message_templete.state_date
     *
     * @mbggenerated
     */
    public void setState_date(Date state_date) {
        this.state_date = state_date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sys_message_templete.description
     *
     * @return the value of t_sys_message_templete.description
     *
     * @mbggenerated
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sys_message_templete.description
     *
     * @param description the value for t_sys_message_templete.description
     *
     * @mbggenerated
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}