package com.egeo.components.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.StringUtils;
import com.egeo.utils.http.HttpClientUtil;
import com.egeo.utils.log.XLogger;
import com.egeo.web.JsonResult;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.xml.bind.DatatypeConverter;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * @Description 蛋糕叔叔请求工具类
 * @Author lsl
 * @Date 2024/4/10 14:54
 * @Version V1.0
 **/
@Component
@ConditionalOnResource(
        resources = {"file:./config/CakeConfig.properties"}
)
@PropertySource(
        value = {"file:./config/CakeConfig.properties"},
        ignoreResourceNotFound = false,
        encoding = "UTF-8",
        name = "CakeConfig.properties"
)
public class CakeUtil {
    private static final XLogger logger = XLogger.getLogger(CakeUtil.class);

    private final String CHARSET_UTF_8="UTF-8";

    /**蛋糕叔叔第三方用户id**/
    @Value("${cake.uid}")
    private String uid;

    @Value("${cake.taxRate}")
    private String cakeTaxRate;

    /**蛋糕叔叔渠道号**/
    @Value("${cake.channelNo}")
    private String cakeChannelNo;

    /**蛋糕叔叔私钥**/
    @Value("${cake.privateKey}")
    private String privateKey;

    /**蛋糕叔叔基础url**/
    @Value("${cake.baseUrl}")
    private String cakeBaseUrl;

    /**平台与渠道用户关联:第三方用户登录接口**/
    @Value("${cake.userLogin}")
    private String userLogin;

    /**订单列表**/
    @Value("${cake.getOrderLists}")
    private String getOrderLists;

    /**订单详情接口**/
    @Value("${cake.orderDetail}")
    private String orderDetail;

    /**查看订单物流接口（即将弃用） POST/GET**/
    @Value("${cake.orderExpress}")
    private String orderExpress;

    /**订单状态查询接口 POST/GET POST/GET**/
    @Value("${cake.getOrderStatus}")
    private String getOrderStatus;

    /**提交订单接口 POST【multipart/form-data】**/
    @Value("${cake.submitOrder}")
    private String submitOrder;

    /**订单支付回调接口 POST【application/json】**/
    @Value("${cake.orderPayResult}")
    private String orderPayResult;

    /**第三方渠道订单接口/渠道订单列表接口 POST/GET**/
    @Value("${cake.getChannelOrderLists}")
    private String getChannelOrderLists;

    /**渠道预付款金额查询接口 POST/GET**/
    @Value("${cake.getChannelOrderMoney}")
    private String getChannelOrderMoney;

    /**订单渠道预付款详情 POST/GET**/
    @Value("${cake.orderDiscountInfo}")
    private String orderDiscountInfo;

    /**（新）订单列表接口V2 GET**/
    @Value("${cake.orderList2}")
    private String orderList2;

    /**确认收货接口**/
    @Value("${cake.confirmReceipt}")
    private String confirmReceipt;

    /**（配送规则接口（最新版） POST【multipart/form-data】**/
    @Value("${cake.getDistributionRules}")
    private String getDistributionRules;

    /**商品列表**/
    @Value("${cake.getProductHotList}")
    private String getProductHotList;

    /**商品详情 POST/GET**/
    @Value("${cake.getProductDetail}")
    private String getProductDetail;

    /**商品状态查询接口 POST/GET**/
    @Value("${cake.getProductStatus}")
    private String getProductStatus;

    /**规格状态查询 POST/GET**/
    @Value("${cake.getProductSpecsStatus}")
    private String getProductSpecsStatus;

    /**商品可售卖城市接口 POST/GET**/
    @Value("${cake.getProductCities}")
    private String getProductCities;

    /**商品配送范围,该接口用于绘制地址 GET**/
    @Value("${cake.getRule}")
    private String getRule;

    /**批量获取配送规则id接口 POST【application/json】**/
    @Value("${cake.getRuleIds}")
    private String getRuleIds;



    /**城市列表**/
    @Value("${cake.getCitiesList}")
    private String getCitiesList;

    /**根据城市名称获取城市id接口 GET/POST**/
    @Value("${cake.getCityId}")
    private String getCityId;

    /**根据城市id获取区县列表接口 GET/POST**/
    @Value("${cake.getCityArea}")
    private String getCityArea;

    /**地址添加、修改接口 GET/POST**/
    @Value("${cake.oprateAddr}")
    private String oprateAddr;

    /**地址删除接口 GET/POST**/
    @Value("${cake.delAddr}")
    private String delAddr;

    /**地址列表接口 GET/POST**/
    @Value("${cake.getAddrList}")
    private String getAddrList;

