package com.egeo.components.product.business.impl;
	

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import com.egeo.components.product.converter.AuthorityConverter;
import com.egeo.components.product.converter.MembershipConverter;
import com.egeo.components.product.facade.*;
import com.egeo.components.product.vo.AuthorityVO;
import com.egeo.components.product.vo.MembershipResultVO;
import com.egeo.components.product.vo.MembershipVO;
import com.egeo.components.product.dto.*;
import com.egeo.exception.BusinessException;
import com.egeo.web.JsonResult;
import com.egeo.utils.EmptyUtil;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.MembershipManage;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("membership")
public class MembershipManageImpl implements MembershipManage{


	
	@Resource(name="membershipFacade")
	private MembershipFacade membershipFacade;
	@Resource(name = "skuFacade")
	private SkuFacade skuFacade;
@Resource(name ="membershipAuthorityFacade")
	private MembershipAuthorityFacade membershipAuthorityFacade;

	@Resource(name = "authorityFacade")
	private AuthorityFacade authorityFacade;



	@Override
	public JsonResult<PageResult<MembershipVO>> findMembershipOfPage(Long categoryId,
																	 String membershipName,
																	 String linkedSkuName,
																	 Long platformId,
																	 Pagination page) {

		//返回前端的分页结果
		PageResult<MembershipVO> membershipVOPageResult = new PageResult<>();
		//判断是否搜索关联商品
		List<SkuDTO> skuDTOList = new ArrayList<>();
		List<Long> skuIdList = new ArrayList<>();
		if(linkedSkuName!=null){
			skuDTOList=skuFacade.findSkuLikeName(linkedSkuName,platformId);
			if(EmptyUtil.isEmpty(skuDTOList)){
				//需要搜索的linkedskuname不存在
				membershipVOPageResult.setList(null);
				membershipVOPageResult.setPageNo(1);
				membershipVOPageResult.setPageSize(page.getPageSize());
				membershipVOPageResult.setTotalSize(0);
			}else {
				for(SkuDTO skuDTO:skuDTOList){
					skuIdList.add(skuDTO.getId());
				}
//				skuIdList = skuDTOList.stream().map(s -> s.getId()).collect(Collectors.toList());

			}


		}
		//根据skuidlist和搜索参数进行搜索
		PageResult<MembershipDTO> membershipDTOPageResult = new PageResult<>();


		membershipDTOPageResult=membershipFacade.findMembershipOfPageByParam(categoryId,membershipName,skuIdList,platformId,page);
		List<MembershipDTO> list = membershipDTOPageResult.getList();
		if(EmptyUtil.isNotEmpty(list)){
			List<MembershipVO> membershipVOList = MembershipConverter.toVO(list);
			//根据skuid查询skuname
			for(MembershipVO vo:membershipVOList){
				SkuDTO skuDTO = new SkuDTO();
				skuDTO.setId(vo.getLinkedSkuId());
				SkuDTO skuById = skuFacade.findSkuById(skuDTO);
				if(EmptyUtil.isEmpty(skuById)){
					throw new BusinessException("[查询会籍业务]:搜索的skuname有异常信息");
				}
				vo.setLinkedSkuName(skuById.getSkuName());
			}

			membershipVOPageResult.setList(membershipVOList );
		}else{
			membershipVOPageResult.setList(null);
		}
		membershipVOPageResult.setPageNo(membershipDTOPageResult.getPageNo());
		membershipVOPageResult.setTotalSize(membershipDTOPageResult.getTotalSize());
		membershipVOPageResult.setPageSize(membershipDTOPageResult.getPageSize());




		return JsonResult.success(membershipVOPageResult);

	}

