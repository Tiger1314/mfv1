package webcat.controller;

import com.alibaba.fastjson.JSONObject;
import mf.entity.*;
import mf.service.*;
import mf.utils.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import webcat.entity.ParamData;
import webcat.utils.*;

import java.util.*;

/**
 * Created by dengfan on 2017/3/5.
 */
@Controller
@RequestMapping(value = "/webcat")
public class WebCatController extends AbstractController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MfHouseInfoService mfHouseInfoService;
    @Autowired
    private MfUserService mfUserService;
    @Autowired
    private MfAreaService mfAreaService;
    @Autowired
    private MfRechargeService mfRechargeService;
    @Autowired
    private MfHouseClickService mfHouseClickService;
    @Autowired
    private MfHouseClickLikeService mfHouseClickLikeService;
    @Autowired
    private MfHouseCollectService mfHouseCollectService;
    @Autowired
    private MfPushService mfPushService;
    @Autowired
    private MfAdService mfAdService;
    @Autowired
    private MfRechargeOrderService mfRechargeOrderService;
    @Autowired
    private MfPushLogService mfPushLogService;

    /**
     * 房源广场
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/houselist", produces = "application/json")
    public Object houselist(){
        try{

            ParamData pd = this.getParamData();

            String infoType = pd.getString("info_type");

            //通过订阅设置获取房源
            Map<String, Object> map = new HashMap<String, Object>();

            int page = 1;

            if(StringUtils.isNotBlank(pd.getString("currentPage"))){

                if(Integer.valueOf(pd.getString("currentPage")) > 0){
                    page = Integer.valueOf(pd.getString("currentPage"));
                }

            }

            map.put("offset", (page - 1) * limit);
            map.put("limit", limit);
            map.put("openId", getOpenId());

            if(StringUtils.isNotBlank(infoType)){
                map.put("infoType", infoType);
            }

            List<MfHouseInfoEntity> list = mfPushLogService.queryMyHouse(map);

            return HouseUtils.getHouses(list);

        }
        catch(Exception e){
            logger.error("houselist", e);
            return sendFailure("系统异常");
        }
    }

    /**
     * 房源详情
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/house", produces = "application/json")
    public Object house(){

        ParamData pd = this.getParamData();

        String houseId = pd.getString("id");

        if(StringUtils.isBlank(houseId)){
            return sendFailure("无效的房源ID.");
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("houseId", houseId);
        map.put("openId", getOpenId());
        List<MfHouseClickEntity> temp = mfHouseClickService.queryList(map);

        if(temp == null || temp.size() == 0){
            //插入查看记录
            MfHouseClickEntity click = new MfHouseClickEntity();
            click.setOpenId(getOpenId());
            click.setHouseId(Integer.valueOf(houseId));
            click.setLikes(0);
            click.setCreateTime(DateUtils.getTodayDate());
            mfHouseClickService.save(click);
        }

        //获取房源详情
        MfHouseInfoEntity entity = mfHouseInfoService.queryObject(Long.valueOf(houseId));

        return HouseUtils.getHouse(entity);
    }

    /**
     * 获取房源手速排行
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/houselistlog", produces = "application/json")
    public Object houseclickorder(){

        ParamData pd = this.getParamData();

        String houseId = pd.getString("house_id");

        if(StringUtils.isBlank(houseId)){
            return sendFailure("房源ID无效.");
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("houseId", houseId);
        map.put("openId", getOpenId());

        List<MfHouseClickEntity> list = mfHouseClickService.queryList(map);

        //获取自己的排行
        MfHouseClickEntity entity = mfHouseClickService.queryObjectByOpenId(getOpenId());

        list.add(0, entity);

        return HouseClickUtil.getHouseClicks(list);
    }

    /**
     * 点赞
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/like", produces = "application/json")
    public Object clicklike(){

        ParamData pd = this.getParamData();

        String clickId = pd.getString("id");

        if(StringUtils.isBlank(clickId)){
            return sendFailure("点赞失败,ID错误.");
        }
        //查询是否点赞过
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("clickId", Integer.valueOf(clickId));
        map.put("openId", getOpenId());

        int count = mfHouseClickLikeService.queryTotal(map);

        if(count > 0){
            return sendFailure("不能重复点赞.");
        }

        MfHouseClickLikeEntity entity = new MfHouseClickLikeEntity();

        entity.setOpenId(getOpenId());
        entity.setClickId(Integer.valueOf(clickId));
        entity.setCreateTime(DateUtils.getTodayDate());

        mfHouseClickLikeService.save(entity);

        return sendSuccess();
    }

    /**
     * 一键收藏
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/collect", produces = "application/json")
    public Object collect(){

        ParamData pd = this.getParamData();

        String houseId = pd.getString("house_id");

        if(StringUtils.isBlank(houseId)){
            return sendFailure("房源id错误.");
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("house_id", Integer.valueOf(houseId));
        map.put("openId", getOpenId());

        int count = mfHouseCollectService.queryTotal(map);

        if(count > 0){
            return sendFailure("不能重复收藏.");
        }

        MfHouseCollectEntity entity = new MfHouseCollectEntity();
        entity.setOpenId(getOpenId());
        entity.setHouseId(Integer.valueOf(houseId));
        entity.setCreateTime(DateUtils.getTodayDate());

        mfHouseCollectService.save(entity);

        return sendSuccess();
    }

    /**
     * 取消收藏
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/uncollect", produces = "application/json")
    public Object uncollect(){

        ParamData pd = this.getParamData();

        String houseId = pd.getString("house_id");

        if(StringUtils.isBlank(houseId)){
            return sendFailure("房源id错误.");
        }

        MfHouseCollectEntity entity = new MfHouseCollectEntity();
        entity.setOpenId(getOpenId());
        entity.setHouseId(Integer.valueOf(houseId));
        mfHouseCollectService.del(entity);

        return sendSuccess();
    }

    /**
     * 我的收藏
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/mycollect", produces = "application/json")
    public Object mycollect(){
        ParamData pd = this.getParamData();

        //通过订阅设置获取房源
        Map<String, Object> map = new HashMap<>();

        int page = 1;

        if(StringUtils.isNotBlank(pd.getString("currentPage"))){

            if(Integer.valueOf(pd.getString("currentPage")) > 0){
                page = Integer.valueOf(pd.getString("currentPage"));
            }

        }

        map.put("offset", (page - 1) * limit);
        map.put("limit", limit);
        map.put("openId", getOpenId());

        List<MfHouseInfoEntity> list = mfHouseCollectService.queryMyCollect(map);

        return HouseUtils.getCollect(list);
    }

    /**
     * 获取用户信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/userinfo", produces = "application/json")
    public Object userinfo(){

        String openId = getOpenId();

        if(StringUtils.isBlank(openId)){
            return sendFailure("没有获取到用户信息.");
        }

        MfUserEntity entity = mfUserService.queryObject(openId);

        return UserUtil.getUser(entity);
    }

    /**
     * 举报房源
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/housereport", produces = "application/json")
    public Object housereport(){

        ParamData pd = this.getParamData();

        String id = pd.getString("id");

        if(StringUtils.isBlank(id)){
            return sendFailure("房源ID错误.");
        }

        mfHouseInfoService.housereport(Long.valueOf(id));

        return sendSuccess();
    }

    /**
     * 获取充值信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getRecharge", produces = "application/json")
    public Object getrecharge(){

        String openId = getOpenId();

        if(StringUtils.isBlank(openId)){
            return sendFailure("获取openId失败,无法充值.");
        }

        List<MfRechargeEntity> l = new LinkedList<MfRechargeEntity>();

        //获取两个包月信息
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("offset", 0);
        map.put("limit", 2);
        map.put("type", 1);

        List<MfRechargeEntity> list = mfRechargeService.queryList(map);
        l.addAll(list);

        //获取4个蜗牛币信息

        map.clear();
        map.put("offset", 0);
        map.put("limit", 4);
        map.put("type", 2);

        list = mfRechargeService.queryList(map);
        l.addAll(list);

        return RechargeUtil.getRecharges(l);
    }


    /**
     * 获取省份，直辖市
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getprovince", produces = "application/json")
    public Object getprovince(){

        Map<String, Object> map = new HashMap<>();

        map.put("degree", 1);
        map.put("parentCode", 0);

        List<MfAreaEntity> list = mfAreaService.queryList(map);

        return AreaUtil.getAreas(list);
    }


    /**
     * 获取市
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getcity", produces = "application/json")
    public Object getcity(){

        ParamData pd = this.getParamData();

        Map<String, Object> map = new HashMap<>();

        map.put("degree", 2);
        map.put("parent_code", pd.getString("parent_code"));

        List<MfAreaEntity> list = mfAreaService.queryList(map);

        return AreaUtil.getAreas(list);
    }

    /**
     * 获取地区
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getcontry", produces = "application/json")
    public Object getcontry(){
        ParamData pd = this.getParamData();

        Map<String, Object> map = new HashMap<>();

        map.put("degree", 3);
        map.put("parent_code", pd.getString("parent_code"));

        List<MfAreaEntity> list = mfAreaService.queryList(map);

        return AreaUtil.getAreas(list);
    }

    /**
     * 获取商圈
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getbusinessdistrict", produces = "application/json")
    public Object getbusinessdistrict(){
        ParamData pd = this.getParamData();

        Map<String, Object> map = new HashMap<>();

        map.put("degree", 4);
        map.put("parent_code", pd.getString("parent_code"));

        List<MfAreaEntity> list = mfAreaService.queryList(map);

        return AreaUtil.getAreas(list);
    }


    /**
     * 获取推送设置
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getpush", produces = "application/json")
    public Object getpush(){

        MfPushEntity entity = mfPushService.queryObject(getOpenId());

        return PushUtil.getPush(entity);
    }

    /**
     * 设置推送
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/setpush", produces = "application/json")
    public Object setpush(){

        MfPushEntity entity = new MfPushEntity();

        ParamData pd = this.getParamData();

        if(StringUtils.isNotBlank(pd.getString("house_type"))){
            entity.setHouseType(pd.getString("house_type"));
        }

        if(StringUtils.isNumeric(pd.getString("is_sell"))){
            entity.setIsSell(Integer.valueOf(pd.getString("is_sell")));
        }
        if(StringUtils.isNotBlank(pd.getString("sell_type"))){
            entity.setSellType(pd.getString("sell_type"));
        }
        if(StringUtils.isNotBlank(pd.getString("sell_area"))){
            entity.setSellArea(pd.getString("sell_area"));
        }
        if(StringUtils.isNotBlank(pd.getString("sell_price"))){
            entity.setSellPrice(pd.getString("sell_price"));
        }

        if(StringUtils.isNumeric(pd.getString("is_buy"))){
            entity.setIsBuy(Integer.valueOf(pd.getString("is_buy")));
        }
        if(StringUtils.isNotBlank(pd.getString("buy_type"))){
            entity.setBuyType(pd.getString("buy_type"));
        }
        if(StringUtils.isNotBlank(pd.getString("buy_area"))){
            entity.setBuyArea(pd.getString("buy_area"));
        }
        if(StringUtils.isNotBlank(pd.getString("buy_price"))){
            entity.setBuyPrice(pd.getString("buy_price"));
        }

        if(StringUtils.isNumeric(pd.getString("is_rent"))){
            entity.setIsRent(Integer.valueOf(pd.getString("is_rent")));
        }
        if(StringUtils.isNotBlank(pd.getString("rent_type"))){
            entity.setRentType(pd.getString("rent_type"));
        }
        if(StringUtils.isNotBlank(pd.getString("rent_area"))){
            entity.setRentArea(pd.getString("rent_area"));
        }
        if(StringUtils.isNotBlank(pd.getString("rent_price"))){
            entity.setRentPrice(pd.getString("rent_price"));
        }

        if(StringUtils.isNumeric(pd.getString("is_qz"))){
            entity.setIsQz(Integer.valueOf(pd.getString("is_qz")));
        }
        if(StringUtils.isNotBlank(pd.getString("qz_type"))){
            entity.setQzType(pd.getString("qz_type"));
        }
        if(StringUtils.isNotBlank(pd.getString("qz_area"))){
            entity.setQzArea(pd.getString("qz_area"));
        }
        if(StringUtils.isNotBlank(pd.getString("qz_price"))){
            entity.setQzPrice(pd.getString("qz_price"));
        }

        if(StringUtils.isNotBlank(pd.getString("push_area"))){
            entity.setPushArea(pd.getString("push_area"));
        }

        mfPushService.save(entity);

        return sendSuccess();
    }

    /**
     * 获取广告
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getAd", produces = "application/json")
    public Object getAd(){

        List<MfAdEntity> list = mfAdService.queryList(new HashMap<String, Object>());

        //取一条广告
        MfAdEntity entity = new MfAdEntity();
        if(list != null && list.size() != 0){
            entity = list.get(0);
        }
        MfUserEntity user = mfUserService.queryObject(getOpenId());

        JSONObject ad = AdUtil.getAd(entity, user);

        //更新用户广告显示时间
        if(ad.getIntValue("show") == 1){
            user.setAdShow(DateUtils.getTodayDate());
            mfUserService.update(user);
        }

        return ad;
    }

    /**
     * 获取用户所有消费订单
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getOrderList", produces = "application/json")
    public Object getOrder(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("openId", getOpenId());

        List<MfRechargeOrderEntity> list = mfRechargeOrderService.queryList(map);

        MfUserEntity user = mfUserService.queryObject(getOpenId());

        return OrderUtil.getOrders(list, user.getWnb());
    }

    /**
     * 设置勿扰模式
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/wrset", produces = "application/json")
    public Object wrset(){

        ParamData pd = this.getParamData();

        MfUserEntity user = new MfUserEntity();

        if(StringUtils.isNotBlank(pd.getString("wrset"))){
            user.setWrStatus(Integer.valueOf(pd.getString("wrset")));
            user.setOpenId(getOpenId());

            mfUserService.update(user);
        }
        return sendSuccess();
    }
}
