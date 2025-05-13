package com.egeo.components.stock.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author min
 * @date 2018-05-17 17:00:02
 */
public class CommodityProductUnitStockRunningWaterVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * pu商品id
	 */
	private Long commodityProductUnitId;
	/**
	 * pu编号
	 */
	private String productUnitSerialNumber;
	/**
	 * pu商品名称
	 */
	private String commodityProductUnitName;
	/**
	 * 操作前库存数量
	 */
	private Long preoperativeStockNum;
	/**
	 * 操作后的库存数量
	 */
	private Long operationBackStockNum;
	/**
	 * 库存变化
	 */
	private Long stockChange;
	/**
	 * 创建人ID
	 */
	private Long createUserid;
	/**
	 * 创建人姓名
	 */
	private String createUsername;
	/**
	 * 创建人IP
	 */
	private String createUserip;
	/**
	 * 创建人MAC地址
	 */
	private String createUsermac;
	/**
	 * 创建时间:创建记录时数据库会自动set值
	 */
	private Date createTime;
	/**
	 * 平台id
	 */
	private Long platformId;
	/**
	 * 操作前冻结库存数量
	 */
	private Long preoperativeRealStockNum;
	/**
	 * 操作后的冻结库存数量
	 */
	private Long operationBackRealStockNum;
	/**
	 * 库存流水类型:1提交订单 2支付 3 取消订单（未支付） 4 取消订单（已支付） 5 进货 6 出货
	 */
	private Integer type;
	/**
	 * 订单编号
	 */
	private String orderCode;

	public Long getId() {
		return id;
	}

	/**
	 * 主键
	 * @param id 主键
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * pu商品id
	 * @return pu商品id
	 */
	public Long getCommodityProductUnitId() {
		return commodityProductUnitId;
	}

	/**
	 * pu商品id
	 * @param commodityProductUnitId pu商品id
	 */
	public void setCommodityProductUnitId(Long commodityProductUnitId) {
		this.commodityProductUnitId = commodityProductUnitId;
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
	 * 操作前库存数量
	 * @return 操作前库存数量
	 */
	public Long getPreoperativeStockNum() {
		return preoperativeStockNum;
	}

	/**
	 * 操作前库存数量
	 * @param preoperativeStockNum 操作前库存数量
	 */
	public void setPreoperativeStockNum(Long preoperativeStockNum) {
		this.preoperativeStockNum = preoperativeStockNum;
	}	
	/**
	 * 操作后的库存数量
	 * @return 操作后的库存数量
	 */
	public Long getOperationBackStockNum() {
		return operationBackStockNum;
	}

	/**
	 * 操作后的库存数量
	 * @param operationBackStockNum 操作后的库存数量
	 */
	public void setOperationBackStockNum(Long operationBackStockNum) {
		this.operationBackStockNum = operationBackStockNum;
	}	
	/**
	 * 库存变化
	 * @return 库存变化
	 */
	public Long getStockChange() {
		return stockChange;
	}

	/**
	 * 库存变化
	 * @param stockChange 库存变化
	 */
	public void setStockChange(Long stockChange) {
		this.stockChange = stockChange;
	}	
	/**
	 * 创建人ID
	 * @return 创建人ID
	 */
	public Long getCreateUserid() {
		return createUserid;
	}

	/**
	 * 创建人ID
	 * @param createUserid 创建人ID
	 */
	public void setCreateUserid(Long createUserid) {
		this.createUserid = createUserid;
	}	
	/**
	 * 创建人姓名
	 * @return 创建人姓名
	 */
	public String getCreateUsername() {
		return createUsername;
	}

	/**
	 * 创建人姓名
	 * @param createUsername 创建人姓名
	 */
	public void setCreateUsername(String createUsername) {
		this.createUsername = createUsername;
	}	
	/**
	 * 创建人IP
	 * @return 创建人IP
	 */
	public String getCreateUserip() {
		return createUserip;
	}

	/**
	 * 创建人IP
	 * @param createUserip 创建人IP
	 */
	public void setCreateUserip(String createUserip) {
		this.createUserip = createUserip;
	}	
	/**
	 * 创建人MAC地址
	 * @return 创建人MAC地址
	 */
	public String getCreateUsermac() {
		return createUsermac;
	}

	/**
	 * 创建人MAC地址
	 * @param createUsermac 创建人MAC地址
	 */
	public void setCreateUsermac(String createUsermac) {
		this.createUsermac = createUsermac;
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
	 * 操作前冻结库存数量
	 * @return 操作前冻结库存数量
	 */
	public Long getPreoperativeRealStockNum() {
		return preoperativeRealStockNum;
	}

	/**
	 * 操作前冻结库存数量
	 * @param preoperativeRealStockNum 操作前冻结库存数量
	 */
	public void setPreoperativeRealStockNum(Long preoperativeRealStockNum) {
		this.preoperativeRealStockNum = preoperativeRealStockNum;
	}	
	/**
	 * 操作后的冻结库存数量
	 * @return 操作后的冻结库存数量
	 */
	public Long getOperationBackRealStockNum() {
		return operationBackRealStockNum;
	}

	/**
	 * 操作后的冻结库存数量
	 * @param operationBackRealStockNum 操作后的冻结库存数量
	 */
	public void setOperationBackRealStockNum(Long operationBackRealStockNum) {
		this.operationBackRealStockNum = operationBackRealStockNum;
	}	
	/**
	 * 库存流水类型:1提交订单 2支付 3 取消订单（未支付） 4 取消订单（已支付） 5 进货 6 出货
	 * @return 库存流水类型:1提交订单 2支付 3 取消订单（未支付） 4 取消订单（已支付） 5 进货 6 出货
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 库存流水类型:1提交订单 2支付 3 取消订单（未支付） 4 取消订单（已支付） 5 进货 6 出货
	 * @param type 库存流水类型:1提交订单 2支付 3 取消订单（未支付） 4 取消订单（已支付） 5 进货 6 出货
	 */
	public void setType(Integer type) {
		this.type = type;
	}	
	/**
	 * 订单编号
	 * @return 订单编号
	 */
	public String getOrderCode() {
		return orderCode;
	}

	/**
	 * 订单编号
	 * @param orderCode 订单编号
	 */
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}	
}
	