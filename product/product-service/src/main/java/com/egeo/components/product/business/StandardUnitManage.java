package com.egeo.components.product.business;

import java.util.List;
import java.util.Map;

import com.egeo.components.product.dto.StandardUnitDTO;
import com.egeo.components.product.vo.StandardUnitExportVO;
import com.egeo.components.promotion.dto.CouponUnitDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.JsonResult;

public interface StandardUnitManage {

	public StandardUnitDTO findStandardUnitById(StandardUnitDTO dto);

	public PageResult<StandardUnitDTO> findStandardUnitOfPage(StandardUnitDTO dto,Pagination page);

	public List<StandardUnitDTO> findStandardUnitAll(StandardUnitDTO dto);

	Long insertStandardUnitWithTx(StandardUnitDTO dto);

	int updateStandardUnitWithTx(StandardUnitDTO dto);

	int deleteStandardUnitWithTx(StandardUnitDTO dto);
	/**
	 * su上下架
	 * @param vo
	 * @param req
	 * @return
	 */
	public int putawaySoldOut(StandardUnitDTO dto,int type);
	/**
	 * 分页显示所有在线su的库存信息
	 * @param vo
	 * @param req
	 * @return
	 */
	public PageResult<Map<String, Object>> findStandardUnitOfPageStock(StandardUnitDTO dto, Pagination page);
	/**
	 * 根据suid查询在线su的库存信息
	 * @param vo
	 * @param req
	 * @return
	 */
	public Map<String, Object> findStandardUnitStockById(StandardUnitDTO dto);
	/**
	 * app商品列表
	 * @param vo
	 * @param page
	 * @param req
	 * @return
	 */
	public PageResult<Map<String, Object>> findStandardUnitOfPageAPP(StandardUnitDTO dto, Pagination page);
	/**
	 * 根据类目节点id查询su商品信息
	 * @param vo
	 * @param req
	 * @return
	 */
	public PageResult<Map<String, Object>> standardUnitStockByCategoryTreeNodeId(StandardUnitDTO dto, Pagination page);
	/**
	 * 根据suid查询su基本信息和销售量
	 * @param standardUnitId
	 * @return
	 */
	public Map<String, Object> standardUnitByStandardUnitId(Long standardUnitId,Long companyId,Long platformId,Long clientId, Long storeId, Long userId,Integer source,String channelProductId);
	/**
	 * 根据条件查询所有上架suid和名称
	 * @param vo
	 * @param req
	 * @return
	 */
	public List<Map<String, Object>> findStandardUnitIdAndName(StandardUnitDTO dto);
	/**
	 * 根据功能模版id查询su商品信息
	 * @param vo
	 * @param req
	 * @return
	 */
	public PageResult<Map<String, Object>> standardUnitByFunctionModuleId(StandardUnitDTO dto, Long functionModuleId,
			Pagination page);
	/**
	 * 根据类目节点id或商品组合id查询su商品列表
	 * @param vo
	 * @param req
	 * @param saleWay
     * @param buyType
     * @return
	 */
	public PageResult<Map<String, Object>> findByCategoryTreeNodeIdOrSUCombination(Integer saleWay, Long categoryTreeNodeId,
                                                                                   Long standardUnitCombinationId, Integer type, Integer fubiPay, Long userId, Long clientId, Long enterrprisetId,Long companyId,
                                                                                   Long platformId, Long couponUnitId, Pagination page, Long storeId, Integer buyType,Integer sellState,String keyWord);
	/**
	 * su商品组合选择商品_su商品列表
	 * @param vo
	 * @param req
	 * @return
	 */
	public PageResult<Map<String, Object>> findBaseByConditionOfPage(Long standardUnitCombinationId,StandardUnitDTO standardUnitDTO, Pagination page);
	/**
	 * 根据su商品名称查询所有su商品
	 *
     * @param platformId
     * @param standardUnitName
     * @return
	 */
	public List<Map<String, Object>> findByStandardUnitName(Long platformId, Long standardUnitId, String standardUnitName);
	/**
	 * 根据suid查询su真实数据
	 * @param standardUnitId
	 * @return
	 */
	public StandardUnitExportVO queryByStandardUnitId(Long standardUnitId);
	/**
	 * 限购规则分页显示已上架su商品列表
	 * @param dto
	 * @param page
	 * @return
	 */
	public PageResult<Map<String, Object>> findStandardUnitMapOfPage(StandardUnitDTO dto, Pagination page,List<Long> standardUnitIdList);

	/**
	 * 查询su列表
	 * @param vo
	 * @return
	 */
	public PageResult<Map<String, Object>> queryStandardUnitListByBlurry(StandardUnitDTO dto, Long excludeId, Pagination page);
	/**
	 * 根据关键词搜索商品
	 *
     *
     * @param saleWay
     * @param storeId
     * @param name 搜索关键词
     * @param fubiPay 是否积分支付
     * @param clientId 客户端id
     * @param companyId 公司id
     * @param platformId 平台id
     * @param page 分页信息
     * @param buyType
     * @return
	 */
	public PageResult<Map<String, Object>> findByKeywordOfPage(Integer saleWay, Long storeId, String name, Long userId, Integer fubiPay, Long clientId, Long companyId, Long platformId, Pagination page, Integer buyType);
	/**
	 * 刷新su商品搜索规则数据信息
	 * @return
	 */
	public JsonResult<Map<String, Object>> syncSaveSuSerachRule();

	/**
	 *  商品组合,关联前台目录树,商品详情展示,根据类目节点id查询su商品信息
	 * @param page
	 * @param categoryTreeNodeIds
	 * @param param
	 * @return
	 */
	PageResult<Map<String, Object>> querySuByCategoryTreeNodeIds(Pagination page, List<Long> categoryTreeNodeIds,
																 Map<String, Object> param);
	/**
	 * 根据门店菜单id查询su商品列表
	 * @param storeMenuNodeId
	 * @param page
	 * @return
	 */
	public Map<String, Object> findStandardUnitByStoreMenuIdOfPage(
			Long storeMenuNodeId,Long storeId,Long platformId, Pagination page);

    public CouponUnitDTO findCouponUnitByCouponUnitId(Long couponUnitId);

    Integer findSuStatus(Long suId);

	Long findPuStock(Long puId, Long storeId);

    void checkJdProductDetail(Long standardUnitId);

    int updateSuVisible(StandardUnitDTO standardUnitDTO);

    int updateMerchantProductVisible(Long standardUnitId, Integer status);
}
