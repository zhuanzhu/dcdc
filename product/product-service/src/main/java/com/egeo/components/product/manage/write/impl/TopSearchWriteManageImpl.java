package com.egeo.components.product.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.TopSearchWriteManage;
import com.egeo.components.product.dao.read.TopSearchReadDAO;
import com.egeo.components.product.dao.write.TopSearchWriteDAO;
import com.egeo.components.product.po.TopSearchPO;
import com.egeo.exception.BusinessException;
import com.egeo.utils.EmptyUtil;

@Service
public class TopSearchWriteManageImpl implements TopSearchWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private TopSearchWriteDAO topSearchWriteDAO;
	
	@Autowired
	private TopSearchReadDAO topSearchReadDAO;

	@Override
	public Long insertTopSearchWithTx(TopSearchPO po) {
		
		int i ;
		try {	
				// 如果排序为空查询热搜排序最大值加一赋值
				//if(EmptyUtil.isEmpty(po.getSortValue()))
					po.setSortValue(topSearchReadDAO.sortValueMax() != null ?  topSearchReadDAO.sortValueMax() + 1 : 1);
					
				i = topSearchWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateTopSearchWithTx(TopSearchPO po) {
		int i;
		i = topSearchWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteTopSearchWithTx(TopSearchPO po) {
		int i;
		i = topSearchWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}
	/**
	 * 根据id启用停用热搜
	 * @param topSearchId
	 * @return
	 */
	@Override
	public int startStopTopSearchWithTx(Long topSearchId) {
		TopSearchPO topSearchPO = new TopSearchPO();
		topSearchPO.setId(topSearchId);
		TopSearchPO searchPO = topSearchReadDAO.findById(topSearchPO);
		Integer isStart = null;
		if(searchPO.getIsStart() == 0){
			isStart = 1;
		}else if(searchPO.getIsStart() == 1){
			isStart = 0;
		}
		topSearchPO.setIsStart(isStart);
		topSearchWriteDAO.update(topSearchPO);
		return isStart;
	}	
}
	