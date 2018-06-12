package thread.interrupt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhouliang
 * @since 2018-06-05 10:47
 **/
public class InterruptTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(InterruptTest.class);

    private String certNo;

    private String name;

    private String mobile;

    public InterruptTest(String certNo, String name, String mobile){
        this.certNo = certNo;
        this.name = name;
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "InterruptTest{" +
                "certNo='" + certNo + '\'' +
                ", name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }

    public void basicInfo(){
        LOGGER.info("basic info is => {}", toString());
    }

    public void InterruptTest(){
        for(int i=0;i<10;i++){
            LOGGER.info("certNo is => {}, name is => {}, mobile is => {}", certNo, name, mobile);
        }
    }
}
