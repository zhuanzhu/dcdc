package com.egeo.components.order.manage.read.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.egeo.components.order.vo.OrderSortExportVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.manage.read.SoChildReadManage;
import com.egeo.components.order.condition.SoChildCondition;
import com.egeo.components.order.dao.read.SoChildReadDAO;
import com.egeo.components.order.po.SoChildPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class SoChildReadManageImpl implements SoChildReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SoChildReadDAO soChildReadDAO;

	public SoChildPO findSoChildById(SoChildPO po) {
		SoChildPO soChildpo = new SoChildPO();
		soChildpo.setId(po.getId());
		return soChildReadDAO.findById(soChildpo);
	}

	public PageResult<SoChildPO> findSoChildOfPage(SoChildPO po, Pagination page) {

		PageResult<SoChildPO> pageResult = new PageResult<SoChildPO>();
		List<SoChildPO> list = null;

		int cnt = soChildReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = soChildReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<SoChildPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<SoChildPO> findSoChildAll(SoChildPO po) {

		return soChildReadDAO.findAll(po,null);
	}

    @Override
    public Long findSoChildIdByThirdpartyCode(String thirdpartyCode) {
        return soChildReadDAO.findSoChildIdByThirdpartyCode(thirdpartyCode);
    }

	@Override
	public List<SoChildPO> findSoChildByCondition(Long[] soChildId, Long platformId) {
		if(soChildId!=null && soChildId.length>0) {
			return soChildReadDAO.findSoChildByCondition(soChildId,platformId,null);
		}
		return null;
	}

	@Override
	public SoChildPO querySoChildByChildCode(String childCode) {
		return soChildReadDAO.querySoChildByChildCode(childCode);
	}

	@Override
	public PageResult<SoChildPO> getSoChildAllList(Integer merchantId, String soChildCode, List<Long> userIds, List<Long> puIds,
                                                   Date soCreateTimeStart, Date soCreateTimeEnd, Integer soType,
                                                   Integer soChildDeliveryStatus, Integer soConfirmStatus, Date sendTimeStart,
                                                   Date sendTimeEnd, Boolean showTest, Long platformId, List<Long> testCompanyIds, Pagination page,
												   Integer orderPayStatus,Integer auditStatus) {
		List<SoChildPO> poList=soChildReadDAO.getSoChildAllListByPage(null,merchantId,soChildCode,userIds,puIds,
				soCreateTimeStart, soCreateTimeEnd, soType,
				soChildDeliveryStatus, soConfirmStatus,sendTimeStart,
				 sendTimeEnd, showTest, platformId, testCompanyIds,page,null,orderPayStatus,auditStatus);
		Integer totalCount=soChildReadDAO.getSoChildAllListTotalCount(null,merchantId,soChildCode,userIds,puIds,
				soCreateTimeStart, soCreateTimeEnd, soType,
				soChildDeliveryStatus, soConfirmStatus,sendTimeStart,
				sendTimeEnd, showTest, platformId, testCompanyIds,null,orderPayStatus,auditStatus);

		PageResult<SoChildPO> soChildPOPageResult = new PageResult<>();
		soChildPOPageResult.copy(page);
		soChildPOPageResult.setList(poList);
		soChildPOPageResult.setTotalSize(totalCount);

		return soChildPOPageResult;
	}

	@Override
	public PageResult<SoChildPO> getSupplierSoChildAllList(Integer merchantId, String soChildCode, List<Long> userIds, List<Long> puIds,
														   Date soCreateTimeStart, Date soCreateTimeEnd, Integer soType,
														   Integer soChildDeliveryStatus, Integer soConfirmStatus, Date sendTimeStart,
														   Date sendTimeEnd, Boolean showTest, Long platformId, List<Long> testCompanyIds,
														   Pagination page, Long supplierId, Integer orderPayStatus,Integer auditStatus) {
		List<SoChildPO> poList=soChildReadDAO.getSoChildAllListByPage(null,merchantId,soChildCode,userIds,puIds,
				soCreateTimeStart, soCreateTimeEnd, soType,
				soChildDeliveryStatus, soConfirmStatus,sendTimeStart,
				 sendTimeEnd, showTest, platformId, testCompanyIds,page,supplierId,orderPayStatus,auditStatus);
		Integer totalCount=soChildReadDAO.getSoChildAllListTotalCount(null,merchantId,soChildCode,userIds,puIds,
				soCreateTimeStart, soCreateTimeEnd, soType,
				soChildDeliveryStatus, soConfirmStatus,sendTimeStart,
				sendTimeEnd, showTest, platformId, testCompanyIds,supplierId,orderPayStatus,auditStatus);

		PageResult<SoChildPO> soChildPOPageResult = new PageResult<>();
		soChildPOPageResult.copy(page);
		soChildPOPageResult.setList(poList);
		soChildPOPageResult.setTotalSize(totalCount);

		return soChildPOPageResult;
	}
	@Override
	public PageResult<SoChildCondition> findMerchantChildOrderOfPage(SoChildCondition po, Pagination page) {
		List<SoChildCondition> poList=soChildReadDAO.findMerchantChildOrderOfPage(po, page);
		Integer totalCount=soChildReadDAO.countMerchantChildOrderOfPage(po,null);

		PageResult<SoChildCondition> soChildConditionPageResult = new PageResult<>();
		soChildConditionPageResult.copy(page);
		soChildConditionPageResult.setList(poList);
		soChildConditionPageResult.setTotalSize(totalCount);

		return soChildConditionPageResult;
	}

	@Override
	public List<SoChildCondition> findWarningChildOrder(Date warningDate) {
		return soChildReadDAO.findWarningChildOrder(warningDate);
	}

	@Override
	public Long findSoChildIdByThirdpartyId(Long jdOrderId) {
		return soChildReadDAO.findSoChildIdByThirdpartyId(jdOrderId);
	}

	@Override
	public List<SoChildCondition> findSoChildListByMerchantId(List<Long> idList, Long merchantId) {
		return soChildReadDAO.findSoChildListByMerchantId(idList,merchantId);
	}

	@Override
	public List<SoChildCondition> findSoChildListBySupplierId(List<Long> idList, Long supplierId) {
		return soChildReadDAO.findSoChildListBySupplierId(idList,supplierId);
	}

	@Override
	public List<OrderSortExportVO> soDetailOrderExport(List<Long> soIdList) {
		return soChildReadDAO.soDetailOrderExport(soIdList);
	}

	@Override
	public List<SoChildPO> getSoChildAllListToExport(Integer merchantId, String soChildCode, List<Long> userIds, List<Long> puIds,
												   Date soCreateTimeStart, Date soCreateTimeEnd, Integer soType,
												   Integer soChildDeliveryStatus, Integer soConfirmStatus, Date sendTimeStart,
												   Date sendTimeEnd, Boolean showTest, Long platformId, List<Long> testCompanyIds, Pagination page,
												   Integer orderPayStatus,Integer auditStatus) {
		List<SoChildPO> poList=soChildReadDAO.getSoChildAllListByPage(null,merchantId,soChildCode,userIds,puIds,
				soCreateTimeStart, soCreateTimeEnd, soType,
				soChildDeliveryStatus, soConfirmStatus,sendTimeStart,
				sendTimeEnd, showTest, platformId, testCompanyIds,page,null,orderPayStatus,auditStatus);

		return poList;
	}

	@Override
	public List<SoChildPO> getSupplierSoChildAllListToExport(Integer merchantId, String soChildCode, List<Long> userIds, List<Long> puIds,
														   Date soCreateTimeStart, Date soCreateTimeEnd, Integer soType,
														   Integer soChildDeliveryStatus, Integer soConfirmStatus, Date sendTimeStart,
														   Date sendTimeEnd, Boolean showTest, Long platformId, List<Long> testCompanyIds,
														   Pagination page, Long supplierId, Integer orderPayStatus,Integer auditStatus) {
		List<SoChildPO> poList=soChildReadDAO.getSoChildAllListByPage(null,merchantId,soChildCode,userIds,puIds,
				soCreateTimeStart, soCreateTimeEnd, soType,
				soChildDeliveryStatus, soConfirmStatus,sendTimeStart,
				sendTimeEnd, showTest, platformId, testCompanyIds,page,supplierId,orderPayStatus,auditStatus);
		return poList;
	}
}
