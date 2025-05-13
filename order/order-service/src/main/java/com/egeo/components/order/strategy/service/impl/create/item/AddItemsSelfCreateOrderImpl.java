package com.egeo.components.order.strategy.service.impl.create.item;

import com.alibaba.fastjson.JSON;
import com.egeo.common.RechargePhoneErrorCode;
import com.egeo.components.order.common.ProductChannelCodeEnum;
import com.egeo.components.order.dto.LimitRuleRecordDTO;
import com.egeo.components.order.dto.SoItemDTO;
import com.egeo.components.order.enums.ThirdConst;
import com.egeo.components.order.facade.ProductFacade;
import com.egeo.components.order.facade.SoFacade;
import com.egeo.components.order.facade.StoreFacade;
import com.egeo.components.order.strategy.vo.CreateOrderReqVO;
import com.egeo.components.order.strategy.vo.CreateOrderRespVO;
import com.egeo.components.product.dto.*;
import com.egeo.components.stock.dto.StorePuWarehouseStockDTO;
import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.thirdparty.RechargePhoneUtil;
import com.egeo.web.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/12/5 14:34
 * @Version V1.0
 **/
@Service("addItemsSelfCreateOrderImpl")
public class AddItemsSelfCreateOrderImpl extends AddItemsCommonBase {
    public Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    @Resource(name = "addItemJdCreateOrderImpl")
    private  AddItemJdCreateOrderImpl addItemJdCreateOrder;

    @Resource(name = "productFacade")
    private ProductFacade productFacade;

    @Resource(name = "soFacade")
    private SoFacade soFacade;

    @Autowired
    private RechargePhoneUtil rechargePhoneUtil;

    @Resource(name = "storeFacade")
    private StoreFacade storeFacade;


    @Override
    public String getChannelCode() {
        return ProductChannelCodeEnum.SELF.getCode();
    }

    @Override
    public JsonResult<CreateOrderRespVO> getProduct(CreateOrderReqVO createOrderReqVO) {
        CreateOrderRespVO createOrderRespVO = new CreateOrderRespVO();
        CommodityProductUnitDTO puType0 = getCommodityProductUnitDTO(createOrderReqVO);
        if(puType0 !=null){
            createOrderRespVO.setSourceProduct(1);
            createOrderRespVO.setProductObject(puType0);
        }
        if(puType0 == null){
            return addItemJdCreateOrder.getProduct(createOrderReqVO);
        }
        return JsonResult.success(createOrderRespVO);
    }

    private CommodityProductUnitDTO getCommodityProductUnitDTO(CreateOrderReqVO createOrderReqVO){
        CommodityProductUnitDTO puType0 = productFacade.queryPuByIdAndSupplierPrice(createOrderReqVO.getPuId());
        return puType0;
    }

