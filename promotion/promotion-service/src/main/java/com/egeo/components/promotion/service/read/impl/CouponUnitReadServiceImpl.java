package com.egeo.components.promotion.service.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.condition.CouponUnitCondition;
import com.egeo.components.promotion.converter.CouponUnitConverter;
import com.egeo.components.promotion.dto.CouponUnitDTO;
import com.egeo.components.promotion.manage.read.CouponReadManage;
import com.egeo.components.promotion.manage.read.CouponStoreReadManage;
import com.egeo.components.promotion.manage.read.CouponUnitReadManage;
import com.egeo.components.promotion.po.CouponPO;
import com.egeo.components.promotion.po.CouponStorePO;
import com.egeo.components.promotion.po.CouponUnitPO;
import com.egeo.components.promotion.service.read.CouponUnitReadService;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;

@Service("couponUnitReadService")
public class CouponUnitReadServiceImpl implements CouponUnitReadService {
	@Autowired
	private CouponUnitReadManage couponUnitReadManage;
	
	@Autowired
	private CouponReadManage couponReadManage;
	
	@Autowired
	private CouponStoreReadManage couponStoreReadManage;

	@Override
	public CouponUnitDTO findCouponUnitById(CouponUnitDTO dto) {
		CouponUnitPO po = CouponUnitConverter.toPO(dto);
		CouponUnitPO list = couponUnitReadManage.findCouponUnitById(po);
		CouponPO couponPO = new CouponPO();
		couponPO.setId(list.getCouponId());
		couponPO = couponReadManage.findCouponById(couponPO);
		CouponUnitDTO couponUnitDTO = CouponUnitConverter.toDTO(list);
		couponUnitDTO.setGoodsType(couponPO.getGoodsType());
		couponUnitDTO.setGoodsId(couponPO.getGoodsId());
		couponUnitDTO.setCouponType(couponPO.getCouponType());
		return couponUnitDTO;
	}

	@Override
	public PageResult<CouponUnitDTO> findCouponUnitOfPage(CouponUnitDTO dto, Pagination page) {
		CouponUnitPO po = CouponUnitConverter.toPO(dto);
		PageResult<CouponUnitPO> pageResult = couponUnitReadManage.findCouponUnitOfPage(po, page);

		List<CouponUnitDTO> list = CouponUnitConverter.toDTO(pageResult.getList());
		PageResult<CouponUnitDTO> result = new PageResult<CouponUnitDTO>();
		result.setList(list);
		result.setPageNo(pageResult.getPageNo());
		result.setPageSize(pageResult.getPageSize());
		result.setTotalSize(pageResult.getTotalSize());
		return result;
	}

	@Override
	public List<CouponUnitDTO> findCouponUnitAll(CouponUnitDTO dto) {
		CouponUnitPO po = CouponUnitConverter.toPO(dto);
		List<CouponUnitPO> list = couponUnitReadManage.findCouponUnitAll(po);
		return CouponUnitConverter.toDTO(list);
	}

	@Override
	public PageResult<CouponUnitDTO> findCouponUnitOfPageByBlurry(CouponUnitDTO dto, List<Long> userList,
			Pagination page) {
		// 查询优惠卷unit列表
		CouponUnitCondition po = CouponUnitConverter.dtoToCondition(dto);
		PageResult<CouponUnitCondition> pageResult = couponUnitReadManage.findCouponUnitOfPageByBlurry(po, userList,
				page);

		List<CouponUnitDTO> list = CouponUnitConverter.conditionToDTO(pageResult.getList());
		PageResult<CouponUnitDTO> result = new PageResult<CouponUnitDTO>();
		result.setList(list);
		result.setPageNo(pageResult.getPageNo());
		result.setPageSize(pageResult.getPageSize());
		result.setTotalSize(pageResult.getTotalSize());
		return result;
	}

	@Override
	public PageResult<CouponUnitDTO> findCouponUnitOfPageByUser(CouponUnitDTO dto, Pagination page) {
		CouponUnitCondition po = CouponUnitConverter.dtoToCondition(dto);
		PageResult<CouponUnitCondition> pageResult = couponUnitReadManage.findCouponUnitOfPageByUser(po, page);

		List<CouponUnitDTO> list = CouponUnitConverter.conditionToDTO(pageResult.getList());
		
		for (CouponUnitDTO cu : list) {
			CouponStorePO couponStorePO = new CouponStorePO();
			couponStorePO.setCouponId(cu.getCouponId());
			if(EmptyUtil.isNotEmpty(dto.getStoreId())){
				couponStorePO.setStoreId(dto.getStoreId());
				Long count = couponStoreReadManage.findCouponStoreCountByCouponIdAndStoreId(couponStorePO);
				if(count>0){
					List<Long> storeIdList = new ArrayList<>();
					storeIdList.add(dto.getStoreId());
					cu.setStoreIds(storeIdList);
				}else{
					cu.setStoreIds(new ArrayList<>());
				}
			}
		}
		
		PageResult<CouponUnitDTO> result = new PageResult<CouponUnitDTO>();
		result.setList(list);
		result.setPageNo(pageResult.getPageNo());
		result.setPageSize(pageResult.getPageSize());
		result.setTotalSize(pageResult.getTotalSize());
		return result;
	}

