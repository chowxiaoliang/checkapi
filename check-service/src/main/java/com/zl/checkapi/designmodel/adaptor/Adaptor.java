package com.zl.checkapi.designmodel.adaptor;

public class Adaptor implements Target {

    private Adaptee adaptee;
    /**
     * 构造方法，传入需要被适配的对象
     * @param adaptee 需要被适配的对象
     */
    public Adaptor(Adaptee adaptee){
        this.adaptee = adaptee;
    }
    @Override
    public void request() {
        adaptee.specificRequest();
    }
}
