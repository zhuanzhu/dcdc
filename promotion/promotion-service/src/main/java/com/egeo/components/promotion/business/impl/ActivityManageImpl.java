package com.egeo.components.promotion.business.impl;
	

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.product.dto.MerchantProdSalesRecordDTO;
import com.egeo.components.product.dto.MerchantProductDTO;
import com.egeo.components.product.dto.PictureDTO;
import com.egeo.components.product.dto.ProductPictureDTO;
import com.egeo.components.promotion.business.ActivityManage;
import com.egeo.components.promotion.converter.ActivityConverter;
import com.egeo.components.promotion.converter.ActivityMerchantProdConverter;
import com.egeo.components.promotion.dto.ActivityDTO;
import com.egeo.components.promotion.dto.ActivityMerchantProdDTO;
import com.egeo.components.promotion.facade.ActivityFacade;
import com.egeo.components.promotion.vo.ActivityMerchantProd;
import com.egeo.components.promotion.vo.ActivityMerchantProdVO;
import com.egeo.components.promotion.vo.ActivityVO;
import com.egeo.components.promotion.vo.MerchantProd;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("activity")
public class ActivityManageImpl implements ActivityManage{

	
	@Resource(name="activityFacade")
	private ActivityFacade activityFacade;

	@Override
	public PageResult<ActivityDTO> findPageActivity(Pagination page, ActivityVO activityVO) {
		return activityFacade.findPageActivity(page, ActivityConverter.toDTO(activityVO));
	}

	@Override
	public Long saveActivity(ActivityVO activityVO) {
		return activityFacade.saveActivity(ActivityConverter.toDTO(activityVO));
	}

	@Override
	public Integer delete(ActivityVO activityVO) {
		Integer rows = activityFacade.delete(ActivityConverter.toDTO(activityVO));
		//判断活动是否删除成功
		if(rows != 0){
			//根据活动id删除活动商品
			Integer row = activityFacade.deleteActivityMerchantByActivityId(activityVO.getId());
		}
		return rows;
	}

	@Override
	public Long updateActivity(ActivityVO activityVO) {
		Long activityId = activityFacade.updateActivity(ActivityConverter.toDTO(activityVO));
		return activityId;
	}

	@Override
	public ActivityVO findById(ActivityVO activityVO) {
		ActivityDTO activityDTO = activityFacade.findById(ActivityConverter.toDTO(activityVO));
		return ActivityConverter.toVO(activityDTO);
	}

	@Override
	public String saveActivityMerchantProd(List<ActivityMerchantProdVO> lists) {
		for (ActivityMerchantProdVO activityMerchantProdVO : lists) {
			activityFacade.updateActivityMerchantProd(ActivityMerchantProdConverter.toDTO(activityMerchantProdVO));
		}
		return "添加活动商品成功";
	}

