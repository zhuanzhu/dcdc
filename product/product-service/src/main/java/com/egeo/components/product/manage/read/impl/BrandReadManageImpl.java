package com.egeo.components.product.manage.read.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.BrandReadManage;
import com.egeo.components.product.dao.read.BrandReadDAO;
import com.egeo.components.product.po.BrandPO;

@Service
public class BrandReadManageImpl implements BrandReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private BrandReadDAO brandReadDAO;
	
        @Override
        public List<BrandPO> findAll(BrandPO po) {
            return brandReadDAO.findAll(po,null);
        }

		@Override
		public BrandPO findById(BrandPO po) {
			return brandReadDAO.findById(po);
		}
	
}
	