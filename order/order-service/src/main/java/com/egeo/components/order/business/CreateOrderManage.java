package com.egeo.components.order.business;

import com.egeo.components.order.vo.CreateOrderRequestExtendsVO;
import com.egeo.web.JsonResult;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Description 将创建订单重构
 * @Author lsl
 * @Version V1.0
 **/
public interface CreateOrderManage {

    /**
     * @Description 重构创建订单新方法
     **/
     JsonResult<Map<String, Object>> createOrderNew(CreateOrderRequestExtendsVO vo, HttpServletRequest req);
}
