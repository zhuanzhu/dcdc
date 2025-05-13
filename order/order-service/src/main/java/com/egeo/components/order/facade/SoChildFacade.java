package com.egeo.components.order.facade;

import com.egeo.components.order.dto.SoChildDTO;
import com.egeo.components.order.dto.SoItemDTO;
import com.egeo.components.order.service.write.SoItemWriteService;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.log.XLogger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class SoChildFacade {
    private static final XLogger logger = XLogger.getLogger(SoChildFacade.class);

    @Resource
    private SoItemWriteService soItemWriteService;


    /**
     * 重新计算商品运费
     * @param soItemDTOS
     * @param soChildDTO
     */
    public void reSplitItemFee(List<SoItemDTO> soItemDTOS, SoChildDTO soChildDTO){
        recalculateFee(soItemDTOS,soChildDTO).forEach(item->soItemWriteService.updateSoItemWithTx(item));
    }

    /**
     * 重新计算运费
     * @param soItemDTOS
     * @param soChildDTO
     */
    private List<SoItemDTO> recalculateFee(List<SoItemDTO> soItemDTOS, SoChildDTO soChildDTO){
        List<SoItemDTO> result=new ArrayList<>();
        List<SoItemDTO> feeItems=new ArrayList<>();
        List<SoItemDTO> noFeeItems=new ArrayList<>();
        soItemDTOS.forEach(item->{
            if (item.getPuCount()>item.getRefundCount()){
                feeItems.add(item);
            }else if (item.getPuCount()==item.getRefundCount()){
                noFeeItems.add(item);
            }
        });
        logger.info("feeItems:{},noFeeItems:{}",feeItems.size(),noFeeItems.size());
        if (EmptyUtil.isNotEmpty(noFeeItems) && EmptyUtil.isNotEmpty(feeItems)){
            noFeeItems.forEach(item->{
                SoItemDTO updateSoItem = new SoItemDTO();
                updateSoItem.setId(item.getId());
                updateSoItem.setDeliveryFeeAver(BigDecimal.ZERO);
                result.add(updateSoItem);
            });
        }
        BigDecimal splitSumDeliverFeeAver=BigDecimal.ZERO;
        for (int i=0;i<feeItems.size();i++) {
            SoItemDTO soItem =feeItems.get(i);
            SoItemDTO updateSoItem = new SoItemDTO();
            updateSoItem.setId(soItem.getId());
            BigDecimal merchantDeliveryFee = soChildDTO.getDeliveryFee();
            BigDecimal merchantAmount = soChildDTO.getProductAmount();
            BigDecimal deliveryFeeAver=BigDecimal.ZERO;
            if (Objects.isNull(merchantDeliveryFee) || merchantDeliveryFee.compareTo(BigDecimal.ZERO) < 1 || merchantAmount.compareTo(BigDecimal.ZERO) < 1) {
                deliveryFeeAver=BigDecimal.ZERO;
            } else {
                if (i==feeItems.size()-1){
                    deliveryFeeAver=merchantDeliveryFee.subtract(splitSumDeliverFeeAver);
                }else {
                    Integer puCount=Objects.nonNull(soItem.getPuCount())?soItem.getPuCount():0;
                    deliveryFeeAver=merchantDeliveryFee.multiply(soItem.getAfterDiscountPriceAver()).multiply(new BigDecimal(puCount))
                            .divide(merchantAmount, 2, BigDecimal.ROUND_DOWN);
                }
            }
            updateSoItem.setDeliveryFeeAver(deliveryFeeAver);
            splitSumDeliverFeeAver=splitSumDeliverFeeAver.add(deliveryFeeAver);
            result.add(updateSoItem);
        }
        return result;
    }
}
