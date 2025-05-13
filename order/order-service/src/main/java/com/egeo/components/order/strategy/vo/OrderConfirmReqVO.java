package com.egeo.components.order.strategy.vo;

import com.egeo.components.order.dto.CartItemDTO;
import com.egeo.components.order.vo.jd.ParseAddressJson;
import com.egeo.components.product.dto.CommodityProductUnitDTO;
import com.egeo.components.product.dto.JdProductDTO;

import java.io.Serializable;
import java.util.List;

/**
 * @Description 订单确认策略请求参数封装对象-优化
 * @Author lsl
 * @Date 2024/12/1 12:56
 * @Version V1.0
 **/
public class OrderConfirmReqVO implements Serializable {

    /**京东地址**/
    ParseAddressJson parseAddressJson;

    /**购物地址**/
    String address;

    /**客户端id**/
    Long clientId;

    /**门店id**/
    Long storeId;

    /**确认购物类型：
     * 0：立即购买确认
     * 1：购物车购买确认
     * **/
    Integer type;

    /**购物项ids**/
    String cartItemIds;

    /**在立即下单时puId必须有值，商品的最小单位id**/
    Long puId;

    /**在立即下单时num必须有值，且大于等于1**/
    Integer num;
    /**确认的收货地址id**/
    Long addrId;

    /**用户id**/
    Long memberId;

    /**平台id**/
    Long platformId;

    /**公司id**/
    Long companyId;

    /**若是手机号充值**/
    String phone;
    /**优惠券类型**/
    Integer couponType;

    /**优惠券id**/
    Long couponUnitId;
    /**产品模板ID**/
    Long commodityTemplateId;
    /**渠道产品ID**/
    String channelProductId;

    /**渠道source
     * null-自营 ；
     * 3-京东；
     * 4蛋糕叔叔；
     * 5全球购
     * **/
    Integer source;
    /**购物项id列表**/
    List<CartItemDTO> cartItemList;
    /**商户id**/
    Long merchantId;
    /**公司类型**/
    Integer companyType;

    /**京东商品**/
    JdProductDTO jdProductDTO;
    /**自营商品商品**/
    CommodityProductUnitDTO pu;

    private Long cartItemId;

    private Long enterpriseId;

    public ParseAddressJson getParseAddressJson() {
        return parseAddressJson;
    }

    public void setParseAddressJson(ParseAddressJson parseAddressJson) {
        this.parseAddressJson = parseAddressJson;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCartItemIds() {
        return cartItemIds;
    }

    public void setCartItemIds(String cartItemIds) {
        this.cartItemIds = cartItemIds;
    }

    public Long getPuId() {
        return puId;
    }

    public void setPuId(Long puId) {
        this.puId = puId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Long getAddrId() {
        return addrId;
    }

    public void setAddrId(Long addrId) {
        this.addrId = addrId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Long platformId) {
        this.platformId = platformId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getCouponType() {
        return couponType;
    }

    public void setCouponType(Integer couponType) {
        this.couponType = couponType;
    }

    public Long getCouponUnitId() {
        return couponUnitId;
    }

    public void setCouponUnitId(Long couponUnitId) {
        this.couponUnitId = couponUnitId;
    }

    public Long getCommodityTemplateId() {
        return commodityTemplateId;
    }

    public void setCommodityTemplateId(Long commodityTemplateId) {
        this.commodityTemplateId = commodityTemplateId;
    }

    public String getChannelProductId() {
        return channelProductId;
    }

    public void setChannelProductId(String channelProductId) {
        this.channelProductId = channelProductId;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public List<CartItemDTO> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(List<CartItemDTO> cartItemList) {
        this.cartItemList = cartItemList;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Integer getCompanyType() {
        return companyType;
    }

    public void setCompanyType(Integer companyType) {
        this.companyType = companyType;
    }

    public JdProductDTO getJdProductDTO() {
        return jdProductDTO;
    }

    public void setJdProductDTO(JdProductDTO jdProductDTO) {
        this.jdProductDTO = jdProductDTO;
    }

    public CommodityProductUnitDTO getPu() {
        return pu;
    }

    public void setPu(CommodityProductUnitDTO pu) {
        this.pu = pu;
    }

    public Long getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(Long cartItemId) {
        this.cartItemId = cartItemId;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }
}
