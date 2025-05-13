package com.egeo.components.order.facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.order.dto.LimitRuleDTO;
import com.egeo.components.order.dto.LimitRuleRecordDTO;
import com.egeo.components.order.dto.LimitRuleStoreDTO;
import com.egeo.components.order.dto.LimitRuleUserDTO;
import com.egeo.components.order.service.read.LimitRuleCompanyReadService;
import com.egeo.components.order.service.read.LimitRuleReadService;
import com.egeo.components.order.service.read.LimitRuleStoreReadService;
import com.egeo.components.order.service.read.LimitRuleUserReadService;
import com.egeo.components.order.service.write.LimitRuleWriteService;
import com.egeo.components.product.client.StandardUnitClient;
import com.egeo.components.product.client.StandardUnitCombinationClient;
import com.egeo.components.product.dto.StandardUnitCombinationDTO;
import com.egeo.components.product.dto.StandardUnitDTO;
import com.egeo.components.user.client.CompanyCoreClient;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;


@Component
public class LimitRuleFacade {
	
	@Resource
	private LimitRuleReadService limitRuleReadService;
	
	@Resource
	private LimitRuleWriteService limitRuleWriteService;
	
	@Resource
	private LimitRuleCompanyReadService limitRuleCompanyReadService;
	
	@Autowired
	private StandardUnitClient standardUnitReadService;
	
	@Autowired
	private CompanyCoreClient companyCoreReadService;
	
	@Resource
	private LimitRuleUserReadService limitRuleUserReadService;
	
	@Resource
	private LimitRuleStoreReadService limitRuleStoreReadService;
	
	@Autowired
	private StandardUnitCombinationClient standardUnitCombinationReadService;
	
	public LimitRuleDTO findLimitRuleById(LimitRuleDTO dto){
		
		return limitRuleReadService.findLimitRuleById(dto);
	}

	public PageResult<LimitRuleDTO> findLimitRuleOfPage(LimitRuleDTO dto,Pagination page){
		
		return limitRuleReadService.findLimitRuleOfPage(dto, page);
		
	}

	public List<LimitRuleDTO> findLimitRuleAll(LimitRuleDTO dto){
		
		return limitRuleReadService.findLimitRuleAll(dto);
		
	}

	public Long insertLimitRuleWithTx(LimitRuleDTO dto, List<Long> companyIdList, List<Long> userCompanyIdList, List<Long> storeIdList){
		return limitRuleWriteService.insertLimitRuleWithTx(dto, companyIdList, userCompanyIdList, storeIdList);
	}
	/**
	 * 根据商品集合和限购规则信息创建限购规则信息集合
	 * @param standardUnitList
	 * @param dto
	 * @param limitRuleList
	 */
	private void saveLimitRuleList(List<StandardUnitDTO> standardUnitList, LimitRuleDTO dto,
			List<LimitRuleDTO> limitRuleList) {
		for (StandardUnitDTO standardUnitDTO : standardUnitList) {
			LimitRuleDTO tar = new LimitRuleDTO();
			tar.setType(dto.getType());	
			tar.setName(dto.getName());	
			tar.setIsStart(dto.getIsStart());	
			tar.setIsLimit(dto.getIsLimit());	
			tar.setSuLimitNum(dto.getSuLimitNum());	
			tar.setLimitTimeType(dto.getLimitTimeType());	
			tar.setLimitOriginTime(dto.getLimitOriginTime());	
			tar.setLimitStopTime(dto.getLimitStopTime());	
			tar.setPeriodType(dto.getPeriodType());	
			tar.setLimitUnit(dto.getLimitUnit());	
			tar.setUserLimitNum(dto.getUserLimitNum());	
			tar.setUserMoneySum(dto.getUserMoneySum());	
			tar.setCreateUserid(dto.getCreateUserid());	
			tar.setCreateUsername(dto.getCreateUsername());	
			tar.setCreateUserip(dto.getCreateUserip());	
			tar.setCreateUsermac(dto.getCreateUsermac());	
			tar.setCreateTime(dto.getCreateTime());	
			tar.setUpdateUserid(dto.getUpdateUserid());	
			tar.setUpdateUsername(dto.getUpdateUsername());	
			tar.setUpdateUserip(dto.getUpdateUserip());	
			tar.setUpdateUsermac(dto.getUpdateUsermac());	
			tar.setUpdateTime(dto.getUpdateTime());	
			tar.setPlatformId(dto.getPlatformId());	
			tar.setStandardUnitId(standardUnitDTO.getId());
			tar.setStandardUnitSerialNumber(standardUnitDTO.getMerchantProductSerialNumber());
			limitRuleList.add(tar);
		}
		
	}

	public int updateLimitRuleWithTx(LimitRuleDTO dto, List<Long> companyIdList, List<Long> userCompanyIdList, List<Long> storeIdList){
		return limitRuleWriteService.updateLimitRuleWithTx(dto, companyIdList, userCompanyIdList, storeIdList);
	}

