package com.egeo.components.product.service.read.impl;

import com.egeo.components.product.converter.ChannelCategoryConverter;
import com.egeo.components.product.converter.ChannelProductConverter;
import com.egeo.components.product.dto.ChannelCategoryDTO;
import com.egeo.components.product.dto.channel.ChannelProductDTO;
import com.egeo.components.product.manage.read.ChannelCategoryReadManage;
import com.egeo.components.product.manage.read.ChannelProductReadManage;
import com.egeo.components.product.po.ChannelCategoryPO;
import com.egeo.components.product.po.ChannelProductPO;
import com.egeo.components.product.service.read.ChannelCategoryReadService;
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
@Service("channelCategoryReadService")
public class ChannelCategoryReadServiceImpl implements ChannelCategoryReadService {



    @Autowired
    private ChannelCategoryReadManage channelCategoryReadManage;

    @Override
    public ChannelCategoryDTO findChannelCategoryById(ChannelCategoryDTO dto) {
        ChannelCategoryPO po = ChannelCategoryConverter.toPO(dto);
        ChannelCategoryPO list = channelCategoryReadManage.findChannelCategoryById(po);
        return ChannelCategoryConverter.toDTO(list);
    }

    @Override
    public PageResult<ChannelCategoryDTO> findChannelCategoryOfPage(ChannelCategoryDTO dto, Pagination page) {
        ChannelCategoryPO po = ChannelCategoryConverter.toPO(dto);
        PageResult<ChannelCategoryPO> pageResult = channelCategoryReadManage.findChannelCategoryOfPage(po, page);

        List<ChannelCategoryDTO> list = ChannelCategoryConverter.toDTO(pageResult.getList());
        PageResult<ChannelCategoryDTO> result = new PageResult<ChannelCategoryDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
    }

    @Override
    public List<ChannelCategoryDTO> findChannelCategoryAll(ChannelCategoryDTO dto) {
        ChannelCategoryPO po = ChannelCategoryConverter.toPO(dto);
        List<ChannelCategoryPO> list = channelCategoryReadManage.findChannelCategoryAll(po);
        return ChannelCategoryConverter.toDTO(list);
    }
}
