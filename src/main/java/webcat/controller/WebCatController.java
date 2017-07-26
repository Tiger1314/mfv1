package webcat.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
import mf.entity.MfAdEntity;
import mf.entity.MfAreaEntity;
import mf.entity.MfComplaintsEntity;
import mf.entity.MfHouseClickEntity;
import mf.entity.MfHouseClickLikeEntity;
import mf.entity.MfHouseCollectEntity;
import mf.entity.MfHouseInfoEntity;
import mf.entity.MfPushEntity;
import mf.entity.MfRechargeEntity;
import mf.entity.MfRechargeOrderEntity;
import mf.entity.MfUserEntity;
import mf.service.MfAdService;
import mf.service.MfAreaService;
import mf.service.MfComplaintsService;
import mf.service.MfHouseClickLikeService;
import mf.service.MfHouseClickService;
import mf.service.MfHouseCollectService;
import mf.service.MfHouseInfoService;
import mf.service.MfPushLogService;
import mf.service.MfPushService;
import mf.service.MfRechargeOrderService;
import mf.service.MfRechargeService;
import mf.service.MfUserService;
import mf.utils.DateUtils;
import mf.utils.PropertyUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import webcat.entity.ParamData;
import webcat.utils.AdUtil;
import webcat.utils.AreaUtil;
import webcat.utils.HouseClickUtil;
import webcat.utils.HouseUtils;
import webcat.utils.MessageConstants;
import webcat.utils.OrderUtil;
import webcat.utils.PushUtil;
import webcat.utils.RechargeUtil;
import webcat.utils.UserUtil;

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
    @Autowired
    private MfComplaintsService mfComplaintsService;

    /**
     * 房源广场
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/houselist", produces = "application/json" )
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

            if(StringUtils.isNotBlank(infoType) && !infoType.equals("0")){
                map.put("infoType", infoType);
            }

            MfPushEntity push = mfPushService.queryObject(getOpenId());

            //出售
            if(push.getIsSell() == 1){

                map.put("isSell", 1);

                //面积
                List<Object> area = new ArrayList<Object>();
                for(String a : push.getSellArea().split(",")){
                    if("1".equals(a)){
                        for(int i = 1; i <= 6; i ++){
                            area.add(i);
                        }
                        break;
                    }
                    else{
                        area.add(Integer.valueOf(a));
                    }
                }
                map.put("sellArea", area.toArray());

                //价格
                List<Object> price = new ArrayList<Object>();
                for(String p : push.getSellPrice().split(",")){
                    if("1".equals(p)){
                        for(int i = 1; i <= 7; i ++){
                            price.add(i);
                        }
                        break;
                    }
                    else{
                        price.add(Integer.valueOf(p));
                    }
                }
                map.put("sellPrice", price.toArray());
            }
            //求购
            if(push.getIsBuy() == 1){

                map.put("isBuy", 1);

                //面积
                List<Object> area = new ArrayList<Object>();
                for(String a : push.getBuyArea().split(",")){
                    if("1".equals(a)){
                        for(int i = 1; i <= 6; i ++){
                            area.add(i);
                        }
                        break;
                    }
                    else{
                        area.add(Integer.valueOf(a));
                    }
                }
                map.put("buyArea", area.toArray());

                //价格
                List<Object> price = new ArrayList<Object>();
                for(String p : push.getBuyPrice().split(",")){
                    if("1".equals(p)){
                        for(int i = 1; i <= 7; i ++){
                            price.add(i);
                        }
                        break;
                    }
                    else{
                        price.add(Integer.valueOf(p));
                    }
                }
                map.put("buyPrice", price.toArray());
            }
            //出租
            if(push.getIsRent() == 1){

                map.put("isRent", 1);

                //类型
                List<Object> type = new ArrayList<Object>();
                for(String t : push.getRentType().split(",")){
                    if("1".equals(t)){
                        for(int i = 2; i <= 3; i ++){
                            type.add(i);
                        }
                        break;
                    }
                    else{
                        type.add(Integer.valueOf(t));
                    }
                }
                map.put("rentType", type.toArray());

                //面积
                List<Object> area = new ArrayList<Object>();
                for(String a : push.getRentArea().split(",")){
                    if("1".equals(a)){
                        for(int i = 1; i <= 6; i ++){
                            area.add(i);
                        }
                        break;
                    }
                    else{
                        area.add(Integer.valueOf(a));
                    }
                }
                map.put("rentArea", area.toArray());

                //价格
                List<Object> price = new ArrayList<Object>();
                for(String p : push.getRentPrice().split(",")){
                    if("1".equals(p)){
                        for(int i = 1; i <= 7; i ++){
                            price.add(i);
                        }
                        break;
                    }
                    else{
                        price.add(Integer.valueOf(p));
                    }
                }
                map.put("rentPrice", price.toArray());

            }
            //求租
            if(push.getIsQz() == 1){

                map.put("isQz", 1);

                //面积
                List<Object> area = new ArrayList<Object>();
                for(String a : push.getQzArea().split(",")){
                    if("1".equals(a)){
                        for(int i = 1; i <= 6; i ++){
                            area.add(i);
                        }
                        break;
                    }
                    else{
                        area.add(Integer.valueOf(a));
                    }
                }
                map.put("qzArea", area.toArray());

                //价格
                List<Object> price = new ArrayList<Object>();
                for(String p : push.getQzPrice().split(",")){
                    if("1".equals(p)){
                        for(int i = 1; i <= 7; i ++){
                            price.add(i);
                        }
                        break;
                    }
                    else{
                        price.add(Integer.valueOf(p));
                    }
                }
                map.put("qzPrice", price.toArray());
            }
            map.put("houseType", push.getHouseType().split(","));
            map.put("pushArea", push.getPushArea().replaceAll("\"", "").split(","));

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
        int c = mfHouseClickService.queryTotal(map);

        if(c == 0){
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

        //获取房源是否已经收藏
        map.clear();
        map.put("houseId", houseId);
        map.put("openId", getOpenId());
        int count = mfHouseCollectService.queryTotal(map);

        if(count > 0){
            entity.setIscollected(1);
        }
        else{
            entity.setIscollected(0);
        }

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

        int myrank = 0;

        List<MfHouseClickEntity> list = mfHouseClickService.queryList(map);

        int i = 1;
        for(MfHouseClickEntity e : list){

            e.setRank(i);

            if(StringUtils.isNotBlank(getOpenId()) && getOpenId().equals(e.getOpenId())){
                myrank = i;
            }

            i ++;
        }

        Integer isshared = 0;

        if(StringUtils.isNotBlank(getOpenId())){
            //获取自己的排行
            MfHouseClickEntity entity = mfHouseClickService.queryObjectByOpenId(getOpenId(), houseId);

            if(entity != null){
                entity.setRank(myrank);

                list.add(0, entity);
            }
            isshared = 1;
        }
        return HouseClickUtil.getHouseClicks(list, isshared);
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
        map.put("houseId", Integer.valueOf(houseId));
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

        int count = mfHouseCollectService.queryTotal(map);

        return HouseUtils.getCollect(list, count);
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
        String type = pd.getString("type");

        if(StringUtils.isBlank(id)){
            return sendFailure("房源ID错误.");
        }

        if(StringUtils.isBlank(type)){
            return sendFailure("举报类型错误.");
        }

        String openId = getOpenId();

        if(StringUtils.isBlank(openId)){
            return sendFailure("没有获取到用户信息.");
        }

        //查询用户是否举报过
        Map<String, Object> param = new HashMap<>();
        param.put("openId", openId);
        param.put("houseId", id);

        int cc = mfComplaintsService.queryHouseTotal(param);

        if(cc >= 1){
            return sendFailure("房源信息不能重复举报.");
        }

        MfUserEntity user = mfUserService.queryObject(openId);

//        mfHouseInfoService.housereport(Long.valueOf(id));

        MfComplaintsEntity c = new MfComplaintsEntity();
        MfHouseInfoEntity h = mfHouseInfoService.queryObject(Long.valueOf(id));

        c.setOpenId(user.getOpenId());
        c.setNickname(user.getNickname());
        c.setHouseId(h.getId().intValue());
        c.setHouseTitle(h.getTitle());
        c.setHouseUrl(h.getUrl());
        c.setStatus(0);
        c.setCreateTime(DateUtils.getTodayDate());
        c.setUpdateTime(DateUtils.getTodayDate());
        c.setComType(type);

        mfComplaintsService.save(c);

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

//        map.put("offset", 0);
//        map.put("limit", 2);
        map.put("type", 1);

        List<MfRechargeEntity> list = mfRechargeService.queryList(map);
        l.addAll(list);

        //获取4个蜗牛币信息

        map.clear();
//        map.put("offset", 0);
//        map.put("limit", 4);
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
        map.put("parentCode", pd.getString("parent_code"));

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
        map.put("parentCode", pd.getString("parent_code"));

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
        map.put("parentCode", pd.getString("parent_code"));

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

        MfPushEntity entity = mfPushService.queryObject(getOpenId());//oBrPNwGoLGX6w0d2Rh0dsh3i4Uks

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

        entity.setOpenId(getOpenId());
        entity.setSystem(Long.valueOf(PropertyUtil.getProperty("system")));

        //删除之前推送设置
        mfPushService.delete(getOpenId());

        mfPushService.save(entity);

        //删除之前的推送消息
        mfPushLogService.deleteByOpenId(getOpenId());

        //删除推送推列
        MessageConstants.OUT_PUSH.put(entity.getOpenId(), DateUtils.getTodayDate());
        //勿扰消息归零
        MessageConstants.WR_HOUSE_MESSAGE.remove(entity.getOpenId());
        //夜间消息归零
        MessageConstants.NIGHT_HOUSE_MESSAGE.remove(entity.getOpenId());

        return sendSuccess();
    }

    /**
     * 获取广告
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getAd", produces = "application/json")
    public Object getAd(){

        ParamData pd = this.getParamData();

        String type = pd.getString("type");

        Map<String, Object> map = new HashMap<String, Object>();

        if(StringUtils.isNotBlank(type)){
            map.put("type", type);
        }

        List<MfAdEntity> list = mfAdService.queryList(map);

        //取一条广告
        MfAdEntity entity = new MfAdEntity();
        if(list != null && list.size() != 0){
            entity = list.get(0);
        }
        MfUserEntity user = mfUserService.queryObject(getOpenId());

        JSONObject ad = AdUtil.getAd(entity, user);

        //更新用户广告显示时间
        if(ad.getIntValue("show") == 1 && user != null){
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
