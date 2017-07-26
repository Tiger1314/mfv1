package mf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import mf.dao.MfAreaDao;
import mf.entity.MfAreaEntity;
import mf.service.MfAreaService;



@Service("mfAreaService")
public class MfAreaServiceImpl implements MfAreaService {
	@Autowired
	private MfAreaDao mfAreaDao;
	
	@Override
	public MfAreaEntity queryObject(Long id){
		return mfAreaDao.queryObject(id);
	}
	
	@Override
	public List<MfAreaEntity> queryList(Map<String, Object> map){
		return mfAreaDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return mfAreaDao.queryTotal(map);
	}
	
	@Override
	public void save(MfAreaEntity mfArea){
		mfAreaDao.save(mfArea);
	}
	
	@Override
	public void update(MfAreaEntity mfArea){
		mfAreaDao.update(mfArea);
	}
	
	@Override
	public void delete(Long id){
		mfAreaDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		mfAreaDao.deleteBatch(ids);
	}
	
}
