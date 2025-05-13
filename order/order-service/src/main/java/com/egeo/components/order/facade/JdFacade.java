package com.egeo.components.order.facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.egeo.components.utils.JdUtils2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.egeo.components.order.dto.ReceiverAddressDTO;
import com.egeo.components.order.vo.jd.JdDeliveryPrice;
import com.egeo.components.order.vo.jd.JdProductStockSearch;
import com.egeo.components.order.vo.jd.JdResponse;
import com.egeo.components.order.vo.jd.JdSkuAddressSellStatus;
import com.egeo.components.order.vo.jd.ParseAddressJson;
import com.egeo.components.order.vo.jd.SkuInfo;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.delivery.JdUtils;

@Component
public class JdFacade {

	public Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	private JdUtils jdUtils;
    @Autowired
    private JedisUtil jedisUtil;


    @Autowired
    private JdUtils2 jdUtils2;

	public Boolean hasStock(ReceiverAddressDTO addr,long num,Long puId) {
        String addrStr = addr.getGoodReceiverProvince()+addr.getGoodReceiverCity()+addr.getGoodReceiverCounty()+addr.getGoodReceiverArea();
        return hasStock(addrStr, num, puId);
    }
	public Boolean hasStock(String addrStr,long num,Long puId) {
		 // String token = jdUtils.getAccessToken(jedisUtil);
        String token = jdUtils2.getAccessToken(jedisUtil);
          ParseAddressJson parseAddressJson = getDeliveryPriceFromJd(token,addrStr);

          if(EmptyUtil.isNotEmpty(parseAddressJson)){
              String skuAddressSellStatusFromJd = jdUtils.getSkuAddressSellStatusFromJd(token, puId+"", parseAddressJson.getProvinceId(), parseAddressJson.getCityId(), parseAddressJson.getCountyId(), parseAddressJson.getTownId());
              JdResponse jdResponse1 = JSON.parseObject(skuAddressSellStatusFromJd, JdResponse.class);
              if(jdResponse1.isSuccess()&&jdResponse1.getResultCode().equals("0000")){
                  String json = jdResponse1.getResult();
                  List<JdSkuAddressSellStatus> jdSkuStatus = JSON.parseArray(json, JdSkuAddressSellStatus.class);
                  if(jdSkuStatus.get(0).getIsAreaRestrict().equals("true")){
                  	return false;
                  }
              }else{
              	return false;
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
              logger.info("京东商品{}检查库存时参数:{}返回结果{}",puId,JSON.toJSONString(searchList),jdProductStockString);
              List<JSONObject> jdProductStockArr= JSONObject.parseArray(jdProductStockString, JSONObject.class);
              JSONObject jdProductStock=JSONObject.parseObject(jdProductStockArr.get(0).toString());
              if(EmptyUtil.isEmpty(jdProductStock)){
              	return false;
              }else{
                  String stockStateId = jdProductStock.getString("stockStateId");
                  if(stockStateId.equals("33")||stockStateId.equals("39")||stockStateId.equals("40")){
                	  return true;
                  }else{
                  	return false;
                  }
              }

          }

        	return null;

	}

	private ParseAddressJson getDeliveryPriceFromJd(String token,String address) {

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

}
