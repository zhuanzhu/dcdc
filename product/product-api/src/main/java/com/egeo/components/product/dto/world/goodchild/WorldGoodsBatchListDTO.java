package com.egeo.components.product.dto.world.goodchild;

import java.io.Serializable;
import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class WorldGoodsBatchListDTO implements Serializable {
    /**
     * 	String	是	供应商商品ID
     **/
    private String supplier_skuid;
    /**
     * String	是	供应商商品SKUCODE
     **/
    private String supplier_skucode	;
    /**
     * 	String	是	默认仓库
     **/
    private String defaultStock;
    /**
     * String	是	批次库存数量（共享）
     **/
    private String num	;
    /**
     * String	是	批次号
     **/
    private String batch_no	;
    /**
     * 	String	是	关务批次ID（专用）
     **/
    private String batch_id;
    /**
     * 	String	是	固定1
     **/
    private String status;

    private List<WorldSpecListDTO> spec_list;

    public String getSupplier_skuid() {
        return supplier_skuid;
    }

    public void setSupplier_skuid(String supplier_skuid) {
        this.supplier_skuid = supplier_skuid;
    }

    public String getSupplier_skucode() {
        return supplier_skucode;
    }

    public void setSupplier_skucode(String supplier_skucode) {
        this.supplier_skucode = supplier_skucode;
    }

    public String getDefaultStock() {
        return defaultStock;
    }

    public void setDefaultStock(String defaultStock) {
        this.defaultStock = defaultStock;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getBatch_no() {
        return batch_no;
    }

    public void setBatch_no(String batch_no) {
        this.batch_no = batch_no;
    }

    public String getBatch_id() {
        return batch_id;
    }

    public void setBatch_id(String batch_id) {
        this.batch_id = batch_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<WorldSpecListDTO> getSpec_list() {
        return spec_list;
    }

    public void setSpec_list(List<WorldSpecListDTO> spec_list) {
        this.spec_list = spec_list;
    }
}