    /**门店列表接口 GET/POST**/
    @Value("${cake.getShopList}")
    private String getShopList;

    /**品牌详情接口 GET/POST**/
    @Value("${cake.getBrandInfo}")
    private String getBrandInfo;

    /**品牌列表与其开通城市接口 GET/POST**/
    //@Value("${cake.brandCityList}")
    private String brandCityList="/dsapi/brand/brand_city_list";

    private String getCatsInfo="/dsapi/product/get_cats_info";

    public  String CODE_KEY="code";
    public  String CODE_SUCCESS="200";
    public  String MSG_KEY="msg";
    public  String DATA_KEY="data";


    public CakeUtil(){

    }

//    public static void main(String[] args) {
//        CakeUtil cakeUtil = new CakeUtil();
        //JSONObject jsonObject = cakeUtil.userLogin("zhang123");
        //JSONObject jsonObject = cakeUtil.getOrderLists("92882334","1");
        //JSONObject jsonObject = cakeUtil.orderExpress("198988272727123");
        //JSONObject jsonObject = cakeUtil.orderDetail("198988272727123",null);
        //JSONObject jsonObject = cakeUtil.submitOrder(getSubmitOrderDemo());
        //JSONObject jsonObject = cakeUtil.orderDiscountInfo("2024-03-01","2024-03-30","0",null,null);
        //JSONObject jsonObject = cakeUtil.orderListsV2("2024-04-01","2024-04-12");
        //JSONObject jsonObject = cakeUtil.confirmReceipt("1",null);
        //JSONObject jsonObject = cakeUtil.getDistributionRules("3",null,"2304,2305","1,2");
        //JSONObject jsonObject = cakeUtil.getProductHotList(cakeUtil.buildProductHotListParam(null,1,null,1,null,null,null,1,1));
        //JSONObject jsonObject = cakeUtil.getProductDetail("1323189","81","92882334");
        //JSONObject jsonObject = cakeUtil.getProductStatus("1323189");
        //JSONObject jsonObject = cakeUtil.getProductSpecsStatus("1184141");
        //JSONObject jsonObject = cakeUtil.getProductCities("102420","1323189");
        //JSONObject jsonObject = cakeUtil.getRule("81","102420","1323189");
        //JSONObject jsonObject = cakeUtil.getRuleIds(getProductDemo());
        //JSONObject jsonObject = cakeUtil.getCityAddress(null);
        //JSONObject jsonObject = cakeUtil.getCityId("安庆市");
        //JSONObject jsonObject = cakeUtil.getCityArea("179");
        //JSONObject jsonObject = cakeUtil.oprateAddr("8408762","北京市","海淀区","玉泉路3号","22.733206652813","114.38496357799","张三","18909897823",null,null,null,null);
        //JSONObject jsonObject = cakeUtil.delAddr("1571668","8408762");
        //JSONObject jsonObject = cakeUtil.getAddrList("8408762");
        //JSONObject jsonObject = cakeUtil.getShopList("81","164589");
        //JSONObject jsonObject = cakeUtil.getBrandInfo("164589");
//        JSONObject jsonObject = cakeUtil.brandCityList();
//        System.out.println(JSONObject.toJSONString(jsonObject));
//    }

    /**
     * @Description 商品分类
     **/
    public  JSONObject getCatsInfo(){
        Map<String, String> requestParam = getRequestParam(null);
        String result = doPost(this.getCatsInfo, requestParam);
        return getResultJsonObject(result);
    }

    private static String getProductDemo(){
        JSONArray array = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("product_id","1305010");
        jsonObject.put("city_id","2");
        array.add(jsonObject);
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("product_id","1325102");
        jsonObject2.put("city_id","2");
        array.add(jsonObject2);
        return array.toString();
    }

    private static String getSubmitOrderDemo(){
        String str = "{\n" +
                "    \"out_order_no\":\"20022610311234561\",\n" +
                "    \"addr\":{\n" +
                "        \"id\":\"839296\",\n" +
                "        \"name\":\"张生\",\n" +
                "        \"phone\":\"180322*****\",\n" +
                "        \"detail\":\"广东省深圳市南山区大冲\"\n" +
                "    },\n" +
                "    \"rule_ids\":\"4437\",\n" +
                "    \"spec_ids\":\"9509\",\n" +
                "    \"quantitys\":\"3\",\n" +
                "    \"tastes_name\":\"冰淇淋\"," +
                "     \"group\":{\n" +
                "        \"4437\":{\n" +
                "            \"ship_type\":\"same\",\n" +
                "            \"ship_date\":false,\n" +
                "            \"ship_time_text\":\"下单后24小时发货，请耐心等候\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"pay_type\":\"channel_no的值\",\n" +
                "    \"buyer_phone\":\"180322*****\",\n" +
                "    \"user_id\":\"8290532\",\n" +
                "    \"city_id\":\"112\",\n" +
                "    \"buyer_msg\":\"请尽快送达\"\n" +
                "}";
        return str;
    }

