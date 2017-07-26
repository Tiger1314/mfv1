package mf.dao;

import mf.entity.MfHouseInfoEntity;
import mf.entity.MfPushLogEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author dengfan
 * @email 283105211@qq.com
 * @date 2017-03-12 15:32:27
 */
public interface MfPushLogDao extends BaseDao<MfPushLogEntity> {

    List<MfHouseInfoEntity> queryMyHouse(Map<String, Object> map);

    void deleteByOpenId(String openId);
}
