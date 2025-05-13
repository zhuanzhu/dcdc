package com.egeo.components.promotion.business.impl;


import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.dto.LinkableButtonPageDTO;
import com.egeo.components.order.dto.SoDTO;
import com.egeo.components.promotion.business.CouponUnitManage;
import com.egeo.components.promotion.common.DateUtils;
import com.egeo.components.promotion.converter.CouponUnitConverter;
import com.egeo.components.promotion.dto.CouponBatchDTO;
import com.egeo.components.promotion.dto.CouponCompanyDTO;
import com.egeo.components.promotion.dto.CouponDTO;
import com.egeo.components.promotion.dto.CouponGroupDTO;
import com.egeo.components.promotion.dto.CouponGroupRelDTO;
import com.egeo.components.promotion.dto.CouponUnitDTO;
import com.egeo.components.promotion.dto.ExchangeActivityDTO;
import com.egeo.components.promotion.dto.ExchangeBatchDTO;
import com.egeo.components.promotion.facade.CouponBatchFacade;
import com.egeo.components.promotion.facade.CouponFacade;
import com.egeo.components.promotion.facade.CouponGroupFacade;
import com.egeo.components.promotion.facade.CouponUnitFacade;
import com.egeo.components.promotion.vo.CouponUnitReVO;
import com.egeo.components.user.dto.QrCodeDTO;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.core.Constant.BusinessExceptionConstant;
import com.egeo.core.Constant.CmsConstant;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.CodeUtils;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.SequenceUtil;
import com.egeo.web.JsonResult;

@Service("couponUnit")
public class CouponUnitManageImpl implements CouponUnitManage {

	// 优惠卷unit状态
	public static int UNUSED_CODE = 0; // 未使用(立即使用)
	public static int USED_CODE = 1; // 已使用
	public static int FREEZE_CODE = 2; // 已冻结
	public static int EXPIRED_CODE = 3; // 已过期()
	public static int INVALID_CODE = 4; // 已失效
	public static int EXCHANGE_CODE=5;//已兑换

	// 优惠卷批次的状态
	public static int CAN_RECEIVE = 0; // 立即领取
	public static int ALREADY_RECEIVED = 1; // 已领取
	public static int USE_IMMEDIATELY = 2; // 立即使用
	public static int FINISHED_RECEIVE = 3; // 已领完
	public static int BEFORE_TIME = 7; // 未开始
	public static int USE_IMMEDIATELY_AND_ALREADY_RECEIVED = 8; // 立即使用已领取

	//以旧换新优惠券状态
	public static int CAN_EXCHANGE=5;//立即兑换
	public static int ADD_PRICE_EXCHANGE=6;//加价兑换
	public static int ALL_RECEIVED=3;//已领完





	//是否显示以旧换新
	public static int SHOW_EXCHANGE = 1; // 显示
	public static int NO_SHOW_EXCHANGE = 0; // 不显示

	Logger logger=Logger.getLogger(CouponUnitManageImpl.class);

	@Resource(name = "couponUnitFacade")
	private CouponUnitFacade couponUnitFacade;

	@Resource(name = "couponBatchFacade")
	private CouponBatchFacade couponBatchFacade;

	@Resource(name = "couponFacade")
	private CouponFacade couponFacade;

	@Resource(name = "couponGroupFacade")
	private CouponGroupFacade couponGroupFacade;

	@Override
	public CouponUnitDTO findCouponUnitById(CouponUnitDTO dto) {
		return couponUnitFacade.findCouponUnitById(dto);
	}

	@Override
	public PageResult<CouponUnitDTO> findCouponUnitOfPage(CouponUnitDTO dto, Pagination page) {
		return couponUnitFacade.findCouponUnitOfPage(dto, page);
	}

	@Override
	public List<CouponUnitDTO> findCouponUnitAll(CouponUnitDTO dto) {
		return couponUnitFacade.findCouponUnitAll(dto);
	}

	@Override
	public int updateCouponUnitWithTx(CouponUnitDTO dto) {
		return couponUnitFacade.updateCouponUnitWithTx(dto);
	}

	@Override
	public int deleteCouponUnitWithTx(CouponUnitDTO dto) {
		return couponUnitFacade.deleteCouponUnitWithTx(dto);
	}

	@Override
	public JsonResult<Map<String, Object>> sendCouponUnitWithTx(Long platformId, Long typeId, Long storeId, Long companyId, Long userId,Long clientId) {
		CouponUnitDTO couponUnitDTO = new CouponUnitDTO();
		couponUnitDTO.setId(typeId);
		CouponUnitDTO couponUnitById = couponUnitFacade.findCouponUnitById(couponUnitDTO);
		if(EmptyUtil.isEmpty(couponUnitById)){
			return JsonResult.fail("优惠券unit不存在");
		}
		CouponDTO couponDTO = new CouponDTO();
		couponDTO.setId(couponUnitById.getCouponId());
		CouponDTO couponById = couponFacade.findCouponById(couponDTO);
		if(EmptyUtil.isEmpty(couponById)){
			return JsonResult.fail("优惠券不存在");
		}
		CouponBatchDTO couponBatchDTO = new CouponBatchDTO();
		couponBatchDTO.setId(couponUnitById.getCouponBatchId());
		CouponBatchDTO couponBatchById = couponBatchFacade.findCouponBatchById(couponBatchDTO);
		if(EmptyUtil.isEmpty(couponBatchById)){
			return JsonResult.fail("优惠券批次不存在");
		}
		if ( couponBatchById.getCouponRelType() == 1
				|| couponBatchById.getGrantType() == 0) {
			return JsonResult.fail("优惠券批次不可领取");
		}

		// 判断优惠券是否未到领取时间
		if (couponBatchById.getReceiveStartTime() != null
				&& couponBatchById.getReceiveStartTime().getTime() > System.currentTimeMillis()) {
			return JsonResult.fail("领取失败，该券不在有效的可领取日期内",BusinessExceptionConstant.RECEIVE_TIME_OUT);
		}

		// 判断优惠券是否已过领取时间
		if (couponBatchById.getReceiveEndTime() != null
				&&couponBatchById.getReceiveEndTime().getTime() <= System.currentTimeMillis()) {
			return JsonResult.fail("领取失败，该券不在有效的可领取日期内", BusinessExceptionConstant.RECEIVE_TIME_OUT);
		}


		// 判断该用户所属公司是否是该优惠卷选择范围内的公司
		List<CouponCompanyDTO> couponCompanyDTOList = couponFacade
				.findCouponCompanyAll(couponById.getId());
		boolean isNotExistCompany = true;
		for (CouponCompanyDTO couponCompanyDTO : couponCompanyDTOList) {
			if (couponCompanyDTO.getCompanyId().equals(companyId)
					|| couponCompanyDTO.getCompanyId().equals(Long.valueOf(-1L))) {
				isNotExistCompany = false;
				break;
			}
		}
		if (isNotExistCompany) {
			return JsonResult.fail("该用户所属公司不在该优惠券选择公司的范围内");
		}
		//校验该unit是否已领取
		if(EmptyUtil.isNotEmpty(couponUnitById.getUserId())){
			return JsonResult.fail("该优惠券unit已被领取不可重复领取");
		}

		// 验证通过,添加优惠卷unit
		CouponUnitDTO dto = new CouponUnitDTO();
		dto.setId(couponUnitById.getId());
		dto.setUserId(userId);
		dto.setStoreId(storeId);
		dto.setCompanyId(companyId);
		// 设置优惠卷unit的有效时间
		if (couponBatchById.getEffectDays() != null
				&& !couponBatchById.getEffectDays().equals(Integer.valueOf(-1))) {

			dto.setEffectStartTime(new Date());
			Date endDate = DateUtils.addDays(new Date(), couponBatchById.getEffectDays().intValue());
			dto.setEffectEndTime(processEndTime(endDate));
		} else {
			dto.setEffectStartTime(couponBatchById.getEffectStartTime());
			dto.setEffectEndTime(processEndTime(couponBatchById.getEffectEndTime()));
		}
		//设置领取时间
		dto.setReceivedTime(new Date());
		int i = couponUnitFacade.updateCouponUnitWithTx(dto);
		//领取成功,将rdid重新更换
		String rdid = CodeUtils.generateCode(10);
		QrCodeDTO qrCodeDTO = new QrCodeDTO();
		qrCodeDTO.setTypeId(couponUnitById.getId());
		QrCodeDTO code=couponUnitFacade.findQrCodeByCouponUnitId(qrCodeDTO);
		qrCodeDTO.setRdid(rdid);
		qrCodeDTO.setId(code.getId());
		couponUnitFacade.updateQrCodeByCouponUnitId(qrCodeDTO);

		//组织回传信息
		/*CouponUnitDTO couponUnitDTO1 = new CouponUnitDTO();
		couponUnitDTO1.setId(couponUnitById.getId());
		CouponUnitDTO unitDTO = couponUnitFacade.findCouponUnitById(couponUnitDTO1);
		unitDTO.setStoreId(storeId);
		unitDTO.setPlatformId(platformId);
		unitDTO.setCompanyId(companyId);*/
		dto.setPlatformId(platformId);
		PageResult<CouponUnitDTO> couponUnitCenterOfPage = couponUnitFacade.findCouponUnitOfPageByUser(dto, new Pagination());
		if(EmptyUtil.isEmpty(couponUnitCenterOfPage.getList())||couponUnitCenterOfPage.getList().size()>1){
			return JsonResult.fail("优惠券unit有误");
		}
		Map<String, Object> map = getCouponBatchUnitObj(couponUnitCenterOfPage.getList().get(0));
		
		setCmsPageId(map,couponUnitById.getCouponBatchId(),clientId,platformId,couponById);
		// 个人优惠卷列表
		map.put("id", couponUnitCenterOfPage.getList().get(0).getId());
		map.put("couponUnitStatus", formatCouponUnitStatus(couponUnitCenterOfPage.getList().get(0)));
		map.put("isSoonExpire",couponUnitCenterOfPage.getList().get(0).getEffectEndTime() != null ?
				DateUtils.addDays(new Date(), 3).getTime() >= couponUnitCenterOfPage.getList().get(0).getEffectEndTime().getTime() : false);

		return JsonResult.success(map);
	}




