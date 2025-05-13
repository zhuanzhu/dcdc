package com.egeo.components.product.controller.api;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.JdProductManage;
import com.egeo.components.product.dao.JdPriceDAO;
import com.egeo.components.product.dto.JdPriceDTO;
import com.egeo.components.product.dto.JdProductDTO;
import com.egeo.components.product.vo.JdPriceAuditingVO;
import com.egeo.config.RuntimeContext;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/jdPrice")
public class JdPriceAction extends BaseSpringController {
	
	@Resource
	private JdPriceDAO jdPrice;

	@Resource(name="jdProduct")
	private JdProductManage jdProductManage;

	// 业务方法：
	@RequestMapping(value = "/set")
	@ResponseBody
	public JsonResult findJdPriceConfig(Long id,Long spuId,Integer priceType,String value) {
		logger.info("jdPrice_set:id-{},spuId-{},priceType-{},value-{}",id,spuId,priceType,value);
		if(RuntimeContext.cacheUser().getEnterpriseId()==null) {
			return JsonResult.fail("操作异常，请联系管理员");
		}
		List<JdPriceDTO> olds = jdPrice.findAllPriceOfEnterpriseAndPid(id, RuntimeContext.cacheUser().getEnterpriseId());
		if(olds==null || olds.size()==0) {
			JdPriceDTO dto = new JdPriceDTO();
			dto.setAudit(1);
			dto.setPriceType(priceType);
			dto.setPriceValue(value);
			dto.setPid(id);
//			dto.setSpuId(spuId);
			dto.setEnterpriseId(RuntimeContext.cacheUser().getEnterpriseId());
			int insert = jdPrice.insert(dto);	
			if(insert>0) {
				return JsonResult.success();
			}
		}else if(olds.size()==1){
			JdPriceDTO dto = new JdPriceDTO();
			dto.setId(olds.get(0).getId());
			dto.setAudit(1);
			dto.setPriceType(priceType);
			dto.setPriceValue(value);
			dto.setPid(id);
//			dto.setSpuId(spuId);
			dto.setEnterpriseId(RuntimeContext.cacheUser().getEnterpriseId());
			int insert = jdPrice.update(dto);	
			if(insert>0) {
				return JsonResult.success();
			}
		}else {
			for(JdPriceDTO one :olds) {
				jdPrice.delete(one);
			}
			JdPriceDTO dto = new JdPriceDTO();
			dto.setAudit(1);
			dto.setPriceType(priceType);
			dto.setPriceValue(value);
			dto.setPid(id);
//			dto.setSpuId(spuId);
			dto.setEnterpriseId(RuntimeContext.cacheUser().getEnterpriseId());
			int insert = jdPrice.insert(dto);	
			if(insert>0) {
				return JsonResult.success();
			}
		}
		
		return JsonResult.fail("操作异常，请联系管理员");
					 
	}	

	// 业务方法：根据id更新数据
	@RequestMapping(value = "/delete")
	@ResponseBody
	public JsonResult<String> cancelPrice(Long spuId) {
		logger.info("jdPrice_delete:spuId-{}",spuId);
		if(EmptyUtil.isEmpty(spuId)){
			return JsonResult.fail("产品Id不存在");

		}
		JdPriceDTO dto = new JdPriceDTO();
		dto.setPid(spuId);
		dto.setEnterpriseId(RuntimeContext.cacheUser().getEnterpriseId());
		
		jdPrice.delete(dto);
		return JsonResult.success("删除成功");
	}
	// 业务方法：根据id更新数据
	/**
	 * pass =0 拒绝  其他  通过
	 * @param priceId
	 * @param pass
	 * @return
	 */
	@RequestMapping(value = "/audit")
	@ResponseBody
	public JsonResult<String> audit(Long priceId,Integer pass) {
		logger.info("jdPrice_audit:priceId-{},pass-{}",priceId,pass);
		if(EmptyUtil.isEmpty(priceId)||EmptyUtil.isEmpty(pass)){
			return JsonResult.fail("设置值不能为空");

		}
		JdPriceDTO dto = new JdPriceDTO();
		dto.setId(priceId);
		dto.setAudit(pass.intValue()==0?0:2);
		jdPrice.audit(dto);
		return JsonResult.success("更新成功");
	}
	
	@RequestMapping(value = "/list/auditing")
	@ResponseBody
	public JsonResult<List<JdPriceAuditingVO>> auditing(Long enterpriseId) {

		if(RuntimeContext.cacheUser()!=null && RuntimeContext.cacheUser().getType()!=null &&RuntimeContext.cacheUser().getType().intValue()==1) {

			List<JdPriceDTO> prices ;
			if(enterpriseId==null) {
				prices = jdPrice.findAllPriceAndAudit(1);
			}else {
				prices = jdPrice.findAllPriceOfEnterpriseAndAudit(enterpriseId, 1);
			}
			return JsonResult.success(jdProductManage.findJdProductForPriceAuditing(prices));
		}
		return JsonResult.fail("异常 403");
	}
		
}
	