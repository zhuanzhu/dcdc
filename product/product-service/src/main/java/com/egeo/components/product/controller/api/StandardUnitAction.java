package com.egeo.components.product.controller.api;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.egeo.components.product.common.PageResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.egeo.common.BusinessExceptionConstant;
import com.egeo.common.LogConstant;
import com.egeo.common.LogTypeConstant;
import com.egeo.components.product.business.StandardUnitManage;
import com.egeo.components.product.converter.StandardUnitConverter;
import com.egeo.components.product.dto.StandardUnitDTO;
import com.egeo.components.product.enums.SUConstant;
import com.egeo.components.product.vo.QuerySuDetailVO;
import com.egeo.components.product.vo.StandardUnitExportVO;
import com.egeo.components.product.vo.StandardUnitVO;
import com.egeo.components.promotion.dto.CouponUnitDTO;
import com.egeo.components.user.client.CompanyClient;
import com.egeo.components.user.client.EnterpriseClient;
import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.components.utils.JsonUtils;
import com.egeo.config.RuntimeContext;
import com.egeo.entity.CacheUser;
import com.egeo.exception.BusinessException;
import com.egeo.log.EgeoBusinessLogCommon;
import com.egeo.log.EgeoLog;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.ActiveMQUtils;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;


@Controller
@RequestMapping("/api/product/standardUnit")
public class StandardUnitAction extends BaseSpringController {

    @Resource(name = "standardUnit")
    private StandardUnitManage standardUnitManage;
    @Autowired
    private CompanyClient companyClient;
    @Autowired
    private EnterpriseClient enterpriseService;

    // 业务方法：
    @RequestMapping(value = "/findStandardUnitById")
    @ResponseBody
    public JsonResult<StandardUnitVO> findStandardUnitById(Long id) {

        StandardUnitDTO dto = new StandardUnitDTO();
        dto.setId(id);
        StandardUnitDTO rt = standardUnitManage.findStandardUnitById(dto);
        return JsonResult.success(StandardUnitConverter.toVO(rt));

    }


    // 业务方法：
    @RequestMapping(value = "/findStandardUnitAll")
    @ResponseBody
    public JsonResult<List<StandardUnitVO>> findStandardUnitAll(StandardUnitVO vo) {
        StandardUnitDTO dto = StandardUnitConverter.toDTO(vo);
        String str = RuntimeContext.currentRequest().getHeader("platformId");
        if (EmptyUtil.isNotEmpty(str)) {
            Long platformId = Long.valueOf(str);
            dto.setPlatformId(platformId);
        }
        List<StandardUnitDTO> rt = standardUnitManage.findStandardUnitAll(dto);
        return JsonResult.success(StandardUnitConverter.toVO(rt));

    }

    // 业务方法：
    @RequestMapping(value = "/findStandardUnitOfPage")
    @ResponseBody
    public JsonResult<PageResult<StandardUnitVO>> findStandardUnitOfPage(StandardUnitVO vo, Pagination page) {
        StandardUnitDTO dto = StandardUnitConverter.toDTO(vo);
        String str = RuntimeContext.currentRequest().getHeader("platformId");
        if (EmptyUtil.isNotEmpty(str)) {
            Long platformId = Long.valueOf(str);
            dto.setPlatformId(platformId);
        }
        PageResult<StandardUnitDTO> rt = standardUnitManage.findStandardUnitOfPage(dto, page);
        List<StandardUnitVO> list = StandardUnitConverter.toVO(rt.getList());
        PageResult<StandardUnitVO> result = new PageResult<StandardUnitVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());

