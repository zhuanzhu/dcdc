package com.egeo.components.product.controller.api;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.MerchantProdAttValueManage;
import com.egeo.components.product.converter.MerchantProdAttValueConverter;
import com.egeo.components.product.dto.MerchantProdAttValueDTO;
import com.egeo.components.product.vo.MerchantProdAttValueVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/merchantProdAttValue")
public class MerchantProdAttValueAction extends BaseSpringController {
	
	@Resource(name="merchantProdAttValue")
	private MerchantProdAttValueManage merchantProdAttValueManage;

	@Resource
	private JedisUtil jedisUtil;

	//测试方法
	// 业务方法：
	@RequestMapping(value = "/findMerchantProdAttValueById")
	@ResponseBody
	public JsonResult<String> findMerchantProdAttValueById(Long size) {

		List<String> my = new ArrayList<>();
		for(int i=0;i<size;i++){
			StringBuffer stringBuffer = new StringBuffer("属性值得内容,大家注意,这是测试数据哦,英雌比较长,不要管就是的空间不瞌睡地方会计核算的看法");
			stringBuffer.append(i+"");
			my.add(stringBuffer.toString());
		}
		/*jedisUtil.set("myketdata", JSON.toJSONString(my));
		String myketdata = jedisUtil.getString("myketdata");
		List<String> strings = JSONObject.parseArray(myketdata, String.class);*/
		merchantProdAttValueManage.insertList(my);

		return JsonResult.success("ok");

	}



	// 业务方法：
	@RequestMapping(value = "/findMerchantProdAttValueAll")
	@ResponseBody
	public JsonResult<List<MerchantProdAttValueVO>> findMerchantProdAttValueAll(MerchantProdAttValueVO vo,HttpServletRequest req ) {
		MerchantProdAttValueDTO dto = MerchantProdAttValueConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<MerchantProdAttValueDTO> rt = merchantProdAttValueManage.findMerchantProdAttValueAll(dto);	
		return JsonResult.success(MerchantProdAttValueConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findMerchantProdAttValueOfPage")
	@ResponseBody
	public JsonResult<PageResult<MerchantProdAttValueVO>> findMerchantProdAttValueOfPage(MerchantProdAttValueVO vo,Pagination page,HttpServletRequest req ) {
		MerchantProdAttValueDTO dto = MerchantProdAttValueConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<MerchantProdAttValueDTO> rt = merchantProdAttValueManage.findMerchantProdAttValueOfPage(dto, page);
        List<MerchantProdAttValueVO> list = MerchantProdAttValueConverter.toVO(rt.getList());
        PageResult<MerchantProdAttValueVO> result = new PageResult<MerchantProdAttValueVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertMerchantProdAttValueWithTx")
	@ResponseBody
	public JsonResult<Long> insertMerchantProdAttValueWithTx(MerchantProdAttValueVO vo,HttpServletRequest req ) {
		MerchantProdAttValueDTO dto = MerchantProdAttValueConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = merchantProdAttValueManage.insertMerchantProdAttValueWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateMerchantProdAttValueByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateMerchantProdAttValueByIdWithTx(MerchantProdAttValueVO vo,HttpServletRequest req ) {
		MerchantProdAttValueDTO dto = MerchantProdAttValueConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = merchantProdAttValueManage.updateMerchantProdAttValueWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteMerchantProdAttValueWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteMerchantProdAttValueWithTx(MerchantProdAttValueVO vo,HttpServletRequest req ) {
		MerchantProdAttValueDTO dto = MerchantProdAttValueConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = merchantProdAttValueManage.deleteMerchantProdAttValueWithTx(dto);	
		return JsonResult.success(rt);					 
	}


		
}
	