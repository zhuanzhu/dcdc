package com.egeo.components.order.business.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.egeo.common.RechargePhoneErrorCode;
import com.egeo.components.config.client.CompanyConfigClient;
import com.egeo.components.config.dto.CompanyConfigDTO;
import com.egeo.components.order.business.CreateOrderManage;
import com.egeo.components.order.common.DateUtils;
import com.egeo.components.order.dto.*;
import com.egeo.components.order.facade.*;
import com.egeo.components.order.facade.utils.UserMembershipCheckUtils;
import com.egeo.components.order.vo.CreateOrderRequestExtendsVO;
import com.egeo.components.order.vo.jd.*;
import com.egeo.components.product.dto.*;
import com.egeo.components.product.dto.channel.ChannelProductBatchDTO;
import com.egeo.components.product.dto.channel.ChannelProductDTO;
import com.egeo.components.product.vo.ChannelProductDetailVO;
import com.egeo.components.stock.dto.StorePuWarehouseStockDTO;
import com.egeo.components.user.client.CompanyClient;
import com.egeo.components.user.client.UserClient;
import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.components.utils.JdUtils2;
import com.egeo.core.Constant.BusinessExceptionConstant;
import com.egeo.exception.BusinessException;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.SequenceUtil;
import com.egeo.utils.Upload;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.delivery.JdUtils;
import com.egeo.utils.log.XLogger;
import com.egeo.utils.str.StringUtils;
import com.egeo.utils.thirdparty.RechargePhoneUtil;
import com.egeo.web.JsonResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
@Service("createOrderManage")
public class CreateOrderManageImpl implements CreateOrderManage {

    public XLogger logger = XLogger.getLogger(this.getClass().getName());

    @Resource(name = "soFacade")
    private SoFacade soFacade;
    @Autowired
    private UserClient userReadService;
    @Resource(name = "soInvoiceFacade")
    private SoInvoiceFacade soInvoiceFacade;

    @Resource(name = "soCustomerServiceFacade")
    private SoCustomerServiceFacade soCustomerServiceFacade;

    @Resource(name = "soItemFacade")
    private SoItemFacade soItemFacade;

    @Resource(name = "soPackageFacade")
    private SoPackageFacade soPackageFacade;

    @Resource(name = "cartItemFacade")
    private CartItemFacade cartItemFacade;

    @Resource(name = "receiverAddressFacade")
    private ReceiverAddressFacade receiverAddressFacade;

    @Resource(name = "accountFacade")
    private AccountFacade accountFacade;

    @Resource(name = "productFacade")
    private ProductFacade productFacade;

    @Resource(name = "userFacade")
    private UserFacade userFacade;

    @Resource(name = "provCityAreaFacade")
    private ProvCityAreaFacade provCityAreaFacade;

    @Resource(name = "soFlowFacade")
    private SoFlowFacade soFlowFacade;

    @Resource(name = "promotionFacade")
    private PromotionFacade promotionFacade;

    @Resource(name = "soThirdpartyFacade")
    private SoThirdpartyFacade soThirdpartyFacade;

    @Resource(name = "productUnitFacade")
    private ProductUnitFacade productUnitFacade;

    @Resource(name = "commodityProductUnitFacade")
    private CommodityProductUnitFacade commodityProductUnitFacade;
    @Resource(name = "storeFacade")
    private StoreFacade storeFacade;

    @Resource(name = "cartFacade")
    private CartFacade cartFacade;
    @Resource(name = "soDeliveryItemFacade")
    private SoDeliveryItemFacade soDeliveryItemFacade;

    @Autowired
    CompanyClient companyClient;
    @Resource(name = "merchantFacade")
    private MerchantFacade merchantFacade;
    @Autowired
    private JedisUtil jedisUtil;
    private String PU_LOCK_VALUE = "pu_lock";

    @Autowired
    private JdUtils jdUtils;
    @Autowired
    private RechargePhoneUtil rechargePhoneUtil;
    @Autowired
    private Upload uploadService;
    @Autowired
    private CompanyConfigClient companyConfig;

    @Autowired
    private JdUtils2 jdUtils2;

    private JSONObject toJSONObject(String src){
        if(EmptyUtil.isNotEmpty(src)) {
            return JSONObject.parseObject(src);
        }
        return null;
    }

    private void checkEmptyException(Object obj,String message){
        if(EmptyUtil.isEmpty(obj)){
            throw new BusinessException(message);
        }
    }

