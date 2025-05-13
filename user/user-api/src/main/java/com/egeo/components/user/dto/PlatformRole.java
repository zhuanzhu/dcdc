package com.egeo.components.user.dto;

import java.io.Serializable;
import java.util.List;

public class PlatformRole implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private PlatformDTO platformDTO;
    private List<RoleDTO> list;
    public PlatformDTO getPlatformDTO() {
        return platformDTO;
    }
    public void setPlatformDTO(PlatformDTO platformDTO) {
        this.platformDTO = platformDTO;
    }
    public List<RoleDTO> getList() {
        return list;
    }
    public void setList(List<RoleDTO> list) {
        this.list = list;
    }
    
}
