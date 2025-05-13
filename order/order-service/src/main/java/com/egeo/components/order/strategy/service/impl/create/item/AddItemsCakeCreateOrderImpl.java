package com.egeo.components.order.strategy.service.impl.create.item;

import com.alibaba.fastjson.JSON;
import com.egeo.components.order.common.ProductChannelCodeEnum;
import com.egeo.components.order.dto.LimitRuleRecordDTO;
import com.egeo.components.order.dto.ReceiverAddressDTO;
import com.egeo.components.order.dto.SoItemDTO;
import com.egeo.components.order.enums.ThirdConst;
import com.egeo.components.order.facade.ProductFacade;
import com.egeo.components.order.strategy.vo.CreateOrderReqVO;
import com.egeo.components.order.strategy.vo.CreateOrderRespVO;
import com.egeo.components.product.dto.CakeProductDetailDTO;
import com.egeo.components.product.dto.CakeProductDetailProductsDTO;
import com.egeo.components.product.dto.CakeProductDetailSpecsDTO;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.components.utils.CakeAddressUtil;
import com.egeo.components.utils.CakeUtil;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/12/5 17:17
 * @Version V1.0
 **/
@Service("addItemsCakeCreateOrderImpl")
public class AddItemsCakeCreateOrderImpl extends AddItemsCommonBase{

    public Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Resource(name = "productFacade")
    private ProductFacade productFacade;

    @Resource
    private CakeUtil cakeUtil;

    @Override
    public String getChannelCode() {
        return ProductChannelCodeEnum.CAKE.getCode();
    }

    @Override
    public JsonResult<CreateOrderRespVO> getProduct(CreateOrderReqVO createOrderReqVO) {
        CakeProductDetailDTO cakeProductDetailDTO = getCakeProductDetailDTO(createOrderReqVO);
        if(cakeProductDetailDTO == null){
            return JsonResult.fail("蛋糕叔叔商品不存在");
        }
        CreateOrderRespVO createOrderRespVO = new CreateOrderRespVO();
        createOrderRespVO.setProductObject(cakeProductDetailDTO);
        createOrderRespVO.setSourceProduct(ThirdConst.Source.CAKE);
        createOrderRespVO.setSource(ThirdConst.Source.CAKE);
        return JsonResult.success(createOrderRespVO);
    }

    private CakeProductDetailDTO getCakeProductDetailDTO(CreateOrderReqVO createOrderReqVO){
        String channelProductId = createOrderReqVO.getChannelProductId();
        Long puId = createOrderReqVO.getPuId();
        Long enterpriseId = createOrderReqVO.getEnterpriseId();
        CakeProductDetailDTO cakeProductDetailDTO =productFacade.getCakeProduct(channelProductId,puId+"",null,null,enterpriseId);
        return cakeProductDetailDTO;
    }

    @Override
    public JsonResult<CreateOrderRespVO> addItemsAndLimitRules(CreateOrderReqVO createOrderReqVO) {
        CakeProductDetailDTO cakeProductDetailDTO = null;
        if(createOrderReqVO.getType().intValue() ==0){
            cakeProductDetailDTO = (CakeProductDetailDTO)createOrderReqVO.getProductObject();
        }else{
            cakeProductDetailDTO =getCakeProductDetailDTO(createOrderReqVO);
        }
        Long puId = createOrderReqVO.getPuId();
        ReceiverAddressDTO addr = createOrderReqVO.getAddr();
        Integer num = createOrderReqVO.getNum();
        Long userId = createOrderReqVO.getUserId();
        Long platformId = createOrderReqVO.getPlatformId();
        String userName = createOrderReqVO.getUserName();
        Long companyId = createOrderReqVO.getCompanyId();
        Long storeId = createOrderReqVO.getStoreId();
        UserDTO user = createOrderReqVO.getUser();
        Long  enterpriseId =createOrderReqVO.getEnterpriseId();
        String channelProductId = createOrderReqVO.getChannelProductId();
        JsonResult checkResult = checkCakeProductInfo(channelProductId,puId+"",cakeProductDetailDTO,addr,enterpriseId);
        if(Objects.nonNull(checkResult)){
            return checkResult;
        }
        Double orderAmount = 0D;
        CreateOrderRespVO createOrderRespVO = new CreateOrderRespVO();
        /**对应渠道对应的商品列表**/
        List<SoItemDTO> soItems = new ArrayList<>();

        // 组织限购规则记录集合
        List<LimitRuleRecordDTO> limitRuleRecordList = new ArrayList<>();
        orderAmount = getOrderAmountAndAddItem(storeId, puId, num, userId, platformId, userName, companyId, cakeProductDetailDTO, user, soItems, limitRuleRecordList, orderAmount);
        createOrderRespVO.setSoItems(soItems);
        createOrderRespVO.setLimitRuleRecordList(limitRuleRecordList);
        createOrderRespVO.setOrderAmount(orderAmount);
        createOrderRespVO.setSource(ThirdConst.Source.CAKE);
        createOrderRespVO.setOrderPayByCash(0D);
        createOrderRespVO.setLimitFuBiPayAmount(BigDecimal.ZERO);
        return JsonResult.success(createOrderRespVO);
    }


