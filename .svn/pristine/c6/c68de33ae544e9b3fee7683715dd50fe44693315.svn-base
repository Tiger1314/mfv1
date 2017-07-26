package mf.service;

import mf.entity.MfHouseClickEntity;
import mf.entity.MfHouseInfoEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author dengfan
 * @email 283105211@qq.com
 * @date 2017-03-05 21:40:30
 */
public interface MfHouseClickService {
	
	MfHouseClickEntity queryObject(Integer id);
	
	List<MfHouseClickEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(MfHouseClickEntity mfHouseClick);
	
	void update(MfHouseClickEntity mfHouseClick);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

	MfHouseClickEntity queryObjectByOpenId(String id);
}
