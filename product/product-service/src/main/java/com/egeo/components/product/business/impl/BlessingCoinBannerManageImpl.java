package com.egeo.components.product.business.impl;
	

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.product.business.BlessingCoinBannerManage;
import com.egeo.components.product.facade.BlessingCoinBannerFacade;
import com.egeo.components.product.dto.BlessingCoinBannerDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;

@Service("blessingCoinBanner")
public class BlessingCoinBannerManageImpl implements BlessingCoinBannerManage{

	
	@Resource(name="blessingCoinBannerFacade")
	private BlessingCoinBannerFacade blessingCoinBannerFacade;

	@Override
	public Map<String, Object> findBlessingCoinBannerById(BlessingCoinBannerDTO dto) {
		return blessingCoinBannerFacade.findBlessingCoinBannerById(dto);
	}

	@Override
	public PageResult<Map<String, Object>> findBlessingCoinBannerOfPage(BlessingCoinBannerDTO dto, Pagination page) {
		PageResult<Map<String, Object>> pageResult = new PageResult<>();
		PageResult<BlessingCoinBannerDTO> result = blessingCoinBannerFacade.findBlessingCoinBannerOfPage(dto, page);
		List<Map<String, Object>> maps = new ArrayList<>();
		List<BlessingCoinBannerDTO> list = result.getList();
		for (BlessingCoinBannerDTO blessingCoinBannerDTO : list) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", blessingCoinBannerDTO.getId());
			map.put("name", blessingCoinBannerDTO.getName());
			map.put("type", blessingCoinBannerDTO.getType());
			map.put("sortValue", blessingCoinBannerDTO.getSortValue());
			map.put("location", blessingCoinBannerDTO.getLocation());
			map.put("isShow", blessingCoinBannerDTO.getIsShow());
			//根据轮播图id查询公司信息
			String companys = blessingCoinBannerFacade.findCompanysByBlessingCoinBannerId(blessingCoinBannerDTO.getId());
			map.put("companys", companys);
			maps.add(map);
		}
		pageResult.setList(maps);
		pageResult.setPageNo(result.getPageNo());
		pageResult.setPageSize(result.getPageSize());
		pageResult.setTotalSize(result.getTotalSize());
		return pageResult;
	}

	@Override
	public List<BlessingCoinBannerDTO> findBlessingCoinBannerAll(BlessingCoinBannerDTO dto) {
		return blessingCoinBannerFacade.findBlessingCoinBannerAll(dto);
	}

	@Override
	public Long insertBlessingCoinBannerWithTx(BlessingCoinBannerDTO dto,List<Long> companyIds) {
		return blessingCoinBannerFacade.insertBlessingCoinBannerWithTx(dto,companyIds);
	}

	@Override
	public int updateBlessingCoinBannerWithTx(BlessingCoinBannerDTO dto,List<Long> companyIds) {
		if(EmptyUtil.isEmpty(dto.getId())){
			blessingCoinBannerFacade.insertBlessingCoinBannerWithTx(dto,companyIds);
			return 1;
		}else{
			return blessingCoinBannerFacade.updateBlessingCoinBannerWithTx(dto,companyIds);
		}
		
	}

	@Override
	public int deleteBlessingCoinBannerWithTx(BlessingCoinBannerDTO dto) {
		return blessingCoinBannerFacade.deleteBlessingCoinBannerWithTx(dto);
	}
	/**
	 * 客户端查询所有有效轮播图信息
	 * @param vo
	 * @param req
	 * @return
	 */
	@Override
	public List<Map<String, Object>> findBlessingCoinBannerAllApp(BlessingCoinBannerDTO dto) {
		List<Map<String, Object>> maps = new ArrayList<>();
		//查询需要显示的轮播图
		dto.setIsShow(1);
		List<BlessingCoinBannerDTO> blessingCoinBannerList = blessingCoinBannerFacade.findBlessingCoinBannerAllApp(dto);
		for (BlessingCoinBannerDTO blessingCoinBannerDTO : blessingCoinBannerList) {
			Map<String, Object> map = new HashMap<>(); 
			map.put("type", blessingCoinBannerDTO.getType());
			map.put("name", blessingCoinBannerDTO.getName());
			map.put("pictureUrl", blessingCoinBannerDTO.getPictureUrl());
			map.put("path", blessingCoinBannerDTO.getPath());
			map.put("sortValue", blessingCoinBannerDTO.getSortValue());
			map.put("standardUnitId", blessingCoinBannerDTO.getStandardUnitId());
			map.put("activityId", blessingCoinBannerDTO.getActivityId());
			maps.add(map);
		}
		return maps;
	}


}
	