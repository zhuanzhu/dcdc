package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.MpSerachKeywordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface MpSerachKeywordReadService {

	public MpSerachKeywordDTO findMpSerachKeywordById(MpSerachKeywordDTO dto);

	public PageResult<MpSerachKeywordDTO> findMpSerachKeywordOfPage(MpSerachKeywordDTO dto,Pagination page);

	public List<MpSerachKeywordDTO> findMpSerachKeywordAll(MpSerachKeywordDTO dto);
	/**
	 * 根据suId查询su草稿关键词
	 * @param id
	 * @param platformId
	 * @return
	 */
	public List<String> keyWordByMerchantProductId(Long merchantProductId, Long platformId);
}
	