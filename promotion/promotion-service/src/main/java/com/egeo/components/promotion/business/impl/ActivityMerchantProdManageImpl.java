package com.egeo.components.promotion.business.impl;
	

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.product.dto.MerchantProductDTO;
import com.egeo.components.promotion.business.ActivityMerchantProdManage;
import com.egeo.components.promotion.converter.ActivityMerchantProdConverter;
import com.egeo.components.promotion.dto.ActivityMerchantProdDTO;
import com.egeo.components.promotion.facade.ActivityMerchantProdFacade;
import com.egeo.components.promotion.vo.ActivityMerchantProdVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;

@Service("activityMerchantProd")
public class ActivityMerchantProdManageImpl implements ActivityMerchantProdManage{

	
	@Resource(name="activityMerchantProdFacade")
	private ActivityMerchantProdFacade activityMerchantProdFacade;

	@Override
	public PageResult<ActivityMerchantProdDTO> findPageActivityMerchantProd(Pagination page,
			ActivityMerchantProdVO activityMerchantProdVO) {
		PageResult<ActivityMerchantProdDTO> pageResult = activityMerchantProdFacade.findPageActivityMerchantProd(page,
				ActivityMerchantProdConverter.toDTO(activityMerchantProdVO));
		
		List<ActivityMerchantProdDTO> list = pageResult.getList();
		List<ActivityMerchantProdDTO> lists = new ArrayList<ActivityMerchantProdDTO>();
		if(list != null){
			for (ActivityMerchantProdDTO activityMerchantProdDTO : list) {
				//根据商品id（其实是根据产品id查询产品）查询商品信息
				MerchantProductDTO merchantProdDTO = new MerchantProductDTO();
				merchantProdDTO.setId(activityMerchantProdDTO.getMerchantProdId());
				MerchantProductDTO merchantProductDTO = activityMerchantProdFacade.findByMerchantProdId(merchantProdDTO);
				activityMerchantProdDTO.setName(merchantProductDTO.getName());
				lists.add(activityMerchantProdDTO);
			}
			pageResult.setList(lists);
		}
		return pageResult;
	}

	@Override
	public List<ActivityMerchantProdDTO> findAll(ActivityMerchantProdVO activityMerchantProdVO) {
		List<ActivityMerchantProdDTO> list = activityMerchantProdFacade.findAll(ActivityMerchantProdConverter.toDTO(activityMerchantProdVO));
		List<ActivityMerchantProdDTO> lists = new ArrayList<ActivityMerchantProdDTO>();
		if(list != null){
			for (ActivityMerchantProdDTO activityMerchantProdDTO : list) {
				//根据商品id查询商品信息
				MerchantProductDTO merchantProdDTO = new MerchantProductDTO();
				merchantProdDTO.setId(activityMerchantProdDTO.getMerchantProdId());
				merchantProdDTO.setStatus(3);
				MerchantProductDTO merchantProductDTO = activityMerchantProdFacade.findByMerchantProdId(merchantProdDTO);
				if(EmptyUtil.isNotEmpty(merchantProductDTO)){
					activityMerchantProdDTO.setName(merchantProductDTO.getName());
					lists.add(activityMerchantProdDTO);
				}
				
			}
		}
		return lists;
	}

	@Override
	public PageResult<MerchantProductDTO> activityMerchantProdByMerchantProdName(Pagination page,
			ActivityMerchantProdVO activityMerchantProdVO) {
		PageResult<MerchantProductDTO> result = new PageResult<MerchantProductDTO>();
		//根据所有活动id查询已选择的活动商品id
		ActivityMerchantProdDTO activityMerchantProdDTO = new ActivityMerchantProdDTO();
		List<ActivityMerchantProdDTO> list = activityMerchantProdFacade.findAll(activityMerchantProdDTO);
		if(list.size() > 0){
			//拼接所有活动商品id字符串
			StringBuffer ids = new StringBuffer();
			for (ActivityMerchantProdDTO activityMerchantProdDTO2 : list) {
				ids.append(activityMerchantProdDTO2.getMerchantProdId());
				ids.append(",");
			}
			result = activityMerchantProdFacade.productByActivityId(page,ids.toString().substring(0,ids.toString().length()-1),activityMerchantProdVO.getName());
		}else{
			result = activityMerchantProdFacade.productByActivityId(page,null,activityMerchantProdVO.getName());
		}
		
		
		return result;
	}

	@Override
	public String saveActivityMerchantProd(ActivityMerchantProdVO activityMerchantProdVO,String list) {
		//截取字符串2边的[]
		String string = list.substring(1,list.length()-1);
		if(!string.equals("")){
			List<String> merchantProdIdList = Arrays.asList(string.split(","));
			for (String string2 : merchantProdIdList) {
				activityMerchantProdVO.setMerchantProdId(Long.valueOf(string2));
				activityMerchantProdVO.setSortValue(1);
				activityMerchantProdFacade.saveActivityMerchantProd(ActivityMerchantProdConverter.toDTO(activityMerchantProdVO));
			}
		}
		return "添加活动商品成功 ";
		
		
	}

	@Override
	public Integer deleteById(ActivityMerchantProdVO activityMerchantProdVO) {
		return activityMerchantProdFacade.deleteById(ActivityMerchantProdConverter.toDTO(activityMerchantProdVO));
	}

	@Override
	public String updateActivityMerchantProd(List<ActivityMerchantProdVO> activityMerchantProdList) {
		for (ActivityMerchantProdVO activityMerchantProdVO : activityMerchantProdList) {
			activityMerchantProdFacade.updateActivityMerchantProd(ActivityMerchantProdConverter.toDTO(activityMerchantProdVO));
		}
		return "修改排序成功";
	}
	


}
	