package com.egeo.components.product.business.impl;

import com.egeo.components.product.business.CakeProductManage;
import com.egeo.components.product.business.ChannelProductManage;
import com.egeo.components.product.common.BusinessException;
import com.egeo.components.product.common.ProductChannelCodeEnum;
import com.egeo.components.product.dto.*;
import com.egeo.components.product.dto.channel.ChannelProductBatchDTO;
import com.egeo.components.product.dto.channel.ChannelProductDTO;
import com.egeo.components.product.dto.channel.ChannelProductPictureDTO;
import com.egeo.components.product.dto.channel.ChannelProductSkuDTO;
import com.egeo.components.product.enums.ChannelPriceConstants;
import com.egeo.components.product.facade.ChannelProductBatchFacade;
import com.egeo.components.product.facade.ChannelProductFacade;
import com.egeo.components.product.facade.ChannelProductPictureFacade;
import com.egeo.components.product.facade.ChannelProductSkuFacade;
import com.egeo.components.product.helper.ChannelPriceHelper;
import com.egeo.components.product.vo.ChannelPriceAuditingVO;

import com.egeo.components.product.vo.ChannelProductDetailRequestVO;
import com.egeo.components.product.vo.ChannelProductDetailVO;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.StringUtils;
import com.egeo.web.JsonResult;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Description 渠道产品接口服务
 * @Author lsl
 * @Version V1.0
 **/
@Service("channelProductManage")
public class ChannelProductManageImpl implements ChannelProductManage {

    @Autowired
    private CakeProductManage cakeProductManage;

    @Autowired
    private ChannelPriceHelper channelPriceHelper;

    @Autowired
    private ChannelProductBatchFacade channelProductBatchFacade;

    @Autowired
    private ChannelProductPictureFacade channelProductPictureFacade;
    @Autowired
    private ChannelProductFacade channelProductFacade;

    @Resource
    private ChannelProductSkuFacade channelProductSkuFacade;

    @Override
    public List<ChannelPriceAuditingVO> findChannelProductForPriceAuditing(String channelCode,List<ChannelPriceDTO> dto){
        List<ChannelPriceAuditingVO> rslt = new ArrayList<>();
        if(CollectionUtils.isEmpty(dto)){
            return rslt;
        }
        //拿到价格配置
        List<ChannelEnterpriseConfigDTO>  channelEnterpriseConfigDTOList = channelPriceHelper.getChannelEnterpriseConfigAll(channelCode,false);
        //查询审核的列表
        List<ChannelPriceAuditingVO>  cakeList = findCakeChannelPriceAuditingVO(channelCode,dto,channelEnterpriseConfigDTOList);
        addToList(rslt,cakeList);
        List<ChannelPriceAuditingVO>  worldList = findWorldChannelPriceAuditingVO(channelCode,dto,channelEnterpriseConfigDTOList);
        addToList(rslt,worldList);
        return rslt;
    }

    private void addToList(List<ChannelPriceAuditingVO> rslt,List<ChannelPriceAuditingVO>  dataList){
        if(!CollectionUtils.isEmpty(dataList)){
            rslt.addAll(dataList);
        }
    }

    private List<ChannelPriceAuditingVO> findWorldChannelPriceAuditingVO(String channelCode, List<ChannelPriceDTO> dtos, List<ChannelEnterpriseConfigDTO> channelEnterpriseConfigDTOList) {
        if(!Objects.equals(channelCode, ProductChannelCodeEnum.WORLD_BUY.getCode())){
            return null;
        }
        List<ChannelPriceAuditingVO>  worldList = new ArrayList<>();
        for (ChannelPriceDTO channelPriceDTO : dtos) {
            ChannelProductDetailRequestVO detailRequestVO = new ChannelProductDetailRequestVO();
            detailRequestVO.setChannelCode(channelCode);
            detailRequestVO.setProductId(channelPriceDTO.getSpuId());
            detailRequestVO.setSkuId(channelPriceDTO.getPid());
            ChannelProductDetailVO channelProductDetailVO = channelProductFacade.getChannelProductDetailUnPrice(detailRequestVO);
            if(Objects.isNull(channelProductDetailVO)){
                continue;
            }
            List<ChannelProductBatchDTO> list  = channelProductDetailVO.getBatchDTOList();
            if(CollectionUtils.isEmpty(list)){
                continue;
            }
            ChannelProductBatchDTO currBatch=  channelProductFacade.getCurrBatch(detailRequestVO,list);
            if(Objects.isNull(currBatch)){
                continue;
            }
            addWorldProductDetailDTO(channelPriceDTO.getPid(),channelProductDetailVO,currBatch,worldList,channelCode,channelEnterpriseConfigDTOList,channelPriceDTO);
        }
        return worldList;
    }

