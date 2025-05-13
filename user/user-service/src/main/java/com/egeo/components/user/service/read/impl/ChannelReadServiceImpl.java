package com.egeo.components.user.service.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.service.read.ChannelReadService;
import com.egeo.components.user.converter.ChannelConverter;
import com.egeo.components.user.dao.read.ChannelReadDAO;
import com.egeo.components.user.dto.ChannelDTO;
import com.egeo.components.user.po.ChannelPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("channelReadService")
public class ChannelReadServiceImpl implements ChannelReadService {
	@Autowired
	private ChannelReadDAO channelReadDAO;

	
	@Override
	public ChannelDTO findChannelById(ChannelDTO dto) {
		ChannelPO po = new ChannelPO();
		po.setId(dto.getId());
		ChannelPO list = channelReadDAO.findById(po);
		return ChannelConverter.toDTO(list);
	}


	@Override
	public PageResult<ChannelDTO> findChannelOfPage(ChannelDTO dto, Pagination page) {
		ChannelPO po = ChannelConverter.toPO(dto);

		PageResult<ChannelPO> pageResult = new PageResult<ChannelPO>();
		List<ChannelPO> listT = null;

		int cnt = channelReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			listT = channelReadDAO.findOfPage(po, page);
		} else {
			listT = new ArrayList<ChannelPO>();
		}
		pageResult.setList(listT);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
        
        List<ChannelDTO> list = ChannelConverter.toDTO(pageResult.getList());
        PageResult<ChannelDTO> result = new PageResult<ChannelDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<ChannelDTO> findChannelAll(ChannelDTO dto) {
		ChannelPO po = ChannelConverter.toPO(dto);
		List<ChannelPO> list = channelReadDAO.findAll(po,null);
		return ChannelConverter.toDTO(list);
	}
	/**
	 * 根据版本类型：1、安卓 2、ios查询渠道信息
	 * @param vo
	 * @param req
	 * @return
	 */
	@Override
	public List<ChannelDTO> findChannelByType(int type) {
		ChannelPO channelPO = new ChannelPO();
		channelPO.setType(type);
		List<ChannelPO> list = channelReadDAO.findAll(channelPO,null);
		return ChannelConverter.toDTO(list);
	}
}
	