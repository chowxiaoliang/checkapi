package com.zl.checkapi.designmodel.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * 观察者
 */
public class NumObserver implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        NumObservable numObservable = (NumObservable)o;
        System.out.println("data has changed to =>" + numObservable.getData());
    }
}
