package com.egeo.components.stock.dao.write;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.stock.po.CommodityProductUnitWarehouseStockPO;
import com.egeo.orm.BaseWriteDAO;

public interface CommodityProductUnitWarehouseStockWriteDAO extends BaseWriteDAO<CommodityProductUnitWarehouseStockPO> {
	/**
	 * 进货
	 * 
	 * @param vo
	 * @param req
	 * @return
	 */
	int merchandiseStock(@Param("po") CommodityProductUnitWarehouseStockPO po);
	
	/**
	 * 批量进货
	 * 
	 * @param vo
	 * @param req
	 * @return
	 */
	int merchandiseStockBatch(@Param("ids") List<Long> puIds,@Param("realStockNum")Long realStockNum);


	/**
	 * 出货
	 * 
	 * @param vo
	 * @param req
	 * @return
	 */
	int deliverStockWithTx(@Param("po") CommodityProductUnitWarehouseStockPO po);

	/**
	 * 冻结库存,当冻结库存数大于实际库存数时,提交失败
	 * 
	 * @param puId
	 * @param puCount
	 * @return
	 */
	int freezeStockWithTx(@Param("puId") Long puId, @Param("puCount") Integer puCount);
	
	/**
	 * 批量冻结库存,当冻结库存数大于实际库存数时,提交失败
	 * 
	 * @param puId
	 * @param puCount
	 * @return
	 */
	int freezeStockBatchWithTx(@Param("ids") List<Long> puIdList, @Param("puCount") Integer puCount);

	/**
	 * 根据puid扣除冻结库存扣除库存
	 * 
	 * @param puId
	 * @param puCount
	 * @return
	 */
	int unfreezeAndDeductStockWithTx(@Param("puId") Long puId, @Param("puCount") Integer puCount);

	
	/**
	 * 批量根据puid扣除冻结库存扣除库存
	 * 
	 * @param puId
	 * @param puCount
	 * @return
	 */
	int unfreezeAndDeductStockBatchWithTx(@Param("ids") List<Long> puIdList, @Param("puCount") Integer puCount);
	
	/**
	 * 解冻订单冻结库存
	 * 
	 * @param puId
	 * @param puCount
	 * @return
	 */
	int unFreeseOrderStock(@Param("puId") Long puId, @Param("puCount") Integer puCount);

	/**
	 * 回复订单库存
	 * 
	 * @param puId
	 * @param puCount
	 * @return
	 */
	int recoverOrderStock(@Param("puId") Long puId, @Param("puCount") Integer puCount);

	/**
	 * 出口剩余在线库存(在线真实库存=在线冻结库存)
	 * 
	 * @param po
	 * @return
	 */
	int deliverResidueStockWithTx(@Param("po") CommodityProductUnitWarehouseStockPO po);

	/**
	 * 批量根据pu生成库存信息
	 * 
	 * @param puIdList
	 * @param merchantProductId
	 * @param platformId
	 */
	int insertCommodityProductUnitWarehouseStockAllWithTx(@Param("ids") List<Long> puIdList,
			@Param("merchantProductId") Long merchantProductId, @Param("platformId") Long platformId,
			@Param("merchantId") Long merchantId);

	/**
	 * 批量出货
	 * @param puids
	 * @param realStockNum
	 * @return
	 */
	int deliverStockBatchWithTx(@Param("ids") List<Long> puids, @Param("realStockNum") Long realStockNum);

	/**
	 * 批量设置
	 * @param puids
	 * @param realStockNum
	 * @return
	 */
	int deliverResidueStockBatchWithTx(@Param("ids") List<Long> puids, @Param("realStockNum") Long realStockNum);

	/**
	 * 批量解冻订单冻结库存
	 * @param puIds
	 * @param puCount
	 * @return
	 */
	int unFreeseOrderStockBatch(@Param("ids")List<Long> puIds,@Param("puCount") Integer puCount);

	/**
	 * 批量恢复订单库存
	 * @param puIds
	 * @param puCount
	 * @return
	 */
	int recoverOrderStockBatch(@Param("ids")List<Long> puIds,@Param("puCount") Integer puCount);

	/**
	 * 批量设置 库存，冻结库存
	 * @param puIds
	 * @param nowRealStockNum
	 * @param nowRealFrozenStockNum
	 */
	void setCommodityProductUnitWarehouseStockWithTx(@Param("ids")List<Long> puIds,@Param("realStockNum") Long nowRealStockNum,
			@Param("realFrozenStockNum")Long nowRealFrozenStockNum);

    void saveCommodityProductUnitWarehouseStock(@Param("poList")List<CommodityProductUnitWarehouseStockPO> commodityProductUnitWarehouseStockPOList);

    void updateJdPUStock();
}
