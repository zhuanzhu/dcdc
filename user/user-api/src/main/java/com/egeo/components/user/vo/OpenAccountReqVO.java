package com.egeo.components.user.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Date 2025/3/27 4:49
 * @Version V1.0
 **/
public class OpenAccountReqVO implements Serializable {
    private List<Long> userIds;
    private List<Integer> accountTypes;
    private Long platformId;



    public Long getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Long platformId) {
        this.platformId = platformId;
    }

    public List<Long> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Long> userIds) {
        this.userIds = userIds;
    }

    public List<Integer> getAccountTypes() {
        return accountTypes;
    }

    public void setAccountTypes(List<Integer> accountTypes) {
        this.accountTypes = accountTypes;
    }
}
