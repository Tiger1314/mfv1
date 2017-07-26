package webcat;

import webcat.Interaction.Message;
import webcat.Interaction.Template;

import java.net.URLDecoder;

/**
 * Created by dengfan on 2017/4/28.
 */
public class Test2 {

    public static void main(String[] args) {
        Template t = new Template();
        Message m = new Message();

//        boolean flag = m.sendWnb50("o9F5mwR50Gu62r-7SQIMxcQG5Tps");

        String mes = t.sendYebzWnb("邓凡", "o9F5mwR50Gu62r-7SQIMxcQG5Tps");

        System.out.println(mes);
    }
}
