package com.zl.checkapi.designmodel.factorymethodpattern;

public class FactoryA implements Factory {
    @Override
    public Product Manufacture() {
        return new ProductA();
    }
}
