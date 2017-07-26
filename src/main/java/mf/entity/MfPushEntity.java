package mf.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 推送设置表
 * 
 * @author dengfan
 * @email 283105211@qq.com
 * @date 2017-03-10 14:46:52
 */
public class MfPushEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String openId;
	//房源类型 1 住宅；2 写字楼
	private String houseType;
	//是否出售 1 是； 0否
	private Integer isSell;
	//出售类型1 住宅；2 写字楼
	private String sellType;
	//出售面积 1，不限;2，50㎡以下;3，50-100㎡;4，100-150㎡;5，150-200㎡;6，200㎡以上
	private String sellArea;
	//出售价格 1，不限;2，60W以下;3，60万-100万;4，100万-150万;5，150万-200万;6，200万-300万;7，300万-500万
	private String sellPrice;
	//是否求购 1 是； 0否
	private Integer isBuy;
	//求购类型1 住宅；2 写字楼
	private String buyType;
	//出售面积 1，不限;2，50㎡以下;3，50-100㎡;4，100-150㎡;5，150-200㎡;6，200㎡以上
	private String buyArea;
	//出售价格 1，不限;2，60W以下;3，60万-100万;4，100万-150万;5，150万-200万;6，200万-300万;7，300万-500万
	private String buyPrice;
	//是否出租 1 是； 0否
	private Integer isRent;
	//出租类型 1，不限 2，分租 3，整租

	private String rentType;
	//出租面积 1，不限
//         2，50㎡以下
//         3，50-100㎡
//         4，100-150㎡
//         5，150-200㎡
//         6，200㎡以上

	private String rentArea;
	//出租面积 1，不限
//         2，1千以内
//         3，1千-2千元
//         4，2千-3千元
//         5，3千-5千
//         6，5千-1万元
//         7，1万元以上

	private String rentPrice;
	//是否求租 1 是； 0否
	private Integer isQz;
	//出租类型 1，不限
//         2，分租
//         3，整租

	private String qzType;
	//出租面积 1，不限
//         2，50㎡以下
//         3，50-100㎡
//         4，100-150㎡
//         5，150-200㎡
//         6，200㎡以上

	private String qzArea;
	//出租面积 1，不限
