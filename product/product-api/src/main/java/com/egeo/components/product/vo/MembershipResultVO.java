package com.egeo.components.product.vo;

/**
 * Created by 0.0 on 2018/9/13.
 */
public class MembershipResultVO {
    private Long id;
    private String membershipName;
    private Integer isUse;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMembershipName() {
        return membershipName;
    }

    public void setMembershipName(String membershipName) {
        this.membershipName = membershipName;
    }

    public Integer getIsUse() {
        return isUse;
    }

    public void setIsUse(Integer isUse) {
        this.isUse = isUse;
    }
}
