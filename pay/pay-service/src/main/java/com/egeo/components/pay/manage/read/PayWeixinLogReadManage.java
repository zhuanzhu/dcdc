package com.egeo.components.pay.manage.read;

import com.egeo.components.pay.po.PayWeixinLogPO;	

public interface PayWeixinLogReadManage {

	PayWeixinLogPO queryPayWeixinLogByOrderCode(String orderCode);



}
	