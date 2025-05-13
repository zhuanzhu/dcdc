package com.egeo.components.promotion.manage.write.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.condition.ExchangeActivityCondition;
import com.egeo.components.promotion.dao.write.ExchangeActivityWriteDAO;
import com.egeo.components.promotion.dao.write.ExchangeBatchWriteDAO;
import com.egeo.components.promotion.dao.write.ExchangeCouponUnitStatusWriteDAO;
import com.egeo.components.promotion.manage.write.ExchangeActivityWriteManage;
import com.egeo.components.promotion.po.ExchangeActivityPO;
import com.egeo.components.promotion.po.ExchangeBatchPO;
import com.egeo.components.promotion.po.ExchangeCouponUnitStatusPO;
import com.egeo.exception.BusinessException;
import com.egeo.utils.EmptyUtil;

@Service
public class ExchangeActivityWriteManageImpl implements ExchangeActivityWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ExchangeActivityWriteDAO exchangeActivityWriteDAO;

	@Autowired
	private ExchangeBatchWriteDAO exchangeBatchWriteDAO;

	@Autowired
	private ExchangeCouponUnitStatusWriteDAO exchangeCouponUnitStatusWriteDAO;

	@Override
	public Long insertExchangeActivityWithTx(ExchangeActivityPO po) {
		
		int i ;
		try {
				i = exchangeActivityWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateExchangeActivityWithTx(ExchangeActivityPO po) {
		int i;
		i = exchangeActivityWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteExchangeActivityWithTx(ExchangeActivityPO po) {
		int i;
		i = exchangeActivityWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}

	@Override
	public int insertOrUpdateExchangeActivityWithTx(ExchangeActivityCondition exchangeActivityCondition) {
		//true:更新,false:新增
		boolean flag=true;
		if(EmptyUtil.isEmpty(exchangeActivityCondition.getId())){
			flag = false;
		}
		ExchangeActivityPO activityPO= new ExchangeActivityPO();
		//1.插入exchangeActivity
		if(flag){
			activityPO.setId(exchangeActivityCondition.getId());
		}
		activityPO.setEndTime(exchangeActivityCondition.getEndTime());
		activityPO.setExchangeName(exchangeActivityCondition.getExchangeName());
		activityPO.setPlatformId(exchangeActivityCondition.getPlatformId());
		activityPO.setStatus(Integer.valueOf(1));//默认开启状态
		if(flag){
			//更新
			exchangeActivityWriteDAO.update(activityPO);
		}else{
			//新增
			try {
				exchangeActivityWriteDAO.insert(activityPO);
			}catch (DuplicateKeyException e){
				logger.error("[插入exchangeActivity错误]", e);
				throw new BusinessException("路径必须唯一!");
			}
		}

		//2.插入exchangeBatch
		if(flag){
			//2.0更新时将原有的关联直接删除,插入新的关联
			ExchangeBatchPO batchPO = new ExchangeBatchPO();
			batchPO.setExchangeId(exchangeActivityCondition.getId());
			batchPO.setPlatformId(exchangeActivityCondition.getPlatformId());
			exchangeBatchWriteDAO.deleteByPara(batchPO);
		}
		// 2.1旧批次(type为0)
		List<ExchangeBatchPO> batchPOList = new ArrayList<>();
		for(Long batchId:exchangeActivityCondition.getOldBatchIdList()){
			ExchangeBatchPO oldBatchPO = new ExchangeBatchPO();
			oldBatchPO.setType(Integer.valueOf(0));
			oldBatchPO.setExchangeId(activityPO.getId());
			oldBatchPO.setBatchId(batchId);
			oldBatchPO.setPlatformId(activityPO.getPlatformId());
			batchPOList.add(oldBatchPO);
		}

		try {
			exchangeBatchWriteDAO.insertBatchList(batchPOList);
		}catch (DuplicateKeyException e){
			logger.error("[插入exchangeBatch旧批次错误]", e);
			throw new BusinessException("路径必须唯一!");
		}


		//2.2插入exchangeBatch旧批次
		List<ExchangeBatchPO> newBatchPOList = new ArrayList<>();
		for(ExchangeBatchPO po:exchangeActivityCondition.getNewBatchList()){
			ExchangeBatchPO newBatchPO = new ExchangeBatchPO();
			newBatchPO.setType(Integer.valueOf(1));
			newBatchPO.setExchangeId(activityPO.getId());
			newBatchPO.setPlatformId(activityPO.getPlatformId());
			newBatchPO.setBatchId(po.getBatchId());
			if(EmptyUtil.isEmpty(po.getAddPrice())){
				newBatchPO.setAddPrice(BigDecimal.valueOf(0));
			}else{
				newBatchPO.setAddPrice(po.getAddPrice());

			}
			newBatchPO.setSort(po.getSort());
			newBatchPOList.add(newBatchPO);
		}
		try {
			exchangeBatchWriteDAO.insertBatchList(newBatchPOList);
		}catch (DuplicateKeyException e){
			logger.error("[插入exchangeBatch旧批次错误]", e);
			throw new BusinessException("路径必须唯一!");
		}


		//3.插入exchangeCouponUnitSatus
		//如果编辑删除原有关联
		if(flag){
			ExchangeCouponUnitStatusPO statusPO = new ExchangeCouponUnitStatusPO();
			statusPO.setPlatformId(exchangeActivityCondition.getPlatformId());
			statusPO.setExchangeId(exchangeActivityCondition.getId());
			exchangeCouponUnitStatusWriteDAO.deleteByPara(statusPO);
		}
		List<ExchangeCouponUnitStatusPO> poList = new ArrayList<>();
		for(Integer status:exchangeActivityCondition.getUnitStatus()){
			ExchangeCouponUnitStatusPO po = new ExchangeCouponUnitStatusPO();
			po.setAllowExchangeUnitStatus(status);
			po.setExchangeId(activityPO.getId());
			po.setPlatformId(activityPO.getPlatformId());
			poList.add(po);
		}
		try {
			exchangeCouponUnitStatusWriteDAO.insertStatusList(poList);
		}catch (DuplicateKeyException e){
			logger.error("[插入exchangeBatch旧批次错误]", e);
			throw new BusinessException("路径必须唯一!");
		}
		return 0;
	}
}
	