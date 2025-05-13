package com.egeo.components.product.controller.api;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.egeo.components.product.business.StoreManage;
import com.egeo.components.product.business.StoreTreeManage;
import com.egeo.components.product.converter.StoreConverter;
import com.egeo.components.product.dto.StoreDTO;
import com.egeo.components.product.dto.StoreTreeDTO;
import com.egeo.components.product.vo.StoreVO;
import com.egeo.components.utils.JsonUtils;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/store")
public class StoreAction extends BaseSpringController {
	
	@Resource(name="store")
	private StoreManage storeManage;
	
	@Resource(name="storeTree")
	private StoreTreeManage storeTreeManage;


	// 业务方法：
	@RequestMapping(value = "/findStoreById")
	@ResponseBody
	public JsonResult<StoreDTO> findStoreById(Long id ) {
		
		StoreDTO dto = new StoreDTO();
		dto.setId(id);
		StoreDTO rt = storeManage.findStoreById(dto);		
		return JsonResult.success(rt);
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findStoreAll")
	@ResponseBody
	public JsonResult<List<StoreVO>> findStoreAll(StoreVO vo,HttpServletRequest req ) {
		StoreDTO dto = StoreConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<StoreDTO> rt = storeManage.findStoreAll(dto);	
		return JsonResult.success(StoreConverter.toVO(rt));
					 
	}	

	// 业务方法：根据门店树id搜索下面所有门店信息
	@RequestMapping(value = "/findStoreAllByTreeId")
	@ResponseBody
	public JsonResult<List<StoreDTO>> findStoreAll(Long storeTreeId,HttpServletRequest req ) {
		List<StoreDTO> rt = storeManage.findStoreAllByTreeId(storeTreeId);	
		return JsonResult.success(rt);
					 
	}	
	
	// 业务方法：搜索所有门店信息
	@RequestMapping(value = "/findStoreAllInfo")
	@ResponseBody
	public JsonResult<List<StoreDTO>> findStoreAllInfo(HttpServletRequest req) {
		String str = req.getHeader("platformId");
		StoreTreeDTO dto = new  StoreTreeDTO();
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<StoreTreeDTO> findStoreTreeAll = storeTreeManage.findStoreTreeAll(dto);
		if(findStoreTreeAll.isEmpty()) {
			return  JsonResult.success(new ArrayList<StoreDTO>());
		}
		List<StoreDTO> list = new ArrayList<StoreDTO>();
		for(StoreTreeDTO l:findStoreTreeAll) {
			List<StoreDTO> rList  =storeManage.findStoreAllByTreeId(l.getId());
			if(!rList.isEmpty()) {				
				list.add(rList.get(0));
			}
			
		}
	
		return JsonResult.success(list);
					 
	}	
	// 业务方法：
	@RequestMapping(value = "/findRootStoreAll")
	@ResponseBody
	public JsonResult<List<StoreVO>> findRootStoreAll(StoreVO vo,Pagination page,HttpServletRequest req ) {
		StoreDTO dto = StoreConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<StoreDTO> rt = storeManage.findRootStoreAll(dto);	
		return JsonResult.success(StoreConverter.toVO(rt));
					 
	}
	
	// 业务方法：
	@RequestMapping(value = "/findRootStoreOfPage")
	@ResponseBody
	public JsonResult<PageResult<StoreVO>> findRootStoreOfPage(StoreVO vo,Pagination page,HttpServletRequest req ) {
		StoreDTO dto = StoreConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<StoreDTO> rt = storeManage.findRootStoreOfPage(dto, page);
        List<StoreVO> list = StoreConverter.toVO(rt.getList());
        PageResult<StoreVO> result = new PageResult<StoreVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}	
	// 业务方法：
	@RequestMapping(value = "/findStoreOfPage")
	@ResponseBody
	public JsonResult<PageResult<StoreVO>> findStoreOfPage(StoreVO vo,Pagination page,HttpServletRequest req ) {
		StoreDTO dto = StoreConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<StoreDTO> rt = storeManage.findStoreOfPage(dto, page);
        List<StoreVO> list = StoreConverter.toVO(rt.getList());
        PageResult<StoreVO> result = new PageResult<StoreVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertStoreWithTx")
	@ResponseBody
	public JsonResult<Long> insertStoreWithTx(StoreVO vo,HttpServletRequest req ) {
		StoreDTO dto = StoreConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = storeManage.insertStoreWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateStoreByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateStoreByIdWithTx(StoreVO vo,HttpServletRequest req ) {
		StoreDTO dto = StoreConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long disCount = vo.getDiscount();
		if(1L>disCount || disCount>100L ) {
			return JsonResult.fail("折扣必须在1~100之间");
		}
		Long isDetail = vo.getIsDetail();
		if(EmptyUtil.isEmpty(isDetail)) {
			return JsonResult.fail("是否零售必填");
		}
//		0 否， 1是
		if(isDetail==1) {
			if(EmptyUtil.isEmpty(vo.getProvinceId())||
					EmptyUtil.isEmpty(vo.getCityId())||
					EmptyUtil.isEmpty(vo.getCountyId())||
					EmptyUtil.isEmpty(vo.getDetailAddress())) {
				return JsonResult.fail("零售地址不能为空");
			}
		}
		int rt = storeManage.updateStoreWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteStoreWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteStoreWithTx(StoreVO vo,HttpServletRequest req ) {
		StoreDTO dto = StoreConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = storeManage.deleteStoreWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	/**
	 * 优惠券模块门店查询
	 * @param vo
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/findCouponStore")
	@ResponseBody
	public JsonResult<List<Map<String, Object>>> findCouponStore(StoreVO vo, HttpServletRequest req ) {
		StoreDTO dto = StoreConverter.toDTO(vo);
		String str = req.getHeader("platformId");
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<Map<String, Object>> result = storeManage.findCouponStore(dto);
		return JsonResult.success(result);
	}

    /**
     * 优惠券模块门店详情查询
     *
     * @param vo
     * @param ids
     * @param page
     * @param req
     * @return
     */
    @RequestMapping(value = "/findCouponStoreOfPage")
    @ResponseBody
    public JsonResult<PageResult<StoreVO>> findCouponStoreOfPage(StoreVO vo, String ids, Pagination page,
                                                                 HttpServletRequest req) {
        List<Map<String, Object>> reds = new ArrayList<>();
        String flag = null;
        if (EmptyUtil.isNotEmpty(ids)) {
            //tags = new ArrayList<Long>(JSONArray.parseArray(ids, Long.class));
            List idList = JsonUtils.jsonToPojo(ids, List.class);
            if (EmptyUtil.isNotEmpty(idList)) {
                if (StringUtils.equalsIgnoreCase("-1", idList.get(0).toString())) {
                    flag = "-1";
                } else {
                    vo.setIds(idList);
                }
            }
        }
        StoreDTO dto = StoreConverter.toDTO(vo);
        String str = req.getHeader("platformId");
        if (EmptyUtil.isNotEmpty(str)) {
            Long platformId = Long.valueOf(str);
            dto.setPlatformId(platformId);
        }
        PageResult<StoreDTO> rt = storeManage.findStoreOfPage(flag, dto, page);
        PageResult<StoreVO> result = new PageResult<StoreVO>();
        if (EmptyUtil.isNotEmpty(rt)) {
            List<StoreVO> list = StoreConverter.toVO(rt.getList());
            result.setList(list);
            result.setPageNo(rt.getPageNo());
            result.setPageSize(rt.getPageSize());
            result.setTotalSize(rt.getTotalSize());
        }
        return JsonResult.success(result);
    }


    @RequestMapping(value = "/getStoreInfo")
	@ResponseBody
    public JsonResult<Map<String,String>> getStoreInfo(Long storeId,HttpServletRequest req){
		logger.info("获取门店信息,storeId:"+storeId);
		String str = req.getHeader("platformId");
		if (EmptyUtil.isEmpty(str)) {
			return JsonResult.fail("platformId不能为空");
		}
		Long platformId = Long.valueOf(str);
    	if(EmptyUtil.isEmpty(storeId)){
			return JsonResult.fail("storeId不能为空");
		}
		return storeManage.getStoreInfo(storeId,platformId);
	}
    
    /**
     * 查询所有 有门店菜单的门店
     * @param req
     * @return
     */
 	@RequestMapping(value = "/findStoreByPlatformId")
 	@ResponseBody
 	public JsonResult<List<StoreDTO>> findStoreByPlatformIdAndStoreMenu(HttpServletRequest req) {
 		String str = req.getHeader("platformId");
 		StoreDTO dto = new StoreDTO();
 		dto.setPlatformId(Long.valueOf(str));
		List<StoreDTO> list  =storeManage.findStoreByPlatformIdAndStoreMenu(dto);
		logger.info("查询所有 有门店菜单的门店 返回结果：" + JSON.toJSONString(list));
 		return JsonResult.success(list);
 					 
 	}
}
	