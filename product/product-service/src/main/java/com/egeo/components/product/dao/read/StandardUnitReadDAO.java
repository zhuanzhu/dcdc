package com.egeo.components.product.dao.read;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.condition.StandardUnitCondition;
import com.egeo.components.product.po.StandardUnitPO;
import com.egeo.orm.BaseReadDAO;
import com.egeo.orm.Pagination;

public interface StandardUnitReadDAO extends BaseReadDAO<StandardUnitPO> {
	/**
	 * 根据suid集合查询su商品信息
	 * 
	 * @param ids
	 * @return
	 */
	List<StandardUnitCondition> findBymerchantProdId(@Param("ids") List<Long> ids);

	/**
	 * 根据spuid查询所有su的条数
	 * 
	 * @param standardProductUnitId
	 * @return
	 */
	int countRecord(@Param("standardProductUnitId") Long standardProductUnitId);

	/**
	 * app商品列表条数
	 * 
	 * @param vo
	 * @param page
	 * @param req
	 * @return
	 */
	int countOfPageAPP(@Param("po") StandardUnitPO po);

	/**
	 * app商品列表
	 * 
	 * @param vo
	 * @param page
	 * @param req
	 * @return
	 */
	List<StandardUnitCondition> findStandardUnitOfPageAPP(@Param("po") StandardUnitPO po,
			@Param("page") Pagination page);

	/**
	 * 根据类目节点id查询su商品条数
	 * 
	 * @param vo
	 * @param req
	 * @return
	 */
	int countStandardUnitStockByCategoryTreeNodeIdAPP(@Param("po") StandardUnitPO po);

	/**
	 * 根据类目节点id查询su商品信息
	 * 
	 * @param vo
	 * @param req
	 * @return
	 */
	List<StandardUnitCondition> standardUnitStockByCategoryTreeNodeId(@Param("po") StandardUnitPO po,
			@Param("page") Pagination page);

	/**
	 * 根据条件查询所有上架suid和名称
	 * 
	 * @param vo
	 * @param req
	 * @return
	 */
	List<StandardUnitPO> findStandardUnitIdAndName(@Param("po") StandardUnitPO po , @Param("page") Pagination page);

	/**
	 * 根据类目节点id集合查询su商品信息
	 * 
	 * @param dto
	 * @param couponType
	 *@param categoryTreeNodeIdList
	 * @param page   @return
	 */
	List<StandardUnitCondition> standardUnitByFunctionModuleId(
			@Param("couponType")Integer couponType, @Param("po") StandardUnitPO po,
			@Param("ids") List<Long> categoryTreeNodeIdList,
			@Param("companyType") Integer companyType,
			@Param("page") Pagination page);

	/**
	 * 根据类目节点id集合查询su商品条数
	 * 
	 * @param dto
	 * @param page
	 * @param couponType
	 *@param categoryTreeNodeIdList  @return
	 */
	int countstandardUnitByFunctionModuleId(@Param("couponType")Integer couponType, @Param("po") StandardUnitPO po,
											@Param("ids") List<Long> categoryTreeNodeIdList, @Param("companyType") Integer companyType);

	/**
	 * 根据商品集合id查询su商品信息
	 * @param queryId
	 * @param couponType
	 * @param saleWay
	 * @param clientId
	 * @param companyId
	 * @param platformId
	 * @param page     @return
	 * @param buyType      */
	List<StandardUnitCondition> standardUnitByStandardUnitCombinationId(
			@Param("couponType") Integer couponType, @Param("saleWay") Integer saleWay, @Param("standardUnitCombinationId") Long standardUnitCombinationId,
			@Param("salePrice") BigDecimal userBalance, @Param("clientId") Long clientId,
			@Param("enterpriseId") Long enterpriseId,@Param("companyId") Long companyId, @Param("companyType") Integer companyType,
			@Param("platformId") Long platformId, @Param("storeId") Long storeId, @Param("page") Pagination page, @Param("buyType") Integer buyType,@Param("frontSerialNumber")Integer frontSerialNumber);

