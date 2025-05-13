package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.BlessingCoinBannerCompanyReadService;
import com.egeo.components.product.service.write.BlessingCoinBannerCompanyWriteService;
import com.egeo.components.product.dto.BlessingCoinBannerCompanyDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class BlessingCoinBannerCompanyFacade {
	
	@Resource
	private BlessingCoinBannerCompanyReadService blessingCoinBannerCompanyReadService;
	
	@Resource
	private BlessingCoinBannerCompanyWriteService blessingCoinBannerCompanyWriteService;
	
	
	public BlessingCoinBannerCompanyDTO findBlessingCoinBannerCompanyById(BlessingCoinBannerCompanyDTO dto){
		
		return blessingCoinBannerCompanyReadService.findBlessingCoinBannerCompanyById(dto);
	}

	public PageResult<BlessingCoinBannerCompanyDTO> findBlessingCoinBannerCompanyOfPage(BlessingCoinBannerCompanyDTO dto,Pagination page){
		
		return blessingCoinBannerCompanyReadService.findBlessingCoinBannerCompanyOfPage(dto, page);
		
	}

	public List<BlessingCoinBannerCompanyDTO> findBlessingCoinBannerCompanyAll(BlessingCoinBannerCompanyDTO dto){
		
		return blessingCoinBannerCompanyReadService.findBlessingCoinBannerCompanyAll(dto);
		
	}

	public Long insertBlessingCoinBannerCompanyWithTx(BlessingCoinBannerCompanyDTO dto){
		
		return blessingCoinBannerCompanyWriteService.insertBlessingCoinBannerCompanyWithTx(dto);
	}

	public int updateBlessingCoinBannerCompanyWithTx(BlessingCoinBannerCompanyDTO dto){
		
		return blessingCoinBannerCompanyWriteService.updateBlessingCoinBannerCompanyWithTx(dto);
	}

	public int deleteBlessingCoinBannerCompanyWithTx(BlessingCoinBannerCompanyDTO dto){
		
		return blessingCoinBannerCompanyWriteService.deleteBlessingCoinBannerCompanyWithTx(dto);
		
	}

}
	