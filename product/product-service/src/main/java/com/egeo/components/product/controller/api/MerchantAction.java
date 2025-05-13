package com.egeo.components.product.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.MerchantManage;
import com.egeo.components.product.converter.MerchantConverter;
import com.egeo.components.product.dto.MerchantDTO;
import com.egeo.components.product.vo.MerchantListVO;
import com.egeo.components.product.vo.MerchantVO;
import com.egeo.entity.CacheUser;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/merchant")
public class MerchantAction extends BaseSpringController {
	
	@Resource(name="merchant")
	private MerchantManage merchantManage;


	// 业务方法：
	@RequestMapping(value = "/findMerchantById")
	@ResponseBody
	public JsonResult<MerchantVO> findMerchantById(Long id ) {
		
		MerchantDTO dto = new MerchantDTO();
		dto.setId(id);
		MerchantDTO rt = merchantManage.findMerchantById(dto);		
		return JsonResult.success(MerchantConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findMerchantAll")
	@ResponseBody
	public JsonResult<List<MerchantVO>> findMerchantAll(MerchantVO vo,HttpServletRequest req ) {
		MerchantDTO dto = MerchantConverter.toDTO(vo);

		List<MerchantDTO> rt = merchantManage.findMerchantAll(dto);	
		return JsonResult.success(MerchantConverter.toVO(rt));
					 
	}

	/**
	 * 查询运营方列表(不区分platformId)
	 * @param vo
	 * @param page
	 * @param req
	 * @return
	 */
	// 业务方法：
	@RequestMapping(value = "/findMerchantOfPage")
	@ResponseBody
	public JsonResult<PageResult<MerchantVO>> findMerchantOfPage(MerchantVO vo,Pagination page,HttpServletRequest req ) {
		MerchantDTO dto = MerchantConverter.toDTO(vo);
		PageResult<MerchantDTO> rt = merchantManage.findMerchantOfPage(dto, page);
        List<MerchantVO> list = MerchantConverter.toVO(rt.getList());
        PageResult<MerchantVO> result = new PageResult<MerchantVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	/**
	 * 新增/更新运营方
	 * @param vo
	 * @param req
	 * @return
	 */
	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertMerchantWithTx")
	@ResponseBody
	public JsonResult<String> insertMerchantWithTx(MerchantVO vo,HttpServletRequest req ) {
		logger.info("新增/更新运营方接口入口");
		logger.info("[新增/更新运营方] 参数vo:"+vo);
		CacheUser userCache= getCacheUser();
		Long userId = userCache.getId();// 用户名称
		String userName = userCache.getName();// 用户名称
		MerchantDTO dto = MerchantConverter.toDTO(vo);
		if(EmptyUtil.isEmpty(vo.getId())){
			//新增
			dto.setOperateUserId(userId);
			dto.setOperateUserName(userName);
			dto.setIsStop(0);
			dto.setType(Integer.valueOf(0));
			merchantManage.insertMerchantWithTx(dto);
		}else{
			dto.setOperateUserId(userId);
			dto.setOperateUserName(userName);
			merchantManage.updateMerchantWithTx(dto);
		}
		return JsonResult.success("成功");
	}

	/**
	 * 设置失效
	 * @param vo
	 * @param req
	 * @return
	 */
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateMerchantStopByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateMerchantByIdWithTx(MerchantVO vo,HttpServletRequest req ) {
		logger.info("设置运营方失效接口入口");
		logger.info("[设置运营方失效,参数]isStop:" + vo.getIsStop());
		CacheUser userCache= getCacheUser();
		Long userId = userCache.getId();// 用户名称
		String userName = userCache.getName();// 用户名称
		MerchantDTO dto = MerchantConverter.toDTO(vo);
		dto.setOperateUserId(userId);
		dto.setOperateUserName(userName);
		int rt = merchantManage.updateMerchantWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteMerchantWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteMerchantWithTx(MerchantVO vo,HttpServletRequest req ) {
		MerchantDTO dto = MerchantConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = merchantManage.deleteMerchantWithTx(dto);	
		return JsonResult.success(rt);					 
	}


	/**
	 * 查询所有已启用的运营方
	 * @return
	 */
	@RequestMapping(value = "/findStartedMerchantList")
	@ResponseBody
	public JsonResult<List<MerchantListVO>> findStartedMerchantList(){
		List<MerchantListVO> rt=merchantManage.findStartedMerchantList();
		return JsonResult.success(rt);
	}
		
}
	