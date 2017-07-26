package mf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import mf.dao.MfHouseClickLikeDao;
import mf.entity.MfHouseClickLikeEntity;
import mf.service.MfHouseClickLikeService;



@Service("mfHouseClickLikeService")
public class MfHouseClickLikeServiceImpl implements MfHouseClickLikeService {
	@Autowired
	private MfHouseClickLikeDao mfHouseClickLikeDao;
	
	@Override
	public MfHouseClickLikeEntity queryObject(Integer id){
		return mfHouseClickLikeDao.queryObject(id);
	}
	
	@Override
	public List<MfHouseClickLikeEntity> queryList(Map<String, Object> map){
		return mfHouseClickLikeDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return mfHouseClickLikeDao.queryTotal(map);
	}
	
	@Override
	public void save(MfHouseClickLikeEntity mfHouseClickLike){
		mfHouseClickLikeDao.save(mfHouseClickLike);
	}
	
	@Override
	public void update(MfHouseClickLikeEntity mfHouseClickLike){
		mfHouseClickLikeDao.update(mfHouseClickLike);
	}
	
	@Override
	public void delete(Integer id){
		mfHouseClickLikeDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		mfHouseClickLikeDao.deleteBatch(ids);
	}

	@Override
	public MfHouseClickLikeEntity queryMylike(Map<String, Object> map) {
		return mfHouseClickLikeDao.queryMylike(map);
	}
}
