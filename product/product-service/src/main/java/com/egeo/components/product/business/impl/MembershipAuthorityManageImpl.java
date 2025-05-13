package com.egeo.components.product.business.impl;
	

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.egeo.components.product.converter.MembershipAuthorityConverter;
import com.egeo.components.product.converter.MembershipConverter;
import com.egeo.components.product.facade.AuthorityFacade;
import com.egeo.components.product.facade.MembershipFacade;
import com.egeo.components.product.vo.MembershipAuthorityVO;
import com.egeo.components.product.dto.AuthorityDTO;
import com.egeo.components.product.dto.MembershipDTO;
import com.egeo.web.JsonResult;
import com.egeo.utils.EmptyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.MembershipAuthorityManage;
import com.egeo.components.product.facade.MembershipAuthorityFacade;
import com.egeo.components.product.dto.MembershipAuthorityDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import org.springframework.web.bind.annotation.ResponseBody;

@Service("membershipAuthority")
public class MembershipAuthorityManageImpl implements MembershipAuthorityManage{

	
	@Resource(name="membershipAuthorityFacade")
	private MembershipAuthorityFacade membershipAuthorityFacade;
	@Resource(name="authorityFacade")
	private AuthorityFacade authorityFacade;
	@Resource(name = "membershipFacade")
	private MembershipFacade membershipFacade;

	@Override
	public JsonResult<PageResult<MembershipAuthorityVO>> findMembershipAuthorityByMembershipId(Long membershipId, Long platformId,Pagination page) {
		PageResult<MembershipAuthorityVO> voPageResult = new PageResult<MembershipAuthorityVO>();
		List<MembershipAuthorityVO> list = new ArrayList<>();
		MembershipAuthorityDTO membershipAuthorityDTO = new MembershipAuthorityDTO();
		if(EmptyUtil.isEmpty(membershipId)||EmptyUtil.isBlank(membershipId.toString())){
			//直接返回全部的权限
			AuthorityDTO authorityDTO = new AuthorityDTO();
			authorityDTO.setPlatformId(platformId);
			PageResult<AuthorityDTO> authorityOfPage = authorityFacade.findAuthorityOfPage(authorityDTO, page);
			for(AuthorityDTO dto:authorityOfPage.getList()){
				MembershipAuthorityVO vo = new MembershipAuthorityVO();
				vo.setAuthorityId(dto.getId());
				vo.setAuthorityName(dto.getAuthorityName());
				vo.setRemarks(dto.getRemarks());
				list.add(vo);
			}
			voPageResult.setList(list);
			voPageResult.setTotalSize(authorityOfPage.getTotalSize());
			voPageResult.setPageNo(authorityOfPage.getPageNo());
			voPageResult.setPageSize(authorityOfPage.getPageSize());
			return JsonResult.success(voPageResult);

		}
		//查询membershipauthtory
		membershipAuthorityDTO.setMembershipId(membershipId);
		membershipAuthorityDTO.setPlatformId(platformId);
		page.setOrderBy("is_stop");
		PageResult<MembershipAuthorityDTO> dtoPageResult=membershipAuthorityFacade.findMembershipAuthorityByMembershipId(membershipAuthorityDTO,page);

		BeanUtils.copyProperties(dtoPageResult,voPageResult);
		//如果查询无结果返回
		if(EmptyUtil.isEmpty(dtoPageResult.getList())){
			voPageResult.setList(null);
			return JsonResult.success(voPageResult);
		}

		//有结果返回

		List<MembershipAuthorityVO> voList = MembershipAuthorityConverter.toVO(dtoPageResult.getList());
		voPageResult.setList(voList);
		//根据authority_id查询权限名和备注
		for(MembershipAuthorityVO vo:voPageResult.getList()){
			AuthorityDTO authorityDTO = new AuthorityDTO();
			authorityDTO.setId(vo.getAuthorityId());
			AuthorityDTO authorityById = authorityFacade.findAuthorityById(authorityDTO);
			if(EmptyUtil.isEmpty(authorityById)){
				return JsonResult.fail("会籍中的权限配置值有误!");
			}
			vo.setAuthorityName(authorityById.getAuthorityName());
			vo.setRemarks(authorityById.getRemarks());

		}
		//根据会籍id查询已停用的权限



		return JsonResult.success(voPageResult);
	}

