package com.egeo.components.order.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.order.service.read.ProvCityAreaReadService;
import com.egeo.components.order.service.read.ReceiverAddressReadService;
import com.egeo.components.order.service.write.ReceiverAddressWriteService;
import com.egeo.components.order.dto.ProvCityAreaDTO;
import com.egeo.components.order.dto.ReceiverAddressDTO;
import com.egeo.components.order.dto.ReceiverAddressDetailDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;


@Component
public class ReceiverAddressFacade {

	@Resource
	private ReceiverAddressReadService receiverAddressReadService;

	@Resource
	private ReceiverAddressWriteService receiverAddressWriteService;

	@Resource
	private ProvCityAreaReadService provCityAreaReadService;

	//根据id查询收获地址
	public ReceiverAddressDTO findReceiverAddressById(Long id){

		return receiverAddressReadService.findReceiverAddressById(id);
	}
	//根据childcode查询原始快照或者快照
	public ReceiverAddressDTO findSnapsAddressByChildCodeAndType(String childCode,int type){
		return receiverAddressReadService.findReceiverAddressByChildCodeAndType(childCode,type);
	}

	public PageResult<ReceiverAddressDTO> findReceiverAddressOfPage(ReceiverAddressDTO dto,Pagination page){

		return receiverAddressReadService.findReceiverAddressOfPage(dto, page);

	}

	public List<ReceiverAddressDTO> findReceiverAddressAll(ReceiverAddressDTO dto){

		return receiverAddressReadService.findReceiverAddressAll(dto);

	}

	public Long insertReceiverAddressWithTx(ReceiverAddressDTO dto){
		// 根据地址id查询地址信息保存地址信息
		saveReceiverAddressInfo(dto);
		return receiverAddressWriteService.insertReceiverAddressWithTx(dto);
	}
	/**
	 * 根据地址id查询地址信息保存地址信息
	 * @param dto
	 */
	private void saveReceiverAddressInfo(ReceiverAddressDTO dto) {
		// 如果地址信息为空并且地址id不为空则查询地址信息赋值（配合web端开发）
		if(EmptyUtil.isEmpty(dto.getGoodReceiverCountry()) && EmptyUtil.isNotEmpty(dto.getGoodReceiverCountryId())){
			// 根据地址id查询地址信息
			ProvCityAreaDTO provCityAreaDTO = provCityAreaReadService.findProvCityAreaById(dto.getGoodReceiverCountryId());
			dto.setGoodReceiverCountry(provCityAreaDTO.getAreaname());
		}
		if(EmptyUtil.isEmpty(dto.getGoodReceiverProvince()) && EmptyUtil.isNotEmpty(dto.getGoodReceiverProvinceId())){
			// 根据地址id查询地址信息
			ProvCityAreaDTO provCityAreaDTO = provCityAreaReadService.findProvCityAreaById(dto.getGoodReceiverProvinceId());
			dto.setGoodReceiverProvince(provCityAreaDTO.getAreaname());
		}
		if(EmptyUtil.isEmpty(dto.getGoodReceiverCity()) && EmptyUtil.isNotEmpty(dto.getGoodReceiverCityId())){
			// 根据地址id查询地址信息
			ProvCityAreaDTO provCityAreaDTO = provCityAreaReadService.findProvCityAreaById(dto.getGoodReceiverCityId());
			dto.setGoodReceiverCity(provCityAreaDTO.getAreaname());
		}
		if(EmptyUtil.isEmpty(dto.getGoodReceiverCounty()) && EmptyUtil.isNotEmpty(dto.getGoodReceiverCountyId())){
			// 根据地址id查询地址信息
			ProvCityAreaDTO provCityAreaDTO = provCityAreaReadService.findProvCityAreaById(dto.getGoodReceiverCountyId());
			dto.setGoodReceiverCounty(provCityAreaDTO.getAreaname());
		}
		if(EmptyUtil.isEmpty(dto.getGoodReceiverArea()) && EmptyUtil.isNotEmpty(dto.getGoodReceiverAreaId())){
			// 根据地址id查询地址信息
			ProvCityAreaDTO provCityAreaDTO = provCityAreaReadService.findProvCityAreaById(dto.getGoodReceiverAreaId());
			dto.setGoodReceiverArea(provCityAreaDTO.getAreaname());
		}
	}

	public int updateReceiverAddressWithTx(ReceiverAddressDTO dto){
		// 根据地址id查询地址信息保存地址信息
		saveReceiverAddressInfo(dto);
		return receiverAddressWriteService.updateReceiverAddressWithTx(dto);
	}

	public int deleteReceiverAddressWithTx(ReceiverAddressDTO dto){

		return receiverAddressWriteService.deleteReceiverAddressWithTx(dto);

	}

	/**
	 * 查询用户默认的收货地址
	 * @param memberId
	 * @param platformId
	 * @return
	 */
	public ReceiverAddressDTO queryDefaultReceiverAddress(Long memberId, Long platformId) {

		return receiverAddressReadService.queryDefaultReceiverAddress(memberId, platformId);
	}

	public List<ReceiverAddressDetailDTO> findReceiveDetailBySoId(Long soId, Long platformId) {

		return receiverAddressReadService.findReceiveDetailBySoId(soId,platformId);
	}

	public void modifyReceiverAddress(Long soChildId, ReceiverAddressDTO receiverAddressDTO) {
		receiverAddressWriteService.modifyReceiverAddress(soChildId,receiverAddressDTO);
	}

	/**
	 * 查询用户自己创建的收获地址
	 * @param userId
	 * @param platformId
	 * @return
	 */
	public List<ReceiverAddressDTO> queryReceiverAddressListCreatedByUser(Long userId, Long platformId) {
		return receiverAddressReadService.queryReceiverAddressListCreatedByUser(userId,platformId);
	}
	/**
	 * 查询当前用户收货地址数量
	 * @param userId 用户id
	 * @param platformId 平台id
	 * @return
	 */
	public int receiverAddressSumByUserId(Long userId, Long platformId) {
		return receiverAddressReadService.receiverAddressSumByUserId(userId, platformId);
	}

	public List<ReceiverAddressDTO> queryReceiverAddressListCreateByBackStage(String childCode) {
		return receiverAddressReadService.queryReceiverAddressListCreateByBackStage(childCode);
	}

	public List<Long> getUserIdListByReceiverAddressMobile(String goodReceiverMobile){
		return receiverAddressReadService.getUserIdListByReceiverAddressMobile(goodReceiverMobile);
	}
}
