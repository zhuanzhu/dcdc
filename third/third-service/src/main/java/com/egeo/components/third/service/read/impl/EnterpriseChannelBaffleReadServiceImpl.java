package com.egeo.components.third.service.read.impl;

import com.egeo.components.third.converter.EnterpriseChannelBaffleConverter;
import com.egeo.components.third.dto.EnterpriseChannelBaffleDTO;
import com.egeo.components.third.manage.read.EnterpriseChannelBaffleReadManage;
import com.egeo.components.third.po.EnterpriseChannelBafflePO;
import com.egeo.components.third.service.read.EnterpriseChannelBaffleReadService;
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
@Service("enterpriseChannelBaffleReadService")
public class EnterpriseChannelBaffleReadServiceImpl implements EnterpriseChannelBaffleReadService {



    @Autowired
    private EnterpriseChannelBaffleReadManage enterpriseChannelBaffleReadManage;

    @Override
    public EnterpriseChannelBaffleDTO findEnterpriseChannelBaffleById(EnterpriseChannelBaffleDTO dto) {
        EnterpriseChannelBafflePO po = EnterpriseChannelBaffleConverter.toPO(dto);
        EnterpriseChannelBafflePO list = enterpriseChannelBaffleReadManage.findEnterpriseChannelBaffleById(po);
        return EnterpriseChannelBaffleConverter.toDTO(list);
    }

    @Override
    public PageResult<EnterpriseChannelBaffleDTO> findEnterpriseChannelBaffleOfPage(EnterpriseChannelBaffleDTO dto, Pagination page) {
        EnterpriseChannelBafflePO po = EnterpriseChannelBaffleConverter.toPO(dto);
        PageResult<EnterpriseChannelBafflePO> pageResult = enterpriseChannelBaffleReadManage.findEnterpriseChannelBaffleOfPage(po, page);

        List<EnterpriseChannelBaffleDTO> list = EnterpriseChannelBaffleConverter.toDTO(pageResult.getList());
        PageResult<EnterpriseChannelBaffleDTO> result = new PageResult<EnterpriseChannelBaffleDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
    }

    @Override
    public List<EnterpriseChannelBaffleDTO> findEnterpriseChannelBaffleAll(EnterpriseChannelBaffleDTO dto) {
        EnterpriseChannelBafflePO po = EnterpriseChannelBaffleConverter.toPO(dto);
        List<EnterpriseChannelBafflePO> list = enterpriseChannelBaffleReadManage.findEnterpriseChannelBaffleAll(po);
        return EnterpriseChannelBaffleConverter.toDTO(list);
    }
}