	@Override
	public JsonResult<MembershipVO> findMembershipById(Long membershipId, Long platformId) {
		if (EmptyUtil.isEmpty(membershipId)) {
			return JsonResult.fail("membershipId为空");
		}
		MembershipDTO dto = new MembershipDTO();
		dto.setId(membershipId);
		dto.setPlatformId(platformId);
		MembershipDTO membershipById = membershipFacade.findMembershipById(dto);
		MembershipVO vo=MembershipConverter.toVO(membershipById);

		//获取该会籍已配置的权限
		MembershipAuthorityDTO membershipAuthorityDTO = new MembershipAuthorityDTO();
		membershipAuthorityDTO.setMembershipId(membershipId);
		membershipAuthorityDTO.setPlatformId(platformId);
		membershipAuthorityDTO.setIsStop(0);
		List<MembershipAuthorityDTO> membershipAuthorityAll = membershipAuthorityFacade.findMembershipAuthorityAll(membershipAuthorityDTO);
		//根据权限id查询出权限对象
		List<AuthorityVO> voList = new ArrayList<>();
		List<Long> authorityList = new ArrayList<>();
		for(MembershipAuthorityDTO mDto:membershipAuthorityAll ){
			AuthorityDTO authority = new AuthorityDTO();
			authority.setId(mDto.getAuthorityId());
			AuthorityDTO authorityById = authorityFacade.findAuthorityById(authority);
			AuthorityVO authorityVO = AuthorityConverter.toVO(authorityById);
			authorityList.add(authorityById.getId());
			voList.add(authorityVO);
		}
		vo.setAuthorityVOList(voList);
		vo.setAuthorityList(authorityList);


		return JsonResult.success(vo);
	}

	@Override
	public List<MembershipResultVO> findMembershipAll(Long platformId) {
		List<MembershipResultVO> resultVOList = new ArrayList<>();
		//查找出所有拥有预售权限的会籍
		List<MembershipDTO> list = new ArrayList<>();
		MembershipAuthorityDTO dto = new MembershipAuthorityDTO();
		dto.setAuthorityId(1L);
		dto.setIsStop(0);
		List<MembershipAuthorityDTO> membershipAuthorityAll = membershipAuthorityFacade.findMembershipAuthorityAll(dto);
		for(MembershipAuthorityDTO mdto:membershipAuthorityAll){
			MembershipDTO dto1 = new MembershipDTO();
			dto1.setId(mdto.getMembershipId());
			MembershipDTO membershipById = membershipFacade.findMembershipById(dto1);
			list.add(membershipById);
		}

		//查询所有的会籍
		MembershipDTO membershipDTO = new MembershipDTO();
		membershipDTO.setPlatformId(platformId);
		List<MembershipDTO> membershipAll = membershipFacade.findMembershipAll(membershipDTO);
		boolean flag = false;
		for(MembershipDTO membershipDTO1:membershipAll){
			for(MembershipDTO membershipDTO2:list){
				if(membershipDTO1.getId().equals(membershipDTO2.getId())){
					flag = true;
					//可配置,有权限
					MembershipResultVO vo=new MembershipResultVO();
					vo.setId(membershipDTO2.getId());
					vo.setMembershipName(membershipDTO2.getMembershipName());
					vo.setIsUse(1);//可用
					resultVOList.add(vo);
				}
			}
			if(!flag){
				//不可配置,没有预售权限
				MembershipResultVO vo=new MembershipResultVO();
				vo.setId(membershipDTO1.getId());
				vo.setMembershipName(membershipDTO1.getMembershipName());
				vo.setIsUse(0);//不可用
				resultVOList.add(vo);
			}
		}

		return resultVOList;
	}



		/*@Override



	@Override
	public List<MembershipDTO> findMembershipAll(MembershipDTO dto) {
		return membershipFacade.findMembershipAll(dto);
	}

	@Override
	public Long insertMembershipWithTx(MembershipDTO dto) {
		return membershipFacade.insertMembershipWithTx(dto);
	}

	@Override
	public int updateMembershipWithTx(MembershipDTO dto) {
		return membershipFacade.updateMembershipWithTx(dto);
	}

	@Override
	public int deleteMembershipWithTx(MembershipDTO dto) {
		return membershipFacade.deleteMembershipWithTx(dto);
	}*/

}
	