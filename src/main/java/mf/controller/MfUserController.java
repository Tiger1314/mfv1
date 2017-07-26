package mf.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mf.entity.*;
import mf.service.MfAreaService;
import mf.service.MfPushService;
import mf.service.MfRechargeOrderService;
import mf.utils.DateUtils;
import mf.utils.PageUtils;
import mf.utils.R;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

import mf.service.MfUserService;
import webcat.Interaction.Message;


/**
 * 
 * 
 * @author dengfan
 * @email 283105211@qq.com
 * @date 2017-03-03 16:14:15
 */
@Controller
@RequestMapping("mfuser")
public class MfUserController {
	@Autowired
	private MfUserService mfUserService;
	@Autowired
	private MfRechargeOrderService mfRechargeOrderService;
	@Autowired
	private MfAreaService mfAreaService;
	@Autowired
	private MfPushService mfPushService;
	
	@RequestMapping("/mfuser.html")
	public String list(){
		return "mfuser/mfuser.html";
	}
	
	@RequestMapping("/mfuser_add.html")
	public String add(){
		return "mfuser/mfuser_add.html";
	}

	@RequestMapping("/mfuser_view.html")
	public String view(){
		return "mfuser/mfuser_view.html";
	}

	@RequestMapping("/send_wnb.html")
	public String sendwnb(){

		return "mfuser/send_wnb.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("mfuser:list")
	public R list(Integer page, Integer limit, String nickname, String order, String sidx){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		map.put("order", order);
		map.put("sidx", sidxConversion(sidx));

		if(StringUtils.isNotBlank(nickname)){
			map.put("nickname", nickname);
		}

		//查询列表数据
		List<MfUserEntity> mfUserList = mfUserService.queryList(map);
		int total = mfUserService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(mfUserList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{openId}")
	@RequiresPermissions("mfuser:info")
	public R info(@PathVariable("openId") String openId){
		MfUserEntity mfUser = mfUserService.queryObject(openId);

		//充值次数
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cz", 1);

		int count = mfRechargeOrderService.queryTotal(map);

		mfUser.setCzCount(count);
		//订阅设置
		MfPushEntity push = mfPushService.queryObject(mfUser.getOpenId());

		//订阅地址
		String areas = "";

		MfAreaEntity area = null;

		for(String c : push.getPushArea().split(",")){

			area = mfAreaService.queryObject(Long.valueOf(c));

			areas += area.getName() + " ";

		}
		mfUser.setDyArea(areas);

		//订阅设置

		return R.ok().put("mfUser", mfUser);
	}



	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("mfuser:save")
	public R save(@RequestBody MfUserEntity mfUser){
		mfUserService.save(mfUser);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("mfuser:update")
	public R update(@RequestBody MfUserEntity mfUser){
		mfUserService.update(mfUser);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("mfuser:delete")
	public R delete(@RequestBody String[] openIds){
		mfUserService.deleteBatch(openIds);
		
		return R.ok();
	}

	/**
	 * 赠送蜗牛币
	 * @param sendwn
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/send")
	public R send(@RequestBody SendWnbEntity sendwn){

		if(StringUtils.isNotBlank(sendwn.getIds())){

			MfUserEntity user;

			for(String id : sendwn.getIds().split(",")){

				user = mfUserService.queryObject(id);
				user.setWnb(user.getWnb() + sendwn.getWnb());
				mfUserService.update(user);

				//插入订单记录
				MfRechargeOrderEntity order = new MfRechargeOrderEntity();
				order.setOpenId(id);
				order.setOperType("蜗牛币");
				order.setStatus(1);
				order.setDes(sendwn.getWnb() + "");
				order.setTitle(sendwn.getDesc());
				order.setCreateTime(DateUtils.getTodayDate());
				order.setOrderType(3);
				order.setNickname(user.getNickname());

				mfRechargeOrderService.save(order);

				//是否发送消息
				if(sendwn.getTzFlag() == 1 && StringUtils.isNotBlank(sendwn.getMessage())){
					Message m = new Message();

					m.sendTextMessage(sendwn.getMessage(), id);
				}

				//为用户开启推送配置
				MfPushEntity pushEntity = mfPushService.queryObject(user.getOpenId());
				if(pushEntity != null){
					pushEntity.setStatus(1);
					mfPushService.update(pushEntity);
				}
			}
		}

		return R.ok();
	}


	private String sidxConversion(String sidx){

		String str = "";

		if("openId".equals(sidx)){
			str = "open_id";
		}
		else if("expireDate".equals(sidx)){
			str = "expire_date";
		}
		else if("adShow".equals(sidx)){
			str = "ad_show";
		}
		else if("lastTime".equals(sidx)){
			str = "last_time";
		}
		else if("createTime".equals(sidx)){
			str = "create_time";
		}
		else if("wrStatus".equals(sidx)){
			str = "wr_status";
		}
		else {
			str = sidx;
		}

		return str;
	}
}
