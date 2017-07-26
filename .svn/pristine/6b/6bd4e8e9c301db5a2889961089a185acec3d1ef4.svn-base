package mf.service;

import mf.entity.MfHouseInfoEntity;

import java.util.List;
import java.util.Map;

/**
 * 房源信息表
 * 
 * @author dengfan
 * @email 283105211@qq.com
 * @date 2017-03-05 10:40:24
 */
public interface MfHouseInfoService {
	
	MfHouseInfoEntity queryObject(Long id);
	
	List<MfHouseInfoEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(MfHouseInfoEntity mfHouseInfo);
	
	void update(MfHouseInfoEntity mfHouseInfo);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);

	/**
	 * 举报房源
	 * @param id
	 */
	void housereport(Long id);

	/**
	 * 获取最新的一条房源记录
	 * @return
	 */
	MfHouseInfoEntity getNewHouse();
}
