package com.zl.checkapi.designmodel.decorator.component;

public class Milk extends Drink {

    public Milk(){
        super.name = "milk";
    }

    @Override
    public int calPrice() {
        return 5;
    }
}
