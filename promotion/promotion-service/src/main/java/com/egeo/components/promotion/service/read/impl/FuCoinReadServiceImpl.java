package com.egeo.components.promotion.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.service.read.FuCoinReadService;
import com.egeo.components.promotion.manage.read.FuCoinReadManage;
import com.egeo.components.promotion.converter.FuCoinConverter;
import com.egeo.components.promotion.dto.FuCoinDTO;
import com.egeo.components.promotion.po.FuCoinPO;
 
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("fuCoinReadService")
public class FuCoinReadServiceImpl implements FuCoinReadService {
	@Autowired
	private FuCoinReadManage fuCoinReadManage;

	@Override
	public FuCoinDTO findFuCoinById(FuCoinDTO dto) {
		FuCoinPO po = FuCoinConverter.toPO(dto);
		FuCoinPO list = fuCoinReadManage.findFuCoinById(po);		
		return FuCoinConverter.toDTO(list);
	}

	@Override
	public PageResult<FuCoinDTO> findFuCoinOfPage(FuCoinDTO dto, Pagination page) {
		FuCoinPO po = FuCoinConverter.toPO(dto);
        PageResult<FuCoinPO> pageResult = fuCoinReadManage.findFuCoinOfPage(po, page);
        
        List<FuCoinDTO> list = FuCoinConverter.toDTO(pageResult.getList());
        PageResult<FuCoinDTO> result = new PageResult<FuCoinDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<FuCoinDTO> findFuCoinAll(FuCoinDTO dto) {
		FuCoinPO po = FuCoinConverter.toPO(dto);
		List<FuCoinPO> list = fuCoinReadManage.findFuCoinAll(po);		
		return FuCoinConverter.toDTO(list);
	}
	/**
	 * 根据用户id查询用户积分额度
	 * @param memberId
	 * @param platformId
	 * @return
	 */
	@Override
	public FuCoinDTO findFCoinByUserId(Long memberId, Long platformId) {
		FuCoinPO fuCoinPO = fuCoinReadManage.findFCoinByUserId(memberId, platformId);
		return FuCoinConverter.toDTO(fuCoinPO);
	}
}
	