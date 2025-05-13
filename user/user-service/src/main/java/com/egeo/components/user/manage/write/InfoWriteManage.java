package com.egeo.components.user.manage.write;

import java.util.List;

import com.egeo.components.user.po.InfoPO;


public interface InfoWriteManage {

	Long insertInfoWithTx(InfoPO po,List<Long> sendWayIds);

	int updateInfoWithTx(InfoPO po);

	int deleteInfoWithTx(InfoPO po);
}
	