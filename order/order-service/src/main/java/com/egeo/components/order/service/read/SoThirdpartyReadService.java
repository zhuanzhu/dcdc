package com.egeo.components.order.service.read;


import java.util.List;

import com.egeo.components.order.dto.SoDTO;
import com.egeo.components.order.dto.SoThirdpartyDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface SoThirdpartyReadService {

	public SoThirdpartyDTO findSoThirdpartyById(SoThirdpartyDTO dto);

	public PageResult<SoThirdpartyDTO> findSoThirdpartyOfPage(SoThirdpartyDTO dto,Pagination page);

	public List<SoThirdpartyDTO> findSoThirdpartyAll(SoThirdpartyDTO dto);


    List<Long> getThirdpartyIdListByStatus();

    Long findSoChildIdByThirdpartyId(Long jdOrderId);

	SoThirdpartyDTO findSoThirdpartyByChildId(Long childId);

	SoThirdpartyDTO findSoThirdpartyByChildCode(String childCode);

	SoThirdpartyDTO findSoThirdpartyByChild(Long childId,String childCode);
}
