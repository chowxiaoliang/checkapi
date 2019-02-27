package com.zl.checkapi.designmodel.decorator.decorator;


import com.zl.checkapi.designmodel.decorator.component.Drink;

public class Ice extends Decorator {

    public Ice(Drink drink) {
        super(drink);
    }

    @Override
    public int calPrice() {
        return super.drink.calPrice() + 1;
    }

    @Override
    public String getName() {
        return "加冰的" + super.drink.getName();
    }
}
