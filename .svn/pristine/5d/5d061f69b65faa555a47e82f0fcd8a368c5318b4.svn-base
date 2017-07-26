package mf.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author dengfan
 * @email 283105211@qq.com
 * @date 2017-03-05 21:40:30
 */
public class MfHouseClickEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private String openId;
	//
	private Integer houseId;
	//
	private Date createTime;

	//点赞个数
	private Integer likes;

	//是否点赞过 0没有; 1有
	private Integer isliked;

	//个性签名
	private String signature;

	//用户信息
	private MfUserEntity user = new MfUserEntity();

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
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：
	 */
	public Date getCreateTime() {
		return createTime;
	}

	public Integer getLikes() {
		return likes;
	}

	public void setLikes(Integer likes) {
		this.likes = likes;
	}

	public MfUserEntity getUser() {
		return user;
	}

	public void setUser(MfUserEntity user) {
		this.user = user;
	}

	public Integer getIsliked() {
		return isliked;
	}

	public void setIsliked(Integer isliked) {
		this.isliked = isliked;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}
}
