package mf.service;

import mf.entity.MfPushEntity;

import java.util.List;
import java.util.Map;

/**
 * 推送设置表
 * 
 * @author dengfan
 * @email 283105211@qq.com
 * @date 2017-03-10 14:37:53
 */
public interface MfPushService {
	
	MfPushEntity queryObject(String openId);
	
	List<MfPushEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(MfPushEntity mfPush);
	
	void update(MfPushEntity mfPush);
	
	void delete(String openId);
	
	void deleteBatch(String[] openIds);
}
