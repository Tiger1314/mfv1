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

import mf.entity.MfAdEntity;
import mf.service.MfAdService;
import mf.utils.PageUtils;
import mf.utils.R;


/**
 * 
 * 
 * @author dengfan
 * @email 283105211@qq.com
 * @date 2017-03-06 22:25:21
 */
@Controller
@RequestMapping("mfad")
public class MfAdController {
	@Autowired
	private MfAdService mfAdService;
	
	@RequestMapping("/mfad.html")
	public String list(){
		return "mfad/mfad.html";
	}
	
	@RequestMapping("/mfad_add.html")
	public String add(){
		return "mfad/mfad_add.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("mfad:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<MfAdEntity> mfAdList = mfAdService.queryList(map);
		int total = mfAdService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(mfAdList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("mfad:info")
	public R info(@PathVariable("id") Integer id){
		MfAdEntity mfAd = mfAdService.queryObject(id);
		
		return R.ok().put("mfAd", mfAd);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("mfad:save")
	public R save(@RequestBody MfAdEntity mfAd){
		mfAdService.save(mfAd);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("mfad:update")
	public R update(@RequestBody MfAdEntity mfAd){
		mfAdService.update(mfAd);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("mfad:delete")
	public R delete(@RequestBody Integer[] ids){
		mfAdService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
