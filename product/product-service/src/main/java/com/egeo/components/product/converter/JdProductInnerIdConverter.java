package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.JdProductInnerIdDTO;
import com.egeo.components.product.po.JdProductInnerIdPO;
import com.egeo.components.product.vo.JdProductInnerIdVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xia
 * @date 2019-05-27 15:07:39
 */
public class JdProductInnerIdConverter {

	
	public static JdProductInnerIdDTO toDTO(JdProductInnerIdVO src) {
		if (src == null)
		return null;	
		JdProductInnerIdDTO tar = new JdProductInnerIdDTO();
		tar.setJdSkuId(src.getJdSkuId());
		tar.setInnerProductId(src.getInnerProductId());	
		tar.setInnerSkuId(src.getInnerSkuId());	
		tar.setInnerSuId(src.getInnerSuId());	
		tar.setInnerProductUnitId(src.getInnerProductUnitId());	
		tar.setInnerPuId(src.getInnerPuId());	
		tar.setInnerPictureId(src.getInnerPictureId());	
		tar.setInnerAttValueId(src.getInnerAttValueId());	
		return tar;
	}

	public static JdProductInnerIdVO toVO(JdProductInnerIdDTO src) {
		if (src == null)
		return null;	
		JdProductInnerIdVO tar = new JdProductInnerIdVO();
		tar.setJdSkuId(src.getJdSkuId());
		tar.setInnerProductId(src.getInnerProductId());	
		tar.setInnerSkuId(src.getInnerSkuId());	
		tar.setInnerSuId(src.getInnerSuId());	
		tar.setInnerProductUnitId(src.getInnerProductUnitId());	
		tar.setInnerPuId(src.getInnerPuId());	
		tar.setInnerPictureId(src.getInnerPictureId());	
		tar.setInnerAttValueId(src.getInnerAttValueId());	
		return tar;
	}

	public static List<JdProductInnerIdDTO> toDTOs(List<JdProductInnerIdVO> srcs) {
		if (srcs == null)
			return null;
		List<JdProductInnerIdDTO> list = new ArrayList<JdProductInnerIdDTO>();
		for (JdProductInnerIdVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<JdProductInnerIdVO> toVO(List<JdProductInnerIdDTO> srcs) {
		if (srcs == null)
			return null;
		List<JdProductInnerIdVO> list = new ArrayList<JdProductInnerIdVO>();
		for (JdProductInnerIdDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static JdProductInnerIdDTO toDTO(JdProductInnerIdPO src) {
		if (src == null)
		return null;	
		JdProductInnerIdDTO tar = new JdProductInnerIdDTO();
		tar.setJdSkuId(src.getJdSkuId());
		tar.setInnerProductId(src.getInnerProductId());
		tar.setInnerSkuId(src.getInnerSkuId());
		tar.setInnerSuId(src.getInnerSuId());
		tar.setInnerProductUnitId(src.getInnerProductUnitId());
		tar.setInnerPuId(src.getInnerPuId());
		tar.setInnerPictureId(src.getInnerPictureId());
		tar.setInnerAttValueId(src.getInnerAttValueId());
		return tar;
	}

	public static JdProductInnerIdPO toPO(JdProductInnerIdDTO src) {
		if (src == null)
		return null;	
		JdProductInnerIdPO tar = new JdProductInnerIdPO();
		tar.setJdSkuId(src.getJdSkuId());
		tar.setInnerProductId(src.getInnerProductId());
		tar.setInnerSkuId(src.getInnerSkuId());
		tar.setInnerSuId(src.getInnerSuId());
		tar.setInnerProductUnitId(src.getInnerProductUnitId());
		tar.setInnerPuId(src.getInnerPuId());
		tar.setInnerPictureId(src.getInnerPictureId());
		tar.setInnerAttValueId(src.getInnerAttValueId());
		return tar;
	}

	public static List<JdProductInnerIdDTO> toDTO(List<JdProductInnerIdPO> srcs) {
		if (srcs == null)
			return null;
		List<JdProductInnerIdDTO> list = new ArrayList<JdProductInnerIdDTO>();
		for (JdProductInnerIdPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<JdProductInnerIdPO> toPO(List<JdProductInnerIdDTO> srcs) {
		if (srcs == null)
			return null;
		List<JdProductInnerIdPO> list = new ArrayList<JdProductInnerIdPO>();
		for (JdProductInnerIdDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	