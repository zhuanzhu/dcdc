package com.egeo.components.product.business.impl;
	

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

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.egeo.components.config.dto.HeadImportRecordsDTO;
import com.egeo.components.config.dto.ImportRecordsDTO;
import com.egeo.components.product.business.MembershipUserManage;
import com.egeo.components.product.converter.MembershipUserConverter;
import com.egeo.components.product.dto.AuthorityDTO;
import com.egeo.components.product.dto.MembershipAuthorityDTO;
import com.egeo.components.product.dto.MembershipDTO;
import com.egeo.components.product.dto.MembershipUserDTO;
import com.egeo.components.product.facade.AuthorityFacade;
import com.egeo.components.product.facade.CompanyFacade;
import com.egeo.components.product.facade.MembershipAuthorityFacade;
import com.egeo.components.product.facade.MembershipFacade;
import com.egeo.components.product.facade.MembershipUserFacade;
import com.egeo.components.product.facade.UserExtendFacade;
import com.egeo.components.product.vo.AuthorityVO;
import com.egeo.components.product.vo.MembershipUserVO;
import com.egeo.components.product.vo.MembershipVO;
import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.components.user.dto.UserExtendDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.excel2.ExcelHeadChecker;
import com.egeo.utils.excel2.ExcelTmplConstant;
import com.egeo.utils.str.StringUtils;
import com.egeo.web.JsonResult;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service("membershipUser")
public class MembershipUserManageImpl implements MembershipUserManage{

	
	@Resource(name="membershipUserFacade")
	private MembershipUserFacade membershipUserFacade;
	@Resource(name = "userExtendFacade")
	private UserExtendFacade userExtendFacade;
	@Resource(name = "companyFacade")
	private CompanyFacade companyFacade;
	@Resource
	private JedisUtil jedisUtil;
	@Resource(name = "membershipFacade")
	private MembershipFacade membershipFacade;
	@Resource(name = "membershipAuthorityFacade")
	private MembershipAuthorityFacade membershipAuthorityFacade;
	@Resource(name = "authorityFacade")
	private AuthorityFacade authorityFacade;


	Gson gson = new Gson();

	@Override
	public JsonResult<PageResult<MembershipUserVO>> findMembershipUserAll(Long membershipId, String name, String mail,
																	String mobile, Integer sex, Date birthday,
																	String companyName, Date startTime, Date endTime,
																	Integer statusCode, Long platformId,Pagination page) {
		if(EmptyUtil.isEmpty(membershipId)){
			return JsonResult.fail("会籍id不能为空");
		}
		//需要进行用户筛选
		List<Long> result = new ArrayList<>();
		if(EmptyUtil.isNotEmpty(name)|| EmptyUtil.isNotEmpty(mail)||
				EmptyUtil.isNotEmpty(mobile)|| EmptyUtil.isNotEmpty(sex)||
				EmptyUtil.isNotEmpty(birthday)||EmptyUtil.isNotEmpty(companyName)){
			List<Long> companyId =null;
			if(EmptyUtil.isNotEmpty(companyName)){
				companyId = new ArrayList<>();
				//模糊查询company

				List<CompanyDTO> companyDTOS=companyFacade.findCompayByName(companyName.trim());

				if(companyDTOS==null||companyDTOS.size()==0){
					return JsonResult.success(null);
				}
				for(CompanyDTO companyDTO:companyDTOS){
					companyId.add(companyDTO.getId());

				}
			}
				if(EmptyUtil.isNotEmpty(name)){
					name=name.trim();
					if(EmptyUtil.isBlank(name)){
						name = null;
					}
				}
				if(EmptyUtil.isNotEmpty(mail)){
					mail = mail.trim();
					if(EmptyUtil.isBlank(mail)){
						mail = null;
					}
				}
				if(EmptyUtil.isNotEmpty(mobile)){
					mobile = mobile.trim();
					if(EmptyUtil.isBlank(mobile)){
						mobile = null;

					}
				}

				result=userExtendFacade.findUserList(name,mail,mobile,sex,birthday,companyId,platformId);

				if(result==null){
					return JsonResult.success(null);

				}
		}

		//对用户_会籍进行筛选
		PageResult<MembershipUserDTO> dtoPage=membershipUserFacade.findMembershipUserAllByParams(membershipId, startTime, endTime, statusCode, page, result);
		//校验有效状态和有效日期是否匹配
		Date date = new Date();
		for(MembershipUserDTO dto:dtoPage.getList()){
			//如果状态是有效,且戒指日期在当前日期之前,需要修改状态
			if(dto.getStatusCode()==1&&dto.getEndTime().before(date)){
				dto.setStatusCode(0);
				membershipUserFacade.updateMembershipUserWithTx(dto);
			}
		}

		PageResult<MembershipUserDTO> dtoPageResult=membershipUserFacade.findMembershipUserAllByParams(membershipId, startTime, endTime, statusCode, page, result);
		List<MembershipUserDTO> list = dtoPageResult.getList();
		List<MembershipUserVO> membershipUserVOS = MembershipUserConverter.toVO(list);
		PageResult<MembershipUserVO> pageResult = new PageResult<>();
		BeanUtils.copyProperties(dtoPageResult,pageResult);
		for(MembershipUserVO vo:membershipUserVOS){
			UserDTO user= userExtendFacade.findUserById(vo.getUserId());
			vo.setMail(user.getMail());
			vo.setMobile(user.getMobile());
			UserExtendDTO  userExtend=userExtendFacade.findUserExtendById(vo.getUserId());
			vo.setBirthday(userExtend.getBirthday());
			vo.setSex(userExtend.getSex());
			vo.setMembershipId(membershipId);
			vo.setUserName(userExtend.getName());
			CompanyDTO companyDTO=companyFacade.findCompanyById(user.getCompanyId());
			if(companyDTO==null){
				return JsonResult.fail("未找到对应的公司,id为"+vo.getCompanyId());
			}
			vo.setCompanyName(companyDTO.getCompanyName());
		}
		pageResult.setList(membershipUserVOS);


		return JsonResult.success(pageResult);
	}

