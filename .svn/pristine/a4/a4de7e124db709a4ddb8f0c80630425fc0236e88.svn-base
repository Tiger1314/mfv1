package mf.service;

import mf.entity.MfHouseInfoEntity;
import mf.entity.MfPushLogEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author dengfan
 * @email 283105211@qq.com
 * @date 2017-03-12 15:32:27
 */
public interface MfPushLogService {
	
	MfPushLogEntity queryObject(Integer id);
	
	List<MfPushLogEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(MfPushLogEntity mfPushLog);
	
	void update(MfPushLogEntity mfPushLog);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

	List<MfHouseInfoEntity> queryMyHouse(Map<String, Object> map);
}
