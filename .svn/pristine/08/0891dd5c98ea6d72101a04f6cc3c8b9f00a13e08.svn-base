package mf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import mf.dao.MfSourceDao;
import mf.entity.MfSourceEntity;
import mf.service.MfSourceService;



@Service("mfSourceService")
public class MfSourceServiceImpl implements MfSourceService {
	@Autowired
	private MfSourceDao mfSourceDao;
	
	@Override
	public MfSourceEntity queryObject(Integer id){
		return mfSourceDao.queryObject(id);
	}
	
	@Override
	public List<MfSourceEntity> queryList(Map<String, Object> map){
		return mfSourceDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return mfSourceDao.queryTotal(map);
	}
	
	@Override
	public void save(MfSourceEntity mfSource){
		mfSourceDao.save(mfSource);
	}
	
	@Override
	public void update(MfSourceEntity mfSource){
		mfSourceDao.update(mfSource);
	}
	
	@Override
	public void delete(Integer id){
		mfSourceDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		mfSourceDao.deleteBatch(ids);
	}
	
}
