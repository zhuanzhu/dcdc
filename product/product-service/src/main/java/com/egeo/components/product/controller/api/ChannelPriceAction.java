package com.egeo.components.product.controller.api;

import com.alibaba.fastjson.JSON;
import com.egeo.components.product.business.ChannelProductManage;
import com.egeo.components.product.dao.JdPriceDAO;
import com.egeo.components.product.dao.write.ChannelPriceDAO;
import com.egeo.components.product.dto.ChannelPriceDTO;
import com.egeo.components.product.dto.JdPriceDTO;
import com.egeo.components.product.vo.ChannelPriceAuditingVO;
import com.egeo.components.product.vo.JdPriceAuditingVO;
import com.egeo.config.RuntimeContext;
import com.egeo.entity.CacheUser;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.StringUtils;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
@Controller
@RequestMapping("/api/product/channelPrice")
public class ChannelPriceAction  extends BaseSpringController {

    @Resource
    private ChannelPriceDAO channelPrice;

    @Resource(name = "channelProductManage")
    private ChannelProductManage channelProductManage;

    // 业务方法：
    @RequestMapping(value = "/set")
    @ResponseBody
    public JsonResult setChannelPrice(String channelCode,String id, String spuId, Integer priceType, String value) {
        if(RuntimeContext.cacheUser().getEnterpriseId()==null) {
            return JsonResult.fail("操作异常，请联系管理员");
        }
        Long enterpriseId = RuntimeContext.cacheUser().getEnterpriseId();
        List<ChannelPriceDTO> olds = channelPrice.findAllPriceOfEnterpriseAndSpu(channelCode,spuId, enterpriseId);
        if(olds==null || olds.size()==0) {
            ChannelPriceDTO dto =toChannelPriceDTO(channelCode,1,priceType,value,id,spuId,enterpriseId);
            int insert = channelPrice.insert(dto);
            if(insert>0) {
                return JsonResult.success();
            }
        }else if(olds.size()==1){
            ChannelPriceDTO dto =toChannelPriceDTO(channelCode,1,priceType,value,id,spuId,enterpriseId);
            dto.setId(olds.get(0).getId());
            int insert = channelPrice.update(dto);
            if(insert>0) {
                return JsonResult.success();
            }
        }else {
            for(ChannelPriceDTO one :olds) {
                channelPrice.delete(one);
            }
            ChannelPriceDTO dto =toChannelPriceDTO(channelCode,1,priceType,value,id,spuId,RuntimeContext.cacheUser().getEnterpriseId());
            int insert = channelPrice.insert(dto);
            if(insert>0) {
                return JsonResult.success();
            }
        }

        return JsonResult.fail("操作异常，请联系管理员");

    }

    private ChannelPriceDTO toChannelPriceDTO(String channelCode,Integer audit,Integer priceType,String priceValue,String pid,String supId,Long enterpriseId){
        ChannelPriceDTO dto = new ChannelPriceDTO();
        dto.setAudit(audit);
        dto.setPriceType(priceType);
        dto.setPriceValue(priceValue);
        dto.setPid(pid);
        dto.setSpuId(supId);
        dto.setEnterpriseId(enterpriseId);
        dto.setChannelCode(channelCode);
        return dto;
    }

    // 业务方法：根据id更新数据
    @RequestMapping(value = "/delete")
    @ResponseBody
    public JsonResult<String> cancelPrice(String channelCode,String spuId,String pId) {
        if(EmptyUtil.isEmpty(spuId)){
            return JsonResult.fail("产品Id不存在");
        }
        ChannelPriceDTO dto = new ChannelPriceDTO();
        dto.setPid(spuId);
        dto.setChannelCode(channelCode);
        dto.setEnterpriseId(RuntimeContext.cacheUser().getEnterpriseId());
        if(EmptyUtil.isNotEmpty(pId)){
            dto.setSpuId(pId);
        }
        logger.info("删除渠道价格参数{}", JSON.toJSONString(dto));
        channelPrice.deleteExists(dto);
        channelPrice.delete(dto);
        return JsonResult.success("删除成功");
    }

    @RequestMapping(value = "/list/auditing")
    @ResponseBody
    public JsonResult<List<ChannelPriceAuditingVO>> auditing(String channelCode, Long enterpriseId) {
        CacheUser cacheUser = RuntimeContext.cacheUser();
        if(cacheUser== null || cacheUser.getType()==null || cacheUser.getType().intValue() !=1){
            return JsonResult.fail("异常 403");
        }
        if(StringUtils.isEmpty(channelCode)){
            return JsonResult.fail("未指定渠道");
        }
        List<ChannelPriceDTO> prices ;
        if(enterpriseId==null) {
            prices = channelPrice.findAllPriceAndAudit(channelCode,1);
        }else {
            prices = channelPrice.findAllPriceOfEnterpriseAndAudit(channelCode,enterpriseId, 1);
        }
        return JsonResult.success(channelProductManage.findChannelProductForPriceAuditing(channelCode,prices));
    }

    /**
     * pass =0 拒绝  其他  通过
     * @param priceId
     * @param pass
     * @return
     */
    @RequestMapping(value = "/audit")
    @ResponseBody
    public JsonResult<String> audit(Long priceId,Integer pass) {
        if(EmptyUtil.isEmpty(priceId)||EmptyUtil.isEmpty(pass)){
            return JsonResult.fail("设置值不能为空");

        }
        ChannelPriceDTO dto = new ChannelPriceDTO();
        dto.setId(priceId);
        dto.setAudit(pass.intValue()==0?0:2);
        channelPrice.audit(dto);
        return JsonResult.success("更新成功");
    }
}
