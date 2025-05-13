package com.egeo.components.third.service.read.impl;

import com.egeo.components.third.converter.ChannelServiceConfigConverter;
import com.egeo.components.third.dto.ChannelServiceConfigDTO;
import com.egeo.components.third.manage.read.ChannelServiceConfigReadManage;
import com.egeo.components.third.po.ChannelServiceConfigPO;
import com.egeo.components.third.service.read.ChannelServiceConfigReadService;
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
@Service("channelServiceConfigReadService")
public class ChannelServiceConfigReadServiceImpl implements ChannelServiceConfigReadService {

    @Autowired
    private ChannelServiceConfigReadManage channelServiceConfigReadManage;

    @Override
    public ChannelServiceConfigDTO findChannelServiceConfigById(ChannelServiceConfigDTO dto) {
        ChannelServiceConfigPO po = ChannelServiceConfigConverter.toPO(dto);
        ChannelServiceConfigPO list = channelServiceConfigReadManage.findChannelServiceConfigById(po);
        return ChannelServiceConfigConverter.toDTO(list);
    }

    @Override
    public PageResult<ChannelServiceConfigDTO> findChannelServiceConfigOfPage(ChannelServiceConfigDTO dto, Pagination page) {
        ChannelServiceConfigPO po = ChannelServiceConfigConverter.toPO(dto);
        PageResult<ChannelServiceConfigPO> pageResult = channelServiceConfigReadManage.findChannelServiceConfigOfPage(po, page);

        List<ChannelServiceConfigDTO> list = ChannelServiceConfigConverter.toDTO(pageResult.getList());
        PageResult<ChannelServiceConfigDTO> result = new PageResult<ChannelServiceConfigDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
    }

    @Override
    public List<ChannelServiceConfigDTO> findChannelServiceConfigAll(ChannelServiceConfigDTO dto) {
        ChannelServiceConfigPO po = ChannelServiceConfigConverter.toPO(dto);
        List<ChannelServiceConfigPO> list = channelServiceConfigReadManage.findChannelServiceConfigAll(po);
        return ChannelServiceConfigConverter.toDTO(list);
    }
}
