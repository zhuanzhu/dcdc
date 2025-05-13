package com.egeo.components.order.business.thread;

import com.egeo.components.order.common.ProductChannelCodeEnum;
import com.egeo.components.order.enums.ThirdConst;
import com.egeo.components.order.strategy.factory.OrderCreateFactory;
import com.egeo.components.order.strategy.vo.SyncCreateThirdPartyOrderReqVO;
import com.egeo.components.order.strategy.vo.SyncCreateThirdPartyOrderRespVO;
import com.egeo.utils.SpringContextTool;
import com.egeo.web.JsonResult;

import java.util.Objects;
import java.util.concurrent.Callable;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/12/6 14:57
 * @Version V1.0
 **/
public class SyncCreateThirdPartyOrderThread implements Callable<JsonResult<SyncCreateThirdPartyOrderRespVO>> {

    private SyncCreateThirdPartyOrderReqVO reqVO;

    public SyncCreateThirdPartyOrderThread(SyncCreateThirdPartyOrderReqVO reqVO) {
        this.reqVO = reqVO;
    }

    @Override
    public JsonResult<SyncCreateThirdPartyOrderRespVO> call(){
        String channelCode = getChannelCode(reqVO);
        OrderCreateFactory orderCreateFactory = SpringContextTool.getBean(OrderCreateFactory.class);
        if(!orderCreateFactory.checkHasSupportSyncCreateThirdPartyOrder(channelCode)){
            return JsonResult.fail("不支持该渠道的创建订单时推送");
        }
        return orderCreateFactory.getSyncCreateThirdPartyOrderStrategy(channelCode).syncThirdPartyOrder(reqVO);
    }

    private String getChannelCode(SyncCreateThirdPartyOrderReqVO reqVO){
        Long performingParty = reqVO.getPerformingParty();
        if(Objects.equals(performingParty, ThirdConst.Merchant.JD)){
            return ProductChannelCodeEnum.JD.getCode();
        }
        if(Objects.equals(performingParty, ThirdConst.Merchant.CAKE)){
            return ProductChannelCodeEnum.CAKE.getCode();
        }
        if(Objects.equals(performingParty, ThirdConst.Merchant.WORLD)){
            return ProductChannelCodeEnum.WORLD_BUY.getCode();
        }
        if(Objects.equals(performingParty, ThirdConst.Merchant.QM)){
            return "qm";
        }
        return null;
    }
}