    /**
     * @Description 平台与渠道用户关联,第三方用户登录接口
     **/
    public JSONObject userLogin(String userId){
        Map<String,String> param = new HashMap<>(1);
        param.put("uid",userId);
        Map<String, String> requestParam = getRequestParam(param);
        String result = doPost(this.userLogin, requestParam);
        return getResultJsonObject(result);
    }


    /**
     * @Description 订单列表接口
     * @Param userId 蛋叔用户id
     * @Param status 订单类型：1-全部，2-待付款，3-待收货
     **/
    public JSONObject getOrderLists(String userId,String status){
        Map<String,String> param = new HashMap<>(1);
        param.put("user_id",userId);
        param.put("status",status);
        Map<String, String> requestParam = getRequestParam(param);
        String result = doPost(this.getOrderLists, requestParam);
        return getResultJsonObject(result);
    }

    /**
     * @Description 查看订单物流接口（即将弃用）
     **/
    public JSONObject orderExpress(String orderNo){
        Map<String, String> param = new HashMap<>(1);
        param.put("order_no",orderNo);
        Map<String, String> requestParam = getRequestParam(param);
        String result = doPost(this.orderExpress, requestParam);
        return getResultJsonObject(result);
    }


    /**
     * @Description 订单详情接口
     **/
    public JSONObject orderDetail(String orderNo,String outOrderNo){
        Map<String,String> param = new HashMap<>();
        putToMap(param,"order_no",orderNo);
        putToMap(param,"out_order_no",outOrderNo);
        Map<String, String> requestParam = getRequestParam(param);
        String result = doPost(this.orderDetail, requestParam);
        return getResultJsonObject(result);
    }


    /**
     * @Description 订单状态查询接口
     * status 订单状态：0-待确认，1-已确认，2-已完成，3-已取消
     **/
    public JSONObject getOrderStatus(String status){
        Map<String,String> param = new HashMap<>(1);
        param.put("status",status);
        Map<String, String> requestParam = getRequestParam(param);
        String result = doPost(this.getOrderStatus, requestParam);
        return getResultJsonObject(result);
    }

    /**
     * @Description 订单详情接口的构建参数
     * @Param orderData json格式数据:
     * 快递配送格式数据，具体参照文档
     * {
     *     "out_order_no":"20022610311234561",
     *     "addr":{
     *         "id":"839296",
     *         "name":"张生",
     *         "phone":"180322*****",
     *         "detail":"广东省深圳市南山区大冲"
     *     },
     *     "rule_ids":"4437",
     *     "spec_ids":"9509",
     *     "quantitys":"3",
     *     "tastes_name":"冰淇淋",
     *      "group":{
     *         "4437":{
     *             "ship_type":"same",
     *             "ship_date":false,
     *             "ship_time_text":"下单后24小时发货，请耐心等候"
     *         }
     *     },
     *     "pay_type":"channel_no的值",
     *     "buyer_phone":"180322*****",
     *     "user_id":"8290532",
     *     "city_id":"112",
     *     "buyer_msg":"请尽快送达"
     * }
     **/
    /**
     * @Description
     *  1、提交订单接口 POST【multipart/form-data】
     *  2、提交订单共有4种方式：快递配送、门店自提、商家自配送、相同配规合并下单，详细请见order_data参数示例
     *
     **/
    public JSONObject submitOrder(String orderData){
        Map<String,String> param = new HashMap<>(1);
        param.put("order_data",orderData);
        Map<String, String> requestParam = getRequestParam(param);
        String mapString =getUrlParam(requestParam);
        //String result = doPost(this.submitOrder, requestParam);
        String result =HttpService.sendPost(this.cakeBaseUrl+this.submitOrder,mapString);
        return getResultJsonObject(result);
    }




    /**
     * @Description 订单支付回调接口 POST【application/json】
     * @Param resultStatus
     * @Param orderSn
     * @Param outOrderSn
     * @Param orderPrice
     * @Param transactionSn
     * @Param channelNo
     **/
    public JSONObject orderPayResult(String resultStatus,String orderSn,String outOrderSn,String orderPrice,String transactionSn){
        Map<String,String> param = buildOrderPayResultParam(resultStatus,orderSn,outOrderSn,orderPrice,transactionSn);
        Map<String, String> requestParam = getRequestParam(param);
        String paramString = JsonUtils.objectToJson(requestParam);
        String result = doPostByJson(this.orderPayResult,paramString);
        return getResultJsonObject(result);
    }



