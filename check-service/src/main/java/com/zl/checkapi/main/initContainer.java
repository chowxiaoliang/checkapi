package com.zl.checkapi.main;

import com.alibaba.dubbo.container.Container;
import com.alibaba.dubbo.container.spring.SpringContainer;
import com.zl.checkapi.task.ReadListTypeJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class initContainer implements Container {
    private final Logger logger = LoggerFactory.getLogger(initContainer.class);
    @Override
    public void start() {
        ReadListTypeJob readListTypeJob = SpringContainer.getContext().getBean("readListTypeJob", ReadListTypeJob.class);
        readListTypeJob.load();
    }

    @Override
    public void stop() {

    }
}
