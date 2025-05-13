package com.egeo.components.product.business.impl;
	

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.product.business.MerchantProdCauseManage;
import com.egeo.components.product.dto.CommodityProductUnitDTO;
import com.egeo.components.product.dto.MerchantProdCauseDTO;
import com.egeo.components.product.dto.MerchantProdClientDTO;
import com.egeo.components.product.dto.MerchantProdPictureDTO;
import com.egeo.components.product.dto.MerchantProductCompanyDTO;
import com.egeo.components.product.dto.MerchantProductDTO;
import com.egeo.components.product.dto.ProductUnitDTO;
import com.egeo.components.product.dto.SellPlatformMerchantProdDTO;
import com.egeo.components.product.dto.SellPlatformStandardUnitDTO;
import com.egeo.components.product.dto.SellPlatformStandardUnitRecordDTO;
import com.egeo.components.product.dto.SkuDTO;
import com.egeo.components.product.dto.StandardProductUnitDTO;
import com.egeo.components.product.dto.StandardUnitClientDTO;
import com.egeo.components.product.dto.StandardUnitClientRecordDTO;
import com.egeo.components.product.dto.StandardUnitCompanyDTO;
import com.egeo.components.product.dto.StandardUnitCompanyRecordDTO;
import com.egeo.components.product.dto.StandardUnitDTO;
import com.egeo.components.product.dto.StandardUnitDescribeDTO;
import com.egeo.components.product.dto.StandardUnitDescribeRecordDTO;
import com.egeo.components.product.dto.StandardUnitPictureDTO;
import com.egeo.components.product.dto.StandardUnitPictureRecordDTO;
import com.egeo.components.product.dto.StandardUnitRecordDTO;
import com.egeo.components.product.enums.SUConstant;
import com.egeo.components.product.facade.CommodityProductUnitFacade;
import com.egeo.components.product.facade.MerchantProdCauseFacade;
import com.egeo.components.product.facade.MerchantProdClientFacade;
import com.egeo.components.product.facade.MerchantProdPictureFacade;
import com.egeo.components.product.facade.MerchantProductCompanyFacade;
import com.egeo.components.product.facade.MerchantProductFacade;
import com.egeo.components.product.facade.ProductUnitFacade;
import com.egeo.components.product.facade.SellPlatformMerchantProdFacade;
import com.egeo.components.product.facade.SellPlatformStandardUnitFacade;
import com.egeo.components.product.facade.SellPlatformStandardUnitRecordFacade;
import com.egeo.components.product.facade.SkuFacade;
import com.egeo.components.product.facade.StandardProductUnitFacade;
import com.egeo.components.product.facade.StandardUnitClientFacade;
import com.egeo.components.product.facade.StandardUnitClientRecordFacade;
import com.egeo.components.product.facade.StandardUnitCompanyFacade;
import com.egeo.components.product.facade.StandardUnitCompanyRecordFacade;
import com.egeo.components.product.facade.StandardUnitDescribeFacade;
import com.egeo.components.product.facade.StandardUnitDescribeRecordFacade;
import com.egeo.components.product.facade.StandardUnitFacade;
import com.egeo.components.product.facade.StandardUnitPictureFacade;
import com.egeo.components.product.facade.StandardUnitPictureRecordFacade;
import com.egeo.components.product.facade.StandardUnitRecordFacade;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.str.StringUtils;

@Service("merchantProdCause")
public class MerchantProdCauseManageImpl implements MerchantProdCauseManage{

	
	@Resource(name="merchantProdCauseFacade")
	private MerchantProdCauseFacade merchantProdCauseFacade;
	
	@Resource(name="merchantProductFacade")
	private MerchantProductFacade merchantProductFacade;
	
	@Resource(name="standardUnitFacade")
	private StandardUnitFacade standardUnitFacade;
	
	@Resource(name="merchantProdPictureFacade")
	private MerchantProdPictureFacade merchantProdPictureFacade;
	
	@Resource(name="standardUnitPictureFacade")
	private StandardUnitPictureFacade standardUnitPictureFacade;
	
	@Resource(name="standardUnitRecordFacade")
	private StandardUnitRecordFacade standardUnitRecordFacade;
	
	@Resource(name="standardProductUnitFacade")
	private StandardProductUnitFacade standardProductUnitFacade;
	
	@Resource(name="merchantProdClientFacade")
	private MerchantProdClientFacade merchantProdClientFacade;
	
	@Resource(name="standardUnitClientFacade")
	private StandardUnitClientFacade standardUnitClientFacade;
	
