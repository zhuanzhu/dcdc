package com.egeo.components.third.facade;

import com.egeo.components.third.common.StateEnum;
import com.egeo.components.third.dto.EnterpriseChannelServiceDTO;
import com.egeo.components.third.service.read.EnterpriseChannelServiceReadService;
import com.egeo.components.third.service.write.EnterpriseChannelServiceWriteService;
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
public class EnterpriseChannelServiceFacade {

    @Resource
    private EnterpriseChannelServiceReadService enterpriseChannelServiceReadService;

    @Resource
    private EnterpriseChannelServiceWriteService enterpriseChannelServiceWriteService;


    public EnterpriseChannelServiceDTO findEnterpriseChannelServiceById(EnterpriseChannelServiceDTO dto){

        return enterpriseChannelServiceReadService.findEnterpriseChannelServiceById(dto);
    }

    public PageResult<EnterpriseChannelServiceDTO> findEnterpriseChannelServiceOfPage(EnterpriseChannelServiceDTO dto, Pagination page){

        return enterpriseChannelServiceReadService.findEnterpriseChannelServiceOfPage(dto, page);

    }

    public List<EnterpriseChannelServiceDTO> findEnterpriseChannelServiceAll(EnterpriseChannelServiceDTO dto){

        return enterpriseChannelServiceReadService.findEnterpriseChannelServiceAll(dto);

    }
    public Long insertEnterpriseChannelServiceWithTx(EnterpriseChannelServiceDTO dto){

        return enterpriseChannelServiceWriteService.insertEnterpriseChannelServiceWithTx(dto);
    }

    public int updateEnterpriseChannelServiceWithTx(EnterpriseChannelServiceDTO dto){

        return enterpriseChannelServiceWriteService.updateEnterpriseChannelServiceWithTx(dto);
    }

    public int deleteEnterpriseChannelServiceWithTx(EnterpriseChannelServiceDTO dto){

        return enterpriseChannelServiceWriteService.deleteEnterpriseChannelServiceWithTx(dto);

    }

    public List<EnterpriseChannelServiceDTO> findEnterpriseChannelServiceAll(String enterpriseId,String  channelServiceName,String channelServiceType){
        EnterpriseChannelServiceDTO channelServiceDTO = new EnterpriseChannelServiceDTO();
        channelServiceDTO.setEnterpriseId(enterpriseId);
        channelServiceDTO.setChannelServiceName(channelServiceName);
        channelServiceDTO.setChannelServiceType(channelServiceType);
        channelServiceDTO.setState(StateEnum.NORMAL.getCode());
        return enterpriseChannelServiceReadService.findEnterpriseChannelServiceAll(channelServiceDTO);
    }

    public List<EnterpriseChannelServiceDTO> findDefaultEnterpriseChannelServiceAll(String enterpriseId,String  channelServiceName,String channelServiceType){
        EnterpriseChannelServiceDTO channelServiceDTO = new EnterpriseChannelServiceDTO();
        channelServiceDTO.setEnterpriseId(enterpriseId);
        channelServiceDTO.setChannelServiceName(channelServiceName);
        channelServiceDTO.setChannelServiceType(channelServiceType);
        channelServiceDTO.setIfDefault("1");
        return enterpriseChannelServiceReadService.findEnterpriseChannelServiceAll(channelServiceDTO);
    }

}
