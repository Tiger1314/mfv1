package webcat;

import webcat.Interaction.Message;

/**
 * Created by dengfan on 2017/3/12.
 */
public class MessageTest {

    public static void main(String[] args) {
        Message m = new Message();
        boolean flag = m.sendTextMessage("测试","oBrPNwLr_YsbyM1GkAtJBIffiXz8");

        System.out.println(flag);
    }

}
