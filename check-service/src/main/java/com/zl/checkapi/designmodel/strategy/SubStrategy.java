package com.zl.checkapi.designmodel.strategy;

public class SubStrategy implements Strategy {
    @Override
    public int calC(int a, int b) {
        return a - b ;
    }
}
