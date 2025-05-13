package com.egeo.components.user.facade;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.config.client.HeadImportRecordsClient;
import com.egeo.components.config.client.ImportRecordsClient;
import com.egeo.components.config.dto.HeadImportRecordsDTO;
import com.egeo.components.config.dto.ImportRecordsDTO;
import com.egeo.components.user.dto.InfoDTO;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.components.user.dto.UserInfoDTO;
import com.egeo.components.user.service.read.InfoReadService;
import com.egeo.components.user.service.read.SendWayTypeReadService;
import com.egeo.components.user.service.read.UserExtendReadService;
import com.egeo.components.user.service.read.UserInfoReadService;
import com.egeo.components.user.service.read.UserReadService;
import com.egeo.components.user.service.write.InfoWriteService;
import com.egeo.components.user.service.write.SendInfoWriteService;
import com.egeo.components.user.service.write.UserInfoWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;

@Component
public class InfoFacade {

	@Resource
	private InfoReadService infoReadService;

	@Resource
	private InfoWriteService infoWriteService;

	@Resource
	private UserInfoReadService userInfoReadService;

	@Resource
	private UserExtendReadService userExtendReadService;
	
	@Resource
	private SendInfoWriteService sendInfoWriteService;
	
	@Resource
	private SendWayTypeReadService sendWayTypeReadService;
	
	@Resource
	private UserInfoWriteService userInfoWriteService;

	@Resource
	private UserReadService userReadService;
	
	@Autowired
	private HeadImportRecordsClient headImportRecordsReadService;
	
	@Autowired
	private HeadImportRecordsClient headImportRecordsWriteService;
	
	@Autowired
	private ImportRecordsClient importRecordsWriteService;


	public InfoDTO findInfoById(InfoDTO dto) {

		return infoReadService.findInfoById(dto);
	}

	public PageResult<Map<String, Object>> findInfoOfPage(InfoDTO dto, Pagination page) {
		PageResult<InfoDTO> rt = infoReadService.findInfoOfPage(dto, page);
		List<InfoDTO> list = rt.getList();
		List<Map<String, Object>> maps = new ArrayList<>();
		for (InfoDTO infoDTO : list) {
			Map<String, Object> map = new HashMap<>();
			// 根据消息id查询用户数量
			int userSum = userInfoReadService.findUserSumByInfoId(infoDTO.getId(), infoDTO.getPlatformId());
			map.put("id", infoDTO.getId());
			map.put("systemInfo", infoDTO.getSystemInfo());
			map.put("userSum", userSum);
			map.put("infoRemark", infoDTO.getInfoTemplateRemark());
			map.put("isAdmin", infoDTO.getIsAdmin());
			
			// 根据消息id查询消息发送方式
			List<String> sendWayName = sendWayTypeReadService.findSendWayTypeByInfoId(infoDTO.getId());
			StringBuilder sendWayNames = new StringBuilder();
			for (int i = 0; i < sendWayName.size(); i++) {
				if(i == sendWayName.size() - 1){
					sendWayNames.append(sendWayName.get(i));
				}else{
					sendWayNames.append(sendWayName.get(i));
					sendWayNames.append(",");
				}
					
			}
			map.put("type", sendWayNames.toString());
			maps.add(map);
		}
		PageResult<Map<String, Object>> result = new PageResult<Map<String, Object>>();
		result.setList(maps);
		result.setPageNo(rt.getPageNo());
		result.setPageSize(rt.getPageSize());
		result.setTotalSize(rt.getTotalSize());
		return result;
	}

	public List<InfoDTO> findInfoAll(InfoDTO dto) {

		return infoReadService.findInfoAll(dto);

	}

	public Long insertInfoWithTx(InfoDTO dto, List<Long> sendWayIds, Integer isUserAll, List<Long> userIds, boolean isImportUser) {
		Long infoId = infoWriteService.insertInfoWithTx(dto,sendWayIds);
		dto.setId(infoId);
		if (!isImportUser) {
			if (dto.getIsAdmin() == 0) {
				// 所有用户
				if (isUserAll == 1) {
					// 根据是否为管理员查询所有用户
					userIds = userExtendReadService.findUserIdByIsAdministrator(0);
				}
			}else{
				// 所有用户
				if (isUserAll == 1) {
					// 根据是否为管理员查询所有用户
					userIds = userExtendReadService.findUserIdByIsAdministrator(1);
				}
			}
		}
		for (Long userId : userIds) {
			dto.setUserId(userId);
			UserDTO userByID = userReadService.findUserByID(userId);
			dto.setMobile(userByID.getMobile());
			sendInfoWriteService.sendInfo(dto);
		}
		return infoId;
	}

