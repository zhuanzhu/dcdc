package com.egeo.components.user.facade;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.config.client.ImportRecordsClient;
import com.egeo.components.config.dto.ImportRecordsDTO;
import com.egeo.components.user.dto.UserQuitTempDTO;
import com.egeo.components.user.service.read.UserQuitTempReadService;
import com.egeo.components.user.service.write.UserQuitTempWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class UserQuitTempFacade {
	
	@Resource
	private UserQuitTempReadService userQuitTempReadService;
	
	@Resource
	private UserQuitTempWriteService userQuitTempWriteService;
	
	@Autowired
	private ImportRecordsClient importRecordsWriteService;
	
	
	public UserQuitTempDTO findUserQuitTempById(UserQuitTempDTO dto){
		
		return userQuitTempReadService.findUserQuitTempById(dto);
	}

	public PageResult<UserQuitTempDTO> findUserQuitTempOfPage(UserQuitTempDTO dto,Pagination page){
		
		return userQuitTempReadService.findUserQuitTempOfPage(dto, page);
		
	}

	public List<UserQuitTempDTO> findUserQuitTempAll(UserQuitTempDTO dto){
		
		return userQuitTempReadService.findUserQuitTempAll(dto);
		
	}

	public Long insertUserQuitTempWithTx(UserQuitTempDTO dto){
		
		return userQuitTempWriteService.insertUserQuitTempWithTx(dto);
	}

	public int updateUserQuitTempWithTx(UserQuitTempDTO dto){
		
		return userQuitTempWriteService.updateUserQuitTempWithTx(dto);
	}

	public int deleteUserQuitTempWithTx(UserQuitTempDTO dto){
		
		return userQuitTempWriteService.deleteUserQuitTempWithTx(dto);


	}

	public Map<String, Object> insertImportRecordsAndUserQuitTempList(ImportRecordsDTO importRecordsDTO,
			List<UserQuitTempDTO> userQuitTempDTOList) {
		Map<String, Object> data =new HashMap<>();
		
		//将导入信息，插入到记录表
		Long insertImportRecordsWithTx = importRecordsWriteService.insertImportRecordsWithTx(importRecordsDTO);
		
		data.put("importUserInfo", insertImportRecordsWithTx);
		
		//将离职员工存储到临时表
		String userQuitTempListIdStr = userQuitTempWriteService.insertUserQuitTempListWithTx(userQuitTempDTOList);
		
		data.put("userInfo", userQuitTempListIdStr);
		return data;
	}

    public int deleteUserQuitTempByParamsWithTx(UserQuitTempDTO quitTempDTO) {
		return userQuitTempWriteService.deleteUserQuitTempByParamsWithTx(quitTempDTO);
    }
}
	