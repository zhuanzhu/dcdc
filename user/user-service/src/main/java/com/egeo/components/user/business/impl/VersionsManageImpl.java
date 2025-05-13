package com.egeo.components.user.business.impl;

import com.egeo.components.user.business.VersionsManage;
import com.egeo.components.user.facade.VersionsFacade;
import com.egeo.components.user.vo.VersionValidateVO;
import com.egeo.components.user.dto.VersionsDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.JsonResult;
import com.egeo.utils.EmptyUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.egeo.web.JsonResult.fail;
import static com.egeo.web.JsonResult.success;

@Service("versions")
public class VersionsManageImpl implements VersionsManage {
	
	public static String IOS_DOWNLOAD_URL = "https://itunes.apple.com/cn/app/id1301759756?mt=8";
	
	@Resource(name = "versionsFacade")
	private VersionsFacade versionsFacade;

	@Override
	public VersionsDTO findVersionsById(VersionsDTO dto) {
		return versionsFacade.findVersionsById(dto);
	}

	@Override
	public PageResult<VersionsDTO> findVersionsOfPage(VersionsDTO dto, Pagination page) {
		return versionsFacade.findVersionsOfPage(dto, page);
	}

	@Override
	public List<VersionsDTO> findVersionsAll(VersionsDTO dto) {
		return versionsFacade.findVersionsAll(dto);
	}

	@Override
	public JsonResult<Map<String, Object>> insertVersionsWithTx(VersionsDTO dto) {
		// 参数校验
		if(EmptyUtil.isBlank(dto.getVersionsMark())){
			return JsonResult.fail("请填写版本号");
		}
		if(EmptyUtil.isEmpty(dto.getVersionCode())){
			return JsonResult.fail("请填写版本水平");
		}
		if(EmptyUtil.isEmpty(dto.getType())){
			return JsonResult.fail("请填写版本类型");
		}
		if(EmptyUtil.isBlank(dto.getInstallName()) && dto.getType() == 0){
			return JsonResult.fail("请填写安装包名称");
		}
		if(EmptyUtil.isEmpty(dto.getResume())){
			return JsonResult.fail("请填写版本简述");
		}
		if(dto.getResume().length() > 150){
			return JsonResult.fail("版本简述最多不超过 150 字");
		}
		if(EmptyUtil.isEmpty(dto.getIsConstraint())){
			return JsonResult.fail("请选择是否强制升级");
		}
		if(EmptyUtil.isBlank(dto.getUrl()) && dto.getType() == 0){
			return JsonResult.fail("下载地址不能为空");
		} else if (dto.getType() == 1) {
			dto.setUrl(VersionsManageImpl.IOS_DOWNLOAD_URL);
		}
		
		// 查询当前最大的版本水平
		Integer maxVersionCode = versionsFacade.queryMaxVersionCode(dto.getType(),dto.getUser(),dto.getPlatformId());
		if(maxVersionCode != null && maxVersionCode.compareTo(dto.getVersionCode()) >= 0){
			return JsonResult.fail("版本水平需大于已存在的版本水平");
		}
		
		// 保存版本信息前,对版本号 版本类型做重复性校验
		VersionsDTO muti = new VersionsDTO();
		muti.setType(dto.getType());
		muti.setVersionsMark(dto.getVersionsMark());
		muti.setUser(dto.getUser());
		muti.setPlatformId(dto.getPlatformId());
		List<VersionsDTO> mutiList = versionsFacade.findVersionsAll(muti);
		if(EmptyUtil.isNotEmpty(mutiList)){
			return JsonResult.fail("版本已存在，请确认版本号和版本类型是否有误");
		}
		
		if(dto.getType() == 1){
			// ios默认为官方版本
			dto.setIsOfficial(1);
		} else if (dto.getType() == 0){
			// 查询版本管理列表是否存在android的官方版本,若不存在,设置为官方版本
			VersionsDTO typeDto = new VersionsDTO();
			typeDto.setType(1);
			typeDto.setIsOfficial(1);
			typeDto.setUser(dto.getUser());
			typeDto.setPlatformId(dto.getPlatformId());
			List<VersionsDTO> officialList = versionsFacade.findVersionsAll(typeDto);
			dto.setIsOfficial(EmptyUtil.isEmpty(officialList) ? 1:0);
		}
		
		dto.setVersionStatus(1);	// 版本新增版本状态默认为启用状态
		dto.setReleaseDate(new Date());		// 发布日期默认为当前日期
		
		Long versionId = versionsFacade.insertVersionsWithTx(dto);
		
		JsonResult<Map<String,Object>> result = new JsonResult<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("versionId",versionId);
		result.setData(map);
		return result;
	}

