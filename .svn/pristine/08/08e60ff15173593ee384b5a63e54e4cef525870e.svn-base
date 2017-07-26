package mf.service;

import mf.entity.MfHouseClickLikeEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author dengfan
 * @email 283105211@qq.com
 * @date 2017-03-05 21:40:30
 */
public interface MfHouseClickLikeService {
	
	MfHouseClickLikeEntity queryObject(Integer id);
	
	List<MfHouseClickLikeEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(MfHouseClickLikeEntity mfHouseClickLike);
	
	void update(MfHouseClickLikeEntity mfHouseClickLike);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

	MfHouseClickLikeEntity queryMylike(Map<String, Object> map);
}
