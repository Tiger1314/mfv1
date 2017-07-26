package mf.service.impl;

import mf.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import mf.dao.MfRechargeDao;
import mf.entity.MfRechargeEntity;
import mf.service.MfRechargeService;



@Service("mfRechargeService")
public class MfRechargeServiceImpl implements MfRechargeService {
	@Autowired
	private MfRechargeDao mfRechargeDao;
	
	@Override
	public MfRechargeEntity queryObject(Integer id){
		return mfRechargeDao.queryObject(id);
	}
	
	@Override
	public List<MfRechargeEntity> queryList(Map<String, Object> map){
		return mfRechargeDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return mfRechargeDao.queryTotal(map);
	}
	
	@Override
	public void save(MfRechargeEntity mfRecharge){

		mfRecharge.setCreateTime(DateUtils.getTodayDate());
		mfRecharge.setUpdateTime(DateUtils.getTodayDate());

		mfRechargeDao.save(mfRecharge);
	}
	
	@Override
	public void update(MfRechargeEntity mfRecharge){

		mfRecharge.setUpdateTime(DateUtils.getTodayDate());

		mfRechargeDao.update(mfRecharge);
	}
	
	@Override
	public void delete(Integer id){
		mfRechargeDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		mfRechargeDao.deleteBatch(ids);
	}
	
}
