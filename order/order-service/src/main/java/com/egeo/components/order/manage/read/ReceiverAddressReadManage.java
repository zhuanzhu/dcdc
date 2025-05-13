package com.egeo.components.order.manage.read;

import java.util.List;

import com.egeo.components.order.condition.ReceiverAddressCondition;
import com.egeo.components.order.po.ReceiverAddressPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface ReceiverAddressReadManage {

	public ReceiverAddressPO findReceiverAddressById(ReceiverAddressPO po);

	public PageResult<ReceiverAddressPO> findReceiverAddressOfPage(ReceiverAddressPO po,Pagination page);

	public List<ReceiverAddressPO> findReceiverAddressAll(ReceiverAddressPO po);

	/**
	 * 查询用户默认收货地址
	 * @param memberId
	 * @param platformId
	 * @return
	 */
	public ReceiverAddressPO queryDefaultReceiverAddress(Long memberId, Long platformId);

	public List<ReceiverAddressCondition> findReceiveDetailBySoId(Long soId, Long platformId);

	/**
	 * 查询用户自己创建的收获地址
	 * @param userId
	 * @param platformId
	 * @return
	 */
	public List<ReceiverAddressPO> queryReceiverAddressListCreatedByUser(Long userId, Long platformId);
	/**
	 * 查询当前用户收货地址数量
	 * @param userId 用户id
	 * @param platformId 平台id
	 * @return
	 */
	public int receiverAddressSumByUserId(Long userId, Long platformId);

    public ReceiverAddressPO queryReceiverAddressByChildCodeAndType(String childCode, int type);


	List<ReceiverAddressPO> queryReceiverAddressListCreateByBackStage(String childCode);

	List<Long> getUserIdListByReceiverAddressMobile(String goodReceiverMobile);
}
