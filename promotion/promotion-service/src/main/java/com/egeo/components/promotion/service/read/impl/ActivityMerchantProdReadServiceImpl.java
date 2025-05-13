package com.egeo.components.promotion.service.read.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.service.read.ActivityMerchantProdReadService;
import com.egeo.components.promotion.manage.read.ActivityMerchantProdReadManage;
import com.egeo.components.promotion.condition.ActivityMerchantProdCondition;
import com.egeo.components.promotion.converter.ActivityMerchantProdConverter;
import com.egeo.components.promotion.dto.ActivityMerchantProdDTO;
import com.egeo.components.promotion.po.ActivityMerchantProdPO;
 
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("activityMerchantProdReadService")
public class ActivityMerchantProdReadServiceImpl implements ActivityMerchantProdReadService {
	@Autowired
	private ActivityMerchantProdReadManage activityMerchantProdReadManage;

	@Override
	public PageResult<ActivityMerchantProdDTO> findPageActivityMerchantProd(Pagination page,
			ActivityMerchantProdDTO dto) {
		ActivityMerchantProdPO po = ActivityMerchantProdConverter.toPO(dto);
        PageResult<ActivityMerchantProdPO> pageResult = activityMerchantProdReadManage.findPage(page, po);
        
        List<ActivityMerchantProdDTO> list = new ArrayList<ActivityMerchantProdDTO>();
        for (ActivityMerchantProdPO tmp : pageResult.getList()) {
            ActivityMerchantProdDTO activityMerchantProdDTO = ActivityMerchantProdConverter.toDTO(tmp);
                list.add(activityMerchantProdDTO);
        }
        PageResult<ActivityMerchantProdDTO> result = new PageResult<ActivityMerchantProdDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<ActivityMerchantProdDTO> findAll(ActivityMerchantProdDTO dto) {
		List<ActivityMerchantProdPO> list = activityMerchantProdReadManage.findAll(ActivityMerchantProdConverter.toPO(dto));
		return ActivityMerchantProdConverter.toDTO(list);
	}
	/**
	 * 查询指定第几个有效活动及商品id
	 * @param date
	 * @param pages
	 * @return
	 */
	@Override
	public List<ActivityMerchantProdDTO> activityMerchantProdByPages(Date date, Integer pages,Long platformId) {
		List<ActivityMerchantProdDTO> ActivityMerchantProdList = new ArrayList<ActivityMerchantProdDTO>();
		List<ActivityMerchantProdCondition> list = activityMerchantProdReadManage.activityMerchantProdByPages(date, pages,platformId);
		for (ActivityMerchantProdCondition activityMerchantProdCondition : list) {
			ActivityMerchantProdDTO activityMerchantProdDTO = ActivityMerchantProdConverter.toDTO(activityMerchantProdCondition);
			activityMerchantProdDTO.setActivityName(activityMerchantProdCondition.getActivityName());
			activityMerchantProdDTO.setStartTime(activityMerchantProdCondition.getStartTime());
			activityMerchantProdDTO.setFinishTime(activityMerchantProdCondition.getFinishTime());
			ActivityMerchantProdList.add(activityMerchantProdDTO);
		}
		return ActivityMerchantProdList;
	}
}
	