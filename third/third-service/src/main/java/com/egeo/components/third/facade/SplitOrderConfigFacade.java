package com.egeo.components.third.facade;

import com.egeo.components.third.dto.SplitOrderConfigDTO;
import com.egeo.components.third.service.read.SplitOrderConfigReadService;
import com.egeo.components.third.service.write.SplitOrderConfigWriteService;
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
public class SplitOrderConfigFacade {

    @Resource
    private SplitOrderConfigReadService splitOrderConfigReadService;

    @Resource
    private SplitOrderConfigWriteService splitOrderConfigWriteService;


    public SplitOrderConfigDTO findSplitOrderConfigById(SplitOrderConfigDTO dto){

        return splitOrderConfigReadService.findSplitOrderConfigById(dto);
    }

    public PageResult<SplitOrderConfigDTO> findSplitOrderConfigOfPage(SplitOrderConfigDTO dto, Pagination page){

        return splitOrderConfigReadService.findSplitOrderConfigOfPage(dto, page);

    }

    public List<SplitOrderConfigDTO> findSplitOrderConfigAll(SplitOrderConfigDTO dto){

        return splitOrderConfigReadService.findSplitOrderConfigAll(dto);

    }
    public Long insertSplitOrderConfigWithTx(SplitOrderConfigDTO dto){

        return splitOrderConfigWriteService.insertSplitOrderConfigWithTx(dto);
    }

    public int updateSplitOrderConfigWithTx(SplitOrderConfigDTO dto){

        return splitOrderConfigWriteService.updateSplitOrderConfigWithTx(dto);
    }

    public int deleteSplitOrderConfigWithTx(SplitOrderConfigDTO dto){

        return splitOrderConfigWriteService.deleteSplitOrderConfigWithTx(dto);

    }
}
