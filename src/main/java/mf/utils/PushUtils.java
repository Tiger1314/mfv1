package mf.utils;

/**
 * Created by dengfan on 2017/4/22.
 */
public class PushUtils {

    //获取面积 出售面积 1，不限;2，50㎡以下;3，50-100㎡;4，100-150㎡;5，150-200㎡;6，200㎡以上
    public String getArea(String areas){

        StringBuffer str = new StringBuffer("面积：");

        for(String a : areas.split(",")){

            int c = Integer.valueOf(a);

            if(c == 1){
                str.append("不限 ");
            }
            else if(c == 2){
                str.append("50㎡以下 ");
            }
            else if(c == 3){
                str.append("50-100㎡ ");
            }
            else if(c == 4){
                str.append("100-150㎡ ");
            }
            else if(c == 5){
                str.append("150-200㎡ ");
            }
            else if(c == 6){
                str.append("200㎡以上");
            }
        }

        return str.toString();
    }

    //价格万元 出售价格 1，不限;2，60W以下;3，60万-100万;4，100万-150万;5，150万-200万;6，200万-300万;7，300万-500万
    public String price1(String prices){

        StringBuffer str = new StringBuffer("价格：");

        for(String p : prices.split(",")){

            int c = Integer.valueOf(p);

            if(c == 1){
                str.append("不限 ");
            }
            else if(c == 2){
                str.append("60W以下 ");
            }
            else if(c == 3){
                str.append("60万-100万 ");
            }
            else if(c == 4){
                str.append("100万-150万 ");
            }
            else if(c == 5){
                str.append("150万-200万 ");
            }
            else if(c == 6){
                str.append("200万-300万 ");
            }
            else if(c == 7){
                str.append("300万-500万");
            }
        }
        return str.toString();
    }

    //价格 出租面积 1，不限 2，1千以内 3，1千-2千元 4，2千-3千元 5，3千-5千 6，5千-1万元 7，1万元以上
    public String price2(String prices){

        StringBuffer str = new StringBuffer("价格：");

        for(String p : prices.split(",")){

            int c = Integer.valueOf(p);

            if(c == 1){
                str.append("不限 ");
            }
            else if(c == 2){
                str.append("1千以内 ");
            }
            else if(c == 3){
                str.append("1千-2千元 ");
            }
            else if(c == 4){
                str.append("2千-3千元 ");
            }
            else if(c == 5){
                str.append("3千-5千 ");
            }
            else if(c == 6){
                str.append("5千-1万元 ");
            }
            else if(c == 7){
                str.append("1万元以上");
            }
        }
        return str.toString();
    }

}