    /**
     * @Description 订单支付回调接口
     **/
    private  Map<String, String> buildOrderPayResultParam(String resultStatus,String orderSn,String outOrderSn,String orderPrice,String transactionSn){
        Map<String,String> param = new HashMap<>();
        putToMap(param,"result_status",resultStatus);
        putToMap(param,"order_sn",orderSn);
        putToMap(param,"out_order_sn",outOrderSn);
        putToMap(param,"order_price",orderPrice);
        putToMap(param,"transaction_sn",transactionSn);
        return param;
    }

    /**
     * @Description
     *  第三方渠道订单接口 POST/GET
     * @Param uid 否 第三方渠道用户id
     * @Param createdAt 否 订单创建时间戳
     * @Param updatedAt 否 订单变更时间戳
     * @Param page 	否 当前页数
     * @Param size 否 每页显示的条数，默认20
     **/
    public JSONObject getChannelOrderLists(String uid,String createdAt,String updatedAt,Integer pageNum,Integer pageSize){
        Map<String,String> param = buildChannelOrderListsParam(uid,createdAt,updatedAt,pageNum,pageSize);
        Map<String, String> requestParam = getRequestParam(param);
        String result = doPost(this.getChannelOrderLists, requestParam);
        return getResultJsonObject(result);
    }

    /**
     * @Description 第三方渠道订单接口构建参数
     * @return
     **/
    private Map<String, String> buildChannelOrderListsParam(String uid,String createdAt,String updatedAt,Integer pageNum,Integer pageSize){
        Map<String,String> param = new HashMap<>();
        putToMap(param,"uid",uid);
        putToMap(param,"created_at",createdAt);
        putToMap(param,"updated_at",updatedAt);
        putToMap(param,"page",pageNum);
        putToMap(param,"size",pageSize);
        return param;
    }

    /**
     * @Description
     *  渠道预付款金额查询接口 POST/GET
     **/
    public JSONObject getChannelOrderMoney(Map<String, String> param){
        Map<String, String> requestParam = getRequestParam(param);
        String result = doPost(this.getChannelOrderMoney, requestParam);
        return getResultJsonObject(result);
    }

    /**
     * @Description 订单渠道预付款详情    POST/GET
     * @Param startTime 	开始时间 	2020-04-01
     * @Param endTime 	结束时间 	2020-04-16
     * @Param type 金额变动类型：0全部 1充值 2消费 3退款 默认全部
     * @Param orderNo 蛋糕叔叔订单号（查询多个，订单号中间以英文逗号分割）
     * @Param outOrderNo 渠道方订单号（查询多个，订单号中间以英文逗号分割）
     **/
    public JSONObject orderDiscountInfo(String startTime,String endTime,String type,String orderNo,String outOrderNo){
        Map<String, String> param = buildOrderDiscountInfoParam(startTime,endTime,type,orderNo,outOrderNo);
        Map<String, String> requestParam = getRequestParam(param);
        String result = doPost(this.orderDiscountInfo, requestParam);
        return getResultJsonObject(result);
    }

   /**
    * @Description 订单渠道预付款详情构建参数
    * @Param startTime 	开始时间 	2020-04-01
    * @Param endTime 	结束时间 	2020-04-16
    * @Param type 金额变动类型：0全部 1充值 2消费 3退款 默认全部
    * @Param orderNo 蛋糕叔叔订单号（查询多个，订单号中间以英文逗号分割）
    * @Param outOrderNo 渠道方订单号（查询多个，订单号中间以英文逗号分割）
    * @return
    * @return java.util.Map<java.lang.String,java.lang.String>
    **/
    private Map<String, String> buildOrderDiscountInfoParam(String startTime,String endTime,String type,String orderNo,String outOrderNo){
        Map<String, String> requestParam = new HashMap<>();
        putToMap(requestParam,"channel_no",this.cakeChannelNo);
        putToMap(requestParam,"start_time",startTime);
        putToMap(requestParam,"end_time",endTime);
        putToMap(requestParam,"type",type);
        putToMap(requestParam,"order_no",orderNo);
        putToMap(requestParam,"out_order_no",outOrderNo);
        return requestParam;
    }


    /**
     * @Description
     *  (新)订单列表接口V2    GET
     **/
    public JSONObject orderListsV2(String startTime,String endTime){
        Map<String, String> param = new HashMap<>();
        putToMap(param,"start_time",startTime);
        putToMap(param,"end_time",endTime);
        Map<String, String> requestParam = getRequestParam(param);
        String result = doGet(this.orderList2, requestParam);
        return getResultJsonObject(result);
    }

