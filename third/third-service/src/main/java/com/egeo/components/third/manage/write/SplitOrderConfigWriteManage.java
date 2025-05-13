package com.egeo.components.third.manage.write;

import com.egeo.components.third.po.SplitOrderConfigPO;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface SplitOrderConfigWriteManage {

    public Long insertSplitOrderConfigWithTx(SplitOrderConfigPO po);

    public int updateSplitOrderConfigWithTx(SplitOrderConfigPO po);

    public int deleteSplitOrderConfigWithTx(SplitOrderConfigPO po);
}
