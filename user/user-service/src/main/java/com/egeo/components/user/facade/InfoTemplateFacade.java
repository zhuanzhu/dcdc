package com.egeo.components.user.facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.egeo.components.user.dto.InfoTemplateDTO;
import com.egeo.components.user.dto.InfoTemplateSendWayDTO;
import com.egeo.components.user.service.read.InfoTemplateReadService;
import com.egeo.components.user.service.read.InfoTemplateSendWayReadService;
import com.egeo.components.user.service.write.InfoTemplateWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class InfoTemplateFacade {
	
	@Resource
	private InfoTemplateReadService infoTemplateReadService;
	
	@Resource
	private InfoTemplateWriteService infoTemplateWriteService;
	
	@Resource
	private InfoTemplateSendWayReadService infoTemplateSendWayReadService;
	
	
	public Map<String, Object> findInfoTemplateById(Long infoTemplateId){
		Map<String, Object> map = new HashMap<>();
		// 根据消息模版id查询消息模版信息
		InfoTemplateDTO infoTemplateDTO = new InfoTemplateDTO();
		infoTemplateDTO.setId(infoTemplateId);
		InfoTemplateDTO infoTemplateDTO2 = infoTemplateReadService.findInfoTemplateById(infoTemplateDTO);
		
		map.put("id", infoTemplateDTO2.getId());
		map.put("name", infoTemplateDTO2.getName());
		map.put("triggers", infoTemplateDTO2.getTriggers());
		
		// 根据消息模版id查询发送方式
		InfoTemplateSendWayDTO infoTemplateSendWayDTO = new InfoTemplateSendWayDTO();
		infoTemplateSendWayDTO.setInfoTemplateId(infoTemplateId);
		List<InfoTemplateSendWayDTO> infoTemplateSendWayList = infoTemplateSendWayReadService.findInfoTemplateSendWayAll(infoTemplateSendWayDTO);
		List<Long> sendWayIdList = new ArrayList<>();
		for (InfoTemplateSendWayDTO infoTemplateSendWayDTO2 : infoTemplateSendWayList) {
			sendWayIdList.add(infoTemplateSendWayDTO2.getSendWayId());
		}
		map.put("sendWayIdList", sendWayIdList);
		
		map.put("systemInfo", infoTemplateDTO2.getSystemInfo());
		map.put("moblieInfo", infoTemplateDTO2.getMoblieInfo());
		map.put("weChatOfficialInfo", infoTemplateDTO2.getWeChatOfficialInfo());
		map.put("mailInfoTitle", infoTemplateDTO2.getMailInfoTitle());
		map.put("mailInfo", infoTemplateDTO2.getMailInfo());
		map.put("mailRemark", infoTemplateDTO2.getMailRemark());
		map.put("infoTemplateRemark", infoTemplateDTO2.getInfoTemplateRemark());
		map.put("infoInform", infoTemplateDTO2.getInfoInform());
		return map;
	}

	public PageResult<Map<String, Object>> findInfoTemplateOfPage(InfoTemplateDTO dto,Pagination page){
		PageResult<InfoTemplateDTO> rt = infoTemplateReadService.findInfoTemplateOfPage(dto, page);
		List<InfoTemplateDTO> infoTemplateList = rt.getList();
		List<Map<String, Object>> list = new ArrayList<>();
		for (InfoTemplateDTO infoTemplateDTO : infoTemplateList) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", infoTemplateDTO.getId());
			map.put("name", infoTemplateDTO.getName());
			map.put("triggers", infoTemplateDTO.getTriggers());
			map.put("isStart", infoTemplateDTO.getIsStart());
			
			// 根据消息模版id查询发送方式
			List<String> sendWayName = infoTemplateSendWayReadService.sendWayNameByInfoTemplateId(infoTemplateDTO.getId());
			StringBuilder sendWayNames = new StringBuilder();
			for (String name : sendWayName) {
				sendWayNames.append(name);
				sendWayNames.append(" ");
			}
			map.put("sendWayNames", sendWayNames);
			list.add(map);
		}
		PageResult<Map<String, Object>> result = new PageResult<Map<String, Object>>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		return result;
		
	}

	public List<InfoTemplateDTO> findInfoTemplateAll(InfoTemplateDTO dto){
		
		return infoTemplateReadService.findInfoTemplateAll(dto);
		
	}

	public Long insertInfoTemplateWithTx(InfoTemplateDTO dto){
		
		return infoTemplateWriteService.insertInfoTemplateWithTx(dto);
	}

	public int updateInfoTemplateWithTx(InfoTemplateDTO dto,List<Long> sendWayIds){
		
		return infoTemplateWriteService.updateInfoTemplateWithTx(dto,sendWayIds);
	}

	public int deleteInfoTemplateWithTx(InfoTemplateDTO dto){
		
		return infoTemplateWriteService.deleteInfoTemplateWithTx(dto);
		
	}
	/**
	 * 根据消息模版id启用停用消息模版
	 * @param infoTemplateId
	 * @return
	 */
	public int isStartByIdWithTx(Long infoTemplateId) {
		// TODO Auto-generated method stub
		return infoTemplateWriteService.isStartByIdWithTx(infoTemplateId);
	}

}
	