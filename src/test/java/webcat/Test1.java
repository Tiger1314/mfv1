package webcat;

import mf.utils.Base64Utils;
import webcat.utils.ConEnum;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by dengfan on 2017/3/14.
 */
public class Test1 {
    public static void main(String[] args) {
       String url = "http://www.bmbang.cc/author/author?redirect_url=fyxq&id=";

        try {
//            System.out.println(URLEncoder.encode("蜗牛MM", "UTF-8"));

//            String eds = URLEncoder.encode(url, "UTF-8");
//            System.out.println(eds);

            System.out.println(URLDecoder.decode("A+%E5%88%9A%E5%AD%90%C2%B2%E2%81%B0%C2%B97", "UTF-8"));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
