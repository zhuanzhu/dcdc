package com.egeo.components.third.dao.write;

import com.egeo.components.third.po.ChannelLogPO;
import com.egeo.orm.BaseWriteDAO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/10/30 13:58
 * @Version V1.0
 **/
@Mapper
public interface ChannelLogWriteDAO extends BaseWriteDAO<ChannelLogPO> {
}
