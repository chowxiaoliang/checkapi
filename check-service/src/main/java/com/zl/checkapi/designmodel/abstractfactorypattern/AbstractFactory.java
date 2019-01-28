package com.zl.checkapi.designmodel.abstractfactorypattern;

public abstract class AbstractFactory {

    abstract Shape getShape(String type);

    abstract Color getColor(String color);
}
