package com.egeo.components.third.service.read.impl;

import com.egeo.components.third.converter.ChannelServiceFieldConfigConverter;
import com.egeo.components.third.dto.ChannelServiceFieldConfigDTO;
import com.egeo.components.third.manage.read.ChannelServiceFieldConfigReadManage;
import com.egeo.components.third.po.ChannelServiceFieldConfigPO;
import com.egeo.components.third.service.read.ChannelServiceFieldConfigReadService;
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
@Service("channelServiceFieldConfigReadService")
public class ChannelServiceFieldConfigReadServiceImpl implements ChannelServiceFieldConfigReadService {

    @Autowired
    private ChannelServiceFieldConfigReadManage channelServiceFieldConfigReadManage;

    @Override
    public ChannelServiceFieldConfigDTO findChannelServiceFieldConfigById(ChannelServiceFieldConfigDTO dto) {
        ChannelServiceFieldConfigPO po = ChannelServiceFieldConfigConverter.toPO(dto);
        ChannelServiceFieldConfigPO list = channelServiceFieldConfigReadManage.findChannelServiceFieldConfigById(po);
        return ChannelServiceFieldConfigConverter.toDTO(list);

    }

    @Override
    public PageResult<ChannelServiceFieldConfigDTO> findChannelServiceFieldConfigOfPage(ChannelServiceFieldConfigDTO dto, Pagination page) {
        ChannelServiceFieldConfigPO po = ChannelServiceFieldConfigConverter.toPO(dto);
        PageResult<ChannelServiceFieldConfigPO> pageResult = channelServiceFieldConfigReadManage.findChannelServiceFieldConfigOfPage(po, page);

        List<ChannelServiceFieldConfigDTO> list = ChannelServiceFieldConfigConverter.toDTO(pageResult.getList());
        PageResult<ChannelServiceFieldConfigDTO> result = new PageResult<ChannelServiceFieldConfigDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
    }

    @Override
    public List<ChannelServiceFieldConfigDTO> findChannelServiceFieldConfigAll(ChannelServiceFieldConfigDTO dto) {
        ChannelServiceFieldConfigPO po = ChannelServiceFieldConfigConverter.toPO(dto);
        List<ChannelServiceFieldConfigPO> list = channelServiceFieldConfigReadManage.findChannelServiceFieldConfigAll(po);
        return ChannelServiceFieldConfigConverter.toDTO(list);
    }
}
