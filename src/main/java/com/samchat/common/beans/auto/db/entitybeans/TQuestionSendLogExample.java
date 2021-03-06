package com.samchat.common.beans.auto.db.entitybeans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TQuestionSendLogExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_question_send_log
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_question_send_log
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_question_send_log
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_question_send_log
     *
     * @mbggenerated
     */
    public TQuestionSendLogExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_question_send_log
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_question_send_log
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_question_send_log
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_question_send_log
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_question_send_log
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_question_send_log
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_question_send_log
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
     * This method corresponds to the database table t_question_send_log
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
     * This method corresponds to the database table t_question_send_log
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_question_send_log
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
     * This class corresponds to the database table t_question_send_log
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

        public Criteria andQst_log_idIsNull() {
            addCriterion("qst_log_id is null");
            return (Criteria) this;
        }

        public Criteria andQst_log_idIsNotNull() {
            addCriterion("qst_log_id is not null");
            return (Criteria) this;
        }

        public Criteria andQst_log_idEqualTo(Long value) {
            addCriterion("qst_log_id =", value, "qst_log_id");
            return (Criteria) this;
        }

        public Criteria andQst_log_idNotEqualTo(Long value) {
            addCriterion("qst_log_id <>", value, "qst_log_id");
            return (Criteria) this;
        }

        public Criteria andQst_log_idGreaterThan(Long value) {
            addCriterion("qst_log_id >", value, "qst_log_id");
            return (Criteria) this;
        }

        public Criteria andQst_log_idGreaterThanOrEqualTo(Long value) {
            addCriterion("qst_log_id >=", value, "qst_log_id");
            return (Criteria) this;
        }

        public Criteria andQst_log_idLessThan(Long value) {
            addCriterion("qst_log_id <", value, "qst_log_id");
            return (Criteria) this;
        }

        public Criteria andQst_log_idLessThanOrEqualTo(Long value) {
            addCriterion("qst_log_id <=", value, "qst_log_id");
            return (Criteria) this;
        }

        public Criteria andQst_log_idIn(List<Long> values) {
            addCriterion("qst_log_id in", values, "qst_log_id");
            return (Criteria) this;
        }

        public Criteria andQst_log_idNotIn(List<Long> values) {
            addCriterion("qst_log_id not in", values, "qst_log_id");
            return (Criteria) this;
        }

        public Criteria andQst_log_idBetween(Long value1, Long value2) {
            addCriterion("qst_log_id between", value1, value2, "qst_log_id");
            return (Criteria) this;
        }

        public Criteria andQst_log_idNotBetween(Long value1, Long value2) {
            addCriterion("qst_log_id not between", value1, value2, "qst_log_id");
            return (Criteria) this;
        }

        public Criteria andQst_idIsNull() {
            addCriterion("qst_id is null");
            return (Criteria) this;
        }

        public Criteria andQst_idIsNotNull() {
            addCriterion("qst_id is not null");
            return (Criteria) this;
        }

        public Criteria andQst_idEqualTo(Long value) {
            addCriterion("qst_id =", value, "qst_id");
            return (Criteria) this;
        }

        public Criteria andQst_idNotEqualTo(Long value) {
            addCriterion("qst_id <>", value, "qst_id");
            return (Criteria) this;
        }

        public Criteria andQst_idGreaterThan(Long value) {
            addCriterion("qst_id >", value, "qst_id");
            return (Criteria) this;
        }

        public Criteria andQst_idGreaterThanOrEqualTo(Long value) {
            addCriterion("qst_id >=", value, "qst_id");
            return (Criteria) this;
        }

        public Criteria andQst_idLessThan(Long value) {
            addCriterion("qst_id <", value, "qst_id");
            return (Criteria) this;
        }

        public Criteria andQst_idLessThanOrEqualTo(Long value) {
            addCriterion("qst_id <=", value, "qst_id");
            return (Criteria) this;
        }

        public Criteria andQst_idIn(List<Long> values) {
            addCriterion("qst_id in", values, "qst_id");
            return (Criteria) this;
        }

        public Criteria andQst_idNotIn(List<Long> values) {
            addCriterion("qst_id not in", values, "qst_id");
            return (Criteria) this;
        }

        public Criteria andQst_idBetween(Long value1, Long value2) {
            addCriterion("qst_id between", value1, value2, "qst_id");
            return (Criteria) this;
        }

        public Criteria andQst_idNotBetween(Long value1, Long value2) {
            addCriterion("qst_id not between", value1, value2, "qst_id");
            return (Criteria) this;
        }

        public Criteria andUser_id_proIsNull() {
            addCriterion("user_id_pro is null");
            return (Criteria) this;
        }

        public Criteria andUser_id_proIsNotNull() {
            addCriterion("user_id_pro is not null");
            return (Criteria) this;
        }

        public Criteria andUser_id_proEqualTo(Long value) {
            addCriterion("user_id_pro =", value, "user_id_pro");
            return (Criteria) this;
        }

        public Criteria andUser_id_proNotEqualTo(Long value) {
            addCriterion("user_id_pro <>", value, "user_id_pro");
            return (Criteria) this;
        }

        public Criteria andUser_id_proGreaterThan(Long value) {
            addCriterion("user_id_pro >", value, "user_id_pro");
            return (Criteria) this;
        }

        public Criteria andUser_id_proGreaterThanOrEqualTo(Long value) {
            addCriterion("user_id_pro >=", value, "user_id_pro");
            return (Criteria) this;
        }

        public Criteria andUser_id_proLessThan(Long value) {
            addCriterion("user_id_pro <", value, "user_id_pro");
            return (Criteria) this;
        }

        public Criteria andUser_id_proLessThanOrEqualTo(Long value) {
            addCriterion("user_id_pro <=", value, "user_id_pro");
            return (Criteria) this;
        }

        public Criteria andUser_id_proIn(List<Long> values) {
            addCriterion("user_id_pro in", values, "user_id_pro");
            return (Criteria) this;
        }

        public Criteria andUser_id_proNotIn(List<Long> values) {
            addCriterion("user_id_pro not in", values, "user_id_pro");
            return (Criteria) this;
        }

        public Criteria andUser_id_proBetween(Long value1, Long value2) {
            addCriterion("user_id_pro between", value1, value2, "user_id_pro");
            return (Criteria) this;
        }

        public Criteria andUser_id_proNotBetween(Long value1, Long value2) {
            addCriterion("user_id_pro not between", value1, value2, "user_id_pro");
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

        public Criteria andState_dateIsNull() {
            addCriterion("state_date is null");
            return (Criteria) this;
        }

        public Criteria andState_dateIsNotNull() {
            addCriterion("state_date is not null");
            return (Criteria) this;
        }

        public Criteria andState_dateEqualTo(Date value) {
            addCriterion("state_date =", value, "state_date");
            return (Criteria) this;
        }

        public Criteria andState_dateNotEqualTo(Date value) {
            addCriterion("state_date <>", value, "state_date");
            return (Criteria) this;
        }

        public Criteria andState_dateGreaterThan(Date value) {
            addCriterion("state_date >", value, "state_date");
            return (Criteria) this;
        }

        public Criteria andState_dateGreaterThanOrEqualTo(Date value) {
            addCriterion("state_date >=", value, "state_date");
            return (Criteria) this;
        }

        public Criteria andState_dateLessThan(Date value) {
            addCriterion("state_date <", value, "state_date");
            return (Criteria) this;
        }

        public Criteria andState_dateLessThanOrEqualTo(Date value) {
            addCriterion("state_date <=", value, "state_date");
            return (Criteria) this;
        }

        public Criteria andState_dateIn(List<Date> values) {
            addCriterion("state_date in", values, "state_date");
            return (Criteria) this;
        }

        public Criteria andState_dateNotIn(List<Date> values) {
            addCriterion("state_date not in", values, "state_date");
            return (Criteria) this;
        }

        public Criteria andState_dateBetween(Date value1, Date value2) {
            addCriterion("state_date between", value1, value2, "state_date");
            return (Criteria) this;
        }

        public Criteria andState_dateNotBetween(Date value1, Date value2) {
            addCriterion("state_date not between", value1, value2, "state_date");
            return (Criteria) this;
        }

        public Criteria andCreate_dateIsNull() {
            addCriterion("create_date is null");
            return (Criteria) this;
        }

        public Criteria andCreate_dateIsNotNull() {
            addCriterion("create_date is not null");
            return (Criteria) this;
        }

        public Criteria andCreate_dateEqualTo(Date value) {
            addCriterion("create_date =", value, "create_date");
            return (Criteria) this;
        }

        public Criteria andCreate_dateNotEqualTo(Date value) {
            addCriterion("create_date <>", value, "create_date");
            return (Criteria) this;
        }

        public Criteria andCreate_dateGreaterThan(Date value) {
            addCriterion("create_date >", value, "create_date");
            return (Criteria) this;
        }

        public Criteria andCreate_dateGreaterThanOrEqualTo(Date value) {
            addCriterion("create_date >=", value, "create_date");
            return (Criteria) this;
        }

        public Criteria andCreate_dateLessThan(Date value) {
            addCriterion("create_date <", value, "create_date");
            return (Criteria) this;
        }

        public Criteria andCreate_dateLessThanOrEqualTo(Date value) {
            addCriterion("create_date <=", value, "create_date");
            return (Criteria) this;
        }

        public Criteria andCreate_dateIn(List<Date> values) {
            addCriterion("create_date in", values, "create_date");
            return (Criteria) this;
        }

        public Criteria andCreate_dateNotIn(List<Date> values) {
            addCriterion("create_date not in", values, "create_date");
            return (Criteria) this;
        }

        public Criteria andCreate_dateBetween(Date value1, Date value2) {
            addCriterion("create_date between", value1, value2, "create_date");
            return (Criteria) this;
        }

        public Criteria andCreate_dateNotBetween(Date value1, Date value2) {
            addCriterion("create_date not between", value1, value2, "create_date");
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
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_question_send_log
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
     * This class corresponds to the database table t_question_send_log
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