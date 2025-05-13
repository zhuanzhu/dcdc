package com.egeo.components.order.strategy.vo;

import com.egeo.components.order.dto.SoDTO;
import com.egeo.components.third.dto.EnterpriseBizConfigDTO;
import com.egeo.components.user.dto.UserDTO;

import java.io.Serializable;
import java.util.List;

/**
 * @Description 客户推单
 * @Author lsl
 * @Date 2024/12/30 14:40
 * @Version V1.0
 **/
public class PushUserOrderReqVO implements Serializable {

    private SoDTO soDTO;
    private Long enterpriseId;
    private String channelCode;
    private EnterpriseBizConfigDTO enterpriseBizConfigDTO;
    private UserDTO userDTO;

    private List<EnterpriseBizConfigDTO> bizConfigDTOList;

    public SoDTO getSoDTO() {
        return soDTO;
    }

    public void setSoDTO(SoDTO soDTO) {
        this.soDTO = soDTO;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public EnterpriseBizConfigDTO getEnterpriseBizConfigDTO() {
        return enterpriseBizConfigDTO;
    }

    public void setEnterpriseBizConfigDTO(EnterpriseBizConfigDTO enterpriseBizConfigDTO) {
        this.enterpriseBizConfigDTO = enterpriseBizConfigDTO;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public List<EnterpriseBizConfigDTO> getBizConfigDTOList() {
        return bizConfigDTOList;
    }

    public void setBizConfigDTOList(List<EnterpriseBizConfigDTO> bizConfigDTOList) {
        this.bizConfigDTOList = bizConfigDTOList;
    }
}
