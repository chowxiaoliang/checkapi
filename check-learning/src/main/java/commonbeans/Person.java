package commonbeans;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author zhouliang
 * @since 2018-06-12 15:21
 * @desc hashCode和equals方法覆盖 关于commons lang的应用
 * https://www.ibm.com/developerworks/cn/education/java/j-lessismore/j-lessismore.html
 * 如果最终实现 equals 方法，那么还需要实现 hashCode 方法（因为 equals 的契约表明，两个相等的对象必须有相同的散列码）
 * 实现equals方法必须遵从一定的契约
 * 1.具有反射性
 * 2.具有对称性
 * 3.具有传递性
 * 4.具有一致性
 * 5.能够适当地处理 null
 *
 * 实现hashCode方法也必须遵从一定的契约
 * hashCode 方法也有一个契约，但是不像 equals 的契约那样正式。然而，重要的是要理解它。和 equals 一样，结果必须一致。
 * 对于对象 foo 和 bar，如果 foo.equals(bar) 返回 true，那么 foo 和 bar 的 hashCode 方法必须返回相同的值。
 * 如果 foo 和 bar 不相等，则不要求返回不同的散列码。但是，Javadocs 提到，如果这些对象有不同的结果，那么通常会运行得更好一些。
 *还需注意，和之前一样，如果没有覆盖它，hashCode 会返回一个看似随机的整数。
 * 这是因为底层平台通常会将基对象的地址位置转换成一个整数；虽然如此，但文档中提到这并不是必需的，因此可以改变。
 * 无论如何，如果最终覆盖 equals 方法，那么也有必要覆盖 hashCode 方法。
 * 记住，虽然 hashCode 方法看上去是开箱即用的，但是 Joshua Bloch 的 Effective Java 花了 6 页的篇幅讨论如何适当地实现 hashCode 方法）
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
