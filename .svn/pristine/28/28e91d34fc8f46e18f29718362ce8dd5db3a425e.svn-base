package mf.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mf.utils.PageUtils;
import mf.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

import mf.entity.MfUserEntity;
import mf.service.MfUserService;



/**
 * 
 * 
 * @author dengfan
 * @email 283105211@qq.com
 * @date 2017-03-03 16:14:15
 */
@Controller
@RequestMapping("mfuser")
public class MfUserController {
	@Autowired
	private MfUserService mfUserService;
	
	@RequestMapping("/mfuser.html")
	public String list(){
		return "mfuser/mfuser.html";
	}
	
	@RequestMapping("/mfuser_add.html")
	public String add(){
		return "mfuser/mfuser_add.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("mfuser:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<MfUserEntity> mfUserList = mfUserService.queryList(map);
		int total = mfUserService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(mfUserList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{openId}")
	@RequiresPermissions("mfuser:info")
	public R info(@PathVariable("openId") String openId){
		MfUserEntity mfUser = mfUserService.queryObject(openId);
		
		return R.ok().put("mfUser", mfUser);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("mfuser:save")
	public R save(@RequestBody MfUserEntity mfUser){
		mfUserService.save(mfUser);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("mfuser:update")
	public R update(@RequestBody MfUserEntity mfUser){
		mfUserService.update(mfUser);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("mfuser:delete")
	public R delete(@RequestBody String[] openIds){
		mfUserService.deleteBatch(openIds);
		
		return R.ok();
	}
	
}
