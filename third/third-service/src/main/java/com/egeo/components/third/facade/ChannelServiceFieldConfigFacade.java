package com.egeo.components.third.facade;

import com.egeo.components.third.dto.ChannelServiceFieldConfigDTO;
import com.egeo.components.third.service.read.ChannelServiceFieldConfigReadService;
import com.egeo.components.third.service.write.ChannelServiceFieldConfigWriteService;
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
public class ChannelServiceFieldConfigFacade {

    @Resource
    private ChannelServiceFieldConfigReadService channelServiceFieldConfigReadService;

    @Resource
    private ChannelServiceFieldConfigWriteService channelServiceFieldConfigWriteService;


    public ChannelServiceFieldConfigDTO findChannelServiceFieldConfigById(ChannelServiceFieldConfigDTO dto){

        return channelServiceFieldConfigReadService.findChannelServiceFieldConfigById(dto);
    }

    public PageResult<ChannelServiceFieldConfigDTO> findChannelServiceFieldConfigOfPage(ChannelServiceFieldConfigDTO dto, Pagination page){

        return channelServiceFieldConfigReadService.findChannelServiceFieldConfigOfPage(dto, page);

    }

    public List<ChannelServiceFieldConfigDTO> findChannelServiceFieldConfigAll(ChannelServiceFieldConfigDTO dto){

        return channelServiceFieldConfigReadService.findChannelServiceFieldConfigAll(dto);

    }
    public Long insertChannelServiceFieldConfigWithTx(ChannelServiceFieldConfigDTO dto){

        return channelServiceFieldConfigWriteService.insertChannelServiceFieldConfigWithTx(dto);
    }

    public int updateChannelServiceFieldConfigWithTx(ChannelServiceFieldConfigDTO dto){

        return channelServiceFieldConfigWriteService.updateChannelServiceFieldConfigWithTx(dto);
    }

    public int deleteChannelServiceFieldConfigWithTx(ChannelServiceFieldConfigDTO dto){

        return channelServiceFieldConfigWriteService.deleteChannelServiceFieldConfigWithTx(dto);

    }
}
