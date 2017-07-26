package mf.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author dengfan
 * @email 283105211@qq.com
 * @date 2017-06-12 15:59:43
 */
public class MfComplaintsEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private String openId;
	//
	private Integer houseId;
	//
	private String houseTitle;
	//
	private String houseUrl;
	//0未处理  1处理完毕
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

	private String nickname;

	private String comcount;

	private String comType;

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
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
	public void setHouseId(Integer houseId) {
		this.houseId = houseId;
	}
	/**
	 * 获取：
	 */
	public Integer getHouseId() {
		return houseId;
	}
	/**
	 * 设置：
	 */
	public void setHouseTitle(String houseTitle) {
		this.houseTitle = houseTitle;
	}
	/**
	 * 获取：
	 */
	public String getHouseTitle() {
		return houseTitle;
	}
	/**
	 * 设置：
	 */
	public void setHouseUrl(String houseUrl) {
		this.houseUrl = houseUrl;
	}
	/**
	 * 获取：
	 */
	public String getHouseUrl() {
		return houseUrl;
	}
	/**
	 * 设置：0未处理  1处理完毕
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：0未处理  1处理完毕
	 */
	public Integer getStatus() {
		return status;
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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getComcount() {
		return comcount;
	}

	public void setComcount(String comcount) {
		this.comcount = comcount;
	}

	public String getComType() {
		return comType;
	}

	public void setComType(String comType) {
		this.comType = comType;
	}
}
