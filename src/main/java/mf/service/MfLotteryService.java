package mf.service;

import mf.entity.MfLotteryEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author dengfan
 * @email 283105211@qq.com
 * @date 2017-06-12 15:03:43
 */
public interface MfLotteryService {
	
	MfLotteryEntity queryObject(Integer id);
	
	List<MfLotteryEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(MfLotteryEntity mfLottery);
	
	void update(MfLotteryEntity mfLottery);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

	boolean checkCanLottery(String openId);

	int canlotteryCount(String openId);
}
