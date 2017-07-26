package mf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import mf.dao.MfComplaintsDao;
import mf.entity.MfComplaintsEntity;
import mf.service.MfComplaintsService;



@Service("mfComplaintsService")
public class MfComplaintsServiceImpl implements MfComplaintsService {
	@Autowired
	private MfComplaintsDao mfComplaintsDao;
	
	@Override
	public MfComplaintsEntity queryObject(Integer id){
		return mfComplaintsDao.queryObject(id);
	}
	
	@Override
	public List<MfComplaintsEntity> queryList(Map<String, Object> map){
		return mfComplaintsDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return mfComplaintsDao.queryTotal(map);
	}
	
	@Override
	public void save(MfComplaintsEntity mfComplaints){
		mfComplaintsDao.save(mfComplaints);
	}
	
	@Override
	public void update(MfComplaintsEntity mfComplaints){
		mfComplaintsDao.update(mfComplaints);
	}
	
	@Override
	public void delete(Integer id){
		mfComplaintsDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		mfComplaintsDao.deleteBatch(ids);
	}

	@Override
	public void complaints(Integer[] ids) {
		mfComplaintsDao.complaints(ids);
	}

	@Override
	public int queryHouseTotal(Map<String, Object> param) {
		return mfComplaintsDao.queryHouseTotal(param);
	}

	/**
	 * 根据房源ID查询
	 *
	 * @param param
	 * @return
	 */
	@Override
	public List<MfComplaintsEntity> queryListHouse(Map<String, Object> param) {
		return mfComplaintsDao.queryListHouse(param);
	}
}
