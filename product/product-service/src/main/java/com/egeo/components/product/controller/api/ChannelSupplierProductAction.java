package com.egeo.components.product.controller.api;

import com.alibaba.fastjson.JSON;
import com.egeo.components.product.business.ChannelSupplierProductManage;
import com.egeo.components.product.business.impl.Thread.CakeExportSearchThread;
import com.egeo.components.product.business.impl.Thread.CakeProductThread;
import com.egeo.components.product.business.impl.Thread.CommonThreadPoolExecutor;
import com.egeo.components.product.common.PageResults;
import com.egeo.components.product.common.ProductChannelCodeEnum;
import com.egeo.components.product.dto.CakeProductDetailDTO;
import com.egeo.components.product.dto.ChannelCategoryDTO;
import com.egeo.components.product.facade.ChannelCategoryFacade;
import com.egeo.components.product.facade.ChannelCategoryThirdFacade;
import com.egeo.components.product.vo.ChannelSupplierProductRequestVO;
import com.egeo.components.product.vo.ChannelSupplierProductResponseVO;
import com.egeo.components.utils.ExcelUtil;
import com.egeo.config.RuntimeContext;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.Upload;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;
import lombok.val;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
@Controller
@RequestMapping("/api/product/search")
public class ChannelSupplierProductAction extends BaseSpringController {

    @Resource(name="channelSupplierProductManage")
    private ChannelSupplierProductManage channelSupplierProductManage;

    @Autowired
    private ChannelCategoryFacade channelCategoryFacade;

    @Autowired
    private ChannelCategoryThirdFacade channelCategoryThirdFacade;
    @Resource
    private Upload uploadService;

    /**
     * @Description 后台查询渠道专区列表
     **/
    @RequestMapping(value = "/platform")
    @ResponseBody
    public JsonResult<PageResult<ChannelSupplierProductResponseVO>> platform(ChannelSupplierProductRequestVO vo){
        logger.info("后台查询渠道专区列表:{}",JSON.toJSONString(vo));
        Pagination page = new Pagination(vo.getPageNo(),vo.getPageSize());
        vo.setEnterpriseId(RuntimeContext.cacheUser().getEnterpriseId());
        JsonResult<PageResult<ChannelSupplierProductResponseVO>> rt = channelSupplierProductManage.platform(vo,page);
        PageResults<ChannelSupplierProductResponseVO> pageResults = new PageResults();
        pageResults.copy(page);
        if(Objects.nonNull(rt)){
            pageResults.copy(rt.getData());
            pageResults.setList(rt.getData().getList());
        }
        rt.setData(pageResults);
        if(Objects.nonNull(rt) && Objects.nonNull(rt.getData()) && Objects.nonNull(rt.getData().getList())){
            logger.info("返回记录数:{}",rt.getData().getList().size());
        }
        return rt;
    }

    /**
     * @Description 查询渠道类目列表
     **/
    @RequestMapping(value = "/getChannelCategoryList")
    @ResponseBody
    public JsonResult<List<ChannelCategoryDTO>> getChannelCategoryList(ChannelCategoryDTO dto){
        if(EmptyUtil.isEmpty(dto.getChannelCode())
                || EmptyUtil.isEmpty(dto.getChannelCategoryPId())
                || Objects.isNull(dto.getChannelCategoryLevel())){
            return JsonResult.fail("缺少必填参数");
        }
        //List<ChannelCategoryDTO> list = channelCategoryFacade.findChannelCategoryAll(dto);
        List<ChannelCategoryDTO> list =channelCategoryThirdFacade.findChannelCategoryAll(dto);
        return JsonResult.success(list);
    }


    // 业务方法：
    @RequestMapping(value = "/enterprise")
    @ResponseBody
    public JsonResult<PageResult<ChannelSupplierProductResponseVO>> enterprise(ChannelSupplierProductRequestVO vo) {
        Pagination page = new Pagination(vo.getPageNo(),vo.getPageSize());
        vo.setEnterpriseId(RuntimeContext.cacheUser().getEnterpriseId());
        PageResults<ChannelSupplierProductResponseVO> pageResults = new PageResults();
        JsonResult<PageResult<ChannelSupplierProductResponseVO>> rt =  channelSupplierProductManage.enterprise(vo,page);
        pageResults.copy(page);
        if(Objects.nonNull(rt)){
            pageResults.copy(rt.getData());
            pageResults.setList(rt.getData().getList());
        }
        rt.setData(pageResults);
        if(Objects.nonNull(rt) && Objects.nonNull(rt.getData()) && Objects.nonNull(rt.getData().getList())){
            logger.info("返回记录数:{}",rt.getData().getList().size());
        }
        return rt;
    }