    /**
     * @Description 确认收货接口
     * 请求确认收货接口需要满足以下条件：
     *      商家配送/门店自提：订单状态已分配并且已到配送日期
     *      快递配送：订单状态已分配并且已上传物流信息
     **/
    public JSONObject confirmReceipt(String orderNo,String outOrderNo){
        Map<String, String> param = new HashMap<>();
        putToMap(param,"order_no",orderNo);
        putToMap(param,"out_order_no",outOrderNo);
        Map<String, String> requestParam = getRequestParam(param);
        String result = doPost(this.confirmReceipt, requestParam);
        return getResultJsonObject(result);
    }


    /**
     * @Description
     *  配送规则接口（最新版）
     *  用于下单前对用户地址进行校验，判断是否可配送
     *  获取运费
     * @Param cityId 是  城市id
     * @Param addrId 否  	收货地址id (提交订单前访问此接口此参数为必填,不填的话只是校验当前城市是否有可配送区域)
     * @Param specId 是  商品规格id，多个用“,”隔开
     * @Param quantitys 是  购买数量，多个用“,”隔开
     **/
    public JSONObject getDistributionRules(String cityId,String addrId,String specId,String quantitys){
        Map<String, String> param = buildConfirmReceiptParam(cityId,addrId,specId,quantitys);
        Map<String, String> requestParam = getRequestParam(param);
        String result = doPost(this.getDistributionRules, requestParam);
        return getResultJsonObject(result);
    }

    /**
     * @Description 配送规则接口（最新版）构建参数
     **/
    private Map<String, String> buildConfirmReceiptParam(String cityId,String addrId,String specId,String quantitys){
        Map<String, String> requestParam = new HashMap<>();
        putToMap(requestParam,"city_id",cityId);
        putToMap(requestParam,"addr_id",addrId);
        putToMap(requestParam,"spec_id",specId);
        putToMap(requestParam,"quantitys",quantitys);
        return requestParam;
    }


    /**
     * @Description 商品详情接口
     **/
    public JSONObject getProductDetail(String productId,String cityId,String userId){
        Map<String, String> param = buildProductDetailParam(productId,cityId,userId);
        Map<String, String> requestParam = getRequestParam(param);
        String result = doPost(this.getProductDetail, requestParam);
        return getResultJsonObject(result);
    }

    /**
     * @Description 商品详情接口 构建参数
     **/
    private Map<String, String> buildProductDetailParam(String productId,String cityId,String userId){
        Map<String, String> requestParam = new HashMap<>();
        putToMap(requestParam,"product_id",productId);
        putToMap(requestParam,"city_id",cityId);
        putToMap(requestParam,"user_id",userId);
        return requestParam;
    }

    /**
     * @Description 构建商品列表接口查询参数
     * @Param
     * cityId 否    int	2	城市id，比如北京城市id是2,有值的话提取当前城市商品,不传提取所有商品
     * productType	否	int	1	商品类型，1是蛋糕，2是零食，3是鲜花
     * searchTitle	否	string	生日	商品关键词搜索
     * sortPriceType	是	int	1	商品价格排序，1是按价格倒序 ，2是按价格升序
     * startTime	否	string	2021-01-01	开始时间(查询商品最后更新时间,第一次全量查询商品信息时候和end_time都不必传参)
     * endTime	否	string	2021-01-02	结束时间
     * brandId	否	int	47	品牌ID,可根据此条件进行筛选
     * pageNum	是	int	1	页数
     * pageSize
     **/
    private  Map<String,String> buildProductHotListParam(String cityId,Integer productType,String searchTitle,Integer sortPriceType,String startTime,String endTime,String brandId,Integer pageNum,Integer pageSize){
        Map<String, String> param = Maps.newHashMap();
        putToMap(param,"city_id",cityId);
        putToMap(param,"product_type",productType);
        putToMap(param,"search_title",searchTitle);
        putToMap(param,"sort_price_type",sortPriceType);
        putToMap(param,"start_time",startTime);
        putToMap(param,"end_time",endTime);
        putToMap(param,"brand_id",brandId);
        putToMap(param,"page",pageNum);
        putToMap(param,"size",pageSize);
        return param;
    }

    /**
     * @Description 商品列表接口
     **/
    public JSONObject getProductHotList(Map<String,String> param){
        Map<String, String> requestParam = getRequestParam(param);
        String result = doPost(this.getProductHotList, requestParam);
        return getResultJsonObject(result);
    }

    /**
     * @Description 商品状态查询接口
     **/
    public JSONObject getProductStatus(String productId){
        Map<String, String> param = Maps.newHashMap();
        putToMap(param,"product_id",productId);
        Map<String, String> requestParam = getRequestParam(param);
        String result = doPost(this.getProductStatus, requestParam);
        return getResultJsonObject(result);
    }


