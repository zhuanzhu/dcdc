package com.egeo.components.product.business;

import java.util.List;
import java.util.Map;

import com.egeo.components.product.dto.StandardUnitCombinationSuDTO;
import com.egeo.components.product.vo.CardCombinationCheckReqVO;
import com.egeo.components.product.vo.CardCombinationCheckRespVO;
import com.egeo.components.product.vo.UserCardCombinationReqVO;
import com.egeo.components.product.vo.UserCardCombinationRespVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import org.springframework.web.bind.annotation.RequestBody;

public interface StandardUnitCombinationSuManage {

	public StandardUnitCombinationSuDTO findStandardUnitCombinationSuById(StandardUnitCombinationSuDTO dto);

	public PageResult<StandardUnitCombinationSuDTO> findStandardUnitCombinationSuOfPage(StandardUnitCombinationSuDTO dto,Pagination page);

	public List<StandardUnitCombinationSuDTO> findStandardUnitCombinationSuAll(StandardUnitCombinationSuDTO dto);

	Long insertStandardUnitCombinationSuWithTx(StandardUnitCombinationSuDTO dto);

	int updateStandardUnitCombinationSuWithTx(StandardUnitCombinationSuDTO dto);

	int deleteStandardUnitCombinationSuWithTx(StandardUnitCombinationSuDTO dto);
	/**
	 * 批量保存su组合和su商品关系
	 * @param standardUnitCombinationId
	 * @param standardUnits
	 * @return
	 */
	public boolean saveStandardUnitCombinationSuAllWithTx(Long standardUnitCombinationId, List<Long> standardUnits,Integer source,List<Map<String,String>> productAndSkuMap);
	/**
	 * 根据su组合id查询su商品列表
	 * @param standardUnitCombinationId
	 * @return
	 */
	public PageResult<Map<String, Object>> findByStandardUnitCombinationIdOfPage(Long standardUnitCombinationId,String keyWord,Pagination page,boolean isUserClient);
	/**
	 * 根据su组合id修改排序
	 * @param standardUnitCombinationId
	 * @param sortValue
	 * @return
	 */
	public boolean sortValueByStandardUnitCombinationIdWithTx(Long standardUnitCombinationSuId, Integer sortValue);

	public CardCombinationCheckRespVO checkCardCombination(CardCombinationCheckReqVO vo);

	public UserCardCombinationRespVO getCardCombination(UserCardCombinationReqVO vo);
}
