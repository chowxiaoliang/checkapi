package com.zl.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparing;

public class TestMain {

    public static void main(String[] args){

        LambdaComparableTest lambdaComparableTest1 = new LambdaComparableTest();
        lambdaComparableTest1.setAge(21);
        lambdaComparableTest1.setName("yangxiaoxiao");
        lambdaComparableTest1.setSex("female");

        LambdaComparableTest lambdaComparableTest2 = new LambdaComparableTest();
        lambdaComparableTest2.setSex("male");
        lambdaComparableTest2.setName("zhouliang");
        lambdaComparableTest2.setAge(22);

        List<LambdaComparableTest> testList = new ArrayList<>();
        testList.add(lambdaComparableTest1);
        testList.add(lambdaComparableTest2);

        Comparator<LambdaComparableTest> comparableTestComparator = comparing(LambdaComparableTest::getName);
//        Collections.sort(testList, comparableTestComparator);
        testList.sort(comparableTestComparator);
        testList.sort(comparing(LambdaComparableTest::getName));
    }
}
