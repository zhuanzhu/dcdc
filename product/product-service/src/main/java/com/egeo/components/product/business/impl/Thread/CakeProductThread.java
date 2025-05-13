package com.egeo.components.product.business.impl.Thread;

import com.egeo.components.product.business.CakeProductManage;
import com.egeo.components.product.dto.CakeProductDetailDTO;
import com.egeo.components.product.dto.CakeProductDetailProductsDTO;
import com.egeo.utils.SpringContextTool;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/11/27 11:07
 * @Version V1.0
 **/
public class CakeProductThread  implements Callable<Map<String,CakeProductDetailDTO>> {

    private List<String> productIds;

    private Long enterpriseId;

    public List<String> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<String> productIds) {
        this.productIds = productIds;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    @Override
    public Map<String,CakeProductDetailDTO> call() throws Exception {
        if(CollectionUtils.isEmpty(productIds)){
            return null;
        }
        CakeProductManage cakeProductManage = SpringContextTool.getBean(CakeProductManage.class);
        List<CakeProductDetailDTO>  searchList = cakeProductManage.getCakeProductDetailDTOList(productIds,null,enterpriseId);
        Map<String,CakeProductDetailDTO> cakeMap = new HashMap<>();
        for (CakeProductDetailDTO cakeProductDetailDTO : searchList) {
            CakeProductDetailProductsDTO productsDTO = cakeProductDetailDTO.getProduct();
            if(productsDTO ==null){
                continue;
            }
            cakeMap.put(cakeProductDetailDTO.getProduct().getId(),cakeProductDetailDTO);
        }
        return cakeMap;
    }
}
