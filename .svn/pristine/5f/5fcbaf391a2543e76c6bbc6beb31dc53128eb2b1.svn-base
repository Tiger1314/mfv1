package mf.service;

import mf.entity.MfSourceProfileEntity;

import java.util.List;
import java.util.Map;

/**
 * 来源的地区主页
 * 
 * @author dengfan
 * @email 283105211@qq.com
 * @date 2017-03-05 10:40:24
 */
public interface MfSourceProfileService {
	
	MfSourceProfileEntity queryObject(Long id);
	
	List<MfSourceProfileEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(MfSourceProfileEntity mfSourceProfile);
	
	void update(MfSourceProfileEntity mfSourceProfile);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
