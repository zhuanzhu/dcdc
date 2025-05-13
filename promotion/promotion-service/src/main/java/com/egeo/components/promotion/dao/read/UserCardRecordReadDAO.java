package com.egeo.components.promotion.dao.read;

import com.egeo.components.promotion.po.UserCardRecordPO;
import com.egeo.components.promotion.vo.SumUserCardTypeAmountVO;
import com.egeo.orm.BaseReadDAO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserCardRecordReadDAO extends BaseReadDAO<UserCardRecordPO>{

    public List<UserCardRecordPO> sumUserCardTypeAmount(@Param("po")UserCardRecordPO po);
}
