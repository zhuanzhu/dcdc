package com.egeo.components.third.service.write;

import com.egeo.components.third.dto.SplitOrderConfigDTO;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface SplitOrderConfigWriteService {

    public Long insertSplitOrderConfigWithTx(SplitOrderConfigDTO dto);

    public int updateSplitOrderConfigWithTx(SplitOrderConfigDTO dto);

    public int deleteSplitOrderConfigWithTx(SplitOrderConfigDTO dto);
}
