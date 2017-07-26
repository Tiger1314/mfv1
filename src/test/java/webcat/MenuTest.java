package webcat;

import webcat.Interaction.Menu;

import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by dengfan on 2017/3/12.
 */
public class MenuTest {

    public static void main(String[] args) {
//        Menu m = new Menu();
//        m.setMunu();
        try{
            System.out.print(URLEncoder.encode("http://www.bmbang.cc/author/author?redirect_url=pay", "UTF-8"));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
