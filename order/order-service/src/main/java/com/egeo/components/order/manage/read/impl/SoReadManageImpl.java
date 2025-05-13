package com.egeo.components.order.manage.read.impl;

import java.math.BigDecimal;
import java.util.*;

import com.egeo.components.order.condition.SoExtendsCondition;
import com.egeo.components.order.dto.SoDTO;
import com.egeo.utils.EmptyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.dao.read.SoReadDAO;
import com.egeo.components.order.manage.read.SoReadManage;
import com.egeo.components.order.po.SoPO;
import com.egeo.components.pay.enums.OrderConstant;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service
public class SoReadManageImpl implements SoReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SoReadDAO soReadDAO;
	@Override
	public PageResult<SoPO> findOrderListByUserAndStatus(SoPO soPO, Pagination page) {

		int cnt = soReadDAO.countOfPage(soPO);
        List<SoPO> list = new ArrayList<SoPO>();
        if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
            page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
            list = soReadDAO.findOfPage(soPO, page);
        }
        PageResult<SoPO> pageResult = new PageResult<SoPO>();
        pageResult.setList(list);
        pageResult.setTotalSize(cnt);
        pageResult.setPageNo(page.getPageNo());
        pageResult.setPageSize(page.getPageSize());
        return pageResult;
	}
	@Override
	public List<SoPO> findAllunpayOrders() {
		SoPO po = new SoPO();
		//1已下单未付款
		po.setOrderStatus(OrderConstant.ORDER_STATUS_UNPAY.getStatus());
		//0未确认订单
		po.setOrderConfirmStatus(0);
		//仅查询半个小时内的
		return soReadDAO.findAllunpayOrders(po);
	}
	@Override
	public List<SoPO> querySoListByCondition(List<String> orderCodeList, Pagination page, Date startTime, Date endTime,
			Integer orderStatus,String exactOrderCode,Long platformId) {

		return soReadDAO.querySoListByCondition(orderCodeList,page,startTime,endTime,orderStatus,exactOrderCode,platformId);
	}
	@Override
	public int querySoCountByCondition(List<String> orderCodeList, Date startTime, Date endTime,
			Integer orderStatus,String exactOrderCode,Long platformId) {
		return soReadDAO.querySoCountByCondition(orderCodeList,startTime,endTime,orderStatus,exactOrderCode,platformId);
	}
	@Override
	public SoPO querySoById(Long orderId) {
		SoPO condition=new SoPO();
		condition.setId(orderId);
		return soReadDAO.findById(condition);
	}
	@Override
	public SoPO querySoByOrderCode(String orderCode) {

		return soReadDAO.querySoByOrderCode(orderCode);
	}
	@Override
	public SoPO queryUndeleteSoByOrderCode(String orderCode) {
		return soReadDAO.queryUndeleteSoByOrderCode(orderCode);
	}


	@Override
	public PageResult<SoPO> findSoOfPage(SoPO po, Integer cashPayType, Integer useFubi, Date startDateTime, Date endDateTime, Pagination page) {
		PageResult<SoPO> pageResult = new PageResult<SoPO>();
		List<SoPO> list = soReadDAO.findOrderOfPage(po,cashPayType,useFubi ,startDateTime,endDateTime,page);
		int cnt = soReadDAO.countOrderOfPage(po,cashPayType,useFubi,startDateTime,endDateTime);
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;
	}
	@Override
	public PageResult<SoPO> queryBackStageSoPage(
			Long storeId, String orderCode, List<Long> userIds, List<Long> puIds, Date startDateTime,
			Date endDateTime, Integer status, Integer confirmStatus, Integer payStatus, Integer paymentType,
			Boolean showTest, Long platformId, Pagination page, boolean refundFlag, List<Long> testCompanyIds,
			List<Long> companyIds,Integer auditStatus,List<Long>  soIds) {
		List<SoPO> poList=null;
		Integer totalCount=null;
		if(EmptyUtil.isEmpty(companyIds)) {
			companyIds = null;
		}
		totalCount=soReadDAO.queryBackStageSoPageTotalSize(storeId,orderCode, userIds, puIds, startDateTime, endDateTime,
					status, confirmStatus, payStatus, paymentType, showTest, platformId,refundFlag, testCompanyIds,
				companyIds,auditStatus,soIds);
        if (Objects.nonNull(totalCount) && totalCount>0){
            poList=soReadDAO.queryBackStageSoPage(storeId,orderCode, userIds,puIds, startDateTime, endDateTime, status,
                    confirmStatus, payStatus, paymentType, showTest, platformId, page,refundFlag, testCompanyIds,
					companyIds,auditStatus,soIds);
        }else {
            poList= Collections.emptyList();
        }
		PageResult<SoPO> poPage=new PageResult<>();
		poPage.setList(poList);
		poPage.setTotalSize(totalCount);
		poPage.copy(page);
		return poPage;
	}
	/**
	 * 查询所有状态为已发货、已收货，订单自动完成时间为空的订单
	 */
	@Override
	public List<SoPO> findByOrderStatusNoOrderAutoCompleteDate() {
		// TODO Auto-generated method stub
		return soReadDAO.findByOrderStatusNoOrderAutoCompleteDate();
	}
	/**
	 * 查询订单自动完成时间不为空的订单
	 * @return
	 */
	@Override
	public List<SoPO> findByOrderAutoCompleteDateNoNull() {
		// TODO Auto-generated method stub
		return soReadDAO.findByOrderAutoCompleteDateNoNull();
	}
	@Override
	public int querySoCountByUserAndStatus(Long userId, Integer orderStatus,Long platformId) {
		// 0：未删除1：回收站-用户可恢复到02：用户完全删除(客服可协助恢复到0或1)
		Integer orderDeleteStatus = 0;
		return soReadDAO.querySoCountByUserAndStatus(userId, orderStatus,orderDeleteStatus,platformId);
	}
	@Override
	public Integer findCurrentMonthOrder(Long storeId, Long platformId) {
		return soReadDAO.findCurrentMonthOrder(storeId, new Date(),platformId);
	}
	@Override
	public List<SoPO> findMonthOrder(Date month) {
		return soReadDAO.findMonthOrder(month);
	}
	@Override
	public Integer findCurrentDayOrder(Long storeId, Long platformId) {
		return soReadDAO.findCurrentDayOrder(storeId, new Date(), platformId);
	}
	@Override
	public Integer findNoSignOrderSumByStatus(Integer orderConfirmStatus, Integer orderPayStatus, Long storeId,
			Long platformId) {
		return soReadDAO.findNoSignOrderSumByStatus(orderConfirmStatus, orderPayStatus, storeId,platformId);
	}
	@Override
	public BigDecimal findCurrentMonthOrderAmount(Long storeId, Long platformId) {
		return soReadDAO.findCurrentMonthOrderAmount(storeId,new Date(), platformId);
	}
	@Override
	public BigDecimal findcurrentDayOrderAmount(Long storeId, Long platformId) {
		return soReadDAO.findcurrentDayOrderAmount(storeId,new Date(), platformId);
	}

	@Override
	public List<SoPO> findSoByCode(String orderCode) {
		SoPO po = new SoPO();
		po.setOrderCode(orderCode);
		return soReadDAO.findAll(po,null);
	}

	@Override
	public SoPO findSoById(Long orderId) {
		SoPO po = new SoPO();
	po.setId(orderId);
		return soReadDAO.findById(po);
	}

	@Override
	public Long findSoSum(SoPO soPO) {
		return soReadDAO.findSoSum(soPO);
	}

	@Override
	public List<SoPO> findSoByPuId(Long puId, Integer orderConfirmStatus) {

		return soReadDAO.findSoByPuId(puId,orderConfirmStatus);
	}
	@Override
	public List<SoPO> findSoByPuIds(List<Long> puIds, Integer orderConfirmStatus) {

		return soReadDAO.findSoByPuIds(puIds,orderConfirmStatus);
	}

	@Override
	public SoPO findSoByThirdpartyId(String jdOrderId) {
		return soReadDAO.findSoByThirdpartyId(jdOrderId);
	}


	@Override
	public PageResult<SoExtendsCondition> getOrderListByUserAndStatus(SoPO soPO, Pagination page) {

		int cnt = soReadDAO.getOrderListByUserAndStatusCount(soPO);
		List<SoExtendsCondition> list = new ArrayList<>();
		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = soReadDAO.getOrderListByUserAndStatusPage(page,soPO);
		}
		PageResult<SoExtendsCondition> pageResult = new PageResult<>();
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;
	}

	@Override
	public List<SoPO> getSoByIds(List<Long> ids){
		return soReadDAO.getSoByIds(ids);
	}

	@Override
	public List<SoPO> queryBackStageSoListPage(
			Long storeId, String orderCode, List<Long> userIds, List<Long> puIds, Date startDateTime,
			Date endDateTime, Integer status, Integer confirmStatus, Integer payStatus, Integer paymentType,
			Boolean showTest, Long platformId, Pagination page, boolean refundFlag, List<Long> testCompanyIds,
			List<Long> companyIds,Integer auditStatus,List<Long>  soIds) {
		List<SoPO> poList=null;
		Integer totalCount=null;
		if(EmptyUtil.isEmpty(companyIds)) {
			companyIds = null;
		}
		totalCount=soReadDAO.queryBackStageSoPageTotalSize(storeId,orderCode, userIds, puIds, startDateTime, endDateTime,
				status, confirmStatus, payStatus, paymentType, showTest, platformId,refundFlag, testCompanyIds,
				companyIds,auditStatus,soIds);
		if (Objects.nonNull(totalCount) && totalCount>0){
			poList=soReadDAO.queryBackStageSoPage(storeId,orderCode, userIds,puIds, startDateTime, endDateTime, status,
					confirmStatus, payStatus, paymentType, showTest, platformId, page,refundFlag, testCompanyIds,
					companyIds,auditStatus,soIds);
		}else {
			poList= Collections.emptyList();
		}
		return poList;
	}
}
