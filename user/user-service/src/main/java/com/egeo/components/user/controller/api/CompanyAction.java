package com.egeo.components.user.controller.api;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.egeo.util.security.MD5Support;
import com.egeo.util.security.MD5Util;
import com.egeo.util.security.SaltUtils;
import com.egeo.utils.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.egeo.components.user.vo.CompanyCreateVO;
import com.egeo.components.user.vo.CompanyIdAndNameVO;
import com.egeo.components.user.vo.CompanyVO;
import com.egeo.config.RuntimeContext;
import com.egeo.common.BusinessExceptionConstant;
import com.egeo.components.user.business.CompanyManage;
import com.egeo.components.user.converter.CompanyConverter;
import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.components.user.dto.CompanyPageDTO;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.components.user.po.UserPO;
import com.egeo.orm.PageResult;
import com.egeo.entity.CacheUser;
import com.egeo.exception.BusinessException;
import com.egeo.orm.Pagination;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;

@Controller
@RequestMapping("/api/user/company")
public class CompanyAction extends BaseSpringController {

	@Resource(name = "company")
	private CompanyManage companyManage;

	// 业务方法：
	@RequestMapping(value = "/findCompanyById")
	@ResponseBody
	public JsonResult<Map<String, Object>> findCompanyById(Long companyId) {

		Map<String, Object> rt = companyManage.findCompanyById(companyId);
		return JsonResult.success(rt);

	}

	// 业务方法：
	@RequestMapping(value = "/findCompanyByEnterpriseId")
	@ResponseBody
	public JsonResult<List<CompanyDTO>> findCompanyByEnterpriseId(Long enterpriseId) {
		CompanyDTO dto = new CompanyDTO();
		dto.setEnterpriseId(enterpriseId);
		List<CompanyDTO> rt = companyManage.findCompanyAll(dto);
		return JsonResult.success(rt);

	}
	/**
	 * 新增企业
	 * @param vo
	 * @param companyPageList
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/insertOrUpdateCompanyWithTx")
	@ResponseBody
	public JsonResult<Long> insertCompanyWithTx(CompanyCreateVO vo,String companyPageList,String linkJson,String cmsPageTabList, HttpServletRequest req) {
		if (vo.getId() == null) {
			logger.info("新增企业");
		} else {
			logger.info("企业编辑");
		}

		if(RuntimeContext.cacheUser().getType().intValue()!=2) {
			return JsonResult.fail("您无权操作该功能");
		}
		CacheUser userCache = this.getCacheUser();

		if (StringUtils.isEmpty(vo.getCompanyName())) {
			return JsonResult.fail("公司名称不能为空");
		}
		/*if (StringUtils.isEmpty(String.valueOf(vo.getCompanyType()))) {
			return JsonResult.fail("公司类型不能为空");
		}*/
		if (vo.getCompanyType() == null) {
			return JsonResult.fail("公司类型不能为空");
		}
		if (vo.getCompanyName().length() > 30) {
			throw new BusinessException(BusinessExceptionConstant.USER_COMPANYNAME_TOOLONG, "公司名称超过限制!");
		}
		if (vo.getMail()==null || vo.getMail().length() < 5) {
			throw new BusinessException(BusinessExceptionConstant.EMAIL_EMPTY, "公司管理员账号为空或长度低于5!");
		}
		if (vo.getPwd()==null || vo.getPwd().length() < 5) {
			throw new BusinessException(BusinessExceptionConstant.USER_PASSWORD_EMPTY, "公司管理员密码为空或长度低于5!");
		}
		if (StringUtils.isNotEmpty(vo.getCompanyContent())) {
			if (vo.getCompanyContent().length() > 200) {
				throw new BusinessException(BusinessExceptionConstant.USER_COMPANYCONTENT_TOOLONG, "公司备注字数太多!");
			}
		}
		
		List<CompanyPageDTO> companyPageDTOs = new ArrayList<>();
		if(StringUtils.isNotEmpty(companyPageList)){
			companyPageDTOs = new ArrayList<CompanyPageDTO>(JSONArray.parseArray(companyPageList, CompanyPageDTO.class));
		}
		//数据库中确认不存在名称相同的公司再运行该程序
		CompanyDTO companyByName=companyManage.queryCompanyByName(vo.getCompanyName());
		
