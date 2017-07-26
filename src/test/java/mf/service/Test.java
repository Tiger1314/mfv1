package mf.service;

import mf.entity.MfHouseInfoEntity;
import mf.entity.MfRechargeOrderEntity;
import mf.entity.MfUserEntity;
import mf.utils.DateUtils;
import mf.utils.PropertyUtil;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import webcat.utils.OrderUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dengfan on 2017/3/31.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mf.xml"})
public class Test {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MfUserService mfUserService;

    @org.junit.Test
    public void test1() throws IOException {

        String appID = PropertyUtil.getProperty("appID");
        logger.info(appID);
    }

}
