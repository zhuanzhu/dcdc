package com.egeo.components.product.service.read.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.MembershipUserReadService;
import com.egeo.components.product.manage.read.MembershipUserReadManage;
import com.egeo.components.product.converter.MembershipUserConverter;
import com.egeo.components.product.dto.MembershipUserDTO;
import com.egeo.components.product.po.MembershipUserPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("membershipUserReadService")
public class MembershipUserReadServiceImpl  implements MembershipUserReadService {
	@Autowired
	private MembershipUserReadManage membershipUserReadManage;

	@Override
	public MembershipUserDTO findMembershipUserById(MembershipUserDTO dto) {
		MembershipUserPO po = MembershipUserConverter.toPO(dto);
		MembershipUserPO list = membershipUserReadManage.findMembershipUserById(po);		
		return MembershipUserConverter.toDTO(list);
	}

	@Override
	public PageResult<MembershipUserDTO> findMembershipUserOfPage(MembershipUserDTO dto, Pagination page) {
		MembershipUserPO po = MembershipUserConverter.toPO(dto);
        PageResult<MembershipUserPO> pageResult = membershipUserReadManage.findMembershipUserOfPage(po, page);
        
        List<MembershipUserDTO> list = MembershipUserConverter.toDTO(pageResult.getList());
        PageResult<MembershipUserDTO> result = new PageResult<MembershipUserDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<MembershipUserDTO> findMembershipUserAll(MembershipUserDTO dto) {
		MembershipUserPO po = MembershipUserConverter.toPO(dto);
		List<MembershipUserPO> list = membershipUserReadManage.findMembershipUserAll(po);		
		return MembershipUserConverter.toDTO(list);
	}

	@Override
	public PageResult<MembershipUserDTO> findMembershipUserAllByParams(Long membershipId, Date startTime, Date endTime, Integer statusCode, Pagination page, List<Long> result) {
		PageResult<MembershipUserPO> poPageResult=membershipUserReadManage.findMembershipUserAllByParams(membershipId, startTime, endTime, statusCode, page, result);
		List<MembershipUserDTO> list = MembershipUserConverter.toDTO(poPageResult.getList());
		PageResult<MembershipUserDTO> res = new PageResult<MembershipUserDTO>();
		res.setList(list);
		res.setPageNo(poPageResult.getPageNo());
		res.setPageSize(poPageResult.getPageSize());
		res.setTotalSize(poPageResult.getTotalSize());
		return res;
	}

	@Override
	public MembershipUserDTO findMembershipUserByMail(Long userId, Long membershipId) {
		MembershipUserDTO userDTO = new MembershipUserDTO();
		userDTO.setUserId(userId);
		userDTO.setMembershipId(membershipId);
		List<MembershipUserPO> membershipUserAll = membershipUserReadManage.findMembershipUserAll(MembershipUserConverter.toPO(userDTO));
		if(membershipUserAll==null||membershipUserAll.size()==0){
			return null;
		}
		return MembershipUserConverter.toDTO(membershipUserAll.get(0));
	}
}
	