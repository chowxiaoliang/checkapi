package com.zl.checkapi.mysql.domain;

import java.util.ArrayList;
import java.util.List;

public class RiskBqsDataFeedbackExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RiskBqsDataFeedbackExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andRiskFlowNoIsNull() {
            addCriterion("risk_flow_no is null");
            return (Criteria) this;
        }

        public Criteria andRiskFlowNoIsNotNull() {
            addCriterion("risk_flow_no is not null");
            return (Criteria) this;
        }

        public Criteria andRiskFlowNoEqualTo(String value) {
            addCriterion("risk_flow_no =", value, "riskFlowNo");
            return (Criteria) this;
        }

        public Criteria andRiskFlowNoNotEqualTo(String value) {
            addCriterion("risk_flow_no <>", value, "riskFlowNo");
            return (Criteria) this;
        }

        public Criteria andRiskFlowNoGreaterThan(String value) {
            addCriterion("risk_flow_no >", value, "riskFlowNo");
            return (Criteria) this;
        }

        public Criteria andRiskFlowNoGreaterThanOrEqualTo(String value) {
            addCriterion("risk_flow_no >=", value, "riskFlowNo");
            return (Criteria) this;
        }

        public Criteria andRiskFlowNoLessThan(String value) {
            addCriterion("risk_flow_no <", value, "riskFlowNo");
            return (Criteria) this;
        }

        public Criteria andRiskFlowNoLessThanOrEqualTo(String value) {
            addCriterion("risk_flow_no <=", value, "riskFlowNo");
            return (Criteria) this;
        }

        public Criteria andRiskFlowNoLike(String value) {
            addCriterion("risk_flow_no like", value, "riskFlowNo");
            return (Criteria) this;
        }

        public Criteria andRiskFlowNoNotLike(String value) {
            addCriterion("risk_flow_no not like", value, "riskFlowNo");
            return (Criteria) this;
        }

        public Criteria andRiskFlowNoIn(List<String> values) {
            addCriterion("risk_flow_no in", values, "riskFlowNo");
            return (Criteria) this;
        }

        public Criteria andRiskFlowNoNotIn(List<String> values) {
            addCriterion("risk_flow_no not in", values, "riskFlowNo");
            return (Criteria) this;
        }

        public Criteria andRiskFlowNoBetween(String value1, String value2) {
            addCriterion("risk_flow_no between", value1, value2, "riskFlowNo");
            return (Criteria) this;
        }

        public Criteria andRiskFlowNoNotBetween(String value1, String value2) {
            addCriterion("risk_flow_no not between", value1, value2, "riskFlowNo");
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

        public Criteria andCertNoIsNull() {
            addCriterion("cert_no is null");
            return (Criteria) this;
        }

        public Criteria andCertNoIsNotNull() {
            addCriterion("cert_no is not null");
            return (Criteria) this;
        }

        public Criteria andCertNoEqualTo(String value) {
            addCriterion("cert_no =", value, "certNo");
            return (Criteria) this;
        }

        public Criteria andCertNoNotEqualTo(String value) {
            addCriterion("cert_no <>", value, "certNo");
            return (Criteria) this;
        }

        public Criteria andCertNoGreaterThan(String value) {
            addCriterion("cert_no >", value, "certNo");
            return (Criteria) this;
        }

        public Criteria andCertNoGreaterThanOrEqualTo(String value) {
            addCriterion("cert_no >=", value, "certNo");
            return (Criteria) this;
        }

        public Criteria andCertNoLessThan(String value) {
            addCriterion("cert_no <", value, "certNo");
            return (Criteria) this;
        }

        public Criteria andCertNoLessThanOrEqualTo(String value) {
            addCriterion("cert_no <=", value, "certNo");
            return (Criteria) this;
        }

        public Criteria andCertNoLike(String value) {
            addCriterion("cert_no like", value, "certNo");
            return (Criteria) this;
        }

        public Criteria andCertNoNotLike(String value) {
            addCriterion("cert_no not like", value, "certNo");
            return (Criteria) this;
        }

        public Criteria andCertNoIn(List<String> values) {
            addCriterion("cert_no in", values, "certNo");
            return (Criteria) this;
        }

        public Criteria andCertNoNotIn(List<String> values) {
            addCriterion("cert_no not in", values, "certNo");
            return (Criteria) this;
        }

        public Criteria andCertNoBetween(String value1, String value2) {
            addCriterion("cert_no between", value1, value2, "certNo");
            return (Criteria) this;
        }

        public Criteria andCertNoNotBetween(String value1, String value2) {
            addCriterion("cert_no not between", value1, value2, "certNo");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andMobileIsNull() {
            addCriterion("mobile is null");
            return (Criteria) this;
        }

        public Criteria andMobileIsNotNull() {
            addCriterion("mobile is not null");
            return (Criteria) this;
        }

        public Criteria andMobileEqualTo(String value) {
            addCriterion("mobile =", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotEqualTo(String value) {
            addCriterion("mobile <>", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThan(String value) {
            addCriterion("mobile >", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThanOrEqualTo(String value) {
            addCriterion("mobile >=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThan(String value) {
            addCriterion("mobile <", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThanOrEqualTo(String value) {
            addCriterion("mobile <=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLike(String value) {
            addCriterion("mobile like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotLike(String value) {
            addCriterion("mobile not like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileIn(List<String> values) {
            addCriterion("mobile in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotIn(List<String> values) {
            addCriterion("mobile not in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileBetween(String value1, String value2) {
            addCriterion("mobile between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotBetween(String value1, String value2) {
            addCriterion("mobile not between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andAmountIsNull() {
            addCriterion("amount is null");
            return (Criteria) this;
        }

        public Criteria andAmountIsNotNull() {
            addCriterion("amount is not null");
            return (Criteria) this;
        }

        public Criteria andAmountEqualTo(String value) {
            addCriterion("amount =", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotEqualTo(String value) {
            addCriterion("amount <>", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThan(String value) {
            addCriterion("amount >", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThanOrEqualTo(String value) {
            addCriterion("amount >=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThan(String value) {
            addCriterion("amount <", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThanOrEqualTo(String value) {
            addCriterion("amount <=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLike(String value) {
            addCriterion("amount like", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotLike(String value) {
            addCriterion("amount not like", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountIn(List<String> values) {
            addCriterion("amount in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotIn(List<String> values) {
            addCriterion("amount not in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountBetween(String value1, String value2) {
            addCriterion("amount between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotBetween(String value1, String value2) {
            addCriterion("amount not between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andLoanDateIsNull() {
            addCriterion("loan_date is null");
            return (Criteria) this;
        }

        public Criteria andLoanDateIsNotNull() {
            addCriterion("loan_date is not null");
            return (Criteria) this;
        }

        public Criteria andLoanDateEqualTo(String value) {
            addCriterion("loan_date =", value, "loanDate");
            return (Criteria) this;
        }

        public Criteria andLoanDateNotEqualTo(String value) {
            addCriterion("loan_date <>", value, "loanDate");
            return (Criteria) this;
        }

        public Criteria andLoanDateGreaterThan(String value) {
            addCriterion("loan_date >", value, "loanDate");
            return (Criteria) this;
        }

        public Criteria andLoanDateGreaterThanOrEqualTo(String value) {
            addCriterion("loan_date >=", value, "loanDate");
            return (Criteria) this;
        }

        public Criteria andLoanDateLessThan(String value) {
            addCriterion("loan_date <", value, "loanDate");
            return (Criteria) this;
        }

        public Criteria andLoanDateLessThanOrEqualTo(String value) {
            addCriterion("loan_date <=", value, "loanDate");
            return (Criteria) this;
        }

        public Criteria andLoanDateLike(String value) {
            addCriterion("loan_date like", value, "loanDate");
            return (Criteria) this;
        }

        public Criteria andLoanDateNotLike(String value) {
            addCriterion("loan_date not like", value, "loanDate");
            return (Criteria) this;
        }

        public Criteria andLoanDateIn(List<String> values) {
            addCriterion("loan_date in", values, "loanDate");
            return (Criteria) this;
        }

        public Criteria andLoanDateNotIn(List<String> values) {
            addCriterion("loan_date not in", values, "loanDate");
            return (Criteria) this;
        }

        public Criteria andLoanDateBetween(String value1, String value2) {
            addCriterion("loan_date between", value1, value2, "loanDate");
            return (Criteria) this;
        }

        public Criteria andLoanDateNotBetween(String value1, String value2) {
            addCriterion("loan_date not between", value1, value2, "loanDate");
            return (Criteria) this;
        }

        public Criteria andBizDateIsNull() {
            addCriterion("biz_date is null");
            return (Criteria) this;
        }

        public Criteria andBizDateIsNotNull() {
            addCriterion("biz_date is not null");
            return (Criteria) this;
        }

        public Criteria andBizDateEqualTo(String value) {
            addCriterion("biz_date =", value, "bizDate");
            return (Criteria) this;
        }

        public Criteria andBizDateNotEqualTo(String value) {
            addCriterion("biz_date <>", value, "bizDate");
            return (Criteria) this;
        }

        public Criteria andBizDateGreaterThan(String value) {
            addCriterion("biz_date >", value, "bizDate");
            return (Criteria) this;
        }

        public Criteria andBizDateGreaterThanOrEqualTo(String value) {
            addCriterion("biz_date >=", value, "bizDate");
            return (Criteria) this;
        }

        public Criteria andBizDateLessThan(String value) {
            addCriterion("biz_date <", value, "bizDate");
            return (Criteria) this;
        }

        public Criteria andBizDateLessThanOrEqualTo(String value) {
            addCriterion("biz_date <=", value, "bizDate");
            return (Criteria) this;
        }

        public Criteria andBizDateLike(String value) {
            addCriterion("biz_date like", value, "bizDate");
            return (Criteria) this;
        }

        public Criteria andBizDateNotLike(String value) {
            addCriterion("biz_date not like", value, "bizDate");
            return (Criteria) this;
        }

        public Criteria andBizDateIn(List<String> values) {
            addCriterion("biz_date in", values, "bizDate");
            return (Criteria) this;
        }

        public Criteria andBizDateNotIn(List<String> values) {
            addCriterion("biz_date not in", values, "bizDate");
            return (Criteria) this;
        }

        public Criteria andBizDateBetween(String value1, String value2) {
            addCriterion("biz_date between", value1, value2, "bizDate");
            return (Criteria) this;
        }

        public Criteria andBizDateNotBetween(String value1, String value2) {
            addCriterion("biz_date not between", value1, value2, "bizDate");
            return (Criteria) this;
        }

        public Criteria andOverDueAmtIsNull() {
            addCriterion("over_due_amt is null");
            return (Criteria) this;
        }

        public Criteria andOverDueAmtIsNotNull() {
            addCriterion("over_due_amt is not null");
            return (Criteria) this;
        }

        public Criteria andOverDueAmtEqualTo(String value) {
            addCriterion("over_due_amt =", value, "overDueAmt");
            return (Criteria) this;
        }

        public Criteria andOverDueAmtNotEqualTo(String value) {
            addCriterion("over_due_amt <>", value, "overDueAmt");
            return (Criteria) this;
        }

        public Criteria andOverDueAmtGreaterThan(String value) {
            addCriterion("over_due_amt >", value, "overDueAmt");
            return (Criteria) this;
        }

        public Criteria andOverDueAmtGreaterThanOrEqualTo(String value) {
            addCriterion("over_due_amt >=", value, "overDueAmt");
            return (Criteria) this;
        }

        public Criteria andOverDueAmtLessThan(String value) {
            addCriterion("over_due_amt <", value, "overDueAmt");
            return (Criteria) this;
        }

        public Criteria andOverDueAmtLessThanOrEqualTo(String value) {
            addCriterion("over_due_amt <=", value, "overDueAmt");
            return (Criteria) this;
        }

        public Criteria andOverDueAmtLike(String value) {
            addCriterion("over_due_amt like", value, "overDueAmt");
            return (Criteria) this;
        }

        public Criteria andOverDueAmtNotLike(String value) {
            addCriterion("over_due_amt not like", value, "overDueAmt");
            return (Criteria) this;
        }

        public Criteria andOverDueAmtIn(List<String> values) {
            addCriterion("over_due_amt in", values, "overDueAmt");
            return (Criteria) this;
        }

        public Criteria andOverDueAmtNotIn(List<String> values) {
            addCriterion("over_due_amt not in", values, "overDueAmt");
            return (Criteria) this;
        }

        public Criteria andOverDueAmtBetween(String value1, String value2) {
            addCriterion("over_due_amt between", value1, value2, "overDueAmt");
            return (Criteria) this;
        }

        public Criteria andOverDueAmtNotBetween(String value1, String value2) {
            addCriterion("over_due_amt not between", value1, value2, "overDueAmt");
            return (Criteria) this;
        }

        public Criteria andGmtOvdDateIsNull() {
            addCriterion("gmt_ovd_date is null");
            return (Criteria) this;
        }

        public Criteria andGmtOvdDateIsNotNull() {
            addCriterion("gmt_ovd_date is not null");
            return (Criteria) this;
        }

        public Criteria andGmtOvdDateEqualTo(String value) {
            addCriterion("gmt_ovd_date =", value, "gmtOvdDate");
            return (Criteria) this;
        }

        public Criteria andGmtOvdDateNotEqualTo(String value) {
            addCriterion("gmt_ovd_date <>", value, "gmtOvdDate");
            return (Criteria) this;
        }

        public Criteria andGmtOvdDateGreaterThan(String value) {
            addCriterion("gmt_ovd_date >", value, "gmtOvdDate");
            return (Criteria) this;
        }

        public Criteria andGmtOvdDateGreaterThanOrEqualTo(String value) {
            addCriterion("gmt_ovd_date >=", value, "gmtOvdDate");
            return (Criteria) this;
        }

        public Criteria andGmtOvdDateLessThan(String value) {
            addCriterion("gmt_ovd_date <", value, "gmtOvdDate");
            return (Criteria) this;
        }

        public Criteria andGmtOvdDateLessThanOrEqualTo(String value) {
            addCriterion("gmt_ovd_date <=", value, "gmtOvdDate");
            return (Criteria) this;
        }

        public Criteria andGmtOvdDateLike(String value) {
            addCriterion("gmt_ovd_date like", value, "gmtOvdDate");
            return (Criteria) this;
        }

        public Criteria andGmtOvdDateNotLike(String value) {
            addCriterion("gmt_ovd_date not like", value, "gmtOvdDate");
            return (Criteria) this;
        }

        public Criteria andGmtOvdDateIn(List<String> values) {
            addCriterion("gmt_ovd_date in", values, "gmtOvdDate");
            return (Criteria) this;
        }

        public Criteria andGmtOvdDateNotIn(List<String> values) {
            addCriterion("gmt_ovd_date not in", values, "gmtOvdDate");
            return (Criteria) this;
        }

        public Criteria andGmtOvdDateBetween(String value1, String value2) {
            addCriterion("gmt_ovd_date between", value1, value2, "gmtOvdDate");
            return (Criteria) this;
        }

        public Criteria andGmtOvdDateNotBetween(String value1, String value2) {
            addCriterion("gmt_ovd_date not between", value1, value2, "gmtOvdDate");
            return (Criteria) this;
        }

        public Criteria andGmtOvdDaysIsNull() {
            addCriterion("gmt_ovd_days is null");
            return (Criteria) this;
        }

        public Criteria andGmtOvdDaysIsNotNull() {
            addCriterion("gmt_ovd_days is not null");
            return (Criteria) this;
        }

        public Criteria andGmtOvdDaysEqualTo(String value) {
            addCriterion("gmt_ovd_days =", value, "gmtOvdDays");
            return (Criteria) this;
        }

        public Criteria andGmtOvdDaysNotEqualTo(String value) {
            addCriterion("gmt_ovd_days <>", value, "gmtOvdDays");
            return (Criteria) this;
        }

        public Criteria andGmtOvdDaysGreaterThan(String value) {
            addCriterion("gmt_ovd_days >", value, "gmtOvdDays");
            return (Criteria) this;
        }

        public Criteria andGmtOvdDaysGreaterThanOrEqualTo(String value) {
            addCriterion("gmt_ovd_days >=", value, "gmtOvdDays");
            return (Criteria) this;
        }

        public Criteria andGmtOvdDaysLessThan(String value) {
            addCriterion("gmt_ovd_days <", value, "gmtOvdDays");
            return (Criteria) this;
        }

        public Criteria andGmtOvdDaysLessThanOrEqualTo(String value) {
            addCriterion("gmt_ovd_days <=", value, "gmtOvdDays");
            return (Criteria) this;
        }

        public Criteria andGmtOvdDaysLike(String value) {
            addCriterion("gmt_ovd_days like", value, "gmtOvdDays");
            return (Criteria) this;
        }

        public Criteria andGmtOvdDaysNotLike(String value) {
            addCriterion("gmt_ovd_days not like", value, "gmtOvdDays");
            return (Criteria) this;
        }

        public Criteria andGmtOvdDaysIn(List<String> values) {
            addCriterion("gmt_ovd_days in", values, "gmtOvdDays");
            return (Criteria) this;
        }

        public Criteria andGmtOvdDaysNotIn(List<String> values) {
            addCriterion("gmt_ovd_days not in", values, "gmtOvdDays");
            return (Criteria) this;
        }

        public Criteria andGmtOvdDaysBetween(String value1, String value2) {
            addCriterion("gmt_ovd_days between", value1, value2, "gmtOvdDays");
            return (Criteria) this;
        }

        public Criteria andGmtOvdDaysNotBetween(String value1, String value2) {
            addCriterion("gmt_ovd_days not between", value1, value2, "gmtOvdDays");
            return (Criteria) this;
        }

        public Criteria andOvdDaysCalwaysIsNull() {
            addCriterion("ovd_days_calways is null");
            return (Criteria) this;
        }

        public Criteria andOvdDaysCalwaysIsNotNull() {
            addCriterion("ovd_days_calways is not null");
            return (Criteria) this;
        }

        public Criteria andOvdDaysCalwaysEqualTo(String value) {
            addCriterion("ovd_days_calways =", value, "ovdDaysCalways");
            return (Criteria) this;
        }

        public Criteria andOvdDaysCalwaysNotEqualTo(String value) {
            addCriterion("ovd_days_calways <>", value, "ovdDaysCalways");
            return (Criteria) this;
        }

        public Criteria andOvdDaysCalwaysGreaterThan(String value) {
            addCriterion("ovd_days_calways >", value, "ovdDaysCalways");
            return (Criteria) this;
        }

        public Criteria andOvdDaysCalwaysGreaterThanOrEqualTo(String value) {
            addCriterion("ovd_days_calways >=", value, "ovdDaysCalways");
            return (Criteria) this;
        }

        public Criteria andOvdDaysCalwaysLessThan(String value) {
            addCriterion("ovd_days_calways <", value, "ovdDaysCalways");
            return (Criteria) this;
        }

        public Criteria andOvdDaysCalwaysLessThanOrEqualTo(String value) {
            addCriterion("ovd_days_calways <=", value, "ovdDaysCalways");
            return (Criteria) this;
        }

        public Criteria andOvdDaysCalwaysLike(String value) {
            addCriterion("ovd_days_calways like", value, "ovdDaysCalways");
            return (Criteria) this;
        }

        public Criteria andOvdDaysCalwaysNotLike(String value) {
            addCriterion("ovd_days_calways not like", value, "ovdDaysCalways");
            return (Criteria) this;
        }

        public Criteria andOvdDaysCalwaysIn(List<String> values) {
            addCriterion("ovd_days_calways in", values, "ovdDaysCalways");
            return (Criteria) this;
        }

        public Criteria andOvdDaysCalwaysNotIn(List<String> values) {
            addCriterion("ovd_days_calways not in", values, "ovdDaysCalways");
            return (Criteria) this;
        }

        public Criteria andOvdDaysCalwaysBetween(String value1, String value2) {
            addCriterion("ovd_days_calways between", value1, value2, "ovdDaysCalways");
            return (Criteria) this;
        }

        public Criteria andOvdDaysCalwaysNotBetween(String value1, String value2) {
            addCriterion("ovd_days_calways not between", value1, value2, "ovdDaysCalways");
            return (Criteria) this;
        }

        public Criteria andUploadDataSourceIsNull() {
            addCriterion("upload_data_source is null");
            return (Criteria) this;
        }

        public Criteria andUploadDataSourceIsNotNull() {
            addCriterion("upload_data_source is not null");
            return (Criteria) this;
        }

        public Criteria andUploadDataSourceEqualTo(String value) {
            addCriterion("upload_data_source =", value, "uploadDataSource");
            return (Criteria) this;
        }

        public Criteria andUploadDataSourceNotEqualTo(String value) {
            addCriterion("upload_data_source <>", value, "uploadDataSource");
            return (Criteria) this;
        }

        public Criteria andUploadDataSourceGreaterThan(String value) {
            addCriterion("upload_data_source >", value, "uploadDataSource");
            return (Criteria) this;
        }

        public Criteria andUploadDataSourceGreaterThanOrEqualTo(String value) {
            addCriterion("upload_data_source >=", value, "uploadDataSource");
            return (Criteria) this;
        }

        public Criteria andUploadDataSourceLessThan(String value) {
            addCriterion("upload_data_source <", value, "uploadDataSource");
            return (Criteria) this;
        }

        public Criteria andUploadDataSourceLessThanOrEqualTo(String value) {
            addCriterion("upload_data_source <=", value, "uploadDataSource");
            return (Criteria) this;
        }

        public Criteria andUploadDataSourceLike(String value) {
            addCriterion("upload_data_source like", value, "uploadDataSource");
            return (Criteria) this;
        }

        public Criteria andUploadDataSourceNotLike(String value) {
            addCriterion("upload_data_source not like", value, "uploadDataSource");
            return (Criteria) this;
        }

        public Criteria andUploadDataSourceIn(List<String> values) {
            addCriterion("upload_data_source in", values, "uploadDataSource");
            return (Criteria) this;
        }

        public Criteria andUploadDataSourceNotIn(List<String> values) {
            addCriterion("upload_data_source not in", values, "uploadDataSource");
            return (Criteria) this;
        }

        public Criteria andUploadDataSourceBetween(String value1, String value2) {
            addCriterion("upload_data_source between", value1, value2, "uploadDataSource");
            return (Criteria) this;
        }

        public Criteria andUploadDataSourceNotBetween(String value1, String value2) {
            addCriterion("upload_data_source not between", value1, value2, "uploadDataSource");
            return (Criteria) this;
        }

        public Criteria andBizTypeIsNull() {
            addCriterion("biz_type is null");
            return (Criteria) this;
        }

        public Criteria andBizTypeIsNotNull() {
            addCriterion("biz_type is not null");
            return (Criteria) this;
        }

        public Criteria andBizTypeEqualTo(String value) {
            addCriterion("biz_type =", value, "bizType");
            return (Criteria) this;
        }

        public Criteria andBizTypeNotEqualTo(String value) {
            addCriterion("biz_type <>", value, "bizType");
            return (Criteria) this;
        }

        public Criteria andBizTypeGreaterThan(String value) {
            addCriterion("biz_type >", value, "bizType");
            return (Criteria) this;
        }

        public Criteria andBizTypeGreaterThanOrEqualTo(String value) {
            addCriterion("biz_type >=", value, "bizType");
            return (Criteria) this;
        }

        public Criteria andBizTypeLessThan(String value) {
            addCriterion("biz_type <", value, "bizType");
            return (Criteria) this;
        }

        public Criteria andBizTypeLessThanOrEqualTo(String value) {
            addCriterion("biz_type <=", value, "bizType");
            return (Criteria) this;
        }

        public Criteria andBizTypeLike(String value) {
            addCriterion("biz_type like", value, "bizType");
            return (Criteria) this;
        }

        public Criteria andBizTypeNotLike(String value) {
            addCriterion("biz_type not like", value, "bizType");
            return (Criteria) this;
        }

        public Criteria andBizTypeIn(List<String> values) {
            addCriterion("biz_type in", values, "bizType");
            return (Criteria) this;
        }

        public Criteria andBizTypeNotIn(List<String> values) {
            addCriterion("biz_type not in", values, "bizType");
            return (Criteria) this;
        }

        public Criteria andBizTypeBetween(String value1, String value2) {
            addCriterion("biz_type between", value1, value2, "bizType");
            return (Criteria) this;
        }

        public Criteria andBizTypeNotBetween(String value1, String value2) {
            addCriterion("biz_type not between", value1, value2, "bizType");
            return (Criteria) this;
        }

        public Criteria andMemoIsNull() {
            addCriterion("memo is null");
            return (Criteria) this;
        }

        public Criteria andMemoIsNotNull() {
            addCriterion("memo is not null");
            return (Criteria) this;
        }

        public Criteria andMemoEqualTo(String value) {
            addCriterion("memo =", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotEqualTo(String value) {
            addCriterion("memo <>", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoGreaterThan(String value) {
            addCriterion("memo >", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoGreaterThanOrEqualTo(String value) {
            addCriterion("memo >=", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLessThan(String value) {
            addCriterion("memo <", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLessThanOrEqualTo(String value) {
            addCriterion("memo <=", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLike(String value) {
            addCriterion("memo like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotLike(String value) {
            addCriterion("memo not like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoIn(List<String> values) {
            addCriterion("memo in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotIn(List<String> values) {
            addCriterion("memo not in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoBetween(String value1, String value2) {
            addCriterion("memo between", value1, value2, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotBetween(String value1, String value2) {
            addCriterion("memo not between", value1, value2, "memo");
            return (Criteria) this;
        }

        public Criteria andReviewStatusIsNull() {
            addCriterion("review_status is null");
            return (Criteria) this;
        }

        public Criteria andReviewStatusIsNotNull() {
            addCriterion("review_status is not null");
            return (Criteria) this;
        }

        public Criteria andReviewStatusEqualTo(Integer value) {
            addCriterion("review_status =", value, "reviewStatus");
            return (Criteria) this;
        }

        public Criteria andReviewStatusNotEqualTo(Integer value) {
            addCriterion("review_status <>", value, "reviewStatus");
            return (Criteria) this;
        }

        public Criteria andReviewStatusGreaterThan(Integer value) {
            addCriterion("review_status >", value, "reviewStatus");
            return (Criteria) this;
        }

        public Criteria andReviewStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("review_status >=", value, "reviewStatus");
            return (Criteria) this;
        }

        public Criteria andReviewStatusLessThan(Integer value) {
            addCriterion("review_status <", value, "reviewStatus");
            return (Criteria) this;
        }

        public Criteria andReviewStatusLessThanOrEqualTo(Integer value) {
            addCriterion("review_status <=", value, "reviewStatus");
            return (Criteria) this;
        }

        public Criteria andReviewStatusIn(List<Integer> values) {
            addCriterion("review_status in", values, "reviewStatus");
            return (Criteria) this;
        }

        public Criteria andReviewStatusNotIn(List<Integer> values) {
            addCriterion("review_status not in", values, "reviewStatus");
            return (Criteria) this;
        }

        public Criteria andReviewStatusBetween(Integer value1, Integer value2) {
            addCriterion("review_status between", value1, value2, "reviewStatus");
            return (Criteria) this;
        }

        public Criteria andReviewStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("review_status not between", value1, value2, "reviewStatus");
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