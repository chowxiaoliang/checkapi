package com.zl.checkapi.designmodel.strategy;

public class MainTest {
    public static void main(String[] args) {
        Environment environment = new Environment(new SubStrategy());
        try {
            Strategy strategy = (Strategy) Class.forName("com.kreditplus.check.designmodel.strategy.SubStrategy").newInstance();
            System.out.println(strategy.calC(4,5));
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        int result = environment.calulate(1, 2);
        System.out.println("the result is => " + result);
    }
}
