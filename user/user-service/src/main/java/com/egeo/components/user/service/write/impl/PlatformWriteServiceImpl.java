package com.egeo.components.user.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.common.BusinessExceptionConstant;
import com.egeo.components.user.converter.PlatformConverter;
import com.egeo.components.user.dto.PlatformDTO;
import com.egeo.components.user.manage.read.PlatformReadManage;
import com.egeo.components.user.manage.write.PlatformWriteManage;
import com.egeo.components.user.po.PlatformPO;
import com.egeo.components.user.service.write.PlatformWriteService;
import com.egeo.exception.BusinessException;

@Service("platformWriteService")
public class PlatformWriteServiceImpl implements PlatformWriteService {
	@Autowired
	private PlatformWriteManage platformWriteManage;
	
	@Autowired
        private PlatformReadManage  platformReadManage;

    @Override
    public int upDateWithTx(PlatformDTO dto) {
        PlatformPO po = PlatformConverter.toPO(dto);
        int rows = 0;
            
        rows = platformWriteManage.upDate(po);
            
        return rows;
    }

    @Override
    public Integer deleteByIdWithTx(PlatformDTO dto) {
        
        return platformWriteManage.deleteById(PlatformConverter.toPO(dto));
    }

    @Override
    public String saveWithTx(PlatformDTO dto) {
        PlatformPO po = PlatformConverter.toPO(dto);
        PlatformPO platformPO = platformReadManage.findById(po);
        int rows = 0;
        if(platformPO != null){
            throw new BusinessException(BusinessExceptionConstant.PID_EXIST, "编号已经存在！");
        }
        PlatformPO po2 = new PlatformPO();
        po2.setName(po.getName());
        if(platformReadManage.findAll(po2) != null){
            throw new BusinessException(BusinessExceptionConstant.PNAME_EXIST, "平台名字已经存在");
        }else{
            rows = platformWriteManage.save(po);
        }
        return rows+"";
    }
}
	