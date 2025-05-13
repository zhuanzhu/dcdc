package com.egeo.components.product.controller.api;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.egeo.components.product.dao.write.ChannelMerchantConfigMapper;
import com.egeo.components.product.dto.ChannelMerchantConfigDTO;
import com.egeo.components.product.vo.*;
import com.egeo.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.JdPriceConfigManage;
import com.egeo.components.product.converter.JdPriceConfigConverter;
import com.egeo.components.product.dao.write.JdMerchantConfigMapper;
import com.egeo.components.product.dto.JdMerchantConfigDTO;
import com.egeo.components.product.dto.JdPriceConfigDTO;
import com.egeo.components.product.vo.JdMerchantConfigVO;
import com.egeo.components.product.vo.JdPriceConfigVO;
import com.egeo.components.product.vo.JdPriceEnterpriseConfigVO;
import com.egeo.config.RuntimeContext;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/jdPriceConfig")
public class JdPriceConfigAction extends BaseSpringController {
	
	@Resource(name="jdPriceConfig")
	private JdPriceConfigManage jdPriceConfigManage;

	@Autowired
	private JdMerchantConfigMapper jdMerchantConfigMapper;

	// 业务方法：
	/*@RequestMapping(value = "/findJdPriceConfigById")
	@ResponseBody
	public JsonResult<JdPriceConfigVO> findJdPriceConfigById(Long id ) {
		
		JdPriceConfigDTO dto = new JdPriceConfigDTO();
		dto.setId(id);
		JdPriceConfigDTO rt = jdPriceConfigManage.findJdPriceConfigById(dto);		
		return JsonResult.success(JdPriceConfigConverter.toVO(rt));
					 
	}*/



