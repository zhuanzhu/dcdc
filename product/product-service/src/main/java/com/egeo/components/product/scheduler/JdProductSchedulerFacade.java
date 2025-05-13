package com.egeo.components.product.scheduler;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.egeo.components.product.dto.JdCategoryDTO;
import com.egeo.components.product.service.read.JdCategoryReadService;
import com.egeo.components.product.service.write.JdCategoryWriteService;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.delivery.JdUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

@Component
public class JdProductSchedulerFacade {

    @Resource
    private JdUtils jdUtils;
    @Resource
    private JedisUtil jedisUtil;
    @Resource
    private JdCategoryReadService jdCategoryReadService;
    @Resource
    private JdCategoryWriteService jdCategoryWriteService;

    public void syncJdCategory(){
        syncJdCategoryByParentId("0",0);
    }


    private void syncJdCategoryByParentId(String jdParentId, int catClass) {
        if (catClass <= 2) {
            JSONObject result = jdUtils.getJdCategory(jedisUtil, jdParentId, catClass, null, null);
            if (EmptyUtil.isNotEmpty(result)) {
                JSONArray cats = result.getJSONArray("categorys");
                if (EmptyUtil.isNotEmpty(cats)) {
                    for (Object cat : cats) {
                        JSONObject catObj = (JSONObject) cat;
                        String catId = catObj.getString("catId");
                        String name = catObj.getString("name");
                        JdCategoryDTO jdCategoryDTO = new JdCategoryDTO();
                        jdCategoryDTO.setId(Long.parseLong(catId));
                        JdCategoryDTO dbDTO = jdCategoryReadService.findJdCategoryById(jdCategoryDTO);
                        if (Objects.isNull(dbDTO)) {
                            jdCategoryDTO.setName(name);
                            jdCategoryDTO.setParentId(jdParentId == null ? null : Long.parseLong(jdParentId));
                            jdCategoryDTO.setCatClass(catClass+1);
                            jdCategoryWriteService.insertJdCategoryWithTx(jdCategoryDTO);
                        }
                        syncJdCategoryByParentId(catId, catClass + 1);
                    }
                }
            }
        }

    }
}
