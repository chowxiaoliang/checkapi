package enums;

public enum TestOne {

    EQ("eq", "等于"),
    NQ("nq", "不等于"),
    IN("in", "包含"),
    NOT_IN("not_in", "不包含"),
    PREFIX("prefix", "前缀"),
    POSTFIX("postfix", "后缀");

    private String code;

    private String message;

    TestOne(String code, String message){
        this.code = code;
        this.message = message;
    }

    public String getCode(){
        return code;
    }

    public String getMessage(){
        return message;
    }
}
