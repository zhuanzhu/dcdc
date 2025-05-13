package com.egeo.components.order.service.read.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.egeo.components.order.condition.SoExtendsCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.converter.SoConverter;
import com.egeo.components.order.converter.SoItemConverter;
import com.egeo.components.order.dto.SoDTO;
import com.egeo.components.order.dto.SoDetailDTO;
import com.egeo.components.order.dto.SoItemDTO;
import com.egeo.components.order.manage.read.SoItemReadManage;
import com.egeo.components.order.manage.read.SoReadManage;
import com.egeo.components.order.po.SoItemPO;
import com.egeo.components.order.po.SoPO;
import com.egeo.components.order.service.read.SoReadService;
import com.egeo.components.utils.DateUtil;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import org.springframework.util.CollectionUtils;

@Service("soReadService")
public class SoReadServiceImpl  implements SoReadService {
	@Autowired
	private SoReadManage soReadManage;

	@Autowired
	private SoItemReadManage soItemReadManage;


	@Override
	public List<SoDetailDTO> findOrderByMonth(String month) {
		Date thisMonth = DateUtil.parseDate(DateUtil.YearMonth, month);
		List<SoPO> soList = soReadManage.findMonthOrder(thisMonth);
		List<SoDetailDTO> rslt = new ArrayList<SoDetailDTO>();
		Set<Long> soIds = new HashSet<Long>();
		for(SoPO so : soList) {
			if(!soIds.contains(so.getId())) {
				soIds.add(so.getId());
			}
		}
		if(soIds!=null && soIds.size()>0) {
			List<SoItemPO> soItemPOList = soItemReadManage.findSoItemsBySoIds(new ArrayList(soIds));

			Map<Long,List<SoItemPO>> soItemMap = new HashMap<Long,List<SoItemPO>>();
			for(SoItemPO item : soItemPOList) {
				Long soId = item.getSoId();
				List<SoItemPO> items = soItemMap.get(soId);
				if(items==null) {
					items = new ArrayList<SoItemPO>();
					soItemMap.put(soId, items);
				}
				items.add(item);
			}
			for(SoPO so : soList) {
				SoDetailDTO soDetailDTO = new SoDetailDTO();
				soDetailDTO.setSoDTO(SoConverter.toDTO(so));
				if(soItemMap.containsKey(so.getId())) {
					soDetailDTO.setSoItemDTOList(SoItemConverter.toDTO(soItemMap.get(so.getId())));
				}
				rslt.add(soDetailDTO);
			}
		}
		return rslt;
	}


	@Override
	public PageResult<SoDetailDTO> findOrderByUserAndStatus(Long userId, Integer orderStatus,Long platformId,Pagination page) {
		SoPO  soPO = new SoPO();
		soPO.setUserId(userId);
 		if(orderStatus != null && orderStatus != -99){
 			soPO.setOrderStatus(orderStatus);
		}
 		soPO.setPlatformId(platformId);
 		//仅查看未删除的订单
 		soPO.setOrderDeleteStatus(0);
 		soPO.setSaleWay(-1);//标记位-1,查询时去除会籍购买
 		PageResult<SoPO> soPOPage = soReadManage.findOrderListByUserAndStatus(soPO,page);
 		List<SoDetailDTO> soDetailDTOList = new ArrayList<SoDetailDTO>();
 		for(SoPO po:soPOPage.getList()){
 			SoItemPO soItem = new SoItemPO();
 			soItem.setUserId(userId);
 			soItem.setSoId(po.getId());
 			//订单项列表
 			List<SoItemPO> soItemPOList = soItemReadManage.findSoItemList(soItem);
 			//合并被拆分的订单项(在facade层做)
// 			List<SoItemPO> soItemPOList_=mergeSoItems(soItemPOList);
 			SoDetailDTO soDetailDTO = new SoDetailDTO();
 			soDetailDTO.setSoDTO(SoConverter.toDTO(po));
 			soDetailDTO.setSoItemDTOList(SoItemConverter.toDTO(soItemPOList));
 			soDetailDTOList.add(soDetailDTO);
 		}

        PageResult<SoDetailDTO> result = new PageResult<SoDetailDTO>();
        result.setList(soDetailDTOList);
        result.copy(soPOPage);
		return result;
	}

