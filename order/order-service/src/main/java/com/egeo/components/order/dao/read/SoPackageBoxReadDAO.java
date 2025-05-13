package com.egeo.components.order.dao.read;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.order.po.SoPackageBoxPO;
import com.egeo.orm.BaseReadDAO;

public interface SoPackageBoxReadDAO extends BaseReadDAO<SoPackageBoxPO>{

	/**
	 * 根据箱号查询箱子
	 * @param boxCode
	 * @param soChildId 
	 * @return
	 */
	SoPackageBoxPO queryBoxByBoxCodeAndChildId(@Param("boxCode")Long boxCode, @Param("soChildId")Long soChildId);
}
	