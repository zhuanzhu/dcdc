package com.egeo.components.product.business.impl;


import java.util.List;

import javax.annotation.Resource;

import com.egeo.exception.BusinessException;
import com.egeo.utils.EmptyUtil;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.SellPlatformManage;
import com.egeo.components.product.facade.SellPlatformFacade;
import com.egeo.components.product.dto.SellPlatformDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("sellPlatform")
public class SellPlatformManageImpl implements SellPlatformManage {


    @Resource(name = "sellPlatformFacade")
    private SellPlatformFacade sellPlatformFacade;

    @Override
    public SellPlatformDTO findSellPlatformById(SellPlatformDTO dto) {
    	SellPlatformDTO sellPlatformDTO = sellPlatformFacade.findSellPlatformById(dto);
    	sellPlatformDTO.setSortValue(sellPlatformDTO.getSortValue() == -1 ? null : sellPlatformDTO.getSortValue());
        return sellPlatformDTO;
    }

    @Override
    public PageResult<SellPlatformDTO> findSellPlatformOfPage(SellPlatformDTO dto, Pagination page) {
        return sellPlatformFacade.findSellPlatformOfPage(dto, page);
    }

    @Override
    public List<SellPlatformDTO> findSellPlatformAll(SellPlatformDTO dto) {
        return sellPlatformFacade.findSellPlatformAll(dto);
    }

    @Override
    public Long insertSellPlatformWithTx(SellPlatformDTO dto) {
        insertOrUpdateCheck(dto);
        if (EmptyUtil.isNotEmpty(dto.getId())) {
            int i = sellPlatformFacade.updateSellPlatformWithTx(dto);
            return Long.valueOf(i);
        }
        return sellPlatformFacade.insertSellPlatformWithTx(dto);
    }

    private void insertOrUpdateCheck(SellPlatformDTO dto) {
    	SellPlatformDTO findDTO = new SellPlatformDTO();
    	findDTO.setPlatformId(dto.getPlatformId());
    	List<SellPlatformDTO> dbDTOList = sellPlatformFacade.findSellPlatformAll(findDTO);
    	if (EmptyUtil.isNotEmpty(dbDTOList)) {
    		for (SellPlatformDTO  dbDTO : dbDTOList) {
				if (dbDTO.getName().equals(dto.getName()) && dto.getId() != dbDTO.getId()) {
    				throw new BusinessException("平台名称已存在");
    			}
    			if (dbDTO.getSortValue() == dto.getSortValue() && dto.getId() != dbDTO.getId()) {
    				throw new BusinessException("排序值已存在");
    			}
    		}
    	}
    }

    @Override
    public int updateSellPlatformWithTx(SellPlatformDTO dto) {
    	insertOrUpdateCheck(dto);
    	checkSortValueForStatusChange(dto);	
        return sellPlatformFacade.updateSellPlatformWithTx(dto);
    }
    
    private void checkSortValueForStatusChange(SellPlatformDTO dto) {
    	SellPlatformDTO findDTO = new SellPlatformDTO();
    	findDTO.setId(dto.getId());
    	SellPlatformDTO sellPlatformDTO = sellPlatformFacade.findSellPlatformById(findDTO);
    	if (sellPlatformDTO == null) {
    		throw new BusinessException("比价平台信息错误");
    	}
    	if (dto.getStatus() == 1 && sellPlatformDTO.getSortValue() == -1) {
    		throw new BusinessException("启用的比价平台排序值不能为空");
    	}
    }

    @Override
    public int deleteSellPlatformWithTx(SellPlatformDTO dto) {
        return sellPlatformFacade.deleteSellPlatformWithTx(dto);
    }


}
	