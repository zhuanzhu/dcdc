package com.egeo.components.product.bean;

import com.egeo.components.product.po.StandardUnitPO;
import com.egeo.components.third.dto.EnterpriseChannelServiceDTO;
import com.egeo.orm.Pagination;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 渠道产品查询参数模型
 * @Author lsl
 * @Date 2024/4/30 1:37
 * @Version V1.0
 **/
public class ChannelProductSearchBean implements Serializable {

    /**优惠券**/
    private Integer couponType;

    /****/
    private StandardUnitPO po;

    /**类目节点ID列表**/
    List<Long> categoryTreeNodeIdList;

    /**公司类型**/
    Integer companyType;

    /**分页参数**/
    Pagination page;

    /**用于策略上下方法方便传值，请勿乱用**/
    List<Long> categorys = new ArrayList<Long>();

    /**统计有多少商品**/
    int cnt;

    private String channelCode;

    private Long enterpriseId;

    EnterpriseChannelServiceDTO channelServiceDTO;

    private boolean hasRecalculatePageSize;

    /**用于策略上下方法方便传值，请勿乱用**/
    List<String> brandIds = new ArrayList<String>();

    /**用于策略上下方法方便传值，请勿乱用**/
    List<String> productTypes = new ArrayList<String>();

    private List<String> cat2 = new ArrayList<>();

    private List<String> cat3 = new ArrayList<>();

    /**前台类目id，也是本次查询类目id**/
    private Long frontCategoryTreeNodeId;

    /**同个用户同等条件下,上一次的pageNo**/
    private int lastPageNo;

    private String searchRedisKey;

    private String keyWord;

    public ChannelProductSearchBean() {

    }

    public ChannelProductSearchBean(Integer couponType, StandardUnitPO po, List<Long> categoryTreeNodeIdList, Integer companyType, Pagination page) {
        this.couponType = couponType;
        this.po = po;
        this.categoryTreeNodeIdList = categoryTreeNodeIdList;
        this.companyType = companyType;
        this.page = page;
    }

    public ChannelProductSearchBean(Integer couponType, StandardUnitPO po, List<Long> categoryTreeNodeIdList, Integer companyType, Pagination page,String channelCode) {
        this.couponType = couponType;
        this.po = po;
        this.categoryTreeNodeIdList = categoryTreeNodeIdList;
        this.companyType = companyType;
        this.page = page;
        this.channelCode = channelCode;
    }

    public Integer getCouponType() {
        return couponType;
    }

    public void setCouponType(Integer couponType) {
        this.couponType = couponType;
    }

    public StandardUnitPO getPo() {
        return po;
    }

    public void setPo(StandardUnitPO po) {
        this.po = po;
    }

    public List<Long> getCategoryTreeNodeIdList() {
        return categoryTreeNodeIdList;
    }

    public void setCategoryTreeNodeIdList(List<Long> categoryTreeNodeIdList) {
        this.categoryTreeNodeIdList = categoryTreeNodeIdList;
    }

    public Integer getCompanyType() {
        return companyType;
    }

    public void setCompanyType(Integer companyType) {
        this.companyType = companyType;
    }

    public Pagination getPage() {
        return page;
    }

    public void setPage(Pagination page) {
        this.page = page;
    }

    public List<Long> getCategorys() {
        return categorys;
    }

    public void setCategorys(List<Long> categorys) {
        this.categorys = categorys;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public boolean isHasRecalculatePageSize() {
        return hasRecalculatePageSize;
    }

    public void setHasRecalculatePageSize(boolean hasRecalculatePageSize) {
        this.hasRecalculatePageSize = hasRecalculatePageSize;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public EnterpriseChannelServiceDTO getChannelServiceDTO() {
        return channelServiceDTO;
    }

    public void setChannelServiceDTO(EnterpriseChannelServiceDTO channelServiceDTO) {
        this.channelServiceDTO = channelServiceDTO;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public List<String> getBrandIds() {
        return brandIds;
    }

    public void setBrandIds(List<String> brandIds) {
        this.brandIds = brandIds;
    }

    public List<String> getProductTypes() {
        return productTypes;
    }

    public void setProductTypes(List<String> productTypes) {
        this.productTypes = productTypes;
    }

    public List<String> getCat2() {
        return cat2;
    }

    public void setCat2(List<String> cat2) {
        this.cat2 = cat2;
    }

    public List<String> getCat3() {
        return cat3;
    }

    public void setCat3(List<String> cat3) {
        this.cat3 = cat3;
    }

    public Long getFrontCategoryTreeNodeId() {
        return frontCategoryTreeNodeId;
    }

    public void setFrontCategoryTreeNodeId(Long frontCategoryTreeNodeId) {
        this.frontCategoryTreeNodeId = frontCategoryTreeNodeId;
    }

    public int getLastPageNo() {
        return lastPageNo;
    }

    public void setLastPageNo(int lastPageNo) {
        this.lastPageNo = lastPageNo;
    }

    public String getSearchRedisKey() {
        return searchRedisKey;
    }

    public void setSearchRedisKey(String searchRedisKey) {
        this.searchRedisKey = searchRedisKey;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
}
