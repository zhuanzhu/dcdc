package com.egeo.components.promotion.service.write.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.common.DateUtils;
import com.egeo.components.promotion.converter.CouponBatchConverter;
import com.egeo.components.promotion.dto.CouponBatchDTO;
import com.egeo.components.promotion.manage.read.CouponGroupRelReadManage;
import com.egeo.components.promotion.manage.read.CouponReadManage;
import com.egeo.components.promotion.manage.read.CouponUnitReadManage;
import com.egeo.components.promotion.manage.write.CouponBatchCompanyWriteManage;
import com.egeo.components.promotion.manage.write.CouponBatchWriteManage;
import com.egeo.components.promotion.manage.write.CouponUnitWriteManage;
import com.egeo.components.promotion.po.CouponBatchCompanyPO;
import com.egeo.components.promotion.po.CouponBatchPO;
import com.egeo.components.promotion.po.CouponGroupRelPO;
import com.egeo.components.promotion.po.CouponPO;
import com.egeo.components.promotion.po.CouponUnitPO;
import com.egeo.components.promotion.service.write.CouponBatchWriteService;
import com.egeo.exception.BusinessException;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.SequenceUtil;

@Service("couponBatchWriteService")
public class CouponBatchWriteServiceImpl implements CouponBatchWriteService {
	@Autowired
	private CouponBatchWriteManage couponBatchWriteManage;

	@Autowired
	private CouponBatchCompanyWriteManage couponBatchCompanyWriteManage;

	@Autowired
	private CouponReadManage couponReadManage;

	@Autowired
	private CouponGroupRelReadManage couponGroupRelReadManage;

	@Autowired
	private CouponUnitWriteManage couponUnitWriteManage;

	@Autowired
	private CouponUnitReadManage couponUnitReadManage;

	@Override
	public Long insertCouponBatchWithTx(CouponBatchDTO dto) {
		CouponBatchPO po = CouponBatchConverter.toPO(dto);
		Long rt = couponBatchWriteManage.insertCouponBatchWithTx(po);

		// 优惠卷批次为系统发放,直接生成优惠卷unit,以及建立优惠卷批次和公司的关系
		if (dto.getGrantType() == 0) {
			// 1.优惠卷unit
			dto.setId(rt);
			if (dto.getCouponRelType() == 0) {
				// 优惠卷
				insertCouponUnitByCouponBatch(dto.getCouponRelId(), dto);
			} else if (dto.getCouponRelType() == 1) {
				// 优惠卷分组
				CouponGroupRelPO couponGroupRelPO = new CouponGroupRelPO();
				couponGroupRelPO.setCouponGroupId(dto.getCouponRelId());
				List<CouponGroupRelPO> couponGroupRelPOList = couponGroupRelReadManage.findCouponGroupRelAll(couponGroupRelPO);
				for (CouponGroupRelPO couponGroupRelPO_ : couponGroupRelPOList) {
					insertCouponUnitByCouponBatch(couponGroupRelPO_.getCouponId(), dto);
				}
			}

			// 2.公司
			if (dto.getChooseWay() == 0) {
				for (Long companyId : dto.getCompList()) {
					CouponBatchCompanyPO CouponBatchCompanyPO = new CouponBatchCompanyPO();
					CouponBatchCompanyPO.setCouponBatchId(dto.getId());
					CouponBatchCompanyPO.setCompanyId(companyId);
					couponBatchCompanyWriteManage.insertCouponBatchCompanyWithTx(CouponBatchCompanyPO);
				}
			}
		}

		return rt;
	}

	/**
	 * 插入系统发放优惠卷批次时,直接生成优惠卷unit
	 * 
	 * @param couponId
	 * @param dto
	 */
	private void insertCouponUnitByCouponBatch(Long couponId, CouponBatchDTO dto) {
		CouponPO couponPO = new CouponPO();
		couponPO.setId(couponId);
		couponPO = couponReadManage.findCouponById(couponPO);
		int i=0;
		for (Long empId : dto.getEmpList()) {
			CouponUnitPO couponUnitPO = new CouponUnitPO();
			couponUnitPO.setBatchIndex(Long.valueOf(i));
			couponUnitPO.setCouponUnitCode(SequenceUtil.genCouponUnitNo(couponPO.getCouponType(),i));
			/*List<CouponUnitPO> couponUnitPOList = couponUnitReadManage.findCouponUnitAll(couponUnitPO);
			if (EmptyUtil.isNotEmpty(couponUnitPOList))
				throw new BusinessException("新增优惠卷unit失败: 优惠卷unit编号重复");*/
			couponUnitPO.setCouponId(couponPO.getId());
			couponUnitPO.setCouponBatchId(dto.getId());
			couponUnitPO.setUserId(empId);
			couponUnitPO.setPlatformId(dto.getPlatformId());
			//领取时间
			couponUnitPO.setReceivedTime(new Date());
			// 设置有效期
			if (dto.getEffectDays() != null 
					&& !dto.getEffectDays().equals(Integer.valueOf(-1))) {
				couponUnitPO.setEffectStartTime(new Date());
				couponUnitPO.setEffectEndTime(DateUtils.addDays(new Date(), dto.getEffectDays().intValue()));
			} else {
				couponUnitPO.setEffectStartTime(dto.getEffectStartTime());
				couponUnitPO.setEffectEndTime(dto.getEffectEndTime());
			}
			couponUnitWriteManage.insertCouponUnitWithTx(couponUnitPO);
			i++;
		}
	}

