package com.egeo.components.user.controller.statistics;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.user.business.StatisticsManage;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;

/**
 * 数据统计
 * @author min
 *
 */
@Controller
@RequestMapping("user/statistics")
public class StatisticsController extends BaseSpringController {
	
	@Resource(name = "statistics")
	private StatisticsManage statisticsManage;
    /**
     * 统计平台商品、订单、用户数据
     * @return
     */
    @RequestMapping(value = "dataStatistics")
	@ResponseBody
    public JsonResult<Map<String, Object>> dataStatistics(Long storeId, HttpServletRequest req) {
    	Long platformId = null;
		String str = req.getHeader("platformId");
		if (EmptyUtil.isNotEmpty(str)) {
			platformId = Long.valueOf(str);
		}
		Map<String, Object> data = statisticsManage.dataStatistics(storeId,platformId);
		return JsonResult.success(data);
    	
  }
}
