package com.zl.checkapi.mysql.domain;

import java.util.Date;

public class UsrPartner {
    private String customId;

    private String partnerId;

    private String operationId;

    private String customAlias;

    private Integer partnerStatus;

    private Integer status;

    private Integer trailDays;

    private Date effectiveDate;

    private Date expireDate;

    private String partnerName;

    private String partnerFullname;

    private String legelPerson;

    private String partnerCity;

    private String partnerProvince;

    private String verifyKey;

    private String partnerLevel;

    private String partnerDesp;

    private String businessLicenceCode;

    private String businessLicenceFile;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    private String contractType;

    private Integer trainIn;

    private String financeType;

    private Integer creditCloudStatus;

    private Integer esStatus;

    private String oldVerifyKey;

    private String usrLocation;

    private String encryptKey;

    private Date encryptKeyTime;

    public String getCustomId() {
        return customId;
    }

    public void setCustomId(String customId) {
        this.customId = customId == null ? null : customId.trim();
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId == null ? null : partnerId.trim();
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId == null ? null : operationId.trim();
    }

    public String getCustomAlias() {
        return customAlias;
    }

    public void setCustomAlias(String customAlias) {
        this.customAlias = customAlias == null ? null : customAlias.trim();
    }

    public Integer getPartnerStatus() {
        return partnerStatus;
    }

    public void setPartnerStatus(Integer partnerStatus) {
        this.partnerStatus = partnerStatus;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTrailDays() {
        return trailDays;
    }

    public void setTrailDays(Integer trailDays) {
        this.trailDays = trailDays;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName == null ? null : partnerName.trim();
    }

    public String getPartnerFullname() {
        return partnerFullname;
    }

    public void setPartnerFullname(String partnerFullname) {
        this.partnerFullname = partnerFullname == null ? null : partnerFullname.trim();
    }

    public String getLegelPerson() {
        return legelPerson;
    }

    public void setLegelPerson(String legelPerson) {
        this.legelPerson = legelPerson == null ? null : legelPerson.trim();
    }

    public String getPartnerCity() {
        return partnerCity;
    }

    public void setPartnerCity(String partnerCity) {
        this.partnerCity = partnerCity == null ? null : partnerCity.trim();
    }

    public String getPartnerProvince() {
        return partnerProvince;
    }

    public void setPartnerProvince(String partnerProvince) {
        this.partnerProvince = partnerProvince == null ? null : partnerProvince.trim();
    }

    public String getVerifyKey() {
        return verifyKey;
    }

    public void setVerifyKey(String verifyKey) {
        this.verifyKey = verifyKey == null ? null : verifyKey.trim();
    }

    public String getPartnerLevel() {
        return partnerLevel;
    }

    public void setPartnerLevel(String partnerLevel) {
        this.partnerLevel = partnerLevel == null ? null : partnerLevel.trim();
    }

    public String getPartnerDesp() {
        return partnerDesp;
    }

    public void setPartnerDesp(String partnerDesp) {
        this.partnerDesp = partnerDesp == null ? null : partnerDesp.trim();
    }

    public String getBusinessLicenceCode() {
        return businessLicenceCode;
    }

    public void setBusinessLicenceCode(String businessLicenceCode) {
        this.businessLicenceCode = businessLicenceCode == null ? null : businessLicenceCode.trim();
    }

    public String getBusinessLicenceFile() {
        return businessLicenceFile;
    }

    public void setBusinessLicenceFile(String businessLicenceFile) {
        this.businessLicenceFile = businessLicenceFile == null ? null : businessLicenceFile.trim();
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType == null ? null : contractType.trim();
    }

    public Integer getTrainIn() {
        return trainIn;
    }

    public void setTrainIn(Integer trainIn) {
        this.trainIn = trainIn;
    }

    public String getFinanceType() {
        return financeType;
    }

    public void setFinanceType(String financeType) {
        this.financeType = financeType == null ? null : financeType.trim();
    }

    public Integer getCreditCloudStatus() {
        return creditCloudStatus;
    }

    public void setCreditCloudStatus(Integer creditCloudStatus) {
        this.creditCloudStatus = creditCloudStatus;
    }

    public Integer getEsStatus() {
        return esStatus;
    }

    public void setEsStatus(Integer esStatus) {
        this.esStatus = esStatus;
    }

    public String getOldVerifyKey() {
        return oldVerifyKey;
    }

    public void setOldVerifyKey(String oldVerifyKey) {
        this.oldVerifyKey = oldVerifyKey == null ? null : oldVerifyKey.trim();
    }

    public String getUsrLocation() {
        return usrLocation;
    }

    public void setUsrLocation(String usrLocation) {
        this.usrLocation = usrLocation == null ? null : usrLocation.trim();
    }

    public String getEncryptKey() {
        return encryptKey;
    }

    public void setEncryptKey(String encryptKey) {
        this.encryptKey = encryptKey == null ? null : encryptKey.trim();
    }

    public Date getEncryptKeyTime() {
        return encryptKeyTime;
    }

    public void setEncryptKeyTime(Date encryptKeyTime) {
        this.encryptKeyTime = encryptKeyTime;
    }
}