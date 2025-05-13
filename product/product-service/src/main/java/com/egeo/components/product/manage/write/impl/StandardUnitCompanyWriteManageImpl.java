package com.egeo.components.product.manage.write.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.StandardUnitCompanyWriteManage;
import com.egeo.components.product.dao.write.StandardUnitCompanyWriteDAO;
import com.egeo.components.product.po.StandardUnitCompanyPO;
import com.egeo.exception.BusinessException;

@Service
public class StandardUnitCompanyWriteManageImpl implements StandardUnitCompanyWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StandardUnitCompanyWriteDAO standardUnitCompanyWriteDAO;

	@Override
	public Long insertStandardUnitCompanyWithTx(StandardUnitCompanyPO po) {
		
		int i ;
		try {
				i = standardUnitCompanyWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateStandardUnitCompanyWithTx(StandardUnitCompanyPO po) {
		int i;
		i = standardUnitCompanyWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteStandardUnitCompanyWithTx(StandardUnitCompanyPO po) {
		int i;
		i = standardUnitCompanyWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}
	/**
	 * 根据suid删除su福利企业关系信息
	 * @param merchantProdId
	 * @return
	 */
	@Override
	public int deleteByStandardUnitIdWithTx(Long standardUnitId) {
		StandardUnitCompanyPO po = new StandardUnitCompanyPO();
		po.setStandardUnitId(standardUnitId);
		return standardUnitCompanyWriteDAO.deleteByPara(po);
	}
	/**
	 * 根据suid和福利企业集合id删除其余关系
	 * @param merchantProductId
	 * @param companyId
	 * @return
	 */
	@Override
	public int delByStandardUnitIdCompanyId(Long standardUnitId, List<Long> companyId) {
		// TODO Auto-generated method stub
		return standardUnitCompanyWriteDAO.delByStandardUnitCompanyId(standardUnitId, companyId);
	}
	/**
	 * 根据suid删除su福利企业关系
	 * @param merchantProductId
	 * @return
	 */
	@Override
	public int delByStandardUnitId(Long standardUnitId) {
		StandardUnitCompanyPO standardUnitCompanyPO = new StandardUnitCompanyPO();
		standardUnitCompanyPO.setStandardUnitId(standardUnitId);
		return standardUnitCompanyWriteDAO.deleteByPara(standardUnitCompanyPO);
	}

	@Override
	public void saveStandardUnitCompany(List<StandardUnitCompanyPO> standardUnitCompanyPOList) {
		try{
		standardUnitCompanyWriteDAO.saveStandardUnitCompany(standardUnitCompanyPOList);
		}catch (Exception e){
			logger.error("saveStandardUnitCompany失败,e:"+e.getMessage());
		}
	}
}
	