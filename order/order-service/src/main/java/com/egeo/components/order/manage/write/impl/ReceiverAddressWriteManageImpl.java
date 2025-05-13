package com.egeo.components.order.manage.write.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.order.manage.write.ReceiverAddressWriteManage;
import com.egeo.components.order.dao.read.ReceiverAddressReadDAO;
import com.egeo.components.order.dao.write.ReceiverAddressWriteDAO;
import com.egeo.components.order.dao.write.SoChildWriteDAO;
import com.egeo.components.order.po.ReceiverAddressPO;
import com.egeo.components.order.po.SoChildPO;
import com.egeo.exception.BusinessException;
import com.egeo.utils.EmptyUtil;

@Service
public class ReceiverAddressWriteManageImpl implements ReceiverAddressWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ReceiverAddressWriteDAO receiverAddressWriteDAO;
	@Autowired
	private ReceiverAddressReadDAO receiverAddressReadDAO;
	@Autowired
	private SoChildWriteDAO soChildWriteDAO;

	@Override
	public Long insertReceiverAddressWithTx(ReceiverAddressPO po) {
		// 验证数据是否有有效
		verifyReceiverAddressValid(po);
		
		int i ;
		try {
				i = receiverAddressWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}
	
	/**
	 * 验证数据是否有有效
	 * @param po
	 */
	private void verifyReceiverAddressValid(ReceiverAddressPO po) {
		// 设默认地址为用户创建
		if(EmptyUtil.isEmpty(po.getType()))
			po.setType(1);
		
		if(po.getType() == 1){
			// 判断是否根据用户id取消默认地址
			if(po.getIsDefault() == 1){
				receiverAddressWriteDAO.cancelReceiverAddressByUserId(po.getUserId());
			}
		}else{
			// 地址不为用户创建强制地址为不是默认地址
			po.setIsDefault(0);
		}
		
		// 如果没有默认地址、强制此地址为默认地址
		/*if(po.getIsDefault() == 0){
			ReceiverAddressPO receiverAddressPO = new ReceiverAddressPO();
			receiverAddressPO.setIsDefault(1);
			receiverAddressPO.setType(1);
			receiverAddressPO.setUserId(po.getUserId());
			receiverAddressPO.setPlatformId(po.getPlatformId());
			List<ReceiverAddressPO> list = receiverAddressReadDAO.findAll(receiverAddressPO);
			if(list.size() == 0)
				po.setIsDefault(1);
		}*/
		
		// 创建类型：1、用户创建 2、运营创建 用户创建不可超过10条
		if(po.getType() == 1){
			// 根据用户id查询用户创建的地址最大数
			int count = receiverAddressReadDAO.receiverAddressSumByUserId(po.getUserId(),po.getPlatformId());
			if(count >= 10){
				throw new BusinessException("最多可以添加十条地址信息");
			}
		}
		
	}


	@Override
	public int updateReceiverAddressWithTx(ReceiverAddressPO po) {
		
		// 验证数据是否有有效
		verifyReceiverAddressValid(po);
		int i;
		i = receiverAddressWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}
	@Override
	public int deleteReceiverAddressWithTx(ReceiverAddressPO po) {
		int i;
		i = receiverAddressWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}

	@Override
	public void modifyReceiverAddressWithTx(Long soChildId, ReceiverAddressPO receiverAddressPO) {
		
		List<ReceiverAddressPO> receiverAddressPOList = receiverAddressReadDAO.findAll(receiverAddressPO,null);
		SoChildPO soChildPO = new SoChildPO();
		soChildPO.setId(soChildId);
		if(EmptyUtil.isEmpty(receiverAddressPOList)){
			// 验证数据是否有有效
			verifyReceiverAddressValid(receiverAddressPO);
			//说明地址是新增的，新建一个地址
			Long receiverAddressId = (long) receiverAddressWriteDAO.insert(receiverAddressPO); 
			soChildPO.setDeliveryId(receiverAddressId);
		}else{
			//给子订单赋值次地址
			Long receiverAddressId2 = receiverAddressPOList.get(0).getId();
			soChildPO.setDeliveryId(receiverAddressId2);
			soChildWriteDAO.update(soChildPO);
		}
	}	
}
	