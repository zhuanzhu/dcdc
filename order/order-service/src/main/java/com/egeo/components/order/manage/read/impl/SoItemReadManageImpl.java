package com.egeo.components.order.manage.read.impl;

import java.util.List;

import com.egeo.components.order.dto.SoItemDTO;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.manage.read.SoItemReadManage;
import com.egeo.components.order.condition.SoItemCondition;
import com.egeo.components.order.dao.read.SoItemReadDAO;
import com.egeo.components.order.po.SoItemPO;

@Service
public class SoItemReadManageImpl implements SoItemReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SoItemReadDAO soItemReadDAO;
	@Override
	public List<SoItemPO> findSoItemList(SoItemPO soItemPO) {

		return soItemReadDAO.findAll(soItemPO,null);
	}

	@Override
	public List<SoItemPO> querySoItemListByMerchantIds(List<Long> merchantIdList,Long platformId) {
		return soItemReadDAO.querySoItemListByMerchantIds(merchantIdList,platformId);
	}

	@Override
	public List<SoItemPO> findSoItemsBySoIds(List<Long> soIds) {
		return soItemReadDAO.findSoItemsBySoIds(soIds);
	}

	@Override
	public List<SoItemPO> querySoItemBySoId(Long soId) {
		return soItemReadDAO.querySoItemListBySoId(soId);
	}
	@Override
	public List<SoItemPO> querySoItemListByPackId(Long packId, Long platformId) {
		SoItemPO soItemPO = new SoItemPO();
//		soItemPO.setPackId(packId);
		soItemPO.setPlatformId(platformId);
		return soItemReadDAO.findAll(soItemPO,null);
	}

	@Override
	public List<SoItemPO> findAll(SoItemPO po) {

		return soItemReadDAO.findAll(po,null);
	}

	@Override
	public List<SoItemPO> soItemByPackageId(Long id) {

		return soItemReadDAO.soItemByPackageId(id);
	}

	@Override
	public List<SoItemPO> querySoItemListBySoId(Long id) {
		return soItemReadDAO.querySoItemListBySoId(id);
	}
	/**
	 * 查询是否该订单是否存在unit商品
	 * @param po
	 * @return
	 */
	@Override
	public List<SoItemCondition> findAllBySoIdAndUnitExist(SoItemPO po) {
		// TODO Auto-generated method stub
		return soItemReadDAO.findAllBySoIdAndUnitExist(po,null);
	}

	@Override
	public Long finPUNum(Long id) {
		return soItemReadDAO.findPUNum(id);


	}

	@Override
	public Long findPuIdBySoChildId(Long soChildId) {
		return soItemReadDAO.findPuIdBySoChildId(soChildId);
	}

	@Override
	public Long findSoChildPUNum(Long id) {
		return soItemReadDAO.findSoChildPUNum(id);
	}

	@Override
	public List<Long> findPuIdBySoId(Long id) {
		return soItemReadDAO.findPuIdBySoId(id);
	}

	@Override
	public List<SoItemCondition> findSoItemByPuId(Long puId, Integer orderConfirmStatus) {
		return soItemReadDAO.findSoByPuId(puId, orderConfirmStatus);
	}

	@Override
	public List<SoItemCondition> findSoItemByPuId(List<Long> puIds, Integer orderConfirmStatus) {
		return soItemReadDAO.findSoByPuIds(puIds, orderConfirmStatus);
	}

	@Override
	public List<String> findProductIdsSoChild(Long soChildId){
		return soItemReadDAO.findProductIdsSoChild(soChildId);
	}

	@Override
	public List<SoItemPO> findSoItemsSoChild(Long soChildId){
		return soItemReadDAO.findSoItemsSoChild(soChildId);
	}

	@Override
	public SoItemPO getById(Long id){
		SoItemPO po = new SoItemPO();
		po.setId(id);
		return soItemReadDAO.findById(po);
	}

	@Override
	public  List<SoItemPO> getByIds(List<Long> ids){
		return soItemReadDAO.getByIds(ids);
	}
}
