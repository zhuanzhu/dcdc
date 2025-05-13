package com.egeo.components.order.controller.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.egeo.components.order.business.QingMeiOrderManage;
import com.egeo.components.order.dto.QingMeiOrderDTO;
import com.egeo.components.order.dto.QingMeiResult;
import com.egeo.components.order.dto.QmOrderRefundDTO;
import com.egeo.components.order.dto.QmOrderStatusUpdateDTO;
import com.egeo.components.utils.QingMeiUtil;
import com.egeo.exception.BusinessException;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.web.BaseSpringController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api/order/qingMeiOrder")
public class QingMeiOrderAction extends BaseSpringController {

    @Resource
    private QingMeiOrderManage qingMeiOrderManage;
    @Resource
    private JedisUtil jedisUtil;
    @Resource
    private QingMeiUtil qingMeiUtil;
    /**
     * 统一下单接口
     * @param param
     * @param req
     * @return
     */
    @RequestMapping(value = "/unifiedorder",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public QingMeiResult<Map<String,Object>> unifiedOrder(@RequestBody Map param, HttpServletRequest req) {
        logger.info("清美unifiedOrder:{}",JSON.toJSONString(param));
        QingMeiOrderDTO orderDTO=JSONObject.parseObject(JSONObject.toJSONString(param), QingMeiOrderDTO.class);
        QingMeiResult<Map<String,Object>> result=new QingMeiResult<>();
        String lockKey="qm_unifiedOrder_lock"+orderDTO.getOutTradeNo();
        try {
            qingMeiOrderManage.verifyUnifiedOrder(orderDTO);
            boolean lock=jedisUtil.lockWithParam(lockKey,"1",JedisUtil.ORDER_CANCEL_LOCK_EXPIRE_TIME);
            if (!lock){
                return QingMeiResult.fail("外部交易单号outTradeNo"+orderDTO.getOutTradeNo()+"正在执行统一下单,请稍后重试","0");
            }
            if (param.containsKey("tradeList")){
                param.put("tradeList",JSONObject.toJSONString(param.get("tradeList")));
            }
            qingMeiOrderManage.checkSign(param);
            String orderCode=qingMeiOrderManage.unifiedOrderWithTx(orderDTO,req);
           /* String payUrl = qingMeiUtil.getPayUrl();
            if(EmptyUtil.isNotEmpty(payUrl) && payUrl.indexOf("qm.payUrl=") !=-1){
                payUrl = payUrl.replace("qm.payUrl=","");
            }*/
            Map<String,Object> map=new HashMap<>();
            map.put("tradeNo",orderCode);
            map.put("payUrl",qingMeiUtil.getPayUrl()+orderCode);
            result.setData(map);
            result.setMsg("下单成功");
        }catch (BusinessException businessException){
            logger.error("清美unifiedOrder:{}", businessException.getMessage());
            result=QingMeiResult.fail(businessException.getMessage(),"0");
        }catch (Exception e){
            e.printStackTrace();
            logger.error("清美unifiedOrder:{}", e);
            result=QingMeiResult.fail("系统异常,请稍后重试","0");
        }finally {
            jedisUtil.delLock(lockKey);
        }
        logger.info("清美unifiedOrder,result:{}",JSON.toJSONString(result));
        return result;
    }

    /**
     *订单状态更新
     * @param statusDTO
     * @param req
     * @return
     */
    @RequestMapping(value = "/orderState",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public QingMeiResult orderState(@RequestBody QmOrderStatusUpdateDTO statusDTO, HttpServletRequest req){
        logger.info("清美orderState:{}",JSON.toJSONString(statusDTO));
        QingMeiResult<Map<String,Object>> result=new QingMeiResult<>();
        String lockKey="qm_orderState_lock"+statusDTO.getOrderNo();
        try {
            qingMeiOrderManage.verifyOrderState(statusDTO);
            boolean lock=jedisUtil.lockWithParam(lockKey,"1",JedisUtil.ORDER_CANCEL_LOCK_EXPIRE_TIME);
            if (!lock){
                return QingMeiResult.fail("子订单号orderNo"+statusDTO.getOrderNo()+"正在执行订单状态更新,请稍后重试","0");
            }
            qingMeiOrderManage.orderStateWithTx(statusDTO,req);
            result.setMsg("操作成功");
        }catch (BusinessException businessException){
            logger.error("清美orderState:{}", businessException.getMessage());
            result=QingMeiResult.fail(businessException.getMessage(),"0");
        }catch (Exception e){
            e.printStackTrace();
            logger.error("清美orderState:{}", e);
            result=QingMeiResult.fail("系统异常,请稍后重试","0");
        }finally {
            jedisUtil.delLock(lockKey);
        }
        logger.info("清美orderState,result:{}",JSON.toJSONString(result));
        return result;
    }

    /**
     * 订单退款
     * @param param
     * @param req
     * @return
     */
    @RequestMapping(value = "/refund",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public QingMeiResult refund(@RequestBody Map param, HttpServletRequest req){
        logger.info("清美refund:{}",JSON.toJSONString(param));
        QmOrderRefundDTO refundDTO=JSONObject.parseObject(JSONObject.toJSONString(param), QmOrderRefundDTO.class);
        QingMeiResult<Map<String,Object>> result=new QingMeiResult<>();
        String lockKey="qm_orderRefund_lock"+refundDTO.getRefundNo();
        try {
            qingMeiOrderManage.verifyOrderRefund(refundDTO);
            boolean lock=jedisUtil.lockWithParam(lockKey,"1",JedisUtil.ORDER_CANCEL_LOCK_EXPIRE_TIME);
            if (!lock){
                return QingMeiResult.fail("退款单号refundNo:"+refundDTO.getRefundNo()+"正在执行订单退款操作,请稍后重试","400");
            }
            if (param.containsKey("goodsList")){
                param.put("goodsList",JSONObject.toJSONString(param.get("goodsList")));
            }
            qingMeiOrderManage.checkSign(param);
            String refundTradeNo=qingMeiOrderManage.orderRefundWithTx(refundDTO,req);
            Map<String,Object> data=new HashMap<>();
            data.put("refundTradeNo",refundTradeNo);
            result.setData(data);
            result.setMsg("操作成功");
        }catch (BusinessException businessException){
            logger.error("清美refund:{}", businessException.getMessage());
            result=QingMeiResult.fail(businessException.getMessage(),"400");
        }catch (Exception e){
            e.printStackTrace();
            logger.error("清美refund:{}", e);
            result=QingMeiResult.fail("系统异常,请稍后重试","400");
        }finally {
            jedisUtil.delLock(lockKey);
        }
        logger.info("清美refund,result:{}",JSON.toJSONString(result));
        return result;
    }


}