	@Override
	public JsonResult<Object> stopUserMembership(List<Long> membewrshipUserId) {
		if(membewrshipUserId!=null){
			/*for(Long member: membewrshipUserId){
				MembershipUserDTO membershipUserDTO = new MembershipUserDTO();
				membershipUserDTO.setId(member);
				membershipUserDTO.setEndTime(new Date());
				membershipUserDTO.setStatusCode(0);
				int i = membershipUserFacade.updateMembershipUserWithTx(membershipUserDTO);
				if(i==0){
					return JsonResult.fail("")
				}
			}*/
			boolean res=membershipUserFacade.updateMembershipUserWithTx(membewrshipUserId);
			if(res){
				return JsonResult.success("设置失效成功");
			}
		}

		return JsonResult.fail("设置失效失败");
	}

	@Override
	public JsonResult<Object> extendUserMembershipDate(List<Long> membewrshipUserId, Date endTime) {

		boolean i = membershipUserFacade.updateMembershipUserWithTx(membewrshipUserId,endTime);
		if(i){
			return JsonResult.success("延期成功");
		}

		return JsonResult.fail("延期失败");
	}

	@Override
	public JsonResult<Map<String,Object>> membershipUserImportWithTx(Long platformId,Long membershipId, List<Map<String, Object>> valueList,Integer tempType) {

		if (valueList.size() > 1002)
			return JsonResult.fail("单次导入数据不能超过1000条");

		// *************************************** 检查头文件及内容

		String err = ExcelHeadChecker.chechHeader(valueList, ExcelTmplConstant.membershipUserImport.getTmplType(), true);
		if (EmptyUtil.isNotBlank(err)){
			return JsonResult.fail(err);
		}
		//校验公司
		List<CompanyDTO> cell2 = companyFacade.findCompayByName(valueList.get(0).get("CELL2").toString());
		if(EmptyUtil.isEmpty(cell2)){
			return JsonResult.fail("公司不存在");
		}else if(cell2.size()>1){
			return JsonResult.fail("填写完整的公司信息");
		}
		if(cell2.get(0).getPlatformId()!=platformId){
			return JsonResult.fail("不存在该公司");
		}
		Long companyId = cell2.get(0).getId();
		//校验导入模板类型
		String tmplName = ExcelTmplConstant.membershipUserImport.getTmplName();
		if (!StringUtils.equals(tmplName, valueList.get(0).get("CELL4").toString())) {
			return JsonResult.fail("导入的文件类型错误，请检查后重新选择文件导入");
		}

		//校验会籍类型是否匹配
		if(!StringUtils.equals("用户会籍", valueList.get(2).get("CELL1").toString())){
			return JsonResult.fail("请导入用户会籍类型的会员");
		}
		//创建时间校验
		// 创建时间
		String createTime = valueList.get(0).get("CELL6").toString();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = null;
		Date date2 = null;
		try {
			date = sdf.parse(createTime);
		} catch (ParseException e) {
			return JsonResult.fail("创建时间解析错误");
		}
		//校验文件序号
		String sn = valueList.get(0).get("CELL8").toString();
		// 创建导入正式记录的信息
		ImportRecordsDTO importRecordsDTO = new ImportRecordsDTO();
		importRecordsDTO.setGeneratedTime(date);
		importRecordsDTO.setTemplateType(tmplName);
		importRecordsDTO.setCompanyName(cell2.get(0).getCompanyName());
		importRecordsDTO.setFileSequenceNumber(sn);
		HeadImportRecordsDTO headImportRecordsDTO = new HeadImportRecordsDTO();
		BeanUtils.copyProperties(importRecordsDTO,headImportRecordsDTO);
		List<HeadImportRecordsDTO> importRecordList=membershipUserFacade.findHeadImportRecordsAll(headImportRecordsDTO);
		if (!EmptyUtil.isEmpty(importRecordList))
			return JsonResult.fail("文件序列已存在,请确认是否已经导入");


		//文件列表校验
		Set<String> set = new HashSet<>();
		List<MembershipUserVO> newMembershipUser = new ArrayList<>();
		for(int i=2;i<valueList.size();i++){
			MembershipUserVO vo = new MembershipUserVO();
			//得到每一行数据
			Map<String, Object> res = valueList.get(i);
			//校验会籍类型
			if(!res.get("CELL1").toString().equals("用户会籍")){
				return JsonResult.fail("会籍填写错误第"+(i+1)+"行");
			}
			//校验mail
			String mail=res.get("CELL2").toString();
			UserDTO user=userExtendFacade.findUserBymail(mail);
			if(user==null){
				return JsonResult.fail("第"+(i+1)+"行用户不存在");
			}
			//校验用户是否已经拥有该权限
			MembershipUserDTO membershipUserDTO=membershipUserFacade.findMembershipUserByMail(user.getId(),membershipId);

			if(membershipUserDTO!=null){
				//用户已存在
				return JsonResult.fail("该用户已拥有该会籍");
			}
			String startTime=res.get("CELL3").toString();
			String endTime=res.get("CELL4").toString();
			try {
				date = sdf.parse(startTime);
				date2 = sdf.parse(endTime);
			} catch (ParseException e) {
				return JsonResult.fail("会籍期限时间解析错误");
			}
			set.add(mail);
			vo.setCreateTime(new Date());
			vo.setEndTime(date2);
			vo.setStartTime(date);
			vo.setMembershipId(membershipId);
			vo.setUserId(user.getId());
			vo.setMail(mail);
			vo.setMembershipCategoryName("用户会籍");
			newMembershipUser.add(vo);
		}
		if(set.size()!=newMembershipUser.size()){
			//用重复mail
			return JsonResult.fail("mail信息有重复");

		}

		//将头信息存入草稿库
		membershipUserFacade.insertImportRecords(importRecordsDTO);
		//将内容信息保存在redis中

		jedisUtil.set(sn,gson.toJson(newMembershipUser));

		Map<String, Object> data = new HashMap<>();
		data.put("list", newMembershipUser);
		data.put("header", headImportRecordsDTO);
		return JsonResult.success(data);


	}

