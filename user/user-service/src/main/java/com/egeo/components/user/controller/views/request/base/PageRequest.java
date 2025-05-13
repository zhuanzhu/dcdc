package com.egeo.components.user.controller.views.request.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: LG
 * @Description:
 * @Date: Created in 2018/3/23 10:44
 * @Modified By:
 */

public class PageRequest {
    //当前页
    private int pageNum = 1;
    //每页的数量
    private int pageSize = 50;
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
    
}
