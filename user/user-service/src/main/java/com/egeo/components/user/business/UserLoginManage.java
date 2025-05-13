package com.egeo.components.user.business;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.egeo.components.user.vo.UserLoginVO;
import com.egeo.components.user.vo.UserVO;
import com.egeo.orm.PageResult;
import com.egeo.entity.CacheUser;
import com.egeo.orm.Pagination;
import com.egeo.web.JsonResult;

public interface UserLoginManage {

	void insertLoginLogWithTx(JsonResult<CacheUser> rt, HttpServletRequest req,String loginType,Long storeId,Long keyMessage);

	void insertLoginExceptionLogWithTx(int code, UserVO user, HttpServletRequest req);

	PageResult<Map<String, Object>> findOfPage(UserLoginVO userVO, Pagination page);

	List<Map<String, Object>> findByUserIds(List<Long> ids,Long startTime,Long endTime);

	Map<String, Object> getOpenIdByMiniProgramCode(String jsCode,String encryptedData, String iv,Long platformId);
	String mtLogin(Long userId,String jumpUrl);
}
	