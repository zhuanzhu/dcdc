package com.egeo.components.product.facade;

import com.egeo.components.product.dto.channel.ChannelProductTextDTO;
import com.egeo.components.product.service.read.ChannelProductTextReadService;
import com.egeo.components.product.service.write.ChannelProductTextWriteService;
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
public class ChannelProductTextFacade {

    @Resource
    private ChannelProductTextReadService channelProductTextReadService;

    @Resource
    private ChannelProductTextWriteService channelProductTextWriteService;


    public ChannelProductTextDTO findChannelProductTextById(ChannelProductTextDTO dto){

        return channelProductTextReadService.findChannelProductTextById(dto);
    }

    public PageResult<ChannelProductTextDTO> findChannelProductTextOfPage(ChannelProductTextDTO dto, Pagination page){

        return channelProductTextReadService.findChannelProductTextOfPage(dto, page);

    }

    public List<ChannelProductTextDTO> findChannelProductTextAll(ChannelProductTextDTO dto){

        return channelProductTextReadService.findChannelProductTextAll(dto);

    }
    public Long insertChannelProductTextWithTx(ChannelProductTextDTO dto){

        return channelProductTextWriteService.insertChannelProductTextWithTx(dto);
    }

    public int updateChannelProductTextWithTx(ChannelProductTextDTO dto){

        return channelProductTextWriteService.updateChannelProductTextWithTx(dto);
    }

    public int deleteChannelProductTextWithTx(ChannelProductTextDTO dto){

        return channelProductTextWriteService.deleteChannelProductTextWithTx(dto);

    }
}
