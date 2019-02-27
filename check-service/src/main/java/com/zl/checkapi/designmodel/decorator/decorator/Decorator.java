package com.zl.checkapi.designmodel.decorator.decorator;


import com.zl.checkapi.designmodel.decorator.component.Drink;

public abstract class Decorator extends Drink {

    Drink drink;

    Decorator(Drink drink){
        this.drink = drink;
    }
}
