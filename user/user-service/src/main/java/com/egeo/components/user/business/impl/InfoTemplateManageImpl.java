package com.egeo.components.user.business.impl;
	

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.user.business.InfoTemplateManage;
import com.egeo.components.user.constant.InfoConstant;
import com.egeo.components.user.dto.InfoTemplateDTO;
import com.egeo.components.user.facade.InfoTemplateFacade;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;

@Service("infoTemplate")
public class InfoTemplateManageImpl implements InfoTemplateManage{

	
	@Resource(name="infoTemplateFacade")
	private InfoTemplateFacade infoTemplateFacade;

	@Override
	public Map<String, Object> findInfoTemplateById(Long infoTemplateId) {
		return infoTemplateFacade.findInfoTemplateById(infoTemplateId);
	}

	@Override
	public PageResult<Map<String, Object>> findInfoTemplateOfPage(InfoTemplateDTO dto, Pagination page) {
		return infoTemplateFacade.findInfoTemplateOfPage(dto, page);
	}

	@Override
	public List<InfoTemplateDTO> findInfoTemplateAll(InfoTemplateDTO dto) {
		return infoTemplateFacade.findInfoTemplateAll(dto);
	}

	@Override
	public Long insertInfoTemplateWithTx(InfoTemplateDTO dto) {
		return infoTemplateFacade.insertInfoTemplateWithTx(dto);
	}

	@Override
	public int updateInfoTemplateWithTx(InfoTemplateDTO dto,List<Long> sendWayIds) {
		if(EmptyUtil.isEmpty(sendWayIds)){
			throw new BusinessException("发送方式不能为空");
		}
		// 验证消息模版数据
		verifyInfoTemplate(dto,sendWayIds);
		return infoTemplateFacade.updateInfoTemplateWithTx(dto,sendWayIds);
	}
	/**
	 * 验证消息模版数据
	 * @param dto
	 */
	private void verifyInfoTemplate(InfoTemplateDTO dto,List<Long> sendWayIds) {
		if(EmptyUtil.isEmpty(dto.getName())){
			throw new BusinessException("模板名称不能为空");
		}
		if(dto.getName().length() > 10){
			throw new BusinessException("模板名称不能超过10个字");
		}
		if(EmptyUtil.isEmpty(dto.getTriggers())){
			throw new BusinessException("触发条件不能为空");
		}
		if(dto.getTriggers().length() > 100){
			throw new BusinessException("触发条件不能超过100个字");
		}
		
		if(sendWayIds.contains(InfoConstant.SYSTEM_INFO_ID.getStatus())){
			if(EmptyUtil.isEmpty(dto.getSystemInfo())){
				throw new BusinessException("系统通知不能为空");
			}
			if(dto.getSystemInfo().length() > 300){
				throw new BusinessException("系统通知不能超过300个字");
			}
		}
		
		if(sendWayIds.contains(InfoConstant.INFO_INFORM_INFO_ID.getStatus())){
			if(EmptyUtil.isEmpty(dto.getInfoInform())){
				throw new BusinessException("消息通知不能为空");
			}
			if(dto.getInfoInform().length() > 300){
				throw new BusinessException("消息通知不能超过300个字");
			}
		}
		
		if(sendWayIds.contains(InfoConstant.MOBLIE__INFO_ID.getStatus())){
			if(EmptyUtil.isEmpty(dto.getMoblieInfo())){
				throw new BusinessException("短信不能为空");
			}
			if(dto.getMoblieInfo().length() > 300){
				throw new BusinessException("短信不能超过300个字");
			}
		}
		
		if(sendWayIds.contains(InfoConstant.WECHAT_OFFICIAL_INFO_ID.getStatus())){
			if(EmptyUtil.isEmpty(dto.getWeChatOfficialInfo())){
				throw new BusinessException("公众号推送不能为空");
			}
			if(dto.getWeChatOfficialInfo().length() > 300){
				throw new BusinessException("公众号推送不能超过300个字");
			}
		}
		
		if(sendWayIds.contains(InfoConstant.MAIL_INFO_ID.getStatus())){
			if(EmptyUtil.isEmpty(dto.getMailInfoTitle())){
				throw new BusinessException("邮件标题不能为空");
			}
			if(dto.getMailInfoTitle().length() > 10){
				throw new BusinessException("邮件标题不能超过10个字");
			}
		}
		
	}

	@Override
	public int deleteInfoTemplateWithTx(InfoTemplateDTO dto) {
		return infoTemplateFacade.deleteInfoTemplateWithTx(dto);
	}
	
	@Override
	public int isStartByIdWithTx(Long infoTemplateId) {
		// TODO Auto-generated method stub
		return infoTemplateFacade.isStartByIdWithTx(infoTemplateId);
	}


}
	