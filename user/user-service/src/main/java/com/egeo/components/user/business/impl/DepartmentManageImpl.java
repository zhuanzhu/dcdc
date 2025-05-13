package com.egeo.components.user.business.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.common.PlatformKeyConstant;
import com.egeo.components.config.dto.HeadImportRecordsDTO;
import com.egeo.components.config.dto.ImportRecordsDTO;
import com.egeo.components.user.business.DepartmentManage;
import com.egeo.components.user.converter.DepartmentConverter;
import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.components.user.dto.DepMemberDTO;
import com.egeo.components.user.dto.DepartmentDTO;
import com.egeo.components.user.dto.DepartmentTempDTO;
import com.egeo.components.user.dto.DepartmentTreeDTO;
import com.egeo.components.user.dto.UserWelfareDTO;
import com.egeo.components.user.facade.CompanyFacade;
import com.egeo.components.user.facade.DepartmentFacade;
import com.egeo.components.user.facade.DepartmentTempFacade;
import com.egeo.components.user.facade.UserExtendFacade;
import com.egeo.components.user.facade.UserFacade;
import com.egeo.components.user.facade.UserWelfareFacade;
import com.egeo.components.user.vo.DepMemberVO;
import com.egeo.components.user.vo.DepSimpleVO;
import com.egeo.components.user.vo.DepSimpleVOForAndroid;
import com.egeo.components.user.vo.DepartmentExcel;
import com.egeo.components.user.vo.DepartmentVO;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.Upload;
import com.egeo.utils.excel2.ExcelHeadChecker;
import com.egeo.utils.excel2.ExcelTmplConstant;
import com.egeo.utils.excel2.PropblemReportRowVO;
import com.egeo.utils.str.StringUtils;
import com.egeo.web.JsonResult;

@Service("department")
public class DepartmentManageImpl implements DepartmentManage {

	@Resource(name = "departmentFacade")
	private DepartmentFacade departmentFacade;

	@Resource(name = "userWelfareFacade")
	private UserWelfareFacade userWelfareFacade;

	@Resource(name = "userExtendFacade")
	private UserExtendFacade userExtendFacade;

	@Resource(name = "userFacade")
	private UserFacade userFacade;

	@Resource(name = "companyFacade")
	private CompanyFacade companyFacade;

	@Autowired
	private Upload uploadService;
	@Resource(name = "departmentTempFacade")
	private DepartmentTempFacade departmentTempFacade;

	@Override
	public Map<String, Object> findDepartmentById(DepartmentDTO dto) {
		DepartmentDTO departmentDTO = departmentFacade.findDepartmentById(dto);
		Map<String, Object> map = new HashMap<>();
		map.put("id", departmentDTO.getId());
		map.put("departmentName", departmentDTO.getDepartmentName());
		map.put("companyId", departmentDTO.getCompanyId());
		map.put("pId", departmentDTO.getPId());
		map.put("sortValue", departmentDTO.getSortValue());
		return map;
	}

	@Override
	public PageResult<DepartmentDTO> findDepartmentOfPage(DepartmentDTO dto, Pagination page) {
		return departmentFacade.findDepartmentOfPage(dto, page);
	}

	@Override
	public List<DepartmentDTO> findDepartmentAll(DepartmentDTO dto) {
		return departmentFacade.findDepartmentAll(dto);
	}

	@Override
	public Long insertDepartmentWithTx(DepartmentDTO dto) {
		if (EmptyUtil.isEmpty(dto.getPId())) {
			// 如果上级机构id为空则默认为0跟部门
			dto.setPId(0L);
		}
		DepartmentDTO departmentDTO = new DepartmentDTO();
		Long departmentId = departmentFacade.insertDepartmentWithTx(dto);
		if (dto.getPId().equals(0L)) {
			departmentDTO.setPath("," + departmentId + ",");
			departmentFacade.updateDepartmentWithTx(departmentDTO);
		} else {
			// 根据上级机构id查询path值
			dto.setId(departmentId);
			DepartmentDTO departmentDTO2 = departmentFacade.findDepartmentById(dto);
			departmentDTO.setPath(departmentDTO2.getPath() + departmentId + ",");
			departmentFacade.updateDepartmentWithTx(departmentDTO);
		}
		return departmentId;
	}

