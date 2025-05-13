package com.egeo.components.cms.service.read.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.service.read.CmsCfgKeyReadService;
import com.egeo.components.cms.manage.read.CmsCfgKeyReadManage;
import com.egeo.components.cms.manage.read.CmsCfgValueReadManage;
import com.egeo.components.cms.converter.CmsCfgKeyConverter;
import com.egeo.components.cms.dto.CmsCfgKeyDTO;
import com.egeo.components.cms.po.CmsCfgKeyPO;
import com.egeo.components.cms.po.CmsCfgValuePO;
import com.egeo.core.Constant.CmsConstant;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;

@Service("cmsCfgKeyReadService")
public class CmsCfgKeyReadServiceImpl  implements CmsCfgKeyReadService {
	@Autowired
	private CmsCfgKeyReadManage cmsCfgKeyReadManage;
	@Autowired
	private CmsCfgValueReadManage cmsCfgValueReadManage;
	

	@Override
	public CmsCfgKeyDTO findCmsCfgKeyById(CmsCfgKeyDTO dto) {
		CmsCfgKeyPO po = CmsCfgKeyConverter.toPO(dto);
		CmsCfgKeyPO list = cmsCfgKeyReadManage.findCmsCfgKeyById(po);		
		return CmsCfgKeyConverter.toDTO(list);
	}

	@Override
	public PageResult<CmsCfgKeyDTO> findCmsCfgKeyOfPage(CmsCfgKeyDTO dto, Pagination page) {
		CmsCfgKeyPO po = CmsCfgKeyConverter.toPO(dto);
        PageResult<CmsCfgKeyPO> pageResult = cmsCfgKeyReadManage.findCmsCfgKeyOfPage(po, page);
        
        List<CmsCfgKeyDTO> list = CmsCfgKeyConverter.toDTO(pageResult.getList());
        PageResult<CmsCfgKeyDTO> result = new PageResult<CmsCfgKeyDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<CmsCfgKeyDTO> findCmsCfgKeyAll(CmsCfgKeyDTO dto) {
		CmsCfgKeyPO po = CmsCfgKeyConverter.toPO(dto);
		List<CmsCfgKeyPO> list = cmsCfgKeyReadManage.findCmsCfgKeyAll(po);	
		List<CmsCfgKeyDTO> cmCfgKeyList = CmsCfgKeyConverter.toDTO(list);
		if (cmCfgKeyList == null) {
			cmCfgKeyList = new ArrayList<>();
		}
		for (CmsCfgKeyDTO cmsCfgKey : cmCfgKeyList) {
			if (cmsCfgKey.getType() == CmsConstant.CMS_CFG_KEY_TYPE_SELECT) {
				CmsCfgValuePO cmsCfgValue = new CmsCfgValuePO();
				cmsCfgValue.setCfgKeyId(cmsCfgKey.getId());
				List<CmsCfgValuePO> cfgValueList = cmsCfgValueReadManage.findCmsCfgValueAll(cmsCfgValue);
				List<Map<String, Object>> cfgValues = new ArrayList<>();
				for (CmsCfgValuePO cv : cfgValueList) {
					Map<String, Object> cfgValue = new HashMap<>();
					cfgValue.put("id", cv.getCode());
					cfgValue.put("name", cv.getName());
					cfgValues.add(cfgValue);
				}
				cmsCfgKey.setCfgValueList(cfgValues);
			}
		}
		return cmCfgKeyList;
	}

	@Override
	public List<CmsCfgKeyDTO> findElementCfgKeyByTemplateId(Long templateId) {
		List<CmsCfgKeyDTO> cmCfgKeyList = CmsCfgKeyConverter.conditionToDTO(cmsCfgKeyReadManage.findElementCfgKeyByTemplateId(templateId));
		if (cmCfgKeyList == null) {
			cmCfgKeyList = new ArrayList<>();
		}
		for (CmsCfgKeyDTO cmsCfgKey : cmCfgKeyList) {
			if (cmsCfgKey.getType() == CmsConstant.CMS_CFG_KEY_TYPE_SELECT 
					|| cmsCfgKey.getType() == CmsConstant.CMS_CFG_KEY_TYPE_RADIO) {
				CmsCfgValuePO cmsCfgValue = new CmsCfgValuePO();
				cmsCfgValue.setCfgKeyId(cmsCfgKey.getId());
				List<CmsCfgValuePO> cfgValueList = cmsCfgValueReadManage.findCmsCfgValueAll(cmsCfgValue);
				if (EmptyUtil.isNotEmpty(cfgValueList)) {
					List<Map<String, Object>> cfgValues = new ArrayList<>();
					for (CmsCfgValuePO po : cfgValueList) {
						Map<String, Object> cfgValue = new HashMap<>();
						cfgValue.put("id", po.getCode());
						cfgValue.put("name", po.getName());
						cfgValues.add(cfgValue);
					}
					cmsCfgKey.setCfgValueList(cfgValues);
				}
			}
		}
		return cmCfgKeyList;
	}
	
	@Override
	public List<CmsCfgKeyDTO> findTemplateCfgKeyByTemplateId(Long templateId) {
		List<CmsCfgKeyDTO> cmCfgKeyList = CmsCfgKeyConverter.conditionToDTO(cmsCfgKeyReadManage.findTemplateCfgKeyByTemplateId(templateId));
		if (cmCfgKeyList == null) {
			cmCfgKeyList = new ArrayList<>();
		}
		for (CmsCfgKeyDTO cmsCfgKey : cmCfgKeyList) {
			if (cmsCfgKey.getType() == CmsConstant.CMS_CFG_KEY_TYPE_SELECT 
					|| cmsCfgKey.getType() == CmsConstant.CMS_CFG_KEY_TYPE_RADIO) {
				CmsCfgValuePO cmsCfgValue = new CmsCfgValuePO();
				cmsCfgValue.setCfgKeyId(cmsCfgKey.getId());
				List<CmsCfgValuePO> cfgValueList = cmsCfgValueReadManage.findCmsCfgValueAll(cmsCfgValue);
				if (EmptyUtil.isNotEmpty(cfgValueList)) {
					List<Map<String, Object>> cfgValues = new ArrayList<>();
					for (CmsCfgValuePO po : cfgValueList) {
						Map<String, Object> cfgValue = new HashMap<>();
						cfgValue.put("id", po.getCode());
						cfgValue.put("name", po.getName());
						cfgValues.add(cfgValue);
					}
					cmsCfgKey.setCfgValueList(cfgValues);
				}
			}
		}
		return cmCfgKeyList;
	}
}
	