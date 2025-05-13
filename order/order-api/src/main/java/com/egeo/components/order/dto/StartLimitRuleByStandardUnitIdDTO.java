package com.egeo.components.order.dto;

import java.util.List;
import java.util.Map;

public class StartLimitRuleByStandardUnitIdDTO {
	private Long standardUnitId;
	private Long companyId;
	private Long companyAllId;
	private Long platformId;
	private Long userId;
	private Long storeId;
	private Map<Long, List<Long>> suCombMap;
	public StartLimitRuleByStandardUnitIdDTO() {
		
	}
	public StartLimitRuleByStandardUnitIdDTO(Long standardUnitId, Long companyId,Long companyAllId, Long platformId, Long userId,Long storeId, Map<Long, List<Long>> suCombMap){
		this.standardUnitId = standardUnitId;
		this.companyId = companyId;
		this.companyAllId = companyAllId;
		this.platformId = platformId;
		this.userId = userId;
		this.storeId = storeId;
		this.suCombMap = suCombMap;
	}
	public Long getStandardUnitId() {
		return standardUnitId;
	}
	public void setStandardUnitId(Long standardUnitId) {
		this.standardUnitId = standardUnitId;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public Long getCompanyAllId() {
		return companyAllId;
	}
	public void setCompanyAllId(Long companyAllId) {
		this.companyAllId = companyAllId;
	}
	public Long getPlatformId() {
		return platformId;
	}
	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getStoreId() {
		return storeId;
	}
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
	public Map<Long, List<Long>> getSuCombMap() {
		return suCombMap;
	}
	public void setSuCombMap(Map<Long, List<Long>> suCombMap) {
		this.suCombMap = suCombMap;
	}
}
