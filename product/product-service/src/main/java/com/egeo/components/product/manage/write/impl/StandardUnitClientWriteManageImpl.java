package com.egeo.components.product.manage.write.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.StandardUnitClientWriteManage;
import com.egeo.components.product.dao.write.StandardUnitClientWriteDAO;
import com.egeo.components.product.po.StandardUnitClientPO;
import com.egeo.exception.BusinessException;

@Service
public class StandardUnitClientWriteManageImpl implements StandardUnitClientWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StandardUnitClientWriteDAO standardUnitClientWriteDAO;

	@Override
	public Long insertStandardUnitClientWithTx(StandardUnitClientPO po) {
		
		int i ;
		try {
				i = standardUnitClientWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateStandardUnitClientWithTx(StandardUnitClientPO po) {
		int i;
		i = standardUnitClientWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteStandardUnitClientWithTx(StandardUnitClientPO po) {
		int i;
		i = standardUnitClientWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}
	/**
	 * 根据suid删除su客户端关系表
	 * @param merchantProdId
	 * @return
	 */
	@Override
	public int deleteByStandardUnitIdWithTx(Long standardUnitId) {
		StandardUnitClientPO po = new StandardUnitClientPO();
		po.setStandardUnitId(standardUnitId);
		return standardUnitClientWriteDAO.deleteByPara(po);
	}
	/**
	 * 根据需要排除的su客户端关系id及suid删除不存在的su客户端关系
	 * @param merchantProductId
	 * @param clientId
	 * @return
	 */
	@Override
	public int delByStandardUnitIdClientId(Long standardUnitId, List<Long> clientId) {
		// TODO Auto-generated method stub
		return standardUnitClientWriteDAO.delByStandardUnitIdClientId(standardUnitId, clientId);
	}

	@Override
	public int delByStandardUnitId(Long standardUnitId) {
		StandardUnitClientPO standardUnitClientPO = new StandardUnitClientPO();
		standardUnitClientPO.setStandardUnitId(standardUnitId);
		return standardUnitClientWriteDAO.deleteByPara(standardUnitClientPO);
	}

	@Override
	public void saveStandardUnitClient(List<StandardUnitClientPO> standardUnitClientPOList) {
		try{
		standardUnitClientWriteDAO.saveStandardUnitClient(standardUnitClientPOList);
		}catch (Exception e){
			logger.error("saveStandardUnitClient失败,e:"+e.getMessage());
		}
	}
}
	