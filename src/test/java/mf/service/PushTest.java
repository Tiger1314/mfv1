package mf.service;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import webcat.task.PushTask;

/**
 * Created by dengfan on 2017/5/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mf.xml"})
public class PushTest {

    @Autowired
    private PushTask pushTask;

    @org.junit.Test
    public void test1(){
        pushTask.pushNightMessage();
    }
}
