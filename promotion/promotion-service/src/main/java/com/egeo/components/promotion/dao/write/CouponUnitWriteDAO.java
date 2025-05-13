package com.egeo.components.promotion.dao.write;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.promotion.po.CouponUnitPO;
import com.egeo.orm.BaseWriteDAO;

public interface CouponUnitWriteDAO extends BaseWriteDAO<CouponUnitPO> {

	/**
	 * 更新时考虑特殊控制情况
	 * @param couponUnitPO_
	 * @return
	 */
	int updateForNull(@Param("po")CouponUnitPO po);

    Integer insertCouponUnitList(@Param("poList")List<CouponUnitPO> poList);

    int updateByParam(@Param("po")CouponUnitPO po);

    int updateCouponUnitStatusByParamsWithTx(@Param("couponBatchId")Long couponBatchId, @Param("startNum")Long startNum, @Param("endNum")Long endNum,@Param("data") Date date);

    int updateCouponUnitLockedByCouponUnitId(@Param("couponUnitId")Long couponUnitId);

    int updateCouponUnitRemoveLock(@Param("oldUnitCode")String oldUnitCode);
    
    void updateCouponUnitReadStatus(@Param("ids")List<Long> ids);
}
	