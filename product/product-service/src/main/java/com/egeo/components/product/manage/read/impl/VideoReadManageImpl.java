package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.VideoReadManage;
import com.egeo.components.product.dao.read.VideoReadDAO;
import com.egeo.components.product.po.VideoPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class VideoReadManageImpl implements VideoReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private VideoReadDAO videoReadDAO;
	
	public VideoPO findVideoById(VideoPO po) {
		VideoPO videopo = new VideoPO();
		videopo.setId(po.getId());
		return videoReadDAO.findById(videopo);
	}

	public PageResult<VideoPO> findVideoOfPage(VideoPO po, Pagination page) {
		
		PageResult<VideoPO> pageResult = new PageResult<VideoPO>();
		List<VideoPO> list = null;

		int cnt = videoReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = videoReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<VideoPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<VideoPO> findVideoAll(VideoPO po) {

		return videoReadDAO.findAll(po,null);
	}
	
}
	