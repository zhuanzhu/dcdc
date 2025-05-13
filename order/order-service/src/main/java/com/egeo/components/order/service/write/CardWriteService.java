package com.egeo.components.order.service.write;

public interface CardWriteService {
	/**
	 * 分配虚拟卡及扣除库存
	 * @param orderId 订单id
	 * @param orderCode 订单编号
	 * @param userId 用户id
	 * @param userName 用户名称
	 * @param ip
	 * @param mac
	 * @param companyType 公司类型 0:正式公司 1:测试公司 2:竞品公司
	 * @return
	 */
	public Boolean allocationCardAndTakeStock(Long orderId, String orderCode, Long userId, String userName, String ip, String mac,Integer companyType);
}

	