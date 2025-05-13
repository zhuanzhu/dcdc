package com.egeo.components.product.controller.api;


import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.egeo.components.order.enums.ThirdConst;
import com.egeo.components.user.client.SupplierClient;
import com.egeo.components.user.dto.Enterprise;
import com.egeo.components.utils.ExcelUtil;
import com.egeo.utils.Upload;
import com.google.common.collect.Lists;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.egeo.components.product.business.StandardUnitCombinationSuManage;
import com.egeo.components.product.converter.StandardUnitCombinationSuConverter;
import com.egeo.components.product.dto.StandardUnitCombinationSuDTO;
import com.egeo.components.product.vo.StandardUnitCombinationSuVO;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/standardUnitCombinationSu")
public class StandardUnitCombinationSuAction extends BaseSpringController {

	@Resource(name="standardUnitCombinationSu")
	private StandardUnitCombinationSuManage standardUnitCombinationSuManage;
	@Resource
	private Upload upload;
	@Resource
	private SupplierClient supplierClient;
	// 业务方法：
	@RequestMapping(value = "/findStandardUnitCombinationSuById")
	@ResponseBody
	public JsonResult<StandardUnitCombinationSuVO> findStandardUnitCombinationSuById(Long id ) {

		StandardUnitCombinationSuDTO dto = new StandardUnitCombinationSuDTO();
		dto.setId(id);
		StandardUnitCombinationSuDTO rt = standardUnitCombinationSuManage.findStandardUnitCombinationSuById(dto);
		return JsonResult.success(StandardUnitCombinationSuConverter.toVO(rt));

	}



	// 业务方法：
	@RequestMapping(value = "/findStandardUnitCombinationSuAll")
	@ResponseBody
	public JsonResult<List<StandardUnitCombinationSuVO>> findStandardUnitCombinationSuAll(StandardUnitCombinationSuVO vo,HttpServletRequest req ) {
		StandardUnitCombinationSuDTO dto = StandardUnitCombinationSuConverter.toDTO(vo);
		List<StandardUnitCombinationSuDTO> rt = standardUnitCombinationSuManage.findStandardUnitCombinationSuAll(dto);
		return JsonResult.success(StandardUnitCombinationSuConverter.toVO(rt));

	}

	// 业务方法：
	@RequestMapping(value = "/findStandardUnitCombinationSuOfPage")
	@ResponseBody
	public JsonResult<PageResult<StandardUnitCombinationSuVO>> findStandardUnitCombinationSuOfPage(StandardUnitCombinationSuVO vo,Pagination page,HttpServletRequest req ) {
		StandardUnitCombinationSuDTO dto = StandardUnitCombinationSuConverter.toDTO(vo);
		PageResult<StandardUnitCombinationSuDTO> rt = standardUnitCombinationSuManage.findStandardUnitCombinationSuOfPage(dto, page);
        List<StandardUnitCombinationSuVO> list = StandardUnitCombinationSuConverter.toVO(rt.getList());
        PageResult<StandardUnitCombinationSuVO> result = new PageResult<StandardUnitCombinationSuVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());

		return JsonResult.success(result);

	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertStandardUnitCombinationSuWithTx")
	@ResponseBody
	public JsonResult<Long> insertStandardUnitCombinationSuWithTx(StandardUnitCombinationSuVO vo,HttpServletRequest req ) {
		StandardUnitCombinationSuDTO dto = StandardUnitCombinationSuConverter.toDTO(vo);
		Long rt = standardUnitCombinationSuManage.insertStandardUnitCombinationSuWithTx(dto);
		return JsonResult.success(rt);
	}

	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateStandardUnitCombinationSuByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateStandardUnitCombinationSuByIdWithTx(StandardUnitCombinationSuVO vo,HttpServletRequest req ) {
		StandardUnitCombinationSuDTO dto = StandardUnitCombinationSuConverter.toDTO(vo);
		int rt = standardUnitCombinationSuManage.updateStandardUnitCombinationSuWithTx(dto);
		return JsonResult.success(rt);
	}

