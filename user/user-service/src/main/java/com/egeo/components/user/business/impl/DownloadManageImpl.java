package com.egeo.components.user.business.impl;
	

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.user.business.DownloadManage;
import com.egeo.components.user.dto.ChannelDTO;
import com.egeo.components.user.dto.DownloadDTO;
import com.egeo.components.user.dto.VersionsDTO;
import com.egeo.components.user.facade.ChannelFacade;
import com.egeo.components.user.facade.DownloadFacade;
import com.egeo.components.user.facade.VersionsFacade;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.str.StringUtils;
import com.egeo.web.JsonResult;

@Service("download")
public class DownloadManageImpl implements DownloadManage{

	
	@Resource(name="downloadFacade")
	private DownloadFacade downloadFacade;
	
	@Resource(name="versionsFacade")
	private VersionsFacade versionsFacade;
	
	@Resource(name="channelFacade")
	private ChannelFacade channelFacade;

	@Override
	public DownloadDTO findDownloadById(DownloadDTO dto) {
		return downloadFacade.findDownloadById(dto);
	}

	@Override
	public PageResult<DownloadDTO> findDownloadOfPage(DownloadDTO dto, Pagination page) {
		return downloadFacade.findDownloadOfPageByBlurry(dto, page);
	}

	@Override
	public List<DownloadDTO> findDownloadAll(DownloadDTO dto) {
		return downloadFacade.findDownloadAll(dto);
	}

	@Override
	public Long insertDownloadWithTx(DownloadDTO dto) {
		if(dto.getName().length() > 30){
			throw new BusinessException("推广渠道名称最多不超过 30 个字");
		}
		// 推广渠道名称查重校验
		DownloadDTO downloadDTO_ = new DownloadDTO();
		downloadDTO_.setName(dto.getName());
		List<DownloadDTO> downloadList_= downloadFacade.findDownloadAll(downloadDTO_);
		if(downloadList_.size() > 0){
			throw new BusinessException("推广渠道名称已存在，请修改后重新保存");
		}
		
		// session 不能重复
		DownloadDTO downloadDTO = new DownloadDTO();
		downloadDTO.setSession(dto.getSession());
		List<DownloadDTO> downloadList= downloadFacade.findDownloadAll(downloadDTO);
		if(downloadList.size() > 0){
			throw new BusinessException("session重复");
		}
		
		return downloadFacade.insertDownloadWithTx(dto);
	}

	@Override
	public int updateDownloadWithTx(DownloadDTO dto) {
		if(dto.getName().length() > 30){
			throw new BusinessException("推广渠道名称最多不超过 30 个字");
		}
		// 推广渠道名称查重校验
		DownloadDTO downloadDTO_ = new DownloadDTO();
		downloadDTO_.setName(dto.getName());
		List<DownloadDTO> downloadList_= downloadFacade.findDownloadAll(downloadDTO_);
		for (DownloadDTO downloadDTO2 : downloadList_) {
			if(!downloadDTO2.getId().equals(dto.getId())){
				throw new BusinessException("推广渠道名称已存在，请修改后重新保存");
			}
		}
		
		// session 不能重复
		DownloadDTO downloadDTO = new DownloadDTO();
		downloadDTO.setSession(dto.getSession());
		List<DownloadDTO> downloadList= downloadFacade.findDownloadAll(downloadDTO);
		for (DownloadDTO downloadDTO2 : downloadList) {
			if(!downloadDTO2.getId().equals(dto.getId())){
				throw new BusinessException("session重复");
			}
		}
		return downloadFacade.updateDownloadWithTx(dto);
	}

	@Override
	public int deleteDownloadWithTx(DownloadDTO dto) {
		return downloadFacade.deleteDownloadWithTx(dto);
	}

	@Override
	public JsonResult<Map<String, Object>> askDownloadUrlWithTx(String session, Integer type,Integer user) {
		if(StringUtils.isBlank(session)){
			// 如果session为空就计算到官方渠道的下载量里
			DownloadDTO officialDownload_ = new DownloadDTO();
			officialDownload_.setId(2L);
			DownloadDTO officialDownload = downloadFacade.findDownloadById(officialDownload_);
			officialDownload.setDailyDownloads(officialDownload.getDailyDownloads() + 1L);
			officialDownload.setDownloads(officialDownload.getDownloads() + 1L);
			downloadFacade.updateDownloadWithTx(officialDownload);
		} else {
			DownloadDTO downloadDTO = new DownloadDTO();
			downloadDTO.setSession(session);
			List<DownloadDTO> downloadList = downloadFacade.findDownloadAll(downloadDTO);
			
			// 添加一条该渠道的下载记录,以及该渠道的总下载量及日下载量+1
			if(EmptyUtil.isEmpty(downloadList)){
				// 访问的渠道 session 无法与后台已有的 session 匹配，则将此下载数记录在其它渠道下
				DownloadDTO downloadDTO_ = new DownloadDTO();
				downloadDTO_.setId(1L);
				DownloadDTO otherDownload = downloadFacade.findDownloadById(downloadDTO_);
				downloadDTO_.setDailyDownloads(otherDownload.getDailyDownloads()+1L);
				downloadDTO_.setDownloads(otherDownload.getDownloads()+1L);
				downloadFacade.updateDownloadWithTx(downloadDTO_);
			} else {
				DownloadDTO downloadDTO_ = new DownloadDTO();
				downloadDTO_.setId(downloadList.get(0).getId());
				downloadDTO_.setDailyDownloads(downloadList.get(0).getDailyDownloads()+1L);
				downloadDTO_.setDownloads(downloadList.get(0).getDownloads()+1L);
				downloadFacade.updateDownloadWithTx(downloadDTO_);
			}
		}
		
		// 返回最高版本启用状态下的官方版本链接(android+ios)
		VersionsDTO versionsDto = new VersionsDTO();
		versionsDto.setType(0);
		versionsDto.setUser(user);
		VersionsDTO androidVersionsDto = versionsFacade.queryLatestOfficialVersion(versionsDto);
		if(EmptyUtil.isEmpty(androidVersionsDto)){
			throw new BusinessException("暂无版本可用信息");
		}
		versionsDto.setType(1);
		versionsDto.setVersionStatus(1);
		VersionsDTO iosVersionsDto = versionsFacade.queryLatestOfficialVersion(versionsDto);
		if(EmptyUtil.isEmpty(iosVersionsDto)){
			throw new BusinessException("暂无版本可用信息");
		}
		
		// 返回微信的url
		ChannelDTO channelDto = new ChannelDTO();
		channelDto.setShortCode(ChannelFacade.SHORTZ_CODE);
		List<ChannelDTO> channelList = channelFacade.findChannelAll(channelDto);
		
		
		JsonResult<Map<String, Object>> result = new JsonResult<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("androidUrl", androidVersionsDto != null ? androidVersionsDto.getUrl() : null);
		map.put("iosUrl", iosVersionsDto != null ? iosVersionsDto.getUrl() : null);
		map.put("wechatUrl",EmptyUtil.isNotEmpty(channelList) ? channelList.get(0).getPath() : null);
		result.setData(map);
		
		return result;
	}


}
	