	@Override
	public JsonResult<String> assureMembershipUserImport(String fileNumber) {
		if(EmptyUtil.isEmpty(fileNumber)){
			return JsonResult.fail("fileNumber不能为空");
		}
		String s= jedisUtil.get(fileNumber).toString();
		List<MembershipUserVO> voList=gson.fromJson(s, new TypeToken<List<MembershipUserVO>>() {
		}.getType());
		List<ImportRecordsDTO> importRecordsDTOS=membershipUserFacade.findImportRecords(fileNumber);
		if(importRecordsDTOS.size()>1){
			return JsonResult.fail("文件序号重复");
		}
		if(importRecordsDTOS==null){
			return JsonResult.fail("文件序号有误");
		}
		ImportRecordsDTO importRecordsDTO = importRecordsDTOS.get(0);
		HeadImportRecordsDTO headImportRecordsDTO = new HeadImportRecordsDTO();
		BeanUtils.copyProperties(importRecordsDTO, headImportRecordsDTO);
		//拼装会籍会员关联信息

		List<MembershipUserDTO> membershipUserDTOS = new ArrayList<>();
		for (MembershipUserVO vo : voList) {
			MembershipUserDTO membershipUserDTO = new MembershipUserDTO();
			//校验mail
			String mail=vo.getMail();
			UserDTO user=userExtendFacade.findUserBymail(mail);
			if(user==null){
				return JsonResult.fail(mail+"的用户不存在");
			}
			//校验用户是否已经拥有该权限
			MembershipUserDTO membershipUserDTO1=membershipUserFacade.findMembershipUserByMail(user.getId(),vo.getMembershipId());

			if(membershipUserDTO1!=null){
				//用户已存在
				return JsonResult.fail("该用户已拥有该会籍");
			}
			membershipUserDTO.setMembershipId(vo.getMembershipId());
			membershipUserDTO.setUserId(vo.getUserId());
			membershipUserDTO.setStatusCode(1);
			membershipUserDTO.setEndTime(vo.getEndTime());
			membershipUserDTO.setStartTime(vo.getStartTime());
			membershipUserDTOS.add(membershipUserDTO);
		}
		membershipUserFacade.sureImportInfo(membershipUserDTOS,headImportRecordsDTO);

		return JsonResult.success("导入成功");
	}

