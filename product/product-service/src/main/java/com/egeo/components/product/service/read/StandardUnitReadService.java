package com.egeo.components.product.service.read;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.egeo.components.product.dto.StandardUnitDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface StandardUnitReadService {

	public StandardUnitDTO findStandardUnitById(StandardUnitDTO dto);

	public PageResult<StandardUnitDTO> findStandardUnitOfPage(StandardUnitDTO dto,Pagination page);

	public List<StandardUnitDTO> findStandardUnitAll(StandardUnitDTO dto);
	/**
	 * 根据suid集合查询su信息
	 * @param ids
	 * @return
	 */
	public List<StandardUnitDTO> findBymerchantProdId(List<Long> ids);
	/**
	 * 根据spuid查询所有su的条数
	 * @param standardProductUnitId
	 * @return
	 */
	public int countRecord(Long standardProductUnitId);
	/**
	 * app商品列表
	 * @param vo
	 * @param page
	 * @param req
	 * @return
	 */
	public PageResult<StandardUnitDTO> findStandardUnitOfPageAPP(
			StandardUnitDTO standardUnitDTO, Pagination page);
	/**
	 * 根据类目节点id查询su商品信息
	 * @param vo
	 * @param req
	 * @return
	 */
	public PageResult<StandardUnitDTO> standardUnitStockByCategoryTreeNodeId(
			StandardUnitDTO dto, Pagination page);
	/**
	 * 根据条件查询所有上架suid和名称
	 * @param vo
	 * @param req
	 * @return
	 */
	public List<StandardUnitDTO> findStandardUnitIdAndName(StandardUnitDTO dto);
	/**
	 * 根据类目节点id集合查询su商品信息
	 * @param dto
	 * @param categoryTreeNodeIdList
	 * @param page
	 * @return
	 */
	public PageResult<StandardUnitDTO> standardUnitByFunctionModuleId(StandardUnitDTO dto,
			List<Long> categoryTreeNodeIdList,Integer companyType, Pagination page);
	/**
	 * 根据前台类目节点id、商品集合id分页查询su商品信息
	 * queryId：查询id
	 * type类型：1、类目节点id，2、商品集合id
     * @param couponType
     * @param saleWay
     * @param companyType 商品所有公司可见类型id -1、所有正式公司 -2、所有演示公司 -3、所有竞品公司
     * @param buyType
     */
	public PageResult<StandardUnitDTO> standardUnitByType(
            Integer couponType, Integer saleWay, Long queryId,
            Integer type,
            Long platformId,
            BigDecimal userBalance,
            Long clientId,
            Long enterpriseId,
            Long companyId,
            Integer companyType,
            Long storeId,
            Pagination page, Integer buyType);

	PageResult<StandardUnitDTO> standardUnitByType(
			Integer couponType, Integer saleWay, Long queryId,
			Integer type,
			Long platformId,
			BigDecimal userBalance,
			Long clientId,
			Long enterpriseId,
			Long companyId,
			Integer companyType,
			Long storeId,
			Pagination page, Integer buyType,Integer sellState,String keyWord);
	/**
	 * su商品组合选择商品_su商品列表
	 * @param vo
	 * @param req
	 * @return
	 */
	public PageResult<StandardUnitDTO> findBaseByConditionOfPage(Long standardUnitCombinationId,
			StandardUnitDTO standardUnitDTO, Pagination page);
	/**
	 * 根据su商品id查询su商品信息
	 * @param standardUnitId
	 * @return
	 */
	public StandardUnitDTO findStandardUnitById(Long standardUnitId);
	/**
	 * 根据su组合id查询su商品信息
	 * @param standardUnitCombinationId
	 * @return
	 */
	public List<StandardUnitDTO> findByStandardUnitCombinationId(
			Long standardUnitCombinationId,Long platformId);
	/**
	 * 根据条件分页查询su商品信息
	 * @param dto
	 * @param page
	 * @return
	 */
	public PageResult<StandardUnitDTO> findStandardUnitExtendOfPage(
			StandardUnitDTO dto, Pagination page,List<Long> standardUnitIdList);

	/**
	 * 模糊查询商品列表
	 * @param dto
	 * @param page
	 * @return
	 */
	public PageResult<StandardUnitDTO> queryStandardUnitListByBlurry(StandardUnitDTO dto, Long excludeId, Pagination page);
	/**
	 * 根据关键词搜索商品
	 *
     *
     * @param saleWay
     * @param storeId
     * @param name 关键词
     * @param userBalance 积分余额
     * @param clientId 客户端id
     * @param companyId 公司id
     * @param platformId 平台id
     * @param page 分页信息
     * @param buyType
     * @return
	 */
	public PageResult<StandardUnitDTO> findByKeywordOfPage(
            Integer saleWay, Long storeId, String name, BigDecimal userBalance, Long clientId,
            Long companyId, Long platformId, Integer companyType, Pagination page, Integer buyType);

	/**
	 * 通过su的id查询所有的商品组合的id
	 * @param suId
	 * @return
	 */
	public List<Long> querySuCombinationBySuId(Long suId);
	/**
	 * 根据su商品名称查询所有su商品
	 *
     * @param platformId
     * @param standardUnitId
     * @param standardUnitName
     * @return
	 */
	public List<StandardUnitDTO> findByStandardUnitName(Long platformId, Long standardUnitId, String standardUnitName);

	/**
	 * 多条件分页才查询su详情
	 * @param page
	 * @param param
	 * @return
	 */
	PageResult<StandardUnitDTO> querySuByCategoryTreeNodeIds(Pagination page, Map<String, Object> param);
	/**
	 * 根据门店菜单id查询su商品列表
	 * @param storeMenuNodeId
	 * @param page
	 * @return
	 */
	public PageResult<StandardUnitDTO> findStandardUnitByStoreMenuIdOfPage(
			Long storeMenuNodeId,Long platformId, Pagination page);
	/**
	 * 根据门店id查询su商品信息
	 * @param storeId
	 * @param platformId
	 * @param page
	 * @return
	 */
	public PageResult<StandardUnitDTO> findStandardUnitByStoreIdOfPage(
			Long storeId, Long platformId, Pagination page);

	/**
	 * 多条件分页才查询su详情
	 * @param page
	 * @param param
	 * @return
	 */
	PageResult<StandardUnitDTO> querySuByFrontCategoryTreeNodeIds(Pagination page, Map<String, Object> param, List<Long> frontCategoryTreeNodeIds);

	/**
	 * 查询优惠券关联的商品在指定门店下的个数（确定是否显示立即使用按钮）
	 * @param suId
	 * @param storeId
	 * @param companyId
	 * @param companyType
	 * @param platformId
	 * @return
	 */
	int countCouponSuBySuId(Long suId, Long storeId, Long companyId, Integer companyType, Long platformId);

	/**
	 * 查询优惠券关联的商品组在指定门店下的个数（确定是否显示立即使用按钮）
	 * @param suCombinationId
	 * @param storeId
	 * @param companyId
	 * @param companyType
	 * @param platformId
	 * @return
	 */
	int countCouponSuBySuCombinationId(Long suCombinationId, Long storeId, Long companyId, Integer companyType, Long platformId);

	public List<Map<String, Object>> findSpuInfo(List<Long> suIdList);

	public List<Map<String, Object>> findPictureInfo(List<Long> suIdList);

	Map<Long, List<Long>> findSuCombinationMap(Long suId, Long platformId);

    List<Long> findSuIdByStandardUnitCombinationId(Long suCombId, Long platformId);

    Long findLastId();

    Integer findStandardUnitCount();

    List<StandardUnitDTO> findStandardUnitAllByPage(Integer i, Integer pageSize);
}