	@Override
	public JsonResult<Map<String, Object>> updateVersionsWithTx(VersionsDTO dto) {
		// 参数校验
		if(EmptyUtil.isEmpty(dto.getId())){
			return JsonResult.fail("版本id不能为空");
		}
		if(EmptyUtil.isBlank(dto.getVersionsMark())){
			return JsonResult.fail("请填写版本号");
		}
		if(EmptyUtil.isEmpty(dto.getVersionCode())){
			return JsonResult.fail("请填写版本水平");
		}
		if(EmptyUtil.isEmpty(dto.getType())){
			return JsonResult.fail("请填写版本类型");
		}
		if(EmptyUtil.isBlank(dto.getInstallName()) && dto.getType() == 0){
			return JsonResult.fail("请填写安装包名称");
		}
		if(EmptyUtil.isEmpty(dto.getResume())){
			return JsonResult.fail("请填写版本简述");
		}
		if(dto.getResume().length() > 150){
			return JsonResult.fail("版本简述最多不超过 150 字");
		}
		if(EmptyUtil.isEmpty(dto.getIsConstraint())){
			return JsonResult.fail("请选择是否强制升级");
		}
		if(EmptyUtil.isBlank(dto.getUrl()) && dto.getType() == 0){
			return JsonResult.fail("下载地址不能为空");
		} else if ( dto.getType() == 1) {
			dto.setUrl(VersionsManageImpl.IOS_DOWNLOAD_URL);
		}
		
		// 保存版本信息前,对版本号 版本类型做重复性校验
		VersionsDTO muti = new VersionsDTO();
		muti.setType(dto.getType());
		muti.setVersionsMark(dto.getVersionsMark());
		muti.setUser(dto.getUser());
		muti.setPlatformId(dto.getPlatformId());
		List<VersionsDTO> mutiList = versionsFacade.findVersionsAll(muti);
		if(EmptyUtil.isNotEmpty(mutiList)){
			for (VersionsDTO versionsDTO : mutiList) {
				if(!versionsDTO.getId().equals(dto.getId())){
					return JsonResult.fail("版本已存在，请确认版本号和版本类型是否有误");
				}
			}
		}
		
		muti.setVersionsMark(null);
		muti.setVersionCode(dto.getVersionCode());
		mutiList = versionsFacade.findVersionsAll(muti);
		if(EmptyUtil.isNotEmpty(mutiList)){
			for (VersionsDTO versionsDTO : mutiList) {
				if(!versionsDTO.getId().equals(dto.getId())){
					return JsonResult.fail("版本水平已存在，请确认版本水平和版本类型是否有误");
				}
			}
		}
				
		VersionsDTO dto_ = new VersionsDTO();
		dto_.setId(dto.getId());
		dto_.setVersionsMark(dto.getVersionsMark());
		dto_.setVersionCode(dto.getVersionCode());
		dto_.setType(dto.getType());
		dto_.setInstallName(dto.getInstallName());
		dto_.setResume(dto.getResume());
		dto_.setIsConstraint(dto.getIsConstraint());
		dto_.setUrl(dto.getUrl());
		dto_.setUser(dto.getUser());
		
		int isSuccess = versionsFacade.updateVersionsWithTx(dto_);
		
		JsonResult<Map<String,Object>> result = new JsonResult<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("isSuccess",isSuccess);
		result.setData(map);
		return result;
	}

	@Override
	public int deleteVersionsWithTx(VersionsDTO dto) {
		return versionsFacade.deleteVersionsWithTx(dto);
	}

