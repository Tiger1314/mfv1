package mf.dao;

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
public interface MfComplaintsDao extends BaseDao<MfComplaintsEntity> {

    void complaints(Object[] ids);

    /**
     * 根据房源ID查询
     * @param param
     * @return
     */
    List<MfComplaintsEntity> queryListHouse(Map<String, Object> param);

    int queryHouseTotal(Map<String, Object> param);
}