    private JsonResult checkCakeProductInfo(String puId, String skuId, CakeProductDetailDTO cakeProductDetailDTO, ReceiverAddressDTO addr,Long  enterpriseId){
        if(Objects.isNull(cakeProductDetailDTO)){
            return JsonResult.fail("无此商品");
        }
        CakeProductDetailProductsDTO detailProductsDTO = cakeProductDetailDTO.getProduct();
        if(Objects.isNull(detailProductsDTO)){
            return JsonResult.fail("此商品已下架");
        }
        if(!Objects.equals( detailProductsDTO.getStatus(),"1")){
            return JsonResult.fail(detailProductsDTO.getTitle()+"此商品已下架");
        }

        CakeProductDetailDTO lastCakeProductDetailDTO = productFacade.getCakeProduct(detailProductsDTO.getId(),skuId,CakeAddressUtil.getCityName(addr), null,enterpriseId);

        if(Objects.isNull(lastCakeProductDetailDTO)){
            return JsonResult.fail(detailProductsDTO.getTitle()+"对应的城市不支持售卖");
        }
        CakeProductDetailProductsDTO lastDetailProductsDTO = lastCakeProductDetailDTO.getProduct();
        if(Objects.isNull(lastDetailProductsDTO) || !Objects.equals( lastDetailProductsDTO.getStatus(),"1")){
            return JsonResult.fail(detailProductsDTO.getTitle()+"选择的收货城市该商品已下架");
        }
        CakeProductDetailSpecsDTO specsDTO = productFacade.getCakeProductSkuInfo(lastCakeProductDetailDTO,skuId);
        if(Objects.isNull(specsDTO)){
            return JsonResult.fail(detailProductsDTO.getTitle()+"无该规格商品");
        }
        if(!Objects.equals(specsDTO.getCan_buy(),"1")){
            return JsonResult.fail(detailProductsDTO.getTitle()+"该规格商品已不能购买");
        }
        if(com.egeo.utils.StringUtils.isNotEmpty(specsDTO.getStock()) && !Objects.equals(specsDTO.getStock(),"-9999999") && Integer.valueOf(specsDTO.getStock()) <=0){
            return JsonResult.fail(detailProductsDTO.getTitle()+"商品已售罄");
        }

        return null;
    }


    private double getOrderAmountAndAddItem(Long storeId, Long puId, Integer num, Long userId, Long platformId, String userName, Long companyId, CakeProductDetailDTO cakeProductDetailDTO, UserDTO user, List<SoItemDTO> soItems, List<LimitRuleRecordDTO> limitRuleRecordList, double orderAmount) {
        CakeProductDetailProductsDTO detailProductsDTO = cakeProductDetailDTO.getProduct();
        CakeProductDetailSpecsDTO specsDTO = productFacade.getCakeProductSkuInfo(cakeProductDetailDTO,String.valueOf(puId));

        //校验结束
        SoItemDTO item = new SoItemDTO();
        item.setCartType(1);
        item.setPlatformId(platformId);
        item.setPrice(new BigDecimal(specsDTO.getPrice()).setScale(2, RoundingMode.HALF_UP));
        item.setPuCount(num);
        item.setPuId(puId);
        // 一期暂时认为购物车商品不存在unit属性
        item.setUnitExist(0);
        item.setUserId(userId);
        item.setPuPicUrl(detailProductsDTO.getImage_path());
        item.setPuName(detailProductsDTO.getTitle());
        item.setMerchantId(ThirdConst.Merchant.CAKE);
        item.setSource(ThirdConst.Source.CAKE);
        item.setExternalSkuId(puId +"");
        item.setTaxRate(new BigDecimal(cakeUtil.getCakeTaxRate()).setScale(2, RoundingMode.HALF_UP));
        item.setSnapshot(JSON.toJSONString(cakeProductDetailDTO));
        item.setExternalProductId(detailProductsDTO.getId());
        soItems.add(item);
        orderAmount += num * item.getPrice().doubleValue();


        // 限购规则记录
        LimitRuleRecordDTO lrro = new LimitRuleRecordDTO();
        lrro.setBuySum(Long.valueOf(num));
        lrro.setBuyMoneySum(item.getPrice().setScale(2, RoundingMode.HALF_UP));
        lrro.setProductUnitId(puId);
        lrro.setProductUnitSerialNumber("cake"+ puId);
        lrro.setStandardUnitId(puId);
        lrro.setCreateUserid(userId);
        lrro.setCreateUsername(userName);
        lrro.setCreateUserMobile(user.getMobile());
        lrro.setCompanyId(companyId);
        lrro.setStoreId(storeId);
        limitRuleRecordList.add(lrro);
        return orderAmount;
    }
}
