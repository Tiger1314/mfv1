package webcat.service;

/**
 * 消息推送服务类
 * Created by dengfan on 2017/3/7.
 */
public interface PushService {

    /**
     * 检查是否为新会员
     * @param openId
     * @return 返回过期时间
     */
    public int checkNewUser(String openId);

    /**
     * 检查用户是否可以推送
     * @param openId
     * @return
     */
    public Boolean checkUserPush(String openId);

    /**
     * 推送成功，回调消息
     * @param openId
     */
    public void pushCallBack(String openId);

}
