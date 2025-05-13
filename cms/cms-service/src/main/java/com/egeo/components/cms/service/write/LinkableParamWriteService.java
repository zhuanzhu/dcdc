package com.egeo.components.cms.service.write;

import com.egeo.components.cms.dto.LinkableParamDTO;


public interface LinkableParamWriteService {

	public Long insertLinkableParamWithTx(LinkableParamDTO dto);

	public int updateLinkableParamWithTx(LinkableParamDTO dto);

	public int deleteLinkableParamWithTx(LinkableParamDTO dto);

	public void deleteByPara(LinkableParamDTO linkableParam);
}
	