package com.zl.checkapi.designmodel.observer;

import java.util.Observable;

/**
 * 被观察者
 */
public class NumObservable extends Observable {

    private int data = 0;

    public int getData(){
        return data;
    }

    public void setData(int i){
        data = i;
        setChanged();
        notifyObservers();
    }
}
