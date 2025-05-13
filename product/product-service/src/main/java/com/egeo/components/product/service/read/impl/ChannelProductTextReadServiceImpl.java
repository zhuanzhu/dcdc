package com.egeo.components.product.service.read.impl;

import com.egeo.components.product.converter.ChannelProductTextConverter;
import com.egeo.components.product.dto.channel.ChannelProductTextDTO;
import com.egeo.components.product.manage.read.ChannelProductTextReadManage;
import com.egeo.components.product.po.ChannelProductTextPO;
import com.egeo.components.product.service.read.ChannelProductTextReadService;
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
@Service("channelProductTextReadService")
public class ChannelProductTextReadServiceImpl implements ChannelProductTextReadService {



    @Autowired
    private ChannelProductTextReadManage channelProductTextReadManage;

    @Override
    public ChannelProductTextDTO findChannelProductTextById(ChannelProductTextDTO dto) {
        ChannelProductTextPO po = ChannelProductTextConverter.toPO(dto);
        ChannelProductTextPO list = channelProductTextReadManage.findChannelProductTextById(po);
        return ChannelProductTextConverter.toDTO(list);
    }

    @Override
    public PageResult<ChannelProductTextDTO> findChannelProductTextOfPage(ChannelProductTextDTO dto, Pagination page) {
        ChannelProductTextPO po = ChannelProductTextConverter.toPO(dto);
        PageResult<ChannelProductTextPO> pageResult = channelProductTextReadManage.findChannelProductTextOfPage(po, page);

        List<ChannelProductTextDTO> list = ChannelProductTextConverter.toDTO(pageResult.getList());
        PageResult<ChannelProductTextDTO> result = new PageResult<ChannelProductTextDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
    }

    @Override
    public List<ChannelProductTextDTO> findChannelProductTextAll(ChannelProductTextDTO dto) {
        ChannelProductTextPO po = ChannelProductTextConverter.toPO(dto);
        List<ChannelProductTextPO> list = channelProductTextReadManage.findChannelProductTextAll(po);
        return ChannelProductTextConverter.toDTO(list);
    }
}
