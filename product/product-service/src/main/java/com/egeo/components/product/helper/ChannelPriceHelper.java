package com.egeo.components.product.helper;

import com.egeo.components.product.dao.ChannelEnterpriseConfigDAO;
import com.egeo.components.product.dao.JdEnterpriseConfigDAO;
import com.egeo.components.product.dao.write.ChannelPriceDAO;
import com.egeo.components.product.dto.ChannelEnterpriseConfigDTO;
import com.egeo.components.product.dto.ChannelPriceDTO;
import com.egeo.components.product.dto.JdEnterpriseConfigDTO;
import com.egeo.components.product.enums.ChannelPriceConstants;
import com.egeo.components.product.enums.JdPriceType;
import com.egeo.components.utils.ChannelPriceTools;
import com.egeo.utils.EmptyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
@Service
public class ChannelPriceHelper {

    @Autowired
    public ChannelEnterpriseConfigDAO channelEnterpriseConfigDAO;

    @Autowired
    private JdEnterpriseConfigDAO jdEnterpriseConfigDAO;

    @Autowired
    private ChannelPriceDAO channelPriceDAO;

    public List<ChannelEnterpriseConfigDTO> getChannelEnterpriseConfigAll(String channelCode,Long enterpriseId,boolean hasAddJD){
        List<ChannelEnterpriseConfigDTO> resultList = new ArrayList<>();
        //addEnterpriseDefaultConfig(resultList,hasAddJD);
        List<ChannelEnterpriseConfigDTO> channelEnterpriseConfigDTOList = channelEnterpriseConfigDAO.findAllConfigOfEnterprise1(enterpriseId,channelCode);
        if(!CollectionUtils.isEmpty(channelEnterpriseConfigDTOList)){
            resultList.addAll(channelEnterpriseConfigDTOList);
        }
        return resultList;
    }

    public List<ChannelEnterpriseConfigDTO> getChannelEnterpriseConfigAll(String channelCode,boolean hasAddJD){
        List<ChannelEnterpriseConfigDTO> resultList = new ArrayList<>();
        //addEnterpriseDefaultConfig(resultList,hasAddJD);
        List<ChannelEnterpriseConfigDTO> channelEnterpriseConfigDTOList = channelEnterpriseConfigDAO.findAllConfig(channelCode);
        if(!CollectionUtils.isEmpty(channelEnterpriseConfigDTOList)){
            resultList.addAll(channelEnterpriseConfigDTOList);
        }
        return resultList;
    }

    public List<ChannelEnterpriseConfigDTO> getEnterpriseDefaultConfig(){
        List<ChannelEnterpriseConfigDTO> resultList = new ArrayList<>();
        List<JdEnterpriseConfigDTO> list = getJdEnterpriseConfigDTOList(false);
        for (JdEnterpriseConfigDTO jdEnterpriseConfigDTO : list) {
            resultList.add(JdEnterpriseConfigToChannelEnterpriseConfigDTO(jdEnterpriseConfigDTO));
        }
        return resultList;
    }

    private void addEnterpriseDefaultConfig(List<ChannelEnterpriseConfigDTO> resultList,boolean hasAddJD){
        List<JdEnterpriseConfigDTO> list = getJdEnterpriseConfigDTOList(hasAddJD);
        for (JdEnterpriseConfigDTO jdEnterpriseConfigDTO : list) {
            resultList.add(JdEnterpriseConfigToChannelEnterpriseConfigDTO(jdEnterpriseConfigDTO));
        }
    }

    private List<JdEnterpriseConfigDTO> getJdEnterpriseConfigDTOList(boolean hasAddJD){
        if(hasAddJD){
            return  jdEnterpriseConfigDAO.findAllConfig();
        }
        return jdEnterpriseConfigDAO.findConfigOfPlatform();
    }

