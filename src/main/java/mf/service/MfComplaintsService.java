package mf.service;

import mf.entity.MfComplaintsEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author dengfan
 * @email 283105211@qq.com
 * @date 2017-06-12 15:59:43
 */
public interface MfComplaintsService {
	
	MfComplaintsEntity queryObject(Integer id);
	
	List<MfComplaintsEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(MfComplaintsEntity mfComplaints);
	
	void update(MfComplaintsEntity mfComplaints);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

	void complaints(Integer[] ids);

	/**
	 * 根据房源ID查询
	 * @param param
	 * @return
	 */
	List<MfComplaintsEntity> queryListHouse(Map<String, Object> param);

	int queryHouseTotal(Map<String, Object> param);
}
