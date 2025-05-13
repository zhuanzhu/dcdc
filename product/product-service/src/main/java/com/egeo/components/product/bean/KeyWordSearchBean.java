package com.egeo.components.product.bean;


import com.egeo.orm.Pagination;
import com.egeo.util.security.MD5Util;

import java.math.BigDecimal;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/11/26 15:49
 * @Version V1.0
 **/
public class KeyWordSearchBean {

    private Integer saleWay;
    private Long storeId;
    private String name;
    private BigDecimal userBalance;
    private Long clientId;
    private Long companyId;
    private Long platformId;
    private Integer companyType;
    private Integer buyType;

    private Pagination page;

    //总的记录数，主要用于计算下一次分页
    private Integer totalCount;

    private Long userId;

    //private String searchValueDecode;

    private String keyWordRedisKey;

    public Integer getSaleWay() {
        return saleWay;
    }

    public void setSaleWay(Integer saleWay) {
        this.saleWay = saleWay;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getUserBalance() {
        return userBalance;
    }

    public void setUserBalance(BigDecimal userBalance) {
        this.userBalance = userBalance;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Long platformId) {
        this.platformId = platformId;
    }

    public Integer getCompanyType() {
        return companyType;
    }

    public void setCompanyType(Integer companyType) {
        this.companyType = companyType;
    }

    public Integer getBuyType() {
        return buyType;
    }

    public void setBuyType(Integer buyType) {
        this.buyType = buyType;
    }

    public Pagination getPage() {
        return page;
    }

    public void setPage(Pagination page) {
        this.page = page;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public  void addTotalCount(Integer currCount){
        if(this.totalCount ==null){
            this.totalCount = 0;
        }
        if(currCount == null){
            currCount = 0;
        }
        this.totalCount = this.getTotalCount()+currCount;
    }

    /*public String getSearchValueDecode() {
        return MD5Util.MD5(this.name);
        //return searchValueDecode;
    }

    public void setSearchValueDecode(String searchValueDecode) {
        this.searchValueDecode = searchValueDecode;
    }*/

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getKeyWordRedisKey() {
        return keyWordRedisKey;
    }

    public void setKeyWordRedisKey(String keyWordRedisKey) {
        this.keyWordRedisKey = keyWordRedisKey;
    }
}
