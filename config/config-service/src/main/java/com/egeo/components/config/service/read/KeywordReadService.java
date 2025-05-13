package com.egeo.components.config.service.read;


import java.util.List;

import com.egeo.components.config.dto.KeywordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface KeywordReadService {

	public KeywordDTO findKeywordById(KeywordDTO dto);

	public PageResult<KeywordDTO> findKeywordOfPage(KeywordDTO dto,Pagination page);

	public List<KeywordDTO> findKeywordAll(KeywordDTO dto);
}
	