	@Override
	public JsonResult<Object> updateMembershipAuthorityByMembershipId(Long membershipId, String membershipCode,
																	   String membershipName, String membershipLogImgUrl,
																	   Long linkedSkuId, Long categoryId,
																	   String categoryName, Integer validPeriodVal,
																	   Integer validPeriodUnit, List<Long> authorityIds,
																	   String remarks, Long platformId) {
		/*if(EmptyUtil.isEmpty(membershipId)){
			return JsonResult.fail("会籍id不能为空");
		}*/
		if(EmptyUtil.isEmpty(membershipCode)){
			return JsonResult.fail("会籍code不能为空");
		}
		if(EmptyUtil.isEmpty(membershipName)){
			return JsonResult.fail("会籍name不能为空");
		}
		if(EmptyUtil.isEmpty(linkedSkuId)){
			return JsonResult.fail("会籍关联skuid不能为空");
		}
		if(EmptyUtil.isEmpty(categoryId)){
			return JsonResult.fail("分类id不能为空");
		}
		if(EmptyUtil.isEmpty(categoryName)){
			return JsonResult.fail("分类name不能为空");
		}
		if(EmptyUtil.isEmpty(validPeriodVal)){
			return JsonResult.fail("有效期值不能为空");
		}
		if(EmptyUtil.isEmpty(validPeriodUnit)){
			return JsonResult.fail("有效期单位不能为空");
		}
		if(EmptyUtil.isEmpty(authorityIds)){
			return JsonResult.fail("权限不能为空");
		}
		//校验code和name是否已存在
		boolean res=checkOnly(membershipId,membershipCode,membershipName,platformId);
		if(!res){
			return JsonResult.fail("户籍code或者会籍name重复");
		}
		MembershipDTO membershipDTO = new MembershipDTO();

		membershipDTO.setPlatformId(platformId);
		membershipDTO.setCategoryId(categoryId);
		membershipDTO.setCategoryName(categoryName);
		membershipDTO.setLinkedSkuId(linkedSkuId);
		membershipDTO.setMembershipCode(membershipCode);
		membershipDTO.setMembershipLogImgUrl(membershipLogImgUrl);
		membershipDTO.setMembershipName(membershipName);
		membershipDTO.setRemarks(remarks);
		membershipDTO.setValidPeriodUnit(validPeriodUnit);
		membershipDTO.setValidPeriodVal(validPeriodVal);

		if(membershipId!=null){
			//更新
			membershipDTO.setId(membershipId);
			//获取所有的权限list
			MembershipAuthorityDTO membershipAuthorityDTO = new MembershipAuthorityDTO();
			membershipAuthorityDTO.setMembershipId(membershipId);
			membershipAuthorityDTO.setPlatformId(platformId);

			List<MembershipAuthorityDTO> membershipAuthorityAll = membershipAuthorityFacade.findMembershipAuthorityAll(membershipAuthorityDTO);
			List<MembershipAuthorityDTO> dtos = new ArrayList<>();
			for(MembershipAuthorityDTO authorityDTO:membershipAuthorityAll){
				boolean flag = false;
				MembershipAuthorityDTO dto = new MembershipAuthorityDTO();
				for(Long aut:authorityIds){

					if(aut==authorityDTO.getAuthorityId()){
						flag = true;
						//配置的权限
						dto.setId(authorityDTO.getId());
						dto.setMembershipId(membershipId);
						dto.setPlatformId(platformId);
						dto.setAuthorityId(aut);
						dto.setIsStop(0);
					}
				}
				if(!flag){
					//未配置的权限
					dto.setId(authorityDTO.getId());
					dto.setAuthorityId(authorityDTO.getAuthorityId());
					dto.setIsStop(1);
					dto.setPlatformId(platformId);
					dto.setMembershipId(membershipId);
				}
				dtos.add(dto);
			}
			//更新会籍表和会籍_权限关联表
			membershipAuthorityFacade.updateMembershipAuthorityWithTx(dtos,membershipDTO);
		}else{
			//新增,对code和name进行唯一校验
			AuthorityDTO authorityDTO1=new AuthorityDTO();
			authorityDTO1.setPlatformId(platformId);
			List<AuthorityDTO> authorityAll = authorityFacade.findAuthorityAll(authorityDTO1);
			List<MembershipAuthorityDTO> dtos = new ArrayList<>();
			for(AuthorityDTO dto:authorityAll){
				MembershipAuthorityDTO authorityDTO = new MembershipAuthorityDTO();
				boolean flag = false;
				for(Long aut:authorityIds){
					if(dto.getId()==aut){
						authorityDTO.setIsStop(0);
						authorityDTO.setAuthorityId(aut);
						authorityDTO.setPlatformId(platformId);
						dtos.add(authorityDTO);
						flag = true;
					}
				}
				if(!flag){
					authorityDTO.setPlatformId(platformId);
					authorityDTO.setAuthorityId(dto.getId());
					authorityDTO.setIsStop(1);
					dtos.add(authorityDTO);

				}

			}
			membershipAuthorityFacade.insertMembershipAuthorityWithTx(dtos, membershipDTO);



		}





		return JsonResult.success();
	}

private boolean checkOnly(Long id, String code, String name,Long platformId){

		//新增,只需要验证code和name是否存在即可
	//验证code
		MembershipDTO membershipDTO = new MembershipDTO();
		membershipDTO.setMembershipCode(code);
		membershipDTO.setPlatformId(platformId);
		List<MembershipDTO> all = membershipFacade.findMembershipAll(membershipDTO);

		if(EmptyUtil.isNotEmpty(all)){
			if(EmptyUtil.isNotEmpty(id)){
				for(MembershipDTO dto:all){
					if(dto.getId()!=id){
						return false;
					}
				}
			}
			if(EmptyUtil.isEmpty(id)){
				return false;
			}
		}
		membershipDTO.setMembershipCode(null);
		membershipDTO.setMembershipName(name);
		membershipDTO.setPlatformId(platformId);
		List<MembershipDTO> list = membershipFacade.findMembershipAll(membershipDTO);
		if(EmptyUtil.isNotEmpty(list)){
			if(EmptyUtil.isNotEmpty(id)){
				for(MembershipDTO dto:list){
					if(dto.getId()!=id){
						return false;
					}
				}
			}else{
				return false;
			}
		}


	return true;
}



/*

	@Override
	public MembershipAuthorityDTO findMembershipAuthorityById(MembershipAuthorityDTO dto) {
		return membershipAuthorityFacade.findMembershipAuthorityById(dto);
	}

	@Override
	public PageResult<MembershipAuthorityDTO> findMembershipAuthorityOfPage(MembershipAuthorityDTO dto, Pagination page) {
		return membershipAuthorityFacade.findMembershipAuthorityOfPage(dto, page);
	}

	@Override
	public List<MembershipAuthorityDTO> findMembershipAuthorityAll(MembershipAuthorityDTO dto) {
		return membershipAuthorityFacade.findMembershipAuthorityAll(dto);
	}

	@Override
	public Long insertMembershipAuthorityWithTx(MembershipAuthorityDTO dto) {
		return membershipAuthorityFacade.insertMembershipAuthorityWithTx(dto);
	}

	@Override
	public int updateMembershipAuthorityWithTx(MembershipAuthorityDTO dto) {
		return membershipAuthorityFacade.updateMembershipAuthorityWithTx(dto);
	}

	@Override
	public int deleteMembershipAuthorityWithTx(MembershipAuthorityDTO dto) {
		return membershipAuthorityFacade.deleteMembershipAuthorityWithTx(dto);
	}

*/

}
	