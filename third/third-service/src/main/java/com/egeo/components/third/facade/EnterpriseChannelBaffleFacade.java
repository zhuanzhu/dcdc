package com.egeo.components.third.facade;

import com.egeo.components.third.common.StateEnum;
import com.egeo.components.third.dto.EnterpriseChannelBaffleDTO;
import com.egeo.components.third.service.read.EnterpriseChannelBaffleReadService;
import com.egeo.components.third.service.write.EnterpriseChannelBaffleWriteService;
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
public class EnterpriseChannelBaffleFacade {


    @Resource
    private EnterpriseChannelBaffleReadService enterpriseChannelBaffleReadService;

    @Resource
    private EnterpriseChannelBaffleWriteService enterpriseChannelBaffleWriteService;


    public EnterpriseChannelBaffleDTO findEnterpriseChannelBaffleById(EnterpriseChannelBaffleDTO dto){

        return enterpriseChannelBaffleReadService.findEnterpriseChannelBaffleById(dto);
    }

    public PageResult<EnterpriseChannelBaffleDTO> findEnterpriseChannelBaffleOfPage(EnterpriseChannelBaffleDTO dto, Pagination page){

        return enterpriseChannelBaffleReadService.findEnterpriseChannelBaffleOfPage(dto, page);

    }

    public List<EnterpriseChannelBaffleDTO> findEnterpriseChannelBaffleAll(EnterpriseChannelBaffleDTO dto){

        return enterpriseChannelBaffleReadService.findEnterpriseChannelBaffleAll(dto);

    }
    public Long insertEnterpriseChannelBaffleWithTx(EnterpriseChannelBaffleDTO dto){

        return enterpriseChannelBaffleWriteService.insertEnterpriseChannelBaffleWithTx(dto);
    }

    public int updateEnterpriseChannelBaffleWithTx(EnterpriseChannelBaffleDTO dto){

        return enterpriseChannelBaffleWriteService.updateEnterpriseChannelBaffleWithTx(dto);
    }

    public int deleteEnterpriseChannelBaffleWithTx(EnterpriseChannelBaffleDTO dto){
        return enterpriseChannelBaffleWriteService.deleteEnterpriseChannelBaffleWithTx(dto);
    }

    public EnterpriseChannelBaffleDTO findEnterpriseChannelBaffleDTO(Integer enterpriseId,String channelCode,String channelServiceName,String channelServiceType){
        EnterpriseChannelBaffleDTO dto = new EnterpriseChannelBaffleDTO(enterpriseId,channelCode,channelServiceName,channelServiceType, StateEnum.NORMAL.getCode());
        List<EnterpriseChannelBaffleDTO> list = this.findEnterpriseChannelBaffleAll(dto);
        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        return list.get(0);
    }
}
