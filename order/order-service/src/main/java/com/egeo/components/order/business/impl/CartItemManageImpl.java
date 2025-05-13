package com.egeo.components.order.business.impl;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;

import com.egeo.components.product.dto.*;
import com.egeo.components.product.dto.channel.ChannelProductBatchDTO;
import com.egeo.components.product.dto.channel.ChannelProductDTO;
import com.egeo.components.product.vo.ChannelProductDetailVO;
import com.egeo.config.RuntimeContext;
import lombok.val;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.egeo.components.order.business.CartItemManage;
import com.egeo.components.order.dto.CartItemDTO;
import com.egeo.components.order.facade.CartItemFacade;
import com.egeo.components.order.facade.ProductFacade;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.JsonResult;

@Service("cartItem")
public class CartItemManageImpl implements CartItemManage{


	@Resource(name="cartItemFacade")
	private CartItemFacade cartItemFacade;
	@Resource(name = "productFacade")
	private ProductFacade productFacade;


	@Override
	public CartItemDTO findCartItemById(Long id) {
		return cartItemFacade.findCartItemById(id);
	}

	@Override
	public PageResult<CartItemDTO> findCartItemOfPage(CartItemDTO dto, Pagination page) {
		return cartItemFacade.findCartItemOfPage(dto, page);
	}

	@Override
	public List<CartItemDTO> findCartItemAll(CartItemDTO dto) {
		return cartItemFacade.findCartItemAll(dto);
	}

	@Override
	public Long insertCartItemWithTx(CartItemDTO dto) {
		return cartItemFacade.insertCartItemWithTx(dto);
	}

	@Override
	public int updateCartItemWithTx(CartItemDTO dto) {
		return cartItemFacade.updateCartItemWithTx(dto);
	}

	@Override
	public int deleteCartItemWithTx(CartItemDTO dto) {
		return cartItemFacade.deleteCartItemWithTx(dto);
	}
	/**
	 * 根据用户id查询购物车pu信息
	 * @param page
	 * @return
	 */
	@Override
	public PageResult<Map<String, Object>> findCartItemOfPageByUserId(Long clientId,Long userId,Long companyId, Long platformId, Long storeId,
			Pagination page) {

		return cartItemFacade.findCartItemOfPageByUserId(clientId,userId,companyId, platformId, storeId,page);
	}

