package com.zl.checkapi.designmodel.decorator.decorator;


import com.zl.checkapi.designmodel.decorator.component.Drink;
/**
 * 装饰者模式
 * https://blog.csdn.net/android_zyf/article/details/68343953
 */
public abstract class Decorator extends Drink {

    Drink drink;

    Decorator(Drink drink){
        this.drink = drink;
    }
}
