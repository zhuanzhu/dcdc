package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.TopSearchWriteService;
import com.egeo.components.product.manage.write.TopSearchWriteManage;
import com.egeo.components.product.converter.TopSearchConverter;
import com.egeo.components.product.dto.TopSearchDTO;
import com.egeo.components.product.po.TopSearchPO;

@Service("topSearchWriteService")
public class TopSearchWriteServiceImpl  implements TopSearchWriteService {
	@Autowired
	private TopSearchWriteManage topSearchWriteManage;

	@Override
	public Long insertTopSearchWithTx(TopSearchDTO dto) {
		TopSearchPO po = TopSearchConverter.toPO(dto);
		Long rt = topSearchWriteManage.insertTopSearchWithTx(po);		
		return rt;
	}

	@Override
	public int updateTopSearchWithTx(TopSearchDTO dto) {
		TopSearchPO po = TopSearchConverter.toPO(dto);
		int rt = topSearchWriteManage.updateTopSearchWithTx(po);		
		return rt;
	}

	@Override
	public int deleteTopSearchWithTx(TopSearchDTO dto) {
		TopSearchPO po = TopSearchConverter.toPO(dto);
		int rt = topSearchWriteManage.deleteTopSearchWithTx(po);		
		return rt;
	}
	/**
	 * 根据id启用停用热搜
	 * @param topSearchId
	 * @return
	 */
	@Override
	public int startStopTopSearchWithTx(Long topSearchId) {
		// TODO Auto-generated method stub
		return topSearchWriteManage.startStopTopSearchWithTx(topSearchId);
	}
}
	