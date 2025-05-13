package com.egeo.components.product.controller.api;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.egeo.components.product.business.JdProductManage;
import com.egeo.components.product.converter.JdProductConverter;
import com.egeo.components.product.dto.JDProductSearchDTO;
import com.egeo.components.product.dto.JdProductDTO;
import com.egeo.components.product.vo.JdProductEnterpriseVO;
import com.egeo.components.product.vo.JdProductVO;
import com.egeo.entity.CacheUser;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.HostUtils;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/jdSearch")
public class JdSearchAction extends BaseSpringController {
	
	
	@Resource(name="jdProduct")
	private JdProductManage jdProductManage;


	// 业务方法：
	/**
	 * @param catId
	 * @return
	 */
	@RequestMapping(value = "/category/{catId}")
	@ResponseBody
	public JsonResult<PageResult<JdProductDTO>> category(@PathVariable String catId) {
		JDProductSearchDTO dto = new JDProductSearchDTO();
		dto.setCatId(catId);
		return jdProductManage.searchOfCategoryLevel2(dto);
		
	}
	// 业务方法：
		@RequestMapping(value = "/search")
		@ResponseBody
		public JsonResult<PageResult<JdProductDTO>> search(String keyword,String catId,Integer pageNo,Integer pageSize,Integer priceMax,
				Integer priceMin,String brand,String catId1,String catId2) {
			JDProductSearchDTO dto = new JDProductSearchDTO();
			dto.addBrands(brand);
			dto.setKeyword(keyword);
			dto.setCatId(catId);
			dto.setPageIndex(pageNo);
			dto.setPageSize(pageSize);
			dto.setPriceMax(priceMax);
			dto.setPriceMin(priceMin);
			dto.setCid1(catId1);
			dto.setCid2(catId2);
			return jdProductManage.search(dto);
		}

		// 业务方法：
			@RequestMapping(value = "/enterprise")
			@ResponseBody
			public JsonResult<PageResult<JdProductEnterpriseVO>> enterprise(String keyword,String catId,Integer pageNo,Integer pageSize,Integer priceMax,
					Integer priceMin,String brand,String catId1,String catId2) {
				JDProductSearchDTO dto = new JDProductSearchDTO();
				dto.addBrands(brand);
				dto.setKeyword(keyword);
				dto.setCatId(catId);
				dto.setPageIndex(pageNo);
				dto.setPageSize(pageSize);
				dto.setPriceMax(priceMax);
				dto.setPriceMin(priceMin);
				dto.setCid1(catId1);
				dto.setCid2(catId2);
				return jdProductManage.searchEnterprise(dto);
			}

	@RequestMapping(value = "/enterpriseExport")
	@ResponseBody
	public JsonResult<Map<String, Object>> enterpriseExport(String keyword, String catId, Integer priceMax,
															Integer priceMin, String brand, String catId1, String catId2) {
		JDProductSearchDTO dto = new JDProductSearchDTO();
		dto.addBrands(brand);
		dto.setKeyword(keyword);
		dto.setCatId(catId);
		dto.setPageIndex(1);
		dto.setPageSize(100);
		dto.setPriceMax(priceMax);
		dto.setPriceMin(priceMin);
		dto.setCid1(catId1);
		dto.setCid2(catId2);
		return jdProductManage.exportEnterprise(dto);
	}


			@RequestMapping(value = "/platform")
			@ResponseBody
			public JsonResult<PageResult<JdProductDTO>> platform(String keyword,String catId,Integer pageNo,Integer pageSize,Integer priceMax,
					Integer priceMin,String brand,String catId1,String catId2) {
				JDProductSearchDTO dto = new JDProductSearchDTO();
				dto.addBrands(brand);
				dto.setKeyword(keyword);
				dto.setCatId(catId);
				dto.setPageIndex(pageNo);
				dto.setPageSize(pageSize);
				dto.setPriceMax(priceMax);
				dto.setPriceMin(priceMin);
				dto.setCid1(catId1);
				dto.setCid2(catId2);
				return jdProductManage.searchPlatform(dto);
			}
		/**
		 * @return 京东商品首页，包含了所有的一级类目，并根据第一级类目查询第一个类目的数据
		 *//*
		@RequestMapping(value = "/index")
		@ResponseBody
		public JsonResult<String> index(String keyword,String catId,Integer pageNo,Integer pageSize,Integer priceMax,
				Integer priceMin,String brand,String cid1,String cid2) {
			
			
			JDProductSearchDTO dto = new JDProductSearchDTO();
			dto.setCatId(catId);
			return jdProductManage.search(dto);
		}*/
}
	