package com.egeo.components.third.service.read.impl;

import com.egeo.components.third.converter.ChannelLogConverter;
import com.egeo.components.third.dto.ChannelLogDTO;
import com.egeo.components.third.manage.read.ChannelLogReadManage;
import com.egeo.components.third.po.ChannelLogPO;
import com.egeo.components.third.service.read.ChannelLogReadService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/10/30 14:21
 * @Version V1.0
 **/
@Service("channelLogReadService")
public class ChannelLogReadServiceImpl implements ChannelLogReadService {

    @Autowired
    private ChannelLogReadManage channelLogReadManage;

    @Override
    public ChannelLogDTO findChannelLogById(ChannelLogDTO dto) {
        ChannelLogPO po = ChannelLogConverter.toPO(dto);
        ChannelLogPO list = channelLogReadManage.findChannelLogById(po);
        return ChannelLogConverter.toDTO(list);
    }

    @Override
    public PageResult<ChannelLogDTO> findChannelLogOfPage(ChannelLogDTO dto, Pagination page) {
        ChannelLogPO po = ChannelLogConverter.toPO(dto);
        PageResult<ChannelLogPO> pageResult = channelLogReadManage.findChannelLogOfPage(po, page);

        List<ChannelLogDTO> list = ChannelLogConverter.toDTO(pageResult.getList());
        PageResult<ChannelLogDTO> result = new PageResult<ChannelLogDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
    }

    @Override
    public List<ChannelLogDTO> findChannelLogAll(ChannelLogDTO dto) {
        ChannelLogPO po = ChannelLogConverter.toPO(dto);
        List<ChannelLogPO> list = channelLogReadManage.findChannelLogAll(po);
        return ChannelLogConverter.toDTO(list);
    }
}
