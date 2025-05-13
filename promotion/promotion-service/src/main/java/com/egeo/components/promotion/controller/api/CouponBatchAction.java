package com.egeo.components.promotion.controller.api;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONArray;
import com.egeo.components.promotion.business.CouponBatchManage;
import com.egeo.components.promotion.converter.CouponBatchConverter;
import com.egeo.components.promotion.dto.CouponBatchDTO;
import com.egeo.components.promotion.dto.CouponUnitDTO;
import com.egeo.components.promotion.vo.CouponBatchVO;
import com.egeo.components.user.dto.QrCodeDTO;
import com.egeo.components.utils.JsonUtils;
import com.egeo.core.Constant.BusinessExceptionConstant;
import com.egeo.entity.CacheUser;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.CodeUtils;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.SequenceUtil;
import com.egeo.utils.Upload;
import com.egeo.utils.excel2.ExcelUtil;
import com.egeo.utils.str.StringUtils;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Controller
@RequestMapping("/api/promotion/couponBatch")
public class CouponBatchAction extends BaseSpringController {
    private Gson gson = new Gson();
    @Resource(name = "couponBatch")
    private CouponBatchManage couponBatchManage;

	@Autowired
	private Upload uploadService;


    @RequestMapping(value = "userImport")
    @ResponseBody
    public JsonResult<Map<String,Object>> userImport(HttpServletRequest req){
        logger.info("优惠券系统发放员工的导入");
        String platformId_=req.getHeader("platformId");
        if(EmptyUtil.isEmpty(platformId_))
            return fail("platformId为空");
        Long platformId=Long.parseLong(platformId_);

        // 从请求体中获取文件
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) req;
        Iterator<String> iter = multiRequest.getFileNames();
        if(!iter.hasNext()) {
            return fail("请上传文件");
        }
        MultipartFile file = multiRequest.getFile(iter.next());
        if (file == null)
            return fail("未发现Excel文件");

        String originalFilename = file.getOriginalFilename();
        // 获取文件后缀
        String suffix = "";

        suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

        if(! StringUtils.equals(suffix, ".xls") && ! StringUtils.equals(suffix, ".xlsx")){
            return fail("导入的文件类型错误，请选择后重新选择文件导入");
        };

        List<Map<String, Object>> valueList = null;
        try {
            valueList = ExcelUtil.readExcelData(0, 0, file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            return fail("Excel文件读取发生异常");
        }


        logger.info("员工导入缓存");
        return couponBatchManage.importQuitUserWithTx(platformId,valueList,req);


    }



