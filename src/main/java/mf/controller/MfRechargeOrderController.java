package mf.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

import mf.entity.MfRechargeOrderEntity;
import mf.service.MfRechargeOrderService;
import mf.utils.PageUtils;
import mf.utils.R;


/**
 * 
 * 
 * @author dengfan
 * @email 283105211@qq.com
 * @date 2017-05-03 09:17:46
 */
@Controller
@RequestMapping("mfrechargeorder")
public class MfRechargeOrderController {
	@Autowired
	private MfRechargeOrderService mfRechargeOrderService;
	
	@RequestMapping("/mfrechargeorder.html")
	public String list(){
		return "mfrechargeorder/mfrechargeorder.html";
	}
	
	@RequestMapping("/mfrechargeorder_add.html")
	public String add(){
		return "mfrechargeorder/mfrechargeorder_add.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("mfrechargeorder:list")
	public R list(Integer page, Integer limit, String nickname, Integer orderType){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);

		if(StringUtils.isNotBlank(nickname)){
			map.put("nickname", nickname);
		}

		map.put("orderType", orderType);
		
		//查询列表数据
		List<MfRechargeOrderEntity> mfRechargeOrderList = mfRechargeOrderService.queryList(map);
		int total = mfRechargeOrderService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(mfRechargeOrderList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("mfrechargeorder:info")
	public R info(@PathVariable("id") Integer id){
		MfRechargeOrderEntity mfRechargeOrder = mfRechargeOrderService.queryObject(id);
		
		return R.ok().put("mfRechargeOrder", mfRechargeOrder);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("mfrechargeorder:save")
	public R save(@RequestBody MfRechargeOrderEntity mfRechargeOrder){
		mfRechargeOrderService.save(mfRechargeOrder);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("mfrechargeorder:update")
	public R update(@RequestBody MfRechargeOrderEntity mfRechargeOrder){
		mfRechargeOrderService.update(mfRechargeOrder);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("mfrechargeorder:delete")
	public R delete(@RequestBody Integer[] ids){
		mfRechargeOrderService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
