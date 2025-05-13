package com.egeo.components.product.controller.api;


import com.alibaba.fastjson.JSONArray;
import com.egeo.components.order.enums.ThirdConst;
import com.egeo.components.product.business.StandardUnitCardBaseSuManage;
import com.egeo.components.product.converter.StandardUnitCardBaseSuConverter;
import com.egeo.components.product.dto.StandardUnitCardBaseSuDTO;
import com.egeo.components.product.vo.StandardUnitCardBaseSuQueryReqVO;
import com.egeo.components.product.vo.StandardUnitCardBaseSuSaveBatchVO;
import com.egeo.components.user.client.SupplierClient;
import com.egeo.components.user.dto.Enterprise;
import com.egeo.components.utils.ExcelUtil;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.Upload;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;
import com.google.common.collect.Lists;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;


@Controller
@RequestMapping("/api/product/standardUnitCardBaseSu")
public class StandardUnitCardBaseSuAction extends BaseSpringController {

	@Resource(name="standardUnitCardBaseSu")
	private StandardUnitCardBaseSuManage standardUnitCardBaseSuManage;
	@Resource
	private Upload upload;
	@Resource
	private SupplierClient supplierClient;
	// 业务方法：
	@RequestMapping(value = "/findStandardUnitCardBaseSuById")
	@ResponseBody
	public JsonResult<StandardUnitCardBaseSuDTO> findStandardUnitCardBaseSuById(Long id ) {
		StandardUnitCardBaseSuDTO dto = new StandardUnitCardBaseSuDTO();
		dto.setId(id);
		StandardUnitCardBaseSuDTO rt = standardUnitCardBaseSuManage.findStandardUnitCardBaseSuById(dto);
		return JsonResult.success(rt);

	}



	// 业务方法：
	@RequestMapping(value = "/findStandardUnitCardBaseSuAll")
	@ResponseBody
	public JsonResult<List<StandardUnitCardBaseSuDTO>> findStandardUnitCardBaseSuAll(StandardUnitCardBaseSuDTO dto,HttpServletRequest req ) {
		List<StandardUnitCardBaseSuDTO> rt = standardUnitCardBaseSuManage.findStandardUnitCardBaseSuAll(dto);
		return JsonResult.success(rt);

	}