	@Override
	public int updateCouponBatchWithTx(CouponBatchDTO dto) {
		CouponBatchPO po = CouponBatchConverter.toPO(dto);
		int rt = couponBatchWriteManage.updateCouponBatchWithTx(po);
		return rt;
	}

	@Override
	public int deleteCouponBatchWithTx(CouponBatchDTO dto) {
		CouponBatchPO po = CouponBatchConverter.toPO(dto);
		int rt = couponBatchWriteManage.deleteCouponBatchWithTx(po);
		return rt;
	}

	@Override
	public int invalidCouponBatchWithTx(CouponBatchDTO dto) {
		int i = 0;

		// 该批次下所有可使用(未过期)的优惠券更改为已失效状态
		CouponUnitPO couponUnitPO_ = new CouponUnitPO();
		couponUnitPO_.setCouponBatchId(dto.getId());
		couponUnitPO_.setCouponUnitStatus(0);
		List<CouponUnitPO> couponUnitPOList = couponUnitReadManage.findCouponUnitAll(couponUnitPO_);
		for (CouponUnitPO couponUnit : couponUnitPOList) {
			if (couponUnit.getCouponUnitStatus() == 0 && (couponUnit.getEffectEndTime() == null 
					|| couponUnit.getEffectEndTime().getTime() > System.currentTimeMillis())){
				// 未过期
				CouponUnitPO couponUnitPO = new CouponUnitPO();
				couponUnitPO.setId(couponUnit.getId());
				couponUnitPO.setCouponUnitStatus(4);
				i = couponUnitWriteManage.updateCouponUnitWithTx(couponUnitPO);
			}
		}
		
		if (dto.getGrantType() == 1) {
			// 用户领取,是否前端显示更改为否
			dto.setIsDisplay(0);
		}
		dto.setIsEffect(1);
		i = couponBatchWriteManage.updateCouponBatchWithTx(CouponBatchConverter.toPO(dto));
		return i;
	}

	@Override
	public int hideCouponBatch() {
		return couponBatchWriteManage.hideCouponBatch();
	}

	@Override
	public Long insertCouponBatchAndUnitWithTx(CouponBatchDTO dto) {
		CouponBatchPO po = CouponBatchConverter.toPO(dto);
		Long rt = couponBatchWriteManage.insertCouponBatchWithTx(po);
/*

		dto.setId(rt);
		//插入coupon_unit
		// 优惠卷批次为用户领取,且领取方式为unit领取时生成coupon_unit
		if (dto.getGrantType() == 1&&dto.getGetType()==2) {
			// 1.优惠卷unit
			if (dto.getCouponRelType() == 0) {
				// 优惠卷
				insertCouponUnitByCouponBatchAndCount(dto);
			}
		}
*/

		return rt;
	}

	private void insertCouponUnitByCouponBatchAndCount(CouponBatchDTO dto) {
		CouponPO couponPO = new CouponPO();
		couponPO.setId(dto.getCouponRelId());
		couponPO = couponReadManage.findCouponById(couponPO);
		List<CouponUnitPO> list = new ArrayList<>();
		for(int i=0;i<dto.getGrantCount();i++){
			CouponUnitPO couponUnitPO = new CouponUnitPO();
			couponUnitPO.setBatchIndex(Long.valueOf(i));
			couponUnitPO.setCouponUnitCode(SequenceUtil.genCouponUnitNo(couponPO.getCouponType(),i));
			List<CouponUnitPO> couponUnitPOList = couponUnitReadManage.findCouponUnitAll(couponUnitPO);
			if (EmptyUtil.isNotEmpty(couponUnitPOList))
				throw new BusinessException("新增优惠卷unit失败: 优惠卷unit编号重复");
			couponUnitPO.setCouponId(couponPO.getId());
			couponUnitPO.setCouponBatchId(dto.getId());
			couponUnitPO.setPlatformId(dto.getPlatformId());
			couponUnitPO.setCouponUnitStatus(Integer.valueOf(0));
			// 设置有效期
			if (dto.getEffectDays() != null
					&& !dto.getEffectDays().equals(Integer.valueOf(-1))) {
				couponUnitPO.setEffectStartTime(new Date());
				couponUnitPO.setEffectEndTime(DateUtils.addDays(new Date(), dto.getEffectDays().intValue()));
			} else {
				couponUnitPO.setEffectStartTime(dto.getEffectStartTime());
				couponUnitPO.setEffectEndTime(dto.getEffectEndTime());
			}
			list.add(couponUnitPO);

		}
		couponUnitWriteManage.insertCouponUnitListWithTx(list);


	}
}
