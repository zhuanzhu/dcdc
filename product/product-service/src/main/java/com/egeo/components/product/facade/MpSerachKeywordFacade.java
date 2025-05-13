package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.MpSerachKeywordReadService;
import com.egeo.components.product.service.write.MpSerachKeywordWriteService;
import com.egeo.components.product.dto.MpSerachKeywordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class MpSerachKeywordFacade {
	
	@Resource
	private MpSerachKeywordReadService mpSerachKeywordReadService;
	
	@Resource
	private MpSerachKeywordWriteService mpSerachKeywordWriteService;
	
	
	public MpSerachKeywordDTO findMpSerachKeywordById(MpSerachKeywordDTO dto){
		
		return mpSerachKeywordReadService.findMpSerachKeywordById(dto);
	}

	public PageResult<MpSerachKeywordDTO> findMpSerachKeywordOfPage(MpSerachKeywordDTO dto,Pagination page){
		
		return mpSerachKeywordReadService.findMpSerachKeywordOfPage(dto, page);
		
	}

	public List<MpSerachKeywordDTO> findMpSerachKeywordAll(MpSerachKeywordDTO dto){
		
		return mpSerachKeywordReadService.findMpSerachKeywordAll(dto);
		
	}

	public Long insertMpSerachKeywordWithTx(MpSerachKeywordDTO dto){
		
		return mpSerachKeywordWriteService.insertMpSerachKeywordWithTx(dto);
	}

	public int updateMpSerachKeywordWithTx(MpSerachKeywordDTO dto){
		
		return mpSerachKeywordWriteService.updateMpSerachKeywordWithTx(dto);
	}

	public int deleteMpSerachKeywordWithTx(MpSerachKeywordDTO dto){
		
		return mpSerachKeywordWriteService.deleteMpSerachKeywordWithTx(dto);
		
	}
	/**
	 * 根据suId查询su草稿关键词
	 * @param id
	 * @param platformId
	 * @return
	 */
	public List<String> keyWordByMerchantProductId(Long merchantProductId, Long platformId) {
		// TODO Auto-generated method stub
		return mpSerachKeywordReadService.keyWordByMerchantProductId(merchantProductId, platformId);
	}

}
	