    private ChannelEnterpriseConfigDTO JdEnterpriseConfigToChannelEnterpriseConfigDTO(JdEnterpriseConfigDTO jdEnterpriseConfigDTO){
        ChannelEnterpriseConfigDTO dto = new ChannelEnterpriseConfigDTO();
        //dto.setId(jdEnterpriseConfigDTO.getId());
        dto.setEnterpriseId(jdEnterpriseConfigDTO.getEnterpriseId());
        dto.setType(jdEnterpriseConfigDTO.getType());
        dto.setPriceAddtion(jdEnterpriseConfigDTO.getPriceAddtion());
        dto.setPriceAddtionMax(jdEnterpriseConfigDTO.getPriceAddtionMax());
        dto.setPriceAddtionMin(jdEnterpriseConfigDTO.getPriceAddtionMin());
        dto.setGrossMarginMax(jdEnterpriseConfigDTO.getGrossMarginMax());
        dto.setPlateformAddtion(jdEnterpriseConfigDTO.getPlateformAddtion());
        dto.setGrossMarginMin(jdEnterpriseConfigDTO.getGrossMarginMin());
        dto.setChannelCategorys(jdEnterpriseConfigDTO.getJdCategorys());
        dto.setChannelPriceMax(jdEnterpriseConfigDTO.getJdPriceMax());
        dto.setChannelPriceMin(jdEnterpriseConfigDTO.getJdPriceMin());
        dto.setCreateMillis(jdEnterpriseConfigDTO.getCreateMillis());
        dto.setUpdateMillis(jdEnterpriseConfigDTO.getUpdateMillis());
        dto.setChannelCode("jd");
        if(dto.getType()==1){
            dto.setChannelCode("default");
        }
        return dto;
    }

    public Map<String, String> calcPrice(String marketPrice, String settlementPrice, List<ChannelEnterpriseConfigDTO> channelEnterpriseConfigs, ChannelPriceDTO one){
        Map<String,String> resultMap = new HashMap<>();
        //获取实时价格
        BigDecimal realPrice = new BigDecimal(settlementPrice);
        BigDecimal realJdPrice = new BigDecimal(marketPrice);

        if(ChannelPriceTools.hasEnterpriseDefaultConfig(channelEnterpriseConfigs,one.getEnterpriseId())){
            ChannelEnterpriseConfigDTO defaultConfig = ChannelPriceTools.getEnterpriseDefaultConfig(channelEnterpriseConfigs,one.getEnterpriseId());
            if(defaultConfig!=null && defaultConfig.getPlateformAddtion()!=null && defaultConfig.getPlateformAddtion()>0) {
                realPrice = realPrice.add(realPrice.multiply(BigDecimal.valueOf(defaultConfig.getPlateformAddtion().longValue()).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP)));
            }
        }

