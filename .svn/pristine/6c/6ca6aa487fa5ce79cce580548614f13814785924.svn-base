package mf.service.impl;

import mf.entity.MfHouseInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import mf.dao.MfPushLogDao;
import mf.entity.MfPushLogEntity;
import mf.service.MfPushLogService;



@Service("mfPushLogService")
public class MfPushLogServiceImpl implements MfPushLogService {
	@Autowired
	private MfPushLogDao mfPushLogDao;
	
	@Override
	public MfPushLogEntity queryObject(Integer id){
		return mfPushLogDao.queryObject(id);
	}
	
	@Override
	public List<MfPushLogEntity> queryList(Map<String, Object> map){
		return mfPushLogDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return mfPushLogDao.queryTotal(map);
	}
	
	@Override
	public void save(MfPushLogEntity mfPushLog){
		mfPushLogDao.save(mfPushLog);
	}
	
	@Override
	public void update(MfPushLogEntity mfPushLog){
		mfPushLogDao.update(mfPushLog);
	}
	
	@Override
	public void delete(Integer id){
		mfPushLogDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		mfPushLogDao.deleteBatch(ids);
	}

	@Override
	public List<MfHouseInfoEntity> queryMyHouse(Map<String, Object> map) {
		return mfPushLogDao.queryMyHouse(map);
	}
}
