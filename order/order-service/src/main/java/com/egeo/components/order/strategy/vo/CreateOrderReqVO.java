package com.egeo.components.order.strategy.vo;

import com.egeo.components.order.dto.LimitRuleRecordDTO;
import com.egeo.components.order.dto.ReceiverAddressDTO;
import com.egeo.components.order.dto.SoItemDTO;
import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.components.user.dto.UserDTO;

import java.io.Serializable;
import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/12/5 13:25
 * @Version V1.0
 **/
public class CreateOrderReqVO implements Serializable {

    /**下单类型:0立即购买下单 6清美下单 否则购物车下单**/
    private Integer type;

    /**最小单位id**/
    private Long puId;

    /**渠道产品id**/
    private String channelProductId;

    /**渠道来源**/
    private Integer source;

    /**对应渠道对应的商品列表**/
    private List<SoItemDTO> soItems;

    // 组织限购规则记录集合
    List<LimitRuleRecordDTO> limitRuleRecordList;

    /**商品对象**/
    private Object productObject;

    private ReceiverAddressDTO addr;

    private Integer num;

    private Long userId;

    private Long enterpriseId;

    private Long platformId;

    private String userName ;

    private Long storeId;

    private UserDTO user;

    private Long companyId;

    private Long commodityTemplateId;

    private CompanyDTO companyDTO;

    private String phone;

    private String thirdOrderJsonStr;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getPuId() {
        return puId;
    }

    public void setPuId(Long puId) {
        this.puId = puId;
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

    public List<SoItemDTO> getSoItems() {
        return soItems;
    }

    public void setSoItems(List<SoItemDTO> soItems) {
        this.soItems = soItems;
    }

    public List<LimitRuleRecordDTO> getLimitRuleRecordList() {
        return limitRuleRecordList;
    }

    public void setLimitRuleRecordList(List<LimitRuleRecordDTO> limitRuleRecordList) {
        this.limitRuleRecordList = limitRuleRecordList;
    }

    public Object getProductObject() {
        return productObject;
    }

    public void setProductObject(Object productObject) {
        this.productObject = productObject;
    }

    public ReceiverAddressDTO getAddr() {
        return addr;
    }

    public void setAddr(ReceiverAddressDTO addr) {
        this.addr = addr;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Long getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Long platformId) {
        this.platformId = platformId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getCommodityTemplateId() {
        return commodityTemplateId;
    }

    public void setCommodityTemplateId(Long commodityTemplateId) {
        this.commodityTemplateId = commodityTemplateId;
    }

    public CompanyDTO getCompanyDTO() {
        return companyDTO;
    }

    public void setCompanyDTO(CompanyDTO companyDTO) {
        this.companyDTO = companyDTO;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getThirdOrderJsonStr() {
        return thirdOrderJsonStr;
    }

    public void setThirdOrderJsonStr(String thirdOrderJsonStr) {
        this.thirdOrderJsonStr = thirdOrderJsonStr;
    }
}
