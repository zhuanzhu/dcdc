package com.egeo.components.order.manage.write.impl;

import java.util.*;

import com.egeo.components.order.dao.read.SoPackageReadDAO;
import com.egeo.components.order.dao.read.SoReadDAO;
import com.egeo.components.order.dao.write.*;
import com.egeo.components.order.po.*;
import com.egeo.components.pay.enums.OrderConstant;
import com.egeo.utils.DateUtils;
import com.egeo.utils.EmptyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.order.manage.write.SoPackageWriteManage;
import com.egeo.components.order.dao.read.ReceiverAddressReadDAO;
import com.egeo.components.order.dao.read.SoChildReadDAO;
import com.egeo.config.RuntimeContext;
import com.egeo.exception.BusinessException;

@Service
public class SoPackageWriteManageImpl implements SoPackageWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SoPackageWriteDAO soPackageWriteDAO;

	@Autowired
	private SoPackageReadDAO soPackageReadDAO;

	@Autowired
	private ReceiverAddressReadDAO raReadDao;

	@Autowired
	private SoPackageBoxWriteDAO boxWriteDAO;

	@Autowired
	private SoChildWriteDAO soChildWriteDAO;
	@Autowired
	private SoChildReadDAO soChildReadDAO;

	@Autowired
	private SoChildFlowWriteDAO soChildFlowWriteDAO;

	@Autowired
	private SoFlowWriteDAO soFlowWriteDAO;

	@Autowired
	private SoReadDAO soReadDAO;

	@Autowired
	private SoWriteDAO soWriteDAO;

	@Override
	public Long insertSoPackageWithTx(SoPackagePO po) {

		int i ;
		try {
				i = soPackageWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateSoPackageWithTx(SoPackagePO po) {
		int i;
		i = soPackageWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteSoPackageWithTx(SoPackagePO po) {
		int i;
		i = soPackageWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}

	@Override
	public void completeDeliveryImport(List<DeliveryImportExcelPO> poList, Long operatorId, Long platformId) {
		//完成订单发货导入:
		Set<Long> soIdSet=new HashSet<>();
		for(DeliveryImportExcelPO po:poList) {
			//为子订单插入运单,
			SoPackagePO pack=new SoPackagePO();
			pack.setDeliveryCode(po.getDeliveryCode());
			pack.setDeliveryCompanyId(po.getDeliveryCompanyId());
			pack.setDeliveryCompanyName(po.getDeliveryCompany());
			pack.setDeliveryMode(1);
			pack.setDeliveryStatus(1);
			if(EmptyUtil.isNotEmpty(po.getDeliveryCode())){
				pack.setDeliveryDate(new Date());
			}
			//查询订单收货信息
			ReceiverAddressPO raCond=new ReceiverAddressPO();
			raCond.setId(po.getReceiverAddressId());
			ReceiverAddressPO ra=raReadDao.findById(raCond);
			pack.setGoodReceiverAddress(ra.getGoodReceiverAddress());
			pack.setGoodReceiverMobile(ra.getGoodReceiverMobile());
			pack.setGoodReceiverName(ra.getGoodReceiverName());
			pack.setOrderCode(po.getOrderCode());
			pack.setPackageType(1);
			pack.setPlatformId(platformId);
			pack.setProCityArea(ra.getGoodReceiverProvince()+ra.getGoodReceiverCity()+ra.getGoodReceiverCounty());
			pack.setReceiverAddressId(po.getReceiverAddressId());
			pack.setSoChildId(po.getSoChildId());
			Long soId=po.getSoId();
			soIdSet.add(soId);//去重
			pack.setSoId(soId);
			pack.setUserId(po.getUserId());
			soPackageWriteDAO.insert(pack);
			Long packId=pack.getId();
			//有箱号时插入箱子,
			if(po.getBoxCode()!=null) {
				SoPackageBoxPO box=new SoPackageBoxPO();
				box.setSoBoxCode(po.getBoxCode());
				box.setSoChildId(po.getSoChildId());
				box.setSoPackageId(packId);
				boxWriteDAO.insert(box);
			}
			//更改子订单的状态为已发货,
			SoChildPO scCond=new SoChildPO();
			scCond.setId(po.getSoChildId());
			scCond.setDeliveryStatus(2);
			soChildWriteDAO.update(scCond);
			//插入操作记录
			SoChildFlowPO scFlow=new SoChildFlowPO();
			scFlow.setOperate(3);
			scFlow.setOperatorId(operatorId);
			scFlow.setSoChildId(po.getSoChildId());
			scFlow.setSoId(soId);
			soChildFlowWriteDAO.insert(scFlow);
		}
		List<Long> soIdList=new ArrayList<>();
		soIdList.addAll(soIdSet);
		for(Long id:soIdList) {
			SoFlowPO flow=new SoFlowPO();
			flow.setOperate(3);
			flow.setOperatorId(operatorId);
			flow.setSoId(id);
			soFlowWriteDAO.insert(flow);
		}
	}

	@Override
	public boolean expressInwayWithTx(Long soId, Long packageId, Long soChildId) {
		SoPackagePO packCond=new SoPackagePO();
		packCond.setId(packageId);
		packCond.setDeliveryStatus(2);
		soPackageWriteDAO.update(packCond);
		SoChildPO childCond=new SoChildPO();
		childCond.setId(soChildId);
		childCond.setDeliveryStatus(2);
		soChildWriteDAO.update(childCond);
		return true;
	}
	@Override
	public boolean expressSignInWithTx(Long soId,String deliveryMessage, Long packageId, Long soChildId) {
		SoPackagePO packCond=new SoPackagePO();
		packCond.setDeliveryMessage(deliveryMessage);
		packCond.setId(packageId);
		packCond.setDeliveryStatus(3);
		soPackageWriteDAO.update(packCond);
		SoChildPO childCond=new SoChildPO();
		childCond.setId(soChildId);
		childCond.setDeliveryStatus(3);
		soChildWriteDAO.update(childCond);
		SoFlowPO flow=new SoFlowPO();
		flow.setOperate(4);
		if(RuntimeContext.cacheUser()!=null) {
			flow.setOperatorId(RuntimeContext.cacheUser().getId());
		}
		flow.setSoId(soId);
		if(soId == null) {
			SoChildPO po = new SoChildPO();
			po.setId(soChildId);
			SoChildPO data = soChildReadDAO.findById(po);
			if(data!=null) {
				flow.setSoId(data.getId());
			}
		}
		if(flow.getSoId()!=null) {
			soFlowWriteDAO.insert(flow);
		}
		return true;
	}

	@Override
	public boolean expressTimeWithTx(Long soId, Long packageId, Long soChildId,String acceptTime) {
		try {
			SoPackagePO packCond=new SoPackagePO();
			packCond.setId(packageId);
			packCond.setDeliveryStatus(2);
			packCond.setDeliveryDate(DateUtils.parseDate(acceptTime,"yyyy-MM-dd HH:mm:ss"));
			soPackageWriteDAO.update(packCond);
		}catch (Exception e){
			logger.error("更新物流发货时间失败，发生异常:{}",e);
			return false;
		}
		return true;
	}


	@Override
	public void completeChildDeliveryImport(List<DeliveryImportExcelPO> poList, Long operatorId, Long platformId) {
		//完成订单发货导入:
		Set<Long> soIdSet=new HashSet<>();
		for(DeliveryImportExcelPO po:poList) {
			//为子订单插入运单,
			SoPackagePO pack=new SoPackagePO();
			pack.setDeliveryCode(po.getDeliveryCode());
			pack.setDeliveryCompanyId(po.getDeliveryCompanyId());
			pack.setDeliveryCompanyName(po.getDeliveryCompany());
			pack.setDeliveryMode(1);
			pack.setDeliveryStatus(1);
			if(EmptyUtil.isNotEmpty(po.getDeliveryCode())){
				pack.setDeliveryDate(new Date());
			}
			//查询订单收货信息
			ReceiverAddressPO raCond=new ReceiverAddressPO();
			raCond.setId(po.getReceiverAddressId());
			ReceiverAddressPO ra=raReadDao.findById(raCond);
			pack.setGoodReceiverAddress(ra.getGoodReceiverAddress());
			pack.setGoodReceiverMobile(ra.getGoodReceiverMobile());
			pack.setGoodReceiverName(ra.getGoodReceiverName());
			pack.setOrderCode(po.getOrderCode());
			pack.setPackageType(1);
			pack.setPlatformId(platformId);
			pack.setProCityArea(ra.getGoodReceiverProvince()+ra.getGoodReceiverCity()+ra.getGoodReceiverCounty());
			pack.setReceiverAddressId(po.getReceiverAddressId());
			pack.setSoChildId(po.getSoChildId());
			Long soId=po.getSoId();
			//去重
			soIdSet.add(soId);
			pack.setSoId(soId);
			pack.setUserId(po.getUserId());
			SoPackagePO queryPO = new SoPackagePO();
			queryPO.setDeliveryCode(pack.getDeliveryCode());
			queryPO.setSoChildId(pack.getSoChildId());
			List<SoPackagePO>	packagePOS = soPackageReadDAO.findAll(queryPO,null);
			if(EmptyUtil.isNotEmpty(packagePOS)){
				SoPackagePO old = packagePOS.get(0);
				pack.setId(old.getId());
				soPackageWriteDAO.update(pack);
			}else{
				soPackageWriteDAO.insert(pack);
			}
			Long packId=pack.getId();
			//有箱号时插入箱子,
			if(po.getBoxCode()!=null) {
				SoPackageBoxPO box=new SoPackageBoxPO();
				box.setSoBoxCode(po.getBoxCode());
				box.setSoChildId(po.getSoChildId());
				box.setSoPackageId(packId);
				boxWriteDAO.insert(box);
			}
			//更改子订单的状态为已发货,
			SoChildPO scCond=new SoChildPO();
			scCond.setId(po.getSoChildId());
			scCond.setDeliveryStatus(2);
			soChildWriteDAO.update(scCond);
			//插入操作记录
			SoChildFlowPO scFlow=new SoChildFlowPO();
			scFlow.setOperate(3);
			scFlow.setOperatorId(operatorId);
			scFlow.setSoChildId(po.getSoChildId());
			scFlow.setSoId(soId);
			soChildFlowWriteDAO.insert(scFlow);
		}
		List<Long> soIdList=new ArrayList<>();
		soIdList.addAll(soIdSet);
		SoPO queryPO =null;
		for(Long id:soIdList) {
			queryPO = new SoPO();
			queryPO.setId(id);
			SoPO old = soReadDAO.findById(queryPO);
			if(old !=null && !Objects.equals(old.getOrderStatus(), OrderConstant.ORDER_STATUS_PAYED.getStatus()) && !Objects.equals(old.getDeliveryStatus(),OrderConstant.ORDER_DELIVERY_STATUS_NO_DELIVERY)){
				queryPO.setOrderStatus(OrderConstant.ORDER_STATUS_DELIVERED.getStatus());
				queryPO.setDeliveryStatus(OrderConstant.ORDER_DELIVERY_STATUS_part_DELIVERY.getStatus());
				soWriteDAO.update(queryPO);
			}
			SoFlowPO flow=new SoFlowPO();
			flow.setOperate(3);
			flow.setOperatorId(operatorId);
			flow.setSoId(id);
			soFlowWriteDAO.insert(flow);
		}
	}
}