	@Override
	public int updateDepartmentWithTx(DepartmentDTO dto) {
		if (EmptyUtil.isEmpty(dto.getPId())) {
			// 如果上级机构id为空则默认为0跟部门
			dto.setPId(0L);
		}
		DepartmentDTO departmentDTO = new DepartmentDTO();
		if (dto.getPId().equals(0L)) {
			departmentDTO.setPath("," + dto.getId() + ",");
			departmentFacade.updateDepartmentWithTx(departmentDTO);
		} else {
			// 根据上级机构id查询path值
			dto.setId(dto.getId());
			DepartmentDTO departmentDTO2 = departmentFacade.findDepartmentById(dto);
			departmentDTO.setPath(departmentDTO2.getPath() + dto.getId() + ",");
			departmentFacade.updateDepartmentWithTx(departmentDTO);
		}
		return departmentFacade.updateDepartmentWithTx(dto);
	}

	@Override
	public int deleteDepartmentWithTx(DepartmentDTO dto) {
		// 根据id查询机构子集
		List<DepartmentDTO> list = departmentFacade.findDepartmentAll(dto);
		if (EmptyUtil.isNotEmpty(list)) {
			throw new BusinessException("该机构拥有子集，不能删除");
		}
		// 根据机构id查询员工信息
		UserWelfareDTO userWelfareDTO = new UserWelfareDTO();
		userWelfareDTO.setDepartmentId(dto.getId());
		List<UserWelfareDTO> findUserWelfareAll = userWelfareFacade.findUserWelfareAll(userWelfareDTO);
		if (EmptyUtil.isNotEmpty(findUserWelfareAll)) {
			throw new BusinessException("该机构已有人员信息，不能删除");
		}
		return departmentFacade.deleteDepartmentWithTx(dto);
	}

	@Override
	public List<DepartmentVO> recursionDepartmentAll(DepartmentDTO dto) {
		List<DepartmentDTO> list = departmentFacade.recursionDepartmentAll(dto);
		List<DepartmentVO> departmentVOList = DepartmentConverter.toVO(list);
		List<DepartmentVO> sortList = new ArrayList<DepartmentVO>();
		for (DepartmentVO tree : departmentVOList) {
			for (DepartmentVO t : departmentVOList) {
				if (t.getPId().equals(tree.getId())) {
					if (tree.getChildren() == null) {
						List<DepartmentVO> myChildrens = new ArrayList<DepartmentVO>();
						myChildrens.add(t);
						tree.setChildren(myChildrens);
					} else {
						tree.getChildren().add(t);
					}
				}
			}
			if (tree.getPId().equals(PlatformKeyConstant.PLATFORMID)) {
				sortList.add(tree);
			}
		}
		return sortList;
	}

	@Override
	public List<Map<String, Object>> departmentBydepartmentId(Long companyId, Long departmentId, Long platformId) {
		DepartmentDTO dto = new DepartmentDTO();
		dto.setCompanyId(companyId);
		dto.setPId(departmentId);
		dto.setPlatformId(platformId);
		List<DepartmentDTO> list = departmentFacade.findDepartmentAll(dto);

		List<Map<String, Object>> departmentMap = new ArrayList<>();
		for (DepartmentDTO departmentDTO : list) {
			Map<String, Object> map = new HashMap<>();
			map.put("departmentId", departmentDTO.getId());
			map.put("departmentName", departmentDTO.getDepartmentName());
			map.put("companyId", departmentDTO.getCompanyId());
			map.put("sortValue", departmentDTO.getSortValue());
			map.put("isSubset", departmentDTO.getIsSubset());
			departmentMap.add(map);
		}
		return departmentMap;
	}

	@Override
	public JsonResult<Map<String, Object>> superDepList(Long companyId) {
		if (companyId == null)
			return JsonResult.fail("请选择公司");
		List<DepartmentDTO> deps = departmentFacade.querySuperDepListByCompanyId(companyId);
		List<DepSimpleVO> voList = new ArrayList<>();
		for (DepartmentDTO dep : deps) {
			DepSimpleVO vo = new DepSimpleVO();
			vo.setId(dep.getId());
			vo.setDepartmentName(dep.getDepartmentName());
			voList.add(vo);
		}
		Map<String, Object> data = new HashMap<>();
		data.put("depList", voList);
		return JsonResult.success(data);
	}

