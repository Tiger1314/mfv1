package mf.dao;

import mf.entity.MfUserEntity;

/**
 * 
 * 
 * @author dengfan
 * @email 283105211@qq.com
 * @date 2017-03-03 16:14:15
 */
public interface MfUserDao extends BaseDao<MfUserEntity> {


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
}
