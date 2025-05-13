package com.egeo.components.product.controller.api;


import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.StoreManage;
import com.egeo.components.product.business.StoreTreeManage;
import com.egeo.components.product.business.StoreTreeNodeManage;
import com.egeo.components.product.converter.StoreConverter;
import com.egeo.components.product.converter.StoreTreeNodeConverter;
import com.egeo.components.product.dto.StoreDTO;
import com.egeo.components.product.dto.StoreTreeDTO;
import com.egeo.components.product.dto.StoreTreeNodeDTO;
import com.egeo.components.product.vo.StoreTreeNodeVO;
import com.egeo.components.product.vo.StoreVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/storeTreeNode")
public class StoreTreeNodeAction extends BaseSpringController {
	
	@Resource(name="storeTreeNode")
	private StoreTreeNodeManage storeTreeNodeManage;

	@Resource(name="storeTree")
	private StoreTreeManage storeTreeManage;
	
	@Resource(name="store")
	private StoreManage storeManage;

	// 业务方法：
	@RequestMapping(value = "/findStoreTreeNodeById")
	@ResponseBody
	public JsonResult<StoreTreeNodeVO> findStoreTreeNodeById(Long id ) {
		
		StoreTreeNodeDTO dto = new StoreTreeNodeDTO();
		dto.setId(id);
		StoreTreeNodeDTO rt = storeTreeNodeManage.findStoreTreeNodeById(dto);		
		return JsonResult.success(StoreTreeNodeConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findStoreTreeNodeAll")
	@ResponseBody
	public JsonResult<List<StoreTreeNodeVO>> findStoreTreeNodeAll(StoreTreeNodeVO vo,HttpServletRequest req ) {
		StoreTreeNodeDTO dto = StoreTreeNodeConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<StoreTreeNodeDTO> rt = storeTreeNodeManage.findStoreTreeNodeAll(dto);	
		return JsonResult.success(StoreTreeNodeConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findStoreTreeNodeOfPage")
	@ResponseBody
	public JsonResult<PageResult<StoreTreeNodeVO>> findStoreTreeNodeOfPage(StoreTreeNodeVO vo,Pagination page,HttpServletRequest req ) {
		StoreTreeNodeDTO dto = StoreTreeNodeConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<StoreTreeNodeDTO> rt = storeTreeNodeManage.findStoreTreeNodeOfPage(dto, page);
        List<StoreTreeNodeVO> list = StoreTreeNodeConverter.toVO(rt.getList());
        PageResult<StoreTreeNodeVO> result = new PageResult<StoreTreeNodeVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：新增加门店树节点
	@RequestMapping(value = "/insertStoreTreeNodeWithTx")
	@ResponseBody
	public JsonResult<Long> insertStoreTreeNodeWithTx(StoreTreeNodeVO storeTreeNodevo,StoreVO storeVO,HttpServletRequest req ) {
		StoreTreeNodeDTO storeTreeNodedto = StoreTreeNodeConverter.toDTO(storeTreeNodevo);
		StoreDTO storedto = StoreConverter.toDTO(storeVO);
		String str = req.getHeader("platformId");	
		Long platformId = 0L;
		if(EmptyUtil.isNotEmpty(str)){
			platformId = Long.valueOf(str);
			storeTreeNodedto.setPlatformId(platformId);
			storedto.setPlatformId(platformId);
		}
		if (EmptyUtil.isEmpty(storedto)) {
			return JsonResult.fail("请输入要新增的节点信息");
		}
		if (EmptyUtil.isEmpty(storedto.getName())) {
			return JsonResult.fail("门店名称不能为空");
		}
		String pids = null;
		StoreDTO storeinfo = null;
		Long nodeId = storeTreeNodevo.getNodeId();
		//如果pids为空，则为根节点，创建新的门店树,返回为新创建树的id
		if (EmptyUtil.isEmpty(nodeId)) {
			pids = "";
			StoreTreeDTO dto = new StoreTreeDTO();
			dto.setName(storedto.getName());
			dto.setCreateTime(new Date());
			dto.setPlatformId(platformId);
			Long rt = storeTreeManage.insertStoreTreeWithTx(dto);	
			storeTreeNodedto.setStoreTreeId(rt);
			storeTreeNodevo.setNodeId(0L);
			storeTreeNodedto.setListSort(rt.intValue());
			storedto.setActivityCode("Shop"+rt);
			if(EmptyUtil.isEmpty(storeVO.getDiscount())) {
				return JsonResult.fail("总店的折扣率必填");
			}
		}else {
			//上级公司的详细信息
			storeinfo = storeManage.findStoreByNodeId(nodeId);
			storedto.setCompanyId(storeinfo.getCompanyId());
			if(EmptyUtil.isEmpty(storeVO.getDiscount())) {
				storedto.setDiscount(storeinfo.getDiscount());
			}
			storedto.setActivityCode(storeinfo.getActivityCode());
		}	
		Long disCount = storedto.getDiscount();
		if(1L>disCount || disCount>100L ) {
			return JsonResult.fail("折扣必须在1~100之间");
		}
		Long isDetail = storeVO.getIsDetail();
		if(EmptyUtil.isEmpty(isDetail)) {
			return JsonResult.fail("是否零售必填");
		}
//		0 否， 1是
		if(isDetail==1) {
			if(EmptyUtil.isEmpty(storeVO.getProvinceId())||
					EmptyUtil.isEmpty(storeVO.getCityId())||
					EmptyUtil.isEmpty(storeVO.getCountyId())||
					EmptyUtil.isEmpty(storeVO.getDetailAddress())) {
				return JsonResult.fail("零售地址不能为空");
			}
		}
		
		if (EmptyUtil.isNotEmpty(storeTreeNodedto.getPids())) {
			pids = storeTreeNodedto.getPids().substring(1, storeTreeNodedto.getPids().length() - 1);
		}
		storeTreeNodedto.setPids(pids);

		storeTreeNodedto.setParentId(storeTreeNodevo.getNodeId());;
		Long rt = storeTreeNodeManage.insertStoreTreeNodeWithTx(storeTreeNodedto,storedto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateStoreTreeNodeByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateStoreTreeNodeByIdWithTx(StoreTreeNodeVO vo,HttpServletRequest req ) {
		StoreTreeNodeDTO dto = StoreTreeNodeConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = storeTreeNodeManage.updateStoreTreeNodeWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteStoreTreeNodeWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteStoreTreeNodeWithTx(StoreTreeNodeVO vo,HttpServletRequest req ) {
		StoreTreeNodeDTO dto = StoreTreeNodeConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = storeTreeNodeManage.deleteStoreTreeNodeWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	