	@Override
	public JsonResult<Map<String, Object>> insertCouponUnitWithTx(CouponUnitDTO dto) {
		CouponBatchDTO couponBatchDTO = new CouponBatchDTO();
		couponBatchDTO.setId(dto.getCouponBatchId());
		CouponBatchDTO couponBatchDTO_ = couponBatchFacade.findCouponBatchById(couponBatchDTO);

		if (EmptyUtil.isEmpty(couponBatchDTO_))
			return JsonResult.fail("优惠券批次不存在");
		if(couponBatchDTO_.getGetType()==3){
			return JsonResult.fail("以旧换新优惠券不可领取");
		}
		logger.info("领取优惠券1="+couponBatchDTO_.getIsDisplay());
		logger.info("领取优惠券2="+couponBatchDTO_.getCouponRelType());
		logger.info("领取优惠券3="+couponBatchDTO_.getGrantType());
		if (EmptyUtil.isEmpty(couponBatchDTO_.getIsDisplay())||couponBatchDTO_.getIsDisplay() == 0 ||EmptyUtil.isEmpty(couponBatchDTO_.getCouponRelType())
				|| couponBatchDTO_.getGrantType() == 0)
			return JsonResult.fail("优惠券批次不可领取");
		List<CouponDTO> couponDTOS = new ArrayList<>();
		if(couponBatchDTO_.getCouponRelType()==0){
			CouponDTO couponDTO = new CouponDTO();
			couponDTO.setId(couponBatchDTO_.getCouponRelId());
			CouponDTO couponDTO_ = couponFacade.findCouponById(couponDTO);
			if (EmptyUtil.isEmpty(couponDTO_)) {
				return JsonResult.fail("优惠券信息有误,优惠卷不存在");
			}
			couponDTOS.add(couponDTO_);
		}else if(couponBatchDTO_.getCouponRelType()==1){
			CouponGroupDTO couponGroupDTO = new CouponGroupDTO();
			couponGroupDTO.setId(couponBatchDTO_.getCouponRelId());
			CouponGroupDTO couponGroupById = couponGroupFacade.findCouponGroupById(couponGroupDTO);
			if(EmptyUtil.isEmpty(couponGroupById)){
				return JsonResult.fail("优惠券组信息有误,优惠券组不存在");
			}
		}else{
			return JsonResult.fail("优惠券批次中的优惠券类型有误");
		}

		// 判断优惠券是否未到领取时间
		if (couponBatchDTO_.getReceiveStartTime() != null
				&& couponBatchDTO_.getReceiveStartTime().getTime() > System.currentTimeMillis())
			return JsonResult.fail("未到优惠券领取时间");

		// 判断优惠券是否已过领取时间
		if (couponBatchDTO_.getReceiveEndTime() != null
				&& couponBatchDTO_.getReceiveEndTime().getTime() <= System.currentTimeMillis()) 
			return JsonResult.fail("已过优惠券领取时间",BusinessExceptionConstant.RECEIVE_TIME_OUT);
		

		// 判断对于该用户来说是否是立即领取的优惠卷批次
		CouponUnitDTO couponUnitDTO = new CouponUnitDTO();
		couponUnitDTO.setCouponBatchId(dto.getCouponBatchId());
		couponUnitDTO.setUserId(dto.getUserId());
		List<CouponUnitDTO> couponUnitDTOList = couponUnitFacade.findCouponUnitAll(couponUnitDTO);
		if (EmptyUtil.isNotEmpty(couponUnitDTOList)) {
			// 可再次领取条件: 1)可再次领取  2)已过期/已使用
			// 非立即领取的优惠卷批次
			for (CouponUnitDTO couponUnitDTO_ : couponUnitDTOList) {
				if (!((couponUnitDTO_.getCouponUnitStatus() == UNUSED_CODE 
						&& (couponUnitDTO_.getEffectEndTime() != null 
						&& couponUnitDTO_.getEffectEndTime().getTime() <= System.currentTimeMillis())
						&& couponBatchDTO_.getIsRepeat() == 1)
						|| (couponUnitDTO_.getCouponUnitStatus() == USED_CODE
								&& couponBatchDTO_.getIsRepeat() == 1))) {
					return JsonResult.fail("该用户不能领取该优惠券");
				}
			}
		}
		// 判断优惠券是否已领完
		CouponUnitDTO couponUnitDTO2 = new CouponUnitDTO();
		couponUnitDTO2.setCouponBatchId(dto.getCouponBatchId());
		couponUnitDTOList = couponUnitFacade.findCouponUnitAll(couponUnitDTO2);
		if (couponBatchDTO_.getGrantCount().intValue() != -1
				&& couponBatchDTO_.getGrantCount().intValue() <= couponUnitDTOList.size()) {
//			PageResult<Map<String, Object>> rt = findCouponUnitCenterOfPage(dto,new Pagination());
//			return JsonResult.success(rt.getList().get(0));
			return JsonResult.fail("优惠券已领完");
		}

		// 判断该用户所属公司是否是该优惠卷选择范围内的公司
		boolean isNotExistCompany = true;
		if(couponBatchDTO_.getCouponRelType()==0){
			//优惠券
			List<CouponCompanyDTO> couponCompanyDTOList = couponFacade
					.findCouponCompanyAll(couponBatchDTO_.getCouponRelId());
			for (CouponCompanyDTO couponCompanyDTO : couponCompanyDTOList) {
				if (couponCompanyDTO.getCompanyId().equals(dto.getCompanyId())
						|| couponCompanyDTO.getCompanyId().equals(Long.valueOf(-1L))) {
					isNotExistCompany = false;
					break;
				}
			}
		}else if(couponBatchDTO_.getCouponRelType()==1){
			//优惠券组
			List<Long> couponIdList=couponGroupFacade.findCouponIdListByGroupId(couponBatchDTO_.getCouponRelId());
			if(EmptyUtil.isEmpty(couponIdList)){
				return JsonResult.fail("不存在对应的优惠券信息");
			}
			for(Long couponId:couponIdList){
				List<CouponCompanyDTO> couponCompanyDTOList = couponFacade
						.findCouponCompanyAll(couponId);
				for (CouponCompanyDTO couponCompanyDTO : couponCompanyDTOList) {
					if (couponCompanyDTO.getCompanyId().equals(dto.getCompanyId())
							|| couponCompanyDTO.getCompanyId().equals(Long.valueOf(-1L))) {
						isNotExistCompany = false;
						CouponDTO couponDTO = new CouponDTO();
						couponDTO.setId(couponId);
						couponDTO=couponFacade.findCouponById(couponDTO);
						couponDTOS.add(couponDTO);
						break;
					}
				}
			}
		}
		if (isNotExistCompany) {
			return JsonResult.fail("该用户所属公司不在该优惠券选择公司的范围内");
		}


		for(CouponDTO couponDTO:couponDTOS){
			// 验证通过,添加优惠卷unit
			CouponUnitDTO couponUnitDTO_ = new CouponUnitDTO();
			couponUnitDTO_.setBatchIndex(Long.valueOf(0));
			couponUnitDTO_.setCouponUnitCode(SequenceUtil.genCouponUnitNo(couponDTO.getCouponType(),Integer.valueOf(0)));//一次仅一张
			couponUnitDTO_.setTitle(couponDTO.getTitle());
			couponUnitDTO_.setPlatformId(dto.getPlatformId());
		/*if (EmptyUtil.isNotEmpty(couponUnitFacade.findCouponUnitAll(couponUnitDTO_)))
			return JsonResult.fail("优惠券unit编号重复,请重试");*/
			couponUnitDTO_.setCouponId(couponDTO.getId());
			couponUnitDTO_.setCouponBatchId(couponBatchDTO_.getId());
			couponUnitDTO_.setUserId(dto.getUserId());
			couponUnitDTO_.setCouponBatchName(couponBatchDTO_.getCouponBatchName());
			// 设置优惠卷unit的有效时间
			if (couponBatchDTO_.getEffectDays() != null
					&& !couponBatchDTO_.getEffectDays().equals(Integer.valueOf(-1))) {

				couponUnitDTO_.setEffectStartTime(new Date());
				Date endDate = DateUtils.addDays(new Date(), couponBatchDTO_.getEffectDays().intValue());
				couponUnitDTO_.setEffectEndTime(processEndTime(endDate));
			} else {
				couponUnitDTO_.setEffectStartTime(couponBatchDTO_.getEffectStartTime());
				couponUnitDTO_.setEffectEndTime(processEndTime(couponBatchDTO_.getEffectEndTime()));
			}
			//领取时间
			couponUnitDTO_.setReceivedTime(new Date());
			Long aLong = couponUnitFacade.insertCouponUnitWithTx(couponUnitDTO_);
			if(EmptyUtil.isNotEmpty(aLong)){
				dto.setId(aLong);
				logger.info("领取的优惠券unitId="+aLong);
			}
		}


		// 领取成功
		if(couponBatchDTO_.getCouponRelType()==0){
			PageResult<Map<String, Object>> rt = findCouponUnitCenterOfPage(dto,new Pagination());
			return JsonResult.success(rt.getList().get(0));
		}else{
			Map<String, Object> map = new HashMap<>();
			map.put("result","领取成功");
			return JsonResult.success(map);
		}
	}

