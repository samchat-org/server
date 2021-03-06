package com.samchat.common.beans.auto.db.entitybeans;

import com.samchat.common.beans.auto.db.BaseEntity;
import java.util.Date;

public class TAdvertisementContent extends BaseEntity {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_advertisement_content.ads_id
     *
     * @mbggenerated
     */
    private Long ads_id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_advertisement_content.ads_type
     *
     * @mbggenerated
     */
    private Byte ads_type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_advertisement_content.content
     *
     * @mbggenerated
     */
    private String content;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_advertisement_content.content_thumb
     *
     * @mbggenerated
     */
    private String content_thumb;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_advertisement_content.user_id_pro
     *
     * @mbggenerated
     */
    private Long user_id_pro;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_advertisement_content.state
     *
     * @mbggenerated
     */
    private Byte state;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_advertisement_content.state_date
     *
     * @mbggenerated
     */
    private Date state_date;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_advertisement_content.create_date
     *
     * @mbggenerated
     */
    private Date create_date;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_advertisement_content.sharding_flag
     *
     * @mbggenerated
     */
    private Integer sharding_flag;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_advertisement_content.ads_id
     *
     * @return the value of t_advertisement_content.ads_id
     *
     * @mbggenerated
     */
    public Long getAds_id() {
        return ads_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_advertisement_content.ads_id
     *
     * @param ads_id the value for t_advertisement_content.ads_id
     *
     * @mbggenerated
     */
    public void setAds_id(Long ads_id) {
        this.ads_id = ads_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_advertisement_content.ads_type
     *
     * @return the value of t_advertisement_content.ads_type
     *
     * @mbggenerated
     */
    public Byte getAds_type() {
        return ads_type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_advertisement_content.ads_type
     *
     * @param ads_type the value for t_advertisement_content.ads_type
     *
     * @mbggenerated
     */
    public void setAds_type(Byte ads_type) {
        this.ads_type = ads_type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_advertisement_content.content
     *
     * @return the value of t_advertisement_content.content
     *
     * @mbggenerated
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_advertisement_content.content
     *
     * @param content the value for t_advertisement_content.content
     *
     * @mbggenerated
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_advertisement_content.content_thumb
     *
     * @return the value of t_advertisement_content.content_thumb
     *
     * @mbggenerated
     */
    public String getContent_thumb() {
        return content_thumb;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_advertisement_content.content_thumb
     *
     * @param content_thumb the value for t_advertisement_content.content_thumb
     *
     * @mbggenerated
     */
    public void setContent_thumb(String content_thumb) {
        this.content_thumb = content_thumb == null ? null : content_thumb.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_advertisement_content.user_id_pro
     *
     * @return the value of t_advertisement_content.user_id_pro
     *
     * @mbggenerated
     */
    public Long getUser_id_pro() {
        return user_id_pro;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_advertisement_content.user_id_pro
     *
     * @param user_id_pro the value for t_advertisement_content.user_id_pro
     *
     * @mbggenerated
     */
    public void setUser_id_pro(Long user_id_pro) {
        this.user_id_pro = user_id_pro;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_advertisement_content.state
     *
     * @return the value of t_advertisement_content.state
     *
     * @mbggenerated
     */
    public Byte getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_advertisement_content.state
     *
     * @param state the value for t_advertisement_content.state
     *
     * @mbggenerated
     */
    public void setState(Byte state) {
        this.state = state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_advertisement_content.state_date
     *
     * @return the value of t_advertisement_content.state_date
     *
     * @mbggenerated
     */
    public Date getState_date() {
        return state_date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_advertisement_content.state_date
     *
     * @param state_date the value for t_advertisement_content.state_date
     *
     * @mbggenerated
     */
    public void setState_date(Date state_date) {
        this.state_date = state_date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_advertisement_content.create_date
     *
     * @return the value of t_advertisement_content.create_date
     *
     * @mbggenerated
     */
    public Date getCreate_date() {
        return create_date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_advertisement_content.create_date
     *
     * @param create_date the value for t_advertisement_content.create_date
     *
     * @mbggenerated
     */
    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_advertisement_content.sharding_flag
     *
     * @return the value of t_advertisement_content.sharding_flag
     *
     * @mbggenerated
     */
    public Integer getSharding_flag() {
        return sharding_flag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_advertisement_content.sharding_flag
     *
     * @param sharding_flag the value for t_advertisement_content.sharding_flag
     *
     * @mbggenerated
     */
    public void setSharding_flag(Integer sharding_flag) {
        this.sharding_flag = sharding_flag;
    }
}