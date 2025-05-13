package com.egeo.components.product.business;

import com.egeo.components.product.dto.*;
import com.egeo.orm.PageResult;
import com.egeo.web.JsonResult;

import java.util.List;

/**
 * @Description 蛋糕叔叔商品服务
 * @Author lsl
 * @Date 2024/4/28 17:17
 * @Version V1.0
 **/
public interface CakeProductManage {

    /**
     * 按二级类目搜索
     * @param search
     * @return
     */
    public JsonResult<List<CakeProductDTO>> searchOfCategoryLevel2(CakeProductSearchDTO search) ;

    /**
     * 查询商品详情
     * @param search
     * @return
     */
    public JsonResult<CakeProductDetailDTO> searchProductDetail(CakeProductDetailSearchDTO search) ;

    /**
     * @Description 商品状态查询接口
     * @Param productId 商品ID
     * @return 商品状态，1是上架，0是下架
     **/
    public JsonResult<String> searchProductStatus(String productId);

    /**
     * @Description 根据商品id列表和城市查询商品详情列表
     * @Param productIdList 商品id列表
     * @Param cityId 可为null
     **/
    List<CakeProductDetailDTO> getCakeProductDetailDTOList(List<String> productIdList,String cityId,Long enterpriseId);


    /**
     * 查询商品详情
     * @param search
     * @return
     */
    public JsonResult<CakeProductDetailDTO> searchProductDetail(CakeSPUIdSearchProductDetailDTO search) ;

    public JsonResult<PageResult<CakeSpecsProductDetailDTO>>  searchPlatform(CakeProductSearchDTO search);

    /**
     * 查询商品详情-已计算好价格
     * @param search
     * @return
     */
    public JsonResult<CakeProductDetailDTO> searchProductDetailCalcPrice(CakeSPUIdSearchProductDetailDTO search);

    /**
     * 查询商品详情且已计算好价格
     * @param search
     * @return
     */
    public JsonResult<CakeProductDetailDTO> searchProductCalcPriceDetail(CakeProductDetailSearchDTO search);

    /**
     * 按标题搜索
     * @param search
     * @return
     */
    public JsonResult<List<CakeProductDTO>> searchOfKeyWord(CakeProductSearchDTO search) ;

    public JsonResult<List<CakeProductDTO>> searchList(CakeProductSearchDTO search);

}
