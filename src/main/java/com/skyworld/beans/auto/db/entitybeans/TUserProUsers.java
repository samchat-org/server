package com.skyworld.beans.auto.db.entitybeans;

import com.skyworld.beans.auto.db.BaseEntity;
import java.util.Date;

public class TUserProUsers extends BaseEntity {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_pro_users.pro_user_id
     *
     * @mbggenerated
     */
    private Long pro_user_id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_pro_users.user_name
     *
     * @mbggenerated
     */
    private String user_name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_pro_users.phone_no
     *
     * @mbggenerated
     */
    private String phone_no;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_pro_users.email
     *
     * @mbggenerated
     */
    private String email;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_pro_users.avatar
     *
     * @mbggenerated
     */
    private String avatar;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_pro_users.state
     *
     * @mbggenerated
     */
    private Byte state;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_pro_users.state_date
     *
     * @mbggenerated
     */
    private Date state_date;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_pro_users.create_date
     *
     * @mbggenerated
     */
    private Date create_date;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_pro_users.pro_user_id
     *
     * @return the value of t_user_pro_users.pro_user_id
     *
     * @mbggenerated
     */
    public Long getPro_user_id() {
        return pro_user_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_pro_users.pro_user_id
     *
     * @param pro_user_id the value for t_user_pro_users.pro_user_id
     *
     * @mbggenerated
     */
    public void setPro_user_id(Long pro_user_id) {
        this.pro_user_id = pro_user_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_pro_users.user_name
     *
     * @return the value of t_user_pro_users.user_name
     *
     * @mbggenerated
     */
    public String getUser_name() {
        return user_name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_pro_users.user_name
     *
     * @param user_name the value for t_user_pro_users.user_name
     *
     * @mbggenerated
     */
    public void setUser_name(String user_name) {
        this.user_name = user_name == null ? null : user_name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_pro_users.phone_no
     *
     * @return the value of t_user_pro_users.phone_no
     *
     * @mbggenerated
     */
    public String getPhone_no() {
        return phone_no;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_pro_users.phone_no
     *
     * @param phone_no the value for t_user_pro_users.phone_no
     *
     * @mbggenerated
     */
    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no == null ? null : phone_no.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_pro_users.email
     *
     * @return the value of t_user_pro_users.email
     *
     * @mbggenerated
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_pro_users.email
     *
     * @param email the value for t_user_pro_users.email
     *
     * @mbggenerated
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_pro_users.avatar
     *
     * @return the value of t_user_pro_users.avatar
     *
     * @mbggenerated
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_pro_users.avatar
     *
     * @param avatar the value for t_user_pro_users.avatar
     *
     * @mbggenerated
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_pro_users.state
     *
     * @return the value of t_user_pro_users.state
     *
     * @mbggenerated
     */
    public Byte getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_pro_users.state
     *
     * @param state the value for t_user_pro_users.state
     *
     * @mbggenerated
     */
    public void setState(Byte state) {
        this.state = state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_pro_users.state_date
     *
     * @return the value of t_user_pro_users.state_date
     *
     * @mbggenerated
     */
    public Date getState_date() {
        return state_date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_pro_users.state_date
     *
     * @param state_date the value for t_user_pro_users.state_date
     *
     * @mbggenerated
     */
    public void setState_date(Date state_date) {
        this.state_date = state_date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_pro_users.create_date
     *
     * @return the value of t_user_pro_users.create_date
     *
     * @mbggenerated
     */
    public Date getCreate_date() {
        return create_date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_pro_users.create_date
     *
     * @param create_date the value for t_user_pro_users.create_date
     *
     * @mbggenerated
     */
    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }
}