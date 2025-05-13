package com.egeo.components.third.service.read.impl;

import com.egeo.components.third.converter.ChannelServiceConfigConverter;
import com.egeo.components.third.converter.EnterpriseChannelServiceChoiceConverter;
import com.egeo.components.third.dto.ChannelServiceConfigDTO;
import com.egeo.components.third.dto.EnterpriseChannelServiceChoiceDTO;
import com.egeo.components.third.manage.read.ChannelServiceConfigReadManage;
import com.egeo.components.third.manage.read.EnterpriseChannelServiceChoiceReadManage;
import com.egeo.components.third.po.ChannelServiceConfigPO;
import com.egeo.components.third.po.EnterpriseChannelServiceChoicePO;
import com.egeo.components.third.service.read.EnterpriseChannelServiceChoiceReadService;
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
@Service("enterpriseChannelServiceChoiceReadService")
public class EnterpriseChannelServiceChoiceReadServiceImpl implements EnterpriseChannelServiceChoiceReadService {



    @Autowired
    private EnterpriseChannelServiceChoiceReadManage enterpriseChannelServiceChoiceReadManage;

    @Override
    public EnterpriseChannelServiceChoiceDTO findEnterpriseChannelServiceChoiceById(EnterpriseChannelServiceChoiceDTO dto) {
        EnterpriseChannelServiceChoicePO po = EnterpriseChannelServiceChoiceConverter.toPO(dto);
        EnterpriseChannelServiceChoicePO list = enterpriseChannelServiceChoiceReadManage.findEnterpriseChannelServiceChoiceById(po);
        return EnterpriseChannelServiceChoiceConverter.toDTO(list);
    }

    @Override
    public PageResult<EnterpriseChannelServiceChoiceDTO> findEnterpriseChannelServiceChoiceOfPage(EnterpriseChannelServiceChoiceDTO dto, Pagination page) {
        EnterpriseChannelServiceChoicePO po = EnterpriseChannelServiceChoiceConverter.toPO(dto);
        PageResult<EnterpriseChannelServiceChoicePO> pageResult = enterpriseChannelServiceChoiceReadManage.findEnterpriseChannelServiceChoiceOfPage(po, page);

        List<EnterpriseChannelServiceChoiceDTO> list = EnterpriseChannelServiceChoiceConverter.toDTO(pageResult.getList());
        PageResult<EnterpriseChannelServiceChoiceDTO> result = new PageResult<EnterpriseChannelServiceChoiceDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
    }

    @Override
    public List<EnterpriseChannelServiceChoiceDTO> findEnterpriseChannelServiceChoiceAll(EnterpriseChannelServiceChoiceDTO dto) {
        EnterpriseChannelServiceChoicePO po = EnterpriseChannelServiceChoiceConverter.toPO(dto);
        List<EnterpriseChannelServiceChoicePO> list = enterpriseChannelServiceChoiceReadManage.findEnterpriseChannelServiceChoiceAll(po);
        return EnterpriseChannelServiceChoiceConverter.toDTO(list);
    }
}
