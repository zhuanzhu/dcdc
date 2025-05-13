package com.egeo.components.order.scheduler;

import org.springframework.stereotype.Component;


@Component
public class AccountShedulerFacade {

	/**
	 * 定时任务处理账户备份数据
	 * 数据备份以excel表格形式存储在fastDFS中
	 * 使用数据库记录这些文件,保证它们是可被管理的
	 * 删除存在90天以上的文件
	 * 新建当天的三份文件
	 */
	public void accountBackUp() {
		//查询存在90天以上的文件
		
		//删除文件以及数据库中的数据
		
		//查询当前官方账户信息,制作excel表格,上传至fastDFS并添加数据库记录
		//查询当前所有个人账户信息,制作excel表格,上传至fastDFS并添加数据库记录
		//查询当前普通公司账户信息,制作excel表格,上传至fastDFS并添加数据库记录
	}
	
	
}
