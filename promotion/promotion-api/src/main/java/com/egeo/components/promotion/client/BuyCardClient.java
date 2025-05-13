package com.egeo.components.promotion.client;

import com.egeo.components.promotion.dto.BuyCardItemDTO;
import com.egeo.components.promotion.dto.BuyCardItemRefundDTO;
import com.egeo.components.promotion.dto.CardUseRecordDTO;
import com.egeo.components.promotion.dto.UserCardRecordDTO;
import com.egeo.components.promotion.vo.BuyCardRefundReqVO;
import com.egeo.components.promotion.vo.UseCardReqVO;
import com.egeo.web.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


@FeignClient(name = "service-promotion-fgj",contextId="BuyCardClient")
public interface BuyCardClient {

    @RequestMapping(value = { "/client/promotion/buyCard/findUserCardRecordAll" }, method = { RequestMethod.POST })
    public List<UserCardRecordDTO> findUserCardRecordAll(UserCardRecordDTO dto);

    @RequestMapping(value = { "/client/promotion/buyCard/findUserCardRecordAllLike" }, method = { RequestMethod.POST })
    public List<UserCardRecordDTO> findUserCardRecordAllLike(UserCardRecordDTO dto);

    @RequestMapping(value = { "/client/promotion/buyCard/sumUserCardCash" }, method = { RequestMethod.POST })
    public BigDecimal sumUserCardCash(UserCardRecordDTO dto);

    @RequestMapping(value = { "/client/promotion/buyCard/useCard" }, method = { RequestMethod.POST })
    public Boolean useCard(UseCardReqVO vo);

    /**
     * @Description 执行让用户卡片记录过期
     **/
    @RequestMapping(value = { "/client/promotion/buyCard/cancelUserCard" }, method = { RequestMethod.POST })
    public Boolean cancelUserCard(UserCardRecordDTO dto);

    /**
     * @Description 查询卡劵使用明细记录
     **/
    @RequestMapping(value = { "/client/promotion/buyCard/findBuyCardItem" }, method = { RequestMethod.POST })
    public List<BuyCardItemDTO>  findBuyCardItem(BuyCardItemDTO dto);

    /**
     * @Description 查询卡劵退款明细记录
     **/
    @RequestMapping(value = { "/client/promotion/buyCard/findBuyCardItemRefund" }, method = { RequestMethod.POST })
    public List<BuyCardItemRefundDTO>  findBuyCardItemRefund(BuyCardItemRefundDTO dto);



    /**
     * @Description 执行退款
     **/
    @RequestMapping(value = { "/client/promotion/buyCard/buyCardRefund" }, method = { RequestMethod.POST })
    public Boolean buyCardRefund(BuyCardRefundReqVO vo);

    @RequestMapping(value = { "/client/promotion/buyCard/findCardUseRecordAll" }, method = { RequestMethod.POST })
    public List<CardUseRecordDTO> findCardUseRecordAll(CardUseRecordDTO dto);
}
