package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.SoItemSkuAttValueDTO;
import com.egeo.components.product.po.SoItemSkuAttValuePO;

public class SoItemSkuAttValueConverter {

	public static SoItemSkuAttValuePO toPO(SoItemSkuAttValueDTO src){
		if(src==null)
			return null;
		SoItemSkuAttValuePO tar=new SoItemSkuAttValuePO();
		tar.setSkuId(src.getSkuId());
		tar.setAttValueCustom(src.getAttValueCustom());
		tar.setSkuAttNameId(src.getSkuAttNameId());
		tar.setSkuAttValueId(src.getSkuAttValueId());
		return tar;
	}
	
	public static SoItemSkuAttValueDTO toDTO(SoItemSkuAttValuePO src){
		if(src==null)
			return null;
		SoItemSkuAttValueDTO tar=new SoItemSkuAttValueDTO();
		tar.setSkuId(src.getSkuId());
		tar.setAttValueCustom(src.getAttValueCustom());
		tar.setSkuAttNameId(src.getSkuAttNameId());
		tar.setSkuAttValueId(src.getSkuAttValueId());
		//tar.setSkuName(src.get);
		return tar;
	}

	public static List<SoItemSkuAttValueDTO> toDTO(List<SoItemSkuAttValuePO> src) {
		List<SoItemSkuAttValueDTO> tar=new ArrayList<>();;
		for(SoItemSkuAttValuePO p:src){
			tar.add(toDTO(p));
		}
		return tar;
	}
}
