package com.egeo.components.user.controller.api;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.egeo.components.user.business.UserLoginManage;
import com.egeo.components.user.vo.UserLoginVO;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.excel.ExcelExportSXXSSF;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/user/userLogin")
public class UserLoginAction extends BaseSpringController {
	
	@Resource(name="userLogin")
	private UserLoginManage userLoginManage;
	
	/**
	 * 查询单个用户的登陆信息
	 * @param page
	 * @param userVO
	 * @param userExtendVO
	 * @param userCookieVO
	 * @return
	 */
	@RequestMapping(value = "userLoginList")
	@ResponseBody
	public JsonResult<PageResult<Map<String, Object>>> findOfPage(Pagination page,UserLoginVO userVO) {
	    logger.info("分页查看该用户所有登陆信息 :" + JSON.toJSONString(userVO));
		JsonResult<PageResult<Map<String, Object>>> result = new JsonResult<PageResult<Map<String, Object>>>();
		if(StringUtils.isNotBlank(userVO.getIos()) && "IOS".equals(userVO.getIos())) {
			userVO.setIos("iPhone");
		}
	    try {
                PageResult<Map<String, Object>> pageResult = userLoginManage.findOfPage(userVO,page);
                result.setData(pageResult);
                return result;
            } catch (Exception e) {
                logger.error("查询所有用户异常！", e);
                result.setCode(1);
                result.setError("查询所有用户失败:" + e.getMessage());
                return result;
            }
	}
	
	/**
	 * 批量导出用户登陆信息
	 * @param userIds
	 * @return
	 */
	@RequestMapping(value = "userLoginByUserIds")
	@ResponseBody
	public JsonResult<String> finduserLoginByUserIds(String userIds,Long startTime,Long endTime,HttpServletResponse response) {
	    logger.info("查看所选用户所有登陆信息:" + userIds);
	    JsonResult<String> result = new JsonResult<>();
	    if(StringUtils.isBlank(userIds)) {
	    	return JsonResult.fail("未选取导出用户");
	    }
	    String[] idsStr = userIds.split("-");
	    List<Long> ids = new ArrayList<Long>();
	    for(String one :idsStr) {
	    	ids.add(Long.valueOf(one));
	    }
	    List<Map<String, Object>> list = userLoginManage.findByUserIds(ids,startTime,endTime);
	    	//导出文件的前缀
	  		String filePrefix="员工登陆记录";
	  		//-1表示关闭自动刷新，手动控制写磁盘的时机，其它数据表示多少数据在内存保存，超过的则写入磁盘
	  		int flushRows=100;
	  		
	  		//指导导出数据的title
	  		List<String> fieldNames=new ArrayList<String>();
	  		fieldNames.add("姓名");
	  		fieldNames.add("员工编号");
	  		fieldNames.add("邮箱");
	  		fieldNames.add("所属公司");
	  		fieldNames.add("登陆时间");
	  		fieldNames.add("IP地址");
	  		fieldNames.add("客户端类型");
	  		fieldNames.add("所属门店");
	  		fieldNames.add("操作系统");
	  		fieldNames.add("登陆来源");
	  		fieldNames.add("关键信息");
	  		
	  		//告诉导出类数据list中对象的属性，让ExcelExportSXXSSF通过反射获取对象的值
	  		List<String> fieldCodes=new ArrayList<String>();
	  		fieldCodes.add("name");
	  		fieldCodes.add("memberCode");
	  		fieldCodes.add("mail");
	  		fieldCodes.add("companyName");
	  		fieldCodes.add("loginTime");
	  		fieldCodes.add("ip");
	  		fieldCodes.add("clientType");
	  		fieldCodes.add("store");
	  		fieldCodes.add("ios");
	  		fieldCodes.add("loginType");
	  		fieldCodes.add("keyMessage");
	  		
	  		
	  		//注意：fieldCodes和fieldNames个数必须相同且属性和title顺序一一对应，这样title和内容才一一对应
	  		
	  		
	  		try {
	  			//开始导出，执行一些workbook及sheet等对象的初始创建
	  			ExcelExportSXXSSF excelExportSXXSSF = ExcelExportSXXSSF.start("./", "./", filePrefix, fieldNames, fieldCodes, flushRows);
	  			
	  			//执行导出
	  			excelExportSXXSSF.writeDatasByMap(list);
	  			//输出文件
	  			excelExportSXXSSF.exportFile(response);
	  	}catch (Exception e) {
	  		throw new BusinessException("数据导出异常");
	  	}
	  	result.setData("数据导出成功");
	    return result;
	}
	
}
	