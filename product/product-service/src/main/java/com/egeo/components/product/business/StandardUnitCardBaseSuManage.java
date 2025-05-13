package com.egeo.components.product.business;

import com.egeo.components.product.dto.StandardUnitCardBaseSuDTO;
import com.egeo.components.product.vo.StandardUnitCardBaseSuSaveBatchVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

import java.util.List;
import java.util.Map;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface StandardUnitCardBaseSuManage {

    public StandardUnitCardBaseSuDTO findStandardUnitCardBaseSuById(StandardUnitCardBaseSuDTO dto);

    public PageResult<StandardUnitCardBaseSuDTO> findStandardUnitCardBaseSuOfPage(StandardUnitCardBaseSuDTO dto,Pagination page);

    public List<StandardUnitCardBaseSuDTO> findStandardUnitCardBaseSuAll(StandardUnitCardBaseSuDTO dto);

    Long insertStandardUnitCardBaseSuWithTx(StandardUnitCardBaseSuDTO dto);

    int updateStandardUnitCardBaseSuWithTx(StandardUnitCardBaseSuDTO dto);

    int deleteStandardUnitCardBaseSuWithTx(StandardUnitCardBaseSuDTO dto);

    /**
     * 批量保存卡片（模板）和su商品关系
     * @param vo
     * @return
     */
    public boolean saveStandardUnitCardBaseSuAllWithTx(StandardUnitCardBaseSuSaveBatchVO vo);
       /**
     * 根据su卡片id修改排序
     * @param standardUnitCardBaseSuId
     * @param sortValue
     * @return
     */
    public boolean sortValueByStandardUnitCardBaseSuIdWithTx(Long standardUnitCardBaseSuId, Integer sortValue);
}
