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

import mf.entity.MfHouseInfoEntity;
import mf.service.MfHouseInfoService;
import mf.utils.PageUtils;
import mf.utils.R;


/**
 * 房源信息表
 * 
 * @author dengfan
 * @email 283105211@qq.com
 * @date 2017-03-05 10:40:24
 */
@Controller
@RequestMapping("mfhouseinfo")
public class MfHouseInfoController {
	@Autowired
	private MfHouseInfoService mfHouseInfoService;
	
	@RequestMapping("/mfhouseinfo.html")
	public String list(){
		return "mfhouseinfo/mfhouseinfo.html";
	}
	
	@RequestMapping("/mfhouseinfo_add.html")
	public String add(){
		return "mfhouseinfo/mfhouseinfo_add.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("mfhouseinfo:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<MfHouseInfoEntity> mfHouseInfoList = mfHouseInfoService.queryList(map);
		int total = mfHouseInfoService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(mfHouseInfoList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("mfhouseinfo:info")
	public R info(@PathVariable("id") Long id){
		MfHouseInfoEntity mfHouseInfo = mfHouseInfoService.queryObject(id);
		
		return R.ok().put("mfHouseInfo", mfHouseInfo);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("mfhouseinfo:save")
	public R save(@RequestBody MfHouseInfoEntity mfHouseInfo){
		mfHouseInfoService.save(mfHouseInfo);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("mfhouseinfo:update")
	public R update(@RequestBody MfHouseInfoEntity mfHouseInfo){
		mfHouseInfoService.update(mfHouseInfo);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("mfhouseinfo:delete")
	public R delete(@RequestBody Long[] ids){
		mfHouseInfoService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
