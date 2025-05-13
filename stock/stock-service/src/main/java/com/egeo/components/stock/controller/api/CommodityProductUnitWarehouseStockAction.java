package com.egeo.components.stock.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.stock.business.CommodityProductUnitWarehouseStockManage;
import com.egeo.components.stock.converter.CommodityProductUnitWarehouseStockConverter;
import com.egeo.components.stock.dto.CommodityProductUnitWarehouseStockDTO;
import com.egeo.components.stock.vo.CommodityProductUnitWarehouseStockVO;
import com.egeo.entity.CacheUser;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.HostUtils;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/stock/commodityProductUnitWarehouseStock")
public class CommodityProductUnitWarehouseStockAction extends BaseSpringController {
	
	@Resource(name="commodityProductUnitWarehouseStock")
	private CommodityProductUnitWarehouseStockManage commodityProductUnitWarehouseStockManage;


	// 业务方法：
	@RequestMapping(value = "/findCommodityProductUnitWarehouseStockById")
	@ResponseBody
	public JsonResult<CommodityProductUnitWarehouseStockVO> findCommodityProductUnitWarehouseStockById(Long id ) {
		
		CommodityProductUnitWarehouseStockDTO dto = new CommodityProductUnitWarehouseStockDTO();
		dto.setId(id);
		CommodityProductUnitWarehouseStockDTO rt = commodityProductUnitWarehouseStockManage.findCommodityProductUnitWarehouseStockById(dto);		
		return JsonResult.success(CommodityProductUnitWarehouseStockConverter.toVO(rt));
					 
	}
	
	/**
	 * 根据puid查询pu库存信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/findByProductUnitId")
	@ResponseBody
	public JsonResult<CommodityProductUnitWarehouseStockVO> findByProductUnitId(Long productUnitId ) {
		logger.info("根据puid查询pu库存信息,puId为："+productUnitId);
		CommodityProductUnitWarehouseStockDTO rt = commodityProductUnitWarehouseStockManage.findByProductUnitId(productUnitId);		
		return JsonResult.success(CommodityProductUnitWarehouseStockConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findCommodityProductUnitWarehouseStockAll")
	@ResponseBody
	public JsonResult<List<CommodityProductUnitWarehouseStockVO>> findCommodityProductUnitWarehouseStockAll(CommodityProductUnitWarehouseStockVO vo,HttpServletRequest req ) {
		CommodityProductUnitWarehouseStockDTO dto = CommodityProductUnitWarehouseStockConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<CommodityProductUnitWarehouseStockDTO> rt = commodityProductUnitWarehouseStockManage.findCommodityProductUnitWarehouseStockAll(dto);	
		return JsonResult.success(CommodityProductUnitWarehouseStockConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findCommodityProductUnitWarehouseStockOfPage")
	@ResponseBody
	public JsonResult<PageResult<CommodityProductUnitWarehouseStockVO>> findCommodityProductUnitWarehouseStockOfPage(CommodityProductUnitWarehouseStockVO vo,Pagination page,HttpServletRequest req ) {
		CommodityProductUnitWarehouseStockDTO dto = CommodityProductUnitWarehouseStockConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<CommodityProductUnitWarehouseStockDTO> rt = commodityProductUnitWarehouseStockManage.findCommodityProductUnitWarehouseStockOfPage(dto, page);
        List<CommodityProductUnitWarehouseStockVO> list = CommodityProductUnitWarehouseStockConverter.toVO(rt.getList());
        PageResult<CommodityProductUnitWarehouseStockVO> result = new PageResult<CommodityProductUnitWarehouseStockVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertCommodityProductUnitWarehouseStockWithTx")
	@ResponseBody
	public JsonResult<Long> insertCommodityProductUnitWarehouseStockWithTx(CommodityProductUnitWarehouseStockVO vo,HttpServletRequest req ) {
		CommodityProductUnitWarehouseStockDTO dto = CommodityProductUnitWarehouseStockConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = commodityProductUnitWarehouseStockManage.insertCommodityProductUnitWarehouseStockWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateCommodityProductUnitWarehouseStockByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateCommodityProductUnitWarehouseStockByIdWithTx(CommodityProductUnitWarehouseStockVO vo,HttpServletRequest req ) {
		CommodityProductUnitWarehouseStockDTO dto = CommodityProductUnitWarehouseStockConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = commodityProductUnitWarehouseStockManage.updateCommodityProductUnitWarehouseStockWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteCommodityProductUnitWarehouseStockWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteCommodityProductUnitWarehouseStockWithTx(CommodityProductUnitWarehouseStockVO vo,HttpServletRequest req ) {
		CommodityProductUnitWarehouseStockDTO dto = CommodityProductUnitWarehouseStockConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = commodityProductUnitWarehouseStockManage.deleteCommodityProductUnitWarehouseStockWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	/**
	 * 进货
	 * @param vo
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/merchandiseStockWithTx")
	@ResponseBody
	public JsonResult<Integer> merchandiseStockWithTx(CommodityProductUnitWarehouseStockVO vo,HttpServletRequest req ) {
		logger.info("action进货：puId:" + vo.getCommodityProductUnitId() + ",数量:"+ vo.getRealStockNum());
		CommodityProductUnitWarehouseStockDTO dto = CommodityProductUnitWarehouseStockConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
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
		int rt = commodityProductUnitWarehouseStockManage.merchandiseStockWithTx(dto,userId,userName,ip,mac);	
		return JsonResult.success(rt);					 
	}
	
	/**
	 * 出货
	 * @param vo
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/deliverStockWithTx")
	@ResponseBody
	public JsonResult<Integer> deliverStockWithTx(CommodityProductUnitWarehouseStockVO vo,HttpServletRequest req ) {
		logger.info("action出货：puId:" + vo.getCommodityProductUnitId() + ",数量:"+ vo.getRealStockNum());
		CommodityProductUnitWarehouseStockDTO dto = CommodityProductUnitWarehouseStockConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
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
		int rt = commodityProductUnitWarehouseStockManage.deliverStockWithTx(dto,userId,userName,ip,mac);	
		return JsonResult.success(rt);					 
	}
	
}
	