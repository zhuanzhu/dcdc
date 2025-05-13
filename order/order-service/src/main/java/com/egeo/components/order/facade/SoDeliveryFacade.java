package com.egeo.components.order.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.order.service.read.DeliveryCompanyReadService;
import com.egeo.components.order.service.read.SoDeliveryReadService;
import com.egeo.components.order.service.read.SoPackageItemReadService;
import com.egeo.components.order.service.read.SoPackageReadService;
import com.egeo.components.order.service.write.SoDeliveryWriteService;
import com.egeo.components.order.service.write.SoPackageWriteService;
import com.egeo.components.order.dto.DeliveryCompanyDTO;
import com.egeo.components.order.dto.SoDeliveryDTO;
import com.egeo.components.order.dto.SoPackageDTO;
import com.egeo.components.order.dto.SoPackageItemDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class SoDeliveryFacade {

	@Resource
	private SoDeliveryReadService soDeliveryReadService;

	@Resource
	private SoDeliveryWriteService soDeliveryWriteService;

	@Resource
	private SoPackageItemReadService soPackageItemReadService;

	@Resource
	private DeliveryCompanyReadService deliveryCompanyReadService;

	@Resource
	private SoPackageReadService soPackageReadService;

	@Resource
	private SoPackageWriteService soPackageWriteService;


	public SoDeliveryDTO findSoDeliveryById(SoDeliveryDTO dto){

		return soDeliveryReadService.findSoDeliveryById(dto);
	}

	public PageResult<SoDeliveryDTO> findSoDeliveryOfPage(SoDeliveryDTO dto,Pagination page){

		return soDeliveryReadService.findSoDeliveryOfPage(dto, page);

	}

	public List<SoDeliveryDTO> findSoDeliveryAll(SoDeliveryDTO dto){

		return soDeliveryReadService.findSoDeliveryAll(dto);

	}

	public int insertSoDeliveryWithTx(SoDeliveryDTO dto){

		return soDeliveryWriteService.insertSoDeliveryWithTx(dto);
	}

	public int updateSoDeliveryWithTx(SoDeliveryDTO dto){

		return soDeliveryWriteService.updateSoDeliveryWithTx(dto);
	}

	public int deleteSoDeliveryWithTx(SoDeliveryDTO dto){

		return soDeliveryWriteService.deleteSoDeliveryWithTx(dto);

	}

	public List<SoPackageItemDTO> findSoPackageItemAll(SoPackageItemDTO soPackageItemDTO) {

		return soPackageItemReadService.findSoPackageItemAll(soPackageItemDTO);
	}

	public List<DeliveryCompanyDTO> findDeliveryCompanyAll(DeliveryCompanyDTO dto) {

		return deliveryCompanyReadService.findDeliveryCompanyAll(dto);
	}

	public List<SoPackageDTO> packageByWaybillNum(String waybillNum) {
		SoPackageDTO dto = new SoPackageDTO();
		dto.setDeliveryCode(waybillNum);
		return soPackageReadService.findSoPackageAll(dto);
	}

	public int updateSoPackage(SoPackageDTO soPackageDTO) {
		return soPackageWriteService.updateSoPackageWithTx(soPackageDTO);
	}

	/**
	 * 签收
	 * @param deliveryMessage 物流轨迹信息
	 * @param packageId 包裹id
	 * @param soChildId 子订单id
	 * @return
	 */
	public boolean expressSignIn(Long soId,
			String deliveryMessage, Long packageId,
			Long soChildId) {

		return soPackageWriteService.expressSignIn(soId,deliveryMessage,packageId,soChildId);
	}

	/**
	 * 在途
	 * @param deliveryMessage 物流轨迹信息
	 * @param packageId 包裹id
	 * @param soChildId 子订单id
	 * @return
	 */
	public boolean expressInway(Long soId,Long packageId, Long soChildId) {

		return soPackageWriteService.expressInway(soId,packageId,soChildId);
	}

	/**
	 * 在途
	 * @param packageId 包裹id
	 * @param soChildId 子订单id
	 * @param acceptTime 物流时间
	 * @return
	 */
	public boolean expressTimeWithTx(Long soId,Long packageId, Long soChildId,String acceptTime) {

		return soPackageWriteService.expressTimeWithTx(soId,packageId,soChildId,acceptTime);
	}
}
