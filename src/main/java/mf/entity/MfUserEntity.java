package mf.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author dengfan
 * @email 283105211@qq.com
 * @date 2017-03-03 16:14:15
 */
public class MfUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String openId;
	//
	private String mobile;
	//到期时间
	private Date expireDate;
	//
	private String nickname;
	//
	private String headimgurl;
	//
	private Integer sex;
	//
	private String city;
	//
	private String country;
	//
	private String province;
	//
	private String remark;

	private Integer wnb;

	private Date lastTime;

	private Date adShow;

	//个性签名
	private String signature;

	private Integer status;
	//
	private Date createTime;
	//
	private Date updateTime;
	//
	private String attr1;
	//
	private String attr2;
	//
	private String attr3;
	//
	private String attr4;

	//会员剩余天数
	private Integer laveDays;
	//我的库房总数
	private Integer collectCount;
	//是否设置了勿扰模式 0 没有 ；1 有
	private Integer wrStatus;
	//系统来源
	private Integer system;

	private Date wnbEndTime;

	//充值次数
	private Integer czCount;

	//订阅类型
	private String dyType;

	//订阅地区
	private String dyArea;

	/**
	 * 设置：
	 */
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	/**
	 * 获取：
	 */
	public String getOpenId() {
		return openId;
	}
	/**
	 * 设置：
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取：
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * 设置：到期时间
	 */
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	/**
	 * 获取：到期时间
	 */
	public Date getExpireDate() {
		return expireDate;
	}
	/**
	 * 设置：
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	/**
	 * 获取：
	 */
	public String getNickname() {
		return nickname;
	}
	/**
	 * 设置：
	 */
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	/**
	 * 获取：
	 */
	public String getHeadimgurl() {
		return headimgurl;
	}
	/**
	 * 设置：
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	/**
	 * 获取：
	 */
	public Integer getSex() {
		return sex;
	}
	/**
	 * 设置：
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * 获取：
	 */
	public String getCity() {
		return city;
	}
	/**
	 * 设置：
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * 获取：
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * 设置：
	 */
	public void setProvince(String province) {
		this.province = province;
	}
	/**
	 * 获取：
	 */
	public String getProvince() {
		return province;
	}
	/**
	 * 设置：
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 设置：
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置：
	 */
	public void setAttr1(String attr1) {
		this.attr1 = attr1;
	}
	/**
	 * 获取：
	 */
	public String getAttr1() {
		return attr1;
	}
	/**
	 * 设置：
	 */
	public void setAttr2(String attr2) {
		this.attr2 = attr2;
	}
	/**
	 * 获取：
	 */
	public String getAttr2() {
		return attr2;
	}
	/**
	 * 设置：
	 */
	public void setAttr3(String attr3) {
		this.attr3 = attr3;
	}
	/**
	 * 获取：
	 */
	public String getAttr3() {
		return attr3;
	}
	/**
	 * 设置：
	 */
	public void setAttr4(String attr4) {
		this.attr4 = attr4;
	}
	/**
	 * 获取：
	 */
	public String getAttr4() {
		return attr4;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getWnb() {
		return wnb;
	}

	public void setWnb(Integer wnb) {
		this.wnb = wnb;
	}

	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	public Date getAdShow() {
		return adShow;
	}

	public void setAdShow(Date adShow) {
		this.adShow = adShow;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public Integer getLaveDays() {
		return laveDays;
	}

	public void setLaveDays(Integer laveDays) {
		this.laveDays = laveDays;
	}

	public Integer getCollectCount() {
		return collectCount;
	}

	public void setCollectCount(Integer collectCount) {
		this.collectCount = collectCount;
	}

	public Integer getWrStatus() {
		return wrStatus;
	}

	public void setWrStatus(Integer wrStatus) {
		this.wrStatus = wrStatus;
	}

	public Integer getSystem() {
		return system;
	}

	public void setSystem(Integer system) {
		this.system = system;
	}

	public Date getWnbEndTime() {
		return wnbEndTime;
	}

	public void setWnbEndTime(Date wnbEndTime) {
		this.wnbEndTime = wnbEndTime;
	}

	public Integer getCzCount() {
		return czCount;
	}

	public void setCzCount(Integer czCount) {
		this.czCount = czCount;
	}

	public String getDyType() {
		return dyType;
	}

	public void setDyType(String dyType) {
		this.dyType = dyType;
	}

	public String getDyArea() {
		return dyArea;
	}

	public void setDyArea(String dyArea) {
		this.dyArea = dyArea;
	}
}
