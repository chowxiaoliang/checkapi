package com.zl.checkapi.designmodel.decorator.component;

public class Cola extends Drink {

    public Cola(){
        super.name = "cola";
    }

    @Override
    public int calPrice() {
        return 10;
    }
}