	// 业务方法：
	@RequestMapping(value = "/findJdPriceConfig")
	@ResponseBody
	public JsonResult<JdPriceConfigVO> findJdPriceConfig(JdPriceConfigVO vo,HttpServletRequest req ) {
		JdPriceConfigDTO dto = JdPriceConfigConverter.toDTO(vo);
		JdPriceConfigDTO rt = jdPriceConfigManage.findJdPriceConfigAll(dto);	
		return JsonResult.success(JdPriceConfigConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/enterpriseJdPriceConfig")
	@ResponseBody
	public JsonResult<JdPriceEnterpriseConfigVO> enterpriseJdPriceConfig(Integer enterpriseId) {
		JdPriceEnterpriseConfigVO rslt = new JdPriceEnterpriseConfigVO();
		JdPriceConfigDTO platformDto = new JdPriceConfigDTO();
		JdPriceConfigDTO enterpriseDto = new JdPriceConfigDTO();
		;
		JdMerchantConfigDTO platformRsltDto = jdMerchantConfigMapper.findPlatformConfig();
		rslt.setPlatformConfig(platformRsltDto);
		rslt.setRadio(2);
		if(enterpriseId!=null) {
			JdMerchantConfigDTO enterpriseRsltDto = jdMerchantConfigMapper.findEnterpriseConfigAndType(enterpriseId,2);	
			rslt.setEnterpriseConfig(enterpriseRsltDto);
			if(enterpriseRsltDto==null) {
				rslt.setRadio(1);
			}
		}else {
			rslt.setRadio(1);
		}
		
		return JsonResult.success(rslt);
					 
	}
	// 业务方法：
	@RequestMapping(value = "/enterpriseSelfJdPriceConfig")
	@ResponseBody
	public JsonResult<JdPriceEnterpriseConfigVO> enterpriseSelfJdPriceConfig() {
		if(RuntimeContext.cacheUser()!=null && RuntimeContext.cacheUser().getType()!=null && RuntimeContext.cacheUser().getType().intValue()==2) {
			Long enterpriseId = RuntimeContext.cacheUser().getEnterpriseId();
			JdPriceEnterpriseConfigVO rslt = new JdPriceEnterpriseConfigVO();
			JdPriceConfigDTO platformDto = new JdPriceConfigDTO();
			JdPriceConfigDTO enterpriseDto = new JdPriceConfigDTO();
			JdMerchantConfigDTO enterpriseDefaultRsltDto = jdMerchantConfigMapper.findEnterpriseConfigAndType(enterpriseId.intValue(),2);
			if(enterpriseDefaultRsltDto==null) {
				JdMerchantConfigDTO platformRsltDto = jdMerchantConfigMapper.findPlatformConfig();
				rslt.setPlatformConfig(platformRsltDto);
			}else {
				rslt.setPlatformConfig(enterpriseDefaultRsltDto);
			}
			rslt.setRadio(2);
			if(enterpriseId!=null) {
				JdMerchantConfigDTO enterpriseRsltDto = jdMerchantConfigMapper.findEnterpriseConfigAndType(enterpriseId.intValue(),3);
				rslt.setEnterpriseConfig(enterpriseRsltDto);
				if(enterpriseRsltDto==null) {
					rslt.setRadio(1);
				}
			}
			
			return JsonResult.success(rslt);
		}
		
		return JsonResult.fail("接口异常 403");
		
					 
	}
	// 业务方法：
		@RequestMapping(value = "/enterpriseJdPriceConfigUpdate")
		@ResponseBody
		public JsonResult<JdPriceEnterpriseConfigVO> enterpriseJdPriceConfigUpdate(JdMerchantConfigVO vo ) {
			if(vo.getRadio()!=null) {
				if(vo.getRadio().intValue()==1) {
					//使用平台缺省配置，删除代理商所有配置
					jdMerchantConfigMapper.deleteEnterpriseConfig(vo.getMerchantId());
				}else {
					JdMerchantConfigDTO enterpriseConfig = new JdMerchantConfigDTO(vo);
					enterpriseConfig.setUpdateMillis(new Date());
					jdMerchantConfigMapper.updateEnterprise(enterpriseConfig);
				}
			}else {
				if(vo!=null) {
					JdMerchantConfigDTO enterpriseConfig = new JdMerchantConfigDTO(vo);
					enterpriseConfig.setCreateMillis(new Date());
					enterpriseConfig.setType(2);
					jdMerchantConfigMapper.insertEnterprise(enterpriseConfig);
				}
			}
			
			return JsonResult.success();
						 
		}

		// 业务方法：
			@RequestMapping(value = "/enterpriseSelfJdPriceConfigUpdate")
			@ResponseBody
			public JsonResult<JdPriceEnterpriseConfigVO> enterpriseSelfJdPriceConfigUpdate(JdMerchantConfigVO vo ) {
				if(RuntimeContext.cacheUser()!=null && RuntimeContext.cacheUser().getType()!=null && RuntimeContext.cacheUser().getType().intValue()==2) {
					JdMerchantConfigDTO enterpriseDefaultRsltDto = jdMerchantConfigMapper.findEnterpriseConfigAndType(RuntimeContext.cacheUser().getEnterpriseId().intValue(),2);
					if(enterpriseDefaultRsltDto==null) {
						enterpriseDefaultRsltDto = jdMerchantConfigMapper.findPlatformConfig();
					}
					
					if(vo.getRadio()!=null) {
						if(vo.getRadio().intValue()==1) {
							//使用平台缺省配置，删除代理商所有配置
							jdMerchantConfigMapper.deleteEnterpriseSelfConfig(RuntimeContext.cacheUser().getEnterpriseId().intValue());

							return JsonResult.success();
						}else {
							JdMerchantConfigDTO enterpriseConfig = new JdMerchantConfigDTO(vo);
							enterpriseConfig.setUpdateMillis(new Date());
							enterpriseConfig.setType(3);
							enterpriseConfig.setMerchantId(RuntimeContext.cacheUser().getEnterpriseId().intValue());
							if(enterpriseConfig.getPriceAddtion()<=enterpriseDefaultRsltDto.getPriceAddtionMax() && 
									enterpriseConfig.getPriceAddtion()>=enterpriseDefaultRsltDto.getPriceAddtionMin() && 
									enterpriseConfig.getJdPriceMinDouble()>=enterpriseDefaultRsltDto.getJdPriceMinDouble() && 
									enterpriseConfig.getJdPriceMaxDouble()<=enterpriseDefaultRsltDto.getJdPriceMaxDouble() && 
									enterpriseConfig.getGrossMarginMin()>=enterpriseDefaultRsltDto.getGrossMarginMin() && 
									enterpriseConfig.getGrossMarginMax()<=enterpriseDefaultRsltDto.getGrossMarginMax() ) {
								JdMerchantConfigDTO hasData = jdMerchantConfigMapper.findEnterpriseConfigAndType(RuntimeContext.cacheUser().getEnterpriseId().intValue(),3);
								if(hasData==null) {
									enterpriseConfig.setPriceAddtionMax(null);
									enterpriseConfig.setPriceAddtionMin(null);
									enterpriseConfig.setCreateMillis(new Date());
									jdMerchantConfigMapper.insertEnterprise(enterpriseConfig);
								}else {
									jdMerchantConfigMapper.updateEnterprise(enterpriseConfig);
								}
								
								return JsonResult.success();
							}else {
								return JsonResult.fail("设置范围不能超过平台设置范围");
							}
							
						}
					}else {
						if(vo!=null) {
							JdMerchantConfigDTO enterpriseConfig = new JdMerchantConfigDTO(vo);
							enterpriseConfig.setCreateMillis(new Date());
							enterpriseConfig.setMerchantId(RuntimeContext.cacheUser().getEnterpriseId().intValue());
							enterpriseConfig.setType(3);
							if(enterpriseConfig.getPriceAddtion()<=enterpriseDefaultRsltDto.getPriceAddtionMax() && 
									enterpriseConfig.getPriceAddtion()>=enterpriseDefaultRsltDto.getPriceAddtionMin() && 
									enterpriseConfig.getJdPriceMinDouble()>=enterpriseDefaultRsltDto.getJdPriceMinDouble() && 
									enterpriseConfig.getJdPriceMaxDouble()<=enterpriseDefaultRsltDto.getJdPriceMaxDouble() && 
									enterpriseConfig.getGrossMarginMin()>=enterpriseDefaultRsltDto.getGrossMarginMin() && 
									enterpriseConfig.getGrossMarginMax()<=enterpriseDefaultRsltDto.getGrossMarginMax() ) {
								enterpriseConfig.setCreateMillis(new Date());
								jdMerchantConfigMapper.insertEnterprise(enterpriseConfig);
								return JsonResult.success();
							}else {
								return JsonResult.fail("设置范围不能超过平台设置范围");
							}
							
							
						}
					}
					
				}
				
				return JsonResult.fail("接口异常 403");
							 
			}
		// 业务方法：
			@RequestMapping(value = "/platformJdPriceConfigUpdate")
			@ResponseBody
			public JsonResult<JdPriceEnterpriseConfigVO> platformJdPriceConfigUpdate(JdMerchantConfigVO vo ) {
				if(vo!=null) {
					JdMerchantConfigDTO enterpriseConfig = new JdMerchantConfigDTO(vo);
					enterpriseConfig.setUpdateMillis(new Date());
					jdMerchantConfigMapper.updatePlatform(enterpriseConfig);
				}
				
				return JsonResult.success();
							 
			}
	// 业务方法：
	/*@RequestMapping(value = "/findJdPriceConfigOfPage")
	@ResponseBody
	public JsonResult<PageResult<JdPriceConfigVO>> findJdPriceConfigOfPage(JdPriceConfigVO vo,Pagination page,HttpServletRequest req ) {
		JdPriceConfigDTO dto = JdPriceConfigConverter.toDTO(vo);
		PageResult<JdPriceConfigDTO> rt = jdPriceConfigManage.findJdPriceConfigOfPage(dto, page);
        List<JdPriceConfigVO> list = JdPriceConfigConverter.toVO(rt.getList());
        PageResult<JdPriceConfigVO> result = new PageResult<JdPriceConfigVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}*/


	// 业务方法：返回是插入行的id
	/*@RequestMapping(value = "/insertJdPriceConfigWithTx")
	@ResponseBody
	public JsonResult<Long> insertJdPriceConfigWithTx(JdPriceConfigVO vo,HttpServletRequest req ) {
		JdPriceConfigDTO dto = JdPriceConfigConverter.toDTO(vo);
		Long rt = jdPriceConfigManage.insertJdPriceConfigWithTx(dto);	
		return JsonResult.success(rt);					 
	}*/
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateJdPriceConfigByIdWithTx")
	@ResponseBody
	public JsonResult<String> updateJdPriceConfigByIdWithTx(Integer standardCompanyRate,
														  Integer democompanysCompanyRate,
														  Integer competingCompanyRate,
														  HttpServletRequest req ) {
		if(EmptyUtil.isEmpty(standardCompanyRate)||EmptyUtil.isEmpty(democompanysCompanyRate)||EmptyUtil.isEmpty(competingCompanyRate)){
			return JsonResult.fail("设置值不能为空");

		}
		JdPriceConfigDTO dto = new JdPriceConfigDTO();
		dto.setId(1L);
		JdPriceConfigDTO jdPriceConfigById = jdPriceConfigManage.findJdPriceConfigById(dto);
		//更新所有商品价格
		jdPriceConfigManage.updateJdProductPriceByRate(competingCompanyRate,democompanysCompanyRate,standardCompanyRate);

		dto.setCompetingCompanyRate(competingCompanyRate);
		dto.setDemocompanysCompanyRate(democompanysCompanyRate);
		dto.setStandardCompanyRate(standardCompanyRate);
		jdPriceConfigManage.updateJdPriceConfigWithTx(dto);
		return JsonResult.success("更新成功");
	}

	// 业务方法：
	/*@RequestMapping(value = "/deleteJdPriceConfigWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteJdPriceConfigWithTx(JdPriceConfigVO vo,HttpServletRequest req ) {
		JdPriceConfigDTO dto = JdPriceConfigConverter.toDTO(vo);
		int rt = jdPriceConfigManage.deleteJdPriceConfigWithTx(dto);	
		return JsonResult.success(rt);					 
	}*/

	@RequestMapping(value = "/platformChannelPriceConfigUpdate")
	@ResponseBody
	public JsonResult<JdPriceEnterpriseConfigVO> platformChannelPriceConfigUpdate(ChannelMerchantConfigVO vo ) {
		/*if(vo!=null) {
			JdMerchantConfigDTO enterpriseConfig = new JdMerchantConfigDTO(vo);
			enterpriseConfig.setUpdateMillis(new Date());
			jdMerchantConfigMapper.updatePlatform(enterpriseConfig);
		}*/
		if(vo == null){
			return JsonResult.success();
		}
		if(StringUtils.isEmpty(vo.getChannelCode()) || StringUtils.isBlank(vo.getChannelCode())){
			return JsonResult.fail("未指定渠道");
		}
		ChannelMerchantConfigDTO old = channelMerchantConfigMapper.findPlatformConfig(vo.getChannelCode());
		ChannelMerchantConfigDTO enterpriseConfig = new ChannelMerchantConfigDTO(vo);
		enterpriseConfig.setUpdateMillis(new Date());
		if(old !=null){
			enterpriseConfig.setId(old.getId());
			channelMerchantConfigMapper.updatePlatform(enterpriseConfig);
		}else{
			channelMerchantConfigMapper.insertEnterprise(enterpriseConfig);
		}
		return JsonResult.success();
	}

	@Autowired
	public ChannelMerchantConfigMapper channelMerchantConfigMapper;

	@RequestMapping(value = "/enterpriseSelfChannelPriceConfigUpdate")
	@ResponseBody
	public JsonResult<JdPriceEnterpriseConfigVO> enterpriseSelfChannelPriceConfigUpdate(ChannelMerchantConfigVO vo ) {
		if(RuntimeContext.cacheUser()!=null && RuntimeContext.cacheUser().getType()!=null && RuntimeContext.cacheUser().getType().intValue()==2) {
			ChannelMerchantConfigDTO enterpriseDefaultRsltDto = channelMerchantConfigMapper.findEnterpriseConfigAndType(RuntimeContext.cacheUser().getEnterpriseId().intValue(),2,vo.getChannelCode());
			if(enterpriseDefaultRsltDto==null) {
				enterpriseDefaultRsltDto = channelMerchantConfigMapper.findPlatformConfig(vo.getChannelCode());
				//enterpriseDefaultRsltDto = new ChannelMerchantConfigDTO(enterpriseDefaultRsltDto2);
			}

			if(vo.getRadio()!=null) {
				if(vo.getRadio().intValue()==1) {
					//使用平台缺省配置，删除代理商所有配置
					channelMerchantConfigMapper.deleteEnterpriseSelfConfig(RuntimeContext.cacheUser().getEnterpriseId().intValue(),vo.getChannelCode());

					return JsonResult.success();
				}else {
					ChannelMerchantConfigDTO enterpriseConfig = new ChannelMerchantConfigDTO(vo);
					enterpriseConfig.setUpdateMillis(new Date());
					enterpriseConfig.setType(3);
					enterpriseConfig.setMerchantId(RuntimeContext.cacheUser().getEnterpriseId().intValue());
					if(enterpriseConfig.getPriceAddtion()<=enterpriseDefaultRsltDto.getPriceAddtionMax() &&
							enterpriseConfig.getPriceAddtion()>=enterpriseDefaultRsltDto.getPriceAddtionMin() &&
							enterpriseConfig.getChannelPriceMinDouble()>=enterpriseDefaultRsltDto.getChannelPriceMinDouble() &&
							enterpriseConfig.getChannelPriceMaxDouble()<=enterpriseDefaultRsltDto.getChannelPriceMaxDouble() &&
							enterpriseConfig.getGrossMarginMin()>=enterpriseDefaultRsltDto.getGrossMarginMin() &&
							enterpriseConfig.getGrossMarginMax()<=enterpriseDefaultRsltDto.getGrossMarginMax() ) {
						ChannelMerchantConfigDTO hasData = channelMerchantConfigMapper.findEnterpriseConfigAndType(RuntimeContext.cacheUser().getEnterpriseId().intValue(),3,vo.getChannelCode());
						if(hasData==null) {
							enterpriseConfig.setPriceAddtionMax(null);
							enterpriseConfig.setPriceAddtionMin(null);
							enterpriseConfig.setCreateMillis(new Date());
							channelMerchantConfigMapper.insertEnterprise(enterpriseConfig);
						}else {
							channelMerchantConfigMapper.updateEnterprise(enterpriseConfig);
						}

						return JsonResult.success();
					}else {
						return JsonResult.fail("设置范围不能超过平台设置范围");
					}

				}
			}else {
				if(vo!=null) {
					ChannelMerchantConfigDTO enterpriseConfig = new ChannelMerchantConfigDTO(vo);
					enterpriseConfig.setCreateMillis(new Date());
					enterpriseConfig.setMerchantId(RuntimeContext.cacheUser().getEnterpriseId().intValue());
					enterpriseConfig.setType(3);
					if(enterpriseConfig.getPriceAddtion()<=enterpriseDefaultRsltDto.getPriceAddtionMax() &&
							enterpriseConfig.getPriceAddtion()>=enterpriseDefaultRsltDto.getPriceAddtionMin() &&
							enterpriseConfig.getChannelPriceMinDouble()>=enterpriseDefaultRsltDto.getChannelPriceMinDouble() &&
							enterpriseConfig.getChannelPriceMaxDouble()<=enterpriseDefaultRsltDto.getChannelPriceMaxDouble() &&
							enterpriseConfig.getGrossMarginMin()>=enterpriseDefaultRsltDto.getGrossMarginMin() &&
							enterpriseConfig.getGrossMarginMax()<=enterpriseDefaultRsltDto.getGrossMarginMax() ) {
						enterpriseConfig.setCreateMillis(new Date());
						channelMerchantConfigMapper.insertEnterprise(enterpriseConfig);
						return JsonResult.success();
					}else {
						return JsonResult.fail("设置范围不能超过平台设置范围");
					}


				}
			}

		}

		return JsonResult.fail("接口异常 403");

	}

	@RequestMapping(value = "/enterpriseChannelPriceConfig")
	@ResponseBody
	public JsonResult<ChannelPriceEnterpriseConfigVO> enterpriseChannelPriceConfig(Integer enterpriseId, String channelCode) {
		ChannelPriceEnterpriseConfigVO rslt = new ChannelPriceEnterpriseConfigVO();
		ChannelMerchantConfigDTO platformRsltDto = channelMerchantConfigMapper.findPlatformConfig(channelCode);
		rslt.setPlatformConfig(platformRsltDto);
		rslt.setRadio(2);
		if(enterpriseId!=null) {
			ChannelMerchantConfigDTO enterpriseRsltDto = channelMerchantConfigMapper.findEnterpriseConfigAndType(enterpriseId,2,channelCode);
			rslt.setEnterpriseConfig(enterpriseRsltDto);
			if(enterpriseRsltDto==null) {
				rslt.setRadio(1);
			}
		}else {
			rslt.setRadio(1);
		}

		return JsonResult.success(rslt);

	}


	@RequestMapping(value = "/enterpriseChannelPriceConfigList")
	@ResponseBody
	public JsonResult<List<ChannelMerchantConfigDTO>> enterpriseChannelPriceConfigList(Integer enterpriseId,String channelCode) {
		List<ChannelMerchantConfigDTO> list = new ArrayList<>();
		List<ChannelMerchantConfigDTO> enterpriseRsltListDto =null;
		if(enterpriseId !=null){
			enterpriseRsltListDto = channelMerchantConfigMapper.findEnterpriseConfigAndCode(enterpriseId,channelCode);
		}else{
			ChannelMerchantConfigDTO platformRsltDto = channelMerchantConfigMapper.findPlatformConfig(channelCode);
			enterpriseRsltListDto = channelMerchantConfigMapper.findEnterpriseConfigAllByChannelCode(channelCode);
			if(null !=platformRsltDto){
				//list.add(platformRsltDto);
			}
		}
		if(!CollectionUtils.isEmpty(enterpriseRsltListDto)){
			list.addAll(enterpriseRsltListDto);
		}
		return JsonResult.success(list);

	}

	@RequestMapping(value = "/enterpriseChannelPriceConfigUpdate")
	@ResponseBody
	public JsonResult<JdPriceEnterpriseConfigVO> enterpriseChannelPriceConfigUpdate(ChannelMerchantConfigVO vo ) {
	/*	if(vo.getMerchantId()==null){
			vo.setMerchantId(RuntimeContext.cacheUser().getEnterpriseId().intValue());
		}*/
		if(vo.getRadio()!=null) {
			if(vo.getRadio().intValue()==1) {
				//使用平台缺省配置，删除代理商所有配置
				channelMerchantConfigMapper.deleteEnterpriseConfig(vo.getMerchantId());
			}else {
				ChannelMerchantConfigDTO enterpriseConfig = new ChannelMerchantConfigDTO(vo);
				enterpriseConfig.setUpdateMillis(new Date());
				channelMerchantConfigMapper.updateEnterprise(enterpriseConfig);
			}
		}else {
			if(vo!=null) {
				ChannelMerchantConfigDTO enterpriseConfig = new ChannelMerchantConfigDTO(vo);
				enterpriseConfig.setCreateMillis(new Date());
				enterpriseConfig.setType(2);
				channelMerchantConfigMapper.insertEnterprise(enterpriseConfig);
			}
		}

		return JsonResult.success();

	}

	@RequestMapping(value = "/enterpriseSelfChannelPriceConfig")
	@ResponseBody
	public JsonResult<ChannelPriceEnterpriseConfigVO> enterpriseSelfChannelPriceConfig(String channelCode) {
		if(RuntimeContext.cacheUser()!=null && RuntimeContext.cacheUser().getType()!=null && RuntimeContext.cacheUser().getType().intValue()==2) {
			Long enterpriseId = RuntimeContext.cacheUser().getEnterpriseId();
			ChannelPriceEnterpriseConfigVO rslt = new ChannelPriceEnterpriseConfigVO();
			ChannelMerchantConfigDTO enterpriseDefaultRsltDto = channelMerchantConfigMapper.findEnterpriseConfigAndType(enterpriseId.intValue(),2,channelCode);
			if(enterpriseDefaultRsltDto==null) {
				ChannelMerchantConfigDTO platformRsltDto = channelMerchantConfigMapper.findPlatformConfig(channelCode);
				rslt.setPlatformConfig(platformRsltDto);
			}else {
				rslt.setPlatformConfig(enterpriseDefaultRsltDto);
			}
			rslt.setRadio(2);
			if(enterpriseId!=null) {
				ChannelMerchantConfigDTO enterpriseRsltDto = channelMerchantConfigMapper.findEnterpriseConfigAndType(enterpriseId.intValue(),3,channelCode);
				rslt.setEnterpriseConfig(enterpriseRsltDto);
				if(enterpriseRsltDto==null) {
					rslt.setRadio(1);
				}
			}

			return JsonResult.success(rslt);
		}

		return JsonResult.fail("接口异常 403");


	}
}
