package com.zl.checkapi.designmodel.factorymethodpattern;

public class FactoryB implements Factory {
    @Override
    public Product Manufacture() {
        return new ProductB();
    }
}
