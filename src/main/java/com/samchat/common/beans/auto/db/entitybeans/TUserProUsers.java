package com.samchat.common.beans.auto.db.entitybeans;

import com.samchat.common.beans.auto.db.BaseEntity;
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
     * This field corresponds to the database column t_user_pro_users.user_id
     *
     * @mbggenerated
     */
    private Long user_id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_pro_users.company_name
     *
     * @mbggenerated
     */
    private String company_name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_pro_users.service_category
     *
     * @mbggenerated
     */
    private String service_category;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_pro_users.service_description
     *
     * @mbggenerated
     */
    private String service_description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_pro_users.country_code
     *
     * @mbggenerated
     */
    private String country_code;

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
     * This field corresponds to the database column t_user_pro_users.longitude
     *
     * @mbggenerated
     */
    private String longitude;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_pro_users.latitude
     *
     * @mbggenerated
     */
    private String latitude;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_pro_users.place_id
     *
     * @mbggenerated
     */
    private String place_id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_pro_users.address
     *
     * @mbggenerated
     */
    private String address;

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
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_pro_users.cur_token
     *
     * @mbggenerated
     */
    private String cur_token;

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
     * This method returns the value of the database column t_user_pro_users.user_id
     *
     * @return the value of t_user_pro_users.user_id
     *
     * @mbggenerated
     */
    public Long getUser_id() {
        return user_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_pro_users.user_id
     *
     * @param user_id the value for t_user_pro_users.user_id
     *
     * @mbggenerated
     */
    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_pro_users.company_name
     *
     * @return the value of t_user_pro_users.company_name
     *
     * @mbggenerated
     */
    public String getCompany_name() {
        return company_name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_pro_users.company_name
     *
     * @param company_name the value for t_user_pro_users.company_name
     *
     * @mbggenerated
     */
    public void setCompany_name(String company_name) {
        this.company_name = company_name == null ? null : company_name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_pro_users.service_category
     *
     * @return the value of t_user_pro_users.service_category
     *
     * @mbggenerated
     */
    public String getService_category() {
        return service_category;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_pro_users.service_category
     *
     * @param service_category the value for t_user_pro_users.service_category
     *
     * @mbggenerated
     */
    public void setService_category(String service_category) {
        this.service_category = service_category == null ? null : service_category.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_pro_users.service_description
     *
     * @return the value of t_user_pro_users.service_description
     *
     * @mbggenerated
     */
    public String getService_description() {
        return service_description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_pro_users.service_description
     *
     * @param service_description the value for t_user_pro_users.service_description
     *
     * @mbggenerated
     */
    public void setService_description(String service_description) {
        this.service_description = service_description == null ? null : service_description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_pro_users.country_code
     *
     * @return the value of t_user_pro_users.country_code
     *
     * @mbggenerated
     */
    public String getCountry_code() {
        return country_code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_pro_users.country_code
     *
     * @param country_code the value for t_user_pro_users.country_code
     *
     * @mbggenerated
     */
    public void setCountry_code(String country_code) {
        this.country_code = country_code == null ? null : country_code.trim();
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
     * This method returns the value of the database column t_user_pro_users.longitude
     *
     * @return the value of t_user_pro_users.longitude
     *
     * @mbggenerated
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_pro_users.longitude
     *
     * @param longitude the value for t_user_pro_users.longitude
     *
     * @mbggenerated
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_pro_users.latitude
     *
     * @return the value of t_user_pro_users.latitude
     *
     * @mbggenerated
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_pro_users.latitude
     *
     * @param latitude the value for t_user_pro_users.latitude
     *
     * @mbggenerated
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_pro_users.place_id
     *
     * @return the value of t_user_pro_users.place_id
     *
     * @mbggenerated
     */
    public String getPlace_id() {
        return place_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_pro_users.place_id
     *
     * @param place_id the value for t_user_pro_users.place_id
     *
     * @mbggenerated
     */
    public void setPlace_id(String place_id) {
        this.place_id = place_id == null ? null : place_id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_pro_users.address
     *
     * @return the value of t_user_pro_users.address
     *
     * @mbggenerated
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_pro_users.address
     *
     * @param address the value for t_user_pro_users.address
     *
     * @mbggenerated
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
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

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_pro_users.cur_token
     *
     * @return the value of t_user_pro_users.cur_token
     *
     * @mbggenerated
     */
    public String getCur_token() {
        return cur_token;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_pro_users.cur_token
     *
     * @param cur_token the value for t_user_pro_users.cur_token
     *
     * @mbggenerated
     */
    public void setCur_token(String cur_token) {
        this.cur_token = cur_token == null ? null : cur_token.trim();
    }
}