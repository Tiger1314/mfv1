package mf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import mf.dao.MfAdDao;
import mf.entity.MfAdEntity;
import mf.service.MfAdService;



@Service("mfAdService")
public class MfAdServiceImpl implements MfAdService {
	@Autowired
	private MfAdDao mfAdDao;
	
	@Override
	public MfAdEntity queryObject(Integer id){
		return mfAdDao.queryObject(id);
	}
	
	@Override
	public List<MfAdEntity> queryList(Map<String, Object> map){
		return mfAdDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return mfAdDao.queryTotal(map);
	}
	
	@Override
	public void save(MfAdEntity mfAd){
		mfAd.setStatus(1);
		mfAdDao.save(mfAd);
	}
	
	@Override
	public void update(MfAdEntity mfAd){
		mfAdDao.update(mfAd);
	}
	
	@Override
	public void delete(Integer id){
		mfAdDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		mfAdDao.deleteBatch(ids);
	}
	
}
