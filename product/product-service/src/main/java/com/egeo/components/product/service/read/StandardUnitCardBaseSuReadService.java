package com.egeo.components.product.service.read;

import com.egeo.components.product.dto.StandardUnitCardBaseSuDTO;
import com.egeo.components.product.po.StandardUnitCardBaseSuPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

import java.util.Date;
import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface StandardUnitCardBaseSuReadService {

    public StandardUnitCardBaseSuDTO findStandardUnitCardBaseSuById(StandardUnitCardBaseSuDTO dto);

    public PageResult<StandardUnitCardBaseSuDTO> findStandardUnitCardBaseSuOfPage(StandardUnitCardBaseSuDTO dto, Pagination page);

    public List<StandardUnitCardBaseSuDTO> findStandardUnitCardBaseSuAll(StandardUnitCardBaseSuDTO dto);

    public  int findStandardUnitCardBaseSuNumberMax(Long cardBaseId);

    public Integer findStandardUnitSize(Long cardBaseId);

    public List<StandardUnitCardBaseSuPO> syncChannelSellState(Integer source,
                                                               Date endCheckTime,
                                                               int size);

}