	@Override
	public JsonResult<Map<String, Object>> depMembersAndSubDeps(Long id, Integer pageNo, Integer pageSize,
			Long userId) {
		if (id == null)
			return JsonResult.fail("请选择部门");
		// 查询除指定用户之外的部门用户列表
		if (pageNo == null)
			pageNo = 1;
		if (pageSize == null)
			pageSize = 20;
		Pagination page = new Pagination(pageNo, pageSize);
		PageResult<DepMemberDTO> mems = userExtendFacade.queryDepMemberPageExceptThisId(page, id, userId);
		PageResult<DepMemberVO> memPage = new PageResult<>();
		memPage.copy(mems);
		List<DepMemberDTO> memList = mems.getList();
		List<DepMemberVO> memberVos = new ArrayList<>();
		for (DepMemberDTO dto : memList) {
			DepMemberVO vo = new DepMemberVO();
			vo.setFace(dto.getFace());
			vo.setId(dto.getId());
			vo.setName(dto.getName());
			vo.setMail(dto.getMail());
			memberVos.add(vo);
		}
		memPage.setList(memberVos);
		Map<String, Object> data = new HashMap<>();
		data.put("memPage", memPage);
		if (pageNo == 1) {
			// 查询下属部门列表
			List<DepartmentDTO> deps = departmentFacade.queryDepsByPid(id);
			List<DepSimpleVO> voList = new ArrayList<>();
			for (DepartmentDTO dep : deps) {
				DepSimpleVO vo = new DepSimpleVO();
				vo.setId(dep.getId());
				vo.setDepartmentName(dep.getDepartmentName());
				voList.add(vo);
			}
			data.put("depList", voList);
		}
		return JsonResult.success(data);
	}

	@Override
	public JsonResult<Map<String, Object>> haveSubDep(Long id) {
		if (id == null)
			return JsonResult.fail("请选择部门");
		List<DepartmentDTO> deps = departmentFacade.queryDepsByPid(id);
		boolean haveSubDep = deps.size() > 0;
		Map<String, Object> data = new HashMap<>();
		data.put("haveSubDep", haveSubDep);
		return JsonResult.success(data);
	}

	@Override
	public JsonResult<Map<String, Object>> depTree(Long companyId) {
		// 查询公司所有部门
		List<DepartmentDTO> deps = departmentFacade.queryDepsByCompanyId(companyId);
		// 组织树
		List<DepSimpleVOForAndroid> depTree = organizeDepTree(deps, 0l);
		Map<String, Object> data = new HashMap<>();
		data.put("tree", depTree);
		return JsonResult.success(data);
	}

	@Override
	public JsonResult<Map<String, Object>> depTree2(Long companyId) {
		// 查询公司所有部门
		List<DepartmentDTO> deps = departmentFacade.queryDepsByCompanyId(companyId);
		Map<String, Object> data = new HashMap<>();
		data.put("tree", deps);
		return JsonResult.success(data);
	}
	
	@Override
	public JsonResult<Map<String, Object>> depTree3(Long companyId) {
		// 查询公司所有部门
		List<DepartmentDTO> deps = departmentFacade.queryDepsByCompanyId(companyId);
		List<Map<String, Object>> depTree = new ArrayList<>();
		for (DepartmentDTO departmentDTO : deps) {
			Map<String, Object> simDept = new HashMap<String, Object>();
			simDept.put("id",departmentDTO.getId());
			simDept.put("name",departmentDTO.getDepartmentName());
			depTree.add(simDept);
		}
		Map<String, Object> data = new HashMap<>();
		data.put("tree", depTree);
		return JsonResult.success(data);
	}

	/**
	 * 组织公司部门树
	 * 
	 * @param deps
	 * @return
	 */
	private List<DepSimpleVOForAndroid> organizeDepTree(List<DepartmentDTO> deps, Long pid) {
		List<DepSimpleVOForAndroid> depTree = new ArrayList<>();
		for (DepartmentDTO dep : deps) {
			Long pid_ = dep.getPId();
			if (pid_.longValue() == pid.longValue()) {
				DepSimpleVOForAndroid depNode = new DepSimpleVOForAndroid();
				depNode.setId(dep.getId());
				depNode.setName(dep.getDepartmentName());
				if (organizeDepTree(deps, dep.getId()).size() == 0) {
					depNode.setChildren(null);
				} else {
					depNode.setChildren(organizeDepTree(deps, dep.getId()));
				}
				depTree.add(depNode);
			}
		}
		return depTree;
	}

