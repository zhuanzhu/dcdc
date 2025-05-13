package com.egeo.components.user.business.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.egeo.common.BusinessConstant;
import com.egeo.components.config.dto.CompanyConfigDTO;
import com.egeo.components.third.client.ChannelServiceConfigClient;
import com.egeo.components.third.dto.RemoteExecuteDTO;
import com.egeo.components.third.enums.ChannelServiceNameEnum;
import com.egeo.components.third.enums.ChannelServiceTypeEnum;
import com.egeo.components.user.business.ThirdCompanyUserManage;
import com.egeo.components.user.business.UserManage;
import com.egeo.components.user.converter.UserConverter;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.components.user.dto.UserExtendDTO;
import com.egeo.components.user.dto.UserRoleDTO;
import com.egeo.components.user.facade.UserFacade;
import com.egeo.components.user.service.write.UserPlatformWriteService;
import com.egeo.components.user.service.write.UserRoleWriteService;
import com.egeo.components.user.vo.UserVO;
import com.egeo.exception.BusinessException;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.http.HttpClientUtil;
import com.egeo.web.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

//科技馆用户接口
@Service("kjgThirdCompany")
public class ThirdCompanyKJGManageImpl implements ThirdCompanyUserManage{
	@Autowired
	UserFacade userFacade;


	@Resource(name="user")
	private UserManage userManage;
	@Autowired
	private UserPlatformWriteService userPlatformWriteService;

	@Autowired
	private UserRoleWriteService userRoleWriteService;

	@Autowired
	private ChannelServiceConfigClient channelServiceConfigClient;

	@Override
	public UserVO getUserInfoByOpenId(String wxOpenId,List<CompanyConfigDTO> configs,String message) {
		// TODO Auto-generated method stub
		String url = null;
		String channelCode = null;
		Long  enterpriseId = null;
		//查询wxOpenId对应的用户
		for(CompanyConfigDTO config : configs) {
			if(config.getKey().equalsIgnoreCase("company.code")) {
				channelCode = config.getValue();
			}else if(config.getKey().equalsIgnoreCase("company.enterprise.id")) {
				enterpriseId = Long.valueOf(config.getValue());
			}
		}
		if(EmptyUtil.isEmpty(message) || EmptyUtil.isBlank(message)){
			throw new BusinessException("参数缺失");
		}
		JSONObject jsonObjectParam = JSONObject.parseObject(message);
		RemoteExecuteDTO executeDTO = new RemoteExecuteDTO();
		executeDTO.setEnterpriseId(enterpriseId.intValue());
		executeDTO.setChannelCode(channelCode);
		executeDTO.setChannelServiceName(ChannelServiceNameEnum.USER_CASH.getChannelServiceName());
		executeDTO.setChannelServiceType(ChannelServiceTypeEnum.REQ.getChannelServiceType());
		executeDTO.setBizCode(jsonObjectParam.getString("userFlag"));
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("userFlag",jsonObjectParam.getString("userFlag"));
		if(jsonObjectParam.containsKey("userName") && EmptyUtil.isNotEmpty(jsonObjectParam.getString("userName"))){
			jsonObject.put("userName",jsonObjectParam.getString("userName"));
		}
		if(jsonObjectParam.containsKey("userMoblie") && EmptyUtil.isNotEmpty(jsonObjectParam.getString("userMoblie"))){
			jsonObject.put("userMoblie",jsonObjectParam.getString("userMoblie"));
		}
		executeDTO.setJsonString(JSON.toJSONString(jsonObject));
		JsonResult jsonResult = channelServiceConfigClient.remoteExecute(executeDTO);
		if(jsonResult !=null && jsonResult.getData() !=null && jsonResult.getCode()==0){
			JSONObject parseObject =JSONObject.parseObject(JSON.toJSONString(jsonResult.getData()));
			if("1".equals(parseObject.getString("status"))){
				JSONObject parseData = parseObject.getJSONObject("data");
				String userName = parseData.getString("userName");
				String thirdUserId = parseData.containsKey("userFlag")?parseData.getString("userFlag"):null;
				String userMoblie = parseData.containsKey("userMoblie")?parseData.getString("userMoblie"):null;
				if(EmptyUtil.isEmpty(thirdUserId) && EmptyUtil.isEmpty(userMoblie)){
					throw new BusinessException("响应结果未找到匹配的用户");
				}
				UserDTO userDto = new UserDTO();
				userDto.setEnterpriseId(enterpriseId);
				userDto.setCompanyId(configs.get(0).getCompanyId());
				userDto.setPlatformId(7L);
				userDto.setName(userName);
				userDto.setLoginName("e"+"-"+enterpriseId.longValue()+"c"+"-"+configs.get(0).getCompanyId().intValue()+"-"+thirdUserId);
				userDto.setMail("e"+"-"+enterpriseId.longValue()+"c"+"-"+configs.get(0).getCompanyId().intValue()+"-"+thirdUserId+"@dachuguanjia.cn");
				UserExtendDTO userExtendDTO = new UserExtendDTO();
				userExtendDTO.setStatus(1);
				userExtendDTO.setAccountStatus(0);
				userExtendDTO.setIsAdministrator(0);
				userExtendDTO.setWeixin(wxOpenId);
				userExtendDTO.setName(userName);
				userExtendDTO.setIsDeleted(0);
				userExtendDTO.setMobile(userMoblie);
				userExtendDTO.setRemark(thirdUserId);
				userExtendDTO.setMemberCode("companyUser-"+configs.get(0).getCompanyId()+"-"+thirdUserId);
				Long createdUserId = userFacade.createUser(userDto, userExtendDTO,configs);
				UserRoleDTO userRolePo = new UserRoleDTO();
				userRolePo.setRoleId(BusinessConstant.PLATFORM7_USER_ROLEID);
				userRolePo.setUserId(createdUserId);
				userRolePo.setCreateTime(new Date());
				userRoleWriteService.saveWithTx(userRolePo);

				UserDTO returnUser = userManage.findByWeiXinOpenId(wxOpenId, 7L);
				return UserConverter.toVO(returnUser);
			}
		}
		return null;
	}




}
