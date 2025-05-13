package com.egeo.components.third.strategy.impl.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.egeo.components.third.bean.SignHelperBean;
import com.egeo.components.third.common.EncryptTypeEnum;
import com.egeo.components.third.strategy.SignService;
import com.egeo.components.utils.DLFUtil;
import com.egeo.components.utils.RsaSignUtil;
import com.egeo.exception.BusinessException;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.log.XLogger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
@Service("dLFSignServiceImpl")
public class DLFSignServiceImpl  implements SignService {
    public XLogger logger = XLogger.getLogger(this.getClass().getName());
    public static final  String PRIVATE_KEY="MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDcz+7TZdAC7DzB7QAgCzQF/7S9hCvLwDnTXUUhzKXCPbDbsFJjnWJRU+lqW15wSeJarZM/PeeS/MckjgBDdzH/xzUv/1vU+MOsQF+aBES0qjmgTkp5OHnplXOhlitHm7jaPAFBRPTinQQ6y1CHiYr7U7WRxhc+H8QodqBx0pd3xDrZnQ9YKl+iwjStlchjRtwS5SK4xq1pYhicR97S6WNAYCz52Mqn3AO5idK9D6cau45kFQQ+rD7pJ+YTiqjTSnefFANjbDBYcaCPBYQ4IDRJJLL1azsld4Rid1LLmfay0aT98uA1xoHUg7fQ1NJuQB6esxO6XlmaBg4BQMrlyQfdAgMBAAECggEAV6smwz/VCpQIu2YBerd3/Dm95NCpIsg7LTQG0piR50DfCxpCW9KMyoVqUxPbtvFesWng4GcPnkhGOLk4WZDlhQvujhPwvenWpnkYe+nd7Wz4XDE5WE+X96miszEQFeo5xku0o9lg8OguWbmHS4jMaWSe3jWr3Lp4Ayi2IvrdsGq3oTOamxCCi2zGSj+t/b6jgivj2iWNLQW5NRYoGmNrl+s1Ki8jP+xWKm8oYI3lt+wmMZ11dIHW1i09ibxlMXerGIsxkv87xdzA0usUUUq8OedojJ2PptMOeRbFETZduofywUSiSADLQkEN+3Gh8rPA2YxwKIw4rP6PA5kaCmsaAQKBgQD1Fn0DXxsxHv1yK0hZ639filuVsuv3NF5GMi6xbgWEff8iETGXl8XxHDCPfP8CCBOcqH+EOYjik2kT+TNss1eoBw2xlOGZwanRDjghMFNXE7lVLVnKrFwG4C5dmUICTXz8I9T8llMjU5zGYL92pSDfJIHOi/AIj8+djFlJb8bV/QKBgQDmpMBMrz0/8wevRjp12jzUKuFAdWj7/xIqEXqHPBWYNBXnjg5zfsE0RlK914R7TM2qDRcaY0fZPtck/kMYkcrIMXHaqN8OBMVOB9P3bpplmsKrY1bvvcEKja9+dvut3TACDmy4Z9AOTwl+cIUmiTGhfwU3R5MZ0FR3cMVejvKvYQKBgQCtzUvL90bmN67NpsoBElLaYth9uUtCm/161B1ur7Hz+hHSi9TaRsQVLsN1DdQdEbMutgLvtolO+/BZPLBuFmREAPSCCS1PBxow6V9+kbGYH6GfOLDjXAVKzvGyaHt5/I90ksh4cvX0KFgWCgfc0E8WTuJKocwPlkRJ2nx66mxb1QKBgQDNgVp+eV3fzxG30KFiRPQSL6rInlTOw/VRWoWMXbL4DOC/k0m5CuXnSAmNcaxvmH1f2Y4OSwvHvEBCi/MIv06plbTWmcU1XnbXEg6B1yDnC0ANKdRahP6uApSt4CJcRkryahvhK06skSvqZyOctH1uYhkpqjKdu4ynZ+nUWRZPwQKBgQCo+fNPlgIU3HpBBNZjRequPA5QbxTJOz3SZ+tdK5fQEyTx09auHq0N5zTek+Mts7S/LQYhz6FLlqUDpzvVvNlbwQF31dpHwRB1rA3xsB1xqUZ3LIsd2tI+r8M8zzi8pv970ci/VkHIzcpQTV9paIUhiD2FuHf22bEAthozE3mmXw==";
    public static final String publicKey= "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApXID5oKQ5syLHxwKqsCf5XrJohwC10EsUjYSO/23o00Pa5ZLv+elkSSJHfy6FPBufZPNtcLsmOubWCmaSoGvxy+/7ioUsZWYjZTZ7f13E0Ynhhi99W8gx+6GzxCiNdoRghobUo3nWVjMLNt5Jq5ij/CIzUhaeTkY602nDuc8cpHty3SUhDP7lcziIw52JOf8PclifCOFxWXBaOKaimgHPNGq/4KAGkTY4yhS1U7Ycf0chy/HnwwsLvTNplzHwOhnV1KRMfuJxBFiz6T/55lmkXbgqZGXBg4t4aobkwOtsooOxT/wGkZ5uxNNGkKnQPFXdYPqSnbOtq3v4TKzqs3t8wIDAQAB";
    public static final String STATUS_KEY="status";
    public static final Integer SUCCESS_STATUS=1;
    private static final String VERSION="1.0";

    @Resource
    private DLFUtil dlfUtil;

    @Override
    public String getEncryptType() {
        return EncryptTypeEnum.DLF.getEncryptType();
    }

    @Override
    public Object encryptionData(SignHelperBean bean) {
        Map<String,Object> finalMap = getRequestMap(bean.getData(),null);
        return JSON.toJSONString(finalMap);

    }

    @Override
    public Object decryptData(SignHelperBean bean) {
        if(bean.getData() == null){
            return bean.getData();
        }
       /*
       String result = RsaSignUtil.decodeUnicodeEscapes(bean.getData().toString());
        logger.info("解密结果：{}",result);
        Object o = parseResult(result);*/
        Object o = convertMsgAndJsonObject(bean);
        return o;
    }

    private Object convertMsgAndJsonObject(SignHelperBean bean){
        try {
            String dataString =null;
            if(bean.getData() instanceof String){
                dataString = (String)bean.getData();
            }
            //String result =  RsaSignUtil.decodeUnicodeEscapes(null !=dataString?dataString:bean.getData().toString());
            JSONObject jsonObject = JSONObject.parseObject(null !=dataString?dataString:bean.getData().toString());
            return jsonObject;
        }catch (Exception e){
            return bean.getData();
        }
    }

    private Object parseResult(String result){
        if(EmptyUtil.isEmpty(result)){
            return result;
        }
        try {
            return JSONObject.parseObject(result);
        }catch (Exception e){
            e.printStackTrace();
            return result;
        }
    }


    public  Map<String,Object> getRequestMap(Object jsonString, Long timestamp){
        Map<String,Object> finalMap = new LinkedHashMap<>();
        finalMap.put("data", jsonString);
        finalMap.put("timestamp",null ==timestamp?String.valueOf(System.currentTimeMillis()):String.valueOf(timestamp));
        finalMap.put("version",VERSION);
        String plainText = JSON.toJSONString(finalMap);
        String sign = null;
        try {
            sign = RsaSignUtil.signData(plainText,dlfUtil.getDlfPrivateKey());
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("签名失败");
        }
        finalMap.put("sign",sign);
        return finalMap;
    }
}
