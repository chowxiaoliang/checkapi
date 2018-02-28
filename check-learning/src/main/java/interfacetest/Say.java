package interfacetest;

public abstract class Say {

    public abstract void say();

    public String str(){
        System.out.println("数据被清洗");
        return "success";
    }
}