	/**
	 * 合并被拆分的订单项
	 * @param soItemPOList
	 * @return
	 */
	@Override
	public List<SoItemDTO> mergeSoItems(List<SoItemDTO> soItemDTOList) {
		List<SoItemPO> soItemPOList = SoItemConverter.toPO(soItemDTOList);
		List<SoItemPO> res=new ArrayList<>();
		outter:for(SoItemPO po:soItemPOList) {
			//查找在res中是否puid重合的项,若有,累计数量,若无,加入res
			long puId=po.getPuId();
			for(SoItemPO po_:res) {
				if(po_.getPuId().longValue()==puId) {
					po_.setPuCount(po_.getPuCount()+po.getPuCount());
					continue outter;
				}
			}
			res.add(po);
		}
		return SoItemConverter.toDTO(res);
	}

	@Override
	public List<SoDTO> findAllunpayOrders() {

		return SoConverter.toDTO(soReadManage.findAllunpayOrders());
	}

	@Override
	public List<SoItemDTO> findSoItemListByOrderCode(Long soId) {
			SoItemPO soItemPO = new SoItemPO();
			soItemPO.setSoId(soId);
			List<SoItemPO> soItemPOList = soItemReadManage.findSoItemList(soItemPO);
			return SoItemConverter.toDTO(soItemPOList);
	}

	@Override
	public List<SoDTO> querySoListByCondition(List<String> orderCodeList, Pagination page, Date startTime, Date endTime,
			Integer orderStatus,String exactOrderCode,Long platformId) {
		List<SoPO> soList=soReadManage.querySoListByCondition(orderCodeList,page,startTime,endTime,orderStatus,exactOrderCode,platformId);
		return SoConverter.toDTO(soList);
	}

	@Override
	public int querySoCountByCondition(List<String> orderCodeList, Date startTime, Date endTime,
			Integer orderStatus,String exactOrderCode,Long platformId) {
		return soReadManage.querySoCountByCondition(orderCodeList,startTime,endTime,orderStatus,exactOrderCode,platformId);
	}

	@Override
	public SoDTO querySoById(Long orderId) {
		SoPO po;
		if(orderId==-999) {

			po=soReadManage.querySoById(null);
		}else {

			po=soReadManage.querySoById(orderId);
		}
		return SoConverter.toDTO(po);
	}

	@Override
	public SoDTO querySoByOrderCode(String orderCode) {
		SoPO po=soReadManage.querySoByOrderCode(orderCode);
		return  SoConverter.toDTO(po);
	}

	@Override
	public SoDTO queryUndeleteSoByOrderCode(String orderCode) {

		return SoConverter.toDTO(soReadManage.queryUndeleteSoByOrderCode(orderCode));
	}

	@Override
	public PageResult<SoDTO> findSoOfPage(SoDTO soDTO, Integer cashPayType, Integer useFubi, Date startDateTime,
			Date endDateTime, Pagination page) {
		SoPO po = SoConverter.toPO(soDTO);
        PageResult<SoPO> pageResult = soReadManage.findSoOfPage(po, cashPayType,useFubi,startDateTime,endDateTime,page);

        List<SoDTO> list = SoConverter.toDTO(pageResult.getList());
        PageResult<SoDTO> result = new PageResult<SoDTO>();
        result.setList(list);
        result.copy(pageResult);
        return result;

	}

	@Override
	public PageResult<SoDTO> queryBackStageSoPage(
			Long storeId, String orderCode, List<Long> userIds, List<Long> puIds, Date startDateTime,
			Date endDateTime, Integer status, Integer confirmStatus, Integer payStatus, Integer paymentType,
			Boolean showTest, Long platformId, Pagination page, boolean refundFlag, List<Long> testCompanyIds,
			List<Long> companyIds,Integer auditStatus,List<Long>  soIds) {
		PageResult<SoPO> poPage=soReadManage.queryBackStageSoPage(
				storeId,orderCode, userIds, puIds, startDateTime,
				endDateTime, status, confirmStatus, payStatus,
				paymentType, showTest, platformId, page,refundFlag,
				testCompanyIds,companyIds,auditStatus,soIds);
		PageResult<SoDTO> dtoPage=new PageResult<>();
		dtoPage.copy(poPage);
		dtoPage.setList(SoConverter.toDTO(poPage.getList()));
		return dtoPage;
	}
	/**
	 * 查询所有状态为已发货、已收货，订单自动完成时间为空的订单
	 */
	@Override
	public List<SoDTO> findByOrderStatusNoOrderAutoCompleteDate() {
		List<SoPO> soList = soReadManage.findByOrderStatusNoOrderAutoCompleteDate();
		return SoConverter.toDTO(soList);
	}
	/**
	 * 查询订单自动完成时间不为空的订单
	 * @return
	 */
	@Override
	public List<SoDTO> findByOrderAutoCompleteDateNoNull() {
		List<SoPO> soList = soReadManage.findByOrderAutoCompleteDateNoNull();
		return SoConverter.toDTO(soList);
	}