    private List<ChannelPriceAuditingVO> findCakeChannelPriceAuditingVO(String channelCode,List<ChannelPriceDTO> dtos,List<ChannelEnterpriseConfigDTO>  channelEnterpriseConfigDTOList){
        if(!Objects.equals(channelCode, ProductChannelCodeEnum.CAKE.getCode())){
            return null;
        }
        List<ChannelPriceAuditingVO>  cakeList = new ArrayList<>();
        CakeSPUIdSearchProductDetailDTO search= new CakeSPUIdSearchProductDetailDTO();
        for (ChannelPriceDTO channelPriceDTO : dtos) {
           /* search.setSpuId(channelPriceDTO.getPid());
            search.setSkuId(channelPriceDTO.getSpuId());*/
            search.setSpuId(channelPriceDTO.getSpuId());
            search.setSkuId(channelPriceDTO.getPid());
            JsonResult<CakeProductDetailDTO> dtoJsonResult =  cakeProductManage.searchProductDetail(search);
            if(dtoJsonResult !=null && dtoJsonResult.getCode() ==0){
                addCakeProductDetailDTO(search.getSkuId(),dtoJsonResult.getData(),cakeList,channelCode,channelEnterpriseConfigDTOList,channelPriceDTO);
            }
        }

        return cakeList;
    }
    private void addCakeProductDetailDTO(String skuId,CakeProductDetailDTO dto,List<ChannelPriceAuditingVO>  cakeList,String channelCode,List<ChannelEnterpriseConfigDTO>  channelEnterpriseConfigDTOList,ChannelPriceDTO channelPriceDTO){
        if(null == dto){
            return;
        }
        ChannelPriceAuditingVO vo = new ChannelPriceAuditingVO(channelPriceDTO);
        CakeProductDetailProductsDTO productDTO = dto.getProduct();
        vo.setChannelCode(channelCode);
        vo.setName(productDTO.getTitle());
        vo.setIsSelf(0);
        vo.setId(channelPriceDTO.getId());
        vo.setProductId(productDTO.getId());
        List<CakeProductDetailSpecsDTO>  specsList = dto.getSpecs();
        if(CollectionUtils.isEmpty(specsList)){
            calcPriceAndToList(vo,productDTO.getPrice(),productDTO.getMarket_price(),cakeList,channelEnterpriseConfigDTOList,channelPriceDTO);
            return ;
        }
        CakeProductDetailSpecsDTO specsDTO = getCakeProductSkuInfo(dto,skuId);
        if(specsDTO == null){
            throw new BusinessException("未找到匹配的规格");
        }
        vo.setName(productDTO.getTitle()+specsDTO.getName());
        calcPriceAndToList(vo,specsDTO.getMarket_price(),specsDTO.getClearing_price(),cakeList,channelEnterpriseConfigDTOList,channelPriceDTO);
    }

