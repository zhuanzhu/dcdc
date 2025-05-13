package com.egeo.components.user.service.write.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.converter.DepartmentTempConverter;
import com.egeo.components.user.dto.DepartmentTempDTO;
import com.egeo.components.user.dto.DepartmentTreeDTO;
import com.egeo.components.user.manage.write.DepartmentTempWriteManage;
import com.egeo.components.user.po.DepartmentTempPO;
import com.egeo.components.user.service.write.DepartmentTempWriteService;
import com.egeo.utils.StringUtils;

@Service("departmentTempWriteService")
public class DepartmentTempWriteServiceImpl implements DepartmentTempWriteService {
	@Autowired
	private DepartmentTempWriteManage departmentTempWriteManage;

	@Override
	public Long insertDepartmentTempWithTx(DepartmentTempDTO dto) {
		DepartmentTempPO po = DepartmentTempConverter.toPO(dto);
		Long rt = departmentTempWriteManage.insertDepartmentTempWithTx(po);		
		return rt;
	}

	@Override
	public int updateDepartmentTempWithTx(DepartmentTempDTO dto) {
		DepartmentTempPO po = DepartmentTempConverter.toPO(dto);
		int rt = departmentTempWriteManage.updateDepartmentTempWithTx(po);		
		return rt;
	}

	@Override
	public int deleteDepartmentTempWithTx(DepartmentTempDTO dto) {
		DepartmentTempPO po = DepartmentTempConverter.toPO(dto);
		int rt = departmentTempWriteManage.deleteDepartmentTempWithTx(po);		
		return rt;
	}

	@Override
	public String insertDepartmentTempPlusWithTx(List<DepartmentTreeDTO> sortList, Long companyId, Long platformId) {

		List<Long> departmentTempList = new ArrayList<>();
		
		for (DepartmentTreeDTO departmentTreeDTO : sortList) {
			
			DepartmentTempPO departmentTempPO = new DepartmentTempPO();
			departmentTempPO.setCompanyId(companyId);
			departmentTempPO.setPlatformId(platformId);
			departmentTempPO.setDepartmentName(departmentTreeDTO.getName());
			departmentTempPO.setPId(0L);
			
			Long insertDepartmentTempID = departmentTempWriteManage.insertDepartmentTempWithTx(departmentTempPO);
			
			departmentTempList.add(insertDepartmentTempID);
			
			DepartmentTempPO departmentTempPO2 = new DepartmentTempPO();
			departmentTempPO2.setId(insertDepartmentTempID);
			
			String path=","+insertDepartmentTempID;
			departmentTempPO2.setPath(path);
			departmentTempWriteManage.updateDepartmentTempWithTx(departmentTempPO2);
			
			
			
			if(StringUtils.isNotEmpty(departmentTreeDTO.getChilds())){
				this.insertDepartmentTempByList(departmentTreeDTO.getChilds(), companyId, platformId,insertDepartmentTempID,path,departmentTempList);
	
			}

		}
		
		//list转字符串
		String departmentTempListIdStr="";
		for (Long departmentTempListId : departmentTempList) {
			departmentTempListIdStr= departmentTempListIdStr +"," +departmentTempListId;
		}

		return departmentTempListIdStr;
		
	}

	private List<Long> insertDepartmentTempByList(List<DepartmentTreeDTO> childs, Long companyId, Long platformId,Long pID, String path,List<Long> departmentTempList) {
		
		for (DepartmentTreeDTO departmentTreeDTO2 : childs) {
			
			 DepartmentTempPO departmentTempPO = new DepartmentTempPO();
				
				departmentTempPO.setCompanyId(companyId);
				departmentTempPO.setPlatformId(platformId);
				departmentTempPO.setDepartmentName(departmentTreeDTO2.getName());
				
				departmentTempPO.setPId(pID);
				
				Long insertDepartmentTempId = departmentTempWriteManage.insertDepartmentTempWithTx(departmentTempPO);
				
				departmentTempList.add(insertDepartmentTempId);
				
				DepartmentTempPO departmentTempPO2 = new DepartmentTempPO();
				departmentTempPO2.setId(insertDepartmentTempId);
				
				String newPath= path+","+insertDepartmentTempId;
				departmentTempPO2.setPath(newPath);
				departmentTempWriteManage.updateDepartmentTempWithTx(departmentTempPO2);
				
				if(StringUtils.isNotEmpty(departmentTreeDTO2.getChilds())){
					this.insertDepartmentTempByList(departmentTreeDTO2.getChilds(), companyId, platformId, insertDepartmentTempId, newPath,departmentTempList);
				}
		}
		
		return departmentTempList;
		
	}
}
	