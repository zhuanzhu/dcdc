package com.egeo.components.order.service.read;

import java.util.List;

import com.egeo.components.order.dto.SoItemDTO;

public interface SoItemReadService {

	/**
	 * 根据商品id列表查询相关的订单项列表
	 * @param merchantIdList
	 * @param platformId
	 * @return
	 */
	List<SoItemDTO> querySoItemListByMerchantIds(List<Long> merchantIdList, Long platformId);

	/**
	 * 查询订单项
	 * @param orderId
	 * @return
	 */
	List<SoItemDTO> querySoItemBySoId(Long soId);
	/**
	 * 根据包裹id查询订单项
	 * @param packId
	 * @param platformId
	 * @return
	 */
	List<SoItemDTO> querySoItemListByPackId(Long packId, Long platformId);

	List<SoItemDTO> findAll(SoItemDTO soItemDTO);
	/**
	 * 根据包裹id查询订单项信息
	 * @param id
	 * @return
	 */
	List<SoItemDTO> soItemByPackageId(Long id);

	/**
	 * 根据订单编号查询订单项
	 * @param id
	 * @return
	 */
	List<SoItemDTO> querySoItemListBySoId(Long id);
	/**
	 * 查询是否该订单是否存在unit商品
	 * @param soItemDTO
	 * @return
	 */
	List<SoItemDTO> findAllBySoIdAndUnitExist(SoItemDTO soItemDTO);

	/**
	 * 根据子订单id查询订单项列表
	 * @param id
	 * @return
	 */
	List<SoItemDTO> querySoItemsBySoChildId(Long id);

    Long findPUNum(Long id);

	Long findPuIdBySoChildId(Long soChildId);

    Long findSoChildPUNum(Long id);

    List<Long> findPuIdBySoId(Long id);

	List<SoItemDTO> findSoItemByPuId(Long puId, Integer status);

	List<SoItemDTO> findSoItemByPuId(List<Long> puIds, Integer status);

	List<String> findProductIdsSoChild(Long soChildId);

	List<SoItemDTO> findSoItemsSoChild(Long soChildId);

	SoItemDTO getById(Long id);

	List<SoItemDTO> getByIds(List<Long> ids);
}
