package reflect.beaninfo;

import reflect.annotation.Autowired;
import reflect.annotation.Service;

/**
 * @author zhouliang
 * @since 2018-06-20 11:24
 **/
@Service(value = "person")
public class Person {
    private String certNo;

    @Autowired(value = "yangxiaoxiao")
    private String name;
    @Autowired(value = "13227136694")
    private String mobile;

    private String bankCard;

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

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }
}
