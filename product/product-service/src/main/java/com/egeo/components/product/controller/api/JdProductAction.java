package com.egeo.components.product.controller.api;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.egeo.components.product.business.JdProductManage;
import com.egeo.components.product.converter.JdProductConverter;
import com.egeo.components.product.dto.JDProductSearchDTO;
import com.egeo.components.product.dto.JdProductDTO;
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
@RequestMapping("/api/product/jdProduct")
public class JdProductAction extends BaseSpringController {
	
	@Resource(name="jdProduct")
	private JdProductManage jdProductManage;


	// 业务方法：
	/*@RequestMapping(value = "/findJdProductById")
	@ResponseBody
	public JsonResult<JdProductVO> findJdProductById(Long id ) {
		
		JdProductDTO dto = new JdProductDTO();
		dto.setId(id);
		JdProductDTO rt = jdProductManage.findJdProductById(dto);		
		return JsonResult.success(JdProductConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findJdProductAll")
	@ResponseBody
	public JsonResult<List<JdProductVO>> findJdProductAll(JdProductVO vo,HttpServletRequest req ) {
		JdProductDTO dto = JdProductConverter.toDTO(vo);
		List<JdProductDTO> rt = jdProductManage.findJdProductAll(dto);	
		return JsonResult.success(JdProductConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findJdProductOfPage")
	@ResponseBody
	public JsonResult<PageResult<JdProductVO>> findJdProductOfPage(JdProductVO vo,Pagination page,HttpServletRequest req ) {
		JdProductDTO dto = JdProductConverter.toDTO(vo);
		PageResult<JdProductDTO> rt = jdProductManage.findJdProductOfPage(dto, page);
        List<JdProductVO> list = JdProductConverter.toVO(rt.getList());
        PageResult<JdProductVO> result = new PageResult<JdProductVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertJdProductWithTx")
	@ResponseBody
	public JsonResult<Long> insertJdProductWithTx(JdProductVO vo,HttpServletRequest req ) {
		JdProductDTO dto = JdProductConverter.toDTO(vo);
		Long rt = jdProductManage.insertJdProductWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateJdProductByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateJdProductByIdWithTx(JdProductVO vo,HttpServletRequest req ) {
		JdProductDTO dto = JdProductConverter.toDTO(vo);
		int rt = jdProductManage.updateJdProductWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteJdProductWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteJdProductWithTx(JdProductVO vo,HttpServletRequest req ) {
		JdProductDTO dto = JdProductConverter.toDTO(vo);
		int rt = jdProductManage.deleteJdProductWithTx(dto);	
		return JsonResult.success(rt);					 
	}*/
	
	/*// 业务方法：
	@RequestMapping(value = "/syncJdProduct")
	@ResponseBody
	public JsonResult<String> syncJdProduct(HttpServletRequest req ) {
		logger.info("开始同步京东商品");
		CacheUser userCache = this.getCacheUser();
		// 获得客户端的ip地址
		String ip = req.getRemoteAddr();
		// 根据ip获取mac地址
		String mac;
		try {
			mac = HostUtils.getLocalMac(ip);
		} catch (Exception e) {
			throw new BusinessException("获取mac地址异常" + e.getMessage());

		}
		long millis = System.currentTimeMillis();
		jdProductManage.syncJdProduct(userCache, ip, mac, req);
		logger.info("------------------------------------------------------------------");
		logger.info("耗时:->"+(System.currentTimeMillis()-millis));
		return JsonResult.success(null);					 
	}*/



	// 业务方法：
	@RequestMapping(value = "/getJdCategory")
	@ResponseBody
	public JsonResult<JSONObject> getJdCategory(HttpServletRequest req,String parentId,Integer catClass) {
		return jdProductManage.getJdCategory(parentId,catClass);

	}

	// 业务方法：
	////	@RequestMapping(value = "/syncJdCategory")
	////@ResponseBody
	public JsonResult<String> syncJdCategory(HttpServletRequest req ) {
		////jdProductManage.syncJdCategory(String.valueOf(0));
		return JsonResult.success(null);					 
	}

	/*
	该接口的更新数据量在80万条sku数据以上
	代码结构有些混乱，由于数据量过大，代码为后期修改的结构
	使用CountDownLatch，是为了保证主线程先完成
	数据分段查询，分段处理是由于数据量过大dubbo调用的响应时间，数据库连接io传输的大小，时间等限制
	 */
	// 业务方法：
	////@RequestMapping(value = "/syncJdProduct")
	////@ResponseBody
	public JsonResult<String> syncJdProduct(HttpServletRequest req ) {
		//先更新类目数
		logger.error("开始同步京东类目数");
		////jdProductManage.syncJdCategory(String.valueOf(0));
		logger.error("开始同步京东商品");
		CacheUser userCache = this.getCacheUser();
		// 获得客户端的ip地址
		String ip = req.getRemoteAddr();
		// 根据ip获取mac地址
		String mac;
		try {
			mac = HostUtils.getLocalMac(ip);
		} catch (Exception e) {
			throw new BusinessException("获取mac地址异常" + e.getMessage());

		}

		////jdProductManage.syncJdProduct(userCache, ip, mac, req);
		//查询所有商品的推送消息,然后删除
		jdProductManage.deleteJdMessage();
		return JsonResult.success(null);
	}

