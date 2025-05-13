package com.egeo.components.order.vo.push;

import com.egeo.components.order.dto.SoItemDTO;
import com.egeo.utils.EmptyUtil;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description todo
 * @Author lsl
 * @Date 2025/1/2 9:49
 * @Version V1.0
 **/
public class PushOrderProductExtVO extends PushOrderProductVO implements Serializable {

    private String productImg;


    public PushOrderProductExtVO(SoItemDTO soItemDTO, String userChannelSource) {
       super(soItemDTO,userChannelSource);
       this.productImg = soItemDTO.getPuPicUrl();
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }
}
