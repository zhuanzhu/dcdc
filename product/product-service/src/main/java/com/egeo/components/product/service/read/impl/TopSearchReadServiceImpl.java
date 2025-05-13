package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.TopSearchReadService;
import com.egeo.components.product.manage.read.TopSearchReadManage;
import com.egeo.components.product.converter.TopSearchConverter;
import com.egeo.components.product.dto.TopSearchDTO;
import com.egeo.components.product.po.TopSearchPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("topSearchReadService")
public class TopSearchReadServiceImpl  implements TopSearchReadService {
	@Autowired
	private TopSearchReadManage topSearchReadManage;

	@Override
	public TopSearchDTO findTopSearchById(TopSearchDTO dto) {
		TopSearchPO po = TopSearchConverter.toPO(dto);
		TopSearchPO list = topSearchReadManage.findTopSearchById(po);		
		return TopSearchConverter.toDTO(list);
	}

	@Override
	public PageResult<TopSearchDTO> findTopSearchOfPage(TopSearchDTO dto, Pagination page) {
		TopSearchPO po = TopSearchConverter.toPO(dto);
        PageResult<TopSearchPO> pageResult = topSearchReadManage.findTopSearchOfPage(po, page);
        
        List<TopSearchDTO> list = TopSearchConverter.toDTO(pageResult.getList());
        PageResult<TopSearchDTO> result = new PageResult<TopSearchDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<TopSearchDTO> findTopSearchAll(TopSearchDTO dto) {
		TopSearchPO po = TopSearchConverter.toPO(dto);
		List<TopSearchPO> list = topSearchReadManage.findTopSearchAll(po);		
		return TopSearchConverter.toDTO(list);
	}
	/**
	 * 查询排序最大值
	 */
	@Override
	public int sortValueMax() {
		// TODO Auto-generated method stub
		return topSearchReadManage.sortValueMax();
	}
	/**
	 * 查询启用的热搜条数
	 * @return
	 */
	@Override
	public int findStartTopSearchNum(Long platformId) {
		// TODO Auto-generated method stub
		return topSearchReadManage.findStartTopSearchNum(platformId);
	}
}
	