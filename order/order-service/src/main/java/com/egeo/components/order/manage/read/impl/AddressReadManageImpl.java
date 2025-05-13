package com.egeo.components.order.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.manage.read.AddressReadManage;
import com.egeo.components.order.dao.read.AddressReadDAO;
import com.egeo.components.order.po.AddressPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class AddressReadManageImpl implements AddressReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AddressReadDAO addressReadDAO;
	
	public AddressPO findAddressById(AddressPO po) {
		AddressPO addresspo = new AddressPO();
		addresspo.setId(po.getId());
		return addressReadDAO.findById(addresspo);
	}

	public PageResult<AddressPO> findAddressOfPage(AddressPO po, Pagination page) {
		
		PageResult<AddressPO> pageResult = new PageResult<AddressPO>();
		List<AddressPO> list = null;

		int cnt = addressReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = addressReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<AddressPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<AddressPO> findAddressAll(AddressPO po) {

		return addressReadDAO.findAll(po,null);
	}
	
}
	