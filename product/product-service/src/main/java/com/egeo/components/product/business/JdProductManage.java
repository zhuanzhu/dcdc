package com.egeo.components.product.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.egeo.components.order.dto.ReceiverAddressDTO;
import com.egeo.components.product.dto.*;
import com.egeo.components.product.vo.JdPriceAuditingVO;
import com.egeo.components.product.vo.JdProductEnterpriseVO;
import com.egeo.entity.CacheUser;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.delivery.jd.JdAllSkuInfo;
import com.egeo.web.JsonResult;

public interface JdProductManage {

	public JdProductDTO findJdProductById(JdProductDTO dto);
	public JdProductDTO findJdProductById2(JdProductDTO dto);
	public List<JdProductDTO> findJdProductByIds(List<JdProductDTO> dto);
	public JdProductDTO findJdProductSimpleById(JdProductDTO dto);

	public JdAllSkuInfo getAllSku(JdProductDTO data,ReceiverAddressDTO addressDto) ;
	public List<JdPriceAuditingVO> findJdProductForPriceAuditing(List<JdPriceDTO> dto);
	/*public PageResult<JdProductDTO> findJdProductOfPage(JdProductDTO dto,Pagination page);

	public List<JdProductDTO> findJdProductAll(JdProductDTO dto);

	Long insertJdProductWithTx(JdProductDTO dto);

	int updateJdProductWithTx(JdProductDTO dto);

	int deleteJdProductWithTx(JdProductDTO dto);

	void syncJdCategory(String parentId);

	void syncJdProduct(CacheUser user, String ip, String mac, HttpServletRequest req);
	void syncJdProductList(CacheUser user, String ip, String mac, HttpServletRequest req);

    void syncJdProductByJdSkuId(List<Long> skuIdList);*/

	PageResult<JdProductDTO> getJdProductListByParams(Long skuId, List<String> skuNameList, Long updateTimeStart, Long updateTimeEnd, Integer profitStart, Integer profitEnd, Integer state, Integer sycStatus, Long catId, Integer isShow, Pagination page);

	JsonResult<JSONObject> getJdCategory(String parentId, Integer catClass);
    void deleteJdMessage();

	JsonResult getJdCategoryList(Integer catClass, Long parentId);

    void deleteRedisJd();

    void updateJdProductByJdSkuId(List<Long> skuIdList);
    //根据数据获取京东skus的价格库存状态等
	Map<String,JdProductDTO> getJdProductStatStockAndPrice(Long companyId,Long enterpriseId,HashMap<String,Integer> skuNumMap,ReceiverAddressDTO addressDto,Boolean price,Boolean stock);

	void updateJdProductByLimitProfit(Integer productLimitProfit);
	/**
	 * 代理商搜索方法
	 * @param search
	 * @return
	 */
	public JsonResult<PageResult<JdProductEnterpriseVO>> searchEnterprise(JDProductSearchDTO search) ;

	JsonResult<Map<String, Object>> exportEnterprise(JDProductSearchDTO search) ;

	/**
	 * 平台搜索方法
	 * @param search
	 * @return
	 */
	public JsonResult<PageResult<JdProductDTO>> searchPlatform(JDProductSearchDTO search) ;


	/**
	 * 微信商城搜索
	 * @param search
	 * @return
	 */
	public JsonResult<PageResult<JdProductDTO>> search(JDProductSearchDTO search) ;
	/**
	 * 按二级类目搜索
	 * @param search
	 * @return
	 */
	public JsonResult<PageResult<JdProductDTO>> searchOfCategoryLevel2(JDProductSearchDTO search) ;

	String getCategoryName(Long jdCategoryId);
	JdProductDTO fetchJdProductRate(Long skuId) ;

	public PageResult<JdSearchHitResultDTO> searchJdHitResult(JDProductSearchDTO search, List<JdProductDTO> jdResultList, List<JdEnterpriseConfigDTO> jdEnterpriseConfigs);

}
