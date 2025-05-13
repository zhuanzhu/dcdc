package com.egeo.components.order.dto;

import com.egeo.components.order.vo.RefundVo;
import com.egeo.utils.EmptyUtil;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author wyy
 * @date 2018-07-19 17:21:52
 */
public class SoRefundDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 退款单编号
	 */
	private String soRefundCode;

	/**
	 * 订单id
	 */
	private Long soId;

	/**
	 * 操作人id
	 */
	private Long creatorId;

	/**
	 * 订单积分退款批次id
	 */
	private Long fubiAccountBatchId;

	/**
	 * 订单现金退款批次id
	 */
	private Long cashAccountBatchId;

	/**
	 * 订单积点退款批次id
	 */
	private Long jidianAccountBatchId;

	/**
	 * 订单卡劵退款批次id
	 */
	private Long buyCardAccountBatchId;

	/**
	 * 积分退款金额
	 */
	private BigDecimal soRefundByFubi;

	/**
	 * 现金退款金额
	 */
	private BigDecimal soRefundByCash;

	/**
	 * 积点退款金额
	 */
	private BigDecimal soRefundByJidian;

	/**
	 * 卡劵退款金额
	 */
	private BigDecimal soRefundByBuyCard;

	/**
	 * 退款原因
	 */
	private String soRefundReason;

	/**
	 * 退款单备注
	 */
	private String remark;

	/**
	 * 更新时间
	 */
	private Date updateTime;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 用户id
	 */
	private Long userId;
	/**
	 * 平台id
	 */
	private Long platformId;

	/**
	 * 第三方退款单号
	 */
	private String thirdRefundCode;

	/**
	 * 业务id
	 */
	private String bizId;

	private List<SoRefundItemDTO> soRefundItemDTOS;

	/**
	 * 退款状态：默认为1已确认 0未确认
	 */
	private Integer refundState;

	public Long getPlatformId() {
		return platformId;
	}

	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
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
	 * 退款单编号
	 * @return 退款单编号
	 */
	public String getSoRefundCode() {
		return soRefundCode;
	}

	/**
	 * 退款单编号
	 * @param soRefundCode 退款单编号
	 */
	public void setSoRefundCode(String soRefundCode) {
		this.soRefundCode = soRefundCode;
	}
	/**
	 * 订单id
	 * @return 订单id
	 */
	public Long getSoId() {
		return soId;
	}

	/**
	 * 订单id
	 * @param soId 订单id
	 */
	public void setSoId(Long soId) {
		this.soId = soId;
	}
	/**
	 * 操作人id
	 * @return 操作人id
	 */
	public Long getCreatorId() {
		return creatorId;
	}

	/**
	 * 操作人id
	 * @param creatorId 操作人id
	 */
	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}
	/**
	 * 订单积分退款批次id
	 * @return 订单积分退款批次id
	 */
	public Long getFubiAccountBatchId() {
		return fubiAccountBatchId;
	}

	/**
	 * 订单积分退款批次id
	 * @param fubiAccountBatchId 订单积分退款批次id
	 */
	public void setFubiAccountBatchId(Long fubiAccountBatchId) {
		this.fubiAccountBatchId = fubiAccountBatchId;
	}
	/**
	 * 订单现金退款批次id
	 * @return 订单现金退款批次id
	 */
	public Long getCashAccountBatchId() {
		return cashAccountBatchId;
	}

	/**
	 * 订单现金退款批次id
	 * @param cashAccountBatchId 订单现金退款批次id
	 */
	public void setCashAccountBatchId(Long cashAccountBatchId) {
		this.cashAccountBatchId = cashAccountBatchId;
	}
	/**
	 * 积分退款金额
	 * @return 积分退款金额
	 */
	public BigDecimal getSoRefundByFubi() {
		return soRefundByFubi;
	}

	/**
	 * 积分退款金额
	 * @param soRefundByFubi 积分退款金额
	 */
	public void setSoRefundByFubi(BigDecimal soRefundByFubi) {
		this.soRefundByFubi = soRefundByFubi;
	}
	/**
	 * 现金退款金额
	 * @return 现金退款金额
	 */
	public BigDecimal getSoRefundByCash() {
		return soRefundByCash;
	}

	/**
	 * 现金退款金额
	 * @param soRefundByCash 现金退款金额
	 */
	public void setSoRefundByCash(BigDecimal soRefundByCash) {
		this.soRefundByCash = soRefundByCash;
	}
	/**
	 * 退款原因
	 * @return 退款原因
	 */
	public String getSoRefundReason() {
		return soRefundReason;
	}

	/**
	 * 退款原因
	 * @param soRefundReason 退款原因
	 */
	public void setSoRefundReason(String soRefundReason) {
		this.soRefundReason = soRefundReason;
	}
	/**
	 * 退款单备注
	 * @return 退款单备注
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 退款单备注
	 * @param remark 退款单备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 更新时间
	 * @return 更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 更新时间
	 * @param updateTime 更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 创建时间
	 * @return 创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间
	 * @param createTime 创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getThirdRefundCode() {
		return thirdRefundCode;
	}

	public void setThirdRefundCode(String thirdRefundCode) {
		this.thirdRefundCode = thirdRefundCode;
	}

	public String getBizId() {
		return bizId;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
	}

	public List<SoRefundItemDTO> getSoRefundItemDTOS() {
		return soRefundItemDTOS;
	}

	public void setSoRefundItemDTOS(List<SoRefundItemDTO> soRefundItemDTOS) {
		this.soRefundItemDTOS = soRefundItemDTOS;
	}

	public Long getJidianAccountBatchId() {
		return jidianAccountBatchId;
	}

	public void setJidianAccountBatchId(Long jidianAccountBatchId) {
		this.jidianAccountBatchId = jidianAccountBatchId;
	}

	public void transferByVo(RefundVo refundVo){
		if (Objects.isNull(refundVo)){
			return;
		}
		this.setBizId(Objects.nonNull(refundVo.getChildId())?String.valueOf(refundVo.getChildId()):null);
		if (EmptyUtil.isNotEmpty(refundVo.getRefundItemVos())){
			List<SoRefundItemDTO> refundmentDTOList=new ArrayList<>();
			refundVo.getRefundItemVos().forEach(item->{
				SoRefundItemDTO itemDTO=new SoRefundItemDTO();
				itemDTO.setSoItemId(item.getSoItemId());
				itemDTO.setSkuId(item.getSkuId());
				itemDTO.setSkuName(item.getSkuName());
				itemDTO.setRefundNum(item.getRefundNum());
				itemDTO.setPrice(item.getPrice());
				itemDTO.setRefundAmount(item.getRefundAmount());
				itemDTO.setRefundDeliveryFee(item.getRefundDeliveryFee());
				itemDTO.setSource(item.getSource());
				itemDTO.setPlatformId(item.getPlatformId());
				refundmentDTOList.add(itemDTO);
			});
			this.setSoRefundItemDTOS(refundmentDTOList);
		}
	}

	public BigDecimal getSoRefundByJidian() {
		return soRefundByJidian;
	}

	public void setSoRefundByJidian(BigDecimal soRefundByJidian) {
		this.soRefundByJidian = soRefundByJidian;
	}

	public Integer getRefundState() {
		return refundState;
	}

	public void setRefundState(Integer refundState) {
		this.refundState = refundState;
	}

	public Long getBuyCardAccountBatchId() {
		return buyCardAccountBatchId;
	}

	public void setBuyCardAccountBatchId(Long buyCardAccountBatchId) {
		this.buyCardAccountBatchId = buyCardAccountBatchId;
	}

	public BigDecimal getSoRefundByBuyCard() {
		return soRefundByBuyCard;
	}

	public void setSoRefundByBuyCard(BigDecimal soRefundByBuyCard) {
		this.soRefundByBuyCard = soRefundByBuyCard;
	}
}
