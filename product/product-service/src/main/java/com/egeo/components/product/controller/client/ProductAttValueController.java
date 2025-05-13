package com.egeo.components.product.controller.client;

import com.egeo.components.product.business.ProductAttValueManage;
import com.egeo.components.product.client.ProductAttValueClient;
import com.egeo.components.product.dto.ProductAttValueDTO;
import com.egeo.components.product.dto.ProductPictureDTO;
import com.egeo.components.product.vo.AttValueCustomReqVO;
import com.egeo.components.product.vo.ProductAttValueVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/12/17 17:27
 * @Version V1.0
 **/
@Controller
@RequestMapping("/client/product/productAttValue")
public class ProductAttValueController implements ProductAttValueClient {

    @Resource
    private ProductAttValueManage productAttValueManage;

    @Override
    @RequestMapping(value = "/findAttValueCustomBySpuId", method = { RequestMethod.POST })
    @ResponseBody
    public ProductAttValueDTO findAttValueCustomBySpuId(@RequestBody AttValueCustomReqVO vo){
        return productAttValueManage.findAttValueCustomBySpuId(vo);
    }
}
