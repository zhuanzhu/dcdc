package com.egeo.components.product.business;

import com.egeo.components.product.dto.ChannelPriceDTO;
import com.egeo.components.product.vo.ChannelPriceAuditingVO;

import java.util.List;

/**
 * @Description 渠道产品接口服务
 * @Author lsl
 * @Version V1.0
 **/
public interface ChannelProductManage {

    public List<ChannelPriceAuditingVO> findChannelProductForPriceAuditing(String channelCode,List<ChannelPriceDTO> dto);
}
