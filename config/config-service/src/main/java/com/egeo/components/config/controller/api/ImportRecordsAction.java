package com.egeo.components.config.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.config.business.ImportRecordsManage;
import com.egeo.components.config.converter.ImportRecordsConverter;
import com.egeo.components.config.dto.ImportRecordsDTO;
import com.egeo.components.config.vo.ImportRecordsVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/config/importRecords")
public class ImportRecordsAction extends BaseSpringController {
	
	@Resource(name="importRecords")
	private ImportRecordsManage importRecordsManage;


	// 业务方法：
	@RequestMapping(value = "/findImportRecordsById")
	@ResponseBody
	public JsonResult<ImportRecordsVO> findImportRecordsById(Long id ) {
		
		ImportRecordsDTO dto = new ImportRecordsDTO();
		dto.setId(id);
		ImportRecordsDTO rt = importRecordsManage.findImportRecordsById(dto);		
		return success(ImportRecordsConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findImportRecordsAll")
	@ResponseBody
	public JsonResult<List<ImportRecordsVO>> findImportRecordsAll(ImportRecordsVO vo,HttpServletRequest req ) {
		ImportRecordsDTO dto = ImportRecordsConverter.toDTO(vo);
		List<ImportRecordsDTO> rt = importRecordsManage.findImportRecordsAll(dto);	
		return success(ImportRecordsConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findImportRecordsOfPage")
	@ResponseBody
	public JsonResult<PageResult<ImportRecordsVO>> findImportRecordsOfPage(ImportRecordsVO vo,Pagination page,HttpServletRequest req ) {
		ImportRecordsDTO dto = ImportRecordsConverter.toDTO(vo);
		PageResult<ImportRecordsDTO> rt = importRecordsManage.findImportRecordsOfPage(dto, page);
        List<ImportRecordsVO> list = ImportRecordsConverter.toVO(rt.getList());
        PageResult<ImportRecordsVO> result = new PageResult<ImportRecordsVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertImportRecordsWithTx")
	@ResponseBody
	public JsonResult<Long> insertImportRecordsWithTx(ImportRecordsVO vo,HttpServletRequest req ) {
		ImportRecordsDTO dto = ImportRecordsConverter.toDTO(vo);
		Long rt = importRecordsManage.insertImportRecordsWithTx(dto);	
		return success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateImportRecordsByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateImportRecordsByIdWithTx(ImportRecordsVO vo,HttpServletRequest req ) {
		ImportRecordsDTO dto = ImportRecordsConverter.toDTO(vo);
		int rt = importRecordsManage.updateImportRecordsWithTx(dto);	
		return success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteImportRecordsWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteImportRecordsWithTx(ImportRecordsVO vo,HttpServletRequest req ) {
		ImportRecordsDTO dto = ImportRecordsConverter.toDTO(vo);
		int rt = importRecordsManage.deleteImportRecordsWithTx(dto);	
		return success(rt);					 
	}
		
}
	