package com.egeo.components.user.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.user.client.QrCodeClient;
import com.egeo.components.user.dto.QrCodeDTO;
import com.egeo.components.user.fo.UpdateQrCodeByCouponUnitIdsFO;
import com.egeo.components.user.service.read.QrCodeReadService;
import com.egeo.components.user.service.write.QrCodeWriteService;

@Controller
@RequestMapping("/client/user/qrCode") 
public class QrCodeController implements QrCodeClient{ 

	@Autowired
	private QrCodeReadService qrCodeReadService;
	@Autowired
	private QrCodeWriteService qrCodeWriteService;


	@Override
	@RequestMapping(value = "/insertQrCodeListWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public Integer insertQrCodeListWithTx(@RequestBody List<QrCodeDTO> qrCodeDTOList) {
		return qrCodeWriteService.insertQrCodeListWithTx(qrCodeDTOList);
	} 
 
	@Override
	@RequestMapping(value = "/updateQrCodeByCouponUnitIds", method = { RequestMethod.POST })
	@ResponseBody
	public void updateQrCodeByCouponUnitIds(@RequestBody UpdateQrCodeByCouponUnitIdsFO fo) {
		qrCodeWriteService.updateQrCodeByCouponUnitIds(fo.getCouponUnitIds(), fo.getChannelActivityId(), fo.getChannelId());
	} 
 
	@Override
	@RequestMapping(value = "/updateQrCodeWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public int updateQrCodeWithTx(@RequestBody QrCodeDTO dto) {
		return qrCodeWriteService.updateQrCodeWithTx(dto);
	} 
 
	@Override
	@RequestMapping(value = "/findQrCodeListByCouponUnitIds", method = { RequestMethod.POST })
	@ResponseBody
	public List<QrCodeDTO> findQrCodeListByCouponUnitIds(@RequestBody  List<String> couponUnitIds) {
		return qrCodeReadService.findQrCodeListByCouponUnitIds(com.egeo.utils.StringUtils.stringsToLongs(couponUnitIds));
	} 
 
	@Override
	@RequestMapping(value = "/findQrCodeByCouponUnitId", method = { RequestMethod.POST })
	@ResponseBody
	public QrCodeDTO findQrCodeByCouponUnitId(@RequestBody  Long id) {
		return qrCodeReadService.findQrCodeByCouponUnitId(id);
	} 
 
	@Override
	@RequestMapping(value = "/findQrCodeAll", method = { RequestMethod.POST })
	@ResponseBody
	public List<QrCodeDTO> findQrCodeAll(@RequestBody QrCodeDTO dto) {
		return qrCodeReadService.findQrCodeAll(dto);
	} 
 
}