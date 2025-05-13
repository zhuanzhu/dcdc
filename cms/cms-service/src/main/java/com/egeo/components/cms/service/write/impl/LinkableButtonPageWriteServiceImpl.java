package com.egeo.components.cms.service.write.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.cms.service.write.LinkableButtonPageWriteService;
import com.egeo.components.cms.manage.write.LinkableButtonPageWriteManage;
import com.egeo.components.cms.converter.LinkableButtonPageConverter;
import com.egeo.components.cms.dto.LinkableButtonPageDTO;
import com.egeo.components.cms.po.LinkableButtonPagePO;

@Service("linkableButtonPageWriteService")
public class LinkableButtonPageWriteServiceImpl  implements LinkableButtonPageWriteService {
	@Autowired
	private LinkableButtonPageWriteManage linkableButtonPageWriteManage;

	@Override
	public Long insertLinkableButtonPageWithTx(LinkableButtonPageDTO dto) {
		LinkableButtonPagePO po = LinkableButtonPageConverter.toPO(dto);
		Long rt = linkableButtonPageWriteManage.insertLinkableButtonPageWithTx(po);		
		return rt;
	}

	@Override
	public int updateLinkableButtonPageWithTx(LinkableButtonPageDTO dto) {
		LinkableButtonPagePO po = LinkableButtonPageConverter.toPO(dto);
		int rt = linkableButtonPageWriteManage.updateLinkableButtonPageWithTx(po);		
		return rt;
	}

	@Override
	public int deleteLinkableButtonPageWithTx(LinkableButtonPageDTO dto) {
		LinkableButtonPagePO po = LinkableButtonPageConverter.toPO(dto);
		int rt = linkableButtonPageWriteManage.deleteLinkableButtonPageWithTx(po);		
		return rt;
	}

	@Override
	public int insertBatchLinkableButtonPageWithTx(List<LinkableButtonPageDTO> dtos) {
		List<LinkableButtonPagePO> pos = LinkableButtonPageConverter.toPO(dtos);
		return linkableButtonPageWriteManage.insertBatchLinkableButtonPageWithTx(pos);
	}

	@Override
	public int deleteLinkableButtonPageByLinkableIdWithTx(LinkableButtonPageDTO dto) {
		LinkableButtonPagePO po = LinkableButtonPageConverter.toPO(dto);
		int rt = linkableButtonPageWriteManage.deleteLinkableButtonPageByLinkableIdWithTx(po);		
		return rt;
	}
	
	
}
	