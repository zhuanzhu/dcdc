package com.egeo.components.promotion.controller.client;

import com.egeo.components.promotion.business.BuyCardItemManage;
import com.egeo.components.promotion.business.BuyCardItemRefundManage;
import com.egeo.components.promotion.business.CardUseRecordManage;
import com.egeo.components.promotion.business.UserCardRecordManage;
import com.egeo.components.promotion.client.BuyCardClient;
import com.egeo.components.promotion.dto.BuyCardItemDTO;
import com.egeo.components.promotion.dto.BuyCardItemRefundDTO;
import com.egeo.components.promotion.dto.CardUseRecordDTO;
import com.egeo.components.promotion.dto.UserCardRecordDTO;
import com.egeo.components.promotion.enums.CardStateEnum;
import com.egeo.components.promotion.vo.BuyCardRefundReqVO;
import com.egeo.components.promotion.vo.UseCardReqVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
@Controller
@RequestMapping("/client/promotion/buyCard")
public class BuyCardController implements BuyCardClient {

    @Resource(name="userCardRecord")
    private UserCardRecordManage userCardRecordManage;

    @Resource(name="buyCardItem")
    private BuyCardItemManage buyCardItemManage;

    @Resource(name="buyCardItemRefund")
    private BuyCardItemRefundManage buyCardItemRefundManage;

    @Resource(name = "cardUseRecord")
    private CardUseRecordManage cardUseRecordManage;

    @RequestMapping(value = "/findUserCardRecordAll", method = { RequestMethod.POST })
    @ResponseBody
    @Override
    public List<UserCardRecordDTO> findUserCardRecordAll(@RequestBody UserCardRecordDTO dto) {
        if(dto.getCardState() == null){
            dto.setCardState(CardStateEnum.EFFECTIVE.getState());
        }
        List<UserCardRecordDTO> rt = userCardRecordManage.findClientUserCardRecordAll(dto);
        return rt;
    }

    @RequestMapping(value = "/findUserCardRecordAllLike", method = { RequestMethod.POST })
    @ResponseBody
    @Override
    public List<UserCardRecordDTO> findUserCardRecordAllLike(@RequestBody UserCardRecordDTO dto) {
        List<UserCardRecordDTO> rt = userCardRecordManage.findClientUserCardRecordAll(dto);
        return rt;
    }

    @RequestMapping(value = { "/sumUserCardCash" }, method = { RequestMethod.POST })
    @ResponseBody
    @Override
    public BigDecimal sumUserCardCash(@RequestBody UserCardRecordDTO dto){
        if(dto.getCardState() == null){
            dto.setCardState(CardStateEnum.EFFECTIVE.getState());
        }
        return userCardRecordManage.sumUserCardCash(dto);
    }

    @RequestMapping(value = { "/useCard" }, method = { RequestMethod.POST })
    @ResponseBody
    @Override
    public Boolean useCard(@RequestBody UseCardReqVO vo){
        return userCardRecordManage.useCard(vo);
    }

    /**
     * @Description 执行让用户卡片记录过期
     **/
    @RequestMapping(value = { "/cancelUserCard" }, method = { RequestMethod.POST })
    @ResponseBody
    @Override
    public Boolean cancelUserCard(@RequestBody UserCardRecordDTO dto){
        return userCardRecordManage.cancelUserCard();
    }

    /**
     * @Description 查询卡劵使用明细记录
     **/
    @RequestMapping(value = { "/findBuyCardItem" }, method = { RequestMethod.POST })
    @ResponseBody
    @Override
    public List<BuyCardItemDTO>  findBuyCardItem(@RequestBody BuyCardItemDTO dto){
       return buyCardItemManage.findBuyCardItemAll(dto);
    }

    /**
     * @Description 查询卡劵退款明细记录
     **/
    @RequestMapping(value = { "/findBuyCardItemRefund" }, method = { RequestMethod.POST })
    @ResponseBody
    @Override
    public List<BuyCardItemRefundDTO>  findBuyCardItemRefund(@RequestBody BuyCardItemRefundDTO dto){

        return buyCardItemRefundManage.findBuyCardItemRefundAll(dto);
    }

    /**
     * @Description 执行退款
     **/
    @RequestMapping(value = { "/buyCardRefund" }, method = { RequestMethod.POST })
    @ResponseBody
    @Override
    public Boolean buyCardRefund(@RequestBody BuyCardRefundReqVO vo){

        return userCardRecordManage.buyCardRefund(vo.getDtos());
    }

    @RequestMapping(value = { "/findCardUseRecordAll" }, method = { RequestMethod.POST })
    @ResponseBody
    @Override
    public List<CardUseRecordDTO> findCardUseRecordAll(@RequestBody CardUseRecordDTO dto){

        return cardUseRecordManage.findCardUseRecordAll(dto);
    }
}
