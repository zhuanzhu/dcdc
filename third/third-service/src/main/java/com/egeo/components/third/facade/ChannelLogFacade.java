package com.egeo.components.third.facade;

import com.egeo.components.third.dto.ChannelLogDTO;
import com.egeo.components.third.dto.RemoteExecuteDTO;
import com.egeo.components.third.po.ChannelLogPO;
import com.egeo.components.third.service.read.ChannelLogReadService;
import com.egeo.components.third.service.write.ChannelLogWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/10/30 14:30
 * @Version V1.0
 **/
@Component
public class ChannelLogFacade {


    @Resource
    private ChannelLogReadService channelLogReadService;

    @Resource
    private ChannelLogWriteService channelLogWriteService;


    public ChannelLogDTO findChannelLogById(ChannelLogDTO dto){

        return channelLogReadService.findChannelLogById(dto);
    }

    public PageResult<ChannelLogDTO> findChannelLogOfPage(ChannelLogDTO dto, Pagination page){

        return channelLogReadService.findChannelLogOfPage(dto, page);

    }

    public List<ChannelLogDTO> findChannelLogAll(ChannelLogDTO dto){

        return channelLogReadService.findChannelLogAll(dto);

    }
    public Long insertChannelLogWithTx(ChannelLogDTO dto){

        return channelLogWriteService.insertChannelLogWithTx(dto);
    }

    public int updateChannelLogWithTx(ChannelLogDTO dto){

        return channelLogWriteService.updateChannelLogWithTx(dto);
    }

    public int deleteChannelLogWithTx(ChannelLogDTO dto){

        return channelLogWriteService.deleteChannelLogWithTx(dto);

    }

    public ChannelLogDTO saveChannelLogWithTx(RemoteExecuteDTO dto){
        try {
            ChannelLogDTO channelLogDTO = new ChannelLogDTO(dto);
             Long id = channelLogWriteService.insertChannelLogWithTx(channelLogDTO);
            channelLogDTO.setId(id);
             return channelLogDTO;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public void editChannelLogWithTx(ChannelLogDTO dto){
        try {
             channelLogWriteService.updateChannelLogWithTx(dto);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
