package com.egeo.components.order.business.impl;


import java.util.*;

import javax.annotation.Resource;

import com.egeo.components.order.dto.SoChildDTO;
import org.springframework.stereotype.Service;

import com.egeo.components.order.business.SoRefundManage;
import com.egeo.components.order.dto.SoDTO;
import com.egeo.components.order.dto.SoRefundDTO;
import com.egeo.components.order.facade.SoFacade;
import com.egeo.components.order.facade.SoRefundFacade;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;

@Service("soRefund")
public class SoRefundManageImpl implements SoRefundManage{


	@Resource(name="soRefundFacade")
	private SoRefundFacade soRefundFacade;

	@Resource(name="soFacade")
	private SoFacade soFacade;

	@Override
	public SoRefundDTO findSoRefundById(SoRefundDTO dto) {
		return soRefundFacade.findSoRefundById(dto);
	}

	@Override
	public PageResult<Map<String,Object>> findSoRefundOfPage(String soRefundCode, String orderCode,
			String mail,Integer refundState, Long platformId, Pagination page) {
		SoRefundDTO soRefundDTO = new SoRefundDTO();
		soRefundDTO.setPlatformId(platformId);
        soRefundDTO.setRefundState(refundState);
		// 根据条件进行筛选
		// 订单
		if (EmptyUtil.isNotEmpty(orderCode)) {
			SoDTO soDTO = soFacade.querySoByOrderCode(orderCode);
			soRefundDTO.setSoId(soDTO != null ? soDTO.getId() : -1L);
		}
		// 用户
		List<Long> userIdList = null;
		if (EmptyUtil.isNotEmpty(mail)) {
			UserDTO userDTO = new UserDTO();
			userDTO.setMail(mail);
			List<UserDTO> userDTOList = soRefundFacade.findUserAll(userDTO);
			if (EmptyUtil.isEmpty(userDTOList)) {
				soRefundDTO.setUserId(-1L);
			} else if (userDTOList.size() == 1) {
				soRefundDTO.setUserId(userDTOList.get(0).getId());
			} else {
				 userIdList = new ArrayList<Long>();
				for (UserDTO userDTO_ : userDTOList) {
					userIdList.add(userDTO_.getId());
				}
			}
		}
		// 退款单
		if (EmptyUtil.isNotEmpty(soRefundCode))
			soRefundDTO.setSoRefundCode(soRefundCode);

		PageResult<SoRefundDTO> rt = soRefundFacade.findSoRefundOfPage(soRefundDTO, userIdList, page);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		for (SoRefundDTO soRefundDTO_ : rt.getList()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", soRefundDTO_.getId());
			map.put("soRefundCode", soRefundDTO_.getSoRefundCode());
            String childOrderCode=null;
            if (Objects.nonNull(soRefundDTO_.getBizId())){
                SoChildDTO childDTO=soFacade.findSoChildById(Long.valueOf(soRefundDTO_.getBizId()));
                if (Objects.nonNull(childDTO)){
                    childOrderCode=childDTO.getChildCode();
                }
            }
			map.put("childOrderCode", childOrderCode);	// 预留字段

			map.put("soRefundByFubi", soRefundDTO_.getSoRefundByFubi());
			map.put("soRefundByBuyCard", soRefundDTO_.getSoRefundByBuyCard());
			map.put("soRefundByCash", soRefundDTO_.getSoRefundByCash());
			map.put("soRefundByJidian", soRefundDTO_.getSoRefundByJidian());
			map.put("createTime", soRefundDTO_.getCreateTime().getTime());
			map.put("soRefundReason", soRefundDTO_.getSoRefundReason());
			map.put("remark", soRefundDTO_.getRemark());
			map.put("refundState", soRefundDTO_.getRefundState());
			// 查询订单信息
			SoDTO soDTO = new SoDTO();
			soDTO.setId(soRefundDTO_.getSoId());
			soDTO =  soFacade.findSoById(soDTO);
			map.put("orderCode", soDTO != null ? soDTO.getOrderCode() : null);

			// 查询创建者信息
			List<UserDTO> userDTOList = soRefundFacade.findUserById(soRefundDTO_.getCreatorId());
			map.put("createUser", EmptyUtil.isNotEmpty(userDTOList) ? userDTOList.get(0).getLoginName() : null);

			// 查询用户信息
			List<UserDTO> userDTOList_ = soRefundFacade.findUserById(soRefundDTO_.getUserId());
			map.put("mail", EmptyUtil.isNotEmpty(userDTOList_) ? userDTOList_.get(0).getLoginName() : null);

			list.add(map);
		}

		PageResult<Map<String, Object>> result = new PageResult<Map<String, Object>>();
		result.setList(list);
		result.setPageNo(rt.getPageNo());
		result.setPageSize(rt.getPageSize());
		result.setTotalSize(rt.getTotalSize());

		return result;
	}

	@Override
	public List<SoRefundDTO> findSoRefundAll(SoRefundDTO dto) {
		return soRefundFacade.findSoRefundAll(dto);
	}

	@Override
	public Long insertSoRefundWithTx(SoRefundDTO dto) {
		return soRefundFacade.insertSoRefundWithTx(dto);
	}

	@Override
	public int updateSoRefundWithTx(SoRefundDTO dto) {
		return soRefundFacade.updateSoRefundWithTx(dto);
	}

	@Override
	public int deleteSoRefundWithTx(SoRefundDTO dto) {
		return soRefundFacade.deleteSoRefundWithTx(dto);
	}


}
