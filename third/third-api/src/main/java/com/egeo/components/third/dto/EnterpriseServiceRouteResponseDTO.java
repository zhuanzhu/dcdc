package com.egeo.components.third.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class EnterpriseServiceRouteResponseDTO implements Serializable {

    /**
     * 企业id
     **/
    private Integer enterpriseId;

    /**
     * 服务接口名称/接口类型
     **/
    private String channelServiceName;

    /**
     * 可用的渠道接口
     **/
    private List<EnterpriseChannelServiceDTO> list;


    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getChannelServiceName() {
        return channelServiceName;
    }

    public void setChannelServiceName(String channelServiceName) {
        this.channelServiceName = channelServiceName;
    }

    public List<EnterpriseChannelServiceDTO> getList() {
        return list;
    }

    public void setList(List<EnterpriseChannelServiceDTO> list) {
        this.list = list;
    }

    public EnterpriseServiceRouteResponseDTO() {
    }

    public EnterpriseServiceRouteResponseDTO(EnterpriseServiceRouteRequestDTO dto, List<EnterpriseChannelServiceDTO> list) {
        this.enterpriseId = dto.getEnterpriseId();
        this.channelServiceName = dto.getChannelServiceName();
        this.list = list;
    }
}
