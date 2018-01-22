package com.zl.checkapi.mysql.domain;

public class RiskBqsDataFeedback {
    private Long id;

    private String riskFlowNo;

    private String partnerId;

    private String certNo;

    private String name;

    private String mobile;

    private String amount;

    private String loanDate;

    private String bizDate;

    private String overDueAmt;

    private String gmtOvdDate;

    private String gmtOvdDays;

    private String ovdDaysCalways;

    private String uploadDataSource;

    private String bizType;

    private String memo;

    private Integer reviewStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRiskFlowNo() {
        return riskFlowNo;
    }

    public void setRiskFlowNo(String riskFlowNo) {
        this.riskFlowNo = riskFlowNo == null ? null : riskFlowNo.trim();
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId == null ? null : partnerId.trim();
    }

    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo == null ? null : certNo.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount == null ? null : amount.trim();
    }

    public String getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(String loanDate) {
        this.loanDate = loanDate == null ? null : loanDate.trim();
    }

    public String getBizDate() {
        return bizDate;
    }

    public void setBizDate(String bizDate) {
        this.bizDate = bizDate == null ? null : bizDate.trim();
    }

    public String getOverDueAmt() {
        return overDueAmt;
    }

    public void setOverDueAmt(String overDueAmt) {
        this.overDueAmt = overDueAmt == null ? null : overDueAmt.trim();
    }

    public String getGmtOvdDate() {
        return gmtOvdDate;
    }

    public void setGmtOvdDate(String gmtOvdDate) {
        this.gmtOvdDate = gmtOvdDate == null ? null : gmtOvdDate.trim();
    }

    public String getGmtOvdDays() {
        return gmtOvdDays;
    }

    public void setGmtOvdDays(String gmtOvdDays) {
        this.gmtOvdDays = gmtOvdDays == null ? null : gmtOvdDays.trim();
    }

    public String getOvdDaysCalways() {
        return ovdDaysCalways;
    }

    public void setOvdDaysCalways(String ovdDaysCalways) {
        this.ovdDaysCalways = ovdDaysCalways == null ? null : ovdDaysCalways.trim();
    }

    public String getUploadDataSource() {
        return uploadDataSource;
    }

    public void setUploadDataSource(String uploadDataSource) {
        this.uploadDataSource = uploadDataSource == null ? null : uploadDataSource.trim();
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType == null ? null : bizType.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public Integer getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(Integer reviewStatus) {
        this.reviewStatus = reviewStatus;
    }
}