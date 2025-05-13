package com.egeo.components.promotion.manage.write.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.common.DateUtils;
import com.egeo.components.promotion.dao.read.CouponBatchReadDAO;
import com.egeo.components.promotion.dao.read.CouponReadDAO;
import com.egeo.components.promotion.dao.read.CouponUnitReadDAO;
import com.egeo.components.promotion.dao.read.ExchangeActivityReadDAO;
import com.egeo.components.promotion.dao.read.ExchangeBatchReadDAO;
import com.egeo.components.promotion.dao.read.ExchangeCouponUnitStatusReadDAO;
import com.egeo.components.promotion.dao.read.ExchangeOrderRecordReadDAO;
import com.egeo.components.promotion.dao.write.CouponUnitWriteDAO;
import com.egeo.components.promotion.dao.write.ExchangeOrderRecordWriteDAO;
import com.egeo.components.promotion.manage.write.ExchangeOrderRecordWriteManage;
import com.egeo.components.promotion.po.CouponBatchPO;
import com.egeo.components.promotion.po.CouponPO;
import com.egeo.components.promotion.po.CouponUnitPO;
import com.egeo.components.promotion.po.ExchangeActivityPO;
import com.egeo.components.promotion.po.ExchangeCouponUnitStatusPO;
import com.egeo.components.promotion.po.ExchangeOrderRecordPO;
import com.egeo.core.Constant.BusinessExceptionConstant;
import com.egeo.exception.BusinessException;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.SequenceUtil;

@Service
public class ExchangeOrderRecordWriteManageImpl implements ExchangeOrderRecordWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ExchangeOrderRecordWriteDAO exchangeOrderRecordWriteDAO;
	@Autowired
	private CouponUnitWriteDAO couponUnitWriteDAO;
	@Autowired
	private CouponReadDAO couponReadDAO;
	@Autowired
	private CouponUnitReadDAO couponUnitReadDAO;
	@Autowired
	private CouponBatchReadDAO couponBatchReadDAO;
