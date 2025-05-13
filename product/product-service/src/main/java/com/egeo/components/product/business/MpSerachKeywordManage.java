package com.egeo.components.product.business;

import java.util.List;
	
import com.egeo.components.product.dto.MpSerachKeywordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface MpSerachKeywordManage {

	public MpSerachKeywordDTO findMpSerachKeywordById(MpSerachKeywordDTO dto);	

	public PageResult<MpSerachKeywordDTO> findMpSerachKeywordOfPage(MpSerachKeywordDTO dto,Pagination page);

	public List<MpSerachKeywordDTO> findMpSerachKeywordAll(MpSerachKeywordDTO dto);

	Long insertMpSerachKeywordWithTx(MpSerachKeywordDTO dto);

	int updateMpSerachKeywordWithTx(MpSerachKeywordDTO dto);

	int deleteMpSerachKeywordWithTx(MpSerachKeywordDTO dto);
}
	