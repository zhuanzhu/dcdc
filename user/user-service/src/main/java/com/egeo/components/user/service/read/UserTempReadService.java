package com.egeo.components.user.service.read;


import java.util.List;
	
import com.egeo.components.user.dto.UserTempDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface UserTempReadService {

	public UserTempDTO findUserTempById(UserTempDTO dto);

	public PageResult<UserTempDTO> findUserTempOfPage(UserTempDTO dto,Pagination page);

	public List<UserTempDTO> findUserTempAll(UserTempDTO dto);
	/**
	 * 根据用户id查询预导入数据id
	 * @param createUserid 用户id
	 * @param platformId 平台id
	 * @return
	 */
	public List<Long> findIdsByCreateUserid(Long createUserid, Long platformId);
}
	