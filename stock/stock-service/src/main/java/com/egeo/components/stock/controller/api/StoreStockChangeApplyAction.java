package com.egeo.components.stock.controller.api;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.egeo.components.stock.business.StoreStockChangeApplyManage;
import com.egeo.components.stock.converter.StoreStockChangeApplyConverter;
import com.egeo.components.stock.dto.StoreStockChangeApplyDTO;
import com.egeo.components.stock.vo.StoreStockChangeApplyVO;
import com.egeo.entity.CacheUser;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/stock/storeStockChangeApply")
public class StoreStockChangeApplyAction extends BaseSpringController {
	
	@Resource(name="storeStockChangeApply")
	private StoreStockChangeApplyManage storeStockChangeApplyManage;


	// 业务方法：
	@RequestMapping(value = "/findStoreStockChangeApplyById")
	@ResponseBody
	public JsonResult<StoreStockChangeApplyVO> findStoreStockChangeApplyById(Long id ) {
		
		StoreStockChangeApplyDTO dto = new StoreStockChangeApplyDTO();
		dto.setId(id);
		StoreStockChangeApplyDTO rt = storeStockChangeApplyManage.findStoreStockChangeApplyById(dto);		
		return JsonResult.success(StoreStockChangeApplyConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findStoreStockChangeApplyAll")
	@ResponseBody
	public JsonResult<List<StoreStockChangeApplyVO>> findStoreStockChangeApplyAll(StoreStockChangeApplyVO vo,HttpServletRequest req ) {
		StoreStockChangeApplyDTO dto = StoreStockChangeApplyConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<StoreStockChangeApplyDTO> rt = storeStockChangeApplyManage.findStoreStockChangeApplyAll(dto);	
		return JsonResult.success(StoreStockChangeApplyConverter.toVO(rt));
					 
	}	

	/**
	 * 分页查询门店库存变更信息
	 * @param vo
	 * @param page
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/findStoreStockChangeApplyOfPage")
	@ResponseBody
	public JsonResult<PageResult<StoreStockChangeApplyVO>> findStoreStockChangeApplyOfPage(StoreStockChangeApplyVO vo,Pagination page,HttpServletRequest req ) {
		StoreStockChangeApplyDTO dto = StoreStockChangeApplyConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<StoreStockChangeApplyDTO> rt = storeStockChangeApplyManage.findStoreStockChangeApplyOfPage(dto, page);
        List<StoreStockChangeApplyVO> list = StoreStockChangeApplyConverter.toVO(rt.getList());
        PageResult<StoreStockChangeApplyVO> result = new PageResult<StoreStockChangeApplyVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
	}
	
	/**
	 * 分页查询门店库存变更信息APP端
	 * @param vo
	 * @param page
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/findStoreStockChangeApplyOfPageAPP")
	@ResponseBody
	public JsonResult<Map<String, Object>> findStoreStockChangeApplyOfPageAPP(StoreStockChangeApplyVO vo,Pagination page,HttpServletRequest req ) {
		StoreStockChangeApplyDTO dto = StoreStockChangeApplyConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		if(EmptyUtil.isEmpty(dto.getType())){
			throw new BusinessException("请选择查询类型");
		}
		Map<String, Object> rt = storeStockChangeApplyManage.findStoreStockChangeApplyOfPageAPP(dto, page);
		return JsonResult.success(rt);
	}


	/**
	 * 添加门店申请变动
	 * @param vo
	 * @param pictureList
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/insertStoreStockChangeApplyWithTx")
	@ResponseBody
	public JsonResult<Long> insertStoreStockChangeApplyWithTx(StoreStockChangeApplyVO vo,String pictureList,HttpServletRequest req ) {
		StoreStockChangeApplyDTO dto = StoreStockChangeApplyConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		String clientIds = req.getHeader("clientId");	
		Long clientId = null;
		if(EmptyUtil.isNotEmpty(clientIds)){
			clientId = Long.valueOf(clientIds);
		}
		CacheUser user = this.getCacheUser();
		dto.setAfterUserId(user.getId());
		dto.setAfterUserName(user.getName());
		// 赋初始值
		dto.setIsConsent(0);
		
		if(EmptyUtil.isEmpty(vo.getStockChange())){
			throw new BusinessException("请填写进货数量");
		}
		if(EmptyUtil.isEmpty(vo.getApplyCauseId())){
			throw new BusinessException("请选择调整原因");
		}
		List<String> pictures = new ArrayList<String>();
		if(EmptyUtil.isNotEmpty(pictureList)){
			if(clientId.doubleValue() == 1){
				String[] picture = pictureList.split(",");
				for (String string : picture) {
					pictures.add(string);
				}
			}else{
				pictures = JSONArray.parseArray(pictureList, String.class);
			}
		}
		Long rt = storeStockChangeApplyManage.insertStoreStockChangeApplyWithTx(dto,pictures);	
		return JsonResult.success(rt);					 
	}
	
	/**
	 * 根据门店申请变动id同意或拒绝
	 * @param vo
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/updateStoreStockChangeApplyByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateStoreStockChangeApplyByIdWithTx(StoreStockChangeApplyVO vo,HttpServletRequest req ) {
		StoreStockChangeApplyDTO dto = StoreStockChangeApplyConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		CacheUser user = this.getCacheUser();
		dto.setOperationUserId(user.getId());
		dto.setOperationUserName(user.getName());
		int rt = storeStockChangeApplyManage.updateStoreStockChangeApplyWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteStoreStockChangeApplyWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteStoreStockChangeApplyWithTx(StoreStockChangeApplyVO vo,HttpServletRequest req ) {
		StoreStockChangeApplyDTO dto = StoreStockChangeApplyConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = storeStockChangeApplyManage.deleteStoreStockChangeApplyWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	