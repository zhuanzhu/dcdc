package com.egeo.components.config.controller.client;

import java.util.List;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.config.client.CompanyConfigClient;
import com.egeo.components.config.dao.write.CompanyConfigMapper;
import com.egeo.components.config.dto.CompanyConfigDTO;

@Controller
@RequestMapping("/client/config/company") 
public class CompanyConfigController implements CompanyConfigClient{
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CompanyConfigMapper companyConfigService;

	@Override
	@RequestMapping(value = { "/configs" }, method = { RequestMethod.POST }) 
	@ResponseBody
	public List<CompanyConfigDTO> queryCompanyconfigs(Long companyId) {
		// TODO Auto-generated method stub
		return companyConfigService.findCompanyConfigs(companyId);
	}
	@Override
	@RequestMapping(value = { "/config/value" }, method = { RequestMethod.POST }) 
	@ResponseBody
	public String queryCompanyconfigValue(Long companyId, String key) {
		// TODO Auto-generated method stub
		return companyConfigService.findCompanyConfigValue(companyId, key);
	}
	@Override
	@RequestMapping(value = { "/config/by/code" }, method = { RequestMethod.POST }) 
	@ResponseBody
	public List<CompanyConfigDTO> queryCompanyconfigsByCode(String code) {
		// TODO Auto-generated method stub
		CompanyConfigDTO tmp = companyConfigService.findCompanyCodeValue(code);
		logger.info("config/by/code=="+JSON.toJSONString(tmp));
		if(tmp!=null && tmp.getCompanyId()!=null) {
			List<CompanyConfigDTO> configDTOS=queryCompanyconfigs(tmp.getCompanyId());
			logger.info("config/by/code=="+JSON.toJSONString(configDTOS));
			return configDTOS;
		}
		return null;
	} 
}