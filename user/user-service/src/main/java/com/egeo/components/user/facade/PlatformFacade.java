package com.egeo.components.user.facade;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.egeo.components.user.dto.PlatformDTO;
import com.egeo.components.user.service.read.PlatformReadService;
import com.egeo.components.user.service.write.PlatformWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Component
public class PlatformFacade {

    @Resource
    private PlatformReadService platformReadService;
    @Resource
    private PlatformWriteService platformWriteService;

    public PageResult<PlatformDTO> findAll(PlatformDTO dto,Pagination page) {
        
        return platformReadService.findAll(dto,page);
    }

    public int upDate(PlatformDTO dto) {
        
        return platformWriteService.upDateWithTx(dto);
    }

    public List<PlatformDTO> PlatformByUserId(Long userId) {
        
        return platformReadService.PlatformByUserId(userId);
    }

    public List<PlatformDTO> PlatformByUId(Long userId) {
        
        return platformReadService.PlatformByUid(userId);
    }

    public Integer deleteById(PlatformDTO dto) {
        
        return platformWriteService.deleteByIdWithTx(dto);
    }

    public String save(PlatformDTO dto) {
        
        return platformWriteService.saveWithTx(dto);
    }

    public List<PlatformDTO> findAllPlatform() {
    	return platformReadService.findAllPlatform();
    }
    
}
