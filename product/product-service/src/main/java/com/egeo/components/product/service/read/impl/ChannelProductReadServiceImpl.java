package com.egeo.components.product.service.read.impl;

import com.egeo.components.product.converter.ChannelProductConverter;
import com.egeo.components.product.dto.channel.ChannelProductDTO;
import com.egeo.components.product.manage.read.ChannelProductReadManage;
import com.egeo.components.product.po.ChannelProductPO;
import com.egeo.components.product.service.read.ChannelProductReadService;
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
@Service("channelProductReadService")
public class ChannelProductReadServiceImpl implements ChannelProductReadService {



    @Autowired
    private ChannelProductReadManage channelProductReadManage;

    @Override
    public ChannelProductDTO findChannelProductById(ChannelProductDTO dto) {
        ChannelProductPO po = ChannelProductConverter.toPO(dto);
        ChannelProductPO list = channelProductReadManage.findChannelProductById(po);
        return ChannelProductConverter.toDTO(list);
    }

    @Override
    public PageResult<ChannelProductDTO> findChannelProductOfPage(ChannelProductDTO dto, Pagination page) {
        ChannelProductPO po = ChannelProductConverter.toPO(dto);
        PageResult<ChannelProductPO> pageResult = channelProductReadManage.findChannelProductOfPage(po, page);

        List<ChannelProductDTO> list = ChannelProductConverter.toDTO(pageResult.getList());
        PageResult<ChannelProductDTO> result = new PageResult<ChannelProductDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
    }

    @Override
    public List<ChannelProductDTO> findChannelProductAll(ChannelProductDTO dto) {
        ChannelProductPO po = ChannelProductConverter.toPO(dto);
        List<ChannelProductPO> list = channelProductReadManage.findChannelProductAll(po);
        return ChannelProductConverter.toDTO(list);
    }
}
