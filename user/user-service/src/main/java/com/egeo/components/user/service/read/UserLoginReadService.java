package com.egeo.components.user.service.read;

import java.util.List;

import com.egeo.components.user.dto.UserLoginDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface UserLoginReadService {

	PageResult<UserLoginDTO> findOfPage(UserLoginDTO dto, Pagination page);

	List<UserLoginDTO> findByUserIds(List<Long> userIds, Long startTime, Long endTime);

}
	