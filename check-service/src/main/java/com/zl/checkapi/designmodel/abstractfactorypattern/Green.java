package com.zl.checkapi.designmodel.abstractfactorypattern;

public class Green implements Color {
    @Override
    public void fill() {
        System.out.println("green");
    }
}
