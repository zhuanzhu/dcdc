package com.egeo.components.stock.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author xiaping
 * @date 2017-08-12 11:44:55
 */
public class WmsWarehouseDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 仓库名称缩写
	 */
	private String shortName;	

	/**
	 * 仓库名称
	 */
	private String warehouseName;	

	/**
	 * 仓库描述信息
	 */
	private String warehouseDesc;	

	/**
	 * 仓库类型 0：普通仓 1：生鲜仓库 2：云仓库
	 */
	private Integer warehouseType;	

	/**
	 * 仓库状态 0-无效，1-有效，表示仓库是否在使用
	 */
	private Integer warehousestatus;	

	/**
	 * 是否实体仓库 0：否 1：是
	 */
	private Integer isRealWarehouse;	

	/**
	 * 存储(保温)类型  0:常温 1:冷藏 2:冷冻
	 */
	private Integer storageType;	

	/**
	 * 功能类型 0:存储+销售 1:仅存储不销售
	 */
	private Integer functionType;	

	/**
	 * 是否支持调拨出库 默认0 否,1 是,
	 */
	private Integer isSupportTran;	

	/**
	 * 是否提供SBY服务 0:不提供 1:提供
	 */
	private Integer isSupportSby;	

	/**
	 * 比重
	 */
	private BigDecimal flex;	

	/**
	 * 仓库类型 0、仓库 1、门店
	 */
	private Integer stockType;	

	/**
	 * 是否OEM仓库 0:非OEM 1:是OEM
	 */
	private Integer isOem;	

	/**
	 * 营业时间
	 */
	private String businessHours;	

	/**
	 * 截单时间
	 */
	private String cutOffTime;	

	/**
	 * 国家ID
	 */
	private Long countryId;	

	/**
	 * 省份ID
	 */
	private Long provinceId;	

	/**
	 * 城市ID
	 */
	private Long cityId;	

	/**
	 * 区县ID
	 */
	private Long countyId;	

	/**
	 * 地址（交货地址）
	 */
	private String addressName;	

	/**
	 * 经度
	 */
	private String warehouseLongitude;	

	/**
	 * 纬度
	 */
	private String warehouseLatitude;	

	/**
	 * 仓库群组email
	 */
	private String warehouseGroupEmail;	

	/**
	 * 联系电话 0.覆盖全国 1.覆盖省份 2:覆盖市 3：覆盖区/县 4:覆盖区域
	 */
	private String warehousePhone;	

	/**
	 * 传真
	 */
	private String warehouseFax;	

	/**
	 * 联系人
	 */
	private String warehouseContactor;	

	/**
	 * 手机
	 */
	private String warehouseContactorMobile;	

	/**
	 * RTV默认快递商
	 */
	private String rtvDefaultExpress;	

	/**
	 * RTV供应商提货地址
	 */
	private String rtvSpReceiveAddress;	

	/**
	 * 退货仓库描述备注
	 */
	private String expressRemark;	

	/**
	 * 退货负责人
	 */
	private String returnDutyPerson;	

	/**
	 * 退货联系电话
	 */
	private String returnPhone;	

	/**
	 * 退货联系手机
	 */
	private String returnMobile;	

	/**
	 * 客户退货联系人
	 */
	private String doReturnDutyPerson;	

	/**
	 * 客户退货联系人电话
	 */
	private String doReturnPhone;	

	/**
	 * 客户退货联系人手机
	 */
	private String doReturnMobile;	

	/**
	 * 顾客退货寄件地址
	 */
	private String customerReturnAddress;	

	/**
	 * 备注
	 */
	private String warehouseReamrk;	

	/**
	 * 平台id
	 */
	private Long platformId;	

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 */
	private Date createTime;	

	/**
	 * 修改时间:更新时数据库会自动set值
	 */
	private Date updateTime;	

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
	 * 仓库名称缩写
	 * @return 仓库名称缩写
	 */
	public String getShortName() {
		return shortName;
	}

	/**
	 * 仓库名称缩写
	 * @param shortName 仓库名称缩写
	 */
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	/**
	 * 仓库名称
	 * @return 仓库名称
	 */
	public String getWarehouseName() {
		return warehouseName;
	}

	/**
	 * 仓库名称
	 * @param warehouseName 仓库名称
	 */
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
	/**
	 * 仓库描述信息
	 * @return 仓库描述信息
	 */
	public String getWarehouseDesc() {
		return warehouseDesc;
	}

	/**
	 * 仓库描述信息
	 * @param warehouseDesc 仓库描述信息
	 */
	public void setWarehouseDesc(String warehouseDesc) {
		this.warehouseDesc = warehouseDesc;
	}
	/**
	 * 仓库类型 0：普通仓 1：生鲜仓库 2：云仓库
	 * @return 仓库类型 0：普通仓 1：生鲜仓库 2：云仓库
	 */
	public Integer getWarehouseType() {
		return warehouseType;
	}

	/**
	 * 仓库类型 0：普通仓 1：生鲜仓库 2：云仓库
	 * @param warehouseType 仓库类型 0：普通仓 1：生鲜仓库 2：云仓库
	 */
	public void setWarehouseType(Integer warehouseType) {
		this.warehouseType = warehouseType;
	}
	/**
	 * 仓库状态 0-无效，1-有效，表示仓库是否在使用
	 * @return 仓库状态 0-无效，1-有效，表示仓库是否在使用
	 */
	public Integer getWarehousestatus() {
		return warehousestatus;
	}

	/**
	 * 仓库状态 0-无效，1-有效，表示仓库是否在使用
	 * @param warehousestatus 仓库状态 0-无效，1-有效，表示仓库是否在使用
	 */
	public void setWarehousestatus(Integer warehousestatus) {
		this.warehousestatus = warehousestatus;
	}
	/**
	 * 是否实体仓库 0：否 1：是
	 * @return 是否实体仓库 0：否 1：是
	 */
	public Integer getIsRealWarehouse() {
		return isRealWarehouse;
	}

	/**
	 * 是否实体仓库 0：否 1：是
	 * @param isRealWarehouse 是否实体仓库 0：否 1：是
	 */
	public void setIsRealWarehouse(Integer isRealWarehouse) {
		this.isRealWarehouse = isRealWarehouse;
	}
	/**
	 * 存储(保温)类型  0:常温 1:冷藏 2:冷冻
	 * @return 存储(保温)类型  0:常温 1:冷藏 2:冷冻
	 */
	public Integer getStorageType() {
		return storageType;
	}

	/**
	 * 存储(保温)类型  0:常温 1:冷藏 2:冷冻
	 * @param storageType 存储(保温)类型  0:常温 1:冷藏 2:冷冻
	 */
	public void setStorageType(Integer storageType) {
		this.storageType = storageType;
	}
	/**
	 * 功能类型 0:存储+销售 1:仅存储不销售
	 * @return 功能类型 0:存储+销售 1:仅存储不销售
	 */
	public Integer getFunctionType() {
		return functionType;
	}

	/**
	 * 功能类型 0:存储+销售 1:仅存储不销售
	 * @param functionType 功能类型 0:存储+销售 1:仅存储不销售
	 */
	public void setFunctionType(Integer functionType) {
		this.functionType = functionType;
	}
	/**
	 * 是否支持调拨出库 默认0 否,1 是,
	 * @return 是否支持调拨出库 默认0 否,1 是,
	 */
	public Integer getIsSupportTran() {
		return isSupportTran;
	}

	/**
	 * 是否支持调拨出库 默认0 否,1 是,
	 * @param isSupportTran 是否支持调拨出库 默认0 否,1 是,
	 */
	public void setIsSupportTran(Integer isSupportTran) {
		this.isSupportTran = isSupportTran;
	}
	/**
	 * 是否提供SBY服务 0:不提供 1:提供
	 * @return 是否提供SBY服务 0:不提供 1:提供
	 */
	public Integer getIsSupportSby() {
		return isSupportSby;
	}

	/**
	 * 是否提供SBY服务 0:不提供 1:提供
	 * @param isSupportSby 是否提供SBY服务 0:不提供 1:提供
	 */
	public void setIsSupportSby(Integer isSupportSby) {
		this.isSupportSby = isSupportSby;
	}
	/**
	 * 比重
	 * @return 比重
	 */
	public BigDecimal getFlex() {
		return flex;
	}

	/**
	 * 比重
	 * @param flex 比重
	 */
	public void setFlex(BigDecimal flex) {
		this.flex = flex;
	}
	/**
	 * 仓库类型 0、仓库 1、门店
	 * @return 仓库类型 0、仓库 1、门店
	 */
	public Integer getStockType() {
		return stockType;
	}

	/**
	 * 仓库类型 0、仓库 1、门店
	 * @param stockType 仓库类型 0、仓库 1、门店
	 */
	public void setStockType(Integer stockType) {
		this.stockType = stockType;
	}
	/**
	 * 是否OEM仓库 0:非OEM 1:是OEM
	 * @return 是否OEM仓库 0:非OEM 1:是OEM
	 */
	public Integer getIsOem() {
		return isOem;
	}

	/**
	 * 是否OEM仓库 0:非OEM 1:是OEM
	 * @param isOem 是否OEM仓库 0:非OEM 1:是OEM
	 */
	public void setIsOem(Integer isOem) {
		this.isOem = isOem;
	}
	/**
	 * 营业时间
	 * @return 营业时间
	 */
	public String getBusinessHours() {
		return businessHours;
	}

	/**
	 * 营业时间
	 * @param businessHours 营业时间
	 */
	public void setBusinessHours(String businessHours) {
		this.businessHours = businessHours;
	}
	/**
	 * 截单时间
	 * @return 截单时间
	 */
	public String getCutOffTime() {
		return cutOffTime;
	}

	/**
	 * 截单时间
	 * @param cutOffTime 截单时间
	 */
	public void setCutOffTime(String cutOffTime) {
		this.cutOffTime = cutOffTime;
	}
	/**
	 * 国家ID
	 * @return 国家ID
	 */
	public Long getCountryId() {
		return countryId;
	}

	/**
	 * 国家ID
	 * @param countryId 国家ID
	 */
	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}
	/**
	 * 省份ID
	 * @return 省份ID
	 */
	public Long getProvinceId() {
		return provinceId;
	}

	/**
	 * 省份ID
	 * @param provinceId 省份ID
	 */
	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}
	/**
	 * 城市ID
	 * @return 城市ID
	 */
	public Long getCityId() {
		return cityId;
	}

	/**
	 * 城市ID
	 * @param cityId 城市ID
	 */
	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
	/**
	 * 区县ID
	 * @return 区县ID
	 */
	public Long getCountyId() {
		return countyId;
	}

	/**
	 * 区县ID
	 * @param countyId 区县ID
	 */
	public void setCountyId(Long countyId) {
		this.countyId = countyId;
	}
	/**
	 * 地址（交货地址）
	 * @return 地址（交货地址）
	 */
	public String getAddressName() {
		return addressName;
	}

	/**
	 * 地址（交货地址）
	 * @param addressName 地址（交货地址）
	 */
	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}
	/**
	 * 经度
	 * @return 经度
	 */
	public String getWarehouseLongitude() {
		return warehouseLongitude;
	}

	/**
	 * 经度
	 * @param warehouseLongitude 经度
	 */
	public void setWarehouseLongitude(String warehouseLongitude) {
		this.warehouseLongitude = warehouseLongitude;
	}
	/**
	 * 纬度
	 * @return 纬度
	 */
	public String getWarehouseLatitude() {
		return warehouseLatitude;
	}

	/**
	 * 纬度
	 * @param warehouseLatitude 纬度
	 */
	public void setWarehouseLatitude(String warehouseLatitude) {
		this.warehouseLatitude = warehouseLatitude;
	}
	/**
	 * 仓库群组email
	 * @return 仓库群组email
	 */
	public String getWarehouseGroupEmail() {
		return warehouseGroupEmail;
	}

	/**
	 * 仓库群组email
	 * @param warehouseGroupEmail 仓库群组email
	 */
	public void setWarehouseGroupEmail(String warehouseGroupEmail) {
		this.warehouseGroupEmail = warehouseGroupEmail;
	}
	/**
	 * 联系电话 0.覆盖全国 1.覆盖省份 2:覆盖市 3：覆盖区/县 4:覆盖区域
	 * @return 联系电话 0.覆盖全国 1.覆盖省份 2:覆盖市 3：覆盖区/县 4:覆盖区域
	 */
	public String getWarehousePhone() {
		return warehousePhone;
	}

	/**
	 * 联系电话 0.覆盖全国 1.覆盖省份 2:覆盖市 3：覆盖区/县 4:覆盖区域
	 * @param warehousePhone 联系电话 0.覆盖全国 1.覆盖省份 2:覆盖市 3：覆盖区/县 4:覆盖区域
	 */
	public void setWarehousePhone(String warehousePhone) {
		this.warehousePhone = warehousePhone;
	}
	/**
	 * 传真
	 * @return 传真
	 */
	public String getWarehouseFax() {
		return warehouseFax;
	}

	/**
	 * 传真
	 * @param warehouseFax 传真
	 */
	public void setWarehouseFax(String warehouseFax) {
		this.warehouseFax = warehouseFax;
	}
	/**
	 * 联系人
	 * @return 联系人
	 */
	public String getWarehouseContactor() {
		return warehouseContactor;
	}

	/**
	 * 联系人
	 * @param warehouseContactor 联系人
	 */
	public void setWarehouseContactor(String warehouseContactor) {
		this.warehouseContactor = warehouseContactor;
	}
	/**
	 * 手机
	 * @return 手机
	 */
	public String getWarehouseContactorMobile() {
		return warehouseContactorMobile;
	}

	/**
	 * 手机
	 * @param warehouseContactorMobile 手机
	 */
	public void setWarehouseContactorMobile(String warehouseContactorMobile) {
		this.warehouseContactorMobile = warehouseContactorMobile;
	}
	/**
	 * RTV默认快递商
	 * @return RTV默认快递商
	 */
	public String getRtvDefaultExpress() {
		return rtvDefaultExpress;
	}

	/**
	 * RTV默认快递商
	 * @param rtvDefaultExpress RTV默认快递商
	 */
	public void setRtvDefaultExpress(String rtvDefaultExpress) {
		this.rtvDefaultExpress = rtvDefaultExpress;
	}
	/**
	 * RTV供应商提货地址
	 * @return RTV供应商提货地址
	 */
	public String getRtvSpReceiveAddress() {
		return rtvSpReceiveAddress;
	}

	/**
	 * RTV供应商提货地址
	 * @param rtvSpReceiveAddress RTV供应商提货地址
	 */
	public void setRtvSpReceiveAddress(String rtvSpReceiveAddress) {
		this.rtvSpReceiveAddress = rtvSpReceiveAddress;
	}
	/**
	 * 退货仓库描述备注
	 * @return 退货仓库描述备注
	 */
	public String getExpressRemark() {
		return expressRemark;
	}

	/**
	 * 退货仓库描述备注
	 * @param expressRemark 退货仓库描述备注
	 */
	public void setExpressRemark(String expressRemark) {
		this.expressRemark = expressRemark;
	}
	/**
	 * 退货负责人
	 * @return 退货负责人
	 */
	public String getReturnDutyPerson() {
		return returnDutyPerson;
	}

	/**
	 * 退货负责人
	 * @param returnDutyPerson 退货负责人
	 */
	public void setReturnDutyPerson(String returnDutyPerson) {
		this.returnDutyPerson = returnDutyPerson;
	}
	/**
	 * 退货联系电话
	 * @return 退货联系电话
	 */
	public String getReturnPhone() {
		return returnPhone;
	}

	/**
	 * 退货联系电话
	 * @param returnPhone 退货联系电话
	 */
	public void setReturnPhone(String returnPhone) {
		this.returnPhone = returnPhone;
	}
	/**
	 * 退货联系手机
	 * @return 退货联系手机
	 */
	public String getReturnMobile() {
		return returnMobile;
	}

	/**
	 * 退货联系手机
	 * @param returnMobile 退货联系手机
	 */
	public void setReturnMobile(String returnMobile) {
		this.returnMobile = returnMobile;
	}
	/**
	 * 客户退货联系人
	 * @return 客户退货联系人
	 */
	public String getDoReturnDutyPerson() {
		return doReturnDutyPerson;
	}

	/**
	 * 客户退货联系人
	 * @param doReturnDutyPerson 客户退货联系人
	 */
	public void setDoReturnDutyPerson(String doReturnDutyPerson) {
		this.doReturnDutyPerson = doReturnDutyPerson;
	}
	/**
	 * 客户退货联系人电话
	 * @return 客户退货联系人电话
	 */
	public String getDoReturnPhone() {
		return doReturnPhone;
	}

	/**
	 * 客户退货联系人电话
	 * @param doReturnPhone 客户退货联系人电话
	 */
	public void setDoReturnPhone(String doReturnPhone) {
		this.doReturnPhone = doReturnPhone;
	}
	/**
	 * 客户退货联系人手机
	 * @return 客户退货联系人手机
	 */
	public String getDoReturnMobile() {
		return doReturnMobile;
	}

	/**
	 * 客户退货联系人手机
	 * @param doReturnMobile 客户退货联系人手机
	 */
	public void setDoReturnMobile(String doReturnMobile) {
		this.doReturnMobile = doReturnMobile;
	}
	/**
	 * 顾客退货寄件地址
	 * @return 顾客退货寄件地址
	 */
	public String getCustomerReturnAddress() {
		return customerReturnAddress;
	}

	/**
	 * 顾客退货寄件地址
	 * @param customerReturnAddress 顾客退货寄件地址
	 */
	public void setCustomerReturnAddress(String customerReturnAddress) {
		this.customerReturnAddress = customerReturnAddress;
	}
	/**
	 * 备注
	 * @return 备注
	 */
	public String getWarehouseReamrk() {
		return warehouseReamrk;
	}

	/**
	 * 备注
	 * @param warehouseReamrk 备注
	 */
	public void setWarehouseReamrk(String warehouseReamrk) {
		this.warehouseReamrk = warehouseReamrk;
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
	 * 创建时间:创建记录时数据库会自动set值
	 * @return 创建时间:创建记录时数据库会自动set值
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 * @param createTime 创建时间:创建记录时数据库会自动set值
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 修改时间:更新时数据库会自动set值
	 * @return 修改时间:更新时数据库会自动set值
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 修改时间:更新时数据库会自动set值
	 * @param updateTime 修改时间:更新时数据库会自动set值
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
	