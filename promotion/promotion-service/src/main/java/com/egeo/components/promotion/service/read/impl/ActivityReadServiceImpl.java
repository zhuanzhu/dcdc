package com.egeo.components.promotion.service.read.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.service.read.ActivityReadService;
import com.egeo.components.promotion.manage.read.ActivityReadManage;
import com.egeo.components.promotion.converter.ActivityConverter;
import com.egeo.components.promotion.dto.ActivityDTO;
import com.egeo.components.promotion.po.ActivityPO;
 
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("activityReadService")
public class ActivityReadServiceImpl implements ActivityReadService {
	@Autowired
	private ActivityReadManage activityReadManage;

	@Override
	public PageResult<ActivityDTO> findPageActivity(Pagination page, ActivityDTO dto) {
		ActivityPO po = ActivityConverter.toPO(dto);
        PageResult<ActivityPO> pageResult = activityReadManage.findPage(page, po);
        
        List<ActivityDTO> list = new ArrayList<ActivityDTO>();
        for (ActivityPO tmp : pageResult.getList()) {
            ActivityDTO activityDTO = ActivityConverter.toDTO(tmp);
                list.add(activityDTO);
        }
        PageResult<ActivityDTO> result = new PageResult<ActivityDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public ActivityDTO findById(ActivityDTO dto) {
		ActivityPO ActivityPO = activityReadManage.findById(ActivityConverter.toPO(dto));
		return ActivityConverter.toDTO(ActivityPO);
	}

	@Override
	public List<ActivityDTO> findAll(ActivityDTO dto) {
		List<ActivityPO> list = activityReadManage.findAll(ActivityConverter.toPO(dto));
		return ActivityConverter.toDTO(list);
	}
	/**
	 * 根据商品id查询该商品对应的活动是否过期
	 * @param merchantProdId
	 * @return
	 */
	@Override
	public boolean activityByMerchantProdIdAndDate(Long merchantProdId) {
		return activityReadManage.activityByMerchantProdIdAndDate(new Date(), merchantProdId);
	}
}
	