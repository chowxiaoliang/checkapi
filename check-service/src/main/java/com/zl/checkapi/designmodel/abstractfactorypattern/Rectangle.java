package com.zl.checkapi.designmodel.abstractfactorypattern;

public class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("rectangle");
    }
}
