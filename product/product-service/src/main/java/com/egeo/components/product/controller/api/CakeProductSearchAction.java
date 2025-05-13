package com.egeo.components.product.controller.api;

import com.egeo.components.product.business.CakeProductManage;
import com.egeo.components.product.dto.CakeProductSearchDTO;
import com.egeo.components.product.dto.CakeSpecsProductDetailDTO;
import com.egeo.orm.PageResult;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
@Controller
@RequestMapping("/api/product/cakeProductSearchAction")
public class CakeProductSearchAction  extends BaseSpringController {

    @Resource(name="cakeProductManage")
    private CakeProductManage cakeProductManage;

    @RequestMapping(value = "/platform")
    @ResponseBody
    public JsonResult<PageResult<CakeSpecsProductDetailDTO>> platform(String keyword, String catId, Integer pageNo, Integer pageSize, Integer priceMax,
                                                                      Integer priceMin, String brand, String catId1, String catId2) {
        CakeProductSearchDTO search = new CakeProductSearchDTO();
        search.setBrand_id(brand);
        search.setSearch_title(keyword);
        search.setProduct_type(catId);
        search.setBrand_id(catId1);
        search.setPage(pageNo);
        search.setSize(pageSize);
        return cakeProductManage.searchPlatform(search);
    }
}
