package com.egeo.components.product.controller.api;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.CommodityProductUnitManage;
import com.egeo.components.product.converter.CommodityProductUnitConverter;
import com.egeo.components.product.dto.CommodityProductUnitDTO;
import com.egeo.components.product.facade.CommodityProductUnitFacade;
import com.egeo.components.product.vo.CommodityProductUnitVO;
import com.egeo.entity.CacheUser;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;

@Controller
@RequestMapping("/api/product/commodityProductUnit")
public class CommodityProductUnitAction extends BaseSpringController {

	@Resource(name = "commodityProductUnit")
	private CommodityProductUnitManage commodityProductUnitManage;

	@Resource(name = "commodityProductUnitFacade")
	private CommodityProductUnitFacade commodityProductUnitFacade;
	// 业务方法：
	@RequestMapping(value = "/findCommodityProductUnitById")
	@ResponseBody
	public JsonResult<CommodityProductUnitVO> findCommodityProductUnitById(Long id) {

		CommodityProductUnitDTO dto = new CommodityProductUnitDTO();
		dto.setId(id);
		CommodityProductUnitDTO rt = commodityProductUnitManage.findCommodityProductUnitById(dto);
		return JsonResult.success(CommodityProductUnitConverter.toVO(rt));

	}

	// 业务方法：
	@RequestMapping(value = "/findCommodityProductUnitAll")
	@ResponseBody
	public JsonResult<List<CommodityProductUnitVO>> findCommodityProductUnitAll(CommodityProductUnitVO vo,
			HttpServletRequest req) {
		CommodityProductUnitDTO dto = CommodityProductUnitConverter.toDTO(vo);
		String str = req.getHeader("platformId");
		if (EmptyUtil.isNotEmpty(str)) {
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<CommodityProductUnitDTO> rt = commodityProductUnitManage.findCommodityProductUnitAll(dto);
		return JsonResult.success(CommodityProductUnitConverter.toVO(rt));

	}

	// 业务方法：
	@RequestMapping(value = "/findCommodityProductUnitOfPage")
	@ResponseBody
	public JsonResult<PageResult<CommodityProductUnitVO>> findCommodityProductUnitOfPage(CommodityProductUnitVO vo,
			Pagination page, HttpServletRequest req) {
		CommodityProductUnitDTO dto = CommodityProductUnitConverter.toDTO(vo);
		String str = req.getHeader("platformId");
		if (EmptyUtil.isNotEmpty(str)) {
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<CommodityProductUnitDTO> rt = commodityProductUnitManage.findCommodityProductUnitOfPage(dto, page);
		List<CommodityProductUnitVO> list = CommodityProductUnitConverter.toVO(rt.getList());
		PageResult<CommodityProductUnitVO> result = new PageResult<CommodityProductUnitVO>();
		result.setList(list);
		result.setPageNo(rt.getPageNo());
		result.setPageSize(rt.getPageSize());
		result.setTotalSize(rt.getTotalSize());

		return JsonResult.success(result);

	}

	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertCommodityProductUnitWithTx")
	@ResponseBody
	public JsonResult<Long> insertCommodityProductUnitWithTx(CommodityProductUnitVO vo, HttpServletRequest req) {
		CommodityProductUnitDTO dto = CommodityProductUnitConverter.toDTO(vo);
		String str = req.getHeader("platformId");
		if (EmptyUtil.isNotEmpty(str)) {
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = commodityProductUnitManage.insertCommodityProductUnitWithTx(dto);
		return JsonResult.success(rt);
	}

	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateCommodityProductUnitByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateCommodityProductUnitByIdWithTx(CommodityProductUnitVO vo, HttpServletRequest req) {
		CommodityProductUnitDTO dto = CommodityProductUnitConverter.toDTO(vo);
		String str = req.getHeader("platformId");
		if (EmptyUtil.isNotEmpty(str)) {
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = commodityProductUnitManage.updateCommodityProductUnitWithTx(dto);
		return JsonResult.success(rt);
	}

	// 业务方法：
	@RequestMapping(value = "/deleteCommodityProductUnitWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteCommodityProductUnitWithTx(CommodityProductUnitVO vo, HttpServletRequest req) {
		CommodityProductUnitDTO dto = CommodityProductUnitConverter.toDTO(vo);
		String str = req.getHeader("platformId");
		if (EmptyUtil.isNotEmpty(str)) {
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = commodityProductUnitManage.deleteCommodityProductUnitWithTx(dto);
		return JsonResult.success(rt);
	}

	/**
	 * 分页查询pu库存信息
	 *
	 * @param vo
	 * @param page
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/findCommodityProductUnitStockOfPage")
	@ResponseBody
	public JsonResult<PageResult<Map<String, Object>>> findCommodityProductUnitStockOfPage(CommodityProductUnitVO vo,
			Pagination page, HttpServletRequest req) {
		CommodityProductUnitDTO dto = CommodityProductUnitConverter.toDTO(vo);
		String str = req.getHeader("platformId");
		if (EmptyUtil.isNotEmpty(str)) {
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<Map<String, Object>> rt = commodityProductUnitManage.findCommodityProductUnitStockOfPage(dto, page);

		return JsonResult.success(rt);

	}

	/**
	 * 根据suid查询su所有pu信息
	 *
	 * @param vo
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/findCommodityProductUnitAllByStandardUnitId")
	@ResponseBody
	public JsonResult<Map<String, Object>> findCommodityProductUnitAllByStandardUnitId(Long addressId,Long standardUnitId, Long storeId,Integer source,String channelProductId,
			HttpServletRequest req) {
		if(EmptyUtil.isEmpty(standardUnitId)){
			return JsonResult.fail("suId缺失");
		}
		//commodityProductUnitManage.checkJdProductDetail(standardUnitId);
		String str = req.getHeader("f");
		Integer f = null;
		if (EmptyUtil.isNotEmpty(str)) {
			f = Integer.valueOf(str);
		}
		if(EmptyUtil.isEmpty(f)){
			throw new BusinessException("请求来源不能为空");
		}
		CacheUser cacheUser = getCacheUser(false);
		Long companyId = cacheUser.getCompanyId();
		Long userId=cacheUser.getId();
		if(source==null) {
			source = 1;
		}
		Map<String, Object> rt = commodityProductUnitManage.findCommodityProductUnitAllByStandardUnitId(addressId,userId,companyId,standardUnitId, f, storeId,source,channelProductId);
		return JsonResult.success(rt);

	}

	@RequestMapping(value = "/findCommodityProductUnitStatus")
	@ResponseBody
	public JsonResult<Map<String, Object>> findCommodityProductUnitStatus(Long standardUnitId, Long storeId,Integer source, Long addressId,
			HttpServletRequest req) {
		if(EmptyUtil.isEmpty(standardUnitId)){
			return JsonResult.fail("suId缺失");
		}
		//commodityProductUnitManage.checkJdProductDetail(standardUnitId);
		String str = req.getHeader("f");
		Integer f = null;
		if (EmptyUtil.isNotEmpty(str)) {
			f = Integer.valueOf(str);
		}
		if(EmptyUtil.isEmpty(f)){
			throw new BusinessException("请求来源不能为空");
		}
		CacheUser cacheUser = getCacheUser(false);
		Long companyId = cacheUser.getCompanyId();
		Long userId=cacheUser.getId();
		if(source==null) {
			source = 1;
		}
		Map<String, Object> rt = commodityProductUnitManage.findCommodityProductUnitStockByStandardUnitIdAddress(userId,companyId,standardUnitId, f, storeId,3,addressId);
		return JsonResult.success(rt);

	}
	@RequestMapping(value = "/findCommodityProductUnitsStatus")
	@ResponseBody
	public JsonResult<Map<String,Map<String, Object>>> findCommodityProductUnitStatus(List<Long> standardUnitIds, Long storeId,Integer source, Long addressId,
			HttpServletRequest req) {
		if(EmptyUtil.isEmpty(standardUnitIds)){
			return JsonResult.fail("suId缺失");
		}
		//commodityProductUnitManage.checkJdProductDetail(standardUnitId);
		String str = req.getHeader("f");
		Integer f = null;
		if (EmptyUtil.isNotEmpty(str)) {
			f = Integer.valueOf(str);
		}
		if(EmptyUtil.isEmpty(f)){
			throw new BusinessException("请求来源不能为空");
		}
		CacheUser cacheUser = getCacheUser(false);
		Long companyId = cacheUser.getCompanyId();
		Long userId=cacheUser.getId();
		if(source==null) {
			source = 1;
		}
		Map<String,Map<String, Object>> rt = commodityProductUnitManage.findCommodityProductUnitStockByStandardUnitIdsAddress(userId,companyId,standardUnitIds, f, storeId,3,addressId);
		return JsonResult.success(rt);

	}
	/**
	 * 查询前50个pu 根据前端编号排序
	 * @param vo
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/findCommodityProductUnitLimit")
	@ResponseBody
	public JsonResult<List<CommodityProductUnitVO>> findCommodityProductUnitLimit(CommodityProductUnitVO vo,HttpServletRequest req ) {
		CommodityProductUnitDTO dto = CommodityProductUnitConverter.toDTO(vo);
		String str = req.getHeader("platformId");
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<CommodityProductUnitDTO> rt = commodityProductUnitManage.findCommodityProductUnitLimit(dto);
		return JsonResult.success(CommodityProductUnitConverter.toVO(rt));

	}

	// 业务方法：根据id更新数据
	@RequestMapping(value = "/checkJdProductDetail")
	@ResponseBody
	public JsonResult<Long> checkJdProductDetail(Long suId) {
		logger.info("更新京东商品详情");

		if(EmptyUtil.isEmpty(suId)){
			return JsonResult.fail("商品suId缺失");
		}
		//Long aLong = commodityProductUnitManage.checkJdProductDetail(suId);
		return JsonResult.success(1l);
	}



}
