package webcat.utils;

import webcat.entity.MessageEntity;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 * Created by dengfan on 2017/3/7.
 */
public class MessageConstants {

    /**
     * 普通的房源推送消息
     */
    private static Queue<MessageEntity> NORMAL_HOUSE_MESSAGE = new ArrayDeque<MessageEntity>();


    /**
     * 夜间模式需要推送的消息
     */
    public static Map<String, Integer> NIGHT_HOUSE_MESSAGE = new HashMap<String, Integer>();


    /**
     * 勿扰模式推送消息
     */
    public static Map<String, Integer> WR_HOUSE_MESSAGE = new HashMap<String, Integer>();

    /**
     * 48小时未互动
     */
    private static Queue<String> HOUS48 = new ArrayDeque<String>();


    /**
     * 夜间模式+1
     * @param openId
     */
    public static void addNigth(String openId){

        Integer count = NIGHT_HOUSE_MESSAGE.get(openId);

        if(count == null){
            count = 1;
        }
        else{
            count ++;
        }

        NIGHT_HOUSE_MESSAGE.put(openId, count);
    }


    /**
     * 勿扰模式+1
     * @param openId
     */
    public static void addWr(String openId){

        Integer count = WR_HOUSE_MESSAGE.get(openId);

        if(count == null){
            count = 1;
        }
        else{
            count ++;
        }
        WR_HOUSE_MESSAGE.put(openId, count);
    }

    /**
     * 添加普通推送消息
     * @param entity
     */
    public static void addMessage(MessageEntity entity){
        NORMAL_HOUSE_MESSAGE.add(entity);
    }

    /**
     * 获取推送消息
     * @return
     */
    public static MessageEntity getMessage(){
        return NORMAL_HOUSE_MESSAGE.poll();
    }

    /**
     * 剩余多少条没有推送
     * @return
     */
    public static Integer getMessageCount(){
        return NORMAL_HOUSE_MESSAGE.size();
    }

    public static void addHous48(String openId){
        HOUS48.add(openId);
    }

    public static String getHous48(){
        return HOUS48.poll();
    }
}
