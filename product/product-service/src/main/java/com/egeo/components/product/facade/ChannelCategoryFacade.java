package com.egeo.components.product.facade;

import com.egeo.components.product.dto.ChannelCategoryDTO;
import com.egeo.components.product.dto.channel.ChannelProductDTO;
import com.egeo.components.product.service.read.ChannelCategoryReadService;
import com.egeo.components.product.service.read.ChannelProductReadService;
import com.egeo.components.product.service.write.ChannelCategoryWriteService;
import com.egeo.components.product.service.write.ChannelProductWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
@Component
public class ChannelCategoryFacade {

    @Resource
    private ChannelCategoryReadService channelCategoryReadService;

    @Resource
    private ChannelCategoryWriteService channelCategoryWriteService;


    public ChannelCategoryDTO findChannelCategoryById(ChannelCategoryDTO dto){

        return channelCategoryReadService.findChannelCategoryById(dto);
    }

    public PageResult<ChannelCategoryDTO> findChannelCategoryOfPage(ChannelCategoryDTO dto, Pagination page){

        return channelCategoryReadService.findChannelCategoryOfPage(dto, page);

    }

    public List<ChannelCategoryDTO> findChannelCategoryAll(ChannelCategoryDTO dto){

        return channelCategoryReadService.findChannelCategoryAll(dto);

    }
    public Long insertChannelCategoryWithTx(ChannelCategoryDTO dto){

        return channelCategoryWriteService.insertChannelCategoryWithTx(dto);
    }

    public int updateChannelCategoryWithTx(ChannelCategoryDTO dto){

        return channelCategoryWriteService.updateChannelCategoryWithTx(dto);
    }

    public int deleteChannelCategoryWithTx(ChannelCategoryDTO dto){

        return channelCategoryWriteService.deleteChannelCategoryWithTx(dto);

    }
}