    /**
     * @Description 规格状态查询
     **/
    public JSONObject getProductSpecsStatus(String specId){
        Map<String, String> param = Maps.newHashMap();
        putToMap(param,"spec_id",specId);
        Map<String, String> requestParam = getRequestParam(param);
        String result = doPost(this.getProductSpecsStatus, requestParam);
        return getResultJsonObject(result);
    }

    /**
     * @Description 商品可售卖城市接口 GET|POST
     * @Param 品牌ID
     * @Param 商品ID
     **/
    public JSONObject getProductCities(String brandId,String productId){
        Map<String, String> param = Maps.newHashMap();
        putToMap(param,"brand_id",brandId);
        putToMap(param,"product_id",productId);
        Map<String, String> requestParam = getRequestParam(param);
        String result = doPost(this.getProductCities, requestParam);
        return getResultJsonObject(result);
    }
    /**
     * @Description 商品可售卖城市接口 GET|POST
     * @Param 城市ID 是
     * @Param 品牌ID 是
     * @Param 商品ID 否
     **/
    public JSONObject getRule(String cityId,String brandId,String productId){
        Map<String, String> param = Maps.newHashMap();
        putToMap(param,"city_id",cityId);
        putToMap(param,"brand_id",brandId);
        putToMap(param,"product_id",productId);
        Map<String, String> requestParam = getRequestParam(param);
        String result = doPost(this.getRule, requestParam);
        return getResultJsonObject(result);
    }

    /**
     * @Description 批量获取配送规则id接口 POST【application/json】
     * @Param 城市ID 是
     * @Param 品牌ID 是
     * @Param 商品ID 否
     **/
    public JSONObject getRuleIds(String product){
        Map<String, String> param = Maps.newHashMap();
        putToMap(param,"product",product);
        Map<String, String> requestParam = getRequestParam(param);
        String paramString = JsonUtils.objectToJson(requestParam);
        String result = doPostByJson(this.getRuleIds, paramString);
        return getResultJsonObject(result);
    }



    /**
     * @Description 城市列表接口
     * @Param [param] 可以为null
     **/
    public  JSONObject getCityAddress(Map<String, String> param){
        Map<String, String> requestParam = getRequestParam(param);
        String result = doPost(this.getCitiesList, requestParam);
        return getResultJsonObject(result);
    }

    /**
     * @Description 根据城市名称获取城市id接口
     * @Param cityName 城市名称 是 如：北京市
     **/
    public  JSONObject getCityId(String cityName){
        Map<String, String> param = new HashMap<>(1);
        param.put("city_name",cityName);
        Map<String, String> requestParam = getRequestParam(param);
        String result = doPost(this.getCityId, requestParam);
        return getResultJsonObject(result);
    }

    /**
     * @Description 根据城市id获取区县列表接口
     * @Param cityId 蛋糕叔叔城市id 是 如：2（北京市）
     **/
    public  JSONObject getCityArea(String cityId){
        Map<String, String> param = new HashMap<>(1);
        param.put("city_id",cityId);
        Map<String, String> requestParam = getRequestParam(param);
        String result = doPost(this.getCityArea, requestParam);
        return getResultJsonObject(result);
    }

    /**
     * @Description 地址添加、修改接口 GET/POST
     * @Param userId 是 请使用渠道免登录返回的user_id
     * @Param cityName 是 城市名称(带 ‘市’ 字) 示例值：北京市
     * @Param area 是 区县名称 示例值：	海淀区
     * @Param addr 是 具体地址(不含 市、区信息) 示例值：	玉泉路3号
     * @Param lat 是 纬度（百度地图）
     * @Param lng 是 经度（百度地图）
     * @Param name 是 收货人名称
     * @Param phone 是 收货人手机号
     * @Param id  否 收货地址id (修改时必填)
     * @Param landmark 否 附近标志物  示例值：车道沟兵器大厦
     * @Param isDefault 否 是否设置为默认地址
     * @Param zip 	否 邮政编码
     **/
    public  JSONObject oprateAddr(String userId,String cityName,String area,String addr,
                                  String lat,String lng,String name,String phone,String id,
                                  String landmark,String isDefault,String zip){
        Map<String, String> param = buildOprateAddrParam(userId,cityName,area,addr,lat,lng,name,phone,id,landmark,isDefault,zip);
        Map<String, String> requestParam = getRequestParam(param);
        String result = doPost(this.oprateAddr, requestParam);
        return getResultJsonObject(result);
    }

