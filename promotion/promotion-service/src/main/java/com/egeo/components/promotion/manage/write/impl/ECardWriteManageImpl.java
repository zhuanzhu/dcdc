package com.egeo.components.promotion.manage.write.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.common.DateUtils;
import com.egeo.components.promotion.dao.write.ECardWriteDAO;
import com.egeo.components.promotion.manage.write.ECardWriteManage;
import com.egeo.components.promotion.po.ECardPO;
import com.egeo.exception.BusinessException;
import com.egeo.utils.EmptyUtil;

@Service
public class ECardWriteManageImpl implements ECardWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ECardWriteDAO eCardWriteDAO;

	@Override
	public Long insertECardWithTx(ECardPO po) {
		
		int i ;
		try {
				i = eCardWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateECardWithTx(ECardPO po) {
		int i;
		i = eCardWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteECardWithTx(ECardPO po) {
		int i;
		i = eCardWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}
	/**
	 * 批量保存卡密信息
	 */
	@Override
	public int insertECardAllWithTx(List<ECardPO> eCardPOs) {
		if(EmptyUtil.isNotEmpty(eCardPOs))
			return eCardWriteDAO.insertAll(eCardPOs);
		return 0;
	}

	@Override
	public int updateCardTypeBySpuId(Long spuId, Integer cardType) {
		// TODO Auto-generated method stub
		return eCardWriteDAO.updateCardTypeBySpuId(spuId, cardType);
	}

	@Override
	public int updateECardByKeyWithTx(Map<String, Object> keys) {
		if(EmptyUtil.isEmpty(keys) || EmptyUtil.isEmpty(keys.get("ids"))) {
			return 0;
		}
		keys.put("updateTime", DateUtils.parseDate(DateUtils.getDefaultDateTimeNow()));
		return eCardWriteDAO.updateECardByKey(keys);
	}
}
	