	//开放出来更新京东商品,并创建京东商品
	//手动更新京东商品(更新京东商品信息+校验符合商品创建条件时创建本地商品)
	@RequestMapping("/updateJdProductByJdSkuId")
	@ResponseBody
	public JsonResult<String>  updateJdProductByJdSkuId(String skuIds){
		logger.info("手动更新京东商品");
		if(EmptyUtil.isEmpty(skuIds)){
			return JsonResult.fail("请选择商品");
		}
		List<Long> skuIdList = JSONArray.parseArray(skuIds, Long.class);
		jdProductManage.updateJdProductByJdSkuId(skuIdList);
		return JsonResult.success("ok");
	}


	/*
	同步京东商品池信息
	 */
	// 业务方法：
	//@RequestMapping(value = "/syncJdProductList")
	////@ResponseBody
	public JsonResult<String> syncJdProductList(HttpServletRequest req ) {
		//先更新类目数
		logger.error("开始同步京东类目数");
		////jdProductManage.syncJdCategory(String.valueOf(0));
		logger.error("开始同步京东商品");
		CacheUser userCache = this.getCacheUser();
		// 获得客户端的ip地址
		String ip = req.getRemoteAddr();
		// 根据ip获取mac地址
		String mac;
		try {
			mac = HostUtils.getLocalMac(ip);
		} catch (Exception e) {
			throw new BusinessException("获取mac地址异常" + e.getMessage());

		}

		////jdProductManage.syncJdProductList(userCache, ip, mac, req);
		//查询所有商品的推送消息,然后删除
		jdProductManage.deleteJdMessage();
		return JsonResult.success(null);
	}


	//手动同步京东商品(强制创建成本地商品)
	////@RequestMapping(value = "/syncJdProductByJdSkuId")
	////@ResponseBody
	public JsonResult<String> syncJdProductByJdSkuId(String skuIds){
		logger.info("手动同步商品中");
		if(EmptyUtil.isEmpty(skuIds)){
			return JsonResult.fail("请选择商品");
		}
		List<Long> skuIdList = JSONArray.parseArray(skuIds, Long.class);
		////jdProductManage.syncJdProductByJdSkuId(skuIdList);
		return JsonResult.success("ok");


	}

	//查询京东商品列表
	@RequestMapping(value = "getJdProductListByParams")
	@ResponseBody
	public JsonResult< PageResult<JdProductDTO>> getJdProductListByParams(Long skuId, String skuNameList, Long updateTimeStart, Long updateTimeEnd,
																   Integer profitStart, Integer profitEnd, Integer state, Integer sycStatus, Long catId, Integer isShow, Pagination page){


		List<String> skuName = JSONObject.parseArray(skuNameList,String.class);
		PageResult<JdProductDTO> result=jdProductManage.getJdProductListByParams(skuId,skuName,updateTimeStart,updateTimeEnd,
				profitStart,profitEnd,state,sycStatus,catId,isShow,page);
		return JsonResult.success(result);
	}

	//old
	/*//查询京东商品列表
		@RequestMapping(value = "getJdProductListByParams")
		@ResponseBody
		public JsonResult< PageResult<JdProductDTO>> getJdProductListByParams(Long skuId, String skuNameList, Long updateTimeStart, Long updateTimeEnd,
																	   Integer profitStart, Integer profitEnd, Integer state, Integer sycStatus, Long catId, Integer isShow, Pagination page){


			List<String> skuName = JSONObject.parseArray(skuNameList,String.class);
			PageResult<JdProductDTO> result=jdProductManage.getJdProductListByParams(skuId,skuName,updateTimeStart,updateTimeEnd,
					profitStart,profitEnd,state,sycStatus,catId,isShow,page);
			return JsonResult.success(result);
		}*/
	//查询京东三级类目
	@RequestMapping("getJdCategoryList")
	@ResponseBody
	public JsonResult getJdCategoryList(Integer catClass,Long parentId){
		if(EmptyUtil.isEmpty(catClass)||EmptyUtil.isEmpty(parentId)){
			logger.info("查询类目树:catClass:"+catClass);
			logger.info("查询类目树:parentId:"+parentId);
			return JsonResult.fail("参数缺失");

		}
		return jdProductManage.getJdCategoryList(catClass,parentId);
	}



	//清空京东缓存
	@RequestMapping("deleteRedisJd")
	@ResponseBody
	public void deleteRedisJd(){
		jdProductManage.deleteRedisJd();
	}



	@RequestMapping("testUrl")
	@ResponseBody
	public JsonResult<String> testUrl(){
		StringBuffer str = new StringBuffer();
		int j=1074;
		for(int i=584;i<30161;i++){
			j=j+1;
			str.append("insert into merchant_product_company(id,merchant_product_id,company_id,company_type) values("+j+","+i+",-1,0);");
			j=j+1;
			str.append("insert into merchant_product_company(id,merchant_product_id,company_id,company_type) values("+j+","+i+",-1,1);");

			j=j+1;
			str.append("insert into merchant_product_company(id,merchant_product_id,company_id,company_type) values("+j+","+i+",-1,2);");


		}
		File file = new File("/json2.txt");

		FileWriter writer = null;
		try {
			if(!file.exists()){
				file.createNewFile();
			}
			writer = new FileWriter(file);
			writer.write(JSONObject.toJSONString(str));
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return JsonResult.success("ok");
	}

	@RequestMapping("queryJdProductTaxRate")
	@ResponseBody
	public void queryJdProductTaxRate(){
		String[] ids=new String[]{

		};
		System.out.println("==========start========queryJdProductTaxRate============");
		for (String skuId:ids){
			JdProductDTO jdProductDTO=jdProductManage.fetchJdProductRate(Long.valueOf(skuId));
			if (Objects.nonNull(jdProductDTO)){
				System.out.println(skuId+":"+jdProductDTO.getTaxInfo());
			}
		}
		System.out.println("==========end========queryJdProductTaxRate============");
	}
}
	