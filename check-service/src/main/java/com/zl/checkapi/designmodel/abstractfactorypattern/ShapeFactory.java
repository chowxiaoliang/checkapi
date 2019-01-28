package com.zl.checkapi.designmodel.abstractfactorypattern;

public class ShapeFactory extends AbstractFactory {
    @Override
    Shape getShape(String type) {
        if("circle".equals(type)){
            return new Circle();
        }else if ("square".equals(type)){
            return new Square();
        }else if ("rectangle".equals(type)){
            return new Rectangle();
        }else {
            return null;
        }
    }

    @Override
    Color getColor(String color) {
        return null;
    }
}
