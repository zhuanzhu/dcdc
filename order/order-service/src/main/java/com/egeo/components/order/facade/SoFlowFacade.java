package com.egeo.components.order.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.order.service.read.SoChildFlowReadService;
import com.egeo.components.order.service.read.SoFlowReadService;
import com.egeo.components.order.service.write.SoChildFlowWriteService;
import com.egeo.components.order.service.write.SoFlowWriteService;
import com.egeo.components.order.dto.SoChildFlowDTO;
import com.egeo.components.order.dto.SoFlowDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class SoFlowFacade {
	
	@Resource
	private SoChildFlowReadService soChildFlowReadService;
	
	@Resource
	private SoChildFlowWriteService soChildFlowWriteService;
	
	@Resource
	private SoFlowReadService soFlowReadService;
	
	@Resource
	private SoFlowWriteService soFlowWriteService;
	
	
	public SoChildFlowDTO findSoChildFlowById(SoChildFlowDTO dto){
		
		return soChildFlowReadService.findSoChildFlowById(dto);
	}

	public PageResult<SoChildFlowDTO> findSoChildFlowOfPage(SoChildFlowDTO dto,Pagination page){
		
		return soChildFlowReadService.findSoChildFlowOfPage(dto, page);
		
	}

	public List<SoChildFlowDTO> findSoChildFlowAll(SoChildFlowDTO dto){
		
		return soChildFlowReadService.findSoChildFlowAll(dto);
		
	}

	public Long insertSoChildFlowWithTx(SoChildFlowDTO dto){
		
		return soChildFlowWriteService.insertSoChildFlowWithTx(dto);
	}

	public int updateSoChildFlowWithTx(SoChildFlowDTO dto){
		
		return soChildFlowWriteService.updateSoChildFlowWithTx(dto);
	}

	public int deleteSoChildFlowWithTx(SoChildFlowDTO dto){
		
		return soChildFlowWriteService.deleteSoChildFlowWithTx(dto);
		
	}

	/**
	 * 根据订单id查询操作流水
	 * @param orderId
	 * @return
	 */
	public List<SoFlowDTO> queryFlowListBySoId(Long orderId) {
		return soFlowReadService.queryFlowListBySoId(orderId);
	}

	/**
	 * 根据子订单id查询操作流水
	 * @param soChildId
	 * @return
	 */
	public List<SoChildFlowDTO> querySoChildFlowListBySoChildId(Long soChildId) {
		return soChildFlowReadService.queryFlowListBySoChildId(soChildId);
	}

}
	