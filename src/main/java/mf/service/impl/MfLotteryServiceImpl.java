package mf.service.impl;

import mf.service.SysConfigService;
import mf.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mf.dao.MfLotteryDao;
import mf.entity.MfLotteryEntity;
import mf.service.MfLotteryService;



@Service("mfLotteryService")
public class MfLotteryServiceImpl implements MfLotteryService {
	@Autowired
	private MfLotteryDao mfLotteryDao;
	@Autowired
	private SysConfigService sysConfigService;
	
	@Override
	public MfLotteryEntity queryObject(Integer id){
		return mfLotteryDao.queryObject(id);
	}
	
	@Override
	public List<MfLotteryEntity> queryList(Map<String, Object> map){
		return mfLotteryDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return mfLotteryDao.queryTotal(map);
	}
	
	@Override
	public void save(MfLotteryEntity mfLottery){
		mfLotteryDao.save(mfLottery);
	}
	
	@Override
	public void update(MfLotteryEntity mfLottery){
		mfLotteryDao.update(mfLottery);
	}
	
	@Override
	public void delete(Integer id){
		mfLotteryDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		mfLotteryDao.deleteBatch(ids);
	}

	@Override
	public boolean checkCanLottery(String openId) {

		Map<String, Object> param = new HashMap<>();

		param.put("openId", openId);
		param.put("createTime", DateUtils.getTodayDate());

		int count = mfLotteryDao.queryTotal(param);

		int lotteryCount = Integer.valueOf(sysConfigService.getValue("LOTTERY_COUNT", "1"));

		if(count < lotteryCount){
			return true;
		}

		return false;
	}

	@Override
	public int canlotteryCount(String openId) {

		Map<String, Object> param = new HashMap<>();

		param.put("openId", openId);
		param.put("createTime", DateUtils.getTodayDate());

		int count = mfLotteryDao.queryTotal(param);

		int lotteryCount = Integer.valueOf(sysConfigService.getValue("LOTTERY_COUNT", "1"));

		return lotteryCount - count;
	}
}
