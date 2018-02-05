package com.zl.checkapi.reptile.citypartition;

public class FinalSingleBean {
    /**
     * 省
     */
    private String province;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getStaticsAreaPartitionCode() {
        return staticsAreaPartitionCode;
    }

    public void setStaticsAreaPartitionCode(String staticsAreaPartitionCode) {
        this.staticsAreaPartitionCode = staticsAreaPartitionCode;
    }

    public String getCityClassificationCode() {
        return cityClassificationCode;
    }

    public void setCityClassificationCode(String cityClassificationCode) {
        this.cityClassificationCode = cityClassificationCode;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    /**
     * 市
     */

    private String city;
    /**
     * 区
     */
    private String county;
    /**
     * 街道
     */
    private String town;
    /**
     * 划分码
     */
    private String staticsAreaPartitionCode;
    /**
     * 城乡分类码
     */
    private String cityClassificationCode;
    /**
     * 居委会或村委会
     */
    private String village;
}