//         2，1千以内
//         3，1千-2千元
//         4，2千-3千元
//         5，3千-5千
//         6，5千-1万元
//         7，1万元以上

	private String qzPrice;
	//推送地区，以“,”分隔，保存前台传入值
	private String pushArea;
	//真实推送地区，把“全部”转换成真实地区
	private String realPushArea;
	//状态 1 正常推送 2 不推送
	private Integer status;
	//系统
	private Long system;

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
	 * 设置：房源类型 1 住宅；2 写字楼
	 */
	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}
	/**
	 * 获取：房源类型 1 住宅；2 写字楼
	 */
	public String getHouseType() {
		return houseType;
	}
	/**
	 * 设置：是否出售 1 是； 0否
	 */
	public void setIsSell(Integer isSell) {
		this.isSell = isSell;
	}
	/**
	 * 获取：是否出售 1 是； 0否
	 */
	public Integer getIsSell() {
		return isSell;
	}
	/**
	 * 设置：出售类型1 住宅；2 写字楼
	 */
	public void setSellType(String sellType) {
		this.sellType = sellType;
	}
	/**
	 * 获取：出售类型1 住宅；2 写字楼
	 */
	public String getSellType() {
		return sellType;
	}
	/**
	 * 设置：出售面积 1，不限;2，50㎡以下;3，50-100㎡;4，100-150㎡;5，150-200㎡;6，200㎡以上
	 */
	public void setSellArea(String sellArea) {
		this.sellArea = sellArea;
	}
	/**
	 * 获取：出售面积 1，不限;2，50㎡以下;3，50-100㎡;4，100-150㎡;5，150-200㎡;6，200㎡以上
	 */
	public String getSellArea() {
		return sellArea;
	}
	/**
	 * 设置：出售价格 1，不限;2，60W以下;3，60万-100万;4，100万-150万;5，150万-200万;6，200万-300万;7，300万-500万
	 */
	public void setSellPrice(String sellPrice) {
		this.sellPrice = sellPrice;
	}
	/**
	 * 获取：出售价格 1，不限;2，60W以下;3，60万-100万;4，100万-150万;5，150万-200万;6，200万-300万;7，300万-500万
	 */
	public String getSellPrice() {
		return sellPrice;
	}
	/**
	 * 设置：是否求购 1 是； 0否
	 */
	public void setIsBuy(Integer isBuy) {
		this.isBuy = isBuy;
	}
	/**
	 * 获取：是否求购 1 是； 0否
	 */
	public Integer getIsBuy() {
		return isBuy;
	}
	/**
	 * 设置：求购类型1 住宅；2 写字楼
	 */
	public void setBuyType(String buyType) {
		this.buyType = buyType;
	}
	/**
	 * 获取：求购类型1 住宅；2 写字楼
	 */
	public String getBuyType() {
		return buyType;
	}
	/**
	 * 设置：出售面积 1，不限;2，50㎡以下;3，50-100㎡;4，100-150㎡;5，150-200㎡;6，200㎡以上
	 */
	public void setBuyArea(String buyArea) {
		this.buyArea = buyArea;
	}
	/**
	 * 获取：出售面积 1，不限;2，50㎡以下;3，50-100㎡;4，100-150㎡;5，150-200㎡;6，200㎡以上
	 */
	public String getBuyArea() {
		return buyArea;
	}
	/**
	 * 设置：出售价格 1，不限;2，60W以下;3，60万-100万;4，100万-150万;5，150万-200万;6，200万-300万;7，300万-500万
	 */
	public void setBuyPrice(String buyPrice) {
		this.buyPrice = buyPrice;
	}
	/**
	 * 获取：出售价格 1，不限;2，60W以下;3，60万-100万;4，100万-150万;5，150万-200万;6，200万-300万;7，300万-500万
	 */
	public String getBuyPrice() {
		return buyPrice;
	}
	/**
	 * 设置：是否出租 1 是； 0否
	 */
	public void setIsRent(Integer isRent) {
		this.isRent = isRent;
	}
	/**
	 * 获取：是否出租 1 是； 0否
	 */
	public Integer getIsRent() {
		return isRent;
	}
	/**
	 * 设置：出租类型 1，不限 2，分租 3，整租

	 */
	public void setRentType(String rentType) {
		this.rentType = rentType;
	}
	/**
	 * 获取：出租类型 1，不限 2，分租 3，整租

	 */
	public String getRentType() {
		return rentType;
	}
	/**
	 * 设置：出租面积 1，不限
         2，50㎡以下
         3，50-100㎡
         4，100-150㎡
         5，150-200㎡
         6，200㎡以上

	 */
	public void setRentArea(String rentArea) {
		this.rentArea = rentArea;
	}
	/**
	 * 获取：出租面积 1，不限
         2，50㎡以下
         3，50-100㎡
         4，100-150㎡
         5，150-200㎡
         6，200㎡以上

	 */
	public String getRentArea() {
		return rentArea;
	}
	/**
	 * 设置：出租面积 1，不限
         2，1千以内
         3，1千-2千元
         4，2千-3千元
         5，3千-5千
         6，5千-1万元
         7，1万元以上

	 */
	public void setRentPrice(String rentPrice) {
		this.rentPrice = rentPrice;
	}
	/**
	 * 获取：出租面积 1，不限
         2，1千以内
         3，1千-2千元
         4，2千-3千元
         5，3千-5千
         6，5千-1万元
         7，1万元以上

	 */
	public String getRentPrice() {
		return rentPrice;
	}
	/**
	 * 设置：是否求租 1 是； 0否
	 */
	public void setIsQz(Integer isQz) {
		this.isQz = isQz;
	}
	/**
	 * 获取：是否求租 1 是； 0否
	 */
	public Integer getIsQz() {
		return isQz;
	}
	/**
	 * 设置：出租类型 1，不限
         2，分租
         3，整租

	 */
	public void setQzType(String qzType) {
		this.qzType = qzType;
	}
	/**
	 * 获取：出租类型 1，不限
         2，分租
         3，整租

	 */
	public String getQzType() {
		return qzType;
	}
	/**
	 * 设置：出租面积 1，不限
         2，50㎡以下
         3，50-100㎡
         4，100-150㎡
         5，150-200㎡
         6，200㎡以上

	 */
	public void setQzArea(String qzArea) {
		this.qzArea = qzArea;
	}
	/**
	 * 获取：出租面积 1，不限
         2，50㎡以下
         3，50-100㎡
         4，100-150㎡
         5，150-200㎡
         6，200㎡以上

	 */
	public String getQzArea() {
		return qzArea;
	}
	/**
	 * 设置：出租面积 1，不限
         2，1千以内
         3，1千-2千元
         4，2千-3千元
         5，3千-5千
         6，5千-1万元
         7，1万元以上

	 */
	public void setQzPrice(String qzPrice) {
		this.qzPrice = qzPrice;
	}
	/**
	 * 获取：出租面积 1，不限
         2，1千以内
         3，1千-2千元
         4，2千-3千元
         5，3千-5千
         6，5千-1万元
         7，1万元以上

	 */
	public String getQzPrice() {
		return qzPrice;
	}
	/**
	 * 设置：推送地区，以“,”分隔，保存前台传入值
	 */
	public void setPushArea(String pushArea) {
		this.pushArea = pushArea;
	}
	/**
	 * 获取：推送地区，以“,”分隔，保存前台传入值
	 */
	public String getPushArea() {
		return pushArea;
	}
	/**
	 * 设置：真实推送地区，把“全部”转换成真实地区
	 */
	public void setRealPushArea(String realPushArea) {
		this.realPushArea = realPushArea;
	}
	/**
	 * 获取：真实推送地区，把“全部”转换成真实地区
	 */
	public String getRealPushArea() {
		return realPushArea;
	}
	/**
	 * 设置：状态 1 正常推送 2 不推送
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态 1 正常推送 2 不推送
	 */
	public Integer getStatus() {
		return status;
	}

	public Long getSystem() {
		return system;
	}

	public void setSystem(Long system) {
		this.system = system;
	}
}
