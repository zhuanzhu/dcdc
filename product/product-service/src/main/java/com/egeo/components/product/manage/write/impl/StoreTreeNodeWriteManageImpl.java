package com.egeo.components.product.manage.write.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.StoreTreeNodeWriteManage;
import com.egeo.components.product.dao.read.StoreReadDAO;
import com.egeo.components.product.dao.read.StoreTreeNodeReadDAO;
import com.egeo.components.product.dao.write.StoreMenuNodeWriteDAO;
import com.egeo.components.product.dao.write.StoreMenuTreeWriteDAO;
import com.egeo.components.product.dao.write.StoreTreeNodeWriteDAO;
import com.egeo.components.product.dao.write.StoreWriteDAO;
import com.egeo.components.product.po.StoreMenuNodePO;
import com.egeo.components.product.po.StoreMenuTreePO;
import com.egeo.components.product.po.StorePO;
import com.egeo.components.product.po.StoreTreeNodePO;
import com.egeo.exception.BusinessException;

@Service
public class StoreTreeNodeWriteManageImpl implements StoreTreeNodeWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StoreTreeNodeWriteDAO storeTreeNodeWriteDAO;
	
	@Autowired
	private StoreWriteDAO storeWriteDAO;
	
	@Autowired
	private StoreReadDAO storeReadDAO;
	
	@Autowired
	private StoreTreeNodeReadDAO storeTreeNodeReadDAO;	
	
	@Autowired
	private StoreMenuTreeWriteDAO storeMenuTreeWriteDAO;
	
	@Autowired
	private StoreMenuNodeWriteDAO storeMenuNodeWriteDAO;

	@Override
	public Long insertStoreTreeNodeWithTx(StoreTreeNodePO storeTreeNodePO,StorePO storePO) {
		
		StorePO sPO = new  StorePO();
		sPO.setName(storePO.getName());
		List<StorePO> storeList = storeReadDAO.findAll(sPO,null);
		if(!storeList.isEmpty()) {
			throw new BusinessException("门店名字不能相同!");
		}
		int i ,j;
		

//		如果是根节点，则排序为树的id，在back已经赋值

		if(storeTreeNodePO.getParentId()!=0) {
			Integer sortList ;
			
			sortList =	getMaxListSort(storeTreeNodePO);
			if(sortList == null) {
				sortList = 0;
			}
			storeTreeNodePO.setListSort(sortList + 1);
			String activityCode =storePO.getActivityCode()+"-" + storeTreeNodePO.getListSort();
			storePO.setActivityCode(activityCode);
		}

		try {
				i = storeWriteDAO.insert(storePO);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		storeTreeNodePO.setStoreId(storePO.getId()); 


		
		try {
				i = storeTreeNodeWriteDAO.insert(storeTreeNodePO);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		// 创建门店菜单类目树和节点
		createStoreMenu(storePO.getId(),storePO.getPlatformId());
		
		return storePO.getId();
	}
	/**
	 * 创建门店菜单类目树和节点
	 * @param storeId
	 */
	private void createStoreMenu(Long storeId,Long platformId) {
		StoreMenuTreePO storeMenuTreePO = new StoreMenuTreePO();
		storeMenuTreePO.setName("门店菜单类目树");
		storeMenuTreePO.setPlatformId(platformId);
		storeMenuTreePO.setStoreId(storeId);
		storeMenuTreePO.setType(1);
		storeMenuTreeWriteDAO.insert(storeMenuTreePO);
		
		StoreMenuNodePO storeMenuNodePO = new StoreMenuNodePO();
		storeMenuNodePO.setParentId(0L);
		storeMenuNodePO.setName("全部");
		storeMenuNodePO.setSortValue(1);
		storeMenuNodePO.setIsAll(1);
		storeMenuNodePO.setPlatformId(platformId);
		storeMenuNodePO.setStoreMenuTreeId(storeMenuTreePO.getId());
		storeMenuNodeWriteDAO.insert(storeMenuNodePO);
		
	}

	@Override
	public int updateStoreTreeNodeWithTx(StoreTreeNodePO po) {
		int i;
		i = storeTreeNodeWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteStoreTreeNodeWithTx(StoreTreeNodePO po) {
		int i;
		i = storeTreeNodeWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
	
	public Integer getMaxListSort(StoreTreeNodePO po){
		StoreTreeNodePO storeTreeNodePO = new StoreTreeNodePO();
		storeTreeNodePO.setParentId(po.getParentId());
		storeTreeNodePO.setStoreTreeId(po.getStoreTreeId());
		storeTreeNodePO.setPlatformId(po.getPlatformId());
		Integer i = storeTreeNodeReadDAO.getMaxListSort(storeTreeNodePO);
		return i;
	}
	
}
	