	/**
	 * 根据商品集合id查询su商品条数
     * @param queryId
     * @param page
     * @param couponType
     * @param saleWay
     * @param clientId
     * @param companyId
     * @param platformId    @return
     * @param buyType      */
	int countstandardUnitByStandardUnitCombinationId(@Param("couponType") Integer couponType, @Param("saleWay") Integer saleWay, @Param("standardUnitCombinationId") Long standardUnitCombinationId,
                                                     @Param("salePrice") BigDecimal userBalance, @Param("clientId") Long clientId,
                                                     @Param("enterpriseId") Long enterpriseId,@Param("companyId") Long companyId, @Param("companyType") Integer companyType, @Param("platformId") Long platformId,
                                                     @Param("storeId") Long storeId, @Param("buyType") Integer buyType,@Param("frontSerialNumber") Integer frontSerialNumber, @Param("page") Pagination page);

	
	

	List<StandardUnitCondition> standardUnitByStandardUnitCombinationIdAndSuId(
			@Param("couponType") Integer couponType, @Param("saleWay") Integer saleWay, @Param("standardUnitCombinationId") Long standardUnitCombinationId,
			@Param("salePrice") BigDecimal userBalance, @Param("clientId") Long clientId,
			@Param("enterpriseId") Long enterpriseId,@Param("companyId") Long companyId, @Param("companyType") Integer companyType,
			@Param("platformId") Long platformId, @Param("storeId") Long storeId, @Param("page") Pagination page,
			@Param("buyType") Integer buyType,@Param("frontSerialNumber")Integer frontSerialNumber,@Param("suIds") List<Long> suIds,
			@Param("showAll") Integer showAll);

	/**
	 * su商品组合选择商品_su商品条数
	 * 
	 * @param vo
	 * @param req
	 * @return
	 */
	int countfindBaseByConditionOfPage(@Param("standardUnitCombinationId") Long standardUnitCombinationId,
			@Param("po") StandardUnitPO po, @Param("page") Pagination page);

	/**
	 * su商品组合选择商品_su商品列表
	 * 
	 * @param vo
	 * @param req
	 * @return
	 */
	List<StandardUnitPO> findBaseByConditionOfPage(@Param("standardUnitCombinationId") Long standardUnitCombinationId,
			@Param("po") StandardUnitPO po, @Param("page") Pagination page);

	/**
	 * 根据类目类型su组合id查询su商品列表
	 * 
	 * @param standardUnitCombinationId
	 * @return
	 */
	List<StandardUnitPO> findByStandardUnitCombinationIdOfPage(
			@Param("standardUnitCombinationId") Long standardUnitCombinationId, @Param("page") Pagination page);

	/**
	 * 根据su商品id查询su商品信息
	 * 
	 * @param standardUnitId
	 * @return
	 */
	StandardUnitCondition findStandardUnitById(@Param("standardUnitId") Long standardUnitId);

	/**
	 * 根据标签类型商品组合id查询su商品列表
	 * 
	 *
	 *
	 * @param couponType
	 * @param saleWay
	 * @param standardUnitCombinationId
	 * @param userBalance
	 * @param clientId
	 * @param companyId
	 * @param platformId
	 * @param page
	 * @param buyType
	 * @return
	 */
	List<StandardUnitCondition> findByTypeTag(@Param("couponType") Integer couponType, @Param("saleWay") Integer saleWay, @Param("standardUnitCombinationId") Long standardUnitCombinationId,
											  @Param("salePrice") BigDecimal userBalance, @Param("clientId") Long clientId,
											  @Param("companyId") Long companyId, @Param("companyType") Integer companyType,
											  @Param("platformId") Long platformId, @Param("storeId") Long storeId, @Param("page") Pagination page, @Param("buyType") Integer buyType,@Param("frontSerialNumber")Integer frontSerialNumber);

