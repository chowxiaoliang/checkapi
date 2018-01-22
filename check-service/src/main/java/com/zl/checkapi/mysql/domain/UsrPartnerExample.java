package com.zl.checkapi.mysql.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class UsrPartnerExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UsrPartnerExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andCustomIdIsNull() {
            addCriterion("custom_id is null");
            return (Criteria) this;
        }

        public Criteria andCustomIdIsNotNull() {
            addCriterion("custom_id is not null");
            return (Criteria) this;
        }

        public Criteria andCustomIdEqualTo(String value) {
            addCriterion("custom_id =", value, "customId");
            return (Criteria) this;
        }

        public Criteria andCustomIdNotEqualTo(String value) {
            addCriterion("custom_id <>", value, "customId");
            return (Criteria) this;
        }

        public Criteria andCustomIdGreaterThan(String value) {
            addCriterion("custom_id >", value, "customId");
            return (Criteria) this;
        }

        public Criteria andCustomIdGreaterThanOrEqualTo(String value) {
            addCriterion("custom_id >=", value, "customId");
            return (Criteria) this;
        }

        public Criteria andCustomIdLessThan(String value) {
            addCriterion("custom_id <", value, "customId");
            return (Criteria) this;
        }

        public Criteria andCustomIdLessThanOrEqualTo(String value) {
            addCriterion("custom_id <=", value, "customId");
            return (Criteria) this;
        }

        public Criteria andCustomIdLike(String value) {
            addCriterion("custom_id like", value, "customId");
            return (Criteria) this;
        }

        public Criteria andCustomIdNotLike(String value) {
            addCriterion("custom_id not like", value, "customId");
            return (Criteria) this;
        }

        public Criteria andCustomIdIn(List<String> values) {
            addCriterion("custom_id in", values, "customId");
            return (Criteria) this;
        }

        public Criteria andCustomIdNotIn(List<String> values) {
            addCriterion("custom_id not in", values, "customId");
            return (Criteria) this;
        }

        public Criteria andCustomIdBetween(String value1, String value2) {
            addCriterion("custom_id between", value1, value2, "customId");
            return (Criteria) this;
        }

        public Criteria andCustomIdNotBetween(String value1, String value2) {
            addCriterion("custom_id not between", value1, value2, "customId");
            return (Criteria) this;
        }

        public Criteria andPartnerIdIsNull() {
            addCriterion("partner_id is null");
            return (Criteria) this;
        }

        public Criteria andPartnerIdIsNotNull() {
            addCriterion("partner_id is not null");
            return (Criteria) this;
        }

        public Criteria andPartnerIdEqualTo(String value) {
            addCriterion("partner_id =", value, "partnerId");
            return (Criteria) this;
        }

        public Criteria andPartnerIdNotEqualTo(String value) {
            addCriterion("partner_id <>", value, "partnerId");
            return (Criteria) this;
        }

        public Criteria andPartnerIdGreaterThan(String value) {
            addCriterion("partner_id >", value, "partnerId");
            return (Criteria) this;
        }

        public Criteria andPartnerIdGreaterThanOrEqualTo(String value) {
            addCriterion("partner_id >=", value, "partnerId");
            return (Criteria) this;
        }

        public Criteria andPartnerIdLessThan(String value) {
            addCriterion("partner_id <", value, "partnerId");
            return (Criteria) this;
        }

        public Criteria andPartnerIdLessThanOrEqualTo(String value) {
            addCriterion("partner_id <=", value, "partnerId");
            return (Criteria) this;
        }

        public Criteria andPartnerIdLike(String value) {
            addCriterion("partner_id like", value, "partnerId");
            return (Criteria) this;
        }

        public Criteria andPartnerIdNotLike(String value) {
            addCriterion("partner_id not like", value, "partnerId");
            return (Criteria) this;
        }

        public Criteria andPartnerIdIn(List<String> values) {
            addCriterion("partner_id in", values, "partnerId");
            return (Criteria) this;
        }

        public Criteria andPartnerIdNotIn(List<String> values) {
            addCriterion("partner_id not in", values, "partnerId");
            return (Criteria) this;
        }

        public Criteria andPartnerIdBetween(String value1, String value2) {
            addCriterion("partner_id between", value1, value2, "partnerId");
            return (Criteria) this;
        }

        public Criteria andPartnerIdNotBetween(String value1, String value2) {
            addCriterion("partner_id not between", value1, value2, "partnerId");
            return (Criteria) this;
        }

        public Criteria andOperationIdIsNull() {
            addCriterion("operation_id is null");
            return (Criteria) this;
        }

        public Criteria andOperationIdIsNotNull() {
            addCriterion("operation_id is not null");
            return (Criteria) this;
        }

        public Criteria andOperationIdEqualTo(String value) {
            addCriterion("operation_id =", value, "operationId");
            return (Criteria) this;
        }

        public Criteria andOperationIdNotEqualTo(String value) {
            addCriterion("operation_id <>", value, "operationId");
            return (Criteria) this;
        }

        public Criteria andOperationIdGreaterThan(String value) {
            addCriterion("operation_id >", value, "operationId");
            return (Criteria) this;
        }

        public Criteria andOperationIdGreaterThanOrEqualTo(String value) {
            addCriterion("operation_id >=", value, "operationId");
            return (Criteria) this;
        }

        public Criteria andOperationIdLessThan(String value) {
            addCriterion("operation_id <", value, "operationId");
            return (Criteria) this;
        }

        public Criteria andOperationIdLessThanOrEqualTo(String value) {
            addCriterion("operation_id <=", value, "operationId");
            return (Criteria) this;
        }

        public Criteria andOperationIdLike(String value) {
            addCriterion("operation_id like", value, "operationId");
            return (Criteria) this;
        }

        public Criteria andOperationIdNotLike(String value) {
            addCriterion("operation_id not like", value, "operationId");
            return (Criteria) this;
        }

        public Criteria andOperationIdIn(List<String> values) {
            addCriterion("operation_id in", values, "operationId");
            return (Criteria) this;
        }

        public Criteria andOperationIdNotIn(List<String> values) {
            addCriterion("operation_id not in", values, "operationId");
            return (Criteria) this;
        }

        public Criteria andOperationIdBetween(String value1, String value2) {
            addCriterion("operation_id between", value1, value2, "operationId");
            return (Criteria) this;
        }

        public Criteria andOperationIdNotBetween(String value1, String value2) {
            addCriterion("operation_id not between", value1, value2, "operationId");
            return (Criteria) this;
        }

        public Criteria andCustomAliasIsNull() {
            addCriterion("custom_alias is null");
            return (Criteria) this;
        }

        public Criteria andCustomAliasIsNotNull() {
            addCriterion("custom_alias is not null");
            return (Criteria) this;
        }

        public Criteria andCustomAliasEqualTo(String value) {
            addCriterion("custom_alias =", value, "customAlias");
            return (Criteria) this;
        }

        public Criteria andCustomAliasNotEqualTo(String value) {
            addCriterion("custom_alias <>", value, "customAlias");
            return (Criteria) this;
        }

        public Criteria andCustomAliasGreaterThan(String value) {
            addCriterion("custom_alias >", value, "customAlias");
            return (Criteria) this;
        }

        public Criteria andCustomAliasGreaterThanOrEqualTo(String value) {
            addCriterion("custom_alias >=", value, "customAlias");
            return (Criteria) this;
        }

        public Criteria andCustomAliasLessThan(String value) {
            addCriterion("custom_alias <", value, "customAlias");
            return (Criteria) this;
        }

        public Criteria andCustomAliasLessThanOrEqualTo(String value) {
            addCriterion("custom_alias <=", value, "customAlias");
            return (Criteria) this;
        }

        public Criteria andCustomAliasLike(String value) {
            addCriterion("custom_alias like", value, "customAlias");
            return (Criteria) this;
        }

        public Criteria andCustomAliasNotLike(String value) {
            addCriterion("custom_alias not like", value, "customAlias");
            return (Criteria) this;
        }

        public Criteria andCustomAliasIn(List<String> values) {
            addCriterion("custom_alias in", values, "customAlias");
            return (Criteria) this;
        }

        public Criteria andCustomAliasNotIn(List<String> values) {
            addCriterion("custom_alias not in", values, "customAlias");
            return (Criteria) this;
        }

        public Criteria andCustomAliasBetween(String value1, String value2) {
            addCriterion("custom_alias between", value1, value2, "customAlias");
            return (Criteria) this;
        }

        public Criteria andCustomAliasNotBetween(String value1, String value2) {
            addCriterion("custom_alias not between", value1, value2, "customAlias");
            return (Criteria) this;
        }

        public Criteria andPartnerStatusIsNull() {
            addCriterion("partner_status is null");
            return (Criteria) this;
        }

        public Criteria andPartnerStatusIsNotNull() {
            addCriterion("partner_status is not null");
            return (Criteria) this;
        }

        public Criteria andPartnerStatusEqualTo(Integer value) {
            addCriterion("partner_status =", value, "partnerStatus");
            return (Criteria) this;
        }

        public Criteria andPartnerStatusNotEqualTo(Integer value) {
            addCriterion("partner_status <>", value, "partnerStatus");
            return (Criteria) this;
        }

        public Criteria andPartnerStatusGreaterThan(Integer value) {
            addCriterion("partner_status >", value, "partnerStatus");
            return (Criteria) this;
        }

        public Criteria andPartnerStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("partner_status >=", value, "partnerStatus");
            return (Criteria) this;
        }

        public Criteria andPartnerStatusLessThan(Integer value) {
            addCriterion("partner_status <", value, "partnerStatus");
            return (Criteria) this;
        }

        public Criteria andPartnerStatusLessThanOrEqualTo(Integer value) {
            addCriterion("partner_status <=", value, "partnerStatus");
            return (Criteria) this;
        }

        public Criteria andPartnerStatusIn(List<Integer> values) {
            addCriterion("partner_status in", values, "partnerStatus");
            return (Criteria) this;
        }

        public Criteria andPartnerStatusNotIn(List<Integer> values) {
            addCriterion("partner_status not in", values, "partnerStatus");
            return (Criteria) this;
        }

        public Criteria andPartnerStatusBetween(Integer value1, Integer value2) {
            addCriterion("partner_status between", value1, value2, "partnerStatus");
            return (Criteria) this;
        }

        public Criteria andPartnerStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("partner_status not between", value1, value2, "partnerStatus");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andTrailDaysIsNull() {
            addCriterion("trail_days is null");
            return (Criteria) this;
        }

        public Criteria andTrailDaysIsNotNull() {
            addCriterion("trail_days is not null");
            return (Criteria) this;
        }

        public Criteria andTrailDaysEqualTo(Integer value) {
            addCriterion("trail_days =", value, "trailDays");
            return (Criteria) this;
        }

        public Criteria andTrailDaysNotEqualTo(Integer value) {
            addCriterion("trail_days <>", value, "trailDays");
            return (Criteria) this;
        }

        public Criteria andTrailDaysGreaterThan(Integer value) {
            addCriterion("trail_days >", value, "trailDays");
            return (Criteria) this;
        }

        public Criteria andTrailDaysGreaterThanOrEqualTo(Integer value) {
            addCriterion("trail_days >=", value, "trailDays");
            return (Criteria) this;
        }

        public Criteria andTrailDaysLessThan(Integer value) {
            addCriterion("trail_days <", value, "trailDays");
            return (Criteria) this;
        }

        public Criteria andTrailDaysLessThanOrEqualTo(Integer value) {
            addCriterion("trail_days <=", value, "trailDays");
            return (Criteria) this;
        }

        public Criteria andTrailDaysIn(List<Integer> values) {
            addCriterion("trail_days in", values, "trailDays");
            return (Criteria) this;
        }

        public Criteria andTrailDaysNotIn(List<Integer> values) {
            addCriterion("trail_days not in", values, "trailDays");
            return (Criteria) this;
        }

        public Criteria andTrailDaysBetween(Integer value1, Integer value2) {
            addCriterion("trail_days between", value1, value2, "trailDays");
            return (Criteria) this;
        }

        public Criteria andTrailDaysNotBetween(Integer value1, Integer value2) {
            addCriterion("trail_days not between", value1, value2, "trailDays");
            return (Criteria) this;
        }

        public Criteria andEffectiveDateIsNull() {
            addCriterion("effective_date is null");
            return (Criteria) this;
        }

        public Criteria andEffectiveDateIsNotNull() {
            addCriterion("effective_date is not null");
            return (Criteria) this;
        }

        public Criteria andEffectiveDateEqualTo(Date value) {
            addCriterionForJDBCDate("effective_date =", value, "effectiveDate");
            return (Criteria) this;
        }

        public Criteria andEffectiveDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("effective_date <>", value, "effectiveDate");
            return (Criteria) this;
        }

        public Criteria andEffectiveDateGreaterThan(Date value) {
            addCriterionForJDBCDate("effective_date >", value, "effectiveDate");
            return (Criteria) this;
        }

        public Criteria andEffectiveDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("effective_date >=", value, "effectiveDate");
            return (Criteria) this;
        }

        public Criteria andEffectiveDateLessThan(Date value) {
            addCriterionForJDBCDate("effective_date <", value, "effectiveDate");
            return (Criteria) this;
        }

        public Criteria andEffectiveDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("effective_date <=", value, "effectiveDate");
            return (Criteria) this;
        }

        public Criteria andEffectiveDateIn(List<Date> values) {
            addCriterionForJDBCDate("effective_date in", values, "effectiveDate");
            return (Criteria) this;
        }

        public Criteria andEffectiveDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("effective_date not in", values, "effectiveDate");
            return (Criteria) this;
        }

        public Criteria andEffectiveDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("effective_date between", value1, value2, "effectiveDate");
            return (Criteria) this;
        }

        public Criteria andEffectiveDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("effective_date not between", value1, value2, "effectiveDate");
            return (Criteria) this;
        }

        public Criteria andExpireDateIsNull() {
            addCriterion("expire_date is null");
            return (Criteria) this;
        }

        public Criteria andExpireDateIsNotNull() {
            addCriterion("expire_date is not null");
            return (Criteria) this;
        }

        public Criteria andExpireDateEqualTo(Date value) {
            addCriterionForJDBCDate("expire_date =", value, "expireDate");
            return (Criteria) this;
        }

        public Criteria andExpireDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("expire_date <>", value, "expireDate");
            return (Criteria) this;
        }

        public Criteria andExpireDateGreaterThan(Date value) {
            addCriterionForJDBCDate("expire_date >", value, "expireDate");
            return (Criteria) this;
        }

        public Criteria andExpireDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("expire_date >=", value, "expireDate");
            return (Criteria) this;
        }

        public Criteria andExpireDateLessThan(Date value) {
            addCriterionForJDBCDate("expire_date <", value, "expireDate");
            return (Criteria) this;
        }

        public Criteria andExpireDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("expire_date <=", value, "expireDate");
            return (Criteria) this;
        }

        public Criteria andExpireDateIn(List<Date> values) {
            addCriterionForJDBCDate("expire_date in", values, "expireDate");
            return (Criteria) this;
        }

        public Criteria andExpireDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("expire_date not in", values, "expireDate");
            return (Criteria) this;
        }

        public Criteria andExpireDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("expire_date between", value1, value2, "expireDate");
            return (Criteria) this;
        }

        public Criteria andExpireDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("expire_date not between", value1, value2, "expireDate");
            return (Criteria) this;
        }

        public Criteria andPartnerNameIsNull() {
            addCriterion("partner_name is null");
            return (Criteria) this;
        }

        public Criteria andPartnerNameIsNotNull() {
            addCriterion("partner_name is not null");
            return (Criteria) this;
        }

        public Criteria andPartnerNameEqualTo(String value) {
            addCriterion("partner_name =", value, "partnerName");
            return (Criteria) this;
        }

        public Criteria andPartnerNameNotEqualTo(String value) {
            addCriterion("partner_name <>", value, "partnerName");
            return (Criteria) this;
        }

        public Criteria andPartnerNameGreaterThan(String value) {
            addCriterion("partner_name >", value, "partnerName");
            return (Criteria) this;
        }

        public Criteria andPartnerNameGreaterThanOrEqualTo(String value) {
            addCriterion("partner_name >=", value, "partnerName");
            return (Criteria) this;
        }

        public Criteria andPartnerNameLessThan(String value) {
            addCriterion("partner_name <", value, "partnerName");
            return (Criteria) this;
        }

        public Criteria andPartnerNameLessThanOrEqualTo(String value) {
            addCriterion("partner_name <=", value, "partnerName");
            return (Criteria) this;
        }

        public Criteria andPartnerNameLike(String value) {
            addCriterion("partner_name like", value, "partnerName");
            return (Criteria) this;
        }

        public Criteria andPartnerNameNotLike(String value) {
            addCriterion("partner_name not like", value, "partnerName");
            return (Criteria) this;
        }

        public Criteria andPartnerNameIn(List<String> values) {
            addCriterion("partner_name in", values, "partnerName");
            return (Criteria) this;
        }

        public Criteria andPartnerNameNotIn(List<String> values) {
            addCriterion("partner_name not in", values, "partnerName");
            return (Criteria) this;
        }

        public Criteria andPartnerNameBetween(String value1, String value2) {
            addCriterion("partner_name between", value1, value2, "partnerName");
            return (Criteria) this;
        }

        public Criteria andPartnerNameNotBetween(String value1, String value2) {
            addCriterion("partner_name not between", value1, value2, "partnerName");
            return (Criteria) this;
        }

        public Criteria andPartnerFullnameIsNull() {
            addCriterion("partner_fullname is null");
            return (Criteria) this;
        }

        public Criteria andPartnerFullnameIsNotNull() {
            addCriterion("partner_fullname is not null");
            return (Criteria) this;
        }

        public Criteria andPartnerFullnameEqualTo(String value) {
            addCriterion("partner_fullname =", value, "partnerFullname");
            return (Criteria) this;
        }

        public Criteria andPartnerFullnameNotEqualTo(String value) {
            addCriterion("partner_fullname <>", value, "partnerFullname");
            return (Criteria) this;
        }

        public Criteria andPartnerFullnameGreaterThan(String value) {
            addCriterion("partner_fullname >", value, "partnerFullname");
            return (Criteria) this;
        }

        public Criteria andPartnerFullnameGreaterThanOrEqualTo(String value) {
            addCriterion("partner_fullname >=", value, "partnerFullname");
            return (Criteria) this;
        }

        public Criteria andPartnerFullnameLessThan(String value) {
            addCriterion("partner_fullname <", value, "partnerFullname");
            return (Criteria) this;
        }

        public Criteria andPartnerFullnameLessThanOrEqualTo(String value) {
            addCriterion("partner_fullname <=", value, "partnerFullname");
            return (Criteria) this;
        }

        public Criteria andPartnerFullnameLike(String value) {
            addCriterion("partner_fullname like", value, "partnerFullname");
            return (Criteria) this;
        }

        public Criteria andPartnerFullnameNotLike(String value) {
            addCriterion("partner_fullname not like", value, "partnerFullname");
            return (Criteria) this;
        }

        public Criteria andPartnerFullnameIn(List<String> values) {
            addCriterion("partner_fullname in", values, "partnerFullname");
            return (Criteria) this;
        }

        public Criteria andPartnerFullnameNotIn(List<String> values) {
            addCriterion("partner_fullname not in", values, "partnerFullname");
            return (Criteria) this;
        }

        public Criteria andPartnerFullnameBetween(String value1, String value2) {
            addCriterion("partner_fullname between", value1, value2, "partnerFullname");
            return (Criteria) this;
        }

        public Criteria andPartnerFullnameNotBetween(String value1, String value2) {
            addCriterion("partner_fullname not between", value1, value2, "partnerFullname");
            return (Criteria) this;
        }

        public Criteria andLegelPersonIsNull() {
            addCriterion("legel_person is null");
            return (Criteria) this;
        }

        public Criteria andLegelPersonIsNotNull() {
            addCriterion("legel_person is not null");
            return (Criteria) this;
        }

        public Criteria andLegelPersonEqualTo(String value) {
            addCriterion("legel_person =", value, "legelPerson");
            return (Criteria) this;
        }

        public Criteria andLegelPersonNotEqualTo(String value) {
            addCriterion("legel_person <>", value, "legelPerson");
            return (Criteria) this;
        }

        public Criteria andLegelPersonGreaterThan(String value) {
            addCriterion("legel_person >", value, "legelPerson");
            return (Criteria) this;
        }

        public Criteria andLegelPersonGreaterThanOrEqualTo(String value) {
            addCriterion("legel_person >=", value, "legelPerson");
            return (Criteria) this;
        }

        public Criteria andLegelPersonLessThan(String value) {
            addCriterion("legel_person <", value, "legelPerson");
            return (Criteria) this;
        }

        public Criteria andLegelPersonLessThanOrEqualTo(String value) {
            addCriterion("legel_person <=", value, "legelPerson");
            return (Criteria) this;
        }

        public Criteria andLegelPersonLike(String value) {
            addCriterion("legel_person like", value, "legelPerson");
            return (Criteria) this;
        }

        public Criteria andLegelPersonNotLike(String value) {
            addCriterion("legel_person not like", value, "legelPerson");
            return (Criteria) this;
        }

        public Criteria andLegelPersonIn(List<String> values) {
            addCriterion("legel_person in", values, "legelPerson");
            return (Criteria) this;
        }

        public Criteria andLegelPersonNotIn(List<String> values) {
            addCriterion("legel_person not in", values, "legelPerson");
            return (Criteria) this;
        }

        public Criteria andLegelPersonBetween(String value1, String value2) {
            addCriterion("legel_person between", value1, value2, "legelPerson");
            return (Criteria) this;
        }

        public Criteria andLegelPersonNotBetween(String value1, String value2) {
            addCriterion("legel_person not between", value1, value2, "legelPerson");
            return (Criteria) this;
        }

        public Criteria andPartnerCityIsNull() {
            addCriterion("partner_city is null");
            return (Criteria) this;
        }

        public Criteria andPartnerCityIsNotNull() {
            addCriterion("partner_city is not null");
            return (Criteria) this;
        }

        public Criteria andPartnerCityEqualTo(String value) {
            addCriterion("partner_city =", value, "partnerCity");
            return (Criteria) this;
        }

        public Criteria andPartnerCityNotEqualTo(String value) {
            addCriterion("partner_city <>", value, "partnerCity");
            return (Criteria) this;
        }

        public Criteria andPartnerCityGreaterThan(String value) {
            addCriterion("partner_city >", value, "partnerCity");
            return (Criteria) this;
        }

        public Criteria andPartnerCityGreaterThanOrEqualTo(String value) {
            addCriterion("partner_city >=", value, "partnerCity");
            return (Criteria) this;
        }

        public Criteria andPartnerCityLessThan(String value) {
            addCriterion("partner_city <", value, "partnerCity");
            return (Criteria) this;
        }

        public Criteria andPartnerCityLessThanOrEqualTo(String value) {
            addCriterion("partner_city <=", value, "partnerCity");
            return (Criteria) this;
        }

        public Criteria andPartnerCityLike(String value) {
            addCriterion("partner_city like", value, "partnerCity");
            return (Criteria) this;
        }

        public Criteria andPartnerCityNotLike(String value) {
            addCriterion("partner_city not like", value, "partnerCity");
            return (Criteria) this;
        }

        public Criteria andPartnerCityIn(List<String> values) {
            addCriterion("partner_city in", values, "partnerCity");
            return (Criteria) this;
        }

        public Criteria andPartnerCityNotIn(List<String> values) {
            addCriterion("partner_city not in", values, "partnerCity");
            return (Criteria) this;
        }

        public Criteria andPartnerCityBetween(String value1, String value2) {
            addCriterion("partner_city between", value1, value2, "partnerCity");
            return (Criteria) this;
        }

        public Criteria andPartnerCityNotBetween(String value1, String value2) {
            addCriterion("partner_city not between", value1, value2, "partnerCity");
            return (Criteria) this;
        }

        public Criteria andPartnerProvinceIsNull() {
            addCriterion("partner_province is null");
            return (Criteria) this;
        }

        public Criteria andPartnerProvinceIsNotNull() {
            addCriterion("partner_province is not null");
            return (Criteria) this;
        }

        public Criteria andPartnerProvinceEqualTo(String value) {
            addCriterion("partner_province =", value, "partnerProvince");
            return (Criteria) this;
        }

        public Criteria andPartnerProvinceNotEqualTo(String value) {
            addCriterion("partner_province <>", value, "partnerProvince");
            return (Criteria) this;
        }

        public Criteria andPartnerProvinceGreaterThan(String value) {
            addCriterion("partner_province >", value, "partnerProvince");
            return (Criteria) this;
        }

        public Criteria andPartnerProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("partner_province >=", value, "partnerProvince");
            return (Criteria) this;
        }

        public Criteria andPartnerProvinceLessThan(String value) {
            addCriterion("partner_province <", value, "partnerProvince");
            return (Criteria) this;
        }

        public Criteria andPartnerProvinceLessThanOrEqualTo(String value) {
            addCriterion("partner_province <=", value, "partnerProvince");
            return (Criteria) this;
        }

        public Criteria andPartnerProvinceLike(String value) {
            addCriterion("partner_province like", value, "partnerProvince");
            return (Criteria) this;
        }

        public Criteria andPartnerProvinceNotLike(String value) {
            addCriterion("partner_province not like", value, "partnerProvince");
            return (Criteria) this;
        }

        public Criteria andPartnerProvinceIn(List<String> values) {
            addCriterion("partner_province in", values, "partnerProvince");
            return (Criteria) this;
        }

        public Criteria andPartnerProvinceNotIn(List<String> values) {
            addCriterion("partner_province not in", values, "partnerProvince");
            return (Criteria) this;
        }

        public Criteria andPartnerProvinceBetween(String value1, String value2) {
            addCriterion("partner_province between", value1, value2, "partnerProvince");
            return (Criteria) this;
        }

        public Criteria andPartnerProvinceNotBetween(String value1, String value2) {
            addCriterion("partner_province not between", value1, value2, "partnerProvince");
            return (Criteria) this;
        }

        public Criteria andVerifyKeyIsNull() {
            addCriterion("verify_key is null");
            return (Criteria) this;
        }

        public Criteria andVerifyKeyIsNotNull() {
            addCriterion("verify_key is not null");
            return (Criteria) this;
        }

        public Criteria andVerifyKeyEqualTo(String value) {
            addCriterion("verify_key =", value, "verifyKey");
            return (Criteria) this;
        }

        public Criteria andVerifyKeyNotEqualTo(String value) {
            addCriterion("verify_key <>", value, "verifyKey");
            return (Criteria) this;
        }

        public Criteria andVerifyKeyGreaterThan(String value) {
            addCriterion("verify_key >", value, "verifyKey");
            return (Criteria) this;
        }

        public Criteria andVerifyKeyGreaterThanOrEqualTo(String value) {
            addCriterion("verify_key >=", value, "verifyKey");
            return (Criteria) this;
        }

        public Criteria andVerifyKeyLessThan(String value) {
            addCriterion("verify_key <", value, "verifyKey");
            return (Criteria) this;
        }

        public Criteria andVerifyKeyLessThanOrEqualTo(String value) {
            addCriterion("verify_key <=", value, "verifyKey");
            return (Criteria) this;
        }

        public Criteria andVerifyKeyLike(String value) {
            addCriterion("verify_key like", value, "verifyKey");
            return (Criteria) this;
        }

        public Criteria andVerifyKeyNotLike(String value) {
            addCriterion("verify_key not like", value, "verifyKey");
            return (Criteria) this;
        }

        public Criteria andVerifyKeyIn(List<String> values) {
            addCriterion("verify_key in", values, "verifyKey");
            return (Criteria) this;
        }

        public Criteria andVerifyKeyNotIn(List<String> values) {
            addCriterion("verify_key not in", values, "verifyKey");
            return (Criteria) this;
        }

        public Criteria andVerifyKeyBetween(String value1, String value2) {
            addCriterion("verify_key between", value1, value2, "verifyKey");
            return (Criteria) this;
        }

        public Criteria andVerifyKeyNotBetween(String value1, String value2) {
            addCriterion("verify_key not between", value1, value2, "verifyKey");
            return (Criteria) this;
        }

        public Criteria andPartnerLevelIsNull() {
            addCriterion("partner_level is null");
            return (Criteria) this;
        }

        public Criteria andPartnerLevelIsNotNull() {
            addCriterion("partner_level is not null");
            return (Criteria) this;
        }

        public Criteria andPartnerLevelEqualTo(String value) {
            addCriterion("partner_level =", value, "partnerLevel");
            return (Criteria) this;
        }

        public Criteria andPartnerLevelNotEqualTo(String value) {
            addCriterion("partner_level <>", value, "partnerLevel");
            return (Criteria) this;
        }

        public Criteria andPartnerLevelGreaterThan(String value) {
            addCriterion("partner_level >", value, "partnerLevel");
            return (Criteria) this;
        }

        public Criteria andPartnerLevelGreaterThanOrEqualTo(String value) {
            addCriterion("partner_level >=", value, "partnerLevel");
            return (Criteria) this;
        }

        public Criteria andPartnerLevelLessThan(String value) {
            addCriterion("partner_level <", value, "partnerLevel");
            return (Criteria) this;
        }

        public Criteria andPartnerLevelLessThanOrEqualTo(String value) {
            addCriterion("partner_level <=", value, "partnerLevel");
            return (Criteria) this;
        }

        public Criteria andPartnerLevelLike(String value) {
            addCriterion("partner_level like", value, "partnerLevel");
            return (Criteria) this;
        }

        public Criteria andPartnerLevelNotLike(String value) {
            addCriterion("partner_level not like", value, "partnerLevel");
            return (Criteria) this;
        }

        public Criteria andPartnerLevelIn(List<String> values) {
            addCriterion("partner_level in", values, "partnerLevel");
            return (Criteria) this;
        }

        public Criteria andPartnerLevelNotIn(List<String> values) {
            addCriterion("partner_level not in", values, "partnerLevel");
            return (Criteria) this;
        }

        public Criteria andPartnerLevelBetween(String value1, String value2) {
            addCriterion("partner_level between", value1, value2, "partnerLevel");
            return (Criteria) this;
        }

        public Criteria andPartnerLevelNotBetween(String value1, String value2) {
            addCriterion("partner_level not between", value1, value2, "partnerLevel");
            return (Criteria) this;
        }

        public Criteria andPartnerDespIsNull() {
            addCriterion("partner_desp is null");
            return (Criteria) this;
        }

        public Criteria andPartnerDespIsNotNull() {
            addCriterion("partner_desp is not null");
            return (Criteria) this;
        }

        public Criteria andPartnerDespEqualTo(String value) {
            addCriterion("partner_desp =", value, "partnerDesp");
            return (Criteria) this;
        }

        public Criteria andPartnerDespNotEqualTo(String value) {
            addCriterion("partner_desp <>", value, "partnerDesp");
            return (Criteria) this;
        }

        public Criteria andPartnerDespGreaterThan(String value) {
            addCriterion("partner_desp >", value, "partnerDesp");
            return (Criteria) this;
        }

        public Criteria andPartnerDespGreaterThanOrEqualTo(String value) {
            addCriterion("partner_desp >=", value, "partnerDesp");
            return (Criteria) this;
        }

        public Criteria andPartnerDespLessThan(String value) {
            addCriterion("partner_desp <", value, "partnerDesp");
            return (Criteria) this;
        }

        public Criteria andPartnerDespLessThanOrEqualTo(String value) {
            addCriterion("partner_desp <=", value, "partnerDesp");
            return (Criteria) this;
        }

        public Criteria andPartnerDespLike(String value) {
            addCriterion("partner_desp like", value, "partnerDesp");
            return (Criteria) this;
        }

        public Criteria andPartnerDespNotLike(String value) {
            addCriterion("partner_desp not like", value, "partnerDesp");
            return (Criteria) this;
        }

        public Criteria andPartnerDespIn(List<String> values) {
            addCriterion("partner_desp in", values, "partnerDesp");
            return (Criteria) this;
        }

        public Criteria andPartnerDespNotIn(List<String> values) {
            addCriterion("partner_desp not in", values, "partnerDesp");
            return (Criteria) this;
        }

        public Criteria andPartnerDespBetween(String value1, String value2) {
            addCriterion("partner_desp between", value1, value2, "partnerDesp");
            return (Criteria) this;
        }

        public Criteria andPartnerDespNotBetween(String value1, String value2) {
            addCriterion("partner_desp not between", value1, value2, "partnerDesp");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceCodeIsNull() {
            addCriterion("business_licence_code is null");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceCodeIsNotNull() {
            addCriterion("business_licence_code is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceCodeEqualTo(String value) {
            addCriterion("business_licence_code =", value, "businessLicenceCode");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceCodeNotEqualTo(String value) {
            addCriterion("business_licence_code <>", value, "businessLicenceCode");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceCodeGreaterThan(String value) {
            addCriterion("business_licence_code >", value, "businessLicenceCode");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceCodeGreaterThanOrEqualTo(String value) {
            addCriterion("business_licence_code >=", value, "businessLicenceCode");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceCodeLessThan(String value) {
            addCriterion("business_licence_code <", value, "businessLicenceCode");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceCodeLessThanOrEqualTo(String value) {
            addCriterion("business_licence_code <=", value, "businessLicenceCode");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceCodeLike(String value) {
            addCriterion("business_licence_code like", value, "businessLicenceCode");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceCodeNotLike(String value) {
            addCriterion("business_licence_code not like", value, "businessLicenceCode");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceCodeIn(List<String> values) {
            addCriterion("business_licence_code in", values, "businessLicenceCode");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceCodeNotIn(List<String> values) {
            addCriterion("business_licence_code not in", values, "businessLicenceCode");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceCodeBetween(String value1, String value2) {
            addCriterion("business_licence_code between", value1, value2, "businessLicenceCode");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceCodeNotBetween(String value1, String value2) {
            addCriterion("business_licence_code not between", value1, value2, "businessLicenceCode");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceFileIsNull() {
            addCriterion("business_licence_file is null");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceFileIsNotNull() {
            addCriterion("business_licence_file is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceFileEqualTo(String value) {
            addCriterion("business_licence_file =", value, "businessLicenceFile");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceFileNotEqualTo(String value) {
            addCriterion("business_licence_file <>", value, "businessLicenceFile");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceFileGreaterThan(String value) {
            addCriterion("business_licence_file >", value, "businessLicenceFile");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceFileGreaterThanOrEqualTo(String value) {
            addCriterion("business_licence_file >=", value, "businessLicenceFile");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceFileLessThan(String value) {
            addCriterion("business_licence_file <", value, "businessLicenceFile");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceFileLessThanOrEqualTo(String value) {
            addCriterion("business_licence_file <=", value, "businessLicenceFile");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceFileLike(String value) {
            addCriterion("business_licence_file like", value, "businessLicenceFile");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceFileNotLike(String value) {
            addCriterion("business_licence_file not like", value, "businessLicenceFile");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceFileIn(List<String> values) {
            addCriterion("business_licence_file in", values, "businessLicenceFile");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceFileNotIn(List<String> values) {
            addCriterion("business_licence_file not in", values, "businessLicenceFile");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceFileBetween(String value1, String value2) {
            addCriterion("business_licence_file between", value1, value2, "businessLicenceFile");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenceFileNotBetween(String value1, String value2) {
            addCriterion("business_licence_file not between", value1, value2, "businessLicenceFile");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNull() {
            addCriterion("create_user is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNotNull() {
            addCriterion("create_user is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserEqualTo(String value) {
            addCriterion("create_user =", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotEqualTo(String value) {
            addCriterion("create_user <>", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThan(String value) {
            addCriterion("create_user >", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThanOrEqualTo(String value) {
            addCriterion("create_user >=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThan(String value) {
            addCriterion("create_user <", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThanOrEqualTo(String value) {
            addCriterion("create_user <=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLike(String value) {
            addCriterion("create_user like", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotLike(String value) {
            addCriterion("create_user not like", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserIn(List<String> values) {
            addCriterion("create_user in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotIn(List<String> values) {
            addCriterion("create_user not in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserBetween(String value1, String value2) {
            addCriterion("create_user between", value1, value2, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotBetween(String value1, String value2) {
            addCriterion("create_user not between", value1, value2, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIsNull() {
            addCriterion("update_user is null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIsNotNull() {
            addCriterion("update_user is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserEqualTo(String value) {
            addCriterion("update_user =", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotEqualTo(String value) {
            addCriterion("update_user <>", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserGreaterThan(String value) {
            addCriterion("update_user >", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserGreaterThanOrEqualTo(String value) {
            addCriterion("update_user >=", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLessThan(String value) {
            addCriterion("update_user <", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLessThanOrEqualTo(String value) {
            addCriterion("update_user <=", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLike(String value) {
            addCriterion("update_user like", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotLike(String value) {
            addCriterion("update_user not like", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIn(List<String> values) {
            addCriterion("update_user in", values, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotIn(List<String> values) {
            addCriterion("update_user not in", values, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserBetween(String value1, String value2) {
            addCriterion("update_user between", value1, value2, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotBetween(String value1, String value2) {
            addCriterion("update_user not between", value1, value2, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andContractTypeIsNull() {
            addCriterion("contract_type is null");
            return (Criteria) this;
        }

        public Criteria andContractTypeIsNotNull() {
            addCriterion("contract_type is not null");
            return (Criteria) this;
        }

        public Criteria andContractTypeEqualTo(String value) {
            addCriterion("contract_type =", value, "contractType");
            return (Criteria) this;
        }

        public Criteria andContractTypeNotEqualTo(String value) {
            addCriterion("contract_type <>", value, "contractType");
            return (Criteria) this;
        }

        public Criteria andContractTypeGreaterThan(String value) {
            addCriterion("contract_type >", value, "contractType");
            return (Criteria) this;
        }

        public Criteria andContractTypeGreaterThanOrEqualTo(String value) {
            addCriterion("contract_type >=", value, "contractType");
            return (Criteria) this;
        }

        public Criteria andContractTypeLessThan(String value) {
            addCriterion("contract_type <", value, "contractType");
            return (Criteria) this;
        }

        public Criteria andContractTypeLessThanOrEqualTo(String value) {
            addCriterion("contract_type <=", value, "contractType");
            return (Criteria) this;
        }

        public Criteria andContractTypeLike(String value) {
            addCriterion("contract_type like", value, "contractType");
            return (Criteria) this;
        }

        public Criteria andContractTypeNotLike(String value) {
            addCriterion("contract_type not like", value, "contractType");
            return (Criteria) this;
        }

        public Criteria andContractTypeIn(List<String> values) {
            addCriterion("contract_type in", values, "contractType");
            return (Criteria) this;
        }

        public Criteria andContractTypeNotIn(List<String> values) {
            addCriterion("contract_type not in", values, "contractType");
            return (Criteria) this;
        }

        public Criteria andContractTypeBetween(String value1, String value2) {
            addCriterion("contract_type between", value1, value2, "contractType");
            return (Criteria) this;
        }

        public Criteria andContractTypeNotBetween(String value1, String value2) {
            addCriterion("contract_type not between", value1, value2, "contractType");
            return (Criteria) this;
        }

        public Criteria andTrainInIsNull() {
            addCriterion("train_in is null");
            return (Criteria) this;
        }

        public Criteria andTrainInIsNotNull() {
            addCriterion("train_in is not null");
            return (Criteria) this;
        }

        public Criteria andTrainInEqualTo(Integer value) {
            addCriterion("train_in =", value, "trainIn");
            return (Criteria) this;
        }

        public Criteria andTrainInNotEqualTo(Integer value) {
            addCriterion("train_in <>", value, "trainIn");
            return (Criteria) this;
        }

        public Criteria andTrainInGreaterThan(Integer value) {
            addCriterion("train_in >", value, "trainIn");
            return (Criteria) this;
        }

        public Criteria andTrainInGreaterThanOrEqualTo(Integer value) {
            addCriterion("train_in >=", value, "trainIn");
            return (Criteria) this;
        }

        public Criteria andTrainInLessThan(Integer value) {
            addCriterion("train_in <", value, "trainIn");
            return (Criteria) this;
        }

        public Criteria andTrainInLessThanOrEqualTo(Integer value) {
            addCriterion("train_in <=", value, "trainIn");
            return (Criteria) this;
        }

        public Criteria andTrainInIn(List<Integer> values) {
            addCriterion("train_in in", values, "trainIn");
            return (Criteria) this;
        }

        public Criteria andTrainInNotIn(List<Integer> values) {
            addCriterion("train_in not in", values, "trainIn");
            return (Criteria) this;
        }

        public Criteria andTrainInBetween(Integer value1, Integer value2) {
            addCriterion("train_in between", value1, value2, "trainIn");
            return (Criteria) this;
        }

        public Criteria andTrainInNotBetween(Integer value1, Integer value2) {
            addCriterion("train_in not between", value1, value2, "trainIn");
            return (Criteria) this;
        }

        public Criteria andFinanceTypeIsNull() {
            addCriterion("finance_type is null");
            return (Criteria) this;
        }

        public Criteria andFinanceTypeIsNotNull() {
            addCriterion("finance_type is not null");
            return (Criteria) this;
        }

        public Criteria andFinanceTypeEqualTo(String value) {
            addCriterion("finance_type =", value, "financeType");
            return (Criteria) this;
        }

        public Criteria andFinanceTypeNotEqualTo(String value) {
            addCriterion("finance_type <>", value, "financeType");
            return (Criteria) this;
        }

        public Criteria andFinanceTypeGreaterThan(String value) {
            addCriterion("finance_type >", value, "financeType");
            return (Criteria) this;
        }

        public Criteria andFinanceTypeGreaterThanOrEqualTo(String value) {
            addCriterion("finance_type >=", value, "financeType");
            return (Criteria) this;
        }

        public Criteria andFinanceTypeLessThan(String value) {
            addCriterion("finance_type <", value, "financeType");
            return (Criteria) this;
        }

        public Criteria andFinanceTypeLessThanOrEqualTo(String value) {
            addCriterion("finance_type <=", value, "financeType");
            return (Criteria) this;
        }

        public Criteria andFinanceTypeLike(String value) {
            addCriterion("finance_type like", value, "financeType");
            return (Criteria) this;
        }

        public Criteria andFinanceTypeNotLike(String value) {
            addCriterion("finance_type not like", value, "financeType");
            return (Criteria) this;
        }

        public Criteria andFinanceTypeIn(List<String> values) {
            addCriterion("finance_type in", values, "financeType");
            return (Criteria) this;
        }

        public Criteria andFinanceTypeNotIn(List<String> values) {
            addCriterion("finance_type not in", values, "financeType");
            return (Criteria) this;
        }

        public Criteria andFinanceTypeBetween(String value1, String value2) {
            addCriterion("finance_type between", value1, value2, "financeType");
            return (Criteria) this;
        }

        public Criteria andFinanceTypeNotBetween(String value1, String value2) {
            addCriterion("finance_type not between", value1, value2, "financeType");
            return (Criteria) this;
        }

        public Criteria andCreditCloudStatusIsNull() {
            addCriterion("credit_cloud_status is null");
            return (Criteria) this;
        }

        public Criteria andCreditCloudStatusIsNotNull() {
            addCriterion("credit_cloud_status is not null");
            return (Criteria) this;
        }

        public Criteria andCreditCloudStatusEqualTo(Integer value) {
            addCriterion("credit_cloud_status =", value, "creditCloudStatus");
            return (Criteria) this;
        }

        public Criteria andCreditCloudStatusNotEqualTo(Integer value) {
            addCriterion("credit_cloud_status <>", value, "creditCloudStatus");
            return (Criteria) this;
        }

        public Criteria andCreditCloudStatusGreaterThan(Integer value) {
            addCriterion("credit_cloud_status >", value, "creditCloudStatus");
            return (Criteria) this;
        }

        public Criteria andCreditCloudStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("credit_cloud_status >=", value, "creditCloudStatus");
            return (Criteria) this;
        }

        public Criteria andCreditCloudStatusLessThan(Integer value) {
            addCriterion("credit_cloud_status <", value, "creditCloudStatus");
            return (Criteria) this;
        }

        public Criteria andCreditCloudStatusLessThanOrEqualTo(Integer value) {
            addCriterion("credit_cloud_status <=", value, "creditCloudStatus");
            return (Criteria) this;
        }

        public Criteria andCreditCloudStatusIn(List<Integer> values) {
            addCriterion("credit_cloud_status in", values, "creditCloudStatus");
            return (Criteria) this;
        }

        public Criteria andCreditCloudStatusNotIn(List<Integer> values) {
            addCriterion("credit_cloud_status not in", values, "creditCloudStatus");
            return (Criteria) this;
        }

        public Criteria andCreditCloudStatusBetween(Integer value1, Integer value2) {
            addCriterion("credit_cloud_status between", value1, value2, "creditCloudStatus");
            return (Criteria) this;
        }

        public Criteria andCreditCloudStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("credit_cloud_status not between", value1, value2, "creditCloudStatus");
            return (Criteria) this;
        }

        public Criteria andEsStatusIsNull() {
            addCriterion("es_status is null");
            return (Criteria) this;
        }

        public Criteria andEsStatusIsNotNull() {
            addCriterion("es_status is not null");
            return (Criteria) this;
        }

        public Criteria andEsStatusEqualTo(Integer value) {
            addCriterion("es_status =", value, "esStatus");
            return (Criteria) this;
        }

        public Criteria andEsStatusNotEqualTo(Integer value) {
            addCriterion("es_status <>", value, "esStatus");
            return (Criteria) this;
        }

        public Criteria andEsStatusGreaterThan(Integer value) {
            addCriterion("es_status >", value, "esStatus");
            return (Criteria) this;
        }

        public Criteria andEsStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("es_status >=", value, "esStatus");
            return (Criteria) this;
        }

        public Criteria andEsStatusLessThan(Integer value) {
            addCriterion("es_status <", value, "esStatus");
            return (Criteria) this;
        }

        public Criteria andEsStatusLessThanOrEqualTo(Integer value) {
            addCriterion("es_status <=", value, "esStatus");
            return (Criteria) this;
        }

        public Criteria andEsStatusIn(List<Integer> values) {
            addCriterion("es_status in", values, "esStatus");
            return (Criteria) this;
        }

        public Criteria andEsStatusNotIn(List<Integer> values) {
            addCriterion("es_status not in", values, "esStatus");
            return (Criteria) this;
        }

        public Criteria andEsStatusBetween(Integer value1, Integer value2) {
            addCriterion("es_status between", value1, value2, "esStatus");
            return (Criteria) this;
        }

        public Criteria andEsStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("es_status not between", value1, value2, "esStatus");
            return (Criteria) this;
        }

        public Criteria andOldVerifyKeyIsNull() {
            addCriterion("old_verify_key is null");
            return (Criteria) this;
        }

        public Criteria andOldVerifyKeyIsNotNull() {
            addCriterion("old_verify_key is not null");
            return (Criteria) this;
        }

        public Criteria andOldVerifyKeyEqualTo(String value) {
            addCriterion("old_verify_key =", value, "oldVerifyKey");
            return (Criteria) this;
        }

        public Criteria andOldVerifyKeyNotEqualTo(String value) {
            addCriterion("old_verify_key <>", value, "oldVerifyKey");
            return (Criteria) this;
        }

        public Criteria andOldVerifyKeyGreaterThan(String value) {
            addCriterion("old_verify_key >", value, "oldVerifyKey");
            return (Criteria) this;
        }

        public Criteria andOldVerifyKeyGreaterThanOrEqualTo(String value) {
            addCriterion("old_verify_key >=", value, "oldVerifyKey");
            return (Criteria) this;
        }

        public Criteria andOldVerifyKeyLessThan(String value) {
            addCriterion("old_verify_key <", value, "oldVerifyKey");
            return (Criteria) this;
        }

        public Criteria andOldVerifyKeyLessThanOrEqualTo(String value) {
            addCriterion("old_verify_key <=", value, "oldVerifyKey");
            return (Criteria) this;
        }

        public Criteria andOldVerifyKeyLike(String value) {
            addCriterion("old_verify_key like", value, "oldVerifyKey");
            return (Criteria) this;
        }

        public Criteria andOldVerifyKeyNotLike(String value) {
            addCriterion("old_verify_key not like", value, "oldVerifyKey");
            return (Criteria) this;
        }

        public Criteria andOldVerifyKeyIn(List<String> values) {
            addCriterion("old_verify_key in", values, "oldVerifyKey");
            return (Criteria) this;
        }

        public Criteria andOldVerifyKeyNotIn(List<String> values) {
            addCriterion("old_verify_key not in", values, "oldVerifyKey");
            return (Criteria) this;
        }

        public Criteria andOldVerifyKeyBetween(String value1, String value2) {
            addCriterion("old_verify_key between", value1, value2, "oldVerifyKey");
            return (Criteria) this;
        }

        public Criteria andOldVerifyKeyNotBetween(String value1, String value2) {
            addCriterion("old_verify_key not between", value1, value2, "oldVerifyKey");
            return (Criteria) this;
        }

        public Criteria andUsrLocationIsNull() {
            addCriterion("usr_location is null");
            return (Criteria) this;
        }

        public Criteria andUsrLocationIsNotNull() {
            addCriterion("usr_location is not null");
            return (Criteria) this;
        }

        public Criteria andUsrLocationEqualTo(String value) {
            addCriterion("usr_location =", value, "usrLocation");
            return (Criteria) this;
        }

        public Criteria andUsrLocationNotEqualTo(String value) {
            addCriterion("usr_location <>", value, "usrLocation");
            return (Criteria) this;
        }

        public Criteria andUsrLocationGreaterThan(String value) {
            addCriterion("usr_location >", value, "usrLocation");
            return (Criteria) this;
        }

        public Criteria andUsrLocationGreaterThanOrEqualTo(String value) {
            addCriterion("usr_location >=", value, "usrLocation");
            return (Criteria) this;
        }

        public Criteria andUsrLocationLessThan(String value) {
            addCriterion("usr_location <", value, "usrLocation");
            return (Criteria) this;
        }

        public Criteria andUsrLocationLessThanOrEqualTo(String value) {
            addCriterion("usr_location <=", value, "usrLocation");
            return (Criteria) this;
        }

        public Criteria andUsrLocationLike(String value) {
            addCriterion("usr_location like", value, "usrLocation");
            return (Criteria) this;
        }

        public Criteria andUsrLocationNotLike(String value) {
            addCriterion("usr_location not like", value, "usrLocation");
            return (Criteria) this;
        }

        public Criteria andUsrLocationIn(List<String> values) {
            addCriterion("usr_location in", values, "usrLocation");
            return (Criteria) this;
        }

        public Criteria andUsrLocationNotIn(List<String> values) {
            addCriterion("usr_location not in", values, "usrLocation");
            return (Criteria) this;
        }

        public Criteria andUsrLocationBetween(String value1, String value2) {
            addCriterion("usr_location between", value1, value2, "usrLocation");
            return (Criteria) this;
        }

        public Criteria andUsrLocationNotBetween(String value1, String value2) {
            addCriterion("usr_location not between", value1, value2, "usrLocation");
            return (Criteria) this;
        }

        public Criteria andEncryptKeyIsNull() {
            addCriterion("encrypt_key is null");
            return (Criteria) this;
        }

        public Criteria andEncryptKeyIsNotNull() {
            addCriterion("encrypt_key is not null");
            return (Criteria) this;
        }

        public Criteria andEncryptKeyEqualTo(String value) {
            addCriterion("encrypt_key =", value, "encryptKey");
            return (Criteria) this;
        }

        public Criteria andEncryptKeyNotEqualTo(String value) {
            addCriterion("encrypt_key <>", value, "encryptKey");
            return (Criteria) this;
        }

        public Criteria andEncryptKeyGreaterThan(String value) {
            addCriterion("encrypt_key >", value, "encryptKey");
            return (Criteria) this;
        }

        public Criteria andEncryptKeyGreaterThanOrEqualTo(String value) {
            addCriterion("encrypt_key >=", value, "encryptKey");
            return (Criteria) this;
        }

        public Criteria andEncryptKeyLessThan(String value) {
            addCriterion("encrypt_key <", value, "encryptKey");
            return (Criteria) this;
        }

        public Criteria andEncryptKeyLessThanOrEqualTo(String value) {
            addCriterion("encrypt_key <=", value, "encryptKey");
            return (Criteria) this;
        }

        public Criteria andEncryptKeyLike(String value) {
            addCriterion("encrypt_key like", value, "encryptKey");
            return (Criteria) this;
        }

        public Criteria andEncryptKeyNotLike(String value) {
            addCriterion("encrypt_key not like", value, "encryptKey");
            return (Criteria) this;
        }

        public Criteria andEncryptKeyIn(List<String> values) {
            addCriterion("encrypt_key in", values, "encryptKey");
            return (Criteria) this;
        }

        public Criteria andEncryptKeyNotIn(List<String> values) {
            addCriterion("encrypt_key not in", values, "encryptKey");
            return (Criteria) this;
        }

        public Criteria andEncryptKeyBetween(String value1, String value2) {
            addCriterion("encrypt_key between", value1, value2, "encryptKey");
            return (Criteria) this;
        }

        public Criteria andEncryptKeyNotBetween(String value1, String value2) {
            addCriterion("encrypt_key not between", value1, value2, "encryptKey");
            return (Criteria) this;
        }

        public Criteria andEncryptKeyTimeIsNull() {
            addCriterion("encrypt_key_time is null");
            return (Criteria) this;
        }

        public Criteria andEncryptKeyTimeIsNotNull() {
            addCriterion("encrypt_key_time is not null");
            return (Criteria) this;
        }

        public Criteria andEncryptKeyTimeEqualTo(Date value) {
            addCriterion("encrypt_key_time =", value, "encryptKeyTime");
            return (Criteria) this;
        }

        public Criteria andEncryptKeyTimeNotEqualTo(Date value) {
            addCriterion("encrypt_key_time <>", value, "encryptKeyTime");
            return (Criteria) this;
        }

        public Criteria andEncryptKeyTimeGreaterThan(Date value) {
            addCriterion("encrypt_key_time >", value, "encryptKeyTime");
            return (Criteria) this;
        }

        public Criteria andEncryptKeyTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("encrypt_key_time >=", value, "encryptKeyTime");
            return (Criteria) this;
        }

        public Criteria andEncryptKeyTimeLessThan(Date value) {
            addCriterion("encrypt_key_time <", value, "encryptKeyTime");
            return (Criteria) this;
        }

        public Criteria andEncryptKeyTimeLessThanOrEqualTo(Date value) {
            addCriterion("encrypt_key_time <=", value, "encryptKeyTime");
            return (Criteria) this;
        }

        public Criteria andEncryptKeyTimeIn(List<Date> values) {
            addCriterion("encrypt_key_time in", values, "encryptKeyTime");
            return (Criteria) this;
        }

        public Criteria andEncryptKeyTimeNotIn(List<Date> values) {
            addCriterion("encrypt_key_time not in", values, "encryptKeyTime");
            return (Criteria) this;
        }

        public Criteria andEncryptKeyTimeBetween(Date value1, Date value2) {
            addCriterion("encrypt_key_time between", value1, value2, "encryptKeyTime");
            return (Criteria) this;
        }

        public Criteria andEncryptKeyTimeNotBetween(Date value1, Date value2) {
            addCriterion("encrypt_key_time not between", value1, value2, "encryptKeyTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

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