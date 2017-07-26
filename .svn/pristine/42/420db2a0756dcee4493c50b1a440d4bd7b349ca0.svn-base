package mf.service.impl;

import mf.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mf.dao.MfHouseInfoDao;
import mf.entity.MfHouseInfoEntity;
import mf.service.MfHouseInfoService;



@Service("mfHouseInfoService")
public class MfHouseInfoServiceImpl implements MfHouseInfoService {
	@Autowired
	private MfHouseInfoDao mfHouseInfoDao;
	
	@Override
	public MfHouseInfoEntity queryObject(Long id){
		return mfHouseInfoDao.queryObject(id);
	}
	
	@Override
	public List<MfHouseInfoEntity> queryList(Map<String, Object> map){
		return mfHouseInfoDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return mfHouseInfoDao.queryTotal(map);
	}
	
	@Override
	public void save(MfHouseInfoEntity mfHouseInfo){
		mfHouseInfoDao.save(mfHouseInfo);
	}
	
	@Override
	public void update(MfHouseInfoEntity mfHouseInfo){
		mfHouseInfoDao.update(mfHouseInfo);
	}
	
	@Override
	public void delete(Long id){
		mfHouseInfoDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		mfHouseInfoDao.deleteBatch(ids);
	}

	/**
	 * 举报房源
	 *
	 * @param id
	 */
	@Override
	public void housereport(Long id) {
		MfHouseInfoEntity entity = new MfHouseInfoEntity();
		entity.setId(id);
		entity.setStatus(1);
		mfHouseInfoDao.update(entity);
	}

	/**
	 * 获取最新的一条房源记录
	 *
	 * @return
	 */
	@Override
	public MfHouseInfoEntity getNewHouse() {

		Map<String, Object> map = new HashMap<>();
		map.put("offset", (1 - 1) * 1);
		map.put("limit", 1);
		map.put("pushedStatus", 0);

		List<MfHouseInfoEntity> list = mfHouseInfoDao.queryList(map);
		if(list != null && list.size() > 0){
			return list.get(0);
		}

		return null;
	}
}
