package com.egeo.components.product.vo;

/**
 * Created by 0.0 on 2018/11/27.
 */
public class MerchantListVO {
    private static final long serialVersionUID = 1L;

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
}
