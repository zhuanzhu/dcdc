package com.egeo.components.product.controller.api;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.MerchantProdCauseManage;
import com.egeo.components.product.converter.MerchantProdCauseConverter;
import com.egeo.components.product.vo.MerchantProdCauseVO;
import com.egeo.components.product.dto.MerchantProdCauseDTO;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;
import com.egeo.utils.EmptyUtil;

@Controller
@RequestMapping("/api/product/merchantMerchantProdCause")
public class MerchantProductCauseAction extends BaseSpringController {

	@Resource(name = "merchantProdCause")
	private MerchantProdCauseManage merchantProdCauseManage;

	@RequestMapping(value = "findAll")
	@ResponseBody
	public JsonResult<MerchantProdCauseVO> findAll(MerchantProdCauseVO merchantProdCauseVO) {
		logger.info("开始根据条件查询 商品审核失败原因信息");
		JsonResult<MerchantProdCauseVO> result = new JsonResult<MerchantProdCauseVO>();
		List<MerchantProdCauseDTO> list = merchantProdCauseManage.findMerchantProdCauseAll(MerchantProdCauseConverter.toDTO(merchantProdCauseVO));
		if(EmptyUtil.isNotEmpty(list)){
			result.setData(MerchantProdCauseConverter.toVO(list.get(0)));
		}
		return result;
	}

	@RequestMapping(value = "saveMerchantProdCause")
	@ResponseBody
	public JsonResult<Long> saveMerchantProdCause(MerchantProdCauseVO merchantProdCauseVO) {
		logger.info("开始保存商品不通过原因");
		JsonResult<Long> result = new JsonResult<Long>();
		Long rows = merchantProdCauseManage.insertMerchantProdCauseWithTx(MerchantProdCauseConverter.toDTO(merchantProdCauseVO));
		result.setData(rows);
		return result;
	}

}
