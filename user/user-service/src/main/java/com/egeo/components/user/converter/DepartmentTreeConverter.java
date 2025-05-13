package com.egeo.components.user.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.user.dto.DepartmentTreeDTO;
import com.egeo.components.user.po.DepartmentTreePO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2017-12-20 11:06:04
 */
public class DepartmentTreeConverter {
	
	public static DepartmentTreeDTO toDTO(DepartmentTreePO src) {
		if (src == null)
		return null;	
		
		DepartmentTreeDTO tar = new DepartmentTreeDTO();
		
		tar.setName(src.getName());
		tar.setpName(src.getpName());
		
		List<DepartmentTreePO> pochilds = src.getChilds();
		
		if(pochilds != null){
			//有子集，遍历/
			List<DepartmentTreeDTO> dtochilds = new ArrayList<DepartmentTreeDTO>();
			tar.setChilds(dtochilds);
			for (DepartmentTreePO departmentTreePO : pochilds) {
				DepartmentTreeDTO departmentTreeDTO=null;
				dtochilds.add(departmentTreeDTO);
				departmentTreeDTO = DepartmentTreeConverter.toDTO(departmentTreePO);
			}
		}
		return tar;
	}

	public static DepartmentTreePO toPO(DepartmentTreeDTO src) {
		if (src == null)
		return null;	
		
		DepartmentTreePO tar = new DepartmentTreePO();
		
		tar.setName(src.getName());
		tar.setpName(src.getpName());
		
		List<DepartmentTreeDTO> dtochilds = src.getChilds();
		
		if(dtochilds != null){
			//有子集，遍历
			List<DepartmentTreePO> pochilds = new ArrayList<DepartmentTreePO>();
			tar.setChilds(pochilds);
			for (DepartmentTreeDTO departmentTreeDTO : dtochilds) {
				DepartmentTreePO departmentTreePO=null;
				pochilds.add(departmentTreePO);
				departmentTreePO = DepartmentTreeConverter.toPO(departmentTreeDTO);
			}
		}
		return tar;
	}

	public static List<DepartmentTreeDTO> toDTO(List<DepartmentTreePO> srcs) {
		if (srcs == null)
			return null;
		List<DepartmentTreeDTO> list = new ArrayList<DepartmentTreeDTO>();
		for (DepartmentTreePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<DepartmentTreePO> toPO(List<DepartmentTreeDTO> srcs) {
		if (srcs == null)
			return null;
		List<DepartmentTreePO> list = new ArrayList<DepartmentTreePO>();
		for (DepartmentTreeDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	