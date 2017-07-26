package mf.service.impl;

import mf.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import mf.dao.MfPushDao;
import mf.entity.MfPushEntity;
import mf.service.MfPushService;



@Service("mfPushService")
public class MfPushServiceImpl implements MfPushService {
	@Autowired
	private MfPushDao mfPushDao;
	
	@Override
	public MfPushEntity queryObject(String openId){
		return mfPushDao.queryObject(openId);
	}
	
	@Override
	public List<MfPushEntity> queryList(Map<String, Object> map){
		return mfPushDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return mfPushDao.queryTotal(map);
	}
	
	@Override
	public void save(MfPushEntity mfPush){

		//判断用户是否已经设置过

		MfPushEntity entity = mfPushDao.queryObject(mfPush.getOpenId());

		//如果存在，则先删除掉
		if(entity != null){
			mfPushDao.delete(mfPush.getOpenId());
		}

		//格式化推送区域 全部-转换成每个具体地区

		mfPush.setStatus(1);

		mfPushDao.save(mfPush);
	}
	
	@Override
	public void update(MfPushEntity mfPush){
		mfPushDao.update(mfPush);
	}
	
	@Override
	public void delete(String openId){
		mfPushDao.delete(openId);
	}
	
	@Override
	public void deleteBatch(String[] openIds){
		mfPushDao.deleteBatch(openIds);
	}
	
}
