package com.egeo.components.promotion.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.product.client.MerchantProductClient;
import com.egeo.components.product.dto.MerchantProductDTO;
import com.egeo.components.promotion.dto.ActivityMerchantProdDTO;
import com.egeo.components.promotion.service.read.ActivityMerchantProdReadService;
import com.egeo.components.promotion.service.write.ActivityMerchantProdWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class ActivityMerchantProdFacade {
	
	@Autowired
	private ActivityMerchantProdReadService activityMerchantProdReadService;
	
	@Autowired
	private ActivityMerchantProdWriteService activityMerchantProdWriteService;
	
	@Autowired
	private MerchantProductClient merchantProductReadService;
	
	/*@Autowired
	private ProductClient productReadService;*/

	public PageResult<ActivityMerchantProdDTO> findPageActivityMerchantProd(Pagination page,
			ActivityMerchantProdDTO dto) {
		return activityMerchantProdReadService.findPageActivityMerchantProd(page,
				dto);
	}

	public MerchantProductDTO findByMerchantProdId(MerchantProductDTO merchantProductDTO) {
		return merchantProductReadService.findMerchantProductById(merchantProductDTO);
	}

	public List<ActivityMerchantProdDTO> findAll(ActivityMerchantProdDTO dto) {
		return activityMerchantProdReadService.findAll(dto);
	}

	public PageResult<MerchantProductDTO> productByActivityId(Pagination page, String ids, String name) {
		return null;
		/*MerchantProductDTO merchantProductDTO = new MerchantProductDTO();
		merchantProductDTO.setName(name);
		merchantProductDTO.setIds(ids);
		//以上架商品：3
		merchantProductDTO.setStatus(3);
		return merchantProductReadService.productByActivityId(page,merchantProductDTO);*/
	}

	public Long saveActivityMerchantProd(ActivityMerchantProdDTO dto) {
		return activityMerchantProdWriteService.saveActivityMerchantProdWithTx(dto);
	}

	public Integer deleteById(ActivityMerchantProdDTO dto) {
		return activityMerchantProdWriteService.deleteByIdWithTx(dto);
	}
	
	public Long updateActivityMerchantProd(ActivityMerchantProdDTO dto){
		
		return activityMerchantProdWriteService.updateActivityMerchantProdWithTx(dto);
		
	}
	
	
	
	


}
	