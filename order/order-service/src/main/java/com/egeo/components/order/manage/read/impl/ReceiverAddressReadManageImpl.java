package com.egeo.components.order.manage.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.manage.read.ReceiverAddressReadManage;
import com.egeo.components.order.condition.ReceiverAddressCondition;
import com.egeo.components.order.dao.read.ReceiverAddressReadDAO;
import com.egeo.components.order.po.ReceiverAddressPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class ReceiverAddressReadManageImpl implements ReceiverAddressReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ReceiverAddressReadDAO receiverAddressReadDAO;

	public ReceiverAddressPO findReceiverAddressById(ReceiverAddressPO po) {
		ReceiverAddressPO receiverAddresspo = new ReceiverAddressPO();
		receiverAddresspo.setId(po.getId());
		return receiverAddressReadDAO.findById(receiverAddresspo);
	}

	public PageResult<ReceiverAddressPO> findReceiverAddressOfPage(ReceiverAddressPO po, Pagination page) {

		PageResult<ReceiverAddressPO> pageResult = new PageResult<ReceiverAddressPO>();
		List<ReceiverAddressPO> list = null;

		int cnt = receiverAddressReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = receiverAddressReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<ReceiverAddressPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<ReceiverAddressPO> findReceiverAddressAll(ReceiverAddressPO po) {

		return receiverAddressReadDAO.findAll(po,null);
	}

	@Override
	public ReceiverAddressPO queryDefaultReceiverAddress(Long memberId, Long platformId) {
		List<ReceiverAddressPO> all = receiverAddressReadDAO.queryReceiverAddressListCreatedByUser(memberId, platformId);
		ReceiverAddressPO defaultAddress = null;
		if(all!=null && all.size()>0) {
			for(ReceiverAddressPO one : all) {
				if(one.getIsDefault()!=null && one.getIsDefault().intValue()==1) {
					defaultAddress = one;
				}
			}
			if(defaultAddress==null) {
				defaultAddress = all.get(0);
			}
		}
		return defaultAddress;
	}

	@Override
	public List<ReceiverAddressCondition> findReceiveDetailBySoId(Long soId, Long platformId) {
		return receiverAddressReadDAO.findReceiveDetailBySoId(soId,platformId);
	}

	@Override
	public List<ReceiverAddressPO> queryReceiverAddressListCreatedByUser(Long userId, Long platformId) {
		return receiverAddressReadDAO.queryReceiverAddressListCreatedByUser(userId,platformId);
	}
	/**
	 * 查询当前用户收货地址数量
	 * @param userId 用户id
	 * @param platformId 平台id
	 * @return
	 */
	@Override
	public int receiverAddressSumByUserId(Long userId, Long platformId) {
		return receiverAddressReadDAO.receiverAddressSumByUserId(userId, platformId);
	}

	@Override
	public ReceiverAddressPO queryReceiverAddressByChildCodeAndType(String childCode, int type) {
		return receiverAddressReadDAO.queryReceiverAddressByChildCodeAndType(childCode,type);
	}

	@Override
	public List<ReceiverAddressPO> queryReceiverAddressListCreateByBackStage(String childCode) {
		return receiverAddressReadDAO.queryReceiverAddressListCreateByBackStage(childCode);
	}

	/**
	 * 根据收货人手机号查询用户id列表
	 * @param goodReceiverMobile
	 * @return
	 */
	@Override
	public List<Long> getUserIdListByReceiverAddressMobile(String goodReceiverMobile){
		return receiverAddressReadDAO.getUserIdList(goodReceiverMobile);
	}
}
