package com.egeo.components.product.manage.read;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.egeo.components.product.condition.StandardUnitCondition;
import com.egeo.components.product.po.StandardUnitPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface StandardUnitReadManage {

	public StandardUnitPO findStandardUnitById(StandardUnitPO po);

	public PageResult<StandardUnitPO> findStandardUnitOfPage(StandardUnitPO po,Pagination page);

	public List<StandardUnitPO> findStandardUnitAll(StandardUnitPO po);
	/**
	 * 根据su草稿id集合查询su信息
	 * @param ids
	 * @return
	 */
	public List<StandardUnitCondition> findBymerchantProdId(List<Long> ids);
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
	public PageResult<StandardUnitCondition> findStandardUnitOfPageAPP(StandardUnitPO po, Pagination page);
	/**
	 * 根据类目节点id查询su商品信息
	 * @param vo
	 * @param req
	 * @return
	 */
	public PageResult<StandardUnitCondition> standardUnitStockByCategoryTreeNodeId(StandardUnitPO po, Pagination page);
	/**
	 * 根据条件查询所有上架suid和名称
	 * @param vo
	 * @param req
	 * @return
	 */
	public List<StandardUnitPO> findStandardUnitIdAndName(StandardUnitPO po);
	/**
	 * 根据类目节点id集合查询su商品信息
	 * @param dto
	 * @param couponType
     *@param categoryTreeNodeIdList
     * @param page   @return
	 */
	public PageResult<StandardUnitCondition> standardUnitByFunctionModuleId(Integer couponType, StandardUnitPO po, List<Long> categoryTreeNodeIdList, Integer companyType, Pagination page);
	/**
	 * 根据类目节点id查询su列表信息
	 * @param queryId
	 * @param saleWay
	 * @param page  @return
	 * @param buyType
	 */
	public PageResult<StandardUnitCondition> standardUnitByCategoryTreeNodeId(
			Integer saleWay, Long categoryTreeNodeId,
			Long platformId,
			BigDecimal userBalance,
			Long clientId, Long companyId,
			Integer companyType,
			Long storeId,
			Pagination page,
			Integer buyType);
	/**
	 * 根据商品集合id查询su商品信息
	 * @param queryId
	 * @param couponType
	 * @param saleWay
	 * @param platformId
	 * @param clientId
	 * @param companyId
	 * @param page     @return
	 * @param buyType      */
	public PageResult<StandardUnitCondition> standardUnitByStandardUnitCombinationId(Integer couponType, Integer saleWay, Long standardUnitCombinationId, Long platformId, BigDecimal userBalance,
																					 Long clientId,Long enterpriseId, Long companyId, Integer companyType, Long storeId, Pagination page, Integer buyType);

	PageResult<StandardUnitCondition> standardUnitByStandardUnitCombinationId(Integer couponType, Integer saleWay, Long standardUnitCombinationId, Long platformId, BigDecimal userBalance,
																					 Long clientId,Long enterpriseId, Long companyId, Integer companyType, Long storeId, Pagination page, Integer buyType,Integer sellState,Integer showAll,Boolean isUserClient,String keyWord);
	/**
	 * su商品组合选择商品_su商品列表
	 * @param vo
	 * @param req
	 * @return
	 */
	public PageResult<StandardUnitPO> findBaseByConditionOfPage(Long standardUnitCombinationId,StandardUnitPO po, Pagination page);
	/**
	 * 根据su商品id查询su商品信息
	 * @param standardUnitId
	 * @return
	 */
	public StandardUnitCondition findStandardUnitById(Long standardUnitId);
	/**
	 * 根据su组合id查询su商品信息
	 * @param standardUnitCombinationId
	 * @return
	 */
	public List<StandardUnitPO> findByStandardUnitCombinationId(Long standardUnitCombinationId,Long platformId);
	/**
	 * 根据条件分页查询su商品信息
	 * @param dto
	 * @param page
	 * @return
	 */
	public PageResult<StandardUnitCondition> findStandardUnitExtendOfPage(StandardUnitPO po, Pagination page,List<Long> standardUnitIdList);

	/**
	 * 商品列表
	 * @param po
	 * @param page
	 * @return
	 */
	public PageResult<StandardUnitPO> queryStandardUnitListByBlurry(StandardUnitPO po, Long excludeId, Pagination page);
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
	public PageResult<StandardUnitCondition> findByKeywordOfPage(Integer saleWay, Long storeId, String name, BigDecimal userBalance, Long clientId,
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
	public List<StandardUnitPO> findByStandardUnitName(Long platformId, Long standardUnitId, String standardUnitName);

	/**
	 * 多条件分页才查询su详情
	 * @param page
	 * @param param
	 * @return
	 */
	PageResult<StandardUnitCondition> standardUnitStockByCategoryTreeNodeId(
			Pagination page, Map<String, Object> param);
	/**
	 * 根据门店菜单id查询su商品列表
	 * @param storeMenuNodeId
	 * @param platformId
	 * @param page
	 * @return
	 */
	public PageResult<StandardUnitCondition> findStandardUnitByStoreMenuIdOfPage(
			Long storeMenuNodeId, Long platformId,
			Pagination page);
	/**
	 * 根据门店id查询su商品信息
	 * @param storeId
	 * @param platformId
	 * @param page
	 * @return
	 */
	public PageResult<StandardUnitCondition> findStandardUnitByStoreIdOfPage(Long storeId, Long platformId,
			Pagination page);

	/**
	 * 根据前台类目树节点查询SU列表
	 * @param page
	 * @param keys
	 * @param frontCategoryTreeNodeIds
	 * @return
	 */
	PageResult<StandardUnitCondition> querySuByCategoryTreeNodeIds(Pagination page,
            Map<String, Object> keys, List<Long> frontCategoryTreeNodeIds);

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

    Long findLastId();

	Integer findStandardUnitCount();

	List<StandardUnitPO> findStandardUnitAllByPage(Integer i, Integer pageSize);

	/**
	 * 根据类目节点id查询su列表信息
	 * @param queryId
	 * @param saleWay
	 * @param page  @return
	 * @param buyType
	 */
	public PageResult<StandardUnitCondition> standardUnitByCategoryTreeNodeId(
			Integer saleWay, Long categoryTreeNodeId,
			Long platformId,
			BigDecimal userBalance,
			Long clientId, Long companyId,Long enterpriseId,
			Integer companyType,
			Long storeId,
			Pagination page,
			Integer buyType,String keyWord);
}
