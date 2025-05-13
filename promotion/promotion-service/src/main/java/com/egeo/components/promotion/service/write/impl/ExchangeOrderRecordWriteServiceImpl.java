package com.egeo.components.promotion.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.converter.CouponBatchConverter;
import com.egeo.components.promotion.converter.ExchangeOrderRecordConverter;
import com.egeo.components.promotion.dto.CouponBatchDTO;
import com.egeo.components.promotion.dto.ExchangeOrderRecordDTO;
import com.egeo.components.promotion.manage.write.ExchangeOrderRecordWriteManage;
import com.egeo.components.promotion.po.ExchangeOrderRecordPO;
import com.egeo.components.promotion.service.write.ExchangeOrderRecordWriteService;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.log.XLogger;

@Service("exchangeOrderRecordWriteService")
public class ExchangeOrderRecordWriteServiceImpl implements ExchangeOrderRecordWriteService {

	private static final XLogger logger = XLogger.getLogger(ExchangeOrderRecordWriteServiceImpl.class);
	@Autowired
	private ExchangeOrderRecordWriteManage exchangeOrderRecordWriteManage;
	@Autowired
	private JedisUtil jedisUtil;

	private static int LOCK_EXPIRE_TIME=30*60;


	@Override
	public Long insertExchangeOrderRecordWithTx(ExchangeOrderRecordDTO dto) {
		ExchangeOrderRecordPO po = ExchangeOrderRecordConverter.toPO(dto);
		Long rt = exchangeOrderRecordWriteManage.insertExchangeOrderRecordWithTx(po);		
		return rt;
	}

	@Override
	public int updateExchangeOrderRecordWithTx(ExchangeOrderRecordDTO dto) {
		ExchangeOrderRecordPO po = ExchangeOrderRecordConverter.toPO(dto);
		int rt = exchangeOrderRecordWriteManage.updateExchangeOrderRecordWithTx(po);		
		return rt;
	}
	@Override
	public int updateExchangeOrderRecordByOrderCodeWithTx(ExchangeOrderRecordDTO dto) {
		ExchangeOrderRecordPO po = ExchangeOrderRecordConverter.toPO(dto);
		int rt = exchangeOrderRecordWriteManage.updateExchangeOrderRecordByOrderCodeWithTx(po);
		return rt;
	}

	@Override
	public int deleteExchangeOrderRecordWithTx(ExchangeOrderRecordDTO dto) {
		ExchangeOrderRecordPO po = ExchangeOrderRecordConverter.toPO(dto);
		int rt = exchangeOrderRecordWriteManage.deleteExchangeOrderRecordWithTx(po);		
		return rt;
	}

	@Override
	public boolean updateExchangeAndCouponWithTx(String couponUnitCode, Integer couponUnitStatus, Long couponUnitId, CouponBatchDTO couponBatchDTO, Long recordId, Long userId) {

		boolean lock = true;
		try {
			lock=jedisUtil.lock();
			logger.info("获得锁,lock="+lock);
		} catch (InterruptedException e) {
			logger.info("获取锁异常",e);
			jedisUtil.delLock(null);
		}
		//取得锁
		boolean b = false;
		if(lock){
			try {
				b = exchangeOrderRecordWriteManage.updateExchangeAndCouponWithTx(couponUnitCode,couponUnitStatus,couponUnitId, CouponBatchConverter.toPO(couponBatchDTO), recordId, userId);
				logger.info("unit发放完成,b="+b);
			}catch (Exception e){
				if(e.getMessage().equals("并发数据异常")){
					//当前订单已经完成了
				}
				return false;
			}finally {
				jedisUtil.delLock(null);//释放锁
			}

		}

		return b;

















		/*
		ZooKeeperUserImportSession zooKeeperUserImportSession = new ZooKeeperUserImportSession();
		// 获取分布式锁
		boolean existNode = zooKeeperUserImportSession.acquireDistributedLock();
		if (!existNode) {
			throw new BusinessException("其他管理员正在导入，请稍后再试");
		}

		boolean b = false;

			try {
				b = exchangeOrderRecordWriteManage.updateExchangeAndCouponWithTx(couponUnitCode,couponUnitStatus,couponUnitId, CouponBatchConverter.toPO(couponBatchDTO), recordId, userId);
			}catch (Exception e){
				logger.info("返回结果:false");
				return false;
			}finally{
				logger.info("返回结果:"+b);
				// 不管是否发生异常都释放zk锁
				try {
					zooKeeperUserImportSession.releaseDistributedLock();
				} catch (Exception e) {
					logger.info("释放掉一个分布式锁失败，请联系管理员");
					return false;
				}
				return b;
			}
*/

	}

	@Override
	public Boolean insertExchangeOrderRecordAndCouponWithTx(String mail,Long userId, Long exchangeCouponBatchId, Long exchangeCouponUnitId, Long exchangeId) {

		boolean lock = true;
		try {
			lock=jedisUtil.lock();
		} catch (InterruptedException e) {
			logger.info("获取锁异常",e);
			jedisUtil.delLock(null);

		}
		//取得锁
		boolean b = false;
		if(lock){
			try {
				b = exchangeOrderRecordWriteManage.insertExchangeOrderRecordAndCouponWithTx(mail, userId, exchangeCouponBatchId, exchangeCouponUnitId, exchangeId);
			}catch (Exception e){
				return false;
			}finally {
				jedisUtil.delLock(null);
			}
		}

		return b;

	/*	ZooKeeperUserImportSession zooKeeperUserImportSession = new ZooKeeperUserImportSession();
		// 获取分布式锁
		boolean existNode = zooKeeperUserImportSession.acquireDistributedLock();
		if (!existNode) {
			throw new BusinessException("其他管理员正在导入，请稍后再试");
		}

		boolean b = false;

		try {
			b = exchangeOrderRecordWriteManage.insertExchangeOrderRecordAndCouponWithTx(mail, userId, exchangeCouponBatchId, exchangeCouponUnitId, exchangeId);
		}catch (Exception e){
			logger.info("返回结果:false");
			return false;
		}finally{
			logger.info("返回结果:"+b);
			// 不管是否发生异常都释放zk锁
			try {
				zooKeeperUserImportSession.releaseDistributedLock();
			} catch (Exception e) {
				logger.info("释放掉一个分布式锁失败，请联系管理员");
				return false;
			}
			return b;
		}
*/

	}

	@Override
	public int deleteExchangeOrderRecordByParamWithTx(ExchangeOrderRecordDTO recordDTO) {
		return exchangeOrderRecordWriteManage.deleteExchangeOrderRecordByParamWithTx(ExchangeOrderRecordConverter.toPO(recordDTO));
	}


}
	