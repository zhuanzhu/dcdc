package com.egeo.components.order.controller.api;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.order.business.SoItemManage;
import com.egeo.components.order.vo.SoItem;
import com.egeo.components.order.vo.SoItemVO;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;



@Controller
@RequestMapping("/api/order/soItem")
public class SoItemAction extends BaseSpringController {
	
	// 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();
	
	@Resource(name="soItem")
	private SoItemManage soItemManage;
	
	@RequestMapping(value = "updateSoItem")
    @ResponseBody
    public JsonResult<Long> updateSoItem(SoItemVO SoItemVO,HttpServletRequest req) {
	String str = req.getHeader("platformId");		
	if(EmptyUtil.isNotEmpty(str)){
		Long platformId = Long.valueOf(str);
		SoItemVO.setPlatformId(platformId);
	}
        logger.info("开始修改订单项信息");
        JsonResult<Long> result = new JsonResult<Long>();
            try {
                Long rows = soItemManage.updateSoItem(SoItemVO);
                result.setData(rows);
                return result;
            } catch (Exception e) {
                logger.error("修改订单项信息异常！", e);
                result.setCode(1);
                result.setError("修改订单项信息异常："+ e.getMessage());
                return result;
            }
    }
	
	@RequestMapping(value = "updateSoItemAll")
    @ResponseBody
    public JsonResult<String> updateSoItemAll(Long packId,String list,HttpServletRequest req) {
	String str = req.getHeader("platformId");
	Long platformId = null;
	if(EmptyUtil.isNotEmpty(str)){
		platformId = Long.valueOf(str);
	}
        logger.info("开始批量修改订单项信息");
        JsonResult<String> result = new JsonResult<String>();
        List<SoItem> lists = null;
            try {
            	
            	if(list != null){
                    JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, SoItem.class);
                    lists = MAPPER.readValue(list, javaType);
                }
            	
                String rows = soItemManage.updateSoItemAll(packId,lists,platformId);
                result.setData(rows);
                return result;
            } catch (Exception e) {
                logger.error("批量修改订单项信息异常！", e);
                result.setCode(1);
                result.setError("批量修改订单项信息异常："+ e.getMessage());
                return result;
            }
    }
	
	/**
	 * 根据母单id查询所有的pu列表
	 * @param SoId
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "findAllSoItemBySoId")
	@ResponseBody
	public JsonResult<Map<String,Object>> findAllSoItemBySoId(Long soId,HttpServletRequest req) {
		logger.info("根据母单id查询所有的pu列表");
		return soItemManage.findAllSoItemBySoId(soId);
	}
	@RequestMapping(value = "findAllChildSoItemBySoId")
	@ResponseBody
	public JsonResult<Map<String,Object>> findAllChildSoItemBySoId(Long soChildId,HttpServletRequest req) {
		logger.info("根据子单id查询所有的pu列表");
		return soItemManage.findAllSoChildItemBySoId(soChildId);
	}
	
	/**
	 * 根据母单id查询所有的unit商品列表
	 * @param SoId
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "findSoItemBySoIdAndUnit")
	@ResponseBody
	public JsonResult<Map<String,Object>> findSoItemBySoIdAndUnit(Long soId,HttpServletRequest req) {
		logger.info("根据母单id查询所有的unit商品列表");
		return soItemManage.findSoItemBySoIdAndUnit(soId);
		
	}
	
	
	
}
	