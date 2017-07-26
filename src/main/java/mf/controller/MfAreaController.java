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

import mf.entity.MfAreaEntity;
import mf.service.MfAreaService;
import mf.utils.PageUtils;
import mf.utils.R;


/**
 * 地理信息表（以58为准）
 * 
 * @author dengfan
 * @email 283105211@qq.com
 * @date 2017-03-05 10:40:24
 */
@Controller
@RequestMapping("mfarea")
public class MfAreaController {
	@Autowired
	private MfAreaService mfAreaService;
	
	@RequestMapping("/mfarea.html")
	public String list(){
		return "mfarea/mfarea.html";
	}
	
	@RequestMapping("/mfarea_add.html")
	public String add(){
		return "mfarea/mfarea_add.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("mfarea:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<MfAreaEntity> mfAreaList = mfAreaService.queryList(map);
		int total = mfAreaService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(mfAreaList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("mfarea:info")
	public R info(@PathVariable("id") Long id){
		MfAreaEntity mfArea = mfAreaService.queryObject(id);
		
		return R.ok().put("mfArea", mfArea);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("mfarea:save")
	public R save(@RequestBody MfAreaEntity mfArea){
		mfAreaService.save(mfArea);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("mfarea:update")
	public R update(@RequestBody MfAreaEntity mfArea){
		mfAreaService.update(mfArea);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("mfarea:delete")
	public R delete(@RequestBody Long[] ids){
		mfAreaService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
