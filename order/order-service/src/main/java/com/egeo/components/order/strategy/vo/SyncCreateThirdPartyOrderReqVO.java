package com.egeo.components.order.strategy.vo;

import com.egeo.components.config.dto.CompanyConfigDTO;
import com.egeo.components.order.dto.ReceiverAddressDTO;
import com.egeo.components.order.dto.SoChildDTO;
import com.egeo.components.order.dto.SoDTO;

import java.io.Serializable;
import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/12/6 13:42
 * @Version V1.0
 **/
public class SyncCreateThirdPartyOrderReqVO implements Serializable {

    private Long companyId;

    private SoChildDTO soChildDTO;

    private ReceiverAddressDTO addr;

    private List<CompanyConfigDTO> configs;

    private SoDTO sodto;

    private Long orderId;

    private Long userId;

    private Long performingParty;

    private Long enterpriseId;

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public SoChildDTO getSoChildDTO() {
        return soChildDTO;
    }

    public void setSoChildDTO(SoChildDTO soChildDTO) {
        this.soChildDTO = soChildDTO;
    }

    public ReceiverAddressDTO getAddr() {
        return addr;
    }

    public void setAddr(ReceiverAddressDTO addr) {
        this.addr = addr;
    }

    public List<CompanyConfigDTO> getConfigs() {
        return configs;
    }

    public void setConfigs(List<CompanyConfigDTO> configs) {
        this.configs = configs;
    }

    public SoDTO getSodto() {
        return sodto;
    }

    public void setSodto(SoDTO sodto) {
        this.sodto = sodto;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPerformingParty() {
        return performingParty;
    }

    public void setPerformingParty(Long performingParty) {
        this.performingParty = performingParty;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }
}
