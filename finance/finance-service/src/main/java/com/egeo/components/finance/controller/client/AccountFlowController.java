package com.egeo.components.finance.controller.client;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.egeo.components.finance.client.AccountFlowClient;
import com.egeo.components.finance.converter.SoJdConverter;
import com.egeo.components.finance.dao.read.SoJdReadDAO;
import com.egeo.components.finance.dao.write.SoJdWriteDAO;
import com.egeo.components.finance.dto.AccountFlowDTO;
import com.egeo.components.finance.po.SoJdPO;
import com.egeo.components.finance.service.read.AccountFlowReadService;
import com.egeo.components.finance.service.write.AccountFlowWriteService;
import com.egeo.components.order.client.SoClient;
import com.egeo.components.order.client.SoItemClient;
import com.egeo.components.order.dto.SoDTO;
import com.egeo.components.order.dto.SoItemDTO;
import com.egeo.components.product.dto.JdProductDTO;

@Controller
@RequestMapping("/client/finance/accountFlow")
public class AccountFlowController implements AccountFlowClient{
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AccountFlowReadService accountFlowReadService;
	@Autowired
	private AccountFlowWriteService accountFlowWriteService;
	@Autowired
	private SoClient soClient;
	@Autowired
	private SoItemClient soItemClient;
	@Autowired
	private SoJdReadDAO soJdReadDAO;
	@Autowired
	SoJdWriteDAO soJdWriteDAO;
	@Override
	@RequestMapping(value = "/findAccountFlowAll", method = { RequestMethod.POST })
	@ResponseBody
	public List<AccountFlowDTO> findAccountFlowAll(@RequestBody AccountFlowDTO dto) {
		return accountFlowReadService.findAccountFlowAll(dto);
	}


	@Override
	@RequestMapping(value = "/queryOrderRefundFlow", method = { RequestMethod.POST })
	@ResponseBody
	public List<AccountFlowDTO> queryOrderRefundFlow(@RequestBody Long orderId) {
		// TODO Auto-generated method stub
		return accountFlowReadService.queryOrderRefundFlow(orderId);
	}


	@Override
	@RequestMapping(value = { "/orderRefund" }, method = { RequestMethod.POST })
	@ResponseBody
	public void orderRefund(@RequestBody String orderCode) {
		// TODO Auto-generated method stub
		List<SoDTO> sos = soClient.findSoByCode(orderCode);

		if(sos!=null && sos.size()==1) {
			SoJdPO sojd = soJdReadDAO.querySoByOrderCode(orderCode);
			if(sojd!=null) {
				SoJdPO soRefundjd = soJdReadDAO.querySoByRefundOrderCode(orderCode);
				if(soRefundjd==null) {
					sojd.setLedgerEnterprise(sojd.getLedgerEnterprise().multiply(BigDecimal.valueOf(-1)));
					sojd.setLedgerPlateform(sojd.getLedgerPlateform().multiply(BigDecimal.valueOf(-1)));
					sojd.setMonth(new Date());
					sojd.setType(2);
					sojd.setId(null);
					soJdWriteDAO.insert(sojd);
				}
			}

		}

	}


	@Override
	@RequestMapping(value = { "/orderConfirm" }, method = { RequestMethod.POST })
	@ResponseBody
	public void orderConfirm(@RequestBody String orderCode) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		List<SoDTO> sos = soClient.findSoByCode(orderCode);
		if(sos!=null && sos.size()==1) {
			SoDTO so = sos.get(0);
			SoJdPO sojd = soJdReadDAO.querySoByOrderCode(orderCode);
			if(sojd!=null) {
				soJdWriteDAO.updateOrderStatusById(sojd.getId(), so.getOrderStatus(), so.getOrderConfirmStatus(), so.getOrderPayStatus(), so.getDeliveryStatus(), so.getLimitCashPayAmount().intValue());
			}else {
				List<SoItemDTO> items = soItemClient.querySoItemBySoId(so.getId());
				BigDecimal  ledgerPlateform = new BigDecimal(0);
				BigDecimal  ledgerEnterprise = new BigDecimal(0);
				if(items!=null && items.size()>0) {
					for(SoItemDTO item :items) {
						if(item.getSnapshot()!=null && item.getSnapshot().length()>2 && item.getSource() !=null && item.getSource()==3) {

							BigDecimal  ledgerPlateformOne = new BigDecimal(0);
							BigDecimal  ledgerEnterpriseOne = new BigDecimal(0);
							JdProductDTO jdProductDTO = JSON.parseObject(item.getSnapshot(), JdProductDTO.class);
							if(jdProductDTO!=null && jdProductDTO.getLedger()!=null &&jdProductDTO.getLedger().length()>0) {
								String ledgerString = jdProductDTO.getLedger();
								JSONObject ledgerObj = JSON.parseObject(ledgerString);
								if(ledgerObj.containsKey("ledgerPlateform")) {
									ledgerPlateformOne = new BigDecimal(ledgerObj.getString("ledgerPlateform"));
								}
								if(ledgerObj.containsKey("ledgerEnterprise")) {
									ledgerEnterpriseOne = new BigDecimal(ledgerObj.getString("ledgerEnterprise"));
								}
								ledgerPlateform = ledgerPlateform.add(ledgerPlateformOne);
								ledgerEnterprise = ledgerEnterprise.add(ledgerEnterpriseOne);
							}
						}
					}
				}
				SoJdPO po = SoJdConverter.toPO(so);
				po.setMonth(new Date());
				po.setLedgerEnterprise(ledgerEnterprise);
				po.setLedgerPlateform(ledgerPlateform);
				po.setSoItems(JSON.toJSONString(items));
				soJdWriteDAO.insert(po);
			}

		}


	}

}
