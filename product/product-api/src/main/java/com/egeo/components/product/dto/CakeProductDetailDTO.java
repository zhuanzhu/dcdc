package com.egeo.components.product.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @Description 商品详情
 * @Author lsl
 * @Version V1.0
 **/
public class CakeProductDetailDTO implements Serializable {

    /**
     * banner轮播图列表
     **/
    private List<CakeProductImageDTO> images;

    /**
     * 图文详情图列表
     **/
    private List<CakeProductImageDTO> content_images;

    /**
     * 商品规格列表
     **/
    private List<CakeProductDetailSpecsDTO> specs;

    /**
     * 品牌
     **/
    private CakeProductBrandDTO brand;

    /**
     * 	商品信息
     **/
    private CakeProductDetailProductsDTO product;

    public List<CakeProductImageDTO> getImages() {
        return images;
    }

    public void setImages(List<CakeProductImageDTO> images) {
        this.images = images;
    }

    public List<CakeProductImageDTO> getContent_images() {
        return content_images;
    }

    public void setContent_images(List<CakeProductImageDTO> content_images) {
        this.content_images = content_images;
    }

    public List<CakeProductDetailSpecsDTO> getSpecs() {
        return specs;
    }

    public void setSpecs(List<CakeProductDetailSpecsDTO> specs) {
        this.specs = specs;
    }

    public CakeProductBrandDTO getBrand() {
        return brand;
    }

    public void setBrand(CakeProductBrandDTO brand) {
        this.brand = brand;
    }

    public CakeProductDetailProductsDTO getProduct() {
        return product;
    }

    public void setProduct(CakeProductDetailProductsDTO product) {
        this.product = product;
    }
}
