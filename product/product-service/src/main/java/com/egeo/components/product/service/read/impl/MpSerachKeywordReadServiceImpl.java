package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.MpSerachKeywordReadService;
import com.egeo.components.product.manage.read.MpSerachKeywordReadManage;
import com.egeo.components.product.converter.MpSerachKeywordConverter;
import com.egeo.components.product.dto.MpSerachKeywordDTO;
import com.egeo.components.product.po.MpSerachKeywordPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("mpSerachKeywordReadService")
public class MpSerachKeywordReadServiceImpl  implements MpSerachKeywordReadService {
	@Autowired
	private MpSerachKeywordReadManage mpSerachKeywordReadManage;

	@Override
	public MpSerachKeywordDTO findMpSerachKeywordById(MpSerachKeywordDTO dto) {
		MpSerachKeywordPO po = MpSerachKeywordConverter.toPO(dto);
		MpSerachKeywordPO list = mpSerachKeywordReadManage.findMpSerachKeywordById(po);		
		return MpSerachKeywordConverter.toDTO(list);
	}

	@Override
	public PageResult<MpSerachKeywordDTO> findMpSerachKeywordOfPage(MpSerachKeywordDTO dto, Pagination page) {
		MpSerachKeywordPO po = MpSerachKeywordConverter.toPO(dto);
        PageResult<MpSerachKeywordPO> pageResult = mpSerachKeywordReadManage.findMpSerachKeywordOfPage(po, page);
        
        List<MpSerachKeywordDTO> list = MpSerachKeywordConverter.toDTO(pageResult.getList());
        PageResult<MpSerachKeywordDTO> result = new PageResult<MpSerachKeywordDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<MpSerachKeywordDTO> findMpSerachKeywordAll(MpSerachKeywordDTO dto) {
		MpSerachKeywordPO po = MpSerachKeywordConverter.toPO(dto);
		List<MpSerachKeywordPO> list = mpSerachKeywordReadManage.findMpSerachKeywordAll(po);		
		return MpSerachKeywordConverter.toDTO(list);
	}
	/**
	 * 根据suId查询su草稿关键词
	 * @param id
	 * @param platformId
	 * @return
	 */
	@Override
	public List<String> keyWordByMerchantProductId(Long merchantProductId, Long platformId) {
		// TODO Auto-generated method stub
		return mpSerachKeywordReadManage.keyWordByMerchantProductId(merchantProductId, platformId);
	}
}
	