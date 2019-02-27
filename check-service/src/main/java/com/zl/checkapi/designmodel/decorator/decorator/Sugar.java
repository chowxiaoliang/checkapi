package com.zl.checkapi.designmodel.decorator.decorator;


import com.zl.checkapi.designmodel.decorator.component.Drink;

public class Sugar extends Decorator {

    public Sugar(Drink drink) {
        super(drink);
    }

    @Override
    public int calPrice() {
        return super.drink.calPrice() + 2;
    }

    @Override
    public String getName() {
        return "加糖的" + super.drink.getName();
    }
}
