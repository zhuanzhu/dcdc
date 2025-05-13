package com.egeo.components.order.controller.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.util.DateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;
import com.egeo.utils.EmptyUtil;

/**
 * 静态接口临时类
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/api/order/soRefundStatic")
public class StaticAction extends BaseSpringController {

	/**
	 * 退款单列表
	 * 
	 * @param soRefundCode
	 * @param orderCode
	 * @param mail
	 * @param page
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/findSoRefundOfPage")
	@ResponseBody
	public JsonResult<PageResult<Map<String, Object>>> findSoRefundOfPage(String soRefundCode, String orderCode,
			String mail, Pagination page, HttpServletRequest req) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (int i = 1; i < 11; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", i);
			map.put("soRefundCode", DateUtil.formatDate(new Date(), "yyyyddMMHHmm") + new Random().nextInt(999) + i);
			map.put("orderCode", "FGJ-I-N-2017122618000000000" + i);
			map.put("childOrderCode", null);	// 预留字段
			map.put("mail", i + "171717@qq.com");
			map.put("soRefundByFubi", 100.50 + i);
			map.put("soRefundByCash", 200.50 + i);
			map.put("createTime", new Date().getTime());
			if (i % 3 == 0) {
				map.put("createUser", null);
				map.put("soRefundReason", "用户取消订单");
				map.put("remark", null);
			} else {
				map.put("createUser", "fgj");
				map.put("soRefundReason", "用户取消订单");
				map.put("remark", "备注备注备注备注备注");
			}
			list.add(map);
		}
		PageResult<Map<String, Object>> result = new PageResult<Map<String, Object>>();
		result.setList(list);
		result.setPageNo(page.getPageNo());
		result.setPageSize(page.getPageSize());
		result.setTotalSize(10);

		return JsonResult.success(result);

	}

	/**
	 * 更新退款单备注
	 * 
	 * @param id
	 * @param remark
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/updateSoRefundRemarkWithTx")
	@ResponseBody
	public JsonResult<Long> updateSoRefundRemarkWithTx(Long id, String remark, HttpServletRequest req) {
		logger.info("修改退款单备注");
		if (EmptyUtil.isEmpty(id))
			return JsonResult.fail("退款单id不能为空");
		if (EmptyUtil.isEmpty(remark))
			return JsonResult.fail("备注不能为空");
		if (remark.length() > 100) 
			return JsonResult.fail("备注长度不能超过100");
		
		return JsonResult.success(id);
	}

}
