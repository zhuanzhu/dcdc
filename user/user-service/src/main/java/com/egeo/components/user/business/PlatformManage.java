package com.egeo.components.user.business;

import java.util.List;

import com.egeo.components.user.vo.PlatformVO;
import com.egeo.components.user.dto.PlatformDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface PlatformManage {

    PageResult<PlatformDTO> findAll(PlatformDTO dto,Pagination page);

    int upDate(PlatformDTO dto);

    List<PlatformDTO> PlatformByUserId(Long useId);

    List<PlatformDTO> PlatformByUId(Long userId);

    Integer deleteById(PlatformVO platformVO);

    String save(PlatformDTO dto);
	

}
	