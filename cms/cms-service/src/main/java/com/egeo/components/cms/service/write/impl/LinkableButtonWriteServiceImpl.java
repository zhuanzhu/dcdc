package com.egeo.components.cms.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.cms.service.write.LinkableButtonWriteService;
import com.egeo.components.cms.manage.write.LinkableButtonWriteManage;
import com.egeo.components.cms.converter.LinkableButtonConverter;
import com.egeo.components.cms.dto.LinkableButtonDTO;
import com.egeo.components.cms.po.LinkableButtonPO;

@Service("linkableButtonWriteService")
public class LinkableButtonWriteServiceImpl  implements LinkableButtonWriteService {
	@Autowired
	private LinkableButtonWriteManage linkableButtonWriteManage;

	@Override
	public Long insertLinkableButtonWithTx(LinkableButtonDTO dto) {
		LinkableButtonPO po = LinkableButtonConverter.toPO(dto);
		Long rt = linkableButtonWriteManage.insertLinkableButtonWithTx(po);		
		return rt;
	}

	@Override
	public int updateLinkableButtonWithTx(LinkableButtonDTO dto) {
		LinkableButtonPO po = LinkableButtonConverter.toPO(dto);
		int rt = linkableButtonWriteManage.updateLinkableButtonWithTx(po);		
		return rt;
	}

	@Override
	public int deleteLinkableButtonWithTx(LinkableButtonDTO dto) {
		LinkableButtonPO po = LinkableButtonConverter.toPO(dto);
		int rt = linkableButtonWriteManage.deleteLinkableButtonWithTx(po);		
		return rt;
	}
}
	