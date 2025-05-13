package com.egeo.components.third.manage.write;

import com.egeo.components.third.po.ChannelLogPO;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/10/30 14:16
 * @Version V1.0
 **/
public interface ChannelLogWriteManage {

    Long insertChannelLogWithTx(ChannelLogPO po);

    int updateChannelLogWithTx(ChannelLogPO po);

    int deleteChannelLogWithTx(ChannelLogPO po);
}
