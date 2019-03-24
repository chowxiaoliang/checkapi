package check;

/**
 * @author zhouliang
 * @desc 签名申请当前阶段
 * @since 2019/2/20
 */
public enum SignStage {
    REGIST("注册"),
    UPLOAD("上传待签名合同"),
    SIGN("签名"),
    DOWNLOAD("下载已签名合同");

    private String code;

    SignStage(String code){
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
