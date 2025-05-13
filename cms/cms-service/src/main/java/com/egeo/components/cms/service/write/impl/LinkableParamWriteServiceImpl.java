package com.egeo.components.cms.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.cms.service.write.LinkableParamWriteService;
import com.egeo.components.cms.manage.write.LinkableParamWriteManage;
import com.egeo.components.cms.converter.LinkableParamConverter;
import com.egeo.components.cms.dto.LinkableParamDTO;
import com.egeo.components.cms.po.LinkableParamPO;

@Service("linkableParamWriteService")
public class LinkableParamWriteServiceImpl  implements LinkableParamWriteService {
	@Autowired
	private LinkableParamWriteManage linkableParamWriteManage;

	@Override
	public Long insertLinkableParamWithTx(LinkableParamDTO dto) {
		LinkableParamPO po = LinkableParamConverter.toPO(dto);
		Long rt = linkableParamWriteManage.insertLinkableParamWithTx(po);		
		return rt;
	}

	@Override
	public int updateLinkableParamWithTx(LinkableParamDTO dto) {
		LinkableParamPO po = LinkableParamConverter.toPO(dto);
		int rt = linkableParamWriteManage.updateLinkableParamWithTx(po);		
		return rt;
	}

	@Override
	public int deleteLinkableParamWithTx(LinkableParamDTO dto) {
		LinkableParamPO po = LinkableParamConverter.toPO(dto);
		int rt = linkableParamWriteManage.deleteLinkableParamWithTx(po);		
		return rt;
	}

	@Override
	public void deleteByPara(LinkableParamDTO linkableParam) {
		LinkableParamPO po = LinkableParamConverter.toPO(linkableParam);
		int rt = linkableParamWriteManage.deleteLinkableParamByParamWithTx(po);		
	}
}
	