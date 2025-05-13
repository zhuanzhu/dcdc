package com.egeo.components.product.facade;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.common.PlatformKeyConstant;
import com.egeo.components.product.dto.StoreDTO;
import com.egeo.components.product.service.read.StoreReadService;
import com.egeo.components.product.service.read.StoreTreeNodeReadService;
import com.egeo.components.product.service.write.StoreWriteService;
import com.egeo.components.promotion.client.CouponStoreClient;
import com.egeo.components.promotion.dto.CouponStoreDTO;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;


@Component
public class StoreFacade {
	
	@Resource
	private StoreReadService storeReadService;
	
	@Resource
	private StoreWriteService storeWriteService;

	@Autowired
	private CouponStoreClient couponStoreReadService;

	@Resource
	private StoreTreeNodeReadService storeTreeNodeReadService;
	
	public StoreDTO findStoreById(StoreDTO dto){
		
		return storeReadService.findStoreById(dto);
	}

	public PageResult<StoreDTO> findStoreOfPage(StoreDTO dto,Pagination page){
		
		return storeReadService.findStoreOfPage(dto, page);
		
	}

	public List<StoreDTO> findStoreAll(StoreDTO dto){
		return storeReadService.findStoreAll(dto);
		
	}

	public List<CouponStoreDTO> findCouponStoreAll(CouponStoreDTO dto){
		return couponStoreReadService.findCouponStoreAll(dto);
	}

	public Long insertStoreWithTx(StoreDTO dto){
		
		return storeWriteService.insertStoreWithTx(dto);
	}

	public int updateStoreWithTx(StoreDTO dto){
		
		return storeWriteService.updateStoreWithTx(dto);
	}

	public int deleteStoreWithTx(StoreDTO dto){
		
		return storeWriteService.deleteStoreWithTx(dto);
		
	}

	public List<StoreDTO> findRootStoreAll(StoreDTO dto) {
		return storeReadService.findRootStoreAll(dto);
	}

	public PageResult<StoreDTO> findRootStoreOfPage(StoreDTO dto, Pagination page) {
		return storeReadService.findRootStoreOfPage(dto, page);
	}

	public List<StoreDTO> findStoreAllByTreeId(Long storeTreeId) {

		return recursionOrganizationData(storeReadService.findStoreAllByTreeId(storeTreeId));
	}

	

   private List<StoreDTO> recursionOrganizationData(List<StoreDTO> storeList) {
       //递归成树结构
       List<StoreDTO> sortList = new ArrayList<StoreDTO>();
       for (StoreDTO tree : storeList) {
           for (StoreDTO t : storeList) {
               if (t.getParentId().equals(tree.getNodeId())) {
                   if (tree.getChildren() == null) {
                       List<StoreDTO> mylistss = new ArrayList<StoreDTO>();
                       mylistss.add(t);
                       tree.setChildren(mylistss);
                   } else {
                       tree.getChildren().add(t);
                   }
               }
           }
           if (tree.getParentId().equals(PlatformKeyConstant.PRODUCT_PLATFORMID)) {
               sortList.add(tree);
           }
       }
       return sortList;
   }

	public StoreDTO findStoreByNodeId(Long nodeId) {
		// TODO Auto-generated method stub
		return storeReadService.findStoreByNodeId(nodeId);
	}	
	
	public List<StoreDTO> findCouponStore(StoreDTO storeDTO) {
		return storeReadService.findCouponStore(storeDTO);
	}

    public StoreDTO getHeadStoreId(Long storeId) {
		StoreDTO headStoreByStoreId = storeReadService.findHeadStoreByStoreId(storeId);
		if(EmptyUtil.isEmpty(headStoreByStoreId)){
			throw new BusinessException(storeId + "不存在总店");
		}
		return headStoreByStoreId;
	}

	public List<StoreDTO> findStoreByPlatformIdAndStoreMenu(StoreDTO dto) {
		return storeReadService.findStoreByPlatformIdAndStoreMenu(dto);
	}
	}
	