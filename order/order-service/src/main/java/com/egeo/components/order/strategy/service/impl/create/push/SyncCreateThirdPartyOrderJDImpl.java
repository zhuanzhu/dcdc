package com.egeo.components.order.strategy.service.impl.create.push;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.egeo.components.config.dto.CompanyConfigDTO;
import com.egeo.components.order.common.ProductChannelCodeEnum;
import com.egeo.components.order.dto.ReceiverAddressDTO;
import com.egeo.components.order.dto.SoChildDTO;
import com.egeo.components.order.dto.SoDTO;
import com.egeo.components.order.dto.SoThirdpartyDTO;
import com.egeo.components.order.service.read.SoChildReadService;
import com.egeo.components.order.service.read.SoThirdpartyReadService;
import com.egeo.components.order.service.write.SoChildWriteService;
import com.egeo.components.order.service.write.SoThirdpartyWriteService;
import com.egeo.components.order.service.write.SoWriteService;
import com.egeo.components.order.strategy.vo.SyncCreateThirdPartyOrderReqVO;
import com.egeo.components.order.strategy.vo.SyncCreateThirdPartyOrderRespVO;
import com.egeo.components.order.vo.jd.JdOrderSubmit;
import com.egeo.components.order.vo.jd.ParseAddressJson;
import com.egeo.components.product.vo.JdResponse;
import com.egeo.components.utils.JdUtils2;
import com.egeo.components.utils.RandomUtil;
import com.egeo.exception.BusinessException;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.SpringContextTool;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.delivery.JdUtils;
import com.egeo.web.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Description 创建（推送给）京东订单
 * @Author lsl
 * @Date 2024/12/6 13:46
 * @Version V1.0
 **/
@Service("syncCreateThirdPartyOrderJDImpl")
public class SyncCreateThirdPartyOrderJDImpl extends SyncCreateThirdPartyOrderBaseImpl{

    public Logger logger = LoggerFactory.getLogger(this.getClass().getName());


    @Resource
    private SoThirdpartyWriteService soThirdpartyWriteService;

    @Resource
    private SoChildReadService soChildReadService;

    @Resource
    private SoChildWriteService soChildWriteService;

    @Resource
    private SoWriteService soWriteService;

    @Resource
    private SoThirdpartyReadService soThirdpartyReadService;

    @Resource
    private JedisUtil jedisUtil;

    @Autowired
    private JdUtils jdUtils;

    @Autowired
    private JdUtils2 jdUtils2;

    @Override
    public String getChannelCode() {
        return ProductChannelCodeEnum.JD.getCode();
    }

