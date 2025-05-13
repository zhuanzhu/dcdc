package com.egeo.components.product.business.impl.Thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.egeo.components.product.dto.CategoryDTO;
import com.egeo.components.product.dto.CategoryTreeNodeDTO;
import com.egeo.components.product.dto.JdCategoryDTO;
import com.egeo.components.product.dto.JdProductDTO;
import com.egeo.exception.BusinessException;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.SpringContextTool;
import com.egeo.utils.delivery.JdUtils;

/**
 * Created by 0.0 on 2019/4/12.
 */
public class JdProductThread implements Callable<List<JdProductDTO>> {

    private String token;
    private String catId;
    private JdCategoryDTO jdCategoryDTO;
    private CategoryDTO categoryDTO;
    private CategoryTreeNodeDTO catTreeNode2;
    private String categoryName ;
	private Long max;
	private Long min;

    public void setMax(Long max) {
        this.max = max;
    }

    public void setMin(Long min) {
        this.min = min;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setJdCategoryDTO(JdCategoryDTO jdCategoryDTO) {
        this.jdCategoryDTO = jdCategoryDTO;
    }

    public void setCategoryDTO(CategoryDTO categoryDTO) {
        this.categoryDTO = categoryDTO;
    }

    public void setCatTreeNode2(CategoryTreeNodeDTO catTreeNode2) {
        this.catTreeNode2 = catTreeNode2;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    @Override
    public List<JdProductDTO> call() throws Exception {
        List<JdProductDTO> jdProductDTOList= new ArrayList<>();
        try {
            //查询京东内容
        	JdUtils jdUtils = SpringContextTool.getBean(JdUtils.class);
            JSONObject categoryProduct = jdUtils.getCategoryProduct(min,max,token, catId, 1 + "");
            Integer pageCount = categoryProduct.getInteger("pageCount");
            if(EmptyUtil.isNotEmpty(pageCount)){
                if(pageCount>=1){
                    for(int j=1;j<=pageCount;j++){
                        JSONObject jsonObject = jdUtils.getCategoryProduct(min, max, token, catId, j + "");
                        JSONArray productJson = jsonObject.getJSONArray("hitResult");
                        if(EmptyUtil.isNotEmpty(productJson)){
                            for(Object product:productJson){
                                JSONObject obj = (JSONObject) product;
                                JdProductDTO productDTO = new JdProductDTO();
                                String pictureUrl=obj.getString("imageUrl");
                                String name=obj.getString("wareName");
                                String skuId=obj.getString("wareId");
                                String spuId = obj.getString("warePId");
                                String wstate = obj.getString("wstate");

                                productDTO.setId(Long.valueOf(skuId));
                                productDTO.setImagePath("http://img13.360buyimg.com/n1/s375x375_"+pictureUrl);
                                productDTO.setPicture("http://img13.360buyimg.com/n1/s375x375_"+pictureUrl);
                                productDTO.setName(name);
                                productDTO.setSpuId(Long.valueOf(spuId));
                                productDTO.setCategoryId(Long.valueOf(catId));
                                productDTO.setState(Integer.valueOf(wstate));


                                jdProductDTOList.add(productDTO);
                            }
                        }

                    }

                }
            }
        }catch (Exception e){
            throw new BusinessException();
         }



        return jdProductDTOList;
    }
}
