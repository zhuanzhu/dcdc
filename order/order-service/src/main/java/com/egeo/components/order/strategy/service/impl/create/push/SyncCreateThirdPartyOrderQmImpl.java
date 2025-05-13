package com.egeo.components.order.strategy.service.impl.create.push;

import com.egeo.components.config.dto.CompanyConfigDTO;
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
import com.egeo.utils.cache.JedisUtil;
import com.egeo.web.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Description 创建（推送给）清美订单
 * @Author lsl
 * @Date 2024/12/6 13:49
 * @Version V1.0
 **/
@Service("syncCreateThirdPartyOrderQmImpl")
public class SyncCreateThirdPartyOrderQmImpl extends SyncCreateThirdPartyOrderBaseImpl{


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

    @Override
    public String getChannelCode() {
        return "qm";
    }

    @Override
    public JsonResult<SyncCreateThirdPartyOrderRespVO> syncThirdPartyOrder(SyncCreateThirdPartyOrderReqVO reqVO) {
        SoChildDTO soChildDTO = reqVO.getSoChildDTO();
        soChildDTO.setThirdpartySoChildStatus(Integer.valueOf(1));
        soChildWriteService.updateSoChildByCodeWithTx(soChildDTO);
        SoThirdpartyDTO oldSoThirdpartyDTO = soThirdpartyReadService.findSoThirdpartyByChild(soChildDTO.getId(),soChildDTO.getChildCode());
        if(oldSoThirdpartyDTO !=null){
            SoThirdpartyDTO editSoThirdpartyDTO = new SoThirdpartyDTO();
            editSoThirdpartyDTO.setThirdpartyId(String.valueOf(soChildDTO.getThirdpartySoChildId()));
            editSoThirdpartyDTO.setThirdpartyPayAmount(soChildDTO.getThirdpartySoChildPayAmount());
            editSoThirdpartyDTO.setThirdpartyPayTime(new Date());
            editSoThirdpartyDTO.setSoChildCode(soChildDTO.getChildCode());
            soThirdpartyWriteService.updateSoThirdpartyByCodeWithTx(editSoThirdpartyDTO);
        }
        return JsonResult.success();
    }
}
