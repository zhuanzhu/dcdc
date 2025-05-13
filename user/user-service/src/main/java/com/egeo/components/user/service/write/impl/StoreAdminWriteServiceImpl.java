package com.egeo.components.user.service.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.converter.StoreAdminConverter;
import com.egeo.components.user.dto.StoreAdminDTO;
import com.egeo.components.user.manage.write.StoreAdminWriteManage;
import com.egeo.components.user.po.StoreAdminPO;
import com.egeo.components.user.service.write.StoreAdminWriteService;

@Service("storeAdminWriteService")
public class StoreAdminWriteServiceImpl implements StoreAdminWriteService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StoreAdminWriteManage storeAdminWriteManage;

	@Override
	public Long insertStoreAdminWithTx(StoreAdminDTO dto) {
		StoreAdminPO po = StoreAdminConverter.toPO(dto);
		Long rt = storeAdminWriteManage.insertStoreAdminWithTx(po);		
		return rt;
	}

	@Override
	public int updateStoreAdminWithTx(StoreAdminDTO dto) {
		StoreAdminPO po = StoreAdminConverter.toPO(dto);
		int rt = storeAdminWriteManage.updateStoreAdminWithTx(po);		
		return rt;
	}

	@Override
	public int deleteStoreAdminWithTx(StoreAdminDTO dto) {
		StoreAdminPO po = StoreAdminConverter.toPO(dto);
		logger.info("被取消管理员的员工ID为:"+dto.getUserId());
		int rt = storeAdminWriteManage.deleteStoreAdminWithTx(po);		
		return rt;
	}
}
	