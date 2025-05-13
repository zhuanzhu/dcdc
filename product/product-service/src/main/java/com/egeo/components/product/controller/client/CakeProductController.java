package com.egeo.components.product.controller.client;

import com.egeo.components.product.business.CakeProductManage;
import com.egeo.components.product.client.CakeProductClient;
import com.egeo.components.product.dto.CakeProductDetailDTO;
import com.egeo.components.product.dto.CakeProductDetailSearchDTO;
import com.egeo.components.product.dto.CakeSPUIdSearchProductDetailDTO;
import com.egeo.web.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/5/6 17:27
 * @Version V1.0
 **/
@Controller
@RequestMapping("/client/product/cakeProduct")
public class CakeProductController  implements CakeProductClient {

    @Autowired
    private CakeProductManage cakeProductManage;

    @Override
    @RequestMapping(value = "/findCakeProductById", method = { RequestMethod.POST })
    @ResponseBody
    public CakeProductDetailDTO findCakeProductById(@RequestBody CakeProductDetailSearchDTO search) {
        JsonResult<CakeProductDetailDTO> rt=  cakeProductManage.searchProductDetail(search);
        return rt.getData();
    }

    @Override
    @RequestMapping(value = "/searchProductStatus", method = { RequestMethod.POST })
    public String searchProductStatus(String productId) {
        JsonResult<String>  rt = cakeProductManage.searchProductStatus(productId);
        return rt.getData();
    }

    @Override
    @RequestMapping(value = "/findCakeProductBySkuId", method = { RequestMethod.POST })
    @ResponseBody
    public CakeProductDetailDTO findCakeProductBySkuId(@RequestBody CakeSPUIdSearchProductDetailDTO dto) {
        JsonResult<CakeProductDetailDTO>  rt =cakeProductManage.searchProductDetailCalcPrice(dto);
        return Objects.nonNull(rt)?rt.getData():null;
    }
}