    @Override
    public JsonResult<Map<String, Object>> createOrderNew(CreateOrderRequestExtendsVO vo, HttpServletRequest req) {
        JSONObject remarkObj = toJSONObject(vo.getRemark());
        JSONObject deliveryPriceObj =  toJSONObject(vo.getDeliveryPrice());

        CompanyDTO companyDTO= soFacade.findCompanyById(vo.getCompanyId());
        checkEmptyException(companyDTO,"所属公司不存在");
        checkEmptyException(vo.getType(),"未知下单类型");
        List<Integer> sources = new ArrayList<>();
        sources.add(3);
        sources.add(4);
        sources.add(5);
        Long interfaceId = 0L;

        if (vo.getUseFubi() == null){
            vo.setUseFubi(0);
        }


        //判断商品是不是会籍商品

        MembershipDTO membershipDTO = null;



        List<Long> cartItemIds_ = null;
        Integer saleWay = 1;
        boolean isJdType0 = false;
        JdProductDTO jdProductDTOType0 = null;
        CommodityProductUnitDTO puType0 = null;
        CakeProductDetailDTO cakeProductDetailDTO = null;
        ChannelProductDetailVO channelProductDetailVO = null;
        Integer sourceProduct=0;
        if(vo.getType().intValue()==0) {
            // 商品存在校验(商品和用户的权限已经校验)

            //自营
            if(Objects.isNull(vo.getSource()) || !sources.contains(vo.getSource())){
                puType0 = productFacade.queryPuById(vo.getPuId());
                sourceProduct =1;
                checkEmptyException(puType0,"商品不存在");
            }
            //京东
            if(Objects.nonNull(vo.getSource()) && vo.getSource() == 3){
                jdProductDTOType0 = productFacade.checkJdProductStatus(vo.getPuId()+"");
                isJdType0 = true;
                sourceProduct = 3;
                checkEmptyException(jdProductDTOType0,"京东商品不存在");
            }
            //蛋糕叔叔
            if(Objects.nonNull(vo.getSource()) && vo.getSource() == 4){
                sourceProduct=4;
                cakeProductDetailDTO =productFacade.getCakeProduct(vo.getChannelProductId(),vo.getPuId()+"",null,null);
                checkEmptyException(cakeProductDetailDTO,"蛋糕叔叔商品不存在");
            }
            if(Objects.nonNull(vo.getSource()) && vo.getSource() == 5){
                sourceProduct=5;
                channelProductDetailVO  = productFacade.findWorldProduct(vo.getChannelProductId(),vo.getPuId()+"");
                checkEmptyException(cakeProductDetailDTO,"海外商品不存在");
            }

        }


        //校验会籍预售权限/门店
        if(sourceProduct !=3 && sourceProduct !=4) {
            if (EmptyUtil.isNotEmpty(vo.getPuId())) {

                StandardUnitDTO suByPuId = getSUByPuId(vo.getPuId());
                Map<Integer, String> integerStringMap = UserMembershipCheckUtils.checkUserMembershipAuthority(vo.getUserId(), suByPuId.getId(), suByPuId.getSaleWay(), vo.getPlatformId());
                Set<Integer> integers = integerStringMap.keySet();
                for (Integer i : integers) {
                    if (i == 0) {
                        return JsonResult.fail(integerStringMap.get(i));
                    }

                }

                StandardProductUnitAttNameDTO standardProductUnitAttNameDTO=soFacade.findStandardProductUnitAttNameById(suByPuId.getStandardProductUnitId());
                List<StandardProductUnitAttValueDTO> standardProductUnitAttValueDTOS=soFacade.findStandardProductUnitValue(standardProductUnitAttNameDTO.getId());
                if(vo.getType()==0&&suByPuId.getSaleWay()==1L&&EmptyUtil.isNotEmpty(standardProductUnitAttValueDTOS)){
                    //会籍购买必须满足第三方对接参数为内部会籍
                    saleWay = 5;
                }else{
                    saleWay = suByPuId.getSaleWay();
                }
                //补差价订单
                if(EmptyUtil.isNotEmpty(vo.getOrderType())&&vo.getType().equals(8)){
                    saleWay=8;
                }
            } else {
                //如果是预售生成的订单编号类型为P,这里设置一下订单类型
                cartItemIds_ = JSONArray.parseArray(vo.getCartItemIds(), Long.class);
                if (EmptyUtil.isEmpty(cartItemIds_)) {
                    return JsonResult.fail("购物车项参数为空");
                }
                for (Long l : cartItemIds_) {
                    CartItemDTO cartItemById = cartItemFacade.findCartItemById(l);
                    CartDTO dto = new CartDTO();
                    dto.setId(cartItemById.getCartId());
                    CartDTO cartById = cartFacade.findCartById(dto);

                    saleWay = cartById.getSaleWay();


                }
            }
        }



        // 生成订单编号, 订单类型：1正常销售、2团购、3兑换券 (默认为1正常销售) 4:普通预售 5:会籍购买 6.会籍预售 8.以旧换新加价(补差价)
        if (vo.getCouponType() != null && vo.getCouponUnitId() != null && vo.getCouponType() == 1) {
            // 兑换卷兑换
            saleWay = 3;
        }



        //生成订单code
        String orderCode = SequenceUtil.genOrderCode(vo.getF(), saleWay, vo.getUserId(), vo.getPlatformId());
        // 订单编号校验
        SoDTO so_ = soFacade.querySoByOrderCode(orderCode);
        if (so_ != null) {
            return JsonResult.fail("订单编号重复,请重试");
        }
        // 发票校验
        SoInvoiceDTO inv = null;
        if (EmptyUtil.isEmpty(vo.getInvoiceId()) || vo.getInvoiceId() == 0) {
            vo.setInvoiceId(null);
        }
        if (vo.getInvoiceId() != null) {
            inv = soInvoiceFacade.findSoInvoiceById(vo.getInvoiceId());
            if (inv == null) {
                return JsonResult.fail("发票信息丢失");
            }
        }

        // 查询用户信息
        UserDTO user = userFacade.queryUserById(vo.getUserId());
        if (user == null) {
            return JsonResult.fail("当前用户不存在");
        }
        // 组织订单项
        List<SoItemDTO> soItems = new ArrayList<>();
        // 组织限购规则记录集合
        List<LimitRuleRecordDTO> limitRuleRecordList = new ArrayList<>();

        double orderAmount = 0d;
        double orderPayByCash = 0d;
        boolean unitFlag = false;

        // 通过puid查询sku,然后查到spu信息
        Long commodityTemplateId = 2L;
        if (vo.getPuId() != null && sourceProduct ==1) {
            commodityTemplateId = productFacade.queryCommodityTemplateIdByPuId(vo.getPuId());
        }

        if (EmptyUtil.isNotEmpty(vo.getReceiveAddressId()) && vo.getReceiveAddressId().equals(Long.valueOf(0))) {
            vo.setReceiveAddressId(null);
        }
        ReceiverAddressDTO addr = null;
        StoreDTO storeById = storeFacade.findStoreById(vo.getStoreId());
        //如果是直接下单,判断商品模板,如果是购物车下单,则一定是实物商品
        if (((commodityTemplateId.equals(2L)||commodityTemplateId.equals(7L))||vo.getType()==1)&&storeById.getIsDetail() != 1) {
            if (vo.getReceiveAddressId() == null) {
                // 收货地址校验:实体商品
                return JsonResult.fail("请填写收货人信息");
            }
            addr = receiverAddressFacade.findReceiverAddressById(vo.getReceiveAddressId());
            if (addr == null) {
                return JsonResult.fail("收货地址不存在");
            }

        } else {
            // 电子卡券类/充值类商品没有收货地址
            vo.setReceiveAddressId(null);
        }

        BigDecimal limitFuBiPayAmount = BigDecimal.ZERO;
        if (vo.getType().intValue() == 0) {
            // 直接购买
            // 参数校验
            if (vo.getPuId() == null || vo.getNum() == null) {
                return JsonResult.fail("参数为空");
            }

            /*对pu进行加锁(同一个pu不同人进行购买时有锁限制)
             */
            boolean lock = true;
            try {
                lock=jedisUtil.lockWithParam(JedisUtil.PU_LOCK_KEY_PRE+vo.getPuId(),PU_LOCK_VALUE,JedisUtil.PU_LOCK_EXPIRE_TIME);
            } catch (InterruptedException e) {
                logger.info("当前获取pu的redis锁异常,puId="+vo.getPuId());
                jedisUtil.delLock(JedisUtil.PU_LOCK_KEY_PRE+vo.getPuId());
                e.printStackTrace();
            }
            if(!lock){
                logger.info("创建订单获取pu锁失败");
                return JsonResult.fail("当前网络繁忙,请稍后重试");
            }
            vo.getPuIdList().add(vo.getPuId());


            // 兑换卷购买su数量必须为1
            if (vo.getCouponType() != null && vo.getCouponUnitId() != null && vo.getCouponType() == 1 && vo.getNum() != 1) {
                return JsonResult.fail("兑换数量必须是1");
            }

            // 判断是否是话费充值
            if (commodityTemplateId != null && (commodityTemplateId.equals(4L)||commodityTemplateId.equals(9L))) {
                if (EmptyUtil.isEmpty(vo.getPhone())) {
                    return JsonResult.fail("手机号码不能为空");
                }
                if (!StringUtils.validMobile(vo.getPhone())) {
                    return JsonResult.fail("请输入正确的手机号码");
                }
                // 默认商品数量为1
                vo.setNum(1);
            }

            // 商品存在校验(商品和用户的权限已经校验)


            if(sourceProduct ==3) {
                if(jdProductDTOType0.getState()==0){
                    return JsonResult.fail("抱歉，该商品已下架");
                }
               // String token = jdUtils.getAccessToken(jedisUtil);
                String token = jdUtils2.getAccessToken(jedisUtil);
                //2.校验京东是否可售
                String skuSellStatusFromJd = jdUtils.getSkuSellStatusFromJd(token, vo.getPuId()+"", "");
                JdResponse jdSellResponse = JSON.parseObject(skuSellStatusFromJd, JdResponse.class);
                if(jdSellResponse.isSuccess()&&jdSellResponse.getResultCode().equals("0000")){
                    String json = jdSellResponse.getResult();
                    List<JdSkuSellStatus> jdSkuStatus = JSON.parseArray(json, JdSkuSellStatus.class);
                    if(jdSkuStatus.get(0).getSaleState()==0){
                        return JsonResult.fail("抱歉，该商品已下架");
                    }
                }else{
                    return JsonResult.fail("查询上下架失败");
                }
                String addrStr = addr.getGoodReceiverProvince()+addr.getGoodReceiverCity()+addr.getGoodReceiverCounty()+addr.getGoodReceiverArea();
                ParseAddressJson parseAddressJson = getDeliveryPriceFromJd(token,addrStr);
                //4.校验是否在可售区域
                if(EmptyUtil.isNotEmpty(addr)){
                    if(EmptyUtil.isNotEmpty(parseAddressJson)){
                        String skuAddressSellStatusFromJd = jdUtils.getSkuAddressSellStatusFromJd(token, vo.getPuId()+"", parseAddressJson.getProvinceId(), parseAddressJson.getCityId(), parseAddressJson.getCountyId(), parseAddressJson.getTownId());
                        JdResponse jdResponse1 = JSON.parseObject(skuAddressSellStatusFromJd, JdResponse.class);
                        if(jdResponse1.isSuccess()&&jdResponse1.getResultCode().equals("0000")){
                            String json = jdResponse1.getResult();
                            List<JdSkuAddressSellStatus> jdSkuStatus = JSON.parseArray(json, JdSkuAddressSellStatus.class);
                            if(jdSkuStatus.get(0).getIsAreaRestrict().equals("true")){
                                return JsonResult.fail("抱歉，商品在收货地址区域内不可售，请重新选择收货地址");
                            }
                        }else{
                            return JsonResult.fail("查询上下架失败");
                        }


                        //3.校验库存状态
                        JdProductStockSearch stockSearch = new JdProductStockSearch();
                        stockSearch.setSkuId(vo.getPuId());
                        stockSearch.setNum(Long.valueOf(vo.getNum()));
                        List<JdProductStockSearch> searchList = new ArrayList<>();
                        searchList.add(stockSearch);
                        Integer provinceId = parseAddressJson.getProvinceId();
                        Integer cityId = parseAddressJson.getCityId();
                        Integer countyId = parseAddressJson.getCountyId();
                        String arae = provinceId + "_" + cityId + "_" + countyId;
                        String jdProductStockString= jdUtils.getJdProductStock(token, JSONObject.toJSONString(searchList), arae);
                        List<JSONObject> jdProductStockArr= JSONObject.parseArray(jdProductStockString, JSONObject.class);
                        JSONObject jdProductStock=JSONObject.parseObject(jdProductStockArr.get(0).toString());
                        if(EmptyUtil.isEmpty(jdProductStock)){
                            return JsonResult.fail("当前商品为商品库存存在问题");
                        }else{
                            String stockStateId = jdProductStock.getString("stockStateId");
                            if(stockStateId.equals("33")||stockStateId.equals("39")||stockStateId.equals("40")){
                                logger.info("有货");
                            }else{
                                return JsonResult.fail("抱歉，该商品无货");
                            }
                        }

                    }

                }
                //校验结束
                SoItemDTO item = new SoItemDTO();
                item.setCartType(1);
                item.setPlatformId(vo.getPlatformId());
                item.setPrice(jdProductDTOType0.getPrice());
                item.setPuCount(vo.getNum());
                item.setPuId(vo.getPuId());
                // 一期暂时认为购物车商品不存在unit属性
                item.setUnitExist(0);
                item.setUserId(vo.getUserId());
                item.setPuPicUrl(jdProductDTOType0.primaryImg());
                item.setPuName(jdProductDTOType0.getName());
                item.setMerchantId(6L);
                item.setSource(3);
                item.setExternalSkuId(vo.getPuId()+"");
                item.setSnapshot(JSON.toJSONString(jdProductDTOType0));
                soItems.add(item);
                orderAmount += vo.getNum() * jdProductDTOType0.getPrice().doubleValue();


                // 限购规则记录
                LimitRuleRecordDTO lrro = new LimitRuleRecordDTO();
                lrro.setBuySum(Long.valueOf(vo.getNum()));
                lrro.setBuyMoneySum(jdProductDTOType0.getPrice());
                lrro.setProductUnitId(vo.getPuId());
                lrro.setProductUnitSerialNumber("jd-"+vo.getPuId());
                lrro.setStandardUnitId(vo.getPuId());
                lrro.setCreateUserid(vo.getUserId());
                lrro.setCreateUsername(vo.getUserName());
                lrro.setCreateUserMobile(user.getMobile());
                lrro.setCompanyId(vo.getCompanyId());
                lrro.setStoreId(vo.getStoreId());
                limitRuleRecordList.add(lrro);
            }if(sourceProduct !=null &&sourceProduct ==4){
                JsonResult checkResult = checkCakeProductInfo(vo.getChannelProductId(),vo.getPuId()+"",cakeProductDetailDTO,addr);
                if(Objects.nonNull(checkResult)){
                    return checkResult;
                }
                orderAmount = getOrderAmountAndAddItem(vo.getStoreId(), vo.getPuId(), vo.getNum(), vo.getUserId(), vo.getPlatformId(), vo.getUserName(), vo.getCompanyId(), cakeProductDetailDTO, user, soItems, limitRuleRecordList, orderAmount);
            }else if(sourceProduct !=null && sourceProduct ==5){
                JsonResult checkResult = checkWorldProductInfo(vo.getChannelProductId(),vo.getPuId()+"",channelProductDetailVO,addr);
                if(Objects.nonNull(checkResult)){
                    return checkResult;
                }
                orderAmount = getWorldOrderAmountAndAddItem(vo.getStoreId(), vo.getPuId(), vo.getNum(), vo.getUserId(), vo.getPlatformId(), vo.getUserName(), vo.getCompanyId(), channelProductDetailVO, user, soItems, limitRuleRecordList, orderAmount);

            }else {
                if (puType0 == null) {
                    return JsonResult.fail("商品不存在");
                }
                if (puType0.getIsVendible() == 0) {
                    // pu不可销售
                    return JsonResult.fail("无该规格商品");
                }
                // 商品上架校验
                if (puType0.getStatus().intValue() != 3) {
                    return JsonResult.fail("商品已下架");
                }
                //**************需要新增京东逻辑

                CommodityProductUnitDTO commodityProductUnitDTO = productFacade.queryPuById(vo.getPuId());
                Long skuId = commodityProductUnitDTO.getSkuId();
                membershipDTO = soFacade.findMembershipBySkuId(skuId,vo.getPlatformId());
                if (membershipDTO != null) {
                    //是会籍商品购买
                    //校验用户是否拥有该权限
                    List<MembershipUserDTO> membershipUserDTOS = soFacade.findMembershipUserByUserId(vo.getUserId());
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

                if(vo.getStoreId()==1L||vo.getStoreId()==2L){
                    if(!standardUnit.getStoreId().equals(vo.getStoreId())){
                        return JsonResult.fail("您提交的商品中有商品已被管理员移除");
                    }
                }else{
                    List<StandardUnitStoreDTO> standUnitStore = productFacade.findStandUnitStore(puType0.getStandardUnitId(), vo.getStoreId());
                    if(EmptyUtil.isEmpty(standUnitStore)){
                        return JsonResult.fail("您提交的商品中有商品已被管理员移除");
                    }
                }


                // 判断是否是话费充值,是,调用第三方接口校验手机号与面值是否被支持
                if (commodityTemplateId != null && (commodityTemplateId.equals(4L)||commodityTemplateId.equals(9L))) {
                    int errorCode = -1;
                    try {
                        if(companyDTO.getCompanyType()==0){
                            errorCode = rechargePhoneUtil.telCheck(vo.getPhone(), puType0.getSalePrice().intValue());
                        }else if(companyDTO.getCompanyType()==1){
                            errorCode = rechargePhoneUtil.telCheck(vo.getPhone(), puType0.getDemoSalePrice().intValue());

                        }else if(companyDTO.getCompanyType()==2){
                            errorCode = rechargePhoneUtil.telCheck(vo.getPhone(), puType0.getCompetingSalePrice().intValue());

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
                Long stock = productFacade.queryStockByPUId(vo.getPuId());
                if (stock == null || stock.intValue() < vo.getNum()) {
                    logger.info("pu商品库存不足,puid:" + vo.getPuId() + "库存：" + stock);
                    return JsonResult.fail("pu商品库存不足");
                }

                // 判断是否有unit库存
                unitFlag = productFacade.queryIsUnit(vo.getPuId());
                if (unitFlag) {
                    // unit库存校验
                    Long unitStock = productFacade.queryUnitStockBySkuId(puType0.getSkuId());
                    if (unitStock == null || unitStock.intValue() < vo.getNum()) {
                        logger.info("sku商品库存不足,skuId" + puType0.getSkuId() + "库存为：" + unitStock);
                        return JsonResult.fail("sku商品库存不足");
                    }

                }
                if(vo.getStoreId()!=1L&&vo.getStoreId()!=2L){
                    StoreProductUnitDTO storeProductUnitDTO=storeFacade.findStorePuId(vo.getStoreId(),vo.getPuId());
                    StorePuWarehouseStockDTO storeStock =soFacade.findStoreStock(storeProductUnitDTO.getId(),storeProductUnitDTO.getStoreId());
                    Long storeStockNum = storeStock.getRealStockNum() - storeStock.getRealFrozenStockNum();
                    if(storeStockNum<vo.getNum()){
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
                    limitFuBiPayAmount = BigDecimal.valueOf(price * vo.getNum());
                }
                SoItemDTO item = new SoItemDTO();
                item.setCartType(1);
                item.setPlatformId(vo.getPlatformId());
                item.setPrice(new BigDecimal(price+""));
                item.setPuCount(vo.getNum());
                item.setPuId(vo.getPuId());
                item.setUnitExist(unitFlag ? 1 : 0);
                item.setUserId(vo.getUserId());
                item.setSupplierId(standardUnit.getSupplierId());
                item.setSkuId(puType0.getSkuId());
                item.setPuName(puType0.getName());
                item.setPuPicUrl(puImg);
                item.setMerchantId(puType0.getMerchantId());
                item.setExternalSkuId(puType0.getExternalSkuId());
                soItems.add(item);
                orderAmount += vo.getNum() * price;
                if(standardUnit.getBuyType()==2){
                    //仅现金支付商品
                    orderPayByCash+=vo.getNum() * price;
                }

                // 限购规则记录
                LimitRuleRecordDTO lrro = new LimitRuleRecordDTO();
                lrro.setBuySum(Long.valueOf(vo.getNum()));
                lrro.setBuyMoneySum(BigDecimal.valueOf(price));
                lrro.setProductUnitId(vo.getPuId());
                lrro.setProductUnitSerialNumber(puType0.getProductUnitSerialNumber());
                lrro.setStandardUnitId(puType0.getStandardUnitId());
                lrro.setCreateUserid(vo.getUserId());
                lrro.setCreateUsername(vo.getUserName());
                lrro.setCreateUserMobile(user.getMobile());
                limitRuleRecordList.add(lrro);
            }




        } else {
            // 购物车下单
            if (vo.getCouponType() != null && vo.getCouponType() == 1) {
                return JsonResult.fail("不能直接从购物车兑换商品");
            }


            for (Long ciid : cartItemIds_) {
                // 购物车存在校验
                CartItemDTO ci = cartItemFacade.findCartItemById(ciid);
                if (ci == null) {
                    return JsonResult.fail("购物车项不存在");
                }
                // 商品存在校验
                Long puId_ = ci.getProductUnitId();



                /*对pu进行加锁(同一个pu不同人进行购买时有锁限制)
                 */
                boolean lock = true;
                try {
                    lock=jedisUtil.lockWithParam(JedisUtil.PU_LOCK_KEY_PRE+puId_,PU_LOCK_VALUE,JedisUtil.PU_LOCK_EXPIRE_TIME);
                } catch (InterruptedException e) {
                    logger.info("当前获取pu的redis锁异常,puId="+puId_);
                    jedisUtil.delLock(JedisUtil.PU_LOCK_KEY_PRE+puId_);
                    e.printStackTrace();
                }
                if(!lock){
                    logger.info("创建订单获取pu锁失败");
                    return JsonResult.fail("当前网络繁忙,请稍后重试");
                }
                vo.getPuIdList().add(puId_);
                int num_ = ci.getNum().intValue();
                if(ci.isJd()) {
                    JdProductDTO jdProductDTO = productFacade.checkJdProductStatus(puId_+"");
                    if(jdProductDTO.getState()==0){
                        return JsonResult.fail("抱歉，该商品已下架");
                    }
                    //String token = jdUtils.getAccessToken(jedisUtil);
                    String token = jdUtils2.getAccessToken(jedisUtil);
                    //2.校验京东是否可售
                    String skuSellStatusFromJd = jdUtils.getSkuSellStatusFromJd(token, puId_+"", "");
                    JdResponse jdSellResponse = JSON.parseObject(skuSellStatusFromJd, JdResponse.class);
                    if(jdSellResponse.isSuccess()&&jdSellResponse.getResultCode().equals("0000")){
                        String json = jdSellResponse.getResult();
                        List<JdSkuSellStatus> jdSkuStatus = JSON.parseArray(json, JdSkuSellStatus.class);
                        if(jdSkuStatus.get(0).getSaleState()==0){
                            return JsonResult.fail("抱歉，该商品已下架");
                        }
                    }else{
                        return JsonResult.fail("查询上下架失败");
                    }
                    String addrStr = addr.getGoodReceiverProvince()+addr.getGoodReceiverCity()+addr.getGoodReceiverCounty()+addr.getGoodReceiverArea();
                    ParseAddressJson parseAddressJson = getDeliveryPriceFromJd(token,addrStr);
                    //4.校验是否在可售区域
                    if(EmptyUtil.isNotEmpty(addr)){
                        if(EmptyUtil.isNotEmpty(parseAddressJson)){
                            String skuAddressSellStatusFromJd = jdUtils.getSkuAddressSellStatusFromJd(token, puId_+"", parseAddressJson.getProvinceId(), parseAddressJson.getCityId(), parseAddressJson.getCountyId(), parseAddressJson.getTownId());
                            JdResponse jdResponse1 = JSON.parseObject(skuAddressSellStatusFromJd, JdResponse.class);
                            if(jdResponse1.isSuccess()&&jdResponse1.getResultCode().equals("0000")){
                                String json = jdResponse1.getResult();
                                List<JdSkuAddressSellStatus> jdSkuStatus = JSON.parseArray(json, JdSkuAddressSellStatus.class);
                                if(jdSkuStatus.get(0).getIsAreaRestrict().equals("true")){
                                    return JsonResult.fail("抱歉，商品在收货地址区域内不可售，请重新选择收货地址");
                                }
                            }else{
                                return JsonResult.fail("查询上下架失败");
                            }


                            //3.校验库存状态
                            JdProductStockSearch stockSearch = new JdProductStockSearch();
                            stockSearch.setSkuId(puId_);
                            stockSearch.setNum(Long.valueOf(num_));
                            List<JdProductStockSearch> searchList = new ArrayList<>();
                            searchList.add(stockSearch);
                            Integer provinceId = parseAddressJson.getProvinceId();
                            Integer cityId = parseAddressJson.getCityId();
                            Integer countyId = parseAddressJson.getCountyId();
                            String arae = provinceId + "_" + cityId + "_" + countyId;
                            String jdProductStockString= jdUtils.getJdProductStock(token, JSONObject.toJSONString(searchList), arae);
                            List<JSONObject> jdProductStockArr= JSONObject.parseArray(jdProductStockString, JSONObject.class);
                            JSONObject jdProductStock=JSONObject.parseObject(jdProductStockArr.get(0).toString());
                            if(EmptyUtil.isEmpty(jdProductStock)){
                                return JsonResult.fail("当前商品为商品库存存在问题");
                            }else{
                                String stockStateId = jdProductStock.getString("stockStateId");
                                if(stockStateId.equals("33")||stockStateId.equals("39")||stockStateId.equals("40")){
                                    logger.info("有货");
                                }else{
                                    return JsonResult.fail("抱歉，该商品无货");
                                }
                            }

                        }

                    }
                    //校验结束
                    SoItemDTO item = new SoItemDTO();
                    item.setCartType(1);
                    item.setPlatformId(vo.getPlatformId());
                    item.setPrice(jdProductDTO.getPrice());
                    item.setPuCount(num_);
                    item.setPuId(puId_);
                    // 一期暂时认为购物车商品不存在unit属性
                    item.setUnitExist(0);
                    item.setUserId(vo.getUserId());
                    item.setPuPicUrl(jdProductDTO.primaryImg());
                    item.setPuName(jdProductDTO.getName());
                    item.setMerchantId(6L);
                    item.setSource(3);
                    item.setExternalSkuId(puId_+"");
                    item.setSnapshot(JSON.toJSONString(jdProductDTO));
                    soItems.add(item);
                    orderAmount += num_ * jdProductDTO.getPrice().doubleValue();


                    // 限购规则记录
                    LimitRuleRecordDTO lrro = new LimitRuleRecordDTO();
                    lrro.setBuySum(Long.valueOf(num_));
                    lrro.setBuyMoneySum(jdProductDTO.getPrice());
                    lrro.setProductUnitId(puId_);
                    lrro.setProductUnitSerialNumber("jd-"+puId_);
                    lrro.setStandardUnitId(puId_);
                    lrro.setCreateUserid(vo.getUserId());
                    lrro.setCreateUsername(vo.getUserName());
                    lrro.setCreateUserMobile(user.getMobile());
                    lrro.setCompanyId(vo.getCompanyId());
                    lrro.setStoreId(vo.getStoreId());
                    limitRuleRecordList.add(lrro);

                }if(ci.isCake()){
                    //蛋糕叔叔商品
                    String skuId = String.valueOf(vo.getPuId());
                    String productId = ci.getChannelProductId();
                    CakeProductDetailDTO dto = productFacade.getCakeProduct(productId,skuId,null,null);
                    JsonResult checkResult = checkCakeProductInfo(vo.getChannelProductId(),vo.getPuId()+"",dto,addr);
                    if(Objects.nonNull(checkResult)){
                        return checkResult;
                    }
                    CakeProductDetailProductsDTO detailProductsDTO = dto.getProduct();
                    CakeProductDetailSpecsDTO specsDTO = productFacade.getCakeProductSkuInfo(dto,skuId);
                    //校验结束
                    SoItemDTO item = new SoItemDTO();
                    item.setCartType(1);
                    item.setPlatformId(vo.getPlatformId());
                    item.setPrice(new BigDecimal(specsDTO.getPrice()).setScale(2));
                    item.setPuCount(num_);
                    item.setPuId(puId_);
                    // 一期暂时认为购物车商品不存在unit属性
                    item.setUnitExist(0);
                    item.setUserId(vo.getUserId());
                    item.setPuPicUrl(detailProductsDTO.getImage_path());
                    item.setPuName(detailProductsDTO.getTitle());
                    item.setMerchantId(7L);
                    item.setSource(4);
                    item.setExternalSkuId(puId_+"");
                    item.setExternalProductId(productId);
                    item.setSnapshot(JSON.toJSONString(dto));
                    soItems.add(item);
                    orderAmount += num_ * item.getPrice().doubleValue();


                    // 限购规则记录
                    LimitRuleRecordDTO lrro = new LimitRuleRecordDTO();
                    lrro.setBuySum(Long.valueOf(num_));
                    lrro.setBuyMoneySum(item.getPrice());
                    lrro.setProductUnitId(puId_);
                    lrro.setProductUnitSerialNumber("cake-"+puId_);
                    lrro.setStandardUnitId(puId_);
                    lrro.setCreateUserid(vo.getUserId());
                    lrro.setCreateUsername(vo.getUserName());
                    lrro.setCreateUserMobile(user.getMobile());
                    lrro.setCompanyId(vo.getCompanyId());
                    lrro.setStoreId(vo.getStoreId());
                    limitRuleRecordList.add(lrro);
                }else if(ci.isWorld()){
                    String productId =ci.getChannelProductId();
                    ChannelProductDetailVO productDetailVO = productFacade.findWorldProduct(productId,puId_+"");

                    JsonResult checkResult = checkWorldProductInfo(productId,puId_+"",productDetailVO,addr);
                    if(Objects.nonNull(checkResult)){
                        return checkResult;
                    }
                    ChannelProductDTO channelProductDTO = productDetailVO.getChannelProductDTO();
                    List<ChannelProductBatchDTO>  batchDTOList = productDetailVO.getBatchDTOList();
                    ChannelProductBatchDTO batchDTO = productFacade.getCurrBatch(puId_+"",batchDTOList);
                    //校验结束
                    SoItemDTO item = new SoItemDTO();
                    item.setCartType(1);
                    item.setPlatformId(vo.getPlatformId());
                    item.setPrice(batchDTO.getPrice().setScale(2));
                    item.setPuCount(num_);
                    item.setPuId(puId_);
                    // 一期暂时认为购物车商品不存在unit属性
                    item.setUnitExist(0);
                    item.setUserId(vo.getUserId());
                    item.setPuPicUrl(productDetailVO.getProductImg());
                    item.setPuName(channelProductDTO.getTitle()+batchDTO.getSpecName());
                    item.setMerchantId(8L);
                    item.setSource(5);
                    item.setExternalSkuId(batchDTO.getLinkSkuId());
                    item.setSnapshot(JSON.toJSONString(productDetailVO));
                    item.setExternalProductId(ci.getChannelProductId());
                    soItems.add(item);
                    orderAmount += num_ * item.getPrice().doubleValue();


                    // 限购规则记录
                    LimitRuleRecordDTO lrro = new LimitRuleRecordDTO();
                    lrro.setBuySum(Long.valueOf(num_));
                    lrro.setBuyMoneySum(item.getPrice());
                    lrro.setProductUnitId(puId_);
                    lrro.setProductUnitSerialNumber("world-"+puId_);
                    lrro.setStandardUnitId(puId_);
                    lrro.setCreateUserid(vo.getUserId());
                    lrro.setCreateUsername(vo.getUserName());
                    lrro.setCreateUserMobile(user.getMobile());
                    lrro.setCompanyId(vo.getCompanyId());
                    lrro.setStoreId(vo.getStoreId());
                    limitRuleRecordList.add(lrro);
                }else {
                    CommodityProductUnitDTO pu = productFacade.queryPuById(puId_);
                    StandardUnitDTO suByPuId = getSUByPuId(puId_);
                    //商品门店校验
                    //门店校验
                    if(vo.getStoreId()==1L||vo.getStoreId()==2L){
                        if(!suByPuId.getStoreId().equals(vo.getStoreId())){
                            return JsonResult.fail("您提交的商品中有商品已被管理员移除");
                        }
                    }else{
                        List<StandardUnitStoreDTO> standUnitStore = productFacade.findStandUnitStore(pu.getStandardUnitId(), vo.getStoreId());
                        if(EmptyUtil.isEmpty(standUnitStore)){
                            return JsonResult.fail("您提交的商品中有商品已被管理员移除");
                        }
                    }

                    //校验会籍预售的权限
                    Map<Integer, String> integerStringMap = UserMembershipCheckUtils.checkUserMembershipAuthority(vo.getUserId(), suByPuId.getId(), suByPuId.getSaleWay(), vo.getPlatformId());
                    Set<Integer> integers = integerStringMap.keySet();
                    for (Integer i : integers) {
                        if (i == 0) {
                            return JsonResult.fail(integerStringMap.get(i));
                        }
                    }

                    if (pu == null) {
                        return JsonResult.fail("商品不存在");
                    }
                    if (pu.getIsVendible() == 0) {
                        // pu不可销售
                        return JsonResult.fail("无该规格商品");
                    }
                    // 商品上架校验
                    if (pu.getStatus().intValue() != 3) {
                        return JsonResult.fail("商品已下架");
                    }
                    String puImg = pu.getPuPicUrl();
                    if (EmptyUtil.isBlank(puImg)) {
                        puImg = productFacade.queryPuNullImgUrl(pu.getSkuId());
                    }
                    // 商品库存校验
                    Long stock = productFacade.queryStockByPUId(puId_);
                    if (stock.intValue() < num_)
                        return JsonResult.fail("商品库存不足");
                    if(vo.getStoreId()!=1L&&vo.getStoreId()!=2L){
                        StoreProductUnitDTO storeProductUnitDTO=storeFacade.findStorePuId(vo.getStoreId(),puId_);
                        StorePuWarehouseStockDTO storeStock =soFacade.findStoreStock(storeProductUnitDTO.getId(),storeProductUnitDTO.getStoreId());
                        Long storeStockNum = storeStock.getRealStockNum() - storeStock.getRealFrozenStockNum();
                        if(storeStockNum<num_){
                            logger.info("sku商品门店库存不足,skuId" + pu.getSkuId() + "库存为：" + storeStockNum);
                            return JsonResult.fail("库存不足");
                        }
                    }



//                    double price_ = pu.getSalePrice().doubleValue();
                    double price_=0;
                    if(companyDTO.getCompanyType()==0){
                        price_ = pu.getSalePrice().doubleValue();
                    }else if(companyDTO.getCompanyType()==1){
                        price_ = pu.getDemoSalePrice().doubleValue();
                    }else if(companyDTO.getCompanyType()==2){
                        price_ = pu.getCompetingSalePrice().doubleValue();
                    }

                    //仅积分支付
                    if(suByPuId.getBuyType()==3){
                        limitFuBiPayAmount = limitFuBiPayAmount.add(BigDecimal.valueOf(price_ * num_));
                    }
                    SoItemDTO item = new SoItemDTO();
                    item.setCartType(1);
                    item.setPlatformId(vo.getPlatformId());
                    item.setPrice(BigDecimal.valueOf(price_));
                    item.setPuCount(num_);
                    item.setSupplierId(suByPuId.getSupplierId());
                    item.setPuId(puId_);
                    // 一期暂时认为购物车商品不存在unit属性
                    item.setUnitExist(0);
                    item.setUserId(vo.getUserId());
                    item.setPuPicUrl(puImg);
                    item.setPuName(pu.getName());
                    item.setMerchantId(pu.getMerchantId());
                    item.setExternalSkuId(pu.getExternalSkuId());

                    item.setSnapshot(JSON.toJSONString(pu));
                    soItems.add(item);
                    orderAmount += num_ * price_;
                    if(suByPuId.getBuyType()==2){
                        //仅现金支付商品
                        orderPayByCash+=num_ * price_;
                    }

                    // 限购规则记录
                    LimitRuleRecordDTO lrro = new LimitRuleRecordDTO();
                    lrro.setBuySum(Long.valueOf(num_));
                    lrro.setBuyMoneySum(BigDecimal.valueOf(price_));
                    lrro.setProductUnitId(pu.getId());
                    lrro.setProductUnitSerialNumber(pu.getProductUnitSerialNumber());
                    lrro.setStandardUnitId(pu.getStandardUnitId());
                    lrro.setCreateUserid(vo.getUserId());
                    lrro.setCreateUsername(vo.getUserName());
                    lrro.setCreateUserMobile(user.getMobile());
                    lrro.setCompanyId(vo.getCompanyId());
                    lrro.setStoreId(vo.getStoreId());
                    limitRuleRecordList.add(lrro);
                }


            }
        }
        if(EmptyUtil.isEmpty(limitRuleRecordList)){
            return JsonResult.fail("商品不能为空,限购规则不能为空");
        }
        // 根据pu商品集合及限购规则判断是否能购买、返回值不为空直接返回错误
        String isLimitBuy = productFacade.isLimitBuy(vo.getStoreId(), limitRuleRecordList, vo.getCompanyId(), vo.getPlatformId(), vo.getUserId());
        if (isLimitBuy != null) {
            return JsonResult.fail(isLimitBuy, BusinessExceptionConstant.LIMITBUYEXCEPTIONCODE);
        }


        // 创建订单
        SoDTO sodto = new SoDTO();
        sodto.setUserId(vo.getUserId());
        sodto.setOrderCode(orderCode);
        sodto.setPlatformId(vo.getPlatformId());
        sodto.setProductAmount(BigDecimal.valueOf(orderAmount));
        sodto.setReceiverAddressId(vo.getReceiveAddressId());
        sodto.setDeliveryStatus(0);
        sodto.setInvoiceId(vo.getInvoiceId());
        sodto.setOrderAmountPay(new BigDecimal(0));
        sodto.setOrderChannel(vo.getF());
        sodto.setOrderConfirmStatus(0);
        sodto.setOrderDeleteStatus(0);
        sodto.setDeliveryFee(new BigDecimal(0));
        sodto.setOrderDeliveryFeeInsuranceAmount(new BigDecimal(0));
        sodto.setOrderDeliveryFeeInsuranceType(0);
        sodto.setOrderGivePoints(new BigDecimal(0));
        sodto.setOrderPaidByCard(new BigDecimal(0));
        sodto.setOrderPaidByCash(new BigDecimal(0));
        sodto.setCouponDiscount(new BigDecimal(0));
        sodto.setOrderPaidByFubi(new BigDecimal(0));
        sodto.setOrderPaidByRebate(new BigDecimal(0));
        sodto.setOrderPayStatus(0);
        sodto.setStoreId(vo.getStoreId());
        sodto.setOrderPromotionDiscount(new BigDecimal(0));
        sodto.setOrderStatus(0);
        sodto.setPaidOnlineThreshold(1);
        sodto.setPlatformId(vo.getPlatformId());
        sodto.setLimitFuBiPayAmount(limitFuBiPayAmount);
//        sodto.setRemark(remark);
        sodto.setUseFubi(vo.getUseFubi());
        sodto.setCompanyId(user.getCompanyId());
        if (saleWay == 4 || saleWay == 6) {
            sodto.setSaleWay(7);
        } else {
            sodto.setSaleWay(saleWay);
        }

        BigDecimal totalDelivery = new BigDecimal(0L);
        Map<Long, BigDecimal> deliveryMap = new HashMap<>();
        if (deliveryPriceObj != null) {
            for (Map.Entry<String, Object> deliveryObj : deliveryPriceObj.entrySet()){
                deliveryMap.put(Long.parseLong(deliveryObj.getKey()), new BigDecimal(deliveryObj.getValue().toString()));
                totalDelivery = totalDelivery.add(new BigDecimal(deliveryObj.getValue().toString()));
            }
        }
        sodto.setOrderDeliveryFee(totalDelivery);
        sodto.setDeliveryFee(totalDelivery);
        sodto.setOrderAmount(totalDelivery.add(BigDecimal.valueOf(orderAmount)));


        // 创建子订单
        List<SoChildDTO> soChildDTOList = new ArrayList<>();
        //7表示订单为预售订单.4,6表示商品是预售商品
        if ((saleWay == 6 || saleWay == 4||saleWay==7)&&EmptyUtil.isEmpty(vo.getPuId())) {
            //预售商品需要进行拆单
            //1.遍历购物车
            Map<Long, String> map = new HashMap<>();
            Map<Long, List<Long>> merchantPreDayMap = new HashMap<>();
            List<Long> merchantIdList = new ArrayList<>();
            for (Long l : cartItemIds_) {
                CartItemDTO cartItemById = cartItemFacade.findCartItemById(l);
                Long pId = cartItemById.getProductUnitId();
                StandardUnitDTO suByPuId = getSUByPuId(pId);
                Long preDay = suByPuId.getPresellPeriod();//预售期
                Long merchantId = suByPuId.getMerchantId();
                Boolean flag = false;
                for (SoItemDTO soItemDTO : soItems) {
                    if (soItemDTO.getPuId().equals(pId)) {
                        flag = true;
                        soItemDTO.setPreDay(preDay);
//                        soItemDTO.setChildCode(childCode);
                    }
                }
                if (!flag) {
                    //soitem与cartitem是一一对应的,这个错误发生时一定是,cartitem的puid在soitem中不存在
                    throw new BusinessException("soItem数据有误");
                }
                if (merchantPreDayMap.containsKey(merchantId)) {
                    if (!merchantPreDayMap.get(merchantId).contains(preDay)) {
                        merchantPreDayMap.get(merchantId).add(preDay);
                    }
                } else {
                    List<Long> preDayList = new ArrayList<>();
                    preDayList.add(preDay);
                    merchantPreDayMap.put(merchantId, preDayList);
                }
                if (!merchantIdList.contains(merchantId)) {
                    merchantIdList.add(merchantId);
                }
            }
            Collections.sort(merchantIdList);
            Map<String, String> childCodeMap = new HashMap<>();
            int n = 0;
            for (Long mId : merchantIdList) {
                List<Long> preDayList = merchantPreDayMap.get(mId);
                boolean flag = true;
                for (Long pd : preDayList) {
                    n++;
                    String childCode = orderCode + "-" + n;
                    SoChildDTO sc = new SoChildDTO();
                    sc.setChildCode(childCode);
                    sc.setPreSell(pd);//設置預售期
                    sc.setDeliverEndTime(DateUtils.nowToSomeDay(pd.intValue()));
                    sc.setPerformingParty(mId);
                    sc.setRemark(EmptyUtil.isEmpty(remarkObj) ? null : remarkObj.getString(String.valueOf(mId)));
                    if (flag) {
                        sc.setOrdinaryDeliveryFee(deliveryPriceObj.getBigDecimal(String.valueOf(mId)));
                        sc.setNeedCountDeliveryFee(1);
                        flag = false;
                    } else {
                        sc.setOrdinaryDeliveryFee(new BigDecimal(0L));
                        sc.setNeedCountDeliveryFee(0);
                    }
                    soChildDTOList.add(sc);
                    childCodeMap.put(mId + "-" + pd, childCode);
                }
            }
            for (SoItemDTO soItemDTO : soItems) {
                soItemDTO.setChildCode(childCodeMap.get(soItemDTO.getMerchantId() + "-" + soItemDTO.getPreDay()));
            }
        } else {
            List<Long> merchantIdList = new ArrayList<>();
            for (SoItemDTO soItemDTO : soItems) {
                if (!merchantIdList.contains(soItemDTO.getMerchantId())) {
                    merchantIdList.add(soItemDTO.getMerchantId());
                }
            }

            Collections.sort(merchantIdList);
            int n = 0;
            Map<Long, String> childCodeMap = new HashMap<>();
            Map<Long, String> selfChildCodeMap = new HashMap<>();

            List<Long> supplierIdList = new ArrayList<>();
            for (SoItemDTO soItemDTO : soItems) {
                if (!supplierIdList.contains(soItemDTO.getSupplierId())) {
                    if(soItemDTO.getSupplierId()!=null) {
                        supplierIdList.add(soItemDTO.getSupplierId());
                    }
                }
            }
            Collections.sort(supplierIdList);
            int m = 0;
            //Map<Long, String> childCodeMap = new HashMap<>();
            for (Long mId : merchantIdList) {
                n++;
                if(mId==1l) {
                    //自营按照供应商拆单
                    for (Long sId : supplierIdList) {
                        m++;
                        SoChildDTO sc = new SoChildDTO();
                        String childCode = orderCode + "-" + n+ "-" + m;
                        sc.setChildCode(childCode);
                        sc.setPerformingParty(mId);
                        sc.setSupplierId(sId);
                        sc.setRemark(EmptyUtil.isEmpty(remarkObj) ? null : remarkObj.getString(String.valueOf(sId)));
                        sc.setOrdinaryDeliveryFee(deliveryPriceObj.getBigDecimal(String.valueOf(mId)));
                        sc.setNeedCountDeliveryFee(1);
                        selfChildCodeMap.put(sId, childCode);
                        soChildDTOList.add(sc);
                    }
                }else {
                    SoChildDTO sc = new SoChildDTO();
                    String childCode = orderCode + "-" + n;
                    sc.setChildCode(childCode);
                    sc.setPerformingParty(mId);
                    sc.setRemark(EmptyUtil.isEmpty(remarkObj) ? null : remarkObj.getString(String.valueOf(mId)));
                    sc.setOrdinaryDeliveryFee(deliveryPriceObj.getBigDecimal(String.valueOf(mId)));
                    sc.setNeedCountDeliveryFee(1);
                    childCodeMap.put(mId, childCode);
                    soChildDTOList.add(sc);
                }
            }



            for (SoItemDTO soItemDTO : soItems) {
                if(soItemDTO.getMerchantId()==1L) {
                    soItemDTO.setChildCode(selfChildCodeMap.get(soItemDTO.getSupplierId()));
                }else {
                    soItemDTO.setChildCode(childCodeMap.get(soItemDTO.getMerchantId()));
                }
            }
            if(saleWay == 6 || saleWay == 4||saleWay==7){
                StandardUnitDTO suByPuId = getSUByPuId(vo.getPuId());
                soChildDTOList.get(0).setPreSell(suByPuId.getPresellPeriod());//設置預售期
                soChildDTOList.get(0).setDeliverEndTime(DateUtils.nowToSomeDay(suByPuId.getPresellPeriod().intValue()));
            }

        }

        //计算子订单的金额
        //设置子订单的付款金额(商品-优惠(此时为0))

        //设置子订单的公共信息
        SoThirdpartyDTO soThirdpartyDTO = new SoThirdpartyDTO();
        for (SoChildDTO soChildDTO : soChildDTOList) {
            //计算子订单的金额
            //设置子订单的付款金额(商品-优惠(此时为0))
            BigDecimal soChildAmount = new BigDecimal(0);
            List<SoChildDTO.SkuInfoDTO> infoDTOList = new ArrayList<>();
            for (SoItemDTO soItemDTO : soItems) {
                SoChildDTO.SkuInfoDTO infoDTO = new SoChildDTO.SkuInfoDTO();
                if (soChildDTO.getChildCode().equals(soItemDTO.getChildCode())) {
                    soChildAmount = soChildAmount.add(soItemDTO.getPrice().multiply(BigDecimal.valueOf(soItemDTO.getPuCount())));
                    infoDTO.setNum(soItemDTO.getPuCount());
                    infoDTO.setSkuId(soItemDTO.getExternalSkuId());
                    infoDTOList.add(infoDTO);
                }
            }
            soChildDTO.setProductAmount(soChildAmount);//商品金额
            soChildDTO.setSkuInfoList(infoDTOList);
            soChildDTO.setDeliveryStatus(0);//设置物流状态为0,代发货
            soChildDTO.setInvoiceId(vo.getInvoiceId());//设置发票id
            soChildDTO.setPlatformId(vo.getPlatformId());
//            soChildDTO.setRemark(remark);
            soChildDTO.setReceiverAddressId(vo.getReceiveAddressId());
            // 获取第三方对接参数
            int thirdpartyAtt=0;
            Long merchantId=0L;
            if(EmptyUtil.isNotEmpty(vo.getPuId())){
                if(isJdType0) {
                    thirdpartyAtt=13;
                }else {
                    thirdpartyAtt=getThirdpartyAttValue(puType0.getStandardProductUnitId());
                    merchantId = puType0.getMerchantId();
                }
            }

            if (commodityTemplateId != null &&(commodityTemplateId.equals(4L)||commodityTemplateId.equals(9L))) {
                // 话费充值
                // 1)设置子订单的第三方订单类型为话费充值
                soChildDTO.setThirdpartyType(Integer.valueOf(1));
                soThirdpartyDTO.setPhone(vo.getPhone());
                soThirdpartyDTO.setThirdpartyStatus(Integer.valueOf(-1));
            }else if(thirdpartyAtt==11){
                //第三方对接参数如果是11则是券仓卡券,需要修改订单的第三方订单类型)
                soChildDTO.setThirdpartyType(Integer.valueOf(2));
                soThirdpartyDTO.setThirdpartyStatus(Integer.valueOf(-1));
            }else {
                // 第三方订单类型: 0:无第三方订单 1:话费充值 2:券仓卡券,3:京东
                soChildDTO.setThirdpartyType(Integer.valueOf(0));
            }
            if(soChildDTO.getPerformingParty().equals(6L)){
                soChildDTO.setThirdpartyType(SoThirdpartyManageImpl.THIRDPARTY_TYPE_CAKE);
                soThirdpartyDTO.setThirdpartyStatus(Integer.valueOf(-1));
                soThirdpartyDTO.setSoChildCode(soChildDTO.getChildCode());
            }

        }


        // 创建订单设备信息
        SoDeviceDTO sd = new SoDeviceDTO();
        sd.setDeviceId(vo.getDeviceId());
        sd.setInterId(interfaceId);
        sd.setIp(vo.getIp());
        sd.setOs(vo.getOs());
        sd.setPhoneModel(vo.getPhoneModel());
        sd.setPlatformId(vo.getPlatformId());
        sd.setVersionCode(vo.getV());
        /*
         * facade方法统一提交事务 (创建订单,创建子订单,创建订单项,
         * 创建订单设备信息,冻结相应商品库存,删除购物购物车,更新发票,创建第三方子订单,优惠卷相关)
         * 此时sodto中
         */
        //订单创建成功,生成原始快照,快照
        if (vo.getReceiveAddressId() != null) {
            Long addressId =null;
            Map<String, Long> address =null;
            for (SoChildDTO soChildDTO : soChildDTOList) {
                address = this.createSnapsAddress(addr, soChildDTO.getChildCode());
                soChildDTO.setReceiverAddressId(address.get("snapAddressId"));
            }
            sodto.setReceiverAddressId(address.get("originalSnapAddressId"));
        }
        List<CompanyConfigDTO> configs = companyConfig.queryCompanyconfigs(user.getCompanyId());

        soFacade.normalOrderCreate(req,jedisUtil,addr,orderPayByCash,vo.getExchangeId(),vo.getExchangeCouponUnitId(),vo.getExchangeCouponBatchId(),sodto, soChildDTOList, sd, soItems, cartItemIds_, unitFlag, null, vo.getUserId(), vo.getUserName(),
                vo.getIp(), vo.getMac(), limitRuleRecordList, vo.getCouponType(), vo.getCouponUnitId(), vo.getCompanyId(), deliveryMap,configs);


        // 返回值
        Map<String, Object> data = new HashMap<>();
        data.put("orderCode", orderCode);
        data.put("saleWay", saleWay);
        return JsonResult.success(data);
    }

    /**抽取的方法:根据puid查询su**/
    private StandardUnitDTO getSUByPuId(Long puId) {
        //根据puid查询pu信息
        CommodityProductUnitDTO puCond = new CommodityProductUnitDTO();
        puCond.setId(puId);
        CommodityProductUnitDTO pu = commodityProductUnitFacade.findCommodityProductUnitById(puCond);
        if (pu == null) {
            throw new BusinessException("商品不存在");
        }
        if (pu.getIsVendible() == 0) {
            //pu不可销售
            throw new BusinessException("无该规格商品");
        }
        //根据suid查询su信息
        StandardUnitDTO standardUnitDTO = new StandardUnitDTO();
        standardUnitDTO.setId(pu.getStandardUnitId());

        StandardUnitDTO standardUnitDTO2 = productUnitFacade.findStandardUnitById(pu.getStandardUnitId());
        if (EmptyUtil.isNotEmpty(standardUnitDTO2)) {
            if (standardUnitDTO2.getStatus() != 3) {
                throw new BusinessException("商品已下架");
            }
        }
        return standardUnitDTO2;
    }

    private JsonResult checkCakeProductInfo(String puId,String skuId,CakeProductDetailDTO cakeProductDetailDTO,ReceiverAddressDTO addr){
        if(Objects.isNull(cakeProductDetailDTO)){
            return JsonResult.fail("无此商品");
        }
        CakeProductDetailProductsDTO detailProductsDTO = cakeProductDetailDTO.getProduct();
        if(Objects.isNull(detailProductsDTO) || !Objects.equals( detailProductsDTO.getStatus(),"1")){
            return JsonResult.fail("此商品已下架");
        }
        if(!Objects.equals( detailProductsDTO.getCan_buy(),"1")){
            return JsonResult.fail("此商品不能购买");
        }
        String cityName = addr.getGoodReceiverCity();
        if(EmptyUtil.isNotEmpty(cityName) && (Objects.equals(cityName,"市辖区") || Objects.equals(cityName,"直辖市"))){
            cityName =addr.getGoodReceiverProvince();
        }
        CakeProductDetailDTO lastCakeProductDetailDTO = productFacade.getCakeProduct(detailProductsDTO.getId(),skuId,cityName,null);

        if(Objects.isNull(lastCakeProductDetailDTO)){
            return JsonResult.fail(addr.getGoodReceiverCity()+"城市无此商品");
        }
        CakeProductDetailProductsDTO lastDetailProductsDTO = lastCakeProductDetailDTO.getProduct();
        if(Objects.isNull(lastCakeProductDetailDTO) || !Objects.equals( lastDetailProductsDTO.getStatus(),"1")){
            return JsonResult.fail("选择的收货城市该商品已下架");
        }
        CakeProductDetailSpecsDTO specsDTO = productFacade.getCakeProductSkuInfo(lastCakeProductDetailDTO,skuId);
        if(Objects.isNull(specsDTO)){
            return JsonResult.fail("无该规格商品");
        }
        if(com.egeo.utils.StringUtils.isNotEmpty(specsDTO.getStock()) && Integer.valueOf(specsDTO.getStock()) <=0){
            return JsonResult.fail("商品已售罄");
        }

        return null;
    }

    //创建快照和原始快照,addr参数仅有地址信息,type和childcode不需要
    private Map<String, Long> createSnapsAddress(ReceiverAddressDTO addr, String childCode) {
        //如果不是电子劵,创建快照
        addr.setSoChildCode(childCode);
        addr.setId(null);
        //拼装原始快照
        ReceiverAddressDTO originalSnapAddressDTO = new ReceiverAddressDTO();
        BeanUtils.copyProperties(addr, originalSnapAddressDTO);
        originalSnapAddressDTO.setType(4);
        //拼装快照
        ReceiverAddressDTO snapAddressDTO = new ReceiverAddressDTO();
        BeanUtils.copyProperties(addr, snapAddressDTO);
        snapAddressDTO.setType(3);
        return soFacade.saveSnapsAddress(originalSnapAddressDTO, snapAddressDTO);
    }


    //根据spuid查询第三方对接参数值
    private Integer getThirdpartyAttValue(Long spuId){
        int i=soFacade.getThirdpartyAttValue(spuId);
        if(EmptyUtil.isEmpty(i)){
            throw new BusinessException("未查询到第三方对接参数");
        }
        return i;
    }

    public ParseAddressJson getDeliveryPriceFromJd(String token,String address) {

        Map<String, String> map = new HashMap<>();
        //1.将地址转换成京东地址编号
        ParseAddressJson parseAddressJson = null;

        try {
            String result = jdUtils.parseAddress(address, token);
            JdResponse jdResponse = JSON.parseObject(result, JdResponse.class);
            if (jdResponse.isSuccess() && jdResponse.getResultCode().equals("0000")) {
                String json = jdResponse.getResult();
                parseAddressJson = JSON.parseObject(json, ParseAddressJson.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("获取京东地址编号失败:" + e.getMessage());
        }
        return parseAddressJson;
    }

    private double getOrderAmountAndAddItem(Long storeId, Long puId, Integer num, Long userId, Long platformId, String userName, Long companyId, CakeProductDetailDTO cakeProductDetailDTO, UserDTO user, List<SoItemDTO> soItems, List<LimitRuleRecordDTO> limitRuleRecordList, double orderAmount) {
        CakeProductDetailProductsDTO detailProductsDTO = cakeProductDetailDTO.getProduct();
        CakeProductDetailSpecsDTO cakeProductDetailSpecsDTO = productFacade.getCakeProductSkuInfo(cakeProductDetailDTO,String.valueOf(puId));
        //校验结束
        SoItemDTO item = new SoItemDTO();
        item.setCartType(1);
        item.setPlatformId(platformId);
        item.setPrice(new BigDecimal(cakeProductDetailSpecsDTO.getPrice()).setScale(2));
        item.setPuCount(num);
        item.setPuId(puId);
        // 一期暂时认为购物车商品不存在unit属性
        item.setUnitExist(0);
        item.setUserId(userId);
        item.setPuPicUrl(detailProductsDTO.getImage_path());
        item.setPuName(detailProductsDTO.getTitle());
        item.setMerchantId(7L);
        item.setSource(4);
        item.setExternalSkuId(puId +"");
        item.setSnapshot(JSON.toJSONString(cakeProductDetailDTO));
        soItems.add(item);
        orderAmount += num * item.getPrice().doubleValue();


        // 限购规则记录
        LimitRuleRecordDTO lrro = new LimitRuleRecordDTO();
        lrro.setBuySum(Long.valueOf(num));
        lrro.setBuyMoneySum(new BigDecimal(orderAmount).setScale(2));
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

    private JsonResult checkWorldProductInfo(String puId,String skuId,ChannelProductDetailVO channelProductDetailVO,ReceiverAddressDTO addr){
        if(Objects.isNull(channelProductDetailVO)){
            return JsonResult.fail("无此商品");
        }
        ChannelProductDTO detailProductsDTO = channelProductDetailVO.getChannelProductDTO();
        if(Objects.isNull(detailProductsDTO) || detailProductsDTO.getStatus() ==null || detailProductsDTO.getStatus() !=1){
            return JsonResult.fail("此商品已下架");
        }

        List<ChannelProductBatchDTO> batchDTOList = channelProductDetailVO.getBatchDTOList();
        ChannelProductBatchDTO batchDTO = productFacade.getCurrBatch(skuId,batchDTOList);
        if(Objects.isNull(batchDTO)){
            return JsonResult.fail("无此规格商品");
        }
        if( batchDTO.getNum()==null || batchDTO.getNum() <=0){
            return JsonResult.fail("商品已售罄");
        }

        return null;
    }

    private double getWorldOrderAmountAndAddItem(Long storeId, Long puId, Integer num, Long userId, Long platformId, String userName, Long companyId, ChannelProductDetailVO channelProductDetailVO, UserDTO user, List<SoItemDTO> soItems, List<LimitRuleRecordDTO> limitRuleRecordList, double orderAmount) {
        ChannelProductDTO channelProductDTO = channelProductDetailVO.getChannelProductDTO();
        List<ChannelProductBatchDTO> batchDTOList = channelProductDetailVO.getBatchDTOList();
        ChannelProductBatchDTO batchDTO = productFacade.getCurrBatch(puId+"",batchDTOList);
        //校验结束
        SoItemDTO item = new SoItemDTO();
        item.setCartType(1);
        item.setPlatformId(platformId);
        item.setPrice(batchDTO.getPrice().setScale(2));
        item.setPuCount(num);
        item.setPuId(puId);
        // 一期暂时认为购物车商品不存在unit属性
        item.setUnitExist(0);
        item.setUserId(userId);
        item.setPuPicUrl(channelProductDetailVO.getProductImg());
        item.setPuName(channelProductDTO.getTitle()+batchDTO.getSpecName());
        item.setMerchantId(8L);
        item.setSource(5);
        item.setExternalSkuId(batchDTO.getLinkSkuId());
        item.setSnapshot(JSON.toJSONString(channelProductDetailVO));
        item.setExternalProductId(channelProductDTO.getProductId());
        soItems.add(item);
        orderAmount += num * item.getPrice().doubleValue();


        // 限购规则记录
        LimitRuleRecordDTO lrro = new LimitRuleRecordDTO();
        lrro.setBuySum(Long.valueOf(num));
        lrro.setBuyMoneySum(new BigDecimal(orderAmount).setScale(2));
        lrro.setProductUnitId(puId);
        lrro.setProductUnitSerialNumber("world"+ puId);
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
