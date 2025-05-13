package com.egeo.components.order.business.thread;

import com.egeo.components.order.common.ProductChannelCodeEnum;
import com.egeo.components.order.enums.ThirdConst;
import com.egeo.components.order.strategy.factory.OrderCreateFactory;
import com.egeo.components.order.strategy.vo.SplitSoChildReqVO;
import com.egeo.components.order.strategy.vo.SplitSoChildRespVO;
import com.egeo.utils.SpringContextTool;
import com.egeo.web.JsonResult;

import java.util.Objects;
import java.util.concurrent.Callable;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/12/6 12:35
 * @Version V1.0
 **/
public class GenerateSoChildThread implements Callable<JsonResult<SplitSoChildRespVO>> {

    private SplitSoChildReqVO reqVO;

    public GenerateSoChildThread(SplitSoChildReqVO reqVO) {
        this.reqVO = reqVO;
    }

    @Override
    public JsonResult<SplitSoChildRespVO> call() {
        String channelCode =mIdToChannelCode(reqVO);
        OrderCreateFactory orderCreateFactory = SpringContextTool.getBean(OrderCreateFactory.class);
        JsonResult<SplitSoChildRespVO> rt = orderCreateFactory.getSplitSoChildChildStrategy(channelCode).splitSoChild(reqVO);
        return rt;
    }

    private String mIdToChannelCode(SplitSoChildReqVO reqVO){
        String channelCode =ProductChannelCodeEnum.DEFAULT.getCode();
        if(reqVO.getmId() ==1L){
            return ProductChannelCodeEnum.SELF.getCode();
        }
        if(Objects.equals(reqVO.getmId(), ThirdConst.Merchant.CAKE)){
            return ProductChannelCodeEnum.CAKE.getCode();
        }
        if(Objects.equals(reqVO.getmId(), ThirdConst.Merchant.WORLD)){
            return ProductChannelCodeEnum.WORLD_BUY.getCode();
        }
        return channelCode;
    }
}