	public int deleteLimitRuleWithTx(LimitRuleDTO dto){
		
		return limitRuleWriteService.deleteLimitRuleWithTx(dto);
		
	}
	/**
	 * 根据限购规则id查询限购规则与公司关系
	 * @param limitRuleId
	 * @return
	 */
	public List<Long> findCompanyByLimitRuleId(Long limitRuleId) {
		// TODO Auto-generated method stub
		return limitRuleCompanyReadService.findCompanyByLimitRuleId(limitRuleId);
	}
	/**
	 * 根据suid查询su信息
	 * @param standardUnitId
	 * @return
	 */
	public StandardUnitDTO standardUnitByStandardUnitId(Long standardUnitId) {
		StandardUnitDTO standardUnitDTO = new StandardUnitDTO();
		standardUnitDTO.setId(standardUnitId);
		return standardUnitReadService.findStandardUnitById(standardUnitDTO);
	}
	/**
	 * 根据限购规则id启用停用限购规则
	 * @param limitRuleId
	 * @param isStart
	 * @return
	 */
	public int isLimitRuleStartWithTx(Long limitRuleId, Integer isStart) {
		// TODO Auto-generated method stub
		return limitRuleWriteService.isLimitRuleStartWithTx(limitRuleId, isStart);
	}
	/**
	 * 根据su商品id,公司id，平台id查询所有启用限购规则，按创建时间排序
	 * @param standardUnitId
	 * @param companyId
	 * @param platformId
	 * @return
	 */
	public List<String> startLimitRuleByStandardUnitId(Long standardUnitId,Long companyId,Long platformId, Long userId, Long storeId) {
		Long companyAllId = companyCoreReadService.findCompanyAllIdByCompanyId(companyId);
		Map<Long, List<String>> suCombMapTmp = standardUnitReadService.findSuCombinationMap(standardUnitId, platformId);
		Iterator<Entry<Long, List<String>>> iter = suCombMapTmp.entrySet().iterator();
		Map<Long, List<Long>> suCombMap = new HashMap<Long, List<Long>>();
		while (iter.hasNext()) {
			Map.Entry<Long, List<String>> entry = iter.next();
			Long key = entry.getKey();
			List<String> val = entry.getValue();
			suCombMap.put(key, com.egeo.utils.StringUtils.stringsToLongs(val));
		}
		return limitRuleReadService.startLimitRuleByStandardUnitId(standardUnitId, companyId, companyAllId, platformId, userId, storeId, suCombMap);
	}

	public List<Long> findLimitRuleUserCompany(Long limitRuleId) {
		LimitRuleUserDTO dto = new LimitRuleUserDTO();
		dto.setLimitRuleId(limitRuleId);
		List<LimitRuleUserDTO> limitRuleUserDTOList = limitRuleUserReadService.findLimitRuleUserAll(dto);
		List<Long> idList = new ArrayList<Long>();
		if (EmptyUtil.isNotEmpty(limitRuleUserDTOList)) {
			for (LimitRuleUserDTO lru : limitRuleUserDTOList) {
				idList.add(lru.getCompanyId());
			}
		}
		return idList;
	}
	
	public List<Long> findLimitRuleStore(Long limitRuleId) {
		LimitRuleStoreDTO dto = new LimitRuleStoreDTO();
		dto.setLimitRuleId(limitRuleId);
		List<LimitRuleStoreDTO> limitRuleStoreDTOList = limitRuleStoreReadService.findLimitRuleStoreAll(dto);
		List<Long> idList = new ArrayList<Long>();
		if (EmptyUtil.isNotEmpty(limitRuleStoreDTOList)) {
			for (LimitRuleStoreDTO lru : limitRuleStoreDTOList) {
				idList.add(lru.getStoreId());
			}
		}
		return idList;
	}
	
	public StandardUnitCombinationDTO findStandardUnitCombinationById(Long standardCombinationUnitId) {
		StandardUnitCombinationDTO dto = new StandardUnitCombinationDTO();
		dto.setId(standardCombinationUnitId);
		return standardUnitCombinationReadService.findStandardUnitCombinationById(dto);
	}
	
	/**
	 * 查询限购规则已购买量
	 * @param limitRuleId
	 * @param standardUnitId
	 * @param standardUnitIdList
	 * @param userId
	 * @param storeId
	 * @param companyId
	 * @param periodType
	 * @return
	 */
	public LimitRuleRecordDTO selectLimitStatistic(Integer orderStatus,Long limitRuleId, Long standardUnitId,
			List<Long> standardUnitIdList, Long userId, Long storeId, Long companyId, Integer periodType) {
		return limitRuleReadService.selectLimitStatistic(orderStatus,limitRuleId, standardUnitId, standardUnitIdList, userId, storeId, companyId, periodType);
	}
}
	