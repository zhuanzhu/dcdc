package com.egeo.components.promotion.controller.api;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.promotion.business.ActivityManage;
import com.egeo.components.promotion.dto.ActivityDTO;
import com.egeo.components.promotion.vo.ActivityMerchantProd;
import com.egeo.components.promotion.vo.ActivityMerchantProdVO;
import com.egeo.components.promotion.vo.ActivityVO;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.str.StringUtils;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/promotion/activity")
public class ActivityAction extends BaseSpringController {
	
	@Resource(name="activity")
	private ActivityManage activityManage;
	
	@RequestMapping(value = "findPageActivity")
    @ResponseBody
    public JsonResult<PageResult<ActivityDTO>> findPageActivity(Pagination page,ActivityVO activityVO,HttpServletRequest req) {
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			activityVO.setPlatformId(platformId);
		}
        logger.info("开始根据条件分页查询活动信息!");
        JsonResult<PageResult<ActivityDTO>> result = new JsonResult<PageResult<ActivityDTO>>();
            try {
                PageResult<ActivityDTO> pageResult = activityManage.findPageActivity(page, activityVO);
                result.setData(pageResult);
                return result;
            } catch (Exception e) {
                logger.error("根据条件分页查询活动信息异常！", e);
                result.setCode(1);
                result.setError("根据条件分页查询活动信息异常："+ e.getMessage());
                return result;
            }
    }
	
	@RequestMapping(value = "findAll")
    @ResponseBody
    public JsonResult<List<ActivityVO>> findAll(ActivityVO activityVO,HttpServletRequest req) {
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			activityVO.setPlatformId(platformId);
		}
        logger.info("开始根据条件查询活动信息!");
        JsonResult<List<ActivityVO>> result = new JsonResult<List<ActivityVO>>();
            try {
                List<ActivityVO> pageResult = activityManage.findAll(activityVO);
                result.setData(pageResult);
                return result;
            } catch (Exception e) {
                logger.error("根据条件查询活动信息异常！", e);
                result.setCode(1);
                result.setError("根据条件查询活动信息异常："+ e.getMessage());
                return result;
            }
    }
	
	
	@RequestMapping(value = "findById")
    @ResponseBody
    public JsonResult<ActivityVO> findById(ActivityVO activityVO) {
        logger.info("开始根据id查询活动信息!");
        JsonResult<ActivityVO> result = new JsonResult<ActivityVO>();
            try {
                ActivityVO activity = activityManage.findById(activityVO);
                result.setData(activity);
                return result;
            } catch (Exception e) {
                logger.error("根据id查询活动信息异常！", e);
                result.setCode(1);
                result.setError("根据id查询活动信息异常："+ e.getMessage());
                return result;
            }
    }
	
	@RequestMapping(value = "saveActivity")
    @ResponseBody
    public JsonResult<Long> saveActivity(String name,String sortValue,Long start,Long finish,HttpServletRequest req) {
		ActivityVO activityVO = new ActivityVO();
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			activityVO.setPlatformId(platformId);
		}
		if(EmptyUtil.isNotEmpty(name)){
			activityVO.setName(name);
		}else{
			throw new BusinessException("活动名称不能为空");
		}
		if(EmptyUtil.isNotEmpty(sortValue)){
			if(StringUtils.isNotFigure(sortValue)){
				activityVO.setSortValue(Integer.valueOf(sortValue));
			}else{
				throw new BusinessException("活动排序必须为整数");
			}
		}else{
			throw new BusinessException("活动排序不能为空");
		}
		if(EmptyUtil.isEmpty(start)){
			throw new BusinessException("活动开始时间不能为空");
		}
		if(EmptyUtil.isEmpty(finish)){
			throw new BusinessException("活动结束时间不能为空");
		}
		
		SimpleDateFormat format =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );  
        logger.info("开始添加活动数据!");
        JsonResult<Long> result = new JsonResult<Long>();
            try {
            	activityVO.setStartTime(format.parse(format.format(start)));
            	activityVO.setFinishTime(format.parse(format.format(finish)));
                Long id = activityManage.saveActivity(activityVO);
                result.setData(id);
                return result;
            } catch (Exception e) {
                logger.error("添加活动数据异常！", e);
                result.setCode(1);
                result.setError("添加活动数据异常："+ e.getMessage());
                return result;
            }
    }
	
	@RequestMapping(value = "updateActivity")
    @ResponseBody
    public JsonResult<String> updateActivity(String id, String name,String sortValue,Long start,Long finish,HttpServletRequest req) {
		ActivityVO activityVO = new ActivityVO();
		
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			activityVO.setPlatformId(platformId);
		}
		if(EmptyUtil.isNotEmpty(id)){
			activityVO.setId(Long.valueOf(id));
		}else{
			throw new BusinessException("必须选择一个活动");
		}
		if(EmptyUtil.isNotEmpty(name)){
			activityVO.setName(name);
		}else{
			throw new BusinessException("活动名称不能为空");
		}
		if(StringUtils.isNotFigure(sortValue)){
			activityVO.setSortValue(Integer.valueOf(sortValue));
		}else{
			throw new BusinessException("活动排序不能为空");
		}
		if(EmptyUtil.isEmpty(start)){
			throw new BusinessException("活动开始时间不能为空");
		}
		if(EmptyUtil.isEmpty(finish)){
			throw new BusinessException("活动结束时间不能为空");
		}
        logger.info("开始修改活动数据!");
        SimpleDateFormat format =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );  
        JsonResult<String> result = new JsonResult<String>();
            try {
            	activityVO.setStartTime(format.parse(format.format(start)));
            	activityVO.setFinishTime(format.parse(format.format(finish)));
                activityManage.updateActivity(activityVO);
                result.setData(id);
                return result;
            } catch (Exception e) {
                logger.error("修改活动数据异常！", e);
                result.setCode(1);
                result.setError("修改活动数据异常："+ e.getMessage());
                return result;
            }
    }
	
	@RequestMapping(value = "saveActivityMerchantProd")
    @ResponseBody
    public JsonResult<String> saveActivityMerchantProd(String list,Long activityId) {
        logger.info("开始添加活动商品数据!");
        JsonResult<String> result = new JsonResult<String>();
            try {
            	List<ActivityMerchantProdVO> lists = new ArrayList<ActivityMerchantProdVO>();
            	List<String> attValueIdList = Arrays.asList(list.substring(1, list.length()-1).split(","));
            	if(attValueIdList.get(0).equals("") && attValueIdList != null){
            		for (String string : attValueIdList) {
                		ActivityMerchantProdVO activityMerchantProdVO = new ActivityMerchantProdVO();
                		activityMerchantProdVO.setActivityId(activityId);
                		activityMerchantProdVO.setMerchantProdId(Long.valueOf(string));
                		lists.add(activityMerchantProdVO);
    				}
            	}
            	String id = activityManage.saveActivityMerchantProd(lists);
                result.setData(id);
                return result;
            } catch (Exception e) {
                logger.error("添加活动商品异常！", e);
                result.setCode(1);
                result.setError("添加活动商品异常："+ e.getMessage());
                return result;
            }
    }
	
	@RequestMapping(value = "delete")
    @ResponseBody
    public JsonResult<Integer> deleteById(ActivityVO activityVO) {
        logger.info("开始根据条件删除属性数据!");
        JsonResult<Integer> result = new JsonResult<Integer>();
            try {
            	Integer id = activityManage.delete(activityVO);
                result.setData(id);
                return result;
            } catch (Exception e) {
                logger.error("根据条件删除属性数据异常！", e);
                result.setCode(1);
                result.setError("根据条件删除属性数据异常："+ e.getMessage());
                return result;
            }
    }
	
	@RequestMapping(value = "PageActivityMerchantProd")
    @ResponseBody
    public JsonResult<ActivityMerchantProd> PageActivityMerchantProd(Pagination page,Integer pages,HttpServletRequest req) {
		String str = req.getHeader("platformId");	
		Long platformId = null;
		if(EmptyUtil.isNotEmpty(str)){
			platformId = Long.valueOf(str);
		}
		//先写死
		platformId= 6L;
        logger.info("根据条件分页查询活动及商品信息!");
        JsonResult<ActivityMerchantProd> result = new JsonResult<ActivityMerchantProd>();
        	
            try {
            	/*//查询多个活动
                PageResult<ActivityMerchantProd> pageResult = activityManage.PageActivityMerchantProd(page, activityVO);
                //暂时返回一条、如果所要活动大于没过期的活动则返回空
                List<ActivityMerchantProd> activityMerchantProdList = pageResult.getList();
                if(pages > activityMerchantProdList.size()){
                	result.setCode(1);
                	result.setData(null);
                }else{
                	if(activityMerchantProdList.size() > 0){
                    	result.setData(activityMerchantProdList.get(pages-1));
                    }else{
                    	result.setCode(1);
                    	result.setData(null);
                    }
                }*/
            	
            	//查询指定第几个有效活动
            	ActivityMerchantProd activityMerchantProd = activityManage.activityMerchantProdByPages(pages,platformId);
            	if(activityMerchantProd == null){
            		result.setCode(1);
                	result.setData(null);
            	}
            	result.setData(activityMerchantProd);
                return result;
            } catch (Exception e) {
                logger.error("根据条件分页查询活动及商品信息异常！", e);
                result.setCode(1);
                result.setError("根据条件分页查询活动及商品信息异常："+ e.getMessage());
                return result;
            }
    }
	
}
	