package webcat;

import webcat.Interaction.Template;
import webcat.entity.MessageEntity;

/**
 * Created by dengfan on 2017/4/20.
 */
public class TemplateTest {
    public static void main(String[] args) {

        MessageEntity messageEntity = new MessageEntity();

        messageEntity.setOpenId("oBrPNwJTUAvMgxKkirgT9xLGlo2c");

        messageEntity.setTitle("【住宅-出售】光谷大道城市花园3室2厅1卫");
        messageEntity.setArea("102㎡");
        messageEntity.setUnit("3室2厅1卫");

        StringBuffer desc = new StringBuffer();
        desc.append("房源地址：光谷大道城市花园").append("\\n");
        desc.append("房源价格：160万").append("\\n");
        desc.append("房源单价：8500元/㎡").append("\\n");
        desc.append("周女士[手机号]在58同城发布了一条【住宅-出售】房源");

        messageEntity.setDesc(desc.toString());

        Template t = new Template();
        System.out.println(t.houseInfoNew(messageEntity));

    }
}
