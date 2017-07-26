package mf.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 支付订单
 * 
 * @author dengfan
 * @email 283105211@qq.com
 * @date 2017-03-12 11:36:21
 */
public class MfRechargeOrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//
	private String orderNo;
	//
	private Integer rechargeId;
	//
	private String des;
	//订单状态 0 待支付； 1支付完成； 2支付失败
	private Integer status;
	//
	private Date createTime;

	private String openId;

	private String title;

	private String operType;

	private String nickname;

	private Integer orderType;

	private Integer houseId;

	/**
	 * 设置：
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	/**
	 * 获取：
	 */
	public String getOrderNo() {
		return orderNo;
	}
	/**
	 * 设置：
	 */
	public void setRechargeId(Integer rechargeId) {
		this.rechargeId = rechargeId;
	}
	/**
	 * 获取：
	 */
	public Integer getRechargeId() {
		return rechargeId;
	}
	/**
	 * 设置：
	 */
	public void setDes(String des) {
		this.des = des;
	}
	/**
	 * 获取：
	 */
	public String getDes() {
		return des;
	}
	/**
	 * 设置：订单状态 0 待支付； 1支付完成； 2支付失败
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：订单状态 0 待支付； 1支付完成； 2支付失败
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

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOperType() {
		return operType;
	}

	public void setOperType(String operType) {
		this.operType = operType;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public Integer getHouseId() {
		return houseId;
	}

	public void setHouseId(Integer houseId) {
		this.houseId = houseId;
	}
}
