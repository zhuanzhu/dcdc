package com.egeo.components.user.service.read.impl;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.egeo.components.user.service.read.InfoReadService;
import com.egeo.components.user.dto.InfoDTO;
import com.egeo.components.user.po.InfoPO;
import com.egeo.components.user.dao.read.InfoReadDAO;
import com.egeo.components.user.converter.InfoConverter;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("infoReadService")
public class InfoReadServiceImpl implements InfoReadService {
	@Autowired
	private InfoReadDAO infoReadDAO ;

	@Override
	public InfoDTO findInfoById(InfoDTO dto) {
		InfoPO po = InfoConverter.toPO(dto);
		InfoPO infopo = new InfoPO();
		infopo.setId(po.getId());
		InfoPO list = infoReadDAO.findById(infopo);
		return InfoConverter.toDTO(list);
	}

	@Override
	public PageResult<InfoDTO> findInfoOfPage(InfoDTO dto, Pagination page) {
		InfoPO po = InfoConverter.toPO(dto);
		PageResult<InfoPO> pageResult = new PageResult<InfoPO>();
		List<InfoPO> listT = null;
		int cnt = infoReadDAO.countOfPage(po);
		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			listT = infoReadDAO.findOfPage(po, page);
		} else {
			listT = new ArrayList<InfoPO>();
		}
		pageResult.setList(listT);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		
        List<InfoDTO> list = InfoConverter.toDTO(pageResult.getList());
        PageResult<InfoDTO> result = new PageResult<InfoDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<InfoDTO> findInfoAll(InfoDTO dto) {
		InfoPO po = InfoConverter.toPO(dto);
		List<InfoPO> list = infoReadDAO.findAll(po,null);
		return InfoConverter.toDTO(list);
	}

	@Override
	public PageResult<InfoDTO> findUserInfoOfPage(InfoDTO dto, Pagination page) {
		InfoPO po = InfoConverter.toPO(dto);
		
        PageResult<InfoPO> pageResult = new PageResult<InfoPO>();
		List<InfoPO> listT = null;

		int cnt = infoReadDAO.countUserInfoOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			listT = infoReadDAO.findUserInfoOfPage(po, page);
		} else {
			listT = new ArrayList<InfoPO>();
		}
		pageResult.setList(listT);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
        
        List<InfoDTO> list = InfoConverter.toDTO(pageResult.getList());
        PageResult<InfoDTO> result = new PageResult<InfoDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public int findUnreadInfoSum(Long userId, int type, Long platformId) {
		return infoReadDAO.findUnreadInfoSum(userId, type, platformId);
	}

	@Override
	public List<InfoDTO> findUserInfoForMsgBox(Long userId, Integer isRead, Integer type, Long platformId, Date createTime,
			Integer count) {
		List<InfoPO> infoPOList = infoReadDAO.findUserInfoForMsgBox(userId, isRead, type, platformId, createTime, count);
		return InfoConverter.toDTO(infoPOList);
	}
}
	