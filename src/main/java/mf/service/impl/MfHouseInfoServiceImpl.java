package mf.service.impl;

import mf.service.SysConfigService;
import mf.utils.CommonUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mf.dao.MfHouseInfoDao;
import mf.entity.MfHouseInfoEntity;
import mf.service.MfHouseInfoService;



@Service("mfHouseInfoService")
public class MfHouseInfoServiceImpl implements MfHouseInfoService {
	@Autowired
	private MfHouseInfoDao mfHouseInfoDao;
	@Autowired
	private SysConfigService sysConfigService;
	
	@Override
	public MfHouseInfoEntity queryObject(Long id){
		return mfHouseInfoDao.queryObject(id);
	}
	
	@Override
	public List<MfHouseInfoEntity> queryList(Map<String, Object> map){
		return mfHouseInfoDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return mfHouseInfoDao.queryTotal(map);
	}
	
	@Override
	public void save(MfHouseInfoEntity mfHouseInfo){
		mfHouseInfoDao.save(mfHouseInfo);
	}
	
	@Override
	public void update(MfHouseInfoEntity mfHouseInfo){
		mfHouseInfoDao.update(mfHouseInfo);
	}
	
	@Override
	public void delete(Long id){
		mfHouseInfoDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		mfHouseInfoDao.deleteBatch(ids);
	}

	/**
	 * 举报房源
	 *
	 * @param id
	 */
	@Override
	public void housereport(Long id) {
		MfHouseInfoEntity entity = new MfHouseInfoEntity();
		entity.setId(id);
		entity.setStatus(1);
		mfHouseInfoDao.update(entity);
	}

	/**
	 * 获取最新的一条房源记录
	 *
	 * @return
	 */
	@Override
	public List<MfHouseInfoEntity> getNewHouse(Long areaCode) {

		int count = Integer.valueOf(sysConfigService.getValue("CREATE_COUNT_ONE_TIME", "10"));

		Map<String, Object> map = new HashMap<>();
		map.put("areaCode", areaCode);
		map.put("offset", 0);
		map.put("limit", 10);
		map.put("pushedStatus", 0);

		List<MfHouseInfoEntity> list = mfHouseInfoDao.queryList(map);

		return list;
	}

	/**
	 * 修改房源信息-房源广场
	 *
	 * @param house
	 */
	@Override
	public void updateHouseInfo(MfHouseInfoEntity house) {
		//出售
		if(house.getInfoType() == 1){
			house.setIsSell(1);

			if(StringUtils.isNotBlank(house.getArea())){
				Double area = Double.valueOf(house.getArea());

				if(area < 50){
					house.setSellArea("2");
				}
				else if(area >= 50 && area < 100){
					house.setSellArea("3");
				}
				else if(area >= 100 && area < 150){
					house.setSellArea("4");
				}
				else if(area >= 150 && area < 200){
					house.setSellArea("5");
				}
				else if(area >= 200){
					house.setSellArea("6");
				}
			}
			else{
				house.setSellArea("1");
			}

			Integer price = house.getTotalPrice() == null ? 0 : house.getTotalPrice();

			if(price < 600000){
				house.setSellPrice("2");
			}
			else if(price >= 600000 && price < 1000000){
				house.setSellPrice("3");
			}
			else if(price >= 1000000 && price < 1500000){
				house.setSellPrice("4");
			}
			else if(price >= 1500000 && price < 2000000){
				house.setSellPrice("5");
			}
			else if(price >= 2000000 && price < 3000000){
				house.setSellPrice("6");
			}
			else if(price >= 3000000 && price < 5000000){
				house.setSellPrice("7");
			}
		}
		//求购
		else if(house.getInfoType() == 3){
			house.setIsBuy(1);

			if(StringUtils.isNotBlank(house.getArea())){
				Double area = Double.valueOf(house.getArea());

				if(area < 50){
					house.setBuyArea("2");
				}
				else if(area >= 50 && area < 100){
					house.setBuyArea("3");
				}
				else if(area >= 100 && area < 150){
					house.setBuyArea("4");
				}
				else if(area >= 150 && area < 200){
					house.setBuyArea("5");
				}
				else if(area >= 200){
					house.setBuyArea("6");
				}
			}
			else{
				house.setBuyArea("1");
			}

			Integer price = house.getTotalPrice() == null ? 0 : house.getTotalPrice();

			if(price < 600000){
				house.setSellPrice("2");
			}
			else if(price >= 600000 && price < 1000000){
				house.setBuyPrice("3");
			}
			else if(price >= 1000000 && price < 1500000){
				house.setBuyPrice("4");
			}
			else if(price >= 1500000 && price < 2000000){
				house.setBuyPrice("5");
			}
			else if(price >= 2000000 && price < 3000000){
				house.setBuyPrice("6");
			}
			else if(price >= 3000000 && price < 5000000){
				house.setBuyPrice("7");
			}
		}
		//出租
		else if(house.getInfoType() == 2 || house.getInfoType() == 5){

			house.setIsRent(1);

			if(house.getInfoType() == 2){
				house.setRentType("2");
			}
			else{
				house.setRentType("3");
			}

			if(StringUtils.isNotBlank(house.getArea())){
				Double area = Double.valueOf(house.getArea());

				if(area < 50){
					house.setRentArea("2");
				}
				else if(area >= 50 && area < 100){
					house.setRentArea("3");
				}
				else if(area >= 100 && area < 150){
					house.setRentArea("4");
				}
				else if(area >= 150 && area < 200){
					house.setRentArea("5");
				}
				else if(area >= 200){
					house.setRentArea("6");
				}
			}
			else{
				house.setRentArea("1");
			}

			Integer price = house.getUnitPrice() == null ? 0 : house.getUnitPrice();

			if(price < 1000){
				house.setRentPrice("2");
			}
			else if(price >= 1000 && price < 2000){
				house.setRentPrice("3");
			}
			else if(price >= 2000 && price < 3000){
				house.setRentPrice("4");
			}
			else if(price >= 3000 && price < 4000){
				house.setRentPrice("5");
			}
			else if(price >= 5000 && price < 10000){
				house.setRentPrice("6");
			}
			else if(price >= 10000){
				house.setRentPrice("7");
			}
		}

		else if(house.getInfoType() == 4){
			house.setIsQz(1);

			if(StringUtils.isNotBlank(house.getArea())){
				Double area = Double.valueOf(house.getArea());

				if(area < 50){
					house.setQzArea("2");
				}
				else if(area >= 50 && area < 100){
					house.setQzArea("3");
				}
				else if(area >= 100 && area < 150){
					house.setQzArea("4");
				}
				else if(area >= 150 && area < 200){
					house.setQzArea("5");
				}
				else if(area >= 200){
					house.setQzArea("6");
				}
			}
			else{
				house.setQzArea("1");
			}

			Integer price = house.getUnitPrice() == null ? 0 : house.getUnitPrice();

			if(price < 1000){
				house.setQzPrice("2");
			}
			else if(price >= 1000 && price < 2000){
				house.setQzPrice("3");
			}
			else if(price >= 2000 && price < 3000){
				house.setQzPrice("4");
			}
			else if(price >= 3000 && price < 4000){
				house.setQzPrice("5");
			}
			else if(price >= 5000 && price < 10000){
				house.setQzPrice("6");
			}
			else if(price >= 10000){
				house.setQzPrice("7");
			}
		}

		house.setPushedStatus(1);
		//为了测试暂时注释掉
		mfHouseInfoDao.update(house);
	}
}
