package mf.service;

import mf.entity.MfUserEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author dengfan
 * @email 283105211@qq.com
 * @date 2017-03-03 16:14:15
 */
public interface MfUserService {
	
	MfUserEntity queryObject(String openId);
	
	List<MfUserEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(MfUserEntity mfUser);
	
	void update(MfUserEntity mfUser);
	
	void delete(String openId);
	
	void deleteBatch(String[] openIds);

	/**
	 * 重新关注
	 * @param userId
	 */
	void subscribe(String userId);

	/**
	 * 取消关注
	 * @param userId
	 */
	void unsubscribe(String userId);

	/**
	 * 获取刚关注用户
	 * @return
	 */
	List<MfUserEntity> getSubscribe();

	/**
	 * 获取会员到期3天的用户
	 * @return
	 */
	List<MfUserEntity> get3DayUser();

	/**
	 * 扣除蜗牛币
	 * @param openId
	 */
	void deductions(String openId, String title, String oper, String dec, Integer type, Integer houseId);
}