	@Override
	public int querySoCountByUserAndStatus(Long userId, Integer orderStatus,Long platformId) {

		return soReadManage.querySoCountByUserAndStatus(userId, orderStatus,platformId);
	}

	@Override
	public Integer findCurrentMonthOrder(Long storeId, Long platformId) {
		return soReadManage.findCurrentMonthOrder(storeId,platformId);
	}

	@Override
	public Integer findCurrentDayOrder(Long storeId, Long platformId) {
		return soReadManage.findCurrentDayOrder(storeId, platformId);
	}

	@Override
	public Integer findNoSignOrderSumByStatus(Integer orderConfirmStatus, Integer orderPayStatus, Long storeId,
			Long platformId) {
		return soReadManage.findNoSignOrderSumByStatus(orderConfirmStatus, orderPayStatus, storeId, platformId);
	}

	@Override
	public BigDecimal findCurrentMonthOrderAmount(Long storeId, Long platformId) {
		return soReadManage.findCurrentMonthOrderAmount(storeId, platformId);
	}

	@Override
	public BigDecimal findcurrentDayOrderAmount(Long storeId, Long platformId) {
		return soReadManage.findcurrentDayOrderAmount(storeId, platformId);
	}

	@Override
	public List<SoDTO> findSoByCode(String orderCode) {

		List<SoPO> list=soReadManage.findSoByCode(orderCode);
		return SoConverter.toDTO(list);
	}

	@Override
	public SoDTO findSoById(Long orderId) {

		return SoConverter.toDTO(soReadManage.findSoById(orderId));
	}

	@Override
	public Long findSoSum(SoDTO soDTO) {
		SoPO soPO = SoConverter.toPO(soDTO);

		return soReadManage.findSoSum(soPO);
	}

	@Override
	public List<SoDTO> findSoDTOByPuId(Long puId, Integer orderPayStatus) {

		List<SoPO> soPOs = soReadManage.findSoByPuId(puId, orderPayStatus);

		return SoConverter.toDTO(soPOs);
	}

	@Override
	public List<SoDTO> findSoDTOByPuId(List<Long> puIds, Integer orderConfirmStatus) {

		List<SoPO> soPOs = soReadManage.findSoByPuIds(puIds, orderConfirmStatus);

		return SoConverter.toDTO(soPOs);
	}

	@Override
	public SoDTO findSoByThirdpartyId(String jdOrderId) {
		return SoConverter.toDTO(soReadManage.findSoByThirdpartyId(jdOrderId));
	}

	@Override
	public PageResult<SoExtendsCondition> getOrderByUserAndStatus(Long userId, Integer orderStatus,Long platformId,Pagination page) {
		SoPO soPO = new SoPO();
		soPO.setUserId(userId);
		if (orderStatus != null && orderStatus != -99) {
			soPO.setOrderStatus(orderStatus);
		}
		soPO.setPlatformId(platformId);
		//仅查看未删除的订单
		soPO.setOrderDeleteStatus(0);
		soPO.setSaleWay(-1);//标记位-1,查询时去除会籍购买
		PageResult<SoExtendsCondition> soPOPage = soReadManage.getOrderListByUserAndStatus(soPO, page);
		return soPOPage;
	}

	@Override
	public List<SoDTO> getSoByIds(List<Long> ids){
		if(CollectionUtils.isEmpty(ids)){
			return null;
		}
		return SoConverter.toDTO(soReadManage.getSoByIds(ids));
	}

	public List<SoDTO> queryBackStageSoList(
			Long storeId, String orderCode, List<Long> userIds, List<Long> puIds, Date startDateTime,
			Date endDateTime, Integer status, Integer confirmStatus, Integer payStatus, Integer paymentType,
			Boolean showTest, Long platformId, Pagination page, boolean refundFlag, List<Long> testCompanyIds,
			List<Long> companyIds,Integer auditStatus,List<Long>  soIds) {
		List<SoPO> poPage=soReadManage.queryBackStageSoListPage(
				storeId,orderCode, userIds, puIds, startDateTime,
				endDateTime, status, confirmStatus, payStatus,
				paymentType, showTest, platformId, page,refundFlag,
				testCompanyIds,companyIds,auditStatus,soIds);
		return SoConverter.toDTO(poPage);
	}
}
