package com.egeo.components.user.manage.write.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.user.dao.read.CompanyPageReadDAO;
import com.egeo.components.user.dao.write.CompanyPageWriteDAO;
import com.egeo.components.user.dao.write.CompanyWriteDAO;
import com.egeo.components.user.manage.write.CompanyWriteManage;
import com.egeo.components.user.po.CompanyPO;
import com.egeo.components.user.po.CompanyPagePO;
import com.egeo.exception.BusinessException;
import com.egeo.utils.StringUtils;

@Service
public class CompanyWriteManageImpl implements CompanyWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CompanyWriteDAO companyWriteDAO;
	
	@Autowired
	private CompanyPageWriteDAO companyPageWriteDAO;
	
	@Autowired
	private CompanyPageReadDAO companyPageReadDAO;

	@Override
	public Long insertCompanyWithTx(CompanyPO po,List<CompanyPagePO> companyPageList) {
		
		int i ;
		try {
				i = companyWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		//保存公司页面配置信息
		for (CompanyPagePO companyPagePO : companyPageList) {
			companyPagePO.setCompanyId(po.getId());
			companyPageWriteDAO.insert(companyPagePO);
		}
		return po.getId();
	}

	@Override
	public int updateCompanyWithTx(CompanyPO po,List<CompanyPagePO> companyPageList) {
		int i;
		i = companyWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		
		//根据公司id和页面配置类型修改配置项是否显示
		for (CompanyPagePO companyPagePO : companyPageList) {
			companyPagePO.setCompanyId(po.getId());
			//根据公司id和配置类型查询是否存在
			CompanyPagePO companyPagePO2 = new CompanyPagePO();
			companyPagePO2.setCompanyId(po.getId());
			companyPagePO2.setType(companyPagePO.getType());
			List<CompanyPagePO> list = companyPageReadDAO.findAll(companyPagePO2,null);
			if(StringUtils.isEmpty(list)){
				//如果不存在新增
				companyPageWriteDAO.insert(companyPagePO);
			}else{
				//存在则根据公司id和配置类型修改配置项是否显示
				companyPageWriteDAO.update(companyPagePO);
			}
			
		}
		return i;
	}

	@Override
	public int deleteCompanyWithTx(CompanyPO po) {
		int i;
		i = companyWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}

	@Override
	public void updateCompanyParamWithTx(CompanyPO companyPO) {
		int i;
		i = companyWriteDAO.update(companyPO);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
	}
}
	