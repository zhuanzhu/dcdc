package com.egeo.components.order.strategy.service;

import com.egeo.components.order.strategy.vo.SplitSoChildReqVO;
import com.egeo.components.order.strategy.vo.SplitSoChildRespVO;
import com.egeo.web.JsonResult;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/12/6 1:25
 * @Version V1.0
 **/
public interface SplitSoChildChildStrategy {

    public String getChannelCode();

    public JsonResult<SplitSoChildRespVO> splitSoChild(SplitSoChildReqVO reqVO);
}