	/**
	 * 根据标签类型商品组合id查询su商品列表
	 * @param page
	 * @param couponType
	 * @param saleWay
	 * @param standardUnitCombinationId
	 * @param userBalance
	 * @param clientId
	 * @param companyId
	 * @param platformId      @return
	 * @param buyType       */
	int countByTypeTag(@Param("couponType") Integer couponType, @Param("saleWay") Integer saleWay, @Param("standardUnitCombinationId") Long standardUnitCombinationId,
					   @Param("salePrice") BigDecimal userBalance, @Param("clientId") Long clientId,
					   @Param("companyId") Long companyId, @Param("companyType") Integer companyType,
					   @Param("platformId") Long platformId, @Param("storeId") Long storeId, @Param("buyType") Integer buyType,@Param("frontSerialNumber")Integer frontSerialNumber);

	/**
	 * 根据suid集合查询su商品信息
	 * 
	 * @param standardUnitId
	 * @return
	 */
	List<StandardUnitPO> findByStandardUnitIds(@Param("ids") List<Long> standardUnitIds);

	/**
	 * 根据类目节点id查询su商品信息
	 * 
	 * @param newList
	 * @return
	 */
	List<StandardUnitPO> findByStandardProductUnitIds(@Param("ids") List<Long> newList);

	/**
	 * 根据商品标签id集合查询su商品信息
	 * 
	 * @param tagIds
	 * @return
	 */
	List<StandardUnitPO> findByTagIds(@Param("ids") List<Long> tagIds);

	/**
	 * 根据条件分页查询su商品及扩展信息
	 * 
	 * @param dto
	 * @param page
	 * @return
	 */
	List<StandardUnitCondition> findStandardUnitExtendOfPage(@Param("po") StandardUnitPO po,
			@Param("page") Pagination page, @Param("ids") List<Long> standardUnitIdList);

	/**
	 * 根据条件分页查询su商品及扩展信息条数
	 * 
	 * @param dto
	 * @param page
	 * @return
	 */
	int countStandardUnitExtendOfPage(@Param("po") StandardUnitPO po, @Param("ids") List<Long> standardUnitIdList);

	/**
	 * 商品列表
	 * 
	 * @param po
	 * @return
	 */
	int countStandardUnitListByBlurry(@Param("po") StandardUnitPO po, @Param("excludeId")Long excludeId);

	List<StandardUnitPO> queryStandardUnitListByBlurry(@Param("po") StandardUnitPO po, @Param("excludeId")Long excludeId, @Param("page") Pagination page);
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
	List<StandardUnitCondition> findByKeywordOfPage(
			@Param("saleWay") Integer saleWay, @Param("storeId") Long storeId, @Param("name") String name,
			@Param("salePrice") BigDecimal userBalance,
			@Param("clientId") Long clientId,
			@Param("companyId") Long companyId,
			@Param("enterpriseId") Long enterpriseId,
			@Param("platformId") Long platformId,
			@Param("companyType") Integer companyType,
			@Param("page") Pagination page, @Param("buyType") Integer buyType,@Param("frontSerialNumber")Integer frontSerialNumber);
	/**
	 * 根据关键词搜索商品条数
	 * @param page 分页信息
	 * @param saleWay
	 * @param storeId
	 * @param name 关键词
	 * @param userBalance 积分余额
	 * @param clientId 客户端id
	 * @param companyId 公司id
	 * @param platformId 平台id      @return
	 * @param buyType       */
	Integer countByKeywordOfPage(
			@Param("saleWay") Integer saleWay, @Param("storeId") Long storeId, @Param("name") String name,
			@Param("salePrice") BigDecimal userBalance,
			@Param("clientId") Long clientId,
			@Param("companyId") Long companyId,
			@Param("companyType") Integer companyType,
			@Param("enterpriseId") Long enterpriseId,
			@Param("platformId") Long platformId, @Param("buyType") Integer buyType,@Param("frontSerialNumber")Integer frontSerialNumber);

