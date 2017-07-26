package webcat.controller;

import com.alibaba.fastjson.JSONObject;
import mf.entity.MfLotteryEntity;
import mf.entity.MfUserEntity;
import mf.service.MfLotteryService;
import mf.service.MfRechargeOrderService;
import mf.service.MfUserService;
import mf.utils.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import webcat.lottery.Prize;
import webcat.lottery.PrizeUtil;

/**
 * Created by admin on 2017/6/12.
 */
@Controller
@RequestMapping(value = "/lottery")
public class LotteryController extends AbstractController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MfLotteryService mfLotteryService;
    @Autowired
    private MfUserService mfUserService;
    @Autowired
    private MfRechargeOrderService mfRechargeOrderService;

    /**
     * 抽奖
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/lottery", produces = "application/json")
    public Object lottery(){

        String openId = getOpenId();

        if(StringUtils.isBlank(openId)){
            return sendFailure("没有获取到用户信息.");
        }

        //判断是否可以抽奖
        if(!mfLotteryService.checkCanLottery(openId)){
            return sendFailure("抽奖次数已经用完.");
        }

        //执行抽奖
        PrizeUtil p = new PrizeUtil();
        Prize prize = p.getPrize();

        MfUserEntity user = mfUserService.queryObject(openId);

        //插入抽奖记录
        MfLotteryEntity lotteryEntity = new MfLotteryEntity();
        lotteryEntity.setOpenId(openId);
        lotteryEntity.setPoint(prize.getWnb());
        lotteryEntity.setCreateTime(DateUtils.getTodayDate());
        lotteryEntity.setUpdateTime(DateUtils.getTodayDate());
        lotteryEntity.setNickname(user.getNickname());
        lotteryEntity.setAttr1(prize.getName());

        mfLotteryService.save(lotteryEntity);

        user.setWnb(user.getWnb() + prize.getWnb());

        mfUserService.update(user);

        mfRechargeOrderService.addOrder(user.getOpenId(), prize.getName() + "[抽奖]", "蜗牛壳", prize.getWnb() + "", 5, null);

        JSONObject mes = new JSONObject();
        mes.put("code", "success");
        mes.put("name", prize.getName());
        mes.put("point", prize.getWnb());
        mes.put("count", mfLotteryService.canlotteryCount(openId));

        logger.info("####################################抽奖成功#############################################");

        return mes;
    }

    /**
     * 是否能抽奖
     * isLottery 0 不能抽奖  1 可以抽奖
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/isLottery", produces = "application/json")
    public Object isLottery(){

        String openId = getOpenId();

        if(StringUtils.isBlank(openId)){
            return sendFailure("没有获取到用户信息.");
        }

        //判断是否可以抽奖
        if(mfLotteryService.checkCanLottery(openId)){
            return sendSuccess("isLottery", 1);
        }

        return sendSuccess("isLottery", 0);
    }
}