	// public static void main(String[] args) {
	// List<DepartmentDTO> deps=new ArrayList<>();
	// DepartmentDTO dep1=new DepartmentDTO();
	// dep1.setId(1l);
	// dep1.setDepartmentName("1");
	// dep1.setPId(0l);
	// DepartmentDTO dep2=new DepartmentDTO();
	// dep2.setId(2l);
	// dep2.setDepartmentName("2");
	// dep2.setPId(1l);
	// DepartmentDTO dep3=new DepartmentDTO();
	// dep3.setId(3l);
	// dep3.setDepartmentName("3");
	// dep3.setPId(1l);
	// DepartmentDTO dep4=new DepartmentDTO();
	// dep4.setId(4l);
	// dep4.setDepartmentName("4");
	// dep4.setPId(2l);
	// DepartmentDTO dep5=new DepartmentDTO();
	// dep5.setId(5l);
	// dep5.setDepartmentName("5");
	// dep5.setPId(2l);
	// DepartmentDTO dep6=new DepartmentDTO();
	// dep6.setId(6l);
	// dep6.setDepartmentName("6");
	// dep6.setPId(3l);
	// DepartmentDTO dep7=new DepartmentDTO();
	// dep7.setId(7l);
	// dep7.setDepartmentName("7");
	// dep7.setPId(3l);
	// DepartmentDTO dep8=new DepartmentDTO();
	// dep8.setId(8l);
	// dep8.setDepartmentName("8");
	// dep8.setPId(5l);
	// deps.add(dep1);
	// deps.add(dep2);
	// deps.add(dep3);
	// deps.add(dep4);
	// deps.add(dep5);
	// deps.add(dep6);
	// deps.add(dep7);
	// deps.add(dep8);
	//// for(DepartmentDTO dep:deps){
	//// System.out.println(dep.getId());
	//// }
	// DepartmentManageImpl inst=new DepartmentManageImpl();
	// List<DepSimpleVOForAndroid> res=inst.organizeDepTree(deps, 0l);
	// inst.show1ClassDeps(res,new StringBuilder());
	// }
	//
	// private void show1ClassDeps(List<DepSimpleVOForAndroid> deps, StringBuilder
	// tab) {
	// for(DepSimpleVOForAndroid dep:deps){
	// tab.append("\t");
	// System.out.println(tab.toString()+dep.getId());
	// show1ClassDeps(dep.getChilds(), tab);
	// tab.deleteCharAt(tab.lastIndexOf("\t"));
	// }
	// }

	@Override
	public Long deleteByCompanyId(DepartmentDTO dtoRe) {
		return departmentFacade.deleteByCompanyId(dtoRe);

	}

	@Override
	public JsonResult<Map<String, Object>> depMemPage(Integer pageNo, Integer pageSize, Long depId, Long userId) {
		if (depId == null)
			return JsonResult.fail("请选择部门");
		if (pageNo == null)
			pageNo = 1;
		if (pageSize == null)
			pageSize = 20;
		Pagination page = new Pagination(pageNo, pageSize);
		PageResult<DepMemberDTO> mems = userExtendFacade.queryDepMemberPageExceptThisId(page, depId, userId);
		PageResult<DepMemberVO> memPage = new PageResult<>();
		memPage.copy(mems);
		List<DepMemberDTO> memList = mems.getList();
		List<DepMemberVO> memberVos = new ArrayList<>();
		for (DepMemberDTO dto : memList) {
			DepMemberVO vo = new DepMemberVO();
			vo.setFace(dto.getFace());
			vo.setId(dto.getId());
			vo.setName(dto.getName());
			vo.setMail(dto.getMail());
			memberVos.add(vo);
		}
		memPage.setList(memberVos);
		Map<String, Object> data = new HashMap<>();
		data.put("memPage", memPage);
		return JsonResult.success(data);
	}

