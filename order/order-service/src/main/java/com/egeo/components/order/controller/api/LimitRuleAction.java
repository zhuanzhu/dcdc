package com.egeo.components.order.controller.api;


import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.egeo.components.order.business.LimitRuleManage;
import com.egeo.components.order.business.LimitRuleRecordManage;
import com.egeo.components.order.converter.LimitRuleConverter;
import com.egeo.components.order.dto.LimitRuleDTO;
import com.egeo.components.order.dto.LimitRuleRecordDTO;
import com.egeo.components.order.vo.LimitRuleVO;
import com.egeo.entity.CacheUser;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.HostUtils;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/order/limitRule")
public class LimitRuleAction extends BaseSpringController {
	
	@Resource(name="limitRule")
	private LimitRuleManage limitRuleManage;

	@Resource(name="limitRuleRecord")
	private LimitRuleRecordManage limitRuleRecordManage;

	/**
	 * 根据限购规则id查询限购规则信息
	 * @param limitRuleById
	 * @return
	 */
	@RequestMapping(value = "/findLimitRuleById")
	@ResponseBody
	public JsonResult<Map<String, Object>> findLimitRuleById(Long limitRuleId ) {
		logger.info("根据限购规则id查询限购规则信息,限购规则id = {}",limitRuleId);
		Map<String, Object> rt = limitRuleManage.findLimitRuleById(limitRuleId);		
		return JsonResult.success(rt);
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findLimitRuleAll")
	@ResponseBody
	public JsonResult<List<LimitRuleVO>> findLimitRuleAll(LimitRuleVO vo,HttpServletRequest req ) {
		LimitRuleDTO dto = LimitRuleConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<LimitRuleDTO> rt = limitRuleManage.findLimitRuleAll(dto);	
		return JsonResult.success(LimitRuleConverter.toVO(rt));
					 
	}	

	/**
	 * 分页显示限购规则信息
	 * @param vo
	 * @param page
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/findLimitRuleOfPage")
	@ResponseBody
	public JsonResult<PageResult<Map<String, Object>>> findLimitRuleOfPage(LimitRuleVO vo, Pagination page,HttpServletRequest req ) {
		LimitRuleDTO dto = LimitRuleConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		// 去空格处理
		if(EmptyUtil.isNotEmpty(vo.getName()))
			dto.setName(vo.getName().trim());
		if(EmptyUtil.isNotEmpty(vo.getSerialNumber()))
			dto.setSerialNumber(vo.getSerialNumber().trim());
		if(EmptyUtil.isNotEmpty(vo.getStandardUnitSerialNumber()))
			dto.setStandardUnitSerialNumber(vo.getStandardUnitSerialNumber().trim());
		PageResult<Map<String, Object>> rt = limitRuleManage.findLimitRuleOfPage(dto, page);
		return JsonResult.success(rt);
					 
	}


	/**
	 * 新增限购规则
	 * @param vo
	 * @param limitOriginTime_
	 * @param limitStopTime_
	 * @param companyIds
	 * @param userCompanyIds
	 * @param storeIds
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/insertLimitRuleWithTx")
	@ResponseBody
	public JsonResult<Long> insertLimitRuleWithTx(LimitRuleVO vo, Long limitOriginTime_, Long limitStopTime_, 
			String companyIds,String userCompanyIds,String storeIds, HttpServletRequest req ) {
		logger.info("新增限购规则");
		LimitRuleDTO dto = LimitRuleConverter.toDTO(vo);
		if(EmptyUtil.isNotEmpty(limitOriginTime_))
			dto.setLimitOriginTime(new Date(limitOriginTime_));
		if(EmptyUtil.isNotEmpty(limitStopTime_))
			dto.setLimitStopTime(new Date(limitStopTime_));
		List<Long> companyIdList = null;
		if(EmptyUtil.isNotEmpty(companyIds))
			companyIdList = JSONArray.parseArray(companyIds,Long.class);
		List<Long> userCompanyIdList = null;
		if(EmptyUtil.isNotEmpty(userCompanyIds))
			userCompanyIdList = JSONArray.parseArray(userCompanyIds,Long.class);
		List<Long> storeIdList = null;
		if(EmptyUtil.isNotEmpty(storeIds))
			storeIdList = JSONArray.parseArray(storeIds,Long.class);
		// 新增限购规则用户赋值
		insertSaveUserInfo(dto,req);
		// 验证限购规则参数有效性
		verifyLimitRule(dto, companyIdList, userCompanyIdList, storeIdList);
		Long rt = limitRuleManage.insertLimitRuleWithTx(dto, companyIdList, userCompanyIdList, storeIdList);	
		return JsonResult.success(rt);					 
	}
	/**
	 * 验证限购规则参数有效性
	 * @param dto
	 * @param StandardUnitIdList
	 * @param companyIdList
	 */
	private void verifyLimitRule(LimitRuleDTO dto,List<Long> companyIdList,List<Long> userCompanyIdList,List<Long> storeIdList) {
		if(dto.getName() == null)
			throw new BusinessException("请填写限购规则名称");
		if (EmptyUtil.isNotEmpty(dto.getLimitUnit()) && !dto.getLimitUnit().contains("0")) {
			if(dto.getLimitUnit().contains("2") && EmptyUtil.isEmpty(companyIdList))
				throw new BusinessException("请选择选购企业");
			if(dto.getLimitUnit().contains("1") && EmptyUtil.isEmpty(userCompanyIdList))
				throw new BusinessException("请选择选购用户所属企业");
			if(dto.getLimitUnit().contains("3") && EmptyUtil.isEmpty(storeIdList))
				throw new BusinessException("请选择选购门店");
		}
		
		if(dto.getIsLimit() == null){
			throw new BusinessException("请选择是否限量");
		}else{
			if(dto.getIsLimit() == 1){
				/*if(dto.getSuLimitNum() == null){
					throw new BusinessException("请填写商品限售数量");
				}*/
				if(EmptyUtil.isNotEmpty(dto.getSuLimitNum())&&dto.getSuLimitNum().longValue() <= 0){
					throw new BusinessException("商品限售数量不能小于等于0");
				}
			}
		}
		
		if(dto.getLimitTimeType() == null){
			throw new BusinessException("请选择限购时间类型");
		}else{
			if(dto.getLimitTimeType() == 1){
				if(dto.getLimitOriginTime() == null){
					throw new BusinessException("请选择限购开始时间");
				}
				if(dto.getLimitStopTime() == null){
					throw new BusinessException("请选择限购终止时间");
				}
				if(dto.getLimitOriginTime().getTime() > dto.getLimitStopTime().getTime()){
					throw new BusinessException("请选择限购终止时间");
				}
			}
			if(dto.getLimitTimeType() == 2){
				if(dto.getPeriodType() == null){
					throw new BusinessException("请选择周期限购规则");
				}
			}
		}
		
		/*if(dto.getLimitType() == null){
			throw new BusinessException("请选择限购方式");
		}else{
			if(dto.getLimitType() == 1){
				if(dto.getUserLimitNum() == null){
					throw new BusinessException("请选择单个用户最多购买总量");
				}
			}
			if(dto.getLimitType() == 2){
				if(dto.getUserMoneySum() == null){
					throw new BusinessException("请选择单个用户最多购买总金额");
				}
			}
			
		}*/
	}



	/**
	 * 新增限购规则用户赋值
	 * @param dto
	 * @param req
	 */
	private void insertSaveUserInfo(LimitRuleDTO dto, HttpServletRequest req) {
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}else{
			throw new BusinessException("平台id不能为空");
		}
		CacheUser userCache = this.getCacheUser();
		Long userId = userCache.getId();// 用户id
		String userName = userCache.getName();// 用户名称
		String ip=HostUtils.getClientIP(req);
		//根据ip获取mac地址
		String mac;
		try {
			mac = HostUtils.getLocalMac(ip);
		} catch (Exception e) {
			throw new BusinessException("获取mac地址异常" + e.getMessage());
		}
		dto.setCreateUserid(userId);
		dto.setCreateUsername(userName);
		dto.setCreateUserip(ip);
		dto.setCreateUsermac(mac);
		dto.setUpdateUserid(userId);
		dto.setUpdateUsername(userName);
		dto.setUpdateUserip(ip);
		dto.setUpdateUsermac(mac);
		
	}



