package com.samchat.common.beans.auto.db.entitybeans;

import com.samchat.common.beans.auto.db.BaseEntity;
import java.util.Date;

public class TOaFollow extends BaseEntity {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_oa_follow.follow_id
     *
     * @mbggenerated
     */
    private Long follow_id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_oa_follow.user_id
     *
     * @mbggenerated
     */
    private Long user_id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_oa_follow.user_id_pro
     *
     * @mbggenerated
     */
    private Long user_id_pro;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_oa_follow.block_tag
     *
     * @mbggenerated
     */
    private Byte block_tag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_oa_follow.favourite_tag
     *
     * @mbggenerated
     */
    private Byte favourite_tag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_oa_follow.state
     *
     * @mbggenerated
     */
    private Byte state;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_oa_follow.state_date
     *
     * @mbggenerated
     */
    private Date state_date;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_oa_follow.follow_id
     *
     * @return the value of t_oa_follow.follow_id
     *
     * @mbggenerated
     */
    public Long getFollow_id() {
        return follow_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_oa_follow.follow_id
     *
     * @param follow_id the value for t_oa_follow.follow_id
     *
     * @mbggenerated
     */
    public void setFollow_id(Long follow_id) {
        this.follow_id = follow_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_oa_follow.user_id
     *
     * @return the value of t_oa_follow.user_id
     *
     * @mbggenerated
     */
    public Long getUser_id() {
        return user_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_oa_follow.user_id
     *
     * @param user_id the value for t_oa_follow.user_id
     *
     * @mbggenerated
     */
    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_oa_follow.user_id_pro
     *
     * @return the value of t_oa_follow.user_id_pro
     *
     * @mbggenerated
     */
    public Long getUser_id_pro() {
        return user_id_pro;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_oa_follow.user_id_pro
     *
     * @param user_id_pro the value for t_oa_follow.user_id_pro
     *
     * @mbggenerated
     */
    public void setUser_id_pro(Long user_id_pro) {
        this.user_id_pro = user_id_pro;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_oa_follow.block_tag
     *
     * @return the value of t_oa_follow.block_tag
     *
     * @mbggenerated
     */
    public Byte getBlock_tag() {
        return block_tag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_oa_follow.block_tag
     *
     * @param block_tag the value for t_oa_follow.block_tag
     *
     * @mbggenerated
     */
    public void setBlock_tag(Byte block_tag) {
        this.block_tag = block_tag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_oa_follow.favourite_tag
     *
     * @return the value of t_oa_follow.favourite_tag
     *
     * @mbggenerated
     */
    public Byte getFavourite_tag() {
        return favourite_tag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_oa_follow.favourite_tag
     *
     * @param favourite_tag the value for t_oa_follow.favourite_tag
     *
     * @mbggenerated
     */
    public void setFavourite_tag(Byte favourite_tag) {
        this.favourite_tag = favourite_tag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_oa_follow.state
     *
     * @return the value of t_oa_follow.state
     *
     * @mbggenerated
     */
    public Byte getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_oa_follow.state
     *
     * @param state the value for t_oa_follow.state
     *
     * @mbggenerated
     */
    public void setState(Byte state) {
        this.state = state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_oa_follow.state_date
     *
     * @return the value of t_oa_follow.state_date
     *
     * @mbggenerated
     */
    public Date getState_date() {
        return state_date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_oa_follow.state_date
     *
     * @param state_date the value for t_oa_follow.state_date
     *
     * @mbggenerated
     */
    public void setState_date(Date state_date) {
        this.state_date = state_date;
    }
}