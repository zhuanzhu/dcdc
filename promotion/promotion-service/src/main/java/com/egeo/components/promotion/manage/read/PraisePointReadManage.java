package com.egeo.components.promotion.manage.read;

import com.egeo.components.promotion.po.PraisePointPO;	

public interface PraisePointReadManage {

	/**
	 * 根据用户id查询点赞福豆
	 * @param userId
	 * @return
	 */
	PraisePointPO queryPraisePointByUserId(Long userId);



}
	