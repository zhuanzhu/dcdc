package com.egeo.components.user.business.impl;


import java.net.URLEncoder;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.egeo.components.config.dto.CompanyConfigDTO;
import com.egeo.components.user.business.ThirdCompanyUserManage;
import com.egeo.components.user.vo.UserVO;
import com.egeo.utils.http.HttpClientUtil;
//上海电信用户接口
@Service("shdxThirdCompany")
public class ThirdCompanySHDXManageImpl implements ThirdCompanyUserManage{

	@Override
	public UserVO getUserInfoByOpenId(String wxOpenId,List<CompanyConfigDTO> configs,String message) {
		// TODO Auto-generated method stub
		String url = null;
		String token = null;
		for(CompanyConfigDTO config : configs) {
			if(config.getKey().equalsIgnoreCase("api.url.getUserByOpenId")) {
				url = config.getValue();
			}else if(config.getKey().equalsIgnoreCase("api.token")) {
				token = config.getValue();
			}
		}
		if(url!=null && token!=null) {
			url+="?token="+token+"&openId="+wxOpenId;
			String jsonStr = HttpClientUtil.doGet(url);
			JSONObject parseObject = JSONObject.parseObject(jsonStr);
			if(parseObject.containsKey("code") && parseObject.getInteger("code").intValue()==0) {
				JSONObject parseData = parseObject.getJSONObject("data");
			}
		}
		return null;
	}




}
