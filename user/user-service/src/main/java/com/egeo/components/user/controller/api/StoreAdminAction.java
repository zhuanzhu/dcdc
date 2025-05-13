package com.egeo.components.user.controller.api;


import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.egeo.components.user.business.StoreAdminManage;
import com.egeo.components.user.converter.StoreAdminConverter;
import com.egeo.components.user.dto.StoreAdminDTO;
import com.egeo.components.user.vo.StoreAdminVO;
import com.egeo.entity.CacheUser;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.StringUtils;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/user/storeAdmin")
public class StoreAdminAction extends BaseSpringController {
	
	@Resource(name="storeAdmin")
	private StoreAdminManage storeAdminManage;


	// 业务方法：
	@RequestMapping(value = "/findStoreAdminById")
	@ResponseBody
	public JsonResult<StoreAdminVO> findStoreAdminById(Long id ) {
		
		StoreAdminDTO dto = new StoreAdminDTO();
		dto.setId(id);
		StoreAdminDTO rt = storeAdminManage.findStoreAdminById(dto);		
		return JsonResult.success(StoreAdminConverter.toVO(rt));
					 
	}



	/**
	 * 根据用户id查询所管理的门店列表
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/findStoreAdminAll")
	@ResponseBody
	public JsonResult<Map<String, Object>> findStoreAdminAll(Long channelId,HttpServletRequest req ) {
		CacheUser user = this.getCacheUser();
		StoreAdminDTO storeAdminDTO = new StoreAdminDTO();
		String str = req.getHeader("platformId");		
		if(StringUtils.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			storeAdminDTO.setPlatformId(platformId);
		}
		storeAdminDTO.setUserId(user.getId());
		storeAdminDTO.setChannelId(channelId);
		Map<String, Object> rt = storeAdminManage.findStoreAdminAll(storeAdminDTO);	
		return JsonResult.success(rt);
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findStoreAdminOfPage")
	@ResponseBody
	public JsonResult<PageResult<StoreAdminVO>> findStoreAdminOfPage(StoreAdminVO vo,Pagination page,HttpServletRequest req ) {
		StoreAdminDTO dto = StoreAdminConverter.toDTO(vo);
		PageResult<StoreAdminDTO> rt = storeAdminManage.findStoreAdminOfPage(dto, page);
        List<StoreAdminVO> list = StoreAdminConverter.toVO(rt.getList());
        PageResult<StoreAdminVO> result = new PageResult<StoreAdminVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertStoreAdminWithTx")
	@ResponseBody
	public JsonResult<String> insertStoreAdminWithTx(String userList,Long storeId,HttpServletRequest req ) {
//		StoreAdminDTO dto = StoreAdminConverter.toDTO(vo);
		List<Long> adminList = null;
        if (StringUtils.isNotEmpty(userList)) {
        	adminList = JSONArray.parseArray(userList, Long.class);
        }else {
        	return JsonResult.fail("用户列表不能为空");
        }
        
        for(Long i :adminList) {
        	StoreAdminDTO tar = new StoreAdminDTO();
        	tar.setCreateTime(new Date());
        	tar.setUserId(i);
        	tar.setStoreId(storeId);
        	Long rt = storeAdminManage.insertStoreAdminWithTx(tar);	
        }	
        
		
		return JsonResult.success("成功关联管理员");					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateStoreAdminByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateStoreAdminByIdWithTx(StoreAdminVO vo,HttpServletRequest req ) {
		StoreAdminDTO dto = StoreAdminConverter.toDTO(vo);
		int rt = storeAdminManage.updateStoreAdminWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteStoreAdminWithTx")
	@ResponseBody
	public JsonResult<String> deleteStoreAdminWithTx(String userList,Long storeId,HttpServletRequest req ) {

		List<Long> adminList = null;
        if (StringUtils.isNotEmpty(userList)) {
        	adminList = JSONArray.parseArray(userList, Long.class);
        }else {
        	return JsonResult.fail("用户列表不能为空");
        }
        
        for(Long i :adminList) {
        	StoreAdminDTO tar = new StoreAdminDTO();

        	tar.setUserId(i);
        	tar.setStoreId(storeId);
        	int rt = storeAdminManage.deleteStoreAdminWithTx(tar);	
        }	

		return JsonResult.success("成功删除管理员");					 
	}
	
	
	// 业务方法：
	@RequestMapping(value = "/manageStoreAdminWithTx")
	@ResponseBody
	public JsonResult<String> manageStoreAdminWithTx(String addUserList,String removeUserList,Long storeId,HttpServletRequest req ) {

		List<Long> aAdminList = null;
		List<Long> rAdminList = null;
        if (StringUtils.isNotEmpty(addUserList)) {
        	aAdminList = JSONArray.parseArray(addUserList, Long.class);
        	if (StringUtils.isNotEmpty(aAdminList)) {
                for(Long i :aAdminList) {
                	StoreAdminDTO tar = new StoreAdminDTO();
                	tar.setCreateTime(new Date());
                	tar.setUserId(i);
                	tar.setStoreId(storeId);
                	Long rt = storeAdminManage.insertStoreAdminWithTx(tar);	
                }	
        	}
        }
        
        if (StringUtils.isNotEmpty(removeUserList)) {
        	rAdminList = JSONArray.parseArray(removeUserList, Long.class);
        	if (StringUtils.isNotEmpty(rAdminList)) {
                for(Long i :rAdminList) {
                	StoreAdminDTO tar = new StoreAdminDTO();
                	tar.setUserId(i);
                	tar.setStoreId(storeId);
                	int rt = storeAdminManage.deleteStoreAdminWithTx(tar);	
                }	
        	}
        }
		return JsonResult.success("成功增加或删除管理员");					 
	}	
		
}
	