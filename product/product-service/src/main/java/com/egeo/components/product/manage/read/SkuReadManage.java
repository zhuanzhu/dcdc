package com.egeo.components.product.manage.read;

import java.util.List;
import java.util.Map;

import com.egeo.components.product.condition.SkuCondition;
import com.egeo.components.product.po.SkuPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface SkuReadManage {

	public SkuPO findSkuById(SkuPO po);

	public PageResult<SkuCondition> findSkuOfPage(SkuPO po,Pagination page);

	public List<SkuCondition> findSkuAll(SkuPO po);
	/**
	 * 分页查询所有电子卡券sku
	 * @param vo
	 * @param req
	 * @return
	 */
	public List<SkuCondition> findSkuECardOfPage(SkuPO po);
	/**
	 * 根据sku编号查询sku电子卡券信息
	 * @param skuSerialNumber
	 * @return
	 */
	public SkuPO findSkuECardBySkuSerialNumber(String skuSerialNumber);
	/**
	 * 查询pu序列号
	 * @param skuId
	 * @return
	 */
	public String productUnitSerialNumberBySkuId(Long skuId);

	/**
	 * 根据skuId查询规格信息
	 * @param skuId
	 * @return
	 */
	public Map<String, String> queryStandardBySkuId(Long skuId);

    List<SkuPO> findSkuLikeName(String linkedSkuName,Long platformId);
	List<Long> getMembershipSku(Long platformId);

    SkuPO findSkuByPuId(Long puId);

	/**
	 * 根据预警属性级别id查询sku集合的id和预警天数
	 */
    Map<Long, String> findSkuIdAndPreDaysByPreAttNameId(Long precautiousAttNameId);

    Long findLastId();

    List<SkuCondition> findSkuListConByIdList(List<Long> idList);

	List<SkuPO> findSkuBySkuSerialNos(List<String> skuSerialNos);

	List<SkuPO> findSkuBySkuIds(List<Long> skuIds);

}
	