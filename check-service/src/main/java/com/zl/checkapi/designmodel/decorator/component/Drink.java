package com.zl.checkapi.designmodel.decorator.component;

public abstract class Drink {

    public String name;

    public abstract int calPrice();

    public String getName(){
        return name;
    }
}
