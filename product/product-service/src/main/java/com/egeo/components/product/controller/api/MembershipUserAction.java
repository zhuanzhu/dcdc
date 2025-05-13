package com.egeo.components.product.controller.api;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.egeo.components.product.business.MembershipUserManage;
import com.egeo.components.product.vo.MembershipUserVO;
import com.egeo.entity.CacheUser;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.excel2.ExcelUtil;
import com.egeo.utils.str.StringUtils;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


@Controller
@RequestMapping("/api/product/membershipUser")
public class MembershipUserAction extends BaseSpringController {

	@Resource(name = "membershipUser")
	private MembershipUserManage membershipUserManage;
	Gson gson = new Gson();
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	// 条件查询会员列表
	@RequestMapping(value = "/findMembershipUserAll")
	@ResponseBody
	public JsonResult<PageResult<MembershipUserVO>> findMembershipUserAll(Long membershipId, String name,
																		  String mail, String mobile,
																		  Integer sex, String birthday,
																		  String companyName, String startTime,
																		  String endTime, Integer statusCode,
																		  HttpServletRequest req, Pagination page) {
		//校验platformId
		String str = req.getHeader("platformId");

		if (EmptyUtil.isEmpty(str)) {
			fail("platformId不能为null");

		}
		Long platformId = Long.valueOf(str);

		Date bir=new Date();
		Date end=new Date();
		Date start=new Date();
		if(EmptyUtil.isNotEmpty(birthday)){
			try {
				bir = format.parse(birthday);
			} catch (ParseException e) {
				return JsonResult.fail("生日时间解析错误");
			}
		}else{
			bir = null;
		}
		if (EmptyUtil.isNotEmpty(endTime)) {
			try {
				end = format.parse(endTime);
			} catch (ParseException e) {
				return JsonResult.fail("失效时间解析错误");
			}
		}else{
			end = null;
		}
		if(EmptyUtil.isNotEmpty(startTime)){
			try {
				start=format.parse(startTime);
			} catch (ParseException e) {
				return JsonResult.fail("失效时间解析错误");
			}

		}else{
			start = null;
		}

		return membershipUserManage.findMembershipUserAll(membershipId, name,
				mail, mobile,
				sex, bir,
				companyName, start,
				end, statusCode, platformId, page);


	}

	@RequestMapping("/stopUserMembership")
	@ResponseBody
	public JsonResult<Object> stopUserMembership(String membershipUserId,
												 HttpServletRequest req) {
		//校验platformId
		String str = req.getHeader("platformId");

		if (EmptyUtil.isEmpty(str)) {
			fail("platformId不能为null");

		}
		Long platformId = Long.valueOf(str);
		List<Long> list = new ArrayList<>();
		if(EmptyUtil.isNotEmpty(membershipUserId)){
			list=gson.fromJson(membershipUserId,new TypeToken<List<Long>>(){}.getType());
		}else{
			list = null;
		}



		return membershipUserManage.stopUserMembership(list);
	}



	@RequestMapping("/extendUserMembershipDate")
	@ResponseBody
	public JsonResult<Object> extendUserMembershipDate(String membershipUserId,
													   String endTime,
													   HttpServletRequest req){
		//校验platformId
		String str = req.getHeader("platformId");

		if(EmptyUtil.isEmpty(str)){
			fail("platformId不能为null");
		}
		Long platformId=Long.valueOf(str);
		if(membershipUserId==null||endTime==null){
			return JsonResult.fail("请选择参数");
		}
		List<Long> ids=gson.fromJson(membershipUserId, new TypeToken<List<Long>>(){}.getType());
		Date end = new Date();
		try {
			end = format.parse(endTime);
		} catch (ParseException e) {
			return JsonResult.fail("时间解析错误");
		}
		if(end.before(new Date())){
			return JsonResult.fail("时间必须是当前时间之后");
		}

		return membershipUserManage.extendUserMembershipDate(ids,end);

	}

	/**
	 * 为会籍导入会员*/

	@RequestMapping("/membershipUserImport")
	@ResponseBody
	public JsonResult<Map<String,Object>> membershipUserImport(Long membershipId,
															   Integer tempType,
															   HttpServletRequest req){
			logger.info("为会籍导入会员");
		if(membershipId==null){
			return JsonResult.fail("请选择会籍id");
		}

		String platformId_=req.getHeader("platformId");
		if(EmptyUtil.isEmpty(platformId_))
			return JsonResult.fail("platformId为空");
		Long platformId=Long.parseLong(platformId_);


		// 从请求体中获取文件
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) req;
		Iterator<String> iter = multiRequest.getFileNames();
		if(!iter.hasNext()) {
			return JsonResult.fail("请上传文件");
		}
		MultipartFile file = multiRequest.getFile(iter.next());
		if (file == null)
			return JsonResult.fail("未发现Excel文件");

		String originalFilename = file.getOriginalFilename();
		// 获取文件后缀
		String suffix = "";

		suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

		if(! StringUtils.equals(suffix, ".xls") && ! StringUtils.equals(suffix, ".xlsx")){
			return JsonResult.fail("导入的文件类型错误，请选择后重新选择文件导入");
		}

		List<Map<String, Object>> valueList = null;
		try {
			valueList = ExcelUtil.readExcelData(0, 0, file.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
			return JsonResult.fail("Excel文件读取发生异常");
		}
		if(EmptyUtil.isEmpty(tempType)){
			return JsonResult.fail("导入模板类型不能为空");
		}
		if(tempType==9){
			logger.info("导入用户会籍模板");
			return membershipUserManage.membershipUserImportWithTx(platformId,membershipId,valueList,tempType);
		}
		return JsonResult.fail("导入失败");


	}

	/**
	 * 确定导入
	 * @return
	 */
	@RequestMapping("/assureMembershipUserImport")
	@ResponseBody
	public JsonResult<String> assureMembershipUserImport(Long membershipId,
														 String fileNumber){
		logger.info("确认导入");
		return membershipUserManage.assureMembershipUserImport(fileNumber);
	}


	/**
	 * 根据userid查询会籍名和会籍权限
	 * @param req
	 * @return
	 */
	@RequestMapping("/findMembershipByUser")
	@ResponseBody
	public JsonResult<Map<String,Object>> findMembershipByUser(HttpServletRequest req){
		CacheUser userCache = this.getCacheUser();
		Long userId = userCache.getId();
		if(EmptyUtil.isEmpty(userId )){
			return JsonResult.fail("userid不能为空");

		}
		String platformId_=req.getHeader("platformId");
		if(EmptyUtil.isEmpty(platformId_))
			return JsonResult.fail("platformId为空");
		Long platformId=Long.parseLong(platformId_);
		return membershipUserManage.findMembershipByUser(userId,platformId);
	}




		
}
	