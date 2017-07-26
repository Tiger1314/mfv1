package mf.service;

import mf.entity.MfAdEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author dengfan
 * @email 283105211@qq.com
 * @date 2017-03-06 22:25:21
 */
public interface MfAdService {
	
	MfAdEntity queryObject(Integer id);
	
	List<MfAdEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(MfAdEntity mfAd);
	
	void update(MfAdEntity mfAd);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
