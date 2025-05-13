package com.egeo.components.cms.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.read.ImagetextGroupCompanyReadManage;
import com.egeo.components.cms.dao.read.ImagetextGroupCompanyReadDAO;
import com.egeo.components.cms.po.ImagetextGroupCompanyPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class ImagetextGroupCompanyReadManageImpl implements ImagetextGroupCompanyReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ImagetextGroupCompanyReadDAO imagetextGroupCompanyReadDAO;
	
	public ImagetextGroupCompanyPO findImagetextGroupCompanyById(ImagetextGroupCompanyPO po) {
		ImagetextGroupCompanyPO imagetextGroupCompanypo = new ImagetextGroupCompanyPO();
		imagetextGroupCompanypo.setId(po.getId());
		return imagetextGroupCompanyReadDAO.findById(imagetextGroupCompanypo);
	}

	public PageResult<ImagetextGroupCompanyPO> findImagetextGroupCompanyOfPage(ImagetextGroupCompanyPO po, Pagination page) {
		
		PageResult<ImagetextGroupCompanyPO> pageResult = new PageResult<ImagetextGroupCompanyPO>();
		List<ImagetextGroupCompanyPO> list = null;

		int cnt = imagetextGroupCompanyReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = imagetextGroupCompanyReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<ImagetextGroupCompanyPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<ImagetextGroupCompanyPO> findImagetextGroupCompanyAll(ImagetextGroupCompanyPO po) {

		return imagetextGroupCompanyReadDAO.findAll(po,null);
	}
	
}
	