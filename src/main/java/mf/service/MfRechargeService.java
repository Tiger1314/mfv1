package mf.service;

import mf.entity.MfRechargeEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author dengfan
 * @email 283105211@qq.com
 * @date 2017-03-05 21:09:29
 */
public interface MfRechargeService {
	
	MfRechargeEntity queryObject(Integer id);
	
	List<MfRechargeEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(MfRechargeEntity mfRecharge);
	
	void update(MfRechargeEntity mfRecharge);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
