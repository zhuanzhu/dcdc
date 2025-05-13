package com.egeo.components.third.facade;

import com.egeo.components.third.dto.PushOrderInfoDTO;
import com.egeo.components.third.service.read.PushOrderInfoReadService;
import com.egeo.components.third.service.write.PushOrderInfoWriteService;
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
public class PushOrderInfoFacade {

    @Resource
    private PushOrderInfoReadService pushOrderInfoReadService;

    @Resource
    private PushOrderInfoWriteService pushOrderInfoWriteService;


    public PushOrderInfoDTO findPushOrderInfoById(PushOrderInfoDTO dto){

        return pushOrderInfoReadService.findPushOrderInfoById(dto);
    }

    public PageResult<PushOrderInfoDTO> findPushOrderInfoOfPage(PushOrderInfoDTO dto, Pagination page){

        return pushOrderInfoReadService.findPushOrderInfoOfPage(dto, page);

    }

    public List<PushOrderInfoDTO> findPushOrderInfoAll(PushOrderInfoDTO dto){

        return pushOrderInfoReadService.findPushOrderInfoAll(dto);

    }
    public Long insertPushOrderInfoWithTx(PushOrderInfoDTO dto){

        return pushOrderInfoWriteService.insertPushOrderInfoWithTx(dto);
    }

    public int updatePushOrderInfoWithTx(PushOrderInfoDTO dto){

        return pushOrderInfoWriteService.updatePushOrderInfoWithTx(dto);
    }

    public int deletePushOrderInfoWithTx(PushOrderInfoDTO dto){

        return pushOrderInfoWriteService.deletePushOrderInfoWithTx(dto);

    }
}
