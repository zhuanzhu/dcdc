package com.egeo.components.product.dao.read;

import com.egeo.components.product.po.StandardUnitCardBaseSuPO;
import com.egeo.orm.BaseReadDAO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface StandardUnitCardBaseSuReadDAO extends BaseReadDAO<StandardUnitCardBaseSuPO> {


    int findStandardUnitCardBaseSuNumberMax(@Param("cardBaseId")Long cardBaseId);

    /**
     * 根据卡片id查询su商品数量
     * @param cardBaseId
     * @return
     */
    Integer findStandardUnitSize(@Param("cardBaseId")Long cardBaseId);

    List<StandardUnitCardBaseSuPO> syncChannelSellState(@Param("source")Integer source,
                                                        @Param("endCheckTime") Date endCheckTime,
                                                        @Param("size")int size);
}