	@Resource(name="merchantProductCompanyFacade")
	private MerchantProductCompanyFacade merchantProductCompanyFacade;
	
	@Resource(name="standardUnitCompanyFacade")
	private StandardUnitCompanyFacade standardUnitCompanyFacade;
	
	@Resource(name="sellPlatformStandardUnitFacade")
	private SellPlatformStandardUnitFacade sellPlatformStandardUnitFacade;
	
	@Resource(name="sellPlatformMerchantProdFacade")
	private SellPlatformMerchantProdFacade sellPlatformMerchantProdFacade;
	
	@Resource(name="standardUnitDescribeFacade")
	private StandardUnitDescribeFacade standardUnitDescribeFacade;
	
	@Resource(name="standardUnitPictureRecordFacade")
	private StandardUnitPictureRecordFacade standardUnitPictureRecordFacade;
	
	@Resource(name="standardUnitClientRecordFacade")
	private StandardUnitClientRecordFacade standardUnitClientRecordFacade;
	
	@Resource(name="standardUnitCompanyRecordFacade")
	private StandardUnitCompanyRecordFacade standardUnitCompanyRecordFacade;
	
	@Resource(name="sellPlatformStandardUnitRecordFacade")
	private SellPlatformStandardUnitRecordFacade sellPlatformStandardUnitRecordFacade;
	
	@Resource(name="standardUnitDescribeRecordFacade")
	private StandardUnitDescribeRecordFacade standardUnitDescribeRecordFacade;
	
	@Resource(name="productUnitFacade")
	private ProductUnitFacade productUnitFacade;
	
	@Resource(name="commodityProductUnitFacade")
	private CommodityProductUnitFacade commodityProductUnitFacade;
	
	@Resource(name="skuFacade")
	private SkuFacade skuFacade;

	@Override
	public MerchantProdCauseDTO findMerchantProdCauseById(MerchantProdCauseDTO dto) {
		return merchantProdCauseFacade.findMerchantProdCauseById(dto);
	}

	@Override
	public PageResult<MerchantProdCauseDTO> findMerchantProdCauseOfPage(MerchantProdCauseDTO dto, Pagination page) {
		return merchantProdCauseFacade.findMerchantProdCauseOfPage(dto, page);
	}

	@Override
	public List<MerchantProdCauseDTO> findMerchantProdCauseAll(MerchantProdCauseDTO dto) {
		return merchantProdCauseFacade.findMerchantProdCauseAll(dto);
	}

	@Override
	public Long insertMerchantProdCauseWithTx(MerchantProdCauseDTO dto) {
		return merchantProdCauseFacade.insertMerchantProdCauseWithTx(dto);
	}

	@Override
	public int updateMerchantProdCauseWithTx(MerchantProdCauseDTO dto) {
		return merchantProdCauseFacade.updateMerchantProdCauseWithTx(dto);
	}

