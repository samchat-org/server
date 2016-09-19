package com.samchat.common.beans.auto.db.entitybeans;

import com.samchat.common.beans.auto.db.BaseEntity;
import java.util.Date;

public class TAdvertisementSendLog extends BaseEntity {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_advertisement_send_log.log_id
     *
     * @mbggenerated
     */
    private Long log_id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_advertisement_send_log.ads_id
     *
     * @mbggenerated
     */
    private Long ads_id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_advertisement_send_log.user_id
     *
     * @mbggenerated
     */
    private Long user_id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_advertisement_send_log.state
     *
     * @mbggenerated
     */
    private Byte state;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_advertisement_send_log.send_date
     *
     * @mbggenerated
     */
    private Date send_date;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_advertisement_send_log.send_count
     *
     * @mbggenerated
     */
    private Integer send_count;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_advertisement_send_log.client_id
     *
     * @mbggenerated
     */
    private String client_id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_advertisement_send_log.remark
     *
     * @mbggenerated
     */
    private String remark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_advertisement_send_log.sharding_flag
     *
     * @mbggenerated
     */
    private Integer sharding_flag;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_advertisement_send_log.log_id
     *
     * @return the value of t_advertisement_send_log.log_id
     *
     * @mbggenerated
     */
    public Long getLog_id() {
        return log_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_advertisement_send_log.log_id
     *
     * @param log_id the value for t_advertisement_send_log.log_id
     *
     * @mbggenerated
     */
    public void setLog_id(Long log_id) {
        this.log_id = log_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_advertisement_send_log.ads_id
     *
     * @return the value of t_advertisement_send_log.ads_id
     *
     * @mbggenerated
     */
    public Long getAds_id() {
        return ads_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_advertisement_send_log.ads_id
     *
     * @param ads_id the value for t_advertisement_send_log.ads_id
     *
     * @mbggenerated
     */
    public void setAds_id(Long ads_id) {
        this.ads_id = ads_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_advertisement_send_log.user_id
     *
     * @return the value of t_advertisement_send_log.user_id
     *
     * @mbggenerated
     */
    public Long getUser_id() {
        return user_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_advertisement_send_log.user_id
     *
     * @param user_id the value for t_advertisement_send_log.user_id
     *
     * @mbggenerated
     */
    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_advertisement_send_log.state
     *
     * @return the value of t_advertisement_send_log.state
     *
     * @mbggenerated
     */
    public Byte getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_advertisement_send_log.state
     *
     * @param state the value for t_advertisement_send_log.state
     *
     * @mbggenerated
     */
    public void setState(Byte state) {
        this.state = state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_advertisement_send_log.send_date
     *
     * @return the value of t_advertisement_send_log.send_date
     *
     * @mbggenerated
     */
    public Date getSend_date() {
        return send_date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_advertisement_send_log.send_date
     *
     * @param send_date the value for t_advertisement_send_log.send_date
     *
     * @mbggenerated
     */
    public void setSend_date(Date send_date) {
        this.send_date = send_date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_advertisement_send_log.send_count
     *
     * @return the value of t_advertisement_send_log.send_count
     *
     * @mbggenerated
     */
    public Integer getSend_count() {
        return send_count;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_advertisement_send_log.send_count
     *
     * @param send_count the value for t_advertisement_send_log.send_count
     *
     * @mbggenerated
     */
    public void setSend_count(Integer send_count) {
        this.send_count = send_count;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_advertisement_send_log.client_id
     *
     * @return the value of t_advertisement_send_log.client_id
     *
     * @mbggenerated
     */
    public String getClient_id() {
        return client_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_advertisement_send_log.client_id
     *
     * @param client_id the value for t_advertisement_send_log.client_id
     *
     * @mbggenerated
     */
    public void setClient_id(String client_id) {
        this.client_id = client_id == null ? null : client_id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_advertisement_send_log.remark
     *
     * @return the value of t_advertisement_send_log.remark
     *
     * @mbggenerated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_advertisement_send_log.remark
     *
     * @param remark the value for t_advertisement_send_log.remark
     *
     * @mbggenerated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_advertisement_send_log.sharding_flag
     *
     * @return the value of t_advertisement_send_log.sharding_flag
     *
     * @mbggenerated
     */
    public Integer getSharding_flag() {
        return sharding_flag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_advertisement_send_log.sharding_flag
     *
     * @param sharding_flag the value for t_advertisement_send_log.sharding_flag
     *
     * @mbggenerated
     */
    public void setSharding_flag(Integer sharding_flag) {
        this.sharding_flag = sharding_flag;
    }
}