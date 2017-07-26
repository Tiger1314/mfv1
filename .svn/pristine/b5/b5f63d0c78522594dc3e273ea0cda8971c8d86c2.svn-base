package mf.service;

import mf.entity.MfAreaEntity;

import java.util.List;
import java.util.Map;

/**
 * 地理信息表（以58为准）
 * 
 * @author dengfan
 * @email 283105211@qq.com
 * @date 2017-03-05 10:40:24
 */
public interface MfAreaService {
	
	MfAreaEntity queryObject(Long id);
	
	List<MfAreaEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(MfAreaEntity mfArea);
	
	void update(MfAreaEntity mfArea);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
