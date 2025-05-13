package com.egeo.components.promotion.business;

import java.util.List;
import java.util.Map;

import com.egeo.components.promotion.dto.ECardDTO;
import com.egeo.components.promotion.dto.ECardStatusDTO;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.JsonResult;

public interface ECardManage {

	public ECardDTO findECardById(ECardDTO dto);	

	public PageResult<Map<String, Object>> findECardOfPage(ECardDTO dto,Pagination page);

	public List<ECardDTO> findECardAll(ECardDTO dto);

	Long insertECardWithTx(ECardDTO dto);

	int updateECardWithTx(ECardDTO dto);

	int deleteECardWithTx(ECardDTO dto);
	/**
	 * 电子卡券导入
	 * @param eCardUploadfile
	 * @param tmplType
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> importECardWithTx(
			List<Map<String, Object>> list,
			String source,
			String remark,
			Long platformId,
			Long userId,
			String userName,
			String ip,
			String mac, int tmplType);
	/**
	 * 电子卡券导入并且自动发放
	 * @param list
	 * @param source
	 * @param remark
	 * @param platformId
	 * @return
	 */
	public Map<String, Object> importECardAutoSend(List<Map<String, Object>> list, String source, String remark,
			Long platformId,Long userId,String userName,String ip,String mac,int tmplType);
	/**
	 * 查询被分配给当前用户的体检券
	 * @param dto
	 * @return
	 */
	public JsonResult<Map<String, Object>> myBodyCheck(ECardDTO dto,Pagination page);

	/**
	 * 卡券unit状态[有效/失效]设置业务
	 * @param type
	 * @param ids
	 * @param skuIds
	 * @param eCardDto
	 * @return
	 * @throws BusinessException
	 */
	Map<String,Object> updateECardStatus(String type, List<String> ids, List<Long> skuIds,
										 List<ECardStatusDTO> eCardDto)throws BusinessException;

}
