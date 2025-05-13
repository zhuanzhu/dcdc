package com.egeo.components.promotion.business.impl;
	

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.promotion.business.BannerManage;
import com.egeo.components.promotion.dto.BannerDTO;
import com.egeo.components.promotion.facade.BannerFacade;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;

@Service("banner")
public class BannerManageImpl implements BannerManage{

	
	@Resource(name="bannerFacade")
	private BannerFacade bannerFacade;

	@Override
	public BannerDTO findBannerById(BannerDTO dto) {
		return bannerFacade.findBannerById(dto);
	}

	@Override
	public PageResult<BannerDTO> findBannerOfPage(BannerDTO dto, Pagination page) {
		return bannerFacade.findBannerOfPage(dto, page);
	}

	@Override
	public List<BannerDTO> findBannerAll(BannerDTO dto) {
		//添加当前时间
		dto.setTime(new Date());
		return bannerFacade.findBannerAll(dto);
	}

	@Override
	public Long insertBannerWithTx(BannerDTO dto) {
		if(dto.getType() == 2){
			throw new BusinessException("轮播图跳活动功能暂为开放");
		}
		return bannerFacade.insertBannerWithTx(dto);
	}

	@Override
	public int updateBannerWithTx(BannerDTO dto) {
		if(EmptyUtil.isEmpty(dto.getName())){
			throw new BusinessException("Banner名称不能为空");
		}
		if(EmptyUtil.isEmpty(dto.getType())){
			throw new BusinessException("Banner类型不能为空");
		}
		if(EmptyUtil.isEmpty(dto.getStartTime())){
			throw new BusinessException("Banner开始时间不能为空");
		}
		if(EmptyUtil.isEmpty(dto.getFinishTime())){
			throw new BusinessException("Banner结束时间不能为空");
		}
		if(dto.getType() == 1){
			//为公会通判断下面活动列表不能为空
			if(dto.getPlatformId().equals(6L)){
				if(EmptyUtil.isEmpty(dto.getActivityId())){
					throw new BusinessException("活动列表不能为空");
				}
			}
			
			if(EmptyUtil.isEmpty(dto.getMerchantProdId())){
				throw new BusinessException("商品列表不能为空");
			}
		}else if(dto.getType() == 2){
			//为公会通判断下面活动列表不能为空
			if(dto.getPlatformId().equals(6L)){
				if(EmptyUtil.isEmpty(dto.getActivityId())){
					throw new BusinessException("活动列表不能为空");
				}
			}
		}else if(dto.getType() == 3){
			if(EmptyUtil.isEmpty(dto.getPath())){
				throw new BusinessException("URL链接不能为空");
			}
		}
		if(EmptyUtil.isEmpty(dto.getSortValue())){
			//查询Banner最大排序值
			Integer maxSortValue = bannerFacade.maxSortValue();
			dto.setSortValue(maxSortValue);
		}
		if(dto.getId() == null){
			bannerFacade.insertBannerWithTx(dto);
			return 1;
		}else{
			return bannerFacade.updateBannerWithTx(dto);
		}
		
	}

	@Override
	public int deleteBannerWithTx(BannerDTO dto) {
		return bannerFacade.deleteBannerWithTx(dto);
	}


}
	