	@Override
	public JsonResult<Map<String, Object>> findMembershipByUser(Long userId, Long platformId) {

		MembershipUserDTO membershipUserDTO = new MembershipUserDTO();
		membershipUserDTO.setUserId(userId);
		membershipUserDTO.setStatusCode(1);
		List<MembershipUserDTO> membershipUserAll = membershipUserFacade.findMembershipUserAll(membershipUserDTO);
		List<MembershipVO> membershipVOList = new ArrayList<>();
		Set<String> set = new HashSet<>();
		Set<Long> set2 = new HashSet<>();
		Set<String> set3 = new HashSet<>();
		for(MembershipUserDTO dto:membershipUserAll){
//			MembershipDTO membershipDTO = new MembershipDTO();
//			membershipDTO.setId(dto.getMembershipId());
//			membershipDTO.setPlatformId(platformId);
//			MembershipDTO membershipById = membershipFacade.findMembershipById(membershipDTO);
			//membershipDTOList.add(membershipById);
			MembershipVO membershipVO = new MembershipVO();
			MembershipAuthorityDTO authorityDTO = new MembershipAuthorityDTO();
			authorityDTO.setIsStop(0);
			authorityDTO.setPlatformId(platformId);
			authorityDTO.setMembershipId(dto.getMembershipId());
			List<MembershipAuthorityDTO> membershipAuthorityAll = membershipAuthorityFacade.findMembershipAuthorityAll(authorityDTO);
			List<AuthorityVO> authorityVOList = new ArrayList<>();
			if(membershipAuthorityAll!=null){
				for(MembershipAuthorityDTO authorityDTO1:membershipAuthorityAll){
					AuthorityVO authorityVO = new AuthorityVO();
					AuthorityDTO autDTO1 = new AuthorityDTO();
					autDTO1 .setId(authorityDTO1.getAuthorityId());
					AuthorityDTO authorityById = authorityFacade.findAuthorityById(autDTO1);
					BeanUtils.copyProperties(authorityById,authorityVO);
					authorityVOList .add(authorityVO);
				}

			}
			membershipVO.setId(dto.getMembershipId());
			membershipVO.setAuthorityVOList(authorityVOList);
			membershipVOList.add(membershipVO);
		}

		for(MembershipVO vo:membershipVOList){
			set2.add(vo.getId());
			MembershipDTO membershipDTO = new MembershipDTO();
			membershipDTO.setId(vo.getId());
			MembershipDTO membershipById = membershipFacade.findMembershipById(membershipDTO);
			if(EmptyUtil.isNotEmpty(membershipById)){
				set3.add(membershipById.getMembershipName());
			}
			for(AuthorityVO authorityVO:vo.getAuthorityVOList()){
					set.add(authorityVO.getAuthorityName());
			}

		}

		Map<String, Object> map = new HashMap<>();
		map.put("membershipId", set2);
		map.put("membershipName", set3);
		map.put("authority", set);


		return JsonResult.success(map);
	}


}
	