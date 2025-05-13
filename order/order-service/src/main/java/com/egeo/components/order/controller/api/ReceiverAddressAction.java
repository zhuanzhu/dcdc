package com.egeo.components.order.controller.api;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.order.business.ReceiverAddressManage;
import com.egeo.components.order.converter.ReceiverAddressConverter;
import com.egeo.components.order.dto.ReceiverAddressDTO;
import com.egeo.components.order.dto.ReceiverAddressDetailDTO;
import com.egeo.components.order.vo.ReceiverAddressDetailVO;
import com.egeo.components.order.vo.ReceiverAddressVO;
import com.egeo.entity.CacheUser;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/order/receiverAddress")
public class ReceiverAddressAction extends BaseSpringController {
	
	@Resource(name="receiverAddress")
	private ReceiverAddressManage receiverAddressManage;


	// 业务方法：
	@RequestMapping(value = "/findReceiverAddressById")
	@ResponseBody
	public JsonResult<ReceiverAddressVO> findReceiverAddressById(Long id ) {
		
		ReceiverAddressDTO rt = receiverAddressManage.findReceiverAddressById(id);		
		return JsonResult.success(ReceiverAddressConverter.toVO(rt));
					 
	}



	/**
	 * 根据用户查询所有收货地址接口
	 * @param vo
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/findReceiverAddressAll")
	@ResponseBody
	public JsonResult<Map<String, Object>> findReceiverAddressAll(ReceiverAddressVO vo,HttpServletRequest req ) {
		String str = req.getHeader("platformId");
		Long platformId=null;
		if(EmptyUtil.isNotEmpty(str)){
			platformId = Long.valueOf(str);
		}
		CacheUser userCache = this.getCacheUser();
		Map<String, Object> data = new HashMap<>();
		List<ReceiverAddressDTO> list = receiverAddressManage.queryReceiverAddressListCreatedByUser(userCache.getId(),platformId);
		data.put("list", list);
		data.put("id", null);
		for (ReceiverAddressDTO receiverAddressDTO : list) {
			if(receiverAddressDTO.getIsDefault() == 1){
				data.put("id", receiverAddressDTO.getId());
				break;
			}
		}
		return JsonResult.success(data);
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findReceiverAddressOfPage")
	@ResponseBody
	public JsonResult<PageResult<ReceiverAddressVO>> findReceiverAddressOfPage(ReceiverAddressVO vo,Pagination page,HttpServletRequest req ) {
		ReceiverAddressDTO dto = ReceiverAddressConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<ReceiverAddressDTO> rt = receiverAddressManage.findReceiverAddressOfPage(dto, page);
        List<ReceiverAddressVO> list = ReceiverAddressConverter.toVO(rt.getList());
        PageResult<ReceiverAddressVO> result = new PageResult<ReceiverAddressVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertReceiverAddressWithTx")
	@ResponseBody
	public JsonResult<Long> insertReceiverAddressWithTx(ReceiverAddressVO vo,HttpServletRequest req ) {
		ReceiverAddressDTO dto = ReceiverAddressConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		CacheUser userCache = this.getCacheUser();
		Long userId=userCache.getId();//用户id
		dto.setUserId(userId);
		Long rt = receiverAddressManage.insertReceiverAddressWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateReceiverAddressByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateReceiverAddressByIdWithTx(ReceiverAddressVO vo,HttpServletRequest req ) {
		ReceiverAddressDTO dto = ReceiverAddressConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		CacheUser userCache = this.getCacheUser();
		Long userId=userCache.getId();//用户id
		dto.setUserId(userId);
		int rt = receiverAddressManage.updateReceiverAddressWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteReceiverAddressWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteReceiverAddressWithTx(ReceiverAddressVO vo,HttpServletRequest req ) {
		ReceiverAddressDTO dto = ReceiverAddressConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = receiverAddressManage.deleteReceiverAddressWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	/**
	 * 修改默认地址
	 */
	@RequestMapping(value = "/defaultAddressById")
	@ResponseBody
	public JsonResult<String> defaultAddressById(Long id){
		CacheUser user = this.getCacheUser();
		String rt = receiverAddressManage.defaultAddressById(id,user.getId());
		return JsonResult.success(rt);	
		
	}
	/**
	 * 根据soId查询子订单所属的发货信息
	 */
	@RequestMapping(value = "/findReceiveDetailBySoId")
	@ResponseBody
	public JsonResult<List<ReceiverAddressDetailVO>> findReceiveDetailBySoId(Long soId,HttpServletRequest req){
		String str = req.getHeader("platformId");	
		Long platformId=null;
		if(EmptyUtil.isNotEmpty(str)){
			platformId = Long.valueOf(str);
		}
		List<ReceiverAddressDetailDTO>  dtoList = receiverAddressManage.findReceiveDetailBySoId(soId,platformId);
		for (ReceiverAddressDetailDTO receiverAddressDetailDTO : dtoList) {
			if(receiverAddressDetailDTO.getCreateTime().getTime() == receiverAddressDetailDTO.getUpdateTime().getTime()){
				receiverAddressDetailDTO.setUpdateTime(null);
				receiverAddressDetailDTO.setModifyMail(null);
			}
		}
		List<ReceiverAddressDetailVO> detailVO = ReceiverAddressConverter.toDetailVO(dtoList);
		
		return JsonResult.success(detailVO);	
		
	}
		
	//修改收货人地址
	@RequestMapping(value = "/modifyReceiverAddress", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult<String> modifyReceiverAddress(Long soChildId,ReceiverAddressVO vo,HttpServletRequest req) {
		logger.info("修改收货人地址");
		String str = req.getHeader("platformId");
		if (EmptyUtil.isEmpty(str)){
			return JsonResult.fail("platformId为空");
		}
		Long platformId = Long.valueOf(str);
		vo.setPlatformId(platformId);
		
		CacheUser user = this.getCacheUser();
		vo.setUserId(user.getId());
		ReceiverAddressDTO receiverAddressDTO = ReceiverAddressConverter.toDTO(vo);
		return receiverAddressManage.modifyReceiverAddress(soChildId,receiverAddressDTO);
	}
	
	/**
	 * 查询当前用户收货地址数量
	 * @param soChildId
	 * @param vo
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/receiverAddressSumByUserId")
	@ResponseBody
	public JsonResult<Integer> receiverAddressSumByUserId(HttpServletRequest req) {
		CacheUser cacheUser = this.getCacheUser();
		logger.info("查询当前用户"+cacheUser.getId()+"收货地址数量");
		String str = req.getHeader("platformId");
		if (EmptyUtil.isEmpty(str)){
			return JsonResult.fail("platformId为空");
		}
		Long platformId = Long.valueOf(str);
		int i = receiverAddressManage.receiverAddressSumByUserId(cacheUser.getId(),platformId);
		return JsonResult.success(i);

	}
}
	