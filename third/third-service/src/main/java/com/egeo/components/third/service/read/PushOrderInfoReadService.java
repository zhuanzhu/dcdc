package com.egeo.components.third.service.read;

import com.egeo.components.third.dto.PushOrderInfoDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface PushOrderInfoReadService {

    public PushOrderInfoDTO findPushOrderInfoById(PushOrderInfoDTO dto);

    public PageResult<PushOrderInfoDTO> findPushOrderInfoOfPage(PushOrderInfoDTO dto, Pagination page);

    public List<PushOrderInfoDTO> findPushOrderInfoAll(PushOrderInfoDTO dto);
}
