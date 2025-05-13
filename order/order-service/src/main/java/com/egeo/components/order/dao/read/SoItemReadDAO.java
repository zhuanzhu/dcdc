package com.egeo.components.order.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.order.condition.SoItemCondition;
import com.egeo.components.order.po.SoItemPO;
import com.egeo.components.order.po.SoPO;
import com.egeo.orm.BaseReadDAO;
import com.egeo.orm.Pagination;

public interface SoItemReadDAO extends BaseReadDAO<SoItemPO>{

	/**
	 * 根据商品id列表查询相关的订单项列表
	 * @param merchantIdList
	 * @param platformId
	 * @return
	 */
	List<SoItemPO> querySoItemListByMerchantIds(@Param("ids")List<Long> merchantIdList, @Param("platformId")Long platformId);

	/**
	 * 根据包裹id查询订单项
	 * @param id
	 * @return
	 */
	List<SoItemPO> soItemByPackageId(@Param("id")Long id);

	/**
	 * 根据订单编号查询订单项
	 * @param id
	 * @return
	 */
	List<SoItemPO> querySoItemListBySoId(@Param("id")Long id);
	/**
	 * 查询是否该订单是否存在unit商品
	 * @param soItemDTO
	 * @return
	 */
	List<SoItemCondition> findAllBySoIdAndUnitExist(@Param("po")SoItemPO po, @Param("page") Pagination page);

    Long findPUNum(@Param("id") Long id);

    Long findPuIdBySoChildId(@Param("soChildId")Long soChildId);

	Long findSoChildPUNum(@Param("id")Long id);

    List<Long> findPuIdBySoId(@Param("id")Long id);

    List<SoItemCondition> findSoByPuId(@Param("puId") Long puId, @Param("orderConfirmStatus")Integer orderConfirmStatus);

    List<SoItemPO> findSoItemsBySoIds(@Param("soIds") List<Long> soIds);

	List<SoItemCondition> findSoByPuIds(@Param("puIds")List<Long> puIds, @Param("orderConfirmStatus")Integer orderConfirmStatus);

	List<String> findProductIdsSoChild(@Param("soChildId")Long soChildId);

	List<SoItemPO> findSoItemsSoChild(@Param("soChildId")Long soChildId);

	List<SoItemPO> getByIds(@Param("ids") List<Long> ids);
}
