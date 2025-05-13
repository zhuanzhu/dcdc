package com.egeo.components.cms.facade;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.egeo.common.NormalConstant;
import com.egeo.components.cms.dto.InstCompanyDTO;
import com.egeo.components.cms.dto.InstDTO;
import com.egeo.components.cms.dto.LinkableButtonPageDTO;
import com.egeo.components.cms.service.read.InstCompanyReadService;
import com.egeo.components.cms.service.read.InstReadService;
import com.egeo.components.cms.service.write.InstWriteService;
import com.egeo.components.cms.vo.LinkableButtonPageVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Component
public class InstFacade {
	
	private static final Logger logger = LoggerFactory.getLogger(InstFacade.class);

	@Resource
	private InstReadService instReadService;

	@Resource
	private InstWriteService instWriteService;

	@Resource
	private InstCompanyReadService instCompanyReadService;

	public InstDTO findInstById(Long id) {

		return instReadService.findInstById(id);
	}

	public PageResult<InstDTO> findInstOfPage(InstDTO dto, Pagination page) {

		return instReadService.findInstOfPage(dto, page);

	}

	public List<InstDTO> findInstAll(InstDTO dto) {

		return instReadService.findInstAll(dto);

	}

	public Long insertInstWithTx(InstDTO dto) {

		return instWriteService.insertInstWithTx(dto);
	}

	public int updateInstWithTx(InstDTO dto) {

		return instWriteService.updateInstWithTx(dto);
	}

	public int deleteInstWithTx(InstDTO dto) {

		return instWriteService.deleteInstWithTx(dto);

	}

	/**
	 * 根据组件id拆查询实例
	 * 
	 * @param elementId
	 * @return
	 */
	public InstDTO queryInstByElementId(Long elementId) {
		return instReadService.queryInstByElementId(elementId);
	}

	/**
	 * 根据实例id查询实例公司关联信息
	 * 
	 * @param id
	 * @return
	 */
	public List<InstCompanyDTO> queryInstCompanyListByInstId(Long id) {
		InstCompanyDTO dto = new InstCompanyDTO();
		dto.setInstId(id);
		return instCompanyReadService.findInstCompanyAll(dto);
	}

	/**
	 * 新建/保存组件和实例
	 */
	public boolean saveEleAndInst(
			// 通用参数
			Long templateId, String name, Integer margin, Long elementDictId, Long elementId, Long instId,
			List<Long> cids, Long pageTabId,
			// banner参数
			String bannerIds,
			// su列表参数
			Long titleColor, String titleName, Integer maxShow, Long sucId, String bannerUrl, Integer linkType,
			Long linkId, String linkUrl,
			// icon参数
			Integer count, String iconArr, String iconTitle,
			// 图文参数
			String imagetextTitle, String imagetextArr, String imagetextBannerArr,
			// 标签参数
			String lableIconUrl, String lableArr,
			
			List<LinkableButtonPageVO> linkableButtonPageVOs) {
		
			List<LinkableButtonPageDTO> linkableButtonPageDTOs = new ArrayList<LinkableButtonPageDTO>();
		
			LinkableButtonPageDTO linkableButtonPageDTO;
			if(linkableButtonPageVOs != null && linkableButtonPageVOs.size() > 0) {
				for (LinkableButtonPageVO linkableButtonPageVO : linkableButtonPageVOs) {
					linkableButtonPageDTO = new LinkableButtonPageDTO();					
					try {
						BeanUtils.copyProperties(linkableButtonPageDTO,linkableButtonPageVO );
					} catch (IllegalAccessException e) {
						logger.error("参数copy 异常 ： linkableButtonPageVO to  linkableButtonPageDTO :",e);
					} catch (InvocationTargetException e) {
						logger.error("参数copy 异常 ： linkableButtonPageVO to  linkableButtonPageDTO :",e);
					}
					linkableButtonPageDTOs.add(linkableButtonPageDTO);
				}
			}
		return instWriteService.saveEleAndInst(templateId, name, margin, elementDictId, elementId, instId, cids,
				pageTabId, bannerIds, titleColor, titleName, maxShow, sucId, bannerUrl, linkType, linkId, linkUrl,
				count, iconArr, iconTitle, imagetextTitle, imagetextArr, imagetextBannerArr, lableIconUrl, lableArr,
				linkableButtonPageDTOs);
	}

	/**
	 * 根据实例id和公司id查询实例与公司关系列表
	 * 
	 * @param id
	 * @param companyId
	 * @return
	 */
	public List<InstCompanyDTO> queryInstCompanyListByInstIdAndCompanyId(Long id, Long companyId, Integer companyType) {
		return instCompanyReadService.queryInstCompanyListByInstIdAndCompanyId(id, companyId,
				NormalConstant.getCompanyIdByCompanyType(companyType));
	}

}
