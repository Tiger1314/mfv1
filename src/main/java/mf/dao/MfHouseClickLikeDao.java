package mf.dao;

import mf.entity.MfHouseClickLikeEntity;

import java.util.Map;

/**
 * 
 * 
 * @author dengfan
 * @email 283105211@qq.com
 * @date 2017-03-05 21:40:30
 */
public interface MfHouseClickLikeDao extends BaseDao<MfHouseClickLikeEntity> {

    MfHouseClickLikeEntity queryMylike(Map<String, Object> map);
}
