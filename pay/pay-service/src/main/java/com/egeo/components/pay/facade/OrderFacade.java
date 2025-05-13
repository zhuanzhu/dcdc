package com.egeo.components.pay.facade;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.egeo.components.order.dto.QmOrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.config.client.SaltClient;
import com.egeo.components.config.dto.SaltDTO;
import com.egeo.components.finance.client.SoFreezeFubiClient;
import com.egeo.components.finance.client.UserAccountClient;
import com.egeo.components.finance.dto.UserAccountDTO;
import com.egeo.components.order.client.SoClient;
import com.egeo.components.order.client.SoItemClient;
import com.egeo.components.order.dto.SoDTO;
import com.egeo.components.order.dto.SoItemDTO;
import com.egeo.components.pay.enums.OrderConstant;
import com.egeo.components.product.client.CommodityProductUnitClient;
import com.egeo.components.product.dto.CommodityProductUnitDTO;
import com.egeo.components.stock.client.CommodityProductUnitStockRunningWaterClient;
import com.egeo.components.stock.client.CommodityProductUnitWarehouseStockClient;
import com.egeo.components.stock.client.MerchantProductWarehouseStockClient;
import com.egeo.components.stock.constant.StockConstant;
import com.egeo.components.stock.dto.CommodityProductUnitStockRunningWaterDTO;
import com.egeo.components.stock.dto.MerchantProductWarehouseStockDTO;
import com.egeo.components.stock.fo.UnfreezeAndDeductStockBatchWithTxFO;
import com.egeo.components.user.client.UserClient;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.exception.BusinessException;
import com.egeo.util.security.MD5Util;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.log.XLogger;

@Component
public class OrderFacade {

	@Autowired
	private SoClient soReadService;

	@Autowired
	private SoClient soWriteService;

	@Autowired
	private SoItemClient soItemReadService;

	@Autowired
	private MerchantProductWarehouseStockClient merchantProductWarehouseStockWriteService;
	
	@Autowired
	private SoFreezeFubiClient soFreezeFubiReadService;
	
	@Autowired
	private UserAccountClient userAccountReadService;
	
	@Autowired
	private SaltClient saltReadService;
	
	@Autowired
	private UserAccountClient userAccountWriteService;
	
	@Autowired
	private SoFreezeFubiClient soFreezeFubiWriteService;
	
	@Autowired
	private CommodityProductUnitWarehouseStockClient commodityProductUnitWarehouseStockWriteService;
	
	@Autowired
	private CommodityProductUnitClient commodityProductUnitReadService;
	@Autowired
	private UserClient userReadService;
	
	@Autowired
	private CommodityProductUnitStockRunningWaterClient commodityProductUnitStockRunningWaterReadService;

	private static final XLogger log = XLogger.getLogger(OrderFacade.class);

	/**
	 * 订单查询
	 * 
	 * @param orderId
	 * @return
	 */
	public SoDTO querySoById(Long orderId) {
		return soReadService.querySoById(orderId);
	}

	/**
	 * 订单项查询
	 * 
	 * @param orderId
	 * @return
	 */
	public List<SoItemDTO> querySoItemBySoId(Long soId) {
		return soItemReadService.querySoItemBySoId(soId);
	}

	/**
	 * 通过订单编号查询订单
	 * 
	 * @param orderCode
	 * @return
	 */
	public SoDTO querySoByOrderCode(String orderCode) {
		return soReadService.querySoByOrderCode(orderCode);
	}

	/**
	 * 通过soId查询QmOrderDTO
	 * @param soId
	 * @return
	 */
	public QmOrderDTO queryQmOrderBySoId(Long soId){
		return soReadService.queryQmOrderBySoId(soId);
	}

	public boolean updateOrderStatusAndUnlockStock(String orderCode) {
		// 修改订单状态
		soWriteService.changeOrderStatusByOrderCode(orderCode, OrderConstant.ORDER_STATUS_CANCELED.getStatus(),null,null,null);
		SoDTO soDTO = soReadService.querySoByOrderCode(orderCode);
		//如果订单积分冻结金额不为空则取消订单冻结积分，并扣除用户积分冻结账户
		cancelSoFreezeFubi(soDTO.getId(), soDTO.getUserId());
		// 释放库存
		List<SoItemDTO> items = soItemReadService.querySoItemBySoId(soDTO.getId());
		return unlockItemsProductUnitStock(items,soDTO.getUserId());
	}
	
