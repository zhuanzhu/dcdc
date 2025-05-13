package com.egeo.components.order.controller.api;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.order.business.InvoiceManage;
import com.egeo.components.order.converter.InvoiceConverter;
import com.egeo.components.order.dto.InvoiceDTO;
import com.egeo.components.order.vo.InvoiceSimpleVO;
import com.egeo.components.order.vo.InvoiceVO;
import com.egeo.entity.CacheUser;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;

@Controller
@RequestMapping("/api/order/invoice")
public class InvoiceAction extends BaseSpringController {

	@Resource(name = "invoice")
	private InvoiceManage invoiceManage;

	/**
	 * 新增或编辑公共发票信息(后台)
	 * 
	 * @param vo
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/insertOrUpdateInvoiceWithTx")
	@ResponseBody
	public JsonResult<Long> insertOrUpdateInvoiceWithTx(InvoiceVO vo, Long orderId, HttpServletRequest req) {
		CacheUser cacheUser = getCacheUser();
		InvoiceDTO dto = InvoiceConverter.toDTO(vo);
		dto.setPlatformId(cacheUser.getPlatformId());
		dto.setUserId(cacheUser.getId());
		if (orderId == null) 
			return JsonResult.fail("订单id不能为空");
		return invoiceManage.insertOrUpdateInvoiceWithTx(dto, orderId);
	}
	
	/**
	 * 编辑发票公共信息列表
	 * @param orderId
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/findInvoiceList")
	@ResponseBody
	public JsonResult<List<InvoiceVO>> findInvoiceList(Long orderId, HttpServletRequest req) {
		
		return invoiceManage.findInvoiceAll(orderId);
	}
	
	//=================后台与客户端的分割线==================
	
	/**
	 * 新增或编辑公共发票信息(前台)
	 * @param vo
	 * @param orderId
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/insertOrUpdateInvoice")
	@ResponseBody
	public JsonResult<InvoiceVO> insertOrUpdateInvoice(InvoiceVO vo, Long orderId, HttpServletRequest req) {
		CacheUser cacheUser = getCacheUser();
		InvoiceDTO dto = InvoiceConverter.toDTO(vo);
		dto.setPlatformId(cacheUser.getPlatformId());
		dto.setUserId(cacheUser.getId());
		dto.setTitleType(1);	// 用户创建 的公共发票信息的发票抬头类型都是公司
		orderId = null;
		JsonResult<Long> rt = invoiceManage.insertOrUpdateInvoiceWithTx(dto, orderId);
		if (rt.getCode() == 0) {
			// 新增或编辑成功
			Long invoiceId = rt.getData();
			InvoiceDTO dto_ = new InvoiceDTO();
			dto_.setId(invoiceId);
			dto_ = invoiceManage.findInvoiceById(dto_);
			return JsonResult.success(InvoiceConverter.toVO(dto_));
		} else {
			
			return fail(rt.getCode(),rt.getError());
		}
		
	}

	/**
	 * 删除公共发票信息(逻辑删除)
	 * 
	 * @param vo
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/deleteInvoiceWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteInvoiceWithTx(InvoiceVO vo, HttpServletRequest req) {
		CacheUser cacheUser = getCacheUser();
		InvoiceDTO dto = InvoiceConverter.toDTO(vo);
		dto.setPlatformId(cacheUser.getPlatformId());
		dto.setUserId(cacheUser.getId());
		int rt = invoiceManage.deleteInvoiceWithTx(dto);
		return JsonResult.success(rt);
	}

	/**
	 * 通过id查询公共发票信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/findInvoiceById")
	@ResponseBody
	public JsonResult<InvoiceVO> findInvoiceById(Long id) {

		if (id == null)
			return JsonResult.fail("id不能为空");
		InvoiceDTO dto = new InvoiceDTO();
		dto.setId(id);
		InvoiceDTO rt = invoiceManage.findInvoiceById(dto);
		if (rt == null)
			return JsonResult.fail("该发票信息不存在");
		return JsonResult.success(InvoiceConverter.toVO(rt));

	}

	/**
	 * 公共发票信息列表
	 * @param vo
	 * @param page
	 * @param req
	 * @param isAddValueTax 是否是增值税发票
	 * @return 
	 */
	@RequestMapping(value = "/findInvoiceOfPage")
	@ResponseBody
	public JsonResult<PageResult<InvoiceSimpleVO>> findInvoiceOfPage(Pagination page, HttpServletRequest req) {
		CacheUser cacheUser = getCacheUser();
		InvoiceDTO dto = new InvoiceDTO();
		dto.setUserId(cacheUser.getId());
		dto.setPlatformId(cacheUser.getPlatformId());
		dto.setIsDelete(0);
		
		return JsonResult.success(invoiceManage.findInvoiceOfPage(dto, page));

	}
	
	/**
	 * 选择发票信息
	 * @param id 订单发票的id
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/chooseInvoice")
	@ResponseBody
	public JsonResult<InvoiceSimpleVO> chooseInvoice(Long id, HttpServletRequest req) {
		CacheUser cacheUser = getCacheUser();
		InvoiceDTO dto = new InvoiceDTO();
		dto.setUserId(cacheUser.getId());
		dto.setPlatformId(cacheUser.getPlatformId());
		
		return JsonResult.success(invoiceManage.chooseInvoice(dto, id));
	}
	
	/**
	 * 查询公共发票的总个数
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/queryCommonInvoiceCount")
	@ResponseBody
	public JsonResult<Integer> queryCommonInvoiceCount(HttpServletRequest req) {
		CacheUser cacheUser = getCacheUser();
		InvoiceDTO dto = new InvoiceDTO();
		dto.setUserId(cacheUser.getId());
		dto.setPlatformId(cacheUser.getPlatformId());
		dto.setCreateType(0);
		dto.setTitleType(1);
		dto.setIsDelete(0);
		return JsonResult.success(invoiceManage.findInvoiceAll(dto).size());
	}

	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateInvoiceByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateInvoiceByIdWithTx(InvoiceVO vo, HttpServletRequest req) {
		InvoiceDTO dto = InvoiceConverter.toDTO(vo);
		String str = req.getHeader("platformId");
		if (EmptyUtil.isNotEmpty(str)) {
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = invoiceManage.updateInvoiceWithTx(dto);
		return JsonResult.success(rt);
	}

	// 业务方法：
	@RequestMapping(value = "/findInvoiceAll")
	@ResponseBody
	public JsonResult<List<InvoiceVO>> findInvoiceAll(InvoiceVO vo, HttpServletRequest req) {
		InvoiceDTO dto = InvoiceConverter.toDTO(vo);
		String str = req.getHeader("platformId");
		if (EmptyUtil.isNotEmpty(str)) {
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<InvoiceDTO> rt = invoiceManage.findInvoiceAll(dto);
		return JsonResult.success(InvoiceConverter.toVO(rt));

	}

	/**
	 * 查询用户默认的发票信息
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "findDefaultInvoiceByUserId")
	@ResponseBody
	private JsonResult<InvoiceVO> findDefaultInvoiceByUserId(HttpServletRequest req){
		CacheUser userCache = getCacheUser();
		Long userId = userCache.getId();// 用户id
		String str = req.getHeader("platformId");
		if (EmptyUtil.isEmpty(str)) {
			return JsonResult.fail("platform缺失");
		}
		Long platformId = Long.valueOf(str);
		return invoiceManage.findDefaultInvoiceByUserId(userId,platformId);

	}

}