@Autowired
private ExchangeActivityReadDAO exchangeActivityReadDAO;
@Autowired
private ExchangeBatchReadDAO exchangeBatchReadDAO;
@Autowired
private ExchangeCouponUnitStatusReadDAO exchangeCouponUnitStatusReadDAO;
@Autowired
private ExchangeOrderRecordReadDAO exchangeOrderRecordReadDAO;

	@Override
	public Long insertExchangeOrderRecordWithTx(ExchangeOrderRecordPO po) {
		
		int i ;
		try {
				i = exchangeOrderRecordWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateExchangeOrderRecordWithTx(ExchangeOrderRecordPO po) {
		int i;
		i = exchangeOrderRecordWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}
	@Override
	public int updateExchangeOrderRecordByOrderCodeWithTx(ExchangeOrderRecordPO po) {
		int i;
		i = exchangeOrderRecordWriteDAO.updateByOrderCode(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteExchangeOrderRecordWithTx(ExchangeOrderRecordPO po) {
		int i;
		i = exchangeOrderRecordWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}

	@Override
	public boolean updateExchangeAndCouponWithTx(String couponUnitCode, Integer couponUnitStatus, Long couponUnitId, CouponBatchPO couponBatchPO, Long recordId, Long userId) {
		try{
			ExchangeOrderRecordPO exchangeOrderRecordPO1 = new ExchangeOrderRecordPO();
			exchangeOrderRecordPO1.setId(recordId);
			ExchangeOrderRecordPO byId = exchangeOrderRecordReadDAO.findById(exchangeOrderRecordPO1);
			if(EmptyUtil.isNotEmpty(byId)&&EmptyUtil.isNotEmpty(byId.getConversionStatus())&&byId.getConversionStatus()==1){
				logger.info("[以旧换新锁内校验]:该券以兑换完成");
				throw new BusinessException(BusinessExceptionConstant.THREAD_ERROR,"并发数据异常");
			}
			//查询旧unit

			ExchangeOrderRecordPO exchangeOrderRecordPO2 = new ExchangeOrderRecordPO();
			exchangeOrderRecordPO2.setOldUnitCode(couponUnitCode);
			exchangeOrderRecordPO2.setConversionStatus(1);
			List<ExchangeOrderRecordPO> all = exchangeOrderRecordReadDAO.findAll(exchangeOrderRecordPO2,null);
			if(EmptyUtil.isNotEmpty(all)){
				logger.info("[以旧换新锁内校验]:旧unit已兑换");
				throw new BusinessException(BusinessExceptionConstant.THREAD_ERROR,"并发数据异常");
			}


			CouponPO couponPO = new CouponPO();
			couponPO.setId(couponBatchPO.getCouponRelId());
			CouponPO coupon = couponReadDAO.findById(couponPO);




			CouponUnitPO couponUnitPO = new CouponUnitPO();
			couponUnitPO.setCouponBatchId(couponBatchPO.getId());
			Long couponUnitAllCount = couponUnitReadDAO.findCouponUnitAllCount(couponUnitPO);
			if(couponBatchPO.getGrantCount()!=-1&&couponBatchPO.getGrantCount()<couponUnitAllCount){
				logger.info("[以旧换新锁内校验]:新批次优惠券已全部发放");
				throw new BusinessException(BusinessExceptionConstant.THREAD_ERROR,"并发数据异常");
			}



			if(couponBatchPO.getIsRepeat()==0){
				//如果是不可重复领取
				CouponUnitPO couponUnitPO1 = new CouponUnitPO();
				couponUnitPO1.setUserId(userId);
				couponUnitPO1.setCouponBatchId(couponBatchPO.getId());
				Long count = couponUnitReadDAO.findCouponUnitAllCount(couponUnitPO1);
				if(count>=2){
					logger.info("[以旧换新锁内校验]:该优惠券不可重复领取");
					throw new BusinessException(BusinessExceptionConstant.THREAD_ERROR,"并发数据异常");
				}
			}



			//1.设置旧优惠券unit为已兑换状态
			CouponUnitPO unitPO = new CouponUnitPO();
			unitPO.setId(couponUnitId);
			unitPO.setCouponUnitStatus(Integer.valueOf(5));//设置已兑换
			couponUnitWriteDAO.update(unitPO);

			//2.生成新的优惠券批次对应的couponUnit
			CouponUnitPO po = new CouponUnitPO();
			// 验证通过,添加优惠卷unit
			po.setCouponUnitCode(SequenceUtil.genCouponUnitNo(coupon.getCouponType(),Integer.valueOf(0)));//一次仅一张
			po.setBatchIndex(Long.valueOf(0));
			po.setPlatformId(coupon.getPlatformId());
			po.setCouponId(couponBatchPO.getCouponRelId());
			po.setCouponBatchId(couponBatchPO.getId());
			po.setUserId(userId);
			// 设置优惠卷unit的有效时间
			if (couponBatchPO.getEffectDays() != null
					&& !couponBatchPO.getEffectDays().equals(Integer.valueOf(-1))) {

				po.setEffectStartTime(new Date());
				Date endDate = DateUtils.addDays(new Date(), couponBatchPO.getEffectDays().intValue());
				po.setEffectEndTime(processEndTime(endDate));
			} else {
				po.setEffectStartTime(couponBatchPO.getEffectStartTime());
				po.setEffectEndTime(processEndTime(couponBatchPO.getEffectEndTime()));
			}
			//领取时间
			po.setReceivedTime(new Date());


			couponUnitWriteDAO.insert(po);
			//3.更新兑换记录
			ExchangeOrderRecordPO recordPO = new ExchangeOrderRecordPO();
			recordPO.setNewUnitCode(po.getCouponUnitCode());
			recordPO.setNewBatchCode(couponBatchPO.getCouponBatchCode());
			recordPO.setNewCouponName(coupon.getTitle());
			recordPO.setNewCouponType(coupon.getCouponType());
			recordPO.setConversionStatus(Integer.valueOf(1));
			recordPO.setId(recordId);
			recordPO.setNewCouponBatchName(couponBatchPO.getCouponBatchName());
			exchangeOrderRecordWriteDAO.update(recordPO);


		}catch (Exception e){
			logger.info("[进行以旧换新操作]异常:",e);
			return false;
		}

		return true;
	}

	@Override
	public Boolean insertExchangeOrderRecordAndCouponWithTx(String mail,Long userId, Long exchangeCouponBatchId, Long exchangeCouponUnitId, Long exchangeId) {
		try {
			//查询需要的信息
			//1.1.查询旧unit
			CouponUnitPO unitPO = new CouponUnitPO();
			unitPO.setId(exchangeCouponUnitId);
			CouponUnitPO oldUnit = couponUnitReadDAO.findById(unitPO);


			//查询旧unit

			ExchangeOrderRecordPO exchangeOrderRecordPO2 = new ExchangeOrderRecordPO();
			exchangeOrderRecordPO2.setOldUnitCode(oldUnit.getCouponUnitCode());
			exchangeOrderRecordPO2.setConversionStatus(1);
			List<ExchangeOrderRecordPO> all3 = exchangeOrderRecordReadDAO.findAll(exchangeOrderRecordPO2,null);
			if(EmptyUtil.isNotEmpty(all3)){
				throw new BusinessException("旧unit已兑换");
			}


			//1.2.查询旧优惠券
			CouponPO coupon = new CouponPO();
			coupon.setId(oldUnit.getCouponId());
			CouponPO oldCoupon = couponReadDAO.findById(coupon);
			//1.3.查询新的优惠券批次
			CouponBatchPO batchPO = new CouponBatchPO();
			batchPO.setId(exchangeCouponBatchId);
			CouponBatchPO newBatch = couponBatchReadDAO.findById(batchPO);


			//1.4.新优惠券
			coupon.setId(newBatch.getCouponRelId());
			CouponPO newCoupon = couponReadDAO.findById(coupon);
			//1.5旧批次
			CouponBatchPO batch = new CouponBatchPO();
			batch.setId(oldUnit.getCouponBatchId());
			CouponBatchPO oldBatch = couponBatchReadDAO.findById(batch);
			//校验有效性(校验旧批次是否状态还可以兑换)
			ExchangeCouponUnitStatusPO statusPO = new ExchangeCouponUnitStatusPO();
			statusPO.setExchangeId(exchangeId);
			statusPO.setAllowExchangeUnitStatus(oldUnit.getCouponUnitStatus());
			List<ExchangeCouponUnitStatusPO> all = exchangeCouponUnitStatusReadDAO.findAll(statusPO,null);
			if(EmptyUtil.isEmpty(all)){
				throw new BusinessException("旧unit状态已失效不可兑换");
			}
			//1.6活动
			ExchangeActivityPO activityPO = new ExchangeActivityPO();
			activityPO.setId(exchangeId);
			ExchangeActivityPO exchangeActivityPO = exchangeActivityReadDAO.findById(activityPO);



			CouponUnitPO couponUnitPO = new CouponUnitPO();
			couponUnitPO.setCouponBatchId(exchangeCouponBatchId);
			Long couponUnitAllCount = couponUnitReadDAO.findCouponUnitAllCount(couponUnitPO);
			if(newBatch.getGrantCount()!=-1&&newBatch.getGrantCount()<couponUnitAllCount){
				throw new BusinessException("当前优惠券已兑完");
			}

			if(newBatch.getIsRepeat()==0){
				//如果是不可重复领取
				CouponUnitPO unit = new CouponUnitPO();
				unit.setUserId(userId);
				unit.setCouponBatchId(newBatch.getId());
				Long unitAllCount = couponUnitReadDAO.findCouponUnitAllCount(unit);
				if(unitAllCount>=2){
					throw new BusinessException("已领取该优惠券");
				}
			}
			ExchangeOrderRecordPO exchangeOrderRecordPO = new ExchangeOrderRecordPO();
			exchangeOrderRecordPO.setOldUnitCode(oldUnit.getCouponUnitCode());
			exchangeOrderRecordPO.setNewBatchCode(newBatch.getCouponBatchCode());
			exchangeOrderRecordPO.setConversionStatus(Integer.valueOf(1));
			List<ExchangeOrderRecordPO> allRecord = exchangeOrderRecordReadDAO.findAll(exchangeOrderRecordPO,null);
			if(EmptyUtil.isNotEmpty(allRecord)&&allRecord.size()>=2){
				throw new BusinessException("该券已兑换");
			}








			//2.设置旧优惠券unit为已兑换状态
			CouponUnitPO oldUnitPO = new CouponUnitPO();
			unitPO.setId(oldUnit.getId());
			unitPO.setCouponUnitStatus(Integer.valueOf(5));//设置已兑换
			couponUnitWriteDAO.update(unitPO);

			//2.生成新的优惠券批次对应的couponUnit
			CouponUnitPO po = new CouponUnitPO();
			// 验证通过,添加优惠卷unit
			po.setCouponUnitCode(SequenceUtil.genCouponUnitNo(newCoupon.getCouponType(),Integer.valueOf(0)));//一次仅一张
			po.setBatchIndex(Long.valueOf(0));
			po.setPlatformId(newCoupon.getPlatformId());
			po.setCouponId(newBatch.getCouponRelId());
			po.setCouponBatchId(newBatch.getId());
			po.setUserId(userId);
			// 设置优惠卷unit的有效时间
			if (newBatch.getEffectDays() != null
					&& !newBatch.getEffectDays().equals(Integer.valueOf(-1))) {

				po.setEffectStartTime(new Date());
				Date endDate = DateUtils.addDays(new Date(), newBatch.getEffectDays().intValue());
				po.setEffectEndTime(processEndTime(endDate));
			} else {
				po.setEffectStartTime(newBatch.getEffectStartTime());
				po.setEffectEndTime(processEndTime(newBatch.getEffectEndTime()));
			}
			//领取时间
			po.setReceivedTime(new Date());

			couponUnitWriteDAO.insert(po);
			//3.更新兑换记录
			ExchangeOrderRecordPO recordPO = new ExchangeOrderRecordPO();
			recordPO.setExchangeId(exchangeActivityPO.getId());
			recordPO.setExchangeName(exchangeActivityPO.getExchangeName());
			recordPO.setOldBatchCode(oldBatch.getCouponBatchCode());
			recordPO.setOldCouponName(oldCoupon.getTitle());
			recordPO.setOldCouponType(oldCoupon.getCouponType());
			recordPO.setOldUnitCode(oldUnit.getCouponUnitCode());
			recordPO.setOldUnitStatus(oldUnit.getCouponUnitStatus());
			recordPO.setOldCouponBatchName(oldBatch.getCouponBatchName());

			recordPO.setNewUnitCode(po.getCouponUnitCode());
			recordPO.setNewBatchCode(newBatch.getCouponBatchCode());
			recordPO.setNewCouponName(newCoupon.getTitle());
			recordPO.setNewCouponType(newCoupon.getCouponType());
			recordPO.setConversionStatus(Integer.valueOf(1));
			recordPO.setPlatformId(oldBatch.getPlatformId());
			recordPO.setExchangeTime(new Date());
			recordPO.setUserMail(mail);
			recordPO.setNewCouponBatchName(newBatch.getCouponBatchName());

			exchangeOrderRecordWriteDAO.insert(recordPO);


		}catch (Exception e){
			logger.info("[进行以旧换新操作]异常:",e);

			throw e;
			//return false;
		}
		return true;
	}

	@Override
	public int deleteExchangeOrderRecordByParamWithTx(ExchangeOrderRecordPO exchangeOrderRecordPO) {
		return exchangeOrderRecordWriteDAO.deleteByPara(exchangeOrderRecordPO);
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


}
	