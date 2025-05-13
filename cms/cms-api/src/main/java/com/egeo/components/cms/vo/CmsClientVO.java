package com.egeo.components.cms.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by 0.0 on 2018/8/30.
 */
public class CmsClientVO implements Serializable {
    private static final long serialVersionUID = 1L;


    private Long id;

    /**
     * 客户端名称
     */
    private String name;
    private List<String> payTypeName;
    private List<CmsClientPayTypeConfigVO> cmsClientPayTypeConfigVOList;

    public List<CmsClientPayTypeConfigVO> getCmsClientPayTypeConfigVOList() {
        return cmsClientPayTypeConfigVOList;
    }

    public void setCmsClientPayTypeConfigVOList(List<CmsClientPayTypeConfigVO> cmsClientPayTypeConfigVOList) {
        this.cmsClientPayTypeConfigVOList = cmsClientPayTypeConfigVOList;
    }

    public List<String> getPayTypeName() {
        return payTypeName;
    }

    public void setPayTypeName(List<String> payTypeName) {
        this.payTypeName = payTypeName;
    }

    public String getClientPayTypeRemarks() {
        return clientPayTypeRemarks;
    }

    public void setClientPayTypeRemarks(String clientPayTypeRemarks) {
        this.clientPayTypeRemarks = clientPayTypeRemarks;
    }

    /**
     * 支付配置备注
     */
    private String clientPayTypeRemarks;

    /**
     * 客户端描述
     */
    private String description;

    /**
     * 创建时间:创建记录时数据库会自动set值
     */
    private Date createTime;

    /**
     * 修改时间:更新时数据库会自动set值
     */
    private Date updateTime;

    public Long getId() {
        return id;
    }

    /**
     * 主键
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 客户端名称
     * @return 客户端名称
     */
    public String getName() {
        return name;
    }

    /**
     * 客户端名称
     * @param name 客户端名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 客户端描述
     * @return 客户端描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 客户端描述
     * @param description 客户端描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 创建时间:创建记录时数据库会自动set值
     * @return 创建时间:创建记录时数据库会自动set值
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间:创建记录时数据库会自动set值
     * @param createTime 创建时间:创建记录时数据库会自动set值
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改时间:更新时数据库会自动set值
     * @return 修改时间:更新时数据库会自动set值
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 修改时间:更新时数据库会自动set值
     * @param updateTime 修改时间:更新时数据库会自动set值
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }



}
