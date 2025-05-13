package com.egeo.components.promotion.controller.api;


import com.egeo.components.promotion.business.UserCardRecordManage;
import com.egeo.components.promotion.dto.UserCardRecordDTO;
import com.egeo.components.promotion.enums.CardStateEnum;
import com.egeo.components.promotion.enums.CardUseStateEnum;
import com.egeo.components.promotion.vo.GrantUserBuyCardVO;
import com.egeo.components.promotion.vo.SumUserCardTypeAmountReqVO;
import com.egeo.components.promotion.vo.SumUserCardTypeAmountVO;
import com.egeo.components.utils.FHCollectionUtils;
import com.egeo.entity.CacheUser;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;


@Controller
@RequestMapping("/api/promotion/userCardRecord")
public class UserCardRecordAction extends BaseSpringController {

	@Resource(name="userCardRecord")
	private UserCardRecordManage userCardRecordManage;


	// 业务方法：
	@RequestMapping(value = "/findUserCardRecordById")
	@ResponseBody
	public JsonResult<UserCardRecordDTO> findUserCardRecordById(Long id ) {
		UserCardRecordDTO dto = new UserCardRecordDTO();
		dto.setId(id);
		UserCardRecordDTO rt = userCardRecordManage.findUserCardRecordById(dto);
		return success(rt);

	}



	// 业务方法：
	@RequestMapping(value = "/findUserCardRecordAll")
	@ResponseBody
	public JsonResult<List<UserCardRecordDTO>> findUserCardRecordAll(UserCardRecordDTO dto,HttpServletRequest req ) {
		List<UserCardRecordDTO> rt = userCardRecordManage.findUserCardRecordAll(dto);
		return success(rt);

	}

	// 业务方法：
	@RequestMapping(value = "/findUserCardRecordOfPage")
	@ResponseBody
	public JsonResult<PageResult<UserCardRecordDTO>> findUserCardRecordOfPage(UserCardRecordDTO dto,Pagination page,HttpServletRequest req ) {

		PageResult<UserCardRecordDTO> rt = userCardRecordManage.findUserCardRecordOfPagePlat(dto, page);
		return success(rt);

	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertUserCardRecordWithTx")
	@ResponseBody
	public JsonResult<Long> insertUserCardRecordWithTx(UserCardRecordDTO dto,HttpServletRequest req ) {
		Long rt = userCardRecordManage.insertUserCardRecordWithTx(dto);
		return success(rt);
	}

	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateUserCardRecordByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateUserCardRecordByIdWithTx(UserCardRecordDTO dto,HttpServletRequest req ) {
		int rt = userCardRecordManage.updateUserCardRecordWithTx(dto);
		return success(rt);
	}

	// 业务方法：
	@RequestMapping(value = "/deleteUserCardRecordWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteUserCardRecordWithTx(UserCardRecordDTO dto,HttpServletRequest req ) {
		int rt = userCardRecordManage.deleteUserCardRecordWithTx(dto);
		return success(rt);
	}

	// 业务方法：员工绑卡，返回成功或失败
	@RequestMapping(value = "/grantUserBuyCard")
	@ResponseBody
	public JsonResult<String> grantUserBuyCard(@RequestBody List<GrantUserBuyCardVO> list) {
		CacheUser cacheUser = getCacheUser();
		return userCardRecordManage.grantUserBuyCard(list,cacheUser.getId());
	}


	// 业务方法：根据客户端登录用户获取有效的购物卡信息
	@RequestMapping(value = "/findClientUserCardRecordOfPage")
	@ResponseBody
	public JsonResult<PageResult<UserCardRecordDTO>> findClientUserCardRecordOfPage(UserCardRecordDTO dto,Pagination page,HttpServletRequest req ) {
		if(EmptyUtil.isEmpty(dto.getUserId())){
			CacheUser cacheUser = getCacheUser();
			dto.setUserId(cacheUser.getId());
		}
		dto.setCardState(CardStateEnum.EFFECTIVE.getState());
		if(dto.getUseState() !=null && Objects.equals(dto.getUseState(), CardUseStateEnum.UN_USE.getUseState())){
			dto.setUseStates(Arrays.asList(CardUseStateEnum.UN_USE.getUseState(),CardUseStateEnum.USE_ING.getUseState()));
			dto.setUseState(null);
		}
		PageResult<UserCardRecordDTO> rt = userCardRecordManage.findClientUserCardRecordOfPage(dto, page);
		return success(rt);

	}

	// 业务方法：根据客户端登录用户统计有效的购物卡记录数
	@RequestMapping(value = "/countUserCardRecord")
	@ResponseBody
	public JsonResult<Integer> countUserCardRecord(UserCardRecordDTO dto,HttpServletRequest req ) {
		if(EmptyUtil.isEmpty(dto.getUserId())){
			CacheUser cacheUser = getCacheUser();
			dto.setUserId(cacheUser.getId());
		}
		dto.setCardState(CardStateEnum.EFFECTIVE.getState());
		if(dto.getUseState() !=null && Objects.equals(dto.getUseState(), CardUseStateEnum.UN_USE.getUseState())){
			dto.setUseStates(Arrays.asList(CardUseStateEnum.UN_USE.getUseState(),CardUseStateEnum.USE_ING.getUseState()));
			dto.setUseState(null);
		}
		Integer rt  = userCardRecordManage.countUserCardRecord(dto);
		return success(rt);
	}

	@RequestMapping(value = "/sumUserCardTypeAmount")
	@ResponseBody
	public JsonResult<List<SumUserCardTypeAmountVO>> sumUserCardTypeAmount(SumUserCardTypeAmountReqVO vo, HttpServletRequest req ) {
		if(EmptyUtil.isEmpty(vo.getUserId())){
			CacheUser cacheUser = getCacheUser();
			vo.setUserId(cacheUser.getId());
		}
		List<SumUserCardTypeAmountVO> queryRT  = userCardRecordManage.sumUserCardTypeAmount(vo);
		return success(queryRT);
	}

	/**
	 * @Description 执行让用户卡片记录过期
	 **/
	@RequestMapping(value = "/cancelUserCard")
	@ResponseBody
	public JsonResult<Boolean> cancelUserCard( HttpServletRequest req ) {
		return success(userCardRecordManage.cancelUserCard());
	}

	@RequestMapping(value = "/exportUserCardRecordSearch")
	@ResponseBody
	public JsonResult<Map<String,Object>> exportUserCardRecordSearch(UserCardRecordDTO dto, HttpServletRequest req ) {
		if(EmptyUtil.isEmpty(dto.getUserId())  && EmptyUtil.isEmpty(dto.getCardName())
			&& EmptyUtil.isEmpty(dto.getCardNo()) && EmptyUtil.isEmpty(dto.getCardNameLike()) && EmptyUtil.isEmpty(dto.getCardNoLike())
			&& EmptyUtil.isEmpty(dto.getCardType()) && EmptyUtil.isEmpty(dto.getUseState()) && EmptyUtil.isEmpty(dto.getCompanyId())){
			return JsonResult.fail("导出失败!查询条件：卡片名称,卡片编号，绑定用户，卡片类型，使用状态，所属企业至少选填一个");
		}
		JsonResult<Map<String,Object>> rt = userCardRecordManage.exportUserCardRecordSearch(dto);
		return rt;

	}
}
