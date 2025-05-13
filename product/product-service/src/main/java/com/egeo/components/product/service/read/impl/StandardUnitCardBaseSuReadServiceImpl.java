package com.egeo.components.product.service.read.impl;

import com.egeo.components.product.converter.StandardUnitCardBaseSuConverter;
import com.egeo.components.product.dto.StandardUnitCardBaseSuDTO;
import com.egeo.components.product.manage.read.StandardUnitCardBaseSuReadManage;
import com.egeo.components.product.po.StandardUnitCardBaseSuPO;
import com.egeo.components.product.service.read.StandardUnitCardBaseSuReadService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
@Service("standardUnitCardBaseSuReadService")
public class StandardUnitCardBaseSuReadServiceImpl implements StandardUnitCardBaseSuReadService {



    @Autowired
    private StandardUnitCardBaseSuReadManage standardUnitCardBaseSuReadManage;

    @Override
    public StandardUnitCardBaseSuDTO findStandardUnitCardBaseSuById(StandardUnitCardBaseSuDTO dto) {
        StandardUnitCardBaseSuPO po = StandardUnitCardBaseSuConverter.toPO(dto);
        StandardUnitCardBaseSuPO list = standardUnitCardBaseSuReadManage.findStandardUnitCardBaseSuById(po);
        return StandardUnitCardBaseSuConverter.toDTO(list);
    }

    @Override
    public PageResult<StandardUnitCardBaseSuDTO> findStandardUnitCardBaseSuOfPage(StandardUnitCardBaseSuDTO dto, Pagination page) {
        StandardUnitCardBaseSuPO po = StandardUnitCardBaseSuConverter.toPO(dto);
        PageResult<StandardUnitCardBaseSuPO> pageResult = standardUnitCardBaseSuReadManage.findStandardUnitCardBaseSuOfPage(po, page);

        List<StandardUnitCardBaseSuDTO> list = StandardUnitCardBaseSuConverter.toDTO(pageResult.getList());
        PageResult<StandardUnitCardBaseSuDTO> result = new PageResult<StandardUnitCardBaseSuDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
    }

    @Override
    public List<StandardUnitCardBaseSuDTO> findStandardUnitCardBaseSuAll(StandardUnitCardBaseSuDTO dto) {
        StandardUnitCardBaseSuPO po = StandardUnitCardBaseSuConverter.toPO(dto);
        List<StandardUnitCardBaseSuPO> list = standardUnitCardBaseSuReadManage.findStandardUnitCardBaseSuAll(po);
        return StandardUnitCardBaseSuConverter.toDTO(list);
    }

    @Override
    public  int findStandardUnitCardBaseSuNumberMax(Long cardBaseId){
        return standardUnitCardBaseSuReadManage.findStandardUnitCardBaseSuNumberMax(cardBaseId);
    }

    @Override
    public Integer findStandardUnitSize(Long cardBaseId){
        return standardUnitCardBaseSuReadManage.findStandardUnitSize(cardBaseId);
    }

    @Override
    public List<StandardUnitCardBaseSuPO> syncChannelSellState(Integer source,
                                                               Date endCheckTime,
                                                               int size){
        return standardUnitCardBaseSuReadManage.syncChannelSellState(source,endCheckTime,size);
    }
}