	@Override
	public int deleteMerchantProdCauseWithTx(MerchantProdCauseDTO dto) {
		return merchantProdCauseFacade.deleteMerchantProdCauseWithTx(dto);
	}
	/**
	 * 审核是否通过
	 * @param vo
	 * @param req
	 * @return
	 */
	@Override
	public int isPass(MerchantProdCauseDTO dto) {
		int i = 0;
		//审核是否通过 ：0、否 1、是
		if(dto.getType() == 1){
			//修改su草稿信息状态
			i = pass(dto.getMerchantProdId());
		}else{
			//添加失败原因记录
			insertMerchantProdCauseWithTx(dto);
		}
		return i;
	}
	/**
	 * 通过
	 * @return
	 */
	private int pass(Long merchantProdId){
		MerchantProductDTO merchantProductDTO = new MerchantProductDTO();
		//修改su草稿状态为已上架
		merchantProductDTO.setStatus(SUConstant.SU_STATUS_RECEIVED_GOODS.getStatus());
		merchantProductDTO.setId(merchantProdId);
		int i = merchantProductFacade.updateStatus(merchantProductDTO);
		
		//同步su表和su记录表
		synchronizationStandardUnitAndStandardUnitRecord(merchantProdId);
		return i;
	}
	/**
	 * 以弃用、没有进行事务管理
	 * @param merchantProdId
	 */
	private void synchronizationStandardUnitAndStandardUnitRecord(Long merchantProdId){
		Long standardUnitRecordId = null;
		//根据su草稿id查询su草稿信息
		MerchantProductDTO merchantProductDTO = new MerchantProductDTO();
		merchantProductDTO.setId(merchantProdId);
		MerchantProductDTO merchantProductDTO2 = merchantProductFacade.findMerchantProductById(merchantProductDTO);
		
		//根据spuid查询spu信息
		StandardProductUnitDTO standardProductUnitDTO = new StandardProductUnitDTO();
		standardProductUnitDTO.setId(merchantProductDTO2.getStandardProductUnitId());
		StandardProductUnitDTO standardProductUnit = standardProductUnitFacade.findStandardProductUnitById(standardProductUnitDTO);
		//根据su草稿id查询是否有su信息
		StandardUnitDTO standardUnitDTO = new StandardUnitDTO();
		standardUnitDTO.setId(merchantProdId);
		StandardUnitDTO standardUnitDTO2 = standardUnitFacade.findStandardUnitById(standardUnitDTO);
		if(EmptyUtil.isEmpty(standardUnitDTO2)){
			
			//没有su信息进行添加操作
			StandardUnitDTO standardUnit = new StandardUnitDTO();
			
			//根据spuid查询所有su的条数
			int countRecord = standardUnitFacade.countRecord(merchantProductDTO2.getStandardProductUnitId());
			
			standardUnit.setId(merchantProductDTO2.getId());
			standardUnit.setMerchantProductSerialNumber(standardProductUnit.getProductSerialNumber() + StringUtils.skuSerialNumber(countRecord + 1));	
			standardUnit.setMerchantId(merchantProductDTO2.getMerchantId());	
			standardUnit.setStandardProductUnitId(merchantProductDTO2.getStandardProductUnitId());
			standardUnit.setMerchantCateTreeNodeId(merchantProductDTO2.getMerchantCateTreeNodeId());	
			standardUnit.setMerchantSeriesId(merchantProductDTO2.getMerchantSeriesId());	
			standardUnit.setName(merchantProductDTO2.getName());	
			standardUnit.setAlias(merchantProductDTO2.getAlias());	
			standardUnit.setIsVisible(merchantProductDTO2.getIsVisible());	
			standardUnit.setSubtitle(merchantProductDTO2.getSubtitle());	
			standardUnit.setSupplierId(merchantProductDTO2.getSupplierId());	
			standardUnit.setType(merchantProductDTO2.getType());	
			standardUnit.setRemark(merchantProductDTO2.getRemark());	
			standardUnit.setSalePrice(merchantProductDTO2.getSalePrice());	
			standardUnit.setSaleTaxRate(merchantProductDTO2.getSaleTaxRate());	
			standardUnit.setReturnDays(merchantProductDTO2.getReturnDays());	
			standardUnit.setReplacementDays(merchantProductDTO2.getReplacementDays());	
			standardUnit.setGuaranteeDays(merchantProductDTO2.getGuaranteeDays());	
			standardUnit.setIsVatInvoice(merchantProductDTO2.getIsVatInvoice());	
			standardUnit.setIsVipCard(merchantProductDTO2.getIsVipCard());	
			standardUnit.setIsEnableShelflife(merchantProductDTO2.getIsEnableShelflife());	
			standardUnit.setShelflifeDays(merchantProductDTO2.getShelflifeDays());	
			standardUnit.setIsVendible(merchantProductDTO2.getIsVendible());	
			standardUnit.setGrossWeight(merchantProductDTO2.getGrossWeight());	
			standardUnit.setNetWeight(merchantProductDTO2.getNetWeight());	
			standardUnit.setCode(merchantProductDTO2.getCode());	
			standardUnit.setMerchantBrandId(merchantProductDTO2.getMerchantBrandId());	
			standardUnit.setMarketPrice(merchantProductDTO2.getMarketPrice());	
			standardUnit.setPromotionPrice(merchantProductDTO2.getPromotionPrice());	
			standardUnit.setStatus(merchantProductDTO2.getStatus());	
			standardUnit.setSaleWay(merchantProductDTO2.getSaleWay());	
			standardUnit.setSoldBase(merchantProductDTO2.getSoldBase());	
			standardUnit.setFreightExplain(merchantProductDTO2.getFreightExplain());	
			standardUnit.setShipmentsExplain(merchantProductDTO2.getShipmentsExplain());	
			standardUnit.setFreightTemplateId(merchantProductDTO2.getFreightTemplateId());	
			standardUnit.setCreateTime(merchantProductDTO2.getCreateTime());	
			standardUnit.setUpdateTime(merchantProductDTO2.getUpdateTime());	
			standardUnit.setPlatformId(merchantProductDTO2.getPlatformId());
			standardUnit.setProductCategory(merchantProductDTO2.getProductCategory());
			Long standardUnitId = standardUnitFacade.insertStandardUnitWithTx(standardUnit);
			
			//根据su草稿id查询所有草稿pu信息
			ProductUnitDTO productUnitDTO = new ProductUnitDTO();
			productUnitDTO.setMerchantProductId(merchantProdId);
			List<ProductUnitDTO> productUnitList = productUnitFacade.findProductUnitAll(productUnitDTO);
			for (ProductUnitDTO productUnitDTO2 : productUnitList) {
					CommodityProductUnitDTO commodityProductUnitDTO = new CommodityProductUnitDTO();
					//通过skuid查询sku信息
					SkuDTO skuDTO = new SkuDTO();
					skuDTO.setId(productUnitDTO2.getSkuId());
					SkuDTO skuDTO2 = skuFacade.findSkuById(skuDTO);
					
					//根据skuid查询pu总条数
					int count = commodityProductUnitFacade.countRecord(skuDTO2.getId());
					commodityProductUnitDTO.setProductUnitId(productUnitDTO2.getId());
					commodityProductUnitDTO.setProductUnitSerialNumber(skuDTO2.getSkuSerialNumber() + StringUtils.skuSerialNumber(count + 1));
					commodityProductUnitDTO.setSkuId(productUnitDTO2.getSkuId());	
					commodityProductUnitDTO.setStandardUnitId(merchantProdId);	
					commodityProductUnitDTO.setName(productUnitDTO2.getName());	
					commodityProductUnitDTO.setRemark(productUnitDTO2.getRemark());	
					commodityProductUnitDTO.setSalePrice(productUnitDTO2.getSalePrice());	
					commodityProductUnitDTO.setStatus(productUnitDTO2.getStatus());	
					commodityProductUnitDTO.setIsVendible(productUnitDTO2.getIsVendible());	
					commodityProductUnitDTO.setCode(productUnitDTO2.getCode());	
					commodityProductUnitDTO.setPuPicUrl(productUnitDTO2.getPuPicUrl());	
					commodityProductUnitDTO.setPlatformId(productUnitDTO2.getPlatformId());	
					commodityProductUnitFacade.insertCommodityProductUnitWithTx(commodityProductUnitDTO);
			}
			
			//同步保存su记录表
			StandardUnitRecordDTO standardUnitRecordDTO = new StandardUnitRecordDTO();
			standardUnitRecordDTO.setMerchantProductSerialNumber(standardUnit.getMerchantProductSerialNumber());	
			standardUnitRecordDTO.setMerchantId(standardUnit.getMerchantId());	
			standardUnitRecordDTO.setStandardUnitId(standardUnitId);
			standardUnitRecordDTO.setStandardProductUnitId(standardUnit.getStandardProductUnitId());
			standardUnitRecordDTO.setMerchantCateTreeNodeId(standardUnit.getMerchantCateTreeNodeId());	
			standardUnitRecordDTO.setMerchantSeriesId(standardUnit.getMerchantSeriesId());	
			standardUnitRecordDTO.setName(standardUnit.getName());	
			standardUnitRecordDTO.setAlias(standardUnit.getAlias());	
			standardUnitRecordDTO.setIsVisible(standardUnit.getIsVisible());	
			standardUnitRecordDTO.setSubtitle(standardUnit.getSubtitle());	
			standardUnitRecordDTO.setSupplierId(standardUnit.getSupplierId());	
			standardUnitRecordDTO.setType(standardUnit.getType());	
			standardUnitRecordDTO.setRemark(standardUnit.getRemark());	
			standardUnitRecordDTO.setSalePrice(standardUnit.getSalePrice());	
			standardUnitRecordDTO.setSaleTaxRate(standardUnit.getSaleTaxRate());	
			standardUnitRecordDTO.setReturnDays(standardUnit.getReturnDays());	
			standardUnitRecordDTO.setReplacementDays(standardUnit.getReplacementDays());	
			standardUnitRecordDTO.setGuaranteeDays(standardUnit.getGuaranteeDays());	
			standardUnitRecordDTO.setIsVatInvoice(standardUnit.getIsVatInvoice());	
			standardUnitRecordDTO.setIsVipCard(standardUnit.getIsVipCard());	
			standardUnitRecordDTO.setIsEnableShelflife(standardUnit.getIsEnableShelflife());	
			standardUnitRecordDTO.setShelflifeDays(standardUnit.getShelflifeDays());	
			standardUnitRecordDTO.setIsVendible(standardUnit.getIsVendible());	
			standardUnitRecordDTO.setGrossWeight(standardUnit.getGrossWeight());	
			standardUnitRecordDTO.setNetWeight(standardUnit.getNetWeight());	
			standardUnitRecordDTO.setCode(standardUnit.getCode());	
			standardUnitRecordDTO.setMerchantBrandId(standardUnit.getMerchantBrandId());	
			standardUnitRecordDTO.setMarketPrice(standardUnit.getMarketPrice());	
			standardUnitRecordDTO.setPromotionPrice(standardUnit.getPromotionPrice());	
			standardUnitRecordDTO.setStatus(standardUnit.getStatus());	
			standardUnitRecordDTO.setSaleWay(standardUnit.getSaleWay());	
			standardUnitRecordDTO.setSoldBase(standardUnit.getSoldBase());	
			standardUnitRecordDTO.setFreightExplain(standardUnit.getFreightExplain());	
			standardUnitRecordDTO.setShipmentsExplain(standardUnit.getShipmentsExplain());	
			standardUnitRecordDTO.setFreightTemplateId(standardUnit.getFreightTemplateId());	
			standardUnitRecordDTO.setPlatformId(standardUnit.getPlatformId());
			standardUnitRecordDTO.setProductCategory(standardUnit.getProductCategory());
			standardUnitRecordId = standardUnitRecordFacade.insertStandardUnitRecordWithTx(standardUnitRecordDTO);
		}else{
			//有su信息进行更新操作
			StandardUnitDTO standardUnit = new StandardUnitDTO();
			standardUnit.setId(merchantProductDTO2.getId());
			standardUnit.setMerchantProductSerialNumber(merchantProductDTO2.getMerchantProductSerialNumber());	
			standardUnit.setMerchantId(merchantProductDTO2.getMerchantId());	
			standardUnit.setStandardProductUnitId(merchantProductDTO2.getStandardProductUnitId());
			standardUnit.setMerchantCateTreeNodeId(merchantProductDTO2.getMerchantCateTreeNodeId());	
			standardUnit.setMerchantSeriesId(merchantProductDTO2.getMerchantSeriesId());	
			standardUnit.setName(merchantProductDTO2.getName());	
			standardUnit.setAlias(merchantProductDTO2.getAlias());	
			standardUnit.setIsVisible(merchantProductDTO2.getIsVisible());	
			standardUnit.setSubtitle(merchantProductDTO2.getSubtitle());	
			standardUnit.setSupplierId(merchantProductDTO2.getSupplierId());	
			standardUnit.setType(merchantProductDTO2.getType());	
			standardUnit.setRemark(merchantProductDTO2.getRemark());	
			standardUnit.setSalePrice(merchantProductDTO2.getSalePrice());	
			standardUnit.setSaleTaxRate(merchantProductDTO2.getSaleTaxRate());	
			standardUnit.setReturnDays(merchantProductDTO2.getReturnDays());	
			standardUnit.setReplacementDays(merchantProductDTO2.getReplacementDays());	
			standardUnit.setGuaranteeDays(merchantProductDTO2.getGuaranteeDays());	
			standardUnit.setIsVatInvoice(merchantProductDTO2.getIsVatInvoice());	
			standardUnit.setIsVipCard(merchantProductDTO2.getIsVipCard());	
			standardUnit.setIsEnableShelflife(merchantProductDTO2.getIsEnableShelflife());	
			standardUnit.setShelflifeDays(merchantProductDTO2.getShelflifeDays());	
			standardUnit.setIsVendible(merchantProductDTO2.getIsVendible());	
			standardUnit.setGrossWeight(merchantProductDTO2.getGrossWeight());	
			standardUnit.setNetWeight(merchantProductDTO2.getNetWeight());	
			standardUnit.setCode(merchantProductDTO2.getCode());	
			standardUnit.setMerchantBrandId(merchantProductDTO2.getMerchantBrandId());	
			standardUnit.setMarketPrice(merchantProductDTO2.getMarketPrice());	
			standardUnit.setPromotionPrice(merchantProductDTO2.getPromotionPrice());	
			standardUnit.setStatus(merchantProductDTO2.getStatus());	
			standardUnit.setSaleWay(merchantProductDTO2.getSaleWay());	
			standardUnit.setSoldBase(merchantProductDTO2.getSoldBase());	
			standardUnit.setFreightExplain(merchantProductDTO2.getFreightExplain());	
			standardUnit.setShipmentsExplain(merchantProductDTO2.getShipmentsExplain());	
			standardUnit.setFreightTemplateId(merchantProductDTO2.getFreightTemplateId());	
			standardUnit.setCreateTime(merchantProductDTO2.getCreateTime());	
			standardUnit.setUpdateTime(merchantProductDTO2.getUpdateTime());	
			standardUnit.setPlatformId(merchantProductDTO2.getPlatformId());
			standardUnit.setProductCategory(merchantProductDTO2.getProductCategory());
			standardUnitFacade.updateStandardUnitWithTx(standardUnit);
			
			//同步保存su记录表
			StandardUnitRecordDTO standardUnitRecordDTO = new StandardUnitRecordDTO();
			standardUnitRecordDTO.setMerchantProductSerialNumber(standardUnit.getMerchantProductSerialNumber());	
			standardUnitRecordDTO.setMerchantId(standardUnit.getMerchantId());	
			standardUnitRecordDTO.setStandardUnitId(standardUnit.getId());
			standardUnitRecordDTO.setStandardProductUnitId(standardUnit.getStandardProductUnitId());
			standardUnitRecordDTO.setMerchantCateTreeNodeId(standardUnit.getMerchantCateTreeNodeId());	
			standardUnitRecordDTO.setMerchantSeriesId(standardUnit.getMerchantSeriesId());	
			standardUnitRecordDTO.setName(standardUnit.getName());	
			standardUnitRecordDTO.setAlias(standardUnit.getAlias());	
			standardUnitRecordDTO.setIsVisible(standardUnit.getIsVisible());	
			standardUnitRecordDTO.setSubtitle(standardUnit.getSubtitle());	
			standardUnitRecordDTO.setSupplierId(standardUnit.getSupplierId());	
			standardUnitRecordDTO.setType(standardUnit.getType());	
			standardUnitRecordDTO.setRemark(standardUnit.getRemark());	
			standardUnitRecordDTO.setSalePrice(standardUnit.getSalePrice());	
			standardUnitRecordDTO.setSaleTaxRate(standardUnit.getSaleTaxRate());	
			standardUnitRecordDTO.setReturnDays(standardUnit.getReturnDays());	
			standardUnitRecordDTO.setReplacementDays(standardUnit.getReplacementDays());	
			standardUnitRecordDTO.setGuaranteeDays(standardUnit.getGuaranteeDays());	
			standardUnitRecordDTO.setIsVatInvoice(standardUnit.getIsVatInvoice());	
			standardUnitRecordDTO.setIsVipCard(standardUnit.getIsVipCard());	
			standardUnitRecordDTO.setIsEnableShelflife(standardUnit.getIsEnableShelflife());	
			standardUnitRecordDTO.setShelflifeDays(standardUnit.getShelflifeDays());	
			standardUnitRecordDTO.setIsVendible(standardUnit.getIsVendible());	
			standardUnitRecordDTO.setGrossWeight(standardUnit.getGrossWeight());	
			standardUnitRecordDTO.setNetWeight(standardUnit.getNetWeight());	
			standardUnitRecordDTO.setCode(standardUnit.getCode());	
			standardUnitRecordDTO.setMerchantBrandId(standardUnit.getMerchantBrandId());	
			standardUnitRecordDTO.setMarketPrice(standardUnit.getMarketPrice());	
			standardUnitRecordDTO.setPromotionPrice(standardUnit.getPromotionPrice());	
			standardUnitRecordDTO.setStatus(standardUnit.getStatus());	
			standardUnitRecordDTO.setSaleWay(standardUnit.getSaleWay());	
			standardUnitRecordDTO.setSoldBase(standardUnit.getSoldBase());	
			standardUnitRecordDTO.setFreightExplain(standardUnit.getFreightExplain());	
			standardUnitRecordDTO.setShipmentsExplain(standardUnit.getShipmentsExplain());	
			standardUnitRecordDTO.setFreightTemplateId(standardUnit.getFreightTemplateId());	
			standardUnitRecordDTO.setPlatformId(standardUnit.getPlatformId());
			standardUnitRecordDTO.setProductCategory(standardUnit.getProductCategory());
			standardUnitRecordFacade.insertStandardUnitRecordWithTx(standardUnitRecordDTO);
			
			//根据suid删除su图片关系表
			standardUnitPictureFacade.deleteByStandardUnitIdWithTx(merchantProdId);
			
			//根据suid删除su客户端关系表
			standardUnitClientFacade.deleteByStandardUnitIdWithTx(merchantProdId);
			
			//根据suid删除su福利企业关系信息
			standardUnitCompanyFacade.deleteByStandardUnitIdWithTx(merchantProdId);
			
			//根据suid删除su比价平台信息
			sellPlatformStandardUnitFacade.deleteByStandardUnitIdWithTx(merchantProdId);
			
			//根据su草稿id查询所有草稿pu信息
			ProductUnitDTO productUnitDTO = new ProductUnitDTO();
			productUnitDTO.setMerchantProductId(merchantProdId);
			List<ProductUnitDTO> productUnitList = productUnitFacade.findProductUnitAll(productUnitDTO);
			for (ProductUnitDTO productUnitDTO2 : productUnitList) {
				CommodityProductUnitDTO commodityProductUnitDTO = new CommodityProductUnitDTO();
				commodityProductUnitDTO.setProductUnitId(productUnitDTO2.getId());
				commodityProductUnitDTO.setSkuId(productUnitDTO2.getSkuId());	
				commodityProductUnitDTO.setStandardUnitId(merchantProdId);	
				commodityProductUnitDTO.setName(productUnitDTO2.getName());	
				commodityProductUnitDTO.setRemark(productUnitDTO2.getRemark());	
				commodityProductUnitDTO.setSalePrice(productUnitDTO2.getSalePrice());	
				commodityProductUnitDTO.setStatus(productUnitDTO2.getStatus());	
				commodityProductUnitDTO.setIsVendible(productUnitDTO2.getIsVendible());	
				commodityProductUnitDTO.setCode(productUnitDTO2.getCode());	
				commodityProductUnitDTO.setPuPicUrl(productUnitDTO2.getPuPicUrl());	
				commodityProductUnitDTO.setPlatformId(productUnitDTO2.getPlatformId());	
				//根据pu草稿id修改pu信息
				commodityProductUnitFacade.updateCommodityProductUnitByProductUnitIdWithTx(commodityProductUnitDTO);
			}
			
		}
		
		//根据su草稿id查询su草稿图片信息
		MerchantProdPictureDTO merchantProdPictureDTO = new MerchantProdPictureDTO();
		merchantProdPictureDTO.setMerchantProdId(merchantProdId);
		List<MerchantProdPictureDTO> merchantProdPictureList = merchantProdPictureFacade.findMerchantProdPictureAll(merchantProdPictureDTO);
		for (MerchantProdPictureDTO merchantProdPictureDTO2 : merchantProdPictureList) {
			//同步添加su图片关系
			StandardUnitPictureDTO standardUnitPictureDTO = new StandardUnitPictureDTO();
			standardUnitPictureDTO.setMerchantPictureId(merchantProdPictureDTO2.getMerchantPictureId());	
			standardUnitPictureDTO.setType(merchantProdPictureDTO2.getType());	
			standardUnitPictureDTO.setStandardUnitId(merchantProdId);	
			standardUnitPictureFacade.insertStandardUnitPictureWithTx(standardUnitPictureDTO);
			
			//同步保存su记录表图片信息
			StandardUnitPictureRecordDTO standardUnitPictureRecordDTO = new StandardUnitPictureRecordDTO();
			standardUnitPictureRecordDTO.setMerchantPictureId(merchantProdPictureDTO2.getMerchantPictureId());	
			standardUnitPictureRecordDTO.setType(merchantProdPictureDTO2.getType());	
			standardUnitPictureRecordDTO.setStandardUnitRecordId(standardUnitRecordId);
			standardUnitPictureRecordFacade.insertStandardUnitPictureRecordWithTx(standardUnitPictureRecordDTO);
		}
		
		//su草稿id查询su草稿客户端关系表
		MerchantProdClientDTO merchantProdClientDTO = new MerchantProdClientDTO();
		merchantProdClientDTO.setMerchantProductId(merchantProdId);
		List<MerchantProdClientDTO> merchantProdClientDTOList = merchantProdClientFacade.findMerchantProdClientAll(merchantProdClientDTO);
		for (MerchantProdClientDTO merchantProdClientDTO2 : merchantProdClientDTOList) {
			//同步su客户端关系
			StandardUnitClientDTO standardUnitClientDTO = new StandardUnitClientDTO();
			standardUnitClientDTO.setClientId(merchantProdClientDTO2.getClientId());
			standardUnitClientDTO.setStandardUnitId(merchantProdId);
			standardUnitClientFacade.insertStandardUnitClientWithTx(standardUnitClientDTO);
			
			//同步保存su记录表信息
			StandardUnitClientRecordDTO standardUnitClientRecordDTO = new StandardUnitClientRecordDTO();
			standardUnitClientRecordDTO.setClientId(standardUnitClientDTO.getClientId());
			standardUnitClientRecordDTO.setStandardUnitRecordId(standardUnitRecordId);
			standardUnitClientRecordFacade.insertStandardUnitClientRecordWithTx(standardUnitClientRecordDTO);
		}
		
		//根据su草稿id查询su草稿公司关系
		MerchantProductCompanyDTO merchantProductCompanyDTO = new MerchantProductCompanyDTO();
		merchantProductCompanyDTO.setMerchantProductId(merchantProdId);
		List<MerchantProductCompanyDTO> merchantProductCompanyList = merchantProductCompanyFacade.findMerchantProductCompanyAll(merchantProductCompanyDTO);
		for (MerchantProductCompanyDTO merchantProductCompanyDTO2 : merchantProductCompanyList) {
			//同步保存su公司关系
			StandardUnitCompanyDTO standardUnitCompanyDTO = new StandardUnitCompanyDTO();
			standardUnitCompanyDTO.setCompanyId(merchantProductCompanyDTO2.getCompanyId());
			standardUnitCompanyDTO.setStandardUnitId(merchantProdId);
			standardUnitCompanyFacade.insertStandardUnitCompanyWithTx(standardUnitCompanyDTO);
			
			//同步保存su记录表信息
			StandardUnitCompanyRecordDTO standardUnitCompanyRecordDTO = new StandardUnitCompanyRecordDTO();
			standardUnitCompanyRecordDTO.setCompanyId(standardUnitCompanyDTO.getCompanyId());
			standardUnitCompanyRecordDTO.setStandardUnitRecordId(standardUnitRecordId);
			standardUnitCompanyRecordFacade.insertStandardUnitCompanyRecordWithTx(standardUnitCompanyRecordDTO);
		}
		
		//根据su草稿id查询比价平台信息
		SellPlatformMerchantProdDTO platformMerchantProdDTO = new SellPlatformMerchantProdDTO();
		platformMerchantProdDTO.setMerchantProductId(merchantProdId);
		List<SellPlatformMerchantProdDTO> sellPlatformMerchantProdList = sellPlatformMerchantProdFacade.findSellPlatformMerchantProdAll(platformMerchantProdDTO);
		for (SellPlatformMerchantProdDTO sellPlatformMerchantProdDTO : sellPlatformMerchantProdList) {
			SellPlatformStandardUnitDTO sellPlatformStandardUnitDTO = new SellPlatformStandardUnitDTO();
			sellPlatformStandardUnitDTO.setSellPlatformId(sellPlatformMerchantProdDTO.getSellPlatformId());	
			sellPlatformStandardUnitDTO.setStandardUnitId(sellPlatformMerchantProdDTO.getMerchantProductId());	
			sellPlatformStandardUnitDTO.setSalePrice(sellPlatformMerchantProdDTO.getSalePrice());	
			sellPlatformStandardUnitDTO.setPath(sellPlatformMerchantProdDTO.getPath());	
			sellPlatformStandardUnitFacade.insertSellPlatformStandardUnitWithTx(sellPlatformStandardUnitDTO);
			
			//同步保存su记录比价平台信息
			SellPlatformStandardUnitRecordDTO sellPlatformStandardUnitRecordDTO = new SellPlatformStandardUnitRecordDTO();
			sellPlatformStandardUnitRecordDTO.setSellPlatformId(sellPlatformMerchantProdDTO.getSellPlatformId());	
			sellPlatformStandardUnitRecordDTO.setStandardUnitRecordId(standardUnitRecordId);
			sellPlatformStandardUnitRecordDTO.setSalePrice(sellPlatformMerchantProdDTO.getSalePrice());	
			sellPlatformStandardUnitRecordDTO.setPath(sellPlatformMerchantProdDTO.getPath());	
			sellPlatformStandardUnitRecordFacade.insertSellPlatformStandardUnitRecordWithTx(sellPlatformStandardUnitRecordDTO);
		}
		
		//同步保存su详情
		StandardUnitDescribeDTO standardUnitDescribeDTO = new StandardUnitDescribeDTO();
		standardUnitDescribeDTO.setContent(merchantProductDTO2.getContent());
		standardUnitDescribeDTO.setStandardUnitId(merchantProdId);
		standardUnitDescribeFacade.insertStandardUnitDescribeWithTx(standardUnitDescribeDTO);
		
		//同步保存su记录详情表信息
		StandardUnitDescribeRecordDTO standardUnitDescribeRecordDTO = new StandardUnitDescribeRecordDTO();
		standardUnitDescribeRecordDTO.setContent(merchantProductDTO2.getContent());
		standardUnitDescribeRecordDTO.setStandardUnitRecordId(standardUnitRecordId);
		standardUnitDescribeRecordFacade.insertStandardUnitDescribeRecordWithTx(standardUnitDescribeRecordDTO);
		
	}
	


}
	