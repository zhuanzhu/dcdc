package com.egeo.components.stock.po;


import java.util.Date;

/**
 * 
 * @author min
 * @date 2018-09-13 16:29:10
 */
public class StoreStockChangeApplyPO {


	private Long id;

	/**
	 * 门店puId
	 */
	private Long storeProductUnitId;	

	/**
	 * 库存变化值
	 */
	private Long stockChange;	

	/**
	 * 库存变化之前的值
	 */
	private Long beforeChangeValue;	

	/**
	 * 库存变化之后的值
	 */
	private Long afterChangeValue;	

	/**
	 * 申请原因id
	 */
	private Long applyCauseId;	

	/**
	 * 具体原因
	 */
	private String concretenessCause;	

	/**
	 * 申请用户id
	 */
	private Long afterUserId;	

	/**
	 * 申请用户名称
	 */
	private String afterUserName;	

	/**
	 * 申请门店id
	 */
	private Long afterStoreId;	

	/**
	 * 申请门店名称
	 */
	private String afterStoreName;	

	/**
	 * 申请时间:创建记录时数据库会自动set值
	 */
	private Date afterTime;	

	/**
	 * 操作用户id
	 */
	private Long operationUserId;	

	/**
	 * 操作用户名称
	 */
	private String operationUserName;	

	/**
	 * 操作时间:更新时数据库会自动set值
	 */
	private Date operationTime;	

	/**
	 * 是否同意：0：初始状态 1：同意 2：拒绝
	 */
	private Integer isConsent;	

	/**
	 * 平台id
	 */
	private Long platformId;	

	/**
	 * pu商品名称
	 */
	private String commodityProductUnitName;	

	/**
	 * pu编号
	 */
	private String productUnitSerialNumber;	

	/**
	 * 类型：1 后台申请 2 前台app申请
	 */
	private Integer type;	
	/**
	 * 申请时间开始
	 */
	private Date afterTimeStart;
	/**
	 * 申请时间停止
	 */
	private Date afterTimeStop;

	public Date getAfterTimeStart() {
		return afterTimeStart;
	}

	public void setAfterTimeStart(Date afterTimeStart) {
		this.afterTimeStart = afterTimeStart;
	}

	public Date getAfterTimeStop() {
		return afterTimeStop;
	}

	public void setAfterTimeStop(Date afterTimeStop) {
		this.afterTimeStop = afterTimeStop;
	}

	public Long getId() {
		return id;
	}

	/**
	 * 
	 * @param id 
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 门店puId
	 * @return 门店puId
	 */
	public Long getStoreProductUnitId() {
		return storeProductUnitId;
	}

	/**
	 * 门店puId
	 * @param storeProductUnitId 门店puId
	 */
	public void setStoreProductUnitId(Long storeProductUnitId) {
		this.storeProductUnitId = storeProductUnitId;
	}

	/**
	 * 库存变化值
	 * @return 库存变化值
	 */
	public Long getStockChange() {
		return stockChange;
	}

	/**
	 * 库存变化值
	 * @param stockChange 库存变化值
	 */
	public void setStockChange(Long stockChange) {
		this.stockChange = stockChange;
	}

	/**
	 * 库存变化之前的值
	 * @return 库存变化之前的值
	 */
	public Long getBeforeChangeValue() {
		return beforeChangeValue;
	}

	/**
	 * 库存变化之前的值
	 * @param beforeChangeValue 库存变化之前的值
	 */
	public void setBeforeChangeValue(Long beforeChangeValue) {
		this.beforeChangeValue = beforeChangeValue;
	}

	/**
	 * 库存变化之后的值
	 * @return 库存变化之后的值
	 */
	public Long getAfterChangeValue() {
		return afterChangeValue;
	}

	/**
	 * 库存变化之后的值
	 * @param afterChangeValue 库存变化之后的值
	 */
	public void setAfterChangeValue(Long afterChangeValue) {
		this.afterChangeValue = afterChangeValue;
	}

	/**
	 * 申请原因id
	 * @return 申请原因id
	 */
	public Long getApplyCauseId() {
		return applyCauseId;
	}

	/**
	 * 申请原因id
	 * @param applyCauseId 申请原因id
	 */
	public void setApplyCauseId(Long applyCauseId) {
		this.applyCauseId = applyCauseId;
	}

	/**
	 * 具体原因
	 * @return 具体原因
	 */
	public String getConcretenessCause() {
		return concretenessCause;
	}

	/**
	 * 具体原因
	 * @param concretenessCause 具体原因
	 */
	public void setConcretenessCause(String concretenessCause) {
		this.concretenessCause = concretenessCause;
	}

