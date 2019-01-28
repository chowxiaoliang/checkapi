package com.zl.checkapi.designmodel.abstractfactorypattern;

public class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("square");
    }
}
