package mf.service.impl;

import mf.entity.MfUserEntity;
import mf.service.MfUserService;
import mf.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import mf.dao.MfRechargeOrderDao;
import mf.entity.MfRechargeOrderEntity;
import mf.service.MfRechargeOrderService;



@Service("mfRechargeOrderService")
public class MfRechargeOrderServiceImpl implements MfRechargeOrderService {
	@Autowired
	private MfRechargeOrderDao mfRechargeOrderDao;
	@Autowired
	private MfUserService mfUserService;
	
	@Override
	public MfRechargeOrderEntity queryObject(Integer id){
		return mfRechargeOrderDao.queryObject(id);
	}
	
	@Override
	public List<MfRechargeOrderEntity> queryList(Map<String, Object> map){
		return mfRechargeOrderDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return mfRechargeOrderDao.queryTotal(map);
	}
	
	@Override
	public void save(MfRechargeOrderEntity mfRechargeOrder){
		mfRechargeOrderDao.save(mfRechargeOrder);
	}
	
	@Override
	public void update(MfRechargeOrderEntity mfRechargeOrder){
		mfRechargeOrderDao.update(mfRechargeOrder);
	}
	
	@Override
	public void delete(Integer id){
		mfRechargeOrderDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		mfRechargeOrderDao.deleteBatch(ids);
	}

	@Override
	public void addOrder(String openId, String title, String oper, String dec, Integer orderType, Integer houseId) {
		MfRechargeOrderEntity entity = new MfRechargeOrderEntity();

		MfUserEntity user = mfUserService.queryObject(openId);

		entity.setOpenId(openId);
		entity.setNickname(user.getNickname());
		entity.setOperType(oper);
		entity.setDes(dec);
		entity.setTitle(title);
		entity.setCreateTime(DateUtils.getTodayDate());
		entity.setStatus(1);
		entity.setOrderType(orderType);
		entity.setHouseId(houseId);

		mfRechargeOrderDao.save(entity);
	}

	@Override
	public MfRechargeOrderEntity queryObjectByOrderNo(Object id) {
		return mfRechargeOrderDao.queryObjectByOrderNo(id);
	}
}
