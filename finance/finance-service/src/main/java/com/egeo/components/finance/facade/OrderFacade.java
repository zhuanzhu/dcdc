package com.egeo.components.finance.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.finance.bean.SoDataDTO;
import com.egeo.components.order.client.SoClient;
import com.egeo.components.order.client.SoItemClient;
import com.egeo.components.order.dto.SoDTO;
import com.egeo.components.order.dto.SoItemDTO;
import com.egeo.components.user.client.CompanyClient;
import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.components.user.dto.UserExtendDTO;
import com.egeo.components.user.dto.UserExtendPageDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
@Component
public class OrderFacade {
	
	@Autowired
	private SoItemClient soItemClient;
	@Autowired
	private SoClient soClient;
	@Autowired
	private CompanyClient companyClient;


	/**
	 * 查询所有公司
	 * @return
	 */
	public SoDataDTO querySoData(Long orderId) {
		SoDataDTO data = new SoDataDTO();
		List<SoItemDTO> items = soItemClient.querySoItemListBySoId(orderId);
		data.setItems(items);
		SoDTO order = soClient.querySoById(orderId);
		data.setOrder(order);
		CompanyDTO company = companyClient.findCompanyById(order.getCompanyId());
		data.setEnterpriseId(company.getEnterpriseId());
		return data;
	}

	
}
