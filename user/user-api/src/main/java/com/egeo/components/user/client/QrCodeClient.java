package com.egeo.components.user.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.egeo.components.user.dto.QrCodeDTO;
import com.egeo.components.user.fo.UpdateQrCodeByCouponUnitIdsFO;


@FeignClient(name = "service-user-fgj",contextId="QrCodeClient")
public interface QrCodeClient {

	@RequestMapping(value = { "/client/user/qrCode/insertQrCodeListWithTx" }, method = { RequestMethod.POST }) 
	public Integer insertQrCodeListWithTx(List<QrCodeDTO> qrCodeDTOList); 
 
 
	@RequestMapping(value = { "/client/user/qrCode/updateQrCodeByCouponUnitIds" }, method = { RequestMethod.POST }) 
	public void updateQrCodeByCouponUnitIds(UpdateQrCodeByCouponUnitIdsFO fo); 
 
 
	@RequestMapping(value = { "/client/user/qrCode/updateQrCodeWithTx" }, method = { RequestMethod.POST }) 
	public int updateQrCodeWithTx(QrCodeDTO dto); 
 
 
	@RequestMapping(value = { "/client/user/qrCode/findQrCodeListByCouponUnitIds" }, method = { RequestMethod.POST }) 
	public List<QrCodeDTO> findQrCodeListByCouponUnitIds(List<String> couponUnitIds); 
 
 
	@RequestMapping(value = { "/client/user/qrCode/findQrCodeByCouponUnitId" }, method = { RequestMethod.POST }) 
	public QrCodeDTO findQrCodeByCouponUnitId(Long id); 
 
 
	@RequestMapping(value = { "/client/user/qrCode/findQrCodeAll" }, method = { RequestMethod.POST }) 
	public List<QrCodeDTO> findQrCodeAll(QrCodeDTO dto); 
 
 
}