	/**
	 * 根据用户id查询购物车pu信息
	 * @param page
	 * @return
	 *//*
	@Override
	public PageResult<Map<String, Object>> refreshJdCartItemOfPageByUserId(Long clientId,Long userId,Long companyId, Long platformId, Long storeId,
			Pagination page) {

		return cartItemFacade.findCartItemOfPageByUserId(clientId,userId,companyId, platformId, storeId,page);
	}*/
	/**
	 * 加入购物车
	 * @return
	 */
	@Override
	public JsonResult<String> saveCartItemWithTx(CartItemDTO dto, Long userId, Long platformId, Long storeId, Long clientId,Integer source) {
		Long enterpriseId = RuntimeContext.cacheUser()!=null?RuntimeContext.cacheUser().getEnterpriseId():null;
		if(EmptyUtil.isEmpty(dto.getProductUnitId())){
			throw new BusinessException("请选择商品");
		}
		if(source!=null && source.intValue()==3) {
			JdProductDTO jdProductDTO = productFacade.checkJdProductStatus(dto.getProductUnitId()+"");
			if(jdProductDTO.getState()==0){
				throw new BusinessException("抱歉，该商品已下架");
			}
			CommodityProductUnitDTO pu = new CommodityProductUnitDTO();
			pu.setMerchantId(6L);
			pu.setStandardUnitId(dto.getProductUnitId());
			pu.setSalePrice(jdProductDTO.getPrice());
			pu.setStatus(3);
			dto.setSource(source);
			dto.setSnapshot(JSON.toJSONString(jdProductDTO));
			return cartItemFacade.saveCartItemWithTx(pu,dto, userId, platformId,storeId,clientId);
		}else if(source!=null && source.intValue() ==4){
			CakeProductDetailDTO cakeProductDetailDTO = productFacade.getCakeProduct(dto.getChannelProductId(),String.valueOf(dto.getProductUnitId()),null,null,enterpriseId);
			if(Objects.isNull(cakeProductDetailDTO)){
				throw new BusinessException("商品未找到");
			}
			CakeProductDetailSpecsDTO cakeProductDetailSpecsDTO = productFacade.getCakeProductSkuInfo(cakeProductDetailDTO,String.valueOf(dto.getProductUnitId()));
			if(Objects.isNull(cakeProductDetailSpecsDTO)){
				throw new BusinessException("无该规格商品");
			}

			CommodityProductUnitDTO pu = new CommodityProductUnitDTO();
			pu.setMerchantId(7L);
			pu.setStandardUnitId(dto.getProductUnitId());
			pu.setSalePrice(new BigDecimal(cakeProductDetailSpecsDTO.getPrice()).setScale(2));
			pu.setStatus(3);
			dto.setSource(source);
			dto.setSnapshot(JSON.toJSONString(cakeProductDetailDTO));
			return cartItemFacade.saveCartItemWithTx(pu,dto, userId, platformId,storeId,clientId);
		}else if(source !=null && source.intValue() ==5){
			ChannelProductDetailVO vo = productFacade.findWorldProduct(dto.getChannelProductId(),String.valueOf(dto.getProductUnitId()));
			ChannelProductDTO channelProductDTO = vo.getChannelProductDTO();
			if(Objects.isNull(vo) || Objects.isNull(channelProductDTO)){
				throw new BusinessException("商品未找到");
			}
			if(channelProductDTO.getStatus() ==null || channelProductDTO.getStatus().intValue() !=1){
				throw new BusinessException("抱歉，该商品已下架");
			}
			List<ChannelProductBatchDTO> batchDTOList = vo.getBatchDTOList();
			ChannelProductBatchDTO batchDTO = productFacade.getCurrBatch(String.valueOf(dto.getProductUnitId()),batchDTOList);
			if(Objects.isNull(batchDTO)){
				throw new BusinessException("无该规格商品");
			}
			CommodityProductUnitDTO pu = new CommodityProductUnitDTO();
			pu.setMerchantId(8L);
			pu.setStandardUnitId(dto.getProductUnitId());
			pu.setSalePrice(batchDTO.getPrice().setScale(2));
			pu.setStatus(3);
			dto.setSource(source);
			dto.setSnapshot(JSON.toJSONString(vo));
			return cartItemFacade.saveCartItemWithTx(pu,dto, userId, platformId,storeId,clientId);
		}else{
			//根据puid查询pu信息
			CommodityProductUnitDTO puCond = new CommodityProductUnitDTO();
			puCond.setId(dto.getProductUnitId());
			CommodityProductUnitDTO pu = productFacade.findCommodityProductUnit(dto.getProductUnitId());
			if(pu==null) {
				throw new BusinessException("商品不存在");
			}
			if(pu.getIsVendible()==0) {
				//pu不可销售
				throw new BusinessException("无该规格商品");
			}
			if(pu.getLimitBuyNum()>=2){
				if(dto.getNum()<pu.getLimitBuyNum()){
					throw new BusinessException("至少购买"+pu.getLimitBuyNum()+"件哦");
				}
			}
			SkuDTO skuBySkuId = productFacade.findSkuBySkuId(pu.getSkuId());
			return cartItemFacade.saveCartItemWithTx(pu,dto, userId, platformId,storeId,clientId);
		}

	}
	/**
	 * 根据购物车pu商品关系id把购物车数量加一
	 * @param cartItemId
	 * @return
	 */
	@Override
	public int addNumWithTx(Long cartItemId) {
		// TODO Auto-generated method stub
		return cartItemFacade.addNumWithTx(cartItemId);
	}
	/**
	 * 根据购物车pu商品关系id把购物车数量减一
	 * @param cartItemId
	 * @return
	 */
	@Override
	public int minusNumWithTx(Long cartItemId) {
		// TODO Auto-generated method stub
		return cartItemFacade.minusNumWithTx(cartItemId);
	}
	/**
	 * 根据用户id查询购物车pu数量--用于head中购物车小图标
	 * @param req
	 * @return
	 */
	@Override
	public Integer findCartItemSumByUserId(Long storeId,Long userId, Long platformId, Long clientId) {
		// TODO Auto-generated method stub
		return cartItemFacade.findCartItemSumByUserId(storeId,userId, platformId, clientId);
	}
	/**
	 * 根据用户id查询用户购物车pu种类数量
	 * @param userId
	 * @param platformId
	 * @param clientId
	 * @return
	 */
	@Override
	public int findCartItemPUSumByUserId(Long userId, Long platformId,Long storeId) {
		// TODO Auto-generated method stub
		return cartItemFacade.findCartItemPUSumByUserId(userId, platformId,storeId);
	}

	@Override
	public int deleteCartItemByIdsWithTx(List<Long> cartItemIdList) {
		return cartItemFacade.deleteCartItemByIdsWithTx(cartItemIdList);
	}

	@Override
	public void checkPUStore(Long productUnitId, Long storeId) {
		CommodityProductUnitDTO productUnitDTO=productFacade.findCommodityProductUnit(productUnitId);
		if(EmptyUtil.isEmpty(productUnitDTO)){
			throw new BusinessException("该商品不存在");
		}
		StandardUnitDTO standardUnit = productFacade.findStandardUnit(productUnitDTO.getStandardUnitId());
		if(EmptyUtil.isEmpty(standardUnit)){
			throw new BusinessException("该商品su不存在");

		}
		if(storeId==1L||storeId==2L){
			//总店
			if(!standardUnit.getStoreId().equals(storeId)){
				throw new BusinessException("该商品已被管理员移除");
			}
		}else{
			List<StandardUnitStoreDTO> standUnitStore = productFacade.findStandUnitStore(productUnitDTO.getStandardUnitId(), storeId);
			if(EmptyUtil.isEmpty(standUnitStore)){
				throw new BusinessException("该商品已被管理员移除");
			}

		}

	}


}
