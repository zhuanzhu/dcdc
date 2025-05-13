package com.egeo.components.third.facade;

import com.egeo.components.third.common.StateEnum;
import com.egeo.components.third.dto.EnterpriseChannelServiceChoiceDTO;
import com.egeo.components.third.dto.EnterpriseServiceRouteRequestDTO;
import com.egeo.components.third.service.read.EnterpriseChannelServiceChoiceReadService;
import com.egeo.components.third.service.write.EnterpriseChannelServiceChoiceWriteService;
import com.egeo.exception.BusinessException;
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
public class EnterpriseChannelServiceChoiceFacade {
    @Resource
    private EnterpriseChannelServiceChoiceReadService enterpriseChannelServiceChoiceReadService;

    @Resource
    private EnterpriseChannelServiceChoiceWriteService enterpriseChannelServiceChoiceWriteService;


    public EnterpriseChannelServiceChoiceDTO findEnterpriseChannelServiceChoiceById(EnterpriseChannelServiceChoiceDTO dto){

        return enterpriseChannelServiceChoiceReadService.findEnterpriseChannelServiceChoiceById(dto);
    }

    public PageResult<EnterpriseChannelServiceChoiceDTO> findEnterpriseChannelServiceChoiceOfPage(EnterpriseChannelServiceChoiceDTO dto, Pagination page){

        return enterpriseChannelServiceChoiceReadService.findEnterpriseChannelServiceChoiceOfPage(dto, page);

    }

    public List<EnterpriseChannelServiceChoiceDTO> findEnterpriseChannelServiceChoiceAll(EnterpriseChannelServiceChoiceDTO dto){

        return enterpriseChannelServiceChoiceReadService.findEnterpriseChannelServiceChoiceAll(dto);

    }
    public Long insertEnterpriseChannelServiceChoiceWithTx(EnterpriseChannelServiceChoiceDTO dto){

        return enterpriseChannelServiceChoiceWriteService.insertEnterpriseChannelServiceChoiceWithTx(dto);
    }

    public int updateEnterpriseChannelServiceChoiceWithTx(EnterpriseChannelServiceChoiceDTO dto){

        return enterpriseChannelServiceChoiceWriteService.updateEnterpriseChannelServiceChoiceWithTx(dto);
    }

    public int deleteEnterpriseChannelServiceChoiceWithTx(EnterpriseChannelServiceChoiceDTO dto){

        return enterpriseChannelServiceChoiceWriteService.deleteEnterpriseChannelServiceChoiceWithTx(dto);

    }

    public List<EnterpriseChannelServiceChoiceDTO> getEnterpriseChannelServiceRute(String enterpriseId,String  channelServiceName,String channelServiceType) {
        EnterpriseChannelServiceChoiceDTO serviceChoiceDTO = new EnterpriseChannelServiceChoiceDTO();
        serviceChoiceDTO.setEnterpriseId(enterpriseId);
        serviceChoiceDTO.setChannelServiceName(channelServiceName);
        serviceChoiceDTO.setChannelServiceType(channelServiceType);
        serviceChoiceDTO.setState(StateEnum.NORMAL.getCode());
        return findEnterpriseChannelServiceChoiceAll(serviceChoiceDTO);
    }

    public EnterpriseChannelServiceChoiceDTO getSingleEnterpriseChannelServiceRute(String enterpriseId,String  channelServiceName,String channelServiceType) {
        List<EnterpriseChannelServiceChoiceDTO> ruteList = getEnterpriseChannelServiceRute(enterpriseId,channelServiceName,channelServiceType);
        checkRuteIsVail(ruteList);
        if(!CollectionUtils.isEmpty(ruteList)){
            return ruteList.get(0);
        }
        return null;
    }

    private void checkRuteIsVail(List<EnterpriseChannelServiceChoiceDTO> ruteList){
        if(!CollectionUtils.isEmpty(ruteList) && ruteList.size() >1){
            throw new BusinessException("一种业务类型应仅有一种规则");
        }
    }
}