		if (vo.getId() == null) {
			//新增
			if(companyByName!=null) {
				//存在已传参公司名称命名的公司,不可新增
				throw new BusinessException("公司名称已被其他公司使用");
			}

			CompanyDTO dto = CompanyConverter.toDTO(vo);
			dto.setUserId(userCache.getId());
			dto.setUserName(userCache.getName());
			dto.setStatus(0);
			dto.setEnterpriseId(RuntimeContext.cacheUser().getEnterpriseId());
			String salt = SaltUtils.getRandomSalt();
			UserDTO userPO = new UserDTO();
			userPO.setMail(vo.getMail());
			userPO.setLoginName(vo.getMail());
			userPO.setPlatformId(7l);
			userPO.setPassword(MD5Support.MD5(MD5Support.MD5(vo.getPwd()), salt));
			userPO.setSalt(salt);
			userPO.setType(3);
			
			String str = req.getHeader("platformId");
			if (StringUtils.isNotEmpty(str)) {
				Long platformId = Long.valueOf(str);
				dto.setPlatformId(platformId);
			}
			
			Long rt = companyManage.insertCompanyAndAdminWithTx(dto,userPO,companyPageDTOs,linkJson,cmsPageTabList);
			return JsonResult.success(rt);
		} else {
			//编辑
			//公司名称能查询到结果时,判断该结果的id与传参id是否相同
			if(companyByName!=null) {
				if(vo.getId().longValue()!=companyByName.getId()) {
					//不同 说明传参公司名称已被其他公司占用 无法再使用
					throw new BusinessException("公司名称已被其他公司使用");
				}
			}
			if (vo.getId() == 1) {
				return JsonResult.fail("此公司不能编辑");
			}

			CompanyDTO dto = CompanyConverter.toDTO(vo);
			String str = req.getHeader("platformId");
			if (StringUtils.isNotEmpty(str)) {
				Long platformId = Long.valueOf(str);
				dto.setPlatformId(platformId);
			}
			int rt = companyManage.updateCompanyWithTx(dto, userCache,companyPageDTOs,linkJson,cmsPageTabList);
			return JsonResult.success(new Long((long) rt));
		}

	}

	/**
	 * 企业失效/有效
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/companyInvalid")
	@ResponseBody
	public JsonResult<String> companyInvalid(Long companyId, Integer validType, Integer recoverUser,HttpServletRequest req) {

		logger.info("企业失效操作");
		CompanyDTO companyDTO = new CompanyDTO();
		companyDTO.setId(companyId);

		if (validType == 1) {
			Integer companyInvalidInfo = companyManage.companyInvalid(companyDTO, validType);

			if (companyInvalidInfo == 0) {
				return JsonResult.fail("请先将企业账户设置为失效");
			}
			if (companyInvalidInfo == 1) {
				return JsonResult.success("公司已失效");
			}
			return JsonResult.fail("发生错误！");
		} else if (validType == 0) {
			// 公司有效
			try {
				companyManage.companyValid(recoverUser,companyDTO);
			} catch (Exception e) {
				return JsonResult.fail("发生错误");
			}
			return JsonResult.success("公司置为有效成功");
		}
		return null;
	}

	/**
	 * 条件查询所有公司
	 * 
	 * @param vo
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/findCompanyAll")
	@ResponseBody
	public JsonResult<List<CompanyVO>> findCompanyAll(CompanyVO vo, HttpServletRequest req) {
		logger.info("条件查询所有公司");
		CompanyDTO dto = CompanyConverter.toDTO(vo);
		String str = req.getHeader("platformId");
		if (StringUtils.isNotEmpty(str)) {
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
/*
		boolean l = MobileDevice.isMobileDevice(req);
		if (l) {
			System.out.println("是客户端");
		} else {
			System.out.println("是web端");
		}*/
		dto.setStatus(0);
		List<CompanyDTO> rt = companyManage.findCompanyAll(dto);
		return JsonResult.success(CompanyConverter.toVO(rt));

	}

	/**
	 * 查询所有公司(只包含id和名称)暂无模版信息
	 * 
	 * @param vo
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/findCompanyIdOrName")
	@ResponseBody
	public JsonResult<List<CompanyIdAndNameVO>> findCompanyIdOrName(CompanyVO vo, HttpServletRequest req) {
		logger.info("条件查询所有公司(只包含id和名称)");
		CompanyDTO dto = CompanyConverter.toDTO(vo);
		String str = req.getHeader("platformId");
		if (StringUtils.isNotEmpty(str)) {
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		if(RuntimeContext.cacheUser()!=null && RuntimeContext.cacheUser().getType()!=null &&RuntimeContext.cacheUser().getType().intValue()==3) {
			Long companyId = RuntimeContext.cacheUser().getCompanyId();
			dto.setId(companyId);
		}else if(RuntimeContext.cacheUser()!=null && RuntimeContext.cacheUser().getType()!=null &&RuntimeContext.cacheUser().getType().intValue()==2) {
			dto.setEnterpriseId(RuntimeContext.cacheUser().getEnterpriseId());
		}
		//dto.setStatus(0);
		List<CompanyDTO> rt = companyManage.findCompanyIdOrName(dto);
		return JsonResult.success(CompanyConverter.toIdAndNameVO(rt));

	}

	// 业务方法：
	/**
	 * 分页查询所有公司
	 * 
	 * @param vo
	 * @param page
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/findCompanyOfPage")
	@ResponseBody
	public JsonResult<PageResult<CompanyVO>> findCompanyOfPage(CompanyVO vo,String companyIds, Pagination page, HttpServletRequest req) {
		CompanyDTO dto = CompanyConverter.toDTO(vo);
		String str = req.getHeader("platformId");
		if (StringUtils.isNotEmpty(str)) {
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		// 判断前端传递公司id集合是否为空，为null并且集合size为0则直接返回
		if(companyIds != null){
			List<Long> companyIdList = JSONArray.parseArray(companyIds, Long.class);
			if(companyIdList.size() <= 0){
				PageResult<CompanyVO> result = new PageResult<CompanyVO>();
				List<CompanyVO> list = new ArrayList<>();
		        result.setList(list);
		        result.setPageNo(page.getPageNo());
		        result.setPageSize(page.getPageSize());
		        result.setTotalSize(0);
		        return JsonResult.success(result);
			}else{
				if(companyIdList.size() == 1 && companyIdList.get(0).equals(-1L)){
					PageResult<CompanyDTO> rt = companyManage.findCompanyOfPage(dto, page,null);
					List<CompanyVO> list = CompanyConverter.toVO(rt.getList());
					PageResult<CompanyVO> result = new PageResult<CompanyVO>();
					result.setList(list);
					result.setPageNo(rt.getPageNo());
					result.setPageSize(rt.getPageSize());
					result.setTotalSize(rt.getTotalSize());
					return JsonResult.success(result);
				}
				PageResult<CompanyDTO> rt = companyManage.findCompanyOfPage(dto, page,companyIdList);
				List<CompanyVO> list = CompanyConverter.toVO(rt.getList());
				PageResult<CompanyVO> result = new PageResult<CompanyVO>();
				result.setList(list);
				result.setPageNo(rt.getPageNo());
				result.setPageSize(rt.getPageSize());
				result.setTotalSize(rt.getTotalSize());
				return JsonResult.success(result);
			}
		}
		PageResult<CompanyDTO> rt = companyManage.findCompanyOfPage(dto, page,null);
		List<CompanyVO> list = CompanyConverter.toVO(rt.getList());
		PageResult<CompanyVO> result = new PageResult<CompanyVO>();
		result.setList(list);
		result.setPageNo(rt.getPageNo());
		result.setPageSize(rt.getPageSize());
		result.setTotalSize(rt.getTotalSize());
		return JsonResult.success(result);

	}

	@RequestMapping(value = "/findEnterpriseCompanyOfPage")
	@ResponseBody
	public JsonResult<PageResult<CompanyVO>> findEnterpriseCompanyOfPage(CompanyVO vo,String companyIds, Pagination page, HttpServletRequest req) {
		CompanyDTO dto = CompanyConverter.toDTO(vo);
		String str = req.getHeader("platformId");
		if (StringUtils.isNotEmpty(str)) {
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		if(RuntimeContext.cacheUser().getType().intValue()!=2) {
			return JsonResult.fail("您无权操作该功能");
		}
		dto.setEnterpriseId(RuntimeContext.cacheUser().getEnterpriseId());
		// 判断前端传递公司id集合是否为空，为null并且集合size为0则直接返回
		if(companyIds != null){
			List<Long> companyIdList = JSONArray.parseArray(companyIds, Long.class);
			if(companyIdList.size() <= 0){
				PageResult<CompanyVO> result = new PageResult<CompanyVO>();
				List<CompanyVO> list = new ArrayList<>();
		        result.setList(list);
		        result.setPageNo(page.getPageNo());
		        result.setPageSize(page.getPageSize());
		        result.setTotalSize(0);
		        return JsonResult.success(result);
			}else{
				if(companyIdList.size() == 1 && companyIdList.get(0).equals(-1L)){
					PageResult<CompanyDTO> rt = companyManage.findCompanyOfPage(dto, page,null);
					List<CompanyVO> list = CompanyConverter.toVO(rt.getList());
					PageResult<CompanyVO> result = new PageResult<CompanyVO>();
					result.setList(list);
					result.setPageNo(rt.getPageNo());
					result.setPageSize(rt.getPageSize());
					result.setTotalSize(rt.getTotalSize());
					return JsonResult.success(result);
				}
				PageResult<CompanyDTO> rt = companyManage.findCompanyOfPage(dto, page,companyIdList);
				List<CompanyVO> list = CompanyConverter.toVO(rt.getList());
				PageResult<CompanyVO> result = new PageResult<CompanyVO>();
				result.setList(list);
				result.setPageNo(rt.getPageNo());
				result.setPageSize(rt.getPageSize());
				result.setTotalSize(rt.getTotalSize());
				return JsonResult.success(result);
			}
		}
		PageResult<CompanyDTO> rt = companyManage.findCompanyOfPage(dto, page,null);
		List<CompanyVO> list = CompanyConverter.toVO(rt.getList());
		PageResult<CompanyVO> result = new PageResult<CompanyVO>();
		result.setList(list);
		result.setPageNo(rt.getPageNo());
		result.setPageSize(rt.getPageSize());
		result.setTotalSize(rt.getTotalSize());
		return JsonResult.success(result);

	}
	// ############################## 暂时不用

	// 业务方法：
	@RequestMapping(value = "/deleteCompanyWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteCompanyWithTx(CompanyVO vo, HttpServletRequest req) {
		CompanyDTO dto = CompanyConverter.toDTO(vo);
		String str = req.getHeader("platformId");
		if (StringUtils.isNotEmpty(str)) {
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = companyManage.deleteCompanyWithTx(dto);
		return JsonResult.success(rt);
	}

	/**
	 * 查询公司列表简要信息
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/simpleCompanyList")
	@ResponseBody
	public JsonResult<Map<String, Object>> simpleCompanyList(HttpServletRequest req) {
		String str = req.getHeader("platformId");
		if (StringUtils.isEmpty(str))
			return JsonResult.fail("platformId为空");
		Long platformId = Long.valueOf(str);
		return companyManage.simpleCompanyList(platformId);
	}

/*	@RequestMapping("/getsaletorder")
	@ResponseBody
	public JsonResult getSalt(){
		// 1.插入公司记录
		// 2.生成uuid
		String uuid = UUID.uuid();
		// 3.生成密文
		String randomSalt = SaltUtils.getRandomSalt();
		BigDecimal defaultValue=new BigDecimal("0.00");
		String ciphertext = MD5Util.MD5Salt(defaultValue.toString(), randomSalt);

		// 4.盐表插入数据
		SaltDTO saltDTO = new SaltDTO();
		saltDTO.setUuid(uuid);
		saltDTO.setSaltValue(randomSalt);
		List<String> str = new ArrayList<>();
		str.add(uuid);
		str.add(randomSalt);
		str.add(ciphertext);
		return JsonResult.success(str);
	}*/

}