	private Date processEndTime(Date date) {
		if (date != null) {
			date.setHours(23);
			date.setMinutes(59);
			date.setSeconds(59);
			return date;
		}
		return null;
	}
	
	@Override
	public PageResult<Map<String, Object>> findCouponUnitOfPageByBlurry(CouponUnitReVO vo, Pagination page) {
		PageResult<Map<String, Object>> result = new PageResult<Map<String, Object>>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<Long> userList = null;

		if (EmptyUtil.isNotEmpty(vo.getOrderCode())) {
			SoDTO soDTO = couponUnitFacade.querySoByOrderCode(vo.getOrderCode());
			vo.setOrderId(soDTO != null ? soDTO.getId() : -1L);
		}

		if (EmptyUtil.isNotEmpty(vo.getMail()) || EmptyUtil.isNotEmpty(vo.getMobile())) {
			userList = new ArrayList<Long>();
			UserDTO userDTO = new UserDTO();
			userDTO.setMail(vo.getMail());
			userDTO.setMobile(vo.getMobile());
			List<UserDTO> userDTOList = couponUnitFacade.findUser(userDTO);
			for (UserDTO userDTO_ : userDTOList) {
				userList.add(userDTO_.getId());
			}
			userList.add(EmptyUtil.isNotEmpty(userDTOList) ? null : -1L);
		}

		CouponUnitDTO dto = CouponUnitConverter.toDTO(vo);
		
		PageResult<CouponUnitDTO> rt = couponUnitFacade.findCouponUnitOfPageByBlurry(dto, userList, page);


		for (CouponUnitDTO couponUnitDTO : rt.getList()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", couponUnitDTO.getId());
			map.put("couponUnitCode", couponUnitDTO.getCouponUnitCode());
			map.put("couponCode", couponUnitDTO.getCouponCode());
			map.put("couponBatchCode", couponUnitDTO.getCouponBatchCode());

			// 查询用户信息
			UserDTO userDTO = couponUnitFacade.findUserById(couponUnitDTO.getUserId());
			map.put("mail", userDTO != null ? userDTO.getMail() : null);
			map.put("mobile", userDTO != null ? userDTO.getMobile() : null);

			map.put("title", couponUnitDTO.getTitle());
			map.put("couponType", couponUnitDTO.getCouponType());
			map.put("effectStartTime", couponUnitDTO.getEffectStartTime() != null 
					? DateUtils.getDefaultDate(couponUnitDTO.getEffectStartTime()) : null);
			map.put("effectEndTime", couponUnitDTO.getEffectEndTime() != null 
					? DateUtils.getDefaultDate(couponUnitDTO.getEffectEndTime()) : null);
			map.put("createTime", DateUtils.format(DateUtils.DATE_TIME_FORMAT, couponUnitDTO.getCreateTime()));
			map.put("usedTime", couponUnitDTO.getUsedTime() != null
					? DateUtils.format(DateUtils.DATE_TIME_FORMAT, couponUnitDTO.getUsedTime()) : null);
			map.put("grantType", couponUnitDTO.getGrantType());
			map.put("getType",couponUnitDTO.getGetType());
			map.put("usedCount", couponUnitDTO.getUsedCount());

			CouponBatchDTO couponBatchDTO = new CouponBatchDTO();
			couponBatchDTO.setId(couponUnitDTO.getCouponBatchId());
			CouponBatchDTO couponBatchDTO2 = couponBatchFacade.findCouponBatchById(couponBatchDTO);
			String couponBatchName = couponBatchDTO2.getCouponBatchName();
			if (EmptyUtil.isNotBlank(couponBatchName)) {
				map.put("couponBatchName",couponBatchName);
			}


			map.put("couponUnitStatus", formatCouponUnitStatusBackstage(couponUnitDTO));

			// 查询订单信息
			SoDTO soDTO_ = couponUnitFacade.querySoById(couponUnitDTO.getOrderId());
			map.put("orderCode", soDTO_ != null ? soDTO_.getOrderCode() : null);
			map.put("receivedTime",EmptyUtil.isEmpty(couponUnitDTO.getReceivedTime())?null:DateUtils.format(DateUtils.DATE_TIME_FORMAT,couponUnitDTO.getReceivedTime()));

			list.add(map);
		}

		result.setList(list);
		result.setPageNo(rt.getPageNo());
		result.setPageSize(rt.getPageSize());
		result.setTotalSize(rt.getTotalSize());

		return result;
	}

	private int formatCouponUnitStatusBackstage(CouponUnitDTO couponUnitDTO) {
		if (couponUnitDTO.getCouponUnitStatus().intValue() == UNUSED_CODE
				&& (couponUnitDTO.getEffectEndTime() == null ||
				couponUnitDTO.getEffectEndTime().getTime() > System.currentTimeMillis()))
			return UNUSED_CODE;

		if (couponUnitDTO.getCouponUnitStatus().intValue() == UNUSED_CODE
				&& (couponUnitDTO.getEffectEndTime() != null &&
				couponUnitDTO.getEffectEndTime().getTime() <= System.currentTimeMillis()))
			return EXPIRED_CODE;

		return couponUnitDTO.getCouponUnitStatus();
	}



