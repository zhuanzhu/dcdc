package com.egeo.components.product.facade;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.product.dto.CommodityProductUnitDTO;
import com.egeo.components.product.dto.JdProductDTO;
import com.egeo.components.product.dto.MembershipDTO;
import com.egeo.components.product.dto.SkuDTO;
import com.egeo.components.product.service.read.CommodityProductUnitReadService;
import com.egeo.components.product.service.read.JdProductReadService;
import com.egeo.components.product.service.read.MembershipReadService;
import com.egeo.components.product.service.read.SkuReadService;
import com.egeo.components.product.service.write.SkuWriteService;
import com.egeo.components.promotion.client.ECardClient;
import com.egeo.components.promotion.dto.ECardDTO;
import com.egeo.components.stock.client.CommodityProductUnitWarehouseStockClient;
import com.egeo.components.stock.client.MerchantProductVirtualStockClient;
import com.egeo.components.stock.client.MerchantProductWarehouseStockClient;
import com.egeo.components.stock.dto.CommodityProductUnitWarehouseStockDTO;
import com.egeo.components.stock.dto.MerchantProductVirtualStockDTO;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;


@Component
public class SkuFacade {
	
	@Resource
	private SkuReadService skuReadService;
	
	@Resource
	private SkuWriteService skuWriteService;
	
	@Autowired
	private CommodityProductUnitWarehouseStockClient commodityProductUnitWarehouseStockReadService;
	
	@Autowired
	private MerchantProductWarehouseStockClient merchantProductWarehouseStockReadService;
	
	@Resource
	private CommodityProductUnitReadService commodityProductUnitReadService;
	
	@Autowired
	private ECardClient eCardReadService;
	
	@Autowired
	private MerchantProductVirtualStockClient merchantProductVirtualStockReadService;
	@Resource
	private MembershipReadService membershipReadService;
	@Resource
	private JdProductReadService jdProductReadService;
	
	
	public SkuDTO findSkuById(SkuDTO dto){
		
		return skuReadService.findSkuById(dto);
	}

	public PageResult<SkuDTO> findSkuOfPage(SkuDTO dto,Pagination page){
		
		return skuReadService.findSkuOfPage(dto, page);
		
	}

	public List<SkuDTO> findSkuAll(SkuDTO dto){
		
		return skuReadService.findSkuAll(dto);
		
	}

	public List<SkuDTO> findSkuBySkuSerialNos(List<String> skuSerialNos){
		return skuReadService.findSkuBySkuSerialNos(skuSerialNos);
	}

	public List<SkuDTO> findSkuBySkuIds(List<Long> skuIds){
		return skuReadService.findSkuBySkuIds(skuIds);
	}

	public Long insertSkuWithTx(SkuDTO dto){
		
		return skuWriteService.insertSkuWithTx(dto);
	}

	public int updateSkuWithTx(SkuDTO dto){
		
		return skuWriteService.updateSkuWithTx(dto);
	}

