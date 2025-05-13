package com.egeo.components.order.manage.read;

import java.util.List;

import com.egeo.components.order.condition.SoItemCondition;
import com.egeo.components.order.dto.SoItemDTO;
import com.egeo.components.order.po.SoItemPO;

public interface SoItemReadManage {

	List<SoItemPO> findSoItemList(SoItemPO soItemPO);

	List<SoItemPO> querySoItemListByMerchantIds(List<Long> merchantIdList, Long platformId);

	/**
	 * 查询订单项
	 * @param orderCode
	 * @return
	 */
	List<SoItemPO> querySoItemBySoId(Long soId);
	List<SoItemPO> findSoItemsBySoIds(List<Long> soIds);
	List<SoItemPO> querySoItemListByPackId(Long packId, Long platformId);

	List<SoItemPO> findAll(SoItemPO po);
	/**
	 * 根据包裹id查询订单项
	 * @param id
	 * @return
	 */
	List<SoItemPO> soItemByPackageId(Long id);

	/**
	 * 根据订单编号查询订单项
	 * @param id
	 * @return
	 */
	List<SoItemPO> querySoItemListBySoId(Long id);
	/**
	 * 查询是否该订单是否存在unit商品
	 * @param soItemDTO
	 * @return
	 */
	List<SoItemCondition> findAllBySoIdAndUnitExist(SoItemPO po);

    Long finPUNum(Long id);

    Long findPuIdBySoChildId(Long soChildId);

    Long findSoChildPUNum(Long id);

    List<Long> findPuIdBySoId(Long id);

	List<SoItemCondition> findSoItemByPuId(Long puId, Integer status);

	List<SoItemCondition> findSoItemByPuId(List<Long> puIds, Integer status);

	List<String> findProductIdsSoChild(Long soChildId);

	List<SoItemPO> findSoItemsSoChild(Long soChildId);

	SoItemPO getById(Long id);

	List<SoItemPO> getByIds(List<Long> ids);
}
