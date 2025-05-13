package com.egeo.components.product.controller.api;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.CardStampsAdministrationManage;
import com.egeo.components.product.converter.CardStampsAdministrationConverter;
import com.egeo.components.product.dto.CardStampsAdministrationDTO;
import com.egeo.components.product.vo.CardStampsAdministrationVO;
import com.egeo.entity.CacheUser;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.HostUtils;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/cardStampsAdministration")
public class CardStampsAdministrationAction extends BaseSpringController {
	
	@Resource(name="cardStampsAdministration")
	private CardStampsAdministrationManage cardStampsAdministrationManage;


	// 业务方法：
	@RequestMapping(value = "/findCardStampsAdministrationById")
	@ResponseBody
	public JsonResult<CardStampsAdministrationVO> findCardStampsAdministrationById(Long id ) {
		
		CardStampsAdministrationDTO dto = new CardStampsAdministrationDTO();
		dto.setId(id);
		CardStampsAdministrationDTO rt = cardStampsAdministrationManage.findCardStampsAdministrationById(dto);		
		return JsonResult.success(CardStampsAdministrationConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findCardStampsAdministrationAll")
	@ResponseBody
	public JsonResult<List<CardStampsAdministrationVO>> findCardStampsAdministrationAll(CardStampsAdministrationVO vo,HttpServletRequest req ) {
		CardStampsAdministrationDTO dto = CardStampsAdministrationConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<CardStampsAdministrationDTO> rt = cardStampsAdministrationManage.findCardStampsAdministrationAll(dto);	
		return JsonResult.success(CardStampsAdministrationConverter.toVO(rt));
					 
	}	

	/**
	 * 分页查询卡券模版信息
	 * @param vo
	 * @param page
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/findPage")
	@ResponseBody
	public JsonResult<PageResult<Map<String, Object>>> findPage(CardStampsAdministrationVO vo,Pagination page,HttpServletRequest req ) {
		CardStampsAdministrationDTO dto = CardStampsAdministrationConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<Map<String, Object>> rt = cardStampsAdministrationManage.findPage(dto, page);
		
		return JsonResult.success(rt);
					 
	}
	
	@RequestMapping(value = "/findCardStampsAdministrationOfPage")
	@ResponseBody
	public JsonResult<PageResult<CardStampsAdministrationVO>> findCardStampsAdministrationOfPage(CardStampsAdministrationVO vo,Pagination page,HttpServletRequest req ) {
		CardStampsAdministrationDTO dto = CardStampsAdministrationConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<CardStampsAdministrationDTO> rt = cardStampsAdministrationManage.findCardStampsAdministrationOfPage(dto, page);
        List<CardStampsAdministrationVO> list = CardStampsAdministrationConverter.toVO(rt.getList());
        PageResult<CardStampsAdministrationVO> result = new PageResult<CardStampsAdministrationVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertCardStampsAdministrationWithTx")
	@ResponseBody
	public JsonResult<Long> insertCardStampsAdministrationWithTx(CardStampsAdministrationVO vo,HttpServletRequest req ) {
		CardStampsAdministrationDTO dto = CardStampsAdministrationConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		String clientVersionno = req.getHeader("v");	
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
			dto.setClientVersionno(clientVersionno);
		}
		CacheUser userCache = this.getCacheUser();
		//获得客户端的ip地址 
		String ip = req.getRemoteAddr();
		//根据ip获取mac地址
		String mac;
		try {
			mac = HostUtils.getLocalMac(ip);
		} catch (Exception e) {
			throw new BusinessException("获取mac地址异常" + e.getMessage());
			
		}
		Long userId = userCache.getId();
		String userName = userCache.getName();
		dto.setCreateUserid(userId);
		dto.setCreateUsername(userName);
		dto.setCreateUserip(ip);
		dto.setCreateUsermac(mac);
		Long rt = cardStampsAdministrationManage.insertCardStampsAdministrationWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateCardStampsAdministrationByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateCardStampsAdministrationByIdWithTx(CardStampsAdministrationVO vo,HttpServletRequest req ) {
		CardStampsAdministrationDTO dto = CardStampsAdministrationConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		String clientVersionno = req.getHeader("v");	
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
			dto.setClientVersionno(clientVersionno);
		}
		CacheUser userCache = this.getCacheUser();
		//获得客户端的ip地址 
		String ip = req.getRemoteAddr();
		//根据ip获取mac地址
		String mac;
		try {
			mac = HostUtils.getLocalMac(ip);
		} catch (Exception e) {
			throw new BusinessException("获取mac地址异常" + e.getMessage());
			
		}
		Long userId = userCache.getId();
		String userName = userCache.getName();
		dto.setUpdateUserid(userId);
		dto.setUpdateUsername(userName);
		dto.setUpdateUserip(ip);
		dto.setUpdateUsermac(mac);
		int rt = cardStampsAdministrationManage.updateCardStampsAdministrationWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteCardStampsAdministrationWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteCardStampsAdministrationWithTx(CardStampsAdministrationVO vo,HttpServletRequest req ) {
		CardStampsAdministrationDTO dto = CardStampsAdministrationConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = cardStampsAdministrationManage.deleteCardStampsAdministrationWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	