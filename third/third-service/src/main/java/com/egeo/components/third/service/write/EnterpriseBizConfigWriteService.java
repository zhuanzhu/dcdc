package com.egeo.components.third.service.write;

import com.egeo.components.third.dto.EnterpriseBizConfigDTO;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface EnterpriseBizConfigWriteService {

    public Long insertEnterpriseBizConfigWithTx(EnterpriseBizConfigDTO dto);

    public int updateEnterpriseBizConfigWithTx(EnterpriseBizConfigDTO dto);

    public int deleteEnterpriseBizConfigWithTx(EnterpriseBizConfigDTO dto);
}
