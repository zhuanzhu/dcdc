package com.egeo.components.order.service.read.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.egeo.components.order.vo.OrderSortExportVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.service.read.SoChildReadService;
import com.egeo.components.order.manage.read.SoChildReadManage;
import com.egeo.components.order.manage.read.SoReadManage;
import com.egeo.components.order.condition.SoChildCondition;
import com.egeo.components.order.converter.SoChildConverter;
import com.egeo.components.order.dto.SoChildDTO;
import com.egeo.components.order.dto.SoChildDTOCondition;
import com.egeo.components.order.po.SoChildPO;
import com.egeo.components.order.po.SoPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import org.springframework.util.CollectionUtils;

@Service("soChildReadService")
public class SoChildReadServiceImpl  implements SoChildReadService {
	@Autowired
	private SoChildReadManage soChildReadManage;

	@Autowired
	private SoReadManage soReadManage;

	@Override
	public SoChildDTO findSoChildById(Long id) {
		SoChildPO po=new SoChildPO();
		po.setId(id);
		SoChildPO list = soChildReadManage.findSoChildById(po);
		return SoChildConverter.toDTO(list);
	}

	@Override
	public PageResult<SoChildDTO> findSoChildOfPage(SoChildDTO dto, Pagination page) {
		SoChildPO po = SoChildConverter.toPO(dto);
        PageResult<SoChildPO> pageResult = soChildReadManage.findSoChildOfPage(po, page);

        List<SoChildDTO> list = SoChildConverter.toDTO(pageResult.getList());
        PageResult<SoChildDTO> result = new PageResult<SoChildDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<SoChildDTO> findSoChildAll(SoChildDTO dto) {
		SoChildPO po = SoChildConverter.toPO(dto);
		List<SoChildPO> list = soChildReadManage.findSoChildAll(po);
		return SoChildConverter.toDTO(list);
	}

	@Override
	public List<SoChildDTOCondition> findSoChildByCondition(Long[] soChildId, Long platformId) {
		List<SoChildPO> soChildPOList=soChildReadManage.findSoChildByCondition(soChildId,platformId);
		return SoChildConverter.toDTOCondition(soChildPOList);
	}


	@Override
	public List<SoChildDTO> findSoChildByIds(Long[] soChildId, Long platformId) {
		List<SoChildPO> soChildPOList=soChildReadManage.findSoChildByCondition(soChildId,platformId);
		return SoChildConverter.toDTO(soChildPOList);
	}
	@Override
	public String queryMaxChildCodePlus1BySoId(Long soId) {
		SoChildPO cond=new SoChildPO();
		cond.setSoId(soId);
		List<SoChildPO> scs=soChildReadManage.findSoChildAll(cond);
		SoPO so=soReadManage.querySoById(soId);
		String orderCode=so.getOrderCode();
		int maxSuffix=0;
		for(SoChildPO sc:scs) {
			String childCode=sc.getChildCode();
			String suffix=childCode.substring(childCode.lastIndexOf("-")+1);
			int suffixInt=Integer.parseInt(suffix);
			maxSuffix=Math.max(suffixInt, maxSuffix);
		}
		return orderCode+"-"+(maxSuffix+1);
	}

	@Override
	public SoChildDTO querySoChildByChildCode(String childCode) {
		return SoChildConverter.toDTOCondition(soChildReadManage.querySoChildByChildCode(childCode));
	}

	@Override
	public List<SoChildDTO> querySoChildListBySoId(Long id) {
		SoChildPO po=new SoChildPO();
		po.setSoId(id);
		return SoChildConverter.toDTO(soChildReadManage.findSoChildAll(po));
	}

	@Override
	public PageResult<SoChildDTO> getSoChildAllList(Integer merchantId, String soChildCode, List<Long> userIds, List<Long> puIds, Date soCreateTimeStart, Date soCreateTimeEnd, Integer soType, Integer soChildDeliveryStatus, Integer soConfirmStatus,
													Date sendTimeStart, Date sendTimeEnd, Boolean showTest, Long platformId, List<Long> testCompanyIds, Pagination page,Integer orderPayStatus,Integer auditStatus) {
		PageResult<SoChildPO> pageResult=soChildReadManage.getSoChildAllList(merchantId,soChildCode, userIds, puIds,
				soCreateTimeStart, soCreateTimeEnd,soType, soChildDeliveryStatus, soConfirmStatus, sendTimeStart,
				sendTimeEnd, showTest, platformId, testCompanyIds,page,orderPayStatus,auditStatus);
		PageResult<SoChildDTO> result = new PageResult<SoChildDTO>();
		if(EmptyUtil.isNotEmpty(pageResult.getList())){
			List<SoChildDTO> list = SoChildConverter.toDTO(pageResult.getList());
			result.setList(list);
		}else{
			result.setList(null);
		}
		result.setPageNo(pageResult.getPageNo());
		result.setPageSize(pageResult.getPageSize());
		result.setTotalSize(pageResult.getTotalSize());
		return result;
	}

	@Override
	public PageResult<SoChildDTO> getSupplierSoChildAllList(Integer merchantId, String soChildCode, List<Long> userIds, List<Long> puIds, Date soCreateTimeStart, Date soCreateTimeEnd, Integer soType, Integer soChildDeliveryStatus, Integer soConfirmStatus,
															Date sendTimeStart, Date sendTimeEnd, Boolean showTest, Long platformId, List<Long> testCompanyIds,
															Pagination page,Long supplierId,Integer orderPayStatus,Integer auditStatus) {
		PageResult<SoChildPO> pageResult=soChildReadManage.getSupplierSoChildAllList(merchantId,soChildCode, userIds, puIds,
				soCreateTimeStart, soCreateTimeEnd,soType, soChildDeliveryStatus, soConfirmStatus,
				sendTimeStart, sendTimeEnd, showTest, platformId, testCompanyIds,page,supplierId,orderPayStatus,auditStatus);
		PageResult<SoChildDTO> result = new PageResult<SoChildDTO>();
		if(EmptyUtil.isNotEmpty(pageResult.getList())){
			List<SoChildDTO> list = SoChildConverter.toDTO(pageResult.getList());
			result.setList(list);
		}else{
			result.setList(null);
		}
		result.setPageNo(pageResult.getPageNo());
		result.setPageSize(pageResult.getPageSize());
		result.setTotalSize(pageResult.getTotalSize());
		return result;
	}
	@Override
	public PageResult<SoChildDTO> findMerchantChildOrderOfPage(SoChildDTO dto, Pagination page) {
		PageResult<SoChildCondition> pageResult = soChildReadManage.findMerchantChildOrderOfPage(SoChildConverter.toCondition(dto), page);
		PageResult<SoChildDTO> result = new PageResult<SoChildDTO>();
		if(EmptyUtil.isNotEmpty(pageResult.getList())){
			List<SoChildDTO> list = SoChildConverter.conditionToDTO(pageResult.getList());
			result.setList(list);
		}else{
			result.setList(new ArrayList<SoChildDTO>());
		}
		result.setPageNo(pageResult.getPageNo());
		result.setPageSize(pageResult.getPageSize());
		result.setTotalSize(pageResult.getTotalSize());
		return result;
	}

	@Override
	public List<SoChildDTO> findWarningChildOrder(Date warningDate) {
		return SoChildConverter.conditionToDTO(soChildReadManage.findWarningChildOrder(warningDate));
	}

	@Override
	public Long findSoChildIdByThirdpartyCode(String thirdpartyCode) {
		return soChildReadManage.findSoChildIdByThirdpartyCode(thirdpartyCode);
	}

	@Override
	public Long findSoChildIdByThirdpartyId(Long jdOrderId) {
		return soChildReadManage.findSoChildIdByThirdpartyId(jdOrderId);
	}

	@Override
	public List<SoChildDTO> findSoChildListByMerchantId(List<Long> idList, long merchantId) {
		return SoChildConverter.conditionToDTO(soChildReadManage.findSoChildListByMerchantId(idList,merchantId));
	}

	@Override
	public List<SoChildDTO> findSoChildListBySupplierId(List<Long> idList, long supplierId) {
		return SoChildConverter.conditionToDTO(soChildReadManage.findSoChildListBySupplierId(idList,supplierId));
	}

	@Override
	public List<SoChildDTO> findSoChildByIdList(List<Long> idList){
		if(CollectionUtils.isEmpty(idList)){
			return null;
		}
		Long[] idArray = idList.stream().toArray(Long[]::new);
		return findSoChildByIds(idArray,null);
	}

	@Override
	public List<OrderSortExportVO> soDetailOrderExport(List<Long> soIdList) {
		return soChildReadManage.soDetailOrderExport(soIdList);
	}

	@Override
	public List<SoChildDTO> getSoChildAllListToExport(Integer merchantId, String soChildCode, List<Long> userIds, List<Long> puIds, Date soCreateTimeStart, Date soCreateTimeEnd, Integer soType, Integer soChildDeliveryStatus, Integer soConfirmStatus,
													Date sendTimeStart, Date sendTimeEnd, Boolean showTest, Long platformId, List<Long> testCompanyIds, Pagination page,Integer orderPayStatus,Integer auditStatus) {
		List<SoChildPO> pageResult=soChildReadManage.getSoChildAllListToExport(merchantId,soChildCode, userIds, puIds,
				soCreateTimeStart, soCreateTimeEnd,soType, soChildDeliveryStatus, soConfirmStatus, sendTimeStart,
				sendTimeEnd, showTest, platformId, testCompanyIds,page,orderPayStatus,auditStatus);

		return SoChildConverter.toDTO(pageResult);
	}

	@Override
	public List<SoChildDTO> getSupplierSoChildAllListToExport(Integer merchantId, String soChildCode, List<Long> userIds, List<Long> puIds, Date soCreateTimeStart, Date soCreateTimeEnd, Integer soType, Integer soChildDeliveryStatus, Integer soConfirmStatus,
															Date sendTimeStart, Date sendTimeEnd, Boolean showTest, Long platformId, List<Long> testCompanyIds,
															Pagination page,Long supplierId,Integer orderPayStatus,Integer auditStatus) {
		List<SoChildPO> pageResult=soChildReadManage.getSupplierSoChildAllListToExport(merchantId,soChildCode, userIds, puIds,
				soCreateTimeStart, soCreateTimeEnd,soType, soChildDeliveryStatus, soConfirmStatus,
				sendTimeStart, sendTimeEnd, showTest, platformId, testCompanyIds,page,supplierId,orderPayStatus,auditStatus);

		return  SoChildConverter.toDTO(pageResult);
	}
}
