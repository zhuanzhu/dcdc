package com.egeo.components.user.business;

import java.util.List;
import java.util.Map;

import com.egeo.components.user.dto.InfoDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.JsonResult;	

public interface InfoManage {

	public InfoDTO findInfoById(InfoDTO dto);	

	public PageResult<Map<String, Object>> findInfoOfPage(InfoDTO dto,Pagination page);

	public List<InfoDTO> findInfoAll(InfoDTO dto);

	Long insertInfoWithTx(
			InfoDTO dto,
			List<Long> sendWayIds,
			Integer isUserAll,
			List<Long> userIds, Long userId, String serialNum);

	int updateInfoWithTx(InfoDTO dto);

	int deleteInfoWithTx(InfoDTO dto);
	/**
	 * 根据当前用户id分页显示当前不同消息列表
	 * @param dto
	 * @param page
	 * @return
	 */
	public Map<String, Object> findUserInfoOfPage(InfoDTO dto, Pagination page);
	
	public JsonResult<Map<String,Object>> parseSendUser(List<Map<String, Object>> valueList, Long userId);
	
	public void clearImportInfoCache(Long userId);
}
	