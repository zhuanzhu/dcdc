package com.egeo.components.product.service.read.impl;

import com.egeo.components.product.converter.ChannelProductDescriptionConverter;
import com.egeo.components.product.dto.channel.ChannelProductDescriptionDTO;
import com.egeo.components.product.manage.read.ChannelProductDescriptionReadManage;
import com.egeo.components.product.po.ChannelProductDescriptionPO;
import com.egeo.components.product.service.read.ChannelProductDescriptionReadService;
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
@Service("channelProductDescriptionReadService")
public class ChannelProductDescriptionReadServiceImpl implements ChannelProductDescriptionReadService {



    @Autowired
    private ChannelProductDescriptionReadManage channelProductDescriptionReadManage;

    @Override
    public ChannelProductDescriptionDTO findChannelProductDescriptionById(ChannelProductDescriptionDTO dto) {
        ChannelProductDescriptionPO po = ChannelProductDescriptionConverter.toPO(dto);
        ChannelProductDescriptionPO list = channelProductDescriptionReadManage.findChannelProductDescriptionById(po);
        return ChannelProductDescriptionConverter.toDTO(list);
    }

    @Override
    public PageResult<ChannelProductDescriptionDTO> findChannelProductDescriptionOfPage(ChannelProductDescriptionDTO dto, Pagination page) {
        ChannelProductDescriptionPO po = ChannelProductDescriptionConverter.toPO(dto);
        PageResult<ChannelProductDescriptionPO> pageResult = channelProductDescriptionReadManage.findChannelProductDescriptionOfPage(po, page);

        List<ChannelProductDescriptionDTO> list = ChannelProductDescriptionConverter.toDTO(pageResult.getList());
        PageResult<ChannelProductDescriptionDTO> result = new PageResult<ChannelProductDescriptionDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
    }

    @Override
    public List<ChannelProductDescriptionDTO> findChannelProductDescriptionAll(ChannelProductDescriptionDTO dto) {
        ChannelProductDescriptionPO po = ChannelProductDescriptionConverter.toPO(dto);
        List<ChannelProductDescriptionPO> list = channelProductDescriptionReadManage.findChannelProductDescriptionAll(po);
        return ChannelProductDescriptionConverter.toDTO(list);
    }
}
