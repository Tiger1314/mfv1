package mf.service.impl;

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
	
}
