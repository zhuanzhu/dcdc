package com.egeo.components.order.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.order.service.read.SoChildReadService;
import com.egeo.components.order.service.read.SoThirdpartyReadService;
import com.egeo.components.order.service.write.SoThirdpartyWriteService;
import com.egeo.components.order.dto.SoChildDTO;
import com.egeo.components.order.dto.SoThirdpartyDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class SoThirdpartyFacade {
	
	@Resource
	private SoThirdpartyReadService soThirdpartyReadService;
	
	@Resource
	private SoThirdpartyWriteService soThirdpartyWriteService;
	
	@Resource
	private SoChildReadService soChildReadService;
	
	public SoThirdpartyDTO findSoThirdpartyById(SoThirdpartyDTO dto){
		
		return soThirdpartyReadService.findSoThirdpartyById(dto);
	}

	public PageResult<SoThirdpartyDTO> findSoThirdpartyOfPage(SoThirdpartyDTO dto,Pagination page){
		
		return soThirdpartyReadService.findSoThirdpartyOfPage(dto, page);
		
	}

	public List<SoThirdpartyDTO> findSoThirdpartyAll(SoThirdpartyDTO dto){
		
		return soThirdpartyReadService.findSoThirdpartyAll(dto);
		
	}

	public Long insertSoThirdpartyWithTx(SoThirdpartyDTO dto){
		
		return soThirdpartyWriteService.insertSoThirdpartyWithTx(dto);
	}

	public int updateSoThirdpartyWithTx(SoThirdpartyDTO dto){
		
		return soThirdpartyWriteService.updateSoThirdpartyWithTx(dto);
	}

	public int deleteSoThirdpartyWithTx(SoThirdpartyDTO dto){
		
		return soThirdpartyWriteService.deleteSoThirdpartyWithTx(dto);
		
	}

	 /**
     * 通过订单id查询子订单信息
     * @param soId
     * @return
     */
    public List<SoChildDTO> querySoChildBySoId(Long soId) {
          SoChildDTO dto = new SoChildDTO();
          dto.setSoId(soId);
          return soChildReadService.findSoChildAll(dto);
    }
}
	