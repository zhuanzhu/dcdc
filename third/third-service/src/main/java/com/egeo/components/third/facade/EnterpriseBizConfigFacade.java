package com.egeo.components.third.facade;

import com.egeo.components.third.dto.EnterpriseBizConfigDTO;
import com.egeo.components.third.service.read.EnterpriseBizConfigReadService;
import com.egeo.components.third.service.write.EnterpriseBizConfigWriteService;
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
public class EnterpriseBizConfigFacade {

    @Resource
    private EnterpriseBizConfigReadService enterpriseBizConfigReadService;

    @Resource
    private EnterpriseBizConfigWriteService enterpriseBizConfigWriteService;


    public EnterpriseBizConfigDTO findEnterpriseBizConfigById(EnterpriseBizConfigDTO dto){

        return enterpriseBizConfigReadService.findEnterpriseBizConfigById(dto);
    }

    public PageResult<EnterpriseBizConfigDTO> findEnterpriseBizConfigOfPage(EnterpriseBizConfigDTO dto, Pagination page){

        return enterpriseBizConfigReadService.findEnterpriseBizConfigOfPage(dto, page);

    }

    public List<EnterpriseBizConfigDTO> findEnterpriseBizConfigAll(EnterpriseBizConfigDTO dto){

        return enterpriseBizConfigReadService.findEnterpriseBizConfigAll(dto);

    }
    public Long insertEnterpriseBizConfigWithTx(EnterpriseBizConfigDTO dto){

        return enterpriseBizConfigWriteService.insertEnterpriseBizConfigWithTx(dto);
    }

    public int updateEnterpriseBizConfigWithTx(EnterpriseBizConfigDTO dto){

        return enterpriseBizConfigWriteService.updateEnterpriseBizConfigWithTx(dto);
    }

    public int deleteEnterpriseBizConfigWithTx(EnterpriseBizConfigDTO dto){

        return enterpriseBizConfigWriteService.deleteEnterpriseBizConfigWithTx(dto);

    }

}
