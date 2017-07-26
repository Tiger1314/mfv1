package webcat.lottery;

/**
 * 奖品以及权重
 * Created by admin on 2017/5/25.
 */
public class Prize {

    //奖品名称
    private String name;
    //蜗牛币
    private Integer wnb;
    //权重
    private Integer weight;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWnb() {
        return wnb;
    }

    public void setWnb(Integer wnb) {
        this.wnb = wnb;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}