	private int formatCouponUnitStatus(CouponUnitDTO couponUnitDTO) {
		if (couponUnitDTO.getCouponUnitStatus().intValue() == UNUSED_CODE
				&& (couponUnitDTO.getEffectEndTime() == null || 
				couponUnitDTO.getEffectEndTime().getTime() > System.currentTimeMillis()))
			return UNUSED_CODE;

		if (couponUnitDTO.getCouponUnitStatus().intValue() == UNUSED_CODE
				&& (couponUnitDTO.getEffectEndTime() != null && 
				couponUnitDTO.getEffectEndTime().getTime() <= System.currentTimeMillis()))
			return EXPIRED_CODE;

		if(couponUnitDTO.getCouponUnitStatus()==2){
			//已冻结返回已使用1
			return USED_CODE;
		}
		if(couponUnitDTO.getCouponUnitStatus()==4){
			return EXPIRED_CODE;
		}
		return couponUnitDTO.getCouponUnitStatus();
	}

	@Override
	public JsonResult<String> resetCouponUnitWithTx(List<Long> couponUnitList, Date effectStartTime,
			Date effectEndTime) {
		int successCount = 0;
		for (Long couponUnitId : couponUnitList) {
			CouponUnitDTO couponUnitDTO = new CouponUnitDTO();
			couponUnitDTO.setId(couponUnitId);
			CouponUnitDTO couponUnitDTO_ = couponUnitFacade.findCouponUnitById(couponUnitDTO);

			if (EmptyUtil.isNotEmpty(couponUnitDTO_) && couponUnitDTO_.getCouponUnitStatus() != USED_CODE
					&& couponUnitDTO_.getCouponUnitStatus() != FREEZE_CODE
					&& couponUnitDTO_.getCouponUnitStatus() != INVALID_CODE) {
				// 已使用、冻结中、已失效不可重置为有效,可使用、已过期可重置为有效
				couponUnitDTO_.setCouponUnitStatus(UNUSED_CODE);
				couponUnitDTO_.setEffectStartTime(effectStartTime);
				couponUnitDTO_.setEffectEndTime(effectEndTime);
				couponUnitFacade.updateCouponUnitWithTx(couponUnitDTO_);
				successCount++;
			}
		}
		
		if (successCount == 0) 
			return JsonResult.success("重置失败");
		
		int failCount = couponUnitList.size() - successCount;
		if (failCount > 0) 
			return JsonResult.success(successCount+"张优惠卷重置成功," + failCount + "张优惠卷重置失败");

		return JsonResult.success("重置成功");
	}

	@Override
	public Map<String, Object> findCouponUnitOfPageByUser(CouponUnitDTO dto, Pagination page) {

		PageResult<CouponUnitDTO> rt = couponUnitFacade.findCouponUnitOfPageByUser(dto, page);
		List<Map<String, Object>> list = new ArrayList<>();
		for (CouponUnitDTO couponUnitDTO : rt.getList()) {
			logger.info("storeIdList="+couponUnitDTO.getStoreIds());
			Map<String, Object> map = getCouponBatchUnitObj(couponUnitDTO);
			// 个人优惠卷列表
			Integer unitStatus=formatCouponUnitStatus(couponUnitDTO);
			map.put("id", couponUnitDTO.getId());
			map.put("isRead", couponUnitDTO.getIsRead());
			map.put("couponUnitStatus", unitStatus);
			map.put("isSoonExpire",couponUnitDTO.getEffectEndTime() != null ?  
					DateUtils.addDays(new Date(), 3).getTime() >= couponUnitDTO.getEffectEndTime().getTime()&&couponUnitDTO.getEffectEndTime().getTime()>=System.currentTimeMillis() : false);

			//处理以旧换新活动
			if(EmptyUtil.isNotEmpty(couponUnitDTO.getCouponBatchId())){
				List<Long> exchangeIds = checkIsShowExchange(couponUnitDTO.getCouponBatchId(), couponUnitDTO.getCouponUnitStatus());
				if(EmptyUtil.isEmpty(exchangeIds)){
					map.put("isShowExchange",NO_SHOW_EXCHANGE);

				}else{
					map.put("isShowExchange",SHOW_EXCHANGE);
				}
				map.put("exchangeIdList",exchangeIds);
			}

			setCmsPageId(map, couponUnitDTO.getCouponBatchId(), dto.getClientId() ,dto.getPlatformId(),
					new CouponDTO(couponUnitDTO.getCouponId(), couponUnitDTO.getCouponType(), couponUnitDTO.getJumpType()));
			
			list.add(map);
		}

		PageResult<Map<String, Object>> result = new PageResult<Map<String, Object>>();
		result.setList(list);
		result.setPageNo(rt.getPageNo());
		result.setPageSize(rt.getPageSize());
		result.setTotalSize(rt.getTotalSize());
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("pageNo", result.getPageNo());
		resultMap.put("pageSize", result.getPageSize());
		resultMap.put("totalSize", result.getTotalSize());
		resultMap.put("totalPage", result.getTotalPage());
		resultMap.put("list", result.getList());
		resultMap.put("finished", result.isFinished());
		resultMap.put("platformName", result.getPlatformName());
		resultMap.put("startItem", result.getStartItem());
		Integer newMJCount = couponUnitFacade.countUnreadCouponUnit(dto.getUserId(), 0);
		Integer newDHCount = couponUnitFacade.countUnreadCouponUnit(dto.getUserId(), 1);
		resultMap.put("newMJCount", newMJCount == null ? 0 : newMJCount);
		resultMap.put("newDHCount", newDHCount == null ? 0 : newDHCount);
		return resultMap;
	}


	//校验当前unit是否存在依旧换新的活动,返回活动id
	private List<Long> checkIsShowExchange(Long batchId,Integer unitStatus ){
		//查看当前
		List<Long> exchangeIds=couponUnitFacade.checkIsShowExchange(batchId,unitStatus);
		return exchangeIds;
	}

	@Override
	public PageResult<Map<String, Object>> findCouponUnitCenterOfPage(CouponUnitDTO dto, Pagination page) {

		long timeMillis = System.currentTimeMillis();
		logger.info("[请求参数]:"+dto.getCouponUnitCode());
		PageResult<CouponUnitDTO> rt = couponUnitFacade.findCouponUnitCenterOfPage(dto, page);
		logger.info("查询结果rt="+rt.getList().size());
		logger.info("时间="+(System.currentTimeMillis()-timeMillis));
		List<Map<String, Object>> list = new ArrayList<>();
		//新增返回pageId
		for (CouponUnitDTO couponUnitDTO : rt.getList()) {

				Map<String, Object> map = getCouponBatchUnitObj(couponUnitDTO);

				// 领卷中心
				Integer unitStatus=formatCouponBatchStatus(couponUnitDTO);
				map.put("id", couponUnitDTO.getId());
				map.put("couponBatchId", couponUnitDTO.getCouponBatchId());


				// 新增返回pageId
				setCmsPageId(map,couponUnitDTO.getCouponBatchId(),couponUnitDTO.getClientId(),dto.getPlatformId(),
						new CouponDTO(couponUnitDTO.getCouponId(), couponUnitDTO.getCouponType(), couponUnitDTO.getJumpType()));


				map.put("couponBatchStatus", unitStatus);
				map.put("isSoonExpire", couponUnitDTO.getCouponBatchEffectEndTime() != null ? DateUtils
						.addDays(new Date(), 3).getTime() >= couponUnitDTO.getCouponBatchEffectEndTime().getTime() : false);


				/*//处理以旧换新活动
				List<Long> exchangeIds = checkIsShowExchange(couponUnitDTO.getCouponBatchId(), couponUnitDTO.getCouponUnitStatus());

				Long time8=System.currentTimeMillis();
				logger.info("时间7:"+(time8-time7));

				if(EmptyUtil.isEmpty(exchangeIds)){
					map.put("isShowExchange",NO_SHOW_EXCHANGE);

				}else{
					map.put("isShowExchange",SHOW_EXCHANGE);
				}
				map.put("exchangeIdList",exchangeIds);*/
				list.add(map);
			}

		PageResult<Map<String, Object>> result = new PageResult<Map<String, Object>>();
		result.setList(list);
		result.setPageNo(rt.getPageNo());
		result.setPageSize(rt.getPageSize());
		result.setTotalSize(rt.getTotalSize());
		logger.info("接口用时:"+(System.currentTimeMillis()-timeMillis));
		return result;
	}

