package com.egeo.components.cms.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.cms.vo.CmsPayTypeVO;
import com.egeo.components.user.dto.PayTypeDTO;

/**
 * Created by 0.0 on 2018/9/4.
 */
public class CmsPayTypeConverter {
    public static CmsPayTypeVO toVO(PayTypeDTO src){
        CmsPayTypeVO cmsPayTypeVO = new CmsPayTypeVO();
        cmsPayTypeVO.setId(src.getId());
        cmsPayTypeVO.setLogImageUrl(src.getLogImageUrl());
        cmsPayTypeVO.setPayTypeCode(src.getPayTypeCode());
        cmsPayTypeVO.setPayTypeName(src.getPayTypeName());
        cmsPayTypeVO.setPayTypeRemarks(src.getPayTypeRemarks());
        return cmsPayTypeVO;
    }

    public static List<CmsPayTypeVO> toVO(List<PayTypeDTO> src){
        List<CmsPayTypeVO> list = new ArrayList<>();
        for (PayTypeDTO dto : src) {
            list.add(toVO(dto));
        }
        return list;

    }



}
