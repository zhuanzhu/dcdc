package com.egeo.components.user.business.impl;
	

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.egeo.components.config.dto.HeadImportRecordsDTO;
import com.egeo.components.user.business.InfoManage;
import com.egeo.components.user.dto.InfoDTO;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.components.user.facade.InfoFacade;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.Upload;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.web.JsonResult;

@Service("info")
public class InfoManageImpl implements InfoManage{

	
	@Resource(name="infoFacade")
	private InfoFacade infoFacade;
	@Resource
	private  JedisUtil cache;

	@Autowired
	private Upload uploadService;
	
	private static final String INFO_FILE_SEQ_PREFFIX = "info_file_seq_";
	
	private static final String INFO_USER_SEQ_PREFFIX = "info_user_seq_";

	@Override
	public InfoDTO findInfoById(InfoDTO dto) {
		return infoFacade.findInfoById(dto);
	}

	@Override
	public PageResult<Map<String, Object>> findInfoOfPage(InfoDTO dto, Pagination page) {
		return infoFacade.findInfoOfPage(dto, page);
	}

	@Override
	public List<InfoDTO> findInfoAll(InfoDTO dto) {
		return infoFacade.findInfoAll(dto);
	}

	@Override
	public Long insertInfoWithTx(InfoDTO dto,
			List<Long> sendWayIds,
			Integer isUserAll,
			List<Long> userIds, Long userId, String serialNum) {
		Long infoId = null;
		if (EmptyUtil.isNotEmpty(serialNum)) {
			List<HeadImportRecordsDTO> historyRecordList = infoFacade.findHeadImportRecords(serialNum, "消息发送名单");
			if (EmptyUtil.isNotEmpty(historyRecordList)) {
				throw new BusinessException("导入批次重复");
			}
			
			String cacheUserIdStr = (String)cache.get(INFO_FILE_SEQ_PREFFIX + userId + "_" + serialNum);
			List<Long> cacheUserIds = JSONArray.parseArray(cacheUserIdStr, Long.class);
			if (EmptyUtil.isEmpty(cacheUserIds)) {
				throw new BusinessException("导入批次未找到");
			}
			infoId = infoFacade.insertInfoWithTx(dto,sendWayIds,isUserAll,cacheUserIds, true);
			infoFacade.saveHeadImportRecords(serialNum, "消息发送名单", new Date());
			
			String userSeqStr = (String)cache.get(INFO_USER_SEQ_PREFFIX + userId);
			List<String> userSeqList;
			List<String> remainUserSeqList = new ArrayList<>();
			if (EmptyUtil.isEmpty(userSeqStr)) {
				userSeqList = JSONArray.parseArray(userSeqStr, String.class);
				for (String userSeq : userSeqList) {
					if (serialNum.equals(userSeq)) {
						cache.del(INFO_FILE_SEQ_PREFFIX + userId + "_" + userSeq);
					} else {
						remainUserSeqList.add(userSeq);
					}
				}
			}
			if (EmptyUtil.isNotEmpty(remainUserSeqList)) {
				cache.set(INFO_USER_SEQ_PREFFIX + userId, JSON.toJSONString(remainUserSeqList));
			}
			
		} else {
			infoId = infoFacade.insertInfoWithTx(dto,sendWayIds,isUserAll,userIds, false);
		}
		return infoId;
	}

	@Override
	public int updateInfoWithTx(InfoDTO dto) {
		return infoFacade.updateInfoWithTx(dto);
	}

	@Override
	public int deleteInfoWithTx(InfoDTO dto) {
		return infoFacade.deleteInfoWithTx(dto);
	}
	
	@Override
	public Map<String, Object> findUserInfoOfPage(InfoDTO dto, Pagination page) {
		return infoFacade.findUserInfoOfPage(dto, page);
	}