    @Override
    public JsonResult<SyncCreateThirdPartyOrderRespVO> syncThirdPartyOrder(SyncCreateThirdPartyOrderReqVO reqVO) {
        //String accessToken = jdUtils.getAccessToken(jedisUtil);
        String accessToken = jdUtils2.getAccessToken(jedisUtil);
        SoChildDTO  soChildDTO = reqVO.getSoChildDTO();
        ReceiverAddressDTO addr = reqVO.getAddr();
        Long companyId = reqVO.getCompanyId();
        List<CompanyConfigDTO> configs = reqVO.getConfigs();
        SoDTO sodto = reqVO.getSodto();
        Long orderId = reqVO.getOrderId();
            SoChildDTO soChildDTO1 = soChildReadService.querySoChildByChildCode(soChildDTO.getChildCode());
            logger.info("对京东订单进行京东下单->"+ JSON.toJSONString(soChildDTO));
            String parseAddress = jdUtils.parseAddress(addr.getGoodReceiverCountry()+addr.getGoodReceiverProvince()+addr.getGoodReceiverCity()+addr.getGoodReceiverCounty()+addr.getGoodReceiverAddress(), accessToken);
            JSONObject jsonObject = JSONObject.parseObject(parseAddress);
            ParseAddressJson addressJson = JSON.parseObject(jsonObject.getString("result"), ParseAddressJson.class);
            //根据公司配置是否允许京东下单(true-允许,false-禁止);
            boolean jdOrderAllow=jdOrderAllow(companyId,configs);
            if((SpringContextTool.isPrd()&&jdOrderAllow) || jdUtils2.TEST_TO_PRO.equals("1")) {
                logger.info("京东生产下单流程:订单号:{}",soChildDTO.getChildCode());
                String json = jdUtils.jdOrderSubmit(accessToken, soChildDTO.getChildCode(),
                        JSON.toJSONString(soChildDTO.getSkuInfoList()), addr.getGoodReceiverName(), addressJson.getProvinceId(), addressJson.getCityId(),
                        addressJson.getCountyId(), addressJson.getTownId(), addr.getGoodReceiverAddress(), null,
                        null, addr.getGoodReceiverMobile(), addr.getGoodReceiverMobile(), null,
                        Integer.valueOf(2), Integer.valueOf(2), Integer.valueOf(5),
                        "大厨管家（上海)网络科技有限公司", Integer.valueOf(1), Integer.valueOf(4),
                        Integer.valueOf(1), Integer.valueOf(0), null,
                        "18616833602", null, null,
                        null, null, null,
                        null, null, null, null,
                        null, null, null,
                        null, null, null,
                        null, null,
                        null, null, null);

                JdResponse jdResponse = JSON.parseObject(json, JdResponse.class);
                if(jdResponse.isSuccess()&&jdResponse.getResultCode().equals("0001")){
                    //京东下单成功
                    JdOrderSubmit jdOrderSubmit = JSON.parseObject(jdResponse.getResult(), JdOrderSubmit.class);
                    Long jdOrderId = jdOrderSubmit.getJdOrderId();
                    soChildDTO.setThirdpartySoChildId(jdOrderId);
                    //更新京东运费价格
                    BigDecimal oldFree = EmptyUtil.isNotEmpty(soChildDTO1.getOrdinaryDeliveryFee())?soChildDTO1.getOrdinaryDeliveryFee():BigDecimal.ZERO;
                    JSONObject jdOrderDetail = jdUtils.getJdOrderDetail(accessToken, jdOrderId.toString());
                    if(EmptyUtil.isNotEmpty(jdOrderDetail)){
                        logger.info("正式环境--对京东订单进行京东下单成功->"+ JSON.toJSONString(jdOrderDetail));
                        BigDecimal freight = jdOrderDetail.getBigDecimal("freight");
                        BigDecimal orderAmount= jdOrderDetail.getBigDecimal("orderPrice");
                        soChildDTO.setDeliveryFee(freight);
                        soChildDTO.setOrdinaryDeliveryFee(freight);
                        soChildDTO.setThirdpartySoChildPayAmount(orderAmount);
                        soChildDTO.setThirdpartySoChildStatus(Integer.valueOf(1));
                        soChildWriteService.updateSoChildByCodeWithTx(soChildDTO);
                        BigDecimal addFree = freight.subtract(oldFree);
                        sodto.setDeliveryFee(sodto.getDeliveryFee().add(addFree));
                        sodto.setOrderDeliveryFee(sodto.getDeliveryFee().add(addFree));
                        sodto.setOrderAmount(sodto.getOrderAmount().add(addFree));
                        sodto.setOrderAmountPay(sodto.getOrderAmountPay().add(addFree));
                        sodto.setId(orderId);
                        soWriteService.updateOrderWithTX(sodto);
                        SoThirdpartyDTO oldSoThirdpartyDTO = soThirdpartyReadService.findSoThirdpartyByChild(soChildDTO.getId(),soChildDTO.getChildCode());
                        if(oldSoThirdpartyDTO !=null){
                            SoThirdpartyDTO editSoThirdpartyDTO = new SoThirdpartyDTO();
                            editSoThirdpartyDTO.setThirdpartyId(jdOrderId+"");
                            editSoThirdpartyDTO.setThirdpartyPayAmount(orderAmount);
                            editSoThirdpartyDTO.setThirdpartyPayTime(new Date());
                            editSoThirdpartyDTO.setSoChildCode(soChildDTO.getChildCode());
                            soThirdpartyWriteService.updateSoThirdpartyByCodeWithTx(editSoThirdpartyDTO);

                        }
                    }
                }else{
                    //京东下单失败
                    //cancelOrderJd(sodto,userId,ip,userName,mac,req);
                    //throw new BusinessException("订单下单失败："+jdResponse.getResultMessage());
                    return JsonResult.fail("订单下单失败："+jdResponse.getResultMessage());
                }
            }else {
                //京东下单成功
                logger.info("测试环境--对京东订单进行京东下单成功->");
                Long jdOrderId = 1000000000l+ RandomUtil.randomId();
                soChildDTO.setThirdpartySoChildId(jdOrderId);
                //更新京东运费价格
                BigDecimal oldFree = EmptyUtil.isNotEmpty(soChildDTO1.getOrdinaryDeliveryFee())?soChildDTO1.getOrdinaryDeliveryFee():BigDecimal.ZERO;
                soChildDTO.setDeliveryFee(soChildDTO1.getDeliveryFee());
                soChildDTO.setOrdinaryDeliveryFee(oldFree);

                soChildDTO.setThirdpartySoChildPayAmount(soChildDTO.getAmount());
                soChildDTO.setThirdpartySoChildStatus(Integer.valueOf(1));
                soChildWriteService.updateSoChildByCodeWithTx(soChildDTO);
                BigDecimal addFree = oldFree.subtract(oldFree);
                sodto.setDeliveryFee(sodto.getDeliveryFee().add(addFree));
                sodto.setOrderDeliveryFee(sodto.getDeliveryFee().add(addFree));
                sodto.setOrderAmount(sodto.getOrderAmount().add(addFree));
                sodto.setOrderAmountPay(sodto.getOrderAmountPay().add(addFree));
                sodto.setId(orderId);
                soWriteService.updateOrderWithTX(sodto);
                SoThirdpartyDTO oldSoThirdpartyDTO = soThirdpartyReadService.findSoThirdpartyByChild(soChildDTO.getId(),soChildDTO.getChildCode());
                if(oldSoThirdpartyDTO !=null){
                    SoThirdpartyDTO editSoThirdpartyDTO = new SoThirdpartyDTO();
                    editSoThirdpartyDTO.setThirdpartyId(jdOrderId+"");
                    editSoThirdpartyDTO.setThirdpartyPayAmount(soChildDTO.getAmount());
                    editSoThirdpartyDTO.setThirdpartyPayTime(new Date());
                    editSoThirdpartyDTO.setSoChildCode(soChildDTO.getChildCode());
                    soThirdpartyWriteService.updateSoThirdpartyByCodeWithTx(editSoThirdpartyDTO);

                }
            }

        return JsonResult.success();
    }

    /**
     * 是否允许京东下单
     * @param companyId
     * @param configs
     * @return true:允许;false:不允许
     */
    private boolean jdOrderAllow(Long companyId,List<CompanyConfigDTO> configs){
        boolean jdOrderAllow = true;
        if (EmptyUtil.isNotEmpty(configs)){
            for(CompanyConfigDTO config : configs) {
                if(config.getKey().equalsIgnoreCase("order.jd.forbid")&& config.getValue()!=null && config.getValue().length()==1) {
                    jdOrderAllow = (Integer.valueOf(config.getValue()).intValue()==1)?false:true;
                    break;
                }
            }
        }
        return jdOrderAllow;
    }
}
