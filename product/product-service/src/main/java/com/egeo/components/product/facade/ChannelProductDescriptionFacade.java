package com.egeo.components.product.facade;

import com.egeo.components.product.dto.channel.ChannelProductDescriptionDTO;
import com.egeo.components.product.service.read.ChannelProductDescriptionReadService;
import com.egeo.components.product.service.write.ChannelProductDescriptionWriteService;
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
public class ChannelProductDescriptionFacade {

    @Resource
    private ChannelProductDescriptionReadService channelProductDescriptionReadService;

    @Resource
    private ChannelProductDescriptionWriteService channelProductDescriptionWriteService;


    public ChannelProductDescriptionDTO findChannelProductDescriptionById(ChannelProductDescriptionDTO dto){

        return channelProductDescriptionReadService.findChannelProductDescriptionById(dto);
    }

    public PageResult<ChannelProductDescriptionDTO> findChannelProductDescriptionOfPage(ChannelProductDescriptionDTO dto, Pagination page){

        return channelProductDescriptionReadService.findChannelProductDescriptionOfPage(dto, page);

    }

    public List<ChannelProductDescriptionDTO> findChannelProductDescriptionAll(ChannelProductDescriptionDTO dto){

        return channelProductDescriptionReadService.findChannelProductDescriptionAll(dto);

    }
    public Long insertChannelProductDescriptionWithTx(ChannelProductDescriptionDTO dto){

        return channelProductDescriptionWriteService.insertChannelProductDescriptionWithTx(dto);
    }

    public int updateChannelProductDescriptionWithTx(ChannelProductDescriptionDTO dto){

        return channelProductDescriptionWriteService.updateChannelProductDescriptionWithTx(dto);
    }

    public int deleteChannelProductDescriptionWithTx(ChannelProductDescriptionDTO dto){

        return channelProductDescriptionWriteService.deleteChannelProductDescriptionWithTx(dto);

    }

    public ChannelProductDescriptionDTO getOneByProductId(String productId,String channelCode){
        ChannelProductDescriptionDTO queryDto = new ChannelProductDescriptionDTO();
        queryDto.setProductId(productId);
        queryDto.setChannelCode(channelCode);
        List<ChannelProductDescriptionDTO> list = findChannelProductDescriptionAll(queryDto);
        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        return list.get(0);
    }
}
