package com.samchat.common.beans.auto.db.entitybeans;

import com.samchat.common.beans.auto.db.BaseEntity;
import java.util.Date;

public class TUserUsers extends BaseEntity {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_users.user_id
     *
     * @mbggenerated
     */
    private Long user_id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_users.user_type
     *
     * @mbggenerated
     */
    private Byte user_type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_users.user_name
     *
     * @mbggenerated
     */
    private String user_name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_users.user_code
     *
     * @mbggenerated
     */
    private String user_code;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_users.user_pwd
     *
     * @mbggenerated
     */
    private String user_pwd;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_users.country_code
     *
     * @mbggenerated
     */
    private String country_code;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_users.phone_no
     *
     * @mbggenerated
     */
    private String phone_no;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_users.email
     *
     * @mbggenerated
     */
    private String email;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_users.longitude
     *
     * @mbggenerated
     */
    private String longitude;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_users.latitude
     *
     * @mbggenerated
     */
    private String latitude;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_users.place_id
     *
     * @mbggenerated
     */
    private String place_id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_users.address
     *
     * @mbggenerated
     */
    private String address;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_users.avatar_origin
     *
     * @mbggenerated
     */
    private String avatar_origin;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_users.avatar_thumb
     *
     * @mbggenerated
     */
    private String avatar_thumb;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_users.state
     *
     * @mbggenerated
     */
    private Byte state;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_users.state_date
     *
     * @mbggenerated
     */
    private Date state_date;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_users.create_date
     *
     * @mbggenerated
     */
    private Date create_date;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_users.cur_device_id
     *
     * @mbggenerated
     */
    private String cur_device_id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_users.cur_token
     *
     * @mbggenerated
     */
    private String cur_token;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_users.question_notify
     *
     * @mbggenerated
     */
    private Byte question_notify;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_users.cur_version
     *
     * @mbggenerated
     */
    private String cur_version;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_users.cur_device_type
     *
     * @mbggenerated
     */
    private String cur_device_type;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_users.user_id
     *
     * @return the value of t_user_users.user_id
     *
     * @mbggenerated
     */
    public Long getUser_id() {
        return user_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_users.user_id
     *
     * @param user_id the value for t_user_users.user_id
     *
     * @mbggenerated
     */
    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_users.user_type
     *
     * @return the value of t_user_users.user_type
     *
     * @mbggenerated
     */
    public Byte getUser_type() {
        return user_type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_users.user_type
     *
     * @param user_type the value for t_user_users.user_type
     *
     * @mbggenerated
     */
    public void setUser_type(Byte user_type) {
        this.user_type = user_type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_users.user_name
     *
     * @return the value of t_user_users.user_name
     *
     * @mbggenerated
     */
    public String getUser_name() {
        return user_name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_users.user_name
     *
     * @param user_name the value for t_user_users.user_name
     *
     * @mbggenerated
     */
    public void setUser_name(String user_name) {
        this.user_name = user_name == null ? null : user_name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_users.user_code
     *
     * @return the value of t_user_users.user_code
     *
     * @mbggenerated
     */
    public String getUser_code() {
        return user_code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_users.user_code
     *
     * @param user_code the value for t_user_users.user_code
     *
     * @mbggenerated
     */
    public void setUser_code(String user_code) {
        this.user_code = user_code == null ? null : user_code.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_users.user_pwd
     *
     * @return the value of t_user_users.user_pwd
     *
     * @mbggenerated
     */
    public String getUser_pwd() {
        return user_pwd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_users.user_pwd
     *
     * @param user_pwd the value for t_user_users.user_pwd
     *
     * @mbggenerated
     */
    public void setUser_pwd(String user_pwd) {
        this.user_pwd = user_pwd == null ? null : user_pwd.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_users.country_code
     *
     * @return the value of t_user_users.country_code
     *
     * @mbggenerated
     */
    public String getCountry_code() {
        return country_code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_users.country_code
     *
     * @param country_code the value for t_user_users.country_code
     *
     * @mbggenerated
     */
    public void setCountry_code(String country_code) {
        this.country_code = country_code == null ? null : country_code.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_users.phone_no
     *
     * @return the value of t_user_users.phone_no
     *
     * @mbggenerated
     */
    public String getPhone_no() {
        return phone_no;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_users.phone_no
     *
     * @param phone_no the value for t_user_users.phone_no
     *
     * @mbggenerated
     */
    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no == null ? null : phone_no.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_users.email
     *
     * @return the value of t_user_users.email
     *
     * @mbggenerated
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_users.email
     *
     * @param email the value for t_user_users.email
     *
     * @mbggenerated
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_users.longitude
     *
     * @return the value of t_user_users.longitude
     *
     * @mbggenerated
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_users.longitude
     *
     * @param longitude the value for t_user_users.longitude
     *
     * @mbggenerated
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_users.latitude
     *
     * @return the value of t_user_users.latitude
     *
     * @mbggenerated
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_users.latitude
     *
     * @param latitude the value for t_user_users.latitude
     *
     * @mbggenerated
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_users.place_id
     *
     * @return the value of t_user_users.place_id
     *
     * @mbggenerated
     */
    public String getPlace_id() {
        return place_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_users.place_id
     *
     * @param place_id the value for t_user_users.place_id
     *
     * @mbggenerated
     */
    public void setPlace_id(String place_id) {
        this.place_id = place_id == null ? null : place_id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_users.address
     *
     * @return the value of t_user_users.address
     *
     * @mbggenerated
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_users.address
     *
     * @param address the value for t_user_users.address
     *
     * @mbggenerated
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_users.avatar_origin
     *
     * @return the value of t_user_users.avatar_origin
     *
     * @mbggenerated
     */
    public String getAvatar_origin() {
        return avatar_origin;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_users.avatar_origin
     *
     * @param avatar_origin the value for t_user_users.avatar_origin
     *
     * @mbggenerated
     */
    public void setAvatar_origin(String avatar_origin) {
        this.avatar_origin = avatar_origin == null ? null : avatar_origin.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_users.avatar_thumb
     *
     * @return the value of t_user_users.avatar_thumb
     *
     * @mbggenerated
     */
    public String getAvatar_thumb() {
        return avatar_thumb;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_users.avatar_thumb
     *
     * @param avatar_thumb the value for t_user_users.avatar_thumb
     *
     * @mbggenerated
     */
    public void setAvatar_thumb(String avatar_thumb) {
        this.avatar_thumb = avatar_thumb == null ? null : avatar_thumb.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_users.state
     *
     * @return the value of t_user_users.state
     *
     * @mbggenerated
     */
    public Byte getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_users.state
     *
     * @param state the value for t_user_users.state
     *
     * @mbggenerated
     */
    public void setState(Byte state) {
        this.state = state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_users.state_date
     *
     * @return the value of t_user_users.state_date
     *
     * @mbggenerated
     */
    public Date getState_date() {
        return state_date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_users.state_date
     *
     * @param state_date the value for t_user_users.state_date
     *
     * @mbggenerated
     */
    public void setState_date(Date state_date) {
        this.state_date = state_date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_users.create_date
     *
     * @return the value of t_user_users.create_date
     *
     * @mbggenerated
     */
    public Date getCreate_date() {
        return create_date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_users.create_date
     *
     * @param create_date the value for t_user_users.create_date
     *
     * @mbggenerated
     */
    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_users.cur_device_id
     *
     * @return the value of t_user_users.cur_device_id
     *
     * @mbggenerated
     */
    public String getCur_device_id() {
        return cur_device_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_users.cur_device_id
     *
     * @param cur_device_id the value for t_user_users.cur_device_id
     *
     * @mbggenerated
     */
    public void setCur_device_id(String cur_device_id) {
        this.cur_device_id = cur_device_id == null ? null : cur_device_id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_users.cur_token
     *
     * @return the value of t_user_users.cur_token
     *
     * @mbggenerated
     */
    public String getCur_token() {
        return cur_token;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_users.cur_token
     *
     * @param cur_token the value for t_user_users.cur_token
     *
     * @mbggenerated
     */
    public void setCur_token(String cur_token) {
        this.cur_token = cur_token == null ? null : cur_token.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_users.question_notify
     *
     * @return the value of t_user_users.question_notify
     *
     * @mbggenerated
     */
    public Byte getQuestion_notify() {
        return question_notify;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_users.question_notify
     *
     * @param question_notify the value for t_user_users.question_notify
     *
     * @mbggenerated
     */
    public void setQuestion_notify(Byte question_notify) {
        this.question_notify = question_notify;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_users.cur_version
     *
     * @return the value of t_user_users.cur_version
     *
     * @mbggenerated
     */
    public String getCur_version() {
        return cur_version;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_users.cur_version
     *
     * @param cur_version the value for t_user_users.cur_version
     *
     * @mbggenerated
     */
    public void setCur_version(String cur_version) {
        this.cur_version = cur_version == null ? null : cur_version.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_users.cur_device_type
     *
     * @return the value of t_user_users.cur_device_type
     *
     * @mbggenerated
     */
    public String getCur_device_type() {
        return cur_device_type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_users.cur_device_type
     *
     * @param cur_device_type the value for t_user_users.cur_device_type
     *
     * @mbggenerated
     */
    public void setCur_device_type(String cur_device_type) {
        this.cur_device_type = cur_device_type == null ? null : cur_device_type.trim();
    }
}