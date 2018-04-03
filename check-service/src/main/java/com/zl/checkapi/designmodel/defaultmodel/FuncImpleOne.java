package com.zl.checkapi.designmodel.defaultmodel;

public class FuncImpleOne extends AbstractFuncService {
    @Override
    public void func1(){
        System.out.println("func1");
    }
    public static void main(String[] args){
        FuncImpleOne funcImpleOne = new FuncImpleOne();
        funcImpleOne.func1();
        funcImpleOne.func2();
    }
}
