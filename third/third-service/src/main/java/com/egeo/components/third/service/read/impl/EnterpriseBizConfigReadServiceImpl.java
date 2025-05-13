package com.egeo.components.third.service.read.impl;

import com.egeo.components.third.converter.EnterpriseBizConfigConverter;
import com.egeo.components.third.dto.EnterpriseBizConfigDTO;
import com.egeo.components.third.manage.read.EnterpriseBizConfigReadManage;
import com.egeo.components.third.po.EnterpriseBizConfigPO;
import com.egeo.components.third.service.read.EnterpriseBizConfigReadService;
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
@Service("enterpriseBizConfigReadService")
public class EnterpriseBizConfigReadServiceImpl implements EnterpriseBizConfigReadService {


    @Autowired
    private EnterpriseBizConfigReadManage enterpriseBizConfigReadManage;

    @Override
    public EnterpriseBizConfigDTO findEnterpriseBizConfigById(EnterpriseBizConfigDTO dto) {
        EnterpriseBizConfigPO po = EnterpriseBizConfigConverter.toPO(dto);
        EnterpriseBizConfigPO list = enterpriseBizConfigReadManage.findEnterpriseBizConfigById(po);
        return EnterpriseBizConfigConverter.toDTO(list);
    }

    @Override
    public PageResult<EnterpriseBizConfigDTO> findEnterpriseBizConfigOfPage(EnterpriseBizConfigDTO dto, Pagination page) {
        EnterpriseBizConfigPO po = EnterpriseBizConfigConverter.toPO(dto);
        PageResult<EnterpriseBizConfigPO> pageResult = enterpriseBizConfigReadManage.findEnterpriseBizConfigOfPage(po, page);

        List<EnterpriseBizConfigDTO> list = EnterpriseBizConfigConverter.toDTO(pageResult.getList());
        PageResult<EnterpriseBizConfigDTO> result = new PageResult<EnterpriseBizConfigDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
    }

    @Override
    public List<EnterpriseBizConfigDTO> findEnterpriseBizConfigAll(EnterpriseBizConfigDTO dto) {
        EnterpriseBizConfigPO po = EnterpriseBizConfigConverter.toPO(dto);
        List<EnterpriseBizConfigPO> list = enterpriseBizConfigReadManage.findEnterpriseBizConfigAll(po);
        return EnterpriseBizConfigConverter.toDTO(list);
    }
}
