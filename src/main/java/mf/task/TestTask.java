package mf.task;

import mf.entity.MfHouseInfoEntity;
import mf.entity.MfUserEntity;
import mf.entity.SysUserEntity;
import mf.service.MfHouseInfoService;
import mf.service.MfUserService;
import mf.service.SysUserService;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import webcat.Interaction.AccessToken;
import webcat.Interaction.User;
import webcat.utils.HttpClientUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试定时任务(演示Demo，可删除)
 * 
 * testTask为spring bean的名称
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年11月30日 下午1:34:24
 */
@Component("testTask")
public class TestTask {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private MfUserService mfUserService;
	@Autowired
	private MfHouseInfoService mfHouseInfoService;
	
	public void test(String params){
		logger.info("我是带参数的test方法，正在被执行，参数为：" + params);
		
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		SysUserEntity user = sysUserService.queryObject(1L);
		System.out.println(ToStringBuilder.reflectionToString(user));
		
	}
	
	
	public void test2(){
		logger.info("我是不带参数的test2方法，正在被执行");
	}

	/**
	 * 拉取用户信息
	 */
	public void getUserInfo(){

	    logger.info("开始拉取用户信息.");

		//获取新用户
		List<MfUserEntity> userList = mfUserService.getSubscribe();

        User user = new User();

		for(MfUserEntity entity : userList){

		    logger.info("拉取用户，openid=" + entity.getOpenId());

		    user.getUserInfo(entity);

		    mfUserService.update(entity);
		}

		logger.info("结束拉取用户信息.");

	}

	public void test3(){

//		MfHouseInfoEntity house = mfHouseInfoService.getNewHouse();
//
//		List<String> userList = new ArrayList<String>();
//		userList.add("oBrPNwA9VQyMbP_FXkoLXoVutOjQ");
//		userList.add("oBrPNwB9kBccoD7iXeACMwygU3fI");
//		userList.add("oBrPNwBn1bP4DAb6EbGMVwZFzC_M");
//		userList.add("oBrPNwBRmwXdMuGNyWPyicCvDsF8");
//		userList.add("oBrPNwC5UG7kTMPl4F-L0qxy8TbQ");
//		userList.add("oBrPNwDoPSReCKajxUcuEsaqCvks");
//		userList.add("oBrPNwDyBRkkMqb4igOYaLXd7K2k");
//		userList.add("oBrPNwGoLGX6w0d2Rh0dsh3i4Uks");
//		userList.add("oBrPNwIod45E-ENGT2JVEq8c9xsQ");
//		userList.add("oBrPNwJTUAvMgxKkirgT9xLGlo2c");
//		userList.add("oBrPNwKjXtig-ttsht5TpugLXqCQ");
//		userList.add("oBrPNwNg_0Av73hpkL7wcL8cDyJ8");
//		userList.add("oBrPNwKb9jNGNsw2uXHV7vaFLGLc");
//
//
//
//		String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=";
//
//		AccessToken token = new AccessToken();
//
//		String send_url = url + token.getAccessToken();
//
//		HttpClientUtils hc = new HttpClientUtils();
//
//		for(String openId : userList){
//
//			String mes = "{\n" +
//					"    \"touser\":\"" + openId + "\",\n" +
//					"    \"msgtype\":\"news\",\n" +
//					"    \"news\":{\n" +
//					"        \"articles\": [\n" +
//					"         {\n" +
//					"             \"title\":\"委托成功通知\",\n" +
//					"             \"description\":\"" +
//					"[出售] 三室一厅"+"\\n房源地址：" +
//					"武汉市江夏区金融港"+"\\n房源户型：" +
//					"三室一厅"+"\\n房源面积：" +
//					"100"+"平\\n房源价格：" +
//					"200"+"万元\\n房源类型：" +
//					"住宅"+"\\n\\n" +
//					"张先生"+"在58同城发布了一条售房消息\",\n" +
//					"             \"url\":\"www.baidu.com\"\n" +
//					"         }\n" +
//					"         ]\n" +
//					"    }\n" +
//					"}";
//
//
//			try {
//				Thread.sleep(10000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//
//			System.out.println("给" +openId + ":" + hc.post(send_url, mes));
//		}

//		house.setPushedStatus(1);
//		mfHouseInfoService.update(house);
	}
}
