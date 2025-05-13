package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.common.BusinessConstant;
import com.egeo.components.product.condition.StandardProductUnitAttValueCondition;
import com.egeo.components.product.dao.read.StandardProductUnitAttValueReadDAO;
import com.egeo.components.product.manage.read.StandardProductUnitAttValueReadManage;
import com.egeo.components.product.po.StandardProductUnitAttValuePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.log.XLogger;


@Service
public class StandardProductUnitAttValueReadManageImpl implements StandardProductUnitAttValueReadManage {
	private static final XLogger logger = XLogger.getLogger(StandardProductUnitAttValueReadManageImpl.class);
	@Autowired
	private StandardProductUnitAttValueReadDAO standardProductUnitAttValueReadDAO;
	
	public StandardProductUnitAttValuePO findStandardProductUnitAttValueById(StandardProductUnitAttValuePO po) {
		StandardProductUnitAttValuePO standardProductUnitAttValuepo = new StandardProductUnitAttValuePO();
		standardProductUnitAttValuepo.setId(po.getId());
		return standardProductUnitAttValueReadDAO.findById(standardProductUnitAttValuepo);
	}

	public PageResult<StandardProductUnitAttValuePO> findStandardProductUnitAttValueOfPage(StandardProductUnitAttValuePO po, Pagination page) {
		
		PageResult<StandardProductUnitAttValuePO> pageResult = new PageResult<StandardProductUnitAttValuePO>();
		List<StandardProductUnitAttValuePO> list = null;

		int cnt = standardProductUnitAttValueReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = standardProductUnitAttValueReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<StandardProductUnitAttValuePO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<StandardProductUnitAttValuePO> findStandardProductUnitAttValueAll(StandardProductUnitAttValuePO po) {

		return standardProductUnitAttValueReadDAO.findAll(po,null);
	}
	/**
	 * 根据su属性id查询属性值信息
	 * @param id
	 * @return
	 */
	@Override
	public List<StandardProductUnitAttValueCondition> findByStandardProductUnitAttNameId(
			Long standardProductUnitAttNameId) {
		// TODO Auto-generated method stub
		return standardProductUnitAttValueReadDAO.findByStandardProductUnitAttNameId(standardProductUnitAttNameId);
	}
	/**
	 * 根据spu草稿id和属性值id查询是否存在spu规格属性
	 * @param productId
	 * @param attValueId
	 * @return
	 */
	@Override
	public StandardProductUnitAttValuePO standardProductUnitAttValueByProductIdAttValueId(Long productId,
			Long attValueId) {
		return standardProductUnitAttValueReadDAO.standardProductUnitAttValueByProductIdAttValueId(productId, attValueId);
	}

	@Override
	public Long queryAttValueIdByPuIdAndAttNameId(
			Long puId, Long attNameId) {
		
		return standardProductUnitAttValueReadDAO.queryAttValueIdByPuIdAndAttNameId(puId,attNameId);
	}
	/**
	 * 判断spu是否是unit商品
	 * @param puId
	 * @return
	 */
	@Override
	public Long queryAttValueIdBySpuIdAndAttNameId(Long standardProductUnitId, Long attNameId) {
		// TODO Auto-generated method stub
		return standardProductUnitAttValueReadDAO.queryAttValueIdBySpuIdAndAttNameId(standardProductUnitId,attNameId);
	}

	@Override
	public List<String> keyWordByStandardProductUnitId(Long standardProductUnitId, Long platformId) {
		return standardProductUnitAttValueReadDAO.keyWordByStandardProductUnitId(standardProductUnitId, platformId);
	}
	/**
	 * 根据skuid查询是否在app内使用
	 */
	@Override
	public boolean isAppUseBySkuId(Long skuId) {
		Long attValueId = standardProductUnitAttValueReadDAO.findAttValueIdBySkuIdAndAttNameId(skuId,BusinessConstant.IS_APP_USE_ID);
		// 是
		if(attValueId.equals(BusinessConstant.IS_APP_USE_ID_IS))
			return true;
		// 否
		if(attValueId.equals(BusinessConstant.IS_APP_USE_ID_NOT_IS))
			return false;
		return false;
	}
	/**
	 * 根据skuid查询第三方链接Id 7、内部 8、话费充值 9、爱康国宾体检套餐券
	 */
	@Override
	public Long findAttValueIdBySkuIdAndAttNameId(Long skuId,Long attNameId) {
		return standardProductUnitAttValueReadDAO.findAttValueIdBySkuIdAndAttNameId(skuId,attNameId);
	}
	/**
	 * 根据spuid查询spu关键字
	 */
	@Override
	public List<String> findSpuKeywordByStandardProductUnitId(Long standardProductUnitId) {
		// TODO Auto-generated method stub
		return standardProductUnitAttValueReadDAO.findSpuKeywordByStandardProductUnitId(standardProductUnitId);
	}

	@Override
	public int findThirdpartyAttBySpuId(Long id) {
		return standardProductUnitAttValueReadDAO.findThirdpartyAttBySpuId(id);
	}

}
	