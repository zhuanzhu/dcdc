package com.egeo.components.order.controller.api;

import com.egeo.components.order.business.SoRefundNewManage;
import com.egeo.components.order.dto.CancelAndRefundOrderExtendsWithTxDTO;
import com.egeo.components.order.dto.SoChildDTO;
import com.egeo.components.order.dto.SoItemDTO;
import com.egeo.components.order.service.read.SoChildReadService;
import com.egeo.components.order.service.read.SoItemReadService;
import com.egeo.dto.HttpServletRequestDTO;
import com.egeo.entity.CacheUser;
import com.egeo.exception.BusinessException;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/10/23 17:34
 * @Version V1.0
 **/
@Controller
@RequestMapping("/api/order/soRefundNew")
public class SoRefundNewAction extends BaseSpringController {

    @Resource
    private SoRefundNewManage soRefundNewManage;

    @Autowired
    private JedisUtil jedisUtil;

    @Autowired
    private SoItemReadService soItemReadService;

    @Autowired
    private SoChildReadService soChildReadService;

    private String CANCEL_LOCK_VALUE = "order_cancel";

    @RequestMapping(value = "/productRefundOrderWithTx")
    @ResponseBody
    public JsonResult productRefundOrderWithTx(@RequestBody CancelAndRefundOrderExtendsWithTxDTO dto, HttpServletRequest req){
        dto.setReq(new HttpServletRequestDTO(req));
        CacheUser userCache = getCacheUser();
        // 用户id
        Long memberId = userCache.getId();
        dto.setUserId(memberId);
        List<SoItemDTO> items =  dto.getItems();
        if(CollectionUtils.isEmpty(items)){
            return JsonResult.fail("参数不合法,退款商品未明确");
        }
        for (SoItemDTO item : items) {
            if(item.getId() ==null){
                return JsonResult.fail("参数不合法,退款商品未明确");
            }
        }
        SoItemDTO soItemDTO = items.get(0);
        SoItemDTO itemDTO = soItemReadService.getById(soItemDTO.getId());
        SoChildDTO soChildDTO = soChildReadService.findSoChildById(itemDTO.getSoChildId());
        String childCode = soChildDTO.getChildCode();
        boolean lock = true;
        try {
            lock=jedisUtil.lockWithParam(JedisUtil.ORDER_CANCEL_LOCK_KEY_PRE+childCode,CANCEL_LOCK_VALUE,JedisUtil.ORDER_CANCEL_LOCK_EXPIRE_TIME);
        } catch (InterruptedException e) {
            logger.info("获取子订单取消锁异常,childId="+childCode);
            jedisUtil.delLock(JedisUtil.ORDER_CANCEL_LOCK_KEY_PRE+childCode);
            e.printStackTrace();
        }
        if(!lock){
            return JsonResult.fail("当前子订单正在取消中,不可重复操作");
        }
        try {
            soRefundNewManage.productRefundOrderWithTx(dto);
            return JsonResult.success();
        }catch (Exception e){
            e.printStackTrace();
            logger.error("商品退款失败:{}",e);
            return JsonResult.fail(e.getMessage());
        }finally {
            jedisUtil.delLock(JedisUtil.ORDER_CANCEL_LOCK_KEY_PRE+childCode);
        }
    }
}
