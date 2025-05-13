package com.egeo.components.order.bean;

import com.alibaba.fastjson.JSON;
import com.egeo.common.LogConstant;
import com.egeo.common.LogTypeConstant;
import com.egeo.components.order.business.SoManage;
import com.egeo.components.order.dto.SoDTO;
import com.egeo.components.order.vo.CreateOrderRequestExtendsVO;
import com.egeo.components.order.vo.CreateOrderRequestVO;
import com.egeo.components.promotion.dto.CouponUnitDTO;
import com.egeo.components.utils.BeanUtil;
import com.egeo.entity.CacheUser;
import com.egeo.exception.BusinessException;
import com.egeo.log.EgeoBusinessLogCommon;
import com.egeo.log.EgeoLog;
import com.egeo.utils.ActiveMQUtils;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.HostUtils;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.log.XLogger;
import com.egeo.web.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
@Component
public class CreateOrderHelper {

    public XLogger logger = XLogger.getLogger(this.getClass().getName());

    private String COUPON_UNIT_LOCK_VALUE = "coupon_unit";


    @Resource(name = "so")
    private SoManage soManage;

    @Autowired
    private JedisUtil jedisUtil;



    public JsonResult checkCreateOrderRequest(CreateOrderRequestVO createOrderRequestVO,HttpServletRequest req){
        String platformId_ = req.getHeader("platformId");
        if (EmptyUtil.isEmpty(platformId_)){
            return JsonResult.fail("platformId为空");
        }
        String ip = HostUtils.getClientIP(req);
        // 根据ip获取mac地址
        try {
             HostUtils.getLocalMac(ip);
        } catch (Exception e) {
            throw new BusinessException("获取mac地址异常" + e.getMessage());
        }
        String f_ = req.getHeader("f");
        if (EmptyUtil.isEmpty(f_)) {
            return JsonResult.fail("渠道来源参数缺失");
        }
        String v = req.getHeader("v");
        if (EmptyUtil.isEmpty(v)) {
            return JsonResult.fail("应用版本号参数缺失");
        }
        String phoneModel = req.getHeader("phoneModel");
        if (EmptyUtil.isEmpty(phoneModel)) {
            return JsonResult.fail("手机型号参数缺失");
        }
        String deviceId = req.getHeader("deviceId");
        if (EmptyUtil.isEmpty(deviceId)) {
            return JsonResult.fail("设备编号参数缺失");
        }
        String os = req.getHeader("os");
        if (EmptyUtil.isEmpty(os)) {
            return JsonResult.fail("操作系统参数缺失");
        }
        return null;
    }

    public CreateOrderRequestExtendsVO toCreateOrderExtendsVO(CreateOrderRequestVO createOrderRequestVO, CacheUser userCache, HttpServletRequest req){
        CreateOrderRequestExtendsVO vo = new CreateOrderRequestExtendsVO();
        vo = BeanUtil.beanCopy(createOrderRequestVO,vo);

        //补充参数
        vo.setUserId(userCache.getId());
        vo.setCompanyId(userCache.getCompanyId());
        vo.setUserName(userCache.getName());
        vo.setPlatformId(Long.parseLong(req.getHeader("platformId")));
        if(EmptyUtil.isNotEmpty(vo.getPuId())&&vo.getPuId()==0){
            vo.setPuId(null);
            vo.setNum(null);
        }
        if(EmptyUtil.isEmpty(vo.getStoreId())){
            if(vo.getPlatformId()==7){
                logger.info("大厨管家订单");
                vo.setStoreId(1L);
            }
            if(vo.getPlatformId()==2){
                logger.info("富宏云采订单");
                vo.setStoreId(2L);
            }
        }
        //从header中获取参数并set到vo对象中
        setSystemInfo(req, vo);

        return vo;
    }

    private void setSystemInfo(HttpServletRequest req, CreateOrderRequestExtendsVO vo) {
        // 根据ip获取mac地址
        String ip = HostUtils.getClientIP(req);
        try {
            vo.setMac(HostUtils.getLocalMac(ip));
        } catch (Exception e) {
            throw new BusinessException("获取mac地址异常" + e.getMessage());
        }
        vo.setIp(ip);
        vo.setF(Integer.parseInt(req.getHeader("f")));
        vo.setV(req.getHeader("v"));
        vo.setPhoneModel(req.getHeader("phoneModel"));
        vo.setDeviceId(req.getHeader("deviceId"));
        vo.setOs(req.getHeader("os"));
    }

    public JsonResult lockOldToNew(CreateOrderRequestExtendsVO vo){
        //orderType为8时,表示以旧换新补差价
        if(EmptyUtil.isNotEmpty(vo.getOrderType())&&vo.getOrderType().equals(8)){
            if(EmptyUtil.isEmpty(vo.getExchangeCouponBatchId())||EmptyUtil.isEmpty(vo.getExchangeCouponUnitId())){
                return JsonResult.fail("以旧换新数据丢失");
            }
            CouponUnitDTO dto=soManage.findCouponUnitById(vo.getExchangeCouponUnitId());
            if(EmptyUtil.isEmpty(dto)){
                return JsonResult.fail("以旧换新的旧unit不存在");
            }
            logger.info("开始进行加锁");
            //锁定当前的旧unit
            boolean lock=true;
            try {
                lock=jedisUtil.lockWithParam(JedisUtil.COUPON_UNIT_LOCK_PRE+dto.getCouponUnitCode(),COUPON_UNIT_LOCK_VALUE,JedisUtil.COUPON_UNIT_LOCK_EXPIRE_TIME);
            } catch (InterruptedException e) {
                logger.info("[创建订单,以旧换新旧unit加锁异常],couponunitCode="+dto.getCouponUnitCode());
                jedisUtil.delLock(JedisUtil.COUPON_UNIT_LOCK_PRE+dto.getCouponUnitCode());
                e.printStackTrace();
            }
            if(!lock){
                return JsonResult.fail("当前旧优惠券unit已存在订单,如需重新兑换请先取消原订单");
            }
        }
        return null;
    }

    public void saveCreateOrderLog(JsonResult<Map<String, Object>> result,HttpServletRequest req){
        //记录创建订单日志
        if (result.getCode() == 0) {
            SoDTO newSoDTO = soManage.querySoByOrderCode((String) result.getData().get("orderCode"));
            EgeoLog log = new EgeoLog();
            log.setModuleName(LogConstant.ORDER_MANAGEMENT.getComment());
            log.setOperObject("OrderCoreAction_createOrder");
            log.setMsgId(LogConstant.ORDER_NEW.getStatus());
            log.setType(LogTypeConstant.SO.getStatus());
            log.setOperatorObjId(newSoDTO.getId());
            log.setOperatorObjCode(newSoDTO.getOrderCode());
            log.setNewObj(newSoDTO);

            EgeoBusinessLogCommon.fillLogValue(log, req);

            try {
                ActiveMQUtils.recordBusinessLog(log);
            }catch (Exception e) {
                // TODO: handle exception
                logger.error("发送日志消息失败："+ JSON.toJSONString(log));
            }
        }
    }
}
