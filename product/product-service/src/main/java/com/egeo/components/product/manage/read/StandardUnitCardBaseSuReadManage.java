package com.egeo.components.product.manage.read;

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
public interface StandardUnitCardBaseSuReadManage {


    public StandardUnitCardBaseSuPO findStandardUnitCardBaseSuById(StandardUnitCardBaseSuPO po);

    public PageResult<StandardUnitCardBaseSuPO> findStandardUnitCardBaseSuOfPage(StandardUnitCardBaseSuPO po, Pagination page);

    public List<StandardUnitCardBaseSuPO> findStandardUnitCardBaseSuAll(StandardUnitCardBaseSuPO po);

    public  int findStandardUnitCardBaseSuNumberMax(Long cardBaseId);

    public Integer findStandardUnitSize(Long cardBaseId);

    public List<StandardUnitCardBaseSuPO> syncChannelSellState(Integer source,
                                                               Date endCheckTime,
                                                               int size);

}
