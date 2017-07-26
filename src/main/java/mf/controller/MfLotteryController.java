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

import mf.entity.MfLotteryEntity;
import mf.service.MfLotteryService;
import mf.utils.PageUtils;
import mf.utils.R;


/**
 * 
 * 
 * @author dengfan
 * @email 283105211@qq.com
 * @date 2017-06-12 15:03:43
 */
@Controller
@RequestMapping("mflottery")
public class MfLotteryController {
	@Autowired
	private MfLotteryService mfLotteryService;
	
	@RequestMapping("/mflottery.html")
	public String list(){
		return "mflottery/mflottery.html";
	}
	
	@RequestMapping("/mflottery_add.html")
	public String add(){
		return "mflottery/mflottery_add.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("mflottery:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<MfLotteryEntity> mfLotteryList = mfLotteryService.queryList(map);
		int total = mfLotteryService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(mfLotteryList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("mflottery:info")
	public R info(@PathVariable("id") Integer id){
		MfLotteryEntity mfLottery = mfLotteryService.queryObject(id);
		
		return R.ok().put("mfLottery", mfLottery);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("mflottery:save")
	public R save(@RequestBody MfLotteryEntity mfLottery){
		mfLotteryService.save(mfLottery);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("mflottery:update")
	public R update(@RequestBody MfLotteryEntity mfLottery){
		mfLotteryService.update(mfLottery);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("mflottery:delete")
	public R delete(@RequestBody Integer[] ids){
		mfLotteryService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