	/**
	 * 根据su组合和su商品关系id删除关系
	 * @param standardUnitCombinationSuId
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/deleteStandardUnitCombinationSuWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteStandardUnitCombinationSuWithTx(Long standardUnitCombinationSuId,HttpServletRequest req ) {
		StandardUnitCombinationSuVO vo = new StandardUnitCombinationSuVO();
		vo.setId(standardUnitCombinationSuId);
		StandardUnitCombinationSuDTO dto = StandardUnitCombinationSuConverter.toDTO(vo);
		int rt = standardUnitCombinationSuManage.deleteStandardUnitCombinationSuWithTx(dto);
		return JsonResult.success(rt);
	}
	/**
	 * 批量保存su组合和su商品关系
	 * @param standardUnitCombinationId
	 * @param standardUnitList
	 * @return
	 */
	@RequestMapping(value = "/saveStandardUnitCombinationSuAll")
	@ResponseBody
	public JsonResult<Boolean> saveStandardUnitCombinationSuAllWithTx(Long standardUnitCombinationId,String standardUnitList ) {
		if(EmptyUtil.isEmpty(standardUnitCombinationId)){
			return JsonResult.fail("su商品组合id不能为空");
		}
		if(EmptyUtil.isEmpty(standardUnitList)){
			//如果没有选择su直接返回
			return JsonResult.success(true);
		}
		List<Long> standardUnits = new ArrayList<Long>(JSONArray.parseArray(standardUnitList,Long.class));
		if(EmptyUtil.isEmpty(standardUnits)){
			//如果没有选择su直接返回
			return JsonResult.success(true);
		}
		boolean rt = standardUnitCombinationSuManage.saveStandardUnitCombinationSuAllWithTx(standardUnitCombinationId,standardUnits,null,null);
		return JsonResult.success(rt);

	}

	@RequestMapping(value = "/addThirdStandardUnitCombinationSu")
	@ResponseBody
	public JsonResult<Boolean> saveThirdStandardUnitCombinationSuAll(Long standardUnitCombinationId,Integer source,String standardUnitList) {
		if(EmptyUtil.isEmpty(standardUnitCombinationId)){
			return JsonResult.fail("su商品组合id不能为空");
		}
		if(EmptyUtil.isEmpty(standardUnitList)){
			//如果没有选择su直接返回
			return JsonResult.success(true);
		}
		List<Long> standardUnits = null;
		List<Map<String,String>> productAndSkuMapList = new ArrayList<>();
		if(source != null && (source.intValue() ==4 || source.intValue() ==5)){
			convertProductIdSkuMapList(standardUnitList,productAndSkuMapList);
		}else{
			standardUnits = new ArrayList<Long>(JSONArray.parseArray(standardUnitList,Long.class));
		}
		if(EmptyUtil.isEmpty(standardUnits) && EmptyUtil.isEmpty(productAndSkuMapList)){
			//如果没有选择su直接返回
			return JsonResult.success(true);
		}
		boolean rt = standardUnitCombinationSuManage.saveStandardUnitCombinationSuAllWithTx(standardUnitCombinationId,standardUnits,source,productAndSkuMapList);
		if(!rt) {
			return JsonResult.fail("新增失败-请检查是否已存在");
		}
		return JsonResult.success(rt);

	}

	private void convertProductIdSkuMapList(String standardUnitList,List<Map<String,String>> productAndSkuMap) {
		List<String> productSkuIdList = new ArrayList<>(JSONArray.parseArray(standardUnitList,String.class));
		if(EmptyUtil.isEmpty(productSkuIdList)){
			return;
		}
		for (String productSkuId : productSkuIdList) {
			String productSkuIdArr[] = productSkuId.split("-");
			Map<String,String> map = new HashMap<>();
			map.put("productId",productSkuIdArr[0]);
			map.put("thirdSkuId",productSkuIdArr[1]);
			productAndSkuMap.add(map);
		}
	}

	/**
	 * 根据su组合id查询su商品列表
	 * @param standardUnitCombinationId
	 * @return
	 */
	@RequestMapping(value = "/findByStandardUnitCombinationIdOfPage")
	@ResponseBody
	public JsonResult<PageResult<Map<String, Object>>> findByStandardUnitCombinationIdOfPage(Long standardUnitCombinationId,String keyWord,Pagination page ) {
		if(EmptyUtil.isEmpty(standardUnitCombinationId)){
			throw new BusinessException("su商品组合id不能为空");
		}
		PageResult<Map<String, Object>> rt = standardUnitCombinationSuManage.findByStandardUnitCombinationIdOfPage(standardUnitCombinationId,keyWord, page,false);
		return JsonResult.success(rt);
	}

