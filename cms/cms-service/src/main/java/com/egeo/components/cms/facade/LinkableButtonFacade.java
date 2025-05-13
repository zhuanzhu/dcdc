package com.egeo.components.cms.facade;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.egeo.components.cms.dto.LinkableButtonDTO;
import com.egeo.components.cms.dto.LinkableButtonPageDTO;
import com.egeo.components.cms.dto.LinkableParamDTO;
import com.egeo.components.cms.service.read.CmsPageReadService;
import com.egeo.components.cms.service.read.LinkableButtonPageReadService;
import com.egeo.components.cms.service.read.LinkableButtonReadService;
import com.egeo.components.cms.service.read.LinkableParamReadService;
import com.egeo.components.cms.vo.LinkableButtonPageVO;

@Component
public class LinkableButtonFacade {
	
	private static final Logger logger = LoggerFactory.getLogger(LinkableButtonFacade.class);

	@Resource
	private LinkableButtonReadService linkableButtonReadService;
	
	@Resource
	private LinkableButtonPageReadService linkableButtonPageReadService;
	
	@Resource
	private CmsPageReadService cmsPageReadService;

	@Resource
	private LinkableParamReadService linkableParamReadService;

	/**
	 * 根据id查询可跳转链接
	 * @param linkableId
	 * @return
	 */
	public LinkableButtonDTO queryLinkableButtonById(Long linkableId) {
		
		return linkableButtonReadService.findLinkableButtonById(linkableId);
	}
	
	/**
	 * 根据linkableId 查询
	 * @param linkableId
	 * @return
	 */
	public List<LinkableButtonPageDTO> queryLinkableButtonPageByLinkableId(Long linkableId){
		
		return linkableButtonPageReadService.findLinkableButtonPageAll(new LinkableButtonPageDTO(linkableId));
	}

	/**
	 * 根据linkableId 查询LinkableButtonPageVO
	 * @param linkableId
	 * @return
	 */
	public List<LinkableButtonPageVO> queryLinkableButtonPagByLinkableId(Long linkableId) {
		
		List<LinkableButtonPageVO> lbpVOs = new ArrayList<>();
		
		List<LinkableButtonPageDTO> lbpDTOs = linkableButtonPageReadService.findLinkableButtonPageAll(new LinkableButtonPageDTO(linkableId));
		
		LinkableButtonPageVO linkableButtonPageVO;
		
		for (LinkableButtonPageDTO linkableButtonPageDTO : lbpDTOs) {
			
			linkableButtonPageVO = new LinkableButtonPageVO();
			
			linkableButtonPageVO.setCmsPageId(linkableButtonPageDTO.getCmsPageId());
			
			linkableButtonPageVO.setClientType(linkableButtonPageDTO.getClientType());
			
			lbpVOs.add(linkableButtonPageVO);
			
		}
		
		return lbpVOs;
	}
	
	public List<LinkableParamDTO> queryLinkableParamByLinkableId(Long linkableId){
		LinkableParamDTO dto = new LinkableParamDTO();
		dto.setLinkButtonId(linkableId);
		return linkableParamReadService.findLinkableParamAll(dto);
	}

}
