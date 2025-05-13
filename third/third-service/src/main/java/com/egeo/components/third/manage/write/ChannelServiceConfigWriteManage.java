package com.egeo.components.third.manage.write;

import com.egeo.components.third.po.ChannelServiceConfigPO;
import com.egeo.orm.BaseWriteDAO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
@Mapper
public interface ChannelServiceConfigWriteManage{


    Long insertChannelServiceConfigWithTx(ChannelServiceConfigPO po);

    int updateChannelServiceConfigWithTx(ChannelServiceConfigPO po);

    int deleteChannelServiceConfigWithTx(ChannelServiceConfigPO po);
}
