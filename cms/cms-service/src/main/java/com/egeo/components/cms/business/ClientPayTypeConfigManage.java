package com.egeo.components.cms.business;

import java.util.Map;

import com.egeo.components.cms.vo.CmsClientVO;
import com.egeo.web.JsonResult;

/**
 * Created by 0.0 on 2018/8/30.
 */
public interface ClientPayTypeConfigManage {

    JsonResult<Map<String,Object>> findAllClientPayType(Long platformId);

    JsonResult<Map<String,Object>> findClientPayTypeByClientId(Long cliendId,Long platformId);

    JsonResult<Map<String,Object>> updateClientPayTypeByClientId(CmsClientVO cmsClientVO, Long platformId);

    JsonResult<Object> getClientPayTypeByClientId(Long clientId, Long platformId);
}

