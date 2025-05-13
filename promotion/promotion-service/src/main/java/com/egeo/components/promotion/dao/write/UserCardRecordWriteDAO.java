package com.egeo.components.promotion.dao.write;

import com.egeo.components.promotion.po.UserCardRecordPO;
import com.egeo.orm.BaseWriteDAO;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

public interface UserCardRecordWriteDAO extends BaseWriteDAO<UserCardRecordPO> {

    public int useCardWithTx(@Param("id")Long id, @Param("useAmount") BigDecimal useAmount, @Param("useState")Integer useState);

    public int refundCardWithTx(@Param("id")Long id, @Param("useAmount") BigDecimal useAmount, @Param("useState")Integer useState);

}
