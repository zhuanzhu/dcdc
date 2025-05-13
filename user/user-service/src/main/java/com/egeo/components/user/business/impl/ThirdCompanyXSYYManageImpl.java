package com.egeo.components.user.business.impl;


import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.egeo.common.BusinessConstant;
import com.egeo.components.config.dto.CompanyConfigDTO;
import com.egeo.components.user.business.ThirdCompanyUserManage;
import com.egeo.components.user.business.UserManage;
import com.egeo.components.user.converter.UserConverter;
import com.egeo.components.user.dao.write.UserPlatformWriteDAO;
import com.egeo.components.user.dao.write.UserRoleWriteDAO;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.components.user.dto.UserExtendDTO;
import com.egeo.components.user.dto.UserPlatformDTO;
import com.egeo.components.user.dto.UserRoleDTO;
import com.egeo.components.user.facade.UserFacade;
import com.egeo.components.user.service.write.UserExtendWriteService;
import com.egeo.components.user.service.write.UserPlatformWriteService;
import com.egeo.components.user.service.write.UserRoleWriteService;
import com.egeo.components.user.service.write.UserWriteService;
import com.egeo.components.user.vo.UserVO;
import com.egeo.utils.http.HttpClientUtil;

//香山医院用户接口
@Service("xsyyThirdCompany")
public class ThirdCompanyXSYYManageImpl implements ThirdCompanyUserManage{
	@Autowired
	UserFacade userFacade;

/*	@Autowired
	private UserWriteService userWriteService;
	@Autowired
	UserExtendWriteService userExtendWriteService;*/

	@Resource(name="user")
	private UserManage userManage;
	@Autowired
	private UserPlatformWriteService userPlatformWriteService;

	@Autowired
	private UserRoleWriteService userRoleWriteService;
	@Override
	public UserVO getUserInfoByOpenId(String wxOpenId,List<CompanyConfigDTO> configs,String message) {
		// TODO Auto-generated method stub
		String url = null;
		String token = null;
		Long  enterpriseId = null;
		//查询wxOpenId对应的用户
		for(CompanyConfigDTO config : configs) {
			if(config.getKey().equalsIgnoreCase("api.url.getUserByOpenId")) {
				url = config.getValue();
			}else if(config.getKey().equalsIgnoreCase("api.token")) {
				token = config.getValue();
			}else if(config.getKey().equalsIgnoreCase("company.enterprise.id")) {
				enterpriseId = Long.valueOf(config.getValue());
			}
		}
		if(url!=null && token!=null && enterpriseId!=null ) {
			url+="?token="+token+"&openId="+wxOpenId;
			String jsonStr = HttpClientUtil.doGet(url);
			JSONObject parseObject = JSONObject.parseObject(jsonStr);
			if(parseObject.containsKey("code") && parseObject.getInteger("code").intValue()==0) {
				JSONObject parseData = parseObject.getJSONObject("data");
				String userName = parseData.getString("userName");
				Long thirdUserId = parseData.getLong("userId");
				UserDTO userDto = new UserDTO();
				userDto.setEnterpriseId(enterpriseId);
				userDto.setCompanyId(configs.get(0).getCompanyId());
				userDto.setPlatformId(7l);
				userDto.setName(userName);
				userDto.setLoginName("e"+"-"+enterpriseId.longValue()+"c"+"-"+configs.get(0).getCompanyId().intValue()+"-"+thirdUserId.longValue());
				userDto.setMail("e"+"-"+enterpriseId.longValue()+"c"+"-"+configs.get(0).getCompanyId().intValue()+"-"+thirdUserId.longValue()+"@dachuguanjia.cn");
				UserExtendDTO userExtendDTO = new UserExtendDTO();
				userExtendDTO.setStatus(1);
				userExtendDTO.setAccountStatus(0);
				userExtendDTO.setIsAdministrator(0);
				userExtendDTO.setWeixin(wxOpenId);
				userExtendDTO.setName(userName);
				userExtendDTO.setIsDeleted(0);
				userExtendDTO.setRemark(thirdUserId.longValue()+"");
				userExtendDTO.setMemberCode("companyUser-"+configs.get(0).getCompanyId()+"-"+thirdUserId);
				Long createdUserId = userFacade.createUser(userDto, userExtendDTO,configs);
				UserRoleDTO userRolePo = new UserRoleDTO();
				userRolePo.setRoleId(BusinessConstant.PLATFORM7_USER_ROLEID);
				userRolePo.setUserId(createdUserId);
				userRolePo.setCreateTime(new Date());
				userRoleWriteService.saveWithTx(userRolePo);
				/*UserPlatformDTO userPlatformDTO = new UserPlatformDTO();
				userPlatformDTO.setIsAvailable(1);
				userPlatformDTO.setUserId(createdUserId);
				userPlatformDTO.setPlatformId(7l);
				userPlatformDTO.setCreateTime(new Date());
				userPlatformWriteService.saveWithTx(userPlatformDTO);*/
				UserDTO returnUser = userManage.findByWeiXinOpenId(wxOpenId, 7l);
				return UserConverter.toVO(returnUser);
				/*//
				# 添加用户主表信息
				INSERT INTO u_user(login_name,mail,company_id,enterprise_id,mobile,import_records_id) VALUES (mail_,mail_,company_id_,enterprise_id_,mobile_,import_records_id_);
				# 获取刚刚添加的用户id
				SET a = (SELECT LAST_INSERT_ID());
				# 给用户授予该平台的权限
				INSERT INTO u_user_platform (user_id,platform_id) VALUES (a,platform_id_);
				# 添加用户扩展信息
				INSERT INTO u_user_extend (id,sex,NAME,name_py,member_code,birthday) VALUES (a,sex_,name_,to_pinyin(name_),member_code_,birthday_);
				# 添加用户本公司业务扩展信息
				INSERT INTO u_user_welfare (user_id,department_id) VALUES (a,department_id_);*/
			}
		}
		return null;
	}




}
