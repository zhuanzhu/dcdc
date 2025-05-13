package com.egeo.components.promotion.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.dao.write.ActivityWriteDAO;
import com.egeo.components.promotion.manage.write.ActivityWriteManage;
import com.egeo.components.promotion.po.ActivityPO;
import com.egeo.exception.BusinessException;

@Service
public class ActivityWriteManageImpl implements ActivityWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ActivityWriteDAO activityWriteDAO;
	
	@Override
	public Long saveActivity(ActivityPO po) {
		activityWriteDAO.insert(po);
		if(po.getId() != null){
			return po.getId();
		}else{
			throw new BusinessException("新增活动信息失败");
		}
		
	}

	@Override
	public Integer deleteWithTx(ActivityPO po) {
		return activityWriteDAO.delete(po);
	}

	@Override
	public Long updateActivity(ActivityPO po) {
		activityWriteDAO.update(po);
		return po.getId();
	}
}
	