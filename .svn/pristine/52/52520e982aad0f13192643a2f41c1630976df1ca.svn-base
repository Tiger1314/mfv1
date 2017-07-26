package mf.service;

import mf.entity.MfRechargeOrderEntity;

import java.util.List;
import java.util.Map;

/**
 * 支付订单
 * 
 * @author dengfan
 * @email 283105211@qq.com
 * @date 2017-03-12 11:36:21
 */
public interface MfRechargeOrderService {
	
	MfRechargeOrderEntity queryObject(Integer id);
	
	List<MfRechargeOrderEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(MfRechargeOrderEntity mfRechargeOrder);
	
	void update(MfRechargeOrderEntity mfRechargeOrder);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
