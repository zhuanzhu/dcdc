package com.egeo.components.user.bean;

import com.egeo.components.user.vo.UserBaseInfoResponse;
import com.github.pagehelper.PageInfo;

public class UniAuthUserInfoPageResponse {
	private PageInfo<UserBaseInfoResponse> page;

	public PageInfo<UserBaseInfoResponse> getPage() {
		return page;
	}

	public void setPage(PageInfo<UserBaseInfoResponse> page) {
		this.page = page;
	}
}
