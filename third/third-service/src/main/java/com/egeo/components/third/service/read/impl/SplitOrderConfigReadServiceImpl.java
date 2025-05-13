package com.egeo.components.third.service.read.impl;

import com.egeo.components.third.converter.SplitOrderConfigConverter;
import com.egeo.components.third.dto.SplitOrderConfigDTO;
import com.egeo.components.third.manage.read.SplitOrderConfigReadManage;
import com.egeo.components.third.po.SplitOrderConfigPO;
import com.egeo.components.third.service.read.SplitOrderConfigReadService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
@Service("splitOrderConfigReadService")
public class SplitOrderConfigReadServiceImpl implements SplitOrderConfigReadService {


    @Autowired
    private SplitOrderConfigReadManage splitOrderConfigReadManage;

    @Override
    public SplitOrderConfigDTO findSplitOrderConfigById(SplitOrderConfigDTO dto) {
        SplitOrderConfigPO po = SplitOrderConfigConverter.toPO(dto);
        SplitOrderConfigPO list = splitOrderConfigReadManage.findSplitOrderConfigById(po);
        return SplitOrderConfigConverter.toDTO(list);
    }

    @Override
    public PageResult<SplitOrderConfigDTO> findSplitOrderConfigOfPage(SplitOrderConfigDTO dto, Pagination page) {
        SplitOrderConfigPO po = SplitOrderConfigConverter.toPO(dto);
        PageResult<SplitOrderConfigPO> pageResult = splitOrderConfigReadManage.findSplitOrderConfigOfPage(po, page);

        List<SplitOrderConfigDTO> list = SplitOrderConfigConverter.toDTO(pageResult.getList());
        PageResult<SplitOrderConfigDTO> result = new PageResult<SplitOrderConfigDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
    }

    @Override
    public List<SplitOrderConfigDTO> findSplitOrderConfigAll(SplitOrderConfigDTO dto) {
        SplitOrderConfigPO po = SplitOrderConfigConverter.toPO(dto);
        List<SplitOrderConfigPO> list = splitOrderConfigReadManage.findSplitOrderConfigAll(po);
        return SplitOrderConfigConverter.toDTO(list);
    }
}