	/**
	 * 判断优惠卷是否包含当前su
	 * 
	 * @param couponUnitDTO
	 * @param goodsId
	 *            当前su的id
	 * @return
	 */
	private boolean isContainCurrentSu(CouponUnitDTO couponUnitDTO, Long goodsId) {
		// 判断该商品是否是该优惠卷批次包含的商品
		// 首先判断是优惠卷还是优惠卷分组
		if (couponUnitDTO.getCouponRelType() == 0) {
			// 优惠卷
			// 通过优惠卷的id查询商品列表
			CouponDTO couponDTO = new CouponDTO();
			couponDTO.setGoodsType(couponUnitDTO.getGoodsType());
			couponDTO.setGoodsId(couponUnitDTO.getGoodsId());
			List<Long> goodIds = couponFacade.findCouponGoodsIdAll(couponDTO);
			if (goodIds.contains(goodsId)) {
				// 优惠卷包含当前商品
				return true;
			}
		} else if (couponUnitDTO.getCouponRelType() == 1) {
			// 优惠卷分组
			// 查询优惠分组下的优惠卷信息
			List<CouponGroupRelDTO> couponGroupRelDTOList = couponGroupFacade
					.queryCouponGroupRelByCouponId(couponUnitDTO.getCouponRelId());
			for (CouponGroupRelDTO couponGroupRelDTO : couponGroupRelDTOList) {
				// 通过优惠卷的id查询商品列表
				CouponDTO couponDTO = new CouponDTO();
				couponDTO.setId(couponGroupRelDTO.getCouponId());
				couponDTO = couponFacade.findCouponById(couponDTO);
				List<Long> goodIds = couponFacade.findCouponGoodsIdAll(couponDTO);
				if (goodIds.contains(goodsId)) {
					return true;
				}
			}
		}
		return false;
	}
	//处理以旧换新优惠券状态问题
	private int formatExchangeCouponBatchStatus(Long userId, CouponUnitDTO couponUnitDTO, Long exchangeId) {
		// 查询当前优惠卷批次的已领取数量
		CouponUnitDTO couponUnitDTO_ = new CouponUnitDTO();
		couponUnitDTO_.setCouponBatchId(couponUnitDTO.getCouponBatchId());
		couponUnitDTO_.setUserId(Long.valueOf(-1));
		//该批次领取的数量
		Long unitAllCount = couponUnitFacade.findCouponUnitAllCount(couponUnitDTO_);
		couponUnitDTO_.setCouponBatchId(couponUnitDTO.getCouponBatchId());
		couponUnitDTO_.setUserId(userId);
		//该用户领取的数量
		Long userUnitCount = couponUnitFacade.findCouponUnitAllCount(couponUnitDTO_);

		if(couponUnitDTO.getCouponBatchReceiveStartTime().getTime()>System.currentTimeMillis()){
			//未开始
			return BEFORE_TIME;
		}else{
			if(EmptyUtil.isEmpty(userUnitCount)||userUnitCount==0){
				//未领取
				if(couponUnitDTO.getGrantCount()!=-1&&EmptyUtil.isNotEmpty(unitAllCount)&&unitAllCount!=0&&unitAllCount>=couponUnitDTO.getGrantCount()){
					//无券
					//已领完
					return ALL_RECEIVED;//已领完
				}else{
					// 立即兑换/加价兑换
					return isAddPriceOrExchangeNow(exchangeId,couponUnitDTO.getCouponBatchId());
				}
			}else{
				//已领取过
				if(couponUnitDTO.getGrantCount()!=-1&&EmptyUtil.isNotEmpty(unitAllCount)&&unitAllCount!=0&&unitAllCount>=couponUnitDTO.getGrantCount()){
					//无券
					//已领完
					return ALREADY_RECEIVED;//已领取
				}else{
					//有券
					if(EmptyUtil.isNotEmpty(couponUnitDTO.getIsRepeat())&&couponUnitDTO.getIsRepeat()==1){
						//可在此领取
						return isAddPriceOrExchangeNow(exchangeId,couponUnitDTO.getCouponBatchId());
					}else{
						//不可再次领取
						return ALREADY_RECEIVED;//已领取
					}
				}
			}
		}


		/*if (couponUnitDTO.getId() == null) {
			// 立即领取(未领取或已领取但可重复领取)/已领完
			if (couponUnitDTO.getGrantCount() == null || (couponUnitDTO.getGrantCount().intValue() != -1 &&
					couponUnitDTO.getGrantCount().intValue() <= couponUnitDTOList.size())) {
				// 已领完
				return ALL_RECEIVED;
			} else {
				// 立即兑换/加价兑换
				//todo
				return isAddPriceOrExchangeNow(exchangeId,couponUnitDTO.getCouponBatchId());
			}

		} else {
			// 已领取(已领取且不可重复领取)/立即使用/立即领取(未领取或已领取但可重复领取)
			if (couponUnitDTO.getCouponUnitStatus().intValue() == UNUSED_CODE
					&& (couponUnitDTO.getEffectEndTime() == null ||
					couponUnitDTO.getEffectEndTime().getTime() > System.currentTimeMillis())) {
				// 立即使用
				return USE_NOW;
			} else if ((couponUnitDTO.getCouponUnitStatus() == UNUSED_CODE
					&&(couponUnitDTO.getEffectEndTime() != null
					&& couponUnitDTO.getEffectEndTime().getTime() <= System.currentTimeMillis())
					&& (couponUnitDTO.getIsRepeat() == null || couponUnitDTO.getIsRepeat() == 0))
					|| ((EmptyUtil.isNotEmpty(couponUnitDTO.getIsRepeat())&&couponUnitDTO.getIsRepeat() == 0))
					|| couponUnitDTO.getCouponUnitStatus() == FREEZE_CODE
					) {
				// 已领取(已领取且不可重复领取)
				return ALREADY_RECEIVED;
			} else if ((couponUnitDTO.getCouponUnitStatus() == UNUSED_CODE
					&& (couponUnitDTO.getEffectEndTime() != null
					&& couponUnitDTO.getEffectEndTime().getTime() <= System.currentTimeMillis())
					&& (couponUnitDTO.getIsRepeat() != null && couponUnitDTO.getIsRepeat() == 1))
					|| ((EmptyUtil.isNotEmpty(couponUnitDTO.getIsRepeat())&&couponUnitDTO.getIsRepeat() == 1))) {
				if (couponUnitDTO.getGrantCount().intValue() != -1
						&& couponUnitDTO.getGrantCount().intValue() <= couponUnitDTOList.size()) {
					// 已领完
					return ALL_RECEIVED;
				} else {
					// 立即领取
					return isAddPriceOrExchangeNow(exchangeId,couponUnitDTO.getCouponBatchId());
				}
			} else {
				logger.error("未识别到的Batch状态,unitId: "+ couponUnitDTO.getId());
				return USE_IMMEDIATELY;
			}

		}*/

	}

	private Integer isAddPriceOrExchangeNow(Long exchangeId,Long batchId){
		//查询批次
		ExchangeBatchDTO exchangeBatchDTO = new ExchangeBatchDTO();
		exchangeBatchDTO.setType(	Integer.valueOf(1));
		exchangeBatchDTO.setBatchId(batchId);
		exchangeBatchDTO.setExchangeId(exchangeId);
		List<ExchangeBatchDTO> list=couponUnitFacade.findExchangeBatch(exchangeBatchDTO);
		if(EmptyUtil.isEmpty(list)){
			throw new BusinessException("以旧换新活动已失效");
		}else if(list.size()>1){
			throw new BusinessException("存在脏数据");
		}
		//加价金额>0
		if(list.get(0).getAddPrice().compareTo(BigDecimal.valueOf(0))>0){
			return ADD_PRICE_EXCHANGE;
		}else{
			return CAN_EXCHANGE;
		}


	}




