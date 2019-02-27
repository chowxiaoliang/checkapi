package com.zl.checkapi.designmodel.decorator;


import com.zl.checkapi.designmodel.decorator.component.Cola;
import com.zl.checkapi.designmodel.decorator.component.Drink;
import com.zl.checkapi.designmodel.decorator.decorator.Ice;
import com.zl.checkapi.designmodel.decorator.decorator.Sugar;

public class TestMain {
    public static void main(String[] args) {
        Drink drink = new Cola();
        drink = new Ice(drink);
        drink = new Sugar(drink);
        System.out.println("drink name is => " + drink.getName() +",drink price is =>"+ drink.calPrice());
    }
}