	public int updateInfoWithTx(InfoDTO dto) {

		return infoWriteService.updateInfoWithTx(dto);
	}

	public int deleteInfoWithTx(InfoDTO dto) {

		return infoWriteService.deleteInfoWithTx(dto);

	}

	public Map<String, Object> findUserInfoOfPage(InfoDTO dto, Pagination page) {
		
		Map<String, Object> mapDate = new HashMap<>();
		PageResult<InfoDTO> rt = infoReadService.findUserInfoOfPage(dto, page);
		
		List<InfoDTO> list = rt.getList();
		List<Map<String, Object>> maps = new ArrayList<>();
		for (InfoDTO infoDTO : list) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", infoDTO.getId());
			map.put("systemInfo", infoDTO.getSystemInfo());
			map.put("type", infoDTO.getType());
			map.put("createTime", infoDTO.getCreateTime());
			
			UserInfoDTO userInfoDTO = new UserInfoDTO();
			userInfoDTO.setUserId(dto.getUserId());
			userInfoDTO.setInfoId(infoDTO.getId());
			List<UserInfoDTO> userInfoDTOList = userInfoReadService.findUserInfoAll(userInfoDTO);
			if (EmptyUtil.isNotEmpty(userInfoDTOList)) {
				map.put("isRead", userInfoDTOList.get(0).getIsRead());
			} else {
				map.put("isRead", 1);
			}
			maps.add(map);
		}
		PageResult<Map<String, Object>> result = new PageResult<Map<String, Object>>();
		result.setList(maps);
		result.setPageNo(rt.getPageNo());
		result.setPageSize(rt.getPageSize());
		result.setTotalSize(rt.getTotalSize());
		mapDate.put("result", result);
		// 根据用户id消息类型和平台id查询用户未读数量 用户消息类型：1、系统通知 2、消息通知
		int systemInfoSum =  infoReadService.findUnreadInfoSum(dto.getUserId(),1,dto.getPlatformId());
		int infoInformSum =  infoReadService.findUnreadInfoSum(dto.getUserId(),2,dto.getPlatformId());
		mapDate.put("systemInfoSum", systemInfoSum);
		mapDate.put("infoInformSum", infoInformSum);
		// 根据用户id和类型修改其消息已读
		userInfoWriteService.updateByUserIdType(dto.getUserId(),dto.getType(),dto.getPlatformId());
		return mapDate;
	}

	public UserDTO findByMailAndCompany(String mail, String companyName) {
		return userReadService.findByMailAndCompany(mail, companyName);
	}
	
	public List<HeadImportRecordsDTO> findHeadImportRecords(String serialNum, String templateType) {
		HeadImportRecordsDTO dto = new HeadImportRecordsDTO();
		dto.setFileSequenceNumber(serialNum);
		dto.setTemplateType(templateType);
		return headImportRecordsReadService.findHeadImportRecordsAll(dto);
	}
	
	public void saveHeadImportRecords(String fileSequenceNumber, String templateType, Date generatedTime) {
		ImportRecordsDTO dto1 = new ImportRecordsDTO();
		dto1.setFileSequenceNumber(fileSequenceNumber);
		dto1.setTemplateType(templateType);
		dto1.setGeneratedTime(generatedTime);
		Long id = importRecordsWriteService.insertImportRecordsWithTx(dto1);
		
		HeadImportRecordsDTO dto = new HeadImportRecordsDTO();
		dto.setId(id);
		dto.setFileSequenceNumber(fileSequenceNumber);
		dto.setTemplateType(templateType);
		dto.setGeneratedTime(generatedTime);
		headImportRecordsWriteService.insertHeadImportRecordsWithTx(dto);
	}
	
}
