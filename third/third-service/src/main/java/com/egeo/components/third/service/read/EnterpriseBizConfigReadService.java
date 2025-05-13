package com.egeo.components.third.service.read;

import com.egeo.components.third.dto.EnterpriseBizConfigDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface EnterpriseBizConfigReadService {

    EnterpriseBizConfigDTO findEnterpriseBizConfigById(EnterpriseBizConfigDTO dto);

    PageResult<EnterpriseBizConfigDTO> findEnterpriseBizConfigOfPage(EnterpriseBizConfigDTO dto, Pagination page);

    List<EnterpriseBizConfigDTO> findEnterpriseBizConfigAll(EnterpriseBizConfigDTO dto);
}
