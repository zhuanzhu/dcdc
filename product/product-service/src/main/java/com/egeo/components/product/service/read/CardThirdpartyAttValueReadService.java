package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.CardThirdpartyAttValueDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface CardThirdpartyAttValueReadService {

	public CardThirdpartyAttValueDTO findCardThirdpartyAttValueById(CardThirdpartyAttValueDTO dto);

	public PageResult<CardThirdpartyAttValueDTO> findCardThirdpartyAttValueOfPage(CardThirdpartyAttValueDTO dto,Pagination page);

	public List<CardThirdpartyAttValueDTO> findCardThirdpartyAttValueAll(CardThirdpartyAttValueDTO dto);
	/**
	 * 根据分组的spu查询spu第三方参数其对应的卡类型
	 * @param standardProductUnitId
	 * @return
	 */
	public Integer findCardTypeByStandardProductUnitId(Long standardProductUnitId);
}
	