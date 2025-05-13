package com.egeo.components.order.facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.egeo.components.order.dto.SoRefundItemDTO;
import com.egeo.utils.EmptyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.order.dto.SoRefundDTO;
import com.egeo.components.order.service.read.SoRefundReadService;
import com.egeo.components.order.service.write.SoRefundWriteService;
import com.egeo.components.user.client.UserClient;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import org.springframework.util.CollectionUtils;


@Component
public class SoRefundFacade {

	@Resource
	private SoRefundReadService soRefundReadService;

	@Resource
	private SoRefundWriteService soRefundWriteService;

	@Autowired
	private UserClient userReadService;

	public SoRefundDTO findSoRefundById(SoRefundDTO dto){

		return soRefundReadService.findSoRefundById(dto);
	}

	public PageResult<SoRefundDTO> findSoRefundOfPage(SoRefundDTO dto,List<Long> userIdList, Pagination page){

		return soRefundReadService.findSoRefundOfPage(dto, userIdList, page);

	}

	public List<SoRefundDTO> findSoRefundAll(SoRefundDTO dto){

		return soRefundReadService.findSoRefundAll(dto);

	}

	public Long insertSoRefundWithTx(SoRefundDTO dto){

		return soRefundWriteService.insertSoRefundWithTx(dto);
	}

	public int updateSoRefundWithTx(SoRefundDTO dto){

		return soRefundWriteService.updateSoRefundWithTx(dto);
	}

	public int deleteSoRefundWithTx(SoRefundDTO dto){

		return soRefundWriteService.deleteSoRefundWithTx(dto);

	}

	public List<UserDTO> findUserAll(UserDTO userDTO) {

		return userReadService.findUser(userDTO);
	}

	public List<UserDTO> findUserById(Long userId) {
		List<Long> userIdList = new ArrayList<Long>();
		userIdList.add(userId);

		return userReadService.queryUserByIds(com.egeo.utils.StringUtils.longsToStrings(userIdList));
	}

	public List<SoRefundItemDTO> getRefundItemByBatchId(Long batchId, Long orderId, Integer type){
		return soRefundReadService.getRefundItemByBatchId(batchId,orderId,type);
	}

	public Map<String,Object> getRefundItemSkuNames(Long batchId, Long orderId, Integer type){
		Map<String,Object> objectMap = new HashMap<>();
		List<SoRefundItemDTO> list = getRefundItemByBatchId(batchId,orderId,type);
		if(CollectionUtils.isEmpty(list)){
			return null;
		}
		StringBuffer stringBuffer = new StringBuffer();
		Integer refundCount =0;
		for (SoRefundItemDTO soRefundItemDTO : list) {
			if(EmptyUtil.isNotEmpty(soRefundItemDTO.getSkuName())){
				stringBuffer.append(soRefundItemDTO.getSkuName()).append(",");
			}
			if(soRefundItemDTO.getRefundNum() !=null){
				refundCount = refundCount+soRefundItemDTO.getRefundNum();
			}
		}
		if(stringBuffer.length() >0 && stringBuffer.lastIndexOf(",") !=-1){
			stringBuffer.deleteCharAt(stringBuffer.length()-1);
		}
		//return stringBuffer.toString();
		objectMap.put("skuName",stringBuffer.toString());
		objectMap.put("refundCount",refundCount);
		return objectMap;
	}
}
