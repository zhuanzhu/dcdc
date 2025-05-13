package com.egeo.components.order.business;

import java.util.List;

import com.egeo.components.order.dto.ReceiverAddressDTO;
import com.egeo.components.order.dto.ReceiverAddressDetailDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.JsonResult;	

public interface ReceiverAddressManage {

	public ReceiverAddressDTO findReceiverAddressById(Long id);	

	public PageResult<ReceiverAddressDTO> findReceiverAddressOfPage(ReceiverAddressDTO dto,Pagination page);

	public List<ReceiverAddressDTO> findReceiverAddressAll(ReceiverAddressDTO dto);

	Long insertReceiverAddressWithTx(ReceiverAddressDTO dto);

	int updateReceiverAddressWithTx(ReceiverAddressDTO dto);

	int deleteReceiverAddressWithTx(ReceiverAddressDTO dto);

	public String defaultAddressById(Long id,Long userId);

	/**\
	 * 根据soId查询子订单所属的发货信息
	 * @param soId
	 * @param platformId
	 * @return
	 */
	public List<ReceiverAddressDetailDTO> findReceiveDetailBySoId(Long soId, Long platformId);

	/**
	 * 修改收货人地址
	 * @param soChildId 
	 * @param receiverAddressDTO
	 * @return
	 */
	public JsonResult<String> modifyReceiverAddress(Long soChildId, ReceiverAddressDTO receiverAddressDTO);

	/**
	 * 查询用户自己创建的收获地址
	 * @param id
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
}