    private Map<String, String> buildOprateAddrParam(String userId,String cityName,String area,String addr,
                                                     String lat,String lng,String name,String phone,String id,
                                                     String landmark,String isDefault,String zip){
        Map<String, String> param = new HashMap<>();
        putToMap(param,"user_id",userId);
        putToMap(param,"city_name",cityName);
        putToMap(param,"area",area);
        putToMap(param,"addr",addr);
        putToMap(param,"lat",lat);
        putToMap(param,"lng",lng);
        putToMap(param,"name",name);
        putToMap(param,"phone",phone);
        putToMap(param,"id",id);
        putToMap(param,"landmark",landmark);
        putToMap(param,"is_default",isDefault);
        putToMap(param,"zip",zip);
        return param;
    }

    /**
     * @Description 地址删除接口 GET/POST
     * @Param id 	地址id
     * @Param userId 	蛋叔用户id
     **/
    public  JSONObject delAddr(String id,String userId){
        Map<String, String> param = new HashMap<>();
        param.put("id",id);
        param.put("user_id",userId);
        Map<String, String> requestParam = getRequestParam(param);
        String result = doPost(this.delAddr, requestParam);
        return getResultJsonObject(result);
    }


    /**
     * @Description 地址删除接口 GET/POST
     * @Param userId 	蛋叔用户id
     **/
    public  JSONObject getAddrList(String userId){
        Map<String, String> param = new HashMap<>();
        param.put("user_id",userId);
        Map<String, String> requestParam = getRequestParam(param);
        String result = doPost(this.getAddrList, requestParam);
        return getResultJsonObject(result);
    }

    /**
     * @Description 门店列表接口 GET/POST
     * @Param cityId 	城市id
     * @Param brandId 	品牌id
     **/
    public  JSONObject getShopList(String cityId,String brandId){
        Map<String, String> param = new HashMap<>();
        param.put("city_id",cityId);
        param.put("brand_id",brandId);
        Map<String, String> requestParam = getRequestParam(param);
        String result = doPost(this.getShopList, requestParam);
        return getResultJsonObject(result);
    }


    /**
     * @Description 品牌详情接口 GET/POST
     * @Param brandId 	品牌id
     **/
    public  JSONObject getBrandInfo(String brandId){
        Map<String, String> param = new HashMap<>();
        param.put("brand_id",brandId);
        Map<String, String> requestParam = getRequestParam(param);
        String result = doPost(this.getBrandInfo, requestParam);
        return getResultJsonObject(result);
    }

    /**
     * @Description 品牌列表与其开通城市接口 GET/POST
     **/
    public  JSONObject brandCityList(){
        Map<String, String> requestParam = getRequestParam(null);
        String result = doPost(this.brandCityList, requestParam);
        return getResultJsonObject(result);
    }


    /**
     * @Description 统一处理回调通知
     **/
    public JSONObject processCallBackParam(Object callBackObject){
        if(EmptyUtil.isEmpty(callBackObject)){
            return null;
        }
        JSONObject rt = null;
        if(callBackObject instanceof String){
            rt =  JSONObject.parseObject((String)callBackObject);
        }else{
            rt = JSONObject.parseObject(JsonUtils.objectToJson(callBackObject));
        }
        boolean verifySignRt = verifySign(rt);
        if(!verifySignRt){
            throw new SecurityException("验签失败");
        }
        return rt;
    }

/*********************************** common start************************************************************/

    private String doPost(String url,Map<String, String> requestParam){
        logger.info("请求地址:"+this.cakeBaseUrl+url);
        logger.info("请求秘钥:{}",this.privateKey);
        logger.info("请求参数:{}", JSON.toJSONString(requestParam));
        String result = HttpClientUtil.doPost(this.cakeBaseUrl + url, requestParam, CHARSET_UTF_8, 30000);
        return result;
    }

    private String doGet(String url ,Map<String, String> param){
        String paramString = getUrlParam(param);
        String requestUrl = this.cakeBaseUrl +url+"?"+paramString;
        return HttpClientUtil.doGet(requestUrl);
    }
    private String doPostByJson(String url,String paramString){
        return HttpClientUtil.doPostByJson(this.cakeBaseUrl + url, CHARSET_UTF_8,paramString);
    }



