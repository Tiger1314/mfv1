package webcat.lottery;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/5/25.
 */
public class PrizeUtil {

    public List<Prize> init(){

        List<Prize> list = new ArrayList<Prize>();

        //谢谢参与
        Prize p = new Prize();
        p.setName("谢谢参与");
        p.setWnb(0);
        p.setWeight(4);
        list.add(p);

        //五等奖
        p = new Prize();
        p.setName("五等奖");
        p.setWnb(2);
        p.setWeight(9000);
        list.add(p);

        //四等奖
        p = new Prize();
        p.setName("四等奖");
        p.setWnb(10);
        p.setWeight(489);
        list.add(p);

        //三等奖
        p = new Prize();
        p.setName("三等奖");
        p.setWnb(20);
        p.setWeight(100);
        list.add(p);

        //二等奖
        p = new Prize();
        p.setName("二等奖");
        p.setWnb(50);
        p.setWeight(10);
        list.add(p);

        //一等奖
        p = new Prize();
        p.setName("一等奖");
        p.setWnb(200);
        p.setWeight(1);
        list.add(p);

        return list;
    }


    /**
     * 抽奖操作类
     * @return
     */
    public Prize getPrize(){
        DecimalFormat df = new DecimalFormat("######0.00");
        int random = -1;

        List<Prize> list = init();

        try{

            //计算总权重
            double sumWeight = 0;

            for(Prize p : list){
                sumWeight += p.getWeight();
            }

            //产生随机数
            double randomNumber;
            randomNumber = Math.random();

            //根据随机数在所有奖品分布的区域并确定所抽奖品
            double d1 = 0;
            double d2 = 0;
            for(int i = 0;i < list.size(); i++){
                d2 += Double.parseDouble(String.valueOf(list.get(i).getWeight())) / sumWeight;
                if(i==0){
                    d1 = 0;
                }else{
                    d1 +=Double.parseDouble(String.valueOf(list.get(i-1).getWeight()))/sumWeight;
                }
                if(randomNumber >= d1 && randomNumber <= d2){
                    random = i;
                    break;
                }
            }

        }
        catch (Exception e){

        }
        return list.get(random);
    }


    public static void main(String[] args) {
        PrizeUtil p = new PrizeUtil();
        for(int i = 1; i <= 10000; i ++){
            Prize prize = p.getPrize();
            System.out.println("第" + i + "次：" + prize.getName());
            if(prize.getWnb() == 200){
                break;
            }
        }

    }


}
