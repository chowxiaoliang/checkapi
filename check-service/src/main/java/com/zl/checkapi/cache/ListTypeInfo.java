package com.zl.checkapi.cache;

import com.zl.checkapi.pojo.CertNOToStarRule;
import org.I0Itec.zkclient.ZkLock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ListTypeInfo {
    /**
     * 一级分类
     */
    private List<String> fstType = new ArrayList<>();
    /**
     * 二级分类
     */
    private List<String> secType = new ArrayList<>();
    /**
     * 一级分类和二级分类code对应的关系
     */
    private Map<String, List<String>> relationCode = new HashMap<>(16);
    /**
     * 身份证转*号的二级分类的itemCode
     */
    private List<String> certNoToStarList = new ArrayList<>();
    /**
     * 身份证转*号的转换规则
     */
    private List<CertNOToStarRule> certNoToStarRultList = new ArrayList<>();
    /**
     * 读写互斥锁
     */
    private ReentrantReadWriteLock reentrantLock = new ReentrantReadWriteLock();


    public List<String> getFstType() {
        try{
            reentrantLock.readLock().lock();
            return fstType;
        }finally {
            reentrantLock.readLock().unlock();
        }
    }

    public void setFstType(List<String> fstType) {
        this.fstType = fstType;
    }

    public List<String> getSecType() {
        try{
            reentrantLock.readLock().lock();
            return secType;
        }finally {
            reentrantLock.readLock().unlock();
        }
    }

    public void setSecType(List<String> secType) {
        this.secType = secType;
    }

    public Map<String, List<String>> getRelationCode() {
        try{
            reentrantLock.readLock().lock();
            return relationCode;
        }finally {
            reentrantLock.readLock().unlock();
        }
    }

    public void setRelationCode(Map<String, List<String>> relationCode) {
        this.relationCode = relationCode;
    }

    public List<String> getCertNoToStarList() {
        try{
            reentrantLock.readLock().lock();
            return certNoToStarList;
        }finally {
            reentrantLock.readLock().unlock();
        }
    }

    public void setCertNoToStarList(List<String> certNoToStarList) {
        this.certNoToStarList = certNoToStarList;
    }

    public List<CertNOToStarRule> getCertNoToStarRultList() {
        try{
            reentrantLock.readLock().lock();
            return certNoToStarRultList;
        }finally {
            reentrantLock.readLock().unlock();
        }
    }

    public void setCertNoToStarRultList(List<CertNOToStarRule> certNoToStarRultList) {
        this.certNoToStarRultList = certNoToStarRultList;
    }

    public ReentrantReadWriteLock getReentrantLock() {
        return reentrantLock;
    }

    public void setReentrantLock(ReentrantReadWriteLock reentrantLock) {
        this.reentrantLock = reentrantLock;
    }



}