    private String getUrlParam(Map<String, String> param){
        if(EmptyUtil.isEmpty(param)) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : param.entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        // 删除最后一个字符
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    private JSONObject getResultJsonObject(String result) {
        if (EmptyUtil.isEmpty(result)) {
            return null;
        }
        return JSONObject.parseObject(result);
    }

    private Map<String, String> getRequestParam(Map<String, String> param) {
        String timestamp = String.valueOf(System.currentTimeMillis());
        if(EmptyUtil.isNotEmpty(param) && param.containsKey("timestamp")){
            timestamp = String.valueOf(param.get("timestamp"));
        }
        String keyValuePairs = getStringBufferCommon(timestamp,this.cakeChannelNo).toString();
        String sign = getEncryptionSign(keyValuePairs);
        Map<String,String> requestParam = getRequestMapParam(timestamp,sign);
        putAllToMap(requestParam,param);
        return requestParam;
    }

    private String getEncryptionSign(String keyValuePairs){
        String sha1String = getSha1(keyValuePairs);
        String md5String = getMD5(sha1String);
        return md5String;
    }

    private Map<String,String> getRequestMapParam(String timestamp,String sign){
        Map<String, String> param = new HashMap();
        param.put("channel_no",this.cakeChannelNo);
        param.put("timestamp",String.valueOf(timestamp));
        param.put("sign",sign);
        return param;
    }

    /**
     * @Description 进行SHA-1算法
     **/
    public String getSha1(String input) {
        //return getAlgorithmString(input, "SHA-1");
        return sha1Text(input);
    }

    /**
     * @Description 进行MD5加密
     **/
    public String getMD5(String input) {
       // return getAlgorithmString(input, "MD5");
        return getMd5HexText(input);
    }

    private  String getAlgorithmString(String input, String algorithm) {
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            byte[] messageDigest = md.digest(input.getBytes(StandardCharsets.UTF_8));
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private void putToMap(Map<String, String> param,String key,Object value){
        if(EmptyUtil.isEmpty(value)){
          return;
        }
        if(value instanceof String){
            param.put(key,(String)value);
            return;
        }
        param.put(key,String.valueOf(value));
    }

    private void putAllToMap(Map<String,String> target,Map<String,String> source){
        if(EmptyUtil.isEmpty(source)){
            return ;
        }
        target.putAll(source);
    }

    private StringBuffer getStringBufferCommon(String timestamp,String cakeChannelNo) {
        StringBuffer sb = new StringBuffer();
        sb.append("channel_no").append(cakeChannelNo);
        sb.append("timestamp").append(timestamp);
        sb.append(this.privateKey);
        return sb;
    }



    public boolean verifySign(JSONObject json){
        if(!json.containsKey("channel_no") || !json.containsKey("timestamp") || !json.containsKey("sign")){
            return false;
        }
        String sign = json.getString("sign");
        String timestamp = json.getString("timestamp");
        String channelNo = json.getString("channel_no");
        return verifySign(sign,timestamp,channelNo);
    }

    public boolean verifySign(String sign,String timestamp,String cakeChannelNo){
        if(EmptyUtil.isEmpty(sign) || EmptyUtil.isEmpty(timestamp) || EmptyUtil.isEmpty(cakeChannelNo)){
            return false;
        }
        String keyValuePairs = getStringBufferCommon(timestamp,cakeChannelNo).toString();
        String signRT = getEncryptionSign(keyValuePairs);
        return Objects.equals(signRT,sign);
    }

    /**
     * @Description 检查返回值，若有错返回错误
     **/
    public JsonResult checkResult(JSONObject jSONObject){
        if(Objects.isNull(jSONObject)){
            return JsonResult.fail("查询为空");
        }
        if(!Objects.equals(jSONObject.getString(CODE_KEY),CODE_SUCCESS)){
            return JsonResult.fail("查询失败"+jSONObject.getString(MSG_KEY));
        }
//        String data = jSONObject.getString(DATA_KEY);
//        if(StringUtils.isEmpty(data)){
//            return JsonResult.success();
//        }
        return null;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCakeTaxRate() {
        return cakeTaxRate;
    }

    public void setCakeTaxRate(String cakeTaxRate) {
        this.cakeTaxRate = cakeTaxRate;
    }

    /*********************************** common end************************************************************/


    private static String getMd5HexText(String sha1Hex)  {
        try {
            //System.out.println("MD5签名字符串: " + sha1Hex);
            MessageDigest md5Digest = MessageDigest.getInstance("MD5");
            byte[] md5Bytes = md5Digest.digest(sha1Hex.getBytes(StandardCharsets.UTF_8));

            // 将MD5哈希值转换为十六进制字符串，并转换为小写
            String md5Hex = DatatypeConverter.printHexBinary(md5Bytes).toLowerCase();
            return md5Hex;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    private static String sha1Text(String input) {
        try {
            //System.out.println("SHA1签名字符串: " + input);
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(input.getBytes(StandardCharsets.UTF_8));
            byte[] digest = md.digest();
            // 将字节数组转换为十六进制字符串
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            //System.out.println("SHA1签名: " + sb.toString());
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
