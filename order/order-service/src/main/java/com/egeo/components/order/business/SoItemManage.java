package com.egeo.components.order.business;

import java.util.List;
import java.util.Map;

import com.egeo.components.order.vo.SoItem;
import com.egeo.components.order.vo.SoItemVO;
import com.egeo.web.JsonResult;

public interface SoItemManage {

	Long updateSoItem(SoItemVO soItemVO);

	String updateSoItemAll(Long packId, List<SoItem> lists, Long platformId);

	/**根据母单id查询所有的pu列表
	 * @param soItemDTO
	 * @return
	 */
	JsonResult<Map<String,Object>> findAllSoItemBySoId(Long soId);
	JsonResult<Map<String,Object>> findAllSoChildItemBySoId(Long soId);

	/**根据母单id查询所有的unit商品列表
	 * @param soItemDTO
	 * @return
	 */
	JsonResult<Map<String,Object>> findSoItemBySoIdAndUnit(Long soId);
	

}
	