	/**
	 * 申请用户id
	 * @return 申请用户id
	 */
	public Long getAfterUserId() {
		return afterUserId;
	}

	/**
	 * 申请用户id
	 * @param afterUserId 申请用户id
	 */
	public void setAfterUserId(Long afterUserId) {
		this.afterUserId = afterUserId;
	}

	/**
	 * 申请用户名称
	 * @return 申请用户名称
	 */
	public String getAfterUserName() {
		return afterUserName;
	}

	/**
	 * 申请用户名称
	 * @param afterUserName 申请用户名称
	 */
	public void setAfterUserName(String afterUserName) {
		this.afterUserName = afterUserName;
	}

	/**
	 * 申请门店id
	 * @return 申请门店id
	 */
	public Long getAfterStoreId() {
		return afterStoreId;
	}

	/**
	 * 申请门店id
	 * @param afterStoreId 申请门店id
	 */
	public void setAfterStoreId(Long afterStoreId) {
		this.afterStoreId = afterStoreId;
	}

	/**
	 * 申请门店名称
	 * @return 申请门店名称
	 */
	public String getAfterStoreName() {
		return afterStoreName;
	}

	/**
	 * 申请门店名称
	 * @param afterStoreName 申请门店名称
	 */
	public void setAfterStoreName(String afterStoreName) {
		this.afterStoreName = afterStoreName;
	}

	/**
	 * 申请时间:创建记录时数据库会自动set值
	 * @return 申请时间:创建记录时数据库会自动set值
	 */
	public Date getAfterTime() {
		return afterTime;
	}

	/**
	 * 申请时间:创建记录时数据库会自动set值
	 * @param afterTime 申请时间:创建记录时数据库会自动set值
	 */
	public void setAfterTime(Date afterTime) {
		this.afterTime = afterTime;
	}

	/**
	 * 操作用户id
	 * @return 操作用户id
	 */
	public Long getOperationUserId() {
		return operationUserId;
	}

	/**
	 * 操作用户id
	 * @param operationUserId 操作用户id
	 */
	public void setOperationUserId(Long operationUserId) {
		this.operationUserId = operationUserId;
	}

	/**
	 * 操作用户名称
	 * @return 操作用户名称
	 */
	public String getOperationUserName() {
		return operationUserName;
	}

	/**
	 * 操作用户名称
	 * @param operationUserName 操作用户名称
	 */
	public void setOperationUserName(String operationUserName) {
		this.operationUserName = operationUserName;
	}

	/**
	 * 操作时间:更新时数据库会自动set值
	 * @return 操作时间:更新时数据库会自动set值
	 */
	public Date getOperationTime() {
		return operationTime;
	}

	/**
	 * 操作时间:更新时数据库会自动set值
	 * @param operationTime 操作时间:更新时数据库会自动set值
	 */
	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}

	/**
	 * 是否同意：0：初始状态 1：同意 2：拒绝
	 * @return 是否同意：0：初始状态 1：同意 2：拒绝
	 */
	public Integer getIsConsent() {
		return isConsent;
	}

	/**
	 * 是否同意：0：初始状态 1：同意 2：拒绝
	 * @param isConsent 是否同意：0：初始状态 1：同意 2：拒绝
	 */
	public void setIsConsent(Integer isConsent) {
		this.isConsent = isConsent;
	}

	/**
	 * 平台id
	 * @return 平台id
	 */
	public Long getPlatformId() {
		return platformId;
	}

	/**
	 * 平台id
	 * @param platformId 平台id
	 */
	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}

	/**
	 * pu商品名称
	 * @return pu商品名称
	 */
	public String getCommodityProductUnitName() {
		return commodityProductUnitName;
	}

	/**
	 * pu商品名称
	 * @param commodityProductUnitName pu商品名称
	 */
	public void setCommodityProductUnitName(String commodityProductUnitName) {
		this.commodityProductUnitName = commodityProductUnitName;
	}

	/**
	 * pu编号
	 * @return pu编号
	 */
	public String getProductUnitSerialNumber() {
		return productUnitSerialNumber;
	}

	/**
	 * pu编号
	 * @param productUnitSerialNumber pu编号
	 */
	public void setProductUnitSerialNumber(String productUnitSerialNumber) {
		this.productUnitSerialNumber = productUnitSerialNumber;
	}

	/**
	 * 类型：1 后台申请 2 前台app申请
	 * @return 类型：1 后台申请 2 前台app申请
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 类型：1 后台申请 2 前台app申请
	 * @param type 类型：1 后台申请 2 前台app申请
	 */
	public void setType(Integer type) {
		this.type = type;
	}
}
	