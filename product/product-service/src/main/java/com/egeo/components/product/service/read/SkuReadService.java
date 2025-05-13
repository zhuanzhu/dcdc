package com.egeo.components.product.service.read;


import java.util.List;
import java.util.Map;

import com.egeo.components.product.dto.SkuDTO;
import com.egeo.components.product.dto.SkuPriceDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface SkuReadService {

	public SkuDTO findSkuById(SkuDTO dto);

	public PageResult<SkuDTO> findSkuOfPage(SkuDTO dto,Pagination page);

	public List<SkuDTO> findSkuAll(SkuDTO dto);
	/**
	 * 分页查询所有电子卡券sku
	 * @param vo
	 * @param req
	 * @return
	 */
	public List<SkuDTO> findSkuECardOfPage(SkuDTO dto);
	/**
	 * 根据sku编号查询sku电子卡券信息
	 * @param skuSerialNumber
	 * @return
	 */
	public SkuDTO findSkuECardBySkuSerialNumber(String skuSerialNumber);

	/**
	 * 根据skuId查询规格信息
	 * @param skuId
	 * @return
	 */
	public Map<String, String> queryStandardBySkuId(Long skuId);

    List<SkuDTO> findSkuLikeName(String linkedSkuName,Long platformId);
	List<Long> getMembershipSku(Long platformId);

    SkuDTO findSkuByPuId(Long puId);

	/**
	 * 根据预警属性级别id查询sku集合的id和预警天数
	 */
	Map<Long,String> findSkuIdAndPreDaysByPreAttNameId(Long precautiousAttNameId);

    Long findLastId();

    List<SkuPriceDTO> findSkuListConByIdList(List<Long> idList);

	List<SkuDTO> findSkuBySkuSerialNos(List<String> skuSerialNos);

    List<SkuDTO> findSkuBySkuIds(List<Long> skuIds);

}
	