	/**
	 * 导出商品组合明细信息
	 * @param standardUnitCombinationId
	 * @return
	 */
    @RequestMapping(value = "/exportStandardUnitCombinationSuById")
    @ResponseBody
    public JsonResult<Map<String, Object>> exportStandardUnitCombinationSuById(Long standardUnitCombinationId,String keyWord){
        if(EmptyUtil.isEmpty(standardUnitCombinationId)){
            throw new BusinessException("su商品组合id不能为空");
        }
        List<Map<String, Object>> exportVos=new ArrayList<>();
        Pagination page=new Pagination(1,1000);
        PageResult<Map<String, Object>> pageResult = standardUnitCombinationSuManage.findByStandardUnitCombinationIdOfPage(standardUnitCombinationId,keyWord, page,false);
        while (EmptyUtil.isNotEmpty(pageResult.getList())){
            exportVos.addAll(pageResult.getList());
            if (pageResult.getList().size()<page.getPageSize()){
                break;
            }
            page.setPageNo(page.getPageNo()+1);
            pageResult = standardUnitCombinationSuManage.findByStandardUnitCombinationIdOfPage(standardUnitCombinationId,keyWord, page,false);
        }
		Map<Long,String> supplierNameMap=new HashMap<>();
		if (EmptyUtil.isNotEmpty(exportVos)){
			Set<Long> supplierIds=new HashSet<>();
			exportVos.forEach(item->{
				if (Objects.equals(1,item.get("source")) &&
						Objects.nonNull(item.get("supplierId"))){
					supplierIds.add((Long) item.get("supplierId"));
				}
			});
			if (EmptyUtil.isNotEmpty(supplierIds)){
				List<Enterprise> supplierDTOS=supplierClient.findByIds(Lists.newArrayList(supplierIds));
				if (EmptyUtil.isNotEmpty(supplierDTOS)){
					supplierDTOS.forEach(item->supplierNameMap.put(item.getId(),item.getName()));
				}
			}
		}
		String[] columnsName=new String[]{"商品ID","商品名称","供应商","供货价","销售价","毛利","毛利率(%)","可售状态","商品排序","组合名称"};
		String excelUrl=ExcelUtil.writeDataInExcel("商品组合", columnsName, exportVos, new ExcelUtil.DoExcel<Map<String, Object>>() {
			@Override
			public void setColumnValue(Row row, Map<String, Object> map) {
				int columnIdx=0;
				row.createCell(columnIdx++).setCellValue(Objects.nonNull(map.get("standardUnitId"))?String.valueOf(map.get("standardUnitId")):null);
				row.createCell(columnIdx++).setCellValue((String) map.get("StandardUnitName"));
				Integer source=(Integer) map.get("source");
				String supplierName=null;
				if (Objects.equals(1,source)){
					if (Objects.nonNull(map.get("supplierId"))){
						supplierName=supplierNameMap.get((Long) map.get("supplierId"));
					}
				} else if (Objects.equals(ThirdConst.Source.JD,source)) {
					supplierName="京东";
				}else if(Objects.equals(ThirdConst.Source.CAKE,source)){
					supplierName="蛋糕叔叔";
				}else if(Objects.equals(ThirdConst.Source.WORLD,source)){
					supplierName="全球购";
				}
				row.createCell(columnIdx++).setCellValue(supplierName);
				row.createCell(columnIdx++).setCellValue(Objects.nonNull(map.get("supplierPrice"))?String.valueOf(map.get("supplierPrice")):null);
				row.createCell(columnIdx++).setCellValue(Objects.nonNull(map.get("suSalePrice"))?String.valueOf(map.get("suSalePrice")):null);
				row.createCell(columnIdx++).setCellValue(Objects.nonNull(map.get("grossProfit"))?String.valueOf(map.get("grossProfit")):null);
				row.createCell(columnIdx++).setCellValue(Objects.nonNull(map.get("profit"))?String.valueOf(map.get("profit")):null);
				row.createCell(columnIdx++).setCellValue(Objects.equals(1,map.get("sellState"))?"可售":"不可售");
				row.createCell(columnIdx++).setCellValue(Objects.nonNull(map.get("sortValue"))?String.valueOf(map.get("sortValue")):null);
				row.createCell(columnIdx++).setCellValue((String) map.get("standardUnitCombinationName"));
			}
		},upload);
		return JsonResult.success("url", excelUrl);
    }
	/**
	 * 根据su组合与su关系id修改排序
	 * @param standardUnitCombinationSuId
	 * @param sortValue
	 * @return
	 */
	@RequestMapping(value = "/sortValueByStandardUnitCombinationId")
	@ResponseBody
	public JsonResult<Boolean> sortValueByStandardUnitCombinationIdWithTx(Long standardUnitCombinationSuId,Integer sortValue ) {
		if(EmptyUtil.isEmpty(standardUnitCombinationSuId)){
			throw new BusinessException("su商品组合与su关系id不能为空");
		}
		boolean rt = standardUnitCombinationSuManage.sortValueByStandardUnitCombinationIdWithTx(standardUnitCombinationSuId, sortValue);
		return JsonResult.success(rt);
	}


}