	/**
	 * 通过su的id查询商品组合id集合
	 * @param suId
	 * @return
	 */
	List<Long> querySuCombinationBySu(@Param("suId")Long suId);

	List<Long> querySuCombinationByTag(@Param("suId")Long suId);

	List<Long> querySuCombinationByTreeNode(@Param("suId")Long suId);
	/**
	 * 根据su商品名称查询所有su商品
	 *
     * @param platformId
     * @param standardUnitId
     * @param standardUnitName
     * @return
	 */
	List<StandardUnitPO> findByStandardUnitName(@Param("platformId")Long platformId, @Param("standardUnitId") Long standardUnitId, @Param("standardUnitName") String standardUnitName, @Param("enterpriseId") String enterpriseId);

	int countSuByCategoryTreeNodeIds(@Param("po")Map<String, Object> keys);

	List<StandardUnitCondition> suByCategoryTreeNodeIdsOfPage(@Param("po")Map<String, Object> keys,
															  @Param("page")Pagination page);
	/**
	 * 根据门店菜单id查询su商品列表条数
	 * @param storeMenuNodeId
	 * @param platformId
	 * @return
	 */
	int countStandardUnitByStoreMenuIdOfPage(
			@Param("storeMenuNodeId")Long storeMenuNodeId, 
			@Param("platformId")Long platformId);
	/**
	 * 根据门店菜单id查询su商品列表
	 * @param storeMenuNodeId
	 * @param platformId
	 * @param page
	 * @return
	 */
	List<StandardUnitCondition> findStandardUnitByStoreMenuIdOfPage(
			@Param("storeMenuNodeId")Long storeMenuNodeId, 
			@Param("platformId")Long platformId, 
			@Param("page")Pagination page);
	/**
	 * 根据门店id查询su商品条数
	 * @param storeId
	 * @param platformId
	 * @return
	 */
	int countStandardUnitByStoreIdOfPage(
			@Param("storeId")Long storeId, 
			@Param("platformId")Long platformId);
	/**
	 * 根据门店id查询su商品信息
	 * @param storeId
	 * @param platformId
	 * @param page
	 * @return
	 */
	List<StandardUnitCondition> findStandardUnitByStoreIdOfPage(
			@Param("storeId")Long storeId, 
			@Param("platformId")Long platformId, 
			@Param("page")Pagination page);
	
	/**
	 * 查询优惠券关联的商品在指定门店下的个数（确定是否显示立即使用按钮）
	 * @param suId
	 * @param storeId
	 * @param companyId
	 * @param companyType
	 * @param platformId
	 * @return
	 */
	int countCouponSuBySuId(@Param("suId")Long suId, @Param("storeId")Long storeId, @Param("companyId")Long companyId,
			@Param("companyType")Integer companyType, @Param("platformId")Long platformId);

	/**
	 * 查询优惠券关联的商品组在指定门店下的个数（确定是否显示立即使用按钮）
	 * @param suCombinationId
	 * @param storeId
	 * @param companyId
	 * @param companyType
	 * @param platformId
	 * @return
	 */
	int countCouponSuBySuCombinationId(@Param("suCombinationId")Long suCombinationId, @Param("storeId")Long storeId,
			@Param("companyId")Long companyId, @Param("companyType")Integer companyType, @Param("platformId") Long platformId);

	List<Map<String, Object>> findSpuInfo(@Param("suIdList")List<Long> suIdList);

	List<Map<String, Object>> findPictureInfo(@Param("suIdList")List<Long> suIdList);

    Long findLastId();

	Integer findStandardUnitCount();

	List<StandardUnitPO> findStandardUnitAllByPage(@Param("i")Integer i, @Param("pageSize")Integer pageSize);
}
