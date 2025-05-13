package com.egeo.components.promotion.dao.write;

import com.egeo.components.promotion.po.CardUseRecordPO;
import com.egeo.orm.BaseWriteDAO;

import java.math.BigDecimal;

public interface CardUseRecordWriteDAO extends BaseWriteDAO<CardUseRecordPO> {

    public int cancelUserCard();
}
