package com.egeo.components.third.facade;

import com.egeo.components.third.common.StateEnum;
import com.egeo.components.third.dto.ChannelServiceConfigDTO;
import com.egeo.components.third.service.read.ChannelServiceConfigReadService;
import com.egeo.components.third.service.write.ChannelServiceConfigWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
@Component
public class ChannelServiceConfigFacade {

    @Resource
    private ChannelServiceConfigReadService channelServiceConfigReadService;

    @Resource
    private ChannelServiceConfigWriteService channelServiceConfigWriteService;


    public ChannelServiceConfigDTO findChannelServiceConfigById(ChannelServiceConfigDTO dto){

        return channelServiceConfigReadService.findChannelServiceConfigById(dto);
    }

    public PageResult<ChannelServiceConfigDTO> findChannelServiceConfigOfPage(ChannelServiceConfigDTO dto, Pagination page){

        return channelServiceConfigReadService.findChannelServiceConfigOfPage(dto, page);

    }

    public List<ChannelServiceConfigDTO> findChannelServiceConfigAll(ChannelServiceConfigDTO dto){

        return channelServiceConfigReadService.findChannelServiceConfigAll(dto);

    }
    public Long insertChannelServiceConfigWithTx(ChannelServiceConfigDTO dto){

        return channelServiceConfigWriteService.insertChannelServiceConfigWithTx(dto);
    }

    public int updateChannelServiceConfigWithTx(ChannelServiceConfigDTO dto){

        return channelServiceConfigWriteService.updateChannelServiceConfigWithTx(dto);
    }

    public int deleteChannelServiceConfigWithTx(ChannelServiceConfigDTO dto){

        return channelServiceConfigWriteService.deleteChannelServiceConfigWithTx(dto);

    }


    /**
     * @Description 根据渠道、接口和类型获取有效的接口配置信息
     **/
    public ChannelServiceConfigDTO getChannelServiceConfigDTO(String channelCode,String channelServiceName,String channelServiceType){
        List<ChannelServiceConfigDTO> list = findChannelServiceConfigAll(new ChannelServiceConfigDTO(channelCode,channelServiceName,channelServiceType, StateEnum.NORMAL.getCode()));
        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        return list.get(0);
    }
}
