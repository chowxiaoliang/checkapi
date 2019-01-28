package com.zl.checkapi.designmodel.abstractfactorypattern;

public class FactoryProducer {

    static AbstractFactory getFactory(String type){
        if ("color".equals(type)){
            return new ColorFactory();
        }else if ("shape".equals(type)){
            return new ShapeFactory();
        }else {
            return null;
        }
    }

}
