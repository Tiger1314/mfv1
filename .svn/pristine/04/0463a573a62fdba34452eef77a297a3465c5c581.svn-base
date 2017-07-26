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

import mf.entity.MfSourceProfileEntity;
import mf.service.MfSourceProfileService;
import mf.utils.PageUtils;
import mf.utils.R;


/**
 * 来源的地区主页
 * 
 * @author dengfan
 * @email 283105211@qq.com
 * @date 2017-03-05 10:40:24
 */
@Controller
@RequestMapping("mfsourceprofile")
public class MfSourceProfileController {
	@Autowired
	private MfSourceProfileService mfSourceProfileService;
	
	@RequestMapping("/mfsourceprofile.html")
	public String list(){
		return "mfsourceprofile/mfsourceprofile.html";
	}
	
	@RequestMapping("/mfsourceprofile_add.html")
	public String add(){
		return "mfsourceprofile/mfsourceprofile_add.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("mfsourceprofile:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<MfSourceProfileEntity> mfSourceProfileList = mfSourceProfileService.queryList(map);
		int total = mfSourceProfileService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(mfSourceProfileList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("mfsourceprofile:info")
	public R info(@PathVariable("id") Long id){
		MfSourceProfileEntity mfSourceProfile = mfSourceProfileService.queryObject(id);
		
		return R.ok().put("mfSourceProfile", mfSourceProfile);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("mfsourceprofile:save")
	public R save(@RequestBody MfSourceProfileEntity mfSourceProfile){
		mfSourceProfileService.save(mfSourceProfile);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("mfsourceprofile:update")
	public R update(@RequestBody MfSourceProfileEntity mfSourceProfile){
		mfSourceProfileService.update(mfSourceProfile);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("mfsourceprofile:delete")
	public R delete(@RequestBody Long[] ids){
		mfSourceProfileService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
