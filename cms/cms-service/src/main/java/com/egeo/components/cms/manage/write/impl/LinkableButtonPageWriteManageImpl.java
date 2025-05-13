package com.egeo.components.cms.manage.write.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.write.LinkableButtonPageWriteManage;
import com.egeo.components.cms.dao.write.LinkableButtonPageWriteDAO;
import com.egeo.components.cms.po.LinkableButtonPagePO;
import com.egeo.exception.BusinessException;

@Service
public class LinkableButtonPageWriteManageImpl implements LinkableButtonPageWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private LinkableButtonPageWriteDAO linkableButtonPageWriteDAO;

	@Override
	public Long insertLinkableButtonPageWithTx(LinkableButtonPagePO po) {
		
		int i ;
		try {
				i = linkableButtonPageWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateLinkableButtonPageWithTx(LinkableButtonPagePO po) {
		int i;
		i = linkableButtonPageWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteLinkableButtonPageWithTx(LinkableButtonPagePO po) {
		int i;
		i = linkableButtonPageWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}

	@Override
	public int insertBatchLinkableButtonPageWithTx(List<LinkableButtonPagePO> pos) {
		int i;
		i = linkableButtonPageWriteDAO.insertBatch(pos);
		if (i == 0)
			throw new BusinessException("未能成插入数据!");
		return i;
	}

	@Override
	public int deleteLinkableButtonPageByLinkableIdWithTx(LinkableButtonPagePO po) {
		int i;
		i = linkableButtonPageWriteDAO.deleteByPara(po);
		
		return i;
	}	
}
	