package com.samchat.common.beans.auto.db.entitybeans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TAdvertisementSendLogExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_advertisement_send_log
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_advertisement_send_log
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_advertisement_send_log
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_advertisement_send_log
     *
     * @mbggenerated
     */
    public TAdvertisementSendLogExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_advertisement_send_log
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_advertisement_send_log
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_advertisement_send_log
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_advertisement_send_log
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_advertisement_send_log
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_advertisement_send_log
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_advertisement_send_log
     *
     * @mbggenerated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_advertisement_send_log
     *
     * @mbggenerated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_advertisement_send_log
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_advertisement_send_log
     *
     * @mbggenerated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_advertisement_send_log
     *
     * @mbggenerated
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andLog_idIsNull() {
            addCriterion("log_id is null");
            return (Criteria) this;
        }

        public Criteria andLog_idIsNotNull() {
            addCriterion("log_id is not null");
            return (Criteria) this;
        }

        public Criteria andLog_idEqualTo(Long value) {
            addCriterion("log_id =", value, "log_id");
            return (Criteria) this;
        }

        public Criteria andLog_idNotEqualTo(Long value) {
            addCriterion("log_id <>", value, "log_id");
            return (Criteria) this;
        }

        public Criteria andLog_idGreaterThan(Long value) {
            addCriterion("log_id >", value, "log_id");
            return (Criteria) this;
        }

        public Criteria andLog_idGreaterThanOrEqualTo(Long value) {
            addCriterion("log_id >=", value, "log_id");
            return (Criteria) this;
        }

        public Criteria andLog_idLessThan(Long value) {
            addCriterion("log_id <", value, "log_id");
            return (Criteria) this;
        }

        public Criteria andLog_idLessThanOrEqualTo(Long value) {
            addCriterion("log_id <=", value, "log_id");
            return (Criteria) this;
        }

        public Criteria andLog_idIn(List<Long> values) {
            addCriterion("log_id in", values, "log_id");
            return (Criteria) this;
        }

        public Criteria andLog_idNotIn(List<Long> values) {
            addCriterion("log_id not in", values, "log_id");
            return (Criteria) this;
        }

        public Criteria andLog_idBetween(Long value1, Long value2) {
            addCriterion("log_id between", value1, value2, "log_id");
            return (Criteria) this;
        }

        public Criteria andLog_idNotBetween(Long value1, Long value2) {
            addCriterion("log_id not between", value1, value2, "log_id");
            return (Criteria) this;
        }

        public Criteria andAds_idIsNull() {
            addCriterion("ads_id is null");
            return (Criteria) this;
        }

        public Criteria andAds_idIsNotNull() {
            addCriterion("ads_id is not null");
            return (Criteria) this;
        }

        public Criteria andAds_idEqualTo(Long value) {
            addCriterion("ads_id =", value, "ads_id");
            return (Criteria) this;
        }

        public Criteria andAds_idNotEqualTo(Long value) {
            addCriterion("ads_id <>", value, "ads_id");
            return (Criteria) this;
        }

        public Criteria andAds_idGreaterThan(Long value) {
            addCriterion("ads_id >", value, "ads_id");
            return (Criteria) this;
        }

        public Criteria andAds_idGreaterThanOrEqualTo(Long value) {
            addCriterion("ads_id >=", value, "ads_id");
            return (Criteria) this;
        }

        public Criteria andAds_idLessThan(Long value) {
            addCriterion("ads_id <", value, "ads_id");
            return (Criteria) this;
        }

        public Criteria andAds_idLessThanOrEqualTo(Long value) {
            addCriterion("ads_id <=", value, "ads_id");
            return (Criteria) this;
        }

        public Criteria andAds_idIn(List<Long> values) {
            addCriterion("ads_id in", values, "ads_id");
            return (Criteria) this;
        }

        public Criteria andAds_idNotIn(List<Long> values) {
            addCriterion("ads_id not in", values, "ads_id");
            return (Criteria) this;
        }

        public Criteria andAds_idBetween(Long value1, Long value2) {
            addCriterion("ads_id between", value1, value2, "ads_id");
            return (Criteria) this;
        }

        public Criteria andAds_idNotBetween(Long value1, Long value2) {
            addCriterion("ads_id not between", value1, value2, "ads_id");
            return (Criteria) this;
        }

        public Criteria andUser_idIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUser_idIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUser_idEqualTo(Long value) {
            addCriterion("user_id =", value, "user_id");
            return (Criteria) this;
        }

        public Criteria andUser_idNotEqualTo(Long value) {
            addCriterion("user_id <>", value, "user_id");
            return (Criteria) this;
        }

        public Criteria andUser_idGreaterThan(Long value) {
            addCriterion("user_id >", value, "user_id");
            return (Criteria) this;
        }

        public Criteria andUser_idGreaterThanOrEqualTo(Long value) {
            addCriterion("user_id >=", value, "user_id");
            return (Criteria) this;
        }

        public Criteria andUser_idLessThan(Long value) {
            addCriterion("user_id <", value, "user_id");
            return (Criteria) this;
        }

        public Criteria andUser_idLessThanOrEqualTo(Long value) {
            addCriterion("user_id <=", value, "user_id");
            return (Criteria) this;
        }

        public Criteria andUser_idIn(List<Long> values) {
            addCriterion("user_id in", values, "user_id");
            return (Criteria) this;
        }

        public Criteria andUser_idNotIn(List<Long> values) {
            addCriterion("user_id not in", values, "user_id");
            return (Criteria) this;
        }

        public Criteria andUser_idBetween(Long value1, Long value2) {
            addCriterion("user_id between", value1, value2, "user_id");
            return (Criteria) this;
        }

        public Criteria andUser_idNotBetween(Long value1, Long value2) {
            addCriterion("user_id not between", value1, value2, "user_id");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(Byte value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(Byte value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(Byte value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(Byte value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(Byte value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(Byte value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<Byte> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<Byte> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(Byte value1, Byte value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(Byte value1, Byte value2) {
            addCriterion("state not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andSend_dateIsNull() {
            addCriterion("send_date is null");
            return (Criteria) this;
        }

        public Criteria andSend_dateIsNotNull() {
            addCriterion("send_date is not null");
            return (Criteria) this;
        }

        public Criteria andSend_dateEqualTo(Date value) {
            addCriterion("send_date =", value, "send_date");
            return (Criteria) this;
        }

        public Criteria andSend_dateNotEqualTo(Date value) {
            addCriterion("send_date <>", value, "send_date");
            return (Criteria) this;
        }

        public Criteria andSend_dateGreaterThan(Date value) {
            addCriterion("send_date >", value, "send_date");
            return (Criteria) this;
        }

        public Criteria andSend_dateGreaterThanOrEqualTo(Date value) {
            addCriterion("send_date >=", value, "send_date");
            return (Criteria) this;
        }

        public Criteria andSend_dateLessThan(Date value) {
            addCriterion("send_date <", value, "send_date");
            return (Criteria) this;
        }

        public Criteria andSend_dateLessThanOrEqualTo(Date value) {
            addCriterion("send_date <=", value, "send_date");
            return (Criteria) this;
        }

        public Criteria andSend_dateIn(List<Date> values) {
            addCriterion("send_date in", values, "send_date");
            return (Criteria) this;
        }

        public Criteria andSend_dateNotIn(List<Date> values) {
            addCriterion("send_date not in", values, "send_date");
            return (Criteria) this;
        }

        public Criteria andSend_dateBetween(Date value1, Date value2) {
            addCriterion("send_date between", value1, value2, "send_date");
            return (Criteria) this;
        }

        public Criteria andSend_dateNotBetween(Date value1, Date value2) {
            addCriterion("send_date not between", value1, value2, "send_date");
            return (Criteria) this;
        }

        public Criteria andSend_countIsNull() {
            addCriterion("send_count is null");
            return (Criteria) this;
        }

        public Criteria andSend_countIsNotNull() {
            addCriterion("send_count is not null");
            return (Criteria) this;
        }

        public Criteria andSend_countEqualTo(Integer value) {
            addCriterion("send_count =", value, "send_count");
            return (Criteria) this;
        }

        public Criteria andSend_countNotEqualTo(Integer value) {
            addCriterion("send_count <>", value, "send_count");
            return (Criteria) this;
        }

        public Criteria andSend_countGreaterThan(Integer value) {
            addCriterion("send_count >", value, "send_count");
            return (Criteria) this;
        }

        public Criteria andSend_countGreaterThanOrEqualTo(Integer value) {
            addCriterion("send_count >=", value, "send_count");
            return (Criteria) this;
        }

        public Criteria andSend_countLessThan(Integer value) {
            addCriterion("send_count <", value, "send_count");
            return (Criteria) this;
        }

        public Criteria andSend_countLessThanOrEqualTo(Integer value) {
            addCriterion("send_count <=", value, "send_count");
            return (Criteria) this;
        }

        public Criteria andSend_countIn(List<Integer> values) {
            addCriterion("send_count in", values, "send_count");
            return (Criteria) this;
        }

        public Criteria andSend_countNotIn(List<Integer> values) {
            addCriterion("send_count not in", values, "send_count");
            return (Criteria) this;
        }

        public Criteria andSend_countBetween(Integer value1, Integer value2) {
            addCriterion("send_count between", value1, value2, "send_count");
            return (Criteria) this;
        }

        public Criteria andSend_countNotBetween(Integer value1, Integer value2) {
            addCriterion("send_count not between", value1, value2, "send_count");
            return (Criteria) this;
        }

        public Criteria andClient_idIsNull() {
            addCriterion("client_id is null");
            return (Criteria) this;
        }

        public Criteria andClient_idIsNotNull() {
            addCriterion("client_id is not null");
            return (Criteria) this;
        }

        public Criteria andClient_idEqualTo(String value) {
            addCriterion("client_id =", value, "client_id");
            return (Criteria) this;
        }

        public Criteria andClient_idNotEqualTo(String value) {
            addCriterion("client_id <>", value, "client_id");
            return (Criteria) this;
        }

        public Criteria andClient_idGreaterThan(String value) {
            addCriterion("client_id >", value, "client_id");
            return (Criteria) this;
        }

        public Criteria andClient_idGreaterThanOrEqualTo(String value) {
            addCriterion("client_id >=", value, "client_id");
            return (Criteria) this;
        }

        public Criteria andClient_idLessThan(String value) {
            addCriterion("client_id <", value, "client_id");
            return (Criteria) this;
        }

        public Criteria andClient_idLessThanOrEqualTo(String value) {
            addCriterion("client_id <=", value, "client_id");
            return (Criteria) this;
        }

        public Criteria andClient_idLike(String value) {
            addCriterion("client_id like", value, "client_id");
            return (Criteria) this;
        }

        public Criteria andClient_idNotLike(String value) {
            addCriterion("client_id not like", value, "client_id");
            return (Criteria) this;
        }

        public Criteria andClient_idIn(List<String> values) {
            addCriterion("client_id in", values, "client_id");
            return (Criteria) this;
        }

        public Criteria andClient_idNotIn(List<String> values) {
            addCriterion("client_id not in", values, "client_id");
            return (Criteria) this;
        }

        public Criteria andClient_idBetween(String value1, String value2) {
            addCriterion("client_id between", value1, value2, "client_id");
            return (Criteria) this;
        }

        public Criteria andClient_idNotBetween(String value1, String value2) {
            addCriterion("client_id not between", value1, value2, "client_id");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andSharding_flagIsNull() {
            addCriterion("sharding_flag is null");
            return (Criteria) this;
        }

        public Criteria andSharding_flagIsNotNull() {
            addCriterion("sharding_flag is not null");
            return (Criteria) this;
        }

        public Criteria andSharding_flagEqualTo(Integer value) {
            addCriterion("sharding_flag =", value, "sharding_flag");
            return (Criteria) this;
        }

        public Criteria andSharding_flagNotEqualTo(Integer value) {
            addCriterion("sharding_flag <>", value, "sharding_flag");
            return (Criteria) this;
        }

        public Criteria andSharding_flagGreaterThan(Integer value) {
            addCriterion("sharding_flag >", value, "sharding_flag");
            return (Criteria) this;
        }

        public Criteria andSharding_flagGreaterThanOrEqualTo(Integer value) {
            addCriterion("sharding_flag >=", value, "sharding_flag");
            return (Criteria) this;
        }

        public Criteria andSharding_flagLessThan(Integer value) {
            addCriterion("sharding_flag <", value, "sharding_flag");
            return (Criteria) this;
        }

        public Criteria andSharding_flagLessThanOrEqualTo(Integer value) {
            addCriterion("sharding_flag <=", value, "sharding_flag");
            return (Criteria) this;
        }

        public Criteria andSharding_flagIn(List<Integer> values) {
            addCriterion("sharding_flag in", values, "sharding_flag");
            return (Criteria) this;
        }

        public Criteria andSharding_flagNotIn(List<Integer> values) {
            addCriterion("sharding_flag not in", values, "sharding_flag");
            return (Criteria) this;
        }

        public Criteria andSharding_flagBetween(Integer value1, Integer value2) {
            addCriterion("sharding_flag between", value1, value2, "sharding_flag");
            return (Criteria) this;
        }

        public Criteria andSharding_flagNotBetween(Integer value1, Integer value2) {
            addCriterion("sharding_flag not between", value1, value2, "sharding_flag");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_advertisement_send_log
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_advertisement_send_log
     *
     * @mbggenerated
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}