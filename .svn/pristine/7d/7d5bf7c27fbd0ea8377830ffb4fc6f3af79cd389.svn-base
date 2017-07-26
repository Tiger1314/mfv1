package mf.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

import mf.entity.MfRechargeEntity;
import mf.service.MfRechargeService;
import mf.utils.PageUtils;
import mf.utils.R;


/**
 * 
 * 
 * @author dengfan
 * @email 283105211@qq.com
 * @date 2017-03-05 21:09:29
 */
@Controller
@RequestMapping("mfrecharge")
public class MfRechargeController {
	@Autowired
	private MfRechargeService mfRechargeService;
	
	@RequestMapping("/mfrecharge.html")
	public String list(){
		return "mfrecharge/mfrecharge.html";
	}
	
	@RequestMapping("/mfrecharge_add.html")
	public String add(){
		return "mfrecharge/mfrecharge_add.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("mfrecharge:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<MfRechargeEntity> mfRechargeList = mfRechargeService.queryList(map);
		int total = mfRechargeService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(mfRechargeList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("mfrecharge:info")
	public R info(@PathVariable("id") Integer id){
		MfRechargeEntity mfRecharge = mfRechargeService.queryObject(id);
		
		return R.ok().put("mfRecharge", mfRecharge);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("mfrecharge:save")
	public R save(@RequestBody MfRechargeEntity mfRecharge){
		mfRechargeService.save(mfRecharge);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("mfrecharge:update")
	public R update(@RequestBody MfRechargeEntity mfRecharge){
		mfRechargeService.update(mfRecharge);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("mfrecharge:delete")
	public R delete(@RequestBody Integer[] ids){
		mfRechargeService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
