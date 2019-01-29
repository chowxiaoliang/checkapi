package com.zl.checkapi.designmodel.factorymethodpattern;

/**
 * @desc 工厂方法模式
 */
public class MainTest {
    public static void main(String[] args) {
        FactoryA factoryA = new FactoryA();
        Product productA = factoryA.Manufacture();
        productA.show();

        FactoryB factoryB = new FactoryB();
        Product productB = factoryB.Manufacture();
        productB.show();
    }
}