    private static Integer MAX_EXPORT_CHANNEL_PRODUCT_PAGE_SIZE=1000;
    /**
     * 渠道产品导出
     * @param vo
     * @return
     */
    @RequestMapping(value = "/enterpriseExport")
    @ResponseBody
    public JsonResult<Map<String, Object>> enterpriseExport(ChannelSupplierProductRequestVO vo) {
        logger.info("渠道产品导出参数:{}", JSON.toJSONString(vo));
        if(EmptyUtil.isEmpty(vo.getKeyword()) || EmptyUtil.isBlank(vo.getKeyword())){
            vo.setKeyword(null);
        }
        vo.setEnterpriseId(RuntimeContext.cacheUser().getEnterpriseId());
        Pagination page = new Pagination(1,MAX_EXPORT_CHANNEL_PRODUCT_PAGE_SIZE);
        List<ChannelSupplierProductResponseVO> results=new ArrayList<>();
        if (Objects.equals(ProductChannelCodeEnum.CAKE.getCode(),vo.getChannelCode())){
            searchProductExport(page,vo,results);
        }else{
            JsonResult<PageResult<ChannelSupplierProductResponseVO>> pageResult=channelSupplierProductManage.enterprise(vo,page);
            while (EmptyUtil.isNotEmpty(pageResult.getData().getList())){
                results.addAll(pageResult.getData().getList());
                page.setPageNo(page.getPageNo()+1);
                pageResult=channelSupplierProductManage.enterprise(vo,page);
            }
        }
        logger.info("渠道{}共计导出记录数:{}",vo.getChannelCode(),results.size());
        String[] header = {"商品ID", "SKU名称","规格ID", "协议价格","销售价格", "市场价格", "销售毛利(%)","渠道"};
        return JsonResult.success("url",
                ExcelUtil.writeDataInExcel("渠道产品列表", header, results, new ExcelUtil.DoExcel<ChannelSupplierProductResponseVO>() {
            @Override
            public void setColumnValue(Row row, ChannelSupplierProductResponseVO item) {
                int columnIdx=0;
                row.createCell(columnIdx++).setCellValue(item.getProductId());
                row.createCell(columnIdx++).setCellValue(item.getProductName());
                row.createCell(columnIdx++).setCellValue(item.getSkuId());
                row.createCell(columnIdx++).setCellValue(Objects.isNull(item.getPrice())?"":item.getPrice().toPlainString());
                row.createCell(columnIdx++).setCellValue(Objects.isNull(item.getSalePrice())?"":item.getSalePrice().toPlainString());
                row.createCell(columnIdx++).setCellValue(Objects.isNull(item.getMarketPrice())?"":item.getMarketPrice().toPlainString());
                row.createCell(columnIdx++).setCellValue(Objects.isNull(item.getProfit())?"":item.getProfit().toPlainString()+"%");
                String channelName="-";
                Map<String,ProductChannelCodeEnum> productChannelCodeMap = ProductChannelCodeEnum.productChannelCodeMap;
                if(EmptyUtil.isNotEmpty(item.getChannelCode()) && !CollectionUtils.isEmpty(productChannelCodeMap)&& productChannelCodeMap.containsKey(item.getChannelCode())){
                    channelName = productChannelCodeMap.get(item.getChannelCode()).getDescription();
                }
                row.createCell(columnIdx).setCellValue(channelName);
            }
        },uploadService));
    }

    private void searchProductExport( Pagination page,ChannelSupplierProductRequestVO vo, List<ChannelSupplierProductResponseVO> resultList){
        JsonResult<PageResult<ChannelSupplierProductResponseVO>> pageResult=channelSupplierProductManage.enterprise(vo,page);
        if(Objects.isNull(pageResult) || Objects.isNull(pageResult.getData()) || EmptyUtil.isEmpty(pageResult.getData().getList())){
           return;
        }
        resultList.addAll(pageResult.getData().getList());
        int everyMaxThreadNum = 5;
        executeChannelProductSearchThread(page, vo, resultList, everyMaxThreadNum);
    }

    private void executeChannelProductSearchThread(Pagination page, ChannelSupplierProductRequestVO vo, List<ChannelSupplierProductResponseVO> resultList,  int everyMaxThreadNum) {
        ThreadPoolExecutor executor = CommonThreadPoolExecutor.getInstance();
        // 主线程优先拿到最先完成的任务的返回值，而不管它们加入线程池的顺序。
        CompletionService<JsonResult<PageResult<ChannelSupplierProductResponseVO>>> completionService = new ExecutorCompletionService<>(executor);
        List<Future<JsonResult<PageResult<ChannelSupplierProductResponseVO>>>> results = new ArrayList<>();
        Future<JsonResult<PageResult<ChannelSupplierProductResponseVO>>> future = null;
        Pagination searchPage = null;
        for (int i = 1; i<= everyMaxThreadNum; i++) {
            searchPage = new Pagination(page.getPageNo()+i,page.getPageSize());
            CakeExportSearchThread cakeProductThread = new CakeExportSearchThread(vo,searchPage);
            future = completionService.submit(cakeProductThread);
            results.add(future);
        }
        boolean isFinished = false;
        for (Future<JsonResult<PageResult<ChannelSupplierProductResponseVO>>> fut : results) {
            try {
                JsonResult<PageResult<ChannelSupplierProductResponseVO>> rt =  fut.get();
                if(EmptyUtil.isNotEmpty(rt.getData().getList())){
                    resultList.addAll(rt.getData().getList());
                }
                if(Objects.nonNull(rt.getData()) && CollectionUtils.isEmpty(rt.getData().getList())){
                    isFinished = true;
                }
            }catch (Exception e){
                logger.error("线程池中获取蛋糕叔叔商品发生异常:{}",e);
            }
        }
        if(isFinished){
            logger.info("渠道:{}最大{}页查询未找到商品了，需要跳出循环:{}",vo.getChannelCode(),page.getPageNo()+everyMaxThreadNum,JSON.toJSONString(vo));
            return;
        }
        searchPage = new Pagination(page.getPageNo()+everyMaxThreadNum,page.getPageSize());
        executeChannelProductSearchThread(searchPage, vo, resultList, everyMaxThreadNum);
    }

}
