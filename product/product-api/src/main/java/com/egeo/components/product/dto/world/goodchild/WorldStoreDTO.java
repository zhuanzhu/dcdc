package com.egeo.components.product.dto.world.goodchild;

import java.io.Serializable;
import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class WorldStoreDTO implements Serializable {

    /**
     * 	String	是	关联SKU id
     **/
    private String linkSkuID;
    /**
     * 	String	是	仓库关区名称
     **/
    private String store_customer_name;
    /**
     * 	String	是	仓库类型弃用
     **/
    private String store_type;
    /**
     * 	String	是	仓库显示名称
     **/
    private String storeDisplayName;
    /**
     * 	String	是	仓库ID
     **/
    private String store_id;
    /**
     * 	String	是	仓库编码
     **/
    private String store_code;
    /**
     * 	String	是	仓库名称
     **/
    private String store_name;
    /**
     * 	String	是	仓库账号
     **/
    private String store_account;
    /**
     * 	String	是	仓库是否可以合并支付下单
     **/
    private String store_isCombineOrders;
    /**
     * 	String	是	仓库模式弃用
     **/
    private String store_mode;
    /**
     * 	String	是	供应商商品ID
     **/
    private String supplier_skuid;
    /**
     * 	String	是	供应商商品SKUCODE
     **/
    private String supplier_skucode;
    /**
     * 	String	是	供应商ID
     **/
    private String supplier_id;
    /**
     * 	String	是	供应商别名
     **/
    private String supplier_alias;
    /**
     * 	String	是	供应商编码
     **/
    private String supplier_code;
    /**
     * 	String	是	供应商状态（1-启用，0-禁用）
     **/
    private String supplier_companystatus;
    /**
     * 	String	是	供应商商品模式弃用
     **/
    private String supplier_commoditydockmode;
    /**
     * 	String	是	供应商库存模式弃用
     **/
    private String supplier_stockdockmode;
    /**
     * 	String	是	供应商订单是否接口（1-是，0-不是）
     **/
    private String supplier_orderdockmode;
    /**
     * 	String	是	营业执照编码
     **/
    private String BusinessLicenseNo;

    /**
     * goods_batch_list	array[]	是	批次组
     **/
    private List<WorldGoodsBatchListDTO> goods_batch_list;

    public String getLinkSkuID() {
        return linkSkuID;
    }

    public void setLinkSkuID(String linkSkuID) {
        this.linkSkuID = linkSkuID;
    }

    public String getStore_customer_name() {
        return store_customer_name;
    }

    public void setStore_customer_name(String store_customer_name) {
        this.store_customer_name = store_customer_name;
    }

    public String getStore_type() {
        return store_type;
    }

    public void setStore_type(String store_type) {
        this.store_type = store_type;
    }

    public String getStoreDisplayName() {
        return storeDisplayName;
    }

    public void setStoreDisplayName(String storeDisplayName) {
        this.storeDisplayName = storeDisplayName;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getStore_code() {
        return store_code;
    }

    public void setStore_code(String store_code) {
        this.store_code = store_code;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getStore_account() {
        return store_account;
    }

    public void setStore_account(String store_account) {
        this.store_account = store_account;
    }

    public String getStore_isCombineOrders() {
        return store_isCombineOrders;
    }

    public void setStore_isCombineOrders(String store_isCombineOrders) {
        this.store_isCombineOrders = store_isCombineOrders;
    }

    public String getStore_mode() {
        return store_mode;
    }

    public void setStore_mode(String store_mode) {
        this.store_mode = store_mode;
    }

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

    public String getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(String supplier_id) {
        this.supplier_id = supplier_id;
    }

    public String getSupplier_alias() {
        return supplier_alias;
    }

    public void setSupplier_alias(String supplier_alias) {
        this.supplier_alias = supplier_alias;
    }

    public String getSupplier_code() {
        return supplier_code;
    }

    public void setSupplier_code(String supplier_code) {
        this.supplier_code = supplier_code;
    }

    public String getSupplier_companystatus() {
        return supplier_companystatus;
    }

    public void setSupplier_companystatus(String supplier_companystatus) {
        this.supplier_companystatus = supplier_companystatus;
    }

    public String getSupplier_commoditydockmode() {
        return supplier_commoditydockmode;
    }

    public void setSupplier_commoditydockmode(String supplier_commoditydockmode) {
        this.supplier_commoditydockmode = supplier_commoditydockmode;
    }

    public String getSupplier_stockdockmode() {
        return supplier_stockdockmode;
    }

    public void setSupplier_stockdockmode(String supplier_stockdockmode) {
        this.supplier_stockdockmode = supplier_stockdockmode;
    }

    public String getSupplier_orderdockmode() {
        return supplier_orderdockmode;
    }

    public void setSupplier_orderdockmode(String supplier_orderdockmode) {
        this.supplier_orderdockmode = supplier_orderdockmode;
    }

    public String getBusinessLicenseNo() {
        return BusinessLicenseNo;
    }

    public void setBusinessLicenseNo(String businessLicenseNo) {
        BusinessLicenseNo = businessLicenseNo;
    }

    public List<WorldGoodsBatchListDTO> getGoods_batch_list() {
        return goods_batch_list;
    }

    public void setGoods_batch_list(List<WorldGoodsBatchListDTO> goods_batch_list) {
        this.goods_batch_list = goods_batch_list;
    }
}
