package com.egeo.components.promotion.controller.api;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.promotion.business.BannerManage;
import com.egeo.components.promotion.converter.BannerConverter;
import com.egeo.components.promotion.dto.BannerDTO;
import com.egeo.components.promotion.vo.BannerVO;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.str.StringUtils;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/promotion/banner")
public class BannerAction extends BaseSpringController {
	
	@Resource(name="banner")
	private BannerManage bannerManage;


	// 业务方法：
	@RequestMapping(value = "/findBannerById")
	@ResponseBody
	public JsonResult<BannerVO> findBannerById(Long id ) {
		logger.info("根据id查询轮播广告!");
		BannerDTO dto = new BannerDTO();
		dto.setId(id);
		BannerDTO rt = bannerManage.findBannerById(dto);		
		return success(BannerConverter.toVO(rt));
					 
	}



	// 业务方法：查询所有未过期轮播广告
	@RequestMapping(value = "/findBannerAll")
	@ResponseBody
	public JsonResult<List<Map<String, Object>>> findBannerAll(BannerVO vo,HttpServletRequest req ) {
		logger.info("查询所有未过期轮播广告!");
		BannerDTO dto = BannerConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<Map<String, Object>> list = new ArrayList<>();
		List<BannerDTO> rt = bannerManage.findBannerAll(dto);
		for (BannerDTO bannerDTO : rt) {
			Map<String, Object> map = new HashMap<>();
			map.put("bannerId", bannerDTO.getId());
			map.put("name", bannerDTO.getName());
			map.put("type", bannerDTO.getType());
			map.put("sortValue", bannerDTO.getSortValue());
			map.put("merchantProdId", bannerDTO.getMerchantProdId());
			map.put("activityId", bannerDTO.getActivityId());
			map.put("pictureUrl", bannerDTO.getPictureUrl());
			map.put("path", bannerDTO.getPath());
			list.add(map);
		}
		return success(list);
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findBannerOfPage")
	@ResponseBody
	public JsonResult<PageResult<BannerVO>> findBannerOfPage(BannerVO vo,Pagination page,HttpServletRequest req ) {
		logger.info("分页查询所有轮播广告!");
		BannerDTO dto = BannerConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<BannerDTO> rt = bannerManage.findBannerOfPage(dto, page);
        List<BannerVO> list = BannerConverter.toVO(rt.getList());
        PageResult<BannerVO> result = new PageResult<BannerVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertBannerWithTx")
	@ResponseBody
	public JsonResult<Long> insertBannerWithTx(BannerVO vo,HttpServletRequest req ) {
		logger.info("添加轮播广告!");
		BannerDTO dto = BannerConverter.toDTO(vo);
		String str = req.getHeader("platformId");
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = bannerManage.insertBannerWithTx(dto);
		return success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateBannerByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateBannerByIdWithTx(BannerVO vo,HttpServletRequest req ) {
		logger.info("修改轮播广告!");
		SimpleDateFormat format =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );  
		BannerDTO dto = BannerConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		try {
			if(EmptyUtil.isNotEmpty(vo.getStart()) && StringUtils.isNotFigure(vo.getStart())){
				dto.setStartTime(format.parse(format.format(Long.valueOf(vo.getStart()))));
			}else{
				throw new BusinessException("活动开始时间不能为空");
			}
			if(EmptyUtil.isNotEmpty(vo.getFinish()) && StringUtils.isNotFigure(vo.getFinish())){
				dto.setFinishTime(format.parse(format.format(Long.valueOf(vo.getFinish()))));
			}else{
				throw new BusinessException("活动结束时间不能为空");
			}
			int rt = bannerManage.updateBannerWithTx(dto);	
			return success(rt);
		} catch (Exception e) {
			logger.error("添加活动数据异常！", e);
            return fail("添加活动数据异常："+ e.getMessage());
		}					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteBannerWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteBannerWithTx(BannerVO vo,HttpServletRequest req ) {
		logger.info("根据id删除轮播广告!");
		BannerDTO dto = BannerConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = bannerManage.deleteBannerWithTx(dto);	
		return success(rt);					 
	}
		
}
	