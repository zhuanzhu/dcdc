package com.egeo.components.user.controller.views.request;

import java.io.Serializable;

/**
 * Copyright (C), 2017-2018, 仁辉科技有限公司 FileName: PageParam Author: EDZ Date:
 * 2018/10/17 9:59 Description: 分页参数类 History: <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
public class PageParam<T> implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Integer currentPage = 1;
	private Integer pageSize = 50;
	private T params;

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public T getParams() {
		return params;
	}

	public void setParams(T params) {
		this.params = params;
	}

	@Override
	public String toString() {
		return "PageParam [currentPage=" + currentPage + ", pageSize=" + pageSize + ", params=" + params != null
				? params.toString()
				: null + "]";
	}

}
