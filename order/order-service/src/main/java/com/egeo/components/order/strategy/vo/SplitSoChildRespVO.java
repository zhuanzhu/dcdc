package com.egeo.components.order.strategy.vo;

import com.egeo.components.order.dto.SoChildDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/12/6 1:26
 * @Version V1.0
 **/
public class SplitSoChildRespVO implements Serializable {

    List<SoChildDTO> soChildDTOList;


    private Long mId;

    public List<SoChildDTO> getSoChildDTOList() {
        return soChildDTOList;
    }

    public void setSoChildDTOList(List<SoChildDTO> soChildDTOList) {
        this.soChildDTOList = soChildDTOList;
    }


    public Long getmId() {
        return mId;
    }

    public void setmId(Long mId) {
        this.mId = mId;
    }

}