	//处理优惠券批次状态问题
	private int formatCouponBatchStatus(CouponUnitDTO couponUnitDTO) {
		// 查询当前优惠卷批次的已领取数量
		CouponUnitDTO couponUnitDTO_ = new CouponUnitDTO();
		couponUnitDTO_.setCouponBatchId(couponUnitDTO.getCouponBatchId());
		Long couponUnitAllCount = couponUnitFacade.findCouponUnitAllCount(couponUnitDTO_);
		//List<CouponUnitDTO> couponUnitDTOList = couponUnitFacade.findCouponUnitAll(couponUnitDTO_);
		if (couponUnitDTO.getId() == null) {
			//未领取过
			if(couponUnitDTO.getCouponBatchReceiveStartTime().getTime()> System.currentTimeMillis()){
				//未到领取时间
				return BEFORE_TIME;
			}else{
				if(EmptyUtil.isNotEmpty(couponUnitDTO.getGrantCount())&&couponUnitDTO.getGrantCount().intValue() <= couponUnitAllCount&&couponUnitDTO.getGrantCount()!=-1){
					//已领完
					return FINISHED_RECEIVE;
				}else{
					//立即领取
					return CAN_RECEIVE;
				}
			}
		} else {
			//已领取过
			if(couponUnitDTO.getCouponBatchReceiveStartTime().getTime()> System.currentTimeMillis()){
				//未到领取时间
				return BEFORE_TIME;
			}else{
				//领取时间内
				if(couponUnitDTO.getCouponUnitStatus()==UNUSED_CODE){
					//已领取+立即使用
					return USE_IMMEDIATELY_AND_ALREADY_RECEIVED;
				}else if(couponUnitDTO.getCouponUnitStatus()==FREEZE_CODE){
					//已领取
					return ALREADY_RECEIVED;
				}else{
					if((couponUnitDTO.getCouponUnitStatus()==USED_CODE || couponUnitDTO.getCouponUnitStatus()==3||couponUnitDTO.getCouponBatchReceiveEndTime().getTime()<System.currentTimeMillis())
							&&couponUnitDTO.getIsRepeat()==1&&(couponUnitDTO.getGrantCount()==-1||(EmptyUtil.isNotEmpty(couponUnitDTO.getGrantCount())&&couponUnitDTO.getGrantCount()>couponUnitAllCount))){
						//立即领取
						return CAN_RECEIVE;
					}else{
						//已领取
						return ALREADY_RECEIVED;
					}
				}
			}

/*

			// 已领取(已领取且不可重复领取)/立即使用/立即领取(未领取或已领取但可重复领取)
			if (couponUnitDTO.getCouponUnitStatus().intValue() == UNUSED_CODE
					&& (couponUnitDTO.getEffectEndTime() == null || 
					couponUnitDTO.getEffectEndTime().getTime() > System.currentTimeMillis())) {
				// 立即使用
				return USE_IMMEDIATELY;
			} else if ((couponUnitDTO.getCouponUnitStatus() == UNUSED_CODE
					&& (couponUnitDTO.getEffectEndTime() != null 
					&& couponUnitDTO.getEffectEndTime().getTime() <= System.currentTimeMillis())
					&& (couponUnitDTO.getIsRepeat() == null || couponUnitDTO.getIsRepeat() == 0))
					|| (couponUnitDTO.getCouponUnitStatus() == USED_CODE && (EmptyUtil.isNotEmpty(couponUnitDTO.getIsRepeat())&&couponUnitDTO.getIsRepeat() == 0))
					|| couponUnitDTO.getCouponUnitStatus() == FREEZE_CODE) {
				// 已领取(已领取且不可重复领取)
				return ALREADY_RECEIVED;
			} else if ((couponUnitDTO.getCouponUnitStatus() == UNUSED_CODE
					&& (couponUnitDTO.getEffectEndTime() != null 
					&& couponUnitDTO.getEffectEndTime().getTime() <= System.currentTimeMillis())
					&& (couponUnitDTO.getIsRepeat() != null && couponUnitDTO.getIsRepeat() == 1))
					|| (couponUnitDTO.getCouponUnitStatus() == USED_CODE && (EmptyUtil.isNotEmpty(couponUnitDTO.getIsRepeat())&&couponUnitDTO.getIsRepeat() == 1))) {
				if (EmptyUtil.isNotEmpty(couponUnitDTO.getGrantCount())&&couponUnitDTO.getGrantCount().intValue() != -1
						&& couponUnitDTO.getGrantCount().intValue() <= couponUnitDTOList.size()) {
					// 已领完
					return FINISHED_RECEIVE;
				} else {
					// 立即领取
					return CAN_RECEIVE;
				}
			} else {
				logger.error("未识别到的Batch状态,unitId: "+ couponUnitDTO.getId());
				return USE_IMMEDIATELY;
			}*/

		}

	}

	@Override
	public List<Map<String, Object>> findCouponBatchGoodsOfPage(CouponUnitDTO dto, Pagination page) {
		page.setOrderBy("effect_start_time,effect_end_time,create_time");
		List<CouponUnitDTO> rt = couponUnitFacade.findSUCouponBatchOfPage(dto, page);
		Date now = new Date();
		List<Map<String, Object>> list = new ArrayList<>();
		for (CouponUnitDTO couponUnitDTO : rt) {
			Map<String, Object> map = getCouponBatchUnitObj(couponUnitDTO);
			map.put("id", couponUnitDTO.getCouponBatchId());
			map.put("couponUnitId",couponUnitDTO.getId());

			//处理以旧换新活动
			List<Long> exchangeIds = checkIsShowExchange(couponUnitDTO.getCouponBatchId(), couponUnitDTO.getCouponUnitStatus());
			if(EmptyUtil.isEmpty(exchangeIds)){
				map.put("isShowExchange",NO_SHOW_EXCHANGE);

			}else{
				map.put("isShowExchange",SHOW_EXCHANGE);
			}
			map.put("exchangeIdList",exchangeIds);
			// 商品详情页
			if (isContainCurrentSu(couponUnitDTO, dto.getGoodsId())) {
				// 优惠卷包含当前商品
				map.put("couponBatchStatus", formatSUCouponBatchStatus(couponUnitDTO));
				list.add(map);
			}
			
			//新增 cmsPageId 字段	
			setCmsPageId(map, couponUnitDTO.getCouponBatchId(), dto.getClientId(),dto.getPlatformId(),
					new CouponDTO(couponUnitDTO.getCouponId(), couponUnitDTO.getCouponType(), couponUnitDTO.getJumpType()));
		

		}

		return list;
	}

