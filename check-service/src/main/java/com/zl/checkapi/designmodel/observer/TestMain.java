package com.zl.checkapi.designmodel.observer;

public class TestMain {
    public static void main(String[] args) {
        NumObservable numObservable = new NumObservable();
        numObservable.addObserver(new NumObserver());
        numObservable.setData(1);
        numObservable.setData(2);
        numObservable.setData(3);
    }
}
