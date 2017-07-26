package mf.service;

import mf.entity.*;
import mf.utils.DateUtils;
import org.junit.*;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import webcat.utils.MessageConstants;
import webcat.utils.OrderUtil;
import webcat.utils.UserUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dengfan on 2017/4/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mf.xml"})
public class Test2 {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MfUserService mfUserService;
    @Autowired
    private MfHouseInfoService mfHouseInfoService;
    @Autowired
    private MfComplaintsService mfComplaintsService;

    @org.junit.Test
    public void test()throws Exception{
        String openId = "o9F5mwSKeH17dZ8WPCLteAsYx25E";

        MfUserEntity user = mfUserService.queryObject(openId);

        MfComplaintsEntity c;
        MfHouseInfoEntity h;

        for(int i = 7; i <= 15; i ++){

            c = new MfComplaintsEntity();
            h = mfHouseInfoService.queryObject((long)i);

            c.setOpenId(user.getOpenId());
            c.setNickname(user.getNickname());
            c.setHouseId(h.getId().intValue());
            c.setHouseTitle(h.getTitle());
            c.setHouseUrl(h.getUrl());
            c.setStatus(0);
            c.setCreateTime(DateUtils.getTodayDate());
            c.setUpdateTime(DateUtils.getTodayDate());

            mfComplaintsService.save(c);
        }
    }
}
