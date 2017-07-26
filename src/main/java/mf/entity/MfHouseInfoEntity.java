package mf.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 房源信息表
 * 
 * @author dengfan
 * @email 283105211@qq.com
 * @date 2017-03-05 10:40:24
 */
public class MfHouseInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//联系电话
	private String phone;
	//联系人
	private String contact;
	//联系地址
	private String address;
	//房源户型
	private String unit;
	//房源类型，1为住宅，2为写字楼
	private Integer houseType;
	//信息类型，1为出售，2为出租，3为求购，4为求租
	private Integer infoType;
	//单价（元/㎡或元/月）
	private Integer unitPrice;
	//总价（万）
	private Integer totalPrice;
	//面积（㎡）
	private String area;
	//标题
	private String title;
	//介绍
	private String introduction;
	//图片，多张逗号","分割
	private String pics;
	//来源类型ID
	private Integer sourceId;
	//来源主页链接（爬虫使用）
	private String profile;
	//来源主页链接（前端展示）
	private String url;
	//发布时间
	private Date publicTime;
	//业务字段，默认为0，-1为虚假房源，1被举报房源
	private Integer status;
	//业务字段，默认为0，1已经推送
	private Integer pushedStatus;
	//对应mf_area省code
	private Integer provinceCode;
	//对应mf_area城市code
	private Integer cityCode;
	//对应mf_area区code
	private Integer districtCode;
	//对应mf_area区code
	private Integer businessCode;
	//标签，多个逗号分割
	private String tags;
	//
	private Date createTime;
	//
	private Date updateTime;

	//是否收藏 1 是  0 否
	private Integer iscollected;

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
	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：联系电话
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 获取：联系电话
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * 设置：联系人
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}
	/**
	 * 获取：联系人
	 */
	public String getContact() {
		return contact;
	}
	/**
	 * 设置：联系地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：联系地址
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 设置：房源户型
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}
	/**
	 * 获取：房源户型
	 */
	public String getUnit() {
		return unit;
	}
	/**
	 * 设置：房源类型，1为住宅，2为写字楼
	 */
	public void setHouseType(Integer houseType) {
		this.houseType = houseType;
	}
	/**
	 * 获取：房源类型，1为住宅，2为写字楼
	 */
	public Integer getHouseType() {
		return houseType;
	}
	/**
	 * 设置：信息类型，1为出售，2为出租，3为求购，4为求租
	 */
	public void setInfoType(Integer infoType) {
		this.infoType = infoType;
	}
	/**
	 * 获取：信息类型，1为出售，2为出租，3为求购，4为求租
	 */
	public Integer getInfoType() {
		return infoType;
	}
	/**
	 * 设置：单价（元/㎡或元/月）
	 */
	public void setUnitPrice(Integer unitPrice) {
		this.unitPrice = unitPrice;
	}
	/**
	 * 获取：单价（元/㎡或元/月）
	 */
	public Integer getUnitPrice() {
		return unitPrice;
	}
	/**
	 * 设置：总价（万）
	 */
	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}
	/**
	 * 获取：总价（万）
	 */
	public Integer getTotalPrice() {
		return totalPrice == null ? 0 : totalPrice;
	}
	/**
	 * 设置：面积（㎡）
	 */
	public void setArea(String area) {
		this.area = area;
	}
	/**
	 * 获取：面积（㎡）
	 */
	public String getArea() {
		return area == null ? "0" : area;
	}
	/**
	 * 设置：标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：标题
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：介绍
	 */
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	/**
	 * 获取：介绍
	 */
	public String getIntroduction() {
		return introduction;
	}
	/**
	 * 设置：图片，多张逗号","分割
	 */
	public void setPics(String pics) {
		this.pics = pics;
	}
	/**
	 * 获取：图片，多张逗号","分割
	 */
	public String getPics() {
		return pics;
	}
	/**
	 * 设置：来源类型ID
	 */
	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}
	/**
	 * 获取：来源类型ID
	 */
	public Integer getSourceId() {
		return sourceId;
	}
	/**
	 * 设置：来源主页链接（爬虫使用）
	 */
	public void setProfile(String profile) {
		this.profile = profile;
	}
	/**
	 * 获取：来源主页链接（爬虫使用）
	 */
	public String getProfile() {
		return profile;
	}
	/**
	 * 设置：来源主页链接（前端展示）
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * 获取：来源主页链接（前端展示）
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * 设置：发布时间
	 */
	public void setPublicTime(Date publicTime) {
		this.publicTime = publicTime;
	}
	/**
	 * 获取：发布时间
	 */
	public Date getPublicTime() {
		return publicTime;
	}
	/**
	 * 设置：业务字段，默认为0，-1为虚假房源，1被举报房源
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：业务字段，默认为0，-1为虚假房源，1被举报房源
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：业务字段，默认为0，1已经推送
	 */
	public void setPushedStatus(Integer pushedStatus) {
		this.pushedStatus = pushedStatus;
	}
	/**
	 * 获取：业务字段，默认为0，1已经推送
	 */
	public Integer getPushedStatus() {
		return pushedStatus;
	}
	/**
	 * 设置：对应mf_area省code
	 */
	public void setProvinceCode(Integer provinceCode) {
		this.provinceCode = provinceCode;
	}
	/**
	 * 获取：对应mf_area省code
	 */
	public Integer getProvinceCode() {
		return provinceCode;
	}
	/**
	 * 设置：对应mf_area城市code
	 */
	public void setCityCode(Integer cityCode) {
		this.cityCode = cityCode;
	}
	/**
	 * 获取：对应mf_area城市code
	 */
	public Integer getCityCode() {
		return cityCode;
	}
	/**
	 * 设置：对应mf_area区code
	 */
	public void setDistrictCode(Integer districtCode) {
		this.districtCode = districtCode;
	}
	/**
	 * 获取：对应mf_area区code
	 */
	public Integer getDistrictCode() {
		return districtCode;
	}
	/**
	 * 设置：对应mf_area区code
	 */
	public void setBusinessCode(Integer businessCode) {
		this.businessCode = businessCode;
	}
	/**
	 * 获取：对应mf_area区code
	 */
	public Integer getBusinessCode() {
		return businessCode;
	}
	/**
	 * 设置：标签，多个逗号分割
	 */
	public void setTags(String tags) {
		this.tags = tags;
	}
	/**
	 * 获取：标签，多个逗号分割
	 */
	public String getTags() {
		return tags;
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

	public Integer getIscollected() {
		return iscollected;
	}

	public void setIscollected(Integer iscollected) {
		this.iscollected = iscollected;
	}

	public Integer getIsSell() {
		return isSell;
	}

	public void setIsSell(Integer isSell) {
		this.isSell = isSell;
	}

	public String getSellType() {
		return sellType;
	}

	public void setSellType(String sellType) {
		this.sellType = sellType;
	}

	public String getSellArea() {
		return sellArea;
	}

	public void setSellArea(String sellArea) {
		this.sellArea = sellArea;
	}

	public String getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(String sellPrice) {
		this.sellPrice = sellPrice;
	}

	public Integer getIsBuy() {
		return isBuy;
	}

	public void setIsBuy(Integer isBuy) {
		this.isBuy = isBuy;
	}

	public String getBuyType() {
		return buyType;
	}

	public void setBuyType(String buyType) {
		this.buyType = buyType;
	}

	public String getBuyArea() {
		return buyArea;
	}

	public void setBuyArea(String buyArea) {
		this.buyArea = buyArea;
	}

	public String getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(String buyPrice) {
		this.buyPrice = buyPrice;
	}

	public Integer getIsRent() {
		return isRent;
	}

	public void setIsRent(Integer isRent) {
		this.isRent = isRent;
	}

	public String getRentType() {
		return rentType;
	}

	public void setRentType(String rentType) {
		this.rentType = rentType;
	}

	public String getRentArea() {
		return rentArea;
	}

	public void setRentArea(String rentArea) {
		this.rentArea = rentArea;
	}

	public String getRentPrice() {
		return rentPrice;
	}

	public void setRentPrice(String rentPrice) {
		this.rentPrice = rentPrice;
	}

	public Integer getIsQz() {
		return isQz;
	}

	public void setIsQz(Integer isQz) {
		this.isQz = isQz;
	}

	public String getQzType() {
		return qzType;
	}

	public void setQzType(String qzType) {
		this.qzType = qzType;
	}

	public String getQzArea() {
		return qzArea;
	}

	public void setQzArea(String qzArea) {
		this.qzArea = qzArea;
	}

	public String getQzPrice() {
		return qzPrice;
	}

	public void setQzPrice(String qzPrice) {
		this.qzPrice = qzPrice;
	}
}