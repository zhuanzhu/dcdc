package com.egeo.components.product.facade;

import com.egeo.components.product.dto.StandardUnitCardBaseSuDTO;
import com.egeo.components.product.po.StandardUnitCardBaseSuPO;
import com.egeo.components.product.service.read.StandardUnitCardBaseSuReadService;
import com.egeo.components.product.service.write.StandardUnitCardBaseSuWriteService;
import com.egeo.components.product.vo.StandardUnitCardBaseSuDetailReqVO;
import com.egeo.components.product.vo.StandardUnitCardBaseSuSaveBatchVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
@Component
public class StandardUnitCardBaseSuFacade {

    @Resource
    private StandardUnitCardBaseSuReadService standardUnitCardBaseSuReadService;

    @Resource
    private StandardUnitCardBaseSuWriteService standardUnitCardBaseSuWriteService;


    public StandardUnitCardBaseSuDTO findStandardUnitCardBaseSuById(StandardUnitCardBaseSuDTO dto){

        return standardUnitCardBaseSuReadService.findStandardUnitCardBaseSuById(dto);
    }

    public PageResult<StandardUnitCardBaseSuDTO> findStandardUnitCardBaseSuOfPage(StandardUnitCardBaseSuDTO dto, Pagination page){

        return standardUnitCardBaseSuReadService.findStandardUnitCardBaseSuOfPage(dto, page);

    }

    public List<StandardUnitCardBaseSuDTO> findStandardUnitCardBaseSuAll(StandardUnitCardBaseSuDTO dto){

        return standardUnitCardBaseSuReadService.findStandardUnitCardBaseSuAll(dto);

    }
    public Long insertStandardUnitCardBaseSuWithTx(StandardUnitCardBaseSuDTO dto){

        return standardUnitCardBaseSuWriteService.insertStandardUnitCardBaseSuWithTx(dto);
    }

    public int updateStandardUnitCardBaseSuWithTx(StandardUnitCardBaseSuDTO dto){

        return standardUnitCardBaseSuWriteService.updateStandardUnitCardBaseSuWithTx(dto);
    }

    public int deleteStandardUnitCardBaseSuWithTx(StandardUnitCardBaseSuDTO dto){

        return standardUnitCardBaseSuWriteService.deleteStandardUnitCardBaseSuWithTx(dto);

    }

    /**
     * 批量保存卡片（模板）和su商品关系
     * @param vo
     * @return
     */
    public boolean saveStandardUnitCardBaseSuAllWithTx(StandardUnitCardBaseSuSaveBatchVO vo){
        boolean isTrue = false;
        int sortValue =  standardUnitCardBaseSuReadService.findStandardUnitCardBaseSuNumberMax(vo.getCardBaseId());
        List<StandardUnitCardBaseSuDetailReqVO> suDetail = vo.getSuDetail();
        StandardUnitCardBaseSuDTO dto = null;
        for (StandardUnitCardBaseSuDetailReqVO detail : suDetail) {
            String productId = detail.getProductId();
            String skuId = detail.getThirdSkuId();
            dto = new StandardUnitCardBaseSuDTO();
            dto.setStandardUnitId(Long.valueOf(skuId));
            dto.setThirdSkuId(productId);
            dto.setSource(detail.getSource());
            dto.setCardBaseId(vo.getCardBaseId());
            List<StandardUnitCardBaseSuDTO> list = standardUnitCardBaseSuReadService.findStandardUnitCardBaseSuAll(dto);
            if(CollectionUtils.isEmpty(list)){
                sortValue = sortValue+1;
                dto.setSortValue(sortValue);
                standardUnitCardBaseSuWriteService.insertStandardUnitCardBaseSuWithTx(dto);
                isTrue = true;
            }
        }
        return isTrue;
    }

    /**
     * 根据su卡片id修改排序
     * @param standardUnitCardBaseSuId
     * @param sortValue
     * @return
     */
    public boolean sortValueByStandardUnitCardBaseSuIdWithTx(Long standardUnitCardBaseSuId, Integer sortValue){
        StandardUnitCardBaseSuDTO editDTO = new StandardUnitCardBaseSuDTO();
        editDTO.setId(standardUnitCardBaseSuId);
        editDTO.setSortValue(sortValue);
        int i =  standardUnitCardBaseSuWriteService.updateStandardUnitCardBaseSuWithTx(editDTO);

        return true;
    }

    public Integer findStandardUnitSize(Long cardBaseId){
        return standardUnitCardBaseSuReadService.findStandardUnitSize(cardBaseId);
    }

    public List<StandardUnitCardBaseSuPO> syncChannelSellState(Integer source,
                                                               Date endCheckTime,
                                                               int size){
        return standardUnitCardBaseSuReadService.syncChannelSellState(source,endCheckTime,size);
    }
}
