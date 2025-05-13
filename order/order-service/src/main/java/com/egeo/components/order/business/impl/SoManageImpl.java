package com.egeo.components.order.business.impl;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.egeo.components.config.client.CompanyConfigClient;
import com.egeo.components.order.business.GenerateChildOrderManage;
import com.egeo.components.order.business.PushOrderManage;
import com.egeo.components.order.business.thread.*;
import com.egeo.components.order.common.Constants;
import com.egeo.components.order.common.ProductChannelCodeEnum;
import com.egeo.components.order.dto.*;
import com.egeo.components.order.enums.ThirdConst;
import com.egeo.components.order.facade.*;
import com.egeo.components.order.manage.write.CakeAddressWriteManage;
import com.egeo.components.order.service.read.SoChildReadService;
import com.egeo.components.order.service.write.SoChildWriteService;
import com.egeo.components.order.strategy.factory.OrderConfirmFactory;
import com.egeo.components.order.strategy.factory.OrderCreateFactory;
import com.egeo.components.order.strategy.vo.*;
import com.egeo.components.order.vo.*;
import com.egeo.components.order.vo.cake.CakeRuleIdsVO;
import com.egeo.components.order.vo.cake.ChannelSkuInfoVO;
import com.egeo.components.order.vo.world.WroldSplitOrderRuleVO;
import com.egeo.components.product.client.CardCombinationClient;
import com.egeo.components.product.client.ProductAttValueClient;
import com.egeo.components.product.dto.*;
import com.egeo.components.product.dto.channel.ChannelProductBatchDTO;
import com.egeo.components.product.dto.channel.ChannelProductDTO;
import com.egeo.components.product.dto.channel.ChannelProductDescriptionDTO;
import com.egeo.components.product.dto.channel.ChannelProductSkuDTO;
import com.egeo.components.product.dto.world.goodchild.WorldStoreDTO;
import com.egeo.components.product.vo.*;
import com.egeo.components.promotion.client.BuyCardClient;
import com.egeo.components.promotion.dto.*;
import com.egeo.components.promotion.enums.CardTypeEnum;
import com.egeo.components.promotion.enums.CardUseStateEnum;
import com.egeo.components.promotion.enums.SettleMethodEnum;
import com.egeo.components.promotion.vo.UseBuyCardItemDetailReqVO;
import com.egeo.components.promotion.vo.UseCardReqVO;
import com.egeo.components.user.client.SupplierClient;
import com.egeo.components.user.enums.UserChannelSourceEnum;
import com.egeo.components.utils.CakeUtil;

import com.egeo.components.utils.FHCollectionUtils;
import com.egeo.components.utils.JdUtils2;
import com.egeo.components.utils.NumberUtils;
import lombok.val;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.helper.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.egeo.common.RechargePhoneErrorCode;
import com.egeo.components.config.dto.CardSaltDTO;
import com.egeo.components.config.dto.CompanyConfigDTO;
import com.egeo.components.config.dto.SaltDTO;
import com.egeo.components.finance.dto.AccountBatchDTO;
import com.egeo.components.finance.dto.AccountFlowDTO;
import com.egeo.components.finance.dto.UserAccountDTO;
import com.egeo.components.order.business.SoManage;
import com.egeo.components.order.common.DateUtils;
import com.egeo.components.order.converter.SoChildConverter;
import com.egeo.components.order.converter.SoConverter;
import com.egeo.components.order.dto.CartDTO;
import com.egeo.components.order.dto.CartItemDTO;
import com.egeo.components.order.dto.LimitRuleRecordDTO;
import com.egeo.components.order.dto.OrderConfirmGoodsDTO;
import com.egeo.components.order.dto.OrderResult;
import com.egeo.components.order.dto.ProvCityAreaDTO;
import com.egeo.components.order.dto.ReceiverAddressDTO;
import com.egeo.components.order.dto.SoChildDTO;
import com.egeo.components.order.dto.SoChildFlowDTO;
import com.egeo.components.order.dto.SoCustomerServiceDTO;
import com.egeo.components.order.dto.SoDTO;
import com.egeo.components.order.dto.SoDeliveryItemDTO;
import com.egeo.components.order.dto.SoDeviceDTO;
import com.egeo.components.order.dto.SoFlowDTO;
import com.egeo.components.order.dto.SoInvoiceDTO;
import com.egeo.components.order.dto.SoItemDTO;
import com.egeo.components.order.dto.SoPackageBoxDTO;
import com.egeo.components.order.dto.SoPackageDTO;
import com.egeo.components.order.dto.SoThirdpartyDTO;
import com.egeo.components.order.facade.utils.UserMembershipCheckUtils;
import com.egeo.components.order.vo.jd.JdDeliveryPrice;
import com.egeo.components.order.vo.jd.JdProductStockSearch;
import com.egeo.components.order.vo.jd.JdResponse;
import com.egeo.components.order.vo.jd.JdSkuAddressSellStatus;
import com.egeo.components.order.vo.jd.JdSkuSellStatus;
import com.egeo.components.order.vo.jd.ParseAddressJson;
import com.egeo.components.order.vo.jd.SkuInfo;
import com.egeo.components.pay.dto.AwaitQueueDTO;
import com.egeo.components.pay.enums.OrderConstant;
import com.egeo.components.product.dto.CommodityProductUnitDTO;
import com.egeo.components.product.dto.JdProductDTO;
import com.egeo.components.product.dto.MembershipDTO;
import com.egeo.components.product.dto.MembershipUserDTO;
import com.egeo.components.product.dto.MerchantDTO;
import com.egeo.components.product.dto.SkuDTO;
import com.egeo.components.product.dto.StandardProductUnitAttNameDTO;
import com.egeo.components.product.dto.StandardProductUnitAttValueDTO;
import com.egeo.components.product.dto.StandardUnitDTO;
import com.egeo.components.product.dto.StandardUnitStoreDTO;
import com.egeo.components.product.dto.StoreDTO;
import com.egeo.components.product.dto.StoreProductUnitDTO;
import com.egeo.components.product.dto.StoreTreeNodeDTO;
import com.egeo.components.stock.dto.StorePuWarehouseStockDTO;
import com.egeo.components.user.client.CompanyClient;
import com.egeo.components.user.client.UserClient;
import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.components.user.dto.Enterprise;
import com.egeo.components.user.dto.PlatformDTO;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.components.user.dto.UserExtendDTO;
import com.egeo.config.RuntimeContext;
import com.egeo.core.Constant.BusinessExceptionConstant;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.util.security.MD5Util;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.SequenceUtil;
import com.egeo.utils.SpringContextTool;
import com.egeo.utils.Upload;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.delivery.JdUtils;
import com.egeo.utils.encrypt.QEncodeUtil;
import com.egeo.utils.pay.PayUtil;
import com.egeo.utils.str.StringUtils;
import com.egeo.utils.thirdparty.RechargePhoneUtil;
import com.egeo.web.JsonResult;
import org.springframework.util.CollectionUtils;

@Service("so")
public class SoManageImpl implements SoManage {

    public Logger logger = LoggerFactory.getLogger(this.getClass().getName());

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
    @Resource
    private SoChildReadService soChildReadService;

    @Autowired
    private CakeAddressWriteManage cakeAddressWriteManage;
    @Resource
    private CakeUtil cakeUtil;

    @Autowired
    private PushOrderManage pushOrderManage;

    @Resource
    private SoChildWriteService soChildWriteService;

    @Resource
    private OrderConfirmFactory orderConfirmFactory;

    @Resource
    private GenerateChildOrderManage generateChildOrderManage;

    @Resource
    private BuyCardClient buyCardClient;
    @Resource
    private CardCombinationClient cardCombinationClient;

    @Autowired
    private JdUtils2 jdUtils2;

    @Override
    public JsonResult<Map<String, Object>> createOrder(HttpServletRequest req, CreateOrderDTO orderDTO) {
        return this.createOrder(req,orderDTO.getPuIdList(),orderDTO.getExchangeId(),orderDTO.getExchangeCouponUnitId(),
                orderDTO.getExchangeCouponBatchId(),orderDTO.getOrderType(),orderDTO.getStoreId(),
                orderDTO.getReceiveAddressId(),orderDTO.getType(),orderDTO.getCartItemIds(),orderDTO.getPuId(),
                orderDTO.getNum(),orderDTO.getUseFubi(),orderDTO.getRemark(),orderDTO.getInvoiceId(),orderDTO.getUserId(),
                orderDTO.getPlatformId(),orderDTO.getDeviceId(),orderDTO.getOrderChannel(),orderDTO.getIp(),orderDTO.getOs(),
                orderDTO.getPhoneModel(),orderDTO.getVersionCode(),orderDTO.getUserName(),orderDTO.getMac(),orderDTO.getCompanyId(),
                orderDTO.getPhone(),orderDTO.getCouponType(),orderDTO.getCouponUnitId(),orderDTO.getDeliveryPrice(),orderDTO.getChannelProductId(),orderDTO.getSource(),orderDTO.getThirdOrderJsonStr());
    }

    private void checkEmptyException(Object obj,String message){
        if(EmptyUtil.isEmpty(obj)){
            throw new BusinessException(message);
        }
    }
    /**
     * 创建订单
     */
    @Override
    public JsonResult<Map<String, Object>> createOrder(HttpServletRequest req, List<Long> puIdList, Long exchangeId, Long exchangeCouponUnitId, Long exchangeCouponBatchId, Integer orderType, Long storeId, Long receiveAddressId, Integer type,
                                                       String cartItemIds, Long puId, Integer num, Integer useFubi, String remark, Long invoiceId, Long userId,
                                                       Long platformId, String deviceId, Integer orderChannel, String ip, String os, String phoneModel,
                                                       String versionCode, String userName, String mac, Long companyId, String phone, Integer couponType,
                                                       Long couponUnitId, String deliveryPrice,String channelProductId,Integer source,String thirdOrderJsonStr) {

        JSONObject remarkObj = null;
        if (EmptyUtil.isNotEmpty(remark)) {
            remarkObj = JSONObject.parseObject(remark);
        }
        JSONObject deliveryPriceObj = null;
        if (EmptyUtil.isNotEmpty(deliveryPrice)) {
            deliveryPriceObj = JSONObject.parseObject(deliveryPrice);
        }
        CompanyDTO companyDTO= soFacade.findCompanyById(companyId);
        if(EmptyUtil.isEmpty(companyDTO)){
            throw new BusinessException("所属公司不存在");
        }

        Long interfaceId = 0l;
        if (type == null) {
            return JsonResult.fail("未知下单类型");
        }
        if (useFubi == null)
            useFubi = 0;
        if (orderChannel == null)
            return JsonResult.fail("渠道参数缺失");
        if (EmptyUtil.isBlank(deviceId))
            return JsonResult.fail("设备编号缺失");
        if (EmptyUtil.isBlank(os))
            return JsonResult.fail("操作系统参数缺失");
        if (EmptyUtil.isBlank(phoneModel))
            return JsonResult.fail("设备型号参数缺失");
        if (EmptyUtil.isBlank(versionCode))
            return JsonResult.fail("应用版本号参数缺失");
        //判断商品是不是会籍商品
        //获得该商品的会籍信息
       /* ProductUnitDTO productUnitDTO = soFacade.findPuByid(puId);
        Long skuId = productUnitDTO.getSkuId();*/
        MembershipDTO membershipDTO = null;

        List<Integer> sources = new ArrayList<>();
        //sources.add(3);
        sources.add(4);
        sources.add(5);

        List<Long> cartItemIds_ = null;
        Integer saleWay = 1;
        boolean isJdType0 = false;
        boolean isQingMei= Objects.equals(type,6);
        QingMeiOrderDTO qingMeiOrderDTO=null;
        JdProductDTO jdProductDTOType0 = null;
        CommodityProductUnitDTO puType0 = null;
        CakeProductDetailDTO cakeProductDetailDTO = null;
        Integer sourceProduct=0;
        ChannelProductDetailVO channelProductDetailVO = null;
        if(type.intValue()==0) {
            // 商品存在校验(商品和用户的权限已经校验)
            if(Objects.isNull(source) || !sources.contains(source)){
                puType0 = productFacade.queryPuByIdAndSupplierPrice(puId);
                if(puType0 !=null){
                    sourceProduct =1;
                }
                if(puType0==null) {
                    jdProductDTOType0 = productFacade.checkJdProductStatus(puId+"");
                    checkEmptyException(jdProductDTOType0,"京东商品不存在");
                    isJdType0 = true;
                    sourceProduct = 3;
                }
            }

            if(source !=null && source.intValue()==3) {
                jdProductDTOType0 = productFacade.checkJdProductStatus(puId+"");
                if(jdProductDTOType0!=null) {
                    isJdType0 = true;
                    sourceProduct = 3;
                }
            }

            //若京东和自营为空
            if(source !=null && source.intValue()==4) {
                sourceProduct=4;
                cakeProductDetailDTO =productFacade.getCakeProduct(channelProductId,puId+"",null,null);
            }

            if(source !=null && source.intValue()==5) {
                sourceProduct=5;
                channelProductDetailVO  = productFacade.findWorldProduct(channelProductId,puId+"");
            }
        }


        //校验会籍预售权限/门店
        //if(!isJdType0){
        if(sourceProduct !=3 && sourceProduct !=4 && !isQingMei && sourceProduct !=5) {
            if (EmptyUtil.isNotEmpty(puId)) {

                StandardUnitDTO suByPuId = getSUByPuId(puId);
                Map<Integer, String> integerStringMap = UserMembershipCheckUtils.checkUserMembershipAuthority(userId, suByPuId.getId(), suByPuId.getSaleWay(), platformId);
                Set<Integer> integers = integerStringMap.keySet();
                for (Integer i : integers) {
                    if (i == 0) {
                        return JsonResult.fail(integerStringMap.get(i));
                    }

                }

                StandardProductUnitAttNameDTO standardProductUnitAttNameDTO=soFacade.findStandardProductUnitAttNameById(suByPuId.getStandardProductUnitId());
                List<StandardProductUnitAttValueDTO> standardProductUnitAttValueDTOS=soFacade.findStandardProductUnitValue(standardProductUnitAttNameDTO.getId());
                if(type==0&&suByPuId.getSaleWay()==1L&&EmptyUtil.isNotEmpty(standardProductUnitAttValueDTOS)){
                    //会籍购买必须满足第三方对接参数为内部会籍
                    saleWay = 5;
                }else{
                    saleWay = suByPuId.getSaleWay();
                }
                //补差价订单
                if(EmptyUtil.isNotEmpty(orderType)&&orderType.equals(8)){
                    saleWay=8;
                }
            } else {
                //如果是预售生成的订单编号类型为P,这里设置一下订单类型
                cartItemIds_ = JSONArray.parseArray(cartItemIds, Long.class);
                if (EmptyUtil.isEmpty(cartItemIds_)) {
                    return JsonResult.fail("购物车项参数为空");
                }
                for (Long l : cartItemIds_) {
                    CartItemDTO cartItemById = cartItemFacade.findCartItemById(l);
                    if(cartItemById==null) {
                        continue;
                    }
                    CartDTO dto = new CartDTO();
                    dto.setId(cartItemById.getCartId());
                    CartDTO cartById = cartFacade.findCartById(dto);

                    saleWay = cartById.getSaleWay();


                }
            }
        }



        // 生成订单编号, 订单类型：1正常销售、2团购、3兑换券 (默认为1正常销售) 4:普通预售 5:会籍购买 6.会籍预售 8.以旧换新加价(补差价)

        if (couponType != null && couponUnitId != null && couponType == 1) {
            // 兑换卷兑换
            saleWay = 3;
        }
      /*  if (membershipDTO != null) {
            saleWay = 5;

        }*/


        //生成订单code
        String orderCode = SequenceUtil.genOrderCode(orderChannel, saleWay, userId, platformId);
        // 订单编号校验
        SoDTO so_ = soFacade.querySoByOrderCode(orderCode);
        if (so_ != null)
            return JsonResult.fail("订单编号重复,请重试");
        // 发票校验
        SoInvoiceDTO inv = null;
        if (EmptyUtil.isEmpty(invoiceId) || invoiceId == 0) {
            invoiceId = null;
        }
        if (invoiceId != null) {
            inv = soInvoiceFacade.findSoInvoiceById(invoiceId);
            if (inv == null)
                return JsonResult.fail("发票信息丢失");
        }

        // 查询用户信息
        UserDTO user = userFacade.queryUserById(userId);
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
        boolean isCakeOrWorld = EmptyUtil.isNotEmpty(source) && (Objects.equals(source,ThirdConst.Source.CAKE) ||  Objects.equals(source,ThirdConst.Source.WORLD));
        // 通过puid查询sku,然后查到spu信息
        Long commodityTemplateId = 2L;
        if (puId != null && (!isJdType0)&& (!isQingMei) && (!isCakeOrWorld)) {
            commodityTemplateId = productFacade.queryCommodityTemplateIdByPuId(puId);
        }

        if (EmptyUtil.isNotEmpty(receiveAddressId) && receiveAddressId.equals(Long.valueOf(0))) {
            receiveAddressId = null;
        }
        ReceiverAddressDTO addr = null;
        StoreDTO storeById = storeFacade.findStoreById(storeId);
        //如果是直接下单,判断商品模板,如果是购物车下单,则一定是实物商品
        if (((commodityTemplateId.equals(2L)||commodityTemplateId.equals(7L))||type==1)&&storeById.getIsDetail() != 1 && !isQingMei) {
            if (receiveAddressId == null) {
                // 收货地址校验:实体商品
                return JsonResult.fail("请填写收货人信息");
            }
            addr = receiverAddressFacade.findReceiverAddressById(receiveAddressId);
            if (addr == null)
                return JsonResult.fail("收货地址不存在");

        } else {
            // 电子卡券类/充值类商品没有收货地址
            receiveAddressId = null;
        }

        BigDecimal limitFuBiPayAmount = BigDecimal.ZERO;
        if (type.intValue() == 0) {
            // 直接购买
            // 参数校验
            if (puId == null || num == null)
                return JsonResult.fail("参数为空");


            /*对pu进行加锁(同一个pu不同人进行购买时有锁限制)
             */
            boolean lock = true;
            try {
                lock=jedisUtil.lockWithParam(JedisUtil.PU_LOCK_KEY_PRE+puId,PU_LOCK_VALUE,JedisUtil.PU_LOCK_EXPIRE_TIME);
            } catch (InterruptedException e) {
                logger.info("当前获取pu的redis锁异常,puId="+puId);
                jedisUtil.delLock(JedisUtil.PU_LOCK_KEY_PRE+puId);
                e.printStackTrace();
            }
            if(!lock){
                logger.info("创建订单获取pu锁失败");
                return JsonResult.fail("当前网络繁忙,请稍后重试");
            }
            puIdList.add(puId);


            // 兑换卷购买su数量必须为1
            if (couponType != null && couponUnitId != null && couponType == 1 && num != 1)
                return JsonResult.fail("兑换数量必须是1");

            // 判断是否是话费充值
            if (commodityTemplateId != null && (commodityTemplateId.equals(4L)||commodityTemplateId.equals(9L))) {
                if (EmptyUtil.isEmpty(phone))
                    return JsonResult.fail("手机号码不能为空");
                if (!StringUtils.validMobile(phone))
                    return JsonResult.fail("请输入正确的手机号码");
                // 默认商品数量为1
                num = Integer.valueOf(1);
            }

            // 商品存在校验(商品和用户的权限已经校验)


            if(sourceProduct ==3) {
                if(jdProductDTOType0.getState()==0){
                    return JsonResult.fail("抱歉，该商品已下架");
                }
                //String token = jdUtils.getAccessToken(jedisUtil);
                String token = jdUtils2.getAccessToken(jedisUtil);
                //2.校验京东是否可售
                String skuSellStatusFromJd = jdUtils.getSkuSellStatusFromJd(token, puId+"", "");
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
                        String skuAddressSellStatusFromJd = jdUtils.getSkuAddressSellStatusFromJd(token, puId+"", parseAddressJson.getProvinceId(), parseAddressJson.getCityId(), parseAddressJson.getCountyId(), parseAddressJson.getTownId());
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
                        stockSearch.setSkuId(puId);
                        stockSearch.setNum(Long.valueOf(num));
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
                item.setPlatformId(platformId);
                item.setPrice(jdProductDTOType0.getPrice());
                item.setPuCount(num);
                item.setPuId(puId);
                // 一期暂时认为购物车商品不存在unit属性
                item.setUnitExist(0);
                item.setUserId(userId);
                item.setPuPicUrl(jdProductDTOType0.primaryImg());
                item.setPuName(jdProductDTOType0.getName());
                item.setMerchantId(6l);
                item.setSource(3);
                item.setExternalSkuId(puId+"");
                item.setSnapshot(JSON.toJSONString(jdProductDTOType0));
                if (EmptyUtil.isNotEmpty(jdProductDTOType0)){
                    String taxInfo=jdProductDTOType0.getTaxInfo();
                    if (EmptyUtil.isNotEmpty(taxInfo) && com.egeo.components.utils.StringUtil.isNumeric(taxInfo)){
                        item.setTaxRate(new BigDecimal(taxInfo));
                    }
                }
                soItems.add(item);
                orderAmount += num * jdProductDTOType0.getPrice().doubleValue();


                // 限购规则记录
                LimitRuleRecordDTO lrro = new LimitRuleRecordDTO();
                lrro.setBuySum(Long.valueOf(num));
                lrro.setBuyMoneySum(jdProductDTOType0.getPrice());
                lrro.setProductUnitId(puId);
                lrro.setProductUnitSerialNumber("jd-"+puId);
                lrro.setStandardUnitId(puId);
                lrro.setCreateUserid(userId);
                lrro.setCreateUsername(userName);
                lrro.setCreateUserMobile(user.getMobile());
                lrro.setCompanyId(companyId);
                lrro.setStoreId(storeId);
                limitRuleRecordList.add(lrro);
            }else if(sourceProduct ==4){
                JsonResult checkResult = checkCakeProductInfo(channelProductId,puId+"",cakeProductDetailDTO,addr);
                if(Objects.nonNull(checkResult)){
                    return checkResult;
                }
                orderAmount = getOrderAmountAndAddItem(storeId, puId, num, userId, platformId, userName, companyId, cakeProductDetailDTO, user, soItems, limitRuleRecordList, orderAmount);
            }else if(sourceProduct ==5){
                JsonResult checkResult = checkWorldProductInfo(channelProductId,puId+"",channelProductDetailVO,addr);
                if(Objects.nonNull(checkResult)){
                    return checkResult;
                }
                orderAmount = getWorldOrderAmountAndAddItem(storeId, puId, num, userId, platformId, userName, companyId, channelProductDetailVO, user, soItems, limitRuleRecordList, orderAmount);

            }else {
                if (puType0 == null)
                    return JsonResult.fail("商品不存在");
                if (puType0.getIsVendible() == 0) {
                    // pu不可销售
                    return JsonResult.fail("无该规格商品");
                }
                // 商品上架校验
                if (puType0.getStatus().intValue() != 3)
                    return JsonResult.fail("商品已下架");
                //**************需要新增京东逻辑

                CommodityProductUnitDTO commodityProductUnitDTO = productFacade.queryPuById(puId);
                Long skuId = commodityProductUnitDTO.getSkuId();
                membershipDTO = soFacade.findMembershipBySkuId(skuId,platformId);
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
            }
        } else if (isQingMei) {
            qingMeiOrderDTO=JSON.parseObject(thirdOrderJsonStr,QingMeiOrderDTO.class);
            qingMeiOrderDTO.setTotalShippingFee(BigDecimal.ZERO);
            for(QingMeiChildOrderDTO qmChildDTO:qingMeiOrderDTO.getTradeList()){
                qingMeiOrderDTO.setTotalShippingFee(qingMeiOrderDTO.getTotalShippingFee().add(qmChildDTO.getShippingFee()));
                for (QingMeiChildItemDTO qmItemDTO:qmChildDTO.getGoodsList()){
                    //校验结束
                    SoItemDTO item = new SoItemDTO();
                    item.setCartType(type);
                    item.setPlatformId(platformId);
                    item.setPuId(qmItemDTO.getPuId());
                    item.setPrice(qmItemDTO.getPrice());
                    item.setPuCount(qmItemDTO.getQuantity());
                    // 一期暂时认为购物车商品不存在unit属性
                    item.setUnitExist(0);
                    item.setUserId(userId);
                    item.setPuPicUrl(qmItemDTO.getGoodsImage());
                    item.setPuName(qmItemDTO.getSkuName());
                    item.setThirdChildCode(qmChildDTO.getOrderNo());
                    item.setMerchantId(ThirdConst.Merchant.QM);
                    item.setSource(ThirdConst.Source.QM);
                    item.setExternalSkuId(qmItemDTO.getSkuCode());
                    item.setSnapshot(JSON.toJSONString(qmItemDTO));
                    item.setFinalPromotionAver(item.getPrice().multiply(new BigDecimal(item.getPuCount())).subtract(qmItemDTO.getGoodsPayPrice()));
                    item.setTaxRate(qmItemDTO.getTaxRate());
                    item.setTaxCode(qmItemDTO.getTaxCode());
                    item.setTaxUnit(qmItemDTO.getTaxUnit());
                    item.setSubBizId(qmItemDTO.getSubBizId());
                    soItems.add(item);
                    orderAmount += item.getPrice().multiply(new BigDecimal(item.getPuCount())).doubleValue();

                    // 限购规则记录
                    LimitRuleRecordDTO lrro = new LimitRuleRecordDTO();
                    lrro.setBuySum(Long.valueOf(qmItemDTO.getQuantity()));
                    lrro.setBuyMoneySum(qmItemDTO.getGoodsPayPrice());
                    lrro.setProductUnitId(qmItemDTO.getPuId());
                    lrro.setProductUnitSerialNumber("qm-"+qmItemDTO.getSkuCode());
                    lrro.setStandardUnitId(qmItemDTO.getPuId());
                    lrro.setCreateUserid(userId);
                    lrro.setCreateUsername(userName);
                    lrro.setCreateUserMobile(user.getMobile());
                    lrro.setCompanyId(companyId);
                    lrro.setStoreId(storeId);
                    limitRuleRecordList.add(lrro);
                }
            }
        } else {
            // 购物车下单
            if (couponType != null && couponType == 1) {
                return JsonResult.fail("不能直接从购物车兑换商品");
            }

			/* 在上面已经校验拆分
            if (EmptyUtil.isEmpty(cartItemIds)) {
				return JsonResult.fail("参数为空1");
			} else {
				cartItemIds = cartItemIds.replace("[", "");
				cartItemIds = cartItemIds.replace("]", "");
				if (EmptyUtil.isEmpty(cartItemIds))
					return JsonResult.fail("参数为空2");
			}
			cartItemIds_ = StringUtils.string2IdList(cartItemIds, ",");*/
            for (Long ciid : cartItemIds_) {
                // 购物车存在校验
                CartItemDTO ci = cartItemFacade.findCartItemById(ciid);
                if (ci == null)
                    return JsonResult.fail("购物车项不存在");
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
                puIdList.add(puId_);
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
                    item.setPlatformId(platformId);
                    item.setPrice(jdProductDTO.getPrice());
                    item.setPuCount(num_);
                    item.setPuId(puId_);
                    // 一期暂时认为购物车商品不存在unit属性
                    item.setUnitExist(0);
                    item.setUserId(userId);
                    item.setPuPicUrl(jdProductDTO.primaryImg());
                    item.setPuName(jdProductDTO.getName());
                    item.setMerchantId(6l);
                    item.setSource(3);
                    item.setExternalSkuId(puId_+"");
                    item.setSnapshot(JSON.toJSONString(jdProductDTO));
                    if (EmptyUtil.isNotEmpty(jdProductDTO)){
                        String taxInfo=jdProductDTO.getTaxInfo();
                        if (EmptyUtil.isNotEmpty(taxInfo) && com.egeo.components.utils.StringUtil.isNumeric(taxInfo)){
                            item.setTaxRate(new BigDecimal(taxInfo));
                        }
                    }
                    soItems.add(item);
                    orderAmount += num_ * jdProductDTO.getPrice().doubleValue();


                    // 限购规则记录
                    LimitRuleRecordDTO lrro = new LimitRuleRecordDTO();
                    lrro.setBuySum(Long.valueOf(num_));
                    lrro.setBuyMoneySum(jdProductDTO.getPrice());
                    lrro.setProductUnitId(puId_);
                    lrro.setProductUnitSerialNumber("jd-"+puId_);
                    lrro.setStandardUnitId(puId_);
                    lrro.setCreateUserid(userId);
                    lrro.setCreateUsername(userName);
                    lrro.setCreateUserMobile(user.getMobile());
                    lrro.setCompanyId(companyId);
                    lrro.setStoreId(storeId);
                    limitRuleRecordList.add(lrro);

                }else if(ci.isCake()){
                    //String productId,String skuId,String cityName,String cityId
                    //CartItemDTO ci
                    //蛋糕叔叔商品
                    String productId =ci.getChannelProductId();
                    CakeProductDetailDTO dto = productFacade.getCakeProduct(productId,null,null,null);
                    JsonResult checkResult = checkCakeProductInfo(productId,puId_+"",dto,addr);
                    if(Objects.nonNull(checkResult)){
                        return checkResult;
                    }
                    CakeProductDetailProductsDTO detailProductsDTO = dto.getProduct();
                    CakeProductDetailSpecsDTO specsDTO = productFacade.getCakeProductSkuInfo(dto,String.valueOf(puId_));

                    //校验结束
                    SoItemDTO item = new SoItemDTO();
                    item.setCartType(1);
                    item.setPlatformId(platformId);
                    item.setPrice(new BigDecimal(specsDTO.getPrice()).setScale(2));
                    item.setPuCount(num_);
                    item.setPuId(puId_);
                    // 一期暂时认为购物车商品不存在unit属性
                    item.setUnitExist(0);
                    item.setUserId(userId);
                    item.setPuPicUrl(detailProductsDTO.getImage_path());
                    item.setPuName(detailProductsDTO.getTitle());
                    item.setMerchantId(7L);
                    item.setSource(4);
                    item.setExternalSkuId(puId_+"");
                    item.setTaxRate(new BigDecimal(cakeUtil.getCakeTaxRate()).setScale(2, RoundingMode.HALF_UP));
                    item.setSnapshot(JSON.toJSONString(dto));
                    item.setExternalProductId(ci.getChannelProductId());
                    soItems.add(item);
                    orderAmount += num_ * item.getPrice().doubleValue();


                    // 限购规则记录
                    LimitRuleRecordDTO lrro = new LimitRuleRecordDTO();
                    lrro.setBuySum(Long.valueOf(num_));
                    lrro.setBuyMoneySum(item.getPrice().setScale(2, RoundingMode.HALF_UP));
                    lrro.setProductUnitId(puId_);
                    lrro.setProductUnitSerialNumber("cake-"+puId_);
                    lrro.setStandardUnitId(puId_);
                    lrro.setCreateUserid(userId);
                    lrro.setCreateUsername(userName);
                    lrro.setCreateUserMobile(user.getMobile());
                    lrro.setCompanyId(companyId);
                    lrro.setStoreId(storeId);
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
                    List<ChannelProductSkuDTO> skuList = productDetailVO.getSkuList();
                    ChannelProductSkuDTO channelProductSkuDTO = productFacade.getCurrSkuInfo(batchDTO.getLinkSkuId(),skuList);
                    BigDecimal taxRate = BigDecimal.ZERO;
                    if(channelProductSkuDTO !=null && channelProductSkuDTO.getTaxRate() !=null){
                        logger.info("商品税率:{}",channelProductSkuDTO.getTaxRate());
                        taxRate = new BigDecimal(channelProductSkuDTO.getTaxRate()).multiply(new BigDecimal(100));
                        taxRate =  taxRate.setScale(2);
                    }
                    WorldStoreDTO worldStoreDTO = null;
                    if(EmptyUtil.isNotEmpty(channelProductSkuDTO.getStoreListText())){
                        List<WorldStoreDTO> worldStoreDTOList = JSON.parseArray(channelProductSkuDTO.getStoreListText(), WorldStoreDTO.class);
                        if(!CollectionUtils.isEmpty(worldStoreDTOList)){
                            worldStoreDTO =worldStoreDTOList.get(0);
                        }
                    }

                    WroldSplitOrderRuleVO splitOrderRuleVO = new WroldSplitOrderRuleVO();
                    splitOrderRuleVO.setGoodsType(channelProductDTO.getGoodsType());
                    if(Objects.nonNull(worldStoreDTO)){
                        splitOrderRuleVO.setStore_code(worldStoreDTO.getStore_code());
                        splitOrderRuleVO.setStore_id(worldStoreDTO.getStore_id());
                        splitOrderRuleVO.setStore_isCombineOrders(Boolean.valueOf(worldStoreDTO.getStore_isCombineOrders()));
                    }
                    //校验结束
                    SoItemDTO item = new SoItemDTO();
                    item.setCartType(1);
                    item.setPlatformId(platformId);
                    item.setPrice(batchDTO.getPrice().setScale(2, RoundingMode.HALF_UP));
                    item.setPuCount(num_);
                    item.setPuId(puId_);
                    // 一期暂时认为购物车商品不存在unit属性
                    item.setUnitExist(0);
                    item.setUserId(userId);
                    item.setPuPicUrl(productDetailVO.getProductImg());
                    item.setPuName(channelProductDTO.getTitle()+batchDTO.getSpecName());
                    item.setMerchantId(8L);
                    item.setSource(5);
                    item.setExternalSkuId(batchDTO.getLinkSkuId());
                    item.setSnapshot(JSON.toJSONString(productDetailVO));
                    item.setExternalProductId(ci.getChannelProductId());
                    item.setTaxRate(taxRate);
                    item.setSplitOrderRule(JSON.toJSONString(splitOrderRuleVO));
                    soItems.add(item);
                    orderAmount += num_ * item.getPrice().doubleValue();


                    // 限购规则记录
                    LimitRuleRecordDTO lrro = new LimitRuleRecordDTO();
                    lrro.setBuySum(Long.valueOf(num_));
                    lrro.setBuyMoneySum(item.getPrice().setScale(2, RoundingMode.HALF_UP));
                    lrro.setProductUnitId(puId_);
                    lrro.setProductUnitSerialNumber("world-"+puId_);
                    lrro.setStandardUnitId(puId_);
                    lrro.setCreateUserid(userId);
                    lrro.setCreateUsername(userName);
                    lrro.setCreateUserMobile(user.getMobile());
                    lrro.setCompanyId(companyId);
                    lrro.setStoreId(storeId);
                    limitRuleRecordList.add(lrro);
                }else {
                    CommodityProductUnitDTO pu = productFacade.queryPuById(puId_);
                    StandardUnitDTO suByPuId = getSUByPuId(puId_);
                    //商品门店校验
                    //门店校验
                    if(storeId==1L||storeId==2L){
                        if(!suByPuId.getStoreId().equals(storeId)){
                            return JsonResult.fail("您提交的商品中有商品已被管理员移除");
                        }
                    }else{
                        List<StandardUnitStoreDTO> standUnitStore = productFacade.findStandUnitStore(pu.getStandardUnitId(), storeId);
                        if(EmptyUtil.isEmpty(standUnitStore)){
                            return JsonResult.fail("您提交的商品中有商品已被管理员移除");
                        }
                    }

                    //校验会籍预售的权限
                    Map<Integer, String> integerStringMap = UserMembershipCheckUtils.checkUserMembershipAuthority(userId, suByPuId.getId(), suByPuId.getSaleWay(), platformId);
                    Set<Integer> integers = integerStringMap.keySet();
                    for (Integer i : integers) {
                        if (i == 0) {
                            return JsonResult.fail(integerStringMap.get(i));
                        }
                    }

                    if (pu == null)
                        return JsonResult.fail("商品不存在");
                    if (pu.getIsVendible() == 0) {
                        // pu不可销售
                        return JsonResult.fail("无该规格商品");
                    }
                    // 商品上架校验
                    if (pu.getStatus().intValue() != 3)
                        return JsonResult.fail("商品已下架");
                    String puImg = pu.getPuPicUrl();
                    if (EmptyUtil.isBlank(puImg)) {
                        puImg = productFacade.queryPuNullImgUrl(pu.getSkuId());
                    }
                    // 商品库存校验
                    Long stock = productFacade.queryStockByPUId(puId_);
                    if (stock.intValue() < num_)
                        return JsonResult.fail("商品库存不足");
                    if(storeId!=1L&&storeId!=2L){
                        StoreProductUnitDTO storeProductUnitDTO=storeFacade.findStorePuId(storeId,puId_);
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
                    item.setPlatformId(platformId);
                    item.setPrice(BigDecimal.valueOf(price_));
                    item.setPuCount(num_);
                    item.setSupplierId(suByPuId.getSupplierId());
                    item.setPuId(puId_);
                    // 一期暂时认为购物车商品不存在unit属性
                    item.setUnitExist(0);
                    item.setUserId(userId);
                    item.setPuPicUrl(puImg);
                    item.setPuName(pu.getName());
                    item.setMerchantId(pu.getMerchantId());
                    item.setExternalSkuId(pu.getExternalSkuId());

                    item.setSnapshot(JSON.toJSONString(pu));
                    item.setTaxRate(pu.getTaxRate());
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
                    lrro.setCreateUserid(userId);
                    lrro.setCreateUsername(userName);
                    lrro.setCreateUserMobile(user.getMobile());
                    lrro.setCompanyId(companyId);
                    lrro.setStoreId(storeId);
                    limitRuleRecordList.add(lrro);
                }


            }
        }
        if(EmptyUtil.isEmpty(limitRuleRecordList)){
            return JsonResult.fail("商品不能为空,限购规则不能为空");
        }
        // 根据pu商品集合及限购规则判断是否能购买、返回值不为空直接返回错误
        String isLimitBuy = productFacade.isLimitBuy(storeId, limitRuleRecordList, companyId, platformId, userId);
        if (isLimitBuy != null)
            return JsonResult.fail(isLimitBuy, BusinessExceptionConstant.LIMITBUYEXCEPTIONCODE);


        // 创建订单
        SoDTO sodto = new SoDTO();
        sodto.setUserId(userId);
        sodto.setOrderCode(orderCode);
        sodto.setPlatformId(platformId);
        sodto.setProductAmount(BigDecimal.valueOf(orderAmount));
        sodto.setReceiverAddressId(receiveAddressId);
        sodto.setDeliveryStatus(0);
        sodto.setInvoiceId(invoiceId);
        sodto.setOrderAmountPay(new BigDecimal(0));
        sodto.setOrderChannel(orderChannel);
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
        sodto.setStoreId(storeId);
        sodto.setOrderPromotionDiscount(new BigDecimal(0));
        sodto.setOrderStatus(0);
        sodto.setPaidOnlineThreshold(1);
        sodto.setPlatformId(platformId);
        sodto.setLimitFuBiPayAmount(limitFuBiPayAmount);
//        sodto.setRemark(remark);
        sodto.setUseFubi(useFubi);
        sodto.setCompanyId(user.getCompanyId());
        if (saleWay == 4 || saleWay == 6) {
            sodto.setSaleWay(7);
        } else {
            sodto.setSaleWay(saleWay);
        }
        List<CompanyConfigDTO> configs = companyConfig.queryCompanyconfigs(user.getCompanyId());
        if (orderAudit(user.getCompanyId(),configs)){
            //订单需要审核
            sodto.setAuditStatus(Constants.OrderAuditStatus.AUDITING);
        }
        // 判断是否有unit库存
        /*if (!unitFlag) {
            // 运费
            StoreDTO storeDTO = storeFacade.findStoreById(storeId);
            if(storeDTO.getIsDetail()==0){
                double freightAmount = productFacade.freightAmountByMerchantId(orderAmount, storeId, platformId, 1L);
                sodto.setOrderDeliveryFee(new BigDecimal(freightAmount));
            }else{
                sodto.setOrderDeliveryFee(new BigDecimal(0L));
            }
        } else {
//            sodto.setOrderAmount(new BigDecimal(orderAmount));
            sodto.setOrderDeliveryFee(new BigDecimal(0L));
        }*/
        BigDecimal totalDelivery = new BigDecimal(0L);
        Map<Long, BigDecimal> deliveryMap = new HashMap<>();
        if (deliveryPriceObj != null) {
            for (Entry<String, Object> deliveryObj : deliveryPriceObj.entrySet()){
                deliveryMap.put(Long.parseLong(deliveryObj.getKey()), new BigDecimal(deliveryObj.getValue().toString()));
                totalDelivery = totalDelivery.add(new BigDecimal(deliveryObj.getValue().toString()));
            }
        }
        if (isQingMei){
            sodto.setOrderDeliveryFee(qingMeiOrderDTO.getTotalShippingFee());
            sodto.setDeliveryFee(qingMeiOrderDTO.getTotalShippingFee());
            sodto.setOrderAmount(qingMeiOrderDTO.getTotalShippingFee().add(new BigDecimal(orderAmount)));
            sodto.setOrderAmountPay(qingMeiOrderDTO.getTotalAmount());
            sodto.setExt(qingMeiOrderDTO.getReturnUrl());
        }else {
            sodto.setOrderDeliveryFee(totalDelivery);
            sodto.setDeliveryFee(totalDelivery);
            sodto.setOrderAmount(totalDelivery.add(BigDecimal.valueOf(orderAmount)));
        }

        // 创建子订单
        List<SoChildDTO> soChildDTOList = new ArrayList<>();
        //7表示订单为预售订单.4,6表示商品是预售商品
        if ((saleWay == 6 || saleWay == 4||saleWay==7)&&EmptyUtil.isEmpty(puId)) {
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

                /*if (map.containsKey(preDay)) {
                    //2.如果出现重复预售期,就获取code放入soitem中
                    String childCode = map.get(preDay);
                    //查询出对应的soitem,将code赋值,在sochild插入后通过code获取id
                    Boolean flag = false;
                    for (SoItemDTO soItemDTO : soItems) {
                        if (soItemDTO.getPuId().equals(pId)) {
                            flag = true;
                            soItemDTO.setChildCode(childCode);
                        }
                    }
                    if (!flag) {
                        //soitem与cartitem是一一对应的,这个错误发生时一定是,cartitem的puid在soitem中不存在
                        throw new BusinessException("soItem数据有误");
                    }
                } else {
                    //3.如果新的预售期,创建子订单,将code放入cartitem中
                    //生成子订单code
                    n++;
                    String childCode = orderCode + "-" + n;
                    //将code放入到map中(为后面相同预售期的商品获取code)
                    map.put(preDay, childCode);

                    Boolean flag = false;
                    for (SoItemDTO soItemDTO : soItems) {
                        if (soItemDTO.getPuId().equals(pId)) {
                            soItemDTO.setChildCode(childCode);
                            flag = true;
                        }
                    }
                    if (!flag) {
                        throw new BusinessException("soItem数据有误");
                    }

                    //3.1生成子订单
                    SoChildDTO sc = new SoChildDTO();
                    sc.setChildCode(childCode);
                    sc.setPreSell(suByPuId.getPresellPeriod());//設置預售期
                    sc.setDeliverEndTime(DateUtils.nowToSomeDay(suByPuId.getPresellPeriod().intValue()));
                    soChildDTOList.add(sc);
                }*/
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
        }
        else if (isQingMei) {
            int n=0;
            Map<String,String> childCodeMap=new HashMap<>();
            BigDecimal totalDiscount=BigDecimal.ZERO;
            for(QingMeiChildOrderDTO qmChild:qingMeiOrderDTO.getTradeList()){
                n++;
                SoChildDTO sc = new SoChildDTO();
                String childCode = orderCode + "-" + n;
                sc.setChildCode(childCode);
                sc.setPerformingParty(ThirdConst.Merchant.QM);
                sc.setAmount(qmChild.getOrderAmount().subtract(qmChild.getShippingFee()));
                sc.setOrdinaryDeliveryFee(qmChild.getShippingFee());
                sc.setDeliveryFee(qmChild.getShippingFee());
                totalDiscount=totalDiscount.add(qmChild.getDiscountAmount());
                sc.setNeedCountDeliveryFee(1);
                sc.setThirdpatyDiscountAmount(qmChild.getDiscountAmount());
                sc.setThirdpartySoChildPayAmount(qmChild.getOrderAmount());
                sc.setThirdpartySoChildStatus(Integer.valueOf(1));
                sc.setThirdpartySoChildId(Long.valueOf(qmChild.getOrderNo()));
                sc.setThirdpartySoChildCode(qmChild.getThirdTradeNo());
                sc.setSource(ThirdConst.Source.QM);
                sc.setExt(qmChild.getOrderUrl());
                childCodeMap.put(qmChild.getOrderNo(), childCode);
                soChildDTOList.add(sc);
            }
            soItems.forEach(item->{
                if (childCodeMap.containsKey(item.getThirdChildCode())){
                    item.setChildCode(childCodeMap.get(item.getThirdChildCode()));
                }
            });
            sodto.setOrderPromotionDiscount(totalDiscount);
        } else {
        	/*List<Long> merchantIdList = new ArrayList<>();
        	for (SoItemDTO soItemDTO : soItems) {
        		if (!merchantIdList.contains(soItemDTO.getMerchantId())) {
        			merchantIdList.add(soItemDTO.getMerchantId());
        		}
        	}
        	Collections.sort(merchantIdList);
        	int n = 0;
        	Map<Long, String> childCodeMap = new HashMap<>();
        	for (Long mId : merchantIdList) {
        		n++;
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
        	for (SoItemDTO soItemDTO : soItems) {
        		soItemDTO.setChildCode(childCodeMap.get(soItemDTO.getMerchantId()));
        	}
        	if(saleWay == 6 || saleWay == 4||saleWay==7){
                StandardUnitDTO suByPuId = getSUByPuId(puId);
                soChildDTOList.get(0).setPreSell(suByPuId.getPresellPeriod());//設置預售期
                soChildDTOList.get(0).setDeliverEndTime(DateUtils.nowToSomeDay(suByPuId.getPresellPeriod().intValue()));
            }*/
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
            Map<String, String> CakeChildCodeMap = new HashMap<>();
            Map<String, String> worldChildCodeMap = new HashMap<>();
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
                        sc.setRemark(EmptyUtil.isEmpty(remarkObj) ? null : remarkObj.getString(String.valueOf(mId)));
                        sc.setOrdinaryDeliveryFee(deliveryPriceObj.getBigDecimal(String.valueOf(mId)));
                        sc.setNeedCountDeliveryFee(1);
                        selfChildCodeMap.put(sId, childCode);
                        soChildDTOList.add(sc);
                    }
                }else if(mId ==7L){
                    //蛋糕叔叔拆单
                    //获取到城市id
                    String cityName = addr.getGoodReceiverCity();
                    if(cityName.equals("市辖区")||cityName.equals("直辖市")){
                        cityName =addr.getGoodReceiverProvince();
                    }
                    String cityId = cakeAddressWriteManage.getCityId(cityName);
                    JSONObject userObject = cakeUtil.userLogin(null);
                    JsonResult checkUserRT = cakeUtil.checkResult(userObject);
                    if(Objects.nonNull(checkUserRT)){
                        logger.error("获取运费时登录三方用户发生失败{}",JSON.toJSONString(checkUserRT));
                        throw new BusinessException("蛋糕叔叔获取运费时登录三方用户失败");
                    }
                    JSONObject userData = userObject.getJSONObject(cakeUtil.DATA_KEY);
                    //获取到蛋糕叔叔的用户id
                    String cakeUserId = userData.getString("id");
                    //获取到地址id
                    CakeAddResultDTO dto = cakeAddressWriteManage.addOrEditCakeAddress(addr,cakeUserId);
                    if(dto == null){
                        throw new BusinessException("第三方新增地址失败");
                    }
                    List<SoItemDTO>  cakeItems = new ArrayList<>();
                    Map<String,Object> productTotalMap = new HashMap<>();
                    List<Map<String,Object>> mapList = new ArrayList<>();
                    for (SoItemDTO soItemDTO : soItems) {
                        if(soItemDTO.getMerchantId() ==7L){
                            //默认为0
                            soItemDTO.setRuleId("0");
                            cakeItems.add(soItemDTO);
                            Map<String,Object> productMap = new HashMap<>();
                            productMap.put("product_id",soItemDTO.getExternalProductId());
                            productMap.put("city_id",cityId);
                            mapList.add(productMap);
                        }
                    }
                    productTotalMap.put("product",mapList);
                    JSONObject jsonObject = cakeUtil.getRuleIds(productTotalMap);
                    JsonResult jsonResult = cakeUtil.checkResult(jsonObject);
                    if(Objects.nonNull(jsonResult)){
                        throw new BusinessException(jsonResult.getError());
                    }
                    JSONArray dataJsonArr = jsonObject.getJSONArray(cakeUtil.DATA_KEY);
                    Map<String,String> ruleMap = new HashMap<>();
                    List<String> ruleIds = new ArrayList<>();
                    if(dataJsonArr !=null && dataJsonArr.size()>0){
                        for (int i = 0; i < dataJsonArr.size(); i++) {
                            JSONObject dataJson = dataJsonArr.getJSONObject(i);
                            ruleMap.put(dataJson.getString("product_id"),dataJson.getString("distribution_rule_id"));
                            if(!ruleIds.contains(dataJson.getString("distribution_rule_id"))){
                                ruleIds.add(dataJson.getString("distribution_rule_id"));
                            }
                        }
                        for (SoItemDTO soItemDTO : soItems) {
                            if(soItemDTO.getMerchantId() ==7L){
                                //设置查询到的规则id
                                soItemDTO.setRuleId(ruleMap.get(soItemDTO.getExternalProductId()));
                            }
                        }
                    }else{
                        logger.info("订单号{}，蛋糕叔叔兼容规则id为0的情况",orderCode);
                        ruleIds.add("0");
                    }

                    Map<String,List<SoItemDTO>> ruleGroupMap = new HashMap<>();
                    for (SoItemDTO soItemDTO : soItems) {
                        if(soItemDTO.getMerchantId() ==7L){
                            if(ruleGroupMap.containsKey(soItemDTO.getRuleId()) || ruleGroupMap.get(soItemDTO.getRuleId()) == null){
                                ruleGroupMap.put(soItemDTO.getRuleId(),new ArrayList<>());
                            }
                            ruleGroupMap.get(soItemDTO.getRuleId()).add(soItemDTO);
                        }
                    }
                    logger.info("蛋糕叔叔订单号{}拆单规则ids:{}",orderCode,JSON.toJSONString(ruleIds));
                    for (String ruleId : ruleIds) {
                        SoChildDTO sc = new SoChildDTO();
                        String childCode = orderCode + "-" + n+"-C-"+ruleId;
                        sc.setChildCode(childCode);
                        sc.setPerformingParty(mId);
                        sc.setRemark(EmptyUtil.isEmpty(remarkObj) ? null : remarkObj.getString(String.valueOf(mId)));
                        sc.setOrdinaryDeliveryFee(BigDecimal.ZERO);
                        sc.setNeedCountDeliveryFee(1);
                        List<SoItemDTO> ruleItems =  ruleGroupMap.get(ruleId);
                        BigDecimal delivery_amount = BigDecimal.ZERO;
                        StringBuffer spceIds = new StringBuffer();
                        StringBuffer quantitys = new StringBuffer();
                        for (SoItemDTO itemDTO : ruleItems) {
                            spceIds.append(itemDTO.getPuId()).append(",");
                            quantitys.append(itemDTO.getPuCount()).append(",");
                        }
                        spceIds.deleteCharAt(spceIds.length() - 1);
                        quantitys.deleteCharAt(quantitys.length() - 1);
                        JSONObject jsonRuleObject = cakeUtil.getDistributionRules(cityId,dto.getId(),spceIds.toString(),quantitys.toString());
                        JsonResult jsonRuleResult = cakeUtil.checkResult(jsonRuleObject);
                        if(Objects.nonNull(jsonRuleResult)){
                            logger.info("获取蛋糕专卖三方（规则）运费结果"+JSON.toJSONString(jsonRuleObject));
                            throw new BusinessException("获取运费时第三方运费规则失败");
                        }
                        JSONObject data = jsonRuleObject.getJSONObject(cakeUtil.DATA_KEY);
                        if(data ==null){
                            logger.info("获取蛋糕专卖三方（规则）运费结果"+JSON.toJSONString(jsonRuleObject));
                            throw new BusinessException("获取运费时第三方运费规则结果失败");
                        }
                        JSONObject validate_same_row = null;
                        if("1".equals(data.getString("can_same"))){
                            try {
                                validate_same_row = data.getJSONObject("validate_same_row");
                            }catch (Exception e){
                                JSONArray jsonArray1 = data.getJSONArray("validate_same_row");
                                if(jsonArray1 !=null && jsonArray1.size()>0){
                                    validate_same_row = jsonArray1.getJSONObject(0);
                                }
                            }
                        }

                        if("1".equals(data.getString("can_same")) && validate_same_row !=null && validate_same_row.containsKey("delivery_amount") && validate_same_row.get("delivery_amount") !=null){
                            delivery_amount = delivery_amount.add(validate_same_row.getBigDecimal("delivery_amount"));
                        }else if(data.getString("can_ship").equals("1")){
                            JSONArray validate_delivery_datesArr = data.getJSONArray("validate_delivery_dates");
                            if(validate_delivery_datesArr !=null && validate_delivery_datesArr.size() >0){
                                JSONObject lastRecord = validate_delivery_datesArr.getJSONObject(0);
                                delivery_amount = delivery_amount.add(lastRecord.getBigDecimal("delivery_amount"));

                            }
                        }

                        sc.setOrdinaryDeliveryFee(delivery_amount);
                        sc.setDeliveryFee(delivery_amount);
                        CakeChildCodeMap.put(ruleId, childCode);
                        soChildDTOList.add(sc);
                    }

                }else if(mId ==8L){
                    //全球购拆单
                    List<String> ruleIdList = new ArrayList<>();
                    for (SoItemDTO soItemDTO : soItems) {
                        if (soItemDTO.getMerchantId() == 8L) {
                            soItemDTO.setRuleId("0");
                            if(EmptyUtil.isNotEmpty(soItemDTO.getSplitOrderRule())){
                                WroldSplitOrderRuleVO splitOrderRuleVO = JSONObject.parseObject(soItemDTO.getSplitOrderRule(),WroldSplitOrderRuleVO.class);
                                String ruleId =splitOrderRuleVO.getGoodsType()+""+splitOrderRuleVO.getStore_id()+""+splitOrderRuleVO.getStore_isCombineOrders();
                                //仓库不允许合并下单
                                if(!splitOrderRuleVO.getStore_isCombineOrders()){
                                    ruleId = ruleId+soItemDTO.getExternalProductId();
                                }
                                soItemDTO.setRuleId(ruleId);
                                if(!ruleIdList.contains(ruleId)){
                                    ruleIdList.add(ruleId);
                                }
                            }
                        }
                    }
                    //兼容没有拆单规则的情况
                    if(CollectionUtils.isEmpty(ruleIdList)){
                        ruleIdList.add("0");
                    }
                    logger.info("全球购拆单规则ids:{}",JSON.toJSONString(ruleIdList));
                    for (int i = 0; i < ruleIdList.size(); i++) {
                        String ruleId = ruleIdList.get(i);
                        SoChildDTO sc = new SoChildDTO();
                        String childCode = orderCode + "-" + n+ "-W-" +i;
                        sc.setChildCode(childCode);
                        sc.setPerformingParty(mId);
                        sc.setRemark(EmptyUtil.isEmpty(remarkObj) ? null : remarkObj.getString(String.valueOf(mId)));
                        //全球购都没运费
                        sc.setOrdinaryDeliveryFee(BigDecimal.ZERO);
                        sc.setDeliveryFee(BigDecimal.ZERO);
                        sc.setNeedCountDeliveryFee(1);
                        worldChildCodeMap.put(ruleId, childCode);
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
                if(soItemDTO.getMerchantId()==1l) {
                    soItemDTO.setChildCode(selfChildCodeMap.get(soItemDTO.getSupplierId()));
                }else if(soItemDTO.getMerchantId()==7L){
                    soItemDTO.setChildCode(CakeChildCodeMap.get(soItemDTO.getRuleId()));
                }else if(soItemDTO.getMerchantId()==8L){
                    soItemDTO.setChildCode(worldChildCodeMap.get(soItemDTO.getRuleId()));
                }else{
                    soItemDTO.setChildCode(childCodeMap.get(soItemDTO.getMerchantId()));
                }
            }
            if(saleWay == 6 || saleWay == 4||saleWay==7){
                StandardUnitDTO suByPuId = getSUByPuId(puId);
                soChildDTOList.get(0).setPreSell(suByPuId.getPresellPeriod());//設置預售期
                soChildDTOList.get(0).setDeliverEndTime(DateUtils.nowToSomeDay(suByPuId.getPresellPeriod().intValue()));
            }
            /*SoChildDTO sc = new SoChildDTO();
            String childCode = orderCode + "-1";
            sc.setChildCode(childCode);
            for (SoItemDTO soItemDTO : soItems) {
                soItemDTO.setChildCode(childCode);
            }
            if(saleWay == 6 || saleWay == 4||saleWay==7){
                StandardUnitDTO suByPuId = getSUByPuId(puId);
                sc.setPreSell(suByPuId.getPresellPeriod());//設置預售期
                sc.setDeliverEndTime(DateUtils.nowToSomeDay(suByPuId.getPresellPeriod().intValue()));
            }
            soChildDTOList.add(sc);*/
			/*sc.setAmount(sodto.getOrderAmount());
			sc.setDeliveryStatus(0);
			sc.setInvoiceId(invoiceId);
			sc.setPlatformId(platformId);
			sc.setRemark(remark);
		//这儿先不设置收件地址id,等该子订单的地址库创建完成后再赋值
		sc.setReceiverAddressId(receiveAddressId);
		// 判断子订单的第三方订单类型
		SoThirdpartyDTO soThirdpartyDTO = new SoThirdpartyDTO();
		if (commodityTemplateId != null && commodityTemplateId.equals(4L)) {

			// 话费充值
			// 1)设置子订单的第三方订单类型为话费充值
			sc.setThirdpartyType(Integer.valueOf(1));
			soThirdpartyDTO.setPhone(phone);
			soThirdpartyDTO.setThirdpartyStatus(Integer.valueOf(-1));
		} else {
			// 第三方订单类型: 0:无第三方订单 1:话费充值
			sc.setThirdpartyType(Integer.valueOf(0));
		}*/

        }

        //计算子订单的金额
        //设置子订单的付款金额(商品-优惠(此时为0))

        //设置子订单的公共信息
        List<SoThirdpartyDTO> soThirdpartyDTOList = new ArrayList<>();

        for (SoChildDTO soChildDTO : soChildDTOList) {
            SoThirdpartyDTO soThirdpartyDTO = new SoThirdpartyDTO();
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
            if(EmptyUtil.isNotEmpty(soChildDTO.getSource()) && (soChildDTO.getSource().equals(ThirdConst.Source.CAKE) || soChildDTO.getSource().equals(ThirdConst.Source.WORLD))){
                soChildDTO.setAmount(soChildAmount);
            }
            soChildDTO.setProductAmount(soChildAmount);//商品金额
            soChildDTO.setSkuInfoList(infoDTOList);
            soChildDTO.setDeliveryStatus(0);//设置物流状态为0,代发货
            soChildDTO.setInvoiceId(invoiceId);//设置发票id
            soChildDTO.setPlatformId(platformId);
//            soChildDTO.setRemark(remark);
            soChildDTO.setReceiverAddressId(receiveAddressId);
            // 获取第三方对接参数
            int thirdpartyAtt=0;
            Long merchantId=0L;
            boolean isThird =false;
            if(EmptyUtil.isNotEmpty(soChildDTO.getPerformingParty())){
                if(Objects.equals(soChildDTO.getPerformingParty(),ThirdConst.Merchant.WORLD) || Objects.equals(soChildDTO.getPerformingParty(),ThirdConst.Merchant.WORLD)){
                    isThird = true;
                }else if(EmptyUtil.isNotEmpty(source) && Objects.equals(source,ThirdConst.Source.CAKE) || Objects.equals(source,ThirdConst.Source.WORLD)){
                    isThird = true;
                }
            }else if(EmptyUtil.isNotEmpty(source) && Objects.equals(source,ThirdConst.Source.CAKE) || Objects.equals(source,ThirdConst.Source.WORLD)){
                isThird = true;
            }
            logger.info("isThird={}",isThird);

            if(EmptyUtil.isNotEmpty(puId) && !isThird){
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
                soThirdpartyDTO.setPhone(phone);
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
                soChildDTO.setThirdpartyType(Integer.valueOf(3));
                soThirdpartyDTO.setThirdpartyStatus(Integer.valueOf(-1));
                soThirdpartyDTO.setSoChildCode(soChildDTO.getChildCode());
            }
            else if(soChildDTO.getPerformingParty().equals(ThirdConst.Merchant.CAKE)){
                soChildDTO.setThirdpartyType(ThirdConst.ThirdPartyType.CAKE);
                soThirdpartyDTO.setThirdpartyStatus(Integer.valueOf(-1));
                soThirdpartyDTO.setSoChildCode(soChildDTO.getChildCode());
            }
            else if(soChildDTO.getPerformingParty().equals(ThirdConst.Merchant.WORLD)){
                soChildDTO.setThirdpartyType(ThirdConst.ThirdPartyType.WORLD);
                soThirdpartyDTO.setThirdpartyStatus(Integer.valueOf(-1));
                soThirdpartyDTO.setSoChildCode(soChildDTO.getChildCode());
            }else if(soChildDTO.getPerformingParty().equals(ThirdConst.Merchant.QM)){
                soChildDTO.setThirdpartyType(ThirdConst.ThirdPartyType.QM);
                soThirdpartyDTO.setThirdpartyStatus(Integer.valueOf(-1));
                soThirdpartyDTO.setSoChildCode(soChildDTO.getChildCode());
            }
            soThirdpartyDTOList.add(soThirdpartyDTO);
        }


        // 创建订单设备信息
        SoDeviceDTO sd = new SoDeviceDTO();
        sd.setDeviceId(deviceId);
        sd.setInterId(interfaceId);
        sd.setIp(ip);
        sd.setOs(os);
        sd.setPhoneModel(phoneModel);
        sd.setPlatformId(platformId);
        sd.setVersionCode(versionCode);
        /*
         * facade方法统一提交事务 (创建订单,创建子订单,创建订单项,
         * 创建订单设备信息,冻结相应商品库存,删除购物购物车,更新发票,创建第三方子订单,优惠卷相关)
         * 此时sodto中
         */
        //订单创建成功,生成原始快照,快照
        if (receiveAddressId != null) {
            Long addressId =null;
            Map<String, Long> address =null;
            for (SoChildDTO soChildDTO : soChildDTOList) {
                address = this.createSnapsAddress(addr, soChildDTO.getChildCode());
                soChildDTO.setReceiverAddressId(address.get("snapAddressId"));
            }
            sodto.setReceiverAddressId(address.get("originalSnapAddressId"));
        }
        Long orderId = soFacade.normalOrderCreate(req,jedisUtil,addr,orderPayByCash,exchangeId,exchangeCouponUnitId,exchangeCouponBatchId,sodto, soChildDTOList, sd, soItems, cartItemIds_, unitFlag, soThirdpartyDTOList, userId, userName,
                ip, mac, limitRuleRecordList, couponType, couponUnitId, companyId, deliveryMap,configs);


        /*//订单创建成功,生成原始快照,快照
        if (receiveAddressId != null) {
            for (SoChildDTO soChildDTO : soChildDTOList) {
                this.createSnapsAddress(addr, soChildDTO.getChildCode());
            }
        }*/
        if(orderId !=null && orderId.intValue() >0){
            pushOrderManage.pushOrderInfo(orderId,null,null);
        }



        // 返回值
        Map<String, Object> data = new HashMap<>();
        data.put("orderCode", orderCode);
        data.put("saleWay", saleWay);
        return JsonResult.success(data);
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
        item.setMerchantId(7L);
        item.setSource(4);
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


    private double getWorldOrderAmountAndAddItem(Long storeId, Long puId, Integer num, Long userId, Long platformId, String userName, Long companyId, ChannelProductDetailVO channelProductDetailVO, UserDTO user, List<SoItemDTO> soItems, List<LimitRuleRecordDTO> limitRuleRecordList, double orderAmount) {
        ChannelProductDTO channelProductDTO = channelProductDetailVO.getChannelProductDTO();
        List<ChannelProductBatchDTO> batchDTOList = channelProductDetailVO.getBatchDTOList();
        ChannelProductBatchDTO batchDTO = productFacade.getCurrBatch(puId+"",batchDTOList);
        List<ChannelProductSkuDTO> channelProductSkuDTOS = channelProductDetailVO.getSkuList();
        ChannelProductSkuDTO channelProductSkuDTO = productFacade.getCurrSkuInfo(batchDTO.getLinkSkuId(),channelProductSkuDTOS);
        BigDecimal taxRate = BigDecimal.ZERO;
        if(channelProductSkuDTO !=null && channelProductSkuDTO.getTaxRate() !=null){
//            taxRate = new BigDecimal(channelProductSkuDTO.getTaxRate()).setScale(2);
            taxRate = new BigDecimal(channelProductSkuDTO.getTaxRate()).multiply(new BigDecimal(100));
            taxRate =  taxRate.setScale(2);
        }
        WorldStoreDTO worldStoreDTO = null;
        if(EmptyUtil.isNotEmpty(channelProductSkuDTO.getStoreListText())){
            List<WorldStoreDTO> worldStoreDTOList = JSON.parseArray(channelProductSkuDTO.getStoreListText(), WorldStoreDTO.class);
            if(!CollectionUtils.isEmpty(worldStoreDTOList)){
                worldStoreDTO =worldStoreDTOList.get(0);
            }
        }

        WroldSplitOrderRuleVO splitOrderRuleVO = new WroldSplitOrderRuleVO();
        splitOrderRuleVO.setGoodsType(channelProductDTO.getGoodsType());
        if(Objects.nonNull(worldStoreDTO)){
            splitOrderRuleVO.setStore_code(worldStoreDTO.getStore_code());
            splitOrderRuleVO.setStore_id(worldStoreDTO.getStore_id());
            splitOrderRuleVO.setStore_isCombineOrders(Boolean.valueOf(worldStoreDTO.getStore_isCombineOrders()));
        }
        //校验结束
        SoItemDTO item = new SoItemDTO();
        item.setCartType(1);
        item.setPlatformId(platformId);
        item.setPrice(batchDTO.getPrice().setScale(2, RoundingMode.HALF_UP));
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
        item.setTaxRate(taxRate);
        item.setSplitOrderRule(JSON.toJSONString(splitOrderRuleVO));
        soItems.add(item);
        orderAmount += num * item.getPrice().doubleValue();


        // 限购规则记录
        LimitRuleRecordDTO lrro = new LimitRuleRecordDTO();
        lrro.setBuySum(Long.valueOf(num));
        lrro.setBuyMoneySum(item.getPrice().setScale(2, RoundingMode.HALF_UP));
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


    //创建订单规则校验

    //限购规则记录



    //根据spuid查询第三方对接参数值
    private Integer getThirdpartyAttValue(Long spuId){
        int i=soFacade.getThirdpartyAttValue(spuId);
        if(EmptyUtil.isEmpty(i)){
            throw new BusinessException("未查询到第三方对接参数");
        }
        return i;
    }


    private Long getStoreDiscount(Long storeId) {
        if (EmptyUtil.isEmpty(storeId)) {
            throw new BusinessException("storeId不能为空");
        }
        StoreDTO storeDTO = new StoreDTO();
        storeDTO.setId(storeId);

        StoreDTO storeById = storeFacade.findStoreById(storeId);
        if(EmptyUtil.isEmpty(storeById)){
            throw new BusinessException(storeById + "门店id无效");
        }
        //没有优惠
        if (EmptyUtil.isEmpty(storeById.getDiscount())) {
            StoreTreeNodeDTO dto = new StoreTreeNodeDTO();
            dto.setStoreId(storeId);
            List<StoreTreeNodeDTO> storeTreeNodeAll = storeFacade.findStoreTreeNodeAll(dto);
            if (storeTreeNodeAll.size() == 0 || storeTreeNodeAll == null) {
                throw new BusinessException(storeId + "是总门店,但没有折扣率,请联系管理员");
            } else if (storeTreeNodeAll.size() > 1) {
                throw new BusinessException(storeId + "同时属于多个门店,配置有误,联系管理元");
            } else {
                this.getStoreDiscount(storeTreeNodeAll.get(0).getParentId());
            }
        }
        return storeById.getDiscount();
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

    @Override
    public JsonResult<Map<String, Object>> cancelOrder(String orderCode, Long userId, Long platformId, String ip,
                                                       String userName, String mac, HttpServletRequest req) {



        // 校验订单是否可以取消
        if (EmptyUtil.isBlank(orderCode))
            return JsonResult.fail("请选择订单");
        SoDTO order = soFacade.querySoByOrderCode(orderCode);
        if (order == null)
            return JsonResult.fail("订单不存在");

        // 权限控制
        if (userId.longValue() != order.getUserId().longValue())
            return JsonResult.fail("订单不存在");

        int orderStatus = order.getOrderStatus().intValue();
        if (orderStatus != OrderConstant.ORDER_STATUS_UNPAY.getStatus()
                && orderStatus != OrderConstant.ORDER_STATUS_PAYED.getStatus()) {
            return JsonResult.fail("取消订单的订单状态必须为待支付或已付款");
        }

        List<SoChildDTO> soChildDTOList = soFacade.querySoChildListBySoId(order.getId());
        for (SoChildDTO soChildDTO : soChildDTOList) {
            if (soChildDTO.getThirdpartyType() == SoThirdpartyManageImpl.THIRDPARTY_TYPE_PHONE && orderStatus == 1)
                return JsonResult.fail("已付款的第三方话费充值订单不可取消");
            if (soChildDTO.getThirdpartyType() == SoThirdpartyManageImpl.THIRDPARTY_TYPE_JD){
                if (orderStatus == OrderConstant.ORDER_STATUS_PAYED.getStatus()){
                    return JsonResult.fail("申请取消订单请拨打电话:400-031-6808");
                }
            }
        }

        // 生成唯一退款单编号(取消已付款订单需要,取消待付款不需要)
        String soRefundCodeByCash = null;
        String soRefundCodeByFubi = null;
        String soRefundCodeByJiDian = null;
        String soRefundCodeByBuyCard = null;
        if (order.getOrderStatus() == OrderConstant.ORDER_STATUS_PAYED.getStatus()) {
            List<String> soRefundNOList = soFacade.genSoRefundNO();
            soRefundCodeByCash = soRefundNOList.get(0);
            soRefundCodeByFubi = soRefundNOList.get(1);
            soRefundCodeByJiDian = soRefundNOList.get(2);
            soRefundCodeByBuyCard = soRefundNOList.get(3);
        }

        // 取消订单逻辑
        soFacade.cancelOrderWithTx(order, ip, userId, userName, mac, soRefundCodeByFubi, soRefundCodeByCash,soRefundCodeByJiDian,soRefundCodeByBuyCard, req);
        if(order.getSaleWay()==8){
            //如果是以旧换新订单需要释放旧unit
            List<ExchangeOrderRecordDTO> exchangeOrderRecordByOrderCode = soFacade.findExchangeOrderRecordByOrderCode(orderCode);
            if(EmptyUtil.isEmpty(exchangeOrderRecordByOrderCode)||exchangeOrderRecordByOrderCode.size()>1){
                logger.info("当前订单对应的以旧换新记录有误,orderCode="+orderCode);
                throw new BusinessException("当前订单对应的以旧换新记录有误,请联系管理员");
            }
            jedisUtil.delLock(JedisUtil.COUPON_UNIT_LOCK_PRE+exchangeOrderRecordByOrderCode.get(0).getOldUnitCode());
            //soFacade.updateCouponUnitRemoveLock(exchangeOrderRecordByOrderCode.get(0).getOldUnitCode());
        }
        //变更订单取消状态
        //int j= soFacade.updateOrderCancelOverStatusByOrderCode(orderCode);
        return JsonResult.success();
    }

    @Override
    public PageResult<SoDetailVO> findOrderByUserAndStatus(Long userId, Integer orderStatus, Long platformId,
                                                           Long f, Long clientId, Pagination page) {
        return soFacade.findOrderByUserAndStatus(userId, orderStatus, platformId, f, clientId, page);
    }

    // @Override
    // public PageResult<SoListBackVo> backOrderList(Integer pageNo, Integer
    // pageSize, String orderCode,
    // String merchantName, Long startTime, Long endTime, Integer orderStatus,
    // Long platformId) {
    // if (pageNo == null)
    // pageNo = 1;
    // if (pageSize == null)
    // pageSize = 20;
    // if (StringUtil.isBlank(orderCode))
    // orderCode = null;
    // List<String> orderCodeList = null;
    // if (StringUtil.isBlank(merchantName)) {
    // merchantName = null;
    // } else {
    // // 查询所有名称类似于条件的商品
    // List<MerchantProductDTO> merchantList =
    // soFacade.queryMerchantListByName(merchantName, platformId);
    // if (merchantList.size() > 0) {
    // List<Long> merchantIdList = new ArrayList<>();
    // // 获得idlist
    // for (MerchantProductDTO m : merchantList) {
    // merchantIdList.add(m.getId());
    // }
    // // 根据这些id查询所有有关的订单项
    // List<SoItemDTO> itemList =
    // soFacade.querySoItemListByMerchantIds(merchantIdList, platformId);
    // if (itemList.size() > 0) {
    // // 获取订单编号列表,作为条件查询的条件之一
    // orderCodeList = new ArrayList<>();
    // for (SoItemDTO i : itemList) {
    // // orderCodeList.add(i.getOrderCode());
    // }
    // }
    // }
    // }
    // Pagination page = new Pagination(pageNo, pageSize);
    // Date startDate = null;
    // if (null != startTime)
    // startDate = new Date(startTime);
    // Date endDate = null;
    // if (null != endTime)
    // endDate = new Date(endTime);
    // List<SoDTO> dtoList = soFacade.querySoListByCondition(orderCodeList,
    // page, startDate, endDate, orderStatus,
    // orderCode, platformId);
    // int totalRow = soFacade.querySoCountByCondition(orderCodeList, startDate,
    // endDate, orderStatus, orderCode,
    // platformId);
    // PageResult<SoListBackVo> pageResult = new PageResult<>();
    // // List<SoListBackVo> result = SoConverter.S oDtoToBackVo(dtoList);
    // // pageResult.setList(result);
    // pageResult.setPageNo(pageNo);
    // pageResult.setPageSize(pageSize);
    // pageResult.setTotalSize(totalRow);
    // return pageResult;
    // }
    @Override
    public JsonResult<Map<String, Object>> soChildAllList(Integer merchantId, String soChildCode, String mail,
                                                          String puName, Date soCreateTimeStart, Date soCreateTimeEnd,
                                                          Integer soType, Integer soChildDeliveryStatus, Integer soConfirmStatus,
                                                          Date sendTimeStart, Date sendTimeEnd, Boolean showTest, Pagination page,
                                                          Long platformId,Long supplierId,Integer orderPayStatus,Integer auditStatus) {

        if (EmptyUtil.isBlank(soChildCode)) {
            soChildCode = null;
        }
        //1.根据mail查询出用户
        List<Long> userIds = null;
        if (EmptyUtil.isBlank(mail)) {
            mail = null;
            userIds = null;
        } else {
            // 根据Email模糊查询用户
            List<UserDTO> users = userFacade.queryUsersByEmail(mail);
            if (users.size() > 300) {
                return JsonResult.fail("匹配到的用户量太大,将会引发数据库异常,请使用更加精确的条件进行查询");
            }
            userIds = new ArrayList<>();
            for (UserDTO u : users) {
                userIds.add(u.getId());
            }
        }
        //2.根据puname查询出所有的puid
        List<Long> puIds = null;
        if (EmptyUtil.isBlank(puName)) {
            puName = null;
        } else {
            // 根据puName模糊查询商品
            List<CommodityProductUnitDTO> pus = productFacade.queryPuByName(puName);
            if (pus.size() > 300) {
                return JsonResult.fail("匹配到的商品量太大,将会引发数据库异常,请使用更加精确的条件进行查询");
            }
            puIds = new ArrayList<>();
            for (CommodityProductUnitDTO pu : pus) {
                puIds.add(pu.getId());
            }
        }

        // 屏蔽测试用户逻辑
        // 测试用户是公司id为1的用户
        // 在订单表中以公司id冗余形式体现
        if (showTest == null) {
            showTest = false;
        }
        // 查询前检查所有List条件是否为0长度
        if (userIds != null && userIds.size() == 0) {
            userIds = null;
        }
        if (puIds != null && puIds.size() == 0) {
            puIds = null;
        }

        List<Long> testCompanyIds = new ArrayList<>();
        if (showTest) {
            // 如果勾选展示测试数据
            CompanyDTO companyDto = new CompanyDTO();
            companyDto.setIsTest(Integer.valueOf(1));
            List<CompanyDTO> companyList = userFacade.findCompanyAll(companyDto);
            for (CompanyDTO companyDto_ : companyList) {
                testCompanyIds.add(companyDto_.getId());
            }
        }
        if (EmptyUtil.isEmpty(testCompanyIds)) {
            // 如果测试公司的id集合为空,则设置该id集合为null
            testCompanyIds = null;
        }
        PageResult<SoChildListBackVO> pageResult = new PageResult<>();
        PageResult<SoChildDTO> pr ;
        if(supplierId==null) {
            pr = soFacade.getSoChildAllList( merchantId,soChildCode, userIds, puIds, soCreateTimeStart,
                    soCreateTimeEnd, soType, soChildDeliveryStatus, soConfirmStatus, sendTimeStart, sendTimeEnd, showTest, platformId, testCompanyIds, page,orderPayStatus,auditStatus);
        }else {
            pr = soFacade.getSupplierSoChildAllList( merchantId,soChildCode, userIds, puIds, soCreateTimeStart,
                    soCreateTimeEnd, soType, soChildDeliveryStatus, soConfirmStatus, sendTimeStart, sendTimeEnd, showTest, platformId, testCompanyIds, page,supplierId,orderPayStatus,auditStatus);
        }

        List<SoChildDTO> soChildDTOList = pr.getList();
        if(EmptyUtil.isEmpty(soChildDTOList)){
            pageResult.setList(null);
            return JsonResult.success("page", pageResult);
        }

        //返回給前台的類
        List<SoChildListBackVO> soChildListBack = new ArrayList<>();
        for (SoChildDTO soChildDTO : soChildDTOList) {
            SoChildListBackVO vo = new SoChildListBackVO();
            vo.setId(soChildDTO.getId());
            if(RuntimeContext.cacheUser()!=null && RuntimeContext.cacheUser().getType()!=null &&RuntimeContext.cacheUser().getType().intValue()==1) {
                //若supplierId==null说明是平台用户
                vo.setSupplierId(soChildDTO.getSupplierId());
            }

            String[] childCodes = soChildDTO.getChildCode().split("-");
            vo.setSoCode(childCodes[0]+"-"+childCodes[1]+"-"+childCodes[2]+"-"+childCodes[3]);
            vo.setSoChildCode(soChildDTO.getChildCode());
            vo.setCreateTime(soChildDTO.getCreateTime());
            Date createTime = soChildDTO.getCreateTime();
            try {
                if(EmptyUtil.isNotEmpty(soChildDTO.getPreSell())){
                    Date date = addDate(createTime, soChildDTO.getPreSell());
                    vo.setSendTime(date);
                }

            } catch (ParseException e) {
                throw new BusinessException("[子订单列表查询时]时间转换异常");
            }
            vo.setSoChildDeliveryStatus(soChildDTO.getDeliveryStatus());

            //瓶装母订单的信息
            Long soId = soChildDTO.getSoId();
            SoDTO soDTO = new SoDTO();
            soDTO.setId(soId);
            SoDTO so = soFacade.findSoById(soDTO);
            Long storeId = so.getStoreId();
            BigDecimal deliveryFee = new BigDecimal(0);
            if (soChildDTO.getDeliveryFee() != null) {
                deliveryFee = soChildDTO.getDeliveryFee();
            }
            BigDecimal productAmount = new BigDecimal(0);
            if (soChildDTO.getProductAmount() != null) {
                productAmount = soChildDTO.getProductAmount();
            }
            vo.setAmountTotal(deliveryFee.add(productAmount).doubleValue());
            BigDecimal couponDiscount = new BigDecimal(0);
            if (soChildDTO.getCouponDiscount() != null) {
                couponDiscount = soChildDTO.getCouponDiscount();
            }
            BigDecimal storeDiscount = new BigDecimal(0);
            if (soChildDTO.getStoreDiscount() != null) {
                storeDiscount = soChildDTO.getStoreDiscount();
            }
            BigDecimal amount = new BigDecimal(0);
            if (soChildDTO.getAmount() != null) {
                amount = soChildDTO.getAmount();
            }
            BigDecimal deliveryFeeDiscount = new BigDecimal(0);
            if (soChildDTO.getDeliveryFeeDiscount() != null) {
                deliveryFeeDiscount = soChildDTO.getDeliveryFeeDiscount();
            }
            vo.setCouponeDiscount(couponDiscount.doubleValue());
            vo.setStoreDiscount(storeDiscount.doubleValue());
            vo.setAmount(amount.add(deliveryFee).subtract(deliveryFeeDiscount).doubleValue());
            vo.setDiscount(couponDiscount.add(storeDiscount).add(deliveryFeeDiscount).doubleValue());

            //拼装user信息
            Long userId = so.getUserId();
            UserDTO u = userFacade.findUserById(userId);
            UserExtendDTO ux = userFacade.findUserExtendById(userId);
            if(u !=null){
                vo.setMail(u.getMail());
                vo.setMobile(u.getMobile());
            }
            vo.setSoPayStatus(Objects.equals(soChildDTO.getCancelStatus(),1)?2:so.getOrderPayStatus());
            if(ux !=null){
                vo.setUserName(ux.getName());
            }
            vo.setSoConfirmStatus(so.getOrderConfirmStatus());
            vo.setSaleWay(so.getSaleWay());


            soChildListBack.add(vo);
        }

        pageResult.copy(pr);
        pageResult.setPlatformName(pr.getPlatformName());
        pageResult.setList(soChildListBack);
        return JsonResult.success("page", pageResult);
    }


    private static Date addDate(Date date, long day) throws ParseException {
        long time = date.getTime(); // 得到指定日期的毫秒数
        day = day * 24 * 60 * 60 * 1000; // 要加上的天数转换成毫秒数
        time += day; // 相加得到新的毫秒数
        return new Date(time); // 将毫秒数转换成日期
    }

    @Override
    public JsonResult<Map<String, Object>> orderList(OrderSearchVO searchVO) {
        PageResult<SoDTO> pr=queryBackStageSoPage(searchVO);
        Map<Long,String> companyNameMap=new HashMap<>();
        if (Objects.nonNull(searchVO.getCompanyNameMap())){
            companyNameMap=searchVO.getCompanyNameMap();
        }
        List<SoDTO> solist = pr.getList();
        List<SoListBackVo> soListBack = new ArrayList<>();
        for (SoDTO so : solist) {
            SoListBackVo vo = new SoListBackVo();
            vo.setId(so.getId());//设置订单id
            vo.setOrderCode(so.getOrderCode());//设置订单code
            vo.setUserId(so.getUserId());
            vo.setCreateTime(so.getCreateTime());
            vo.setOrderConfirmStatus(so.getOrderConfirmStatus());
            vo.setCompanyId(so.getCompanyId());
            if(companyNameMap.containsKey(so.getCompanyId())) {
                vo.setCompanyName(companyNameMap.get(so.getCompanyId()));
            }
            //查询母订单中商品的数量
            Long sum = soItemFacade.findPUNum(so.getId());
            vo.setPuNum(sum);//商品数量


            vo.setOrderPayStatus(so.getOrderPayStatus());
            //如果已支付查询支付时间
            if (so.getOrderPayStatus() != 0) {
                vo.setPayTime(so.getOrderPaymentConfirmDate());//付款时间
            }

            vo.setSoAmount(so.getOrderAmount());//总金额=商品+运费
            vo.setDiscount(so.getOrderPromotionDiscount());//总优惠
            vo.setAuditStatus(so.getAuditStatus());
            List<SoChildDTO> soChildDTOList = soFacade.findSoChildBySoId(so.getId());
            BigDecimal sum1 = new BigDecimal(0);
            for (SoChildDTO soChildDTO : soChildDTOList) {
                sum1 = sum1.add(soChildDTO.getAmount());
            }
           /* BigDecimal puAmount = vo.getSoAmount().subtract(so.getOrderDeliveryFee());
            SoItemDTO soItemDTO = new SoItemDTO();
            soItemDTO.setSoId(so.getId());
            List<SoItemDTO> soItemDTOList=soFacade.findSoItemListBySoId(soItemDTO);
            BigDecimal couponDiscount = BigDecimal.valueOf(0);
            if(EmptyUtil.isEmpty(soItemDTOList)){
                throw new BusinessException("订单项不存在");
            }
            for(SoItemDTO soItemDTO1:soItemDTOList){
                couponDiscount = couponDiscount.add(soItemDTO1.getFinalPromotionAver());
            }*/
            vo.setCouponDiscount(so.getCouponDiscount());//优惠卷优惠金额
            //门店优惠
            vo.setStoreDiscount(so.getStoreDiscount());
            vo.setPayAmount(so.getOrderAmountPay());

            // 订单总额
            vo.setOrderAmount(so.getOrderAmount());
            // 订单优惠金额
            vo.setOrderAmountBenefit(so.getOrderPromotionDiscount());
            // 订单结算
            vo.setOrderAmountPay(so.getOrderAmountPay());



            StoreDTO storeDTO = storeFacade.findStoreById(so.getStoreId());
            if(EmptyUtil.isEmpty(storeDTO)){
                return JsonResult.fail(so.getId() + "中的门店不存在");
            }
            vo.setIsDetail(storeDTO.getIsDetail());
            vo.setStoreName(storeDTO.getName());

            vo.setOrderPaidByCash(so.getOrderPaidByCash());
            vo.setOrderPaidByFubi(so.getOrderPaidByFubi());
            vo.setOrderCardPaid(so.getOrderCardPaid());
            vo.setOrderStatus(so.getOrderStatus());
            vo.setOrderStatusStr(OrderConstant.translate(so.getOrderStatus()));
            vo.setUseFubi(so.getUseFubi().intValue() == 1);
            vo.setCashPayType(so.getCashPayType());
            vo.setOrderChannel(so.getOrderChannel());
            vo.setSaleWay(so.getSaleWay());
            Long receiverAddressId = so.getReceiverAddressId();
            // 查询收获人地址信息
            ReceiverAddressDTO ra = receiverAddressFacade.findReceiverAddressById(receiverAddressId);
            if (ra != null) {
                vo.setMobile(ra.getGoodReceiverMobile());
            }
            UserDTO user = userFacade.queryUserById(so.getUserId());
            if (user != null) {
                vo.setMail(user.getMail());//设置邮箱
            }
            UserExtendDTO ue = userFacade.queryUserExtendById(so.getUserId());
            if (ue != null) {
                vo.setUserName(ue.getName());
            }
            soListBack.add(vo);
        }
        PageResult<SoListBackVo> pageResult = new PageResult<SoListBackVo>();
        pageResult.copy(pr);
        pageResult.setPlatformName(pr.getPlatformName());
        pageResult.setList(soListBack);
        return JsonResult.success("page", pageResult);
    }


    @Override
    public PageResult<SoDTO> queryBackStageSoPage(OrderSearchVO searchVO) {
        Pagination page = new Pagination(searchVO.getPageNo(), searchVO.getPageSize());
        if (EmptyUtil.isBlank(searchVO.getOrderCode())) {
            searchVO.setOrderCode(null);
        }
        List<Long> userIds = null;

        if (EmptyUtil.isNotEmpty(searchVO.getKeyWords())) {
            searchVO.setOrderCode(searchVO.getKeyWords());

        }
        if (EmptyUtil.isBlank(searchVO.getEmail())) {
            searchVO.setEmail(null);
            userIds = null;
        } else {
            // 根据Email模糊查询用户
            List<UserDTO> users = userFacade.queryUsersByEmail(searchVO.getEmail());
            if (users.size() > 300) {
                throw new BusinessException("匹配到的用户量太大,将会引发数据库异常,请使用更加精确的条件进行查询");
            }
            userIds = new ArrayList<>();
            for (UserDTO u : users) {
                userIds.add(u.getId());
            }
        }
        //新增根据收货人手机号查询
        if(EmptyUtil.isNotEmpty(searchVO.getGoodReceiverMobile()) && EmptyUtil.isNotBlank(searchVO.getGoodReceiverMobile())){
            List<Long> userIdList = receiverAddressFacade.getUserIdListByReceiverAddressMobile(searchVO.getGoodReceiverMobile());
            if(EmptyUtil.isNotEmpty(userIdList) && userIdList.size() >300){
                throw new BusinessException("匹配到的用户量太大,将会引发数据库异常,请使用更加精确的条件进行查询");
            }
            if(EmptyUtil.isNotEmpty(userIdList)){
                if(userIds == null || userIds.size() ==0){
                    userIds = userIdList;
                }else{
                    userIds.addAll(userIdList);
                }
            }
        }

        List<Long> puIds = null;
        if (EmptyUtil.isBlank(searchVO.getPuName())) {
            searchVO.setPuName(null);
        } else {
            // 根据puName模糊查询商品
            List<CommodityProductUnitDTO> pus = productFacade.queryPuByName(searchVO.getPuName());
            if (pus.size() > 300) {
                throw new BusinessException("匹配到的商品量太大,将会引发数据库异常,请使用更加精确的条件进行查询");
            }
            puIds = new ArrayList<>();
            for (CommodityProductUnitDTO pu : pus) {
                puIds.add(pu.getId());
            }
        }
        Date startDateTime = null;
        Date endDateTime = null;
        if (Objects.nonNull(searchVO.getStartTime()))
            startDateTime = new Date(searchVO.getStartTime());
        if (Objects.nonNull(searchVO.getEndTime()))
            endDateTime = new Date(searchVO.getEndTime());
        // 屏蔽测试用户逻辑
        // 测试用户是公司id为1的用户
        // 在订单表中以公司id冗余形式体现
        if (Objects.isNull(searchVO.getShowTest())) {
            searchVO.setShowTest(false);
        }
        // 查询前检查所有List条件是否为0长度
        if (userIds != null && userIds.size() == 0) {
            userIds = null;
        }
        if (puIds != null && puIds.size() == 0) {
            puIds = null;
        }

        List<Long> testCompanyIds = new ArrayList<>();
        if (!searchVO.getShowTest()) {
            // 如果勾选展示测试数据
            CompanyDTO companyDto = new CompanyDTO();
            companyDto.setIsTest(Integer.valueOf(1));
            List<CompanyDTO> companyList = userFacade.findCompanyAll(companyDto);
            for (CompanyDTO companyDto_ : companyList) {
                testCompanyIds.add(companyDto_.getId());
            }
        }
        if (EmptyUtil.isEmpty(testCompanyIds)) {
            // 如果测试公司的id集合为空,则设置该id集合为null
            testCompanyIds = null;
        }
        //storeId为空时查询platformid为2的所有订单
        List<Long> companyIds = null;
        //限制公司
        Map<Long,String> companyNameMap = new HashMap<Long,String>();
        searchVO.setCompanyNameMap(companyNameMap);
        if(Objects.nonNull(RuntimeContext.cacheUser()) && Objects.nonNull(RuntimeContext.cacheUser().getType())) {
            if(Objects.equals(4,RuntimeContext.cacheUser().getType())||Objects.equals(3, RuntimeContext.cacheUser().getType())) {
                companyIds = new ArrayList<Long>();
                companyIds.add(RuntimeContext.cacheUser().getCompanyId());
                CompanyDTO company = userFacade.queryCompanyById(RuntimeContext.cacheUser().getCompanyId());
                companyNameMap.put( RuntimeContext.cacheUser().getCompanyId(), company.getCompanyName());
                testCompanyIds = null;
            }else if(Objects.equals(2,RuntimeContext.cacheUser().getType())) {
                testCompanyIds = null;
                if(Objects.isNull(searchVO.getCompanyId())) {
                    CompanyDTO dto = new CompanyDTO();
                    dto.setEnterpriseId(RuntimeContext.cacheUser().getEnterpriseId());
                    List<CompanyDTO> companys = userFacade.findCompanyAll(dto);
                    if(EmptyUtil.isNotEmpty(companys)) {
                        companyIds = new ArrayList<Long>();
                        for(CompanyDTO co : companys) {
                            companyNameMap.put(co.getId(), co.getCompanyName());
                            if(Objects.nonNull(co.getId())) {
                                if(!companyIds.contains(co.getId())) {
                                    companyIds.add(co.getId());
                                }
                            }

                        }
                    }else {
                        companyIds.add(-11l);
                    }
                }else {
                    CompanyDTO dto = new CompanyDTO();
                    dto.setEnterpriseId(RuntimeContext.cacheUser().getEnterpriseId());
                    List<CompanyDTO> companys = userFacade.findCompanyAll(dto);
                    companyIds = new ArrayList<Long>();
                    if(EmptyUtil.isNotEmpty(companys)) {
                        for(CompanyDTO co : companys) {
                            if(co.getId().equals(searchVO.getCompanyId())) {
                                if(!companyIds.contains(co.getId())) {
                                    companyIds.add(co.getId());
                                }
                            }
                        }
                        if(companyIds.size()==0) {
                            companyIds.add(-11l);
                        }
                    }else {
                        companyIds.add(-11l);
                    }
                }


            }else {
                testCompanyIds = null;
                companyIds = null;
                if (EmptyUtil.isNotEmpty(searchVO.getCompanyId())){
                    companyIds =new ArrayList<>();
                    companyIds.add(searchVO.getCompanyId());
                }
            }
        }else {
            return null;
        }
        return soFacade.queryBackStageSoPage(searchVO.getStoreId(),searchVO.getOrderCode(),userIds,puIds,
                startDateTime,endDateTime,searchVO.getStatus(),searchVO.getOrderConfirmStatus(),searchVO.getOrderPayStatus(),
                searchVO.getPaymentType(),searchVO.getShowTest(),searchVO.getPlatformId(),page,searchVO.isRefundFlag(),
                testCompanyIds,companyIds,searchVO.getAuditStatus(),searchVO.getSoIds());
    }


    //抽取的方法:根据puid查询su
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

    private List<OrderResult> constructOrderResult(Long companyId,Long storeId,List<SoChildDTO> soChildDTOS) {
        CompanyDTO companyDTO=soFacade.findCompanyById(companyId);
        if(EmptyUtil.isEmpty(companyDTO)){
            throw new BusinessException("所属公司不存在");
        }
        List<OrderResult> orderResultList = new ArrayList<>();
        for (SoChildDTO soChildDTO:soChildDTOS){
            OrderResult orderResult =buyProductByChild(soChildDTO,soChildDTO.getPerformingParty(),storeId);
            orderResultList.add(orderResult);
        }
        return orderResultList;
    }

    private List<OrderResult> coustructOrderResult(ParseAddressJson parseAddressJson, String address, Long clientId, Long storeId, Integer type, String cartItemIds, Long puId, Integer num,
                                                   Long addrId, Long memberId, Long platformId, Long companyId, String phone, Integer couponType,
                                                   Long couponUnitId, Long commodityTemplateId,String channelProductId,Integer source) {
        CompanyDTO companyDTO=soFacade.findCompanyById(companyId);
        if(EmptyUtil.isEmpty(companyDTO)){
            throw new BusinessException("所属公司不存在");

        }
        List<OrderResult> orderResultList = new ArrayList<>();
        if (type.intValue() == 0) {
            OrderResult orderResult = buyPrductByRightNow(parseAddressJson,address,clientId,companyDTO.getCompanyType(),puId, num, couponType,
                    couponUnitId, commodityTemplateId,
                    phone, storeId,channelProductId,source);
            orderResultList.add(orderResult);
        } else {
            List<Long> cartItemIdList = JSONArray.parseArray(cartItemIds, Long.class);
            if (EmptyUtil.isEmpty(cartItemIdList)) {
                throw new BusinessException("已勾选的购物车项为空");
            }
            Map<Long, List<CartItemDTO>> merchantCartMap = new HashMap<>();
            for (Long cartItemId : cartItemIdList) {
                CartItemDTO cartItemDTO = cartItemFacade.findCartItemById(cartItemId);
                if (cartItemDTO == null) {
                    throw new BusinessException("购物车项不存在");
                }
                Long merchantId = cartItemDTO.getMerchantId();
                if (merchantCartMap.containsKey(merchantId)) {
                    merchantCartMap.get(merchantId).add(cartItemDTO);
                } else {
                    List<CartItemDTO> cartItemList = new ArrayList<>();
                    cartItemList.add(cartItemDTO);
                    //购物车按照商户来分类
                    merchantCartMap.put(merchantId, cartItemList);
                }
            }
            for (Entry<Long, List<CartItemDTO>> entry : merchantCartMap.entrySet()) {
                OrderResult orderResult = buyProductByCart(parseAddressJson,address,clientId,companyDTO.getCompanyType(),entry.getValue(), puId, memberId, platformId,storeId, entry.getKey());
                orderResultList.add(orderResult);
            }
        }
        return orderResultList;
    }

    //展示商品以及结算信息
    @Override
    public JsonResult<Map<String, Object>> orderConfirm(Long storeId, Integer type, String cartItemIds, Long puId, Integer num,
                                                        Long addrId, Long memberId, Long platformId, Long companyId, String phone, Integer couponType,
                                                        Long couponUnitId,Long clientId,String channelProductId,Integer source) {
        Long time1 = System.currentTimeMillis();
        if (type == null) {
            return JsonResult.fail("未知下单类型");
        }

        List<CompanyConfigDTO> configs =  userReadService.findUserCompanyConfigs(memberId);
        UserDTO userDTO =  userReadService.findUserByID(memberId);
        boolean isDlfUser = false;
        if(userDTO !=null && EmptyUtil.isNotEmpty(userDTO.getChannelSource()) && Objects.equals(userDTO.getChannelSource(),UserChannelSourceEnum.DLF.getChannelSource())){
            isDlfUser = true;
        }
        boolean hasExistsWorldBuy = false;
        boolean validFubiAcc = true;
        boolean validFdAcc = true;
        boolean payFuBiOnly=false;
        for(CompanyConfigDTO config : configs) {
            if(config.getKey().equalsIgnoreCase("account.0.valid")&& config.getValue()!=null && config.getValue().length()==1) {
                validFubiAcc = (Integer.valueOf(config.getValue()).intValue()==0)?false:true;
            }

            if(config.getKey().equalsIgnoreCase("account.4.valid")&& config.getValue()!=null && config.getValue().length()==1) {
                validFdAcc = (Integer.valueOf(config.getValue()).intValue()==0)?false:true;
            }
            if(config.getKey().equalsIgnoreCase("pay.fubi.only")&& config.getValue()!=null && config.getValue().length()==1) {
                payFuBiOnly = (Integer.valueOf(config.getValue()).intValue()==1)?true:false;
            }
        }

        if (type.intValue() == 0) {
            //校验直接购买的商品是否为会员预售,会员是否有权限
            try {
                StandardUnitDTO suByPuId = getSUByPuId(puId);
                Map<Integer, String> integerStringMap = UserMembershipCheckUtils.checkUserMembershipAuthority(memberId, suByPuId.getId(), suByPuId.getSaleWay(), platformId);
                Set<Integer> integers = integerStringMap.keySet();
                for (Integer i : integers) {
                    if (i == 0) {
                        return JsonResult.fail(integerStringMap.get(i));
                    }
                }
            } catch (Exception e) {
                // TODO: handle exception
            }


        }

        // 通过puid查询sku,然后查到spu信息
        DefaultReceiverInfoVo receiverInfo = defaultReceiverAddress(memberId, addrId, platformId);
        if(EmptyUtil.isEmpty(receiverInfo)){
            receiverInfo = new DefaultReceiverInfoVo();
        }
        //String token = jdUtils.getAccessToken(jedisUtil);
        String token = jdUtils2.getAccessToken(jedisUtil);
        ParseAddressJson parseAddressJson = getDeliveryPriceFromJd(token,receiverInfo.getAddress());
        String errMess=null;
        if(EmptyUtil.isEmpty(parseAddressJson)){
            errMess = "抱歉，因为网络问题，运费计算有误,请以订单最终收取的运费为准";
        }
        Long commodityTemplateId = 2l;
        if(puId!=null) {
            commodityTemplateId = productFacade.queryCommodityTemplateIdByPuId(puId);
        }
        if(commodityTemplateId==null) {
            commodityTemplateId = 2l;
        }
        Long time2 = System.currentTimeMillis();
        logger.info("时间2:"+(time2-time1));
        List<OrderResult> orderResultList = coustructOrderResult(parseAddressJson,receiverInfo.getAddress(),clientId,storeId, type, cartItemIds, puId, num, addrId, memberId, platformId, companyId, phone, couponType, couponUnitId, commodityTemplateId,channelProductId,source);
        Long time3 = System.currentTimeMillis();
        logger.info("时间3:"+(time3-time2));
        List<Map<String, Object>> goodGroupList = new ArrayList<>();
        BigDecimal orderAmountTotal = BigDecimal.ZERO;
        BigDecimal orderAmountOnlyCashTotal = BigDecimal.valueOf(0);//仅支持现金购买的商品金额
        int goodsAccount = 0;
        double totalDeliveryPrice = 0;
        List<OrderConfirmGoodsDTO> goodsList = new ArrayList<>();
        List<OrderConfirmGoodsDTO> unBuyGoodsList = new ArrayList<>();
        Map<Long, BigDecimal> deliveryPriceMap = new HashMap<>();
        List<LimitRuleRecordDTO> limitRuleRecordList = new ArrayList<>();
        logger.info("校验限购记录="+orderResultList.size());
        for(OrderResult orderResult : orderResultList){
            if(!orderResult.isSuccess()){
                return JsonResult.fail(orderResult.getError());
            }
            if(EmptyUtil.isNotEmpty(orderResult.getLimitRuleRecordList())){
                limitRuleRecordList.addAll(orderResult.getLimitRuleRecordList());
            }
        }
        // 根据pu商品集合及限购规则判断是否能购买、返回值不为空直接返回错误
        if(EmptyUtil.isNotEmpty(limitRuleRecordList)){
            String isLimitBuy = productFacade.isLimitBuy(storeId,limitRuleRecordList, companyId, platformId, memberId);
            logger.info("限购结果:"+isLimitBuy);
            if (isLimitBuy != null) {
                return JsonResult.fail(isLimitBuy, BusinessExceptionConstant.LIMITBUYEXCEPTIONCODE);
            }
        }

        Long time5 = System.currentTimeMillis();
        logger.info("时间5:"+(time5-time3));
        BigDecimal payByFuBiAmount = BigDecimal.ZERO;
        for (OrderResult orderResult : orderResultList) {
            payByFuBiAmount = payByFuBiAmount.add(orderResult.getPayByFuBiAmount());
            if(!orderResult.isSuccess()){
                return JsonResult.fail(orderResult.getError());
            }
        	/*// 根据pu商品集合及限购规则判断是否能购买、返回值不为空直接返回错误
            if(EmptyUtil.isEmpty(orderResult.getLimitRuleRecordList())){
                return JsonResult.fail("商品为空,导致限购规则为空");
            }
            String isLimitBuy = productFacade.isLimitBuy(storeId,orderResult.getLimitRuleRecordList(), companyId, platformId, memberId);
            logger.info("限购结果:"+isLimitBuy);
            if (isLimitBuy != null) {
                return JsonResult.fail(isLimitBuy, BusinessExceptionConstant.LIMITBUYEXCEPTIONCODE);
            }*/
            Map<String, Object> goodGroup = new HashMap<>();
            //orderAmountTotal += orderResult.getOrderAmount().doubleValue();
            orderAmountTotal =orderAmountTotal.add(orderResult.getOrderAmount());

            if(EmptyUtil.isEmpty(orderResult.getOrderAmountOnlyCash())){
                orderResult.setOrderAmountOnlyCash(BigDecimal.ZERO);
            }
            orderAmountOnlyCashTotal = orderAmountOnlyCashTotal.add(orderResult.getOrderAmountOnlyCash());
            goodsAccount += orderResult.getGoodsAccount();
            goodGroup.put("goodsList", orderResult.getGoodsList());
            goodsList.addAll(orderResult.getGoodsList());
            unBuyGoodsList.addAll(orderResult.getJdDownGoods());
            logger.info("receiverInfo打印:{}",JSON.toJSONString(receiverInfo));
            logger.info("orderResult打印:{}",JSON.toJSONString(orderResult));
            if (EmptyUtil.isNotEmpty(receiverInfo.getAddress())&&EmptyUtil.isNotEmpty(orderResult.getGoodsList())&&!orderResult.getGoodsList().get(0).isCard()
                    && !orderResult.getStore().getIsDetail().equals(1L)
                    && (couponType == null || couponType != 1)) {
                if(orderResult.getMerchant().getId()==6L){
                    //如果是京东运营方则通过接口调用获取京东的运费
                    List<OrderConfirmGoodsDTO> goodsListDto = orderResult.getGoodsList();
                    List<SkuInfo> skuInfoList = new ArrayList<>();
                    for(OrderConfirmGoodsDTO goodsDTO:goodsListDto){
                        SkuInfo skuInfo = new SkuInfo();
                        String skuId=goodsDTO.getExternalSkuId();
                        Integer skuNum = goodsDTO.getNum();
                        skuInfo.setNum(skuNum);
                        skuInfo.setSkuId(skuId);
                        skuInfoList.add(skuInfo);
                    }


                    Map<String, String> deliveryPriceFromJd =null;
                    if(EmptyUtil.isNotEmpty(parseAddressJson)){

                        deliveryPriceFromJd=buildDeliveryPrice(token,skuInfoList,parseAddressJson);
                    }
                    //获取运费成功
                    if(EmptyUtil.isNotEmpty(deliveryPriceFromJd)&&("1").equals(deliveryPriceFromJd.get("success"))){
                        goodGroup.put("deliveryPrice", Double.valueOf(deliveryPriceFromJd.get("deliveryPrice")));
                        deliveryPriceMap.put(orderResult.getMerchant().getId(), BigDecimal.valueOf(Double.valueOf(deliveryPriceFromJd.get("deliveryPrice"))));
                        orderResult.setNeedCountDelivery(true);
                        totalDeliveryPrice += Double.valueOf(deliveryPriceFromJd.get("deliveryPrice"));


                        if(EmptyUtil.isNotEmpty(deliveryPriceFromJd.get("deliveryPrice"))&&Double.valueOf(deliveryPriceFromJd.get("deliveryPrice"))>0){
                            goodGroup.put("deliveryMethod", 1);
                        }else{
                            goodGroup.put("deliveryMethod", 0);
                        }
                    }else{
                        deliveryPriceMap.put(orderResult.getMerchant().getId(), BigDecimal.ZERO);
                        //获取运费失败
                        goodGroup.put("jdDeliveryError", errMess);
                    }
                    logger.info("获取京东商品运费最终结论:{},运费金额:{}",JSON.toJSONString(goodGroup),JSON.toJSONString(deliveryPriceMap));

                }else if(orderResult.getMerchant().getId()==7L){
                    //如果是蛋糕叔叔运营方则通过接口调用获取蛋糕叔叔的运费
                    List<OrderConfirmGoodsDTO> goodsListDto = orderResult.getGoodsList();
                    List<SkuInfo> skuInfoList = new ArrayList<>();
                    List<ChannelSkuInfoVO> channelSkuInfoVOList = new ArrayList<>();
                    for(OrderConfirmGoodsDTO goodsDTO:goodsListDto){
                        SkuInfo skuInfo = new SkuInfo();
                        String skuId=goodsDTO.getExternalSkuId();
                        Integer skuNum = goodsDTO.getNum();
                        skuInfo.setNum(skuNum);
                        skuInfo.setSkuId(skuId);
                        skuInfoList.add(skuInfo);

                        ChannelSkuInfoVO channelSkuInfoVO = new ChannelSkuInfoVO();
                        channelSkuInfoVO.setProductId(goodsDTO.getExternalProductId());
                        channelSkuInfoVO.setSkuId(goodsDTO.getExternalSkuId());
                        channelSkuInfoVO.setNum(skuNum);
                        channelSkuInfoVO.setDistribution_rule_id(goodsDTO.getDistribution_rule_id());
                        channelSkuInfoVOList.add(channelSkuInfoVO);
                    }
                    Map<String, String> deliveryPriceFromCake =null;
                    if(EmptyUtil.isNotEmpty(receiverInfo)){

                        deliveryPriceFromCake=buildDeliveryPriceFromCake(channelSkuInfoVOList,receiverInfo);
                    }
                    //获取运费成功
                    if(EmptyUtil.isNotEmpty(deliveryPriceFromCake)&&("1").equals(deliveryPriceFromCake.get("success"))){
                        goodGroup.put("deliveryPrice", Double.valueOf(deliveryPriceFromCake.get("deliveryPrice")));
                        deliveryPriceMap.put(orderResult.getMerchant().getId(), BigDecimal.valueOf(Double.valueOf(deliveryPriceFromCake.get("deliveryPrice"))));
                        orderResult.setNeedCountDelivery(true);
                        totalDeliveryPrice += Double.valueOf(deliveryPriceFromCake.get("deliveryPrice"));


                        if(EmptyUtil.isNotEmpty(deliveryPriceFromCake.get("deliveryPrice"))&&Double.valueOf(deliveryPriceFromCake.get("deliveryPrice"))>0){
                            goodGroup.put("deliveryMethod", 1);
                        }else{
                            goodGroup.put("deliveryMethod", 0);
                        }
                    }else{
                        deliveryPriceMap.put(orderResult.getMerchant().getId(), BigDecimal.ZERO);
                        //获取运费失败
                        goodGroup.put("jdDeliveryError", errMess);
                    }
                }else if(orderResult.getMerchant().getId()==8L){
                    hasExistsWorldBuy =true;
                    //如果是全球购运营方则通过接口调用获取全球购的运费
                    List<OrderConfirmGoodsDTO> goodsListDto = orderResult.getGoodsList();
                    List<SkuInfo> skuInfoList = new ArrayList<>();
                    for(OrderConfirmGoodsDTO goodsDTO:goodsListDto){
                        SkuInfo skuInfo = new SkuInfo();
                        String skuId=goodsDTO.getExternalSkuId();
                        Integer skuNum = goodsDTO.getNum();
                        skuInfo.setNum(skuNum);
                        skuInfo.setSkuId(skuId);
                        skuInfoList.add(skuInfo);
                    }
                    Map<String, String> deliveryPriceFromWorld =null;
                    if(EmptyUtil.isNotEmpty(receiverInfo)){

                        deliveryPriceFromWorld=buildDeliveryPriceFromWorld(skuInfoList,receiverInfo);
                    }
                    //获取运费成功
                    if(EmptyUtil.isNotEmpty(deliveryPriceFromWorld)&&("1").equals(deliveryPriceFromWorld.get("success"))){
                        goodGroup.put("deliveryPrice", Double.valueOf(deliveryPriceFromWorld.get("deliveryPrice")));
                        deliveryPriceMap.put(orderResult.getMerchant().getId(), BigDecimal.valueOf(Double.valueOf(deliveryPriceFromWorld.get("deliveryPrice"))));
                        orderResult.setNeedCountDelivery(true);
                        totalDeliveryPrice += Double.valueOf(deliveryPriceFromWorld.get("deliveryPrice"));


                        if(EmptyUtil.isNotEmpty(deliveryPriceFromWorld.get("deliveryPrice"))&&Double.valueOf(deliveryPriceFromWorld.get("deliveryPrice"))>0){
                            goodGroup.put("deliveryMethod", 1);
                        }else{
                            goodGroup.put("deliveryMethod", 0);
                        }
                    }else{
                        deliveryPriceMap.put(orderResult.getMerchant().getId(), BigDecimal.ZERO);
                        //获取运费失败
                        goodGroup.put("jdDeliveryError", errMess);
                    }
                }else{
                    //判断不是零售门店且不是电子卡券商品，且不是通过兑换券直接购买，计算运费
                    //计算门店折扣后的金额，再来计算运费
                    BigDecimal dicount=BigDecimal.valueOf(promotionFacade.getDiscount(storeId,platformId)).divide(BigDecimal.valueOf(100));//折扣率*100
                    //门店折扣后金额
                    BigDecimal afterStoreDiscount = (orderResult.getOrderAmount().multiply(dicount)).setScale(2,BigDecimal.ROUND_DOWN);
                    double freightAmount = productFacade.freightAmountByMerchantId(afterStoreDiscount.doubleValue(), storeId, platformId, orderResult.getMerchant().getId());
                    goodGroup.put("deliveryPrice", freightAmount);
                    deliveryPriceMap.put(orderResult.getMerchant().getId(), BigDecimal.valueOf(freightAmount));
                    orderResult.setNeedCountDelivery(true);
                    totalDeliveryPrice += freightAmount;
                    if (freightAmount > 0) {
                        // 运费大于0 配送方式不包邮
                        goodGroup.put("deliveryMethod", 1);
                    } else {
                        goodGroup.put("deliveryMethod", 0);
                    }
                }

            } else {
                goodGroup.put("deliveryMethod", 0);
                goodGroup.put("deliveryPrice", 0);
                deliveryPriceMap.put(orderResult.getMerchant().getId(), BigDecimal.valueOf(0));
                orderResult.setNeedCountDelivery(false);
            }
            goodGroup.put("merchantId", orderResult.getMerchant().getId());
            goodGroup.put("merchantName", orderResult.getMerchant().getName());
            goodGroup.put("storeName", orderResult.getStore().getName());
            goodGroup.put("isOwnMerchant", orderResult.getMerchant().getId().equals(1L) ? 1 : 0);//是否自营
            goodGroupList.add(goodGroup);
        }

        // 返回用户默认收货地址
        Map<String, Object> data = new HashMap<>();
        boolean isFuBi = true;
        for (OrderResult orderResult : orderResultList) {
            if(orderResult.getIsPayByFuBiOnly()==0){
                isFuBi = false;
            }
        }
        if (payFuBiOnly){
            isFuBi=payFuBiOnly;
        }
        if(isFuBi){
            data.put("isPayByFuBiOnly",1);
        }else{
            data.put("isPayByFuBiOnly",0);

        }
        data.put("receiverInfo", receiverInfo);
        data.put("goodGroupList", goodGroupList);
        data.put("unBuyGoodsList",unBuyGoodsList );
        // 默认发票信息
        data.put("invoiceType", 0);
        // 商品数量
        data.put("goodsAccount", goodsAccount);
        data.put("totalDeliveryPrice", totalDeliveryPrice);
        //判断是否仅现金支付
        Integer isPayByCashOnly=Integer.valueOf(1);
        for(OrderConfirmGoodsDTO dto:goodsList){
            if(dto.getBuyType()!=2){
                isPayByCashOnly=Integer.valueOf(0);
            }
        }
        data.put("isPayByCashOnly",isPayByCashOnly);
        //根据storeId判断当前门店是不是零售门店
        StoreDTO storeById1 = storeFacade.findStoreById(storeId);
        if (storeById1==null) {
            return JsonResult.fail(storeId + "不存在");
        }
        data.put("isDetailStore", storeById1.getIsDetail());
        data.put("orderAmount", orderAmountTotal);

        //计算用户可用的积分金额
        // 可达积分余额
        BigDecimal availableFbBalance = new BigDecimal(0);
        BigDecimal availableJiDianBalance = new BigDecimal(0);
        if (platformId == 7) {
            // 用户可用积分数量:积分余额-已冻结积分
            // 积分账户
            UserAccountDTO fubiAcc = accountFacade.queryUserAccountByUserId(memberId, 0);
            UserAccountDTO fubiFdAcc = accountFacade.queryUserAccountByUserId(memberId, 4);
            UserAccountDTO jidianAcc = accountFacade.queryUserAccountByUserId(memberId, 5);
            if(jidianAcc !=null && jidianAcc.getBalance() !=null){
                availableJiDianBalance = jidianAcc.getBalance();
            }
            if (fubiAcc != null ) {
                if(validFubiAcc) {
                    // 账户加密值校验
                    SaltDTO salt = accountFacade.querySaltByUUID(fubiAcc.getUuid());
                    if (salt == null)
                        return JsonResult.fail("积分账户加密数据未就绪");
                    boolean cipherValid = MD5Util.md5Valid(fubiAcc.getBalance().toString(), salt.getSaltValue(),
                            fubiAcc.getCiphertext());
                    if (SpringContextTool.isPrd() && !cipherValid)
                        return JsonResult.fail("积分账户已被冻结");
                }
            } else {
                return JsonResult.fail("积分账户不存在,请联系管理员");
            }

            if (fubiFdAcc != null ) {
                if(validFdAcc) {

                    // 账户加密值校验
                    SaltDTO salt = accountFacade.querySaltByUUID(fubiFdAcc.getUuid());
                    if (salt == null)
                        return JsonResult.fail("积分账户加密数据未就绪");
                    boolean cipherValid = MD5Util.md5Valid(fubiFdAcc.getBalance().toString(), salt.getSaltValue(),
                            fubiFdAcc.getCiphertext());
                    if (SpringContextTool.isPrd() && !cipherValid)
                        return JsonResult.fail("积分账户已被冻结");// 账户加密值校验
                }
                /*SaltDTO salt = accountFacade.querySaltByUUID(fubiFdAcc.getUuid());
                if (salt == null)
                    return JsonResult.fail("积分账户加密数据未就绪");
                boolean cipherValid = MD5Util.md5Valid(fubiFdAcc.getBalance().toString(), salt.getSaltValue(),
                		fubiFdAcc.getCiphertext());
                if (SpringContextTool.isPrd() && !cipherValid)
                    return JsonResult.fail("积分账户已被冻结");*/
            } else {
                fubiFdAcc = new UserAccountDTO();
                fubiFdAcc.setBalance(new BigDecimal(0));;
            }

            // 积分冻结账户
            UserAccountDTO ffAcc = accountFacade.queryUserAccountByUserId(memberId, 2);
            if (ffAcc != null) {
                // 账户加密值校验
                SaltDTO salt = accountFacade.querySaltByUUID(ffAcc.getUuid());
                if (salt == null)
                    return JsonResult.fail("积分冻结账户加密数据未就绪");
                boolean cipherValid = MD5Util.md5Valid(ffAcc.getBalance().toString(), salt.getSaltValue(),
                        ffAcc.getCiphertext());
                if (!cipherValid)
                    return JsonResult.fail("积分冻结账户已被冻结");
            } else {
                return JsonResult.fail("积分冻结账户不存在,请联系管理员");
            }

            if(!isDlfUser){
                availableFbBalance = fubiAcc.getBalance().add((fubiFdAcc==null || fubiFdAcc.getBalance()==null)?new BigDecimal(0) : fubiFdAcc.getBalance()).subtract(ffAcc.getBalance());
            }else{
                availableFbBalance = fubiAcc.getBalance();
            }

            //availableFbBalance = fubiAcc.getBalance().subtract(ffAcc.getBalance());

        }
        data.put("fubiBalance", availableFbBalance);
        data.put("jidianBalance", availableJiDianBalance);
        //可用于积分支付的金额
        BigDecimal orderAmountByFuBi = orderAmountTotal.subtract(orderAmountOnlyCashTotal);

        /*double cashPay = 0d;
        if (availableFbBalance.compareTo(orderAmountByFuBi)<0) {
        	cashPay = orderAmountByFuBi.subtract(availableFbBalance).add(orderAmountOnlyCashTotal).doubleValue();
        }
        data.put("cashPay", cashPay);*/

        // 商品总额
        data.put("goodsAmount", orderAmountTotal);


        // 优惠卷unit
        List<OrderConfirmCouponUnitVO> couponUnitList = new ArrayList<OrderConfirmCouponUnitVO>();
        CouponUnitDTO couponUnitDTO = new CouponUnitDTO();
        couponUnitDTO.setUserId(memberId);
        couponUnitDTO.setCouponType(couponType == null ? 0 : couponType);
        //获取优惠卷优惠后的金额
        logger.info("优惠券信息:"+couponUnitId);
        logger.info("优惠券信息:"+JSONObject.toJSONString(couponUnitDTO));
        couponUnitList = promotionFacade.calculateAfterCouponDetail(orderAmountTotal,payByFuBiAmount,orderAmountByFuBi,orderAmountOnlyCashTotal,orderResultList, storeId, couponUnitId, couponUnitDTO, platformId, data, deliveryPriceMap,clientId);
        Long time7 = System.currentTimeMillis();
        logger.info("时间7:"+(time7-time5));
        //没有优惠卷
        BigDecimal dicount = new BigDecimal(promotionFacade.getDiscount(storeId, platformId).toString()).divide(BigDecimal.valueOf(100));//折扣率*100
        //门店优惠金额
        BigDecimal orderAmount = orderAmountTotal;//商品金额
        BigDecimal storeDiscount = (orderAmount.subtract(orderAmount.multiply(dicount))).setScale(2,BigDecimal.ROUND_DOWN);//门店优惠
//        BigDecimal realPay = BigDecimal.valueOf(cashPay).subtract(storeDiscount).add(BigDecimal.valueOf(totalDeliveryPrice));
        data.put("orderAmount", orderAmount.add(new BigDecimal(String.valueOf(totalDeliveryPrice))).subtract(storeDiscount));//商品总额+运费-总优惠
        data.put("storeDiscount", storeDiscount);
        double cashPay = 0d;
        BigDecimal orderAmountPaidByFuBi = BigDecimal.valueOf(0);
        //如果仅积分支付
        Integer canPay=1;
        BigDecimal payByFuBi=payByFuBiAmount.subtract(storeDiscount);
        if(availableFbBalance.compareTo(payByFuBi)<0){
            canPay=0;
        }
        data.put("canPay",canPay);

        BigDecimal compareAmount = orderAmount.add(new BigDecimal(String.valueOf(totalDeliveryPrice))).subtract(storeDiscount).subtract(new BigDecimal(String.valueOf(totalDeliveryPrice))).subtract(orderAmountOnlyCashTotal);
        //当可用积分余额≥max{0,实付款 -总运费 – 仅支持现金支付的商品总额} + 总运费 时，可抵扣金额 = max{0，实付款 - 总运费 – 仅支持现金支付的商品总额} + 总运费
        //当可用积分余额≤max{0，实付款 - 总运费 – 仅支持现金支付的商品总额} + 总运费时，可抵扣金额 = 当可用积分余额





        if(compareAmount.compareTo(BigDecimal.valueOf(0))>=0){
            //实付款 -总运费 – 仅支持现金支付的商品总额 max
            if(availableFbBalance.compareTo(compareAmount.add(new BigDecimal(String.valueOf(totalDeliveryPrice))))>=0){
                //当可用积分余额>=max
                orderAmountPaidByFuBi = compareAmount.add(new BigDecimal(String.valueOf(totalDeliveryPrice)));
            }else{
                //当可用积分余额<=max
                orderAmountPaidByFuBi = availableFbBalance;

            }

        }else{
            //0 max
            if(availableFbBalance.compareTo(new BigDecimal(String.valueOf(totalDeliveryPrice)))>=0){
                //当可用积分余额>=max
                orderAmountPaidByFuBi = new BigDecimal(String.valueOf(totalDeliveryPrice));
            }else{
                //当可用积分余额<=max
                orderAmountPaidByFuBi = availableFbBalance;
            }
        }
        cashPay = orderAmount.add(new BigDecimal(String.valueOf(totalDeliveryPrice))).subtract(storeDiscount).subtract(orderAmountPaidByFuBi).doubleValue();

        //如果仅积分支付，存在现金支付时不可支付
        if(payFuBiOnly && (cashPay>0)){
            data.put("canPay",0);
        }
/*

        //积分现金支付商品总金额-门店优惠=积分可支付金额
        orderAmountByFuBi = orderAmountByFuBi.subtract(storeDiscount);
        //如果可积分支付金额小于0,则将差值加在现金支付金额上,可抵扣金额为0
        if(orderAmountByFuBi.compareTo(BigDecimal.valueOf(0))<=0){
            orderAmountPaidByFuBi = BigDecimal.valueOf(0).add(BigDecimal.valueOf(totalDeliveryPrice));//将积分可抵扣金额设置为0+运费
            cashPay = orderAmountOnlyCashTotal.add(orderAmountByFuBi).doubleValue();
        }else{
            //如果可抵扣金额大于0
            if (availableFbBalance.compareTo(orderAmountByFuBi)<0) {
                //账户积分不足
                orderAmountPaidByFuBi = availableFbBalance;
                cashPay= orderAmountOnlyCashTotal.add(orderAmountByFuBi.subtract(availableFbBalance)).doubleValue();
            }else{
                //账户积分充足
                orderAmountPaidByFuBi = orderAmountByFuBi;
                cashPay = orderAmountOnlyCashTotal.doubleValue();
            }
        }

*/





        data.put("cashPay", cashPay>0?cashPay:0);
        data.put("orderAmountPaidByFuBi",orderAmountPaidByFuBi);

        data.put("couponUnitList", couponUnitList);


        // 话费充值手机号
        data.put("phone", phone);

        //判断门店是不是零售门店
        boolean isShowAddr = true;
        StoreDTO storeById = storeFacade.findStoreById(storeId);

        if (storeById.getIsDetail() == 1) {
            //零售门店不显示地址
            isShowAddr = false;
        } else {
            // 判断是否展示收货地址信息(电子和充话费不用显示)
            isShowAddr = commodityTemplateId == null || commodityTemplateId.equals(2L)|| commodityTemplateId.equals(7L)
                    ? true : false;

        }
        data.put("isShowAddr", isShowAddr);

        //是否需要填写身份证和姓名
        data.put("needIdCardNo", "0");
        //若是存在全球购的商品则需要填写身份证号和真实姓名
        if(hasExistsWorldBuy && (EmptyUtil.isEmpty(userDTO.getName()) || EmptyUtil.isEmpty(userDTO.getIdCardNo()))){
            data.put("needIdCardNo", "1");
        }
        Long time6 = System.currentTimeMillis();
        logger.info("时间6:"+(time6-time5));
        return JsonResult.success(data);
    }


    private Map<String, String> buildDeliveryPriceFromCake(List<ChannelSkuInfoVO> skuInfoList, DefaultReceiverInfoVo receiverInfo) {
        Map<String, String> map = new HashMap<>();
        map.put("success", "0");
        JSONObject userObject = cakeUtil.userLogin(null);
        JsonResult checkUserRT = cakeUtil.checkResult(userObject);
        if(Objects.nonNull(checkUserRT)){
            logger.error("获取运费时登录三方用户发生失败{}",JSON.toJSONString(checkUserRT));
            map.put("jdDeliveryError", "获取运费时第三方用户发生失败");
            return map;
        }
        JSONObject userData = userObject.getJSONObject(cakeUtil.DATA_KEY);
        String userId = userData.getString("id");
        CakeAddResultDTO dto = null;
        ReceiverAddressDTO addr  = receiverAddressFacade.findReceiverAddressById(receiverInfo.getId());
        String cityId =null;
        try {
            dto = cakeAddressWriteManage.addOrEditCakeAddress(addr,userId);
            String cityName = addr.getGoodReceiverCity();
            if("市辖区".equals(cityName) || "直辖市".equals(cityName)){
                cityName = addr.getGoodReceiverProvince();
            }
            cityId = cakeAddressWriteManage.getCityId(cityName);
        }catch (Exception e){
            logger.error("获取运费时请求第三方地址失败{}",e);
            map.put("jdDeliveryError", "获取运费时请求第三方地址失败"+e.getMessage());
            return map;
        }

        List<Map<String,Object>> mapList = new ArrayList<>();
        for (ChannelSkuInfoVO skuInfo : skuInfoList) {
            Map<String,Object> productMap = new HashMap<>();
            productMap.put("product_id",skuInfo.getProductId());
            productMap.put("city_id",cityId);
            mapList.add(productMap);
        }
        Map<String,Object> productTotalMap = new HashMap<>();
        productTotalMap.put("product",mapList);
        JSONObject ruleIdsJSONObject = cakeUtil.getRuleIds(productTotalMap);
        logger.info("批量获取规则id结果:{}",JSON.toJSONString(ruleIdsJSONObject));
        JsonResult ruleIdsResult = cakeUtil.checkResult(ruleIdsJSONObject);
        if(Objects.nonNull(ruleIdsResult)){
            map.put("jdDeliveryError", "获取批量规则id规则失败"+ruleIdsResult.getError());
            return map;
        }
        JSONArray jsonArray = ruleIdsJSONObject.getJSONArray("data");
        List<CakeRuleIdsVO> cakeRuleIdsVOS = new ArrayList<>();
        Map<String,String> productRuleMap = new HashMap<>();
        // 遍历JSON数组并转换成CakeRuleIdsVO对象
        if(jsonArray !=null && jsonArray.size() >0){
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject dataObject = jsonArray.getJSONObject(i);
                productRuleMap.put(dataObject.getString("product_id"),dataObject.getString("distribution_rule_id"));
            }

            for (ChannelSkuInfoVO skuInfo : skuInfoList) {
                if(productRuleMap.containsKey(skuInfo.getProductId())){
                    skuInfo.setDistribution_rule_id(productRuleMap.get(skuInfo.getProductId()));
                }
            }
        }


        logger.info("set完规则后的sku信息：{}",JSON.toJSONString(skuInfoList));
        BigDecimal delivery_amount = BigDecimal.ZERO;
        String delivery_text = "";
        for (ChannelSkuInfoVO channelSkuInfoVO : skuInfoList) {
            if(EmptyUtil.isEmpty(channelSkuInfoVO.getDistribution_rule_id())){
                channelSkuInfoVO.setDistribution_rule_id("0");
            }
        }
        Map<String, List<ChannelSkuInfoVO>> groupedByRuleIdSkuMAP = skuInfoList.stream()
                .collect(Collectors.groupingBy(ChannelSkuInfoVO::getDistribution_rule_id));
        for (Entry<String, List<ChannelSkuInfoVO>> stringListEntry : groupedByRuleIdSkuMAP.entrySet()) {
            List<ChannelSkuInfoVO> listEntryValue = stringListEntry.getValue();
            StringBuffer spceIds = new StringBuffer();
            StringBuffer quantitys = new StringBuffer();
            for (ChannelSkuInfoVO skuInfo : listEntryValue) {
                spceIds.append(skuInfo.getSkuId()).append(",");
                quantitys.append(skuInfo.getNum()).append(",");
            }
            spceIds.deleteCharAt(spceIds.length() - 1);
            quantitys.deleteCharAt(quantitys.length() - 1);
            JSONObject jsonObject = cakeUtil.getDistributionRules(cityId,dto.getId(),spceIds.toString(),quantitys.toString());
            JsonResult jsonResult = cakeUtil.checkResult(jsonObject);
            if(Objects.nonNull(jsonResult)){
                map.put("jdDeliveryError", "获取运费时第三方运费规则失败"+jsonResult.getError());
                return map;
            }
            logger.info("获取蛋糕专卖三方（规则）运费结果"+JSON.toJSONString(jsonObject));
            JSONObject data = jsonObject.getJSONObject(cakeUtil.DATA_KEY);
            JSONObject validate_same_row = null;
            if("1".equals(data.getString("can_same"))){
                try {
                    validate_same_row = data.getJSONObject("validate_same_row");
                }catch (Exception e){
                    JSONArray jsonArray1 = data.getJSONArray("validate_same_row");
                    if(jsonArray1 !=null && jsonArray1.size()>0){
                        validate_same_row = jsonArray1.getJSONObject(0);
                    }
                }
            }

            boolean validateRule = false;
            String currText ="";
            if("1".equals(data.getString("can_same")) && validate_same_row !=null && validate_same_row.containsKey("delivery_amount") && validate_same_row.get("delivery_amount") !=null){
                validateRule = true;
                delivery_amount = delivery_amount.add(validate_same_row.getBigDecimal("delivery_amount"));
                currText = data.containsKey("delivery_text")?data.getString("delivery_text"):"";
            }else if(data.getString("can_ship").equals("1")){
                validateRule = true;
                JSONArray validate_delivery_datesArr = data.getJSONArray("validate_delivery_dates");
                if(validate_delivery_datesArr !=null && validate_delivery_datesArr.size() >0){
                    JSONObject lastRecord = validate_delivery_datesArr.getJSONObject(0);
                    delivery_amount = delivery_amount.add(lastRecord.getBigDecimal("delivery_amount"));
                    String dateStr = lastRecord.getString("date");
                    JSONArray times = lastRecord.getJSONArray("validate_delivery_times");
                    if(times !=null && times.size() >0){
                        String fristTime = times.getString(0);
                        dateStr = dateStr+" "+fristTime;
                    }
                    currText =dateStr;
                }
            }

            if(!validateRule){
                map.put("jdDeliveryError", "获取运费时第三方规则失败");
                logger.info("蛋糕叔叔获取运费时validateRule=false");
                return map;
            }

            if(EmptyUtil.isNotEmpty(delivery_text)){
                delivery_text = delivery_text+","+currText;
            }else{
                delivery_text = currText;
            }

        }
        map.put("success", "1");
        map.put("deliveryPrice",delivery_amount.toPlainString());
        map.put("deliveryText",delivery_text);
        logger.info("蛋糕叔叔最终获取运费为:{}",JSON.toJSONString(map));
        return map;
    }

    private Map<String, String> buildDeliveryPriceFromWorld(List<SkuInfo> skuInfoList, DefaultReceiverInfoVo receiverInfo) {
        Map<String, String> map = new HashMap<>();
        map.put("success", "1");
        map.put("deliveryPrice","0");
        return map;
    }

    @Override
    public JsonResult<Map<String, Object>> queryOrderPayInfo(String soCode) {
        Long time1 = System.currentTimeMillis();
        SoDTO soDTO = soFacade.querySoByOrderCode(soCode);
        if (Objects.isNull(soDTO)) {
            return JsonResult.fail("订单号:soCode" + soCode + "不存在");
        }
        Long memberId = soDTO.getUserId();
        List<CompanyConfigDTO> configs = userReadService.findUserCompanyConfigs(memberId);
        Map<String, CompanyConfigDTO> configMap = new HashMap<>();
        if (EmptyUtil.isNotEmpty(configs)) {
            configs.forEach(cf -> {
                if (EmptyUtil.isNotEmpty(cf.getKey()) && EmptyUtil.isNotBlank(cf.getValue()) && cf.getValue().length() == 1) {
                    configMap.put(cf.getKey().toLowerCase(), cf);
                }
            });
        }
        boolean validFubiAcc = true;
        boolean validFdAcc = true;
        boolean payFuBiOnly = false;
        if (configMap.containsKey("account.0.valid")) {
            validFubiAcc = (Integer.valueOf(configMap.get("account.0.valid").getValue()).intValue() == 0) ? false : true;
        }
        if (configMap.containsKey("account.4.valid")) {
            validFdAcc = (Integer.valueOf(configMap.get("account.4.valid").getValue()).intValue() == 0) ? false : true;
        }
        if (configMap.containsKey("pay.fubi.only")) {
            payFuBiOnly = (Integer.valueOf(configMap.get("pay.fubi.only").getValue()).intValue() == 1) ? true : false;
        }
        Long time2 = System.currentTimeMillis();
        logger.info("时间2:" + (time2 - time1));
        List<SoChildDTO> soChildDTOS = soChildReadService.querySoChildListBySoId(soDTO.getId());
        List<OrderResult> orderResultList = constructOrderResult(soDTO.getCompanyId(), soDTO.getStoreId(), soChildDTOS);
        Long time3 = System.currentTimeMillis();
        logger.info("时间3:" + (time3 - time2));
        List<Map<String, Object>> goodGroupList = new ArrayList<>();
        BigDecimal orderAmountTotal = BigDecimal.ZERO;
        BigDecimal orderAmountOnlyCashTotal = BigDecimal.ZERO;//仅支持现金购买的商品金额
        int goodsAccount = 0;
        double totalDeliveryPrice = 0;
        List<OrderConfirmGoodsDTO> goodsList = new ArrayList<>();
        List<OrderConfirmGoodsDTO> unBuyGoodsList = new ArrayList<>();
        Map<Long, BigDecimal> deliveryPriceMap = new HashMap<>();
        List<LimitRuleRecordDTO> limitRuleRecordList = new ArrayList<>();
        logger.info("校验限购记录=" + orderResultList.size());
        for (OrderResult orderResult : orderResultList) {
            if (!orderResult.isSuccess()) {
                return JsonResult.fail(orderResult.getError());
            }
            if (EmptyUtil.isNotEmpty(orderResult.getLimitRuleRecordList())) {
                limitRuleRecordList.addAll(orderResult.getLimitRuleRecordList());
            }
        }
        Long time5 = System.currentTimeMillis();
        logger.info("时间5:" + (time5 - time3));
        BigDecimal payByFuBiAmount = BigDecimal.ZERO;
        for (OrderResult orderResult : orderResultList) {
            payByFuBiAmount = payByFuBiAmount.add(orderResult.getPayByFuBiAmount());
            if (!orderResult.isSuccess()) {
                return JsonResult.fail(orderResult.getError());
            }
            Map<String, Object> goodGroup = new HashMap<>();
            orderAmountTotal = orderAmountTotal.add(orderResult.getOrderAmount());

            if (EmptyUtil.isEmpty(orderResult.getOrderAmountOnlyCash())) {
                orderResult.setOrderAmountOnlyCash(BigDecimal.ZERO);
            }
            orderAmountOnlyCashTotal = orderAmountOnlyCashTotal.add(orderResult.getOrderAmountOnlyCash());
            goodsAccount += orderResult.getGoodsAccount();
            goodGroup.put("goodsList", orderResult.getGoodsList());
            BigDecimal deliveryAmount = Objects.nonNull(orderResult.getDeliveryAmount()) ? orderResult.getDeliveryAmount() : BigDecimal.ZERO;
            totalDeliveryPrice += deliveryAmount.doubleValue();
            goodGroup.put("deliveryPrice", deliveryAmount.doubleValue());

            goodsList.addAll(orderResult.getGoodsList());
            unBuyGoodsList.addAll(orderResult.getJdDownGoods());
            goodGroup.put("deliveryMethod", deliveryAmount.compareTo(BigDecimal.ZERO) > 0 ? 1 : 0);
            deliveryPriceMap.put(orderResult.getMerchant().getId(), BigDecimal.valueOf(0));
            orderResult.setNeedCountDelivery(deliveryAmount.compareTo(BigDecimal.ZERO) > 0);
            goodGroup.put("merchantId", orderResult.getMerchant().getId());
            goodGroup.put("merchantName", orderResult.getMerchant().getName());
            goodGroup.put("storeName", orderResult.getStore().getName());
            goodGroup.put("isOwnMerchant", orderResult.getMerchant().getId().equals(1L) ? 1 : 0);//是否自营
            goodGroupList.add(goodGroup);
        }

        // 返回用户默认收货地址
        Map<String, Object> data = new HashMap<>();
        boolean isFuBi = true;
        for (OrderResult orderResult : orderResultList) {
            if (orderResult.getIsPayByFuBiOnly() == 0) {
                isFuBi = false;
            }
        }
        if (payFuBiOnly) {
            isFuBi = payFuBiOnly;
        }
        if (isFuBi) {
            data.put("isPayByFuBiOnly", 1);
        } else {
            data.put("isPayByFuBiOnly", 0);
        }
        data.put("goodGroupList", goodGroupList);
        data.put("unBuyGoodsList", unBuyGoodsList);
        // 默认发票信息
        data.put("invoiceType", 0);
        // 商品数量
        data.put("goodsAccount", goodsAccount);
        data.put("totalDeliveryPrice", totalDeliveryPrice);
        //判断是否仅现金支付
        Integer isPayByCashOnly = Integer.valueOf(1);
        for (OrderConfirmGoodsDTO dto : goodsList) {
            if (dto.getBuyType() != 2) {
                isPayByCashOnly = Integer.valueOf(0);
            }
        }
        data.put("isPayByCashOnly", isPayByCashOnly);
        //根据storeId判断当前门店是不是零售门店
        StoreDTO storeById1 = storeFacade.findStoreById(soDTO.getStoreId());
        if (storeById1 == null) {
            return JsonResult.fail(soDTO.getStoreId() + "不存在");
        }
        data.put("isDetailStore", storeById1.getIsDetail());
        data.put("orderAmount", orderAmountTotal);

        //计算用户可用的积分金额
        // 可达积分余额
        UserDTO userDTO =  userReadService.findUserByID(memberId);
        boolean isDlfUser = false;
        if (Objects.nonNull(userDTO) && EmptyUtil.isNotEmpty(userDTO.getChannelSource())
                && Objects.equals(UserChannelSourceEnum.DLF.getChannelSource(),userDTO.getChannelSource())){
            isDlfUser=true;
        }
        BigDecimal availableFbBalance = BigDecimal.ZERO;
        BigDecimal availableJiDianBalance = BigDecimal.ZERO;
        if (soDTO.getPlatformId() == 7) {
            // 用户可用积分数量:积分余额-已冻结积分
            // 积分账户
            UserAccountDTO fubiAcc = accountFacade.queryUserAccountByUserId(memberId, 0);
            UserAccountDTO fubiFdAcc = accountFacade.queryUserAccountByUserId(memberId, 4);
            if (isDlfUser){
                UserAccountDTO jidianAcc = accountFacade.queryUserAccountByUserId(memberId, 5);
                if(Objects.nonNull(jidianAcc) && Objects.nonNull(jidianAcc.getBalance())){
                    availableJiDianBalance = jidianAcc.getBalance();
                }
            }
            if (fubiAcc != null) {
                if (validFubiAcc) {
                    // 账户加密值校验
                    SaltDTO salt = accountFacade.querySaltByUUID(fubiAcc.getUuid());
                    if (salt == null)
                        return JsonResult.fail("积分账户加密数据未就绪");
                    boolean cipherValid = MD5Util.md5Valid(fubiAcc.getBalance().toString(), salt.getSaltValue(),
                            fubiAcc.getCiphertext());
                    if (SpringContextTool.isPrd() && !cipherValid)
                        return JsonResult.fail("积分账户已被冻结");
                }
            } else {
                return JsonResult.fail("积分账户不存在,请联系管理员");
            }
            if (fubiFdAcc != null) {
                if (validFdAcc) {
                    // 账户加密值校验
                    SaltDTO salt = accountFacade.querySaltByUUID(fubiFdAcc.getUuid());
                    if (salt == null)
                        return JsonResult.fail("积分账户加密数据未就绪");
                    boolean cipherValid = MD5Util.md5Valid(fubiFdAcc.getBalance().toString(), salt.getSaltValue(),
                            fubiFdAcc.getCiphertext());
                    if (SpringContextTool.isPrd() && !cipherValid)
                        return JsonResult.fail("积分账户已被冻结");// 账户加密值校验
                }
            } else {
                fubiFdAcc = new UserAccountDTO();
                fubiFdAcc.setBalance(new BigDecimal(0));
            }
            // 积分冻结账户
            UserAccountDTO ffAcc = accountFacade.queryUserAccountByUserId(memberId, 2);
            if (ffAcc != null) {
                // 账户加密值校验
                SaltDTO salt = accountFacade.querySaltByUUID(ffAcc.getUuid());
                if (salt == null)
                    return JsonResult.fail("积分冻结账户加密数据未就绪");
                boolean cipherValid = MD5Util.md5Valid(ffAcc.getBalance().toString(), salt.getSaltValue(),
                        ffAcc.getCiphertext());
                if (!cipherValid)
                    return JsonResult.fail("积分冻结账户已被冻结");
            } else {
                return JsonResult.fail("积分冻结账户不存在,请联系管理员");
            }
            if(!isDlfUser){
                availableFbBalance = fubiAcc.getBalance()
                        .add((Objects.isNull(fubiFdAcc) || Objects.isNull(fubiFdAcc.getBalance()))?BigDecimal.ZERO : fubiFdAcc.getBalance()).subtract(ffAcc.getBalance());
            }else{
                availableFbBalance = fubiAcc.getBalance();
            }
        }
        data.put("fubiBalance", availableFbBalance);
        data.put("jidianBalance", availableJiDianBalance);
        // 商品总额
        data.put("goodsAmount", orderAmountTotal);
        //没有优惠卷
        BigDecimal dicount = soDTO.getOrderAmount().subtract(soDTO.getOrderAmountPay());//折扣率*100
        data.put("orderAmount", soDTO.getOrderAmountPay());//商品总额+运费-总优惠
        data.put("storeDiscount", dicount);
        double cashPay = 0d;
        //如果仅积分支付
        Integer canPay = 1;
        BigDecimal payByFuBi = soDTO.getOrderAmountPay();
        if (isFuBi && availableFbBalance.compareTo(payByFuBi) < 0) {
            canPay = 0;
        }
        data.put("canPay", canPay);
        BigDecimal compareAmount = soDTO.getOrderAmountPay().subtract(orderAmountOnlyCashTotal);
        //当可用积分余额≥max{0,实付款 -总运费 – 仅支持现金支付的商品总额} + 总运费 时，可抵扣金额 = max{0，实付款 - 总运费 – 仅支持现金支付的商品总额} + 总运费
        //当可用积分余额≤max{0，实付款 - 总运费 – 仅支持现金支付的商品总额} + 总运费时，可抵扣金额 = 当可用积分余额
        BigDecimal orderAmountPaidByFuBi = BigDecimal.ZERO;
        if (compareAmount.compareTo(BigDecimal.ZERO) >= 0) {
            orderAmountPaidByFuBi = compareAmount.min(availableFbBalance);
        }
        cashPay = soDTO.getOrderAmountPay().subtract(orderAmountPaidByFuBi).doubleValue();

        //如果仅积分支付，存在现金支付时不可支付
        if (payFuBiOnly && (cashPay > 0)) {
            data.put("canPay", 0);
        }
        data.put("cashPay", cashPay > 0 ? cashPay : 0);
        data.put("orderAmountPaidByFuBi", orderAmountPaidByFuBi);

        //判断门店是不是零售门店
        data.put("isShowAddr", false);
        Long time6 = System.currentTimeMillis();
        logger.info("时间6:" + (time6 - time5));
        return JsonResult.success(data);
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
    private Map<String,String> buildDeliveryPrice(String token,List<SkuInfo> skuInfoList,ParseAddressJson parseAddressJson){
        Map<String, String> map = new HashMap<>();
        try {
            String deliveryPriceFromJd = jdUtils.getDeliveryPriceFromJd(token,
                    JSON.toJSONString(skuInfoList),
                    parseAddressJson.getProvinceId(),
                    parseAddressJson.getCityId(),
                    parseAddressJson.getCountyId(),
                    parseAddressJson.getTownId()
            );
            logger.info("京东商品查询结果:sku列表{},结果{}",JSON.toJSONString(skuInfoList),deliveryPriceFromJd);
            if(EmptyUtil.isNotEmpty(deliveryPriceFromJd)){
                JSONObject resultObj = JSONObject.parseObject(deliveryPriceFromJd);
                Boolean success = resultObj.getBoolean("success");
                if (EmptyUtil.isNotEmpty(success) && success) {
                    JdDeliveryPrice jdDeliveryPrice = JSON.parseObject(resultObj.getString("result"), JdDeliveryPrice.class);
                    if(EmptyUtil.isEmpty(jdDeliveryPrice.getFreight())){
                        map.put("deliveryPrice","0");

                    }else {
                        map.put("deliveryPrice",jdDeliveryPrice.getFreight() );
                    }
                    map.put("success", "1");
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            logger.info("查询地址运费出错" + e.getMessage());
            map.put("success", "-1");
        }

        return map;

    }

    //购物车下单生成限购规则,订单商品列表
    private OrderResult buyProductByCart(ParseAddressJson parseAddressJson, String address, Long clientId, Integer companyType, List<CartItemDTO> cartItemList, Long puId, Long userId, Long platformId, Long storeId, Long merchantId) {

        OrderResult result = new OrderResult();
        List<OrderConfirmGoodsDTO> goodsList = new ArrayList<>();
        List<LimitRuleRecordDTO> limitRuleRecordList = new ArrayList<>();
        int goodsAccount = 0;//订单商品总数
        BigDecimal orderAmount = BigDecimal.ZERO;//订单总金额
        BigDecimal orderAmountOnlyCash = BigDecimal.valueOf(0);//仅现金支付金额
        MerchantDTO merchant = merchantFacade.findMerchantById(merchantId);
        List<OrderConfirmGoodsDTO> jdDownGoods = new ArrayList<>();
        result.setIsPayByFuBiOnly(0);
        result.setMerchant(merchant);
        BigDecimal payByFuBiAmount = BigDecimal.ZERO;
        // 购物车下单
        for (CartItemDTO ci : cartItemList) {
            // 商品存在校验
            Long puId_ = ci.getProductUnitId();
            boolean flag = false;
            int num_ = ci.getNum().intValue();
            double price_ =0;
            OrderConfirmGoodsDTO gvo = new OrderConfirmGoodsDTO();
            Integer buyType=0;
            if(ci.isJd()) {
                buyType = 1;
                JdProductDTO jdProductDTO = productFacade.checkJdProductStatus(puId_+"");
                //校验京东
                //1.校验京东上下架
                String externalSkuId = puId_+"";

                price_ = jdProductDTO.getPrice().doubleValue();
                gvo.setNum(num_);
                gvo.setCartItemId(ci.getId());
                gvo.setPrice(price_);
                gvo.setPuId(puId_);
                String imagePath = jdProductDTO.getImagePath();
                JSONObject object = JSONObject.parseObject(imagePath);
                String assisImg = object.getString("assisImg");
                String primaryImg = object.getString("primaryImg");
                gvo.setPuImg(primaryImg);
                gvo.setPuName(jdProductDTO.getName());
                gvo.setBuyType(1);
                gvo.setExternalSkuId(externalSkuId);
                gvo.setStandardUnitId(puId_);
                gvo.setLimitBuyNum(1l);
                gvo.setSource(ThirdConst.Source.JD);
                if(jdProductDTO.getSkuJson()!=null && jdProductDTO.getSkuJson().length()>5) {
                    JSONObject skuJson = JSON.parseObject(jdProductDTO.getSkuJson());
                    StringBuffer skuDesc = new StringBuffer();
                    for(Entry<String, Object> entry: skuJson.entrySet()) {
                        if(skuDesc.length()>2) {
                            skuDesc.append("\n");
                        }
                        skuDesc.append(entry.getKey()).append(":").append(entry.getValue().toString());
                    }
                    gvo.setSkuDesc(skuDesc.toString());
                }
                gvo.setIsOwnMerchant(0);
                if(jdProductDTO.getState()==0){
                    flag = true;
                    if(clientId==2){
                        gvo.setErrMessage("抱歉，该商品已下架");
                        jdDownGoods.add(gvo);
                        continue;
                    }else{
                        result.setError("抱歉，该商品已下架");
                        result.setSuccess(false);
                        return result;
                    }

                }
                //String token = jdUtils.getAccessToken(jedisUtil);
                String token = jdUtils2.getAccessToken(jedisUtil);
                //2.校验京东是否可售
                String skuSellStatusFromJd = jdUtils.getSkuSellStatusFromJd(token, externalSkuId, "");
                JdResponse jdSellResponse = JSON.parseObject(skuSellStatusFromJd, JdResponse.class);
                if(jdSellResponse==null) {
                    result.setError("抱歉，该商品已下架");
                    result.setSuccess(false);
                    return result;
                }
                if(jdSellResponse.isSuccess()&&jdSellResponse.getResultCode().equals("0000")){
                    String json = jdSellResponse.getResult();
                    List<JdSkuSellStatus> jdSkuStatus = JSON.parseArray(json, JdSkuSellStatus.class);
                    if(jdSkuStatus.get(0).getSaleState()==0){
                        flag = true;
                        if(clientId==2){
                            gvo.setErrMessage("抱歉，该商品已下架");
                            jdDownGoods.add(gvo);
                            continue;
                        }else{
                            result.setError("抱歉，该商品已下架");
                            result.setSuccess(false);
                            return result;
                        }
                    }
                }else{
                    throw new BusinessException("查询上下架失败");
                }
                //4.校验是否在可售区域
                if(EmptyUtil.isNotEmpty(address)){
                    if(EmptyUtil.isNotEmpty(parseAddressJson)){
                        String skuAddressSellStatusFromJd = jdUtils.getSkuAddressSellStatusFromJd(token, externalSkuId, parseAddressJson.getProvinceId(), parseAddressJson.getCityId(), parseAddressJson.getCountyId(), parseAddressJson.getTownId());
                        JdResponse jdResponse1 = JSON.parseObject(skuAddressSellStatusFromJd, JdResponse.class);
                        if(jdResponse1.isSuccess()&&jdResponse1.getResultCode().equals("0000")){
                            String json = jdResponse1.getResult();
                            List<JdSkuAddressSellStatus> jdSkuStatus = JSON.parseArray(json, JdSkuAddressSellStatus.class);
                            if(jdSkuStatus.get(0).getIsAreaRestrict().equals("true")){
                                flag = true;
                                if(clientId==2){
                                    gvo.setErrMessage("抱歉，该商品在收货地址区域内不可售，请重新选择收货地址");
                                    jdDownGoods.add(gvo);
                                    continue;
                                }else{
                                    result.setError("抱歉，该商品在收货地址区域内不可售，请重新选择收货地址");
                                    result.setSuccess(false);
                                    return result;
                                }
                            }
                        }else{
                            throw new BusinessException("查询上下架失败");
                        }


                        //3.校验库存状态
                        JdProductStockSearch stockSearch = new JdProductStockSearch();
                        stockSearch.setSkuId(Long.valueOf(externalSkuId));
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
                            throw new BusinessException("当前商品为商品库存存在问题");
                        }else{
                            String stockStateId = jdProductStock.getString("stockStateId");
                            if(stockStateId.equals("33")||stockStateId.equals("39")||stockStateId.equals("40")){
                                logger.info("有货");
                            }else{
                                flag = true;
                                if(clientId==2){
                                    gvo.setErrMessage("抱歉，该商品无货");
                                    jdDownGoods.add(gvo);
                                    continue;
                                }else{
                                    result.setError("抱歉，该商品无货");
                                    result.setSuccess(false);
                                    return result;
                                }

                            }
                        }

                    }

                }



            }else if(ci.isCake()){
                CakeProductDetailDTO cakeProductDetailDTO = productFacade.getCakeProduct(ci.getChannelProductId(),puId_+"",null,null);
                if(cakeProductDetailDTO == null){
                    result.setError("商品不存在");
                    result.setSuccess(false);
                    return result;
                }
                CakeProductDetailProductsDTO product = cakeProductDetailDTO.getProduct();
                if(product == null){
                    result.setError("商品不存在");
                    result.setSuccess(false);
                    return result;
                }
                List<CakeProductDetailSpecsDTO> specsDTOS = cakeProductDetailDTO.getSpecs();
                if(CollectionUtils.isEmpty(specsDTOS)){
                    result.setError("商品无规格");
                    result.setSuccess(false);
                    return result;
                }
                CakeProductDetailSpecsDTO specsDTO = null;
                for (CakeProductDetailSpecsDTO dto : specsDTOS) {
                    if(dto.getId().equals(puId_+"")){
                        specsDTO = dto;
                    }
                }
                if(specsDTO==null){
                    result.setError("商品无对应的规格");
                    result.setSuccess(false);
                    return result;
                }
                price_ = new BigDecimal(specsDTO.getPrice()).doubleValue();
                gvo.setNum(num_);
                gvo.setCartItemId(ci.getId());
                gvo.setPrice(price_);
                gvo.setPuId(puId_);
                gvo.setExternalProductId(ci.getChannelProductId());
                gvo.setPuImg(StringUtils.isNotEmpty(product.getImage_path())?product.getImage_path():specsDTO.getSpec_img());
                gvo.setPuName(product.getTitle()+specsDTO.getName());
                gvo.setBuyType(1);
                gvo.setExternalSkuId(specsDTO.getId());
                gvo.setStandardUnitId(puId_);
                gvo.setLimitBuyNum(1L);
                gvo.setSkuDesc(specsDTO.getDescription());
                gvo.setIsOwnMerchant(0);
                gvo.setSource(ThirdConst.Source.CAKE);
                if(!product.getStatus().equals("1")){
                    flag = true;
                    if(clientId==2){
                        gvo.setErrMessage("抱歉，该商品已下架");
                        jdDownGoods.add(gvo);
                        continue;
                    }else{
                        result.setError("抱歉，该商品已下架");
                        result.setSuccess(false);
                        return result;
                    }
                }
                if(EmptyUtil.isEmpty(specsDTO.getStock()) ||EmptyUtil.isBlank(specsDTO.getStock())){
                    throw new BusinessException("当前商品为商品库存存在问题");
                }

                String stock = specsDTO.getStock();
                if(stock.equals("-9999999") || Integer.valueOf(stock) >0){
                    logger.info("有货");
                }else{
                    flag = true;
                    if(clientId==2){
                        gvo.setErrMessage("抱歉，该商品无货");
                        jdDownGoods.add(gvo);
                        continue;
                    }else{
                        result.setError("抱歉，该商品无货");
                        result.setSuccess(false);
                        return result;
                    }

                }
            }else if(ci.isWorld()){
                ChannelProductDetailVO channelProductDetailVO = productFacade.findWorldProduct(ci.getChannelProductId(),puId_+"");
                if(channelProductDetailVO == null){
                    result.setError("商品不存在");
                    result.setSuccess(false);
                    return result;
                }
                ChannelProductDTO channelProductDTO = channelProductDetailVO.getChannelProductDTO();
                if(channelProductDTO == null){
                    result.setError("商品不存在");
                    result.setSuccess(false);
                    return result;
                }
                List<ChannelProductBatchDTO> batchDTOList = channelProductDetailVO.getBatchDTOList();
                ChannelProductBatchDTO batchDTO = productFacade.getCurrBatch(puId_+"",batchDTOList);
                if(batchDTO==null){
                    result.setError("商品无对应的规格");
                    result.setSuccess(false);
                    return result;
                }
                price_ = batchDTO.getPrice().doubleValue();
                gvo.setNum(num_);
                gvo.setCartItemId(ci.getId());
                gvo.setPrice(price_);
                gvo.setPuId(puId_);

                gvo.setPuImg(channelProductDetailVO.getProductImg());
                gvo.setPuName(channelProductDTO.getTitle()+batchDTO.getSpecName());
                gvo.setBuyType(1);
                gvo.setExternalSkuId(batchDTO.getLinkSkuId());
                gvo.setStandardUnitId(puId_);
                gvo.setLimitBuyNum(1L);
                gvo.setSkuDesc(batchDTO.getSpecName());
                gvo.setIsOwnMerchant(0);
                gvo.setSource(ThirdConst.Source.WORLD);
                if(channelProductDTO.getStatus() !=1){
                    flag = true;
                    if(clientId==2){
                        gvo.setErrMessage("抱歉，该商品已下架");
                        jdDownGoods.add(gvo);
                        continue;
                    }else{
                        result.setError("抱歉，该商品已下架");
                        result.setSuccess(false);
                        return result;
                    }
                }
                if(EmptyUtil.isEmpty(batchDTO.getNum())){
                    throw new BusinessException("当前商品为商品库存存在问题");
                }

                Integer stock =batchDTO.getNum();
                if(stock >0){
                    logger.info("有货");
                }else{
                    flag = true;
                    if(clientId==2){
                        gvo.setErrMessage("抱歉，该商品无货");
                        jdDownGoods.add(gvo);
                        continue;
                    }else{
                        result.setError("抱歉，该商品无货");
                        result.setSuccess(false);
                        return result;
                    }

                }
            }else {
                CommodityProductUnitDTO pu = productFacade.queryPuById(puId_);
                SkuDTO skuDTO=productFacade.findSkuBySkuId(pu.getSkuId());
                //根据公司类型区分价格

                if (pu == null) {
                    result.setError("商品不存在");
                    result.setSuccess(false);
                    return result;
                }
                StandardUnitDTO suByPuId = getSUByPuId(pu.getId());
                buyType = suByPuId.getBuyType();
                if(suByPuId.getBuyType()==3){
                    result.setIsPayByFuBiOnly(1);
                }
                Map<Integer, String> integerStringMap = UserMembershipCheckUtils.checkUserMembershipAuthority(userId, suByPuId.getId(), suByPuId.getSaleWay(), platformId);
                Set<Integer> integers = integerStringMap.keySet();
                for (Integer i : integers) {
                    if (i == 0) {
                        result.setError(integerStringMap.get(i));
                        result.setSuccess(false);
                        return result;
                    }
                }

                // 商品上架校验
                if(suByPuId.getMerchantId()!=6&&clientId!=2){
                    //京东的上下架单独校验(且微信端也是单独校验)
                    if (pu.getStatus().intValue() != 3) {
                        result.setError("抱歉，该商品已下架");
                        result.setSuccess(false);
                        return result;
                    }
                }

                String puImg = pu.getPuPicUrl();
                if (EmptyUtil.isBlank(puImg)) {
                    // pu图片查询逻辑
                    puImg = productFacade.queryPuNullImgUrl(pu.getSkuId());

                }
                // 商品库存校验
                Long stock = productFacade.queryStockByPUId(puId_);
                if (stock.intValue() < num_) {
                    result.setError("库存不足");
                    result.setSuccess(false);
                    return result;
                }

                if(storeId!=1L&&storeId!=2L){
                    StoreProductUnitDTO storeProductUnitDTO=storeFacade.findStorePuId(storeId,puId_);
                    StorePuWarehouseStockDTO storeStock =soFacade.findStoreStock(storeProductUnitDTO.getId(),storeProductUnitDTO.getStoreId());
                    Long storeStockNum = storeStock.getRealStockNum() - storeStock.getRealFrozenStockNum();
                    if(storeStockNum<num_){
                        logger.info("sku商品门店库存不足,skuId" + pu.getSkuId() + "库存为：" + storeStockNum);
                        result.setSuccess(false);
                        result.setError("库存不足");
                        return result;
                    }
                }
                //校验门店
                if(storeId==1L||storeId==2L){
                    if(!suByPuId.getStoreId().equals(storeId)){
                        result.setError("您提交的商品有被管理员移除");
                        result.setSuccess(false);
                        return result;

                    }
                }else{
                    List<StandardUnitStoreDTO> standUnitStore = productFacade.findStandUnitStore(suByPuId.getId(), storeId);
                    if(EmptyUtil.isEmpty(standUnitStore)){
                        result.setError("您提交的商品有被管理员移除");
                        result.setSuccess(false);
                        return result;
                    }
                }



                if(companyType==0){
                    price_ = pu.getSalePrice().doubleValue();
                }else if(companyType==1){
                    price_ = pu.getDemoSalePrice().doubleValue();

                }else if(companyType==2){
                    price_ = pu.getCompetingSalePrice().doubleValue();
                }
                //double price_ = pu.getSalePrice().doubleValue();

                gvo.setNum(num_);
                gvo.setCartItemId(ci.getId());
                gvo.setPrice(price_);
                gvo.setPuId(puId_);
                gvo.setPuImg(puImg);
                gvo.setPuName(pu.getName());
                gvo.setBuyType(suByPuId.getBuyType());
                gvo.setExternalSkuId(skuDTO.getExternalSkuId());
                gvo.setStandardUnitId(pu.getStandardUnitId());
                gvo.setLimitBuyNum(pu.getLimitBuyNum());
                if(suByPuId.getBuyType()==3){
                    payByFuBiAmount = payByFuBiAmount.add(BigDecimal.valueOf(price_).multiply(BigDecimal.valueOf(num_)));
                }else{
                    result.setPayByFuBiAmount(BigDecimal.ZERO);
                }
                if(pu.getLimitBuyNum()>=2){
                    if(num_<pu.getLimitBuyNum()){
                        throw new BusinessException("至少购买"+pu.getLimitBuyNum()+"件哦");
                    }

                }

                if (suByPuId.getSaleWay() == 4 || suByPuId.getSaleWay() == 6) {
                    gvo.setPreSaleDay(suByPuId.getPresellPeriod());
                    gvo.setSaleWay(suByPuId.getSaleWay());

                }

                gvo.setIsOwnMerchant(merchant.getId().equals(1L) ? 1 : 0);
                // 限购规则记录
                LimitRuleRecordDTO lrro = new LimitRuleRecordDTO();
                lrro.setBuySum(Long.valueOf(num_));
                lrro.setBuyMoneySum(BigDecimal.valueOf(price_));
                lrro.setProductUnitId(puId);
                lrro.setStandardUnitId(pu.getStandardUnitId());
                limitRuleRecordList.add(lrro);
            }


            if(!flag){
                goodsAccount += num_;
                orderAmount=orderAmount.add(BigDecimal.valueOf(num_).multiply(BigDecimal.valueOf(price_)));
                if(buyType.equals(2)){
                    orderAmountOnlyCash = orderAmountOnlyCash.add(BigDecimal.valueOf(num_).multiply(BigDecimal.valueOf(price_)));
                }
                result.setOrderAmountOnlyCash(orderAmountOnlyCash);
                goodsList.add(gvo);
            }

        }

        StoreDTO store = storeFacade.findStoreById(storeId);
        result.setStore(store);

        result.setGoodsAccount(goodsAccount);
        result.setPayByFuBiAmount(payByFuBiAmount);
        result.setOrderAmount(orderAmount);
        result.setLimitRuleRecordList(limitRuleRecordList);
        result.setGoodsList(goodsList);
        result.setJdDownGoods(jdDownGoods);
        result.setSuccess(true);
        return result;

    }

    private OrderResult buyProductByChild(SoChildDTO soChildDTO, Long merchantId,Long storeId) {
        List<SoItemDTO> soItemDTOList =soItemFacade.querySoItemsBySoChildId(soChildDTO.getId());
        OrderResult result = new OrderResult();
        List<OrderConfirmGoodsDTO> goodsList = new ArrayList<>();
        List<LimitRuleRecordDTO> limitRuleRecordList = new ArrayList<>();
        int goodsAccount = 0;//订单商品总数
        BigDecimal orderAmountOnlyCash = BigDecimal.ZERO;//仅现金支付金额
        MerchantDTO merchant = merchantFacade.findMerchantById(merchantId);
        List<OrderConfirmGoodsDTO> jdDownGoods = new ArrayList<>();
        result.setIsPayByFuBiOnly(0);
        result.setMerchant(merchant);
        BigDecimal payByFuBiAmount = BigDecimal.ZERO;
        int buyType=0;
        // 购物车下单
        for (SoItemDTO ci : soItemDTOList) {
            OrderConfirmGoodsDTO gvo = new OrderConfirmGoodsDTO();
            gvo.setNum(ci.getPuCount());
            gvo.setPrice(ci.getPrice().doubleValue());
            gvo.setPuId(ci.getPuId());
            gvo.setPuImg(ci.getPuPicUrl());
            gvo.setPuName(ci.getPuName());
            gvo.setExternalSkuId(ci.getExternalSkuId());
            gvo.setStandardUnitId(ci.getPuId());
            gvo.setIsOwnMerchant(merchant.getId().equals(1L) ? 1 : 0);
            goodsAccount += ci.getPuCount();
            if(ci.isThirdParty()) {
                buyType=1;
                gvo.setBuyType(1);
                gvo.setLimitBuyNum(1l);
            }else {
                CommodityProductUnitDTO pu = productFacade.queryPuById(ci.getPuId());
                //根据公司类型区分价格
                if (pu == null) {
                    result.setError("商品不存在");
                    result.setSuccess(false);
                    return result;
                }
                StandardUnitDTO suByPuId = getSUByPuId(pu.getId());
                buyType = suByPuId.getBuyType();
                if(suByPuId.getBuyType()==3){
                    result.setIsPayByFuBiOnly(1);
                }
                // 限购规则记录
                LimitRuleRecordDTO lrro = new LimitRuleRecordDTO();
                lrro.setBuySum(Long.valueOf(ci.getPuCount()));
                lrro.setBuyMoneySum(ci.getPrice());
                lrro.setProductUnitId(ci.getPuId());
                lrro.setStandardUnitId(pu.getStandardUnitId());
                limitRuleRecordList.add(lrro);
            }
            if(buyType==2){
                orderAmountOnlyCash = orderAmountOnlyCash.add(BigDecimal.valueOf(ci.getPuCount()).multiply(ci.getPrice()));
            }
            goodsList.add(gvo);

        }

        StoreDTO store = storeFacade.findStoreById(storeId);
        result.setStore(store);
        result.setOrderAmountOnlyCash(orderAmountOnlyCash);
        result.setGoodsAccount(goodsAccount);
        result.setPayByFuBiAmount(payByFuBiAmount);
        result.setOrderAmount(soChildDTO.getAmount());
        result.setLimitRuleRecordList(limitRuleRecordList);
        result.setGoodsList(goodsList);
        result.setDeliveryAmount(soChildDTO.getDeliveryFee());
        result.setJdDownGoods(jdDownGoods);
        result.setSuccess(true);
        return result;

    }

    //直接下单判断是否为unit,生成限购规则记录,生成订单商品列表
    private OrderResult  buyPrductByRightNow(ParseAddressJson parseAddressJson, String address, Long clientId, Integer companyType, Long puId, Integer num, Integer couponType,
                                             Long couponUnitId, Long commodityTemplateId,
                                             String phone, Long storeId,String channelProductId,Integer source) {
        OrderResult result = new OrderResult();
        // 参数校验
        if (puId == null || num == null || puId == 0 || num == 0) {

            result.setSuccess(false);
            result.setError("参数为空");
            return result;
        }

        result.setIsPayByFuBiOnly(0);
        // 兑换卷购买su数量必须为1
        if ((EmptyUtil.isNotEmpty(couponType)) && (EmptyUtil.isNotEmpty(couponUnitId)) && couponType == 1 && num != 1) {
            result.setSuccess(false);
            result.setError("兑换数量必须是1");
            return result;
        }
        BigDecimal payByFuBiAmount = BigDecimal.ZERO;

        // 判断是否是话费充值
        if (commodityTemplateId != null && (commodityTemplateId.equals(4L)||commodityTemplateId.equals(9L))) {
            if (EmptyUtil.isEmpty(phone)) {
                result.setSuccess(false);
                result.setError("手机号码不能为空");
                return result;
            }
            if (!StringUtils.validMobile(phone)) {
                result.setSuccess(false);
                result.setError("请输入正确的手机号码");
                return result;
            }
            // 默认商品数量为1
            num = Integer.valueOf(1);
        }

        // 商品存在校验
        CommodityProductUnitDTO pu = productFacade.queryPuById(puId);
        JdProductDTO jdProductDTO = null;
        boolean isJd = false;
        if(pu==null && (source==null || source.intValue()==3)) {
            jdProductDTO = productFacade.checkJdProductStatus(puId+"");
            if(jdProductDTO!=null) {
                isJd = true;
            }
        }

        OrderConfirmGoodsDTO gvo = new OrderConfirmGoodsDTO();
        Boolean flag = false;

        int goodsAccount = num;//订单商品总数
        BigDecimal orderAmount = BigDecimal.ZERO;//订单总金额
        List<OrderConfirmGoodsDTO> goodsList = new ArrayList<>();
        List<OrderConfirmGoodsDTO> jdDownGoods = new ArrayList<>();
        List<LimitRuleRecordDTO> limitRuleRecordList = new ArrayList<>();
        if(isJd) {

            int buyType = 1;
            //校验京东
            //1.校验京东上下架
            String externalSkuId = puId+"";

            double price = jdProductDTO.getPrice().doubleValue();
            gvo.setNum(num);
            gvo.setPrice(price);
            gvo.setPuId(puId);
            if(jdProductDTO.getSkuJson()!=null && jdProductDTO.getSkuJson().length()>5) {
                JSONObject skuJson = JSON.parseObject(jdProductDTO.getSkuJson());
                StringBuffer skuDesc = new StringBuffer();
                for(Entry<String, Object> entry: skuJson.entrySet()) {
                    if(skuDesc.length()>2) {
                        skuDesc.append("\n");
                    }
                    skuDesc.append(entry.getKey()).append(":").append(entry.getValue().toString());
                }
                gvo.setSkuDesc(skuDesc.toString());
            }
            String imagePath = jdProductDTO.getImagePath();
            JSONObject object = JSONObject.parseObject(imagePath);
            String assisImg = object.getString("assisImg");
            String primaryImg = object.getString("primaryImg");
            gvo.setPuImg(primaryImg);
            gvo.setPuName(jdProductDTO.getName());
            gvo.setBuyType(1);
            gvo.setExternalSkuId(externalSkuId);
            gvo.setStandardUnitId(puId);
            gvo.setLimitBuyNum(1l);
            gvo.setSource(ThirdConst.Source.JD);
            gvo.setIsOwnMerchant(0);
            if(jdProductDTO.getState()==0){
                flag = true;

                gvo.setErrMessage("抱歉，该商品已下架");
                jdDownGoods.add(gvo);
                result.setError("抱歉，该商品已下架");
                result.setSuccess(false);
                result.setJdDownGoods(jdDownGoods);

                return result;


            }
            //String token = jdUtils.getAccessToken(jedisUtil);
            String token = jdUtils2.getAccessToken(jedisUtil);
            //2.校验京东是否可售
            String skuSellStatusFromJd = jdUtils.getSkuSellStatusFromJd(token, externalSkuId, "");
            JdResponse jdSellResponse = JSON.parseObject(skuSellStatusFromJd, JdResponse.class);
            if(jdSellResponse.isSuccess()&&jdSellResponse.getResultCode().equals("0000")){
                String json = jdSellResponse.getResult();
                List<JdSkuSellStatus> jdSkuStatus = JSON.parseArray(json, JdSkuSellStatus.class);
                if(jdSkuStatus.get(0).getSaleState()==0){
                    flag = true;

                    gvo.setErrMessage("抱歉，该商品已下架");
                    jdDownGoods.add(gvo);
                    result.setError("抱歉，该商品已下架");
                    result.setSuccess(false);
                    result.setJdDownGoods(jdDownGoods);
                    return result;

                }
            }else{
                throw new BusinessException("查询上下架失败");
            }
            //4.校验是否在可售区域
            if(EmptyUtil.isNotEmpty(address)){
                if(EmptyUtil.isNotEmpty(parseAddressJson)){
                    String skuAddressSellStatusFromJd = jdUtils.getSkuAddressSellStatusFromJd(token, externalSkuId, parseAddressJson.getProvinceId(), parseAddressJson.getCityId(), parseAddressJson.getCountyId(), parseAddressJson.getTownId());
                    JdResponse jdResponse1 = JSON.parseObject(skuAddressSellStatusFromJd, JdResponse.class);
                    if(jdResponse1.isSuccess()&&jdResponse1.getResultCode().equals("0000")){
                        String json = jdResponse1.getResult();
                        List<JdSkuAddressSellStatus> jdSkuStatus = JSON.parseArray(json, JdSkuAddressSellStatus.class);
                        if(jdSkuStatus.get(0).getIsAreaRestrict().equals("true")){
                            flag = true;

                            gvo.setErrMessage("抱歉，该商品在收货地址区域内不可售，请重新选择收货地址");
                            jdDownGoods.add(gvo);
                            result.setError("抱歉，该商品在收货地址区域内不可售，请重新选择收货地址");
                            result.setSuccess(false);
                            result.setJdDownGoods(jdDownGoods);
                            return result;
                        }
                    }else{
                        throw new BusinessException("查询上下架失败");
                    }


                    //3.校验库存状态
                    JdProductStockSearch stockSearch = new JdProductStockSearch();
                    stockSearch.setSkuId(Long.valueOf(externalSkuId));
                    stockSearch.setNum(Long.valueOf(num));
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
                        throw new BusinessException("当前商品为商品库存存在问题");
                    }else{
                        String stockStateId = jdProductStock.getString("stockStateId");
                        if(stockStateId.equals("33")||stockStateId.equals("39")||stockStateId.equals("40")){
                            logger.info("有货");
                        }else{
                            flag = true;

                            gvo.setErrMessage("抱歉，该商品无货");
                            jdDownGoods.add(gvo);
                            result.setError("抱歉，该商品无货");
                            result.setSuccess(false);
                            result.setJdDownGoods(jdDownGoods);
                            return result;

                        }
                    }

                }

            }

            if(!flag){
                orderAmount=orderAmount.add(BigDecimal.valueOf(num).multiply(BigDecimal.valueOf(price)));
                //立即购买,单个商品,设置仅现金支付金额
                BigDecimal orderAmountOnlyCash = new BigDecimal(0);

                result.setOrderAmountOnlyCash(orderAmountOnlyCash);//如果商品是积分+现金则设置为0
                goodsList.add(gvo);
            }
            MerchantDTO merchant = merchantFacade.findMerchantById(6l);
            result.setMerchant(merchant);

        }else if(source !=null && source.intValue()==4){
            int buyType = 1;
            //校验蛋糕叔叔
            //1.校验蛋糕叔叔上下架
            String externalSkuId = puId+"";
            CakeProductDetailDTO cakeProductDetailDTO = productFacade.getCakeProduct(channelProductId,puId+"",null,null);
            if(cakeProductDetailDTO==null){
                result.setSuccess(false);
                result.setError("商品不存在");
                return result;
            }
            CakeProductDetailProductsDTO productsDTO = cakeProductDetailDTO.getProduct();
            CakeProductBrandDTO brand = cakeProductDetailDTO.getBrand();
            if(productsDTO==null){
                result.setSuccess(false);
                result.setError("商品明细不存在");
                return result;
            }
            if(!productsDTO.getStatus().equals("1")){
                flag = true;
                gvo.setErrMessage("抱歉，该商品无货");
                jdDownGoods.add(gvo);
                result.setError("抱歉，该商品已下架");
                result.setSuccess(false);
                return result;

            }
            List<CakeProductDetailSpecsDTO> specsDTOS = cakeProductDetailDTO.getSpecs();
            if(CollectionUtils.isEmpty(specsDTOS)){
                result.setSuccess(false);
                result.setError("商品无规格");
                return result;
            }
            CakeProductDetailSpecsDTO specsDTO = null;
            for (CakeProductDetailSpecsDTO dto : specsDTOS) {
                if(dto.getId().equals(puId+"")){
                    specsDTO = dto;
                    break;
                }
            }
            if(specsDTO==null){
                result.setSuccess(false);
                result.setError("商品无对应的规格");
                return result;
            }
            if(EmptyUtil.isEmpty(specsDTO.getStock()) || EmptyUtil.isBlank(specsDTO.getStock())){
                throw new BusinessException("当前商品库存存在问题");
            }
            if(specsDTO.getStock().equals("-9999999") || Integer.valueOf(specsDTO.getStock()) >0){
                logger.info("有货");
            }else{
                flag = true;

                gvo.setErrMessage("抱歉，该商品无货");
                jdDownGoods.add(gvo);
                result.setError("抱歉，该商品无货");
                result.setSuccess(false);
                result.setJdDownGoods(jdDownGoods);
                return result;

            }
            double price = new BigDecimal(specsDTO.getPrice()).doubleValue();
            gvo.setNum(num);
            gvo.setPrice(price);
            gvo.setPuId(puId);
            gvo.setSkuDesc(specsDTO.getDescription());
            gvo.setPuImg(StringUtils.isNotEmpty(productsDTO.getImage_path())?productsDTO.getImage_path():specsDTO.getSpec_img());
            gvo.setPuName(productsDTO.getTitle()+specsDTO.getName());
            gvo.setBuyType(1);
            gvo.setExternalSkuId(externalSkuId);
            gvo.setStandardUnitId(puId);
            gvo.setLimitBuyNum(1L);
            gvo.setExternalProductId(channelProductId);
            gvo.setIsOwnMerchant(0);
            gvo.setDistribution_rule_id(productsDTO.getDistribution_rule_id());
            gvo.setSource(ThirdConst.Source.CAKE);
            if(EmptyUtil.isEmpty(productsDTO.getDistribution_rule_id())){
                gvo.setDistribution_rule_id(brand.getDistribution_rule_id());
            }
            if(!flag){
                orderAmount=orderAmount.add(BigDecimal.valueOf(num).multiply(BigDecimal.valueOf(price)));
                //立即购买,单个商品,设置仅现金支付金额
                BigDecimal orderAmountOnlyCash = new BigDecimal(0);

                result.setOrderAmountOnlyCash(orderAmountOnlyCash);//如果商品是积分+现金则设置为0
                goodsList.add(gvo);
            }
            MerchantDTO merchant = merchantFacade.findMerchantById(7L);
            result.setMerchant(merchant);

        }else if(source !=null && source.intValue()==5){
            int buyType = 1;
            //校验全球购
            //1.校验全球购上下架
            String externalSkuId = puId+"";
            ChannelProductDetailVO channelProductDetailVO = productFacade.findWorldProduct(channelProductId,externalSkuId);
            if(channelProductDetailVO==null){
                result.setSuccess(false);
                result.setError("商品不存在");
                return result;
            }
            ChannelProductDTO channelProductDTO = channelProductDetailVO.getChannelProductDTO();
            if(channelProductDTO==null){
                result.setSuccess(false);
                result.setError("商品不存在");
                return result;
            }

            List<ChannelProductBatchDTO> batchDTOList = channelProductDetailVO.getBatchDTOList();
            ChannelProductBatchDTO batchDTO = productFacade.getCurrBatch(externalSkuId,batchDTOList);
            if(batchDTO==null){
                result.setSuccess(false);
                result.setError("商品无对应的规格");
                return result;
            }
            if(batchDTO.getNum() ==null){
                throw new BusinessException("当前商品库存存在问题");
            }
            if(batchDTO.getNum() >0){
                logger.info("有货");
            }else{
                flag = true;
                gvo.setErrMessage("抱歉，该商品无货");
                jdDownGoods.add(gvo);
                result.setError("抱歉，该商品无货");
                result.setSuccess(false);
                result.setJdDownGoods(jdDownGoods);
                return result;
            }
            ChannelProductDescriptionDTO channelProductDescriptionDTO = channelProductDetailVO.getDescriptionDTO();
            String description = channelProductDTO.getName()+batchDTO.getSpecName();
            if(channelProductDescriptionDTO !=null){
                description = channelProductDescriptionDTO.getContent();
            }
            double price = batchDTO.getPrice().doubleValue();
            gvo.setNum(num);
            gvo.setPrice(price);
            gvo.setPuId(puId);
            gvo.setSkuDesc(description);
            gvo.setPuImg(channelProductDetailVO.getProductImg());
            gvo.setPuName(channelProductDTO.getTitle()+batchDTO.getSpecName());
            gvo.setBuyType(1);
            gvo.setExternalSkuId(externalSkuId);
            gvo.setStandardUnitId(puId);
            gvo.setLimitBuyNum(1L);
            gvo.setIsOwnMerchant(0);
            gvo.setSource(ThirdConst.Source.WORLD);
            gvo.setExternalProductId(channelProductDTO.getProductId());
            if(!flag){
                orderAmount=orderAmount.add(BigDecimal.valueOf(num).multiply(BigDecimal.valueOf(price)));
                //立即购买,单个商品,设置仅现金支付金额
                BigDecimal orderAmountOnlyCash = new BigDecimal(0);
                result.setOrderAmountOnlyCash(orderAmountOnlyCash);//如果商品是积分+现金则设置为0
                goodsList.add(gvo);
            }
            MerchantDTO merchant = merchantFacade.findMerchantById(8L);
            result.setMerchant(merchant);
        }else {
            if (pu == null) {
                result.setSuccess(false);
                result.setError("商品不存在");
                return result;
            }
            result.setIsPayByFuBiOnly(0);
            StandardUnitDTO standardUnitDTO=productFacade.findStandardUnit(pu.getStandardUnitId());
            if(standardUnitDTO.getBuyType()==3){
                result.setIsPayByFuBiOnly(1);
            }
            MerchantDTO merchant = merchantFacade.findMerchantById(standardUnitDTO.getMerchantId());
            result.setMerchant(merchant);
            StoreDTO store = storeFacade.findStoreById(storeId);
            result.setStore(store);

            if(storeId==1L||storeId==2L){
                //总店
                if(!standardUnitDTO.getStoreId().equals(storeId)){
                    result.setSuccess(false);
                    result.setError("您提交的订单中有商品已被总店管理员移除");
                    return result;
                }

            }else{
                //门店
                List<StandardUnitStoreDTO> standardUnitStoreDTO= (List<StandardUnitStoreDTO>) productFacade.findStandUnitStore(pu.getStandardUnitId(),storeId);
                if(EmptyUtil.isEmpty(standardUnitStoreDTO)){
                    result.setSuccess(false);
                    result.setError("您提交的订单中有商品已被门店管理员移除");
                    return result;
                }
            }

            if (pu.getIsVendible() == 0) {
                // pu不可销售
                result.setSuccess(false);
                result.setError("无该规格商品");
                return result;
            }
            // 商品上架校验
            if (pu.getStatus().intValue() != 3&&clientId!=2) {
                result.setSuccess(false);
                result.setError("商品已下架");
                return result;
            }

            // 判断是否是话费充值,是,调用第三方接口校验手机号与面值是否被支持
            if (commodityTemplateId != null && (commodityTemplateId.equals(4L)||commodityTemplateId.equals(9L))) {
                int errorCode = -1;
                try {
                    if(companyType==0){
                        errorCode = rechargePhoneUtil.telCheck(phone, pu.getSalePrice().intValue());
                    }else if(companyType==1){
                        errorCode = rechargePhoneUtil.telCheck(phone, pu.getDemoSalePrice().intValue());

                    }else if(companyType==2){
                        errorCode = rechargePhoneUtil.telCheck(phone, pu.getCompetingSalePrice().intValue());
                    }

                } catch (Exception e) {
                    logger.error("手机号码校验错误", e);
                }
                if (errorCode != RechargePhoneErrorCode.REQUEST_SUCCESS.getCode()) {
                    result.setSuccess(false);
                    result.setError("请输入正确的手机号码");
                    return result;
                }
            }

            String puImg = pu.getPuPicUrl();
            if (EmptyUtil.isBlank(puImg)) {
                // pu图片查询逻辑()
                puImg = productFacade.queryPuNullImgUrl(pu.getSkuId());

            }
            // 商品库存校验
            Long stock = productFacade.queryStockByPUId(puId);
            if (stock == null || stock.intValue() < num) {
                logger.info("商品库存不足，puId:" + puId + "，库存：" + stock);
                result.setSuccess(false);
                result.setError("库存不足");
                return result;
            }
            // 判断unit库存属性
            // 判断是否有unit库存
            boolean unitFlag = productFacade.queryIsUnit(puId);
            if (unitFlag) {
                Long unitStock = productFacade.queryUnitStockBySkuId(pu.getSkuId());
                if (unitStock == null || unitStock.intValue() < num) {
                    logger.info("sku商品库存不足,skuId" + pu.getSkuId() + "库存为：" + unitStock);
                    result.setSuccess(false);
                    result.setError("卡券库存不足");
                    return result;
                }
            }
            boolean isCard=productFacade.queryIsCard(puId);
            //校验门店库存
            //根据storeid和puid查询storeproductunitid
            if(storeId!=1L&&storeId!=2L){
                StoreProductUnitDTO storeProductUnitDTO=storeFacade.findStorePuId(storeId,puId);
                StorePuWarehouseStockDTO storeStock =soFacade.findStoreStock(storeProductUnitDTO.getId(),storeProductUnitDTO.getStoreId());
                Long storeStockNum = storeStock.getRealStockNum() - storeStock.getRealFrozenStockNum();
                if(storeStockNum<num){
                    logger.info("sku商品门店库存不足,skuId" + pu.getSkuId() + "库存为：" + storeStockNum);
                    result.setSuccess(false);
                    result.setError("库存不足");
                    return result;
                }
            }



            double price =0;
            if(companyType==0){
                price = pu.getSalePrice().doubleValue();
            }else if(companyType==1){
                price = pu.getDemoSalePrice().doubleValue();

            }else if(companyType==2){
                price = pu.getCompetingSalePrice().doubleValue();
            }



            gvo.setNum(num);
            gvo.setPrice(price);
            gvo.setPuId(puId);
            gvo.setPuImg(puImg);
            gvo.setPuName(pu.getName());
            gvo.setStandardUnitId(pu.getStandardUnitId());
            gvo.setUnit(unitFlag);
            gvo.setCard(isCard);
            gvo.setBuyType(standardUnitDTO.getBuyType());
            gvo.setExternalSkuId(pu.getExternalSkuId());
            gvo.setLimitBuyNum(pu.getLimitBuyNum());

            if(standardUnitDTO.getBuyType()==3){
                result.setPayByFuBiAmount(BigDecimal.valueOf(price).multiply(BigDecimal.valueOf(num)));
            }else{
                result.setPayByFuBiAmount(BigDecimal.ZERO);
            }
            if(pu.getLimitBuyNum()>=2){
                if(num<pu.getLimitBuyNum()){
                    throw new BusinessException("至少购买"+pu.getLimitBuyNum()+"件哦");
                }
            }
            if(standardUnitDTO.getSaleWay()==4||standardUnitDTO.getSaleWay()==6){
                gvo.setPreSaleDay(standardUnitDTO.getPresellPeriod());
                gvo.setSaleWay(standardUnitDTO.getSaleWay());
            }
            gvo.setIsOwnMerchant(merchant.getId().equals(1L) ? 1 : 0);

            //校验当前商品是否为京东商品,是的话校验是否下架失效(微信端展示商品,app端报错)

            if(!flag){
                orderAmount=orderAmount.add(BigDecimal.valueOf(num).multiply(BigDecimal.valueOf(price)));
                //立即购买,单个商品,设置仅现金支付金额
                BigDecimal orderAmountOnlyCash = new BigDecimal(0);
                if(standardUnitDTO.getBuyType().equals(2)){
                    //仅现金支付,此时商品的金额就是仅现金支付的金额
                    orderAmountOnlyCash = orderAmount;
                }
                result.setOrderAmountOnlyCash(orderAmountOnlyCash);//如果商品是积分+现金则设置为0
                goodsList.add(gvo);
            }

            // 限购规则记录
            LimitRuleRecordDTO lrro = new LimitRuleRecordDTO();
            lrro.setBuySum(Long.valueOf(num));
            lrro.setBuyMoneySum(BigDecimal.valueOf(price));
            lrro.setProductUnitId(puId);
            lrro.setStandardUnitId(pu.getStandardUnitId());
            limitRuleRecordList.add(lrro);
            logger.info("立即购买添加限购记录="+limitRuleRecordList.size());


        }
        StoreDTO store = storeFacade.findStoreById(storeId);
        result.setStore(store);
        result.setGoodsList(goodsList);
        result.setLimitRuleRecordList(limitRuleRecordList);
        result.setOrderAmount(orderAmount);
        result.setGoodsAccount(goodsAccount);
        result.setPayByFuBiAmount(payByFuBiAmount);
        result.setJdDownGoods(jdDownGoods);
        result.setSuccess(true);
        return result;
    }


    //该方法在慢有优项目中已修改,原有方法
	/*@Override
	public JsonResult<Map<String, Object>> orderConfirm(Integer type, String cartItemIds, Long puId, Integer num,
			Long addrId, Long memberId, Long platformId, Long companyId, String phone, Integer couponType,
			Long couponUnitId) {
		if (type == null) {
			return JsonResult.fail("未知下单类型");
		}
		int goodsAccount = 0;//订单商品总数
		double orderAmount = 0d;//订单总金额
		boolean unitFlag = false;
		List<OrderConfirmGoodsVO> goodsList = new ArrayList<>();
		// 组织限购规则记录集合
		List<LimitRuleRecordDTO> limitRuleRecordList = new ArrayList<>();

		// 通过puid查询sku,然后查到spu信息
		Long commodityTemplateId = productFacade.queryCommodityTemplateIdByPuId(puId);

		// 组织返回列表
		if (type.intValue() == 0) {
			// 直接购买
			// 参数校验
			if (puId == null || num == null)
				return JsonResult.fail("商品或数量为空");

			// 兑换卷购买su数量必须为1
			if (couponType != null && couponUnitId != null && couponType == 1 && num != 1)
				return JsonResult.fail("兑换数量必须是1");

			// 判断是否是话费充值
			if (commodityTemplateId != null && commodityTemplateId.equals(4L)) {
				if (EmptyUtil.isEmpty(phone))
					return JsonResult.fail("手机号码不能为空");
				if (!StringUtils.validMobile(phone))
					return JsonResult.fail("请输入正确的手机号码");
				// 默认商品数量为1
				num = Integer.valueOf(1);
			}

			// 商品存在校验
			CommodityProductUnitDTO pu = productFacade.queryPuById(puId);

			if (pu == null)
				return JsonResult.fail("商品不存在");

			if (pu.getIsVendible() == 0) {
				// pu不可销售
				return JsonResult.fail("无该规格商品");
			}
			// 商品上架校验
			if (pu.getStatus().intValue() != 3)
				return JsonResult.fail("商品已下架");

			// 判断是否是话费充值,是,调用第三方接口校验手机号与面值是否被支持
			if (commodityTemplateId != null && commodityTemplateId.equals(4L)) {
				int errorCode = -1;
				try {
					errorCode = RechargePhoneUtil.telCheck(phone, pu.getSalePrice().intValue());
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (errorCode != RechargePhoneErrorCode.REQUEST_SUCCESS.getCode())
					return JsonResult.fail("请输入正确的手机号码");
			}

			String puImg = pu.getPuPicUrl();
			if (EmptyUtil.isBlank(puImg)) {
				// pu图片查询逻辑
				puImg = productFacade.queryPuNullImgUrl(pu.getSkuId());

			}
			// 商品库存校验
			Long stock = productFacade.queryStockByPUId(puId);
			if (stock == null || stock.intValue() < num) {
				logger.info("商品库存不足，puId:" + puId + "，库存：" + stock);
				return JsonResult.fail("库存不足");
			}
			// 判断unit库存属性
			// 判断是否有unit库存
			unitFlag = productFacade.queryIsUnit(puId);
			if (unitFlag) {
				Long unitStock = productFacade.queryUnitStockBySkuId(pu.getSkuId());
				if (unitStock == null || unitStock.intValue() < num) {
					logger.info("sku商品库存不足,skuId" + pu.getSkuId() + "库存为：" + unitStock);
					return JsonResult.fail("卡券库存不足");
				}

			}
			goodsAccount += num;
			double price = pu.getSalePrice().doubleValue();
			orderAmount += num * price;
			OrderConfirmGoodsVO gvo = new OrderConfirmGoodsVO();
			gvo.setNum(num);
			gvo.setPrice(price);
			gvo.setPuId(puId);
			gvo.setPuImg(puImg);
			gvo.setPuName(pu.getName());
			gvo.setStandardUnitId(pu.getStandardUnitId());
			goodsList.add(gvo);

			// 限购规则记录
			LimitRuleRecordDTO lrro = new LimitRuleRecordDTO();
			lrro.setBuySum(Long.valueOf(num));
			lrro.setBuyMoneySum(BigDecimal.valueOf(price));
			lrro.setProductUnitId(puId);
			lrro.setStandardUnitId(pu.getStandardUnitId());
			limitRuleRecordList.add(lrro);
		} else {
			// 购物车下单
			if (EmptyUtil.isEmpty(cartItemIds)) {
				return JsonResult.fail("购物车项为空");
			} else {
				cartItemIds = cartItemIds.replace("[", "");
				cartItemIds = cartItemIds.replace("]", "");
				if (EmptyUtil.isEmpty(cartItemIds))
					return JsonResult.fail("未指定购物车项");
			}
			List<Long> cartItemIds_ = StringUtils.string2IdList(cartItemIds, ",");
			for (Long ciid : cartItemIds_) {
				// 购物车存在校验
				CartItemDTO ci = cartItemFacade.findCartItemById(ciid);
				if (ci == null)
					return JsonResult.fail("购物车项不存在");
				// 商品存在校验
				Long puId_ = ci.getProductUnitId();
				CommodityProductUnitDTO pu = productFacade.queryPuById(puId_);
				if (pu == null)
					return JsonResult.fail("商品不存在");
				// 商品上架校验
				if (pu.getStatus().intValue() != 3)
					return JsonResult.fail("商品已下架");
				String puImg = pu.getPuPicUrl();
				if (EmptyUtil.isBlank(puImg)) {
					// pu图片查询逻辑
					puImg = productFacade.queryPuNullImgUrl(pu.getSkuId());

				}
				// 商品库存校验
				Long stock = productFacade.queryStockByPUId(puId_);
				int num_ = ci.getNum().intValue();
				if (stock.intValue() < num_)
					return JsonResult.fail("库存不足");
				goodsAccount += num_;
				double price_ = pu.getSalePrice().doubleValue();
				orderAmount += num_ * price_;
				OrderConfirmGoodsVO gvo = new OrderConfirmGoodsVO();
				gvo.setNum(num_);
				gvo.setPrice(price_);
				gvo.setPuId(puId_);
				gvo.setPuImg(puImg);
				gvo.setPuName(pu.getName());
				gvo.setStandardUnitId(pu.getStandardUnitId());
				goodsList.add(gvo);

				// 限购规则记录
				LimitRuleRecordDTO lrro = new LimitRuleRecordDTO();
				lrro.setBuySum(Long.valueOf(num_));
				lrro.setBuyMoneySum(BigDecimal.valueOf(price_));
				lrro.setProductUnitId(puId);
				lrro.setStandardUnitId(pu.getStandardUnitId());
				limitRuleRecordList.add(lrro);
			}
		}

		// 根据pu商品集合及限购规则判断是否能购买、返回值不为空直接返回错误
		String isLimitBuy = productFacade.isLimitBuy(limitRuleRecordList, companyId, platformId, memberId);
		if (isLimitBuy != null)
			return JsonResult.fail(isLimitBuy, BusinessExceptionConstant.LIMITBUYEXCEPTIONCODE);

		// 返回用户默认收货地址
		DefaultReceiverInfoVo receiverInfo = defaultReceiverAddress(memberId, addrId);
		Map<String, Object> data = new HashMap<>();
		data.put("receiverInfo", receiverInfo);
		// 商品列表OrderConfirmGoodsVO
		data.put("goodsList", goodsList);
		// 配送方式
		data.put("deliveryMethod", 0);
		// 运费
		data.put("deliveryPrice", 0);
		// 默认发票信息
		data.put("invoiceType", 0);
		// 商品数量
		data.put("goodsAccount", goodsAccount);

		// 判断是否有unit库存
		if (!unitFlag) {
			// 运费
			double freightAmount = productFacade.freightAmountByMerchantId(orderAmount);
			data.put("deliveryPrice", freightAmount);
			// 订单总金额
			data.put("orderAmount", orderAmount + freightAmount);
			if (freightAmount > 0) {
				// 运费大于0 配送方式不包邮
				data.put("deliveryMethod", 1);
			}

		} else {
			// 订单总金额
			data.put("orderAmount", orderAmount);
		}

		// 用户可用积分数量:积分余额-已冻结积分
		// 积分账户
		UserAccountDTO fubiAcc = accountFacade.queryUserAccountByUserId(memberId, 0);
		if (fubiAcc != null) {
			// 账户加密值校验
			SaltDTO salt = accountFacade.querySaltByUUID(fubiAcc.getUuid());
			if (salt == null)
				return JsonResult.fail("积分账户加密数据未就绪");
			boolean cipherValid = MD5Util.md5Valid(fubiAcc.getBalance().toString(), salt.getSaltValue(),
					fubiAcc.getCiphertext());
			if (!cipherValid)
				return JsonResult.fail("积分账户已被冻结");
		} else {
			return JsonResult.fail("积分账户不存在,请联系管理员");
		}
		// 积分冻结账户
		UserAccountDTO ffAcc = accountFacade.queryUserAccountByUserId(memberId, 2);
		if (ffAcc != null) {
			// 账户加密值校验
			SaltDTO salt = accountFacade.querySaltByUUID(ffAcc.getUuid());
			if (salt == null)
				return JsonResult.fail("积分冻结账户加密数据未就绪");
			boolean cipherValid = MD5Util.md5Valid(ffAcc.getBalance().toString(), salt.getSaltValue(),
					ffAcc.getCiphertext());
			if (!cipherValid)
				return JsonResult.fail("积分冻结账户已被冻结");
		} else {
			return JsonResult.fail("积分冻结账户不存在,请联系管理员");
		}
		// 可达积分余额
		BigDecimal availableFbBalance = fubiAcc.getBalance().subtract(ffAcc.getBalance());
		data.put("fubiBalance", availableFbBalance);
		double cashPay = 0d;
		if (availableFbBalance.doubleValue() < orderAmount) {
			cashPay = orderAmount - availableFbBalance.doubleValue();
		}
		data.put("cashPay", cashPay);

		// 商品总额
		data.put("goodsAmount", orderAmount);

		// 优惠卷unit
		List<OrderConfirmCouponUnitVO> couponUnitList = new ArrayList<OrderConfirmCouponUnitVO>();
		CouponUnitDTO couponUnitDTO = new CouponUnitDTO();
		couponUnitDTO.setUserId(memberId);
		couponUnitDTO.setCouponType(couponType);
		if (couponType == null || couponType == 0) {
			// 满减卷
			couponUnitDTO.setCouponType(0);
		} else if (couponType == 1 && type == 0) {
			// 兑换卷(直接购买)
			couponUnitDTO.setCouponType(1);
			data.put("deliveryMethod", 0); // 0:包邮 1:不包邮
			data.put("deliveryPrice", 0);
			data.put("orderAmount", orderAmount);
		}
		couponUnitList = promotionFacade.queryCouponUnitListByIdAndGoodsList(couponUnitId, couponUnitDTO, platformId,
				goodsList, data);

		data.put("couponUnitList", couponUnitList);

		// 话费充值手机号
		data.put("phone", phone);

		// 判断是否展示收货地址信息
		boolean isShowAddr = commodityTemplateId == null || commodityTemplateId.equals(2L)
				? true : false;
		data.put("isShowAddr",isShowAddr);

		return JsonResult.success(data);
	}*/

    /**
     * 获取指定收货地址/默认收货地址
     *
     * @param memberId
     * @param addrId
     * @return
     */
    private DefaultReceiverInfoVo defaultReceiverAddress(Long memberId, Long addrId, Long platformId) {
        ReceiverAddressDTO addressDto = null;
        if (addrId == null || addrId.longValue() == 0l) {
            // 返回默认收货地址DefaultReceiverInfoVo
            addressDto = receiverAddressFacade.queryDefaultReceiverAddress(memberId, platformId);
        } else {
            // 返回指定的收货地址
            addressDto = receiverAddressFacade.findReceiverAddressById(addrId);
            if (addressDto == null || addressDto.getUserId().longValue() != memberId.longValue()) {
                addressDto = null;
            }
        }
        DefaultReceiverInfoVo receiverInfo = null;
        if (addressDto != null) {
            receiverInfo = new DefaultReceiverInfoVo();
            receiverInfo.setId(addressDto.getId());
            String address = addressDto.getGoodReceiverProvince() + addressDto.getGoodReceiverCity()
                    + addressDto.getGoodReceiverCounty() + addressDto.getGoodReceiverAddress();
            receiverInfo.setAddress(address);
            receiverInfo.setPhoneNo(addressDto.getGoodReceiverMobile());
            receiverInfo.setUserId(addressDto.getUserId());
            receiverInfo.setReceiverName(addressDto.getGoodReceiverName());
        }
        return receiverInfo;
    }

    @Override
    public JsonResult<Map<String, Object>> backOrderDetail(Long orderId, Long platformId) {
        if (orderId == null) {
            return JsonResult.fail("请选择商品");
        }
        // 获取订单信息
        SoDTO soDto = soFacade.querySoById(orderId);
        SoVO so = SoConverter.toVO(soDto);
        //商品价格
        so.setPuAmount(so.getOrderAmount().subtract(so.getOrderDeliveryFee()));
        List<SoChildDTO> soChildDTOList = soFacade.findSoChildBySoId(so.getId());
        BigDecimal sum = new BigDecimal(0);
        for (SoChildDTO soChildDTO : soChildDTOList) {
            sum = sum.add(soChildDTO.getAmount());
        }
        //门店信息
        StoreDTO store= storeFacade.findStoreById(soDto.getStoreId());
        so.setStoreName(store.getName());
        so.setIsDetail(store.getIsDetail());

        //计算优惠卷
        so.setCouponDiscount(so.getPuAmount().subtract(sum));
        //计算门店优惠（宗优惠-优惠卷优惠）
        so.setStoreDiscount(so.getOrderPromotionDiscount().subtract(so.getCouponDiscount()));


        // so.setOrderPaymentTypeStr(PaymentTypeConstant.translate(so.getOrderPaymentType()));
        String orderCode = so.getOrderCode();
        // 获取订单商品信息
        List<SoItemDTO> itemDtoList = soItemFacade.querySoItemListByOrderCode(orderCode);
        List<SoDetailItemVo> itemVoList = new ArrayList<>();
        if (itemDtoList.size() > 0) {
            // List<Long> skuIdList = new ArrayList<>();
            // for (SoItemDTO it : itemDtoList) {
            // skuIdList.add(it.getSkuId());
            // }
            // List<SoItemSkuAttValueDTO> attValueList =
            // soItemFacade.querySkuValueListBySkuIds(skuIdList, platformId);
            // 组织展示列表
            // for (SoItemDTO d : itemDtoList) {
            // SoDetailItemVo div = new SoDetailItemVo();
            // Long skuId = d.getSkuId();
            // div.setSkuId(skuId);
            // div.setBuyCount(d.getProductItemNum());
            // div.setCode(d.getOrderCode());
            // div.setId(d.getId());
            // div.setName(d.getProductCname());
            // div.setOrderAmount(d.getProductPriceFinal());
            // div.setProductAmount(d.getProductItemAmount());
            // div.setSkuAttValues(new ArrayList<String>());
            // for (SoItemSkuAttValueDTO sav : attValueList) {
            // if (skuId.longValue() == sav.getSkuId().longValue()) {
            // div.getSkuAttValues().add(sav.getAttValueCustom() + ";");
            // }
            // }
            // itemVoList.add(div);
            // }
        }
        // 获取发票信息
        SoInvoiceDTO invoice = soInvoiceFacade.querySoInvoiceByOrderCode(orderCode, platformId);
        // 获取物流信息
        List<SoPackageDTO> packageDtoList = soPackageFacade.queryPackageListByOrderCode(orderCode, platformId);
        // 匹配每个包裹中的商品
        List<SoDetailPackageVo> detailPackageVoList = new ArrayList<>();
        for (SoPackageDTO p : packageDtoList) {
            Long pId = p.getId();
            SoDetailPackageVo dp = new SoDetailPackageVo();





            dp.setId(pId);
            dp.setDeliveryCompanyName(p.getDeliveryCompanyName());
            dp.setDeliveryExpressNbr(p.getDeliveryCode());
            dp.setItemNames(new ArrayList<String>());
            // for (SoItemDTO d : itemDtoList) {
            // if(EmptyUtil.isNotEmpty(d.getPackId())){
            // if(EmptyUtil.isNotEmpty(d.getPackId())){
            // if (pId.longValue() == d.getPackId().longValue()) {
            // dp.getItemNames().add(d.getProductCname());
            // }
            // }
            //
            // }
            //
            // }
            detailPackageVoList.add(dp);
        }
        Map<String, Object> data = new HashMap<>();
        data.put("orderInfo", so);
        data.put("orderItemList", itemVoList);
        data.put("invoiceInfo", invoice);
        data.put("packageList", detailPackageVoList);
        /* data.put("receiverAddress", receiverAddress); */
        return JsonResult.success(data);
    }

    @Override
    public JsonResult<List<Map<String, String>>> orderStatusList() {
        List<Map<String, String>> result = new ArrayList<>();
        for (OrderConstant oc : OrderConstant.values()) {
            Map<String, String> map = new HashMap<>();
            if (oc.name().startsWith("ORDER_STATUS_")) {
                map.put("key", oc.getComment());
                map.put("value", String.valueOf(oc.getStatus()));
                result.add(map);
            }
        }
        JsonResult<List<Map<String, String>>> rt = new JsonResult<>();
        rt.setData(result);
        return rt;
    }

    @Override
    public JsonResult<Map<String, Object>> updateOrderStatus(String orderCode, Integer status) {
        if (EmptyUtil.isBlank(orderCode))
            return JsonResult.fail("请选择订单");
        if (status == null)
            return JsonResult.fail("请选择状态");
        soFacade.changeOrderStatus(orderCode, status, null, null, null);
        Map<String, Object> data = new HashMap<>();
        data.put("status", OrderConstant.translate(status));
        return JsonResult.success(data);
    }

    @Override
    public JsonResult<Map<String, Object>> updateOrderInfo(Long orderId, BigDecimal orderAmount,
                                                           BigDecimal deliveryAmount, BigDecimal payMoney) {
        if (orderId == null)
            return JsonResult.fail("请选择订单");
        if (orderAmount == null)
            return JsonResult.fail("请填写订单金额");
        if (deliveryAmount == null)
            return JsonResult.fail("请填写运费金额");
        if (payMoney == null)
            return JsonResult.fail("请填写实付金额");
        soFacade.updateOrderMoneyInfo(orderId, orderAmount, deliveryAmount, payMoney);
        Map<String, Object> data = new HashMap<>();
        data.put("orderAmount", orderAmount);
        data.put("deliveryAmount", deliveryAmount);
        data.put("payMoney", payMoney);
        return JsonResult.success(data);
    }

    @Override
    public JsonResult<Map<String, Object>> updateDeliveryInfo(Long orderId, String receiverName, String receiverPhone,
                                                              String receiverAddress) {
        if (orderId == null)
            return JsonResult.fail("请选择订单");
        if (StringUtil.isBlank(receiverName))
            return JsonResult.fail("请填写收件人姓名");
        if (StringUtil.isBlank(receiverPhone))
            return JsonResult.fail("请填写收件人联系电话");
        if (StringUtil.isBlank(receiverAddress))
            return JsonResult.fail("请填写收件地址");
        soFacade.updateDeliveryInfo(orderId, receiverName, receiverPhone, receiverAddress);
        Map<String, Object> data = new HashMap<>();
        data.put("receiverName", receiverName);
        data.put("receiverPhone", receiverPhone);
        data.put("receiverAddress", receiverAddress);
        return JsonResult.success(data);
    }

    @Override
    public JsonResult<List<SoDetailItemVo>> soDetailItemVoBySoId(Long soId, Long packId, Long platformId) {
        JsonResult<List<SoDetailItemVo>> jsonResult = new JsonResult<List<SoDetailItemVo>>();
        // 获取订单商品信息
        // 获取订单信息
        // SoDTO soDto = soFacade.querySoById(soId);
        // SoVO so = SoConverter.toVO(soDto);
        // so.setOrderPaymentTypeStr(PaymentTypeConstant.translate(so.getOrderPaymentType()));
        // String orderCode = so.getOrderCode();
        // List<SoItemDTO> itemDtoList =
        // soItemFacade.querySoItemListByOrderCode(orderCode);

        List<SoItemDTO> itemDto = soItemFacade.querySoItemListByPackId(packId, platformId);
        List<SoDetailItemVo> itemVoList = new ArrayList<>();
        // if (itemDtoList.size() > 0) {
        // List<Long> skuIdList = new ArrayList<>();
        // for (SoItemDTO it : itemDtoList) {
        // skuIdList.add(it.getSkuId());
        // }
        // List<SoItemSkuAttValueDTO> attValueList =
        // soItemFacade.querySkuValueListBySkuIds(skuIdList, platformId);
        // 组织展示列表
        // for (SoItemDTO d : itemDtoList) {
        // SoDetailItemVo div = new SoDetailItemVo();
        // Long skuId = d.getSkuId();
        // div.setSkuId(skuId);
        // div.setBuyCount(d.getProductItemNum());
        // div.setCode(d.getCode());
        // div.setId(d.getId());
        // div.setName(d.getProductCname());
        // div.setOrderAmount(d.getProductPriceFinal());
        // div.setProductAmount(d.getProductItemAmount());
        // div.setSkuAttValues(new ArrayList<String>());
        // for (SoItemSkuAttValueDTO sav : attValueList) {
        // if (skuId.longValue() == sav.getSkuId().longValue()) {
        // div.getSkuAttValues().add(sav.getAttValueCustom() + ";");
        // }
        // }
        // itemVoList.add(div);
        // }
        // }
        // 判断包裹中是否有该订单项、如果有置为true
        for (SoDetailItemVo soDetailItemVo : itemVoList) {
            for (SoItemDTO soItemDTO : itemDto) {
                if (soDetailItemVo.getId() == soItemDTO.getId()) {
                    soDetailItemVo.setChecked(true);
                }
            }
        }
        jsonResult.setData(itemVoList);
        return jsonResult;
    }

    @Override
    public JsonResult<Map<String, Object>> deleteByOrderCode(String orderCode, Long userId) {
        if (EmptyUtil.isBlank(orderCode))
            return JsonResult.fail("请选择订单");
        soFacade.deleteByOrderCode(orderCode, userId);
        return JsonResult.success();
    }

    @Override
    public String affirmOrderBySoId(String orderCode, Long platformId, Long userId) {
        SoDTO so = soFacade.querySoByOrderCode(orderCode);

        return soFacade.affirmOrderBySoId(so, platformId);
    }

    @Override
    public JsonResult<Map<String, Object>> orderPayConfirm(String orderCode) {
        SoDTO so = soFacade.querySoByOrderCode(orderCode);
        System.out.println("orderPayConfirm:orderCode:{}"+orderCode);
        logger.info("orderPayConfirm:orderCode:{}",orderCode);
        if (so == null)
            return JsonResult.fail("订单不存在");
        // 订单支付状态
        Integer status = so.getOrderPayStatus();

        // 超时判断
        long createTime = so.getCreateTime().getTime();
        long currTime = System.currentTimeMillis();
        // 30*60*1000
        if (currTime - createTime >= PayUtil.ORDER_EXIST_TIME_MS && status == 0) {
            return JsonResult.fail("订单支付超时", BusinessExceptionConstant.ORDER_EXPIRED);
        }
        if (EmptyUtil.isNotEmpty(so.getExt())){
            QmOrderDTO qmOrderDTO=soFacade.queryQmOrderBySoId(so.getId());
            if (Objects.nonNull(qmOrderDTO) && Objects.nonNull(qmOrderDTO.getExpireTime())){
                if (qmOrderDTO.getExpireTime().before(new Date())){
                    return JsonResult.fail("订单支付超时", BusinessExceptionConstant.ORDER_EXPIRED);
                }
            }
        }
        AwaitQueueDTO queue = soFacade.queryAwaiteQueueByOrderId(so.getId());
        Map<String, Object> result = new HashMap<>();
        // 判断等待队列不为空，并且是否成功支付为0失败
        if (EmptyUtil.isNotEmpty(queue) && EmptyUtil.isNotEmpty(queue.getIsPayTrue()) && queue.getIsPayTrue() == 0)
            status = 3;
        logger.info("orderPayConfirm===orderCode:{},payStatus:{}",orderCode,status);
        result.put("payStatus", status);
        result.put("orderInQueue", queue != null);
        return JsonResult.success(result);
    }

    /**
     * 根据订单id修改订单收货人信息
     *
     * @param soVO
     * @param *    @return
     */
    @Override
    public JsonResult<String> updateOrderByOrderId(SoVO soVO) {
        return soFacade.updateOrderByOrderId(SoConverter.toDTO(soVO));
    }

    @Override
    public JsonResult<Map<String, Object>> orderDetail(String orderCode, Long platformId, Long userId,
                                                       Long f, Long clientId) {
        if (EmptyUtil.isBlank(orderCode))
            return JsonResult.fail("请选择订单");
        // 查询订单信息
        SoDTO so = soFacade.queryUndeleteSoByOrderCode(orderCode);
        if (so == null)
            return JsonResult.fail("订单不存在");
		/*if (!userId.equals(so.getUserId()))
			return JsonResult.fail("订单不存在");*/
        SoDetailOneVo vo = new SoDetailOneVo();
        vo.setIfChildOrder("0");
        vo.setCreateTime(com.egeo.utils.DateUtils.getDefaultDateTime(so.getCreateTime()));//下单日期
        UserDTO userById = userFacade.findUserById(so.getUserId());
        vo.setUserMail(userById.getMail());
        vo.setDeliveryFee(so.getDeliveryFee().doubleValue());//运费
        vo.setGoodsAmount(so.getProductAmount().doubleValue());//商品金额
        //如果已支付查询支付时间
        if (so.getOrderPayStatus() != 0) {
            if(EmptyUtil.isNotEmpty(so.getOrderPaymentConfirmDate())){
                vo.setPayTime(com.egeo.utils.DateUtils.getDefaultDateTime(so.getOrderPaymentConfirmDate()));//付款时间
            }
        }
        vo.setOrderPayStatus(so.getOrderPayStatus());
        vo.setOrderConfirmStatus(so.getOrderConfirmStatus());
        SoDeliveryItemDTO soDeliveryItemDTO = new SoDeliveryItemDTO();
        soDeliveryItemDTO.setOrderCode(orderCode);
        List<SoDeliveryItemDTO> soDeliveryItemAll = soDeliveryItemFacade.findSoDeliveryItemAll(soDeliveryItemDTO);
        if(EmptyUtil.isNotEmpty(soDeliveryItemAll)){
            vo.setDeliveryDate(com.egeo.utils.DateUtils.getDefaultDateTime(soDeliveryItemAll.get(0).getDeliveryDate()));//发货日期
        }



        vo.setId(so.getId());
        // 订单总额 = 订单金额 - 优惠金额
        vo.setOrderCode(so.getOrderCode());
        vo.setPaidByCash(so.getOrderPaidByCash().doubleValue());
        vo.setPaidByFubi(so.getOrderPaidByFubi().doubleValue());
        vo.setPaidByJiDian(so.getOrderPaidByJidian().doubleValue());
        vo.setPayTypeName("未知");
        vo.setPayType("-2");
        if(userById !=null &&EmptyUtil.isNotEmpty(userById.getChannelSource()) && userById.getChannelSource().equals(UserChannelSourceEnum.DLF.getChannelSource())){
            if(so.getOrderPaidByCash() !=null && so.getOrderPaidByCash().compareTo(BigDecimal.ZERO) >0){
                vo.setPayTypeName("现金支付");
                if(so.getCashPayType() !=null && so.getCashPayType().intValue() ==1){
                    vo.setPayTypeName("支付宝");
                }else if(so.getCashPayType() !=null && so.getCashPayType().intValue() ==2){
                    vo.setPayTypeName("微信");
                }else if(so.getCashPayType() !=null && so.getCashPayType().intValue() ==3){
                    vo.setPayTypeName("银联");
                }else if(so.getCashPayType() !=null && so.getCashPayType().intValue() ==4){
                    vo.setPayTypeName("建行");
                }
                vo.setPayType("-1");
            }else if(so.getOrderPaidByFubi() !=null && so.getOrderPaidByFubi().compareTo(BigDecimal.ZERO) >0){
                vo.setPayTypeName("餐卡支付");
                vo.setPayType("0");
            }else if(so.getOrderPaidByJidian() !=null && so.getOrderPaidByJidian().compareTo(BigDecimal.ZERO) >0){
                vo.setPayTypeName("积点支付");
                vo.setPayType("5");
            }else if(so.getOrderCardPaid() !=null && so.getOrderCardPaid().compareTo(BigDecimal.ZERO) >0){
                vo.setPayTypeName("卡劵支付");
                vo.setPayType("6");
            }
        }else{
            if(so.getOrderPaidByFubi() !=null && so.getOrderPaidByCash() !=null){
                if(so.getOrderPaidByFubi().compareTo(BigDecimal.ZERO) >0 && so.getOrderPaidByCash().compareTo(BigDecimal.ZERO) >0){
                    //混合支付
                    vo.setPayTypeName("混合支付");
                    vo.setPayType("-2");
                }else if(so.getOrderPaidByCash().compareTo(BigDecimal.ZERO) >0){
                    vo.setPayTypeName("现金支付");
                    if(so.getCashPayType() !=null && so.getCashPayType().intValue() ==1){
                        vo.setPayTypeName("支付宝");
                    }else if(so.getCashPayType() !=null && so.getCashPayType().intValue() ==2){
                        vo.setPayTypeName("微信");
                    }else if(so.getCashPayType() !=null && so.getCashPayType().intValue() ==3){
                        vo.setPayTypeName("银联");
                    }else if(so.getCashPayType() !=null && so.getCashPayType().intValue() ==4){
                        vo.setPayTypeName("建行");
                    }
                    vo.setPayType("-1");
                }else if(so.getOrderPaidByFubi().compareTo(BigDecimal.ZERO) >0){
                    vo.setPayTypeName("积分支付");
                    vo.setPayType("0");
                }else if(so.getOrderPaidByJidian().compareTo(BigDecimal.ZERO) >0){
                    vo.setPayTypeName("积点支付");
                    vo.setPayType("5");
                }else if(so.getOrderCardPaid().compareTo(BigDecimal.ZERO) >0){
                    vo.setPayTypeName("卡劵支付");
                    vo.setPayType("6");
                }
            }
        }
        //商品总价
        vo.setPUAmount(so.getProductAmount());
        vo.setOrderType(so.getSaleWay());
        /*//优惠卷优惠金额
        BigDecimal couponDiscount = BigDecimal.valueOf(0);
        SoItemDTO soItemDTO = new SoItemDTO();
        soItemDTO.setSoId(so.getId());
        List<SoItemDTO> soItemList= soFacade.findSoItemListBySoId(soItemDTO);
        if(EmptyUtil.isEmpty(soItemList)){
            throw new BusinessException("订单项不存在");
        }
        for(SoItemDTO itemDTO:soItemList){
            couponDiscount = couponDiscount.add(itemDTO.getFinalPromotionAver());
        }*/
        vo.setCouponDiscount(so.getCouponDiscount());
        //门店优惠
        vo.setStoreDiscount(so.getStoreDiscount());

        vo.setDeliveryFee(so.getDeliveryFee().doubleValue());

        vo.setOrderAmount(so.getOrderAmountPay().doubleValue());

        Integer cashPayType = so.getCashPayType();
        vo.setPayCashMethod(cashPayType);
        vo.setPayCashMethodStr(
                judgeOrderPayCashMethodShow(cashPayType, so.getOrderConfirmStatus(), so.getOrderPayStatus()));
        vo.setStatus(so.getOrderStatus());
        vo.setStatusStr(OrderConstant.translate(so.getOrderStatus()));
        vo.setUseFubi(so.getUseFubi().intValue() == 1);
        List<SoChildDTO> scList = soFacade.querySoChildListBySoId(so.getId());
        List<Integer> scDeliveryStatusList = new ArrayList<>();
        // 设置话费充值的信息
        String rechangePhone = null;
        Integer thirdpartyType = 0;
        String jumpUrl=null;
        boolean isQc=false;
        for (SoChildDTO sc : scList) {
            scDeliveryStatusList.add(sc.getDeliveryStatus());
            // 通过子订单类型和id 查询第三方订单信息
            // 话费充值
            if (sc.getThirdpartyType() == SoThirdpartyManageImpl.THIRDPARTY_TYPE_PHONE) {
                thirdpartyType = SoThirdpartyManageImpl.THIRDPARTY_TYPE_PHONE;
                SoThirdpartyDTO dto = new SoThirdpartyDTO();
                dto.setSoChildId(sc.getId());
                List<SoThirdpartyDTO> soThirdpartyList = soThirdpartyFacade.findSoThirdpartyAll(dto);
                // 一个子订单对应一个第三方订单
                if (EmptyUtil.isNotEmpty(soThirdpartyList)) {
                    rechangePhone = soThirdpartyList.get(0).getPhone();
                }
            }
            //券仓订单,此时一个母订单只有一个子订单
            if(sc.getThirdpartyType().equals(SoThirdpartyManageImpl.THIRDPARTY_TYPE_QC)){
                isQc = true;
            }else if (sc.getThirdpartyType()== ThirdConst.ThirdPartyType.QM){
                thirdpartyType = ThirdConst.ThirdPartyType.QM;
                jumpUrl=sc.getExt();
            }
        }
        vo.setRechangePhone(rechangePhone);

        // 查询订单的优惠卷信息
        vo.setCouponType(soFacade.findCouponUnitByOrder(so));
        vo.setOrderPromotionDiscount(so.getOrderPromotionDiscount().intValue());

        vo.setOperateStatus(soFacade.judgeOrderOperateStatus(clientId, so.getId(), so.getOrderPayStatus(), so.getOrderConfirmStatus(),
                scDeliveryStatusList, thirdpartyType,so.getLimitCashPayAmount(),so.getOrderAmount(),userId,platformId));

        // 收货地址信息
        // 判断是否展示收货地址信息
        ReceiverAddressDTO addressDto = null;
        boolean isShowAddr = false;
        if (so.getReceiverAddressId() != null) {
            isShowAddr = true;
            addressDto = receiverAddressFacade.findReceiverAddressById(so.getReceiverAddressId());
        } else if (vo.getOperateStatus().equals(Integer.valueOf(2))
                || vo.getOperateStatus().equals(Integer.valueOf(3))) {
            // 电子卡券类商品没有查看物流和确认收货按钮的特殊处理(话费充值类已特殊处理)
            vo.setOperateStatus(4);
        }
        if(isQc&&!vo.getOperateStatus().equals(0)){
            //如果第三方对接参数是券仓,则第三方订单类型为券仓卡券,且订单已付款,则仅仅显示删除订单按钮
            vo.setOperateStatus(Integer.valueOf(4));
        }

        if(so.getOrderStatus()==4){
            //如果是加价购查询优惠券类型
            if(so.getSaleWay()==8){
                vo.setOperateStatus(6);
                List<ExchangeOrderRecordDTO> list=soFacade.findExchangeOrderRecordByOrderCode(so.getOrderCode());
                if(EmptyUtil.isEmpty(list)||list.size()>1){
                    logger.info("[该订单号数据有误,订单详情]orderCode="+so.getOrderCode());
                    throw new BusinessException("该订单号数据有误");
                }
                vo.setExchangeCouponType(list.get(0).getNewCouponType());
            }
        }





        vo.setIsShowAddr(isShowAddr);
        DefaultReceiverInfoVo receiverInfo = new DefaultReceiverInfoVo();
        if (addressDto != null) {
            receiverInfo.setId(addressDto.getId());
            String address = addressDto.getGoodReceiverProvince() + addressDto.getGoodReceiverCity()
                    + addressDto.getGoodReceiverCounty() + addressDto.getGoodReceiverAddress();
            receiverInfo.setAddress(address);
            receiverInfo.setPhoneNo(addressDto.getGoodReceiverMobile());
            receiverInfo.setUserId(addressDto.getUserId());
            receiverInfo.setReceiverName(addressDto.getGoodReceiverName());
        }
        // 查询订单项信息
        List<SoItemDTO> soItems = mergeSoItems(soItemFacade.querySoItemListBySoId(so.getId()));
        // 合并订单项

        List<SoChildDTO> originalSoChildList = soFacade.findAllCountDeliverySoChild(so.getId(), 1);
        Map<Long, SoChildDTO> merchantSoChildMap = new HashMap<>();
        for (SoChildDTO soChild : originalSoChildList) {
            merchantSoChildMap.put(soChild.getPerformingParty(), soChild);
        }

        boolean unitFlag = false;
        Map<Long, List<OrderConfirmGoodsVO>> soItemMap = new HashMap<>();
        Boolean flag = false;
        for (SoItemDTO item : soItems) {
            OrderConfirmGoodsVO goodsVo = new OrderConfirmGoodsVO();
            goodsVo.setNum(item.getPuCount());
            goodsVo.setPrice(item.getPrice().doubleValue());
            goodsVo.setPuId(item.getPuId());
            goodsVo.setPuName(item.getPuName());
            goodsVo.setSaleWay(1);
            goodsVo.setPuImg(item.getPuPicUrl());
            goodsVo.setBuyType(1);
            goodsVo.setChannelProductId(item.getExternalProductId());
            goodsVo.setSource(item.getSource());

            if(item.isJd()) {
                goodsVo.setStandardUnitId(item.getPuId());
                String snapStr = item.getSnapshot();
                if(snapStr==null||snapStr.length()<10) {
                    logger.info("[该订单产品数据,订单详情]orderCode="+so.getOrderCode());
                    continue;
                }
                JdProductDTO standardUnitById = JSON.parseObject(snapStr, JdProductDTO.class);
                flag = true;
                goodsVo.setProductUnitSerialNumber("jd-"+item.getPuId());
                goodsVo.setIsOwnMerchant(0);
                List<OrderConfirmGoodsVO> goodsList = soItemMap.get(6l);
                if(goodsList==null) {
                    goodsList = new ArrayList<>();
                    soItemMap.put(6l, goodsList);
                }
                goodsList.add(goodsVo);
                if (item.getUnitExist() == 1) {
                    unitFlag = true;
                }
            }else if(item.isQM()) {
                goodsVo.setStandardUnitId(item.getPuId());
                String snapStr = item.getSnapshot();
                if(snapStr==null||snapStr.length()<10) {
                    logger.info("[该订单产品数据,订单详情]orderCode="+so.getOrderCode());
                    continue;
                }
                flag = true;
                goodsVo.setProductUnitSerialNumber("qm-"+item.getPuId());
                goodsVo.setIsOwnMerchant(0);
                List<OrderConfirmGoodsVO> goodsList = soItemMap.get(ThirdConst.Merchant.QM);
                if(goodsList==null) {
                    goodsList = new ArrayList<>();
                    soItemMap.put(ThirdConst.Merchant.QM, goodsList);
                }
                goodsList.add(goodsVo);
                if (item.getUnitExist() == 1) {
                    unitFlag = true;
                }
            }else if(item.isCake()){
                goodsVo.setStandardUnitId(item.getPuId());
                String snapStr = item.getSnapshot();
                if(snapStr==null||snapStr.length()<10) {
                    logger.info("[该订单产品数据,订单详情]orderCode="+so.getOrderCode());
                    continue;
                }
                flag = true;
                goodsVo.setProductUnitSerialNumber("cake-"+item.getPuId());
                goodsVo.setIsOwnMerchant(0);
                List<OrderConfirmGoodsVO> goodsList = soItemMap.get(7L);
                if(goodsList==null) {
                    goodsList = new ArrayList<>();
                    soItemMap.put(7L, goodsList);
                }
                goodsList.add(goodsVo);
                if (item.getUnitExist() == 1) {
                    unitFlag = true;
                }
            }else if(item.isWorld()){
                goodsVo.setStandardUnitId(item.getPuId());
                String snapStr = item.getSnapshot();
                if(snapStr==null||snapStr.length()<10) {
                    logger.info("[该订单产品数据,订单详情]orderCode="+so.getOrderCode());
                    continue;
                }
                flag = true;
                goodsVo.setProductUnitSerialNumber("world-"+item.getPuId());
                goodsVo.setIsOwnMerchant(0);
                List<OrderConfirmGoodsVO> goodsList = soItemMap.get(8L);
                if(goodsList==null) {
                    goodsList = new ArrayList<>();
                    soItemMap.put(8L, goodsList);
                }
                goodsList.add(goodsVo);
                if (item.getUnitExist() == 1) {
                    unitFlag = true;
                }
            }else {
                CommodityProductUnitDTO pu = productFacade.queryPuById(item.getPuId());
                StandardUnitDTO standardUnitById = productUnitFacade.findStandardUnitById(pu.getStandardUnitId());
                goodsVo.setPreSellDay(standardUnitById.getPresellPeriod());//预售期
                goodsVo.setSaleWay(standardUnitById.getSaleWay());
                goodsVo.setPuImg(item.getPuPicUrl());
                goodsVo.setPuName(item.getPuName());
                goodsVo.setBuyType(standardUnitById.getBuyType());
                if(standardUnitById.getBuyType()!=3){
                    flag = true;
                }
                if(pu !=null){
                    goodsVo.setStandardUnitId(pu.getStandardUnitId());
                }
                goodsVo.setProductUnitSerialNumber(queryProductUnitSerialNumber(item.getPuId()));
                Long mId = standardUnitById.getMerchantId();
                goodsVo.setIsOwnMerchant(mId.equals(1L) ? 1 : 0);
                if (soItemMap.containsKey(mId)) {
                    soItemMap.get(mId).add(goodsVo);
                } else {
                    List<OrderConfirmGoodsVO> goodsList = new ArrayList<>();
                    goodsList.add(goodsVo);
                    soItemMap.put(mId, goodsList);
                }

                if (item.getUnitExist() == 1) {
                    unitFlag = true;
                }
            }
        }

        //商品全部都是仅积分支付商品，且操作状态是0（取消，其他，积分都有）
        if(!flag){
            if(vo.getOperateStatus()==0){
                vo.setOperateStatus(8);
            }

        }
        if (Objects.equals(thirdpartyType,ThirdConst.ThirdPartyType.QM)){
            vo.setOperateStatus(9);//清美订单，直接跳转到清美页面，操作按钮不显示
            vo.setJumpUrl(jumpUrl);
        }
        StoreDTO store = storeFacade.findStoreById(so.getStoreId());

        List<Map<String, Object>> goodGroupList = new ArrayList<>();
        for (Entry<Long, SoChildDTO> entry : merchantSoChildMap.entrySet()) {
            Map<String, Object> map = new HashMap<>();
            MerchantDTO merchant = merchantFacade.findMerchantById(entry.getKey());
            map.put("merchantId", entry.getKey());
            map.put("merchantName", merchant.getName());
            map.put("isOwnMerchant", entry.getKey().equals(1L) ? 1 : 0);
            map.put("storeName", store.getName());
            map.put("deliveryPrice", entry.getValue().getDeliveryFee());
            map.put("remark", entry.getValue().getRemark());
            map.put("goodsList", soItemMap.get(entry.getKey()));
            goodGroupList.add(map);
        }
        //如果是券仓卡券订单则存在卡券,否则按照是否存在unit库存来判断
        vo.setUnitExist(unitFlag);
        if(isQc){
            vo.setUnitExist(isQc);
        }
        // 查询订单相关的卡密信息
        List<SimpleECardVO> cardList = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        if (unitFlag||isQc) {
            Integer companyType = productFacade.findCompanyTypeByUserId(userId);
            if (companyType != 0) {
                SimpleECardVO svo = new SimpleECardVO();
                svo.setCardNumber(SequenceUtil.genCardNumberCode(userId));
                svo.setCardThick("123456");
                cardList.add(svo);
            } else {
                List<ECardDTO> cards = promotionFacade.queryECardListByOrderCode(orderCode);
                for (ECardDTO c : cards) {
                    SimpleECardVO svo = new SimpleECardVO();
                    svo.setCardNumber(c.getCardNumber());
                    svo.setShortUrl(c.getShortUrl());
                    if(EmptyUtil.isEmpty(c.getEndTime())){
                        svo.setEndTime(null);
                    }else{
                        svo.setEndTime(format.format(c.getEndTime()));
                    }
                    //如果不是券仓卡券且没有卡密
                    if(!isQc&&EmptyUtil.isEmpty(c.getCardThick())){
                        throw new BusinessException("电子卡券未分配密码");
                    }
                    if(EmptyUtil.isNotEmpty(c.getCardThick())){
                        String cipher = c.getCardThick();
                        String uuid = c.getUuid();
                        CardSaltDTO salt = accountFacade.queryCardSaltByUUID(uuid);
                        if (salt == null) {
                            svo.setCardThick("该电子卡券密码有误");
                        } else {
                            String cipherDecrypt;
                            try {
                                cipherDecrypt = QEncodeUtil.aesDecrypt(cipher, salt.getSaltValue());
                            } catch (Exception e) {
                                cipherDecrypt = "该电子卡券密码存在异常";
                                e.printStackTrace();
                            }
                            svo.setCardThick(cipherDecrypt);
                        }
                    }
                    cardList.add(svo);
                }
            }
        }
        // 查询订单相关的发票信息
        Map<String, Object> invoiceInfo = null;
        if (so.getInvoiceId() != null) {
            // 查询公共信息
            SoInvoiceDTO soInvoiceDTO = new SoInvoiceDTO();
            soInvoiceDTO.setSoChildId(-1L);
            soInvoiceDTO.setSoId(so.getId());
            List<SoInvoiceDTO> soInvoiceList = soInvoiceFacade.findSoInvoiceAll(soInvoiceDTO);
            Map<String, Object> invoiceBaseInfo = new HashMap<>();
            if (EmptyUtil.isNotEmpty(soInvoiceList)) {
                SoInvoiceDTO publicInvoice = soInvoiceList.get(0);
                invoiceBaseInfo.put("invoiceExists", true);
                invoiceBaseInfo.put("title", publicInvoice.getInvoiceTitleContent());
                invoiceBaseInfo.put("invoiceType", publicInvoice.getInvoiceForm());
                invoiceBaseInfo.put("invoiceContentType", publicInvoice.getInvoiceContentType());
                invoiceBaseInfo.put("registerAddr", publicInvoice.getRegisterAddr());
                invoiceBaseInfo.put("registerTel", publicInvoice.getRegisterTel());
                invoiceBaseInfo.put("depositBank", publicInvoice.getDepositBank());

                String bankAccount = publicInvoice.getBankAccount();
                // 银行账户脱敏处理
                if (bankAccount != null && bankAccount.length() > 4) {

                    String displayStr = bankAccount.substring(bankAccount.length() - 4, bankAccount.length());
                    int hideStrCount = bankAccount.length() - 4;
                    String hideStr = "";
                    for (int i = 0; i < hideStrCount; i++) {
                        hideStr += "*";
                    }
                    bankAccount = hideStr + displayStr;
                }

                invoiceBaseInfo.put("bankAccount", bankAccount);

            } else {
                invoiceBaseInfo.put("invoiceExists", false);
                invoiceBaseInfo.put("title", null);
                invoiceBaseInfo.put("invoiceType", null);
                invoiceBaseInfo.put("invoiceContentType", null);
                invoiceBaseInfo.put("registerAddr", null);
                invoiceBaseInfo.put("registerTel", null);
                invoiceBaseInfo.put("depositBank", null);
                invoiceBaseInfo.put("bankAccount", null);
            }

            Map<String, Object> invoice = new HashMap<>();
            // 查询所有子订单开票信息(状态)
            List<SoChildDTO> scs = soFacade.querySoChildListBySoId(so.getId());
            List<SoChildInvoiceVO> voList = new ArrayList<>();
            for (SoChildDTO sc : scs) {
                SoChildInvoiceVO scVo = new SoChildInvoiceVO();

                scVo.setChildCode(sc.getChildCode());
                scVo.setId(sc.getId());
                SoInvoiceDTO inv = soInvoiceFacade.querySoInvoiceBySoChildId(sc.getId());
                if (inv != null) {
                    scVo.setInvoiceId(inv.getId());
                    scVo.setInvoiceCode(inv.getInvoiceCode());
                    scVo.setFinanceStatus(inv.getInvoiceStatus());
                    scVo.setRemark(inv.getInvoiceRemark());
                    scVo.setInvoiceType(inv.getInvoiceForm());
                }
                voList.add(scVo);
            }

            invoice.put("invoiceBaseInfo", invoiceBaseInfo);
            invoice.put("childInvoiceList", voList);
            invoiceInfo = invoice;
        }

        Map<String, Object> data = new HashMap<>();
        data.put("orderInfo", vo);
        data.put("goodGroupList", goodGroupList);
        data.put("addressInfo", receiverInfo);
        data.put("cardList", cardList);
        data.put("invoiceInfo", invoiceInfo);


        return JsonResult.success(data);
    }

    private String queryProductUnitSerialNumber(Long puId) {
        CommodityProductUnitDTO dto = new CommodityProductUnitDTO();
        dto.setId(puId);
        CommodityProductUnitDTO commodityProductUnitDTO = soItemFacade.findPU(dto);
        return commodityProductUnitDTO != null ? commodityProductUnitDTO.getProductUnitSerialNumber() : "";
    }

    /**
     * 判断订单支付方式显示字符串:无/未选择/支付宝/微信
     *
     * @param cashPayType
     * @param orderConfirmStatus
     * @param orderPayStatus
     * @return
     */
    private String judgeOrderPayCashMethodShow(Integer cashPayType, Integer orderConfirmStatus,
                                               Integer orderPayStatus) {
        if (orderConfirmStatus == 0) {
            return "未选择";
        } else if (orderConfirmStatus == 1 || orderConfirmStatus == 3) {
            return cashPayType == null ? "无" : (cashPayType == 1 ? "支付宝" : "微信");
        } else if (orderConfirmStatus == 2) {
            if (orderPayStatus == 0) {
                // 未支付
                return "未选择";
            } else {
                // 已支付或已退款
                return cashPayType == null ? "无" : (cashPayType == 1 ? "支付宝" : "微信");
            }
        }
        return "无";
    }

    private List<SoItemDTO> mergeSoItems(List<SoItemDTO> soItemDTOList) {
        List<SoItemDTO> res = new ArrayList<>();
        outter:
        for (SoItemDTO dto : soItemDTOList) {
            // 查找在res中是否puid重合的项,若有,累计数量,若无,加入res
            long puId = dto.getPuId();
            for (SoItemDTO dto_ : res) {
                if (dto_.getPuId().longValue() == puId) {
                    dto_.setPuCount(dto_.getPuCount() + dto.getPuCount());
                    continue outter;
                }
            }
            res.add(dto);
        }
        return res;
    }

    @Override
    public JsonResult<Map<String, Object>> orderPayEndTime(String orderCode) {
        if (EmptyUtil.isBlank(orderCode))
            return JsonResult.fail("请选择订单");
        SoDTO so = soFacade.querySoByOrderCode(orderCode);
        if (so == null)
            return JsonResult.fail("订单不存在");
        // Integer status = so.getOrderStatus();
        // if (status != 0)
        // return JsonResult.fail("订单状态错误");
        Date createTime_ = so.getCreateTime();
        Long createTime = createTime_.getTime() / 1000l;
        Long current = System.currentTimeMillis() / 1000l;
        Long timeLeft = current - createTime - PayUtil.ORDER_EXIST_TIME_S;
        if (timeLeft < 0) {
            Map<String, Object> data = new HashMap<>();
            data.put("timeLeft", -timeLeft);
            return JsonResult.success(data);
        }
        return JsonResult.fail("订单支付超时", BusinessExceptionConstant.ORDER_EXPIRED);
    }

    @Override
    public JsonResult<Map<String, Object>> findOrderDetail(Long orderId, Long platformId) {
        if (orderId == null) {
            return JsonResult.fail("请选择订单");
        }

        // 获取订单信息
        SoDTO so = soFacade.querySoById(orderId);
        if (so == null)
            return JsonResult.fail("订单不存在");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("orderCode", so.getOrderCode());
        map.put("status", so.getOrderStatus());
        map.put("createTime", so.getCreateTime());
        map.put("platformId", so.getPlatformId());
        //查询门店信息
        StoreDTO storeDTO=soFacade.findStoreById(so.getStoreId());
        map.put("storeName", storeDTO.getName());
        map.put("isDetail", storeDTO.getIsDetail());
        // 平台名称
        PlatformDTO platform = userFacade.queryPlatformById(so.getPlatformId());
        map.put("platformName", platform == null ? "" : platform.getName());
        map.put("orderConfirmStatus", so.getOrderConfirmStatus());
        map.put("orderChannel", so.getOrderChannel());
        map.put("orderAutoCompleteDate", so.getOrderAutoCompleteDate());
        map.put("paymentType", translateOrderCashPayType(so.getCashPayType()));
        map.put("orderPayStatus", so.getOrderPayStatus());
        map.put("orderPaymentConfirmDate", so.getOrderPaymentConfirmDate());
        // 订单总额
        map.put("orderAmount", so.getOrderAmount());
        // 优惠金额
        map.put("orderAmountBenefit", so.getOrderPromotionDiscount());
        // 结算金额
        map.put("orderAmountPay", so.getOrderAmountPay());

        map.put("soAmount",so.getOrderAmount());//总金额=商品+运费
        map.put("discount", so.getOrderPromotionDiscount());//总优惠
        List<SoChildDTO> soChildDTOList = soFacade.findSoChildBySoId(so.getId());
        /*
        for (SoChildDTO soChildDTO : soChildDTOList) {
            sum1 = sum1.add(soChildDTO.getAmount());
        }*/
       /* BigDecimal puAmount = so.getOrderAmount().subtract(so.getOrderDeliveryFee());
        //根据soid查询soitem
        SoItemDTO soItemDTO = new SoItemDTO();
        soItemDTO.setSoId(so.getId());
        List<SoItemDTO> allSoItem = soItemFacade.findAllSoItem(soItemDTO);
        if(EmptyUtil.isEmpty(allSoItem)){
            throw new BusinessException("该订单不存在订单项");
        }

        BigDecimal couponDiscount=new BigDecimal(0);
        for(SoItemDTO dto:allSoItem){
            couponDiscount = couponDiscount.add(dto.getFinalPromotionAver());
        }*/

        map.put("couponDiscount",so.getCouponDiscount());//优惠卷优惠金额
        //门店优惠
        map.put("storeDiscount",so.getStoreDiscount());
        map.put("payAmount", so.getOrderAmountPay());

        map.put("orderPaidByCash", so.getOrderPaidByCash());
        map.put("orderPaidByJidian", so.getOrderPaidByJidian());
        map.put("orderPaidByFubi", so.getOrderPaidByFubi());
        map.put("orderCardPaid", so.getOrderCardPaid());
        map.put("orderDeliveryFee", so.getDeliveryFee());
        map.put("orderChannel", so.getOrderChannel());
        map.put("saleWay", so.getSaleWay());
        map.put("remark", so.getRemark());
        UserDTO userDTO = soFacade.finduserByUserId(so.getUserId());
        if (userDTO != null) {
            // 邮箱是买家账户
            map.put("mail", userDTO.getMail());
        }

        UserExtendDTO ue = userFacade.queryUserExtendById(so.getUserId());
        if (ue != null) {
            map.put("userName", ue.getName());
        }
        ReceiverAddressDTO ra = receiverAddressFacade.findReceiverAddressById(so.getReceiverAddressId());
        // 电话是收货人邮箱
        map.put("mobile", ra == null ? "" : ra.getGoodReceiverMobile());
        map.put("isShowAddr", so.getReceiverAddressId() == null ? 0 : 1);
        // map.put("sex", ue.getSex());
        map.put("useFubi", so.getUseFubi());

        return JsonResult.success(map);
    }

    @Override
    public JsonResult<Map<String, Object>> findAllSOChild(SoChildDTO soChildDTO, Pagination page) {
        Long soId = soChildDTO.getSoId();
        SoDTO so = soFacade.querySoById(soId);
        if (so == null)
            return JsonResult.fail("该子订单所属的母订单不存在");
        // 根据条件查询子订单
        List<SoChildDTO> scList = soFacade.querySoChildListBySoId(soId);
        List<SoChildVO> SoChildVOList = new ArrayList<>();
        // 遍历子订单
        for (SoChildDTO sc : scList) {

            SoChildVO childVO = SoChildConverter.toVO(sc);
            // 在so_item查询拓展信息
            SoItemDTO soItemDTO = new SoItemDTO();
            soItemDTO.setSoChildId(sc.getId());
            List<SoItemDTO> soItemDTO2List = soItemFacade.findAllSoItem(soItemDTO);
            if (soItemDTO2List != null && !soItemDTO2List.isEmpty()) {
                // childVO.setPromotionAver(soItemDTO2List.get(0).getPromotionAver());
                // 查询此订单的所属用户id
                UserDTO finduserByUserId = soFacade.finduserByUserId(soItemDTO2List.get(0).getUserId());
                // 设置用户邮箱
                childVO.setMail(finduserByUserId.getMail());
            }
            //查询快递公司名称
            String deliveryCompanyName=soFacade.findDeliveryCompanyNameBySoChildId(sc.getId());
            childVO.setDeliveryCompanyName(deliveryCompanyName);

            // 设置子订单的平摊优惠金额和原价总金额
           /* BigDecimal amount = BigDecimal.ZERO;
            BigDecimal promotionAver = BigDecimal.ZERO;
            for (SoItemDTO soItemDTO_ : soItemDTO2List) {
                amount = amount.add(soItemDTO_.getPrice().multiply(new BigDecimal(soItemDTO_.getPuCount())));
                promotionAver = promotionAver.add(soItemDTO_.getFinalPromotionAver());
            }
            childVO.setAmount(amount);
            childVO.setPromotionAver(promotionAver);
            childVO.setAmount(sc.getAmount());
            SoItemDTO soItem = new SoItemDTO();
            soItem.setSoChildId(sc.getId());
            List<SoItemDTO> allSoItem = soItemFacade.findAllSoItem(soItem);
            BigDecimal allAmount = new BigDecimal(0);
            BigDecimal couponDiscount = BigDecimal.valueOf(0);
            for (SoItemDTO dto : allSoItem) {
                allAmount = allAmount.add(dto.getPrice().multiply(BigDecimal.valueOf(dto.getPuCount())));
                couponDiscount=couponDiscount.add(dto.getFinalPromotionAver());
            }*/

            BigDecimal productAmount = new BigDecimal(0);
            if (sc.getProductAmount() != null) {
                productAmount = sc.getProductAmount();
            }
            BigDecimal deliveryFee = new BigDecimal(0);
            if (sc.getDeliveryFee() != null) {
                deliveryFee = sc.getDeliveryFee();
            }
            BigDecimal couponDiscount = new BigDecimal(0);
            if (sc.getCouponDiscount() != null) {
                couponDiscount = sc.getCouponDiscount();
            }
            BigDecimal storeDiscount = new BigDecimal(0);
            if (sc.getStoreDiscount() != null) {
                storeDiscount = sc.getStoreDiscount();
            }
            BigDecimal amount = new BigDecimal(0);
            if (sc.getAmount() != null) {
                amount = sc.getAmount();
            }
            BigDecimal deliveryFeeDiscount = new BigDecimal(0);
            if (sc.getDeliveryFeeDiscount() != null) {
                deliveryFeeDiscount = sc.getDeliveryFeeDiscount();
            }
            BigDecimal thirdpatyDiscountAmount = new BigDecimal(0);
            if (sc.getThirdpatyDiscountAmount() != null) {
                thirdpatyDiscountAmount = sc.getThirdpatyDiscountAmount();
            }
            childVO.setSoChildAmount(productAmount.add(deliveryFee));//商品总价格
            childVO.setStoreDiscount(storeDiscount);//门店优惠


            childVO.setDiscount(couponDiscount.add(storeDiscount).add(thirdpatyDiscountAmount));//总优惠
            childVO.setPayAmount(amount.add(deliveryFee).subtract(deliveryFeeDiscount));//实付金额
            childVO.setAmount(childVO.getPayAmount());
            childVO.setThirdpartySoChildId(EmptyUtil.isNotEmpty(sc.getThirdpartySoChildCode())?String.valueOf(sc.getThirdpartySoChildCode()):
                    (EmptyUtil.isNotEmpty(sc.getThirdpartySoChildId())?String.valueOf(sc.getThirdpartySoChildId()):null));
            childVO.setSoThirdpartySoChildStatusStr(getThirdpartySoChildStatusString(sc.getThirdpartySoChildStatus()));




            Long lastModifierId = childVO.getLastOperatorId();
            if (lastModifierId != null) {
                // 查询最后修改人
                UserExtendDTO lastMod = userFacade.queryUserExtendById(lastModifierId);
                childVO.setLastOperatorName(lastMod.getName());
            }
            Long ordinaryId = childVO.getOrdinaryId();
            if (ordinaryId != null) {
                // 查询原始子订单id
                SoChildDTO ord = soFacade.findSoChildById(ordinaryId);
                childVO.setOrdinaryCode(ord.getChildCode());
            }
            // 查询订单的支付状态
            SoDTO soDTO = new SoDTO();
            soDTO.setId(sc.getSoId());
            SoDTO soDTO2 = soFacade.findSoById(soDTO);
            childVO.setOrderPayStatus(soDTO2.getOrderPayStatus());
            childVO.setOrderCode(so.getOrderCode());
            SoChildVOList.add(childVO);
        }
        return JsonResult.success("list", SoChildVOList);
    }

    @Override
    public JsonResult<Map<String, Object>> exportSoChild(Long orderId, Long platformId) {
        // 查询订单信息
        SoDTO so = soFacade.querySoById(orderId);
        if (so == null)
            return JsonResult.fail("订单不存在");
        // 查询订单用户信息
        UserExtendDTO ue = userFacade.queryUserExtendById(so.getUserId());
        UserDTO u = userFacade.queryUserById(so.getUserId());
        if (ue == null || u == null) {
            return JsonResult.fail("用户信息异常");
        }
        // 查询用户公司
        CompanyDTO company = userFacade.queryCompanyById(u.getCompanyId());
        // 查询发票公共信息
        SoInvoiceDTO invoice = soInvoiceFacade.queryMainSoInvoiceByOrderId(orderId);
        // 查询所有订单项
        List<SoItemDTO> items = soItemFacade.querySoItemListBySoId(orderId);
        // 查询所有子订单
        // List<SoChildDTO> scs=soFacade.querySoChildListBySoId(orderId);

        List<SoChildExportVO> voList = new ArrayList<>();

        for (SoItemDTO it : items) {
            SoChildExportVO vo = new SoChildExportVO();
            // 查询子订单信息
            SoChildDTO sc = soFacade.findSoChildById(it.getSoChildId());
            vo.setChildCode(sc.getChildCode());
            vo.setCompanyName(company == null ? "" : company.getCompanyName());
            // 查询物流信息
            List<SoPackageDTO> packs = soPackageFacade.queryPackageBySoChildId(sc.getId());
            if (packs != null && packs.size()>0) {
                vo.setDeliveryCode(packs.get(0).getDeliveryCode());
                vo.setDeliveryCompany(packs.get(0).getDeliveryCompanyName());

            } else {
                vo.setDeliveryCode("");
                vo.setDeliveryCompany("");

            }
            vo.setEmail(u.getMail());
            if (invoice != null) {
                vo.setInvoiceContent(invoice.getInvoiceContentType().intValue() == 0 ? "商品明细" : "商品类别");
                vo.setInvoiceExist("是");
                vo.setInvoiceTitle(invoice.getInvoiceTitleContent());
                vo.setInvoiceType(invoice.getInvoiceForm().intValue() == 0 ? "纸质发票" : "电子发票");
                vo.setTitleType(invoice.getInvoiceTitleType().intValue() == 0 ? "个人" : "公司");
            } else {
                vo.setInvoiceContent("");
                vo.setInvoiceExist("否");
                vo.setInvoiceTitle("");
                vo.setInvoiceType("");
                vo.setTitleType("");
            }
            vo.setNum(it.getPuCount());
            vo.setOrderCode(so.getOrderCode());
            vo.setPrice(it.getPrice().doubleValue());
            // 查询商品信息
            CommodityProductUnitDTO pu = productFacade.queryPuById(it.getPuId());
            if (pu != null) {
                vo.setPuName(pu.getName());
            } else {
                vo.setPuName("");
            }
            // 查询收获地址信息
            ReceiverAddressDTO ra = receiverAddressFacade.findReceiverAddressById(sc.getReceiverAddressId());
            if (ra != null) {
                vo.setReceiverAddress(ra.getGoodReceiverProvince() + ra.getGoodReceiverCity()
                        + ra.getGoodReceiverCounty() + ra.getGoodReceiverAddress());
                vo.setReceiverMobile(ra.getGoodReceiverMobile());
                vo.setReceiverName(ra.getGoodReceiverName());
            } else {
                vo.setReceiverAddress("");
                vo.setReceiverMobile("");
                vo.setReceiverName("");
            }
            vo.setSum(vo.getNum() * vo.getPrice());
            vo.setUserCode(ue.getMemberCode());
            vo.setUserName(ue.getName());

            voList.add(vo);
        }
        // for (SoChildDTOCondition soChildDTOCondition :
        // soChildDTOConditionList) {
        // // SoInvoiceDTO soInvoiceDTO = new SoInvoiceDTO();
        // // soInvoiceDTO.setId(soChildDTOCondition.getInvoiceId());
        // SoInvoiceDTO soInvoiceDto =
        // soInvoiceFacade.findSoInvoiceById(soChildDTOCondition.getInvoiceId());
        //
        // // 查询订单所属用户的邮箱
        // UserDTO userDto =
        // soFacade.finduserByUserId(soInvoiceDto.getUserId());
        // // 查询母订单的详情
        // SoDTO soDTO = new SoDTO();
        // soDTO.setId(soChildDTOCondition.getSoId());
        // SoDTO soById = soFacade.findSoById(soDTO);
        //
        // // 查询soItem的详情
        // SoItemDTO soItemDTO = new SoItemDTO();
        // soItemDTO.setSoChildId(soChildDTOCondition.getId());
        // List<SoItemDTO> findAllSoItem =
        // soItemFacade.findAllSoItem(soItemDTO);
        //
        // // 查询最后次修改人的信息
        // UserDTO userDto2 =
        // soFacade.finduserByUserId(soChildDTOCondition.getLastOperatorId());
        //
        // soChildDTOCondition.setInvoiceValue(soInvoiceDto.getInvoiceValue());
        // soChildDTOCondition.setInvoiceStatus(soInvoiceDto.getInvoiceStatus());
        // soChildDTOCondition.setInvoiceCode(soInvoiceDto.getInvoiceCode());
        // soChildDTOCondition.setInvoiceValue(soInvoiceDto.getInvoiceValue());
        // soChildDTOCondition.setMail(userDto.getMail());
        // soChildDTOCondition.setOrderPayStatus(soById.getOrderPayStatus());
        // soChildDTOCondition.setRemark(soById.getRemark());
        // soChildDTOCondition.setPromotionAver(findAllSoItem.get(0).getPromotionAver());
        // soChildDTOCondition.setLastOperatorMail(userDto2.getMail());
        // }
        // // 处理已经封装好的 soChildDTOConditionList
        // if (soChildDTOConditionList.size() == 0) {
        // return JsonResult.fail("数据不存在");
        // }
        return wirteSoChildDetailInExcel(voList);
    }

    private JsonResult<Map<String, Object>> wirteSoChildDetailInExcel(List<SoChildExportVO> voList) {
        Workbook wsd = new XSSFWorkbook();
        Sheet cloneSheet = wsd.createSheet("订单明细");
        String[] headArr = {"订单号", "子订单编号", "快递单号", "物流公司", "收货人", "收货地址", "联系电话", "商品名称", "数量", "单价", "总价", "会员姓名",
                "会员编号", "会员公司", "用户邮箱", "是否开发票", "发票抬头", "发票内容", "发票类型", "抬头类型"};

        Row head = cloneSheet.createRow(0);
        for (int i = 0; i < headArr.length; i++) {
            head.createCell(i).setCellValue(headArr[i]);
        }

        for (int i = 0; i < voList.size(); i++) {
            SoChildExportVO vo = voList.get(i);
            Row row = cloneSheet.createRow(i + 1);
            row.createCell(0).setCellValue(vo.getOrderCode());
            row.createCell(1).setCellValue(vo.getChildCode());
            row.createCell(2).setCellValue(vo.getDeliveryCode());
            row.createCell(3).setCellValue(vo.getDeliveryCompany());
            row.createCell(4).setCellValue(vo.getReceiverName());
            row.createCell(5).setCellValue(vo.getReceiverAddress());
            row.createCell(6).setCellValue(vo.getReceiverMobile());
            row.createCell(7).setCellValue(vo.getPuName());
            row.createCell(8).setCellValue(vo.getNum());
            row.createCell(9).setCellValue(vo.getPrice());
            row.createCell(10).setCellValue(vo.getSum());
            row.createCell(11).setCellValue(vo.getUserName());
            row.createCell(12).setCellValue(vo.getUserCode());
            row.createCell(13).setCellValue(vo.getCompanyName());
            row.createCell(14).setCellValue(vo.getEmail());
            row.createCell(15).setCellValue(vo.getInvoiceExist());
            row.createCell(16).setCellValue(vo.getInvoiceTitle());
            row.createCell(17).setCellValue(vo.getInvoiceContent());
            row.createCell(18).setCellValue(vo.getInvoiceType());
            row.createCell(19).setCellValue(vo.getTitleType());
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            wsd.write(bos);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.fail("导出失败!");
        }
        String upload = uploadService.fastDFSUpload(bos.toByteArray(), "xlsx");
        return JsonResult.success("url", upload);
    }

    @Override
    public JsonResult<Map<String, Object>> findSochildById(Long id) {
        if (id == null)
            return JsonResult.fail("请选择子订单");
        SoChildDTO sc = soFacade.findSoChildById(id);

        Map<String, Object> map = new HashMap<>();
        map.put("id", sc.getId());
        map.put("childCode", sc.getChildCode());
        String ordinaryChildCode = "";
        if (sc.getOrdinaryId() != null) {
            SoChildDTO ordinarySoChild = soFacade.findSoChildById(sc.getOrdinaryId());
            ordinaryChildCode = ordinarySoChild.getChildCode();
        }
        map.put("ordinaryChildCode", ordinaryChildCode);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        map.put("createTime", sdf.format(sc.getCreateTime()));
        Long loid = sc.getLastOperatorId();
        if (loid != null) {
            UserDTO ue = userFacade.queryUserById(loid);
            map.put("lastOperator", ue.getMail());
        } else {
            map.put("lastOperator", "");
        }
        if (sc.getLastOperateTime() == null) {
            map.put("lastOperateTime", "");
        } else {
            map.put("lastOperateTime", sdf.format(sc.getLastOperateTime()));
        }

        BigDecimal productAmount = sc.getProductAmount() != null ? sc.getProductAmount() : new BigDecimal(0);
        BigDecimal deliveryFee = sc.getDeliveryFee() != null ? sc.getDeliveryFee() : new BigDecimal(0);
        BigDecimal deliveryFeeDiscount = sc.getDeliveryFeeDiscount() != null ? sc.getDeliveryFeeDiscount() : new BigDecimal(0);
        BigDecimal couponDiscount = sc.getCouponDiscount() != null ? sc.getCouponDiscount() : new BigDecimal(0);
        BigDecimal storeDiscount = sc.getStoreDiscount() != null ? sc.getStoreDiscount() : new BigDecimal(0);
        BigDecimal amount = sc.getAmount() != null ? sc.getAmount() : new BigDecimal(0);

        map.put("orderAmount", productAmount.add(deliveryFee).setScale(2).toString());
        map.put("discount", couponDiscount.add(storeDiscount).add(deliveryFeeDiscount).setScale(2).toString());
        map.put("couponDiscount", couponDiscount.setScale(2).toString());
        map.put("payAmount", amount.add(deliveryFee).subtract(deliveryFeeDiscount).setScale(2).toString());
        map.put("deliverFee", deliveryFee.setScale(2).toString());
        map.put("storeDiscount", storeDiscount.setScale(2).toString());
        map.put("deliverFeeDiscount", deliveryFeeDiscount.setScale(2).toString());
        if (sc.getPerformingParty() == null) {
            map.put("performingParty", "");
        } else {
            map.put("performingParty", sc.getPerformingParty().intValue() == 0 ? "自营" : "第三方");
        }
        // 查询运单信息
        List<SoPackageDTO> packs = soPackageFacade.queryPackageBySoChildId(sc.getId());
        if (packs == null || packs.size()==0) {
            map.put("deliveryCode", "");
            map.put("deliveryCompany", "");
        } else {
            map.put("deliveryCode", packs.get(0).getDeliveryCode());
            map.put("deliveryCompany", packs.get(0).getDeliveryCompanyName());
        }
        ReceiverAddressDTO ra =  null;
        if (EmptyUtil.isNotEmpty(sc.getReceiverAddressId())){
            ra=receiverAddressFacade.findReceiverAddressById(sc.getReceiverAddressId());
        }
        if (Objects.nonNull(ra)) {
            map.put("receiverName",ra.getGoodReceiverName());
            map.put("receiverMobile",ra.getGoodReceiverMobile());
            map.put("receiverAddress",ra.getGoodReceiverProvince() + ra.getGoodReceiverCity()
                    + ra.getGoodReceiverCounty() + ra.getGoodReceiverAddress());
        }else {
            map.put("receiverName","");
            map.put("receiverMobile","");
            map.put("receiverAddress","");
        }
        map.put("deliveryStatus", sc.getDeliveryStatus());
        // 发票信息
        SoInvoiceDTO inv = soInvoiceFacade.querySoInvoiceBySoChildId(sc.getId());
        if (inv == null) {
            map.put("invoiceStatus", "无需发票");
            map.put("invoiceAmount", 0);
        } else {
            map.put("invoiceStatus", inv.getInvoiceStatus().intValue() == 0 ? "待开票" : "已开票");
            map.put("invoiceAmount", inv.getInvoiceValue());
        }
        map.put("afterSaleStatus", "");
        return JsonResult.success("soChildInfo", map);
    }

    @Override
    public JsonResult<Map<String, Object>> puInfoForOpenOrder(Long soChildId, Long platformId) {
        if (soChildId == null) {
            return JsonResult.fail("请选择子订单");
        }
        SoItemDTO soItemDTO = new SoItemDTO();
        soItemDTO.setSoChildId(soChildId);
        // 查询此子订单下的所有pu
        List<SoItemDTO> soItemList = soItemFacade.findAllSoItem(soItemDTO);

        List<SoItemAndPUView> viewList = new ArrayList<>();
        for (SoItemDTO soItem : soItemList) {
            SoItemAndPUView view = new SoItemAndPUView();
            view.setId(soItem.getId());
            view.setUserId(soItem.getUserId());
            view.setPuCount(soItem.getPuCount());
            view.setSoId(soItem.getSoId());
            view.setSoChildId(soItem.getSoChildId());
            view.setPuId(soItem.getPuId());
            view.setChannelProductId(soItem.getExternalProductId());
            // 根据puid查询商品
            CommodityProductUnitDTO pu = productFacade.queryPuById(soItem.getPuId());
            view.setPuSn(pu.getProductUnitSerialNumber());
            view.setPuName(pu.getName());
            // 根据puid查询商品的规格
            Map<String, String> standards = productFacade.queryStandardBySkuId(pu.getSkuId());
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String> en : standards.entrySet()) {
                sb.append(en.getKey()).append(":").append(en.getValue()).append(";");
            }
            view.setStandard(sb.toString());
            viewList.add(view);
        }
        // // 封装子订单的收件信息
        // SoChildDTO soChild = soFacade.findSoChildById(sochildId);
        //
        // if (EmptyUtil.isNotEmpty(soChild)) {
        // ReceiverAddressDTO receiverAddressDTO2 = receiverAddressFacade
        // .findReceiverAddressById(soChild.getReceiverAddressId());
        // map.put("receiverAddress", receiverAddressDTO2);
        // }
        return JsonResult.success("puList", viewList);
    }

    /**
     * 获得子订单所有的快照,原始快照,用户创建,后台创建
     *
     * @param soChildId
     * @param platformId
     * @param pageNum
     * @return
     */
    @Override
    public JsonResult<Map<String, Object>> getSoChildAllReceiverInfoPage(Long soChildId, Long platformId, Long pageNum) {
        // 校验子母订单
        if (soChildId == null)
            return JsonResult.fail("请指定子订单");
        SoChildDTO sc = soFacade.findSoChildById(soChildId);
        if (sc == null) {
            return JsonResult.fail("子订单不存在");
        }
        if (EmptyUtil.isEmpty(pageNum)) {
            pageNum = 1L;
        }
        Long soId = sc.getSoId();
        SoDTO so = soFacade.querySoById(soId);
        if (so == null) {
            return JsonResult.fail("子订单所属的母订单信息有误");
        }
        //返回给前台的地址数据
        List<OptionalReceiverAddressVO> res = new ArrayList<>();
        //获得订单所属的用户id
        Long userId = so.getUserId();
        //1.查询快照信息
        ReceiverAddressDTO snapAddress = receiverAddressFacade.findSnapsAddressByChildCodeAndType(sc.getChildCode(), 3);
        if (snapAddress == null) {
            return JsonResult.fail("该类商品无需收货地址");
        }
        res.add(this.dto2VO(snapAddress));
        //2.查询原始快照信息
        ReceiverAddressDTO orginalSnapAddress = receiverAddressFacade.findSnapsAddressByChildCodeAndType(sc.getChildCode(), 4);
        res.add(this.dto2VO(orginalSnapAddress));
        //3.查询用户创建list
        List<ReceiverAddressDTO> userCreateAddressList = receiverAddressFacade.queryReceiverAddressListCreatedByUser(userId, sc.getPlatformId());
        for (ReceiverAddressDTO dto : userCreateAddressList) {
            res.add(this.dto2VO(dto));
        }
        //4.查询后台创建list
        List<ReceiverAddressDTO> backStageCreateAddressList = receiverAddressFacade.queryReceiverAddressListCreateByBackStage(sc.getChildCode());
        for (ReceiverAddressDTO dto : backStageCreateAddressList) {
            res.add(this.dto2VO(dto));
        }
        //5.对list进行分页
        List<OptionalReceiverAddressVO> pageList = this.getPageList(pageNum.intValue(), 8, res);
        Map<String, Object> data = new HashMap<>();
        data.put("list", pageList);
        data.put("selectedId", snapAddress.getId());
        data.put("totalSize", res.size());
        return JsonResult.success(data);
    }

    //对list进行分页
    private List<OptionalReceiverAddressVO> getPageList(int pageNum, int pageSize, List<OptionalReceiverAddressVO> list) {
        List<OptionalReceiverAddressVO> pageList = new ArrayList<>();
        if (pageSize * pageNum >= list.size()) {
            for (int i = (pageNum - 1) * pageSize; i < list.size(); i++) {
                pageList.add(list.get(i));
            }

        } else {
            for (int i = (pageNum - 1) * pageSize; i < (pageSize * pageNum); i++) {
                pageList.add(list.get(i));
            }
        }


        return pageList;
    }

    @Override
    public JsonResult<Object> receiverAddressById(Long updateAddressId, Long platformId) {
        ReceiverAddressDTO receiverAddress = receiverAddressFacade.findReceiverAddressById(updateAddressId);
        if (receiverAddress == null) {
            return JsonResult.fail("选择的地址不存在");
        }

        return JsonResult.success(receiverAddress);
    }


    //将ReceiverAddressDTO信息分装到OptionalReceiverAddressVO
    private OptionalReceiverAddressVO dto2VO(ReceiverAddressDTO dto) {
        OptionalReceiverAddressVO re = new OptionalReceiverAddressVO();
        re.setAddress(dto.getGoodReceiverAddress());
        re.setCity(dto.getGoodReceiverCity());
        re.setCounty(dto.getGoodReceiverCounty());
        re.setId(dto.getId());
        re.setMobile(dto.getGoodReceiverMobile());
        re.setName(dto.getGoodReceiverName());
        re.setProvince(dto.getGoodReceiverProvince());
        re.setType(dto.getType());
        return re;

    }
/*

	@Override
	public JsonResult<Map<String, Object>> userReceiverInfos(Long sochildId, Long platformId) {
		// 查询用户收货地址列表
		if (sochildId == null)
			return JsonResult.fail("请指定子订单");
		SoChildDTO sc = soFacade.findSoChildById(sochildId);
		if (sc == null) {
			return JsonResult.fail("子订单不存在");
		}
		Long raId = sc.getReceiverAddressId();
		Long soId = sc.getSoId();
		SoDTO so = soFacade.querySoById(soId);
		if (so == null) {
			return JsonResult.fail("子订单所属的母订单信息有误");
		}
		Long userId = so.getUserId();
		ReceiverAddressDTO cond = new ReceiverAddressDTO();
		cond.setUserId(userId);
		List<ReceiverAddressDTO> ras = receiverAddressFacade.findReceiverAddressAll(cond);
		List<OptionalReceiverAddressVO> res = new ArrayList<>();
		for (ReceiverAddressDTO r : ras) {
			OptionalReceiverAddressVO re = new OptionalReceiverAddressVO();
			re.setAddress(r.getGoodReceiverAddress());
			re.setCity(r.getGoodReceiverCity());
			re.setCounty(r.getGoodReceiverCounty());
			re.setId(r.getId());
			re.setMobile(r.getGoodReceiverMobile());
			re.setName(r.getGoodReceiverName());
			re.setProvince(r.getGoodReceiverProvince());
			res.add(re);
		}
		Map<String, Object> data = new HashMap<>();
		data.put("list", res);
		data.put("selectedId", raId);
		return JsonResult.success(data);

		// Map<String, Object> map = new HashMap<>();
		//
		// SoItemDTO soItemDTO = new SoItemDTO();
		// soItemDTO.setSoChildId(sochildId);
		// List<SoItemDTO> findAllSoItem =
		// soItemFacade.findAllSoItem(soItemDTO);
		// if (EmptyUtil.isNotEmpty(findAllSoItem)) {
		// ReceiverAddressDTO receiverAddressDTO = new ReceiverAddressDTO();
		// receiverAddressDTO.setUserId(findAllSoItem.get(0).getUserId());
		// List<ReceiverAddressDTO> receiverAddressByUserId =
		// receiverAddressFacade
		// .findReceiverAddressAll(receiverAddressDTO);
		// Set<String> userNameSet = new HashSet<>();
		//
		// List<ReceiverAddressView> receiverAddressViewList = new
		// ArrayList<>();
		// for (ReceiverAddressDTO addressDTO : receiverAddressByUserId) {
		// ReceiverAddressView receiverAddressView = new ReceiverAddressView();
		// receiverAddressView.setGoodReceiverAddress(addressDTO.getGoodReceiverAddress());
		// receiverAddressView.setGoodReceiverCountryId(addressDTO.getGoodReceiverCountryId());
		// receiverAddressView.setGoodReceiverCountry(addressDTO.getGoodReceiverCountry());
		// receiverAddressView.setGoodReceiverCityId(addressDTO.getGoodReceiverCityId());
		// receiverAddressView.setGoodReceiverCity(addressDTO.getGoodReceiverCity());
		// receiverAddressView.setGoodReceiverCountyId(addressDTO.getGoodReceiverCountyId());
		// receiverAddressView.setGoodReceiverCounty(addressDTO.getGoodReceiverCounty());
		// receiverAddressView.setUserId(addressDTO.getUserId());
		// receiverAddressView.setId(addressDTO.getId());
		//
		// receiverAddressViewList.add(receiverAddressView);
		// userNameSet.add(addressDTO.getGoodReceiverName());
		// }
		//
		// List<String> userNameList = new ArrayList<String>(userNameSet);
		// map.put("userNameList", userNameList);
		// map.put("receiverAddressView", receiverAddressViewList);
		// }
		// return JsonResult.success(map);
	}
*/

    /**
     * 拆分订单
     *
     * @param soChildId
     * @param puIdArr
     * @param userId
     * @return
     */
    @Override
    public JsonResult<Map<String, Object>> openOrder(Long soChildId, String puIdArr, Long userId) {

        /*
         * 检查子订单物流状态是否是待发货或者分拣中 检查母订单确认状态是否是已确认
         * 查询母订单下的所有子订单,取得子订单编号尾缀的最大值,+1获取新子订单的编号 检验拆单数量
         * 原子执行:创建子订单,拷贝订单项,减少原订单项的数量,插入操作流水,更新原子订单数据
         */
        if (soChildId == null) {
            return JsonResult.fail("请选择子订单");
        }
        if (EmptyUtil.isBlank(puIdArr))
            return JsonResult.fail("拆单参数数组不存在");
        JSONArray jarr = JSONArray.parseArray(puIdArr);
        SoChildDTO sc = soFacade.findSoChildById(soChildId);
        if (sc == null)
            return JsonResult.fail("子订单不存在");
        Integer deliveryStatus = sc.getDeliveryStatus();
        if (deliveryStatus != 0 && deliveryStatus != 1) {
            return JsonResult.fail("子订单物流状态不是待发货或分拣中,无法拆单");
        }
        SoDTO so = soFacade.querySoById(sc.getSoId());
        if (so == null)
            return JsonResult.fail("母订单不存在");
        Integer soConfirmStatus = so.getOrderConfirmStatus();
        if (soConfirmStatus != 1) {
            return JsonResult.fail("母订单状态不是已确认,无法拆单");
        }
        // 订单项拆出数字校验,同时将待插入/更新的订单项挑出来
        List<SoItemDTO> items = soItemFacade.querySoItemListBySoId(sc.getSoId());
        List<SoItemDTO> insertItems = new ArrayList<>();
        List<SoItemDTO> updateItems = new ArrayList<>();// 包含直接变更子订单id的订单项,需要减少商品数量的订单项
        // 统计新子订单项数据
        BigDecimal amountNew = new BigDecimal(0);
        BigDecimal deliveryFeeNew = new BigDecimal(0);
        BigDecimal productAmountNew = new BigDecimal(0);
        BigDecimal couponDiscountNew = new BigDecimal(0);
        BigDecimal storeDiscountNew = new BigDecimal(0);
        // 统计拆单之后原订单剩余的商品数量
        int ordinaryPuCount = 0;
        for (SoItemDTO item : items) {
            Long itemId = item.getId();
            ordinaryPuCount += item.getPuCount();
            for (int i = 0; i < jarr.size(); i++) {
                JSONObject jobj = jarr.getJSONObject(i);
                Integer numJ = jobj.getInteger("num");// 拆出数量
                if (numJ == null) {
                    numJ = 0;
                }
                if (numJ > 0) {
                    Long itemIdJ = jobj.getLong("itemId");
                    if (itemId.longValue() == itemIdJ.longValue()) {
                        Integer num = item.getPuCount();
                        if (numJ.intValue() > num.intValue()) {
                            return JsonResult.fail("拆出数量不能超过原先数量");
                        } else {
                            if (numJ.intValue() == num.intValue()) {
                                // 该商品被完全拆出
                                ordinaryPuCount -= num;
                                // 更改订单项的子订单id
                                SoItemDTO updateDTO = new SoItemDTO();
                                updateDTO.setId(itemId);
                                // updateDTO.setSoChildId(soChildId);
                                updateItems.add(updateDTO);
                                // 计算订单项价值
                                BigDecimal cnt = BigDecimal.valueOf(item.getPuCount());
                                amountNew = amountNew.add(item.getAfterDiscountPriceAver().multiply(cnt));
                                deliveryFeeNew = deliveryFeeNew.add(item.getDeliveryFeeAver().multiply(cnt));
                                productAmountNew = productAmountNew.add(item.getPrice().multiply(cnt));
                                couponDiscountNew = couponDiscountNew.add(item.getPromotionAver().multiply(cnt));
                                storeDiscountNew = storeDiscountNew.add(item.getStoreDiscountAver().multiply(cnt));
                            } else {
                                // 该商品被部分拆出
                                // 更改原订单项的数量
                                SoItemDTO updateDTO = new SoItemDTO();
                                updateDTO.setId(itemId);
                                int puLeft = num - numJ;
                                updateDTO.setPuCount(puLeft);
                                // 变更原子订单Pu数量
                                ordinaryPuCount -= numJ;
                                updateItems.add(updateDTO);
                                // 新增一条订单项(基本拷贝原订单项的信息)
                                SoItemDTO insertDTO = item.copyThis();
                                // InsertDTO.setSoChildId(soChildId);
                                insertDTO.setPuCount(numJ);
                                insertItems.add(insertDTO);
                                BigDecimal numJBD = BigDecimal.valueOf(numJ);
                                amountNew = amountNew.add(item.getAfterDiscountPriceAver().multiply(numJBD));
                                deliveryFeeNew = deliveryFeeNew.add(item.getDeliveryFeeAver().multiply(numJBD));
                                productAmountNew = productAmountNew.add(item.getPrice().multiply(numJBD));
                                couponDiscountNew = couponDiscountNew.add(item.getPromotionAver().multiply(numJBD));
                                storeDiscountNew = storeDiscountNew.add(item.getStoreDiscountAver().multiply(numJBD));
                            }
                        }
                    }
                }
            }
        }
        if (ordinaryPuCount == 0) {
            // 原子订单不能被拆空
            return JsonResult.fail("原子订单应至少包含1件商品");
        }
        if (insertItems.size() == 0 && updateItems.size() == 0) {
            return JsonResult.fail("没有可拆的项");
        }
        // 查询当前最大子订单编号+1
        String maxChildCode = soFacade.queryMaxChildCodePlus1BySoId(sc.getSoId());

        // 组织新增子订单数据
        SoChildDTO insertSoChild = new SoChildDTO();
        insertSoChild.setOrdinaryDeliveryFee(new BigDecimal(0));
        insertSoChild.setNeedCountDeliveryFee(0);
        insertSoChild.setAmount(amountNew);
        insertSoChild.setDeliveryFee(deliveryFeeNew);
        insertSoChild.setProductAmount(productAmountNew);
        insertSoChild.setCouponDiscount(couponDiscountNew);
        insertSoChild.setStoreDiscount(storeDiscountNew);

        insertSoChild.setChildCode(maxChildCode);
        insertSoChild.setDeliveryStatus(deliveryStatus);
        insertSoChild.setReceiverAddressId(sc.getReceiverAddressId());
        insertSoChild.setOrdinaryId(soChildId);
        insertSoChild.setPerformingParty(sc.getPerformingParty());
        insertSoChild.setPlatformId(sc.getPlatformId());
        insertSoChild.setSoId(sc.getSoId());
        insertSoChild.setPreSell(sc.getPreSell());
        insertSoChild.setDeliverEndTime(sc.getDeliverEndTime());
        insertSoChild.setRemark(sc.getRemark());
        insertSoChild.setInvoiceId(sc.getInvoiceId());
        insertSoChild.setSupplierId(sc.getSupplierId());
        SoChildDTO updateSoChild = new SoChildDTO();
        updateSoChild.setAmount(sc.getAmount().subtract(amountNew));
        updateSoChild.setDeliveryFee(sc.getDeliveryFee().subtract(deliveryFeeNew));
        updateSoChild.setProductAmount(sc.getProductAmount().subtract(productAmountNew));
        updateSoChild.setCouponDiscount(sc.getCouponDiscount().subtract(couponDiscountNew));
        updateSoChild.setStoreDiscount(sc.getStoreDiscount().subtract(storeDiscountNew));
        updateSoChild.setId(soChildId);
        updateSoChild.setLastOperateTime(new Date());
        updateSoChild.setLastOperatorId(userId);
        soFacade.openOrder(insertSoChild, insertItems, updateItems, updateSoChild);
        //拆单成功,为新的子订单生成快照,原始快照
        //电子卡券不生成快照和原始快照
        if (sc.getReceiverAddressId() != null) {
            ReceiverAddressDTO addressDTO = receiverAddressFacade.findReceiverAddressById(sc.getReceiverAddressId());
            this.createSnapsAddress(addressDTO, maxChildCode);
        }

        return JsonResult.success();
    }

    @Override
    public JsonResult<String> soSort(String soIdArrStr, Long platformId) {

        if (EmptyUtil.isBlank(soIdArrStr)) {
            return JsonResult.fail("请选择订单");
        }
        List<Long> soIds = StringUtils.stringWithBorder2IdList(soIdArrStr, ",");
        if (soIds.size() == 0) {
            return JsonResult.fail("请选择订单");
        }
        soFacade.SoSort(soIds, platformId);
        return JsonResult.success();
    }

    @Override
    public JsonResult<Map<String, Object>> findFinanceBySoId(SoDTO soDTO) {
        SoDTO so = soFacade.findSoById(soDTO);
        if (so == null) {
            return JsonResult.fail("订单不存在");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("orderAmount", so.getOrderAmount());
        map.put("orderPromotionDiscount", so.getOrderPromotionDiscount());
        map.put("useFubi", so.getUseFubi());
        map.put("orderPromotionDiscount", so.getOrderPromotionDiscount());
        // map.put("orderAmountPay", so.getOrderAmountPay());
        map.put("orderAmountPay", so.getOrderAmount().subtract(so.getOrderPromotionDiscount()));
        map.put("orderPaidByCash", so.getOrderPaidByCash());
        map.put("cashPayType", translateOrderCashPayType(so.getCashPayType()));
        map.put("orderPaidByFubi", so.getOrderPaidByFubi());
        map.put("orderPayStatus", so.getOrderPayStatus());
        Date payTime = so.getOrderPaymentConfirmDate();
        if (payTime == null) {
            map.put("payConfirmTime", "");

        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            map.put("payConfirmTime", sdf.format(payTime));

        }
        return JsonResult.success(map);
    }

    /**
     * 翻译现金支付方式
     *
     * @param cashPayType
     * @return
     */
    private String translateOrderCashPayType(Integer cashPayType) {
        if (cashPayType == null) {
            return "未选择";
        }
        // 1 支付宝 2 微信 3 银联 4 建行
        switch (cashPayType) {
            case 1:
                return "支付宝";
            case 2:
                return "微信";
            case 3:
                return "银联";
            case 4:
                return "建行";
            default:
                return "未知方式";
        }
    }

    @Override
    public JsonResult<Map<String, Object>> soRefundInfo(Long orderId) {
        if (orderId == null)
            return JsonResult.fail("订单不存在");
        SoDTO so = soFacade.querySoById(orderId);
        if (so == null)
            return JsonResult.fail("订单不存在");
        Map<String, Object> data = new HashMap<>();
        data.put("refundCash", so.getRefundCash());
        data.put("refundFubi", so.getRefundFubi());
        data.put("paidByCash", so.getOrderPaidByCash());
        data.put("paidByFubi", so.getOrderPaidByFubi());
        return JsonResult.success(data);
    }

    @Override
    public JsonResult<Map<String, Object>> updateAddressCreateByBackStage(Long soChildId, Long updateAddressId,
                                                                          String receiverName, String receiverMobile,
                                                                          Long provinceId, Long cityId,
                                                                          Long countyId, String address,
                                                                          Long platformId, Long memberId) {
        // 参数校验
        if (soChildId == null)
            return JsonResult.fail("请选择子订单");
        String province, city, county = null;

        if (EmptyUtil.isBlank(receiverName))
            return JsonResult.fail("请填写收货人姓名");
        if (!StringUtils.validMobile(receiverMobile)) {
            return JsonResult.fail("请输入正确的手机号码");
        }
        if (EmptyUtil.isBlank(receiverMobile))
            return JsonResult.fail("请填写收货人手机");
        if (provinceId == null)
            return JsonResult.fail("请选择省份");
        ProvCityAreaDTO province_ = provCityAreaFacade.findProvCityAreaById(provinceId);
        if (province_ == null) {
            return JsonResult.fail("所选省份不存在");
        }
        province = province_.getAreaname();
        if (cityId == null)
            return JsonResult.fail("请选择城市");
        ProvCityAreaDTO city_ = provCityAreaFacade.findProvCityAreaById(cityId);
        if (city_ == null) {
            return JsonResult.fail("所选城市不存在");
        }
        city = city_.getAreaname();
        if (countyId == null)
            return JsonResult.fail("请选择地区");
        ProvCityAreaDTO county_ = provCityAreaFacade.findProvCityAreaById(countyId);
        if (county_ == null) {
            return JsonResult.fail("所选区域不存在");
        }
        county = county_.getAreaname();
        if (EmptyUtil.isBlank(address))
            return JsonResult.fail("请填写地址");

        // 查询操作人信息
        UserDTO operator = userFacade.queryUserById(memberId);
        if (operator == null)
            return JsonResult.fail("操作人信息有误");
        // 查询子订单信息 获取母订单信息
        SoChildDTO sc = soFacade.findSoChildById(soChildId);
        if (sc == null) {
            return JsonResult.fail("子订单不存在");
        }
        Long soId = sc.getSoId();
        SoDTO s = soFacade.querySoById(soId);
        if (s == null) {
            return JsonResult.fail("订单不存在");
        }
        Long soUserId = s.getUserId();
        ReceiverAddressDTO ra = receiverAddressFacade.findReceiverAddressById(updateAddressId);
        ra.setGoodReceiverAddress(address);
        ra.setGoodReceiverCity(city);
        ra.setGoodReceiverCityId(cityId);
        ra.setGoodReceiverCounty(county);
        ra.setGoodReceiverCountyId(countyId);
        ra.setGoodReceiverMobile(receiverMobile);
        ra.setGoodReceiverName(receiverName);
        ra.setGoodReceiverProvince(province);
        ra.setGoodReceiverProvinceId(provinceId);
        ra.setIsDefault(0);
        ra.setPlatformId(platformId);
        ra.setModifyMail(operator.getMail());
        ra.setType(2);// 运营创建
        ra.setUserId(soUserId);
        ra.setSoChildCode(sc.getChildCode());
        // 原子创建收货地址并更新子订单收货地址
        soFacade.updateAddressCreateByBackStage(ra);
        return JsonResult.success();
    }


    //后台新增地址,后台修改保存收货地址
    @Override
    public JsonResult<Map<String, Object>> updateReceiverInfo(Long soChildId, Long receiverAddressId,
                                                              String receiverName, String receiverMobile,
                                                              Long provinceId, Long cityId,
                                                              Long countyId, String address,
                                                              Long platformId, Long operatorId) {
        // 参数校验
        if (soChildId == null)
            return JsonResult.fail("请选择子订单");
        String province, city, county = null;

        if (receiverAddressId != null) {
            //1.修改收货地址
            // 查询该地址是否存在
            ReceiverAddressDTO ra = receiverAddressFacade.findReceiverAddressById(receiverAddressId);
            if (ra == null) {
                return JsonResult.fail("所选的收货地址不存在");
            }
            //更新快照
            SoChildDTO soChildDTO = soFacade.findSoChildById(soChildId);
            ReceiverAddressDTO snapsAddress = receiverAddressFacade.findSnapsAddressByChildCodeAndType(soChildDTO.getChildCode(), 3);
            //修改时选择的id不是快照id
            if (snapsAddress.getId() != receiverAddressId) {
                ReceiverAddressDTO addressDTO = receiverAddressFacade.findReceiverAddressById(receiverAddressId);
                snapsAddress.setGoodReceiverAddress(addressDTO.getGoodReceiverAddress());
                snapsAddress.setGoodReceiverArea(addressDTO.getGoodReceiverArea());
                snapsAddress.setGoodReceiverAreaId(addressDTO.getGoodReceiverAreaId());
                snapsAddress.setGoodReceiverCity(addressDTO.getGoodReceiverCity());
                snapsAddress.setGoodReceiverCityId(addressDTO.getGoodReceiverCityId());
                snapsAddress.setGoodReceiverCountry(addressDTO.getGoodReceiverCountry());
                snapsAddress.setGoodReceiverCountryId(addressDTO.getGoodReceiverCountryId());
                snapsAddress.setGoodReceiverMobile(addressDTO.getGoodReceiverMobile());
                snapsAddress.setGoodReceiverName(addressDTO.getGoodReceiverName());
                snapsAddress.setGoodReceiverPhone(addressDTO.getGoodReceiverPhone());
                snapsAddress.setGoodReceiverProvince(addressDTO.getGoodReceiverProvince());
                snapsAddress.setGoodReceiverProvinceId(addressDTO.getGoodReceiverProvinceId());
                snapsAddress.setGoodReceiverCounty(addressDTO.getGoodReceiverCounty());
                snapsAddress.setGoodReceiverCountyId(addressDTO.getGoodReceiverCountyId());
                //跟新数据库中的快照地址信息
                receiverAddressFacade.updateReceiverAddressWithTx(snapsAddress);
            }
            // 更新子订单收货地址为快照
            soFacade.updateSoChildReceiverAddress(snapsAddress.getId(), soChildId);
            return JsonResult.success();
        } else {
            //2.后台新增地址
            if (!StringUtils.validMobile(receiverMobile)) {
                return JsonResult.fail("请输入正确的手机号码");
            }
            //校验数据
            if (EmptyUtil.isBlank(receiverName))
                return JsonResult.fail("请填写收货人姓名");
            if (EmptyUtil.isBlank(receiverMobile))
                return JsonResult.fail("请填写收货人手机");
            if (provinceId == null)
                return JsonResult.fail("请选择省份");
            ProvCityAreaDTO province_ = provCityAreaFacade.findProvCityAreaById(provinceId);
            if (province_ == null) {
                return JsonResult.fail("所选省份不存在");
            }
            province = province_.getAreaname();
            if (cityId == null)
                return JsonResult.fail("请选择城市");
            ProvCityAreaDTO city_ = provCityAreaFacade.findProvCityAreaById(cityId);
            if (city_ == null) {
                return JsonResult.fail("所选城市不存在");
            }
            city = city_.getAreaname();
            if (countyId == null)
                return JsonResult.fail("请选择地区");
            ProvCityAreaDTO county_ = provCityAreaFacade.findProvCityAreaById(countyId);
            if (county_ == null) {
                return JsonResult.fail("所选区域不存在");
            }
            county = county_.getAreaname();
            if (EmptyUtil.isBlank(address))
                return JsonResult.fail("请填写地址");
        }
        // 查询操作人信息
        UserDTO operator = userFacade.queryUserById(operatorId);
        if (operator == null)
            return JsonResult.fail("操作人信息有误");
        // 查询子订单信息 获取母订单信息
        SoChildDTO sc = soFacade.findSoChildById(soChildId);
        if (sc == null) {
            return JsonResult.fail("子订单不存在");
        }
        Long soId = sc.getSoId();
        SoDTO s = soFacade.querySoById(soId);
        if (s == null) {
            return JsonResult.fail("订单不存在");
        }
        Long soUserId = s.getUserId();
        // 查母订单
        ReceiverAddressDTO ra = new ReceiverAddressDTO();
        ra.setGoodReceiverAddress(address);
        ra.setGoodReceiverCity(city);
        ra.setGoodReceiverCityId(cityId);
        ra.setGoodReceiverCounty(county);
        ra.setGoodReceiverCountyId(countyId);
        ra.setGoodReceiverMobile(receiverMobile);
        ra.setGoodReceiverName(receiverName);
        ra.setGoodReceiverProvince(province);
        ra.setGoodReceiverProvinceId(provinceId);
        ra.setIsDefault(0);
        ra.setPlatformId(platformId);
        ra.setModifyMail(operator.getMail());
        ra.setType(2);// 运营创建
        ra.setUserId(soUserId);
        ra.setSoChildCode(sc.getChildCode());
        // 原子创建收货地址并更新子订单收货地址
        soFacade.insertReceiverAddressByBackStage(ra);
        return JsonResult.success();
    }



	/*@Override
	public JsonResult<Map<String, Object>> updateReceiverInfo(Long soChildId, Long receiverAddressId,
			String receiverName, String receiverMobile, Long provinceId, Long cityId, Long countyId, String address,
			Long platformId, Long operatorId) {
		// 参数校验
		if (soChildId == null)
			return JsonResult.fail("请选择子订单");
		String province, city, county = null;

		if (receiverAddressId != null) {
			// 查询该地址是否存在
			ReceiverAddressDTO ra = receiverAddressFacade.findReceiverAddressById(receiverAddressId);
			if (ra == null) {
				return JsonResult.fail("所选的收货地址不存在");
			}
			// 更新子订单收货地址
			soFacade.updateSoChildReceiverAddress(receiverAddressId, soChildId);
			return JsonResult.success();
		} else {

			if (EmptyUtil.isBlank(receiverName))
				return JsonResult.fail("请填写收货人姓名");
			if (EmptyUtil.isBlank(receiverMobile))
				return JsonResult.fail("请填写收货人手机");
			if (provinceId == null)
				return JsonResult.fail("请选择省份");
			ProvCityAreaDTO province_ = provCityAreaFacade.findProvCityAreaById(provinceId);
			if (province_ == null) {
				return JsonResult.fail("所选省份不存在");
			}
			province = province_.getAreaname();
			if (cityId == null)
				return JsonResult.fail("请选择城市");
			ProvCityAreaDTO city_ = provCityAreaFacade.findProvCityAreaById(cityId);
			if (city_ == null) {
				return JsonResult.fail("所选城市不存在");
			}
			city = city_.getAreaname();
			if (countyId == null)
				return JsonResult.fail("请选择地区");
			ProvCityAreaDTO county_ = provCityAreaFacade.findProvCityAreaById(countyId);
			if (county_ == null) {
				return JsonResult.fail("所选区域不存在");
			}
			county = county_.getAreaname();
			if (EmptyUtil.isBlank(address))
				return JsonResult.fail("请填写地址");
		}
		// 查询操作人信息
		UserDTO operator = userFacade.queryUserById(operatorId);
		if (operator == null)
			return JsonResult.fail("操作人信息有误");
		// 查询子订单信息 获取母订单信息
		SoChildDTO sc = soFacade.findSoChildById(soChildId);
		if (sc == null) {
			return JsonResult.fail("子订单不存在");
		}
		Long soId = sc.getSoId();
		SoDTO s = soFacade.querySoById(soId);
		if (s == null) {
			return JsonResult.fail("订单不存在");
		}
		Long soUserId = s.getUserId();
		// 查母订单
		ReceiverAddressDTO ra = new ReceiverAddressDTO();
		ra.setGoodReceiverAddress(address);
		ra.setGoodReceiverCity(city);
		ra.setGoodReceiverCityId(cityId);
		ra.setGoodReceiverCounty(county);
		ra.setGoodReceiverCountyId(countyId);
		ra.setGoodReceiverMobile(receiverMobile);
		ra.setGoodReceiverName(receiverName);
		ra.setGoodReceiverProvince(province);
		ra.setGoodReceiverProvinceId(provinceId);
		ra.setIsDefault(0);
		ra.setPlatformId(platformId);
		ra.setModifyMail(operator.getMail());
		ra.setType(2);// 运营创建
		ra.setUserId(soUserId);
		// 原子创建收货地址并更新子订单收货地址
		soFacade.createAndUpdateSoChildReceiverAddress(ra, soChildId);
		return JsonResult.success();
	}*/

    //根据orderid查询所有子订单的快照地址
    @Override
    public JsonResult<Map<String, Object>> receiverInfos(Long orderId) {
        if (orderId == null)
            return JsonResult.fail("请选择订单");
        //查询所有子订单
        List<SoChildDTO> scs = soFacade.querySoChildListBySoId(orderId);
        List<SoChildReceiveAddressVO> res = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        for (SoChildDTO sc : scs) {
            //封装每一个子订单的收货信息vo
            SoChildReceiveAddressVO ra = new SoChildReceiveAddressVO();
            //根据childcode获取快照地址
            String soChildCode = sc.getChildCode();
            //查询快照地址
            ReceiverAddressDTO snapReceiverAddressDTO = receiverAddressFacade.findSnapsAddressByChildCodeAndType(soChildCode, 3);


			/*Long raId = sc.getReceiverAddressId();
			Long scId = sc.getId();
			ra.setId(raId);
			ra.setSoChildId(scId);*/
            if (snapReceiverAddressDTO != null) {
//				ReceiverAddressDTO ra_ = receiverAddressFacade.findReceiverAddressById(raId);
//				if (ra_ != null) {
                ra.setAddress(snapReceiverAddressDTO.getGoodReceiverAddress());//商品收货地址
                ra.setChildCode(soChildCode);//子订单code
                ra.setCity(snapReceiverAddressDTO.getGoodReceiverCity());//收货城市
                ra.setCounty(snapReceiverAddressDTO.getGoodReceiverCounty());//收货国家
                ra.setMobile(snapReceiverAddressDTO.getGoodReceiverMobile());//收货电话
                ra.setProvince(snapReceiverAddressDTO.getGoodReceiverProvince());//收货省份
                ra.setReceiverName(snapReceiverAddressDTO.getGoodReceiverName());//收货人
                ra.setSoChildId(sc.getId());

//				}
            }
            // 根据子订单id查询物流信息(配送日期)
            Long scId = sc.getId();
            List<SoPackageDTO> packs = soPackageFacade.queryPackageBySoChildId(scId);
            if (packs != null && packs.size()>0 && packs.get(0).getDeliveryDate() != null) {
                ra.setDeliveryDate(sdf.format(packs.get(0).getDeliveryDate()));
            }
            //最后修改人
            Long lastModifierId = sc.getLastOperatorId();
            if (lastModifierId != null) {
                // 查询用户信息
                UserDTO mdfr = userFacade.queryUserById(lastModifierId);
                if (mdfr != null) {
                    ra.setModifier(mdfr.getMail());
                }
            }
            //修改时间
            Date mat = sc.getModifyAddressTime();
            if (mat != null) {
                ra.setUpdateTime(sdf.format(mat));
            }
            res.add(ra);
        }
        return JsonResult.success("list", res);
    }

    @Override
    public JsonResult<Map<String, Object>> customerServices(Long orderId) {
        if (orderId == null)
            return JsonResult.fail("请选择订单");
        // 查询订单用户信息
        SoDTO so = soFacade.querySoById(orderId);
        if (so == null)
            return JsonResult.fail("订单信息不存在");
        UserExtendDTO user = userFacade.queryUserExtendById(so.getUserId());
        if (user == null)
            return JsonResult.fail("订单用户信息有误");
        // 查询子订单信息
        List<SoChildDTO> scs = soFacade.querySoChildListBySoId(orderId);
        List<SoCustomerServiceVO> voList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (SoChildDTO sc : scs) {
            SoCustomerServiceVO vo = new SoCustomerServiceVO();
            Long scId = sc.getId();
            vo.setSoChildId(scId);
            vo.setChildCode(sc.getChildCode());
            vo.setSoId(sc.getSoId());
            vo.setUserName(user.getName());
            vo.setUserRemark(so.getRemark());
            // 查询客服信息
            SoCustomerServiceDTO dto = soCustomerServiceFacade.queryCustomerServiceBySoChildId(scId);
            if (dto != null) {
                vo.setId(dto.getId());
                vo.setOperateRemark(dto.getOperateRemark());
                vo.setUpdateTime(sdf.format(dto.getUpdateTime()));
            }
            voList.add(vo);
        }
        return JsonResult.success("list", voList);
    }

    @Override
    public JsonResult<Map<String, Object>> saveOperatorRemark(Long soChildId, String operatorRemark) {
        if (soChildId == null)
            return JsonResult.fail("请选择子订单");
        if (EmptyUtil.isBlank(operatorRemark))
            return JsonResult.fail("请输入备注");
        // 查询子订单信息
        SoChildDTO sc = soFacade.findSoChildById(soChildId);
        if (sc == null)
            return JsonResult.fail("子订单不存在");
        // 根据soChildId查询客服信息
        SoCustomerServiceDTO cs = soCustomerServiceFacade.queryCustomerServiceBySoChildId(soChildId);
        if (cs == null) {
            // 如果没有则新增
            SoCustomerServiceDTO insertDTO = new SoCustomerServiceDTO();
            insertDTO.setOperateRemark(operatorRemark);
            insertDTO.setSoChildId(soChildId);
            insertDTO.setSoId(sc.getSoId());
            soCustomerServiceFacade.insertSoCustomerServiceWithTx(insertDTO);
        } else {
            // 如果有则修改
            SoCustomerServiceDTO updateDTO = new SoCustomerServiceDTO();
            updateDTO.setId(cs.getId());
            updateDTO.setOperateRemark(operatorRemark);
            soCustomerServiceFacade.updateSoCustomerServiceWithTx(updateDTO);
        }
        return JsonResult.success();
    }

    @Override
    public JsonResult<Map<String, Object>> soOpFlow(Long orderId) {
        if (orderId == null) {
            return JsonResult.fail("请选择订单");
        }
        SoDTO so = soFacade.querySoById(orderId);
        if (so == null) {
            return JsonResult.fail("订单不存在");
        }
        // 查询所有操作流水
        List<SoFlowDTO> scfs = soFlowFacade.queryFlowListBySoId(orderId);
        List<SoOPFlowVO> voList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        for (SoFlowDTO scf : scfs) {
            SoOPFlowVO vo = new SoOPFlowVO();
            vo.setCreatTime(sdf.format(scf.getCreatTime()));
            vo.setId(scf.getId());
            vo.setOperate(scf.getOperate());
            Long operatorId = scf.getOperatorId();
            // 查询操作人信息
            UserExtendDTO oper = userFacade.queryUserExtendById(operatorId);
            if (oper != null) {
                vo.setOperatorName(oper.getName());
            }
            vo.setOrderCode(so.getOrderCode());
            voList.add(vo);
        }
        return JsonResult.success("list", voList);
    }

    @Override
    public JsonResult<Map<String, Object>> soChildOpFlow(Long soChildId) {
        if (soChildId == null) {
            return JsonResult.fail("请选择子订单");
        }
        // 查询子订单操作流水
        List<SoChildFlowDTO> scfs = soFlowFacade.querySoChildFlowListBySoChildId(soChildId);
        List<SoOPFlowVO> voList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (SoChildFlowDTO scf : scfs) {
            SoOPFlowVO vo = new SoOPFlowVO();
            vo.setCreatTime(sdf.format(scf.getCreatTime()));
            vo.setId(scf.getId());
            vo.setOperate(scf.getOperate());
            Long operatorId = scf.getOperatorId();
            // 查询操作人信息
            UserExtendDTO oper = userFacade.queryUserExtendById(operatorId);
            if (oper != null) {
                vo.setOperatorName(oper.getName());
            }
            SoChildDTO sc = soFacade.findSoChildById(scf.getSoChildId());
            vo.setOrderCode(sc.getChildCode());
            voList.add(vo);
        }
        return JsonResult.success("list", voList);
    }

    @Override
    public JsonResult<Map<String, Object>> soRefund(Long orderId, Double refundCash, Double refundFubi, String reason, Long platformId, Long operatorId,HttpServletRequest req,Double refundJiDian) {
        RefundVo refundVo=new RefundVo();
        refundVo.setOrderId(orderId);
        refundVo.setRefundCash(refundCash);
        refundVo.setRefundFubi(refundFubi);
        refundVo.setReason(reason);
        refundVo.setCancelOrder(true);
        refundVo.setPlatformId(platformId);
        refundVo.setOperatorId(operatorId);
        refundVo.setThirdRefundCode(null);
        refundVo.setRefundJiDian(refundJiDian);
        return soRefund(refundVo,req);
    }

    @Override
    public JsonResult<Map<String, Object>> soRefund(RefundVo refundVo, HttpServletRequest req) {
        // 参数校验
        if (refundVo.getOrderId() == null)
            return JsonResult.fail("请选择订单");
        if (Objects.isNull(refundVo.getRefundCash())) {
            refundVo.setRefundCash(0d);
        }
        if (Objects.isNull(refundVo.getRefundFubi())) {
            refundVo.setRefundFubi(0d);
        }
        if (Objects.isNull(refundVo.getRefundJiDian())) {
            refundVo.setRefundJiDian(0d);
        }
        if (refundVo.getRefundCash() == 0d && refundVo.getRefundFubi() == 0d && refundVo.getRefundJiDian() == 0d) {
            return JsonResult.fail("请输入本次退款金额");
        }
        if (refundVo.getRefundCash() < 0d || refundVo.getRefundFubi() < 0d || refundVo.getRefundJiDian() < 0d) {
            return JsonResult.fail("退款金额不能为负数");
        }
        if (EmptyUtil.isBlank(refundVo.getReason())) {
            return JsonResult.fail("请填写退款原因");
        }
        // 查询订单信息
        SoDTO so = soFacade.querySoById(refundVo.getOrderId());
        if (so == null) {
            return JsonResult.fail("订单不存在");
        }
        // 判断退款金额是否超标
        // 订单已付金额
        BigDecimal paidByCash = so.getOrderPaidByCash();
        BigDecimal paidByFubi = so.getOrderPaidByFubi();
        BigDecimal paidByJidian = so.getOrderPaidByJidian();
        // 订单剩余可退金额
        BigDecimal canRefundCash = paidByCash.subtract(so.getRefundCash());
        BigDecimal canRefundFubi = paidByFubi.subtract(so.getRefundFubi());
        BigDecimal canRefundJidian = paidByJidian.subtract(so.getRefundJidian());
        // 本次打算退款的金额
        BigDecimal refundCashD = new BigDecimal(refundVo.getRefundCash().toString()+"");
        BigDecimal refundFubiD = new BigDecimal(refundVo.getRefundFubi().toString()+"");
        BigDecimal refundJidianD = new BigDecimal(refundVo.getRefundJiDian().toString()+"");
        int refundCashCompareResult = refundCashD.compareTo(canRefundCash);
        if (refundCashCompareResult == 1) {
            return JsonResult.fail("本次退款金额大于可退款金额");
        }
        int refundFubiCompareResult = refundFubiD.compareTo(canRefundFubi);
        if (refundFubiCompareResult == 1) {
            return JsonResult.fail("本次退款金额大于可退款金额");
        }
        int refundJidianComareResult =refundJidianD.compareTo(canRefundJidian);
        if (refundJidianComareResult == 1) {
            return JsonResult.fail("本次退款金额大于可退款金额");
        }
        // 生成唯一退款单编号
        List<String> soRefundNOList = soFacade.genSoRefundNO();
        boolean refundCashSuccess=true;
        if (refundVo.isAutoRefundCash()){
            refundCashSuccess=soFacade.refundCashWithTx(new RefundCashWithTxDTO(so.getUserId(),so,refundCashD,
                    refundVo.getSoItemDTOS(),soRefundNOList.get(1)));
            if (!refundCashSuccess){
                refundCashD=BigDecimal.ZERO;
            }
        }

        // 设置订单参数
        SoDTO soDTO = new SoDTO();
        soDTO.setId(refundVo.getOrderId());
        soDTO.setOrderCode(so.getOrderCode());
        soDTO.setRefundCash(refundCashD);
        soDTO.setRefundFubi(refundFubiD);
        soDTO.setUserId(so.getUserId());
        soDTO.setPlatformId(refundVo.getPlatformId());
        soDTO.setCashPayType(so.getCashPayType());
        soDTO.setRefundJidian(refundJidianD);
        // 设置订单的支付状态 : 是否全额退款, 是:已退款  否:不变
        soDTO.setOrderPayStatus((refundCashSuccess && refundCashCompareResult == 0 && refundFubiCompareResult == 0) ? 2 : null);
        if (Objects.equals(soDTO.getOrderPayStatus(),2) && refundVo.isCancelOrder()){
            soDTO.setOrderStatus(OrderConstant.ORDER_STATUS_CANCELED.getStatus());
        }
        soDTO.setRefundVo(refundVo);
        // 原子事物:资金流动,订单退款额变动,订单状态变动
        List<String> refundNos=soFacade.soRefundWithTx(soDTO, refundVo.getReason(), refundVo.getOperatorId(),
                soRefundNOList.get(0), soRefundNOList.get(1), false,refundVo.getThirdRefundCode(), req,soRefundNOList.get(1),soRefundNOList.get(1));
        Map<String, Object> data = new HashMap<>();
        data.put("refundNos", refundNos);
        return JsonResult.success(data);
    }

    @Resource
    private SoRefundFacade soRefundFacade;

    @Override
    public JsonResult<Map<String, Object>> refundDetail(Long orderId) {
        if (orderId == null)
            return JsonResult.fail("请选择订单");
        // 查询订单
        SoDTO so = soFacade.querySoById(orderId);
        if (so == null)
            return JsonResult.fail("订单不存在");
        // 查询该订单的所有退款流水
        List<AccountFlowDTO> flows = accountFacade.queryOrderRefundFlow(orderId);
        List<RefundOrderVO> voList = new ArrayList<>();
        // 遍历查询批次,补足信息
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (AccountFlowDTO f : flows) {

            RefundOrderVO vo = new RefundOrderVO();
            vo.setId(f.getId());
            AccountBatchDTO batch = null;
            if (f.getBatchId() !=null){
                batch = accountFacade.queryBatchById(f.getBatchId());
            }
            if (batch != null) {
                Long operatorId = batch.getOperatorId();
                if (Objects.nonNull(operatorId)){
                    UserDTO user = userFacade.queryUserById(operatorId);
                    if (user != null) {
                        vo.setOperator(user.getMail());
                    }
                }
            }
            vo.setReason(f.getRemark());
            int type = f.getType();
            if (type==5){
                vo.setRefundCash(f.getSum());
                vo.setRefundFubi(new BigDecimal("0.00"));
                vo.setRefundJidian(new BigDecimal("0.00"));
                vo.setRefundCard(new BigDecimal("0.00"));
            } else if (type==6) {
                vo.setRefundCash(new BigDecimal("0.00"));
                vo.setRefundFubi(f.getSum());
                vo.setRefundJidian(new BigDecimal("0.00"));
                vo.setRefundCard(new BigDecimal("0.00"));
            }else if(type ==16){
                vo.setRefundJidian(f.getSum());
                vo.setRefundCash(new BigDecimal("0.00"));
                vo.setRefundFubi(new BigDecimal("0.00"));
                vo.setRefundCard(new BigDecimal("0.00"));
            }else if(type ==18){
                vo.setRefundJidian(new BigDecimal("0.00"));
                vo.setRefundCash(new BigDecimal("0.00"));
                vo.setRefundFubi(new BigDecimal("0.00"));
                vo.setRefundCard(f.getSum());
            }else{
                vo.setRefundFubi(new BigDecimal("0.00"));
                vo.setRefundCash(new BigDecimal("0.00"));
                vo.setRefundJidian(new BigDecimal("0.00"));
                vo.setRefundCard(new BigDecimal("0.00"));
            }
            if(f.getBatchId() !=null){
                vo.setRefundCount(0);
                Map<String,Object> itemMap= soRefundFacade.getRefundItemSkuNames(f.getBatchId(),so.getId(),type);
                if(EmptyUtil.isNotEmpty(itemMap)){
                    String skuName = itemMap.get("skuName") !=null?itemMap.get("skuName").toString():null;
                    Integer refundCount = itemMap.get("refundCount") !=null?Integer.valueOf(itemMap.get("refundCount").toString()):0;
                    vo.setProductName(skuName);
                    vo.setRefundCount(refundCount);
                }

            }
            vo.setRefundTime(sdf.format(f.getCreateTime()));
            voList.add(vo);
        }
        Map<String, Object> data = new HashMap<>();
        data.put("paidByFubi", so.getOrderPaidByFubi());
        data.put("refundFubi", so.getRefundFubi());
        data.put("canRefundFubi", so.getOrderPaidByFubi().subtract(so.getRefundFubi()));
        data.put("paidByCash", so.getOrderPaidByCash());
        data.put("paidByJidian", so.getOrderPaidByJidian());
        data.put("refundCash", so.getRefundCash());
        data.put("canRefundCash", so.getOrderPaidByCash().subtract(so.getRefundCash()));
        data.put("canRefundJidian", so.getOrderPaidByJidian().subtract(so.getRefundJidian()));
        data.put("refundJidian", so.getRefundJidian());
        data.put("refundcard", so.getRefundCard());
        data.put("canRefundCard", so.getOrderCardPaid().subtract(so.getRefundCard()));
        data.put("list", voList);
        return JsonResult.success(data);
    }


    @Override
    public void receviedConfirm(Long orderId, Long operatorId, Long platformId) {
        checkOrderStatus(orderId.toString());
    }

    @Override
    public void sendHostoryConfirm(Long orderId, Long operatorId, Long platformId) {
        soFacade.sendHostoryConfirm(orderId, operatorId, platformId);


    }

    @Override
    public List<SoDTO> findSoByCode(String orderCode) {
        return soFacade.findSoByCode(orderCode);

    }

    @Override
    public void writeDeliveryDate(Long orderId,Long platformId,Date date) {
        soFacade.writeDeliveryDate(orderId,platformId,date);
    }

    @Override
    public JsonResult<Long> getUnPayNum(Long storeId, Long platformId,Long userId) {
        SoDTO soDTO = new SoDTO();
        //soDTO.setStoreId(storeId);
        soDTO.setPlatformId(platformId);
        soDTO.setOrderPayStatus(0);
        soDTO.setOrderConfirmStatus(0);
        soDTO.setUserId(userId);
        return soFacade.findSoSum(soDTO);
    }

    @Override
    public JsonResult<Long> getPaiedNum(Long storeId, Long platformId,Long userId) {
        SoDTO soDTO = new SoDTO();
        //soDTO.setStoreId(storeId);
        soDTO.setPlatformId(platformId);
        soDTO.setOrderPayStatus(1);
        soDTO.setUserId(userId);

        soDTO.setOrderConfirmStatus(1);
        return soFacade.findSoSum(soDTO);
    }

    @Override
    public JsonResult<Long> getSendedNum(Long storeId, Long platformId,Long userId) {
        SoDTO soDTO = new SoDTO();
        //soDTO.setStoreId(storeId);
        soDTO.setPlatformId(platformId);
        soDTO.setOrderPayStatus(1);
        soDTO.setDeliveryStatus(10);
        soDTO.setUserId(userId);

        soDTO.setOrderConfirmStatus(1);
        return soFacade.findSoSum(soDTO);
    }

    @Override
    public JsonResult<Map<String, Object>> orderSortExport(String orderIds, Long operatorId, Long platformId) {
        JsonResult<List<OrderSortExportVO>> listJsonResult = checkOrderStatus(orderIds);
        if(listJsonResult.getCode()==-1){
            return JsonResult.fail(listJsonResult.getError());
        }
        List<OrderSortExportVO> voList = listJsonResult.getData();
        orderIds=orderIds.replace("[", "");
        orderIds=orderIds.replace("]", "");
        List<Long> orderIds_ = StringUtils.string2IdList(orderIds, ",");
       /* if (EmptyUtil.isBlank(orderIds))
            return JsonResult.fail("请选择订单");
        // 去除方括号
        orderIds = orderIds.substring(1, orderIds.length() - 1);

        List<Long> orderIds_ = StringUtils.string2IdList(orderIds, ",");
        if (orderIds_.size() == 0) {
            return JsonResult.fail("请选择订单");
        }
        *//**
         * 分拣导出 选中需要导出的订单，点击[分拣导出]，后台作如下判断： 选中的订单确认状态是否为已确认且其子订单状态均为待发货，
         * 若不是则提示用户： 所选订单中存在已分拣的订单，请检查后重新选择； 若判断所选项符合导出条件则按照模板导出Excel文件至本地，
         * 并批量更新导出订单的所有子订单物流状态为分拣中。
         *//*
        // 检验信息,同时生成voList
        List<OrderSortExportVO> voList = new ArrayList<>();
        for (Long oId : orderIds_) {
            // 检查订单信息
            SoDTO so = soFacade.querySoById(oId);
            if (so == null) {
                return JsonResult.fail("id为" + oId + "的订单不存在");
            }
            if (so.getOrderConfirmStatus() != 1) {
                return JsonResult.fail("所选订单中存在未确认的订单，请检查后重新选择");
            }
            // 查询用户信息
            UserDTO user = userFacade.queryUserById(so.getUserId());
            Long companyId = user.getCompanyId();
            CompanyDTO company = userFacade.queryCompanyById(companyId);
            UserExtendDTO userExtend = userFacade.queryUserExtendById(so.getUserId());
            // 检查子订单
            List<SoChildDTO> childs = soFacade.querySoChildListBySoId(oId);
            for (SoChildDTO child : childs) {
                if (child.getDeliveryStatus().intValue() != 0) {
                    return JsonResult.fail("所选订单中存在已分拣的订单，请检查后重新选择");
                }
                // 查询物流信息
                SoPackageDTO pack = soPackageFacade.queryPackageBySoChildId(child.getId());
                Long receiverAddressId = child.getReceiverAddressId();
                ReceiverAddressDTO ra = null;
                if (receiverAddressId != null) {
                    ra = receiverAddressFacade.findReceiverAddressById(receiverAddressId);
                }
                // 查询发票信息
                SoInvoiceDTO invoice = soInvoiceFacade.querySoInvoiceBySoChildId(child.getId());
                // 查询订单项
                List<SoItemDTO> items = soItemFacade.querySoItemsBySoChildId(child.getId());
                for (SoItemDTO item : items) {
                    OrderSortExportVO vo = new OrderSortExportVO();
                    vo.setOrderCode(so.getOrderCode());
                    vo.setChildCode(child.getChildCode());
                    if (pack != null) {
                        vo.setDeliveryCode(pack.getDeliveryCode());
                        vo.setDeliveryCompany(pack.getDeliveryCompanyName());
                    }
                    if (ra != null) {
                        vo.setReceiverName(ra.getGoodReceiverName());
                        vo.setReceiverAddress(ra.getGoodReceiverProvince() + ra.getGoodReceiverCity()
                                + ra.getGoodReceiverCounty() + ra.getGoodReceiverAddress());
                        vo.setReceiverMobile(ra.getGoodReceiverMobile());
                    }
                    vo.setPuName(item.getPuName());
                    vo.setPuSpec(item.getPuName());
                    vo.setPuCount(item.getPuCount());
                    vo.setPrice(item.getPrice());
                    vo.setSum(item.getPrice().multiply(new BigDecimal(item.getPuCount())));
                    vo.setUserName(userExtend.getName());
                    vo.setMemberCode(userExtend.getMemberCode());
                    vo.setCompanyName(company.getCompanyName());
                    vo.setUserEmail(user.getMail());
                    if (invoice != null) {
                        vo.setInvoiceExist("是");
                        vo.setInvoiceTitle(invoice.getInvoiceTitleContent());
                        vo.setInvoiceContent(invoice.getInvoiceContent());
                        vo.setInvoiceForm(invoice.getInvoiceForm().intValue() == 0 ? "纸质发票" : "电子发票");
                        vo.setInvoiceTitleType(invoice.getInvoiceTitleType().intValue() == 0 ? "个人" : "公司");
                    } else {
                        vo.setInvoiceExist("否");
                    }
                    voList.add(vo);
                }
            }
        }*/
        // 生成Excel
        Workbook wb = new XSSFWorkbook();
        Sheet s = wb.createSheet("分拣导出订单明细");
        String[] header = {"订单号", "子订单编号", "快递单号", "物流公司", "收货人", "收货地址", "联系电话","供应商名称", "产品编码","商品名称", "规格", "数量","结算单价", "单价", "总价",
                "会员姓名", "会员编号", "会员公司", "用户邮箱", "是否开发票", "发票抬头", "发票内容", "发票类型", "抬头类型"};
        // 创建表头
        Row head = s.createRow(0);
        for (int i = 0; i < header.length; i++) {
            head.createCell(i).setCellValue(header[i]);
        }
        completeOrderSortSheet(s, voList);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            wb.write(bos);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.fail("导出失败!");
        }
        String upload = uploadService.fastDFSUpload(bos.toByteArray(), "xlsx");
        // 变更这批订单的子订单的物流状态为分拣中,并且插入操作流水记录
        soFacade.orderSort(orderIds_, operatorId);
        return JsonResult.success("url", upload);
    }

    @Override
    public JsonResult<Map<String, Object>> orderChildSortExport(String orderChildIds, Long operatorId, Long platformId,Long supplierId) {
        JsonResult<List<OrderSortExportVO>> listJsonResult = checkOrderChildStatus(orderChildIds,operatorId,supplierId);
        if(listJsonResult.getCode()==-1){
            return JsonResult.fail(listJsonResult.getError());
        }
        List<OrderSortExportVO> voList = listJsonResult.getData();
        orderChildIds=orderChildIds.replace("[", "");
        orderChildIds=orderChildIds.replace("]", "");
        List<Long> orderIds_ = StringUtils.string2IdList(orderChildIds, ",");
        // 生成Excel
        Workbook wb = new XSSFWorkbook();
        Sheet s = wb.createSheet("分拣导出订单明细");
        //"订单号","子订单编号","物流公司","快递单号","箱号"
        String[] header = {"订单号","子订单编号", "快递单号", "物流公司", "收货人", "收货地址", "联系电话","供应商", "商品名称","产品编码", "规格", "数量", "结算单价"/*, "总价",
                "会员姓名", "会员编号", "会员公司", "用户邮箱", "是否开发票", "发票抬头", "发票内容", "发票类型", "抬头类型"*/};
        // 创建表头
        Row head = s.createRow(0);
        for (int i = 0; i < header.length; i++) {
            head.createCell(i).setCellValue(header[i]);
        }
        completeOrderChildSortSheet(s, voList);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            wb.write(bos);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.fail("导出失败!");
        }
        String upload = uploadService.fastDFSUpload(bos.toByteArray(), "xlsx");
        // 变更这批订单的子订单的物流状态为分拣中,并且插入操作流水记录
        soFacade.orderChildSort(orderIds_, operatorId);
        return JsonResult.success("url", upload);
    }

    private JsonResult<List<OrderSortExportVO>> checkOrderStatus(String orderIds) {
        if (EmptyUtil.isBlank(orderIds))
            return JsonResult.fail("请选择订单");
        // 去除方括号
        orderIds = orderIds.substring(1, orderIds.length() - 1);

        List<Long> orderIds_ = StringUtils.string2IdList(orderIds, ",");
        if (orderIds_.size() == 0) {
            return JsonResult.fail("请选择订单");
        }
        /**
         * 分拣导出 选中需要导出的订单，点击[分拣导出]，后台作如下判断： 选中的订单确认状态是否为已确认且其子订单状态均为待发货，
         * 若不是则提示用户： 所选订单中存在已分拣的订单，请检查后重新选择； 若判断所选项符合导出条件则按照模板导出Excel文件至本地，
         * 并批量更新导出订单的所有子订单物流状态为分拣中。
         */
        // 检验信息,同时生成voList
        List<OrderSortExportVO> voList = new ArrayList<>();
        for (Long oId : orderIds_) {
            // 检查订单信息
            SoDTO so = soFacade.querySoById(oId);
            if (so == null) {
                return JsonResult.fail("id为" + oId + "的订单不存在");
            }
            if (so.getOrderConfirmStatus() != 1) {
                return JsonResult.fail("所选订单中存在未确认的订单，请检查后重新选择");
            }
            getSoDetail(voList,so,oId,false);

        }
        return JsonResult.success(voList);
    }

    private JsonResult<List<OrderSortExportVO>> checkOrderChildStatus(String orderChildIds, Long operatorId,Long supplierId) {
        if (EmptyUtil.isBlank(orderChildIds))
            return JsonResult.fail("请选择子订单");
        if (operatorId ==null )
            return JsonResult.fail("无权操作");
        // 去除方括号
        orderChildIds = orderChildIds.substring(1, orderChildIds.length() - 1);

        if (EmptyUtil.isBlank(orderChildIds)) {
            return JsonResult.fail("请选择子订单");
        }
        List<Long> orderIds_ = StringUtils.string2IdList(orderChildIds, ",");
        if (orderIds_.size() == 0) {
            return JsonResult.fail("请选择子订单");
        }
        /**
         * 分拣导出 选中需要导出的订单，点击[分拣导出]，后台作如下判断： 选中的订单确认状态是否为已确认且其子订单状态均为待发货，
         * 若不是则提示用户： 所选订单中存在已分拣的订单，请检查后重新选择； 若判断所选项符合导出条件则按照模板导出Excel文件至本地，
         * 并批量更新导出订单的所有子订单物流状态为分拣中。
         */
        //首先过滤出该供应商的子订单
        List<SoChildDTO> soChildren = soFacade.findSoChildListBySupplierId(orderIds_,supplierId);

        // 检验信息,同时生成voList
        List<OrderSortExportVO> voList = new ArrayList<>();
        HashMap<String,List<SoChildDTO>> soAndSelectChildMap = new HashMap<String,List<SoChildDTO>>();
        for (SoChildDTO ochild : soChildren) {
            List<SoChildDTO> soSelectChildList = soAndSelectChildMap.get(ochild.getSoId().longValue()+"");
            if(soSelectChildList==null) {
                soSelectChildList = new ArrayList<SoChildDTO>();
                soAndSelectChildMap.put(ochild.getSoId().longValue()+"", soSelectChildList);
            }
            soSelectChildList.add(ochild);
        }
        for (Entry<String,List<SoChildDTO>> entry:soAndSelectChildMap.entrySet()) {
            SoDTO so = soFacade.querySoById(Long.valueOf(entry.getKey()));
            if (so == null) {
                return JsonResult.fail("id为" + entry.getKey() + "的订单不存在");
            }
            if (!Objects.equals(so.getOrderPayStatus(), OrderConstant.ORDER_STATUS_PAYED.getStatus())){
                return JsonResult.fail("所选订单状态存在非已支付状态，请检查后重新选择");
            }
            if (so.getOrderConfirmStatus() != 1) {
                //暂时放开
                //return JsonResult.fail("所选订单中存在未确认的订单，请检查后重新选择");
            }
            getSoDetailFilterBySoChildIds(voList, so, Long.valueOf(entry.getKey()), true, entry.getValue());
        }
        /*
        for (Long oId : orderIds_) {
            // 检查订单信息
            SoDTO so = soFacade.querySoById(oId);
            if (so == null) {
                return JsonResult.fail("id为" + oId + "的订单不存在");
            }
            if (so.getOrderConfirmStatus() != 1) {
                return JsonResult.fail("所选订单中存在未确认的订单，请检查后重新选择");
            }
            getSoDetail(voList,so,oId,true);

        }*/
        return JsonResult.success(voList);
    }
    private List<OrderSortExportVO> getSoDetail(List<OrderSortExportVO> voList,SoDTO so,Long oId,Boolean isCheckSoDelivery){
        // 查询用户信息
        UserDTO user = userFacade.queryUserById(so.getUserId());
        Long companyId = user.getCompanyId();
        CompanyDTO company = userFacade.queryCompanyById(companyId);
        UserExtendDTO userExtend = userFacade.queryUserExtendById(so.getUserId());
        // 检查子订单
        List<SoChildDTO> childs = soFacade.querySoChildListBySoId(oId);
        for (SoChildDTO child : childs) {
            //网店管家订单跳过
            if (Long.valueOf(3L).equals(child.getPerformingParty())) {
                continue;
            }
            if(isCheckSoDelivery){
                if (child.getDeliveryStatus().intValue() != 0) {
                    throw new BusinessException("所选订单中存在已分拣的订单，请检查后重新选择");
                }
            }

            // 查询物流信息
            List<SoPackageDTO> packs = soPackageFacade.queryPackageBySoChildId(child.getId());
            Long receiverAddressId = child.getReceiverAddressId();
            ReceiverAddressDTO ra = null;
            if (receiverAddressId != null) {
                ra = receiverAddressFacade.findReceiverAddressById(receiverAddressId);
            }
            // 查询发票信息
            SoInvoiceDTO invoice = soInvoiceFacade.querySoInvoiceBySoChildId(child.getId());
            // 查询订单项
            List<SoItemDTO> items = soItemFacade.querySoItemsBySoChildId(child.getId());

            //查询所有箱子
            List<SoPackageBoxDTO> boxes = soPackageFacade.queryBoxListBySoChildId(child.getId());
            StringBuilder sb = new StringBuilder();
            if (boxes.size()>0) {
                for (SoPackageBoxDTO b : boxes) {
                    sb.append(b.getSoBoxCode()).append("/");
                }
            }

            for (SoItemDTO item : items) {
                OrderSortExportVO vo = new OrderSortExportVO();
                vo.setOrderCode(so.getOrderCode());
                vo.setChildCode(child.getChildCode());
                //bug2959start
                vo.setSoBoxCode(sb.toString());
                //bug2959end
                if (packs != null && packs.size()>0) {
                    vo.setDeliveryCode(packs.get(0).getDeliveryCode());
                    vo.setDeliveryCompany(packs.get(0).getDeliveryCompanyName());
                }
                if (ra != null) {
                    vo.setReceiverName(ra.getGoodReceiverName());
                    vo.setReceiverAddress(ra.getGoodReceiverProvince() + ra.getGoodReceiverCity()
                            + ra.getGoodReceiverCounty() + ra.getGoodReceiverAddress());
                    vo.setReceiverMobile(ra.getGoodReceiverMobile());
                }
                BigDecimal supplierPrice = null;
                String productUnitSerialNumber="-";
                String supplierName ="-";
                if(item.getSnapshot()!=null) {
                    logger.info(item.getSnapshot());
                    if(item.getSource()==null || item.getSource().intValue()<3 ) {
                        CommodityProductUnitDTO selfProduct = JSON.parseObject(item.getSnapshot(), CommodityProductUnitDTO.class);
                        if(selfProduct!=null && selfProduct.getSupplierPrice()!=null) {
                            supplierPrice = selfProduct.getSupplierPrice();
                        }
                        productUnitSerialNumber = getProductUnitSerialNumber(selfProduct);
                        supplierName = getSupplierName(item.getSupplierId());
                    }else if(item.getSource()!=null && item.getSource().intValue()==3) {
                        JdProductDTO jdProduct = JSON.parseObject(item.getSnapshot(), JdProductDTO.class);

                        if(jdProduct!=null && jdProduct.getLedger()!=null) {
                            JSONObject ledger = JSON.parseObject(jdProduct.getLedger());
                            if(ledger.containsKey("jdPrice")) {
                                supplierPrice = ledger.getBigDecimal("jdPrice");
                            }
                        }
                        supplierName = "京东";
                    }else if(item.getSource()!=null && item.getSource().intValue()==4){
                        supplierName = "蛋糕叔叔";
                        if(EmptyUtil.isNotEmpty(item.getSnapshot())){
                            CakeProductDetailDTO snap = JSON.parseObject(item.getSnapshot(), CakeProductDetailDTO.class);
                            CakeProductDetailSpecsDTO specsDTO = productFacade.getCakeProductSkuInfo(snap,String.valueOf(item.getPuId()));
                            if(EmptyUtil.isNotEmpty(specsDTO.getClearing_price())){
                                supplierPrice =new BigDecimal(specsDTO.getClearing_price());
                            }

                        }
                        productUnitSerialNumber = item.getExternalProductId();

                    }else if(item.getSource()!=null && item.getSource().intValue()==5){
                        supplierName = "全球购";
                        if(EmptyUtil.isNotEmpty(item.getSnapshot())){
                            ChannelProductDetailVO snap = JSON.parseObject(item.getSnapshot(), ChannelProductDetailVO.class);
                            List<ChannelProductBatchDTO> batchDTOList = snap.getBatchDTOList();
                            ChannelProductBatchDTO batchDTO = productFacade.getCurrBatch(item.getPuId()+"",batchDTOList);
                            supplierPrice =batchDTO.getPriceSettleMent();
                        }
                        productUnitSerialNumber = item.getExternalProductId();
                    }else if(item.getSource()!=null && item.getSource().intValue()==9){
                        supplierName = "清美";
                    }
                }

                if(supplierPrice!=null) {
                    vo.setSupplierPrice(supplierPrice);
                }
                vo.setSupplierName(supplierName);
                vo.setPuName(item.getPuName());
                vo.setPuSpec(item.getPuName());
                vo.setPuCount(item.getPuCount());
                vo.setMerchantProductSerialNumber(productUnitSerialNumber);
                vo.setPrice(item.getPrice());
                vo.setSum(item.getPrice().multiply(new BigDecimal(item.getPuCount()+"")));
                vo.setUserName(userExtend.getName());
                vo.setMemberCode(userExtend.getMemberCode());
                vo.setCompanyName(company.getCompanyName());
                vo.setUserEmail(user.getMail());
                if (invoice != null) {
                    vo.setInvoiceExist("是");
                    vo.setInvoiceTitle(invoice.getInvoiceTitleContent());
                    vo.setInvoiceContent(invoice.getInvoiceContent());
                    if(EmptyUtil.isNotEmpty(invoice.getInvoiceForm())){
                        vo.setInvoiceForm(invoice.getInvoiceForm().intValue() == 0 ? "纸质发票" : "电子发票");
                    }
                    if(EmptyUtil.isNotEmpty(invoice.getInvoiceTitleType())){
                        vo.setInvoiceTitleType(invoice.getInvoiceTitleType().intValue() == 0 ? "个人" : "公司");
                    }
                } else {
                    vo.setInvoiceExist("否");
                }
                voList.add(vo);
            }
        }
        return voList;
    }

    @Resource
    private ProductAttValueClient productAttValueClient;
    @Resource
    private SupplierClient supplierClient;

    // 查询用户信息
    private List<OrderSortExportVO> getSoDetailFilterBySoChildIds(List<OrderSortExportVO> voList,SoDTO so,Long oId,Boolean isCheckSoDelivery,List<SoChildDTO> childFilterIds){
        if(childFilterIds==null||childFilterIds.size()==0) {
            return null;
        }
        UserDTO user = userFacade.queryUserById(so.getUserId());
        Long companyId = user.getCompanyId();
        CompanyDTO company = userFacade.queryCompanyById(companyId);
        UserExtendDTO userExtend = userFacade.queryUserExtendById(so.getUserId());
        HashMap<String,SoChildDTO> childFilterMap = new HashMap<String,SoChildDTO>();
        for(SoChildDTO filterOne : childFilterIds) {
            if(!childFilterMap.containsKey(filterOne.getId().longValue()+"")) {
                childFilterMap.put(filterOne.getId().longValue()+"", filterOne);
            }
        }


        // 检查子订单
        List<SoChildDTO> childs = soFacade.querySoChildListBySoId(oId);
        for (SoChildDTO child : childs) {
            //网店管家订单跳过
            if (Long.valueOf(3L).equals(child.getPerformingParty())) {
                continue;
            }
            if(!childFilterMap.containsKey(child.getId().longValue()+"")) {
                continue;
            }
            if (Objects.equals(child.getCancelStatus(), 1)){
                throw new BusinessException("所选订单状态存在非已支付状态，请检查后重新选择");
            }
            if(isCheckSoDelivery){
                if (child.getDeliveryStatus().intValue() != 0) {
                    //throw new BusinessException("所选订单中存在已分拣的订单，请检查后重新选择");
                }
            }
            // 查询物流信息
            List<SoPackageDTO> packs = soPackageFacade.queryPackageBySoChildId(child.getId());
            Long receiverAddressId = child.getReceiverAddressId();
            ReceiverAddressDTO ra = null;
            if (receiverAddressId != null) {
                ra = receiverAddressFacade.findReceiverAddressById(receiverAddressId);
            }
            // 查询发票信息
            SoInvoiceDTO invoice = soInvoiceFacade.querySoInvoiceBySoChildId(child.getId());
            // 查询订单项
            List<SoItemDTO> items = soItemFacade.querySoItemsBySoChildId(child.getId());

            //查询所有箱子
            List<SoPackageBoxDTO> boxes = soPackageFacade.queryBoxListBySoChildId(child.getId());
            StringBuilder sb = new StringBuilder();
            if (boxes.size()>0) {
                for (SoPackageBoxDTO b : boxes) {
                    sb.append(b.getSoBoxCode()).append("/");
                }
            }

            for (SoItemDTO item : items) {
                OrderSortExportVO vo = new OrderSortExportVO();
                vo.setOrderCode(so.getOrderCode());
                vo.setChildCode(child.getChildCode());
                //bug2959start
                vo.setSoBoxCode(sb.toString());
                //bug2959end
                if (packs != null && packs.size()>0) {
                    vo.setDeliveryCode(packs.get(0).getDeliveryCode());
                    vo.setDeliveryCompany(packs.get(0).getDeliveryCompanyName());
                }
                if (ra != null) {
                    vo.setReceiverName(ra.getGoodReceiverName());
                    vo.setReceiverAddress(ra.getGoodReceiverProvince() + ra.getGoodReceiverCity()
                            + ra.getGoodReceiverCounty() + ra.getGoodReceiverAddress());
                    vo.setReceiverMobile(ra.getGoodReceiverMobile());
                }
                BigDecimal supplierPrice = null;
                String productUnitSerialNumber="-";
                String supplierName ="-";
                if(item.getSnapshot()!=null) {
                    logger.info(item.getSnapshot());
                    if(item.getSource()==null || item.getSource().intValue()<3 ) {
                        CommodityProductUnitDTO selfProduct = JSON.parseObject(item.getSnapshot(), CommodityProductUnitDTO.class);
                        if(selfProduct!=null && selfProduct.getSupplierPrice()!=null) {
                            supplierPrice = selfProduct.getSupplierPrice();
                        }

                        productUnitSerialNumber = getProductUnitSerialNumber(selfProduct);
                        supplierName = getSupplierName(item.getSupplierId());
                    }else if(item.getSource()!=null && item.getSource().intValue()==3) {
                        JdProductDTO jdProduct = JSON.parseObject(item.getSnapshot(), JdProductDTO.class);

                        if(jdProduct!=null && jdProduct.getLedger()!=null) {
                            JSONObject ledger = JSON.parseObject(jdProduct.getLedger());
                            if(ledger.containsKey("jdPrice")) {
                                supplierPrice = ledger.getBigDecimal("jdPrice");
                            }
                        }
                        supplierName="京东";
                    }
                }
                if(supplierPrice!=null) {
                    vo.setSupplierPrice(supplierPrice);
                }
                vo.setPuName(item.getPuName());
                vo.setPuSpec(item.getPuName());
                vo.setPuCount(item.getPuCount());
                vo.setMerchantProductSerialNumber(productUnitSerialNumber);
                vo.setPrice(item.getPrice());
                vo.setSum(item.getPrice().multiply(new BigDecimal(item.getPuCount()+"")));
                vo.setUserName(userExtend.getName());
                vo.setMemberCode(userExtend.getMemberCode());
                vo.setCompanyName(company.getCompanyName());
                vo.setUserEmail(user.getMail());
                vo.setSupplierName(supplierName);
                if (invoice != null) {
                    vo.setInvoiceExist("是");
                    vo.setInvoiceTitle(invoice.getInvoiceTitleContent());
                    vo.setInvoiceContent(invoice.getInvoiceContent());
                    if(EmptyUtil.isNotEmpty(invoice.getInvoiceForm())){
                        vo.setInvoiceForm(invoice.getInvoiceForm().intValue() == 0 ? "纸质发票" : "电子发票");
                    }
                    if(EmptyUtil.isNotEmpty(invoice.getInvoiceTitleType())){
                        vo.setInvoiceTitleType(invoice.getInvoiceTitleType().intValue() == 0 ? "个人" : "公司");
                    }
                } else {
                    vo.setInvoiceExist("否");
                }
                voList.add(vo);
            }
        }
        return voList;
    }

    private String getProductUnitSerialNumber(CommodityProductUnitDTO selfProduct) {
        if(selfProduct !=null && EmptyUtil.isNotEmpty(selfProduct.getStandardProductUnitId())){
            AttValueCustomReqVO queryVO = new AttValueCustomReqVO();
            queryVO.setStandardProductUnitId(selfProduct.getStandardProductUnitId());
            queryVO.setAttrName("产品编码");
            ProductAttValueDTO productAttValueDTO = productAttValueClient.findAttValueCustomBySpuId(queryVO);
            if(productAttValueDTO !=null && EmptyUtil.isNotEmpty(productAttValueDTO.getAttValueCustom())){
                return productAttValueDTO.getAttValueCustom();
            }
        }
        return "-";
    }

    private String getSupplierName(Long supplierId){
        try {
            if(supplierId == null){
                return "-";
            }
            Enterprise enterprise = supplierClient.findById(supplierId.intValue());
            if(enterprise !=null && EmptyUtil.isNotEmpty(enterprise.getName())){
                return enterprise.getName();
            }
        }catch (Exception e){

        }

        return "-";
    }


    private List<OrderSortExportVO> getSoDetailOrderExport(List<OrderSortExportVO> voList,SoDTO so,Long oId,Boolean isCheckSoDelivery){
        // 查询用户信息
        UserDTO user = userFacade.queryUserById(so.getUserId());
        Long companyId = user.getCompanyId();
        CompanyDTO company = userFacade.queryCompanyById(companyId);
        UserExtendDTO userExtend = userFacade.queryUserExtendById(so.getUserId());
        // 检查子订单
        List<SoChildDTO> childs = soFacade.querySoChildListBySoId(oId);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (SoChildDTO child : childs) {
            //网店管家订单跳过
            if (Long.valueOf(3L).equals(child.getPerformingParty())) {
                continue;
            }
            if(isCheckSoDelivery){
                if (child.getDeliveryStatus().intValue() != 0) {
                    throw new BusinessException("所选订单中存在已分拣的订单，请检查后重新选择");
                }
            }

            // 查询物流信息
            List<SoPackageDTO> packs = soPackageFacade.queryPackageBySoChildId(child.getId());
            Long receiverAddressId = child.getReceiverAddressId();
            ReceiverAddressDTO ra = null;
            if (receiverAddressId != null) {
                ra = receiverAddressFacade.findReceiverAddressById(receiverAddressId);
            }
            // 查询发票信息
            SoInvoiceDTO invoice = soInvoiceFacade.querySoInvoiceBySoChildId(child.getId());
            // 查询订单项
            List<SoItemDTO> items = soItemFacade.querySoItemsBySoChildId(child.getId());

            //查询所有箱子
            List<SoPackageBoxDTO> boxes = soPackageFacade.queryBoxListBySoChildId(child.getId());
            StringBuilder sb = new StringBuilder();
            if (boxes.size()>0) {
                for (SoPackageBoxDTO b : boxes) {
                    sb.append(b.getSoBoxCode()).append("/");
                }
            }
            Map<Long,CommodityProductUnitDTO> selfProdMap=new HashMap<>();
            Map<Long,String> jdProdMap=new HashMap<>();
            String deliveryCode=null;
            String deliveryCompany=null;
            String deliveryDate=null;
            if (packs != null && packs.size()>0) {
                deliveryCode=packs.get(0).getDeliveryCode();
                deliveryCompany=packs.get(0).getDeliveryCompanyName();
                if (Objects.nonNull(packs.get(0).getDeliveryDate())){
                    deliveryDate=DateUtils.formatDateTime(packs.get(0).getDeliveryDate());
                }
            }
            for (SoItemDTO item : items) {
                OrderSortExportVO vo = new OrderSortExportVO();
                vo.setOrderCode(so.getOrderCode());
                vo.setOrderCreateTime(EmptyUtil.isNotEmpty(so.getCreateTime())?sdf.format(so.getCreateTime()):"");
                vo.setChildCode(child.getChildCode());
                //bug2959start
                vo.setSoBoxCode(sb.toString());
                //bug2959end
                vo.setDeliveryCode(deliveryCode);
                vo.setDeliveryCompany(deliveryCompany);
                vo.setDeliveryDate(deliveryDate);
                if (ra != null) {
                    vo.setReceiverName(ra.getGoodReceiverName());
                    vo.setReceiverAddress(ra.getGoodReceiverProvince() + ra.getGoodReceiverCity()
                            + ra.getGoodReceiverCounty() + ra.getGoodReceiverAddress());
                    vo.setReceiverMobile(ra.getGoodReceiverMobile());
                }
                vo.setPuName(item.getPuName());
                vo.setPuSpec(item.getPuName());
                vo.setPuCount(item.getPuCount());
                vo.setMerchantId(child.getPerformingParty());
                MerchantDTO merchant = merchantFacade.findMerchantById(child.getPerformingParty());
                logger.info("merchantId="+item.getMerchantId());
                logger.info("merchant="+JSON.toJSONString(merchant));
                vo.setMerchantName(merchant.getName());
                if(item.getSupplierId()!=null) {
                    Enterprise supplier = userFacade.findSupplier(item.getSupplierId().intValue());
                    vo.setSupplierName(supplier.getName());
                    vo.setSupplierAccount(supplier.getAdminLoginName());
                    if(item.getSnapshot()!=null) {
                        CommodityProductUnitDTO snap = JSON.parseObject(item.getSnapshot(), CommodityProductUnitDTO.class);
                        if(snap.getSupplierPrice()!=null) {
                            vo.setSupplierPrice(snap.getSupplierPrice());
                            vo.setProductCategory(snap.getProductCategory());
                            vo.setMerchantProductSerialNumber(snap.getMerchantProductSerialNumber());
                        }
                    }
                    if (EmptyUtil.isEmpty(vo.getMerchantProductSerialNumber())){
                        CommodityProductUnitDTO puType0 = productFacade.queryPuByIdAndSupplierPrice(item.getPuId());
                        if (Objects.nonNull(puType0)){
                            vo.setMerchantProductSerialNumber(puType0.getMerchantProductSerialNumber());
                            vo.setProductCategory(puType0.getProductCategory());
                        }
                    }
                }else {
                    if(item.getSource()!=null && item.getSource().intValue()==3) {
                        vo.setSupplierName("京东");
                        String categoryIds=null;
                        if(item.getSnapshot()!=null) {
                            JdProductDTO snap  = JSON.parseObject(item.getSnapshot(), JdProductDTO.class);
                            if(snap.getLedger()!=null) {
                                categoryIds=snap.getCategoryIds();
                                JSONObject ledgerObj = JSON.parseObject(snap.getLedger());
                                if(ledgerObj.containsKey("jdPrice")) {
                                    vo.setSupplierPrice(ledgerObj.getBigDecimal("jdPrice"));
                                }
                            }
                        }
                        String categoryName=null;
                        if (jdProdMap.containsKey(item.getPuId())){
                            categoryName=jdProdMap.get(item.getPuId());
                        }else {
                            if (EmptyUtil.isEmpty(categoryIds)){
                                JSONObject detail = jdUtils.getDetail(jedisUtil, item.getPuId());
                                if (Objects.nonNull(detail) && EmptyUtil.isNotEmpty(detail.getString("category"))){
                                    categoryIds = detail.getString("category");
                                }
                            }
                            if (EmptyUtil.isNotEmpty(categoryIds)){
                                String[] split = categoryIds.split(";");
                                Long catId = Long.valueOf(split[split.length - 1]);
                                categoryName = productFacade.getJdCategoryName(catId);
                                jdProdMap.put(item.getPuId(),categoryName);
                            }
                        }
                        vo.setProductCategory(categoryName);
                        vo.setMerchantProductSerialNumber(String.valueOf(item.getPuId()));
                    }else if (EmptyUtil.isNotEmpty(item.getSource()) && Objects.equals(ThirdConst.Source.QM,item.getSource())){
                        vo.setSupplierName("清美");
                        if(EmptyUtil.isNotEmpty(item.getSnapshot())) {
                            QingMeiChildItemDTO snap  = JSON.parseObject(item.getSnapshot(), QingMeiChildItemDTO.class);
                            vo.setProductCategory(snap.getCategory());
                            vo.setMerchantProductSerialNumber(String.valueOf(item.getPuId()));
                        }
                    }else if(EmptyUtil.isNotEmpty(item.getSource()) && Objects.equals(ThirdConst.Source.CAKE,item.getSource())){
                        vo.setSupplierName("蛋糕叔叔");
                        vo.setMerchantProductSerialNumber(String.valueOf(item.getPuId()));
                        if(EmptyUtil.isNotEmpty(item.getSnapshot())) {
                            CakeProductDetailDTO snap = JSON.parseObject(item.getSnapshot(), CakeProductDetailDTO.class);
                            CakeProductDetailSpecsDTO specsDTO = productFacade.getCakeProductSkuInfo(snap,String.valueOf(item.getPuId()));
                            if(specsDTO !=null){
                                vo.setSupplierPrice(new BigDecimal(specsDTO.getClearing_price()).setScale(2));
                            }
                        }


                    }else if(EmptyUtil.isNotEmpty(item.getSource()) && Objects.equals(ThirdConst.Source.WORLD,item.getSource())){
                        vo.setSupplierName("全球购");
                        vo.setMerchantProductSerialNumber(String.valueOf(item.getPuId()));
                        if(EmptyUtil.isNotEmpty(item.getSnapshot())) {
                            ChannelProductDetailVO snap = JSON.parseObject(item.getSnapshot(), ChannelProductDetailVO.class);
                            List<ChannelProductBatchDTO> batchDTOList = snap.getBatchDTOList();
                            ChannelProductBatchDTO batchDTO = productFacade.getCurrBatch(item.getPuId()+"",batchDTOList);
                            if(batchDTO !=null){
                                vo.setSupplierPrice(batchDTO.getPriceSettleMent());
                            }
                        }
                    }
                }
                vo.setPrice(item.getPrice());
                vo.setDeliveryFeeAver(EmptyUtil.isEmpty(item.getDeliveryFeeAver())?BigDecimal.ZERO:item.getDeliveryFeeAver());
                vo.setSum(item.getPrice().multiply(new BigDecimal(item.getPuCount()+"")));
                vo.setUserName(userExtend.getName());
                vo.setMemberCode(userExtend.getMemberCode());
                vo.setCompanyName(company.getCompanyName());
                vo.setUserEmail(user.getMail());
                vo.setRefundCount(item.getRefundCount());
                vo.setRefundAmount(com.egeo.components.utils.StringUtil.nullToZero(item.getRefundAmount()).add(com.egeo.components.utils.StringUtil.nullToZero(item.getRefundDeliveryFee())));
                if (EmptyUtil.isNotEmpty(item.getTaxRate())){
                    vo.setTaxRate(item.getTaxRate().toPlainString()+"%");
                }
                if (invoice != null) {
                    vo.setInvoiceExist("是");
                    vo.setInvoiceTitle(invoice.getInvoiceTitleContent());
                    vo.setInvoiceContent(invoice.getInvoiceContent());
                    if(EmptyUtil.isNotEmpty(invoice.getInvoiceForm())){
                        vo.setInvoiceForm(invoice.getInvoiceForm().intValue() == 0 ? "纸质发票" : "电子发票");
                    }
                    if(EmptyUtil.isNotEmpty(invoice.getInvoiceTitleType())){
                        vo.setInvoiceTitleType(invoice.getInvoiceTitleType().intValue() == 0 ? "个人" : "公司");
                    }
                } else {
                    vo.setInvoiceExist("否");
                }
                voList.add(vo);
            }
        }
        return voList;
    }


    private List<OrderSortExportVO> getSoDetailOrderExport(List<Long> orderIds){
        List<OrderSortExportVO> voList=soFacade.querySoDetailOrderExport(orderIds);
        Map<Long,String> jdProdMap=new HashMap<>();
        for (OrderSortExportVO vo : voList ){
            if(vo.getSupplierId()!=null) {
                Enterprise supplier = userFacade.findSupplier(vo.getSupplierId().intValue());
                vo.setSupplierName(supplier.getName());
                vo.setSupplierAccount(supplier.getAdminLoginName());
                if(vo.getSnapshot()!=null) {
                    CommodityProductUnitDTO snap = JSON.parseObject(vo.getSnapshot(), CommodityProductUnitDTO.class);
                    if(snap.getSupplierPrice()!=null) {
                        vo.setSupplierPrice(snap.getSupplierPrice());
                        vo.setProductCategory(snap.getProductCategory());
                        vo.setMerchantProductSerialNumber(snap.getMerchantProductSerialNumber());
                    }
                }
            }else {
                if(vo.getSource()!=null && vo.getSource().intValue()==3) {
                    vo.setSupplierName("京东");
                    String categoryIds=null;
                    if(vo.getSnapshot()!=null) {
                        JdProductDTO snap  = JSON.parseObject(vo.getSnapshot(), JdProductDTO.class);
                        if(snap.getLedger()!=null) {
                            categoryIds=snap.getCategoryIds();
                            JSONObject ledgerObj = JSON.parseObject(snap.getLedger());
                            if(ledgerObj.containsKey("jdPrice")) {
                                vo.setSupplierPrice(ledgerObj.getBigDecimal("jdPrice"));
                            }
                        }
                    }
                    String categoryName=null;
                    if (jdProdMap.containsKey(vo.getPuId())){
                        categoryName=jdProdMap.get(vo.getPuId());
                    }else {
                        if (EmptyUtil.isEmpty(categoryIds)){
                            JSONObject detail = jdUtils.getDetail(jedisUtil, vo.getPuId());
                            if (Objects.nonNull(detail) && EmptyUtil.isNotEmpty(detail.getString("category"))){
                                categoryIds = detail.getString("category");
                            }
                        }
                        if (EmptyUtil.isNotEmpty(categoryIds)){
                            String[] split = categoryIds.split(";");
                            Long catId = Long.valueOf(split[split.length - 1]);
                            categoryName = productFacade.getJdCategoryName(catId);
                            jdProdMap.put(vo.getPuId(),categoryName);
                        }
                    }
                    vo.setProductCategory(categoryName);
                    vo.setMerchantProductSerialNumber(String.valueOf(vo.getPuId()));
                }else if (EmptyUtil.isNotEmpty(vo.getSource()) && Objects.equals(ThirdConst.Source.QM,vo.getSource())){
                    vo.setSupplierName("清美");
                    if(EmptyUtil.isNotEmpty(vo.getSnapshot())) {
                        QingMeiChildItemDTO snap  = JSON.parseObject(vo.getSnapshot(), QingMeiChildItemDTO.class);
                        vo.setProductCategory(snap.getCategory());
                        vo.setMerchantProductSerialNumber(String.valueOf(vo.getPuId()));
                    }
                }
                vo.setDeliveryFeeAver(EmptyUtil.isEmpty(vo.getDeliveryFeeAver())?BigDecimal.ZERO:vo.getDeliveryFeeAver());
                vo.setSum(vo.getPrice().multiply(new BigDecimal(vo.getPuCount()+"")));
                if (EmptyUtil.isNotEmpty(vo.getTaxRate())){
                    vo.setTaxRate(vo.getTaxRate()+"%");
                }
            }
        }
        return voList;
    }

    // {"订单号","子订单编号", "快递单号", "物流公司", "收货人", "收货地址", "联系电话", "商品名称", "规格", "数量"

    private void completeOrderChildSortSheet(Sheet s, List<OrderSortExportVO> voList) {
        for (int i = 0; i < voList.size(); i++) {
            Row r = s.createRow(i + 1);
            OrderSortExportVO vo = voList.get(i);
            r.createCell(0).setCellValue(vo.getOrderCode());
            r.createCell(1).setCellValue(vo.getChildCode());
            r.createCell(2).setCellValue(vo.getDeliveryCode());
            r.createCell(3).setCellValue(vo.getDeliveryCompany());
            r.createCell(4).setCellValue(vo.getReceiverName());
            r.createCell(5).setCellValue(vo.getReceiverAddress());
            r.createCell(6).setCellValue(vo.getReceiverMobile());
            r.createCell(7).setCellValue(vo.getSupplierName());
            r.createCell(8).setCellValue(vo.getPuName());
            r.createCell(9).setCellValue(vo.getMerchantProductSerialNumber());
            r.createCell(10).setCellValue(vo.getPuSpec());
            r.createCell(11).setCellValue(vo.getPuCount());
            r.createCell(12).setCellValue(vo.getSupplierPrice()==null?"":vo.getSupplierPrice().toPlainString());
            /* r.createCell(11).setCellValue(vo.getSum().doubleValue());
            r.createCell(12).setCellValue(vo.getUserName());
            r.createCell(13).setCellValue(vo.getMemberCode());
            r.createCell(14).setCellValue(vo.getCompanyName());
            r.createCell(15).setCellValue(vo.getUserEmail());
            r.createCell(16).setCellValue(vo.getInvoiceExist());
            r.createCell(17).setCellValue(vo.getInvoiceTitle());
            r.createCell(18).setCellValue(vo.getInvoiceContent());
            r.createCell(19).setCellValue(vo.getInvoiceForm());
            r.createCell(20).setCellValue(vo.getInvoiceTitleType());*/
        }
    }
    /**
     * 将数据填写进分拣导出excel表格
     *
     * @param s
     * @param voList
     */
    private void completeOrderSortSheet(Sheet s, List<OrderSortExportVO> voList) {
        for (int i = 0; i < voList.size(); i++) {
            Row r = s.createRow(i + 1);
            OrderSortExportVO vo = voList.get(i);
            r.createCell(0).setCellValue(vo.getOrderCode());
            r.createCell(1).setCellValue(vo.getChildCode());
            r.createCell(2).setCellValue(vo.getDeliveryCode());
            r.createCell(3).setCellValue(vo.getDeliveryCompany());
            r.createCell(4).setCellValue(vo.getReceiverName());
            r.createCell(5).setCellValue(vo.getReceiverAddress());
            r.createCell(6).setCellValue(vo.getReceiverMobile());
            r.createCell(7).setCellValue(vo.getSupplierName());
            r.createCell(8).setCellValue(vo.getMerchantProductSerialNumber());
            r.createCell(9).setCellValue(vo.getPuName());
            r.createCell(10).setCellValue(vo.getPuSpec());
            r.createCell(11).setCellValue(vo.getPuCount());
            r.createCell(12).setCellValue(vo.getSupplierPrice()==null?"":vo.getSupplierPrice().toPlainString());
            r.createCell(13).setCellValue(vo.getPrice().doubleValue());
            r.createCell(14).setCellValue(vo.getSum().doubleValue());
            r.createCell(15).setCellValue(vo.getUserName());
            r.createCell(16).setCellValue(vo.getMemberCode());
            r.createCell(17).setCellValue(vo.getCompanyName());
            r.createCell(18).setCellValue(vo.getUserEmail());
            r.createCell(19).setCellValue(vo.getInvoiceExist());
            r.createCell(20).setCellValue(vo.getInvoiceTitle());
            r.createCell(21).setCellValue(vo.getInvoiceContent());
            r.createCell(22).setCellValue(vo.getInvoiceForm());
            r.createCell(23).setCellValue(vo.getInvoiceTitleType());
        }
    }
    private void completeOrderDetailSheetExport(Sheet s, List<OrderSortExportVO> voList) {
        for (int i = 0; i < voList.size(); i++) {
            Row r = s.createRow(i + 1);
            OrderSortExportVO vo = voList.get(i);
            int cellCunt = 0;
            r.createCell(cellCunt++).setCellValue(vo.getOrderCode());
            r.createCell(cellCunt++).setCellValue(vo.getChildCode());
            r.createCell(cellCunt++).setCellValue(vo.getDeliveryCode());
            r.createCell(cellCunt++).setCellValue(vo.getSoBoxCode());
            r.createCell(cellCunt++).setCellValue(vo.getDeliveryCompany());
            r.createCell(cellCunt++).setCellValue(vo.getReceiverName());
            r.createCell(cellCunt++).setCellValue(vo.getReceiverAddress());
            r.createCell(cellCunt++).setCellValue(vo.getReceiverMobile());
            r.createCell(cellCunt++).setCellValue(vo.getMerchantName());
            r.createCell(cellCunt++).setCellValue(vo.getPuName());
            r.createCell(cellCunt++).setCellValue(vo.getPuSpec());
            r.createCell(cellCunt++).setCellValue(vo.getSupplierName());
            r.createCell(cellCunt++).setCellValue(vo.getSupplierAccount());
            r.createCell(cellCunt++).setCellValue(vo.getPuCount());
            r.createCell(cellCunt++).setCellValue(vo.getTaxRate());
            r.createCell(cellCunt++).setCellValue(vo.getSupplierPrice()==null?"":vo.getSupplierPrice().toPlainString());
            r.createCell(cellCunt++).setCellValue(vo.getPrice().doubleValue());
            r.createCell(cellCunt++).setCellValue(vo.getSum().doubleValue());
            r.createCell(cellCunt++).setCellValue(vo.getDeliveryFeeAver()==null?0:vo.getDeliveryFeeAver().doubleValue());
            r.createCell(cellCunt++).setCellValue(vo.getRefundCount()==null?0:vo.getRefundCount().doubleValue());
            r.createCell(cellCunt++).setCellValue(vo.getRefundAmount()==null?0:vo.getRefundAmount().doubleValue());
            r.createCell(cellCunt++).setCellValue(vo.getUserName());
            r.createCell(cellCunt++).setCellValue(vo.getMemberCode());
            r.createCell(cellCunt++).setCellValue(vo.getCompanyName());
            r.createCell(cellCunt++).setCellValue(vo.getUserEmail());
            r.createCell(cellCunt++).setCellValue(vo.getInvoiceExist());
            r.createCell(cellCunt++).setCellValue(vo.getInvoiceCode());
            r.createCell(cellCunt++).setCellValue(vo.getInvoiceTitle());
            r.createCell(cellCunt++).setCellValue(vo.getInvoiceContent());
            r.createCell(cellCunt++).setCellValue(vo.getInvoiceForm());
            r.createCell(cellCunt++).setCellValue(vo.getInvoiceTitleType());
            r.createCell(cellCunt++).setCellValue(vo.getOrderCreateTime());
            r.createCell(cellCunt++).setCellValue(vo.getDeliveryDate());
            r.createCell(cellCunt++).setCellValue(vo.getMerchantProductSerialNumber());
            r.createCell(cellCunt++).setCellValue(vo.getProductCategory());
        }
    }

    private void completeOrderDetailSheetCExport(Sheet s, List<OrderSortExportVO> voList) {
        for (int i = 0; i < voList.size(); i++) {
            Row r = s.createRow(i + 1);
            OrderSortExportVO vo = voList.get(i);
            int cellCunt = 0;
            r.createCell(cellCunt++).setCellValue(vo.getOrderCode());
            r.createCell(cellCunt++).setCellValue(vo.getChildCode());
            r.createCell(cellCunt++).setCellValue(vo.getReceiverName());
            r.createCell(cellCunt++).setCellValue(vo.getReceiverAddress());
            r.createCell(cellCunt++).setCellValue(vo.getReceiverMobile());

            r.createCell(cellCunt++).setCellValue(vo.getPuName());
            r.createCell(cellCunt++).setCellValue(vo.getPuSpec());
            r.createCell(cellCunt++).setCellValue(vo.getPuCount());
            r.createCell(cellCunt++).setCellValue(vo.getSum().doubleValue());
            r.createCell(cellCunt++).setCellValue(vo.getDeliveryFeeAver()==null?0:vo.getDeliveryFeeAver().doubleValue());
            r.createCell(cellCunt++).setCellValue(vo.getOrderCreateTime());
        }
    }
    @Override
    public JsonResult<Map<String, Object>> orderExport(String orderIds) {
        if (EmptyUtil.isBlank(orderIds))
            return JsonResult.fail("请选择订单");
        // 去除方括号
        orderIds = orderIds.substring(1, orderIds.length() - 1);
        List<Long> orderIds_ = StringUtils.string2IdList(orderIds, ",");
        List<OrderListExportVO> voList = new ArrayList<>();
        List<OrderSortExportVO> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<SoDTO> soDTOList = soFacade.getSoByIds(orderIds_);
        CardUseRecordDTO dto = new CardUseRecordDTO();
        BuyCardItemRefundDTO buyCardItemRefundQueryDTO = new BuyCardItemRefundDTO();
        for (SoDTO so:soDTOList){

            if(EmptyUtil.isNotEmpty(so.getUserId())){
                UserDTO user = userFacade.queryUserById(so.getUserId());
                if(user == null){
                    continue;
                }
            }
            OrderListExportVO vo = new OrderListExportVO();
            vo.setOrderCode(so.getOrderCode());
            vo.setOrderStatus(OrderConstant.translate(so.getOrderStatus()));
            vo.setPayStatus(translateOrderPayStatus(so.getOrderPayStatus()));
            vo.setUseFubi(so.getUseFubi().intValue() == 0 ? "否" : "是");
            Integer cashPayType = so.getCashPayType();
            vo.setCashPayMethod(cashPayType == null ? "未选择" : tanslateOrderCashPayMethod(so.getCashPayType()));
            // 订单结算总额
            vo.setSum(so.getOrderAmount().subtract(so.getOrderPromotionDiscount()));
            vo.setPayByFubi(so.getOrderPaidByFubi());
            vo.setPayByCash(so.getOrderPaidByCash());
            vo.setPayByJiDian(so.getOrderPaidByJidian());
            vo.setPayByBuyCard(so.getOrderCardPaid());
            vo.setRefundCash(com.egeo.components.utils.StringUtil.nullToZero(so.getRefundCash()));
            vo.setRefundFubi(com.egeo.components.utils.StringUtil.nullToZero(so.getRefundFubi()));
            vo.setRefundJidian(com.egeo.components.utils.StringUtil.nullToZero(so.getRefundJidian()));
            vo.setRefundCard(com.egeo.components.utils.StringUtil.nullToZero(so.getRefundCard()));
            vo.setRefundSum(vo.getRefundCash().add(vo.getRefundFubi()).add(vo.getRefundJidian()).add(vo.getRefundCard()));
            vo.setCreateTime(sdf.format(so.getCreateTime()));
            if (EmptyUtil.isNotEmpty(so.getOrderPaymentConfirmDate())){
                vo.setPayTime(sdf.format(so.getOrderPaymentConfirmDate()));
            }
            dto.setSoId(so.getId());
            List<CardUseRecordDTO>  cardUseRecordDTOS =  buyCardClient.findCardUseRecordAll(dto);
            if(!CollectionUtils.isEmpty(cardUseRecordDTOS)){
                CardUseRecordDTO cardUseRecordDTO = cardUseRecordDTOS.get(0);
                vo.setBuyCardType(CardTypeEnum.getByTypeName(cardUseRecordDTO.getCardType()));
                vo.setSettleMethod(SettleMethodEnum.ofDescribe(cardUseRecordDTO.getSettleMethod()));
                StringBuffer sb = new StringBuffer();
                for (CardUseRecordDTO useRecordDTO : cardUseRecordDTOS) {
                    sb.append("卡号：").append(useRecordDTO.getCardNo());
                    sb.append("金额：").append(useRecordDTO.getUseAmount());
                    sb.append("，");
                }
                sb.deleteCharAt(sb.length()-1);
                vo.setBuyCardStr(sb.toString());
            }
            buyCardItemRefundQueryDTO.setSoId(so.getId());
            List<BuyCardItemRefundDTO> buyCardItemRefundDTOS = buyCardClient.findBuyCardItemRefund(buyCardItemRefundQueryDTO);
            if(!CollectionUtils.isEmpty(buyCardItemRefundDTOS)){
                StringBuffer sb = new StringBuffer();
                for (BuyCardItemRefundDTO buyCardItemRefundDTO : buyCardItemRefundDTOS) {
                    sb.append("卡号：").append(buyCardItemRefundDTO.getCardNo());
                    sb.append("金额：").append(buyCardItemRefundDTO.getRefundAmount());
                    sb.append("，");
                }
                sb.deleteCharAt(sb.length()-1);
                vo.setBuyCardRefundStr(sb.toString());
            }
            getSoDetailOrderExport(list,so,so.getId(),false);//无需校验子订单是否已经检出

            voList.add(vo);
        }
        Workbook wb = new XSSFWorkbook();
        Sheet s = wb.createSheet("订单清单");
        Sheet s2 = wb.createSheet("订单详情");
        String[] header = {"订单号", "订单状态", "支付状态", "订单结算总额", "饭卡支付金额","积点支付金额","现金实付金额", "卡劵支付金额","卡劵类型","卡劵结算方式","支付卡劵","退款金额", "饭卡退款金额","积点退款金额", "微信退款金额", "卡劵退款金额", "退款卡劵", "下单时间","支付时间"};
        String[] header2=new String[]{"订单号", "子订单编号", "快递单号","箱号","物流公司", "收货人", "收货地址", "联系电话", "运营方","商品名称","规格","供应商名称","供应商账号", "数量","商品税率", "结算单价","单价", "总价",
                "运费","退款数量","退款金额", "会员姓名", "会员编号", "会员公司", "用户邮箱", "是否开发票", "发票号","发票抬头", "发票内容", "发票类型", "抬头类型","下单时间","发货时间","商品编码","类目"};
        boolean exportC=EmptyUtil.isNotEmpty(RuntimeContext.cacheUser())&& RuntimeContext.cacheUser().getType()==3;
        if (exportC){
            header2=new String[]{"订单号", "子订单编号", "收货人", "收货地址", "联系电话","商品名称","规格", "数量", "总价",
                    "运费","下单时间"};
        }
        // 创建表头
        Row head = s.createRow(0);
        Row head2 = s2.createRow(0);
        for (int i = 0; i < header.length; i++) {
            head.createCell(i).setCellValue(header[i]);
        }
        for(int i=0;i<header2.length;i++){
            head2.createCell(i).setCellValue(header2[i]);
        }
        //订单信息写入
        completeOrderListSheet(s, voList);
        //订单物流详情写入
        if (exportC){
            completeOrderDetailSheetCExport(s2,list);
        }else {
            completeOrderDetailSheetExport(s2,list);
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            wb.write(bos);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.fail("导出失败!");
        }
        String upload = uploadService.fastDFSUpload(bos.toByteArray(), "xlsx");
        return JsonResult.success("url", upload);
    }

    /**
     * 翻译订单现金支付方式
     *
     * @param cashPayType
     * @return
     */
    private String tanslateOrderCashPayMethod(Integer cashPayType) {
        // 现金付款方式 1 支付宝 2 微信 3 银联 4 建行
        switch (cashPayType) {
            case 1:
                return "支付宝";
            case 2:
                return "微信";
            case 3:
                return "银联";
            case 4:
                return "建行";
        }
        return "";
    }

    /**
     * 翻译订单支付状态
     *
     * @param orderPayStatus
     * @return
     */
    private String translateOrderPayStatus(Integer orderPayStatus) {
        // 订单支付状态 0:未支付、1:已支付、2:已退款
        switch (orderPayStatus) {
            case 0:
                return "未支付";
            case 1:
                return "已支付";
            case 2:
                return "已退款";
        }
        return "";
    }

    /**
     * 将数据填写进订单清单导出excel表格
     *
     * @param s
     * @param voList
     */
    private void completeOrderListSheet(Sheet s, List<OrderListExportVO> voList) {
        for (int i = 0; i < voList.size(); i++) {
            Row r = s.createRow(i + 1);
            OrderListExportVO vo = voList.get(i);
            int cellCunt = 0;
            r.createCell(cellCunt++).setCellValue(vo.getOrderCode());
            r.createCell(cellCunt++).setCellValue(vo.getOrderStatus());
            r.createCell(cellCunt++).setCellValue(vo.getPayStatus());
//            r.createCell(cellCunt++).setCellValue(vo.getUseFubi());
//            r.createCell(cellCunt++).setCellValue(vo.getCashPayMethod());
            r.createCell(cellCunt++).setCellValue(vo.getSum().doubleValue());
            r.createCell(cellCunt++).setCellValue(vo.getPayByFubi().doubleValue());
            r.createCell(cellCunt++).setCellValue(vo.getPayByJiDian().doubleValue());
            r.createCell(cellCunt++).setCellValue(vo.getPayByCash().doubleValue());
            r.createCell(cellCunt++).setCellValue(vo.getPayByBuyCard().doubleValue());
            r.createCell(cellCunt++).setCellValue(vo.getBuyCardType());
            r.createCell(cellCunt++).setCellValue(vo.getSettleMethod());
            r.createCell(cellCunt++).setCellValue(vo.getBuyCardStr());
            r.createCell(cellCunt++).setCellValue(vo.getRefundSum().doubleValue());
            r.createCell(cellCunt++).setCellValue(vo.getRefundFubi().doubleValue());
            r.createCell(cellCunt++).setCellValue(vo.getRefundJidian().doubleValue());
            r.createCell(cellCunt++).setCellValue(vo.getRefundCash().doubleValue());
            r.createCell(cellCunt++).setCellValue(vo.getRefundCard().doubleValue());
            r.createCell(cellCunt++).setCellValue(vo.getBuyCardRefundStr());
            r.createCell(cellCunt++).setCellValue(vo.getCreateTime());
            r.createCell(cellCunt++).setCellValue(vo.getPayTime());
        }
    }

    @Override
    public JsonResult<Map<String, Object>> soChildExport(Long orderId) {
        if (orderId == null) {
            return JsonResult.fail("请选择订单");
        }
        // 查询订单
        SoDTO so = soFacade.querySoById(orderId);
        if (so == null) {
            return JsonResult.fail("订单不存在");
        }
        // 查询用户信息
        UserDTO user = userFacade.queryUserById(so.getUserId());
        Long companyId = user.getCompanyId();
        CompanyDTO company = userFacade.queryCompanyById(companyId);
        UserExtendDTO userExtend = userFacade.queryUserExtendById(so.getUserId());
        // 查询子订单
        List<SoChildDTO> scs = soFacade.querySoChildListBySoId(orderId);
        List<OrderSortExportVO> voList = new ArrayList<>();
        for (SoChildDTO sc : scs) {
            Long scId = sc.getId();
            List<SoPackageDTO> packs = soPackageFacade.queryPackageBySoChildId(scId);
            ReceiverAddressDTO ra = null;
            if (packs != null) {
                ra = receiverAddressFacade.findReceiverAddressById(packs.get(0).getReceiverAddressId());
            }
            // 查询发票信息
            SoInvoiceDTO invoice = soInvoiceFacade.querySoInvoiceBySoChildId(scId);
            // 查询订单项
            List<SoItemDTO> items = soItemFacade.querySoItemsBySoChildId(scId);


            for (SoItemDTO item : items) {
                OrderSortExportVO vo = new OrderSortExportVO();
                vo.setOrderCode(so.getOrderCode());
                vo.setChildCode(sc.getChildCode());
                if (packs != null && packs.size()>0) {
                    vo.setDeliveryCode(packs.get(0).getDeliveryCode());
                    vo.setDeliveryCompany(packs.get(0).getDeliveryCompanyName());
                    if (ra != null) {
                        vo.setReceiverName(ra.getGoodReceiverName());
                        vo.setReceiverAddress(ra.getGoodReceiverProvince() + ra.getGoodReceiverCity()
                                + ra.getGoodReceiverCounty() + ra.getGoodReceiverAddress());
                        vo.setReceiverMobile(ra.getGoodReceiverMobile());
                    }
                }
                BigDecimal supplierPrice = null;
                String productUnitSerialNumber="-";
                String supplierName ="-";
                if(item.getSnapshot()!=null) {
                    logger.info(item.getSnapshot());
                    if(item.getSource()==null || item.getSource().intValue()<3 ) {
                        CommodityProductUnitDTO selfProduct = JSON.parseObject(item.getSnapshot(), CommodityProductUnitDTO.class);
                        if(selfProduct!=null && selfProduct.getSupplierPrice()!=null) {
                            supplierPrice = selfProduct.getSupplierPrice();
                        }
                        productUnitSerialNumber = getProductUnitSerialNumber(selfProduct);
                        supplierName = getSupplierName(item.getSupplierId());
                    }else if(item.getSource()!=null && item.getSource().intValue()==3) {
                        JdProductDTO jdProduct = JSON.parseObject(item.getSnapshot(), JdProductDTO.class);

                        if(jdProduct!=null && jdProduct.getLedger()!=null) {
                            JSONObject ledger = JSON.parseObject(jdProduct.getLedger());
                            if(ledger.containsKey("jdPrice")) {
                                supplierPrice = ledger.getBigDecimal("jdPrice");
                            }
                        }
                        supplierName = "京东";
                    }else if(item.getSource()!=null && item.getSource().intValue()==4){
                        supplierName = "蛋糕叔叔";
                    }else if(item.getSource()!=null && item.getSource().intValue()==5){
                        supplierName = "全球购";
                    }else if(item.getSource()!=null && item.getSource().intValue()==9){
                        supplierName = "清美";
                    }
                }

                if(supplierPrice!=null) {
                    vo.setSupplierPrice(supplierPrice);
                }
                vo.setSupplierName(supplierName);
                vo.setMerchantProductSerialNumber(productUnitSerialNumber);
                vo.setPuName(item.getPuName());
                vo.setPuSpec(item.getPuName());
                vo.setPuCount(item.getPuCount());
                vo.setPrice(item.getPrice());
                vo.setSum(item.getPrice().multiply(new BigDecimal(item.getPuCount()+"")));
                vo.setUserName(userExtend.getName());
                vo.setMemberCode(userExtend.getMemberCode());
                vo.setCompanyName(company.getCompanyName());
                vo.setUserEmail(user.getMail());
                if (invoice != null) {
                    vo.setInvoiceExist("是");
                    vo.setInvoiceTitle(invoice.getInvoiceTitleContent());
                    vo.setInvoiceContent(invoice.getInvoiceContent());
                    vo.setInvoiceForm(invoice.getInvoiceForm().intValue() == 0 ? "纸质发票" : "电子发票");
                    vo.setInvoiceTitleType(invoice.getInvoiceTitleType().intValue() == 0 ? "个人" : "公司");
                } else {
                    vo.setInvoiceExist("否");
                }
                voList.add(vo);
            }
        }

        // 子订单导出和订单导出共用同一套导出模板
        // 生成Excel
        Workbook wb = new XSSFWorkbook();
        Sheet s = wb.createSheet("子订单导出");
        String[] header = {"订单号", "子订单编号", "快递单号", "物流公司", "收货人", "收货地址", "联系电话","供应商","产品编码","商品名称", "规格", "数量","结算单价", "单价", "总价",
                "会员姓名", "会员编号", "会员公司", "用户邮箱", "是否开发票", "发票抬头", "发票内容", "发票类型", "抬头类型"};
        // 创建表头
        Row head = s.createRow(0);
        for (int i = 0; i < header.length; i++) {
            head.createCell(i).setCellValue(header[i]);
        }
        completeOrderSortSheet(s, voList);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            wb.write(bos);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.fail("导出失败!");
        }
        String upload = uploadService.fastDFSUpload(bos.toByteArray(), "xlsx");
        return JsonResult.success("url", upload);
    }

    @Override
    public JsonResult<Map<String, Object>> operateFlowExport(Long orderId) {
        if (orderId == null) {
            return JsonResult.fail("请选择订单");
        }
        SoDTO so = soFacade.querySoById(orderId);
        if (so == null) {
            return JsonResult.fail("订单不存在");
        }
        // 查询所有操作流水
        List<SoFlowDTO> scfs = soFlowFacade.queryFlowListBySoId(orderId);
        List<SoOPFlowVO> voList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        for (SoFlowDTO scf : scfs) {
            SoOPFlowVO vo = new SoOPFlowVO();
            vo.setCreatTime(sdf.format(scf.getCreatTime()));
            vo.setId(scf.getId());
            vo.setOperate(scf.getOperate());
            Long operatorId = scf.getOperatorId();
            // 查询操作人信息
            UserExtendDTO oper = userFacade.queryUserExtendById(operatorId);
            if (oper != null) {
                vo.setOperatorName(oper.getName());
            }
            vo.setOrderCode(so.getOrderCode());
            voList.add(vo);
        }
        Workbook wb = new XSSFWorkbook();
        Sheet s = wb.createSheet("订单操作流水导出");
        String[] header = {"流水编号", "订单", "操作", "操作人", "操作时间"};
        // 创建表头
        Row head = s.createRow(0);
        for (int i = 0; i < header.length; i++) {
            head.createCell(i).setCellValue(header[i]);
        }
        completeOperateFlowSheet(s, voList);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            wb.write(bos);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.fail("导出失败!");
        }
        String upload = uploadService.fastDFSUpload(bos.toByteArray(), "xlsx");
        return JsonResult.success("url", upload);
    }

    /**
     * 将数据填写进订单操作流水导出excel表格
     *
     * @param s
     * @param voList
     */
    private void completeOperateFlowSheet(Sheet s, List<SoOPFlowVO> voList) {
        for (int i = 0; i < voList.size(); i++) {
            Row r = s.createRow(i + 1);
            SoOPFlowVO vo = voList.get(i);
            r.createCell(0).setCellValue(vo.getId());
            r.createCell(1).setCellValue(vo.getOrderCode());
            r.createCell(2).setCellValue(translateOperateType(vo.getOperate()));
            r.createCell(3).setCellValue(vo.getOperatorName());
            r.createCell(4).setCellValue(vo.getCreatTime());
        }
    }

    /**
     * 翻译操作类型
     *
     * @param operate
     * @return
     */
    private String translateOperateType(Integer operate) {
        // 操作 0:拆单、1:分拣、2:开票、3:发货、4:签收导入
        switch (operate) {
            case 0:
                return "拆单";
            case 1:
                return "分拣";
            case 2:
                return "开票";
            case 3:
                return "发货";
            case 4:
                return "签收导入";
        }
        return "";
    }

    @Override
    public Map<String, Object> getOrderInfoByOrderIds(List<Long> orderIds) {
        return soFacade.getOrderInfoByOrderIds(orderIds);
    }

    @Override
    public Map<String, Object> getOrderInfoByOrderChildIds(List<Long> orderIds) {
        return soFacade.getOrderInfoByChildOrderIds(orderIds);
    }
    @Override
    public SoDTO findOrderById(Long orderId) {
        return soFacade.querySoById(orderId);
    }

    @Override
    public SoDTO querySoByOrderCode(String orderCode) {
        return soFacade.querySoByOrderCode(orderCode);
    }

    @Override
    public ReceiverAddressDTO queryReceiverAddressBySoChildId(Long soChildId) {
        SoChildDTO soChildDTO = soFacade.findSoChildById(soChildId);
        ReceiverAddressDTO receiverAddressDTO = soChildDTO != null && soChildDTO.getReceiverAddressId() != null
                ? receiverAddressFacade.findReceiverAddressById(soChildDTO.getReceiverAddressId()) : null;
        return receiverAddressDTO;
    }

    @Override
    public SoChildDTO querySoChildById(Long id) {
        return soFacade.findSoChildById(id);
    }

    @Override
    public ReceiverAddressDTO queryReceiverAddressById(Long id) {
        return receiverAddressFacade.findReceiverAddressById(id);
    }

    @Override
    public JsonResult<Map<String, Object>> queryUserStatisticalInfo(Long userId, Long platformId) {

        return JsonResult.success(soFacade.queryUserStatisticalInfo(userId, platformId));
    }

    @Override
    public void repairOrderDataWithTx() {
        soFacade.repairOrderDataWithTx();
    }

    @Override
    public int updateCouponUnitLockedByCouponUnitId(Long couponUnitId) {
        return soFacade.updateCouponUnitLockedByCouponUnitId(couponUnitId);
    }

    @Override
    public CartItemDTO findCartItemByItemId(Long itemId) {
        return  cartItemFacade.findCartItemById(itemId);
    }

    @Override
    public CouponUnitDTO findCouponUnitById(Long exchangeCouponUnitId) {
        return soFacade.findCouponUnitById(exchangeCouponUnitId);
    }

    @Override
    public Map<String, String> getDeliveryPriceFromJd(String address, List<SkuInfo> skuInfoList) {
        return null;
    }

    @Override
    public JsonResult<Map<String, Object>> thirdpartyOrderExport(List<Long> idList, Long merchantId) {
        //准备数据
        //查询所有的京东子订单
        List<SoChildDTO> soChildList=soFacade.findSoChildListByMerchantId(idList,merchantId);
        MerchantDTO merchant=soFacade.findMerchantById(merchantId);
        if(EmptyUtil.isEmpty(merchant)){
            return JsonResult.fail("您选择的运营方不存在");
        }
        List<SoChildVO> soChildVOList = new ArrayList<>();
        for(SoChildDTO dto:soChildList){
            Long userId = dto.getUserId();
            UserDTO userById = userFacade.findUserById(userId);
            SoChildVO vo = new SoChildVO();
            vo.setMerchantName(merchant.getName());
            vo.setCreateTime(dto.getCreateTime());
            vo.setSoCode(dto.getOrderCode());
            vo.setUserName(userById.getName());
            vo.setUserMail(userById.getMail());
            vo.setSoChildCode(dto.getChildCode());
            vo.setThirdpartySoChildId(Objects.nonNull(dto.getThirdpartySoChildCode())?String.valueOf(dto.getThirdpartySoChildCode()):String.valueOf(dto.getThirdpartySoChildId()));
            vo.setThirdpartySoChildStatus(dto.getThirdpartySoChildStatus());
            vo.setSoStatus(dto.getOrderStatus());
            vo.setSoConfirmStatus(dto.getOrderConfirmStatus());
            vo.setSoPayStatus(dto.getOrderPayStatus());
            vo.setPayAmount(dto.getAmount().add(dto.getDeliveryFee()));
            vo.setSoChildAmount(dto.getProductAmount().add(dto.getDeliveryFee()));
            vo.setCouponDiscount(dto.getCouponDiscount());
            vo.setDiscount(dto.getDiscount());
            vo.setThirdpartySoChildPayAmount(dto.getThirdpartySoChildPayAmount());
            soChildVOList.add(vo);
        }
        //写入excel
        Workbook wb = new XSSFWorkbook();
        Sheet s = wb.createSheet("第三方订单详情");
        String[] header = {"运营方", "下单时间", "用户姓名", "买家账号",
                "父订单编号", "对应子订单编号", "第三方订单编号",
                "第三方子订单状态", "订单状态","订单确认状态","订单支付状态",
                "优惠前总金额","优惠总额","优惠券优惠金额","结算总额",
                "第三方扣款总额"};

        // 创建表头
        Row head = s.createRow(0);

        for (int i = 0; i < header.length; i++) {
            head.createCell(i).setCellValue(header[i]);
        }
        //订单信息写入
        completeSoChildListSheet(s, soChildVOList);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            wb.write(bos);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.fail("导出失败!");
        }
        String upload = uploadService.fastDFSUpload(bos.toByteArray(), "xlsx");
        return JsonResult.success("url", upload);
    }

    private void completeSoChildListSheet(Sheet s, List<SoChildVO> soChildVOList) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < soChildVOList.size(); i++) {
            Row r = s.createRow(i + 1);
            SoChildVO vo = soChildVOList.get(i);
            r.createCell(0).setCellValue(vo.getMerchantName());
            r.createCell(1).setCellValue(format.format(vo.getCreateTime()));
            r.createCell(2).setCellValue(vo.getUserName());
            r.createCell(3).setCellValue(vo.getUserMail());
            r.createCell(4).setCellValue(vo.getSoCode());
            r.createCell(5).setCellValue(vo.getSoChildCode());
            r.createCell(6).setCellValue(EmptyUtil.isEmpty(vo.getThirdpartySoChildId()) ? "" : vo.getThirdpartySoChildId().toString());
            r.createCell(7).setCellValue(EmptyUtil.isEmpty(vo.getThirdpartySoChildStatus()) ? "" : getThirdpartySoChildStatusString(vo.getThirdpartySoChildStatus()));
            r.createCell(8).setCellValue(EmptyUtil.isEmpty(vo.getSoStatus())?"":getSoStatusString(vo.getSoStatus()));
            r.createCell(9).setCellValue(getSoConfirmStatusString(vo.getSoConfirmStatus()));
            r.createCell(10).setCellValue(getSoPayStatusString(vo.getSoPayStatus()));
            r.createCell(11).setCellValue(EmptyUtil.isEmpty(vo.getSoChildAmount())? 0:vo.getSoChildAmount().doubleValue());
            r.createCell(12).setCellValue(EmptyUtil.isEmpty(vo.getDiscount())?0:vo.getDiscount().doubleValue());
            r.createCell(13).setCellValue(EmptyUtil.isEmpty(vo.getCouponDiscount())?0:vo.getCouponDiscount().doubleValue());
            r.createCell(14).setCellValue(EmptyUtil.isEmpty(vo.getPayAmount())?0:vo.getPayAmount().doubleValue());
            r.createCell(15).setCellValue(EmptyUtil.isEmpty(vo.getThirdpartySoChildPayAmount())?0:vo.getThirdpartySoChildPayAmount().doubleValue());


        }
    }

    private String getThirdpartySoChildStatusString(Integer thirdpartySoChildStatus) {
        String status = "";
        if(EmptyUtil.isEmpty(thirdpartySoChildStatus)){
            return status;
        }
        switch (thirdpartySoChildStatus){
            case 1:
                status = "新单";
                break;
            case 2:
                status = "等待支付";
                break;
            case 3:
                status = "等待支付确认";
                break;
            case 4:
                status = "延迟付款确认";
                break;
            case 5:
                status = "订单暂停";
                break;
            case 6:
                status = "店长最终审核";
                break;
            case 7:
                status = "等待打印";
                break;
            case 8:
                status = "等待出库";
                break;
            case 9:
                status = "等待打包";
                break;
            case 10:
                status = "等待发货";
                break;
            case 11:
                status = "自提途中";
                break;
            case 12:
                status = "上门提货";
                break;
            case 13:
                status = "自提退货";
                break;
            case 14:
                status = "确认自提";
                break;
            case 16:
                status = "等待确认收货";
                break;
            case 17:
                status = "配送退货";
                break;
            case 18:
                status = "货到付款确认";
                break;
            case 19:
                status = "已完成";
                break;
            case 21:
                status = "收款确认";
                break;
            case 29:
                status = "等待三方出库";
                break;
            case 30:
                status = "等待三方发货";
                break;
            case 31:
                status = "等待三方发货完成";
                break;
        }
        return status;
    }

    private String getSoPayStatusString(Integer soPayStatus) {
        String status = "";
        switch (soPayStatus){
            case 0:
                status = "未支付";
                break;
            case 1:
                status = "已支付";
                break;
            case 2:
                status = "已退款";
                break;
        }
        return status;
    }

    private String getSoConfirmStatusString(Integer soConfirmStatus) {
        String status = "";
        switch (soConfirmStatus){
            case 0:
                status = "未确认";
                break;
            case 1:
                status = "已确认";
                break;
            case 2:
                status = "已取消";
                break;
            case 3:
                status = "已完成";
                break;

        }
        return status;
    }

    private String getSoStatusString(Integer soStatus) {
        String status = "";
        switch (soStatus){
            case 0:
                status = "待付款";
                break;
            case 1:
                status = "已付款";
                break;
            case 2:
                status = "已发货";
                break;
            case 3:
                status = "已收货";
                break;
            case 4:
                status = "已完成";
                break;
            case 5:
                status = "预退款";
                break;
            case 6:
                status = "已退款";
                break;
            case 7:
                status = "部分发货";
                break;
            case 8:
                status = "换货中";
                break;
            case 10:
                status = "已取消";
                break;
        }
        return status;
    }

    /**
     * 母订单是否需要审核
     * @param companyId
     * @return
     */
    private boolean orderAudit(Long companyId,List<CompanyConfigDTO> configs){
        boolean orderAudit = false;
        if (EmptyUtil.isNotEmpty(configs)){
            for(CompanyConfigDTO config : configs) {
                if(config.getKey().equalsIgnoreCase("order.so.audit")&& config.getValue()!=null && config.getValue().length()==1) {
                    orderAudit = (Integer.valueOf(config.getValue()).intValue()==1)?true:false;
                    break;
                }
            }
        }
        return orderAudit;
    }

    private JsonResult checkCakeProductInfo(String puId,String skuId,CakeProductDetailDTO cakeProductDetailDTO,ReceiverAddressDTO addr){
        if(Objects.isNull(cakeProductDetailDTO)){
            return JsonResult.fail("无此商品");
        }
        CakeProductDetailProductsDTO detailProductsDTO = cakeProductDetailDTO.getProduct();
        if(Objects.isNull(detailProductsDTO) || !Objects.equals( detailProductsDTO.getStatus(),"1")){
            return JsonResult.fail("此商品已下架");
        }
       /* if(!Objects.equals( detailProductsDTO.getCan_buy(),"1")){
            return JsonResult.fail("此商品不能购买");
        }*/
        String cityName = addr.getGoodReceiverCity();
        if(EmptyUtil.isNotEmpty(cityName) && (Objects.equals(cityName,"市辖区") || Objects.equals(cityName,"直辖市"))){
            cityName =addr.getGoodReceiverProvince();
        }
        CakeProductDetailDTO lastCakeProductDetailDTO = productFacade.getCakeProduct(detailProductsDTO.getId(),skuId,cityName,null);

        if(Objects.isNull(lastCakeProductDetailDTO)){
            return JsonResult.fail("对应的城市无此商品");
        }
        CakeProductDetailProductsDTO lastDetailProductsDTO = lastCakeProductDetailDTO.getProduct();
        if(Objects.isNull(lastDetailProductsDTO) || !Objects.equals( lastDetailProductsDTO.getStatus(),"1")){
            return JsonResult.fail("选择的收货城市该商品已下架");
        }
        CakeProductDetailSpecsDTO specsDTO = productFacade.getCakeProductSkuInfo(lastCakeProductDetailDTO,skuId);
        if(Objects.isNull(specsDTO)){
            return JsonResult.fail("无该规格商品");
        }
        if(!Objects.equals(specsDTO.getCan_buy(),"1")){
            return JsonResult.fail("该规格商品已不能购买");
        }
        if(com.egeo.utils.StringUtils.isNotEmpty(specsDTO.getStock()) && !Objects.equals(specsDTO.getStock(),"-9999999") && Integer.valueOf(specsDTO.getStock()) <=0){
            return JsonResult.fail("商品已售罄");
        }

        return null;
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

    @Override
    public PageResult<SoDetailVO> getOrderByUserAndStatus(Long userId, Integer orderStatus, Long platformId,
                                                          Long f, Long clientId, Pagination page) {
        return soFacade.getOrderByUserAndStatus(userId, orderStatus, platformId, f, clientId, page);
    }


    @Override
    public JsonResult<Map<String, Object>> childOrderDetail(String orderCode, Long platformId, Long userId,
                                                            Long f, Long clientId) {
        if (EmptyUtil.isBlank(orderCode))
            return JsonResult.fail("请选择订单");
        // 查询订单信息
        SoChildDTO soChildDTO = soChildReadService.querySoChildByChildCode(orderCode);
        if (soChildDTO == null)
            return JsonResult.fail("订单不存在");
        SoDTO queryDTO = new SoDTO();
        queryDTO.setId(soChildDTO.getSoId());
        SoDTO so = soFacade.findSoById(queryDTO);
        if (so == null)
            return JsonResult.fail("母订单不存在");
        SoDetailOneVo vo = new SoDetailOneVo();
        vo.setIfChildOrder("1");
        //下单日期
        vo.setCreateTime(com.egeo.utils.DateUtils.getDefaultDateTime(so.getCreateTime()));
        UserDTO userById = userFacade.findUserById(so.getUserId());
        vo.setUserMail(userById.getMail());
        //运费
        vo.setDeliveryFee(soChildDTO.getDeliveryFee().doubleValue());
        //商品金额
        vo.setGoodsAmount(soChildDTO.getProductAmount().doubleValue());
        //如果已支付查询支付时间//付款时间
        if (so.getOrderPayStatus() != 0) {
            if(EmptyUtil.isNotEmpty(so.getOrderPaymentConfirmDate())){
                vo.setPayTime(com.egeo.utils.DateUtils.getDefaultDateTime(so.getOrderPaymentConfirmDate()));
            }
        }
        vo.setOrderPayStatus(so.getOrderPayStatus());
        vo.setOrderConfirmStatus(so.getOrderConfirmStatus());
       /* SoDeliveryItemDTO soDeliveryItemDTO = new SoDeliveryItemDTO();
        soDeliveryItemDTO.setOrderCode(so.getOrderCode());
        List<SoDeliveryItemDTO> soDeliveryItemAll = soDeliveryItemFacade.findSoDeliveryItemAll(soDeliveryItemDTO);
        if(EmptyUtil.isNotEmpty(soDeliveryItemAll)){
            vo.setDeliveryDate(com.egeo.utils.DateUtils.getDefaultDateTime(soDeliveryItemAll.get(0).getDeliveryDate()));//发货日期
        }*/



        vo.setId(soChildDTO.getId());
        // 订单总额 = 订单金额 - 优惠金额
        vo.setOrderCode(soChildDTO.getChildCode());
       /* vo.setPaidByCash(so.getOrderPaidByCash().doubleValue());
        vo.setPaidByFubi(so.getOrderPaidByFubi().doubleValue());
        vo.setPaidByJiDian(so.getOrderPaidByJidian().doubleValue());*/
        vo.setPayTypeName("未知");
        vo.setPayType("-2");
        if(userById !=null &&EmptyUtil.isNotEmpty(userById.getChannelSource()) && userById.getChannelSource().equals(UserChannelSourceEnum.DLF.getChannelSource())){
            if(so.getOrderPaidByCash() !=null && so.getOrderPaidByCash().compareTo(BigDecimal.ZERO) >0){
                vo.setPayTypeName("现金支付");
                if(so.getCashPayType() !=null && so.getCashPayType().intValue() ==1){
                    vo.setPayTypeName("支付宝");
                }else if(so.getCashPayType() !=null && so.getCashPayType().intValue() ==2){
                    vo.setPayTypeName("微信");
                }else if(so.getCashPayType() !=null && so.getCashPayType().intValue() ==3){
                    vo.setPayTypeName("银联");
                }else if(so.getCashPayType() !=null && so.getCashPayType().intValue() ==4){
                    vo.setPayTypeName("建行");
                }
                vo.setPayType("-1");
            }else if(so.getOrderPaidByFubi() !=null && so.getOrderPaidByFubi().compareTo(BigDecimal.ZERO) >0){
                vo.setPayTypeName("餐卡支付");
                vo.setPayType("0");
            }else if(so.getOrderPaidByJidian() !=null && so.getOrderPaidByJidian().compareTo(BigDecimal.ZERO) >0){
                vo.setPayTypeName("积点支付");
                vo.setPayType("5");
            }else if(so.getOrderCardPaid() !=null && so.getOrderCardPaid().compareTo(BigDecimal.ZERO) >0){
                vo.setPayTypeName("卡劵支付");
                vo.setPayType("6");
            }
        }else{
            if(so.getOrderPaidByFubi() !=null && so.getOrderPaidByCash() !=null){
                if(so.getOrderPaidByFubi().compareTo(BigDecimal.ZERO) >0 && so.getOrderPaidByCash().compareTo(BigDecimal.ZERO) >0){
                    //混合支付
                    vo.setPayTypeName("混合支付");
                    vo.setPayType("-2");
                }else if(so.getOrderPaidByCash().compareTo(BigDecimal.ZERO) >0){
                    vo.setPayTypeName("现金支付");
                    if(so.getCashPayType() !=null && so.getCashPayType().intValue() ==1){
                        vo.setPayTypeName("支付宝");
                    }else if(so.getCashPayType() !=null && so.getCashPayType().intValue() ==2){
                        vo.setPayTypeName("微信");
                    }else if(so.getCashPayType() !=null && so.getCashPayType().intValue() ==3){
                        vo.setPayTypeName("银联");
                    }else if(so.getCashPayType() !=null && so.getCashPayType().intValue() ==4){
                        vo.setPayTypeName("建行");
                    }
                    vo.setPayType("-1");
                }else if(so.getOrderPaidByFubi().compareTo(BigDecimal.ZERO) >0){
                    vo.setPayTypeName("积分支付");
                    vo.setPayType("0");
                }else if(so.getOrderPaidByJidian().compareTo(BigDecimal.ZERO) >0){
                    vo.setPayTypeName("积点支付");
                    vo.setPayType("5");
                }else if(so.getOrderCardPaid().compareTo(BigDecimal.ZERO) >0){
                    vo.setPayTypeName("卡劵支付");
                    vo.setPayType("6");
                }
            }
        }
        //商品总价
        vo.setPUAmount(soChildDTO.getProductAmount());
        vo.setOrderType(so.getSaleWay());
        vo.setCouponDiscount(soChildDTO.getCouponDiscount());
        //门店优惠
        vo.setStoreDiscount(soChildDTO.getStoreDiscount());

        vo.setDeliveryFee(soChildDTO.getDeliveryFee().doubleValue());
        BigDecimal orderAmount = soChildDTO.getAmount().add(soChildDTO.getDeliveryFee() !=null?soChildDTO.getDeliveryFee():BigDecimal.ZERO);
        vo.setOrderAmount(orderAmount.doubleValue());

        Integer cashPayType = so.getCashPayType();
        vo.setPayCashMethod(cashPayType);
        vo.setPayCashMethodStr(
                judgeOrderPayCashMethodShow(cashPayType, so.getOrderConfirmStatus(), so.getOrderPayStatus()));
        if(soChildDTO.getCancelStatus() ==1){
            vo.setStatus(OrderConstant.ORDER_STATUS_CANCELED.getStatus());
            vo.setStatusStr(OrderConstant.translate(OrderConstant.ORDER_STATUS_CANCELED.getStatus()));
        }else if(!so.getOrderStatus().equals(OrderConstant.ORDER_STATUS_CANCELED.getStatus()) &&soChildDTO.getCancelStatus() !=1 && soChildDTO.getDeliveryStatus() ==0){
            vo.setStatus(OrderConstant.ORDER_STATUS_PAYED.getStatus());
            vo.setStatusStr("待发货");
        }else if(!so.getOrderStatus().equals(OrderConstant.ORDER_STATUS_CANCELED.getStatus()) &&soChildDTO.getCancelStatus() !=1 && soChildDTO.getDeliveryStatus() ==1){
            vo.setStatus(OrderConstant.ORDER_STATUS_PAYED.getStatus());
            vo.setStatusStr("分拣中");
        }else if(!so.getOrderStatus().equals(OrderConstant.ORDER_STATUS_CANCELED.getStatus()) &&soChildDTO.getCancelStatus() !=1 && soChildDTO.getDeliveryStatus() ==2){
            vo.setStatus(OrderConstant.ORDER_STATUS_DELIVERED.getStatus());
            vo.setStatusStr(OrderConstant.translate(OrderConstant.ORDER_STATUS_DELIVERED.getStatus()));
        }else if(soChildDTO.getCancelStatus() !=1 && (soChildDTO.getDeliveryStatus().equals(OrderConstant.ORDER_STATUS_RECEIVED_FINISHED.getStatus()) || soChildDTO.getDeliveryStatus().equals(OrderConstant.ORDER_STATUS_RECEIVED_GOODS.getStatus()))){
            vo.setStatus(OrderConstant.ORDER_STATUS_RECEIVED_FINISHED.getStatus());
            vo.setStatusStr(OrderConstant.translate(OrderConstant.ORDER_STATUS_RECEIVED_FINISHED.getStatus()));
        }else{
            vo.setStatus(so.getOrderStatus());
            vo.setStatusStr(OrderConstant.translate(so.getOrderStatus()));
        }

        vo.setUseFubi(so.getUseFubi().intValue() == 1);
        //List<SoChildDTO> scList = soFacade.querySoChildListBySoId(so.getId());
        //List<Integer> scDeliveryStatusList = new ArrayList<>();
        // 设置话费充值的信息
        String rechangePhone = null;
        Integer thirdpartyType = 0;
        String jumpUrl=null;
        boolean isQc=false;
        SoChildDTO sc = soChildDTO;
        //scDeliveryStatusList.add(sc.getDeliveryStatus());
        // 通过子订单类型和id 查询第三方订单信息
        // 话费充值
        if (sc.getThirdpartyType() == SoThirdpartyManageImpl.THIRDPARTY_TYPE_PHONE) {
            thirdpartyType = SoThirdpartyManageImpl.THIRDPARTY_TYPE_PHONE;
            SoThirdpartyDTO dto = new SoThirdpartyDTO();
            dto.setSoChildId(sc.getId());
            List<SoThirdpartyDTO> soThirdpartyList = soThirdpartyFacade.findSoThirdpartyAll(dto);
            // 一个子订单对应一个第三方订单
            if (EmptyUtil.isNotEmpty(soThirdpartyList)) {
                rechangePhone = soThirdpartyList.get(0).getPhone();
            }
        }
        //券仓订单,此时一个母订单只有一个子订单
        if(sc.getThirdpartyType().equals(SoThirdpartyManageImpl.THIRDPARTY_TYPE_QC)){
            isQc = true;
        }else if (sc.getThirdpartyType()== ThirdConst.ThirdPartyType.QM){
            thirdpartyType = ThirdConst.ThirdPartyType.QM;
            jumpUrl=sc.getExt();
        }
        vo.setRechangePhone(rechangePhone);

        // 查询订单的优惠卷信息
        vo.setCouponType(soFacade.findCouponUnitByOrder(so));
        vo.setOrderPromotionDiscount(so.getOrderPromotionDiscount().intValue());

        vo.setOperateStatus(soFacade.judgeChildOrderOperateStatus(clientId, so.getId(), so.getOrderPayStatus(), so.getOrderConfirmStatus(),
                sc.getDeliveryStatus(), thirdpartyType,so.getLimitCashPayAmount(),so.getOrderAmount(),userId,platformId,so.getOrderStatus(),sc));

        // 收货地址信息
        // 判断是否展示收货地址信息
        ReceiverAddressDTO addressDto = null;
        boolean isShowAddr = false;
        if (so.getReceiverAddressId() != null) {
            isShowAddr = true;
            addressDto = receiverAddressFacade.findReceiverAddressById(so.getReceiverAddressId());
        } else if (vo.getOperateStatus().equals(Integer.valueOf(2))
                || vo.getOperateStatus().equals(Integer.valueOf(3))) {
            // 电子卡券类商品没有查看物流和确认收货按钮的特殊处理(话费充值类已特殊处理)
            vo.setOperateStatus(4);
        }
        if(isQc&&!vo.getOperateStatus().equals(0)){
            //如果第三方对接参数是券仓,则第三方订单类型为券仓卡券,且订单已付款,则仅仅显示删除订单按钮
            vo.setOperateStatus(Integer.valueOf(4));
        }

        if(so.getOrderStatus()==4){
            //如果是加价购查询优惠券类型
            if(so.getSaleWay()==8){
                vo.setOperateStatus(6);
                List<ExchangeOrderRecordDTO> list=soFacade.findExchangeOrderRecordByOrderCode(so.getOrderCode());
                if(EmptyUtil.isEmpty(list)||list.size()>1){
                    logger.info("[该订单号数据有误,订单详情]orderCode="+so.getOrderCode());
                    throw new BusinessException("该订单号数据有误");
                }
                vo.setExchangeCouponType(list.get(0).getNewCouponType());
            }
        }





        vo.setIsShowAddr(isShowAddr);
        DefaultReceiverInfoVo receiverInfo = new DefaultReceiverInfoVo();
        if (addressDto != null) {
            receiverInfo.setId(addressDto.getId());
            String address = addressDto.getGoodReceiverProvince() + addressDto.getGoodReceiverCity()
                    + addressDto.getGoodReceiverCounty() + addressDto.getGoodReceiverAddress();
            receiverInfo.setAddress(address);
            receiverInfo.setPhoneNo(addressDto.getGoodReceiverMobile());
            receiverInfo.setUserId(addressDto.getUserId());
            receiverInfo.setReceiverName(addressDto.getGoodReceiverName());
        }
        // 查询订单项信息
        List<SoItemDTO> soItems = mergeSoItems(soItemFacade.querySoItemsBySoChildId(soChildDTO.getId()));
        // 合并订单项

        Map<Long, SoChildDTO> merchantSoChildMap = new HashMap<>();
        merchantSoChildMap.put(soChildDTO.getPerformingParty(), soChildDTO);


        boolean unitFlag = false;
        Map<Long, List<OrderConfirmGoodsVO>> soItemMap = new HashMap<>();
        Boolean flag = false;
        for (SoItemDTO item : soItems) {
            OrderConfirmGoodsVO goodsVo = new OrderConfirmGoodsVO();
            goodsVo.setNum(item.getPuCount());
            goodsVo.setPrice(item.getPrice().doubleValue());
            goodsVo.setPuId(item.getPuId());
            goodsVo.setPuName(item.getPuName());
            goodsVo.setSaleWay(1);
            goodsVo.setPuImg(item.getPuPicUrl());
            goodsVo.setBuyType(1);
            goodsVo.setSource(item.getSource());
            goodsVo.setChannelProductId(item.getExternalProductId());
            if(item.isJd()) {
                goodsVo.setStandardUnitId(item.getPuId());
                String snapStr = item.getSnapshot();
                if(snapStr==null||snapStr.length()<10) {
                    logger.info("[该订单产品数据,订单详情]orderCode="+so.getOrderCode());
                    continue;
                }
                JdProductDTO standardUnitById = JSON.parseObject(snapStr, JdProductDTO.class);
                flag = true;
                goodsVo.setProductUnitSerialNumber("jd-"+item.getPuId());
                goodsVo.setIsOwnMerchant(0);
                List<OrderConfirmGoodsVO> goodsList = soItemMap.get(6l);
                if(goodsList==null) {
                    goodsList = new ArrayList<>();
                    soItemMap.put(6l, goodsList);
                }
                goodsList.add(goodsVo);
                if (item.getUnitExist() == 1) {
                    unitFlag = true;
                }
            }else if(item.isQM()) {
                goodsVo.setStandardUnitId(item.getPuId());
                String snapStr = item.getSnapshot();
                if(snapStr==null||snapStr.length()<10) {
                    logger.info("[该订单产品数据,订单详情]orderCode="+so.getOrderCode());
                    continue;
                }
                flag = true;
                goodsVo.setProductUnitSerialNumber("qm-"+item.getPuId());
                goodsVo.setIsOwnMerchant(0);
                List<OrderConfirmGoodsVO> goodsList = soItemMap.get(ThirdConst.Merchant.QM);
                if(goodsList==null) {
                    goodsList = new ArrayList<>();
                    soItemMap.put(ThirdConst.Merchant.QM, goodsList);
                }
                goodsList.add(goodsVo);
                if (item.getUnitExist() == 1) {
                    unitFlag = true;
                }
            }else if(item.isCake()){
                goodsVo.setStandardUnitId(item.getPuId());
                String snapStr = item.getSnapshot();
                if(snapStr==null||snapStr.length()<10) {
                    logger.info("[该订单产品数据,订单详情]orderCode="+so.getOrderCode());
                    continue;
                }
                flag = true;
                goodsVo.setProductUnitSerialNumber("jd-"+item.getPuId());
                goodsVo.setIsOwnMerchant(0);
                List<OrderConfirmGoodsVO> goodsList = soItemMap.get(7L);
                if(goodsList==null) {
                    goodsList = new ArrayList<>();
                    soItemMap.put(7L, goodsList);
                }
                goodsList.add(goodsVo);
                if (item.getUnitExist() == 1) {
                    unitFlag = true;
                }
            }else if(item.isWorld()){
                goodsVo.setStandardUnitId(item.getPuId());
                String snapStr = item.getSnapshot();
                if(snapStr==null||snapStr.length()<10) {
                    logger.info("[该订单产品数据,订单详情]orderCode="+so.getOrderCode());
                    continue;
                }
                flag = true;
                goodsVo.setProductUnitSerialNumber("world-"+item.getPuId());
                goodsVo.setIsOwnMerchant(0);
                List<OrderConfirmGoodsVO> goodsList = soItemMap.get(8L);
                if(goodsList==null) {
                    goodsList = new ArrayList<>();
                    soItemMap.put(8L, goodsList);
                }
                goodsList.add(goodsVo);
                if (item.getUnitExist() == 1) {
                    unitFlag = true;
                }
            }else {
                CommodityProductUnitDTO pu = productFacade.queryPuById(item.getPuId());
                StandardUnitDTO standardUnitById = productUnitFacade.findStandardUnitById(pu.getStandardUnitId());
                goodsVo.setPreSellDay(standardUnitById.getPresellPeriod());//预售期
                goodsVo.setSaleWay(standardUnitById.getSaleWay());
                goodsVo.setPuImg(item.getPuPicUrl());
                goodsVo.setPuName(item.getPuName());
                goodsVo.setBuyType(standardUnitById.getBuyType());
                if(standardUnitById.getBuyType()!=3){
                    flag = true;
                }
                if(pu !=null){
                    goodsVo.setStandardUnitId(pu.getStandardUnitId());
                }
                goodsVo.setProductUnitSerialNumber(queryProductUnitSerialNumber(item.getPuId()));
                Long mId = standardUnitById.getMerchantId();
                goodsVo.setIsOwnMerchant(mId.equals(1L) ? 1 : 0);
                if (soItemMap.containsKey(mId)) {
                    soItemMap.get(mId).add(goodsVo);
                } else {
                    List<OrderConfirmGoodsVO> goodsList = new ArrayList<>();
                    goodsList.add(goodsVo);
                    soItemMap.put(mId, goodsList);
                }

                if (item.getUnitExist() == 1) {
                    unitFlag = true;
                }
            }
        }

        //商品全部都是仅积分支付商品，且操作状态是0（取消，其他，积分都有）
        if(!flag){
            if(vo.getOperateStatus()==0){
                vo.setOperateStatus(8);
            }

        }
        if (Objects.equals(thirdpartyType,ThirdConst.ThirdPartyType.QM)){
            vo.setOperateStatus(9);//清美订单，直接跳转到清美页面，操作按钮不显示
            vo.setJumpUrl(jumpUrl);
        }
        StoreDTO store = storeFacade.findStoreById(so.getStoreId());

        List<Map<String, Object>> goodGroupList = new ArrayList<>();
        for (Entry<Long, SoChildDTO> entry : merchantSoChildMap.entrySet()) {
            Map<String, Object> map = new HashMap<>();
            MerchantDTO merchant = merchantFacade.findMerchantById(entry.getKey());
            map.put("merchantId", entry.getKey());
            map.put("merchantName", merchant.getName());
            map.put("isOwnMerchant", entry.getKey().equals(1L) ? 1 : 0);
            map.put("storeName", store.getName());
            map.put("deliveryPrice", entry.getValue().getDeliveryFee());
            map.put("remark", entry.getValue().getRemark());
            map.put("goodsList", soItemMap.get(entry.getKey()));
            goodGroupList.add(map);
        }
        //如果是券仓卡券订单则存在卡券,否则按照是否存在unit库存来判断
        vo.setUnitExist(unitFlag);
        if(isQc){
            vo.setUnitExist(isQc);
        }
        // 查询订单相关的卡密信息
        List<SimpleECardVO> cardList = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        if (unitFlag||isQc) {
            Integer companyType = productFacade.findCompanyTypeByUserId(userId);
            if (companyType != 0) {
                SimpleECardVO svo = new SimpleECardVO();
                svo.setCardNumber(SequenceUtil.genCardNumberCode(userId));
                svo.setCardThick("123456");
                cardList.add(svo);
            } else {
                List<ECardDTO> cards = promotionFacade.queryECardListByOrderCode(orderCode);
                for (ECardDTO c : cards) {
                    SimpleECardVO svo = new SimpleECardVO();
                    svo.setCardNumber(c.getCardNumber());
                    svo.setShortUrl(c.getShortUrl());
                    if(EmptyUtil.isEmpty(c.getEndTime())){
                        svo.setEndTime(null);
                    }else{
                        svo.setEndTime(format.format(c.getEndTime()));
                    }
                    //如果不是券仓卡券且没有卡密
                    if(!isQc&&EmptyUtil.isEmpty(c.getCardThick())){
                        throw new BusinessException("电子卡券未分配密码");
                    }
                    if(EmptyUtil.isNotEmpty(c.getCardThick())){
                        String cipher = c.getCardThick();
                        String uuid = c.getUuid();
                        CardSaltDTO salt = accountFacade.queryCardSaltByUUID(uuid);
                        if (salt == null) {
                            svo.setCardThick("该电子卡券密码有误");
                        } else {
                            String cipherDecrypt;
                            try {
                                cipherDecrypt = QEncodeUtil.aesDecrypt(cipher, salt.getSaltValue());
                            } catch (Exception e) {
                                cipherDecrypt = "该电子卡券密码存在异常";
                                e.printStackTrace();
                            }
                            svo.setCardThick(cipherDecrypt);
                        }
                    }
                    cardList.add(svo);
                }
            }
        }
        // 查询订单相关的发票信息
        Map<String, Object> invoiceInfo = null;
        if (so.getInvoiceId() != null) {
            // 查询公共信息
            SoInvoiceDTO soInvoiceDTO = new SoInvoiceDTO();
            soInvoiceDTO.setSoChildId(soChildDTO.getId());
            List<SoInvoiceDTO> soInvoiceList = soInvoiceFacade.findSoInvoiceAll(soInvoiceDTO);
            Map<String, Object> invoiceBaseInfo = new HashMap<>();
            if (EmptyUtil.isNotEmpty(soInvoiceList)) {
                SoInvoiceDTO publicInvoice = soInvoiceList.get(0);
                invoiceBaseInfo.put("invoiceExists", true);
                invoiceBaseInfo.put("title", publicInvoice.getInvoiceTitleContent());
                invoiceBaseInfo.put("invoiceType", publicInvoice.getInvoiceForm());
                invoiceBaseInfo.put("invoiceContentType", publicInvoice.getInvoiceContentType());
                invoiceBaseInfo.put("registerAddr", publicInvoice.getRegisterAddr());
                invoiceBaseInfo.put("registerTel", publicInvoice.getRegisterTel());
                invoiceBaseInfo.put("depositBank", publicInvoice.getDepositBank());

                String bankAccount = publicInvoice.getBankAccount();
                // 银行账户脱敏处理
                if (bankAccount != null && bankAccount.length() > 4) {

                    String displayStr = bankAccount.substring(bankAccount.length() - 4, bankAccount.length());
                    int hideStrCount = bankAccount.length() - 4;
                    String hideStr = "";
                    for (int i = 0; i < hideStrCount; i++) {
                        hideStr += "*";
                    }
                    bankAccount = hideStr + displayStr;
                }

                invoiceBaseInfo.put("bankAccount", bankAccount);

            } else {
                invoiceBaseInfo.put("invoiceExists", false);
                invoiceBaseInfo.put("title", null);
                invoiceBaseInfo.put("invoiceType", null);
                invoiceBaseInfo.put("invoiceContentType", null);
                invoiceBaseInfo.put("registerAddr", null);
                invoiceBaseInfo.put("registerTel", null);
                invoiceBaseInfo.put("depositBank", null);
                invoiceBaseInfo.put("bankAccount", null);
            }

            Map<String, Object> invoice = new HashMap<>();
            // 查询所有子订单开票信息(状态)
            List<SoChildInvoiceVO> voList = new ArrayList<>();
            SoChildInvoiceVO scVo = new SoChildInvoiceVO();

            scVo.setChildCode(sc.getChildCode());
            scVo.setId(sc.getId());
            SoInvoiceDTO inv = soInvoiceFacade.querySoInvoiceBySoChildId(sc.getId());
            if (inv != null) {
                scVo.setInvoiceId(inv.getId());
                scVo.setInvoiceCode(inv.getInvoiceCode());
                scVo.setFinanceStatus(inv.getInvoiceStatus());
                scVo.setRemark(inv.getInvoiceRemark());
                scVo.setInvoiceType(inv.getInvoiceForm());
            }
            voList.add(scVo);

            invoice.put("invoiceBaseInfo", invoiceBaseInfo);
            invoice.put("childInvoiceList", voList);
            invoiceInfo = invoice;
        }

        Map<String, Object> data = new HashMap<>();
        data.put("orderInfo", vo);
        data.put("goodGroupList", goodGroupList);
        data.put("addressInfo", receiverInfo);
        data.put("cardList", cardList);
        data.put("invoiceInfo", invoiceInfo);


        return JsonResult.success(data);
    }


    @Override
    public JsonResult<Map<String, Object>> cancelChildOrder(String orderCode, Long userId, Long platformId, String ip,
                                                            String userName, String mac, HttpServletRequest req) {



        // 校验订单是否可以取消
        if (EmptyUtil.isBlank(orderCode))
            return JsonResult.fail("请选择订单");
        SoChildDTO soChildDTO = soChildReadService.querySoChildByChildCode(orderCode);
        SoDTO order = soFacade.querySoById(soChildDTO.getSoId());
        if (order == null)
            return JsonResult.fail("订单不存在");

        // 权限控制
        if (userId.longValue() != order.getUserId().longValue())
            return JsonResult.fail("订单不存在");

        int orderStatus = order.getOrderStatus().intValue();
        /*if (orderStatus != OrderConstant.ORDER_STATUS_UNPAY.getStatus()
                && orderStatus != OrderConstant.ORDER_STATUS_PAYED.getStatus()) {
            return JsonResult.fail("取消订单的订单状态必须为待支付或已付款");
        }*/
        if (orderStatus == OrderConstant.ORDER_STATUS_CANCELED.getStatus()
                || orderStatus == OrderConstant.ORDER_STATUS_RETURN_CASH_FINISHED.getStatus()) {
            return JsonResult.fail("订单为已取消或已退款,不能再次操作");
        }
        if (order.getOrderPayStatus() !=1) {
            return JsonResult.fail("该子订单未支付不能操作子订单退款");
        }


        if (soChildDTO.getThirdpartyType() == SoThirdpartyManageImpl.THIRDPARTY_TYPE_PHONE && orderStatus == 1)
            return JsonResult.fail("已付款的第三方话费充值订单不可取消");
        if (soChildDTO.getThirdpartyType() == SoThirdpartyManageImpl.THIRDPARTY_TYPE_JD){
            if (orderStatus == OrderConstant.ORDER_STATUS_PAYED.getStatus()){
                return JsonResult.fail("申请取消订单请拨打电话:400-031-6808");
            }
        }

        // 生成唯一退款单编号(取消已付款订单需要,取消待付款不需要)
        String soRefundCodeByCash = null;
        String soRefundCodeByFubi = null;
        String soRefundCodeByJiDian = null;
        String soRefundCodeByCard=null;
        if (order.getOrderStatus() == OrderConstant.ORDER_STATUS_PAYED.getStatus()) {
            List<String> soRefundNOList = soFacade.genSoRefundNO();
            soRefundCodeByCash = soRefundNOList.get(0);
            soRefundCodeByFubi = soRefundNOList.get(1);
            soRefundCodeByJiDian = soRefundNOList.get(2);
            soRefundCodeByCard = soRefundNOList.get(3);
        }

        // 取消订单逻辑
        soFacade.cancelChildOrderWithTx(order, ip, userId, userName, mac, soRefundCodeByFubi, soRefundCodeByCash,soRefundCodeByJiDian,soChildDTO, req,soRefundCodeByCard);
        if(order.getSaleWay()==8){
            //如果是以旧换新订单需要释放旧unit
            List<ExchangeOrderRecordDTO> exchangeOrderRecordByOrderCode = soFacade.findExchangeOrderRecordByOrderCode(orderCode);
            if(EmptyUtil.isEmpty(exchangeOrderRecordByOrderCode)||exchangeOrderRecordByOrderCode.size()>1){
                logger.info("当前订单对应的以旧换新记录有误,orderCode="+orderCode);
                throw new BusinessException("当前订单对应的以旧换新记录有误,请联系管理员");
            }
            String redisKey = JedisUtil.COUPON_UNIT_LOCK_PRE+exchangeOrderRecordByOrderCode.get(0).getOldUnitCode();
            jedisUtil.delLock(redisKey);
        }
        //变更订单取消状态
        return JsonResult.success();
    }

    /**
     * 删除订子订单单
     * 改变订单删除标志位
     * @param orderCode
     * @return
     */
    @Override
    public JsonResult<Map<String, Object>> deleteByChildOrderCode(String orderCode,Long userId){

        SoChildDTO soChildDTO = soChildReadService.querySoChildByChildCode(orderCode);

        if(soChildDTO == null){
            return JsonResult.fail("订单不存在");
        }
        SoChildDTO editSoChildDTO = new SoChildDTO();
        editSoChildDTO.setChildCode(soChildDTO.getChildCode());
        editSoChildDTO.setChildDeleteStatus(2);
        soChildWriteService.deleteByChildCodeWithTx(editSoChildDTO);
        return JsonResult.success();
    }

    @Override
    public String affirmOrderByChildCode(String orderCode, Long platformId, Long userId) {
        SoChildDTO soChildDTO = soChildReadService.querySoChildByChildCode(orderCode);
        if(soChildDTO == null){
            throw new BusinessException("子订单未找到");
        }
        return soFacade.affirmOrderByChildCode(soChildDTO, platformId);
    }

    @Override
    public JsonResult<Map<String,Object>> onlyCancelChildOrder(String orderCode, Long userId,Long platformId,String ip,
                                                               String userName,String mac, HttpServletRequest req){
        // 校验订单是否可以取消
        if (EmptyUtil.isBlank(orderCode))
            return JsonResult.fail("请选择订单");
        SoChildDTO soChildDTO = soChildReadService.querySoChildByChildCode(orderCode);
        SoDTO order = soFacade.querySoById(soChildDTO.getSoId());
        if (order == null)
            return JsonResult.fail("订单不存在");

        // 权限控制
        if (userId.longValue() != order.getUserId().longValue())
            return JsonResult.fail("订单不存在");

        int orderStatus = order.getOrderStatus().intValue();
        if (orderStatus != OrderConstant.ORDER_STATUS_UNPAY.getStatus()) {
            return JsonResult.fail("取消订单的订单状态必须为待支付");
        }
        //更新子订单为取消订单
        SoChildDTO editSoChildDTO = new SoChildDTO();
        editSoChildDTO.setId(soChildDTO.getId());
        editSoChildDTO.setDeliveryStatus(4);
        editSoChildDTO.setCancelStatus(1);
        soChildWriteService.updateSoChildWithTx(editSoChildDTO);

        List<SoChildDTO> soChildDTOList = soChildReadService.querySoChildListBySoId(order.getId());
        boolean isAllCancel = true;
        for (SoChildDTO childDTO : soChildDTOList) {
            if(childDTO.getCancelStatus() !=1){
                isAllCancel = false;
                break;
            }
        }
        //若全部已取消
        if(isAllCancel){
            SoDTO newSoDTO = new SoDTO();
            newSoDTO.setId(order.getId());
            newSoDTO.setOrderStatus(OrderConstant.ORDER_STATUS_CANCELED.getStatus());
            newSoDTO.setOrderConfirmStatus(2);
            newSoDTO.setCancelReason(1);
            soFacade.updateOrderWithTX(newSoDTO);
        }
        pushOrderManage.pushOrderInfo(order.getId(),null,null);
        return JsonResult.success();
    }

    //展示商品以及结算信息
    @Override
    public JsonResult<Map<String, Object>> orderConfirmNew(Long storeId, Integer type, String cartItemIds, Long puId, Integer num,
                                                           Long addrId, Long memberId, Long platformId, Long companyId, String phone, Integer couponType,
                                                           Long couponUnitId,Long clientId,String channelProductId,Integer source) {
        Long time1 = System.currentTimeMillis();
        if (type == null) {
            return JsonResult.fail("未知下单类型");
        }

        List<CompanyConfigDTO> configs =  userReadService.findUserCompanyConfigs(memberId);
        UserDTO userDTO =  userReadService.findUserByID(memberId);
        boolean isDlfUser = false;
        if(userDTO !=null && EmptyUtil.isNotEmpty(userDTO.getChannelSource()) && Objects.equals(userDTO.getChannelSource(),UserChannelSourceEnum.DLF.getChannelSource())){
            isDlfUser = true;
        }
        boolean hasExistsWorldBuy = false;
        boolean validFubiAcc = true;
        boolean validFdAcc = true;
        boolean payFuBiOnly=false;
        for(CompanyConfigDTO config : configs) {
            if(config.getKey().equalsIgnoreCase("account.0.valid")&& config.getValue()!=null && config.getValue().length()==1) {
                validFubiAcc = (Integer.valueOf(config.getValue()).intValue()==0)?false:true;
            }

            if(config.getKey().equalsIgnoreCase("account.4.valid")&& config.getValue()!=null && config.getValue().length()==1) {
                validFdAcc = (Integer.valueOf(config.getValue()).intValue()==0)?false:true;
            }
            if(config.getKey().equalsIgnoreCase("pay.fubi.only")&& config.getValue()!=null && config.getValue().length()==1) {
                payFuBiOnly = (Integer.valueOf(config.getValue()).intValue()==1)?true:false;
            }
        }

        if (type.intValue() == 0) {
            //校验直接购买的商品是否为会员预售,会员是否有权限
            try {
                StandardUnitDTO suByPuId = getSUByPuId(puId);
                Map<Integer, String> integerStringMap = UserMembershipCheckUtils.checkUserMembershipAuthority(memberId, suByPuId.getId(), suByPuId.getSaleWay(), platformId);
                Set<Integer> integers = integerStringMap.keySet();
                for (Integer i : integers) {
                    if (i == 0) {
                        return JsonResult.fail(integerStringMap.get(i));
                    }
                }
            } catch (Exception e) {
                // TODO: handle exception
            }


        }

        // 通过puid查询sku,然后查到spu信息
        DefaultReceiverInfoVo receiverInfo = defaultReceiverAddress(memberId, addrId, platformId);
        if(EmptyUtil.isEmpty(receiverInfo)){
            receiverInfo = new DefaultReceiverInfoVo();
        }
        //String token = jdUtils.getAccessToken(jedisUtil);
        String token = jdUtils2.getAccessToken(jedisUtil);
        ParseAddressJson parseAddressJson = getDeliveryPriceFromJd(token,receiverInfo.getAddress());
        String errMess=null;
        if(EmptyUtil.isEmpty(parseAddressJson)){
            errMess = "抱歉，因为网络问题，运费计算有误,请以订单最终收取的运费为准";
        }
        Long commodityTemplateId = 2l;
        if(puId!=null) {
            commodityTemplateId = productFacade.queryCommodityTemplateIdByPuId(puId);
        }
        if(commodityTemplateId==null) {
            commodityTemplateId = 2l;
        }
        Long time2 = System.currentTimeMillis();
        logger.info("时间2:"+(time2-time1));
        List<OrderResult> orderResultList = coustructOrderResultNew(parseAddressJson,receiverInfo.getAddress(),clientId,storeId, type, cartItemIds, puId, num, addrId, memberId, platformId, companyId, phone, couponType, couponUnitId, commodityTemplateId,channelProductId,source);
        Long time3 = System.currentTimeMillis();
        logger.info("时间3:"+(time3-time2));
        List<Map<String, Object>> goodGroupList = new ArrayList<>();
        BigDecimal orderAmountTotal = BigDecimal.ZERO;
        BigDecimal orderAmountOnlyCashTotal = BigDecimal.valueOf(0);//仅支持现金购买的商品金额
        int goodsAccount = 0;
        double totalDeliveryPrice = 0;
        List<OrderConfirmGoodsDTO> goodsList = new ArrayList<>();
        List<OrderConfirmGoodsDTO> unBuyGoodsList = new ArrayList<>();
        Map<Long, BigDecimal> deliveryPriceMap = new HashMap<>();
        List<LimitRuleRecordDTO> limitRuleRecordList = new ArrayList<>();
        logger.info("校验限购记录="+orderResultList.size());
        for(OrderResult orderResult : orderResultList){
            if(!orderResult.isSuccess()){
                return JsonResult.fail(orderResult.getError());
            }
            if(EmptyUtil.isNotEmpty(orderResult.getLimitRuleRecordList())){
                limitRuleRecordList.addAll(orderResult.getLimitRuleRecordList());
            }
        }
        // 根据pu商品集合及限购规则判断是否能购买、返回值不为空直接返回错误
        if(EmptyUtil.isNotEmpty(limitRuleRecordList)){
            String isLimitBuy = productFacade.isLimitBuy(storeId,limitRuleRecordList, companyId, platformId, memberId);
            logger.info("限购结果:"+isLimitBuy);
            if (isLimitBuy != null) {
                return JsonResult.fail(isLimitBuy, BusinessExceptionConstant.LIMITBUYEXCEPTIONCODE);
            }
        }

        Long time5 = System.currentTimeMillis();
        logger.info("时间5:"+(time5-time3));
        // 使用固定数量的线程池，避免高并发导致服务器线程无限制的增长
        ThreadPoolExecutor executor = CommonThreadPoolExecutor.getInstance();
        // 主线程优先拿到最先完成的任务的返回值，而不管它们加入线程池的顺序。
        CompletionService<Map<String,Object>> completionService = new ExecutorCompletionService<>(executor);
        List<Future<Map<String,Object>>> results = new ArrayList<>();
        Future<Map<String,Object>> future = null;

        for (OrderResult orderResult : orderResultList) {
            OrderConfirmChannelDeliveryReqVO reqVO = new OrderConfirmChannelDeliveryReqVO();
            Long merchantId = orderResult.getMerchant().getId();
            reqVO.setMerchantId(merchantId);
            reqVO.setParseAddressJson(parseAddressJson);
            reqVO.setReceiverInfo(receiverInfo);
            reqVO.setToken(token);
            reqVO.setPlatformId(platformId);
            reqVO.setStoreId(storeId);
            reqVO.setOrderResult(orderResult);
            future = completionService.submit(new OrderConfirmDeliveryThread(reqVO));
            results.add(future);
        }
        Map<Long,Map<String,Object>> deliveryMap = new HashMap<>();
        for (Future<Map<String, Object>> result : results) {
            try {
                Map<String,Object> merchantDeliveryMap = result.get();
                if(EmptyUtil.isNotEmpty(result)){
                    Long merchantId =  (Long)merchantDeliveryMap.get("merchantId");
                    deliveryMap.put(merchantId,merchantDeliveryMap);
                }
            }catch (Exception e){
                logger.error("获取商户运费失败，发生异常:{}",e);
            }
        }
        Long getDeliveryTime = System.currentTimeMillis();
        logger.info("确认订单获取运费总体耗时:"+(getDeliveryTime-time5));
        BigDecimal payByFuBiAmount = BigDecimal.ZERO;
        for (OrderResult orderResult : orderResultList) {
            payByFuBiAmount = payByFuBiAmount.add(orderResult.getPayByFuBiAmount());
            if (!orderResult.isSuccess()) {
                return JsonResult.fail(orderResult.getError());
            }

            Map<String, Object> goodGroup = new HashMap<>();
            orderAmountTotal = orderAmountTotal.add(orderResult.getOrderAmount());

            if (EmptyUtil.isEmpty(orderResult.getOrderAmountOnlyCash())) {
                orderResult.setOrderAmountOnlyCash(BigDecimal.ZERO);
            }
            orderAmountOnlyCashTotal = orderAmountOnlyCashTotal.add(orderResult.getOrderAmountOnlyCash());
            goodsAccount += orderResult.getGoodsAccount();
            goodGroup.put("goodsList", orderResult.getGoodsList());
            goodsList.addAll(orderResult.getGoodsList());
            unBuyGoodsList.addAll(orderResult.getJdDownGoods());
            logger.info("receiverInfo打印:{}", JSON.toJSONString(receiverInfo));
            logger.info("orderResult打印:{}", JSON.toJSONString(orderResult));
            Map<String, Object> merchantDeliveryMap = deliveryMap.get(orderResult.getMerchant().getId());
            goodGroup.put("deliveryMethod", 0);
            goodGroup.put("deliveryPrice", 0);
            deliveryPriceMap.put(orderResult.getMerchant().getId(), BigDecimal.ZERO);
            orderResult.setNeedCountDelivery(false);
            if (EmptyUtil.isNotEmpty(receiverInfo.getAddress()) && EmptyUtil.isNotEmpty(orderResult.getGoodsList()) && !orderResult.getGoodsList().get(0).isCard()
                    && !orderResult.getStore().getIsDetail().equals(1L)
                    && (couponType == null || couponType != 1)) {
                String successCode = (String) merchantDeliveryMap.get("success");
                if (Objects.equals("1", successCode)) {
                    Double deliveryPrice = Double.valueOf(merchantDeliveryMap.get("deliveryPrice")+"");
                    goodGroup.put("deliveryPrice", deliveryPrice);
                    deliveryPriceMap.put(orderResult.getMerchant().getId(), BigDecimal.valueOf(Double.valueOf(deliveryPrice)));
                    if(deliveryPrice >0){
                        goodGroup.put("deliveryMethod", 1);
                        orderResult.setNeedCountDelivery(true);
                        totalDeliveryPrice += deliveryPrice;
                    }
                } else {
                    Object msg = merchantDeliveryMap.get("msg");
                    goodGroup.put("jdDeliveryError", errMess);
                    if (EmptyUtil.isNotEmpty(msg)) {
                        goodGroup.put("jdDeliveryError", msg);
                    }
                }
            }
            goodGroup.put("merchantId", orderResult.getMerchant().getId());
            goodGroup.put("merchantName", orderResult.getMerchant().getName());
            goodGroup.put("storeName", orderResult.getStore().getName());
            goodGroup.put("isOwnMerchant", orderResult.getMerchant().getId().equals(1L) ? 1 : 0);//是否自营
            goodGroupList.add(goodGroup);
        }

        // 返回用户默认收货地址
        Map<String, Object> data = new HashMap<>();
        boolean isFuBi = true;
        for (OrderResult orderResult : orderResultList) {
            if(orderResult.getIsPayByFuBiOnly()==0){
                isFuBi = false;
            }
        }
        if (payFuBiOnly){
            isFuBi=payFuBiOnly;
        }
        if(isFuBi){
            data.put("isPayByFuBiOnly",1);
        }else{
            data.put("isPayByFuBiOnly",0);

        }
        data.put("receiverInfo", receiverInfo);
        data.put("goodGroupList", goodGroupList);
        data.put("unBuyGoodsList",unBuyGoodsList );
        // 默认发票信息
        data.put("invoiceType", 0);
        // 商品数量
        data.put("goodsAccount", goodsAccount);
        data.put("totalDeliveryPrice", totalDeliveryPrice);
        //判断是否仅现金支付
        Integer isPayByCashOnly=Integer.valueOf(1);
        for(OrderConfirmGoodsDTO dto:goodsList){
            if(dto.getBuyType()!=2){
                isPayByCashOnly=Integer.valueOf(0);
            }
        }
        data.put("isPayByCashOnly",isPayByCashOnly);
        //根据storeId判断当前门店是不是零售门店
        StoreDTO storeById1 = storeFacade.findStoreById(storeId);
        if (storeById1==null) {
            return JsonResult.fail(storeId + "不存在");
        }
        data.put("isDetailStore", storeById1.getIsDetail());
        data.put("orderAmount", orderAmountTotal);

        //计算用户可用的积分金额
        // 可达积分余额
        BigDecimal availableFbBalance = new BigDecimal(0);
        BigDecimal availableJiDianBalance = new BigDecimal(0);
        if (platformId == 7) {
            // 用户可用积分数量:积分余额-已冻结积分
            // 积分账户
            UserAccountDTO fubiAcc = accountFacade.queryUserAccountByUserId(memberId, 0);
            UserAccountDTO fubiFdAcc = accountFacade.queryUserAccountByUserId(memberId, 4);
            UserAccountDTO jidianAcc = accountFacade.queryUserAccountByUserId(memberId, 5);
            if(jidianAcc !=null && jidianAcc.getBalance() !=null){
                availableJiDianBalance = jidianAcc.getBalance();
            }
            if (fubiAcc != null ) {
                if(validFubiAcc) {
                    // 账户加密值校验
                    SaltDTO salt = accountFacade.querySaltByUUID(fubiAcc.getUuid());
                    if (salt == null)
                        return JsonResult.fail("积分账户加密数据未就绪");
                    boolean cipherValid = MD5Util.md5Valid(fubiAcc.getBalance().toString(), salt.getSaltValue(),
                            fubiAcc.getCiphertext());
                    if (SpringContextTool.isPrd() && !cipherValid)
                        return JsonResult.fail("积分账户已被冻结");
                }
            } else {
                return JsonResult.fail("积分账户不存在,请联系管理员");
            }

            if (fubiFdAcc != null ) {
                if(validFdAcc) {

                    // 账户加密值校验
                    SaltDTO salt = accountFacade.querySaltByUUID(fubiFdAcc.getUuid());
                    if (salt == null)
                        return JsonResult.fail("积分账户加密数据未就绪");
                    boolean cipherValid = MD5Util.md5Valid(fubiFdAcc.getBalance().toString(), salt.getSaltValue(),
                            fubiFdAcc.getCiphertext());
                    if (SpringContextTool.isPrd() && !cipherValid)
                        return JsonResult.fail("积分账户已被冻结");// 账户加密值校验
                }

            } else {
                fubiFdAcc = new UserAccountDTO();
                fubiFdAcc.setBalance(new BigDecimal(0));;
            }

            // 积分冻结账户
            UserAccountDTO ffAcc = accountFacade.queryUserAccountByUserId(memberId, 2);
            if (ffAcc != null) {
                // 账户加密值校验
                SaltDTO salt = accountFacade.querySaltByUUID(ffAcc.getUuid());
                if (salt == null)
                    return JsonResult.fail("积分冻结账户加密数据未就绪");
                boolean cipherValid = MD5Util.md5Valid(ffAcc.getBalance().toString(), salt.getSaltValue(),
                        ffAcc.getCiphertext());
                if (!cipherValid)
                    return JsonResult.fail("积分冻结账户已被冻结");
            } else {
                return JsonResult.fail("积分冻结账户不存在,请联系管理员");
            }

            if(!isDlfUser){
                availableFbBalance = fubiAcc.getBalance().add((fubiFdAcc==null || fubiFdAcc.getBalance()==null)?new BigDecimal(0) : fubiFdAcc.getBalance()).subtract(ffAcc.getBalance());
            }else{
                availableFbBalance = fubiAcc.getBalance();
            }

        }
        data.put("fubiBalance", availableFbBalance);
        data.put("jidianBalance", availableJiDianBalance);
        //可用于积分支付的金额
        BigDecimal orderAmountByFuBi = orderAmountTotal.subtract(orderAmountOnlyCashTotal);


        // 商品总额
        data.put("goodsAmount", orderAmountTotal);


        // 优惠卷unit
        List<OrderConfirmCouponUnitVO> couponUnitList = new ArrayList<OrderConfirmCouponUnitVO>();
        CouponUnitDTO couponUnitDTO = new CouponUnitDTO();
        couponUnitDTO.setUserId(memberId);
        couponUnitDTO.setCouponType(couponType == null ? 0 : couponType);
        //获取优惠卷优惠后的金额
        logger.info("优惠券信息:"+couponUnitId);
        logger.info("优惠券信息:"+JSONObject.toJSONString(couponUnitDTO));
        couponUnitList = promotionFacade.calculateAfterCouponDetail(orderAmountTotal,payByFuBiAmount,orderAmountByFuBi,orderAmountOnlyCashTotal,orderResultList, storeId, couponUnitId, couponUnitDTO, platformId, data, deliveryPriceMap,clientId);
        Long time7 = System.currentTimeMillis();
        logger.info("时间7:"+(time7-time5));
        //没有优惠卷
        BigDecimal dicount = new BigDecimal(promotionFacade.getDiscount(storeId, platformId).toString()).divide(BigDecimal.valueOf(100));//折扣率*100
        //门店优惠金额
        BigDecimal orderAmount = orderAmountTotal;//商品金额
        BigDecimal storeDiscount = (orderAmount.subtract(orderAmount.multiply(dicount))).setScale(2,BigDecimal.ROUND_DOWN);//门店优惠
//        BigDecimal realPay = BigDecimal.valueOf(cashPay).subtract(storeDiscount).add(BigDecimal.valueOf(totalDeliveryPrice));
        data.put("orderAmount", orderAmount.add(new BigDecimal(String.valueOf(totalDeliveryPrice))).subtract(storeDiscount));//商品总额+运费-总优惠
        data.put("storeDiscount", storeDiscount);
        double cashPay = 0d;
        BigDecimal orderAmountPaidByFuBi = BigDecimal.valueOf(0);
        //如果仅积分支付
        Integer canPay=1;
        BigDecimal payByFuBi=payByFuBiAmount.subtract(storeDiscount);
        if(availableFbBalance.compareTo(payByFuBi)<0){
            canPay=0;
        }
        data.put("canPay",canPay);

        BigDecimal compareAmount = orderAmount.add(new BigDecimal(String.valueOf(totalDeliveryPrice))).subtract(storeDiscount).subtract(new BigDecimal(String.valueOf(totalDeliveryPrice))).subtract(orderAmountOnlyCashTotal);
        //当可用积分余额≥max{0,实付款 -总运费 – 仅支持现金支付的商品总额} + 总运费 时，可抵扣金额 = max{0，实付款 - 总运费 – 仅支持现金支付的商品总额} + 总运费
        //当可用积分余额≤max{0，实付款 - 总运费 – 仅支持现金支付的商品总额} + 总运费时，可抵扣金额 = 当可用积分余额





        if(compareAmount.compareTo(BigDecimal.valueOf(0))>=0){
            //实付款 -总运费 – 仅支持现金支付的商品总额 max
            if(availableFbBalance.compareTo(compareAmount.add(new BigDecimal(String.valueOf(totalDeliveryPrice))))>=0){
                //当可用积分余额>=max
                orderAmountPaidByFuBi = compareAmount.add(new BigDecimal(String.valueOf(totalDeliveryPrice)));
            }else{
                //当可用积分余额<=max
                orderAmountPaidByFuBi = availableFbBalance;

            }

        }else{
            //0 max
            if(availableFbBalance.compareTo(new BigDecimal(String.valueOf(totalDeliveryPrice)))>=0){
                //当可用积分余额>=max
                orderAmountPaidByFuBi = new BigDecimal(String.valueOf(totalDeliveryPrice));
            }else{
                //当可用积分余额<=max
                orderAmountPaidByFuBi = availableFbBalance;
            }
        }
        cashPay = orderAmount.add(new BigDecimal(String.valueOf(totalDeliveryPrice))).subtract(storeDiscount).subtract(orderAmountPaidByFuBi).doubleValue();

        //如果仅积分支付，存在现金支付时不可支付
        if(payFuBiOnly && (cashPay>0)){
            data.put("canPay",0);
        }


        data.put("cashPay", cashPay>0?cashPay:0);
        data.put("orderAmountPaidByFuBi",orderAmountPaidByFuBi);

        data.put("couponUnitList", couponUnitList);


        // 话费充值手机号
        data.put("phone", phone);

        //判断门店是不是零售门店
        boolean isShowAddr = true;
        StoreDTO storeById = storeFacade.findStoreById(storeId);

        if (storeById.getIsDetail() == 1) {
            //零售门店不显示地址
            isShowAddr = false;
        } else {
            // 判断是否展示收货地址信息(电子和充话费不用显示)
            isShowAddr = commodityTemplateId == null || commodityTemplateId.equals(2L)|| commodityTemplateId.equals(7L)
                    ? true : false;

        }
        data.put("isShowAddr", isShowAddr);

        //是否需要填写身份证和姓名
        data.put("needIdCardNo", "0");
        //若是存在全球购的商品则需要填写身份证号和真实姓名
        if(hasExistsWorldBuy && (EmptyUtil.isEmpty(userDTO.getName()) || EmptyUtil.isEmpty(userDTO.getIdCardNo()))){
            data.put("needIdCardNo", "1");
        }
        Long time6 = System.currentTimeMillis();
        logger.info("时间6:"+(time6-time5));
        return JsonResult.success(data);
    }


    private List<OrderResult> coustructOrderResultNew(ParseAddressJson parseAddressJson, String address, Long clientId, Long storeId, Integer type, String cartItemIds, Long puId, Integer num,
                                                      Long addrId, Long memberId, Long platformId, Long companyId, String phone, Integer couponType,
                                                      Long couponUnitId, Long commodityTemplateId,String channelProductId,Integer source) {
        CompanyDTO companyDTO=soFacade.findCompanyById(companyId);
        if(EmptyUtil.isEmpty(companyDTO)){
            throw new BusinessException("所属公司不存在");

        }
        List<OrderResult> orderResultList = new ArrayList<>();
        if (type.intValue() == 0) {
            OrderResult orderResult = buyPrductByRightNowNew(parseAddressJson,address,clientId,companyDTO.getCompanyType(),puId, num, couponType,
                    couponUnitId, commodityTemplateId,
                    phone, storeId,channelProductId,source,type);
            orderResultList.add(orderResult);
        } else {
            List<Long> cartItemIdList = JSONArray.parseArray(cartItemIds, Long.class);
            if (EmptyUtil.isEmpty(cartItemIdList)) {
                throw new BusinessException("已勾选的购物车项为空");
            }
            Map<Long, List<CartItemDTO>> merchantCartMap = new HashMap<>();
            for (Long cartItemId : cartItemIdList) {
                CartItemDTO cartItemDTO = cartItemFacade.findCartItemById(cartItemId);
                if (cartItemDTO == null) {
                    throw new BusinessException("购物车项不存在");
                }
                Long merchantId = cartItemDTO.getMerchantId();
                if (merchantCartMap.containsKey(merchantId)) {
                    merchantCartMap.get(merchantId).add(cartItemDTO);
                } else {
                    List<CartItemDTO> cartItemList = new ArrayList<>();
                    cartItemList.add(cartItemDTO);
                    //购物车按照商户来分类
                    merchantCartMap.put(merchantId, cartItemList);
                }
            }

            //仅有一条没必要构建线程池
            if(merchantCartMap.size() ==1){
                for (Entry<Long, List<CartItemDTO>> entry : merchantCartMap.entrySet()) {
                    OrderResult orderResult = buyProductByCartNew(parseAddressJson, address, clientId, companyDTO.getCompanyType(), entry.getValue(), puId, memberId, platformId, storeId, entry.getKey());
                    orderResultList.add(orderResult);
                }
                return orderResultList;
            }
            Long enterpriseId = RuntimeContext.cacheUser().getEnterpriseId();
            // 使用固定数量的线程池，避免高并发导致服务器线程无限制的增长
            ThreadPoolExecutor executor = CommonThreadPoolExecutor.getInstance();
            // 主线程优先拿到最先完成的任务的返回值，而不管它们加入线程池的顺序。
            CompletionService<OrderResult> completionService = new ExecutorCompletionService<>(executor);
            List<Future<OrderResult>> results = new ArrayList<>();
            Future<OrderResult> future = null;

            for (Entry<Long, List<CartItemDTO>> entry : merchantCartMap.entrySet()) {
                OrderConfirmMerchantThreadReqVo reqVo = new OrderConfirmMerchantThreadReqVo();
                reqVo.setEnterpriseId(enterpriseId);
                reqVo.setAddress(address);
                reqVo.setClientId(clientId);
                reqVo.setCompanyType(companyDTO.getCompanyType());
                reqVo.setCartItemList(entry.getValue());
                reqVo.setUserId(memberId);
                reqVo.setStoreId(storeId);
                reqVo.setPlatformId(platformId);
                reqVo.setMerchantId(entry.getKey());
                future = completionService.submit(new OrderConfirmMerchantThread(reqVo));
                results.add(future);
            }
            for (Future<OrderResult> resultFuture : results) {
                try {
                    OrderResult orderResult = resultFuture.get();
                    if(Objects.isNull(orderResult)){
                        continue;
                    }
                    orderResultList.add(orderResult);
                }catch (Exception e){

                }
            }
        }
        return orderResultList;
    }

    //购物车下单生成限购规则,订单商品列表
    private OrderResult buyProductByCartNew(ParseAddressJson parseAddressJson, String address, Long clientId, Integer companyType, List<CartItemDTO> cartItemList, Long puId, Long userId, Long platformId, Long storeId, Long merchantId) {
        Long enterpriseId = RuntimeContext.cacheUser().getEnterpriseId();
        OrderResult result = new OrderResult();
        List<OrderConfirmGoodsDTO> goodsList = new ArrayList<>();
        List<LimitRuleRecordDTO> limitRuleRecordList = new ArrayList<>();
        int goodsAccount = 0;//订单商品总数
        BigDecimal orderAmount = BigDecimal.ZERO;//订单总金额
        BigDecimal orderAmountOnlyCash = BigDecimal.valueOf(0);//仅现金支付金额
        MerchantDTO merchant = merchantFacade.findMerchantById(merchantId);
        List<OrderConfirmGoodsDTO> jdDownGoods = new ArrayList<>();
        result.setIsPayByFuBiOnly(0);
        result.setMerchant(merchant);
        BigDecimal payByFuBiAmount = BigDecimal.ZERO;

        // 使用固定数量的线程池，避免高并发导致服务器线程无限制的增长
        ThreadPoolExecutor executor = CommonChildThreadPoolExecutor.getInstance();
        // 主线程优先拿到最先完成的任务的返回值，而不管它们加入线程池的顺序。
        CompletionService<OrderResult> completionService = new ExecutorCompletionService<>(executor);
        List<Future<OrderResult>> results = new ArrayList<>();
        Future<OrderResult> future = null;

        // 购物车下单
        for (CartItemDTO ci : cartItemList) {
            // 商品存在校验
            Long puId_ = ci.getProductUnitId();
            int num_ = ci.getNum().intValue();
            OrderConfirmReqVO reqVO = new OrderConfirmReqVO();
            reqVO.setCartItemId(ci.getId());
            reqVO.setEnterpriseId(enterpriseId);
            reqVO.setParseAddressJson(parseAddressJson);
            reqVO.setAddress(address);
            reqVO.setClientId(clientId);
            reqVO.setCompanyType(companyType);
            reqVO.setPuId(puId_);
            reqVO.setNum(num_);
            reqVO.setStoreId(storeId);
            reqVO.setChannelProductId(ci.getChannelProductId());
            reqVO.setSource(ci.getSource());
            reqVO.setPlatformId(platformId);
            reqVO.setMemberId(userId);
            reqVO.setMerchantId(merchantId);
            future = completionService.submit(new OrderConfirmProductThread(reqVO));
            results.add(future);
        }

        for (Future<OrderResult> orderResultFuture : results) {
            try {
                OrderResult pOrderResult =  orderResultFuture.get();
                if(Objects.isNull(pOrderResult)){
                    continue;
                }
                int pNum = EmptyUtil.isNotEmpty(pOrderResult.getGoodsAccount())?pOrderResult.getGoodsAccount():0;
                BigDecimal pPayByFuBiAmount = EmptyUtil.isNotEmpty(pOrderResult.getPayByFuBiAmount())?pOrderResult.getPayByFuBiAmount():BigDecimal.ZERO;
                BigDecimal pOrderAmount = EmptyUtil.isNotEmpty(pOrderResult.getOrderAmount())?pOrderResult.getOrderAmount():BigDecimal.ZERO;
                goodsAccount =  goodsAccount+pNum;
                payByFuBiAmount = payByFuBiAmount.add(pPayByFuBiAmount);
                orderAmount = orderAmount.add(pOrderAmount);
                if(EmptyUtil.isNotEmpty(pOrderResult.getGoodsList())){
                    goodsList.addAll(pOrderResult.getGoodsList());
                }
                if(EmptyUtil.isNotEmpty(pOrderResult.getLimitRuleRecordList())){
                    limitRuleRecordList.addAll(pOrderResult.getLimitRuleRecordList());
                }
                if(EmptyUtil.isNotEmpty(pOrderResult.getJdDownGoods())){
                    jdDownGoods.addAll(pOrderResult.getJdDownGoods());
                }
            }catch (Exception e){
                logger.error("执行OrderConfirmProductThread任务失败:{}",e);
            }
        }

        StoreDTO store = storeFacade.findStoreById(storeId);
        result.setStore(store);
        result.setMerchant(merchant);
        result.setGoodsAccount(goodsAccount);
        result.setPayByFuBiAmount(payByFuBiAmount);
        result.setOrderAmount(orderAmount);
        result.setLimitRuleRecordList(limitRuleRecordList);
        result.setGoodsList(goodsList);
        result.setJdDownGoods(jdDownGoods);
        result.setSuccess(true);
        return result;

    }


    //直接下单判断是否为unit,生成限购规则记录,生成订单商品列表
    private OrderResult  buyPrductByRightNowNew(ParseAddressJson parseAddressJson, String address, Long clientId, Integer companyType, Long puId, Integer num, Integer couponType,
                                                Long couponUnitId, Long commodityTemplateId,
                                                String phone, Long storeId,String channelProductId,Integer source,Integer type) {
        Long enterpriseId = RuntimeContext.cacheUser().getEnterpriseId();
        OrderConfirmReqVO reqVO = new OrderConfirmReqVO();
        reqVO.setEnterpriseId(enterpriseId);
        reqVO.setParseAddressJson(parseAddressJson);
        reqVO.setAddress(address);
        reqVO.setClientId(clientId);
        reqVO.setCompanyType(companyType);
        reqVO.setPuId(puId);
        reqVO.setNum(num);
        reqVO.setCouponType(couponType);
        reqVO.setPhone(phone);
        reqVO.setStoreId(storeId);
        reqVO.setChannelProductId(channelProductId);
        reqVO.setSource(source);
        reqVO.setCouponUnitId(couponUnitId);
        reqVO.setCommodityTemplateId(commodityTemplateId);
        reqVO.setType(type);
        //随便找一个渠道去调用公共检查

        OrderResult orderResult = orderConfirmFactory.getSearchProductStrategy(ProductChannelCodeEnum.SELF.getCode()).checkBefore(reqVO);
        if(!orderResult.isSuccess()){
            return orderResult;
        }
        // 商品存在校验
        CommodityProductUnitDTO pu = productFacade.queryPuById(puId);
        JdProductDTO jdProductDTO = null;
        boolean isJd = false;
        if(pu==null && (source==null || source.intValue()==3)) {
            jdProductDTO = productFacade.checkJdProductStatus(puId+"");
            if(jdProductDTO!=null) {
                isJd = true;
            }
        }
        if(pu != null){
            reqVO.setPu(pu);
        }
        if(jdProductDTO !=null){
            reqVO.setJdProductDTO(jdProductDTO);
        }
        String channelCode = sourceTransformChannelCode(source,isJd);
        orderResult = orderConfirmFactory.getSearchProductStrategy(channelCode).buyPrductByRightNow(reqVO);
        StoreDTO store = storeFacade.findStoreById(storeId);
        orderResult.setStore(store);
        return orderResult;
    }

    private String sourceTransformChannelCode(Integer source,boolean isJd) {
        if(isJd || (source !=null && Objects.equals(source,ThirdConst.Source.JD))){
            return ProductChannelCodeEnum.JD.getCode();
        }
        if(source !=null && Objects.equals(source,ThirdConst.Source.CAKE)){
            return ProductChannelCodeEnum.CAKE.getCode();
        }else if(source !=null && Objects.equals(source,ThirdConst.Source.WORLD)){
            return ProductChannelCodeEnum.WORLD_BUY.getCode();
        }
        return ProductChannelCodeEnum.SELF.getCode();
    }

    @Resource
    private OrderCreateFactory orderCreateFactory;

    /**
     * 创建订单
     */
    @Override
    public JsonResult<Map<String, Object>> createOrderNew(HttpServletRequest req, List<Long> puIdList, Long exchangeId, Long exchangeCouponUnitId, Long exchangeCouponBatchId, Integer orderType, Long storeId, Long receiveAddressId, Integer type,
                                                          String cartItemIds, Long puId, Integer num, Integer useFubi, String remark, Long invoiceId, Long userId,
                                                          Long platformId, String deviceId, Integer orderChannel, String ip, String os, String phoneModel,
                                                          String versionCode, String userName, String mac, Long companyId, String phone, Integer couponType,
                                                          Long couponUnitId, String deliveryPrice,String channelProductId,Integer source,String thirdOrderJsonStr) {

        JSONObject remarkObj = EmptyUtil.isNotEmpty(remark)?JSONObject.parseObject(remark):null;
        JSONObject deliveryPriceObj = EmptyUtil.isNotEmpty(deliveryPrice)?JSONObject.parseObject(deliveryPrice):null;
        CompanyDTO companyDTO= soFacade.findCompanyById(companyId);

        String checkRt = checkParam(companyDTO,type,orderChannel,deviceId,os,phoneModel,versionCode);
        if(EmptyUtil.isNotEmpty(checkRt)){
            return JsonResult.fail(checkRt);
        }
        // 查询用户信息
        UserDTO user = userFacade.queryUserById(userId);
        if (user == null) {
            return JsonResult.fail("当前用户不存在");
        }
        Long interfaceId = 0l;
        if (useFubi == null)
            useFubi = 0;

        //判断商品是不是会籍商品
        //获得该商品的会籍信息
        MembershipDTO membershipDTO = null;
        List<Integer> sources = new ArrayList<>();
        //sources.add(3);
        sources.add(4);
        sources.add(5);

        List<Long> cartItemIds_ = null;
        Integer saleWay = 1;
        boolean isJdType0 = false;
        boolean isQingMei= Objects.equals(type,6);
        Integer sourceProduct=0;
        Long enterpriseId =null;
        if(RuntimeContext.cacheUser() !=null){
            enterpriseId = RuntimeContext.cacheUser().getEnterpriseId();
        }
        CreateOrderReqVO createOrderReqVO = new CreateOrderReqVO();
        createOrderReqVO.setPuId(puId);
        createOrderReqVO.setType(type);
        createOrderReqVO.setChannelProductId(channelProductId);
        createOrderReqVO.setEnterpriseId(enterpriseId);
        createOrderReqVO.setStoreId(storeId);
        createOrderReqVO.setCompanyDTO(companyDTO);
        createOrderReqVO.setCompanyId(companyId);
        createOrderReqVO.setUser(user);
        createOrderReqVO.setUserId(userId);
        createOrderReqVO.setUserName(userName);
        createOrderReqVO.setPhone(phone);
        createOrderReqVO.setPlatformId(platformId);
        createOrderReqVO.setThirdOrderJsonStr(thirdOrderJsonStr);
        CreateOrderRespVO createOrderRespVO = null;
        QingMeiOrderDTO qingMeiOrderDTO=null;
        CommodityProductUnitDTO puType0 =null;
        if(type.intValue()==0) {
            String channelCode = getChannelCode(source);
            JsonResult<CreateOrderRespVO> jrt = orderCreateFactory.getOrderCreateStrategy(channelCode).getProduct(createOrderReqVO);
            if(Objects.isNull(jrt)){
                return JsonResult.fail("查找商品失败,请稍后重试");
            }
            if(jrt.getCode() !=0){
                return JsonResult.fail(jrt.getError());
            }
            createOrderRespVO =  jrt.getData();
            if(Objects.isNull(createOrderRespVO)){
                return JsonResult.fail("查找商品失败,请稍后重试");
            }
            sourceProduct =  createOrderRespVO.getSourceProduct();
            if(Objects.equals(ThirdConst.Source.JD,sourceProduct)){
                isJdType0 = true;
            }
            if(Objects.equals(Integer.valueOf(1),sourceProduct) && Objects.nonNull(createOrderRespVO.getProductObject())){
                puType0 = (CommodityProductUnitDTO)createOrderRespVO.getProductObject();
            }
        }


        //校验会籍预售权限/门店
        //if(!isJdType0){
        if(sourceProduct !=3 && sourceProduct !=4 && !isQingMei && sourceProduct !=5) {
            if (EmptyUtil.isNotEmpty(puId)) {

                StandardUnitDTO suByPuId = getSUByPuId(puId);
                Map<Integer, String> integerStringMap = UserMembershipCheckUtils.checkUserMembershipAuthority(userId, suByPuId.getId(), suByPuId.getSaleWay(), platformId);
                Set<Integer> integers = integerStringMap.keySet();
                for (Integer i : integers) {
                    if (i == 0) {
                        return JsonResult.fail(integerStringMap.get(i));
                    }

                }

                StandardProductUnitAttNameDTO standardProductUnitAttNameDTO=soFacade.findStandardProductUnitAttNameById(suByPuId.getStandardProductUnitId());
                List<StandardProductUnitAttValueDTO> standardProductUnitAttValueDTOS=soFacade.findStandardProductUnitValue(standardProductUnitAttNameDTO.getId());
                if(type==0&&suByPuId.getSaleWay()==1L&&EmptyUtil.isNotEmpty(standardProductUnitAttValueDTOS)){
                    //会籍购买必须满足第三方对接参数为内部会籍
                    saleWay = 5;
                }else{
                    saleWay = suByPuId.getSaleWay();
                }
                //补差价订单
                if(EmptyUtil.isNotEmpty(orderType)&&orderType.equals(8)){
                    saleWay=8;
                }
            } else {
                //如果是预售生成的订单编号类型为P,这里设置一下订单类型
                cartItemIds_ = JSONArray.parseArray(cartItemIds, Long.class);
                if (EmptyUtil.isEmpty(cartItemIds_)) {
                    return JsonResult.fail("购物车项参数为空");
                }
                for (Long l : cartItemIds_) {
                    CartItemDTO cartItemById = cartItemFacade.findCartItemById(l);
                    if(cartItemById==null) {
                        continue;
                    }
                    CartDTO dto = new CartDTO();
                    dto.setId(cartItemById.getCartId());
                    CartDTO cartById = cartFacade.findCartById(dto);

                    saleWay = cartById.getSaleWay();


                }
            }
        }



        // 生成订单编号, 订单类型：1正常销售、2团购、3兑换券 (默认为1正常销售) 4:普通预售 5:会籍购买 6.会籍预售 8.以旧换新加价(补差价)

        if (couponType != null && couponUnitId != null && couponType == 1) {
            // 兑换卷兑换
            saleWay = 3;
        }
      /*  if (membershipDTO != null) {
            saleWay = 5;

        }*/


        //生成订单code
        String orderCode = SequenceUtil.genOrderCode(orderChannel, saleWay, userId, platformId);
        // 订单编号校验
        SoDTO so_ = soFacade.querySoByOrderCode(orderCode);
        if (so_ != null)
            return JsonResult.fail("订单编号重复,请重试");
        // 发票校验
        SoInvoiceDTO inv = null;
        if (EmptyUtil.isEmpty(invoiceId) || invoiceId == 0) {
            invoiceId = null;
        }
        if (invoiceId != null) {
            inv = soInvoiceFacade.findSoInvoiceById(invoiceId);
            if (inv == null)
                return JsonResult.fail("发票信息丢失");
        }


        // 组织订单项
        List<SoItemDTO> soItems = new ArrayList<>();
        // 组织限购规则记录集合
        List<LimitRuleRecordDTO> limitRuleRecordList = new ArrayList<>();

        double orderAmount = 0d;
        double orderPayByCash = 0d;
        boolean unitFlag = false;
        boolean isCakeOrWorld = EmptyUtil.isNotEmpty(source) && (Objects.equals(source,ThirdConst.Source.CAKE) ||  Objects.equals(source,ThirdConst.Source.WORLD));
        // 通过puid查询sku,然后查到spu信息
        Long commodityTemplateId = 2L;
        if (puId != null && (!isJdType0)&& (!isQingMei) && (!isCakeOrWorld)) {
            commodityTemplateId = productFacade.queryCommodityTemplateIdByPuId(puId);
        }
        if (EmptyUtil.isNotEmpty(receiveAddressId) && receiveAddressId.equals(Long.valueOf(0))) {
            receiveAddressId = null;
        }
        ReceiverAddressDTO addr = null;
        StoreDTO storeById = storeFacade.findStoreById(storeId);
        //如果是直接下单,判断商品模板,如果是购物车下单,则一定是实物商品
        if (((commodityTemplateId.equals(2L)||commodityTemplateId.equals(7L))||type==1)&&storeById.getIsDetail() != 1 && !isQingMei) {
            if (receiveAddressId == null) {
                // 收货地址校验:实体商品
                return JsonResult.fail("请填写收货人信息");
            }
            addr = receiverAddressFacade.findReceiverAddressById(receiveAddressId);
            if (addr == null)
                return JsonResult.fail("收货地址不存在");

        } else {
            // 电子卡券类/充值类商品没有收货地址
            receiveAddressId = null;
        }
        BigDecimal limitFuBiPayAmount = BigDecimal.ZERO;
        if (type.intValue() == 0) {
            // 直接购买
            // 参数校验
            if (puId == null || num == null)
                return JsonResult.fail("参数为空");


            /*对pu进行加锁(同一个pu不同人进行购买时有锁限制)
             */
            boolean lock = true;
            try {
                lock=jedisUtil.lockWithParam(JedisUtil.PU_LOCK_KEY_PRE+puId,PU_LOCK_VALUE,JedisUtil.PU_LOCK_EXPIRE_TIME);
            } catch (InterruptedException e) {
                logger.info("当前获取pu的redis锁异常,puId="+puId);
                jedisUtil.delLock(JedisUtil.PU_LOCK_KEY_PRE+puId);
                e.printStackTrace();
            }
            if(!lock){
                logger.info("创建订单获取pu锁失败");
                return JsonResult.fail("当前网络繁忙,请稍后重试");
            }
            puIdList.add(puId);


            // 兑换卷购买su数量必须为1
            if (couponType != null && couponUnitId != null && couponType == 1 && num != 1)
                return JsonResult.fail("兑换数量必须是1");

            // 判断是否是话费充值
            if (commodityTemplateId != null && (commodityTemplateId.equals(4L)||commodityTemplateId.equals(9L))) {
                if (EmptyUtil.isEmpty(phone))
                    return JsonResult.fail("手机号码不能为空");
                if (!StringUtils.validMobile(phone))
                    return JsonResult.fail("请输入正确的手机号码");
                // 默认商品数量为1
                num = Integer.valueOf(1);
            }

            // 商品存在校验(商品和用户的权限已经校验)
            String channelCode = getChannelCode(createOrderRespVO.getSourceProduct());
            createOrderReqVO.setNum(num);
            createOrderReqVO.setEnterpriseId(enterpriseId);
            createOrderReqVO.setAddr(addr);
            createOrderReqVO.setPuId(puId);
            createOrderReqVO.setSource(createOrderRespVO.getSource());
            createOrderReqVO.setCommodityTemplateId(commodityTemplateId);
            createOrderReqVO.setProductObject(createOrderRespVO.getProductObject());

            JsonResult<CreateOrderRespVO> jrt = orderCreateFactory.getOrderCreateStrategy(channelCode).addItemsAndLimitRules(createOrderReqVO);
            if(Objects.isNull(jrt)){
                return JsonResult.fail("查找商品失败,请稍后重试");
            }
            if(jrt.getCode() !=0){
                return JsonResult.fail(jrt.getError());
            }
            CreateOrderRespVO createOrderRespVO1 = jrt.getData();
            limitRuleRecordList = createOrderRespVO1.getLimitRuleRecordList();
            soItems = createOrderRespVO1.getSoItems();
            orderAmount = createOrderRespVO1.getOrderAmount();
        } else if (isQingMei) {
            qingMeiOrderDTO= JSON.parseObject(thirdOrderJsonStr, QingMeiOrderDTO.class);
            qingMeiOrderDTO.setTotalShippingFee(BigDecimal.ZERO);
            JsonResult<CreateOrderRespVO> jrt = orderCreateFactory.getOrderCreateStrategy("qm").addItemsAndLimitRules(createOrderReqVO);
            CreateOrderRespVO createOrderRespVO1 = jrt.getData();
            limitRuleRecordList = createOrderRespVO1.getLimitRuleRecordList();
            soItems = createOrderRespVO1.getSoItems();
            orderAmount = createOrderRespVO1.getOrderAmount();
        } else {
            // 购物车下单
            if (couponType != null && couponType == 1) {
                return JsonResult.fail("不能直接从购物车兑换商品");
            }

            //
            // 使用固定数量的线程池，避免高并发导致服务器线程无限制的增长
            ThreadPoolExecutor executor = CommonThreadPoolExecutor.getInstance();
            // 主线程优先拿到最先完成的任务的返回值，而不管它们加入线程池的顺序。
            CompletionService<JsonResult<CreateOrderRespVO>> completionService = new ExecutorCompletionService<>(executor);
            List<Future<JsonResult<CreateOrderRespVO>>> results = new ArrayList<>();
            Future<JsonResult<CreateOrderRespVO>> future = null;


            for (Long ciid : cartItemIds_) {
                // 购物车存在校验
                CartItemDTO ci = cartItemFacade.findCartItemById(ciid);
                if (ci == null)
                    return JsonResult.fail("购物车项不存在");
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
                puIdList.add(puId_);
                int num_ = ci.getNum().intValue();


                //

                CreateOrderReqVO cartCreateOrderReqVO = new CreateOrderReqVO();
                cartCreateOrderReqVO.setPuId(puId_);
                cartCreateOrderReqVO.setType(type);
                cartCreateOrderReqVO.setChannelProductId(ci.getChannelProductId());
                cartCreateOrderReqVO.setEnterpriseId(enterpriseId);
                cartCreateOrderReqVO.setStoreId(storeId);
                cartCreateOrderReqVO.setCompanyDTO(companyDTO);
                cartCreateOrderReqVO.setCompanyId(companyId);
                cartCreateOrderReqVO.setUser(user);
                cartCreateOrderReqVO.setUserId(userId);
                cartCreateOrderReqVO.setUserName(userName);
                cartCreateOrderReqVO.setPhone(phone);
                cartCreateOrderReqVO.setPlatformId(platformId);
                cartCreateOrderReqVO.setNum(num_);
                cartCreateOrderReqVO.setEnterpriseId(enterpriseId);
                cartCreateOrderReqVO.setAddr(addr);
                cartCreateOrderReqVO.setSource(ci.getSource());
                cartCreateOrderReqVO.setCommodityTemplateId(commodityTemplateId);
                future = completionService.submit(new OrderCreateProductThread(cartCreateOrderReqVO));
                results.add(future);
            }
            for (Future<JsonResult<CreateOrderRespVO>> result : results) {
                try {
                    JsonResult<CreateOrderRespVO> rt =  result.get();
                    if(Objects.isNull(rt)){
                        return JsonResult.fail("购物车中的商品失败");
                    }
                    if(rt.getCode() !=0){
                        return JsonResult.fail(rt.getError());
                    }
                    CreateOrderRespVO createOrderRespVO1 = rt.getData();
                    if(!CollectionUtils.isEmpty(createOrderRespVO1.getLimitRuleRecordList())){
                        limitRuleRecordList.addAll(createOrderRespVO1.getLimitRuleRecordList());
                    }
                    if(!CollectionUtils.isEmpty(createOrderRespVO1.getSoItems())){
                        soItems.addAll(createOrderRespVO1.getSoItems());
                    }
                    if(Objects.nonNull(createOrderRespVO1.getOrderAmount())){
                        orderAmount = orderAmount+createOrderRespVO1.getOrderAmount();
                    }
                    if(Objects.nonNull(createOrderRespVO1.getOrderPayByCash())){
                        orderPayByCash = orderPayByCash+createOrderRespVO1.getOrderPayByCash();
                    }
                    if(Objects.nonNull(createOrderRespVO1.getLimitFuBiPayAmount())){
                        limitFuBiPayAmount = limitFuBiPayAmount.add(createOrderRespVO1.getLimitFuBiPayAmount());
                    }

                }catch (Exception e){
                    logger.error("查询购物车中的商品发生异常：{}",e);
                }
            }
        }
        if(EmptyUtil.isEmpty(limitRuleRecordList)){
            return JsonResult.fail("商品不能为空,限购规则不能为空");
        }
        // 根据pu商品集合及限购规则判断是否能购买、返回值不为空直接返回错误
        String isLimitBuy = productFacade.isLimitBuy(storeId, limitRuleRecordList, companyId, platformId, userId);
        if (isLimitBuy != null)
            return JsonResult.fail(isLimitBuy, BusinessExceptionConstant.LIMITBUYEXCEPTIONCODE);


        // 创建订单
        SoDTO sodto = new SoDTO();
        sodto.setUserId(userId);
        sodto.setOrderCode(orderCode);
        sodto.setPlatformId(platformId);
        sodto.setProductAmount(BigDecimal.valueOf(orderAmount));
        sodto.setReceiverAddressId(receiveAddressId);
        sodto.setDeliveryStatus(0);
        sodto.setInvoiceId(invoiceId);
        sodto.setOrderAmountPay(new BigDecimal(0));
        sodto.setOrderChannel(orderChannel);
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
        sodto.setStoreId(storeId);
        sodto.setOrderPromotionDiscount(new BigDecimal(0));
        sodto.setOrderStatus(0);
        sodto.setPaidOnlineThreshold(1);
        sodto.setPlatformId(platformId);
        sodto.setLimitFuBiPayAmount(limitFuBiPayAmount);
//        sodto.setRemark(remark);
        sodto.setUseFubi(useFubi);
        sodto.setCompanyId(user.getCompanyId());
        if (saleWay == 4 || saleWay == 6) {
            sodto.setSaleWay(7);
        } else {
            sodto.setSaleWay(saleWay);
        }
        List<CompanyConfigDTO> configs = companyConfig.queryCompanyconfigs(user.getCompanyId());
        if (orderAudit(user.getCompanyId(),configs)){
            //订单需要审核
            sodto.setAuditStatus(Constants.OrderAuditStatus.AUDITING);
        }
        BigDecimal totalDelivery = new BigDecimal(0L);
        Map<Long, BigDecimal> deliveryMap = new HashMap<>();
        if (deliveryPriceObj != null) {
            for (Entry<String, Object> deliveryObj : deliveryPriceObj.entrySet()){
                deliveryMap.put(Long.parseLong(deliveryObj.getKey()), new BigDecimal(deliveryObj.getValue().toString()));
                totalDelivery = totalDelivery.add(new BigDecimal(deliveryObj.getValue().toString()));
            }
        }
        if (isQingMei){
            sodto.setOrderDeliveryFee(qingMeiOrderDTO.getTotalShippingFee());
            sodto.setDeliveryFee(qingMeiOrderDTO.getTotalShippingFee());
            sodto.setOrderAmount(qingMeiOrderDTO.getTotalShippingFee().add(new BigDecimal(orderAmount)));
            sodto.setOrderAmountPay(qingMeiOrderDTO.getTotalAmount());
            sodto.setExt(qingMeiOrderDTO.getReturnUrl());
        }else {
            sodto.setOrderDeliveryFee(totalDelivery);
            sodto.setDeliveryFee(totalDelivery);
            sodto.setOrderAmount(totalDelivery.add(BigDecimal.valueOf(orderAmount)));
        }

        // 创建子订单
        List<SoChildDTO> soChildDTOList = new ArrayList<>();
        //7表示订单为预售订单.4,6表示商品是预售商品
        if ((saleWay == 6 || saleWay == 4||saleWay==7)&&EmptyUtil.isEmpty(puId)) {
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
        }
        else if (isQingMei) {
            int n=0;
            Map<String,String> childCodeMap=new HashMap<>();
            BigDecimal totalDiscount=BigDecimal.ZERO;
            for(QingMeiChildOrderDTO qmChild:qingMeiOrderDTO.getTradeList()){
                n++;
                SoChildDTO sc = new SoChildDTO();
                String childCode = orderCode + "-" + n;
                sc.setChildCode(childCode);
                sc.setPerformingParty(ThirdConst.Merchant.QM);
                sc.setAmount(qmChild.getOrderAmount().subtract(qmChild.getShippingFee()));
                sc.setOrdinaryDeliveryFee(qmChild.getShippingFee());
                sc.setDeliveryFee(qmChild.getShippingFee());
                totalDiscount=totalDiscount.add(qmChild.getDiscountAmount());
                sc.setNeedCountDeliveryFee(1);
                sc.setThirdpatyDiscountAmount(qmChild.getDiscountAmount());
                sc.setThirdpartySoChildPayAmount(qmChild.getOrderAmount());
                sc.setThirdpartySoChildStatus(Integer.valueOf(1));
                sc.setThirdpartySoChildId(Long.valueOf(qmChild.getOrderNo()));
                sc.setThirdpartySoChildCode(qmChild.getThirdTradeNo());
                sc.setSource(ThirdConst.Source.QM);
                sc.setExt(qmChild.getOrderUrl());
                childCodeMap.put(qmChild.getOrderNo(), childCode);
                soChildDTOList.add(sc);
            }
            soItems.forEach(item->{
                if (childCodeMap.containsKey(item.getThirdChildCode())){
                    item.setChildCode(childCodeMap.get(item.getThirdChildCode()));
                }
            });
            sodto.setOrderPromotionDiscount(totalDiscount);
        } else {
            //不是预售单和清美，则是其他的生成规则
            generateChildOrderManage.generateChildByMerchant(soChildDTOList,soItems,orderCode,remarkObj,addr,deliveryPriceObj);
            if(saleWay == 6 || saleWay == 4||saleWay==7){
                StandardUnitDTO suByPuId = getSUByPuId(puId);
                soChildDTOList.get(0).setPreSell(suByPuId.getPresellPeriod());//設置預售期
                soChildDTOList.get(0).setDeliverEndTime(DateUtils.nowToSomeDay(suByPuId.getPresellPeriod().intValue()));
            }

        }

        //计算子订单的金额
        //设置子订单的付款金额(商品-优惠(此时为0))

        //设置子订单的公共信息
        List<SoThirdpartyDTO> soThirdpartyDTOList = generateChildOrderManage.generateSoThirdpartyDTO(soChildDTOList,soItems,platformId,invoiceId,receiveAddressId,source,commodityTemplateId,phone,puType0,puId,isJdType0);

        // 创建订单设备信息
        SoDeviceDTO sd = new SoDeviceDTO();
        sd.setDeviceId(deviceId);
        sd.setInterId(interfaceId);
        sd.setIp(ip);
        sd.setOs(os);
        sd.setPhoneModel(phoneModel);
        sd.setPlatformId(platformId);
        sd.setVersionCode(versionCode);
        /*
         * facade方法统一提交事务 (创建订单,创建子订单,创建订单项,
         * 创建订单设备信息,冻结相应商品库存,删除购物购物车,更新发票,创建第三方子订单,优惠卷相关)
         * 此时sodto中
         */
        //订单创建成功,生成原始快照,快照
        if (receiveAddressId != null) {
            long addressId =addr.getId();
            logger.info("addressId =:{}",addressId);
            Map<String, Long> address =null;
            for (SoChildDTO soChildDTO : soChildDTOList) {
                address = this.createSnapsAddress(addr, soChildDTO.getChildCode());
                soChildDTO.setReceiverAddressId(address.get("snapAddressId"));
            }
            sodto.setReceiverAddressId(address.get("originalSnapAddressId"));
            //操作完成后再将收货id放置回去-因为上面操作存在浅拷贝，放置回去有风险
            addr.setId(addressId);
        }
        Long orderId = soFacade.normalOrderCreateNew(req,jedisUtil,addr,orderPayByCash,exchangeId,exchangeCouponUnitId,exchangeCouponBatchId,sodto, soChildDTOList, sd, soItems, cartItemIds_, unitFlag, soThirdpartyDTOList, userId, userName,
                ip, mac, limitRuleRecordList, couponType, couponUnitId, companyId, deliveryMap,configs);



        if(orderId !=null && orderId.intValue() >0){
            pushOrderManage.pushOrderInfo(orderId,null,null);
        }



        // 返回值
        Map<String, Object> data = new HashMap<>();
        data.put("orderCode", orderCode);
        data.put("saleWay", saleWay);
        return JsonResult.success(data);
    }

    private String checkParam(CompanyDTO companyDTO, Integer type, Integer orderChannel, String deviceId, String os, String phoneModel, String versionCode) {
        if(EmptyUtil.isEmpty(companyDTO)){
            return "所属公司不存在";
        }
        if (type == null) {
            return "未知下单类型";
        }
        if (orderChannel == null)
            return "渠道参数缺失";
        if (EmptyUtil.isBlank(deviceId)){
            return "设备编号缺失";
        }

        if (EmptyUtil.isBlank(os)){
            return "操作系统参数缺失";
        }
        if (EmptyUtil.isBlank(phoneModel)) {
            return "设备型号参数缺失";
        }
        if (EmptyUtil.isBlank(versionCode)) {
            return "应用版本号参数缺失";
        }
        return null;
    }

    private String getChannelCode(Integer source){
        String channelCode = ProductChannelCodeEnum.SELF.getCode();
        if(source !=null && Objects.equals(source,ThirdConst.Source.JD)){
            return ProductChannelCodeEnum.JD.getCode();
        }
        if(source !=null && Objects.equals(source,ThirdConst.Source.CAKE)){
            return ProductChannelCodeEnum.CAKE.getCode();
        }
        if(source !=null && Objects.equals(source,ThirdConst.Source.WORLD)){
            return ProductChannelCodeEnum.WORLD_BUY.getCode();
        }
        return channelCode;
    }


    @Override
    public JsonResult<Map<String, Object>> soChildBatchExport(List<Long> ids) {
        if (EmptyUtil.isEmpty(ids)){
            return JsonResult.fail("请选择子订单");
        }
        List<SoChildDTO> soChildDTOList =  soChildReadService.findSoChildByIdList(ids);
        return getSoChildExportUrl(soChildDTOList);
    }

    private JsonResult<Map<String, Object>> getSoChildExportUrl(List<SoChildDTO> soChildDTOList) {
        if(CollectionUtils.isEmpty(soChildDTOList)){
            return JsonResult.fail("无数据可导出");
        }
        List<OrderSortExportVO> list = new ArrayList<>();
        SoDTO soDTO =null;
        for (SoChildDTO soChildDTO: soChildDTOList){
            soDTO = new SoDTO();
            soDTO.setId(soChildDTO.getSoId());
            SoDTO so = soFacade.findSoById(soDTO);
            //无需校验子订单是否已经检出
            getSoChildDetailOrderExport(list,so,soChildDTO,false);

        }
        Workbook wb = new XSSFWorkbook();
        Sheet s2 = wb.createSheet("订单详情");
        String[] header2=new String[]{"订单号", "子订单编号", "快递单号","箱号","物流公司", "收货人", "收货地址", "联系电话", "运营方","商品名称","规格","供应商名称","供应商账号", "数量","商品税率", "结算单价","单价", "总价",
                "运费","退款数量","退款金额", "会员姓名", "会员编号", "会员公司", "用户邮箱", "是否开发票", "发票号","发票抬头", "发票内容", "发票类型", "抬头类型","下单时间","发货时间","商品编码","类目"};
        boolean exportC=EmptyUtil.isNotEmpty(RuntimeContext.cacheUser())&& RuntimeContext.cacheUser().getType()==3;
        if (exportC){
            header2=new String[]{"订单号", "子订单编号", "收货人", "收货地址", "联系电话","商品名称","规格", "数量", "总价",
                    "运费","下单时间"};
        }
        // 创建表头
        Row head2 = s2.createRow(0);
        for(int i=0;i<header2.length;i++){
            head2.createCell(i).setCellValue(header2[i]);
        }
        //订单物流详情写入
        if (exportC){
            completeOrderDetailSheetCExport(s2, list);
        }else {
            completeOrderDetailSheetExport(s2, list);
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            wb.write(bos);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.fail("导出失败!");
        }
        String upload = uploadService.fastDFSUpload(bos.toByteArray(), "xlsx");
        return JsonResult.success("url", upload);
    }

    private void getSoChildDetailOrderExport(List<OrderSortExportVO> voList ,SoDTO so,SoChildDTO child,Boolean isCheckSoDelivery){
        // 查询用户信息
        UserDTO user = userFacade.queryUserById(so.getUserId());
        Long companyId = user.getCompanyId();
        CompanyDTO company = userFacade.queryCompanyById(companyId);
        UserExtendDTO userExtend = userFacade.queryUserExtendById(so.getUserId());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //网店管家订单跳过
        if (Long.valueOf(3L).equals(child.getPerformingParty())) {
            //
            return;
        }
        if(isCheckSoDelivery){
            if (child.getDeliveryStatus().intValue() != 0) {
                throw new BusinessException("所选订单中存在已分拣的订单，请检查后重新选择");
            }
        }

        // 查询物流信息
        List<SoPackageDTO> packs = soPackageFacade.queryPackageBySoChildId(child.getId());
        Long receiverAddressId = child.getReceiverAddressId();
        ReceiverAddressDTO ra = null;
        if (receiverAddressId != null) {
            ra = receiverAddressFacade.findReceiverAddressById(receiverAddressId);
        }
        // 查询发票信息
        SoInvoiceDTO invoice = soInvoiceFacade.querySoInvoiceBySoChildId(child.getId());
        // 查询订单项
        List<SoItemDTO> items = soItemFacade.querySoItemsBySoChildId(child.getId());

        //查询所有箱子
        List<SoPackageBoxDTO> boxes = soPackageFacade.queryBoxListBySoChildId(child.getId());
        StringBuilder sb = new StringBuilder();
        if (boxes.size()>0) {
            for (SoPackageBoxDTO b : boxes) {
                sb.append(b.getSoBoxCode()).append("/");
            }
        }
        Map<Long,CommodityProductUnitDTO> selfProdMap=new HashMap<>();
        Map<Long,String> jdProdMap=new HashMap<>();
        String deliveryCode=null;
        String deliveryCompany=null;
        String deliveryDate=null;
        if (packs != null && packs.size()>0) {
            deliveryCode=packs.get(0).getDeliveryCode();
            deliveryCompany=packs.get(0).getDeliveryCompanyName();
            if (Objects.nonNull(packs.get(0).getDeliveryDate())){
                deliveryDate=DateUtils.formatDateTime(packs.get(0).getDeliveryDate());
            }
        }
        for (SoItemDTO item : items) {
            OrderSortExportVO vo = new OrderSortExportVO();
            vo.setOrderCode(so.getOrderCode());
            vo.setOrderCreateTime(EmptyUtil.isNotEmpty(so.getCreateTime())?sdf.format(so.getCreateTime()):"");
            vo.setChildCode(child.getChildCode());
            //bug2959start
            vo.setSoBoxCode(sb.toString());
            //bug2959end
            vo.setDeliveryCode(deliveryCode);
            vo.setDeliveryCompany(deliveryCompany);
            vo.setDeliveryDate(deliveryDate);
            if (ra != null) {
                vo.setReceiverName(ra.getGoodReceiverName());
                vo.setReceiverAddress(ra.getGoodReceiverProvince() + ra.getGoodReceiverCity()
                        + ra.getGoodReceiverCounty() + ra.getGoodReceiverAddress());
                vo.setReceiverMobile(ra.getGoodReceiverMobile());
            }
            vo.setPuName(item.getPuName());
            vo.setPuSpec(item.getPuName());
            vo.setPuCount(item.getPuCount());
            vo.setMerchantId(child.getPerformingParty());
            MerchantDTO merchant = merchantFacade.findMerchantById(child.getPerformingParty());
            logger.info("merchantId="+item.getMerchantId());
            logger.info("merchant="+JSON.toJSONString(merchant));
            vo.setMerchantName(merchant.getName());
            if(item.getSupplierId()!=null) {
                Enterprise supplier = userFacade.findSupplier(item.getSupplierId().intValue());
                vo.setSupplierName(supplier.getName());
                vo.setSupplierAccount(supplier.getAdminLoginName());
                if(item.getSnapshot()!=null) {
                    CommodityProductUnitDTO snap = JSON.parseObject(item.getSnapshot(), CommodityProductUnitDTO.class);
                    if(snap.getSupplierPrice()!=null) {
                        vo.setSupplierPrice(snap.getSupplierPrice());
                        vo.setProductCategory(snap.getProductCategory());
                        vo.setMerchantProductSerialNumber(snap.getMerchantProductSerialNumber());
                    }
                }
                if (EmptyUtil.isEmpty(vo.getMerchantProductSerialNumber())){
                    CommodityProductUnitDTO puType0 = productFacade.queryPuByIdAndSupplierPrice(item.getPuId());
                    if (Objects.nonNull(puType0)){
                        vo.setMerchantProductSerialNumber(puType0.getMerchantProductSerialNumber());
                        vo.setProductCategory(puType0.getProductCategory());
                    }
                }
            }else {
                if(item.getSource()!=null && item.getSource().intValue()==3) {
                    vo.setSupplierName("京东");
                    String categoryIds=null;
                    if(item.getSnapshot()!=null) {
                        JdProductDTO snap  = JSON.parseObject(item.getSnapshot(), JdProductDTO.class);
                        if(snap.getLedger()!=null) {
                            categoryIds=snap.getCategoryIds();
                            JSONObject ledgerObj = JSON.parseObject(snap.getLedger());
                            if(ledgerObj.containsKey("jdPrice")) {
                                vo.setSupplierPrice(ledgerObj.getBigDecimal("jdPrice"));
                            }
                        }
                    }
                    String categoryName=null;
                    if (jdProdMap.containsKey(item.getPuId())){
                        categoryName=jdProdMap.get(item.getPuId());
                    }else {
                        if (EmptyUtil.isEmpty(categoryIds)){
                            JSONObject detail = jdUtils.getDetail(jedisUtil, item.getPuId());
                            if (Objects.nonNull(detail) && EmptyUtil.isNotEmpty(detail.getString("category"))){
                                categoryIds = detail.getString("category");
                            }
                        }
                        if (EmptyUtil.isNotEmpty(categoryIds)){
                            String[] split = categoryIds.split(";");
                            Long catId = Long.valueOf(split[split.length - 1]);
                            categoryName = productFacade.getJdCategoryName(catId);
                            jdProdMap.put(item.getPuId(),categoryName);
                        }
                    }
                    vo.setProductCategory(categoryName);
                    vo.setMerchantProductSerialNumber(String.valueOf(item.getPuId()));
                }else if (EmptyUtil.isNotEmpty(item.getSource()) && Objects.equals(ThirdConst.Source.QM,item.getSource())){
                    vo.setSupplierName("清美");
                    if(EmptyUtil.isNotEmpty(item.getSnapshot())) {
                        QingMeiChildItemDTO snap  = JSON.parseObject(item.getSnapshot(), QingMeiChildItemDTO.class);
                        vo.setProductCategory(snap.getCategory());
                        vo.setMerchantProductSerialNumber(String.valueOf(item.getPuId()));
                    }
                }else if(EmptyUtil.isNotEmpty(item.getSource()) && Objects.equals(ThirdConst.Source.CAKE,item.getSource())){
                    vo.setSupplierName("蛋糕叔叔");
                    vo.setMerchantProductSerialNumber(String.valueOf(item.getPuId()));
                    if(EmptyUtil.isNotEmpty(item.getSnapshot())) {
                        CakeProductDetailDTO snap = JSON.parseObject(item.getSnapshot(), CakeProductDetailDTO.class);
                        CakeProductDetailSpecsDTO specsDTO = productFacade.getCakeProductSkuInfo(snap,String.valueOf(item.getPuId()));
                        if(specsDTO !=null){
                            vo.setSupplierPrice(new BigDecimal(specsDTO.getClearing_price()).setScale(2));
                        }
                    }


                }else if(EmptyUtil.isNotEmpty(item.getSource()) && Objects.equals(ThirdConst.Source.WORLD,item.getSource())){
                    vo.setSupplierName("全球购");
                    vo.setMerchantProductSerialNumber(String.valueOf(item.getPuId()));
                    if(EmptyUtil.isNotEmpty(item.getSnapshot())) {
                        ChannelProductDetailVO snap = JSON.parseObject(item.getSnapshot(), ChannelProductDetailVO.class);
                        List<ChannelProductBatchDTO> batchDTOList = snap.getBatchDTOList();
                        ChannelProductBatchDTO batchDTO = productFacade.getCurrBatch(item.getPuId()+"",batchDTOList);
                        if(batchDTO !=null){
                            vo.setSupplierPrice(batchDTO.getPriceSettleMent());
                        }
                    }
                }
            }
            vo.setPrice(item.getPrice());
            vo.setDeliveryFeeAver(EmptyUtil.isEmpty(item.getDeliveryFeeAver())?BigDecimal.ZERO:item.getDeliveryFeeAver());
            vo.setSum(item.getPrice().multiply(new BigDecimal(item.getPuCount()+"")));
            vo.setUserName(userExtend.getName());
            vo.setMemberCode(userExtend.getMemberCode());
            vo.setCompanyName(company.getCompanyName());
            vo.setUserEmail(user.getMail());
            vo.setRefundCount(item.getRefundCount());
            vo.setRefundAmount(com.egeo.components.utils.StringUtil.nullToZero(item.getRefundAmount()).add(com.egeo.components.utils.StringUtil.nullToZero(item.getRefundDeliveryFee())));
            if (EmptyUtil.isNotEmpty(item.getTaxRate())){
                vo.setTaxRate(item.getTaxRate().toPlainString()+"%");
            }
            if (invoice != null) {
                vo.setInvoiceExist("是");
                vo.setInvoiceTitle(invoice.getInvoiceTitleContent());
                vo.setInvoiceContent(invoice.getInvoiceContent());
                if(EmptyUtil.isNotEmpty(invoice.getInvoiceForm())){
                    vo.setInvoiceForm(invoice.getInvoiceForm().intValue() == 0 ? "纸质发票" : "电子发票");
                }
                if(EmptyUtil.isNotEmpty(invoice.getInvoiceTitleType())){
                    vo.setInvoiceTitleType(invoice.getInvoiceTitleType().intValue() == 0 ? "个人" : "公司");
                }
            } else {
                vo.setInvoiceExist("否");
            }
            voList.add(vo);
        }
    }

    @Override
    public JsonResult<Map<String, Object>> orderSearchExport(OrderSearchVO searchVO) {

        List<OrderListExportVO> voList = new ArrayList<>();
        List<OrderSortExportVO> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<SoDTO> soDTOList = queryBackStageSoList(searchVO);
        BuyCardItemRefundDTO buyCardItemRefundQueryDTO= new BuyCardItemRefundDTO();
        CardUseRecordDTO cardUseRecordQueryDTO = new CardUseRecordDTO();
        for (SoDTO so:soDTOList){

            if(EmptyUtil.isNotEmpty(so.getUserId())){
                UserDTO user = userFacade.queryUserById(so.getUserId());
                if(user == null){
                    continue;
                }
            }
            OrderListExportVO vo = new OrderListExportVO();
            vo.setOrderCode(so.getOrderCode());
            vo.setOrderStatus(OrderConstant.translate(so.getOrderStatus()));
            vo.setPayStatus(translateOrderPayStatus(so.getOrderPayStatus()));
            vo.setUseFubi(so.getUseFubi().intValue() == 0 ? "否" : "是");
            Integer cashPayType = so.getCashPayType();
            vo.setCashPayMethod(cashPayType == null ? "未选择" : tanslateOrderCashPayMethod(so.getCashPayType()));
            // 订单结算总额
            vo.setSum(so.getOrderAmount().subtract(so.getOrderPromotionDiscount()));
            vo.setPayByFubi(so.getOrderPaidByFubi());
            vo.setPayByCash(so.getOrderPaidByCash());
            vo.setPayByJiDian(so.getOrderPaidByJidian());
            vo.setPayByBuyCard(so.getOrderCardPaid());
            vo.setRefundCash(com.egeo.components.utils.StringUtil.nullToZero(so.getRefundCash()));
            vo.setRefundFubi(com.egeo.components.utils.StringUtil.nullToZero(so.getRefundFubi()));
            vo.setRefundJidian(com.egeo.components.utils.StringUtil.nullToZero(so.getRefundJidian()));
            vo.setRefundCard(com.egeo.components.utils.StringUtil.nullToZero(so.getRefundCard()));
            vo.setRefundSum(vo.getRefundCash().add(vo.getRefundFubi()).add(vo.getRefundJidian()).add(vo.getRefundCard()));
            vo.setCreateTime(sdf.format(so.getCreateTime()));
            if (EmptyUtil.isNotEmpty(so.getOrderPaymentConfirmDate())){
                vo.setPayTime(sdf.format(so.getOrderPaymentConfirmDate()));
            }

            cardUseRecordQueryDTO.setSoId(so.getId());
            List<CardUseRecordDTO>  cardUseRecordDTOS =  buyCardClient.findCardUseRecordAll(cardUseRecordQueryDTO);
            if(!CollectionUtils.isEmpty(cardUseRecordDTOS)){
                CardUseRecordDTO cardUseRecordDTO = cardUseRecordDTOS.get(0);
                vo.setBuyCardType(CardTypeEnum.getByTypeName(cardUseRecordDTO.getCardType()));
                vo.setSettleMethod(SettleMethodEnum.ofDescribe(cardUseRecordDTO.getSettleMethod()));
                StringBuffer sb = new StringBuffer();
                for (CardUseRecordDTO useRecordDTO : cardUseRecordDTOS) {
                    sb.append("卡号：").append(useRecordDTO.getCardNo());
                    sb.append("金额：").append(useRecordDTO.getUseAmount());
                    sb.append("，");
                }
                sb.deleteCharAt(sb.length()-1);
                vo.setBuyCardStr(sb.toString());
            }

            buyCardItemRefundQueryDTO.setSoId(so.getId());
            List<BuyCardItemRefundDTO> buyCardItemRefundDTOS = buyCardClient.findBuyCardItemRefund(buyCardItemRefundQueryDTO);
            if(!CollectionUtils.isEmpty(buyCardItemRefundDTOS)){
                StringBuffer sb = new StringBuffer();
                for (BuyCardItemRefundDTO buyCardItemRefundDTO : buyCardItemRefundDTOS) {
                    sb.append("卡号：").append(buyCardItemRefundDTO.getCardNo());
                    sb.append("金额：").append(buyCardItemRefundDTO.getRefundAmount());
                    sb.append("，");
                }
                sb.deleteCharAt(sb.length()-1);
                vo.setBuyCardRefundStr(sb.toString());
            }

            getSoDetailOrderExport(list,so,so.getId(),false);//无需校验子订单是否已经检出

            voList.add(vo);
        }
        Workbook wb = new XSSFWorkbook();
        Sheet s = wb.createSheet("订单清单");
        Sheet s2 = wb.createSheet("订单详情");
        String[] header = {"订单号", "订单状态", "支付状态", "订单结算总额", "饭卡支付金额","积点支付金额", "现金实付金额","卡劵支付金额","卡劵类型","卡劵结算方式","支付卡劵","退款金额", "饭卡退款金额","积点退款金额", "微信退款金额","卡劵退款金额","退款卡劵", "下单时间","支付时间"};
        String[] header2=new String[]{"订单号", "子订单编号", "快递单号","箱号","物流公司", "收货人", "收货地址", "联系电话", "运营方","商品名称","规格","供应商名称","供应商账号", "数量","商品税率", "结算单价","单价", "总价",
                "运费","退款数量","退款金额", "会员姓名", "会员编号", "会员公司", "用户邮箱", "是否开发票", "发票号","发票抬头", "发票内容", "发票类型", "抬头类型","下单时间","发货时间","商品编码","类目"};
        boolean exportC=EmptyUtil.isNotEmpty(RuntimeContext.cacheUser())&& RuntimeContext.cacheUser().getType()==3;
        if (exportC){
            header2=new String[]{"订单号", "子订单编号", "收货人", "收货地址", "联系电话","商品名称","规格", "数量", "总价",
                    "运费","下单时间"};
        }
        // 创建表头
        Row head = s.createRow(0);
        Row head2 = s2.createRow(0);
        for (int i = 0; i < header.length; i++) {
            head.createCell(i).setCellValue(header[i]);
        }
        for(int i=0;i<header2.length;i++){
            head2.createCell(i).setCellValue(header2[i]);
        }
        //订单信息写入
        completeOrderListSheet(s, voList);
        //订单物流详情写入
        if (exportC){
            completeOrderDetailSheetCExport(s2,list);
        }else {
            completeOrderDetailSheetExport(s2,list);
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            wb.write(bos);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.fail("导出失败!");
        }
        String upload = uploadService.fastDFSUpload(bos.toByteArray(), "xlsx");
        return JsonResult.success("url", upload);
    }

    public List<SoDTO> queryBackStageSoList(OrderSearchVO searchVO) {
        Pagination page = null;
        if (EmptyUtil.isBlank(searchVO.getOrderCode())) {
            searchVO.setOrderCode(null);
        }
        List<Long> userIds = null;

        if (EmptyUtil.isNotEmpty(searchVO.getKeyWords())) {
            searchVO.setOrderCode(searchVO.getKeyWords());

        }
        if (EmptyUtil.isBlank(searchVO.getEmail())) {
            searchVO.setEmail(null);
            userIds = null;
        } else {
            // 根据Email模糊查询用户
            List<UserDTO> users = userFacade.queryUsersByEmail(searchVO.getEmail());
            if (users.size() > 300) {
                throw new BusinessException("匹配到的用户量太大,将会引发数据库异常,请使用更加精确的条件进行查询");
            }
            userIds = new ArrayList<>();
            for (UserDTO u : users) {
                userIds.add(u.getId());
            }
        }
        //新增根据收货人手机号查询
        if(EmptyUtil.isNotEmpty(searchVO.getGoodReceiverMobile()) && EmptyUtil.isNotBlank(searchVO.getGoodReceiverMobile())){
            List<Long> userIdList = receiverAddressFacade.getUserIdListByReceiverAddressMobile(searchVO.getGoodReceiverMobile());
            if(EmptyUtil.isNotEmpty(userIdList) && userIdList.size() >300){
                throw new BusinessException("匹配到的用户量太大,将会引发数据库异常,请使用更加精确的条件进行查询");
            }
            if(EmptyUtil.isNotEmpty(userIdList)){
                if(userIds == null || userIds.size() ==0){
                    userIds = userIdList;
                }else{
                    userIds.addAll(userIdList);
                }
            }
        }

        List<Long> puIds = null;
        if (EmptyUtil.isBlank(searchVO.getPuName())) {
            searchVO.setPuName(null);
        } else {
            // 根据puName模糊查询商品
            List<CommodityProductUnitDTO> pus = productFacade.queryPuByName(searchVO.getPuName());
            if (pus.size() > 300) {
                throw new BusinessException("匹配到的商品量太大,将会引发数据库异常,请使用更加精确的条件进行查询");
            }
            puIds = new ArrayList<>();
            for (CommodityProductUnitDTO pu : pus) {
                puIds.add(pu.getId());
            }
        }
        Date startDateTime = null;
        Date endDateTime = null;
        if (Objects.nonNull(searchVO.getStartTime()))
            startDateTime = new Date(searchVO.getStartTime());
        if (Objects.nonNull(searchVO.getEndTime()))
            endDateTime = new Date(searchVO.getEndTime());
        // 屏蔽测试用户逻辑
        // 测试用户是公司id为1的用户
        // 在订单表中以公司id冗余形式体现
        if (Objects.isNull(searchVO.getShowTest())) {
            searchVO.setShowTest(false);
        }
        // 查询前检查所有List条件是否为0长度
        if (userIds != null && userIds.size() == 0) {
            userIds = null;
        }
        if (puIds != null && puIds.size() == 0) {
            puIds = null;
        }

        List<Long> testCompanyIds = new ArrayList<>();
        if (!searchVO.getShowTest()) {
            // 如果勾选展示测试数据
            CompanyDTO companyDto = new CompanyDTO();
            companyDto.setIsTest(Integer.valueOf(1));
            List<CompanyDTO> companyList = userFacade.findCompanyAll(companyDto);
            for (CompanyDTO companyDto_ : companyList) {
                testCompanyIds.add(companyDto_.getId());
            }
        }
        if (EmptyUtil.isEmpty(testCompanyIds)) {
            // 如果测试公司的id集合为空,则设置该id集合为null
            testCompanyIds = null;
        }
        //storeId为空时查询platformid为2的所有订单
        List<Long> companyIds = null;
        //限制公司
        Map<Long,String> companyNameMap = new HashMap<Long,String>();
        searchVO.setCompanyNameMap(companyNameMap);
        if(Objects.nonNull(RuntimeContext.cacheUser()) && Objects.nonNull(RuntimeContext.cacheUser().getType())) {
            if(Objects.equals(4,RuntimeContext.cacheUser().getType())||Objects.equals(3, RuntimeContext.cacheUser().getType())) {
                companyIds = new ArrayList<Long>();
                companyIds.add(RuntimeContext.cacheUser().getCompanyId());
                CompanyDTO company = userFacade.queryCompanyById(RuntimeContext.cacheUser().getCompanyId());
                companyNameMap.put( RuntimeContext.cacheUser().getCompanyId(), company.getCompanyName());
                testCompanyIds = null;
            }else if(Objects.equals(2,RuntimeContext.cacheUser().getType())) {
                testCompanyIds = null;
                if(Objects.isNull(searchVO.getCompanyId())) {
                    CompanyDTO dto = new CompanyDTO();
                    dto.setEnterpriseId(RuntimeContext.cacheUser().getEnterpriseId());
                    List<CompanyDTO> companys = userFacade.findCompanyAll(dto);
                    if(EmptyUtil.isNotEmpty(companys)) {
                        companyIds = new ArrayList<Long>();
                        for(CompanyDTO co : companys) {
                            companyNameMap.put(co.getId(), co.getCompanyName());
                            if(Objects.nonNull(co.getId())) {
                                if(!companyIds.contains(co.getId())) {
                                    companyIds.add(co.getId());
                                }
                            }

                        }
                    }else {
                        companyIds.add(-11l);
                    }
                }else {
                    CompanyDTO dto = new CompanyDTO();
                    dto.setEnterpriseId(RuntimeContext.cacheUser().getEnterpriseId());
                    List<CompanyDTO> companys = userFacade.findCompanyAll(dto);
                    companyIds = new ArrayList<Long>();
                    if(EmptyUtil.isNotEmpty(companys)) {
                        for(CompanyDTO co : companys) {
                            if(co.getId().equals(searchVO.getCompanyId())) {
                                if(!companyIds.contains(co.getId())) {
                                    companyIds.add(co.getId());
                                }
                            }
                        }
                        if(companyIds.size()==0) {
                            companyIds.add(-11l);
                        }
                    }else {
                        companyIds.add(-11l);
                    }
                }


            }else {
                testCompanyIds = null;
                companyIds = null;
                if (EmptyUtil.isNotEmpty(searchVO.getCompanyId())){
                    companyIds =new ArrayList<>();
                    companyIds.add(searchVO.getCompanyId());
                }
            }
        }else {
            return null;
        }
        return soFacade.queryBackStageSoList(searchVO.getStoreId(),searchVO.getOrderCode(),userIds,puIds,
                startDateTime,endDateTime,searchVO.getStatus(),searchVO.getOrderConfirmStatus(),searchVO.getOrderPayStatus(),
                searchVO.getPaymentType(),searchVO.getShowTest(),searchVO.getPlatformId(),page,searchVO.isRefundFlag(),
                testCompanyIds,companyIds,searchVO.getAuditStatus(),searchVO.getSoIds());
    }

    @Override
    public JsonResult<Map<String, Object>> soChildAllListToExport(Integer merchantId, String soChildCode, String mail,
                                                          String puName, Date soCreateTimeStart, Date soCreateTimeEnd,
                                                          Integer soType, Integer soChildDeliveryStatus, Integer soConfirmStatus,
                                                          Date sendTimeStart, Date sendTimeEnd, Boolean showTest, Pagination page,
                                                          Long platformId,Long supplierId,Integer orderPayStatus,Integer auditStatus) {

        if (EmptyUtil.isBlank(soChildCode)) {
            soChildCode = null;
        }
        //1.根据mail查询出用户
        List<Long> userIds = null;
        if (EmptyUtil.isBlank(mail)) {
            mail = null;
            userIds = null;
        } else {
            // 根据Email模糊查询用户
            List<UserDTO> users = userFacade.queryUsersByEmail(mail);
            if (users.size() > 300) {
                return JsonResult.fail("匹配到的用户量太大,将会引发数据库异常,请使用更加精确的条件进行查询");
            }
            userIds = new ArrayList<>();
            for (UserDTO u : users) {
                userIds.add(u.getId());
            }
        }
        //2.根据puname查询出所有的puid
        List<Long> puIds = null;
        if (EmptyUtil.isBlank(puName)) {
            puName = null;
        } else {
            // 根据puName模糊查询商品
            List<CommodityProductUnitDTO> pus = productFacade.queryPuByName(puName);
            if (pus.size() > 300) {
                return JsonResult.fail("匹配到的商品量太大,将会引发数据库异常,请使用更加精确的条件进行查询");
            }
            puIds = new ArrayList<>();
            for (CommodityProductUnitDTO pu : pus) {
                puIds.add(pu.getId());
            }
        }

        // 屏蔽测试用户逻辑
        // 测试用户是公司id为1的用户
        // 在订单表中以公司id冗余形式体现
        if (showTest == null) {
            showTest = false;
        }
        // 查询前检查所有List条件是否为0长度
        if (userIds != null && userIds.size() == 0) {
            userIds = null;
        }
        if (puIds != null && puIds.size() == 0) {
            puIds = null;
        }

        List<Long> testCompanyIds = new ArrayList<>();
        if (showTest) {
            // 如果勾选展示测试数据
            CompanyDTO companyDto = new CompanyDTO();
            companyDto.setIsTest(Integer.valueOf(1));
            List<CompanyDTO> companyList = userFacade.findCompanyAll(companyDto);
            for (CompanyDTO companyDto_ : companyList) {
                testCompanyIds.add(companyDto_.getId());
            }
        }
        if (EmptyUtil.isEmpty(testCompanyIds)) {
            // 如果测试公司的id集合为空,则设置该id集合为null
            testCompanyIds = null;
        }
        PageResult<SoChildListBackVO> pageResult = new PageResult<>();
        List<SoChildDTO> list ;
        if(supplierId==null) {
            list = soFacade.getSoChildAllListToExport( merchantId,soChildCode, userIds, puIds, soCreateTimeStart,
                    soCreateTimeEnd, soType, soChildDeliveryStatus, soConfirmStatus, sendTimeStart, sendTimeEnd, showTest, platformId, testCompanyIds, page,orderPayStatus,auditStatus);
        }else {
            list = soFacade.getSupplierSoChildAllListToExport( merchantId,soChildCode, userIds, puIds, soCreateTimeStart,
                    soCreateTimeEnd, soType, soChildDeliveryStatus, soConfirmStatus, sendTimeStart, sendTimeEnd, showTest, platformId, testCompanyIds, page,supplierId,orderPayStatus,auditStatus);
        }
        return getSoChildExportUrl(list);
    }



    @Override
    public JsonResult<BuyCardUseCheckRespVO> buyCardUseCheck(BuyCardUseCheckReqVO vo){
        if(EmptyUtil.isEmpty(vo.getOrderId()) && EmptyUtil.isEmpty(vo.getOrderCode())){
            return JsonResult.fail("订单信息不能为空");
        }
        SoDTO soDTO = soFacade.findSoDTO(vo.getOrderId(),vo.getOrderCode());
        if(soDTO == null){
            return JsonResult.fail("未找到订单信息");
        }
        //这几种类型无需卡劵
        if(vo.getPayType() ==null || vo.getPayType().intValue() ==1 || vo.getPayType().intValue() ==2){
            return JsonResult.success();
        }

        Integer cardType = (vo.getPayType().intValue()==3 || vo.getPayType().intValue()==6)? CardTypeEnum.ALL.getCardType() : CardTypeEnum.GIFT.getCardType();
        UserCardRecordDTO userCardRecordReqDTO = new UserCardRecordDTO();
        userCardRecordReqDTO.setCardType(cardType);
        userCardRecordReqDTO.setUserId(soDTO.getUserId());
        userCardRecordReqDTO.setUseStates(Arrays.asList(CardUseStateEnum.UN_USE.getUseState(),CardUseStateEnum.USE_ING.getUseState()));
        List<UserCardRecordDTO> userCardRecordDTOS =  buyCardClient.findUserCardRecordAll(userCardRecordReqDTO);
        if(CollectionUtils.isEmpty(userCardRecordDTOS)){
            return JsonResult.fail("余额不足(无卡)");
        }
        BigDecimal totalCardCash = userCardRecordDTOS.stream()
                .map(UserCardRecordDTO::getCardCash)    // 提取cardCash属性
                .filter(Objects::nonNull)               // 过滤null值（可选，根据需求决定）
                .reduce(BigDecimal.ZERO, BigDecimal::add); // 求和
        if(totalCardCash.compareTo(soDTO.getOrderAmount())<0){
            return JsonResult.fail("余额不足");
        }

        return ifGiftCard(userCardRecordDTOS,soDTO,vo);
    }

    private JsonResult<BuyCardUseCheckRespVO> ifGiftCard(List<UserCardRecordDTO> userCardRecordDTOS,SoDTO soDTO,BuyCardUseCheckReqVO vo){
        List<Integer> list =  Arrays.asList(4,7);
        if(!list.contains(vo.getPayType())){
            return JsonResult.success();
        }
        List<Long> combinationIds = new ArrayList<>();
        for (UserCardRecordDTO userCardRecordDTO : userCardRecordDTOS) {
            if(combinationIds.contains(userCardRecordDTO.getCombinationId())){
                continue;
            }
            combinationIds.add(userCardRecordDTO.getCombinationId());
        }
        List<SoItemDTO> itemDTOS =  soItemFacade.querySoItemListBySoId(soDTO.getId());
        if(CollectionUtils.isEmpty(itemDTOS)){
            return JsonResult.success();
        }
        List<CardCombinationVO> cardCombinations = new ArrayList<>();
        Map<Long,SoItemDTO> soItemDTOMap = new HashMap<>();
        for (SoItemDTO itemDTO : itemDTOS) {
            CardCombinationVO cardCombinationVO = new CardCombinationVO();
            cardCombinationVO.setItemId(itemDTO.getId());
            cardCombinationVO.setSource(itemDTO.getSource()==null?1:itemDTO.getSource());
            cardCombinationVO.setSuId(itemDTO.getPuId());
            cardCombinationVO.setSkuId(itemDTO.getExternalSkuId());
            cardCombinations.add(cardCombinationVO);
            soItemDTOMap.put(itemDTO.getId(),itemDTO);
        }
        CardCombinationCheckReqVO reqVO = new CardCombinationCheckReqVO();
        reqVO.setCombinationIds(combinationIds);
        reqVO.setCardCombinations(cardCombinations);
        CardCombinationCheckRespVO cardCombinationCheckRespVO = cardCombinationClient.checkCardCombination(reqVO);
        List<CardCombinationVO> cardCombinationsResp =  cardCombinationCheckRespVO.getCardCombinations();
        if(cardCombinationCheckRespVO !=null && cardCombinationsResp !=null || !EmptyUtil.isEmpty(cardCombinationsResp)){
            BuyCardUseCheckRespVO buyCardUseCheckRespVO = new BuyCardUseCheckRespVO();
            StringBuffer sb = new StringBuffer();
            for (CardCombinationVO cardCombinationVO : cardCombinationsResp) {
                SoItemDTO itemDTO = soItemDTOMap.get(cardCombinationVO.getItemId());
                sb.append(itemDTO.getPuName()).append("\n");
            }
            return JsonResult.fail("存在非礼品卡可购买商品:"+sb.toString());
        }
        return JsonResult.success();
    }

    @Override
    public JsonResult<Integer> buyCardUseCheckNum(BuyCardUseCheckReqVO vo){
        if(EmptyUtil.isEmpty(vo.getOrderId()) && EmptyUtil.isEmpty(vo.getOrderCode())){
            return JsonResult.fail("订单信息不能为空");
        }
        SoDTO soDTO = soFacade.findSoDTO(vo.getOrderId(),vo.getOrderCode());
        if(soDTO == null){
            return JsonResult.fail("未找到订单信息");
        }
        List<Integer> list = Arrays.asList(3,4,6,7);
        if(vo.getPayType() ==null || !list.contains(vo.getPayType())){
            return JsonResult.success(0);
        }
        Integer cardType = (vo.getPayType().intValue()==3 || vo.getPayType().intValue()==6)? CardTypeEnum.ALL.getCardType() : CardTypeEnum.GIFT.getCardType();
        UserCardRecordDTO userCardRecordReqDTO = new UserCardRecordDTO();
        userCardRecordReqDTO.setCardType(cardType);
        userCardRecordReqDTO.setUserId(soDTO.getUserId());
        userCardRecordReqDTO.setUseStates(Arrays.asList(CardUseStateEnum.UN_USE.getUseState(),CardUseStateEnum.USE_ING.getUseState()));
        List<UserCardRecordDTO> userCardRecordDTOS =  buyCardClient.findUserCardRecordAll(userCardRecordReqDTO);
        if(CollectionUtils.isEmpty(userCardRecordDTOS)){
            logger.info("余额不足(无卡)");
            return JsonResult.fail("余额不足(无卡)");
        }
        BigDecimal totalCardCash = userCardRecordDTOS.stream()
                .map(UserCardRecordDTO::getCardCash)    // 提取cardCash属性
                .filter(Objects::nonNull)               // 过滤null值（可选，根据需求决定）
                .reduce(BigDecimal.ZERO, BigDecimal::add); // 求和
        if(totalCardCash.compareTo(soDTO.getOrderAmount())<0){
            logger.info("订单号:{}余额不足,订单金额:{},卡余额:{}",soDTO.getOrderCode(),soDTO.getOrderAmount(),totalCardCash);
            return JsonResult.fail("余额不足");
        }
        if(vo.getPayType().intValue()==3 || vo.getPayType().intValue()==6){
            return JsonResult.success(userCardRecordDTOS.size());
        }

        return ifGiftCardNum(userCardRecordDTOS,soDTO,vo);
    }


    private JsonResult<Integer> ifGiftCardNum(List<UserCardRecordDTO> userCardRecordDTOS,SoDTO soDTO,BuyCardUseCheckReqVO vo){

        List<Long> combinationIds = new ArrayList<>();
        for (UserCardRecordDTO userCardRecordDTO : userCardRecordDTOS) {
            if(userCardRecordDTO.getCombinationId()==null){
                continue;
            }
           /* if(combinationIds.contains(userCardRecordDTO.getCombinationId())){
                continue;
            }*/
            combinationIds.add(userCardRecordDTO.getCombinationId());
        }
        List<SoItemDTO> itemDTOS =  soItemFacade.querySoItemListBySoId(soDTO.getId());
        if(CollectionUtils.isEmpty(itemDTOS)){
            return JsonResult.success(0);
        }
        List<CardCombinationVO> cardCombinations = new ArrayList<>();
        Map<Long,SoItemDTO> soItemDTOMap = new HashMap<>();
        for (SoItemDTO itemDTO : itemDTOS) {
            CardCombinationVO cardCombinationVO = new CardCombinationVO();
            cardCombinationVO.setItemId(itemDTO.getId());
            cardCombinationVO.setSource(itemDTO.getSource()==null?1:itemDTO.getSource());
            cardCombinationVO.setSuId(itemDTO.getPuId());
            cardCombinationVO.setSkuId(itemDTO.getExternalSkuId());
            cardCombinations.add(cardCombinationVO);
            soItemDTOMap.put(itemDTO.getId(),itemDTO);
        }
        CardCombinationCheckReqVO reqVO = new CardCombinationCheckReqVO();
        reqVO.setCombinationIds(combinationIds);
        reqVO.setCardCombinations(cardCombinations);
        CardCombinationCheckRespVO cardCombinationCheckRespVO = cardCombinationClient.checkCardCombination(reqVO);
        if(cardCombinationCheckRespVO ==null || cardCombinationCheckRespVO.getCardCombinations()==null){
            return JsonResult.success(0);
        }
        // 假设 userCardRecords 是原始列表
       /* Map<Long, List<UserCardRecordDTO>> groupedRecords = userCardRecordDTOS.stream()
                // 1. 过滤掉 combinationId 为 null 的记录
                .filter(record -> record.getCombinationId() != null && record.getCardCash() !=null && record.getCardCash().compareTo(BigDecimal.ZERO)==1)
                // 2. 按 combinationId 分组
                .collect(Collectors.groupingBy(UserCardRecordDTO::getCombinationId));*/
        List<CardCombinationVO> cardCombinationsList =  cardCombinationCheckRespVO.getCardCombinations();
       int num =  userCardRecordDTOS.size()-cardCombinationsList.size();
        return JsonResult.success(num);
    }

    @Override
    public JsonResult<CardPayCanUseRespVO> findCanUserCards(CardPayCanUseReqVO vo){
        if(EmptyUtil.isEmpty(vo.getOrderId()) && EmptyUtil.isEmpty(vo.getOrderCode())){
            return JsonResult.fail("订单信息不能为空");
        }
        SoDTO soDTO = soFacade.findSoDTO(vo.getOrderId(),vo.getOrderCode());
        if(soDTO == null){
            return JsonResult.fail("未找到订单信息");
        }
        List<UserCardRecordDTO> typeAll =  findCardTypeAll(soDTO.getUserId(),CardTypeEnum.ALL.getCardType());
        List<UserCardRecordDTO> typeGift =  findCardTypeAll(soDTO.getUserId(),CardTypeEnum.GIFT.getCardType());
        CardPayCanUseRespVO rtVO = new CardPayCanUseRespVO();
        rtVO.setWidelyAllCards(converters(typeAll));
        rtVO.setGiftCards(getGiftCard(soDTO,typeGift));
        return JsonResult.success(rtVO);
    }

    private List<UserCardDetailVO> getGiftCard(SoDTO soDTO,List<UserCardRecordDTO> typeGift){
        if(CollectionUtils.isEmpty(typeGift)){
            return null;
        }

        // 假设 userCardRecords 是原始列表
        Map<Long, List<UserCardRecordDTO>> groupedRecords = typeGift.stream()
                // 1. 过滤掉 combinationId 为 null 的记录
                .filter(record -> record.getCombinationId() != null && record.getCardCash() !=null && record.getCardCash().compareTo(BigDecimal.ZERO)==1)
                // 2. 按 combinationId 分组
                .collect(Collectors.groupingBy(UserCardRecordDTO::getCombinationId));

        List<Long> combinationIds = FHCollectionUtils.listToStrList(typeGift,UserCardRecordDTO::getCombinationId,e->e.getCombinationId() !=null && e.getCardCash().compareTo(BigDecimal.ZERO) ==1);

        if(EmptyUtil.isEmpty(groupedRecords) || CollectionUtils.isEmpty(combinationIds)){
            return null;
        }
        List<SoItemDTO> itemDTOS =  soItemFacade.querySoItemListBySoId(soDTO.getId());
        if(CollectionUtils.isEmpty(itemDTOS)){
            return null;
        }
        List<CardCombinationVO> cardCombinations = new ArrayList<>();
        for (SoItemDTO itemDTO : itemDTOS) {
            CardCombinationVO cardCombinationVO = new CardCombinationVO();
            cardCombinationVO.setItemId(itemDTO.getId());
            cardCombinationVO.setSource(itemDTO.getSource()==null?1:itemDTO.getSource());
            cardCombinationVO.setSuId(itemDTO.getPuId());
            cardCombinationVO.setSkuId(itemDTO.getExternalProductId());

            cardCombinations.add(cardCombinationVO);
        }
        UserCardCombinationReqVO reqVO = new UserCardCombinationReqVO();
        reqVO.setCombinationIds(combinationIds);
        reqVO.setCardCombinations(cardCombinations);
        UserCardCombinationRespVO respVO = cardCombinationClient.getCardCombination(reqVO);
        if(respVO ==null || CollectionUtils.isEmpty(respVO.getCombinationIds())){
            return null;
        }
        List<UserCardDetailVO> typeCard = new ArrayList<>();
        for (Long combinationId : respVO.getCombinationIds()) {
            if(groupedRecords !=null && !groupedRecords.containsKey(combinationId)){
                continue;
            }
            List<UserCardRecordDTO> dtos =  groupedRecords.get(combinationId);
            for (UserCardRecordDTO dto : dtos) {
                UserCardDetailVO src = new UserCardDetailVO();
                src.setCardId(dto.getId());
                src.setCardCash(dto.getCardCash());
                src.setCardName(dto.getCardName());
                src.setCardType(dto.getCardType());
                src.setExpireDateStart(dto.getExpireDateStart());
                src.setExpireDateEnd(dto.getExpireDateEnd());
                typeCard.add(src);
            }
        }
        return typeCard;
    }


    private List<UserCardDetailVO> converters(List<UserCardRecordDTO> list){
        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        List<UserCardDetailVO> typeAllUserCard = new ArrayList<>();
        for (UserCardRecordDTO src : list) {
            if(src.getCardCash().compareTo(BigDecimal.ZERO) <1){
                continue;
            }
            UserCardDetailVO tar = new UserCardDetailVO();
            tar.setCardCash(src.getCardCash());
            tar.setCardId(src.getId());
            tar.setCardName(src.getCardName());
            tar.setCardType(src.getCardType());
            tar.setExpireDateStart(src.getExpireDateStart());
            tar.setExpireDateEnd(src.getExpireDateEnd());
            typeAllUserCard.add(tar);
        }
        return typeAllUserCard;
    }

    private List<UserCardRecordDTO> findCardTypeAll(Long userId,Integer cardType){
        UserCardRecordDTO userCardRecordReqDTO = new UserCardRecordDTO();
        userCardRecordReqDTO.setCardType(cardType);
        userCardRecordReqDTO.setUserId(userId);
        userCardRecordReqDTO.setUseStates(Arrays.asList(CardUseStateEnum.UN_USE.getUseState(),CardUseStateEnum.USE_ING.getUseState()));
        List<UserCardRecordDTO> userCardRecordDTOS =  buyCardClient.findUserCardRecordAll(userCardRecordReqDTO);
        return userCardRecordDTOS;
    }

    @Override
    public BuyCardPayRespVO buyCardPay(BuyCardPayReqVO vo){
        BuyCardPayRespVO respVO = new BuyCardPayRespVO(false);
        if(EmptyUtil.isEmpty(vo.getCardIds()) && (EmptyUtil.isEmpty(vo.getOrderId()) || EmptyUtil.isEmpty(vo.getOrderCode()))){
            respVO.setMsg("使用卡劵时参数缺失");
            return respVO;
        }
        SoDTO soDTO = soFacade.findSoDTO(vo.getOrderId(),vo.getOrderCode());
        if(soDTO == null){
            respVO.setMsg("订单未找到");
            return respVO;
        }
        List<SoItemDTO> itemDTOS =  soItemFacade.querySoItemListBySoId(soDTO.getId());
        if(CollectionUtils.isEmpty(itemDTOS)){
            respVO.setMsg("订单商品未找到");
            return respVO;
        }

        List<Long> cardIds_ = JSONArray.parseArray(vo.getCardIds(), Long.class);
        UserCardRecordDTO userCardRecordReqDTO = new UserCardRecordDTO();
        userCardRecordReqDTO.setIds(cardIds_);
        List<UserCardRecordDTO> userCardRtDTOS =  buyCardClient.findUserCardRecordAll(userCardRecordReqDTO);
        List<UserCardRecordDTO> userCardRecordDTOS = userCardRtDTOS.stream()
                .sorted(Comparator.comparing(UserCardRecordDTO::getExpireDateEnd))
                .collect(Collectors.toList());
        if(CollectionUtils.isEmpty(userCardRecordDTOS)){
            respVO.setMsg("未找到可使用卡劵");
            logger.info("订单{},订单号:{}未找到可使用卡劵", soDTO.getId(),soDTO.getOrderCode());
            return respVO;
        }
        BigDecimal totalCardCash = userCardRecordDTOS.stream()
                .map(UserCardRecordDTO::getCardCash)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal orderAmount = soDTO.getOrderAmount().subtract(soDTO.getOrderPromotionDiscount());
        if(totalCardCash.compareTo(orderAmount)<0){
            respVO.setMsg("卡劵余额不足");
            logger.error("订单{},订单号:{},订单金额:{}实际金额:{}卡劵余额不足", soDTO.getId(),soDTO.getOrderCode(),orderAmount,totalCardCash);
            return respVO;
        }
        Integer cardType  = userCardRecordDTOS.get(0).getCardType();
        Map<Long,BigDecimal> useCardMap = new HashMap<>();
        List<UseBuyCardItemDetailReqVO> useDetails = new ArrayList<>();
        logger.info("订单号:{}卡劵支付类型:{}",soDTO.getOrderCode(),cardType.intValue());
        if(cardType.intValue() == CardTypeEnum.ALL.getCardType().intValue()){
            //直接使用
            for (SoItemDTO itemDTO : itemDTOS) {
                BigDecimal needAmount = itemDTO.getPrice().multiply(new BigDecimal(itemDTO.getPuCount()));
                for (UserCardRecordDTO userCardRecordDTO : userCardRecordDTOS) {
                    if(needAmount.compareTo(BigDecimal.ZERO) <=0){
                        break;
                    }
                    BigDecimal cash = userCardRecordDTO.getCardCash();
                    if(cash.compareTo(BigDecimal.ZERO)<=0){
                        continue;
                    }
                    //余额减扣当前已使用
                    BigDecimal supCash = cash.subtract(useCardMap.containsKey(userCardRecordDTO.getId())?useCardMap.get(userCardRecordDTO.getId()):BigDecimal.ZERO);
                    if(supCash.compareTo(BigDecimal.ZERO)<=0){
                        continue;
                    }
                    UseBuyCardItemDetailReqVO useBuyCardItemDetailReqVO = new UseBuyCardItemDetailReqVO();
                    BigDecimal usePayAmount = BigDecimal.ZERO;
                    //若余额大于所需金额则减扣所需金额
                    logger.info("订单号:{},可用余额:{},所需金额:{}判断结果:{}",soDTO.getOrderCode(),supCash,needAmount,supCash.compareTo(needAmount));
                    if(supCash.compareTo(needAmount)>=0){
                        // 优化后的合并逻辑
                        usePayAmount = needAmount;
                        needAmount = BigDecimal.ZERO;
                    }else{
                        //否则余额小于所需金额，减扣余额
                        usePayAmount = supCash;
                        needAmount = needAmount.subtract(usePayAmount);
                    }
                    logger.info("订单号：{},itemId:{}卡劵id:{},卡劵编号:{},付款金额:{}",soDTO.getOrderCode(),itemDTO.getId(),userCardRecordDTO.getId(),userCardRecordDTO.getCardNo(),usePayAmount);
                    useBuyCardItemDetailReqVO.setCardId(userCardRecordDTO.getId());
                    useBuyCardItemDetailReqVO.setUseAmount(usePayAmount);
                    useBuyCardItemDetailReqVO.setItemId(itemDTO.getId());
                    useBuyCardItemDetailReqVO.setChildId(itemDTO.getSoChildId());
                    useBuyCardItemDetailReqVO.setSoId(itemDTO.getSoId());
                    useBuyCardItemDetailReqVO.setOrderCode(soDTO.getOrderCode());
                    useBuyCardItemDetailReqVO.setUserId(soDTO.getUserId());
                    useDetails.add(useBuyCardItemDetailReqVO);
                    useCardMap.merge(userCardRecordDTO.getId(),usePayAmount,BigDecimal::add);
                }
            }
        }else{
            //验证使用
            List<CardCombinationVO> cardCombinations = null;
            CardCombinationVO cardCombinationVO =null;
            UserCardCombinationReqVO userCardCombinationReqVO = null;
            List<Long> cardCombinationIds = FHCollectionUtils.listToStrList(userCardRecordDTOS,UserCardRecordDTO::getCombinationId,e->e.getCombinationId() !=null);
            if(CollectionUtils.isEmpty(cardCombinationIds)){
                //throw new BusinessException("选择的礼品卡未找到需要限制的商品");
                respVO.setMsg("选择的礼品卡未找到需要限制的商品");
                return respVO;
            }
            for (SoItemDTO itemDTO : itemDTOS) {
                cardCombinations = new ArrayList<>();
                cardCombinationVO = new CardCombinationVO();
                cardCombinationVO.setSuId(itemDTO.getPuId());
                cardCombinationVO.setSource(itemDTO.getSource() == null ?1:itemDTO.getSource());
                cardCombinationVO.setSkuId(itemDTO.getExternalProductId());
                cardCombinations.add(cardCombinationVO);
                BigDecimal needAmount = itemDTO.getPrice().multiply(new BigDecimal(itemDTO.getPuCount()));
                for (UserCardRecordDTO userCardRecordDTO : userCardRecordDTOS) {
                    if(userCardRecordDTO.getCombinationId() == null){
                        continue;
                    }
                    if(needAmount.compareTo(BigDecimal.ZERO) <=0){
                        break;
                    }
                    BigDecimal cash = userCardRecordDTO.getCardCash();
                    if(cash.compareTo(BigDecimal.ZERO)<=0){
                        continue;
                    }
                    BigDecimal supCash = cash.subtract(useCardMap.containsKey(userCardRecordDTO.getId())?useCardMap.get(userCardRecordDTO.getId()):BigDecimal.ZERO);
                    if(supCash.compareTo(BigDecimal.ZERO) <=0){
                        continue;
                    }
                    userCardCombinationReqVO = new UserCardCombinationReqVO();
                    userCardCombinationReqVO.setCombinationIds(cardCombinationIds);
                    userCardCombinationReqVO.setCardCombinations(cardCombinations);
                    UserCardCombinationRespVO userCardCombinationRespVO = cardCombinationClient.getCardCombination(userCardCombinationReqVO);
                    if(userCardCombinationRespVO == null || CollectionUtils.isEmpty(userCardCombinationRespVO.getCombinationIds())){
                       // throw new BusinessException("存在商品不支持卡劵支付:"+itemDTO.getPuName());
                        respVO.setMsg("存在商品不支持卡劵支付:"+itemDTO.getPuName());
                        return respVO;
                    }
                    userCardCombinationReqVO.setCombinationIds(Arrays.asList(userCardRecordDTO.getCombinationId()));
                    userCardCombinationReqVO.setCardCombinations(cardCombinations);
                    userCardCombinationRespVO = cardCombinationClient.getCardCombination(userCardCombinationReqVO);
                    if(userCardCombinationRespVO == null || CollectionUtils.isEmpty(userCardCombinationRespVO.getCombinationIds())){
                        continue;
                    }
                    UseBuyCardItemDetailReqVO useBuyCardItemDetailReqVO = new UseBuyCardItemDetailReqVO();
                    BigDecimal usePayAmount = BigDecimal.ZERO;
                    //若余额大于所需金额则减扣所需金额
                    if(supCash.compareTo(needAmount)>=0){
                        // 优化后的合并逻辑
                        usePayAmount = needAmount;
                        needAmount = BigDecimal.ZERO;
                    }else{
                        //否则余额小于所需金额，减扣余额
                        usePayAmount = supCash;
                        needAmount = needAmount.subtract(usePayAmount);
                    }
                    logger.info("订单号：{},itemId:{}卡劵id:{},卡劵编号:{},付款金额:{}",soDTO.getOrderCode(),itemDTO.getId(),userCardRecordDTO.getId(),userCardRecordDTO.getCardNo(),usePayAmount);
                    useBuyCardItemDetailReqVO.setCardId(userCardRecordDTO.getId());
                    useBuyCardItemDetailReqVO.setUseAmount(usePayAmount);
                    useBuyCardItemDetailReqVO.setItemId(itemDTO.getId());
                    useBuyCardItemDetailReqVO.setChildId(itemDTO.getSoChildId());
                    useBuyCardItemDetailReqVO.setSoId(itemDTO.getSoId());
                    useBuyCardItemDetailReqVO.setOrderCode(soDTO.getOrderCode());
                    useBuyCardItemDetailReqVO.setUserId(soDTO.getUserId());
                    useDetails.add(useBuyCardItemDetailReqVO);
                    useCardMap.merge(userCardRecordDTO.getId(),usePayAmount,BigDecimal::add);
                }
            }

        }
        if(CollectionUtils.isEmpty(useCardMap)){
            respVO.setMsg("使用卡劵失败");
            logger.error("订单{},订单号:{}使用卡劵失败useCardMap为空", soDTO.getId(),soDTO.getOrderCode());
            return respVO;
        }
        BigDecimal acAmount = useCardMap.values().stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        if(acAmount.compareTo(orderAmount)<0){
            respVO.setMsg("实际找到卡劵余额不足");
            logger.error("订单{},订单号:{},订单金额:{}实际金额:{}实际找到卡劵余额不足", soDTO.getId(),soDTO.getOrderCode(),orderAmount,acAmount);
            logger.error("订单找到的卡信息:{}",JSON.toJSONString(useCardMap));
            return respVO;
        }
        UseCardReqVO useCardReqVO = new UseCardReqVO();
        useCardReqVO.setMap(useCardMap);
        useCardReqVO.setOrderId(soDTO.getId());
        useCardReqVO.setUseDetails(useDetails);
        useCardReqVO.setOrderCode(soDTO.getOrderCode());
        buyCardClient.useCard(useCardReqVO);
        List<BuyCardPayDetailRespVO> details = new ArrayList<>();
        for (UserCardRecordDTO userCardRecordDTO : userCardRecordDTOS) {
            if(!useCardMap.containsKey(userCardRecordDTO.getId())){
                continue;
            }
            BuyCardPayDetailRespVO buyCardPayDetailRespVO = new BuyCardPayDetailRespVO();
            buyCardPayDetailRespVO.setCardId(userCardRecordDTO.getId());
            buyCardPayDetailRespVO.setCardName(userCardRecordDTO.getCardName());
            buyCardPayDetailRespVO.setPayAmount(useCardMap.get(userCardRecordDTO.getId()));
            details.add(buyCardPayDetailRespVO);
        }
        respVO.setDetails(details);
        respVO.setMsg("操作完成");
        respVO.setResult(true);
        logger.info("订单{},订单号:{}卡劵使用完成", soDTO.getId(),soDTO.getOrderCode());
        return respVO;
    }

}
