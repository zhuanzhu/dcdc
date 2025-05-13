package com.egeo.components.user.service.read.impl;

import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.egeo.components.user.service.read.PlatformReadService;
import com.egeo.components.user.dto.PlatformDTO;
import com.egeo.components.user.manage.read.PlatformReadManage;
import com.egeo.components.user.po.PlatformPO;
import com.egeo.components.user.dao.read.PlatformReadDAO;
import com.egeo.components.user.converter.PlatformConverter;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("platformReadService")
public class PlatformReadServiceImpl implements PlatformReadService {
	@Autowired
	private PlatformReadManage platformReadManage ;

    @Override
    public PageResult<PlatformDTO> findAll(PlatformDTO dto,Pagination page) {
        PlatformPO po = PlatformConverter.toPO(dto);
        PageResult<PlatformPO> pageResult = platformReadManage.findPage(page, po);
        
        List<PlatformDTO> list = new ArrayList<PlatformDTO>();
        for (PlatformPO tmp : pageResult.getList()) {
            PlatformDTO PlatformDTO = PlatformConverter.toDTO(tmp);
            PlatformDTO.setName(tmp.getName());
                list.add(PlatformDTO);
        }
        PageResult<PlatformDTO> result = new PageResult<PlatformDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
    }

    @Override
    public List<PlatformDTO> PlatformByUserId(Long userId) {
        List<PlatformPO> list = platformReadManage.PlatformByUserId(userId);
        List<PlatformPO> allPlatform=platformReadManage.findAll(new PlatformPO());
        List<PlatformDTO> dto = PlatformConverter.toDTO(allPlatform);
        //赋值available
        for(PlatformDTO platformDTO:dto){
        	platformDTO.setIsAvailable(0);
        	for(PlatformPO myPf:list){
        		if(platformDTO.getId().equals(myPf.getId())){
        		    platformDTO.setChecked(true);
        		}
        	}
        }
        return dto;
    }

    @Override
    public PlatformDTO find(Long pid) {
        PlatformPO platformPO = platformReadManage.find(pid);
        return PlatformConverter.toDTO(platformPO);
    }
    public List<PlatformDTO> PlatformByUId(Long userId) {
        List<PlatformPO> list = platformReadManage.PlatformByUserId(userId);
        return PlatformConverter.toDTO(list);
    }

    @Override
    public List<PlatformDTO> PlatformByUid(Long userId) {
        
        List<PlatformPO> list = platformReadManage.PlatformByUid(userId);
        return PlatformConverter.toDTO(list);
    }
    
    @Override
    public List<PlatformDTO> findAllPlatform() {
    	List<PlatformPO> list = platformReadManage.findAllPlatform();
        return PlatformConverter.toDTO(list);
    }
    
}
	