	/**
	 * 把锁定的库存解锁
	 * @param soItemDTOList
	 * @param userId
	 * @return
	 */
	public boolean unlockItemsProductUnitStock(List<SoItemDTO> soItemDTOList, Long userId) {
		boolean  result = false;
		//改变所有购买项的库存，解锁锁定的库存
		for(SoItemDTO soItemDto:soItemDTOList){
			SoDTO soDTO = soReadService.querySoById(soItemDto.getId());
			CommodityProductUnitDTO commodityProductUnitDTO = new CommodityProductUnitDTO();
			commodityProductUnitDTO.setId(soItemDto.getPuId());
			CommodityProductUnitDTO commodityProductUnitDTO2 = commodityProductUnitReadService
					.findCommodityProductUnitById(commodityProductUnitDTO);
			// 库存流水类型:1提交订单 2支付 3 取消订单（未支付） 4 取消订单（已支付） 5 进货 6 出货
			UserDTO userByID = userReadService.findUserByID(userId);
			if(EmptyUtil.isEmpty(userByID)){
				throw new BusinessException(userId + "用户不存在");
			}
			
			//根据订单ID查询 puIds 
			//根据puIds
			List<CommodityProductUnitStockRunningWaterDTO> waterDTOs =
			commodityProductUnitStockRunningWaterReadService.findCommodityProductUnitStockRunningWaterByOrderCodesAndStatus(Arrays.asList(soDTO.getOrderCode()),StockConstant.STOCK_STATUS_CONTACT_STOCK.getStatus());
			List<Long> puIdList = new ArrayList<>();
			Set<Long> puIdSet = new HashSet<>();
			for (CommodityProductUnitStockRunningWaterDTO waterDTO : waterDTOs) {
				puIdSet.add(waterDTO.getCommodityProductUnitId());
			}
			puIdList.addAll(puIdSet);
			
			List<CommodityProductUnitDTO> commodityProductUnitDTOs = commodityProductUnitReadService.findByPUIdSkuId(com.egeo.utils.StringUtils.longsToStrings(puIdList),commodityProductUnitDTO2.getSkuId());
			
			if(commodityProductUnitDTOs != null && commodityProductUnitDTOs.size() > 0) {
				puIdList.add(soItemDto.getPuId());
				commodityProductUnitDTOs.add(commodityProductUnitDTO2);
				commodityProductUnitWarehouseStockWriteService.unfreezeAndDeductStockBatchWithTx(new UnfreezeAndDeductStockBatchWithTxFO(soItemDto.getPuId(), soItemDto.getPuCount(), 
						StockConstant.STOCK_STATUS_CANCEL_ORDER_NO_PAYED.getStatus(), soDTO.getOrderCode(), userId, userByID.getName(),
						null, null, puIdList, commodityProductUnitDTOs));
			}else {
				commodityProductUnitWarehouseStockWriteService.unfreezeAndDeductStockWithTx(soItemDto.getPuId(),soItemDto.getPuCount(), StockConstant.STOCK_STATUS_CANCEL_ORDER_NO_PAYED.getStatus(),
						commodityProductUnitDTO2.getProductUnitSerialNumber(), commodityProductUnitDTO2.getName(),soDTO.getOrderCode(),userId,userByID.getName(),null,null);
			}
			
		}
		result = true;
		return 	result;
		
	}
	
	/**
	 * 如果订单积分冻结金额不为空则取消订单冻结积分，并扣除用户积分账户
	 */
	public void cancelSoFreezeFubi(Long orderId, Long userId) {
		// 根据订单id查询订单冻结积分
		BigDecimal soFreezeBalance = soFreezeFubiReadService.findSoFreezeBalanceBySoId(orderId);
		if (soFreezeBalance!=null) {
			// 如果订单积分冻结金额不为空则取消订单冻结积分，并扣除用户积分账户
			//根据订单id删除订单冻结积分
			soFreezeFubiWriteService.delBySoId(orderId);
			
			// 根据用户id和账户类型查询用户积分账户 账户类型 0:积分账户 1:点赞福豆账户 2:积分冻结账户 4:现金支出账户
			UserAccountDTO userAccount = userAccountReadService.queryUserAccountByUserIdAndType(userId, 2);
			// 根据uuid查询
			SaltDTO salt = saltReadService.querySaltByUUID(userAccount.getUuid());
			//略过加密值校验
//			if (!ciphertextSalt.equals(userAccount.getCiphertext())) {
//				log.debug("积分冻结账户异常,id:"+userAccount.getId());
//			}
			BigDecimal balance = userAccount.getBalance().subtract(soFreezeBalance);
			String ciphert = MD5Util.MD5Salt(balance.toString(), salt.getSaltValue());
			// 根据用户积分冻结id修改冻结积分余额
			UserAccountDTO userAccountDTO2 = new UserAccountDTO();
			userAccountDTO2.setId(userAccount.getId());
			userAccountDTO2.setCiphertext(ciphert);
			userAccountDTO2.setBalance(balance);
			userAccountWriteService.updateUserAccountWithTx(userAccountDTO2);
		}
	}

	private MerchantProductWarehouseStockDTO converSoItemToMps(SoItemDTO soItemDTO) {
		MerchantProductWarehouseStockDTO mpsDto = new MerchantProductWarehouseStockDTO();
		// 商家
//		mpsDto.setMerchantId(soItemDTO.getMerchantId());
		// 商品
		// mpsDto.setMerchantProductId(soItemDTO.getMpId());
//		mpsDto.setSkuId(soItemDTO.getSkuId());
		// 商品购买的数量
//		mpsDto.setRealFrozenStockNum(soItemDTO.getProductItemNum().longValue());

		return mpsDto;
	}

	/**
	 * 根据订单id查询订单冻结积分的量
	 * @param id
	 * @return
	 */
	public BigDecimal findSoFreezeBalanceBySoId(Long id) {
		return soFreezeFubiReadService.findSoFreezeBalanceBySoId(id);
	}
}
