package com.egeo.components.stock.controller.api;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.stock.business.MerchantProductWarehouseStockManage;
import com.egeo.components.stock.vo.MerchantProductStock;
import com.egeo.components.stock.vo.MerchantProductWarehouseStockVO;
import com.egeo.components.stock.dto.MerchantProductWarehouseStockDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;
import com.egeo.utils.EmptyUtil;

@Controller
@RequestMapping("/api/stock/merchantProductWarehouseStock")
public class MerchantProductWarehouseStockAction extends BaseSpringController {

	@Resource(name = "merchantProductWarehouseStock")
	private MerchantProductWarehouseStockManage merchantProductWarehouseStockManage;

	@RequestMapping(value = "saveMerchantProductWarehouseStock")
	@ResponseBody
	public JsonResult<Long> saveMerchantProductWarehouseStock(
			MerchantProductWarehouseStockVO merchantProductWarehouseStockVO) {
		logger.info("开始保存商品库存信息");
		JsonResult<Long> result = new JsonResult<Long>();
		Long rows = merchantProductWarehouseStockManage
				.saveMerchantProductWarehouseStock(merchantProductWarehouseStockVO);
		result.setData(rows);
		return result;
	}

	@RequestMapping(value = "updateMerchantProductWarehouseStock")
	@ResponseBody
	public JsonResult<Long> updateMerchantProductWarehouseStock(
			MerchantProductWarehouseStockVO merchantProductWarehouseStockVO) {
		logger.info("开始修改商品库存信息");
		JsonResult<Long> result = new JsonResult<Long>();
		Long rows = merchantProductWarehouseStockManage
				.updateMerchantProductWarehouseStock(merchantProductWarehouseStockVO);
		result.setData(rows);
		return result;
	}

	@RequestMapping(value = "findById")
	@ResponseBody
	public JsonResult<MerchantProductWarehouseStockDTO> findById(
			MerchantProductWarehouseStockVO merchantProductWarehouseStockVO) {
		logger.info("开始根据id查询商品库存信息");
		JsonResult<MerchantProductWarehouseStockDTO> result = new JsonResult<MerchantProductWarehouseStockDTO>();
		MerchantProductWarehouseStockDTO MerchantProductWarehouseStockDTO = merchantProductWarehouseStockManage
				.findById(merchantProductWarehouseStockVO);
		result.setData(MerchantProductWarehouseStockDTO);
		return result;
	}

	@RequestMapping(value = "findAll")
	@ResponseBody
	public JsonResult<List<MerchantProductWarehouseStockVO>> findAll(
			MerchantProductWarehouseStockVO merchantProductWarehouseStockVO) {
		logger.info("开始根据条件查询商品库存信息");
		JsonResult<List<MerchantProductWarehouseStockVO>> result = new JsonResult<List<MerchantProductWarehouseStockVO>>();
		List<MerchantProductWarehouseStockVO> list = merchantProductWarehouseStockManage
				.findAll(merchantProductWarehouseStockVO);
		result.setData(list);
		return result;
	}

	@RequestMapping(value = "findPageMerchantProductWarehouseStock")
	@ResponseBody
	public JsonResult<PageResult<MerchantProductStock>> findPageMerchantProductWarehouseStock(Pagination page,
			MerchantProductWarehouseStockVO MerchantProductWarehouseStockVO, HttpServletRequest req) {
		String str = req.getHeader("platformId");
		if (EmptyUtil.isNotEmpty(str)) {
			Long platformId = Long.valueOf(str);
			MerchantProductWarehouseStockVO.setPlatformId(platformId);
		}
		logger.info("开始根据条件分页查询商品库存信息!");
		JsonResult<PageResult<MerchantProductStock>> result = new JsonResult<PageResult<MerchantProductStock>>();
		PageResult<MerchantProductStock> pageResult = merchantProductWarehouseStockManage
				.findPageMerchantProductWarehouseStock(page, MerchantProductWarehouseStockVO);
		result.setData(pageResult);
		return result;
	}

}