	@Override
	public JsonResult<Map<String, Object>> insertDepartmentWithTx(Long companyId, Long platformId,
			List<Map<String, Object>> valueList) {
		JsonResult<Map<String, Object>>  jsonResult = new JsonResult<>();
		Map<String, Object> map = new HashMap<>();

		// ****************************************非空判断
		// 公司校验
		if (companyId == null) {
			return JsonResult.fail("请选择公司");
		}
		// 数量校验
		if (valueList.size() > 1002)
			return JsonResult.fail("单次导入数据不能超过1000条");

		// *************************************** 检查头文件及内容
		String err = ExcelHeadChecker.chechHeader(valueList, ExcelTmplConstant.deptTree.getTmplType(), true);
		if (EmptyUtil.isNotBlank(err))
			return JsonResult.fail(err);

		// 公司校验
		CompanyDTO c = companyFacade.findCompanyById(companyId);
		if (c == null)
			return JsonResult.fail("公司不存在");

		if (!c.getCompanyName().equals(valueList.get(0).get("CELL2").toString()))
			return JsonResult.fail("公司信息填写错误");

		// 模板类型
		String tmplName = ExcelTmplConstant.deptTree.getTmplName();
		if (!StringUtils.equals(tmplName, valueList.get(0).get("CELL4").toString())) {
			return JsonResult.fail("导入的文件类型错误，请检查后重新选择文件导入");
		}

		// 创建时间
		String createTime = valueList.get(0).get("CELL6").toString();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = null;
		try {
			date = sdf.parse(createTime);
		} catch (ParseException e) {
			return JsonResult.fail("创建时间解析错误");
		}

		// 序列号校验
		String sn = valueList.get(0).get("CELL8").toString();
		// 创建导入正式记录的信息
		HeadImportRecordsDTO headImportRecordsDTO = new HeadImportRecordsDTO();

		headImportRecordsDTO.setGeneratedTime(date);
		headImportRecordsDTO.setTemplateType(tmplName);
		headImportRecordsDTO.setCompanyName(c.getCompanyName());
		headImportRecordsDTO.setFileSequenceNumber(sn);

		List<HeadImportRecordsDTO> importRecordList = departmentFacade.findHeadImportRecordsAll(headImportRecordsDTO);
		if (!EmptyUtil.isEmpty(importRecordList))
			return JsonResult.fail("文件序列已存在,请确认是否已经导入");
		// ************************************
		// 列表文件校验
		// 封装错误的集合
		List<PropblemReportRowVO<DepartmentExcel>> problemRep = new ArrayList<>();
		// 预览的集合
		List<DepartmentExcel> departmentExcelList = new ArrayList<>();

		// 对部门创建一个集合
		Set<String> department = new HashSet<String>();
		// 对父部门创建一个集合
		List<String> pDepartment = new ArrayList<>();

		for (int i = 2; i < valueList.size(); i++) {

			// 内部不重复,空值校验

			DepartmentExcel vo = row2Bean(valueList.get(i), problemRep, i, department, pDepartment);

			departmentExcelList.add(vo);
		}

		// 如果没有问题
		if (problemRep.size() == 0) {
			// 判断是否可以形成树,先把hashSet转换成list
			List<String> departmentList = new ArrayList<>(department);
			boolean contains = departmentList.containsAll(pDepartment);

			if (contains) {

				List<DepartmentTreeDTO> DepartmentTreeDTOList = new ArrayList<>();

				for (DepartmentExcel departmentExcel : departmentExcelList) {
					DepartmentTreeDTO departmentTreeDTO = new DepartmentTreeDTO();

					departmentTreeDTO.setName(departmentExcel.getDepartment());
					departmentTreeDTO.setpName(departmentExcel.getSuperiorDepartment());

					DepartmentTreeDTOList.add(departmentTreeDTO);
				}

				List<DepartmentTreeDTO> sortList = new ArrayList<DepartmentTreeDTO>();

				for (DepartmentTreeDTO tree : DepartmentTreeDTOList) {

					for (DepartmentTreeDTO t : DepartmentTreeDTOList) {

						if (!StringUtils.equals("无", t.getpName())) {

							if (t.getpName().equals(tree.getName())) {
								if (tree.getChilds() == null) {
									List<DepartmentTreeDTO> myChildrens = new ArrayList<DepartmentTreeDTO>();
									myChildrens.add(t);
									tree.setChilds(myChildrens);
								} else {
									tree.getChilds().add(t);
								}
							}
						}

					}
					if (StringUtils.equals("无", tree.getpName())) {
						sortList.add(tree);
					}
				}

				// 写导入记录表的数据
				ImportRecordsDTO importRecordsDTO = new ImportRecordsDTO();
				importRecordsDTO.setCompanyName(valueList.get(0).get("CELL2").toString());
				importRecordsDTO.setTemplateType(valueList.get(0).get("CELL4").toString());
				importRecordsDTO.setGeneratedTime(date);
				importRecordsDTO.setFileSequenceNumber(sn);

				// 将数据插入到临时表，将表头信息写入到导入记录表，
				Map<String, Object> data = departmentTempFacade.insertInfoInDeptAndRecord(sortList, companyId,
						platformId, importRecordsDTO);

				data.put("overView", departmentExcelList);

				// 将表头信息返回
				data.put("importRecordsInfo", importRecordsDTO);
				
				map.put("success", data);
				map.put("type", 0);
				jsonResult.setData(map);
				return jsonResult;
			}

		} else {
			// 有问题

			/*
			 * 结果集包含内容包含内容: 错误报告url(导入失败) 导入预览批次(导入成功用于查询预览)
			 */
			// 出现错误
			// 生成问题报告.xlsx上传至dfs
			Map<String, Object> data = new HashMap<>();
			String repUrl = genAndUploadRep(problemRep);
			map.put("problemRep", repUrl);
			map.put("type", 1);
			jsonResult.setData(map);
			return jsonResult;
		}
		return null;
	}

