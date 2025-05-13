package com.egeo.components.user.controller.views.response.base;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Copyright (C), 2017-2018, 仁辉科技有限公司 FileName: UniauthDepartmentResponse
 * Author: EDZ Date: 2018/10/16 18:07 Description: 部门 History: <author> <time>
 * <version> <desc> 作者姓名 修改时间 版本号 描述
 */
public class PageResponse<T> {

	private int code = 0;
	private String msg = "成功";
	private PageInfo<T> data;

	public PageInfo<T> getData() {
		return data;
	}

	public void setData(PageInfo<T> data) {
		this.data = data;
	}

	public PageResponse() {
	}

	public PageResponse(List<T> list) {
		PageInfo<T> rslt = new PageInfo<>(list);
		rslt.setList(list);

		this.setData(rslt);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