        return JsonResult.success(result);

    }

    /**
     * 限购规则分页显示已上架su商品列表
     *
     * @param vo
     * @param page
     * @param req
     * @return
     */
    @RequestMapping(value = "/findStandardUnitMapOfPage")
    @ResponseBody
    public JsonResult<PageResult<Map<String, Object>>> findStandardUnitMapOfPage(StandardUnitVO vo, String standardUnitIds, Pagination page) {
        StandardUnitDTO dto = StandardUnitConverter.toDTO(vo);
        String str = RuntimeContext.currentRequest().getHeader("platformId");
        if (EmptyUtil.isNotEmpty(str)) {
            Long platformId = Long.valueOf(str);
            dto.setPlatformId(platformId);
        }
        // 判断前端传递suid集合是否是null，如果不为null并且id数量为0直接返回空列表
        if (standardUnitIds != null) {
            List<Long> standardUnitIdList = JSONArray.parseArray(standardUnitIds, Long.class);
            if (standardUnitIdList.size() <= 0) {
                PageResult<Map<String, Object>> result = new PageResult<Map<String, Object>>();
                List<Map<String, Object>> list = new ArrayList<>();
                result.setList(list);
                result.setPageNo(page.getPageNo());
                result.setPageSize(page.getPageSize());
                result.setTotalSize(0);
                return JsonResult.success(result);
            } else {
                // 设置默认值、已上架
                dto.setStatus(SUConstant.SU_STATUS_RECEIVED_GOODS.getStatus());
                // 限购规则分页显示已上架su商品列表
                PageResult<Map<String, Object>> rt = standardUnitManage.findStandardUnitMapOfPage(dto, page, standardUnitIdList);
            }

        }
        // 设置默认值、已上架
        dto.setStatus(SUConstant.SU_STATUS_RECEIVED_GOODS.getStatus());
        // 限购规则分页显示已上架su商品列表
        PageResult<Map<String, Object>> rt = standardUnitManage.findStandardUnitMapOfPage(dto, page, null);

        return JsonResult.success(rt);

    }

    /**
     * app商品列表(第一版、现已不用)
     *
     * @param vo
     * @param page
     * @param req
     * @return
     */
    @RequestMapping(value = "/findStandardUnitOfPageAPP")
    @ResponseBody
    public JsonResult<PageResult<Map<String, Object>>> findStandardUnitOfPageAPP(StandardUnitVO vo, Pagination page) {
        StandardUnitDTO dto = StandardUnitConverter.toDTO(vo);
        String str = RuntimeContext.currentRequest().getHeader("platformId");
        if (EmptyUtil.isNotEmpty(str)) {
            Long platformId = Long.valueOf(str);
            dto.setPlatformId(platformId);
        }
        String clientId = RuntimeContext.currentRequest().getHeader("clientId");
        if (EmptyUtil.isNotEmpty(str)) {
            Long clientId_ = Long.valueOf(clientId);
            dto.setClientId(clientId_);
        }
        CacheUser userCache = this.getCacheUser();
        Long companyId = userCache.getCompanyId();
        dto.setCompanyId(companyId);
        PageResult<Map<String, Object>> rt = standardUnitManage.findStandardUnitOfPageAPP(dto, page);
        JsonResult<PageResult<Map<String, Object>>> jsonResult = new JsonResult<>();

        if (EmptyUtil.isEmpty(rt.getList())) {
            jsonResult.setCode(1);
            jsonResult.setData(null);
            return jsonResult;
        } else {
            jsonResult.setData(rt);
            return jsonResult;
        }

    }


    // 业务方法：返回是插入行的id
    @RequestMapping(value = "/insertStandardUnitWithTx")
    @ResponseBody
    public JsonResult<Long> insertStandardUnitWithTx(StandardUnitVO vo) {
        StandardUnitDTO dto = StandardUnitConverter.toDTO(vo);
        String str = RuntimeContext.currentRequest().getHeader("platformId");
        if (EmptyUtil.isNotEmpty(str)) {
            Long platformId = Long.valueOf(str);
            dto.setPlatformId(platformId);
        }
        Long rt = standardUnitManage.insertStandardUnitWithTx(dto);
        return JsonResult.success(rt);
    }

    // 业务方法：根据id更新数据
    @RequestMapping(value = "/updateStandardUnitByIdWithTx")
    @ResponseBody
    public JsonResult<Integer> updateStandardUnitByIdWithTx(StandardUnitVO vo) {
        StandardUnitDTO dto = StandardUnitConverter.toDTO(vo);
        String str = RuntimeContext.currentRequest().getHeader("platformId");
        if (EmptyUtil.isNotEmpty(str)) {
            Long platformId = Long.valueOf(str);
            dto.setPlatformId(platformId);
        }
        int rt = standardUnitManage.updateStandardUnitWithTx(dto);
        return JsonResult.success(rt);
    }

    // 业务方法：
    @RequestMapping(value = "/deleteStandardUnitWithTx")
    @ResponseBody
    public JsonResult<Integer> deleteStandardUnitWithTx(StandardUnitVO vo) {
        StandardUnitDTO dto = StandardUnitConverter.toDTO(vo);
        String str = RuntimeContext.currentRequest().getHeader("platformId");
        if (EmptyUtil.isNotEmpty(str)) {
            Long platformId = Long.valueOf(str);
            dto.setPlatformId(platformId);
        }
        int rt = standardUnitManage.deleteStandardUnitWithTx(dto);
        return JsonResult.success(rt);
    }




    @RequestMapping("/updateSuVisible")
    @ResponseBody
    public JsonResult<String> updateSuVisible(Integer status, Long standardUnitId){
        if(EmptyUtil.isEmpty(standardUnitId)){
            return JsonResult.fail("suId缺失");
        }
        if(EmptyUtil.isEmpty(status)){
            return JsonResult.fail("状态缺失");
        }
        StandardUnitDTO standardUnitDTO = new StandardUnitDTO();
        standardUnitDTO.setId(standardUnitId);
        standardUnitDTO.setIsVisible(status);
        int i=standardUnitManage.updateSuVisible(standardUnitDTO);
        int j=standardUnitManage.updateMerchantProductVisible(standardUnitId,status);
        if(i>0&&j>0){
            return JsonResult.success("ok");

        }else{
            return JsonResult.fail("更新失败");
        }

    }

    /**
     * 根据suId上下架su商品
     *
     * @param status
     * @param id
     * @param type
     * @param req
     * @return
     */
    @RequestMapping(value = "/putawaySoldOut")
    @ResponseBody
    public JsonResult<Integer> putawaySoldOut(Integer status, Long id, int type) {
        StandardUnitDTO dto = new StandardUnitDTO();
        String str = RuntimeContext.currentRequest().getHeader("platformId");
        if (EmptyUtil.isNotEmpty(str)) {
            Long platformId = Long.valueOf(str);
            dto.setPlatformId(platformId);
        }
        if (EmptyUtil.isEmpty(id)) {
            return JsonResult.fail("请选择商品");
        }
        if (EmptyUtil.isEmpty(status)) {
            return JsonResult.fail("请选择上架或下架");
        }
        logger.info("根据suId上下架su商品,suId = {}", id);
        dto.setId(id);
        dto.setStatus(status);
        StandardUnitDTO oldObj = standardUnitManage.findStandardUnitById(dto);

        int rt = standardUnitManage.putawaySoldOut(dto, type);

        StandardUnitDTO newObj = standardUnitManage.findStandardUnitById(dto);

        EgeoLog log = new EgeoLog();
        if (status == 3) {
            log.setMsgId(LogConstant.STANDARDUNIT_PUTAWAY.getStatus());
        }
        if (status == 4) {
            log.setMsgId(LogConstant.STANDARDUNIT_DOWN_SHELF.getStatus());
        }
        log.setModuleName(LogConstant.PRODUCT_MANAGEMENT.getComment());
        log.setOperObject("StandardUnitAction_putawaySoldOut");
        log.setOldObj(oldObj);
        log.setNewObj(newObj);
        log.setOperatorObjId(id);
        log.setType(LogTypeConstant.STANDARDUNIT.getStatus());
        log.setOperatorObjName(oldObj.getName());
        log.setOperatorObjCode(oldObj.getMerchantProductSerialNumber());
        EgeoBusinessLogCommon.fillLogValue(log, RuntimeContext.currentRequest());

		try {
			ActiveMQUtils.recordBusinessLog(log);
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("发送日志消息失败："+ JSON.toJSONString(log));
		}
        return JsonResult.success(rt);
    }

    /**
     * 分页显示所有在线su的库存信息
     *
     * @param vo
     * @param req
     * @return
     */
    @RequestMapping(value = "/findStandardUnitOfPageStock")
    @ResponseBody
    public JsonResult<PageResult<Map<String, Object>>> findStandardUnitOfPageStock(StandardUnitVO vo, Pagination page) {
        logger.info("分页显示所有在线su的库存信息");
        StandardUnitDTO dto = StandardUnitConverter.toDTO(vo);
        String str = RuntimeContext.currentRequest().getHeader("platformId");
        if (EmptyUtil.isNotEmpty(str)) {
            Long platformId = Long.valueOf(str);
            dto.setPlatformId(platformId);
        }
        PageResult<Map<String, Object>> rt = standardUnitManage.findStandardUnitOfPageStock(dto, page);
        return JsonResult.success(rt);
    }

    /**
     * 根据suid查询在线su的库存信息
     *
     * @param StandardUnitId
     * @return
     */
    // 业务方法：
    @RequestMapping(value = "/findStandardUnitStockById")
    @ResponseBody
    public JsonResult<Map<String, Object>> findStandardUnitStockById(Long StandardUnitId) {
        logger.info("根据suid查询在线su的库存信息,suId = {}", StandardUnitId);
        StandardUnitDTO dto = new StandardUnitDTO();
        dto.setId(StandardUnitId);
        Map<String, Object> map = standardUnitManage.findStandardUnitStockById(dto);
        return JsonResult.success(map);

    }

    /**
     * 根据类目节点id查询su商品信息
     *
     * @param vo
     * @param req
     * @return
     */
    @RequestMapping(value = "/standardUnitStockByCategoryTreeNodeId")
    @ResponseBody
    public JsonResult<PageResult<Map<String, Object>>> standardUnitStockByCategoryTreeNodeId(StandardUnitVO vo, Pagination page) {
        logger.info("根据类目节点id查询su商品信息");
        StandardUnitDTO dto = StandardUnitConverter.toDTO(vo);
        String str = RuntimeContext.currentRequest().getHeader("platformId");
        if (EmptyUtil.isNotEmpty(str)) {
            Long platformId = Long.valueOf(str);
            dto.setPlatformId(platformId);
        }
        PageResult<Map<String, Object>> rt = standardUnitManage.standardUnitStockByCategoryTreeNodeId(dto, page);
        return JsonResult.success(rt);

    }

    /**
     * 根据功能模版id查询su商品信息
     *
     * @param vo
     * @param req
     * @return
     */
    @RequestMapping(value = "/standardUnitByFunctionModuleId")
    @ResponseBody
    public JsonResult<PageResult<Map<String, Object>>> standardUnitByFunctionModuleId(StandardUnitVO vo, Long functionModuleId, Pagination page) {
        logger.info("根据功能模版id查询su商品信息,功能模版id = {}", functionModuleId);
        StandardUnitDTO dto = StandardUnitConverter.toDTO(vo);
        String str = RuntimeContext.currentRequest().getHeader("platformId");
        if (EmptyUtil.isNotEmpty(str)) {
            Long platformId = Long.valueOf(str);
            dto.setPlatformId(platformId);
        }
        PageResult<Map<String, Object>> rt = standardUnitManage.standardUnitByFunctionModuleId(dto, functionModuleId, page);
        return JsonResult.success(rt);

    }


    /**
     * 根据suid查询su详情
     *
     * @param standardUnitId
     * @return
     */
    @RequestMapping(value = "/findByStandardUnitId")
    @ResponseBody
    public JsonResult<Map<String, Object>> findByStandardUnitId(Long standardUnitId, Long storeId,Integer source,String channelProductId) {
        logger.info("根据suid查询su详情,suId = {}", standardUnitId);
        if(EmptyUtil.isEmpty(standardUnitId)){
            return JsonResult.fail("suId缺失");
        }
        //standardUnitManage.checkJdProductDetail(standardUnitId);
        String str = RuntimeContext.currentRequest().getHeader("platformId");
        Long platformId = null;
        if (EmptyUtil.isNotEmpty(str)) {
            platformId = Long.valueOf(str);
        }
        if(EmptyUtil.isEmpty(storeId)&&platformId.equals(7L)){
            storeId=1L;
        }
        String client_id = RuntimeContext.currentRequest().getHeader("clientId");
        Long clientId = null;
        if (EmptyUtil.isNotEmpty(client_id)) {
            clientId = Long.valueOf(client_id);
        } else {
            throw new BusinessException("客户端id不能为空");
        }
        getCacheUser(false);
        CacheUser userCache = RuntimeContext.cacheUser();
        // 如果没有ut则创建赋默认正式公司id
        if (EmptyUtil.isEmpty(userCache)) {
            userCache = new CacheUser();
            userCache.setCompanyId(1L);
        }
        Map<String, Object> rt = standardUnitManage.standardUnitByStandardUnitId(standardUnitId, userCache.getCompanyId(), platformId, clientId, storeId, userCache.getId(),source,channelProductId);

        return JsonResult.success(rt);
    }

    /**
     * 判断兑换券是否有效
     */
    @RequestMapping(value = "/queryCouponIsEffected")
    @ResponseBody
    public  JsonResult queryCouponIsEffected(Long storeId,Long couponUnitId,Long suId,Long puId,Integer type) {

        logger.info("[校验优惠券有效性接口]参数:storeId"+storeId);
        logger.info("[校验优惠券有效性接口]参数:couponUnitId"+couponUnitId);
        logger.info("[校验优惠券有效性接口]参数:suId"+suId);
        logger.info("[校验优惠券有效性接口]参数:puId"+puId);
        logger.info("[校验优惠券有效性接口]参数:type"+type);
        if(type==1){
            //校验su上下架
            if(EmptyUtil.isEmpty(suId)){
                return JsonResult.fail("suId缺失");
            }
            Integer status=standardUnitManage.findSuStatus(suId);
            if(EmptyUtil.isEmpty(status)){
                return JsonResult.fail("su数据有误,联系管理员");
            }
            if(status.equals(4)){
                return fail(BusinessExceptionConstant.MERCHANTPRODUCT_SOLD_OUT,"商品已下架");
            }

        }

        CouponUnitDTO couponUnitDTO = standardUnitManage.findCouponUnitByCouponUnitId(couponUnitId);
        if(EmptyUtil.isEmpty(couponUnitDTO)){
            return JsonResult.fail("优惠券不存在");
        }
        Date now = new Date();
        //coupon_unit_status 0:未使用 1:已使用 2:已冻结 3:已过期(此状态要结合失效时间一起判断) 4:已失效',
        //判断过期时间
        if (EmptyUtil.isNotEmpty(couponUnitDTO.getEffectStartTime())) {
            if ( now.before(couponUnitDTO.getEffectStartTime()) ) {
                return fail(BusinessExceptionConstant.PRE_COUPON_TIME,"优惠券未生效");

            }
        }
        if (EmptyUtil.isNotEmpty(couponUnitDTO.getEffectEndTime())) {
            if ( now.after(couponUnitDTO.getEffectEndTime()) || couponUnitDTO.getCouponUnitStatus() == 2 ||
                    couponUnitDTO.getCouponUnitStatus() == 3 || couponUnitDTO.getCouponUnitStatus() == 4 ||
                    couponUnitDTO.getCouponUnitStatus() == 1) {
                return fail(BusinessExceptionConstant.COUPON_INVALID,"优惠券已失效");
            }
        }



        if(type==3){
            if(EmptyUtil.isEmpty(puId)){
                return JsonResult.fail("puId缺失");
            }
            //校验pu库存
            Long puStock=standardUnitManage.findPuStock(puId,storeId);
            if(EmptyUtil.isEmpty(puStock)||puStock<=0){
                return fail(BusinessExceptionConstant.PRODUCT_INSUFFICIENT,"库存不足");
            }
        }
        return JsonResult.success("ok");
    }


    /**
     * 根据条件查询所有上架suid和名称
     *
     * @param vo
     * @param req
     * @return
     */
    @RequestMapping(value = "/findStandardUnitIdAndName")
    @ResponseBody
    public JsonResult<List<Map<String, Object>>> findStandardUnitIdAndName(StandardUnitVO vo) {
        logger.info("根据条件查询所有上架suid和名称");
        StandardUnitDTO dto = StandardUnitConverter.toDTO(vo);
        String str = RuntimeContext.currentRequest().getHeader("platformId");
        if (EmptyUtil.isNotEmpty(str)) {
            Long platformId = Long.valueOf(str);
            dto.setPlatformId(platformId);
        }
        List<Map<String, Object>> rt = standardUnitManage.findStandardUnitIdAndName(dto);
        return JsonResult.success(rt);

    }

    /**
     * 根据类目节点id或商品组合id查询su商品列表
     *
     * @param categoryTreeNodeId        前台类目节点id
     * @param standardUnitCombinationId su组合id (1)
     * @param type                      是那里过来的接口：1、类目 2、su组合 (2)
     * @param fubiPay                   是否限制显示大于积分余额商品：0否1是
     * @param orderByType               0、默认 1、销量、2、价格
     * @param sortRegulation            0、默认 1、升序、2降序
     * @param couponUnitId              优惠卷unit的id
     * @return
     */
    @RequestMapping(value = "/findByCategoryTreeNodeIdOrSUC")
    @ResponseBody
    public JsonResult<PageResult<Map<String, Object>>> findByCategoryTreeNodeIdOrSUCombination(Long categoryTreeNodeId,Integer buyType,
    																							 Long standardUnitCombinationId,  Integer type,
                                                                                               Integer fubiPay,
                                                                                                Integer orderByType,
                                                                                               Integer sortRegulation,
                                                                                                Integer saleWay,
                                                                                               Long couponUnitId, Long storeId, Integer pageSize,Integer pageNo,
                                                                                               String keyWord) {
        logger.info("根据su组合id或类目节点id查询susp列表:categoryTreeNodeId:{},standardUnitCombinationId:{},购买方式buyType:{},查询类型type:{}",categoryTreeNodeId,standardUnitCombinationId,buyType,type);
        String str = RuntimeContext.currentRequest().getHeader("platformId");
        Long platformId = null;
        Pagination page = new Pagination(pageNo, pageSize);
        if (EmptyUtil.isNotEmpty(str)) {
            platformId = Long.valueOf(str);
        } else {
            throw new BusinessException("平台id不能为空");
        }

        String client_id = RuntimeContext.currentRequest().getHeader("clientId");
        Long clientId = null;
        if (EmptyUtil.isNotEmpty(client_id)) {
            clientId = Long.valueOf(client_id);
        } else {
            throw new BusinessException("客户端id不能为空");
        }
        CacheUser userCache = this.getCacheUser(false);
        //用户id
        Long userId = userCache.getId();
        //公司id
        Long companyId = userCache.getCompanyId();
        Long enterrprisetId = userCache.getEnterpriseId();
        if (EmptyUtil.isEmpty(companyId)) {
            throw new BusinessException("公司id为空");
        }
        if(enterrprisetId==null) {
        	CompanyDTO company = companyClient.findCompanyById(RuntimeContext.cacheUser().getCompanyId());
        	if(company.getEnterpriseId()!=null) {
        		logger.info("用户："+userCache.getLoginName()+"登录："+ buyType+" 公司id:"+companyId.longValue()+" 代理id为空并填充成功");
        		RuntimeContext.cacheUser().setEnterpriseId(company.getEnterpriseId());
        		enterrprisetId = company.getEnterpriseId();
        	}
        }
        if (EmptyUtil.isEmpty(type))
            return JsonResult.fail("跳转类型不能为空");
        if(orderByType!=null && orderByType.intValue()==1) {
        	switch (sortRegulation) {
	            case 0:
	            case 1:
	                page.setOrderBy("sales_volume");
	                break;
	            case 2:
	                page.setOrderBy("sales_volume desc");
	                break;
	        }
        }
        if(orderByType!=null && orderByType.intValue()==2) {
        	switch (sortRegulation) {
	            case 0:
	            case 1:
	                page.setOrderBy("su.sale_price");
	                break;
	            case 2:
	                page.setOrderBy("su.sale_price desc");
	                break;
	        }
        }

        if(EmptyUtil.isEmpty(storeId)){
            if(platformId==7){
                storeId=1L;
            }
            if(platformId==2){
                storeId=2L;
            }
        }
        if(EmptyUtil.isEmpty(saleWay)||saleWay==0){
            saleWay = null;
        }
        PageResult<Map<String, Object>> result = standardUnitManage.findByCategoryTreeNodeIdOrSUCombination(saleWay,categoryTreeNodeId,
                standardUnitCombinationId, type, fubiPay, userId, clientId,enterrprisetId, companyId, platformId, couponUnitId, page, storeId, buyType,1,keyWord);
        PageResults results=new PageResults();
        results.copy(result);
        results.setList(result.getList());
        return JsonResult.success(results);

    }

    /**
     * su商品组合选择商品_su商品列表
     *
     * @param standardUnitCombinationId
     * @param standardUnitName
     * @param status
     * @param saleWay
     * @param page
     * @param req
     * @return
     */
    @RequestMapping(value = "/findBaseByConditionOfPage")
    @ResponseBody
    public JsonResult<PageResult<Map<String, Object>>> findBaseByConditionOfPage(
            Long standardUnitCombinationId, String standardUnitName, Integer status,Long cateNodeId,Long supplierId, Integer saleWay, Long merchantId, Pagination page) {
        String str = RuntimeContext.currentRequest().getHeader("platformId");
        Long platformId = null;
        if (EmptyUtil.isNotEmpty(str)) {
            platformId = Long.valueOf(str);
        } else {
            throw new BusinessException("平台id不能为空");
        }
        if (EmptyUtil.isEmpty(standardUnitCombinationId)) {
            throw new BusinessException("su商品组合id不能为空");
        }
        StandardUnitDTO standardUnitDTO = new StandardUnitDTO();
        standardUnitDTO.setPlatformId(platformId);
        standardUnitDTO.setName(standardUnitName);
        standardUnitDTO.setStatus(status);
        standardUnitDTO.setSaleWay(saleWay);
        standardUnitDTO.setMerchantId(merchantId);
        standardUnitDTO.setMerchantCateTreeNodeId(cateNodeId);
        standardUnitDTO.setSupplierId(supplierId);
        PageResult<Map<String, Object>> result = standardUnitManage.findBaseByConditionOfPage(standardUnitCombinationId, standardUnitDTO, page);
        return JsonResult.success(result);
    }

    /**
     * 根据su商品名称查询所有su商品
     *
     * @param standardUnitName
     * @return
     */
    @RequestMapping(value = "/findByStandardUnitName")
    @ResponseBody
    public JsonResult<List<Map<String, Object>>> findByStandardUnitName(Long standardUnitId, String standardUnitName,HttpServletRequest req) {
        String str = RuntimeContext.currentRequest().getHeader("platformId");
        Long platformId;
        if(EmptyUtil.isEmpty(str)){
            return JsonResult.fail("platformId不能为空");
        }else{
            platformId= Long.valueOf(str);
        }
        List<Map<String, Object>> result = standardUnitManage.findByStandardUnitName(platformId,standardUnitId, standardUnitName);
        return JsonResult.success(result);
    }

    /**
     * 根据suid查询su真实数据
     *
     * @param standardUnitId
     * @return
     */
    @RequestMapping(value = "/queryByStandardUnitId")
    @ResponseBody
    public JsonResult<StandardUnitExportVO> queryByStandardUnitId(Long standardUnitId) {
        logger.info("根据suid查询su真实数据{}",standardUnitId);
        StandardUnitExportVO rt = standardUnitManage.queryByStandardUnitId(standardUnitId);
        return JsonResult.success(rt);
    }


    /**
     * 查询正式已上架su列表
     *
     * @param vo
     * @param page
     * @return
     */
    @RequestMapping(value = "/queryStandardUnitListByBlurry")
    @ResponseBody
    public JsonResult<PageResult<Map<String, Object>>> queryStandardUnitListByBlurry(StandardUnitVO vo, Long excludeId, Pagination page) {
        logger.info("查询正式表su列表");
        String str = RuntimeContext.currentRequest().getHeader("platformId");
        if (EmptyUtil.isNotEmpty(str)) {
            Long platformId = Long.valueOf(str);
            vo.setPlatformId(platformId);
        }
        return JsonResult.success(standardUnitManage.queryStandardUnitListByBlurry(StandardUnitConverter.toDTO(vo), excludeId, page));
    }

    /**
     * 根据关键词搜索商品
     *
     * @param name
     * @param fubiPay
     * @param orderByType
     * @param sortRegulation
     * @param page
     * @param req
     * @return
     */
    @RequestMapping(value = "/findByKeywordOfPage")
    @ResponseBody
    public JsonResult<PageResult<Map<String, Object>>> findByKeywordOfPage(
            Integer buyType,
            Integer saleWay,
            Long storeId,
            String name,
            @RequestParam(value = "fubiPay", defaultValue = "0") Integer fubiPay,
            @RequestParam(value = "orderByType", defaultValue = "0") Integer orderByType,
            @RequestParam(value = "sortRegulation", defaultValue = "0") Integer sortRegulation,
            Pagination page) {
        Long time1 = System.currentTimeMillis();
        if (EmptyUtil.isNotEmpty(name)) {
            name = name.trim();
    	}
        logger.info("根据关键词搜索商品");
        logger.info("------------------------------orderby---------------------:" + orderByType);
        logger.info("购买方式buyType："+ buyType);
        String str = RuntimeContext.currentRequest().getHeader("platformId");
        Long platformId = null;
        if (EmptyUtil.isNotEmpty(str)) {
            platformId = Long.valueOf(str);
        } else {
            throw new BusinessException("平台id不能为空");
        }
        String client_id = RuntimeContext.currentRequest().getHeader("clientId");
        Long clientId = null;
        if (EmptyUtil.isNotEmpty(client_id)) {
            clientId = Long.valueOf(client_id);
        } else {
            throw new BusinessException("客户端id不能为空");
        }
        /*if(EmptyUtil.isEmpty(storeId)&&platformId==7){
            storeId=1L;
        }*/
        if(EmptyUtil.isEmpty(storeId)){
            if(platformId==7L){
                storeId=1L;
            }else if(platformId==2L){
                storeId = 2L;
            }
        }
        if(EmptyUtil.isEmpty(storeId)){
            return JsonResult.fail("storeId不能为空");
        }
        CacheUser userCache = this.getCacheUser(false);
        //用户id
        Long userId = userCache.getId();
        //公司id
        Long companyId = userCache.getCompanyId();
        if (EmptyUtil.isEmpty(companyId)) {
            throw new BusinessException("公司id为空");
        }
        if (EmptyUtil.isEmpty(name))
            throw new BusinessException("搜索关键词不能为空");
        if(orderByType ==null || (orderByType.intValue() ==0 && sortRegulation.intValue() ==0)){
            orderByType =2;
            sortRegulation =1;
        }
        if(orderByType!=null && orderByType.intValue()==1) {
        	switch (sortRegulation) {
	            case 0:
	            case 1:
	                page.setOrderBy("sales_volume");
	                break;
	            case 2:
	                page.setOrderBy("sales_volume desc");
	                break;
	        }
        }
        if(orderByType!=null && orderByType.intValue()==2) {
        	switch (sortRegulation) {
	            case 0:
	            case 1:
	                page.setOrderBy("su.sale_price");
	                break;
	            case 2:
	                page.setOrderBy("su.sale_price desc");
	                break;
	        }
        }
        PageResult<Map<String, Object>> byKeywordOfPage = standardUnitManage.findByKeywordOfPage(saleWay, storeId, name, userId, fubiPay, clientId, companyId, platformId, page, buyType);
        Long time2 = System.currentTimeMillis() - time1;
        logger.info("耗时:"+time2);
        return JsonResult.success(byKeywordOfPage);
    }

    /**
     * 刷新su商品搜索规则数据信息
     *
     * @return
     */
    @RequestMapping(value = "/syncSaveSuSerachRule")
    @ResponseBody
    public JsonResult<Map<String, Object>> syncSaveSuSerachRule() {
        logger.info("刷新su商品搜索规则数据信息");
        return standardUnitManage.syncSaveSuSerachRule();
    }


    /**
     * 商品组合,关联前台目录树,商品详情展示,根据类目节点id查询su商品信息
     *
     * @param categoryTreeNodeIds
     * @param vo
     * @param page
     * @param req
     * @return
     */
    @RequestMapping(value = "/querySuByCategoryTreeNodeIds", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<PageResult<Map<String, Object>>> querySuByCategoryTreeNodeIds(String categoryTreeNodeIds,
                                                                                    QuerySuDetailVO vo, Pagination page) throws Exception {
        Map<String, Object> reqParam = new HashMap<>();
        List<Long> categoryTreeNodeIdsList = new ArrayList<>();
        if (EmptyUtil.isNotEmpty(categoryTreeNodeIds)) {
            categoryTreeNodeIdsList = JsonUtils.jsonToPojo(categoryTreeNodeIds, List.class);
        }
        String str = RuntimeContext.currentRequest().getHeader("platformId");
        Long platformId = null;
        if (EmptyUtil.isNotEmpty(str)) {
            platformId = Long.valueOf(str);
        } else {
            throw new BusinessException("平台id不能为空");
        }

        if (!EmptyUtil.isEmpty(vo)) {
            if (EmptyUtil.isNotEmpty(vo.getName())) {
                reqParam.put("name", vo.getName());
            }
            if (EmptyUtil.isNotEmpty(vo.getMerchantProductSerialNumber())) {
                reqParam.put("merchantProductSerialNumber", vo.getMerchantProductSerialNumber());
            }
            if (EmptyUtil.isNotEmpty(vo.getBeginSalePrice())) {
                reqParam.put("beginSalePrice", vo.getBeginSalePrice());
            }
            if (EmptyUtil.isNotEmpty(vo.getEndSalePrice())) {
                reqParam.put("endSalePrice", vo.getEndSalePrice());
            }
            if (EmptyUtil.isNotEmpty(vo.getBeginPromotionPrice())) {
                reqParam.put("beginPromotionPrice", vo.getBeginPromotionPrice());
            }
            if (EmptyUtil.isNotEmpty(vo.getEndPromotionPrice())) {
                reqParam.put("endPromotionPrice", vo.getEndPromotionPrice());
            }
            if (EmptyUtil.isNotEmpty(vo.getStatus())) {
                reqParam.put("status", vo.getStatus());
            }
            if (EmptyUtil.isNotEmpty(vo.getIsVisible())) {
                reqParam.put("isVisible", vo.getIsVisible());
            }
            if (EmptyUtil.isNotEmpty(platformId)) {
                reqParam.put("platformId", platformId);
            }
        }
        PageResult<Map<String, Object>> rt = standardUnitManage.querySuByCategoryTreeNodeIds(page,
                categoryTreeNodeIdsList, reqParam);
        return JsonResult.success(rt);
    }

    /**
     * 根据门店菜单id查询su商品列表
     *
     * @param storeMenuNodeId 门店菜单id
     * @param page
     * @return
     */
    @RequestMapping(value = "/findStandardUnitByStoreMenuIdOfPage")
    @ResponseBody
    public JsonResult<Map<String, Object>> findStandardUnitByStoreMenuIdOfPage(
            Long storeMenuNodeId, Long storeId, Integer salesVolumeSort, Integer salePriceSort, Pagination page) {
        logger.info("根据门店菜单id查询su商品列表，门店菜单id：{}", storeMenuNodeId);
        this.getCacheUser(false);
        String str = RuntimeContext.currentRequest().getHeader("platformId");
        Long platformId = null;
        if (EmptyUtil.isNotEmpty(str)) {
            platformId = Long.valueOf(str);
        } else {
            throw new BusinessException("平台id不能为空");
        }
        if (EmptyUtil.isNotEmpty(salesVolumeSort)) {
            if (salesVolumeSort == 1) {
                page.setOrderBy("sales_volume");
            }
            if (salesVolumeSort == 2) {
                page.setOrderBy("sales_volume desc");
            }
        }
        if (EmptyUtil.isNotEmpty(salePriceSort)) {
            if (salePriceSort == 1) {
                page.setOrderBy("su.sale_price");
            }
            if (salePriceSort == 2) {
                page.setOrderBy("su.sale_price desc");
            }
        }
        Map<String, Object> data = standardUnitManage.findStandardUnitByStoreMenuIdOfPage(
                storeMenuNodeId, storeId, platformId, page);
        return JsonResult.success(data);
    }

    /**
     * 共用库存关联商品页面
     * @param vo
     * @param page
     * @param req
     * @return
     */
    @RequestMapping(value = "/findStandardUnitBySpuId")
    @ResponseBody
    public JsonResult<PageResult<StandardUnitVO>> findStandardUnitBySpuId(StandardUnitVO vo, Pagination page) {
        StandardUnitDTO dto = StandardUnitConverter.toDTO(vo);
        String str = RuntimeContext.currentRequest().getHeader("platformId");
        if (EmptyUtil.isNotEmpty(str)) {
            Long platformId = Long.valueOf(str);
            dto.setPlatformId(platformId);
        }

        PageResult<StandardUnitDTO> rt = standardUnitManage.findStandardUnitOfPage(dto, page);
        List<StandardUnitVO> list = StandardUnitConverter.toVO(rt.getList());
        PageResult<StandardUnitVO> result = new PageResult<StandardUnitVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());

        return JsonResult.success(result);

    }
}
