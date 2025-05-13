package com.egeo.components.third.manage.write;

import com.egeo.components.third.po.EnterpriseBizConfigPO;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface EnterpriseBizConfigWriteManage {

    public Long insertEnterpriseBizConfigWithTx(EnterpriseBizConfigPO po);

    public int updateEnterpriseBizConfigWithTx(EnterpriseBizConfigPO po);

    public int deleteEnterpriseBizConfigWithTx(EnterpriseBizConfigPO po);
}