	//处理优惠券批次状态问题
	private int formatSUCouponBatchStatus(CouponUnitDTO couponUnitDTO) {
		// 查询当前优惠卷批次的已领取数量
		CouponUnitDTO couponUnitDTO_ = new CouponUnitDTO();
		couponUnitDTO_.setCouponBatchId(couponUnitDTO.getCouponBatchId());
		Long couponUnitAllCount = couponUnitFacade.findCouponUnitAllCount(couponUnitDTO_);
		//List<CouponUnitDTO> couponUnitDTOList = couponUnitFacade.findCouponUnitAll(couponUnitDTO_);
		if (couponUnitDTO.getId() == null) {
			//未领取过
			if(couponUnitDTO.getCouponBatchReceiveStartTime().getTime()> System.currentTimeMillis()){
				//未到领取时间
				return BEFORE_TIME;
			}else{
				if(EmptyUtil.isNotEmpty(couponUnitDTO.getGrantCount())&&couponUnitDTO.getGrantCount().intValue() <= couponUnitAllCount&&couponUnitDTO.getGrantCount()!=-1){
					//已领完
					return FINISHED_RECEIVE;
				}else{
					//立即领取
					return CAN_RECEIVE;
				}
			}
		} else {
			// 已领取(已领取且不可重复领取)/立即使用/立即领取(未领取或已领取但可重复领取)
			if (couponUnitDTO.getCouponUnitStatus().intValue() == UNUSED_CODE
					&& (couponUnitDTO.getEffectEndTime() == null ||
					couponUnitDTO.getEffectEndTime().getTime() > System.currentTimeMillis())) {
				// 立即使用
				return USE_IMMEDIATELY;
			} else if ((couponUnitDTO.getCouponUnitStatus() == UNUSED_CODE
					&& (couponUnitDTO.getEffectEndTime() != null
					&& couponUnitDTO.getEffectEndTime().getTime() <= System.currentTimeMillis())
					&& (couponUnitDTO.getIsRepeat() == null || couponUnitDTO.getIsRepeat() == 0))
					|| (couponUnitDTO.getCouponUnitStatus() == USED_CODE && (EmptyUtil.isNotEmpty(couponUnitDTO.getIsRepeat())&&couponUnitDTO.getIsRepeat() == 0))
					|| couponUnitDTO.getCouponUnitStatus() == FREEZE_CODE||couponUnitDTO.getCouponUnitStatus()==EXCHANGE_CODE) {
				// 已领取(已领取且不可重复领取或者已兑换)
				return ALREADY_RECEIVED;
			} else if ((couponUnitDTO.getCouponUnitStatus() == UNUSED_CODE
					&& (couponUnitDTO.getEffectEndTime() != null
					&& couponUnitDTO.getEffectEndTime().getTime() <= System.currentTimeMillis())
					&& (couponUnitDTO.getIsRepeat() != null && couponUnitDTO.getIsRepeat() == 1))
					|| (couponUnitDTO.getCouponUnitStatus() == USED_CODE && (EmptyUtil.isNotEmpty(couponUnitDTO.getIsRepeat())&&couponUnitDTO.getIsRepeat() == 1))) {
				if (EmptyUtil.isNotEmpty(couponUnitDTO.getGrantCount())&&couponUnitDTO.getGrantCount().intValue() != -1
						&& couponUnitDTO.getGrantCount().intValue() <= couponUnitAllCount) {
					// 已领完
					return FINISHED_RECEIVE;
				} else {
					// 立即领取
					return CAN_RECEIVE;
				}
			} else {
				logger.error("未识别到的Batch状态,unitId: "+ couponUnitDTO.getId());
				return USE_IMMEDIATELY;
			}

		}

	}
	/**
	 * 优惠卷列表返回对象相同部分
	 * 
	 * @param couponUnitDTO
	 * @return
	 */
	private Map<String, Object> getCouponBatchUnitObj(CouponUnitDTO couponUnitDTO) {
		Map<String, Object> map = new HashMap<>();
		
		map.put("effectStartTime", couponUnitDTO.getCouponBatchEffectStartTime() != null
				? couponUnitDTO.getCouponBatchEffectStartTime().getTime() : null);
		map.put("effectEndTime", couponUnitDTO.getCouponBatchEffectEndTime() != null
				? couponUnitDTO.getCouponBatchEffectEndTime().getTime() : null);
		
		// 有效期处理问题
		dealEffectTimeRange(couponUnitDTO, map);
		
		map.put("title", couponUnitDTO.getTitle());
		map.put("couponType", couponUnitDTO.getCouponType());

		map.put("discountAmount",
				couponUnitDTO.getDiscountAmount() != null ? couponUnitDTO.getDiscountAmount().intValue() : null);
		map.put("triggerAmount",
				couponUnitDTO.getTriggerAmount() != null ? couponUnitDTO.getTriggerAmount().intValue() : null);
		map.put("iconUrl", couponUnitDTO.getIconUrl());
		map.put("detail", couponUnitDTO.getDetail());
		map.put("jumpType", couponUnitDTO.getJumpType());
		map.put("goodsType", couponUnitDTO.getGoodsType());
		map.put("goodsId", couponUnitDTO.getGoodsId());
		map.put("storeIds", couponUnitDTO.getStoreIds());
		if (couponUnitDTO.getGoodsType() == 0) {
			// 单su
			// 通过su的id查询commodityTemplateId
			Long commodityTemplateId = couponFacade.queryTempIdBySuId(couponUnitDTO.getGoodsId());
			map.put("commodityTemplateId", commodityTemplateId);
		} else if (couponUnitDTO.getGoodsType() == 1) {
			// 商品组
			map.put("commodityTemplateId", -1L);
		}

		return map;
	}



	private void dealEffectTimeRange(CouponUnitDTO couponUnitDTO, Map<String, Object> map) {
		if (couponUnitDTO.getId() != null) {
			// 已领取优惠卷unit,以unit有效期为准
			if (couponUnitDTO.getEffectStartTime() == null || couponUnitDTO.getEffectEndTime() == null) {
				map.put("effectTimeRange", "有效期:不限时间");
				map.put("isShowEffectTimeRange", true);
			} else {
				map.put("effectTimeRange", DateUtils.format(DateUtils.DATE_FORMAT_POINT, couponUnitDTO.getEffectStartTime())
						+ "-" + DateUtils.format(DateUtils.DATE_FORMAT_POINT, couponUnitDTO.getEffectEndTime()));
				if (couponUnitDTO.getEffectStartTime().before(new Date()) 
						&& couponUnitDTO.getEffectEndTime().after(new Date())) {
					map.put("isShowEffectTimeRange", true);
				} else {
					map.put("isShowEffectTimeRange", false);
				}
			}
			
		} else {
			// 未领取,以批次有效期为准
			if (couponUnitDTO.getEffectDays() != null && !couponUnitDTO.getEffectDays().equals(Integer.valueOf(-1))){
				/*map.put("effectTimeRange", DateUtils.format(DateUtils.DATE_FORMAT_POINT, new Date())
						+ "-" + DateUtils.format(DateUtils.DATE_FORMAT_POINT, DateUtils.addDays(new Date(), couponUnitDTO.getEffectDays())));*/
				map.put("effectTimeRange", "从领取日起"+couponUnitDTO.getEffectDays()+"日内有效");
				map.put("isShowEffectTimeRange", true);
			} else if (couponUnitDTO.getCouponBatchEffectStartTime() != null && couponUnitDTO.getCouponBatchEffectEndTime() != null) {
				
				map.put("effectTimeRange", DateUtils.format(DateUtils.DATE_FORMAT_POINT, couponUnitDTO.getCouponBatchEffectStartTime())
						+ "-" + DateUtils.format(DateUtils.DATE_FORMAT_POINT, couponUnitDTO.getCouponBatchEffectEndTime()));
				if (couponUnitDTO.getCouponBatchEffectStartTime().before(new Date()) 
						&& couponUnitDTO.getCouponBatchEffectEndTime().after(new Date())) {
					map.put("isShowEffectTimeRange", true);
				} else {
					map.put("isShowEffectTimeRange", false);
				}
			} else {
				map.put("effectTimeRange", "有效期:不限时间");
				map.put("isShowEffectTimeRange", true);
			}
		}
	}