	@Override
	public PageResult<ActivityMerchantProd> PageActivityMerchantProd(Pagination page, ActivityVO activityVO) {
		Date d=new Date();   
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		PageResult<ActivityMerchantProd> pageResult = new PageResult<ActivityMerchantProd>();
		List<ActivityMerchantProd> activityMerchantProdList = new ArrayList<ActivityMerchantProd>();
		ActivityDTO dto = ActivityConverter.toDTO(activityVO);
		//查询未过期的活动
		//添加当前时间
		dto.setTime(new Date());
		PageResult<ActivityDTO> activityResult = activityFacade.findPageActivity(page, dto);
		List<ActivityDTO> activityList = activityResult.getList();
		for (ActivityDTO activityDTO : activityList) {
			ActivityMerchantProd activityMerchantProd = new ActivityMerchantProd();
			activityMerchantProd.setId(activityDTO.getId());
			activityMerchantProd.setActivityName(activityDTO.getName());
			activityMerchantProd.setSortValue(activityDTO.getSortValue());
			long time = activityDTO.getFinishTime().getTime();
			long time2 = d.getTime();
			activityMerchantProd.setTime(time - time2);
			//根据活动id查询活动商品
			List<MerchantProd> merchantProdList = new ArrayList<MerchantProd>();
			ActivityMerchantProdDTO activityMerchantProdDTO = new ActivityMerchantProdDTO();
			activityMerchantProdDTO.setActivityId(activityDTO.getId());
			List<ActivityMerchantProdDTO> ActivityMerchantProdList = activityFacade.activityMerchantProdFindAll(activityMerchantProdDTO);
			for (ActivityMerchantProdDTO activityMerchantProdDTO2 : ActivityMerchantProdList) {
				MerchantProd merchantProd = new MerchantProd();
				//根据商品id查询商品信息
				MerchantProductDTO merchantProductDTO = new MerchantProductDTO();
				merchantProductDTO.setId(activityMerchantProdDTO2.getMerchantProdId());
				MerchantProductDTO merchantProductDTO2 = activityFacade.merchantProductFindById(merchantProductDTO);
				//判断商品是否上架、如果是上架状态则添加到活动中
				if(merchantProductDTO2.getStatus() == 3){
					merchantProd.setId(merchantProductDTO2.getId());
					merchantProd.setName(merchantProductDTO2.getName());
					merchantProd.setMarketPrice(merchantProductDTO2.getMarketPrice());
					//根据产品id查询列表图片
					ProductPictureDTO productPictureDTO = new ProductPictureDTO();
					List<ProductPictureDTO> list = activityFacade.ProductPictureFindAll(productPictureDTO);
					for (ProductPictureDTO productPictureDTO2 : list) {
						PictureDTO pictureDTO = new PictureDTO();
						pictureDTO.setId(productPictureDTO2.getPictureId());
						PictureDTO pictureDTO2 = activityFacade.PictureFindById(pictureDTO);
						if(pictureDTO2.getType() == 1){
							merchantProd.setPicture(pictureDTO2.getUrl());
							break;
						}
					}
					
					//根据商品id查询以售数量
					MerchantProdSalesRecordDTO merchantProdSalesRecordDTO = new MerchantProdSalesRecordDTO();
					List<MerchantProdSalesRecordDTO> MerchantProdSalesRecordList = activityFacade.merchantProdSalesRecordFindAll(merchantProdSalesRecordDTO);
					
					Long salesVolume = 0L;
					for (MerchantProdSalesRecordDTO merchantProdSalesRecordDTO2 : MerchantProdSalesRecordList) {
						salesVolume = salesVolume + merchantProdSalesRecordDTO2.getSalesVolume();
					}
					
					merchantProd.setSalesVolume(salesVolume + merchantProductDTO2.getSoldBase());
					merchantProdList.add(merchantProd);
				}
				
			}
			activityMerchantProd.setMerchantProdList(merchantProdList);
			activityMerchantProdList.add(activityMerchantProd);
		}
		pageResult.setList(activityMerchantProdList);
		return pageResult;
	}

	@Override
	public List<ActivityVO> findAll(ActivityVO activityVO) {
		List<ActivityDTO> list = activityFacade.findAll(ActivityConverter.toDTO(activityVO));
		return ActivityConverter.toVO(list);
	}
	/**
	 * 查询指定第几个有效活动
	 * @param pages
	 * @return
	 */
	@Override
	public ActivityMerchantProd activityMerchantProdByPages(Integer pages,Long platformId) {
		ActivityMerchantProd activityMerchantProd = null;
		//查询指定第几个有效活动及商品id
		List<ActivityMerchantProdDTO> list = activityFacade.activityMerchantProdByPages(new Date(), pages,platformId);
		if(list.size() > 0){
			activityMerchantProd = new ActivityMerchantProd();
			activityMerchantProd.setId(list.get(0).getId());
			activityMerchantProd.setActivityName(list.get(0).getActivityName());
			activityMerchantProd.setSortValue(list.get(0).getSortValue());
			
			long time = list.get(0).getFinishTime().getTime();
			long time2 = new Date().getTime();
			activityMerchantProd.setTime(time - time2);
			
			List<Long> merchantProdId = new  ArrayList<Long>();
			for (ActivityMerchantProdDTO activityMerchantProdDTO : list) {
				merchantProdId.add(activityMerchantProdDTO.getMerchantProdId());
			}
			//根据商品id集合查询上架商品的名称、列表图片、以售数量、市场价格、销售价格
			List<MerchantProd> merchantProdList = merchantProdListByIds(merchantProdId,platformId);
			activityMerchantProd.setMerchantProdList(merchantProdList);
		}
		//因为指定活动，活动只可能有一个
		return activityMerchantProd;
	}
	/**
	 * 根据商品id集合查询上架商品的名称、列表图片、以售数量、市场价格、销售价格
	 * @param merchantProdId
	 * @param platformId
	 * @return
	 */
	private List<MerchantProd> merchantProdListByIds(List<Long> merchantProdId, Long platformId) {
		return activityFacade.merchantProdListByIds(merchantProdId,platformId);
	}
	


}
	