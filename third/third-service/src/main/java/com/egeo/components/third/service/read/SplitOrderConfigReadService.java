package com.egeo.components.third.service.read;

import com.egeo.components.third.dto.SplitOrderConfigDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface SplitOrderConfigReadService {

    public SplitOrderConfigDTO findSplitOrderConfigById(SplitOrderConfigDTO dto);

    public PageResult<SplitOrderConfigDTO> findSplitOrderConfigOfPage(SplitOrderConfigDTO dto, Pagination page);

    public List<SplitOrderConfigDTO> findSplitOrderConfigAll(SplitOrderConfigDTO dto);
}
