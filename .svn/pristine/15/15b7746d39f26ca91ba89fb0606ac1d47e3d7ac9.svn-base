package mf.service;

import mf.entity.MfSourceEntity;

import java.util.List;
import java.util.Map;

/**
 * 房源来源表
 * 
 * @author dengfan
 * @email 283105211@qq.com
 * @date 2017-03-05 10:40:24
 */
public interface MfSourceService {
	
	MfSourceEntity queryObject(Integer id);
	
	List<MfSourceEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(MfSourceEntity mfSource);
	
	void update(MfSourceEntity mfSource);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
