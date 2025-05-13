package com.egeo.components.stock.service.write;

import java.io.Serializable;
import java.util.List;

import com.egeo.components.product.dto.CommodityProductUnitDTO;
import com.egeo.components.stock.dto.CommodityProductUnitWarehouseStockDTO;

public interface CommodityProductUnitWarehouseStockWriteService extends Serializable {

	public Long insertCommodityProductUnitWarehouseStockWithTx(CommodityProductUnitWarehouseStockDTO dto);

	public int updateCommodityProductUnitWarehouseStockWithTx(CommodityProductUnitWarehouseStockDTO dto);

	public int deleteCommodityProductUnitWarehouseStockWithTx(CommodityProductUnitWarehouseStockDTO dto);

	/**
	 * 进货
	 * @param dto commodityProductUnitId : puId,realStockNum : 进货数量
	 * @param userId 用户id
	 * @param userName 用户名称
	 * @param ip 
	 * @param mac
	 * @param productUnitSerialNumber pu序列号
	 * @param puName pu名称
	 * @return
	 */
	public int merchandiseStockWithTx(CommodityProductUnitWarehouseStockDTO dto, Long userId, String userName,
			String ip, String mac, String productUnitSerialNumber, String puName);

	/**
	 * 出货
	 * @param dto commodityProductUnitId : puId,realStockNum : 出货数量
	 * @param userId 用户id
	 * @param userName 用户名称
	 * @param ip 
	 * @param mac
	 * @param productUnitSerialNumber pu序列号
	 * @param puName pu名称
	 */
	public int deliverStockWithTx(CommodityProductUnitWarehouseStockDTO dto, Long userId, String userName, String ip,
			String mac, String productUnitSerialNumber, String puName);

	/**
	 * 删除redis中的缓存
	 * 
	 * @param commodityProductUnitWarehouseStockDTO
	 */
	public void removeCommodityProductUnitWarehouseStockCache(
			CommodityProductUnitWarehouseStockDTO commodityProductUnitWarehouseStockDTO);

	/**
	 * 冻结库存
	 * 
	 * @param puId
	 * @param puCount
	 * @return
	 */
	public int freezeStockWithTx(Long puId, Integer puCount, String orderCode, Integer type,
			String productUnitSerialNumber, String puName, Long userId, String userName, String ip, String mac);

	/**
	 * 根据puid扣除冻结库存扣除库存
	 * 
	 * @param puId
	 * @param puCount
	 * @return
	 */
	public int unfreezeAndDeductStockWithTx(Long puId, Integer puCount, Integer type, String productUnitSerialNumber,
			String puName, String orderCode, Long userId, String userName, String ip, String mac);

	/**
	 * 仅解冻订单冻结库存
	 * 
	 * @param puId
	 * @param puCount
	 */
	public int unFreeseOrderStock(Long puId, Integer puCount, String orderCode, Integer type,
			String productUnitSerialNumber, String puName, Long userId, String userName, String ip, String mac);

	/**
	 * 回复订单库存
	 * 
	 * @param puId
	 * @param puCount
	 */
	public int recoverOrderStock(Long puId, Integer puCount, String orderCode, Integer type,
			String productUnitSerialNumber, String puName, Long userId, String userName, String ip, String mac);
	/**
	 * 批量根据pu生成库存信息
	 * @param puIdList
	 * @param merchantProductId
	 * @param platformId
	 */
	public int insertCommodityProductUnitWarehouseStockAllWithTx(List<Long> puIdList, Long merchantProductId,
			Long platformId);
	
	/**
	 * 库存同步
	 * @param puId
	 * @param quantity
	 * @param puSerialNumber
	 * @param puName
	 */
	void syncStockWithTx(Long puId, Long quantity, String puSerialNumber, String puName);


	int deliverStockBatchWithTx(CommodityProductUnitWarehouseStockDTO dto, Long userId, String userName, String ip,
			String mac, List<Long> puIds,List<CommodityProductUnitDTO> commodityProductUnitDTOs);

	int freezeStockBatchWithTx(Long puId, Integer puCount, String orderCode, Integer type,
			 Long userId, String userName, String ip, String mac,
			List<Long> puIds,List<CommodityProductUnitDTO> commodityProductUnitDTOs);

	int unfreezeAndDeductStockBatchWithTx(Long puId, Integer puCount, Integer type, 
			 String orderCode, Long userId, String userName, String ip, String mac, List<Long> puIds,List<CommodityProductUnitDTO> commodityProductUnitDTOs);

	int unFreeseOrderStockBatch(Long puId, Integer puCount, String orderCode, Integer type,
			 Long userId, String userName, String ip, String mac, List<Long> puIds,List<CommodityProductUnitDTO> commodityProductUnitDTOs);

	int merchandiseStockBatchWithTx(CommodityProductUnitWarehouseStockDTO dto, Long userId, String userName, String ip,
			String mac, List<Long> puIds,List<CommodityProductUnitDTO> commodityProductUnitDTOs);

	/**
	 * 根据Puid 批量设置 当前库存，当前冻结库存
	 * @param puIds
	 * @param nowRealStockNum
	 * @param nowRealFrozenStockNum
	 */
	public void updateCommodityProductUnitWarehouseStockWithTx(List<Long> puIds, Long nowRealStockNum,
			Long nowRealFrozenStockNum);

	public void recoverOrderStockBatch(Long puId, Integer puCount, String orderCode, int status, Long userId,
			String userName, String ip, String mac, List<Long> puIdList,
			List<CommodityProductUnitDTO> commodityProductUnitDTOs);

	/**
	 * 初始化pu时 初始化库存信息
	 * 如果有共用库存 则计算库存 并生成流水
	 * @param commodityProductUnitDTOs
	 */
	public void initPuStock(List<CommodityProductUnitDTO> commodityProductUnitDTOs,Long suId);

    void saveCommodityProductUnitWarehouseStock(List<Long> puIdList, List<Long> suIdList, List<Long> merchantProductIdList);

    void updateJdPUStock();
}
