package com.zl.checkapi.designmodel.abstractfactorypattern;

public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("circle");
    }
}
