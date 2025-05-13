package com.egeo.components.third.service.read.impl;

import com.egeo.components.third.converter.EnterpriseChannelServiceConverter;
import com.egeo.components.third.dto.EnterpriseChannelServiceDTO;
import com.egeo.components.third.manage.read.EnterpriseChannelServiceReadManage;
import com.egeo.components.third.po.EnterpriseChannelServicePO;
import com.egeo.components.third.service.read.EnterpriseChannelServiceReadService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
@Service("enterpriseChannelServiceReadService")
public class EnterpriseChannelServiceReadServiceImpl implements EnterpriseChannelServiceReadService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EnterpriseChannelServiceReadManage enterpriseChannelServiceReadManage;

    @Override
    public EnterpriseChannelServiceDTO findEnterpriseChannelServiceById(EnterpriseChannelServiceDTO dto) {
        EnterpriseChannelServicePO po = EnterpriseChannelServiceConverter.toPO(dto);
        EnterpriseChannelServicePO list = enterpriseChannelServiceReadManage.findEnterpriseChannelServiceById(po);
        return EnterpriseChannelServiceConverter.toDTO(list);
    }

    @Override
    public PageResult<EnterpriseChannelServiceDTO> findEnterpriseChannelServiceOfPage(EnterpriseChannelServiceDTO dto, Pagination page) {
        EnterpriseChannelServicePO po = EnterpriseChannelServiceConverter.toPO(dto);
        PageResult<EnterpriseChannelServicePO> pageResult = enterpriseChannelServiceReadManage.findEnterpriseChannelServiceOfPage(po, page);

        List<EnterpriseChannelServiceDTO> list = EnterpriseChannelServiceConverter.toDTO(pageResult.getList());
        PageResult<EnterpriseChannelServiceDTO> result = new PageResult<EnterpriseChannelServiceDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
    }

    @Override
    public List<EnterpriseChannelServiceDTO> findEnterpriseChannelServiceAll(EnterpriseChannelServiceDTO dto) {
        EnterpriseChannelServicePO po = EnterpriseChannelServiceConverter.toPO(dto);
        List<EnterpriseChannelServicePO> list = enterpriseChannelServiceReadManage.findEnterpriseChannelServiceAll(po);
        return EnterpriseChannelServiceConverter.toDTO(list);
    }
}
