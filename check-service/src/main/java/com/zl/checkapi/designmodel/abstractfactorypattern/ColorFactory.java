package com.zl.checkapi.designmodel.abstractfactorypattern;

public class ColorFactory extends AbstractFactory {

    @Override
    Shape getShape(String type) {
        return null;
    }

    @Override
    Color getColor(String color){
        if ("red".equals(color)){
            return new Red();
        }else if ("green".equals(color)){
            return new Green();
        }else if ("blue".equals(color)){
            return new Blue();
        }else {
            return null;
        }
    }
}
