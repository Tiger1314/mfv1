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

import mf.entity.MfSourceEntity;
import mf.service.MfSourceService;
import mf.utils.PageUtils;
import mf.utils.R;


/**
 * 房源来源表
 * 
 * @author dengfan
 * @email 283105211@qq.com
 * @date 2017-03-05 10:40:24
 */
@Controller
@RequestMapping("mfsource")
public class MfSourceController {
	@Autowired
	private MfSourceService mfSourceService;
	
	@RequestMapping("/mfsource.html")
	public String list(){
		return "mfsource/mfsource.html";
	}
	
	@RequestMapping("/mfsource_add.html")
	public String add(){
		return "mfsource/mfsource_add.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("mfsource:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<MfSourceEntity> mfSourceList = mfSourceService.queryList(map);
		int total = mfSourceService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(mfSourceList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("mfsource:info")
	public R info(@PathVariable("id") Integer id){
		MfSourceEntity mfSource = mfSourceService.queryObject(id);
		
		return R.ok().put("mfSource", mfSource);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("mfsource:save")
	public R save(@RequestBody MfSourceEntity mfSource){
		mfSourceService.save(mfSource);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("mfsource:update")
	public R update(@RequestBody MfSourceEntity mfSource){
		mfSourceService.update(mfSource);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("mfsource:delete")
	public R delete(@RequestBody Integer[] ids){
		mfSourceService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
