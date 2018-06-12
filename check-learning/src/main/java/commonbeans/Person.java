package commonbeans;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author zhouliang
 * @since 2018-06-12 15:21
 * @desc hashCode和equals方法覆盖 关于commons lang的应用
 * https://www.ibm.com/developerworks/cn/education/java/j-lessismore/j-lessismore.html
 * 实现equals方法必须遵从一定的契约
 * 1.具有反射性
 * 2.具有对称性
 * 3.具有传递性
 * 4.具有一致性
 **/
public class Person {

    private String certNo;

    private String name;

    private String mobile;

    private String bankCardNo;

    private String address;

    public Person(String certNo, String name, String mobile, String bankCardNo, String address){
        this.certNo = certNo;
        this.name = name;
        this.mobile = mobile;
        this.bankCardNo = bankCardNo;
        this.address = address;
    }

    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public int hashCode(){
        return HashCodeBuilder.reflectionHashCode(this, true);
    }

    @Override
    public boolean equals(Object obj){
        return EqualsBuilder.reflectionEquals(this, obj, true);
    }

    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString(this);
    }

}