	@Override
	public JsonResult<Map<String,Object>> parseSendUser(List<Map<String, Object>> valueList, Long userId) {
		Map<String, String> headInfo = new HashMap<>();
		String checkHeader = checkHeader(valueList, headInfo);
		if (EmptyUtil.isNotEmpty(checkHeader)) {
			return JsonResult.fail(checkHeader);
		}
		List<HeadImportRecordsDTO> historyRecordList = infoFacade.findHeadImportRecords(headInfo.get("fileSerialNum"), "消息发送名单");
		if (EmptyUtil.isNotEmpty(historyRecordList)) {
			throw new BusinessException("导入批次重复");
		}
		
		List<Map<String, Object>> errorList = new ArrayList<>();
		List<Map<String, Object>> userList = new ArrayList<>();
		List<Long> userIds = new ArrayList<>();
		checkList(valueList, errorList, userList, userIds);
		if (errorList.isEmpty()) {
			Map<String, Object> data = new HashMap<>();
			
			data.put("headInfo", headInfo);
			data.put("userList", userList);
			
			cache.set(INFO_FILE_SEQ_PREFFIX + userId + "_" + headInfo.get("fileSerialNum"), JSON.toJSONString(userIds));
			
			String userSeqStr = (String)cache.get(INFO_USER_SEQ_PREFFIX + userId);
			List<String> userSeqList;
			if (EmptyUtil.isNotEmpty(userSeqStr)) {
				userSeqList = JSONArray.parseArray(userSeqStr, String.class);
			} else {
				userSeqList = new ArrayList<>();
			}
			userSeqList.add(headInfo.get("fileSerialNum"));
			cache.set(INFO_USER_SEQ_PREFFIX + userId, JSON.toJSONString(userSeqList));
			
			return JsonResult.success("success", data);
		} else {
			String repUrl = createErrorFile(errorList);
			return JsonResult.fail(repUrl, 169);
		}
	}

	private String checkHeader(List<Map<String, Object>> valueList, Map<String, String> headInfo) {
		Map<String,Object> row0 = valueList.get(0);
		String templateName = row0.get("CELL2") == null ? null : row0.get("CELL2").toString();
		String createTime = row0.get("CELL4") == null ? null : row0.get("CELL4").toString();
		String fileSerialNum = row0.get("CELL6") == null ? null : row0.get("CELL6").toString();
		if (EmptyUtil.isEmpty(templateName)) {
			return "模板名称为空";
		}
		if (EmptyUtil.isEmpty(createTime)) {
			return "创建时间为空";
		}
		if (EmptyUtil.isEmpty(fileSerialNum)) {
			return "文件序号为空";
		}
		headInfo.put("templateName", templateName);
		headInfo.put("createTime", createTime);
		headInfo.put("fileSerialNum", fileSerialNum);
		return null;
	}
	
	private void checkList(List<Map<String, Object>> valueList, List<Map<String, Object>> errorList, List<Map<String, Object>> userList, List<Long> userIds) {
		for (int i = 2; i < valueList.size() ; i++) {
			Map<String, Object> row = valueList.get(i);
			Object mailObj = row.get("CELL1");
			Object companyNameObj = row.get("CELL2");
			Map<String, Object> rowMap = new HashMap<>();
			rowMap.put("mail", mailObj);
			rowMap.put("companyName", companyNameObj);
			
			if (EmptyUtil.isEmpty(mailObj) || EmptyUtil.isEmpty(companyNameObj)) {
				errorList.add(rowMap);
				continue;
			}
			
			String mail = mailObj.toString();
			String companyName = companyNameObj.toString();
			UserDTO user = infoFacade.findByMailAndCompany(mail, companyName);
			if (user == null) {
				rowMap.put("description", "对应公司下用户未找到");
				errorList.add(rowMap);
			} else {
				userList.add(rowMap);
				userIds.add(user.getId());
			}
		}
	}

	
	private String createErrorFile(List<Map<String, Object>> errorList) {
		Workbook wb = new XSSFWorkbook();
		Sheet sh = wb.createSheet("消息发送导入错误报告");

		Row headLine = sh.createRow(0);
		headLine.createCell(0).setCellValue("*邮箱账号");
		headLine.createCell(1).setCellValue("*所属企业名称");
		headLine.createCell(2).setCellValue("*问题描述");

		for (int i = 0; i < errorList.size(); i++) {
			Map<String, Object> rowMap = errorList.get(0);
			Row r = sh.createRow(i);
			Object mailObj = rowMap.get("mail");
			Object companyNameObj = rowMap.get("companyName");
			Object descriptionObj = rowMap.get("description");
			r.createCell(0).setCellValue(mailObj == null ? null : mailObj.toString());
			r.createCell(1).setCellValue(companyNameObj == null ? null : companyNameObj.toString());
			r.createCell(2).setCellValue(descriptionObj == null ? null : descriptionObj.toString());
		}
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			wb.write(bos);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("问题报告生成失败");
		}
		// 文件上传至文件服务器
		String filePath = uploadService.fastDFSUpload(bos.toByteArray(), "xlsx");
		return filePath;
	}
	
	@Override
	public void clearImportInfoCache(Long userId) {
		String userSeqStr = (String)cache.get(INFO_USER_SEQ_PREFFIX + userId);
		List<String> userSeqList;
		if (EmptyUtil.isNotEmpty(userSeqStr)) {
			userSeqList = JSONArray.parseArray(userSeqStr, String.class);
			for (String userSeq : userSeqList) {
				cache.del(INFO_FILE_SEQ_PREFFIX + userId + "_" + userSeq);
			}
		}
	}
	
}
	