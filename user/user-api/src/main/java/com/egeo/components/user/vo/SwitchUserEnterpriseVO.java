package com.egeo.components.user.vo;

import java.io.Serializable;

/**
 * @Description todo
 * @Author lsl
 * @Date 2025/1/7 12:28
 * @Version V1.0
 **/
public class SwitchUserEnterpriseVO implements Serializable {

    /**迁移到该代理商企业id**/
    private Long enterpriseId;

    /**源公司id（需要迁移的公司id）**/
    private Long companyId;

    private Long userId;

    /**目标公司id（迁移至该公司id）**/
    private Long targetCompanyId;

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTargetCompanyId() {
        return targetCompanyId;
    }

    public void setTargetCompanyId(Long targetCompanyId) {
        this.targetCompanyId = targetCompanyId;
    }
}
