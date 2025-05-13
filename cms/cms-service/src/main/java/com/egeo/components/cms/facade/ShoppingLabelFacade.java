package com.egeo.components.cms.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.cms.service.read.ShoppingLabelGroupReadService;
import com.egeo.components.cms.service.read.ShoppingLabelReadService;
import com.egeo.components.cms.service.write.ShoppingLabelGroupWriteService;
import com.egeo.components.cms.service.write.ShoppingLabelWriteService;
import com.egeo.components.cms.dto.ShoppingLabelDTO;
import com.egeo.components.cms.dto.ShoppingLabelGroupDTO;


@Component
public class ShoppingLabelFacade {
	
	@Resource
	private ShoppingLabelReadService shoppingLabelReadService;
	
	@Resource
	private ShoppingLabelWriteService shoppingLabelWriteService;

	@Resource
	private ShoppingLabelGroupReadService shoppingLabelGroupReadService;
	
	@Resource
	private ShoppingLabelGroupWriteService shoppingLabelGroupWriteService;
	
	/**
	 * 根据实例id查询商城标签组
	 * @param instId
	 * @return
	 */
	public ShoppingLabelGroupDTO queryShoppingLabelGroupByInstId(Long instId) {
		return shoppingLabelGroupReadService.queryShoppingLabelGroupByInstId(instId);
	}
	
	/**
	 * 根据组id查询商城标签列表
	 * @param groupId
	 * @return
	 */
	public List<ShoppingLabelDTO> queryShoppingLabelListByGroupId(Long groupId) {
		return shoppingLabelReadService.queryShoppingLabelListByGroupId(groupId);
	}

}
	