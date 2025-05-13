package com.egeo.components.order.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.order.condition.ReceiverAddressCondition;
import com.egeo.components.order.po.ReceiverAddressPO;
import com.egeo.orm.BaseReadDAO;

public interface ReceiverAddressReadDAO extends BaseReadDAO<ReceiverAddressPO>{
	/**
	 * 查询用户默认收货地址
	 * @param memberId
	 * @param platformId
	 * @return
	 */
	ReceiverAddressPO queryDefaultReceiverAddress(@Param("memberId")Long memberId, @Param("platformId")Long platformId);

	List<ReceiverAddressCondition> findReceiveDetailBySoId(@Param("soId")Long soId, @Param("platformId")Long platformId);

	/**
	 * 查询用户自己创建的收获地址
	 * @param userId
	 * @param platformId
	 * @return
	 */
	List<ReceiverAddressPO> queryReceiverAddressListCreatedByUser(
			@Param("userId")Long userId,
			@Param("platformId")Long platformId);
	/**
	 * 查询当前用户收货地址数量
	 * @param userId 用户id
	 * @param platformId 平台id
	 * @return
	 */
	int receiverAddressSumByUserId(
			@Param("userId")Long userId,
			@Param("platformId")Long platformId);

	/**
	 *查询子订单的快照和原始快照
	 * @param childCode 字子订单code
	 * @param type     类型(1、用户创建 2、运营创建 3、快照 4、原始快照)
	 * @return
	 */
    ReceiverAddressPO queryReceiverAddressByChildCodeAndType(@Param("childCode")String childCode,
															 @Param("type")int type);


	/**
	 * 根据子订单code查询后台创建地址list
	 * @param childCode
	 * @return
	 */
	List<ReceiverAddressPO> queryReceiverAddressListCreateByBackStage(@Param("childCode") String childCode);

	/**
	 * 根据收货人手机号查询用户id列表
	 * @param goodReceiverMobile
	 * @return
	 */
	List<Long> getUserIdList(@Param("goodReceiverMobile") String goodReceiverMobile);
}
