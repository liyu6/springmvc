package com.springmvc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

/**
 * 线程池测试类
 * @Title: ThreadPoolTest
 * @Description:
 * @Author: qianyikai from yitang， E-mail: 469640411@qq.com
 * @Date: Created in 2018/8/8 16:37
 * @Version 1.0
 * @Company ShangHaiYiTang Co., Ltd.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring-context.xml"})
@Transactional
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class})
public class ThreadPoolTest {
    private static final Logger logger = LoggerFactory.getLogger(ThreadPoolTest.class);
    @Autowired
    private ThreadPoolTaskExecutor fixThreadPool;

    @Test
    public void test(){
        fixThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <100000 ; i++) {
                    logger.info("i为{}",i);
                }
            }
        });
    }

}
