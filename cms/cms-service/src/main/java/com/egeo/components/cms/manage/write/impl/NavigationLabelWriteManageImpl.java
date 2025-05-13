package com.egeo.components.cms.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.write.NavigationLabelWriteManage;
import com.egeo.components.cms.dao.write.LinkableButtonWriteDAO;
import com.egeo.components.cms.dao.write.NavigationLabelWriteDAO;
import com.egeo.components.cms.po.LinkableButtonPO;
import com.egeo.components.cms.po.NavigationLabelPO;
import com.egeo.exception.BusinessException;

@Service
public class NavigationLabelWriteManageImpl implements NavigationLabelWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private NavigationLabelWriteDAO navigationLabelWriteDAO;
	
	@Autowired
	private LinkableButtonWriteDAO linkableButtonWriteDAO;

	@Override
	public Long insertNavigationLabelWithTx(NavigationLabelPO po) {
		
		int i ;
		try {
				i = navigationLabelWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateNavigationLabelWithTx(NavigationLabelPO po) {
		int i;
		i = navigationLabelWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteNavigationLabelWithTx(NavigationLabelPO po) {
		int i;
		i = navigationLabelWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}

	@Override
	public Long insertOrUpdateNavigationLableWithTx(NavigationLabelPO po, LinkableButtonPO linkableButtonPO) {
		int i;
		if (po.getId() == null) {
			// 新增
			// 1.插入导航栏标签
			i = navigationLabelWriteDAO.insert(po);
			if (i == 0)
				throw new BusinessException("未能成功插入数据!");
			
			// 2.插入可跳转链接按钮
			i = linkableButtonWriteDAO.insert(linkableButtonPO);
			if (i == 0)
				throw new BusinessException("未能成功插入数据!");
			
			// 3.更新导航栏标签的可跳转链接按钮信息
			po.setLinkableButtonId(linkableButtonPO.getId());
			i = navigationLabelWriteDAO.update(po);
			if (i == 0)
				throw new BusinessException("未能成功更新数据!");
		} else {
			// 编辑
			// 1.更新导航栏标签
			i = navigationLabelWriteDAO.update(po);
			if (i == 0)
				throw new BusinessException("未能成功更新数据!");
			
			// 2.0 删除已有可跳转链接按钮
			
			// 2.插入可跳转链接按钮
			i = linkableButtonWriteDAO.insert(linkableButtonPO);
			if (i == 0)
				throw new BusinessException("未能成功插入数据!");
			
			// 3.更新导航栏标签的可跳转链接按钮信息
			po.setLinkableButtonId(linkableButtonPO.getId());
			i = navigationLabelWriteDAO.update(po);
			if (i == 0)
				throw new BusinessException("未能成功更新数据!");
		}
		
		return po.getId();
	}	
}
	