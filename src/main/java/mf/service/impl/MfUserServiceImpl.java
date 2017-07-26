package mf.service.impl;

import mf.entity.MfHouseInfoEntity;
import mf.entity.MfRechargeOrderEntity;
import mf.service.MfHouseCollectService;
import mf.service.MfRechargeOrderService;
import mf.utils.DateUtils;
import mf.utils.PropertyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mf.dao.MfUserDao;
import mf.entity.MfUserEntity;
import mf.service.MfUserService;
import webcat.Interaction.Message;
import webcat.Interaction.Template;


@Service("mfUserService")
public class MfUserServiceImpl implements MfUserService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private MfUserDao mfUserDao;
	@Autowired
	private MfHouseCollectService mfHouseCollectService;
	@Autowired
	private MfRechargeOrderService mfRechargeOrderService;
	
	@Override
	public MfUserEntity queryObject(String openId){

		MfUserEntity user = mfUserDao.queryObject(openId);

		if(user != null){
			if(user.getExpireDate() != null){
				user.setLaveDays(DateUtils.daysBetween(DateUtils.getTodayDate(), user.getExpireDate()));
			}
			else{
				user.setLaveDays(0);
			}

			//获取收藏数量
			Map<String, Object> map = new HashMap<>();
			map.put("openId", openId);
			List<MfHouseInfoEntity> list = mfHouseCollectService.queryMyCollect(map);
			user.setCollectCount(list.size());
		}

		return user;
	}
	
	@Override
	public List<MfUserEntity> queryList(Map<String, Object> map){

		return mfUserDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return mfUserDao.queryTotal(map);
	}
	
	@Override
	public void save(MfUserEntity mfUser){
		mfUser.setCreateTime(DateUtils.getTodayDate());
		mfUser.setUpdateTime(DateUtils.getTodayDate());
		mfUserDao.save(mfUser);
	}
	
	@Override
	public void update(MfUserEntity mfUser){
		mfUser.setUpdateTime(DateUtils.getTodayDate());
		mfUserDao.update(mfUser);
	}
	
	@Override
	public void delete(String openId){
		mfUserDao.delete(openId);
	}
	
	@Override
	public void deleteBatch(String[] openIds){
		mfUserDao.deleteBatch(openIds);
	}

	@Override
	public void subscribe(String userId) {
		mfUserDao.subscribe(userId);
	}

	@Override
	public void unsubscribe(String userId) {
		mfUserDao.unsubscribe(userId);
	}

	@Override
	public List<MfUserEntity> getSubscribe() {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("status", 2);
		map.put("system", PropertyUtil.getProperty("system"));

		return mfUserDao.queryList(map);
	}

	/**
	 * 获取会员到期3天的用户
	 *
	 * @return
	 */
	@Override
	public List<MfUserEntity> get3DayUser() {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("status", 1);
		map.put("vip", 1);

		return mfUserDao.queryList(map);
	}

	/**
	 * 扣除蜗牛币
	 *	type 1 及时消息
	 * @param openId
	 */
	@Override
	public void deductions(String openId, String title, String oper, String dec, Integer type, Integer houseId) {

		//判断是否是包月会员
		MfUserEntity user = mfUserDao.queryObject(openId);

		//是否进行了扣费
		boolean kf_flag = false;

		if(type == 1){
			user.setWnb(user.getWnb() - 1);

			kf_flag = true;
			//保存消费记录
			mfRechargeOrderService.addOrder(openId, title, oper, dec, 1, houseId);
		}
		else{
			if(user.getExpireDate() == null || DateUtils.getTodayDate().getTime() > user.getExpireDate().getTime()){
				user.setWnb(user.getWnb() - 1);

				kf_flag = true;
				//保存消费记录
				mfRechargeOrderService.addOrder(openId, title, oper, dec, 1, houseId);
			}
		}

		if(kf_flag){
			Template t = new Template();
			Message m = new Message();

			if(user.getWnb() == 50){
				boolean flag = m.sendWnb50(user.getOpenId());
				if(flag){
					logger.info("50蜗牛币发送成功");
				}
				else{
					logger.error("50蜗牛币发送失败");
				}
			}
			else if(user.getWnb() == 0){
				try {
					String str = t.sendYebzWnb(URLDecoder.decode(user.getNickname(), "UTF-8"), user.getOpenId());
					logger.info("没有蜗牛币提醒：" + str);
				} catch (UnsupportedEncodingException e) {
					logger.info("消息发送失败.");
				}
			}
			mfUserDao.update(user);
		}
	}
}
