package com.egeo.components.stock.manage.write;

import java.util.List;

import com.egeo.components.stock.po.CommodityProductUnitWarehouseStockPO;


public interface CommodityProductUnitWarehouseStockWriteManage {

	Long insertCommodityProductUnitWarehouseStockWithTx(CommodityProductUnitWarehouseStockPO po);

	int updateCommodityProductUnitWarehouseStockWithTx(CommodityProductUnitWarehouseStockPO po);

	int deleteCommodityProductUnitWarehouseStockWithTx(CommodityProductUnitWarehouseStockPO po);
	/**
	 * 进货
	 * @param vo
	 * @param req
	 * @return
	 */
	int merchandiseStock(CommodityProductUnitWarehouseStockPO po);
	/**
	 * 出货
	 * @param vo
	 * @param req
	 * @return
	 */
	int deliverStockWithTx(CommodityProductUnitWarehouseStockPO po);

	/**
	 * 冻结库存
	 * @param puId
	 * @param puCount
	 * @return
	 */
	int freezeStockWithTx(Long puId, Integer puCount,String orderCode,Integer type,String productUnitSerialNumber,String puName);
	/**
	 * 根据puid扣除冻结库存扣除库存
	 * @param puId
	 * @param puCount
	 * @return
	 */
	int unfreezeAndDeductStockWithTx(Long puId, Integer puCount,Integer type,String productUnitSerialNumber,String puName);

	/**
	 * 解冻订单冻结库存
	 * @param puId
	 * @param puCount
	 * @return
	 */
	int unFreeseOrderStockWithTx(Long puId, Integer puCount,String orderCode,Integer type,String productUnitSerialNumber,String puName);

	/**
	 * 回复订单库存
	 * @param puId
	 * @param puCount
	 * @return
	 */
	int recoverOrderStockWithTx(Long puId, Integer puCount,String orderCode,Integer type,String productUnitSerialNumber,String puName);
	/**
	 * 批量根据pu生成库存信息
	 * 
	 * @param puIdList
	 * @param merchantProductId
	 * @param platformId
	 */
	int insertCommodityProductUnitWarehouseStockAllWithTx(List<Long> puIdList, Long merchantProductId, Long platformId,Long merchantId);
	
	/**
	 * 库存同步
	 * @param puId
	 * @param quantity
	 * @param puSerialNumber
	 * @param puName
	 */
	void syncStockWithTx(Long puId, Long quantity, String puSerialNumber, String puName);

	int merchandiseStockBatch(List<Long> pos,Long realStockNum);

	int deliverStockBatchWithTx(List<Long> puids, Long realStockNum);

	int freezeStockBatchWithTx(List<Long> puIds, Integer puCount);

	int unfreezeAndDeductStockBatchWithTx(List<Long> puIds, Integer puCount);

	int unFreeseOrderStockBatchWithTx(List<Long> puIds, Integer puCount);

	int recoverOrderStockBatchWithTx(List<Long> puIds, Integer puCount);

	/**
	 * 批量设置库存 ，冻结库存
	 * @param puIds
	 * @param nowRealStockNum
	 * @param nowRealFrozenStockNum
	 */
	void updateCommodityProductUnitWarehouseStockWithTx(List<Long> puIds, Long nowRealStockNum,
			Long nowRealFrozenStockNum);

    void saveCommodityProductUnitWarehouseStock(List<CommodityProductUnitWarehouseStockPO> commodityProductUnitWarehouseStockPOList);

    void updateJdPUStock();
}
	