package com.egeo.components.user.controller.api;


import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.egeo.components.user.vo.DepartmentVO;
import com.egeo.config.RuntimeContext;
import com.egeo.components.user.business.CompanyManage;
import com.egeo.components.user.business.DepartmentManage;
import com.egeo.components.user.business.DepartmentTempManage;
import com.egeo.components.user.converter.DepartmentConverter;
import com.egeo.components.user.dto.DepartmentDTO;
import com.egeo.orm.PageResult;
import com.egeo.entity.CacheUser;
import com.egeo.exception.BusinessException;
import com.egeo.orm.Pagination;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;
import com.egeo.utils.excel2.ExcelUtil;
import com.egeo.utils.io.OnLineDownload;
import com.egeo.utils.str.StringUtils;



@Controller
@RequestMapping("/api/user/department")
public class DepartmentAction extends BaseSpringController {
	
	@Resource(name="department")
	private DepartmentManage departmentManage;
	
	@Resource(name="departmentTemp")
	private DepartmentTempManage departmentTempManage;
	
	@Resource(name="company")
	private CompanyManage companyManage;
	


	
	/**
	 * 获取组织架构导入模板
	 */
	@RequestMapping(value = "departmentTemplate")
	@ResponseBody
	public void departmentTemplate(HttpServletRequest req,HttpServletResponse response) {
		try {
			//获取目标文件的绝对路径  
	        String fullFileName = req.getSession().getServletContext().getRealPath("/departmentTemplate.xls");  
			OnLineDownload.downLoad(fullFileName, response, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 添加机构信息
	 * @param vo
	 * @param req
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/insertDepartmentWithTx")
	@ResponseBody
	public JsonResult<Map<String, Object>> insertDepartmentWithTx(Long companyId,HttpServletRequest req) {
		logger.info("上传机构信息");
    	if(companyId==null){
    		return JsonResult.fail("请选择公司！");
    	}
    	
		String platformId_=req.getHeader("platformId");
		if(com.egeo.utils.StringUtils.isEmpty(platformId_))
			return JsonResult.fail("platformId为空");
		Long platformId=Long.parseLong(platformId_);
    	
		// 从请求体中获取文件
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) req;
		Iterator<String> iter = multiRequest.getFileNames();
		MultipartFile file = multiRequest.getFile(iter.next());
		if (file == null)
			return JsonResult.fail("未发现Excel文件");
		
    	String originalFilename = file.getOriginalFilename();
		
		// 获取文件后缀
		String suffix = "";
	
			try {
				suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		if(! StringUtils.equals(suffix, ".xls") && ! StringUtils.equals(suffix, ".xlsx")){
		return JsonResult.fail("导入的文件类型错误，请选择后重新选择文件导入");
			};
		
		InputStream inputStream=null;
		try {
			inputStream = file.getInputStream();
		} catch (IOException e2) {
			e2.printStackTrace();
			return JsonResult.fail("Excel文件读取发生异常");
		}
		
		List<Map<String, Object>> valueList = ExcelUtil.readExcelData(0, 0, inputStream);
		
		
		return  departmentManage.insertDepartmentWithTx(companyId,platformId,valueList);
		

	}
	@RequestMapping(value = "/insertDepartmentOfCompanyWithTx")
	@ResponseBody
	public JsonResult<Map<String, Object>> insertDepartmentOfCompanyWithTx(HttpServletRequest req) {
		logger.info("上传机构信息");
    	if(!RuntimeContext.cacheUser().isCompanyUser()) {
    		return JsonResult.fail("权限异常");
    	}
    	Long companyId = RuntimeContext.cacheUser().getCompanyId();
		String platformId_=req.getHeader("platformId");
		if(com.egeo.utils.StringUtils.isEmpty(platformId_))
			return JsonResult.fail("platformId为空");
		Long platformId=Long.parseLong(platformId_);
    	
		// 从请求体中获取文件
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) req;
		Iterator<String> iter = multiRequest.getFileNames();
		MultipartFile file = multiRequest.getFile(iter.next());
		if (file == null)
			return JsonResult.fail("未发现Excel文件");
		
    	String originalFilename = file.getOriginalFilename();
		
		// 获取文件后缀
		String suffix = "";
	
			try {
				suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		if(! StringUtils.equals(suffix, ".xls") && ! StringUtils.equals(suffix, ".xlsx")){
		return JsonResult.fail("导入的文件类型错误，请选择后重新选择文件导入");
			};
		
		InputStream inputStream=null;
		try {
			inputStream = file.getInputStream();
		} catch (IOException e2) {
			e2.printStackTrace();
			return JsonResult.fail("Excel文件读取发生异常");
		}
		
		List<Map<String, Object>> valueList = ExcelUtil.readExcelData(0, 0, inputStream);
		
		
		return  departmentManage.insertDepartmentWithTx(companyId,platformId,valueList);
		

	}
	
	//确认导入组织架构
	@RequestMapping(value = "/assureImportDepartmentAll")
	@ResponseBody
	public JsonResult<String> assureImportDepartmentAll(String companyIdStr ,HttpServletRequest req ,String departmentTempListIdStr,String importRecordId) {
		logger.info("确认导入组织架构");
		Long companyId = -1l;
		DepartmentDTO departmentDTO = new DepartmentDTO();	
		if(companyIdStr==null || companyIdStr.length()<1) {
			if(!RuntimeContext.cacheUser().isCompanyUser()) {
	    		return JsonResult.fail("权限异常");
	    	}
			companyId = RuntimeContext.cacheUser().getCompanyId();
		}else {
			companyId = Long.valueOf(companyIdStr);
		}
		
    	
		
		
		String str = req.getHeader("platformId");
		if(com.egeo.utils.StringUtils.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			departmentDTO.setPlatformId(platformId);
		}
		
		Long parseLong =null;
		if(companyId==null){
			return JsonResult.fail("公司不能为空");
		}
		if(importRecordId==null){
			return JsonResult.fail("导入记录信息为空");
		}else{
			try {
				parseLong = Long.parseLong(importRecordId);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		
		JsonResult<String>  dataStr=departmentManage.assureImportDepartmentAll(companyId,parseLong,departmentTempListIdStr,importRecordId);
		return dataStr;
	}

	//确认导入组织架构
	@RequestMapping(value = "/assureImportDepartmentAllOfCompany")
	@ResponseBody
	public JsonResult<String> assureImportDepartmentAllOfCompany(HttpServletRequest req ,String departmentTempListIdStr,String importRecordId) {
		logger.info("确认导入组织架构");
		DepartmentDTO departmentDTO = new DepartmentDTO();	

    	if(!RuntimeContext.cacheUser().isCompanyUser()) {
    		return JsonResult.fail("权限异常");
    	}
    	Long companyId = RuntimeContext.cacheUser().getCompanyId();
		String str = req.getHeader("platformId");
		if(com.egeo.utils.StringUtils.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			departmentDTO.setPlatformId(platformId);
		}
		
		Long parseLong =null;
		if(companyId==null){
			return JsonResult.fail("公司不能为空");
		}
		if(importRecordId==null){
			return JsonResult.fail("导入记录信息为空");
		}else{
			try {
				parseLong = Long.parseLong(importRecordId);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		
		JsonResult<String>  dataStr=departmentManage.assureImportDepartmentAll(companyId,parseLong,departmentTempListIdStr,importRecordId);
		return dataStr;
	}
	/**
	 * 导出组织架构
	 * @param companyId
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/outImportDepartment")
	@ResponseBody
	public JsonResult<String> outImportDepartment(Long companyId ,HttpServletRequest req) {
		logger.info("导出组织架构");
		if(RuntimeContext.cacheUser()!=null && RuntimeContext.cacheUser().getType()!=null &&RuntimeContext.cacheUser().getType().intValue()==3) {
			companyId = RuntimeContext.cacheUser().getCompanyId();
		}
		DepartmentDTO departmentDTO = new DepartmentDTO();	
		if(companyId==null){
			return JsonResult.fail("公司不能为空");
		}
		String str = req.getHeader("platformId");
		if(com.egeo.utils.StringUtils.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			departmentDTO.setPlatformId(platformId);
		}
		departmentDTO.setCompanyId(companyId);
		
		JsonResult<String>  dataStr=departmentManage.outImportDepartment(departmentDTO);
		return dataStr;
	}
	

	/**
	 * 根据公司id递归查询所有部门信息
	 * @param vo
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/recursionDepartmentAll")
	@ResponseBody
	public JsonResult<List<DepartmentVO>> recursionDepartmentAll(DepartmentVO vo,HttpServletRequest req ) {
		logger.info("根据公司id递归查询所有部门信息");
		DepartmentDTO dto = DepartmentConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(com.egeo.utils.StringUtils.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		if(com.egeo.utils.StringUtils.isEmpty(vo.getCompanyId())){
			throw new BusinessException("公司编号不能为空");
		}
		List<DepartmentVO> rt = departmentManage.recursionDepartmentAll(dto);	
		return JsonResult.success(rt);
	}
	
	/**
	 * 根据公司id机构id查询子部门信息
	 * @param vo
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/departmentBydepartmentId")
	@ResponseBody
	public JsonResult<List<Map<String, Object>>> departmentBydepartmentId(Long companyId,Long departmentId,HttpServletRequest req ) {
		logger.info("根据公司id机构id查询子部门信息");
		String str = req.getHeader("platformId");	
		Long platformId = null;
		if(com.egeo.utils.StringUtils.isNotEmpty(str)){
			platformId = Long.valueOf(str);
		}
		if(com.egeo.utils.StringUtils.isEmpty(companyId)){
			throw new BusinessException("公司编号不能为空");
		}
		if(com.egeo.utils.StringUtils.isEmpty(departmentId)){
			departmentId = 0L;
		}
		List<Map<String, Object>> rt = departmentManage.departmentBydepartmentId(companyId,departmentId,platformId);	
		return JsonResult.success(rt);
	}	
	
	/**
	 * 公司一级部门列表(客户端给你点赞模块使用)
	 * @param companyId
	 * @return
	 */
	@RequestMapping(value = "/superDepList")
	@ResponseBody
	public JsonResult<Map<String,Object>> superDepList(HttpServletRequest req){
		CacheUser userCache = this.getCacheUser();
		Long companyId=userCache.getCompanyId();
		if(companyId==null)
			return JsonResult.fail("公司信息有误");
		return departmentManage.superDepList(companyId);
	}
	
	/**
	 * 查询部门的下级部门和本部门直属员工列表
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/depMembersAndSubDeps")
	@ResponseBody
	public JsonResult<Map<String,Object>> depMembersAndSubDeps(Long id,Integer pageNo,Integer pageSize,HttpServletRequest req){
		CacheUser userCache = this.getCacheUser();
		Long userId=userCache.getId();
		return departmentManage.depMembersAndSubDeps(id,pageNo,pageSize,userId);
	}
	
	/**
	 * 该部门是否有子部门
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/haveSubDep")
	@ResponseBody
	public JsonResult<Map<String,Object>> haveSubDep(Long id){
		return departmentManage.haveSubDep(id);
	}
	
	/**
	 * 获取公司部门树
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/depTree")
	@ResponseBody
	public JsonResult<Map<String,Object>> depTree(HttpServletRequest req){
		CacheUser userCache = this.getCacheUser();
		Long companyId=userCache.getCompanyId();
		if(companyId==null)
			return JsonResult.fail("公司数据有误");
		return departmentManage.depTree(companyId);
	}
	
	@RequestMapping(value = "/depTree2")
	@ResponseBody
	public JsonResult<Map<String,Object>> depTree2(HttpServletRequest req){
		CacheUser userCache = this.getCacheUser();
		Long companyId=userCache.getCompanyId();
		if(companyId==null)
			return JsonResult.fail("公司数据有误");
		return departmentManage.depTree2(companyId);
	}
	
	@RequestMapping(value = "/depTree3")
	@ResponseBody
	public JsonResult<Map<String,Object>> depTree2(Long companyId){
		if(companyId==null)
			return JsonResult.fail("查询部门请先选择公司");
		return departmentManage.depTree3(companyId);
	}
	
	/**
	 * 根据公司获取公司部门树
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/depTreeByCompany")
	@ResponseBody
	public JsonResult<Map<String,Object>> depTreeByCompany(Long companyId,HttpServletRequest req){
		if(RuntimeContext.cacheUser()!=null && RuntimeContext.cacheUser().getType()!=null &&RuntimeContext.cacheUser().getType().intValue()==3) {
			companyId = RuntimeContext.cacheUser().getCompanyId();
		}
		logger.info("根据公司查询公司的组织架构");
		if(companyId==null)
			return JsonResult.fail("公司数据有误");
		
		return departmentManage.depTree(companyId);
	}
	
	/**
	 * 部门员工分页列表
	 * @param depId
	 * @return
	 */
	@RequestMapping(value = "/depMemPage")
	@ResponseBody
	public JsonResult<Map<String,Object>> depMemPage(Integer pageNo,Integer pageSize,Long depId,HttpServletRequest req){
		CacheUser userCache = this.getCacheUser();
		Long userId=userCache.getId();
		return departmentManage.depMemPage(pageNo,pageSize,depId,userId);
	}
	
	
	
	
//暂时不用*********************************************************	
	// 业务方法：
	@RequestMapping(value = "/findDepartmentById")
	@ResponseBody
	public JsonResult<Map<String, Object>> findDepartmentById(Long departmentId ) {
		
		DepartmentDTO dto = new DepartmentDTO();
		dto.setId(departmentId);
		Map<String, Object> map = departmentManage.findDepartmentById(dto);		
		return JsonResult.success(map);
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findDepartmentAll")
	@ResponseBody
	public JsonResult<List<DepartmentVO>> findDepartmentAll(DepartmentVO vo,HttpServletRequest req ) {
		DepartmentDTO dto = DepartmentConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(com.egeo.utils.StringUtils.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<DepartmentDTO> rt = departmentManage.findDepartmentAll(dto);	
		return JsonResult.success(DepartmentConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findDepartmentOfPage")
	@ResponseBody
	public JsonResult<PageResult<DepartmentVO>> findDepartmentOfPage(DepartmentVO vo,Pagination page,HttpServletRequest req ) {
		DepartmentDTO dto = DepartmentConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(com.egeo.utils.StringUtils.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<DepartmentDTO> rt = departmentManage.findDepartmentOfPage(dto, page);
        List<DepartmentVO> list = DepartmentConverter.toVO(rt.getList());
        PageResult<DepartmentVO> result = new PageResult<DepartmentVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}
	
	
	
	
	
	/**
	 * 根据id更新机构信息
	 * @param vo
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/updateDepartmentByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateDepartmentByIdWithTx(DepartmentVO vo,HttpServletRequest req ) {
		logger.info("根据id更新机构信息");
		DepartmentDTO dto = DepartmentConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(com.egeo.utils.StringUtils.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = departmentManage.updateDepartmentWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	/**
	 * 根据机构id删除机构信息接口
	 * @param vo
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/deleteDepartmentWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteDepartmentWithTx(DepartmentVO vo,HttpServletRequest req ) {
		logger.info("根据机构id删除机构信息接口");
		DepartmentDTO dto = DepartmentConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(com.egeo.utils.StringUtils.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = departmentManage.deleteDepartmentWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	

	
}