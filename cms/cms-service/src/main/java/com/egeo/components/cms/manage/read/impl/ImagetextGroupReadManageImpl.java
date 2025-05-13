package com.egeo.components.cms.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.read.ImagetextGroupReadManage;
import com.egeo.components.cms.dao.read.ImagetextGroupReadDAO;
import com.egeo.components.cms.po.ImagetextGroupPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class ImagetextGroupReadManageImpl implements ImagetextGroupReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ImagetextGroupReadDAO imagetextGroupReadDAO;
	
	public ImagetextGroupPO findImagetextGroupById(ImagetextGroupPO po) {
		ImagetextGroupPO imagetextGrouppo = new ImagetextGroupPO();
		imagetextGrouppo.setId(po.getId());
		return imagetextGroupReadDAO.findById(imagetextGrouppo);
	}

	public PageResult<ImagetextGroupPO> findImagetextGroupOfPage(ImagetextGroupPO po, Pagination page) {
		
		PageResult<ImagetextGroupPO> pageResult = new PageResult<ImagetextGroupPO>();
		List<ImagetextGroupPO> list = null;

		int cnt = imagetextGroupReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = imagetextGroupReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<ImagetextGroupPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<ImagetextGroupPO> findImagetextGroupAll(ImagetextGroupPO po) {

		return imagetextGroupReadDAO.findAll(po,null);
	}

	@Override
	public ImagetextGroupPO queryImagetextGroupByInstId(Long instId, Integer groupType) {
		return imagetextGroupReadDAO.queryImagetextGroupByInstId(instId, groupType);
	}
	
}
	