	public int deleteSkuWithTx(SkuDTO dto){
		
		return skuWriteService.deleteSkuWithTx(dto);
		
	}
	/**
	 * 分页查询所有电子卡券sku
	 * @param vo
	 * @param req
	 * @param page
	 * @return
	 */
	public PageResult<SkuDTO> findSkuECardOfPage(SkuDTO dto, Pagination page) {
		// TODO Auto-generated method stub
		List<SkuDTO> result=skuReadService.findSkuECardOfPage(dto);
		List<SkuDTO> list = new ArrayList<>();
		List<SkuDTO> pageList = new ArrayList<>();
		//去除券仓类sku无unit记录的商品
		for(SkuDTO skuDTO:result){
			if(skuDTO.getMerchantId().equals(2L)){
				ECardDTO eCardDTO = new ECardDTO();
				eCardDTO.setSkuId(skuDTO.getId());
				List<ECardDTO> eCardAll = eCardReadService.findECardAll(eCardDTO);
				if(EmptyUtil.isEmpty(eCardAll)){
					continue;
				}
			}
			list.add(skuDTO);
		}
		for(int i=page.getPageSize()*(page.getPageNo()-1);i<page.getPageSize()*page.getPageNo()-1&&i<list.size();i++){
			pageList.add(list.get(i));
		}
		PageResult pageResult = new PageResult();
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		pageResult.setTotalSize(list.size());
		pageResult.setList(pageList);
		return pageResult;
	}
	/**
	 * 根据skuid查询sku库存信息
	 * @param dto
	 * @return
	 */
	public MerchantProductVirtualStockDTO findMerchantProductWarehouseStockAll(Long skuId){
		CommodityProductUnitDTO commodityProductUnitDTO = new CommodityProductUnitDTO();
		commodityProductUnitDTO.setSkuId(skuId);
		List<CommodityProductUnitDTO> commodityProductUnitList = commodityProductUnitReadService.findCommodityProductUnitAll(commodityProductUnitDTO);
		//拼接puid集合
		List<Long> commodityProductUnitIds = new ArrayList<>();
		for (CommodityProductUnitDTO commodityProductUnitDTO2 : commodityProductUnitList) {
			commodityProductUnitIds.add(commodityProductUnitDTO2.getId());
		}
		MerchantProductVirtualStockDTO merchantProductVirtualStockDTO = merchantProductVirtualStockReadService.merchantProductVirtualStockBySkuId(skuId);
		if(EmptyUtil.isEmpty(merchantProductVirtualStockDTO)){
			throw new BusinessException("sku库存信息异常，skuId："+skuId);
		}
		List<CommodityProductUnitWarehouseStockDTO> commodityProductUnitWarehouseStockList = null;
		if(EmptyUtil.isNotEmpty(commodityProductUnitIds)){
			//根据skuid查询pu库存
			commodityProductUnitWarehouseStockList = commodityProductUnitWarehouseStockReadService.findByPUId(com.egeo.utils.StringUtils.longsToStrings(commodityProductUnitIds));
		}
		
		//拼接所有pu真实库存之和
		Long realStockNums = 0L;
		if(EmptyUtil.isNotEmpty(commodityProductUnitWarehouseStockList)){
			for (CommodityProductUnitWarehouseStockDTO commodityProductUnitWarehouseStockPO : commodityProductUnitWarehouseStockList) {
				realStockNums = realStockNums + (commodityProductUnitWarehouseStockPO.getRealStockNum()-commodityProductUnitWarehouseStockPO.getRealFrozenStockNum());
			}
		}
		
		merchantProductVirtualStockDTO.setRealStockNums(realStockNums);
		return merchantProductVirtualStockDTO;
	}
	/**
	 * 个人扭矩条件查询电子卡券信息
	 * @param dto
	 * @return
	 */
	public List<ECardDTO> findECardAll(ECardDTO dto){
		return eCardReadService.findECardAll(dto);
	}
	/**
	 * 批量启用停用
	 * @param ids
	 * @param type
	 * @return
	 */
	public int isAvailableWithTx(List<Long> ids, int type) {
		// TODO Auto-generated method stub
		return skuWriteService.isAvailableWithTx(ids, type);
	}

	public List<SkuDTO> findSkuLikeName(String linkedSkuName,Long platformId) {
		return skuReadService.findSkuLikeName(linkedSkuName,platformId);
	}

	public List<Long> getMembershipSku(Long platformId) {

		return skuReadService.getMembershipSku(platformId);
	}

	public List<Long> getUsedMembershipSkuId(Long platformId) {
		MembershipDTO membershipDTO = new MembershipDTO();
		membershipDTO.setPlatformId(platformId);
		List<MembershipDTO> membershipAll = membershipReadService.findMembershipAll(membershipDTO);
		if(EmptyUtil.isEmpty(membershipAll)){
			return null;
		}
		List<Long> result=new ArrayList<>();
		for(MembershipDTO dto:membershipAll){
			result.add(dto.getLinkedSkuId());
		}
		return result;
	}

    public void updateExternalSkuId(Long skuId, String externalSkuId, String barCode) {
		SkuDTO skuDTO = new SkuDTO();
		skuDTO.setId(skuId);
		skuDTO.setExternalSkuId(externalSkuId);
		skuDTO.setBarCode(barCode);
		skuWriteService.updateSkuParamsWithTx(skuDTO);
	}

	public JdProductDTO findJdProductById(String externalSkuId) {
		JdProductDTO jdProductDTO = new JdProductDTO();
		jdProductDTO.setId(Long.valueOf(externalSkuId));
		return jdProductReadService.findJdProductById(jdProductDTO);
	}
}
	