    //确认导入员工相关
    @RequestMapping(value = "/assureImportUser")
    @ResponseBody
    public JsonResult<List<Long>> assureImport(HttpServletRequest req ,String serialNum,String importUserInfo) {
        logger.info("确认导入优惠券员工");
        Long platformId;
        String str = req.getHeader("platformId");
        CacheUser cacheUser = this.getCacheUser();
        Long userId = cacheUser.getId();
        if(EmptyUtil.isNotEmpty(str)){
            platformId = Long.valueOf(str);
        }else{
            return fail("平台id不能为空");
        }
        Long parseLong=null;
        if(importUserInfo==null){
            return fail("导入记录信息为空");
        }else{
            try {
                parseLong = Long.parseLong(importUserInfo);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        //************************************第二部分

        List<Long> result= couponBatchManage.assureImportUser(userId,platformId,serialNum,parseLong);


        return success(result);

    }




    @RequestMapping("deleteCache")
    @ResponseBody
    public JsonResult deleteCache(){
        CacheUser cacheUser = this.getCacheUser();
        Long userId = cacheUser.getId();
        couponBatchManage.deleteCache(userId);
        return success("清除缓存成功");
    }








    /**
     * 新建或编辑优惠卷批次
     *
     * VO新增linkableButtonPageList 参数 接收跳转页面信息
     *
     * @param vo
     * @param req
     * @return
     */
    @RequestMapping(value = "/insertOrUpdateCouponBatchWithTx")
    @ResponseBody
    public JsonResult<Map<String, Object>> insertOrUpdateCouponBatchWithTx(CouponBatchVO vo, String emps, String comps,
                                                                           Integer effectType, HttpServletRequest req) {
        logger.info("新建或编辑优惠卷批次");
        String str = req.getHeader("platformId");
        if (EmptyUtil.isNotEmpty(str)) {
            Long platformId = Long.valueOf(str);
            vo.setPlatformId(platformId);
        }
        String companyStr = req.getHeader("companyId");
        Long companyId=null;
        if(EmptyUtil.isNotEmpty(companyStr)){
            companyId = Long.valueOf(companyStr);
        }
        vo.setCompanyId(companyId);
        // 参数校验
        vo.setCreator(vo.getId() == null ? getCacheUser().getId() : null);
        try {
            vo.setEmpList(EmptyUtil.isNotEmpty(emps) ? JSONArray.parseArray(emps, Long.class) : null);
        } catch (Exception e) {
            return fail("优惠卷批次所选员工参数格式错误");
        }
        try {
            vo.setCompList(EmptyUtil.isNotEmpty(comps) ? JSONArray.parseArray(comps, Long.class) : null);
        } catch (Exception e) {
            return fail("优惠卷批次所选企业参数格式错误");
        }

        if (EmptyUtil.isEmpty(vo.getCouponRelType()))
            return fail("请选择优惠卷或优惠卷分组");
        if (EmptyUtil.isEmpty(vo.getCouponRelId()))
            return fail("请选择优惠卷或优惠卷分组");
        if (EmptyUtil.isEmpty(vo.getGrantType()))
            return fail("请选择发放类型");
        if (EmptyUtil.isEmpty(vo.getCouponBatchName()))
            return fail("请输入批次名称");

        if (vo.getGrantType() == 0) {
            // 系统发放
            if (EmptyUtil.isEmpty(vo.getChooseWay()))
                return fail("请选择员工方式");
            if (vo.getChooseWay() == 0 && EmptyUtil.isEmpty(vo.getCompList()))
                return fail("请选择企业");
            if (vo.getChooseWay() == 1 && EmptyUtil.isEmpty(vo.getEmpList()))
                return fail("请根据员工列表选择员工");
            if (vo.getChooseWay() == 2 && EmptyUtil.isEmpty(vo.getEmpList()))
                return fail("请根据员工列表选择员工");

            vo.setIsRepeat(null);
            vo.setIsDisplay(null);
        } else if (vo.getGrantType() == 1) {
            // 用户领取
            if (EmptyUtil.isEmpty(vo.getIsRepeat()))
                return fail("请选择使用/过期后是否可再次领用");
            if (EmptyUtil.isEmpty(vo.getIsDisplay()))
                return fail("请选择是否前端展示");
            if (EmptyUtil.isEmpty(vo.getReceiveStartTime()))
                return fail("请填写可领取开始日期");
            if (EmptyUtil.isEmpty(vo.getReceiveEndTime()))
                return fail("请填写可领取结束日期");
            if (EmptyUtil.isEmpty(vo.getGrantCount()))
                return fail("请填写发放数量");
            if(vo.getGetType()==2){
                //领取方式为unit领取
                if(EmptyUtil.isEmpty(vo.getChannelId())){
                    return fail("请选择渠道");
                }
                if(EmptyUtil.isEmpty(vo.getChannelActivityId())){
                    return fail("请选择活动");
                }
            }

        }

        if (EmptyUtil.isEmpty(effectType))
            return fail("请选择有效类型");

        if (effectType == 0) {
            // 有效期不限
            vo.setEffectEndTime(null);
            vo.setEffectStartTime(null);
            vo.setEffectDays(-1);

        } else if (effectType == 1) {
            // 有效期范围
            vo.setEffectDays(-1);
            if (EmptyUtil.isEmpty(vo.getEffectStartTime()))
                return fail("请选择有效期开始时间");
            if (EmptyUtil.isEmpty(vo.getEffectEndTime()))
                return fail("请选择有效期结束时间");

        } else if (effectType == 2) {
            // 有效期周期
            vo.setEffectEndTime(null);
            vo.setEffectStartTime(null);
            if (EmptyUtil.isEmpty(vo.getEffectDays()) && vo.getEffectDays().equals(Integer.valueOf(-1)))
                return fail("有效周期不能为空");

        }

        if (vo.getGrantType() == 1) {
            // 用户领取
            // 1、可领取截止日期需小于等于优惠券有效截止日期
            if (EmptyUtil.isNotEmpty(vo.getEffectEndTime()) && vo.getReceiveEndTime() > vo.getEffectEndTime())
                return fail("可领取截止日期需小于等于优惠券有效截止日期");
            // 2、可领取日期、有效期的截止日期需大于等于系统当前日期
            if (vo.getReceiveEndTime() <= System.currentTimeMillis())
                return fail("可领取截止日期需大于系统当前日期");
            if (vo.getReceiveEndTime() < vo.getReceiveStartTime())
                return fail("可领取截止日期需大于等于起始日期");
        }

        // 有效期的截止日期需大于等于系统当前日期
        if (EmptyUtil.isNotEmpty(vo.getEffectEndTime()) && vo.getEffectEndTime() <= System.currentTimeMillis())
            return fail("有效截止日期需大于系统当前日期");
        // 3、可领取日期、有效期的截止日期需大于等于起始日期
        if (EmptyUtil.isNotEmpty(vo.getEffectEndTime()) && EmptyUtil.isNotEmpty(vo.getEffectStartTime())
                && vo.getEffectEndTime() < vo.getEffectStartTime())
            return fail("有效期截止日期需大于等于起始日期");

        if (EmptyUtil.isNotEmpty(vo.getCouponBatchName()) && vo.getCouponBatchName().length()>15) {
            return fail("批次名称最多可输入15字");
        }

        return couponBatchManage.insertOrUpdateCouponBatchWithTx(CouponBatchConverter.toDTO(vo),vo.getChannelActivityId(),vo.getChannelId());
    }

    /**
     * 显示/隐藏优惠卷批次
     * @param id
     * @param req
     * @return
     */
    @RequestMapping(value = "/updateCouponBatchByIdWithTx")
    @ResponseBody
    public JsonResult<Integer> updateCouponBatchByIdWithTx(Long id, HttpServletRequest req) {
        logger.info("显示/隐藏优惠卷批次");
        if (id == null)
            return fail("优惠卷批次id不能为空");
        CouponBatchDTO dto = new CouponBatchDTO();
        dto.setId(id);
        return couponBatchManage.updateCouponBatchWithTx(dto);
    }

    /**
     * 将优惠卷批次设置为失效
     *
     * @param id
     * @param req
     * @return
     */
    @RequestMapping(value = "/invalidCouponBatchWithTx")
    @ResponseBody
    public JsonResult<Integer> invalidCouponBatchWithTx(Long id, HttpServletRequest req) {
        logger.info("将优惠卷批次设置为失效");
        if (id == null)
            return fail("优惠卷批次id不能为空");
        CouponBatchDTO dto = new CouponBatchDTO();
        dto.setId(id);
        return couponBatchManage.invalidCouponBatchWithTx(dto);
    }

    /**
     * 查询优惠卷批次详情通过id
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/findCouponBatchInfoById")
    @ResponseBody
    public JsonResult<Map<String, Object>> findCouponBatchInfoById(Long id) {
        CouponBatchDTO dto = new CouponBatchDTO();
        dto.setId(id);
        Map<String, Object> map = couponBatchManage.findCouponBatchInfoById(dto);
        if (EmptyUtil.isEmpty(map))
            return fail("优惠卷批次不存在");
        return success(map);

    }

    /**
     * 通过优惠卷批次查询公司信息
     *
     * @param vo
     * @return
     */
    @RequestMapping(value = "/findCompanyByCouponBatch")
    @ResponseBody
    public JsonResult<List<Map<String, Object>>> findCompanyByCouponBatch(CouponBatchVO vo, HttpServletRequest req) {
        String str = req.getHeader("platformId");
        if (EmptyUtil.isNotEmpty(str)) {
            Long platformId = Long.valueOf(str);
            vo.setPlatformId(platformId);
        }
        return success(couponBatchManage.findCompanyByCouponBatch(CouponBatchConverter.toDTO(vo)));
    }

    /**
     * 查询优惠卷批次已选择的员工
     *
     * @param vo
     * @param page
     * @return
     */
    @RequestMapping(value = "/findEmployeeByCouponBatch")
    @ResponseBody
    public JsonResult<PageResult<Map<String, Object>>> findEmployeeByCouponBatch(CouponBatchVO vo, Pagination page) {
        if (EmptyUtil.isEmpty(vo.getId()))
            return fail("优惠卷批次id不能为空");
        return success(couponBatchManage.findEmployeeByCouponBatch(CouponBatchConverter.toDTO(vo), page));
    }

    /**
     * 通过优惠卷id或优惠卷分组或优惠卷批次id查询优惠卷批次的所有员工
     *
     * @param vo
     * @param page
     * @return
     */
    @RequestMapping(value = "/findAllEmployeeByCouponBatch")
    @ResponseBody
    public JsonResult<PageResult<Map<String, Object>>> findAllEmployeeByCouponBatch(CouponBatchVO vo, Pagination page) {
        if (EmptyUtil.isEmpty(vo.getId())) {
            if (EmptyUtil.isEmpty(vo.getCouponRelType()))
                return fail("优惠卷批次的类型不能为空");
            if (EmptyUtil.isEmpty(vo.getCouponRelId()))
                return fail("优惠卷或优惠卷分组的id不能为空");
        }
        PageResult<Map<String, Object>> rt = couponBatchManage
                .findAllEmployeeByCouponBatch(CouponBatchConverter.toDTO(vo), page);
        if (EmptyUtil.isEmpty(rt))
            return fail("优惠卷批次不存在");
        return success(rt);
    }

    /**
     * 优惠卷批次列表
     * @param vo
     * @param page
     * @param req
     * @return
     */
    @RequestMapping(value = "/findCouponBatchOfPage")
    @ResponseBody
    public JsonResult<PageResult<Map<String, Object>>> findCouponBatchOfPage(CouponBatchVO vo, Pagination page,
                                                                             HttpServletRequest req) {
        String str = req.getHeader("platformId");
        if (EmptyUtil.isNotEmpty(str)) {
            Long platformId = Long.valueOf(str);
            vo.setPlatformId(platformId);
        }
        CacheUser userCache = this.getCacheUser();
        return success(couponBatchManage.findCouponBatchOfPageByBlurry(userCache.getId(), CouponBatchConverter.toDTO(vo), page));
    }

    // 业务方法：
    @RequestMapping(value = "/findCouponBatchAll")
    @ResponseBody
    public JsonResult<List<CouponBatchVO>> findCouponBatchAll(CouponBatchVO vo, HttpServletRequest req) {
        String str = req.getHeader("platformId");
        if (EmptyUtil.isNotEmpty(str)) {
            Long platformId = Long.valueOf(str);
            vo.setPlatformId(platformId);
        }
        CouponBatchDTO dto = CouponBatchConverter.toDTO(vo);
        List<CouponBatchDTO> rt = couponBatchManage.findCouponBatchAll(dto);
        return success(CouponBatchConverter.toVO(rt));

    }

    // 业务方法：返回是插入行的id
    @RequestMapping(value = "/insertCouponBatchWithTx")
    @ResponseBody
    public JsonResult<Long> insertCouponBatchWithTx(CouponBatchVO vo, HttpServletRequest req) {
        String str = req.getHeader("platformId");
        if (EmptyUtil.isNotEmpty(str)) {
            Long platformId = Long.valueOf(str);
            vo.setPlatformId(platformId);
        }
        CouponBatchDTO dto = CouponBatchConverter.toDTO(vo);
        Long rt = couponBatchManage.insertCouponBatchWithTx(dto);
        return success(rt);
    }

    // 业务方法：
    @RequestMapping(value = "/deleteCouponBatchWithTx")
    @ResponseBody
    public JsonResult<Integer> deleteCouponBatchWithTx(CouponBatchVO vo, HttpServletRequest req) {
        String str = req.getHeader("platformId");
        if (EmptyUtil.isNotEmpty(str)) {
            Long platformId = Long.valueOf(str);
            vo.setPlatformId(platformId);
        }
        CouponBatchDTO dto = CouponBatchConverter.toDTO(vo);
        int rt = couponBatchManage.deleteCouponBatchWithTx(dto);
        return success(rt);
    }

    /**
     * 用户注册自动领取优惠券
     *
     * @param vo
     * @param req
     * @return
     */
    @RequestMapping(value = "/registerGetCoupon")
    @ResponseBody
    public JsonResult<Map<String, Object>> registerGetCoupon(CouponBatchVO vo, HttpServletRequest req) {
        String str = req.getHeader("platformId");
        if (EmptyUtil.isNotEmpty(str)) {
            Long platformId = Long.valueOf(str);
            vo.setPlatformId(platformId);
        }
        CouponBatchDTO dto = CouponBatchConverter.toDTO(vo);
        CacheUser userCache = this.getCacheUser();
        //CacheUser userCache = new CacheUser ();
        //userCache.setId(268l); //TODO dev data , prd delete
        return success(couponBatchManage.registerGetCoupon(userCache.getId(), dto));
    }

    /**
     * 根据批次导出couponunit
     * @param batchList
     * @return
     */
    @RequestMapping(value = "exportCouponUnit",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult<String> exportCouponUnit(@RequestParam("batchList") String batchList,@RequestParam("platformId") Long platformId, HttpServletRequest req,HttpServletResponse response ){
        logger.info("[couponUnit导出]参数batchList:" + batchList);
        List<Long> couponBatchList = gson.fromJson(batchList, new TypeToken<List<Long>>() {
        }.getType());
        if(EmptyUtil.isEmpty(couponBatchList)){
            return fail("请选择优惠券");
        }
        if(EmptyUtil.isEmpty(platformId)){
            return fail("platformId不能为空");
        }
        return couponBatchManage.exportCouponUnit(platformId,couponBatchList,response);
    }



    /**
     * 查询新旧批次
     * type 0.旧批次,1.新批次
     */
    @RequestMapping("/findCouponBatchByParam")
    @ResponseBody
    public JsonResult<PageResult<CouponBatchDTO>> findCouponBatchByParam(String batchIds,String title,String couponBatchCode,String couponBatchName,Integer type,HttpServletRequest req,Pagination page){
        logger.info("[查询新旧批次]参数:type="+type);
        logger.info("[查询新旧批次]参数:title="+title);
        logger.info("[查询新旧批次]参数:couponBatchCode="+couponBatchCode);
        logger.info("[查询新旧批次]参数:batchIds="+batchIds);
        String str = req.getHeader("platformId");
        if(EmptyUtil.isEmpty(str)){
            return fail("platformId不能为空");
        }
        Long platformId = Long.valueOf(str);
        List<Long> batchIdList = null;
        if(EmptyUtil.isNotEmpty(batchIds)){
             batchIdList=JsonUtils.jsonToList(batchIds, Long.class);
             if(EmptyUtil.isEmpty(batchIdList)){
                 PageResult<CouponBatchDTO> pageResult = new PageResult<CouponBatchDTO>();
                 List<CouponBatchDTO> list = new ArrayList<>();
                 pageResult.setList(list);
                 return success(pageResult);
                 //return fail("batchIds为空");
             }
        }

        return couponBatchManage.findCouponBatchByParam(batchIdList,title,couponBatchCode,couponBatchName,type,platformId,page);




    }














    /***************************************************************脚本代码*************************************************/

    /**
     * 生成couponunit和rdid的脚本
     */
    @RequestMapping(value = "createProductUnitAndQrCode")
    @ResponseBody
    public JsonResult createProductUnitAndQrCode(String title,
                                                 String host,String campaignName,
                                                 String channelName,Long channelId,
                                                 Long campaignId,String campaignCode,
                                                 Long storeId,Long couponUnitStartId,
                                                 Long effectStartTime,Long effectEndTime,
                                                 Long platformId,Long couponBatchId,
                                                 Long couponId,Long grantCount,
                                                 Integer couponType){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        List<CouponUnitDTO> couponUnitDTOList = new ArrayList<>();
        List<QrCodeDTO> qrCodeDTOList = new ArrayList<>();
        List<String> unitSqlList = new ArrayList<>();
        List<String> rdidSqlList = new ArrayList<>();
        //组织数据
        for(int i=0;i<grantCount;i++){
            //生成couponunit
            CouponUnitDTO couponUnitDTO = new CouponUnitDTO();
            Long couponUnitId=couponUnitStartId+i;
            couponUnitDTO.setId(couponUnitId);
            couponUnitDTO.setCouponUnitCode(SequenceUtil.genCouponUnitNo(couponType,i));
            couponUnitDTO.setBatchIndex(Long.valueOf(i));
            couponUnitDTO.setCouponId(couponId);
            couponUnitDTO.setTitle(title);
            couponUnitDTO.setCouponBatchId(couponBatchId);
            couponUnitDTO.setPlatformId(platformId);
            couponUnitDTO.setCouponUnitStatus(Integer.valueOf(0));
            if(EmptyUtil.isNotEmpty(effectEndTime)){
                couponUnitDTO.setEffectEndTime(new Date(effectEndTime));
                couponUnitDTO.setEffectEndTimeStr(format.format(couponUnitDTO.getEffectEndTime()));
            }
            if(EmptyUtil.isNotEmpty(effectStartTime)){
                couponUnitDTO.setEffectStartTime(new Date(effectStartTime));
                couponUnitDTO.setEffectStartTimeStr(format.format(couponUnitDTO.getEffectStartTime()));
            }
            String unitSql= "INSERT INTO `coupon_unit`(`id`,`coupon_unit_code`,`coupon_id`,`coupon_batch_id`,`effect_start_time`,`effect_end_time`,`coupon_unit_status`,`platform_id`) " + "VALUES('"+couponUnitDTO.getId()+"','"+couponUnitDTO.getCouponUnitCode()+"','"+couponId+"','"+couponBatchId+"','"+format2.format(couponUnitDTO.getEffectStartTime())+"','"+format2.format(couponUnitDTO.getEffectEndTime())+"','"+0+"','"+platformId+"');";
            unitSqlList.add(unitSql);

            //生成rdid
            QrCodeDTO qrCodeDTO = new QrCodeDTO();
            String code = CodeUtils.generateCode(10);
            qrCodeDTO.setRdid(code);
            qrCodeDTO.setTypeId(couponUnitId);
            qrCodeDTO.setBranchId(storeId);
            qrCodeDTO.setCampaignCode(campaignCode);
            qrCodeDTO.setCampaignId(campaignId);
            qrCodeDTO.setPlatformId(platformId);
            qrCodeDTO.setChannelId(channelId);
            String rdidSql="INSERT INTO `u_qr_code` (`branch_id`,`channel_id`,`campaign_id`,`type_id`,`rdid`) VALUES('"+storeId+"','"+channelId+"','"+campaignId+"','"+couponUnitId+"','"+code+"');";
            rdidSqlList.add(rdidSql);
            couponUnitDTO.setCampaignName(campaignName);
            couponUnitDTO.setChannelName(channelName);
            couponUnitDTO.setCampaignCode(campaignCode);
            Long clientId=2L;
            String type = "coupon_unit";
            String unitUrl=host+"/#/promotionURL/QRCode.html?platformID="+platformId+"&clientID="+clientId+"&channelID="+channelId+"&campaignID="+campaignCode+"&type="+type+"&typeID="+couponUnitId+"&branchID="+storeId+"&rdid="+code;
            couponUnitDTO.setUnitUrl(unitUrl);



            couponUnitDTOList.add(couponUnitDTO);
            qrCodeDTOList.add(qrCodeDTO);
        }
        Map<String, Object> map = new HashMap<>();
        String url="";
        String url2="";
        String url3="";
        //导出couponunit数据库脚本

           url= export2(unitSqlList);

        //导出qrcode数据库脚本

            url2=export2(rdidSqlList);


        //批次导出

            url3=export1(couponUnitDTOList);



        map.put("优惠卷导出",url);
        map.put("编码导出",url2);
        map.put("批次导出",url3);

        return success(map);
    }

    private String export1(List<CouponUnitDTO> couponUnitDTOList){
        Workbook wb = new XSSFWorkbook();
        Sheet s = wb.createSheet("优惠券导出表");
        String[] header = {"优惠券批次", "优惠券组/标题", "渠道名", "活动名", "unit 编号", "有效起始日期", "有效终止日期", "unit 链接"};
        // 创建表头
        Row head = s.createRow(0);
        for (int i = 0; i < header.length; i++) {
            head.createCell(i).setCellValue(header[i]);
        }

        //订单信息写入
        for (int i = 0; i < couponUnitDTOList.size(); i++) {
            Row r = s.createRow(i + 1);
            CouponUnitDTO vo = couponUnitDTOList.get(i);
            r.createCell(0).setCellValue(vo.getCouponBatchId());
            r.createCell(1).setCellValue(vo.getTitle());
            r.createCell(2).setCellValue(vo.getChannelName());
            r.createCell(3).setCellValue(vo.getCampaignName());
            r.createCell(4).setCellValue(vo.getCouponUnitCode());
            r.createCell(5).setCellValue(vo.getEffectStartTimeStr());
            r.createCell(6).setCellValue(vo.getEffectEndTimeStr());
            r.createCell(7).setCellValue(vo.getUnitUrl());
        }


        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            wb.write(bos);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("失败");
        }
        String upload = uploadService.fastDFSUpload(bos.toByteArray(), "xlsx");
        return upload;
    }
    private String export2(List<String> unitSql){
        Workbook wb = new XSSFWorkbook();
        Sheet s = wb.createSheet("unit表");
        String[] header = {"结果"};
        // 创建表头
        Row head = s.createRow(0);
        for (int i = 0; i < header.length; i++) {
            head.createCell(i).setCellValue(header[i]);
        }

        //订单信息写入
        for (int i = 0; i < unitSql.size(); i++) {
            Row r = s.createRow(i + 1);
            String vo = unitSql.get(i);
            r.createCell(0).setCellValue(vo);
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            wb.write(bos);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("失败");
        }
        String upload = uploadService.fastDFSUpload(bos.toByteArray(), "xlsx");
        return upload;
    }


    /**
     * 优惠券unit设置失效
     * type=1.设置当前unit失效.2.设置当前unit所属的优惠券批次的一批unit为失效
     */
    @RequestMapping("/updateCouponUnitStatus")
    @ResponseBody
    public JsonResult updateCouponUnitStatus(Long couponBatchId,Long startNum,Long endNum){
        logger.info("[优惠券unit设置失效]couponBatchId="+couponBatchId);
        logger.info("[优惠券unit设置失效]startNum="+startNum);
        logger.info("[优惠券unit设置失效]endNum="+endNum);

        if(EmptyUtil.isEmpty(couponBatchId)){
            return fail(BusinessExceptionConstant.DATA_ERROE,"couponBatchId不能为空");
        }
        if(EmptyUtil.isEmpty(startNum)||EmptyUtil.isEmpty(endNum)){
            return fail(BusinessExceptionConstant.DATA_ERROE,"unit起止编号缺失");
        }
        if(startNum>endNum){
            return fail(BusinessExceptionConstant.DATA_ERROE,"请填写正确的起止编号");
        }

        return couponBatchManage.updateCouponUnitStatus(couponBatchId,startNum,endNum);
    }


}
