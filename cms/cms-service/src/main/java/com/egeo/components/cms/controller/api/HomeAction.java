package com.egeo.components.cms.controller.api;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.cms.dao.read.HomeConfigReadDAO;
import com.egeo.components.cms.po.BannerImgPO;
import com.egeo.components.cms.po.HomeConfigPO;
import com.egeo.orm.Pagination;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/cms/home")
public class HomeAction extends BaseSpringController {
	
	@Autowired
	private HomeConfigReadDAO homeConfigReadDAO;
	
	/**
	 * 查询轮播图列表
	 * @param vo
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/queryHomeTempate")
	@ResponseBody
	public JsonResult<List<HomeConfigPO>> queryHomeTempate() {
		return JsonResult.success(homeConfigReadDAO.findAll(new HomeConfigPO(), null));
	}
	@RequestMapping(value = "/setHomeTempate")
	@ResponseBody
	public JsonResult<List<HomeConfigPO>> setHomeTempate(Long companyId,Integer tempId) {
		return JsonResult.success(homeConfigReadDAO.findAll(new HomeConfigPO(), null));
	}
	

		
}
	