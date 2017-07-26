package mf.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mf.entity.MfRechargeOrderEntity;
import mf.entity.MfUserEntity;
import mf.service.MfRechargeOrderService;
import mf.service.MfUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

import mf.entity.MfComplaintsEntity;
import mf.service.MfComplaintsService;
import mf.utils.PageUtils;
import mf.utils.R;


/**
 * 
 * 
 * @author dengfan
 * @email 283105211@qq.com
 * @date 2017-06-12 15:59:43
 */
@Controller
@RequestMapping("mfcomplaints")
public class MfComplaintsController {
	@Autowired
	private MfComplaintsService mfComplaintsService;
	@Autowired
	private MfRechargeOrderService mfRechargeOrderService;
	@Autowired
	private MfUserService mfUserService;
	
	@RequestMapping("/mfcomplaints.html")
	public String list(){
		return "mfcomplaints/mfcomplaints.html";
	}
	
	@RequestMapping("/mfcomplaints_add.html")
	public String add(){
		return "mfcomplaints/mfcomplaints_add.html";
	}

	@RequestMapping("/house_view.html")
	public String houseView(){
		return "mfcomplaints/house_view.html";
	}

	@RequestMapping("/user_view.html")
	public String userView(){
		return "mfcomplaints/user_view.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<MfComplaintsEntity> mfComplaintsList = mfComplaintsService.queryList(map);
		int total = mfComplaintsService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(mfComplaintsList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}

	/**
	 * 根据房源ID 获取举报列表
	 * @param houseId
	 * @param page
	 * @param limit
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/listhouse")
	public R listhouse(Integer houseId, Integer page, Integer limit){

		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		map.put("houseId", houseId);

		List<MfComplaintsEntity> mfComplaintsList = mfComplaintsService.queryListHouse(map);
		int total = mfComplaintsService.queryHouseTotal(map);

		for(MfComplaintsEntity entity : mfComplaintsList){

			map.clear();
			map.put("openId", entity.getOpenId());

			int c = mfComplaintsService.queryHouseTotal(map);

			entity.setComcount(String.valueOf(c));
		}

		PageUtils pageUtil = new PageUtils(mfComplaintsList, total, limit, page);

		return R.ok().put("page", pageUtil);
	}

	@ResponseBody
	@RequestMapping("/listuser")
	public R listuser(String openId, Integer page, Integer limit){

		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		map.put("openId", openId);

		List<MfComplaintsEntity> mfComplaintsList = mfComplaintsService.queryListHouse(map);
		int total = mfComplaintsService.queryHouseTotal(map);

		PageUtils pageUtil = new PageUtils(mfComplaintsList, total, limit, page);

		return R.ok().put("page", pageUtil);
	}


	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id){
		MfComplaintsEntity mfComplaints = mfComplaintsService.queryObject(id);
		
		return R.ok().put("mfComplaints", mfComplaints);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	public R save(@RequestBody MfComplaintsEntity mfComplaints){
		mfComplaintsService.save(mfComplaints);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	public R update(@RequestBody MfComplaintsEntity mfComplaints){
		mfComplaintsService.update(mfComplaints);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids){
		mfComplaintsService.deleteBatch(ids);
		
		return R.ok();
	}

	/**
	 * 设置可以房源
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/complaints")
	public R complaints(@RequestBody Integer[] ids){

		for(Integer id : ids){
			//查询所有推送过的用户
			Map<String, Object> param = new HashMap<>();
			param.put("houseId", id);

			List<MfRechargeOrderEntity> list = mfRechargeOrderService.queryList(param);

			MfUserEntity user;

			for(MfRechargeOrderEntity entity : list){

				user = mfUserService.queryObject(entity.getOpenId());
				user.setWnb(user.getWnb() + 1);

				mfUserService.update(user);

				mfRechargeOrderService.addOrder(user.getOpenId(), entity.getTitle() + "[可疑房源]", "蜗牛壳", "1", 4, id);
			}
		}
		mfComplaintsService.complaints(ids);

		return R.ok();
	}
}
