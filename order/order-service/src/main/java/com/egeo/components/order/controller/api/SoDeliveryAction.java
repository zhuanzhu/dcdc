package com.egeo.components.order.controller.api;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.egeo.components.order.business.DeliveryCompanyManage;
import com.egeo.components.order.business.SoDeliveryManage;
import com.egeo.components.order.converter.SoDeliveryConverter;
import com.egeo.components.order.dto.SoDeliveryDTO;
import com.egeo.components.order.facade.SoPackageFacade;
import com.egeo.components.order.vo.SoDeliveryVO;
import com.egeo.components.order.vo.Steps;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/order/soDelivery")
public class SoDeliveryAction extends BaseSpringController {
	
	@Resource(name="soDelivery")
	private SoDeliveryManage soDeliveryManage;
	
	@Resource(name="deliveryCompany")
	private DeliveryCompanyManage deliveryCompanyManage;

	@Resource(name = "soPackageFacade")
	private SoPackageFacade soPackageFacade;
	// 业务方法：
	@RequestMapping(value = "/findSoDeliveryById")
	@ResponseBody
	public JsonResult<SoDeliveryVO> findSoDeliveryById(Long id ) {
		
		SoDeliveryDTO dto = new SoDeliveryDTO();
		dto.setId(id);
		SoDeliveryDTO rt = soDeliveryManage.findSoDeliveryById(dto);		
		return JsonResult.success(SoDeliveryConverter.toVO(rt));
					 
	}

	// 业务方法：
	@RequestMapping(value = "/findSoDeliveryAll")
	@ResponseBody
	public JsonResult<List<SoDeliveryVO>> findSoDeliveryAll(SoDeliveryVO vo,HttpServletRequest req ) {
		SoDeliveryDTO dto = SoDeliveryConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<SoDeliveryDTO> rt = soDeliveryManage.findSoDeliveryAll(dto);	
		return JsonResult.success(SoDeliveryConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findSoDeliveryOfPage")
	@ResponseBody
	public JsonResult<PageResult<SoDeliveryVO>> findSoDeliveryOfPage(SoDeliveryVO vo,Pagination page,HttpServletRequest req ) {
		SoDeliveryDTO dto = SoDeliveryConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<SoDeliveryDTO> rt = soDeliveryManage.findSoDeliveryOfPage(dto, page);
        List<SoDeliveryVO> list = SoDeliveryConverter.toVO(rt.getList());
        PageResult<SoDeliveryVO> result = new PageResult<SoDeliveryVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：
	@RequestMapping(value = "/insertSoDeliveryWithTx")
	@ResponseBody
	public JsonResult<Integer> insertSoDeliveryWithTx(SoDeliveryVO vo,HttpServletRequest req ) {
		SoDeliveryDTO dto = SoDeliveryConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = soDeliveryManage.insertSoDeliveryWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateSoDeliveryByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateSoDeliveryByIdWithTx(SoDeliveryVO vo,HttpServletRequest req ) {
		SoDeliveryDTO dto = SoDeliveryConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = soDeliveryManage.updateSoDeliveryWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteSoDeliveryWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteSoDeliveryWithTx(SoDeliveryVO vo,HttpServletRequest req ) {
		SoDeliveryDTO dto = SoDeliveryConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = soDeliveryManage.deleteSoDeliveryWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	/**
	 * 开放物流信息查询(app/h5)
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="openDeliveryTrace")
	public JsonResult<List<Steps>> openDeliveryTrace(String waybillNum,String shipCompanyCode){
		JsonResult<List<Steps>> result = new JsonResult<List<Steps>>();
		logger.info("根据物流单号和物流公司编号true");
		try {
			List<Steps> stepsList = soDeliveryManage.openDeliveryTrace(waybillNum,shipCompanyCode);
			logger.info("物流结果:"+ JSON.toJSONString(stepsList));
			result.setData(stepsList);
			return result;
		} catch (Exception e) {
			logger.error("根据物流单号和物流公司编号异常！", e);
            result.setCode(1);
            result.setError("根据物流单号和物流公司编号异常："+ e.getMessage());
            return result;
		}
	}
	
	/**
	 * 开放物流信息查询(web端)
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="queryWebOpenDeliveryTrace")
	public JsonResult<Map<String,Object>> queryWebOpenDeliveryTrace(String childCode){
		JsonResult<List<Steps>> result = new JsonResult<List<Steps>>();
		logger.info("开放物流信息查询(web端)");
		
		return soDeliveryManage.queryWebOpenDeliveryTrace(childCode);
	}

	@ResponseBody
	@RequestMapping(value="refreshStatusXA")
	public JsonResult<Map<String,Object>> queryWebOpenDeliveryTrace(int before){
		
		logger.info("开放物流信息查询(web端)");
		soPackageFacade.refreshSoPackageUnReceive(before);
		return JsonResult.success();
	}

	@ResponseBody
	@RequestMapping(value="refreshStatusAll")
	public JsonResult<Map<String,Object>> refreshStatusAll(){
		
		logger.info("开放物流信息查询(web端)");
		soPackageFacade.refreshSoPackageUnReceiveAll();
		return JsonResult.success();
	}
}
	