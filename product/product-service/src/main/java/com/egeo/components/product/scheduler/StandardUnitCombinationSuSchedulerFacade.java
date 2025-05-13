package com.egeo.components.product.scheduler;

import com.alibaba.fastjson.JSON;
import com.egeo.components.order.vo.jd.JdResponse;
import com.egeo.components.order.vo.jd.JdSkuSellStatus;
import com.egeo.components.product.dto.StandardUnitCombinationSuDTO;
import com.egeo.components.product.facade.StandardUnitCombinationFacade;
import com.egeo.components.product.facade.StandardUnitCombinationSuFacade;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.delivery.JdUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

@Component
public class StandardUnitCombinationSuSchedulerFacade {
    Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());


    @Resource
    private StandardUnitCombinationFacade standardUnitCombinationFacade;
    @Resource
    private StandardUnitCombinationSuFacade standardUnitCombinationSuFacade;
    @Resource
    private JdUtils jdUtils;
    @Resource
    private JedisUtil jedisUtil;

    public void refreshCombinationSuSellStatus(Date endDateTime){
        List<StandardUnitCombinationSuDTO> suList= standardUnitCombinationFacade.syncJdSellState(3,endDateTime,100);
        int i=1;
        while (EmptyUtil.isNotEmpty(suList)){
            logger.info("处理同步商品组合京东可售状态:第{}次-{}条",i,suList.size());
            Set<String> skuIds=new HashSet<>();
            suList.forEach(su->skuIds.add(String.valueOf(su.getStandardUnitId())));
            String jdSkuIds=String.join(",",skuIds);
            Map<Long,Integer> skuSellStateMap=new HashMap<>();
            Map<Long,JdSkuSellStatus> jdSkuSellStatusMap=new HashMap<>();
            String token = jdUtils.getAccessToken(jedisUtil);
            //2.校验京东是否可售
            logger.info("处理同步商品组合京东可售状态request,skuIds:{}",jdSkuIds);
            String skuSellStatusFromJd = jdUtils.getSkuSellStatusFromJd(token,jdSkuIds, "");
            logger.info("处理同步商品组合京东可售状态result:{}",skuSellStatusFromJd);
            JdResponse jdSellResponse = JSON.parseObject(skuSellStatusFromJd, JdResponse.class);
            if(jdSellResponse.isSuccess()&&jdSellResponse.getResultCode().equals("0000")){
                String json = jdSellResponse.getResult();
                List<JdSkuSellStatus> jdSkuStatus = JSON.parseArray(json, JdSkuSellStatus.class);
                jdSkuStatus.forEach(state->skuSellStateMap.put(state.getSkuId(),state.getSaleState()));
                jdSkuStatus.forEach(state->jdSkuSellStatusMap.put(state.getSkuId(),state));
            }else{
                logger.error("处理同步商品组合京东可售状态-查询上下架失败:{}",jdSkuIds);
            }
            for (StandardUnitCombinationSuDTO suDTO:suList){
                StandardUnitCombinationSuDTO updateSuDTO=new StandardUnitCombinationSuDTO();
                updateSuDTO.setId(suDTO.getId());
                if (skuSellStateMap.containsKey(suDTO.getStandardUnitId())){
                    updateSuDTO.setSellState(Objects.equals(1,skuSellStateMap.get(suDTO.getStandardUnitId()))?1:0);

                }
                if (jdSkuSellStatusMap.containsKey(suDTO.getStandardUnitId())){
                    JdSkuSellStatus jdSku= jdSkuSellStatusMap.get(suDTO.getStandardUnitId());
                    updateSuDTO.setThirdSkuName(jdSku.getName());
                }
                updateSuDTO.setCheckTime(new Date());
                standardUnitCombinationSuFacade.updateStandardUnitCombinationSuWithTx(updateSuDTO);
            }
            suList= standardUnitCombinationFacade.syncJdSellState(3,endDateTime,100);
            i++;
        }
    }
}
