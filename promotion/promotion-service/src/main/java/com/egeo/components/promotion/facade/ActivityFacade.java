package com.egeo.components.promotion.facade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.product.dto.PictureDTO;
import com.egeo.components.product.client.MerchantProductClient;
import com.egeo.components.product.client.PictureClient;
import com.egeo.components.product.client.ProductPictureClient;
import com.egeo.components.product.dto.MerchantProdSalesRecordDTO;
import com.egeo.components.product.dto.MerchantProductDTO;
import com.egeo.components.product.dto.ProductPictureDTO;
import com.egeo.components.promotion.dto.ActivityDTO;
import com.egeo.components.promotion.dto.ActivityMerchantProdDTO;
import com.egeo.components.promotion.service.read.ActivityMerchantProdReadService;
import com.egeo.components.promotion.service.read.ActivityReadService;
import com.egeo.components.promotion.service.write.ActivityMerchantProdWriteService;
import com.egeo.components.promotion.service.write.ActivityWriteService;
import com.egeo.components.promotion.vo.MerchantProd;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class ActivityFacade {
	
	@Autowired
	private ActivityReadService activityReadService;
	
	@Autowired
	private ActivityWriteService activityWriteService;
	
	@Autowired
	private ActivityMerchantProdWriteService activityMerchantProdWriteService;
	
	@Autowired
	private ActivityMerchantProdReadService activityMerchantProdReadService;
	
	@Autowired
	private MerchantProductClient merchantProductReadService;
	
	@Autowired
	private ProductPictureClient productPictureReadService;
	
	@Autowired
	private PictureClient pictureReadService;
	

	public PageResult<ActivityDTO> findPageActivity(Pagination page, ActivityDTO dto) {
		return activityReadService.findPageActivity(page, dto);
	}

	public Long saveActivity(ActivityDTO dto) {
		return activityWriteService.saveActivityWithTx(dto);
	}

	public Integer delete(ActivityDTO dto) {
		return activityWriteService.deleteWithTx(dto);
	}

	public Long updateActivity(ActivityDTO dto) {
		return activityWriteService.updateActivity(dto);
	}

	public Long updateActivityMerchantProd(ActivityMerchantProdDTO dto) {
		return activityMerchantProdWriteService.updateActivityMerchantProdWithTx(dto);
	}

	public ActivityDTO findById(ActivityDTO dto) {
		return activityReadService.findById(dto);
	}
	
	public List<ActivityMerchantProdDTO> activityMerchantProdFindAll(ActivityMerchantProdDTO dto){
		
		return activityMerchantProdReadService.findAll(dto);
		
	}
	
	public MerchantProductDTO merchantProductFindById(MerchantProductDTO dto){
		
		return merchantProductReadService.findMerchantProductById(dto);
		
	}
	
	public List<ProductPictureDTO> ProductPictureFindAll(ProductPictureDTO dto){
		
		return productPictureReadService.findProductPictureAll(dto);
		
	}
	
	public PictureDTO PictureFindById(PictureDTO dto){
		
		return pictureReadService.findById(dto);
		
	}
	
	public List<MerchantProdSalesRecordDTO> merchantProdSalesRecordFindAll(MerchantProdSalesRecordDTO dto){
		return null;
		
	}

	public List<ActivityDTO> findAll(ActivityDTO dto) {
		return activityReadService.findAll(dto);
	}
	/**
	 * 查询指定第几个有效活动及商品id
	 * @param date
	 * @param pages
	 * @return
	 */
	public List<ActivityMerchantProdDTO> activityMerchantProdByPages(Date date, Integer pages,Long platformId) {
		return activityMerchantProdReadService.activityMerchantProdByPages(date, pages,platformId);
	}
	/**
	 * 根据商品id集合查询上架商品的名称、列表图片、以售数量、市场价格、销售价格
	 * @param merchantProdId
	 * @return
	 */
	public List<MerchantProd> merchantProdListByIds(List<Long> merchantProdId,Long platformId) {
		List<MerchantProd> MerchantProdList = new ArrayList<>();
		/*List<MerchantProductDTO> list = merchantProductReadService.merchantProdListByIds(merchantProdId,platformId);
		for (MerchantProductDTO merchantProductDTO : list) {
			MerchantProd merchantProd = new MerchantProd();
			merchantProd.setId(merchantProductDTO.getId());
			merchantProd.setName(merchantProductDTO.getName());
			//此处已售数量是用已售基数接得
			merchantProd.setSalesVolume(merchantProductDTO.getSoldBase());
			merchantProd.setPicture(merchantProductDTO.getUrl());
			merchantProd.setMarketPrice(merchantProductDTO.getMarketPrice());
			merchantProd.setSalePrice(merchantProductDTO.getSalePrice());
			MerchantProdList.add(merchantProd);
		}*/
		return MerchantProdList;
	}
	/**
	 * 根据活动id删除活动商品
	 * @param activityId
	 * @return
	 */
	public Integer deleteActivityMerchantByActivityId(Long activityId) {
		return activityMerchantProdWriteService.deleteActivityMerchantByActivityIdWithTx(activityId);
	}


}
	