	private List<Long> getStoreIds(List<CouponStorePO> couponStoreList) {
		List<Long> storeIds = new ArrayList<>();
		if (EmptyUtil.isNotEmpty(couponStoreList)) {
			for (CouponStorePO cs : couponStoreList) {
				storeIds.add(cs.getStoreId());
			}
		}
		return storeIds;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public PageResult<CouponUnitDTO> findCouponUnitCenterOfPage(CouponUnitDTO dto, Long companyIdByType, Pagination page) {

		PageResult<CouponUnitCondition> pageResult = couponUnitReadManage
				.findCouponUnitCenterOfPage(CouponUnitConverter.dtoToCondition(dto), companyIdByType, page);

		List<CouponUnitDTO> list = CouponUnitConverter.conditionToDTO(pageResult.getList());

		for (CouponUnitDTO cu : list) {
			CouponStorePO couponStorePO = new CouponStorePO();
			couponStorePO.setCouponId(cu.getCouponId());
			if(EmptyUtil.isNotEmpty(dto.getStoreId())){
				couponStorePO.setStoreId(dto.getStoreId());
				Long count = couponStoreReadManage.findCouponStoreCountByCouponIdAndStoreId(couponStorePO);
				if(count>0){
					List<Long> storeIdList = new ArrayList<>();
					storeIdList.add(dto.getStoreId());
					cu.setStoreIds(storeIdList);
				}else{
					cu.setStoreIds(new ArrayList<>());
				}

			}
		}
		
		PageResult<CouponUnitDTO> result = new PageResult<CouponUnitDTO>();
		result.setList(list);
		result.setPageNo(pageResult.getPageNo());
		result.setPageSize(pageResult.getPageSize());
		result.setTotalSize(pageResult.getTotalSize());
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CouponUnitDTO> findSUCouponBatchOfPage(CouponUnitDTO dto, List<Long> goodIdList, Long companyIdByType, Pagination page) {
		List<CouponUnitCondition> pageResult = couponUnitReadManage
				.findSUCouponBatchOfPage(CouponUnitConverter.dtoToCondition(dto), goodIdList, companyIdByType, page);
		List<CouponUnitDTO> result=CouponUnitConverter.conditionToDTO(pageResult);
		for (CouponUnitDTO cu : result) {
			CouponStorePO couponStorePO = new CouponStorePO();
			couponStorePO.setCouponId(cu.getCouponId());
			List<CouponStorePO> couponStoreList = couponStoreReadManage.findCouponStoreAll(couponStorePO);
			cu.setStoreIds(getStoreIds(couponStoreList));
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CouponUnitDTO> findCouponUnitOfAllByUser(CouponUnitDTO dto) {
		List<CouponUnitCondition> pageResult = couponUnitReadManage
				.findCouponUnitOfAllByUser(CouponUnitConverter.dtoToCondition(dto));

		return CouponUnitConverter.conditionToDTO(pageResult);
	}

	@Override
	public Integer findCouponUnitByOrderId(Long orderId) {
		CouponUnitPO po = new CouponUnitPO();
		po.setOrderId(orderId);
		List<CouponUnitPO> list = couponUnitReadManage.findCouponUnitAll(po);
		CouponUnitDTO dto = null;

		if (EmptyUtil.isNotEmpty(list)) {
			dto = CouponUnitConverter.toDTO(list.get(0));
			CouponPO couponPO = new CouponPO();
			couponPO.setId(dto.getCouponId());
			couponPO = couponReadManage.findCouponById(couponPO);
			
			if (couponPO == null) {
				throw new BusinessException("优惠卷数据错误");
			}
			
			return couponPO.getCouponType();
		}

		// 未使用优惠卷
		return -1;
	}

	@Override
	public List<CouponUnitDTO> findCouponUnitListByBatchIdList(List<Long> couponBatchList) {
		return CouponUnitConverter.conditionToDTO(couponUnitReadManage.findCouponUnitListByBatchIdList(couponBatchList));
	}

	@Override
	public Long findCouponUnitAllCount(CouponUnitDTO couponUnitDTO) {
		return couponUnitReadManage.findCouponUnitAllCount(CouponUnitConverter.toPO(couponUnitDTO));
	}

	@Override
	public List<CouponUnitDTO> findCouponUnitAllByCouponUnitCode(String oldUnitCode) {
		CouponUnitPO couponUnitPO = new CouponUnitPO();
		couponUnitPO.setCouponUnitCode(oldUnitCode);
		return CouponUnitConverter.toDTO(couponUnitReadManage.findCouponUnitAll(couponUnitPO));
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<CouponUnitDTO> findCouponUnitAndBatchExchange(CouponUnitDTO dto, Long companyIdByType) {
		List<CouponUnitCondition> result= couponUnitReadManage
				.findCouponUnitAndBatchExchange(CouponUnitConverter.dtoToCondition(dto), companyIdByType);

		List<CouponUnitDTO> list = CouponUnitConverter.conditionToDTO(result);

		for (CouponUnitDTO cu : list) {
			CouponStorePO couponStorePO = new CouponStorePO();
			couponStorePO.setCouponId(cu.getCouponId());
			List<CouponStorePO> couponStoreList = couponStoreReadManage.findCouponStoreAll(couponStorePO);
			cu.setStoreIds(getStoreIds(couponStoreList));
		}
		return list;
	}

	@Override
	public Long findCouponUnitCountOfFreezeByParams(Long couponBatchId, Long startNum, Long endNum) {
		return couponUnitReadManage.findCouponUnitCountOfFreezeByParams(couponBatchId,startNum,endNum);
	}

	@Override
	public Integer countUnreadCouponUnit(Long userId, Integer couponType) {
		return couponUnitReadManage.countUnreadCouponUnit(userId, couponType);
	}
}
