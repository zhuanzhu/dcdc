package com.egeo.components.product.business.impl;


import com.egeo.components.product.business.StandardUnitCardBaseSuManage;
import com.egeo.components.product.dto.StandardUnitCardBaseSuDTO;
import com.egeo.components.product.facade.StandardUnitCardBaseSuFacade;
import com.egeo.components.product.vo.StandardUnitCardBaseSuSaveBatchVO;
import org.springframework.stereotype.Service;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
@Service("standardUnitCardBaseSu")
public class StandardUnitCardBaseSuManageImpl implements StandardUnitCardBaseSuManage {

    @Resource(name="standardUnitCardBaseSuFacade")
    private StandardUnitCardBaseSuFacade standardUnitCardBaseSuFacade;

    @Override
    public StandardUnitCardBaseSuDTO findStandardUnitCardBaseSuById(StandardUnitCardBaseSuDTO dto) {
        return standardUnitCardBaseSuFacade.findStandardUnitCardBaseSuById(dto);
    }

    @Override
    public PageResult<StandardUnitCardBaseSuDTO> findStandardUnitCardBaseSuOfPage(StandardUnitCardBaseSuDTO dto, Pagination page) {
        return standardUnitCardBaseSuFacade.findStandardUnitCardBaseSuOfPage(dto, page);
    }

    @Override
    public List<StandardUnitCardBaseSuDTO> findStandardUnitCardBaseSuAll(StandardUnitCardBaseSuDTO dto) {
        return standardUnitCardBaseSuFacade.findStandardUnitCardBaseSuAll(dto);
    }

    @Override
    public Long insertStandardUnitCardBaseSuWithTx(StandardUnitCardBaseSuDTO dto) {
        return standardUnitCardBaseSuFacade.insertStandardUnitCardBaseSuWithTx(dto);
    }

    @Override
    public int updateStandardUnitCardBaseSuWithTx(StandardUnitCardBaseSuDTO dto) {
        return standardUnitCardBaseSuFacade.updateStandardUnitCardBaseSuWithTx(dto);
    }

    @Override
    public int deleteStandardUnitCardBaseSuWithTx(StandardUnitCardBaseSuDTO dto) {
        return standardUnitCardBaseSuFacade.deleteStandardUnitCardBaseSuWithTx(dto);
    }

    /**
     * 批量保存卡片（模板）和su商品关系
     * @param vo
     * @return
     */
    @Override
    public boolean saveStandardUnitCardBaseSuAllWithTx(StandardUnitCardBaseSuSaveBatchVO vo){
       return  standardUnitCardBaseSuFacade.saveStandardUnitCardBaseSuAllWithTx(vo);
    }
    /**
     * 根据su卡片id修改排序
     * @param standardUnitCardBaseSuId
     * @param sortValue
     * @return
     */
    @Override
    public boolean sortValueByStandardUnitCardBaseSuIdWithTx(Long standardUnitCardBaseSuId, Integer sortValue) {

        return standardUnitCardBaseSuFacade.sortValueByStandardUnitCardBaseSuIdWithTx(standardUnitCardBaseSuId, sortValue);
    }
}