	// 业务方法：
	@RequestMapping(value = "/findStandardUnitCardBaseSuOfPage")
	@ResponseBody
	public JsonResult<PageResult<StandardUnitCardBaseSuDTO>> findStandardUnitCardBaseSuOfPage(StandardUnitCardBaseSuDTO dto,Pagination page,HttpServletRequest req ) {
		PageResult<StandardUnitCardBaseSuDTO> rt = standardUnitCardBaseSuManage.findStandardUnitCardBaseSuOfPage(dto, page);
		return JsonResult.success(rt);

	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertStandardUnitCardBaseSuWithTx")
	@ResponseBody
	public JsonResult<Long> insertStandardUnitCardBaseSuWithTx(StandardUnitCardBaseSuDTO dto,HttpServletRequest req ) {
		Long rt = standardUnitCardBaseSuManage.insertStandardUnitCardBaseSuWithTx(dto);
		return JsonResult.success(rt);
	}

	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateStandardUnitCardBaseSuByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateStandardUnitCardBaseSuByIdWithTx(StandardUnitCardBaseSuDTO dto,HttpServletRequest req ) {
		int rt = standardUnitCardBaseSuManage.updateStandardUnitCardBaseSuWithTx(dto);
		return JsonResult.success(rt);
	}

	/**
	 * 根据卡片和su商品关系id删除关系
	 * @param standardUnitCardBaseSuId
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/deleteStandardUnitCardBaseSuWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteStandardUnitCardBaseSuWithTx(Long standardUnitCardBaseSuId,HttpServletRequest req ) {
		StandardUnitCardBaseSuDTO dto = new StandardUnitCardBaseSuDTO();
		dto.setId(standardUnitCardBaseSuId);
		int rt = standardUnitCardBaseSuManage.deleteStandardUnitCardBaseSuWithTx(dto);
		return JsonResult.success(rt);
	}
	/**
	 * 批量保存卡片和su商品关系
	 * @param vo
	 * @return
	 */
	@RequestMapping(value = "/saveStandardUnitCardBaseSuAll")
	@ResponseBody
	public JsonResult<Boolean> saveStandardUnitCardBaseSuAllWithTx(StandardUnitCardBaseSuSaveBatchVO vo) {
		if(EmptyUtil.isEmpty(vo.getCardBaseId())){
			return JsonResult.fail("卡片id不能为空");
		}
		if(EmptyUtil.isEmpty(vo.getSuDetail())){
			//如果没有选择su直接返回
			return JsonResult.success(true);
		}
		boolean rt = standardUnitCardBaseSuManage.saveStandardUnitCardBaseSuAllWithTx(vo);
		return JsonResult.success(rt);

	}

	/**
	 * 根据卡片id查询su商品列表
	 * @param vo
	 * @return
	 */
	@RequestMapping(value = "/findByCardBaseIdOfPage")
	@ResponseBody
	public JsonResult<PageResult<Map<String, Object>>> findByCardBaseIdOfPage(StandardUnitCardBaseSuQueryReqVO vo,Pagination page ) {
		if(EmptyUtil.isEmpty(vo.getCardBaseId())){
			throw new BusinessException("卡片id不能为空");
		}
		/*PageResult<Map<String, Object>> rt = standardUnitCardBaseSuManage.findByStandardUnitCombinationIdOfPage(standardUnitCombinationId,keyWord, page,false);
		return JsonResult.success(rt);*/
		PageResult<Map<String, Object>> rt = new PageResult<>();
		return JsonResult.success(rt);
	}

	/**
	 * 导出卡片和商品关联明细信息
	 * @param vo
	 * @return
	 */
	@RequestMapping(value = "/exportCardBaseSuById")
	@ResponseBody
	public JsonResult<Map<String, Object>> exportCardBaseSuById(StandardUnitCardBaseSuQueryReqVO vo){
		if(EmptyUtil.isEmpty(vo.getCardBaseId())){
			throw new BusinessException("su商品组合id不能为空");
		}
		/*List<Map<String, Object>> exportVos=new ArrayList<>();
		Pagination page=new Pagination(1,1000);
		PageResult<Map<String, Object>> pageResult = StandardUnitCardBaseSuManage.findByStandardUnitCombinationIdOfPage(standardUnitCombinationId,keyWord, page,false);
		while (EmptyUtil.isNotEmpty(pageResult.getList())){
			exportVos.addAll(pageResult.getList());
			if (pageResult.getList().size()<page.getPageSize()){
				break;
			}
			page.setPageNo(page.getPageNo()+1);
			pageResult = StandardUnitCardBaseSuManage.findByStandardUnitCombinationIdOfPage(standardUnitCombinationId,keyWord, page,false);
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
		return JsonResult.success("url", excelUrl);*/
		return JsonResult.success("url", null);
	}
	/**
	 * 根据卡片与su关系id修改排序
	 * @param standardUnitCardBaseSuId
	 * @param sortValue
	 * @return
	 */
	@RequestMapping(value = "/sortValueByStandardUnitCardBaseIdWithTx")
	@ResponseBody
	public JsonResult<Boolean> sortValueByStandardUnitCardBaseIdWithTx(Long standardUnitCardBaseSuId,Integer sortValue ) {
		if(EmptyUtil.isEmpty(standardUnitCardBaseSuId)){
			throw new BusinessException("su商品组合与su关系id不能为空");
		}
		boolean rt = standardUnitCardBaseSuManage.sortValueByStandardUnitCardBaseSuIdWithTx(standardUnitCardBaseSuId, sortValue);
		return JsonResult.success(rt);
	}


}
