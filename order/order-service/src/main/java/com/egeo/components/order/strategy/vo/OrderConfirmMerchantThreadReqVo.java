package com.egeo.components.order.strategy.vo;

import com.egeo.components.order.dto.CartItemDTO;
import com.egeo.components.order.vo.jd.ParseAddressJson;

import java.io.Serializable;
import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/12/1 19:34
 * @Version V1.0
 **/
public class OrderConfirmMerchantThreadReqVo implements Serializable {

    ParseAddressJson parseAddressJson;
    String address;
    Long clientId;
    Integer companyType;
    List<CartItemDTO> cartItemList;
    Long puId;
    Long userId;
    Long platformId;
    Long storeId;
    Long merchantId;

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

    public Integer getCompanyType() {
        return companyType;
    }

    public void setCompanyType(Integer companyType) {
        this.companyType = companyType;
    }

    public List<CartItemDTO> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(List<CartItemDTO> cartItemList) {
        this.cartItemList = cartItemList;
    }

    public Long getPuId() {
        return puId;
    }

    public void setPuId(Long puId) {
        this.puId = puId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Long platformId) {
        this.platformId = platformId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }
}
