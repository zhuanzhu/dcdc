package com.egeo.components.product.dto.world.goodchild;

import java.io.Serializable;
import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class WorldSkuDetailDTO implements Serializable {

    /***;
     *		String	是	skuID;
     **/
    private String skuId;
    /***;
     *		String	是	下单使用的自编码;
     **/
    private String skucode;
    /***;
     *		String	是	国际条码;
     **/
    private String barcode;
    /***;
     *	String	是	税率（0.091）;
     **/
    private String tax_rate	;
    /***;
     *		String	是	重量，单位（g）;
     **/
    private String weight;
    /***;
     *		String	是	限购数量（极少商品才有限购）;
     **/
    private String limit_buy_count;
    /***;
     *	String 是	是否含税（1-含税，0-不含税）;
     **/
    private String has_rate	;
    /***;
     *		String	是	商品消费税（高税）预警单价（裸价）;
     **/
    private String consumption_price;
    /***;
     *		String	是	商品消费税（高税）税率;
     **/
    private String consumption_tax_rate;
    /***;
     *		String	是	固定1;
     **/
    private String status;
    /***;
     *	String	是	建议零售价;
     **/
    private String price;
    /***;
     *		String	是	市场价;
     **/
    private String price_market;
    /***;
     *		String	是	控价;
     **/
    private String price_control;
    /***;
     *		String	是	spuid;
     **/
    private String spu_id;
    /***;
     *	String	是	spu名称;
     **/
    private String spu_name	;
    /***;
     *		String	是	spu编码;
     **/
    private String spu_code;
    /***;
     *	String	是	规格型号（目前暂时不支持）;
     **/
    private String specList	;
    /***;
     *		String	是	供应商是否申报信息情况说明;
     **/
    private WorldCommodityCustomerInfoDTO commodity_customer_info;

    /***;
     *		storeList	array[]	是	仓库明细
     **/
    private List<WorldStoreDTO> storeList;

    /**
     * 是	分佣结构（一般情况用不上）
     **/
    private List<WorldCommissionLevelListDTO> commission_level_list;

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getSkucode() {
        return skucode;
    }

    public void setSkucode(String skucode) {
        this.skucode = skucode;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getTax_rate() {
        return tax_rate;
    }

    public void setTax_rate(String tax_rate) {
        this.tax_rate = tax_rate;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getLimit_buy_count() {
        return limit_buy_count;
    }

    public void setLimit_buy_count(String limit_buy_count) {
        this.limit_buy_count = limit_buy_count;
    }

    public String getHas_rate() {
        return has_rate;
    }

    public void setHas_rate(String has_rate) {
        this.has_rate = has_rate;
    }

    public String getConsumption_price() {
        return consumption_price;
    }

    public void setConsumption_price(String consumption_price) {
        this.consumption_price = consumption_price;
    }

    public String getConsumption_tax_rate() {
        return consumption_tax_rate;
    }

    public void setConsumption_tax_rate(String consumption_tax_rate) {
        this.consumption_tax_rate = consumption_tax_rate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice_market() {
        return price_market;
    }

    public void setPrice_market(String price_market) {
        this.price_market = price_market;
    }

    public String getPrice_control() {
        return price_control;
    }

    public void setPrice_control(String price_control) {
        this.price_control = price_control;
    }

    public String getSpu_id() {
        return spu_id;
    }

    public void setSpu_id(String spu_id) {
        this.spu_id = spu_id;
    }

    public String getSpu_name() {
        return spu_name;
    }

    public void setSpu_name(String spu_name) {
        this.spu_name = spu_name;
    }

    public String getSpu_code() {
        return spu_code;
    }

    public void setSpu_code(String spu_code) {
        this.spu_code = spu_code;
    }

    public String getSpecList() {
        return specList;
    }

    public void setSpecList(String specList) {
        this.specList = specList;
    }

    public WorldCommodityCustomerInfoDTO getCommodity_customer_info() {
        return commodity_customer_info;
    }

    public void setCommodity_customer_info(WorldCommodityCustomerInfoDTO commodity_customer_info) {
        this.commodity_customer_info = commodity_customer_info;
    }

    public List<WorldStoreDTO> getStoreList() {
        return storeList;
    }

    public void setStoreList(List<WorldStoreDTO> storeList) {
        this.storeList = storeList;
    }

    public List<WorldCommissionLevelListDTO> getCommission_level_list() {
        return commission_level_list;
    }

    public void setCommission_level_list(List<WorldCommissionLevelListDTO> commission_level_list) {
        this.commission_level_list = commission_level_list;
    }
}
