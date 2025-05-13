package com.egeo.components.product.service.read.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.egeo.components.product.condition.MerchantProductUnitCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.MerchantProductReadService;
import com.egeo.components.product.manage.read.MerchantProductReadManage;
import com.egeo.components.product.condition.MerchantProductCondition;
import com.egeo.components.product.converter.MerchantProductConverter;
import com.egeo.components.product.dto.CommodityDetailsDTO;
import com.egeo.components.product.dto.MerchantProductDTO;
import com.egeo.components.product.po.CommodityDetailsPO;
import com.egeo.components.product.po.MerchantProductPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;

@Service("merchantProductReadService")
public class MerchantProductReadServiceImpl  implements MerchantProductReadService {
    @Autowired
    private MerchantProductReadManage merchantProductReadManage;

    @Override
    public MerchantProductDTO findMerchantProductById(MerchantProductDTO dto) {
        MerchantProductPO po = MerchantProductConverter.toPO(dto);
        MerchantProductCondition merchantProductCondition = merchantProductReadManage.findMerchantProductById(po);
        MerchantProductDTO merchantProductDTO = MerchantProductConverter.toDTO(merchantProductCondition);
        if (merchantProductDTO != null) {
            merchantProductDTO.setContent(merchantProductCondition != null ? merchantProductCondition.getContent() : null);
            merchantProductDTO.setRelevanceSuName(merchantProductCondition.getRelevanceSuName());
        }
        return merchantProductDTO;
    }

    @Override
    public PageResult<MerchantProductDTO> findMerchantProductOfPage(BigDecimal priceStart, BigDecimal priceEnd, Integer startProfit, Integer endProfit, List<Long> storeIds, Date startTime, Date endTime, MerchantProductDTO dto, Pagination page, List<String> nameList) {
        MerchantProductPO po = MerchantProductConverter.toPO(dto);
        PageResult<MerchantProductCondition> pageResult = merchantProductReadManage.findMerchantProductOfPage(priceStart,priceEnd,startProfit,endProfit,storeIds,startTime,endTime,po, page,nameList);
        List<MerchantProductCondition> merchantProductConditionList = pageResult.getList();
        List<MerchantProductDTO> list = new ArrayList<MerchantProductDTO>();
        if(EmptyUtil.isEmpty(merchantProductConditionList)){
            list = null;
        }else{
            for (MerchantProductCondition merchantProductCondition : merchantProductConditionList) {
                MerchantProductDTO merchantProductDTO = MerchantProductConverter.toDTO(merchantProductCondition);
                merchantProductDTO.setMerchantName(merchantProductCondition.getMerchantName());
                list.add(merchantProductDTO);
            }
        }

        PageResult<MerchantProductDTO> result = new PageResult<MerchantProductDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
    }

    @Override
    public PageResult<MerchantProductDTO> findPlatformMerchantProductOfPage(BigDecimal priceStart, BigDecimal priceEnd, Integer startProfit, Integer endProfit, List<Long> storeIds, Date startTime, Date endTime, MerchantProductDTO dto, Pagination page, List<String> nameList) {
        MerchantProductPO po = MerchantProductConverter.toPO(dto);
        PageResult<MerchantProductCondition> pageResult = merchantProductReadManage.findPlatformMerchantProductOfPage(priceStart,priceEnd,startProfit,endProfit,storeIds,startTime,endTime,po, page,nameList);
        List<MerchantProductCondition> merchantProductConditionList = pageResult.getList();
        List<MerchantProductDTO> list = new ArrayList<MerchantProductDTO>();
        if(EmptyUtil.isEmpty(merchantProductConditionList)){
            list = null;
        }else{
            for (MerchantProductCondition merchantProductCondition : merchantProductConditionList) {
                MerchantProductDTO merchantProductDTO = MerchantProductConverter.toDTO(merchantProductCondition);
                merchantProductDTO.setMerchantName(merchantProductCondition.getMerchantName());
                list.add(merchantProductDTO);
            }
        }

        PageResult<MerchantProductDTO> result = new PageResult<MerchantProductDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
    }
    @Override
    public List<MerchantProductDTO> findMerchantProductAll(MerchantProductDTO dto) {
        MerchantProductPO po = MerchantProductConverter.toPO(dto);
        List<MerchantProductPO> list = merchantProductReadManage.findMerchantProductAll(po);
        return MerchantProductConverter.toDTO(list);
    }

    /**
     * 根据su草稿id查询基本信息（app预览）
     *
     * @param merchantProductId
     * @return
     */
    @Override
    public MerchantProductDTO findMerchantProductAppById(Long merchantProductId) {
        MerchantProductCondition merchantProductCondition = merchantProductReadManage.findMerchantProductAppById(merchantProductId);
        MerchantProductDTO merchantProductDTO = MerchantProductConverter.toDTO(merchantProductCondition);
        merchantProductDTO.setContent(merchantProductCondition.getContent());
        return merchantProductDTO;
    }

    @Override
    public PageResult<MerchantProductDTO> findCommodityDetailsOfPage(CommodityDetailsDTO dto, Pagination page) {
        List<MerchantProductDTO> list = new ArrayList<MerchantProductDTO>();
        PageResult<MerchantProductDTO> result = new PageResult<MerchantProductDTO>();
        CommodityDetailsPO po = MerchantProductConverter.cdToPO(dto);
        PageResult<MerchantProductCondition> pageResult = merchantProductReadManage.findCommodityDetailsOfPage(po, page);
        if (!EmptyUtil.isEmpty(pageResult)) {
            List<MerchantProductCondition> merchantProductConditionList = pageResult.getList();
            for (MerchantProductCondition merchantProductCondition : merchantProductConditionList) {
                MerchantProductDTO merchantProductDTO = MerchantProductConverter.toDTO(merchantProductCondition);
                merchantProductDTO.setMerchantName(merchantProductCondition.getMerchantName());
                list.add(merchantProductDTO);
            }
            result.setList(list);
            result.setPageNo(pageResult.getPageNo());
            result.setPageSize(pageResult.getPageSize());
            result.setTotalSize(pageResult.getTotalSize());
        }
        return result;
    }

    @Override
    public Long findLastId() {
        return merchantProductReadManage.findLastId();
    }

	@Override
	public long findMaxfrontSerialNumber() {
		// TODO Auto-generated method stub
		return merchantProductReadManage.findMaxfrontSerialNumber();
	}

    @Override
    public List<MerchantProductUnitCondition> exportMerchantProduct(BigDecimal priceStart, BigDecimal priceEnd, Integer startProfit, Integer endProfit, List<Long> storeIds, Date starTime, Date endTime, MerchantProductDTO dto, List<String> nameList) {
        MerchantProductPO po = MerchantProductConverter.toPO(dto);
        Pagination page=new Pagination(1,1000);
        List<MerchantProductUnitCondition> exportVOS=new ArrayList<>();
        PageResult<MerchantProductUnitCondition>  pageResult=merchantProductReadManage.exportMerchantProduct(priceStart,priceEnd,startProfit,endProfit,storeIds,starTime,endTime,po,page,nameList);
        while (EmptyUtil.isNotEmpty(pageResult.getList())){
            exportVOS.addAll(pageResult.getList());
            if (pageResult.getList().size()<page.getPageSize()){
                break;
            }
            page.setPageNo(page.getPageNo()+1);
            pageResult=merchantProductReadManage.exportMerchantProduct(priceStart,priceEnd,startProfit,endProfit,storeIds,starTime,endTime,po,page,nameList);
        }
        return exportVOS;
    }
}
	