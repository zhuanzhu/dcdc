package com.egeo.components.product.controller.client;

import com.egeo.components.product.business.StandardUnitCombinationSuManage;
import com.egeo.components.product.client.CardCombinationClient;
import com.egeo.components.product.vo.CardCombinationCheckReqVO;
import com.egeo.components.product.vo.CardCombinationCheckRespVO;
import com.egeo.components.product.vo.UserCardCombinationReqVO;
import com.egeo.components.product.vo.UserCardCombinationRespVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Description todo
 * @Author lsl
 * @Date 2025/3/25 12:33
 * @Version V1.0
 **/
@Controller
@RequestMapping("/client/product/cardCombination")
public class CardCombinationController implements CardCombinationClient {

    @Resource
    private StandardUnitCombinationSuManage standardUnitCombinationSuManage;

    @Override
    @RequestMapping(value = "/checkCardCombination", method = { RequestMethod.POST })
    @ResponseBody
    public CardCombinationCheckRespVO checkCardCombination(@RequestBody CardCombinationCheckReqVO vo) {
        return standardUnitCombinationSuManage.checkCardCombination(vo);
    }

    @Override
    @RequestMapping(value = "/getCardCombination", method = { RequestMethod.POST })
    @ResponseBody
    public UserCardCombinationRespVO getCardCombination(@RequestBody UserCardCombinationReqVO vo){
        return standardUnitCombinationSuManage.getCardCombination(vo);
    }
}