    private void calcPriceAndToList(ChannelPriceAuditingVO vo,String marketPrice,String  clearingPrice,List<ChannelPriceAuditingVO>  cakeList,List<ChannelEnterpriseConfigDTO>  channelEnterpriseConfigDTOList,ChannelPriceDTO channelPriceDTO){
        //计算价格
        Map<String,String> resultMap = channelPriceHelper.calcPrice(marketPrice,clearingPrice,channelEnterpriseConfigDTOList,channelPriceDTO);
        if(resultMap ==null || resultMap.isEmpty()){
            throw new BusinessException("计算价格失败");
        }
        vo.setChannelPrice(resultMap.get(ChannelPriceConstants.CHANNEL_PRICE_KEY));
        vo.setEnterprisePrice(resultMap.get(ChannelPriceConstants.ENTERPRISE_PRICE_KEY));
        vo.setMarketPrice(resultMap.get(ChannelPriceConstants.MARKET_PRICE_KEY));
        vo.setProfit(resultMap.get(ChannelPriceConstants.PROFIT_KEY));
        vo.setPrice(clearingPrice);
        vo.setSalePrice(resultMap.get(ChannelPriceConstants.SALE_PRICE_KEY));
        cakeList.add(vo);
    }


    public CakeProductDetailSpecsDTO getCakeProductSkuInfo(CakeProductDetailDTO dto,String skuId){
        if(StringUtils.isEmpty(skuId)){
            return null;
        }
        if(Objects.isNull(dto)){
            return null;
        }
        List<CakeProductDetailSpecsDTO>  specsList = dto.getSpecs();
        if(CollectionUtils.isEmpty(specsList)){
            return null;
        }
        for (CakeProductDetailSpecsDTO cakeProductDetailSpecsDTO : specsList) {
            if(Objects.equals(cakeProductDetailSpecsDTO.getId(),skuId)){
                return cakeProductDetailSpecsDTO;
            }
        }
        return null;
    }

    private void addChannelProductDetailDTO(String skuId,CakeProductDetailDTO dto,List<ChannelPriceAuditingVO>  channelList,String channelCode,List<ChannelEnterpriseConfigDTO>  channelEnterpriseConfigDTOList,ChannelPriceDTO channelPriceDTO){
        if(null == dto){
            return;
        }
        ChannelPriceAuditingVO vo = new ChannelPriceAuditingVO(channelPriceDTO);
        CakeProductDetailProductsDTO productDTO = dto.getProduct();
        vo.setChannelCode(channelCode);
        vo.setName(productDTO.getTitle());
        vo.setIsSelf(0);
        vo.setId(channelPriceDTO.getId());
        vo.setProductId(productDTO.getId());
        List<CakeProductDetailSpecsDTO>  specsList = dto.getSpecs();
        if(CollectionUtils.isEmpty(specsList)){
            calcPriceAndToList(vo,productDTO.getPrice(),productDTO.getMarket_price(),channelList,channelEnterpriseConfigDTOList,channelPriceDTO);
            return ;
        }
        CakeProductDetailSpecsDTO specsDTO = getCakeProductSkuInfo(dto,skuId);
        if(specsDTO == null){
            throw new BusinessException("未找到匹配的规格");
        }
        vo.setName(productDTO.getTitle()+specsDTO.getName());
        calcPriceAndToList(vo,specsDTO.getClearing_price(),specsDTO.getMarket_price(),channelList,channelEnterpriseConfigDTOList,channelPriceDTO);
    }

    private void addWorldProductDetailDTO(String skuId,ChannelProductDetailVO channelProductDetailVO,ChannelProductBatchDTO currBatch,List<ChannelPriceAuditingVO>  channelList,String channelCode,List<ChannelEnterpriseConfigDTO>  channelEnterpriseConfigDTOList,ChannelPriceDTO channelPriceDTO){
        if(null == channelProductDetailVO){
            return;
        }
        ChannelPriceAuditingVO vo = new ChannelPriceAuditingVO(channelPriceDTO);
        ChannelProductDTO productDTO = channelProductDetailVO.getChannelProductDTO();
        vo.setChannelCode(channelCode);
        vo.setName(productDTO.getTitle());
        vo.setIsSelf(0);
        vo.setId(channelPriceDTO.getId());
        vo.setProductId(productDTO.getProductId());
        if(currBatch == null){
            throw new BusinessException("未找到匹配的规格");
        }
        vo.setName(productDTO.getTitle()+currBatch.getSpecName());
        calcPriceAndToList(vo,currBatch.getPriceMarket(),currBatch.getPriceSettleMent().toPlainString(),channelList,channelEnterpriseConfigDTOList,channelPriceDTO);
    }
}
