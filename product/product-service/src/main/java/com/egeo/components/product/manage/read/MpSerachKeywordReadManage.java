package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.MpSerachKeywordPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface MpSerachKeywordReadManage {

	public MpSerachKeywordPO findMpSerachKeywordById(MpSerachKeywordPO po);

	public PageResult<MpSerachKeywordPO> findMpSerachKeywordOfPage(MpSerachKeywordPO po,Pagination page);

	public List<MpSerachKeywordPO> findMpSerachKeywordAll(MpSerachKeywordPO po);
	/**
	 * 根据suId查询su草稿关键词
	 * @param id
	 * @param platformId
	 * @return
	 */
	public List<String> keyWordByMerchantProductId(Long merchantProductId, Long platformId);
}
	