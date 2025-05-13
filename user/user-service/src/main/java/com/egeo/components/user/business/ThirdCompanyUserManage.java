package com.egeo.components.user.business;

import java.util.List;

import com.egeo.components.config.dto.CompanyConfigDTO;
import com.egeo.components.user.vo.ChannelActivityVO;
import com.egeo.components.user.vo.UserVO;

public interface ThirdCompanyUserManage {

	UserVO getUserInfoByOpenId(String wxOpenId,List<CompanyConfigDTO> configs,String message);
}
