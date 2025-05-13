package com.egeo.components.user.service.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.service.read.DownloadReadService;
import com.egeo.components.user.converter.DownloadConverter;
import com.egeo.components.user.dao.read.DownloadReadDAO;
import com.egeo.components.user.dto.DownloadDTO;
import com.egeo.components.user.po.DownloadPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("downloadReadService")
public class DownloadReadServiceImpl implements DownloadReadService {
	@Autowired
	private DownloadReadDAO downloadReadDAO;

	@Override
	public DownloadDTO findDownloadById(DownloadDTO dto) {
		DownloadPO po = DownloadConverter.toPO(dto);
		DownloadPO downloadpo = new DownloadPO();
		downloadpo.setId(po.getId());
		DownloadPO list = downloadReadDAO.findById(downloadpo);	
		return DownloadConverter.toDTO(list);
	}

	@Override
	public PageResult<DownloadDTO> findDownloadOfPage(DownloadDTO dto, Pagination page) {
		DownloadPO po = DownloadConverter.toPO(dto);
		
		PageResult<DownloadPO> pageResult = new PageResult<DownloadPO>();
		List<DownloadPO> listT = null;

		int cnt = downloadReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			listT = downloadReadDAO.findOfPage(po, page);
		} else {
			listT = new ArrayList<DownloadPO>();
		}
		pageResult.setList(listT);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
        
        List<DownloadDTO> list = DownloadConverter.toDTO(pageResult.getList());
        PageResult<DownloadDTO> result = new PageResult<DownloadDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<DownloadDTO> findDownloadAll(DownloadDTO dto) {
		DownloadPO po = DownloadConverter.toPO(dto);
		List<DownloadPO> list = downloadReadDAO.findAll(po,null);	
		return DownloadConverter.toDTO(list);
	}

	@Override
	public PageResult<DownloadDTO> findDownloadOfPageByBlurry(DownloadDTO dto, Pagination page) {
		DownloadPO po = DownloadConverter.toPO(dto);
		PageResult<DownloadPO> pageResult = new PageResult<DownloadPO>();
		List<DownloadPO> listT = null;

		int cnt = downloadReadDAO.countOfPageByBlurry(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			listT = downloadReadDAO.findOfPageByBlurry(po, page);
		} else {
			listT = new ArrayList<DownloadPO>();
		}
		pageResult.setList(listT);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
        
        List<DownloadDTO> list = DownloadConverter.toDTO(pageResult.getList());
        PageResult<DownloadDTO> result = new PageResult<DownloadDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}
}
	