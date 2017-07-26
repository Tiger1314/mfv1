package mf.service.impl;

import mf.entity.MfHouseClickLikeEntity;
import mf.entity.MfHouseInfoEntity;
import mf.service.MfHouseClickLikeService;
import mf.service.MfUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import mf.dao.MfHouseClickDao;
import mf.entity.MfHouseClickEntity;
import mf.service.MfHouseClickService;



@Service("mfHouseClickService")
public class MfHouseClickServiceImpl implements MfHouseClickService {
	@Autowired
	private MfHouseClickDao mfHouseClickDao;
	@Autowired
	private MfHouseClickLikeService mfHouseClickLikeService;
	@Autowired
	private MfUserService mfUserService;
	
	@Override
	public MfHouseClickEntity queryObject(Integer id){

		return mfHouseClickDao.queryObject(id);
	}
	
	@Override
	public List<MfHouseClickEntity> queryList(Map<String, Object> map){

		String openId = (String)map.get("openId");
		map.remove("openId");

		List<MfHouseClickEntity> list = mfHouseClickDao.queryList(map);

		List<MfHouseClickEntity> l = new LinkedList<MfHouseClickEntity>();

		for(MfHouseClickEntity entity : list){

			map.clear();
			map.put("clickId", entity.getId());

			entity.setLikes(mfHouseClickLikeService.queryTotal(map));

			entity.setUser(mfUserService.queryObject(entity.getOpenId()));

			//查询自己是否点赞过
			map.clear();
			map.put("openId", openId);
			map.put("clickId", entity.getId());
			MfHouseClickLikeEntity temp = mfHouseClickLikeService.queryMylike(map);
			if(temp == null){
				entity.setIsliked(0);
			}
			else{
				entity.setIsliked(1);
			}

			l.add(entity);

		}

		return l;
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return mfHouseClickDao.queryTotal(map);
	}
	
	@Override
	public void save(MfHouseClickEntity mfHouseClick){
		mfHouseClickDao.save(mfHouseClick);
	}
	
	@Override
	public void update(MfHouseClickEntity mfHouseClick){
		mfHouseClickDao.update(mfHouseClick);
	}
	
	@Override
	public void delete(Integer id){
		mfHouseClickDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		mfHouseClickDao.deleteBatch(ids);
	}

	@Override
	public MfHouseClickEntity queryObjectByOpenId(String id) {

		MfHouseClickEntity entity = mfHouseClickDao.queryObjectByOpenId(id);

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("clickId", entity.getId());

		entity.setLikes(mfHouseClickLikeService.queryTotal(map));

		entity.setUser(mfUserService.queryObject(entity.getOpenId()));

		//查询自己是否点赞过
		map.clear();
		map.put("openId", id);
		map.put("clickId", entity.getId());
		MfHouseClickLikeEntity temp = mfHouseClickLikeService.queryMylike(map);
		if(temp == null){
			entity.setIsliked(0);
		}
		else{
			entity.setIsliked(1);
		}

		return entity;
	}
}