	@Override
	public JsonResult<Map<String, Object>> validate(Integer vCode, Integer type, Integer user, Long platformId) {
		// 查询最高版本
		// 是否存在同类型的版本水平高于当前版本的启用状态下的新版本
		VersionsDTO latestVersion = versionsFacade.queryLatestVersion(type,user);
		if (latestVersion == null)
			return JsonResult.fail("暂无版本更新信息");
		// 兼容老版本
		// 如果vCode为空
		if (vCode == null) {
			// 直接返回最高版本,并强制更新
			VersionValidateVO vo = new VersionValidateVO();
			vo.setUrl(latestVersion.getUrl());
			vo.setVersionName(latestVersion.getVersionsMark());
			vo.setDescription(latestVersion.getDescription());
			vo.setNeedUpdate(true);
			vo.setvCode(latestVersion.getVersionCode());
			vo.setResume(latestVersion.getResume());
			return JsonResult.success("version", vo);
		}
		VersionValidateVO vo = new VersionValidateVO();
		vo.setUrl(latestVersion.getUrl());
		vo.setVersionName(latestVersion.getVersionsMark());
		vo.setDescription(latestVersion.getDescription());
		vo.setvCode(latestVersion.getVersionCode());
		vo.setResume(latestVersion.getResume());
		
		if (latestVersion.getIsConstraint().intValue() == 0) {
			// 最新版本属于非强制更新
			VersionsDTO currVersion = versionsFacade.queryVerisonByVersionCode(vCode, type,user,platformId);
			if (currVersion == null) {
				// 查不到当前版本信息
				vo.setNeedUpdate(true);// 必须更新
			} else {
				// 检查当前版本到最新版本之间是否存在强制更新版本
				List<VersionsDTO> laterVersions = versionsFacade.queryLaterVersionsByVersionCode(vCode, type);
				boolean needUpdateExist = false;
				for (VersionsDTO v : laterVersions) {
					if (v.getIsConstraint().intValue() == 1) {
						needUpdateExist = true;
						break;
					}
				}
				if (needUpdateExist) {
					vo.setNeedUpdate(true);
				} else {
					vo.setNeedUpdate(false);
				}
			}
		} else if (vCode.intValue() == latestVersion.getVersionCode().intValue() ){
			// 如果当前版本就是最新版本,属于非强制更新
			vo.setNeedUpdate(false);
		} else {
			vo.setNeedUpdate(true);
		}
		return JsonResult.success("version", vo);
	}

	@Override
	public JsonResult<Map<String, Object>> updatePartialVersionsWithTx(VersionsDTO dto, Integer updateTarget) {
		// 参数校验
		if(EmptyUtil.isEmpty(dto.getId())){
			return JsonResult.fail("版本id不能为空");
		}
		if(EmptyUtil.isEmpty(updateTarget) || (updateTarget != 0 && updateTarget != 1)){
			return JsonResult.fail("请选择更新对象");
		}
		if(updateTarget == 0 && EmptyUtil.isEmpty(dto.getVersionStatus())){
			return JsonResult.fail("版本状态不能为空");
		} else if (updateTarget == 1 && EmptyUtil.isEmpty(dto.getIsOfficial())){
			return JsonResult.fail("官网版本不能为空");
		}
		
		VersionsDTO dto_ = new VersionsDTO();
		dto_.setId(dto.getId());
		if(updateTarget == 0){
			dto_.setVersionStatus(dto.getVersionStatus());
		} else if (updateTarget == 1){
			// 更新为官网版本状态之前,判断更新对象类型是否是安卓,然后将安卓的所有官网版本状态改为非官网版本状态
			dto_.setIsOfficial(dto.getIsOfficial());
			VersionsDTO v = versionsFacade.findVersionsById(dto_);
			if(dto.getIsOfficial() == 0){
				return JsonResult.fail("不能自行设为非官方版本");
			}
			if(dto.getIsOfficial() == 1 && v.getType() == 0){
				versionsFacade.updateVersionsOfficialByTypeWithTx(v.getUser());
			}
		}

		int isSuccess = versionsFacade.updateVersionsWithTx(dto_);
		
		JsonResult<Map<String,Object>> result = new JsonResult<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("isSuccess",isSuccess);
		result.setData(map);
		return result;
	}

	@Override
	public PageResult<VersionsDTO> findVersionsOfPageByBlurry(VersionsDTO dto, Pagination page) {
		return versionsFacade.findVersionsOfPageByBlurry(dto, page);
	}

	@Override
	public PageResult<VersionsDTO> getVersionsOfPage(VersionsDTO dto, Pagination page) {
		return versionsFacade.getVersionsOfPage( dto,page);
	}
}
