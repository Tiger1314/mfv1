package webcat.utils;

/**
 * Created by dengfan on 2017/3/3.
 */
public class ConEnum {


    /**
     * 模板消息
     */
    public enum Model{

        CZTX_BY("CHIhRkfxvu-CUVrTE_vaHJIWDpnyIBJBM77T0n6X-OU"),

        CZTX_WNB("s8HVfRHlt-dM-XYJmDJDLFnj44jy6QmOA9oa7jVDEqU"),

        YEBZ_WNB("QtkS-I-hSNmEROZ5C_MAIYSy0I8Jn7sSXR1kpCtWvG8");

        private String value;

        private Model(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

    }

    /**
     * 菜单
     */
    public enum Menu{

        //支付页面
        PAY("http%3A%2F%2Fwww.bmbang.cc%2Fauthor%2Fauthor%3Fredirect_url%3Dpay"),

        //房源广场
        FYGC("http%3A%2F%2Fwww.bmbang.cc%2Fauthor%2Fauthor%3Fredirect_url%3Dfygc"),

        //订阅设置
        DYSZ("http%3A%2F%2Fwww.bmbang.cc%2Fauthor%2Fauthor%3Fredirect_url%3Ddysz"),

        //加入会员
        JRHY("http%3A%2F%2Fwww.bmbang.cc%2Fauthor%2Fauthor%3Fredirect_url%3Dpay"),

        //房源详情
        FYXQ("http%3A%2F%2Fwww.bmbang.cc%2Fauthor%2Fauthor%3Fredirect_url%3Dfyxq");

        private String value;

        private Menu(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

    }

    public enum MenuUrl{
        FYGC("fygc", "/dist/index.html"),

        PAY("pay", "/dist/index.html#user"),

        DYSZ("dysz", "/dist/index.html#filter"),

        JRHY("jrhy", "/dist/index.html#user"),

        FYXQ("fyxq", "/dist/details.html#");


        private String key;

        private String value;

        /**
         * 通过key 获取value值
         *
         * @param key
         * @return
         */
        public static String getValue(String key) {
            for (MenuUrl p : MenuUrl.values()) {
                if (p.getKey().equals(key)) {
                    return p.getValue();
                }
            }
            return null;
        }


        private MenuUrl(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }


    /**
     * 房源来源
     */
    public enum HouseSource {
        S_58(1, "58同城", "images/58.png"),
        S_GJ(2, "赶集", "images/gj.png"),
        S_FTX(3, "房天下", "images/ftx.png"),
        S_YF(4, "亿房", "images/yf.png"),
        S_AJK(5, "安居客", "images/ajk.png"),
        S_BXW(6, "百姓网", "images/bxw.png");

        private int key;
        private String value;
        private String url;

        private HouseSource(int key, String value, String url) {
            this.key = key;
            this.value = value;
            this.url = url;
        }

        /**
         * 通过key 获取value值
         *
         * @param key
         * @return
         */
        public static String getValue(int key) {
            for (HouseSource p : HouseSource.values()) {
                if (p.getKey() == key) {
                    return p.getValue();
                }
            }
            return null;
        }

        public static String getUrl(int key){
            for (HouseSource p : HouseSource.values()) {
                if (p.getKey() == key) {
                    return p.getUrl();
                }
            }
            return null;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }




}
