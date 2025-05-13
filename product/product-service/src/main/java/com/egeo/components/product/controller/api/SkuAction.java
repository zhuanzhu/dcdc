package com.egeo.components.product.controller.api;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.egeo.components.product.business.SkuManage;
import com.egeo.components.product.converter.SkuConverter;
import com.egeo.components.product.dto.SkuDTO;
import com.egeo.components.product.vo.SkuVO;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.excel.ExcelExportSXXSSF;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/sku")
public class SkuAction extends BaseSpringController {
	
	@Resource(name="sku")
	private SkuManage skuManage;


	// 业务方法：
	@RequestMapping(value = "/findSkuById")
	@ResponseBody
	public JsonResult<SkuVO> findSkuById(Long id ) {
		
		SkuDTO dto = new SkuDTO();
		dto.setId(id);
		SkuDTO rt = skuManage.findSkuById(dto);		
		return JsonResult.success(SkuConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findSkuAll")
	@ResponseBody
	public JsonResult<List<SkuVO>> findSkuAll(SkuVO vo,HttpServletRequest req ) {
		SkuDTO dto = SkuConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<SkuDTO> rt = skuManage.findSkuAll(dto);	
		return JsonResult.success(SkuConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findSkuOfPage")
	@ResponseBody
	public JsonResult<PageResult<SkuVO>> findSkuOfPage(SkuVO vo,Pagination page,HttpServletRequest req ) {
		SkuDTO dto = SkuConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<SkuDTO> rt = skuManage.findSkuOfPage(dto, page);
        List<SkuVO> list = SkuConverter.toVO(rt.getList());
        PageResult<SkuVO> result = new PageResult<SkuVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}
	
	/**
	 * 分页查询所有电子卡券sku(已修改原有的查询逻辑,现逻辑:是否存在unit库存为是,或者第三方对接参数为券仓且有unit分配的商品)
	 * @param vo
	 * @param page
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/findSkuECardOfPage")
	@ResponseBody
	public JsonResult<PageResult<Map<String, Object>>> findSkuECardOfPage(SkuVO vo,Pagination page,HttpServletRequest req ) {
		logger.info("分页查询所有电子卡券sku");
		SkuDTO dto = SkuConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<Map<String, Object>> rt = skuManage.findSkuECardOfPage(dto, page);
		
		return JsonResult.success(rt);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertSkuWithTx")
	@ResponseBody
	public JsonResult<Long> insertSkuWithTx(SkuVO vo,HttpServletRequest req ) {
		SkuDTO dto = SkuConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = skuManage.insertSkuWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateSkuByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateSkuByIdWithTx(SkuVO vo,HttpServletRequest req ) {
		SkuDTO dto = SkuConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = skuManage.updateSkuWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteSkuWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteSkuWithTx(SkuVO vo,HttpServletRequest req ) {
		SkuDTO dto = SkuConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = skuManage.deleteSkuWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	/**
	 * 批量启用停用
	 * @param ids
	 * @param type
	 * @return
	 */
	@RequestMapping(value = "/isAvailableWithTx")
	@ResponseBody
	public JsonResult<Integer> isAvailableWithTx(String ids,int isAvailable) {
		if(EmptyUtil.isEmpty(isAvailable)){
			throw new BusinessException("批量启用停用状态不能为空");
		}
		List<Long> id = new ArrayList<>();
		List<String> idList = new ArrayList<String>(JSONArray.parseArray(ids, String.class));
		for (String string : idList) {
			id.add(Long.valueOf(string));
		}
		int rt = skuManage.isAvailableWithTx(id,isAvailable);
		return JsonResult.success(rt);					 
	}
	
	/**
	 * 批量导出
	 * @param ids
	 * @param type
	 * @return
	 */
	@RequestMapping(value = "/excelExport")
	@ResponseBody
	public JsonResult<String> excelExport(SkuVO vo,HttpServletRequest req ,HttpServletResponse response) {
		JsonResult<String> result = new JsonResult<>();
		SkuDTO dto = SkuConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<SkuDTO> list = skuManage.findSkuAll(dto);
		/**            导出文件存放物理路径
		 * @param fileWebPath
		 *            导出文件web下载路径
		 * @param filePrefix
		 *            导出文件名的前缀          
		 * @param flushRows
		 *            存放在内存的数据量
		 * @param fieldNames
		 *            导出文件列标题
		 * @param fieldCodes
		 * 			  导出数据对象的字段名称     
		 * @param flushRows*/
		//导出文件的前缀
		String filePrefix="egeo";
		//-1表示关闭自动刷新，手动控制写磁盘的时机，其它数据表示多少数据在内存保存，超过的则写入磁盘
		int flushRows=100;
		
		//指导导出数据的title
		List<String> fieldNames=new ArrayList<String>();
		fieldNames.add("最后同步时间");
		fieldNames.add("最后编辑时间");
		fieldNames.add("sku编码");
		fieldNames.add("产品名称");
		fieldNames.add("sku名称");
		fieldNames.add("规格码");
		fieldNames.add("启用/停用状态");
		fieldNames.add("有效性状态");
		fieldNames.add("图片");
		
		//告诉导出类数据list中对象的属性，让ExcelExportSXXSSF通过反射获取对象的值
		List<String> fieldCodes=new ArrayList<String>();
		fieldCodes.add("synchronizationString");//最后同步时间
		fieldCodes.add("updateString");//最后编辑时间
		fieldCodes.add("skuSerialNumber");//sku编码
		fieldCodes.add("skuProductName");//产品名称
		fieldCodes.add("skuName");//sku名称
		fieldCodes.add("code");//规格码
		fieldCodes.add("isAvailableString");//启用/停用状态
		fieldCodes.add("isValidString");//有效性状态
		fieldCodes.add("skuPicUrl");//图片
		
		
		//注意：fieldCodes和fieldNames个数必须相同且属性和title顺序一一对应，这样title和内容才一一对应
		
		
		try {
			//开始导出，执行一些workbook及sheet等对象的初始创建
			ExcelExportSXXSSF excelExportSXXSSF = ExcelExportSXXSSF.start("./", "./", filePrefix, fieldNames, fieldCodes, flushRows);
			
			//执行导出
			excelExportSXXSSF.writeDatasByObject(list);
			//输出文件
			excelExportSXXSSF.exportFile(response);
	}catch (Exception e) {
		throw new BusinessException("数据导出异常");
	}
	result.setData("数据导出成功");
	return result;
		
	}

	//查询所有的会籍关联的sku商品
	@RequestMapping("/getMembershipSkuVO")
	@ResponseBody
	public JsonResult<List<SkuVO>> getMembershipSkuVO(HttpServletRequest req){
		//校验platformId
		String str = req.getHeader("platformId");

		if(EmptyUtil.isEmpty(str)){
			fail("platformId不能为null");

		}
		Long platformId=Long.valueOf(str);

		return skuManage.getMembershipSku(platformId);

	}

	/**
	 * 手动输入外部skuid或者条形码
	 * @param skuId
	 * @param externalSkuId
	 * @return
	 */
	@RequestMapping("/updateSkuParamsBackStage")
	@ResponseBody
	public JsonResult<String> updateSkuParamsBackStage(Long skuId,String externalSkuId,String barCode){
		logger.info("手动输入外部skuid或者条形码接口入口");
		logger.info("skuId:"+skuId);
		logger.info("externalSkuId:"+externalSkuId);
		logger.info("barCode:"+barCode);
		if(EmptyUtil.isEmpty(skuId)){
			return JsonResult.fail("未传递skuId");
		}
		skuManage.updateExternalSkuIdBackStage(skuId,externalSkuId,barCode);
		return JsonResult.success("保存成功");
	}

}
	