	/**
	 * 根据限购规则id更新限购规则
	 * @param vo
	 * @param StandardUnitIds
	 * @param companyIds
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/updateLimitRuleByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateLimitRuleByIdWithTx(LimitRuleVO vo, Long limitOriginTime_, Long limitStopTime_, 
			String companyIds,String userCompanyIds,String storeIds, HttpServletRequest req ) {
		logger.info("根据限购规则id更新限购规则,限购规则id = {}",vo.getId());
		LimitRuleDTO dto = LimitRuleConverter.toDTO(vo);
		if(EmptyUtil.isNotEmpty(limitOriginTime_))
			dto.setLimitOriginTime(new Date(limitOriginTime_));
		if(EmptyUtil.isNotEmpty(limitStopTime_))
			dto.setLimitStopTime(new Date(limitStopTime_));
		List<Long> companyIdList = null;
		if(EmptyUtil.isNotEmpty(companyIds))
			companyIdList = JSONArray.parseArray(companyIds,Long.class);
		List<Long> userCompanyIdList = null;
		if(EmptyUtil.isNotEmpty(userCompanyIds))
			userCompanyIdList = JSONArray.parseArray(userCompanyIds,Long.class);
		List<Long> storeIdList = null;
		if(EmptyUtil.isNotEmpty(storeIds))
			storeIdList = JSONArray.parseArray(storeIds,Long.class);
		// 更新限购规则用户赋值
		updateSaveUserInfo(dto,req);
		// 验证限购规则参数有效性
//		verifyLimitRule(dto, companyIdList, userCompanyIdList, storeIdList);
		//判断是否有该条限购规则的购买记录
		if (hasLimitRoleRecord(vo)) {
			throw new BusinessException("该规则已存在购买记录,无法修改");
		}
		int rt = limitRuleManage.updateLimitRuleWithTx(dto, companyIdList, userCompanyIdList, storeIdList);	
		return JsonResult.success(rt);					 
	}

	/**
	 * 判断限购规则是否有购买记录
	 * @param vo
	 * @return
	 */
	private boolean hasLimitRoleRecord(LimitRuleVO vo) {
		logger.info("判断限购规则是否有购买记录");
		LimitRuleRecordDTO dto = new LimitRuleRecordDTO();
		dto.setLimitRuleId(vo.getId());
		List<LimitRuleRecordDTO> dto2 = limitRuleRecordManage.findLimitRuleRecordAll(dto);
		if (EmptyUtil.isNotEmpty(dto2)) {
			for (LimitRuleRecordDTO limitRuleRecordDTO : dto2) {
				if (limitRuleRecordDTO.getBuySum().intValue() > 0L) {
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * 更新限购规则用户赋值
	 * @param dto
	 * @param req
	 */
	private void updateSaveUserInfo(LimitRuleDTO dto, HttpServletRequest req) {
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}else{
			throw new BusinessException("平台id不能为空");
		}
		CacheUser userCache = this.getCacheUser();
		Long userId = userCache.getId();// 用户id
		String userName = userCache.getName();// 用户名称
		String ip=HostUtils.getClientIP(req);
		//根据ip获取mac地址
		String mac;
		try {
			mac = HostUtils.getLocalMac(ip);
		} catch (Exception e) {
			throw new BusinessException("获取mac地址异常" + e.getMessage());
		}
		dto.setUpdateUserid(userId);
		dto.setUpdateUsername(userName);
		dto.setUpdateUserip(ip);
		dto.setUpdateUsermac(mac);
		
	}



	/**
	 * 根据限购规则id启用停用限购规则
	 * @param limitRuleId
	 * @param isStart
	 * @return
	 */
	@RequestMapping(value = "/isLimitRuleStartWithTx")
	@ResponseBody
	public JsonResult<Integer> isLimitRuleStartWithTx(Long limitRuleId,Integer isStart) {
		logger.info("根据限购规则id启用停用限购规则,限购规则id = {}",limitRuleId);
		if(limitRuleId == null){
			return JsonResult.fail("限购规则id不能为空");
		}
		if(isStart == null){
			return JsonResult.fail("是否启用停用状态不能为空");
		}
		int rt = limitRuleManage.isLimitRuleStartWithTx(limitRuleId,isStart);	
		return JsonResult.success(rt);					 
	}
	
	/**
	 * 根据su商品id,公司id，平台id查询所有启用限购规则，按创建时间排序
	 * @param limitRuleId
	 * @param isStart
	 * @return
	 */
	@RequestMapping(value = "/startLimitRuleByStandardUnitId")
	@ResponseBody
	public JsonResult<Map<String, Object>> startLimitRuleByStandardUnitId(Long standardUnitId, Long storeId, HttpServletRequest req) {
		logger.info("根据su商品id查询所有启用限购规则,su商品id = {}",standardUnitId);
		String str = req.getHeader("platformId");		
		Long platformId = null;
		if(EmptyUtil.isNotEmpty(str)){
			platformId = Long.valueOf(str);
		}else{
			throw new BusinessException("平台id不能为空");
		}
		CacheUser userCache = this.getCacheUser();
		Long companyId = userCache.getCompanyId(); // 公司id
		if(standardUnitId == null){
			return JsonResult.fail("su商品id不能为空");
		}
		Map<String, Object> rt = limitRuleManage.startLimitRuleByStandardUnitId(standardUnitId,companyId,platformId, userCache.getId(), storeId);	
		return JsonResult.success(rt);					 
	}
	
		
}
	