	/**
	 * 生成和上传问题报告
	 * 
	 * @param problemRep
	 * @return
	 */
	private String genAndUploadRep(List<PropblemReportRowVO<DepartmentExcel>> problemRep) {
		Workbook wb = new XSSFWorkbook();
		Sheet sh = wb.createSheet("组织架构导入问题报告");

		Row headLine = sh.createRow(0);
		headLine.createCell(0).setCellValue("本部门");
		headLine.createCell(1).setCellValue("父部门");
		headLine.createCell(2).setCellValue("问题");
		headLine.createCell(3).setCellValue("行号");

		for (int i = 1; i < problemRep.size() + 1; i++) {
			PropblemReportRowVO<DepartmentExcel> vo = problemRep.get(i - 1);
			Row r = sh.createRow(i);
			DepartmentExcel lineMeta = vo.getLineMeta();
			r.createCell(0).setCellValue(lineMeta.getDepartment());
			r.createCell(1).setCellValue(lineMeta.getSuperiorDepartment());
			r.createCell(2).setCellValue(vo.getProblem());
			r.createCell(3).setCellValue(vo.getLineNo());
		}
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			wb.write(bos);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("问题报告生成失败");
		}
		// 文件上传至文件服务器
		String filePath = uploadService.fastDFSUpload(bos.toByteArray(), "xlsx");
		return filePath;
	}

	/**
	 * 行转对象,进行空值校验和重复性校验 如果发现问题,存入报告列表中
	 * 
	 * @param map
	 * @param problemRep
	 * @param rowNo
	 * @param pDepartment
	 * @param department2
	 * @return
	 */
	private DepartmentExcel row2Bean(Map<String, Object> row, List<PropblemReportRowVO<DepartmentExcel>> problemRep,
			int rowNo, Set<String> departmentName, List<String> pDepartmentName) {
		DepartmentExcel vo = new DepartmentExcel();

		// 1.部门
		Object department = row.get("CELL1");
		if (department == null) {
			problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "部门名称为空", rowNo + 1, vo));
		} else {
			// 是否重复
			boolean orReapt = departmentName.add(department.toString().trim());
			if (!orReapt) {
				// 如果重复，则
				problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "部门名称有重复", rowNo + 1, vo));
			}
			vo.setDepartment(department.toString().trim());
		}
		

		// 2.上级部门
		Object superiorDepartment = row.get("CELL2");
		if (superiorDepartment == null) {
			problemRep
					.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "上级部门为空,若为顶级部门，则填‘无’", rowNo + 1, vo));
		} else {

			if (!StringUtils.equals("无", superiorDepartment.toString().trim())) {
				if ((!departmentName.contains(superiorDepartment.toString().trim()))) {
					problemRep.add(
							new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "上级部门填写有问题，请核对！", rowNo + 1, vo));
				} else {
					pDepartmentName.add(superiorDepartment.toString().trim());
				}
			}
			vo.setSuperiorDepartment(superiorDepartment.toString().trim());
		}
		
		return vo;
	}

	@Override
	public Long insertDepartmentFromTmpWithTx(DepartmentDTO departmentDTO2) {
		Long insertDepartmentWithTx = departmentFacade.insertDepartmentWithTx(departmentDTO2);
		return insertDepartmentWithTx;

	}

	@Override
	public JsonResult<String> assureImportDepartmentAll(Long companyId, Long parseLong, String departmentTempListIdStr,
			String importRecordId) {

		// 1.将临时表的数据同步到正式表
		// 去掉最前面的一个逗号
		departmentTempListIdStr = departmentTempListIdStr.substring(1);

		String[] departmentTempIds = departmentTempListIdStr.split(",");

		// 转换成Long数组
		Long[] departmentTempIdsArr = new Long[departmentTempIds.length];

		for (int i = 0; i < departmentTempIdsArr.length; i++) {

			departmentTempIdsArr[i] = Long.parseLong(departmentTempIds[i]);
		}

		DepartmentTempDTO dto = new DepartmentTempDTO();
		List<DepartmentTempDTO> findDepartmentTempAll = departmentTempFacade.findDepartmentTempAllByIdsArr(dto,
				departmentTempIdsArr);

		List<DepartmentDTO> departmentDTOList = new ArrayList<>();
		for (DepartmentTempDTO departmentTempDTO : findDepartmentTempAll) {

			DepartmentDTO departmentDTO2 = new DepartmentDTO();
			departmentDTO2.setId(departmentTempDTO.getId());
			departmentDTO2.setPId(departmentTempDTO.getPId());
			departmentDTO2.setPath(departmentTempDTO.getPath());
			departmentDTO2.setLevel(departmentTempDTO.getLevel());
			departmentDTO2.setDepartmentName(departmentTempDTO.getDepartmentName());
			departmentDTO2.setDepartmentContent(departmentTempDTO.getDepartmentContent());
			departmentDTO2.setSortValue(departmentTempDTO.getSortValue());
			departmentDTO2.setCompanyId(departmentTempDTO.getCompanyId());
			departmentDTO2.setIsSubset(departmentTempDTO.getIsSubset());
			departmentDTO2.setPlatformId(departmentTempDTO.getPlatformId());

			departmentDTOList.add(departmentDTO2);

		}

		int status = departmentFacade.assureImportDepartmentAll(companyId, departmentDTOList, parseLong);

		if (status != 0) {
			return JsonResult.fail("发生错误");
		}
		return JsonResult.success("导入成功");

	}

	@Override
	public JsonResult<String> outImportDepartment(DepartmentDTO departmentDTO) {
		// .生成一张表，上传到文件服务器，返回
		List<DepartmentDTO> departmentList = departmentFacade.findDepartmentAll(departmentDTO);
		String CompanyName = companyFacade.findCompanyById(departmentDTO.getCompanyId()).getCompanyName();

		Workbook wsd = new HSSFWorkbook();
		Sheet cloneSheet = wsd.createSheet("部门表");

		Row row0 = cloneSheet.createRow(0);
		Cell cell01 = row0.createCell(0);
		Cell cell02 = row0.createCell(1);
		cell01.setCellValue("公司：");
		cell02.setCellValue(CompanyName);

		Row row1 = cloneSheet.createRow(1);
		Cell cell11 = row1.createCell(0);
		Cell cell12 = row1.createCell(1);
		cell11.setCellValue("部门编号");
		cell12.setCellValue("部门名称");

		for (int i = 0; i < departmentList.size(); i++) {
			Row row = cloneSheet.createRow(i + 2);
			Cell cell1 = row.createCell(0);
			Cell cell2 = row.createCell(1);

			DepartmentDTO departmentDTO3 = departmentList.get(i);

			cell1.setCellValue("" + departmentDTO3.getId());
			cell2.setCellValue("" + departmentDTO3.getDepartmentName());
		}

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			wsd.write(bos);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.fail("导出失败!");
		}
		String upload = uploadService.fastDFSUpload(bos.toByteArray(), "xls");
		return JsonResult.success(upload);
	}

}