	@Override
	public JsonResult<List<Map<String,Object>>> findExchangeActivityByCouponUnitId(Long couponUnitId, Long userId, Long companyId, Long storeId,
			Long clientId,Long platformId) {		
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");

		//查询优惠券
		CouponUnitDTO unitDTO = new CouponUnitDTO();
		unitDTO.setId(couponUnitId);
		CouponUnitDTO couponUnitDTO = couponUnitFacade.findCouponUnitById(unitDTO);
		if(EmptyUtil.isEmpty(couponUnitDTO)){
			return JsonResult.fail("优惠券unit不存在");
		}
		//2.判断是否存在有效的活动
		List<Long> exchangeList = checkIsShowExchange(couponUnitDTO.getCouponBatchId(), couponUnitDTO.getCouponUnitStatus());
		if(EmptyUtil.isEmpty(exchangeList)){
			return JsonResult.fail("优惠券unit无以旧换新活动",BusinessExceptionConstant.NO_EXCHANGE);
		}
		//3.查询活动
		List<ExchangeActivityDTO> exchangeActivityDTOList=couponUnitFacade.findCouponUnitByIds(exchangeList);
		List<Map<String, Object>> result = new ArrayList<>();
		//4.拼装活动对应的批次信息
		for(ExchangeActivityDTO dto:exchangeActivityDTOList){
			List<CouponBatchDTO> couponBatchDTOList=couponUnitFacade.findCouponBatchByExchange(dto.getId());
			logger.info("参数:活动id="+dto.getId());
			if(EmptyUtil.isEmpty(couponBatchDTOList)){
				continue;
			}

			Map<String, Object> activityMap = new HashMap<>();
			activityMap.put("exchangeName",dto.getExchangeName());
			activityMap.put("exchangeId",dto.getId());
			activityMap.put("endTime","活动截止日期至"+ format.format(dto.getEndTime()));
			List<Map<String, Object>> list = new ArrayList<>();
			for(CouponBatchDTO batchDTO:couponBatchDTOList) {
				CouponUnitDTO unitDTO1 = new CouponUnitDTO();
				unitDTO1.setCouponBatchId(batchDTO.getId());
				unitDTO1.setUserId(userId);
				unitDTO1.setCompanyId(companyId);
				unitDTO1.setCouponRelType(0);
				unitDTO1.setStoreId(storeId);
				unitDTO1.setPlatformId(platformId);


				List<CouponUnitDTO> rt = couponUnitFacade.findCouponUnitAndBatchExchange(unitDTO1);
				if(EmptyUtil.isEmpty(rt)){
					continue;
				}
				if (rt.size() > 1) {
					return JsonResult.fail("优惠券有误");
				}


				CouponUnitDTO unit = rt.get(0);
				Map<String, Object> map = getCouponBatchUnitObj(unit);
				// 领卷中心
				Integer unitStatus = formatExchangeCouponBatchStatus(userId,unit,dto.getId());
				map.put("id", couponUnitId);
				map.put("couponBatchId", unit.getCouponBatchId());
				
				// 添加pageId 信息
				setCmsPageId(map , unit.getCouponBatchId(),clientId,platformId,
						new CouponDTO(unit.getCouponId(), unit.getCouponType(), unit.getJumpType()));
				
				
				map.put("couponBatchStatus", unitStatus);
				map.put("isSoonExpire", isSoonExpire(unit.getCouponBatchEffectEndTime()));
				//加价金额
				ExchangeBatchDTO exchangeBatch = new ExchangeBatchDTO();
				exchangeBatch.setType(Integer.valueOf(1));//新优惠券批次
				exchangeBatch.setBatchId(batchDTO.getId());
				exchangeBatch.setExchangeId(dto.getId());
				List<ExchangeBatchDTO> exchangeBatchAll = couponUnitFacade.findExchangeBatch(exchangeBatch);
				if(EmptyUtil.isEmpty(exchangeBatchAll)||exchangeBatchAll.size()>1){
					throw new BusinessException("数据有误");
				}
				if(EmptyUtil.isNotEmpty(exchangeBatchAll.get(0).getAddPrice())&&exchangeBatchAll.get(0).getAddPrice().compareTo(BigDecimal.valueOf(0))>0){
					map.put("addPrice",exchangeBatchAll.get(0).getAddPrice().intValue());
				}


				list.add(map);
			}
			activityMap.put("couponList",list);
			//如果没有可见优惠券批次,将该活动隐藏
			if(EmptyUtil.isNotEmpty(list)){
				result.add(activityMap);
			}
		}

		return JsonResult.success(result);
	}


	private Boolean isSoonExpire(Date time){
		//是否快过期
		boolean isSoonExpire=false;
		if(EmptyUtil.isNotEmpty(time)){
			if(DateUtils.addDays(new Date(), 3).getTime() >= time.getTime()&&time.getTime()>=new Date().getTime()){
				isSoonExpire = true;
			}
		}
		return isSoonExpire;
	}


	/**
	 *  vo中 set linkableButtonPageList信息
	 * @param map
	 * @param couponBatchId
	 */
	private void setCmsPageId(Map<String, Object> map , Long couponBatchId,Long clientId ,Long platformId,CouponDTO couponDTO) {
		//clientId 映射为  clientType
		Integer clienType = CmsConstant.CMS_CLINTE_TYPE_MOBILE;
		logger.info("优惠券默认页面请求参数 ：couponBatchId:" + couponBatchId + " clientId:" + clientId + "couponId:" + couponDTO.getId() );
				
		Integer jumpType = couponDTO.getJumpType();
		Integer couponType = couponDTO.getCouponType();
		logger.info("优惠券默认页面请求参数 ：jumpType : " + jumpType + " ");
		if(clientId != null) {
			clienType = clientId == 3 ? CmsConstant.CMS_CLINTE_TYPE_PC : CmsConstant.CMS_CLINTE_TYPE_MOBILE;
		}
		
		//跳转类型不为SU组 直接跳过
		if(jumpType == null || jumpType != 2) {
			
			map.put("cmsPageId", null);		
			return ;
		}

		List<LinkableButtonPageDTO> listDto = couponUnitFacade.findLinkableButtonPageByCouponBatchId(couponBatchId);
		
		Long cmsPageId = null;	
		
		if(listDto == null || listDto.size() == 0) {				
			
			map.put("cmsPageId", getDefaultPageId(platformId,clienType ,couponType));		
			
			return ;
		}
		
		for (LinkableButtonPageDTO linkableButtonPageDTO : listDto) {
			
			if(clienType == linkableButtonPageDTO.getClientType()) {
				
				cmsPageId = linkableButtonPageDTO.getCmsPageId();
			}
		}
		
		if(cmsPageId == null) {
			cmsPageId = getDefaultPageId(platformId,clienType ,couponType);
		}
		map.put("cmsPageId", cmsPageId);
	}


	/**
	 * 获取默认初始化页面
	 * @param platformId
	 * @param clienType
	 * @param couponType
	 * @return
	 */
	public Long getDefaultPageId(Long platformId,Integer clienType ,Integer couponType) {
		
		Long cmsPageId = null;
		
		if(platformId == CmsConstant.CMS_PLATFORM_FGJ) {
			
			if(clienType == CmsConstant.CMS_CLINTE_TYPE_MOBILE) {
				
				if(couponType == CmsConstant.CMS_COUPON_TYPE_FULL_REDUCED) {
					cmsPageId =   CmsConstant.CMS_DEFAULT_PAGE_COUPON_3;
				} else if(couponType == CmsConstant.CMS_COUPON_TYPE_EXCHANGE) {
					cmsPageId =   CmsConstant.CMS_DEFAULT_PAGE_COUPON_4;
				}
				
			} else {
				
				if(couponType == CmsConstant.CMS_COUPON_TYPE_FULL_REDUCED) {
					cmsPageId =   CmsConstant.CMS_DEFAULT_PAGE_COUPON_5;
				} else if(couponType == CmsConstant.CMS_COUPON_TYPE_EXCHANGE) {
					cmsPageId =   CmsConstant.CMS_DEFAULT_PAGE_COUPON_6;
				}
			}
		} else if(platformId == CmsConstant.CMS_PLATFORM_MYY) {
			
				if(clienType == CmsConstant.CMS_CLINTE_TYPE_MOBILE) {
				
				if(couponType == CmsConstant.CMS_COUPON_TYPE_FULL_REDUCED) {
					cmsPageId =   CmsConstant.CMS_DEFAULT_PAGE_COUPON_11;
				} else if(couponType == CmsConstant.CMS_COUPON_TYPE_EXCHANGE) {
					cmsPageId =   CmsConstant.CMS_DEFAULT_PAGE_COUPON_12;
				}
				
			} else {
				
				if(couponType == CmsConstant.CMS_COUPON_TYPE_FULL_REDUCED) {
					cmsPageId =   CmsConstant.CMS_DEFAULT_PAGE_COUPON_13;
				} else if(couponType == CmsConstant.CMS_COUPON_TYPE_EXCHANGE) {
					cmsPageId =   CmsConstant.CMS_DEFAULT_PAGE_COUPON_14;
				}
			}
		}
		
		logger.info(" 获取初始化 pageId : " + cmsPageId
				);
		return cmsPageId;
	}
	
	@Override
	public void updateCouponUnitReadStatus(List<Long> ids) {
		couponUnitFacade.updateCouponUnitReadStatus(ids);
	}
}
