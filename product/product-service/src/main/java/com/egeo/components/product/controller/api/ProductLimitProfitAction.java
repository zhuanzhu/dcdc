package com.egeo.components.product.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


import com.egeo.components.product.business.JdProductManage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.ProductLimitProfitManage;
import com.egeo.components.product.converter.ProductLimitProfitConverter;
import com.egeo.components.product.vo.ProductLimitProfitVO;
import com.egeo.components.product.dto.ProductLimitProfitDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;
import com.egeo.utils.EmptyUtil;



@Controller
@RequestMapping("/api/product/productLimitProfit")
public class ProductLimitProfitAction extends BaseSpringController {
	
	@Resource(name="productLimitProfit")
	private ProductLimitProfitManage productLimitProfitManage;

	@Resource(name = "jdProduct")
	private JdProductManage jdProductManage;

	// 业务方法：
	@RequestMapping(value = "/findProductLimitProfitById")
	@ResponseBody
	public JsonResult<ProductLimitProfitVO> findProductLimitProfitById() {
		
		ProductLimitProfitDTO dto = new ProductLimitProfitDTO();
		dto.setId(1L);
		ProductLimitProfitDTO rt = productLimitProfitManage.findProductLimitProfitById(dto);		
		return JsonResult.success(ProductLimitProfitConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findProductLimitProfitAll")
	@ResponseBody
	public JsonResult<List<ProductLimitProfitVO>> findProductLimitProfitAll(ProductLimitProfitVO vo,HttpServletRequest req ) {
		ProductLimitProfitDTO dto = ProductLimitProfitConverter.toDTO(vo);
		List<ProductLimitProfitDTO> rt = productLimitProfitManage.findProductLimitProfitAll(dto);	
		return JsonResult.success(ProductLimitProfitConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findProductLimitProfitOfPage")
	@ResponseBody
	public JsonResult<PageResult<ProductLimitProfitVO>> findProductLimitProfitOfPage(ProductLimitProfitVO vo,Pagination page,HttpServletRequest req ) {
		ProductLimitProfitDTO dto = ProductLimitProfitConverter.toDTO(vo);
		PageResult<ProductLimitProfitDTO> rt = productLimitProfitManage.findProductLimitProfitOfPage(dto, page);
        List<ProductLimitProfitVO> list = ProductLimitProfitConverter.toVO(rt.getList());
        PageResult<ProductLimitProfitVO> result = new PageResult<ProductLimitProfitVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertProductLimitProfitWithTx")
	@ResponseBody
	public JsonResult<Long> insertProductLimitProfitWithTx(ProductLimitProfitVO vo,HttpServletRequest req ) {
		ProductLimitProfitDTO dto = ProductLimitProfitConverter.toDTO(vo);
		Long rt = productLimitProfitManage.insertProductLimitProfitWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateProductLimitProfitByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateProductLimitProfitByIdWithTx(ProductLimitProfitVO vo,HttpServletRequest req ) {
		ProductLimitProfitDTO dto = ProductLimitProfitConverter.toDTO(vo);
		if(EmptyUtil.isEmpty(vo.getProductLimitProfit())){
			return JsonResult.fail("设置值不能为空");

		}
		dto.setId(1L);
		int rt = productLimitProfitManage.updateProductLimitProfitWithTx(dto);
		//对京东商品本地处理
		jdProductManage.updateJdProductByLimitProfit(dto.getProductLimitProfit());
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteProductLimitProfitWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteProductLimitProfitWithTx(ProductLimitProfitVO vo,HttpServletRequest req ) {
		ProductLimitProfitDTO dto = ProductLimitProfitConverter.toDTO(vo);
		int rt = productLimitProfitManage.deleteProductLimitProfitWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	