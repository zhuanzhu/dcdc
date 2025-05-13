package com.egeo.components.product.manage.write.impl;

import com.alibaba.fastjson.JSON;
import com.egeo.components.product.po.ProductUnitPO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.JdProductWriteManage;
import com.egeo.components.product.dao.write.JdProductWriteDAO;
import com.egeo.components.product.po.JdProductPO;
import com.egeo.exception.BusinessException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

@Service
public class JdProductWriteManageImpl implements JdProductWriteManage{
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private JdProductWriteDAO jdProductWriteDAO;

	@Override
	public Long insertJdProductWithTx(JdProductPO po) {
		
		int i ;
		try {
				i = jdProductWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateJdProductWithTx(JdProductPO po) {
		int i;
		i = jdProductWriteDAO.update(po);
		return i;
	}

	@Override
	public int deleteJdProductWithTx(JdProductPO po) {
		int i;
		i = jdProductWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}

	@Override
	public void updateSyncStatus(JdProductPO po) {
		jdProductWriteDAO.updateSyncStatus(po);
	}


	private List<JdProductPO> param;

	public void setParam(List<JdProductPO> param) {
		this.param = param;
	}

	@Override
	public void saveJdProductListFirst(List<JdProductPO> jdProductPOList) {

		try {
			jdProductWriteDAO.saveJdProductListFirst(jdProductPOList);

		}catch (Exception e){
			logger.error("插入京东商品线程出错,e:"+e.getMessage());
			List<Long> res = new ArrayList<>();
			Set<Long> set = new HashSet<>();
			for(JdProductPO po:jdProductPOList){
				res.add(po.getId());
				set.add(po.getCategoryId());
			}
			logger.error("错误的京东商品idList:"+ JSON.toJSONString(res));
			logger.error("错误的京东商品分类有:"+ JSON.toJSONString(set));

		}
	}

	@Override
	public void setAllSyncStatus(int status) {
		jdProductWriteDAO.setAllSyncStatus(status);


	}

	@Override
	public void updateList(List<JdProductPO> updateList) {
		jdProductWriteDAO.updateList(updateList);
	}

	@Override
	public void updateJdProductPrice(List<JdProductPO> jdProductPOList) {
		jdProductWriteDAO.updateJdProductPrice(jdProductPOList);
	}

	@Override
	public void updateProductCreateTime(List<Long> jdProductIdList) {
		jdProductWriteDAO.updateProductCreateTime(jdProductIdList);
	}


}
	