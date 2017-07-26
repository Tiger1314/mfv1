package mf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import mf.dao.MfSourceProfileDao;
import mf.entity.MfSourceProfileEntity;
import mf.service.MfSourceProfileService;



@Service("mfSourceProfileService")
public class MfSourceProfileServiceImpl implements MfSourceProfileService {
	@Autowired
	private MfSourceProfileDao mfSourceProfileDao;
	
	@Override
	public MfSourceProfileEntity queryObject(Long id){
		return mfSourceProfileDao.queryObject(id);
	}
	
	@Override
	public List<MfSourceProfileEntity> queryList(Map<String, Object> map){
		return mfSourceProfileDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return mfSourceProfileDao.queryTotal(map);
	}
	
	@Override
	public void save(MfSourceProfileEntity mfSourceProfile){
		mfSourceProfileDao.save(mfSourceProfile);
	}
	
	@Override
	public void update(MfSourceProfileEntity mfSourceProfile){
		mfSourceProfileDao.update(mfSourceProfile);
	}
	
	@Override
	public void delete(Long id){
		mfSourceProfileDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		mfSourceProfileDao.deleteBatch(ids);
	}
	
}
