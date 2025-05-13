package com.egeo.components.product.client;

import com.egeo.components.product.vo.CardCombinationCheckReqVO;
import com.egeo.components.product.vo.CardCombinationCheckRespVO;
import com.egeo.components.product.vo.UserCardCombinationReqVO;
import com.egeo.components.product.vo.UserCardCombinationRespVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Description todo
 * @Author lsl
 * @Date 2025/3/25 12:30
 * @Version V1.0
 **/
@FeignClient(
        name = "service-product-fgj",
        contextId = "cardCombinationClient"
)
public interface CardCombinationClient {

    @RequestMapping(
            value = {"/client/product/cardCombination/checkCardCombination"},
            method = {RequestMethod.POST}
    )
    public CardCombinationCheckRespVO checkCardCombination(CardCombinationCheckReqVO vo);


    @RequestMapping(
            value = {"/client/product/cardCombination/getCardCombination"},
            method = {RequestMethod.POST}
    )
    public UserCardCombinationRespVO getCardCombination(UserCardCombinationReqVO vo);
}