        BigDecimal finalPrice = BigDecimal.valueOf(realPrice.doubleValue());
        ChannelEnterpriseConfigDTO config=null;
        //优先级1的是代理商自定义价格
        if(one!=null && JdPriceType.isValidate(one.getPriceType())) {
            if(JdPriceType.IncreaseFixedValue.equal(one.getPriceType())) {
                finalPrice = realPrice.add(new BigDecimal(one.getPriceValue()));
            }else if(JdPriceType.IncreaseFixedRatio.equal(one.getPriceType())) {
                finalPrice = realPrice.add(realPrice.multiply((new BigDecimal(one.getPriceValue()).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP))));
            }else if(JdPriceType.FixedPrice.equal(one.getPriceType())) {
                finalPrice = new BigDecimal(one.getPriceValue());
            }
        }else {

            if(ChannelPriceTools.hasEnterpriseSellConfig(channelEnterpriseConfigs,one.getEnterpriseId())) {
                //优先级2 代理商自定义渠道配置
                 config = ChannelPriceTools.getEnterpriseSellConfig(channelEnterpriseConfigs,one.getEnterpriseId());
                long configValue = config.getPriceAddtion()==null?0:config.getPriceAddtion().longValue();
                BigDecimal vv = BigDecimal.valueOf(configValue).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);
                finalPrice = realPrice.add(realPrice.multiply(vv));
            }else if(ChannelPriceTools.hasEnterpriseDefaultConfig(channelEnterpriseConfigs,one.getEnterpriseId())){
                //优先级3 平台给代理商定义的配置
                 config = ChannelPriceTools.getEnterpriseDefaultConfig(channelEnterpriseConfigs,one.getEnterpriseId());
                long configValue = config.getPriceAddtion()==null?0:config.getPriceAddtion().longValue();
                BigDecimal vv = BigDecimal.valueOf(configValue).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);
                finalPrice = realPrice.add(realPrice.multiply(vv));
            }else if(ChannelPriceTools.hasPlatformConfig(channelEnterpriseConfigs)){
                //优先级4 平台缺省配置
            }
        }

        finalPrice = finalPrice.setScale(2, RoundingMode.HALF_UP);
        if(finalPrice.compareTo(realJdPrice)>0) {
            //渠道价格逻辑：若计算价格大于渠道市场价，售价就是渠道市场价
            finalPrice = BigDecimal.valueOf(realJdPrice.doubleValue());
        }
        Integer profit = finalPrice.subtract(realPrice).divide(finalPrice,4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).intValue();

        boolean isMoreThanGrossMarginMin = true;
        if (Objects.nonNull(config)){
            if (Objects.nonNull(config.getGrossMarginMin()) && config.getGrossMarginMin()>0){
                if (profit<config.getGrossMarginMin()){
                    isMoreThanGrossMarginMin = false;
                }
            }
        }
        boolean isMoreThanPriceScope = false;
        if (Objects.nonNull(config)){
            if (Objects.nonNull(config.getChannelPriceMax()) && Objects.nonNull(config.getChannelPriceMin())){
                //是否超过设置的价格范围
                if (realJdPrice.compareTo(new BigDecimal(config.getChannelPriceMin()))==-1
                        || realJdPrice.compareTo(new BigDecimal(config.getChannelPriceMax())) ==1){
                    isMoreThanPriceScope = true;
                }
            }
        }
        //企业所看见的价格是平台加价后的价格
        resultMap.put(ChannelPriceConstants.ENTERPRISE_PRICE_KEY,realPrice.toPlainString());
        //渠道的结算价
        resultMap.put(ChannelPriceConstants.CHANNEL_PRICE_KEY,new BigDecimal(settlementPrice).toPlainString());
        //毛利率
        resultMap.put(ChannelPriceConstants.PROFIT_KEY,String.valueOf(profit));
        //渠道所给的市场标准价
        resultMap.put(ChannelPriceConstants.MARKET_PRICE_KEY, new BigDecimal(marketPrice).toPlainString());
        //真正的售卖价
        resultMap.put(ChannelPriceConstants.SALE_PRICE_KEY,finalPrice.toPlainString());
        //毛利率小于设置的最小毛利率
        resultMap.put(ChannelPriceConstants.IS_MORE_THAN_GROSS_MARGIN_MIN,String.valueOf(isMoreThanGrossMarginMin));
        //超过设置的价格范围
        resultMap.put(ChannelPriceConstants.IS_MORE_THAN_PRICE_SCOPE,String.valueOf(isMoreThanPriceScope));
        return resultMap;
    }


    public Map<String, String> calcPlatformPrice(String marketPrice, String settlementPrice, List<ChannelEnterpriseConfigDTO> channelEnterpriseConfigs, ChannelPriceDTO customPrice){
        Map<String,String> resultMap = new HashMap<>();
        //获取实时价格
        BigDecimal realPrice = new BigDecimal(settlementPrice);
        BigDecimal realChannelMarketPrice = new BigDecimal(marketPrice);
        //先平台增价，因此realPrice就等于每个企业代理商所能看见的商品价格
        if(ChannelPriceTools.hasEnterpriseDefaultConfig(channelEnterpriseConfigs)){
            ChannelEnterpriseConfigDTO defaultConfig = ChannelPriceTools.getEnterpriseDefaultConfig(channelEnterpriseConfigs);
            if(defaultConfig.getPlateformAddtion()!=null && defaultConfig.getPlateformAddtion()>0) {
                realPrice = realPrice.add(realPrice.multiply(BigDecimal.valueOf(defaultConfig.getPlateformAddtion().longValue()).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP)));
            }
        }
        BigDecimal finalPrice = realPrice;
        ChannelEnterpriseConfigDTO config = null;
        //优先级1的是代理商自定义价格
        if(customPrice!=null && JdPriceType.isValidate(customPrice.getPriceType())) {
            if(JdPriceType.IncreaseFixedValue.equal(customPrice.getPriceType())) {
                finalPrice = realPrice.add(new BigDecimal(customPrice.getPriceValue()));
            }else if(JdPriceType.IncreaseFixedRatio.equal(customPrice.getPriceType())) {
                finalPrice = realPrice.add(realPrice.multiply((new BigDecimal(customPrice.getPriceValue()).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP))));
            }else if(JdPriceType.FixedPrice.equal(customPrice.getPriceType())) {
                finalPrice = new BigDecimal(customPrice.getPriceValue());
            }
        }else {

            if(ChannelPriceTools.hasEnterpriseSellConfig(channelEnterpriseConfigs)) {
                //优先级2 代理商自定义京东配置
                 config = ChannelPriceTools.getEnterpriseSellConfig(channelEnterpriseConfigs);
                long configValue = config.getPriceAddtion()==null?0:config.getPriceAddtion().longValue();
                BigDecimal vv = BigDecimal.valueOf(configValue).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);
                finalPrice = realPrice.add(realPrice.multiply(vv));
            }else if(ChannelPriceTools.hasEnterpriseDefaultConfig(channelEnterpriseConfigs)){
                //优先级3 平台给代理商定义的配置
                 config = ChannelPriceTools.getEnterpriseDefaultConfig(channelEnterpriseConfigs);
                long configValue = config.getPriceAddtion()==null?0:config.getPriceAddtion().longValue();
                BigDecimal vv = BigDecimal.valueOf(configValue).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);
                finalPrice = realPrice.add(realPrice.multiply(vv));
            }else if(ChannelPriceTools.hasPlatformConfig(channelEnterpriseConfigs)){
                //优先级4 平台缺省配置
            }
        }
        //渠道价格逻辑预设置
        finalPrice = finalPrice.setScale(2, RoundingMode.HALF_UP);
        //超过市场价就等于市场价
        if(finalPrice.compareTo(realChannelMarketPrice)>0) {
            finalPrice = BigDecimal.valueOf(realChannelMarketPrice.doubleValue());
        }
        if(customPrice!=null){
            resultMap.put(ChannelPriceConstants.PRICE_TYPE,customPrice.getPriceType()+"");
            resultMap.put(ChannelPriceConstants.PRICE_AUDIT,customPrice.getAudit()+"");
            resultMap.put(ChannelPriceConstants.PRICE_VALUE,customPrice.getPriceValue()+"");
        }
        Integer profit=finalPrice.subtract(realPrice).divide(finalPrice,4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).intValue();
        //Integer profit=finalPrice.subtract(realPrice).divide(realPrice,4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).intValue();
        boolean isMoreThanGrossMarginMin = true;
        if (Objects.nonNull(config)){
            if (Objects.nonNull(config.getGrossMarginMin()) && config.getGrossMarginMin()>0){
                if (profit<config.getGrossMarginMin()){
                    isMoreThanGrossMarginMin = false;
                }
            }
        }
        boolean isMoreThanPriceScope = true;
        if (Objects.nonNull(config)){
            if (Objects.nonNull(config.getChannelPriceMax()) && Objects.nonNull(config.getChannelPriceMin())){
                //是否超过设置的价格范围
                if (finalPrice.compareTo(new BigDecimal(config.getChannelPriceMin()))==-1
                        || finalPrice.compareTo(new BigDecimal(config.getChannelPriceMax())) ==1){
                    isMoreThanPriceScope = false;
                }
            }
        }
        //企业所看见的价格是平台加价后的价格
        resultMap.put(ChannelPriceConstants.ENTERPRISE_PRICE_KEY,realPrice.toPlainString());
        //渠道的结算价
        resultMap.put(ChannelPriceConstants.CHANNEL_PRICE_KEY,new BigDecimal(settlementPrice).toPlainString());
        //毛利率
        BigDecimal grossProfit=finalPrice.subtract(new BigDecimal(settlementPrice)).divide(finalPrice,4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
        resultMap.put(ChannelPriceConstants.PROFIT_KEY,grossProfit.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());
        if(grossProfit.compareTo(BigDecimal.ZERO)<0){
            resultMap.put(ChannelPriceConstants.PROFIT_KEY,BigDecimal.ZERO.toPlainString());
        }
        //渠道所给的市场标准价
        resultMap.put(ChannelPriceConstants.MARKET_PRICE_KEY, new BigDecimal(marketPrice).toPlainString());
        //真正的售卖价
        resultMap.put(ChannelPriceConstants.SALE_PRICE_KEY,finalPrice.toPlainString());
        //毛利率小于设置的最小毛利率
        resultMap.put(ChannelPriceConstants.IS_MORE_THAN_GROSS_MARGIN_MIN,String.valueOf(isMoreThanGrossMarginMin));
        //超过设置的价格范围
        resultMap.put(ChannelPriceConstants.IS_MORE_THAN_PRICE_SCOPE,String.valueOf(isMoreThanPriceScope));
        return resultMap;
    }

    /**
     * @Description 检查是否合法
     **/
    public boolean checkIsVaild(Map<String,String> priceMap){
        boolean isMoreThan = Boolean.valueOf(priceMap.get(ChannelPriceConstants.IS_MORE_THAN_GROSS_MARGIN_MIN));
        boolean isMoreThanPriceScope = Boolean.valueOf(priceMap.get(ChannelPriceConstants.IS_MORE_THAN_PRICE_SCOPE));
        //毛利大于了
//        if(!isMoreThan){
//            return false;
//        }
//        //是否超过了配置的价格范围
//        if(isMoreThanPriceScope){
//            return false;
//        }
        return true;
    }

    public Map<String,ChannelPriceDTO> getChannelPriceMap(String channelCode,List<String> pIds,List<String> skuIds,Long enterpriseId){
        List<ChannelPriceDTO> channelPriceDTOS = channelPriceDAO.findAllPriceOfEnterpriseAndSpusPIds(channelCode,skuIds,pIds,enterpriseId);
        return convertChannelPriceToMap(channelPriceDTOS);
    }

    private Map<String,ChannelPriceDTO> convertChannelPriceToMap(List<ChannelPriceDTO> channelPriceDTOS) {
        Map<String,ChannelPriceDTO> priceDTOMap = new HashMap<>();
        if(CollectionUtils.isEmpty(channelPriceDTOS)){
            return priceDTOMap;
        }
        for (ChannelPriceDTO channelPriceDTO : channelPriceDTOS) {
            priceDTOMap.put(appendMapKey(channelPriceDTO),channelPriceDTO);
        }
        return priceDTOMap;
    }

    private String appendMapKey(ChannelPriceDTO channelPriceDTO){
        if(EmptyUtil.isNotEmpty(channelPriceDTO.getPid()) && EmptyUtil.isNotEmpty(channelPriceDTO.getSpuId())){
            return channelPriceDTO.getSpuId()+channelPriceDTO.getPid();
        }
        if(EmptyUtil.isNotEmpty(channelPriceDTO.getSpuId())){
            return channelPriceDTO.getPid();
        }
        if(EmptyUtil.isNotEmpty(channelPriceDTO.getSpuId())){
            return channelPriceDTO.getSpuId();
        }
        return null;
    }
}
