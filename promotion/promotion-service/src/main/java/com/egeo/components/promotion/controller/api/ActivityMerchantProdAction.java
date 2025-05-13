package com.egeo.components.promotion.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.dto.MerchantProductDTO;
import com.egeo.components.promotion.business.ActivityMerchantProdManage;
import com.egeo.components.promotion.dto.ActivityMerchantProdDTO;
import com.egeo.components.promotion.vo.ActivityMerchantProdVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;



@Controller
@RequestMapping("/api/promotion/activityMerchantProd")
public class ActivityMerchantProdAction extends BaseSpringController {
	
	// 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();
	
	@Resource(name="activityMerchantProd")
	private ActivityMerchantProdManage activityMerchantProdManage;
	
	@RequestMapping(value = "findPageActivityMerchantProd")
    @ResponseBody
    public JsonResult<PageResult<ActivityMerchantProdDTO>> findPageActivityMerchantProd(Pagination page,ActivityMerchantProdVO activityMerchantProdVO, HttpServletRequest req) {
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			activityMerchantProdVO.setPlatformId(platformId);
		}
        logger.info("开始根据条件分页查询活动商品信息!");
        JsonResult<PageResult<ActivityMerchantProdDTO>> result = new JsonResult<PageResult<ActivityMerchantProdDTO>>();
            try {
                PageResult<ActivityMerchantProdDTO> pageResult = activityMerchantProdManage.findPageActivityMerchantProd(page, activityMerchantProdVO);
                result.setData(pageResult);
                return result;
            } catch (Exception e) {
                logger.error("根据条件分页查询活动商品信息异常！", e);
                result.setCode(1);
                result.setError("根据条件分页查询活动商品信息异常："+ e.getMessage());
                return result;
            }
    }
	
	@RequestMapping(value = "activityMerchantProdByMerchantProdName")
    @ResponseBody
    public JsonResult<PageResult<MerchantProductDTO>> ActivityMerchantProdByMerchantProdName(Pagination page,ActivityMerchantProdVO activityMerchantProdVO, HttpServletRequest req) {
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			activityMerchantProdVO.setPlatformId(platformId);
		}
        logger.info("根据条件商品名称分页查询可选择的商品信息!");
        JsonResult<PageResult<MerchantProductDTO>> result = new JsonResult<PageResult<MerchantProductDTO>>();
            try {
                PageResult<MerchantProductDTO> pageResult = activityMerchantProdManage.activityMerchantProdByMerchantProdName(page, activityMerchantProdVO);
                result.setData(pageResult);
                return result;
            } catch (Exception e) {
                logger.error("根据条件商品名称分页查询可选择的商品信息异常！", e);
                result.setCode(1);
                result.setError("根据条件商品名称分页查询可选择的商品信息异常："+ e.getMessage());
                return result;
            }
    }
	
	@RequestMapping(value = "saveActivityMerchantProd")
    @ResponseBody
    public JsonResult<String> saveActivityMerchantProd(ActivityMerchantProdVO activityMerchantProdVO, String list, HttpServletRequest req) {
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			activityMerchantProdVO.setPlatformId(platformId);
		}
        logger.info("开始保存活动与商品的关系信息");
        JsonResult<String> result = new JsonResult<String>();
            try {
                
                String rows = activityMerchantProdManage.saveActivityMerchantProd(activityMerchantProdVO,list);
                result.setData(rows);
                return result;
            } catch (Exception e) {
                logger.error("保存活动与商品的关系信息异常！", e);
                result.setCode(1);
                result.setError("保存活动与商品的关系信息异常："+ e.getMessage());
                return result;
            }
    }
	
	@RequestMapping(value = "updateActivityMerchantProd")
    @ResponseBody
    public JsonResult<String> updateActivityMerchantProd(String list ) {
        logger.info("开始修改活动商品排序信息");
        List<ActivityMerchantProdVO> activityMerchantProdList = null;
        JsonResult<String> result = new JsonResult<String>();
            try {
            	if(list != null){
                    JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, ActivityMerchantProdVO.class);
                    activityMerchantProdList = MAPPER.readValue(list, javaType);
                }
                String rows = activityMerchantProdManage.updateActivityMerchantProd(activityMerchantProdList);
                result.setData(rows);
                return result;
            } catch (Exception e) {
                logger.error("修改活动商品排序信息异常！", e);
                result.setCode(1);
                result.setError("修改活动商品排序信息异常："+ e.getMessage());
                return result;
            }
    }
	
	@RequestMapping(value = "deleteById")
    @ResponseBody
    public JsonResult<Integer> deleteById(ActivityMerchantProdVO activityMerchantProdVO) {
        logger.info("开始根据id删除活动商品信息");
        JsonResult<Integer> result = new JsonResult<Integer>();
            try {
            	Integer rows = activityMerchantProdManage.deleteById(activityMerchantProdVO);
                result.setData(rows);
                return result;
            } catch (Exception e) {
                logger.error("根据id删除活动商品信息异常！", e);
                result.setCode(1);
                result.setError("根据id删除活动商品信息异常："+ e.getMessage());
                return result;
            }
    }
	
	@RequestMapping(value = "findAll")
    @ResponseBody
    public JsonResult<List<ActivityMerchantProdDTO>> findAll(ActivityMerchantProdVO activityMerchantProdVO, HttpServletRequest req) {
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			activityMerchantProdVO.setPlatformId(platformId);
		}
        logger.info("开始根据条件查询活动商品信息");
        JsonResult<List<ActivityMerchantProdDTO>> result = new JsonResult<List<ActivityMerchantProdDTO>>();
            try {
                List<ActivityMerchantProdDTO> list = activityMerchantProdManage.findAll(activityMerchantProdVO);
                result.setData(list);
                return result;
            } catch (Exception e) {
                logger.error("根据条件查询活动商品信息异常！", e);
                result.setCode(1);
                result.setError("根据条件查询活动商品信息异常："+ e.getMessage());
                return result;
            }
    }
	
}
	