package com.egeo.components.cms.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.read.ImagetextReadManage;
import com.egeo.components.cms.dao.read.ImagetextReadDAO;
import com.egeo.components.cms.po.ImagetextPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class ImagetextReadManageImpl implements ImagetextReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ImagetextReadDAO imagetextReadDAO;
	
	public ImagetextPO findImagetextById(ImagetextPO po) {
		ImagetextPO imagetextpo = new ImagetextPO();
		imagetextpo.setId(po.getId());
		return imagetextReadDAO.findById(imagetextpo);
	}

	public PageResult<ImagetextPO> findImagetextOfPage(ImagetextPO po, Pagination page) {
		
		PageResult<ImagetextPO> pageResult = new PageResult<ImagetextPO>();
		List<ImagetextPO> list = null;

		int cnt = imagetextReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = imagetextReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<ImagetextPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<ImagetextPO> findImagetextAll(ImagetextPO po) {

		return imagetextReadDAO.findAll(po,null);
	}

	@Override
	public List<ImagetextPO> queryImagetextByGroupId(Long groupId) {
		return imagetextReadDAO.queryImagetextByGroupId(groupId);
	}
	
}
	