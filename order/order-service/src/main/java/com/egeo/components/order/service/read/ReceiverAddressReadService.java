package com.egeo.components.order.service.read;


import java.util.List;

import com.egeo.components.order.dto.ReceiverAddressDTO;
import com.egeo.components.order.dto.ReceiverAddressDetailDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface ReceiverAddressReadService {

	public ReceiverAddressDTO findReceiverAddressById(Long id);

	public PageResult<ReceiverAddressDTO> findReceiverAddressOfPage(ReceiverAddressDTO dto,Pagination page);

	public List<ReceiverAddressDTO> findReceiverAddressAll(ReceiverAddressDTO dto);

	/**
	 * 查询用户默认收货地址
	 * @param memberId
	 * @param platformId
	 * @return
	 */
	public ReceiverAddressDTO queryDefaultReceiverAddress(Long memberId, Long platformId);


	/**
	 * 根据soId查询子订单所属的发货信息
	 * @param soId
	 * @param platformId
	 * @return
	 */
	public List<ReceiverAddressDetailDTO> findReceiveDetailBySoId(Long soId, Long platformId);

	/**
	 * 查询用户自己创建的收获地址
	 * @param userId
	 * @param platformId
	 * @return
	 */
	public List<ReceiverAddressDTO> queryReceiverAddressListCreatedByUser(Long userId, Long platformId);
	/**
	 * 查询当前用户收货地址数量
	 * @param userId 用户id
	 * @param platformId 平台id
	 * @return
	 */
	public int receiverAddressSumByUserId(Long userId, Long platformId);

    public ReceiverAddressDTO findReceiverAddressByChildCodeAndType(String childCode, int type);

	List<ReceiverAddressDTO> queryReceiverAddressListCreateByBackStage(String childCode);

	List<Long> getUserIdListByReceiverAddressMobile(String goodReceiverMobile);
}