    @Override
    public JsonResult<CreateOrderRespVO> addItemsAndLimitRules(CreateOrderReqVO createOrderReqVO) {
        CommodityProductUnitDTO puType0 = null;
            if(createOrderReqVO.getType().intValue() ==0){
                puType0 = (CommodityProductUnitDTO)createOrderReqVO.getProductObject();
            }else {
                puType0 = getCommodityProductUnitDTO(createOrderReqVO);
            }
            if (puType0 == null){
                return JsonResult.fail("商品不存在");
            }
            if (puType0.getIsVendible() == 0) {
                // pu不可销售
                return JsonResult.fail("无该规格商品");
            }
            // 商品上架校验
            if (puType0.getStatus().intValue() != 3)
                return JsonResult.fail("商品已下架");
            //**************需要新增京东逻辑
            Long puId = createOrderReqVO.getPuId();


            CommodityProductUnitDTO commodityProductUnitDTO = productFacade.queryPuById(puId);
            Long skuId = commodityProductUnitDTO.getSkuId();
            Long platformId = createOrderReqVO.getPlatformId();
            Long userId = createOrderReqVO.getUserId();
            Long storeId = createOrderReqVO.getStoreId();
            Long commodityTemplateId = createOrderReqVO.getCommodityTemplateId();
            CompanyDTO companyDTO = createOrderReqVO.getCompanyDTO();
            String phone = createOrderReqVO.getPhone();
            Integer num = createOrderReqVO.getNum();
            String userName =createOrderReqVO.getUserName();
            UserDTO user = createOrderReqVO.getUser();
            boolean unitFlag = false;
            BigDecimal limitFuBiPayAmount= BigDecimal.ZERO;
            Double orderAmount = 0D;
            Double orderPayByCash = 0D;
            /**对应渠道对应的商品列表**/
            List<SoItemDTO> soItems = new ArrayList<>();
            // 组织限购规则记录集合
            List<LimitRuleRecordDTO> limitRuleRecordList = new ArrayList<>();
            MembershipDTO membershipDTO  = soFacade.findMembershipBySkuId(skuId,platformId);
            if (membershipDTO != null) {
                //是会籍商品购买
                //校验用户是否拥有该权限
                List<MembershipUserDTO> membershipUserDTOS = soFacade.findMembershipUserByUserId(userId);
                if (membershipUserDTOS != null) {
                    for (MembershipUserDTO dto : membershipUserDTOS) {
                        if (membershipDTO.getId() == dto.getMembershipId()) {
                            return JsonResult.fail("您已拥有该会籍无需继续购买,如已过期,请续费");
                        }
                    }
                }
            }


            //门店校验
            StandardUnitDTO standardUnit = productFacade.findStandardUnit(puType0.getStandardUnitId());

            if(storeId==1L||storeId==2L){
                if(!standardUnit.getStoreId().equals(storeId)){
                    return JsonResult.fail("您提交的商品中有商品已被管理员移除");
                }
            }else{
                List<StandardUnitStoreDTO> standUnitStore = productFacade.findStandUnitStore(puType0.getStandardUnitId(), storeId);
                if(EmptyUtil.isEmpty(standUnitStore)){
                    return JsonResult.fail("您提交的商品中有商品已被管理员移除");
                }
            }


            // 判断是否是话费充值,是,调用第三方接口校验手机号与面值是否被支持
            if (commodityTemplateId != null && (commodityTemplateId.equals(4L)||commodityTemplateId.equals(9L))) {
                int errorCode = -1;
                try {
                    if(companyDTO.getCompanyType()==0){
                        errorCode = rechargePhoneUtil.telCheck(phone, puType0.getSalePrice().intValue());
                    }else if(companyDTO.getCompanyType()==1){
                        errorCode = rechargePhoneUtil.telCheck(phone, puType0.getDemoSalePrice().intValue());

                    }else if(companyDTO.getCompanyType()==2){
                        errorCode = rechargePhoneUtil.telCheck(phone, puType0.getCompetingSalePrice().intValue());

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (errorCode != RechargePhoneErrorCode.REQUEST_SUCCESS.getCode()) {
                    logger.error("话费充值校验失败:" + errorCode);
                    return JsonResult.fail("请输入正确的手机号码");
                }
            }

            String puImg = puType0.getPuPicUrl();
            if (EmptyUtil.isBlank(puImg)) {
                puImg = productFacade.queryPuNullImgUrl(puType0.getSkuId());
            }
            // 商品库存校验
            Long stock = productFacade.queryStockByPUId(puId);
            if (stock == null || stock.intValue() < num) {
                logger.info("pu商品库存不足,puid:" + puId + "库存：" + stock);
                return JsonResult.fail("pu商品库存不足");
            }

            // 判断是否有unit库存
            unitFlag = productFacade.queryIsUnit(puId);
            if (unitFlag) {
                // unit库存校验
                Long unitStock = productFacade.queryUnitStockBySkuId(puType0.getSkuId());
                if (unitStock == null || unitStock.intValue() < num) {
                    logger.info("sku商品库存不足,skuId" + puType0.getSkuId() + "库存为：" + unitStock);
                    return JsonResult.fail("sku商品库存不足");
                }

            }
            if(storeId!=1L&&storeId!=2L){
                StoreProductUnitDTO storeProductUnitDTO=storeFacade.findStorePuId(storeId,puId);
                StorePuWarehouseStockDTO storeStock =soFacade.findStoreStock(storeProductUnitDTO.getId(),storeProductUnitDTO.getStoreId());
                Long storeStockNum = storeStock.getRealStockNum() - storeStock.getRealFrozenStockNum();
                if(storeStockNum<num){
                    logger.info("sku商品门店库存不足,skuId" + puType0.getSkuId() + "库存为：" + storeStockNum);
                    return JsonResult.fail("库存不足");
                }
            }


            double price=0;
            if(companyDTO.getCompanyType()==0){
                price = puType0.getSalePrice().doubleValue();
            }else if(companyDTO.getCompanyType()==1){
                price = puType0.getDemoSalePrice().doubleValue();
            }else if(companyDTO.getCompanyType()==2){
                price = puType0.getCompetingSalePrice().doubleValue();
            }

            //仅积分支付
            if(standardUnit.getBuyType()==3){
                limitFuBiPayAmount = BigDecimal.valueOf(price * num);
            }
            SoItemDTO item = new SoItemDTO();
            item.setCartType(1);
            item.setPlatformId(platformId);
            item.setPrice(new BigDecimal(price+""));
            item.setPuCount(num);
            item.setPuId(puId);

            item.setSnapshot(JSON.toJSONString(puType0));
            item.setUnitExist(unitFlag ? 1 : 0);
            item.setUserId(userId);
            item.setSupplierId(standardUnit.getSupplierId());
            item.setSkuId(puType0.getSkuId());
            item.setPuName(puType0.getName());
            item.setPuPicUrl(puImg);
            item.setMerchantId(puType0.getMerchantId());
            item.setExternalSkuId(puType0.getExternalSkuId());
            item.setTaxRate(puType0.getTaxRate());
            soItems.add(item);
            orderAmount += num * price;
            if(standardUnit.getBuyType()==2){
                //仅现金支付商品
                orderPayByCash+=num * price;
            }

        // 限购规则记录
        LimitRuleRecordDTO lrro = new LimitRuleRecordDTO();
        lrro.setBuySum(Long.valueOf(num));
        lrro.setBuyMoneySum(BigDecimal.valueOf(price));
        lrro.setProductUnitId(puId);
        lrro.setProductUnitSerialNumber(puType0.getProductUnitSerialNumber());
        lrro.setStandardUnitId(puType0.getStandardUnitId());
        lrro.setCreateUserid(userId);
        lrro.setCreateUsername(userName);
        lrro.setCreateUserMobile(user.getMobile());
        limitRuleRecordList.add(lrro);

        CreateOrderRespVO createOrderRespVO = new CreateOrderRespVO();
        createOrderRespVO.setSoItems(soItems);
        createOrderRespVO.setLimitRuleRecordList(limitRuleRecordList);
        createOrderRespVO.setOrderAmount(orderAmount);
        createOrderRespVO.setSource(null);
        createOrderRespVO.setOrderPayByCash(orderPayByCash);
        createOrderRespVO.setLimitFuBiPayAmount(limitFuBiPayAmount);
        return JsonResult.success(createOrderRespVO);
    }
}
