package com.egeo.components.product.manage.write.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.FreightTemplateWriteManage;
import com.egeo.components.product.dao.read.FreightTemplateReadDAO;
import com.egeo.components.product.dao.write.FreightRegulationWriteDAO;
import com.egeo.components.product.dao.write.FreightTemplateWriteDAO;
import com.egeo.components.product.po.FreightRegulationPO;
import com.egeo.components.product.po.FreightTemplatePO;
import com.egeo.exception.BusinessException;
import com.egeo.utils.EmptyUtil;

@Service
public class FreightTemplateWriteManageImpl implements FreightTemplateWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private FreightTemplateWriteDAO freightTemplateWriteDAO;
	
	@Autowired
	private FreightRegulationWriteDAO freightRegulationWriteDAO;
	
	@Autowired
	private FreightTemplateReadDAO freightTemplateReadDAO;

	@Override
	public Long insertFreightTemplateWithTx(FreightTemplatePO po,List<FreightRegulationPO> freightRegulationList) {
		
		int i ;
		try {
				i = freightTemplateWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		if(EmptyUtil.isNotEmpty(po.getId())){
			for (FreightRegulationPO freightRegulationPO2 : freightRegulationList) {
				freightRegulationPO2.setFreightTemplateId(po.getId());
				freightRegulationWriteDAO.insert(freightRegulationPO2);
			}
			
		}
		
		//根据商家id停用运费模版
//		freightTemplateWriteDAO.stopDreightTemplateByMerchantId(po.getMerchantId(),po.getStoreId());
		return po.getId();
	}
	/**
	 * 根据运费模版id修改运费模版
	 * @param vo
	 * @param freightRegulations
	 * @param req
	 * @return
	 */
	@Override
	public int updateFreightTemplateWithTx(FreightTemplatePO po,List<FreightRegulationPO> freightRegulationList) {
		int i;
		i = freightTemplateWriteDAO.update(po);
		if (i == 0){
			throw new BusinessException("未能成功更新数据!");
		}
		FreightRegulationPO freightRegulationPO = new FreightRegulationPO();
		freightRegulationPO.setFreightTemplateId(po.getId());
		freightRegulationWriteDAO.deleteByPara(freightRegulationPO);
		
		for (FreightRegulationPO freightRegulationPO2 : freightRegulationList) {
			freightRegulationPO2.setFreightTemplateId(po.getId());
			freightRegulationWriteDAO.insert(freightRegulationPO2);
		}
		
		//根据商家id停用运费模版
//		freightTemplateWriteDAO.stopDreightTemplateByMerchantId(po.getMerchantId(),po.getStoreId());
		return i;
	}

	@Override
	public int deleteFreightTemplateWithTx(FreightTemplatePO po) {
		int i;
		i = freightTemplateWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}
	/**
	 * 根据运费模版id启用运费模版
	 * @param freightTemplateId
	 * @return
	 */
	@Override
	public int startFreightTemplateWithTx(Long freightTemplateId) {
		FreightTemplatePO freightTemplatePO = new FreightTemplatePO();
		freightTemplatePO.setId(freightTemplateId);
		FreightTemplatePO freightTemplatePO2 = freightTemplateReadDAO.findById(freightTemplatePO);
		//根据商家id停用运费模版
		freightTemplateWriteDAO.stopDreightTemplateByMerchantId(freightTemplatePO2.getMerchantId(),freightTemplatePO2.getStoreId());
		
		freightTemplatePO.setIsValid(1);
		return freightTemplateWriteDAO.update(freightTemplatePO);
	}	
}
	