package com.egeo.components.third.manage.write;

import com.egeo.components.third.po.ChannelServiceConfigPO;
import com.egeo.components.third.po.ChannelServiceFieldConfigPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
@Mapper
public interface ChannelServiceFieldConfigWriteManage {


    Long insertChannelServiceFieldConfigWithTx(ChannelServiceFieldConfigPO po);

    int updateChannelServiceFieldConfigWithTx(ChannelServiceFieldConfigPO po);

    int deleteChannelServiceFieldConfigWithTx(ChannelServiceFieldConfigPO po);
}
