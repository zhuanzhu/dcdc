package com.egeo.components.product.dto.world;

import com.egeo.components.product.dto.world.goodchild.WorldFreightTemplateNoticeDTO;
import com.egeo.components.product.dto.world.goodchild.WorldSkuDetailDTO;

import java.io.Serializable;
import java.util.List;

/**
 * @Description 商品变更或者新增消息通知接口
 * @Author lsl
 * @Version V1.0
 **/
public class WorldGoodsChangeNoticeDTO implements Serializable {

    /***
     * 	String	是 商品ID
     **/
    private String goodsId;


    /***
     *	String	是 商品名称
     **/
    private String goodsName;


    /***
     *	String	是 商品简介
     **/
    private String introName;

    /***
     *	String	是 	数组;
     **/
    private List<String> images;

    /***
     *	String	是 	商品描述
     **/
    private String description;

    /***
     *	String	是 商品类型(0-一般贸易,1-保税,2-海外直邮)
     **/
    private String goodsType;

    /***
     *	String	是 扩展字段类型(一般情况下用不到)
     **/
    private String goods_extends_type;

    /***
     *	String	是 固定为1
     **/
    private String status;

    /***
     *	String	是 三级分类ID
     **/
    private String categoryId;

    /***
     *	String	是	三级分类编码
     **/
    private String categoryCode;

    /***
     *	String	是 三级分类名称
     **/
    private String categoryName;

    /***
     *	String	是 二级分类ID
     **/
    private String categoryPid;

    /***
     *	String	是 二级分类编码
     **/
    private String categoryPreCode;

    /***
     *	String	是 二级分类名称
     **/
    private String categoryPreName;

    /***
     *String	是 一级分类ID
     **/
    private String categoryFPid;

    /***
     *	String	是 一级分类编码
     **/
    private String categoryFPreCode;

    /***
     *	String	是 一级分类名称
     **/
    private String categoryFPreName;

    /***
     *	String	是 一级分类ID
     **/
    private String categoryPreId;

    /***
     *	String	是 品牌ID
     **/
    private String brandID;

    /***
     *	String	是 品牌CODE
     **/
    private String brandCode;

    /***
     *	String	是 品牌名称
     **/
    private String brandName;

    /***
     *	String	是 产地ID
     **/
    private String countryId;

    /***
     *	String	是 产地编码
     **/
    private String countryCode;

    /***
     *	String	是 产地名称
     **/
    private String countryName;

    /***
     *	String	是 单位ID
     **/
    private String unitID;

    /***
     *	String	是 单位编码
     **/
    private String unitCode;

    /***
     *	String	是 单位名称
     **/
    private String unitName;

    /***
     *	String	是 商品等级编码
     **/
    private String goods_level;

    /***
     *	array	是	运费模板  array变对象
     **/
    private WorldFreightTemplateNoticeDTO freight_template;

    /***
     *		String	是	是否包邮（1-包邮，0-不包邮）
     **/
    private String isFreePostFee;

    /***
     *	String	是	是否仅支持微信支付（1-是，0-不是）
     **/
    private String isNeedWeiXinPay;
    /***
     *		String	是	是否需要上传身份证正反面
     **/
    private String isNeedUploadIDCard;
    /***
     *	String	是	是否需要身份证
     **/
    private String isNeedIDCard;
    /***
     *		String	是	标签管理
     **/
    private List<String> tags;

    /***
     *		String	是	sku明细
     **/
    private List<WorldSkuDetailDTO> skuList;

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getIntroName() {
        return introName;
    }

    public void setIntroName(String introName) {
        this.introName = introName;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getGoods_extends_type() {
        return goods_extends_type;
    }

    public void setGoods_extends_type(String goods_extends_type) {
        this.goods_extends_type = goods_extends_type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryPid() {
        return categoryPid;
    }

    public void setCategoryPid(String categoryPid) {
        this.categoryPid = categoryPid;
    }

    public String getCategoryPreCode() {
        return categoryPreCode;
    }

    public void setCategoryPreCode(String categoryPreCode) {
        this.categoryPreCode = categoryPreCode;
    }

    public String getCategoryPreName() {
        return categoryPreName;
    }

    public void setCategoryPreName(String categoryPreName) {
        this.categoryPreName = categoryPreName;
    }

    public String getCategoryFPid() {
        return categoryFPid;
    }

    public void setCategoryFPid(String categoryFPid) {
        this.categoryFPid = categoryFPid;
    }

    public String getCategoryFPreCode() {
        return categoryFPreCode;
    }

    public void setCategoryFPreCode(String categoryFPreCode) {
        this.categoryFPreCode = categoryFPreCode;
    }

    public String getCategoryFPreName() {
        return categoryFPreName;
    }

    public void setCategoryFPreName(String categoryFPreName) {
        this.categoryFPreName = categoryFPreName;
    }

    public String getCategoryPreId() {
        return categoryPreId;
    }

    public void setCategoryPreId(String categoryPreId) {
        this.categoryPreId = categoryPreId;
    }

    public String getBrandID() {
        return brandID;
    }

    public void setBrandID(String brandID) {
        this.brandID = brandID;
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getUnitID() {
        return unitID;
    }

    public void setUnitID(String unitID) {
        this.unitID = unitID;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getGoods_level() {
        return goods_level;
    }

    public void setGoods_level(String goods_level) {
        this.goods_level = goods_level;
    }

    public WorldFreightTemplateNoticeDTO getFreight_template() {
        return freight_template;
    }

    public void setFreight_template(WorldFreightTemplateNoticeDTO freight_template) {
        this.freight_template = freight_template;
    }

    public String getIsFreePostFee() {
        return isFreePostFee;
    }

    public void setIsFreePostFee(String isFreePostFee) {
        this.isFreePostFee = isFreePostFee;
    }

    public String getIsNeedWeiXinPay() {
        return isNeedWeiXinPay;
    }

    public void setIsNeedWeiXinPay(String isNeedWeiXinPay) {
        this.isNeedWeiXinPay = isNeedWeiXinPay;
    }

    public String getIsNeedUploadIDCard() {
        return isNeedUploadIDCard;
    }

    public void setIsNeedUploadIDCard(String isNeedUploadIDCard) {
        this.isNeedUploadIDCard = isNeedUploadIDCard;
    }

    public String getIsNeedIDCard() {
        return isNeedIDCard;
    }

    public void setIsNeedIDCard(String isNeedIDCard) {
        this.isNeedIDCard = isNeedIDCard;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<WorldSkuDetailDTO> getSkuList() {
        return skuList;
    }

    public void setSkuList(List<WorldSkuDetailDTO> skuList) {
        this.skuList = skuList;
    }
}
