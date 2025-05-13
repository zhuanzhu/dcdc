package com.egeo.components.promotion.business.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.client.LinkableButtonPageClient;
import com.egeo.components.config.dto.HeadImportRecordsDTO;
import com.egeo.components.config.dto.ImportRecordsDTO;
import com.egeo.components.product.dto.StoreDTO;
import com.egeo.components.promotion.business.CouponBatchManage;
import com.egeo.components.promotion.dto.CouponBatchDTO;
import com.egeo.components.promotion.dto.CouponDTO;
import com.egeo.components.promotion.dto.CouponGroupDTO;
import com.egeo.components.promotion.dto.CouponGroupStoreDTO;
import com.egeo.components.promotion.dto.CouponStoreDTO;
import com.egeo.components.promotion.dto.CouponUnitDTO;
import com.egeo.components.promotion.facade.CouponBatchFacade;
import com.egeo.components.promotion.facade.CouponFacade;
import com.egeo.components.promotion.facade.CouponGroupFacade;
import com.egeo.components.promotion.facade.CouponUnitFacade;
import com.egeo.components.promotion.vo.CouponUnitUserVO;
import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.components.user.dto.QrCodeDTO;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.components.user.dto.UserExtendDTO;
import com.egeo.config.RuntimeContext;
import com.egeo.core.Constant.BusinessExceptionConstant;
import com.egeo.entity.CacheUser;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.DateUtils;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.HostUtils;
import com.egeo.utils.MailCheckUtils;
import com.egeo.utils.SequenceUtil;
import com.egeo.utils.Upload;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.excel.ExcelExportSXXSSF;
import com.egeo.utils.excel2.ExcelHeadChecker;
import com.egeo.utils.excel2.ExcelTmplConstant;
import com.egeo.utils.excel2.PropblemReportRowVO;
import com.egeo.utils.str.StringUtils;
import com.egeo.web.JsonResult;
import com.google.gson.Gson;

@Service("couponBatch")
public class CouponBatchManageImpl implements CouponBatchManage {

	@Value("url.FGJ.WECHAT")
	private String urlFgjWechat ;
	@Value("url.MYY.WECHAT")
	private String urlMyyWechat ;
    @Resource(name = "couponBatchFacade")
    private CouponBatchFacade couponBatchFacade;

    @Resource(name = "couponUnitFacade")
    private CouponUnitFacade couponUnitFacade;

    @Resource(name = "couponFacade")
    private CouponFacade couponFacade;

    @Resource(name = "couponGroupFacade")
    private CouponGroupFacade couponGroupFacade;

    @Resource(name = "jedisUtil")
    private JedisUtil jedisUtil;
    @Autowired
    private LinkableButtonPageClient linkableButtonPageReadService;
    

	@Autowired
	private Upload uploadService;
    private Gson gson=new Gson();


    @Override
    public CouponBatchDTO findCouponBatchById(CouponBatchDTO dto) {
        return couponBatchFacade.findCouponBatchById(dto);
    }

    @Override
    public PageResult<CouponBatchDTO> findCouponBatchOfPage(CouponBatchDTO dto, Pagination page) {
        return couponBatchFacade.findCouponBatchOfPage(dto, page);
    }

    @Override
    public List<CouponBatchDTO> findCouponBatchAll(CouponBatchDTO dto) {
        return couponBatchFacade.findCouponBatchAll(dto);
    }

    @Override
    public Long insertCouponBatchWithTx(CouponBatchDTO dto) {
        return couponBatchFacade.insertCouponBatchWithTx(dto);
    }

    @Override
    public JsonResult<Integer> updateCouponBatchWithTx(CouponBatchDTO dto) {
        CouponBatchDTO dto_ = couponBatchFacade.findCouponBatchById(dto);
        if (EmptyUtil.isEmpty(dto_))
            return JsonResult.fail("该优惠卷批次不存在");
        if (dto_.getGrantType() == 0)
            return JsonResult.fail("系统发放的批次不能改变该属性");
        if (dto_.getIsDisplay() == 0 && System.currentTimeMillis() >= dto_.getReceiveEndTime().getTime())
            return JsonResult.fail("该优惠券批次已过领取时间，不可前端显示");

        dto.setIsDisplay(dto_.getIsDisplay() == 0 ? 1 : 0);
        return JsonResult.success(couponBatchFacade.updateCouponBatchWithTx(dto));
    }

    @Override
    public int deleteCouponBatchWithTx(CouponBatchDTO dto) {
        return couponBatchFacade.deleteCouponBatchWithTx(dto);
    }

    @Override
    public JsonResult<Map<String, Object>> insertOrUpdateCouponBatchWithTx(CouponBatchDTO dto, Long channelActivityId, Long channelId) {
    	if (EmptyUtil.isNotEmpty(dto.getEffectEndTime())) {
    		dto.setEffectEndTime(processEndTime(dto.getEffectEndTime()));
    	}
    	if (EmptyUtil.isNotEmpty(dto.getReceiveEndTime())) {
    		dto.setReceiveEndTime(processEndTime(dto.getReceiveEndTime()));
    	}
        if (EmptyUtil.isEmpty(dto.getId())) {
            // 新增
            // 优惠卷批次编号设置及查重
            dto.setCouponBatchCode(SequenceUtil.genCouponBatchNo());
            CouponBatchDTO couponBatchDTO = new CouponBatchDTO();
            couponBatchDTO.setCouponBatchCode(dto.getCouponBatchCode());
            
            List<CouponBatchDTO> couponBatchDTOList = couponBatchFacade.findCouponBatchAll(couponBatchDTO);
            
            if (EmptyUtil.isNotEmpty(couponBatchDTOList))
                return JsonResult.fail("优惠卷批次编号重复,请重试");

            CouponBatchDTO couponBatchDTO2 = new CouponBatchDTO();
            couponBatchDTO2.setCouponBatchName(dto.getCouponBatchName());
            List<CouponBatchDTO> couponBatchDTOList2 = couponBatchFacade.findCouponBatchAll(couponBatchDTO2);
            if (EmptyUtil.isNotEmpty(couponBatchDTOList2))
                return JsonResult.fail("优惠卷批次名称重复,请重试");

            //新增unit领取创建couponunit逻辑
            if(EmptyUtil.isNotEmpty(dto.getGetType())&&dto.getGetType()==2){
                Long couponBatchId = couponBatchFacade.insertCouponBatchAndUnitWithTx(dto,channelActivityId,channelId);

            }else{
                couponBatchFacade.insertCouponBatchWithTx(dto);
            }
        } else {
            // 编辑
            CouponBatchDTO couponBatchDTO = couponBatchFacade.findCouponBatchById(dto);
            if (EmptyUtil.isEmpty(couponBatchDTO))
                return JsonResult.fail("该优惠卷批次不存在");

            // 不能修改: 编号、优惠券/优惠券组、发放类型,创建人
            dto.setCouponRelType(null);
            dto.setCouponRelId(null);
            dto.setCouponBatchCode(null);
            dto.setGrantType(null);
            dto.setCreator(null);

            // 编辑优惠卷批次时,判断已领取数量是否>0.若是,只可编辑发放数量、可领取日期、有效期
            if (couponBatchDTO.getGrantType() == 0) {
                // 系统发放
                return JsonResult.fail("系统发放的优惠券批次不能编辑");
            } else if (couponBatchDTO.getGrantType() == 1) {
                // 用户领取
                // 获取已领取数量,判断是否>0
                CouponUnitDTO couponUnitDTO = new CouponUnitDTO();
                couponUnitDTO.setCouponBatchId(dto.getId());
                List<CouponUnitDTO> couponUnitPOList = couponUnitFacade.findCouponUnitAll(couponUnitDTO);
                if (EmptyUtil.isNotEmpty(couponUnitPOList)) {
                    if (couponBatchDTO.getEffectEndTime() == null && couponBatchDTO.getEffectDays() == null
                            && dto.getEffectEndTime() != null)
                        return JsonResult.fail("可领取截止日期只支持延后操作");
                    if (couponBatchDTO.getEffectEndTime() != null && dto.getEffectEndTime() != null
                            && couponBatchDTO.getEffectEndTime().after(dto.getEffectEndTime()))
                        return JsonResult.fail("可领取截止日期只支持延后操作");
                    if (couponBatchDTO.getReceiveEndTime().after(dto.getReceiveEndTime()))
                        return JsonResult.fail("有效截止日期只支持延后操作");
                    if (dto.getGrantCount()!=-1&&dto.getGrantCount().compareTo(couponUnitPOList.size()) < 0)
                        return JsonResult.fail("发放数量需大于当前已领取数量");
                    dto.setEffectEndTime(
                            couponBatchDTO.getReceiveEndTime().getTime() != -1L ? dto.getEffectEndTime() : null);
                    dto.setReceiveEndTime(dto.getReceiveEndTime());
                    if(dto.getGetType()!=2){
                        dto.setGrantCount(dto.getGrantCount());
                    }
                    //
                    dto.setLinkableButtonPageList(dto.getLinkableButtonPageList());


                   /* CouponBatchDTO couponBatchDTO_ = new CouponBatchDTO();
                    couponBatchDTO_.setId(dto.getId());
                    couponBatchDTO_.setEffectEndTime(
                            couponBatchDTO.getReceiveEndTime().getTime() != -1L ? dto.getEffectEndTime() : null);
                    couponBatchDTO_.setReceiveEndTime(dto.getReceiveEndTime());
                    if(dto.getGetType()!=2){
                        couponBatchDTO_.setGrantCount(dto.getGrantCount());
                    }
                    //
                    couponBatchDTO_.setLinkableButtonPageList(dto.getLinkableButtonPageList());
                    dto = couponBatchDTO_;*/
                }
                if(EmptyUtil.isNotEmpty(dto.getGetType())&&dto.getGetType()==2){
                    //更新qrcode
                    if(EmptyUtil.isEmpty(couponUnitPOList)){
                        throw new BusinessException("该批次没有对应的优惠卷unit");
                    }
                    List<Long> couponUnitIds = new ArrayList<>();
                    for(CouponUnitDTO unitDTO:couponUnitPOList){
                        couponUnitIds.add(unitDTO.getId());
                    }

                    couponBatchFacade.updateQrCodeByCouponUnitIds(couponUnitIds,channelId,channelActivityId);
                }

            }

            couponBatchFacade.updateCouponBatchWithTx(dto);
        }

        return JsonResult.success(null);
    }
    
    private Date processEndTime(Date date) {
		date.setHours(23);
		date.setMinutes(59);
		date.setSeconds(59);
		return date;
	}

    @Override
    public JsonResult<Integer> invalidCouponBatchWithTx(CouponBatchDTO dto) {
        CouponBatchDTO dto_ = couponBatchFacade.findCouponBatchById(dto);
        if (EmptyUtil.isEmpty(dto_))
            return JsonResult.fail("该优惠卷批次不存在");
        if (dto_.getIsEffect() != null && dto_.getIsEffect() == 1)
            return JsonResult.fail("该优惠卷批次已失效");

        dto.setGrantType(dto_.getGrantType());
        couponBatchFacade.invalidCouponBatchWithTx(dto);
        return JsonResult.success(null);
    }

    @Override
    public Map<String, Object> findCouponBatchInfoById(CouponBatchDTO dto) {
        dto = couponBatchFacade.findCouponBatchById(dto);
        if (EmptyUtil.isEmpty(dto))
            return null;

        Map<String, Object> map = new HashMap<String, Object>();

        if (dto.getCouponRelType() == 0) {
            // 优惠卷
            CouponDTO couponDTO = new CouponDTO();
            couponDTO.setId(dto.getCouponRelId());
            couponDTO = couponFacade.findCouponById(couponDTO);
            map.put("title", couponDTO.getTitle());
            //商品组时 返回跳转链接页面ID
            if(dto.getLinkableId() != null) {          	
            	map.put("linkbuttonPageList", couponBatchFacade.findLinkableButtonPageByLinkableId(dto.getLinkableId()));
            }
        } else if (dto.getCouponRelType() == 1) {
            // 优惠卷分组
            CouponGroupDTO couponGroupDTO = new CouponGroupDTO();
            couponGroupDTO.setId(dto.getCouponRelId());
            couponGroupDTO = couponGroupFacade.findCouponGroupById(couponGroupDTO);
            map.put("title", couponGroupDTO.getGroupName());
            map.put("linkbuttonPageList", null);
        }

        // 数据初始化(前端要求)
        map.put("comps", Collections.EMPTY_LIST);
        map.put("emps", Collections.EMPTY_LIST);
        map.put("chooseWay", null);
        map.put("grantCount", null);
        map.put("isRepeat", null);
        map.put("isDisplay", null);
        map.put("receiveStartTime", null);
        map.put("receiveEndTime", null);
        // 判断发放类型
        if (dto.getGrantType() == 0) {
            // 系统发放
            map.put("chooseWay", dto.getChooseWay());
            // 判断选择员工方式
            if (dto.getChooseWay() == 0) {
                // 选择企业id集合
                map.put("comps", couponBatchFacade.queryCouponBatchCompany(dto));
            } else if (dto.getChooseWay() == 1) {
                // 选择员工数量
                map.put("emps", couponBatchFacade.queryCouponBatchEmployeeId(dto));

            }
        } else if (dto.getGrantType() == 1) {
            // 用户领取
            map.put("grantCount", dto.getGrantCount());
            map.put("isRepeat", dto.getIsRepeat());
            map.put("isDisplay", dto.getIsDisplay());
            map.put("receiveStartTime", dto.getReceiveStartTime() != null ? dto.getReceiveStartTime().getTime() : null);
            map.put("receiveEndTime", dto.getReceiveEndTime() != null ? dto.getReceiveEndTime().getTime() : null);
        }

        // 查询优惠卷批次的已领取优惠卷unit数量
        CouponUnitDTO couponUnitDTO = new CouponUnitDTO();
        couponUnitDTO.setCouponBatchId(dto.getId());
        List<CouponUnitDTO> couponUnitPOList = couponUnitFacade.findCouponUnitAll(couponUnitDTO);
        map.put("couponUnitCount", couponUnitPOList.size());

        map.put("id", dto.getId());
        map.put("couponRelType", dto.getCouponRelType());
        map.put("couponRelId", dto.getCouponRelId());
        map.put("grantType", dto.getGrantType());
        map.put("getType", dto.getGetType());
        map.put("effectDays", dto.getEffectDays());
        map.put("effectStartTime", dto.getEffectStartTime() != null ? dto.getEffectStartTime().getTime() : null);
        map.put("effectEndTime", dto.getEffectEndTime() != null ? dto.getEffectEndTime().getTime() : null);
        map.put("couponBatchName",dto.getCouponBatchName());
        if(dto.getGetType()==2){
            //unit领取,查询同一批次中的一个unit,去查询渠道和活动信息
            if(EmptyUtil.isEmpty(couponUnitPOList)){
                throw new BusinessException("该批次没有对应的优惠卷unit");
            }
            QrCodeDTO codeDTO=couponBatchFacade.findQrCodeByCouponUnitId(couponUnitPOList.get(0).getId());
            if(EmptyUtil.isEmpty(codeDTO)){
                throw new BusinessException("couponunit数据有误");
            }
            codeDTO.setRdid(null);
            map.put("qrCode",codeDTO);

        }
        // 有效时间选择   0:不限 1:时间范围 2:时间周期
        Integer effectType = Integer.valueOf(0);
        if (dto.getEffectStartTime() != null && dto.getEffectEndTime() != null)
            effectType = Integer.valueOf(1);
        if (dto.getEffectDays() != null && !dto.getEffectDays().equals(Integer.valueOf(-1)))
            effectType = Integer.valueOf(2);
        map.put("effectType", effectType);

        return map;
    }

    @Override
    public List<Map<String, Object>> findCompanyByCouponBatch(CouponBatchDTO dto) {
        if ((dto.getCouponRelType() == null || dto.getCouponRelId() == null) && dto.getId() != null)
            dto = couponBatchFacade.findCouponBatchById(dto);

        // 通过优惠卷或优惠卷分组查询企业
        return couponBatchFacade.findCompanyByCouponBatch(dto);
    }

    @Override
    public PageResult<Map<String, Object>> findEmployeeByCouponBatch(CouponBatchDTO dto, Pagination page) {
        return couponBatchFacade.queryCouponBatchEmployee(dto, page);
    }

    @Override
    public PageResult<Map<String, Object>> findAllEmployeeByCouponBatch(CouponBatchDTO dto, Pagination page) {
        if (EmptyUtil.isNotEmpty(dto.getId())) {
            CouponBatchDTO dto_ = new CouponBatchDTO();
            dto_.setId(dto.getId());
            dto = couponBatchFacade.findCouponBatchById(dto_);
            if (EmptyUtil.isEmpty(dto))
                return null;
        }
        return couponBatchFacade.queryAllCouponBatchEmployee(dto, page);
    }

    
    
    @Override
    public PageResult<Map<String, Object>> findCouponBatchOfPageByBlurry(Long userId, CouponBatchDTO dto, Pagination page) {
        PageResult<CouponBatchDTO> rt = couponBatchFacade.findCouponBatchOfPageByBlurry(dto, page);
        
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (CouponBatchDTO couponBatchDTO : rt.getList()) {
            CouponUnitDTO couponUnitDTO = new CouponUnitDTO();
            couponUnitDTO.setCouponBatchId(couponBatchDTO.getId());
            //-1标记一下-1时查询userid部位空的数据
            couponUnitDTO.setUserId(Long.valueOf(-1));
            List<CouponUnitDTO> couponUnitDTOList = couponUnitFacade.findCouponUnitAll(couponUnitDTO);
            int usedCount = 0;
            int expiredCount = 0;
            for (CouponUnitDTO couponUnitDTO_ : couponUnitDTOList) {
                if (couponUnitDTO_.getCouponUnitStatus() == 0
                        && (couponUnitDTO_.getEffectEndTime() != null
                        && couponUnitDTO_.getEffectEndTime().getTime() <= System.currentTimeMillis())) {
                    // 已过期
                    expiredCount++;
                } else if (couponUnitDTO_.getCouponUnitStatus() == 1) {
                    // 已使用
                    usedCount++;
                }
            }

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("couponBatchCode", couponBatchDTO.getCouponBatchCode());
            map.put("title", couponBatchDTO.getTitle());
            map.put("createTime", DateUtils.format(DateUtils.DATE_TIME_FORMAT, couponBatchDTO.getCreateTime()));
            map.put("updateTime", !couponBatchDTO.getUpdateTime().equals(couponBatchDTO.getCreateTime()) ?
                    DateUtils.format(DateUtils.DATE_TIME_FORMAT, couponBatchDTO.getUpdateTime()) : null);
            if (couponBatchDTO.getEffectStartTime() != null && couponBatchDTO.getEffectEndTime() != null ){
                map.put("effectDays", null);
                map.put("effectTime", DateUtils.getDefaultDate(couponBatchDTO.getEffectStartTime()) + "至"
                        + DateUtils.getDefaultDate(couponBatchDTO.getEffectEndTime()));
            } else {
                map.put("effectDays", couponBatchDTO.getEffectDays() == -1
                        ? "不限时间" : couponBatchDTO.getEffectDays() + "天");
                map.put("effectTime", null);
            }
            
            if(couponBatchDTO.getLinkableId() != null) {
            	map.put("linkableButtonPageList",couponBatchFacade.findCmsPageByLinkableId(couponBatchDTO.getLinkableId())); 
            }

            map.put("receiveTime", couponBatchDTO.getReceiveStartTime() != null && couponBatchDTO.getReceiveEndTime() != null ?
                    DateUtils.getDefaultDate(couponBatchDTO.getReceiveStartTime()) + "至"
                            + DateUtils.getDefaultDate(couponBatchDTO.getReceiveEndTime()) : null);
            map.put("grantType", couponBatchDTO.getGrantType());
            map.put("isDisplay", couponBatchDTO.getIsDisplay());
            map.put("isRepeat", couponBatchDTO.getIsRepeat());
            map.put("grantCount", couponBatchDTO.getGrantType() == 0 ?
                    couponUnitDTOList.size() : couponBatchDTO.getGrantCount());
            map.put("creator", couponBatchFacade.findUserNameById(couponBatchDTO.getCreator()));
            map.put("isEffect", couponBatchDTO.getIsEffect());
            map.put("isDisplay", couponBatchDTO.getIsDisplay());

            map.put("receivedCount", couponUnitDTOList.size());
            map.put("usedCount", usedCount);
            map.put("expiredCount", expiredCount);
            map.put("id", couponBatchDTO.getId());
            map.put("couponRelType", couponBatchDTO.getCouponRelType());
            map.put("getType", couponBatchDTO.getGetType());

            if(couponBatchDTO.getCouponBatchName() != null){
                map.put("couponBatchName",couponBatchDTO.getCouponBatchName());
            }else {
                map.put("couponBatchName","");
            }

            // 活动码
//            Long channel = couponBatchFacade.findUserChannelById(userId);
            StringBuffer campaignId = new StringBuffer();
            campaignId.append("Gift-").append(StringUtils.getRandomByCount(5));
            map.put("campaignId", campaignId);

            //微信二维码，活动码+门店
            List<Long> stores = new ArrayList<>();
            if (0 == couponBatchDTO.getCouponRelType()) {    //优惠卷批次的类型   0:优惠卷  1:优惠卷分组
                CouponStoreDTO couponStoreDTO = new CouponStoreDTO();
                couponStoreDTO.setCouponId(couponBatchDTO.getCouponRelId());
                List<CouponStoreDTO> couponStoreDTOList = couponBatchFacade.findCouponStore(couponStoreDTO);
                for (CouponStoreDTO couponStore : couponStoreDTOList) {
                    stores.add(couponStore.getStoreId());
                }
            } else if (1 == couponBatchDTO.getCouponRelType()) {
                CouponGroupStoreDTO couponGroupStoreDTO = new CouponGroupStoreDTO();
                couponGroupStoreDTO.setCouponGroupId(couponBatchDTO.getCouponRelId());
                List<CouponGroupStoreDTO> couponGroupStoreList = couponBatchFacade.findCouponGroupStoreAll(couponGroupStoreDTO);
                for (CouponGroupStoreDTO couponGroupStore : couponGroupStoreList) {
                    stores.add(couponGroupStore.getStoreId());
                }
            }
            map.put("stores", stores);
            list.add(map);
        }
        PageResult<Map<String, Object>> result = new PageResult<Map<String, Object>>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
        return result;
    }

    @Override
    public Map<String, Object> registerGetCoupon(Long userId, CouponBatchDTO dto) {
        Map<String, Object> res = new HashMap<>();
        Map<String, Object> key = new HashMap<>();
        UserExtendDTO userExtendDTO = couponBatchFacade.findUserInfo(userId);
        if (EmptyUtil.isEmpty(userExtendDTO)) {
            throw new BusinessException("无效的用户userId");
            //logger.info("--------------注册用户自动领取优惠券异常:无效的用户userId");
        }
        if (EmptyUtil.isEmpty(userExtendDTO.getRegisterStoreId())) {
            res.put("result", "用户注册的门店码无效");
            return res;
            // throw new BusinessException("用户注册的门店码无效");
        }
        Long registerStoreId = userExtendDTO.getRegisterStoreId();
        Long companyId = userExtendDTO.getCompanyId();
        // 查询门店所拥有的优惠券，且优惠券的领取方式是注册领取
        StoreDTO storeDTO = couponBatchFacade.findStoreDTO(registerStoreId);
        if (EmptyUtil.isEmpty(storeDTO)) {
            throw new BusinessException("门店不存在");
        }
        // 门店优惠券关联表
        List<CouponStoreDTO> couponStoreDTOList = couponBatchFacade.findCouponStore(storeDTO.getId());
        List<Long> couponIdList = new ArrayList<>();
        List<Long> couponGroupIdList = new ArrayList<>();
        for (CouponStoreDTO couponStoreDTO : couponStoreDTOList) {
            couponIdList.add(couponStoreDTO.getCouponId());
        }
        //门店关联优惠券组
        CouponGroupStoreDTO couponGroupStoreDTO = new CouponGroupStoreDTO();
        couponGroupStoreDTO.setStoreId(storeDTO.getId());
        List<CouponGroupStoreDTO> couponGroupStoreList = couponBatchFacade.findCouponGroupStoreAll(couponGroupStoreDTO);
        for (CouponGroupStoreDTO couponGroupStore : couponGroupStoreList) {
            couponGroupIdList.add(couponGroupStore.getCouponGroupId());
        }

        //门店所关联的所有优惠券批次
        CouponBatchDTO couponBatchDTO = new CouponBatchDTO();
        couponBatchDTO.setCouponRelType(0); //0:优惠卷
        couponBatchDTO.setGetType(3); //3：注册领取
        couponBatchDTO.setIsEffect(1); //优惠券有效
        couponBatchDTO.setIsDisplay(1); //前端显示
        key.put("currentDate", new Date()); //优惠券批次开始领取时间 < 当前时间 &&  当前时间>优惠券批次结束领取时间
        List<CouponBatchDTO> findCouponBatchList = couponBatchFacade.findCouponBatchByCouponIds(couponBatchDTO, couponIdList, key);

        //门店所关联的所有优惠券组批次
        CouponBatchDTO couponBatch = new CouponBatchDTO();
        couponBatch.setCouponRelType(1); // 1:优惠卷分组
        couponBatch.setGetType(3); //3：注册领取
        couponBatch.setIsEffect(1); //优惠券有效
        couponBatch.setIsDisplay(1); //前端显示
        key.put("currentDate", new Date()); //优惠券批次开始领取时间 < 当前时间 &&  当前时间>优惠券批次结束领取时间
        List<CouponBatchDTO> findCouponBatchGroupList = couponBatchFacade.findCouponBatchByCouponIds(couponBatchDTO, couponGroupIdList, key);

        int i=0;
        //循环优惠券批次，批量下发用户unit
        for (CouponBatchDTO couponUnitDTO_ : findCouponBatchList) {
            boolean flag = insertCouponUnitWithTx(userId, couponUnitDTO_, dto,i);
            i++;
            if (!flag) {
                continue;
            }
        }
        for (CouponBatchDTO couponUnitDTO_ : findCouponBatchGroupList) {
            boolean flag = insertCouponUnitWithTx(userId, couponUnitDTO_, dto,i);
            i++;
            if (!flag) {
                continue;
            }
        }
        res.put("data", "success");
        return res;
    }

    @Override
    public JsonResult<String> exportCouponUnit(Long platformId, List<Long> couponBatchList, HttpServletResponse response) {
        JsonResult<String> result = new JsonResult<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //根据批次查询所有的couponUnit列表
        List<CouponUnitDTO> couponUnitDTOList=couponBatchFacade.findCouponUnitListByBatchIdList(couponBatchList);
        if(EmptyUtil.isEmpty(couponUnitDTOList)){
            return JsonResult.fail("该批次没有对应的优惠卷unit");
        }
        //补全信息
        List<Long> couponUnitIds = new ArrayList<>();
        for(CouponUnitDTO couponUnitDTO:couponUnitDTOList){
            couponUnitIds.add(couponUnitDTO.getId());
        }
        List<QrCodeDTO> qrCodeList=couponBatchFacade.findQrCodeListByCouponUnitIds(couponUnitIds);
        if(EmptyUtil.isEmpty(qrCodeList)){
            return JsonResult.fail("未查询到对应的rdid信息");
        }
        //默认clientId为2,微信端
        Integer clientId=2;
        String type = "coupon_unit";
        for(CouponUnitDTO couponUnitDTO:couponUnitDTOList){
            for(QrCodeDTO qrCodeDTO:qrCodeList){
                if(qrCodeDTO.getTypeId().equals(couponUnitDTO.getId())){
                    couponUnitDTO.setCampaignName(qrCodeDTO.getCampaignName());
                    couponUnitDTO.setChannelName(qrCodeDTO.getChannelName());
                    couponUnitDTO.setCampaignCode(qrCodeDTO.getCampaignCode());
                    //生成二维码连接
                    String host ="";
                    if(platformId.equals(2L)){
                        host = urlMyyWechat;
                    }else if(platformId.equals(7L)){
                        host = urlFgjWechat;

                    }
                    String unitUrl=host+"/#/promotionURL/QRCode.html?platformID="+couponUnitDTO.getPlatformId()+"&clientID="+clientId+"&channelID="+qrCodeDTO.getChannelId()+"&campaignID="+qrCodeDTO.getCampaignCode()+"&type="+type+"&typeID="+couponUnitDTO.getId()+"&branchID="+qrCodeDTO.getBranchId()+"&rdid="+qrCodeDTO.getRdid();
                    couponUnitDTO.setUnitUrl(unitUrl);

                    if(EmptyUtil.isNotEmpty(couponUnitDTO.getEffectEndTime())){
                        couponUnitDTO.setEffectEndTimeStr(format.format(couponUnitDTO.getEffectEndTime()));
                    }
                    if(EmptyUtil.isNotEmpty(couponUnitDTO.getEffectStartTime())){
                        couponUnitDTO.setEffectStartTimeStr(format.format(couponUnitDTO.getEffectStartTime()));
                    }
                }
            }
        }

        String fileName=null;
        //确定文件名
        CouponBatchDTO couponBatchDTO = new CouponBatchDTO();
        couponBatchDTO.setId(couponBatchList.get(0));
        CouponBatchDTO dto = couponBatchFacade.findCouponBatchById(couponBatchDTO);
        if(couponBatchList.size()==1){
            //只有一个批次

            fileName = dto.getCouponBatchName()+sdf.format(new Date());
        }else if(couponBatchList.size()>1){
            //多个批次
            fileName = dto.getCouponBatchName() + "等批次" + sdf.format(new Date());
        }
        //-1表示关闭自动刷新，手动控制写磁盘的时机，其它数据表示多少数据在内存保存，超过的则写入磁盘
        int flushRows=100;
        //指导导出数据的title
        List<String> sheetNames=new ArrayList<String>();
        sheetNames.add("优惠券批次");
        sheetNames.add("优惠券组/标题");
        sheetNames.add("渠道名");
        sheetNames.add("活动名");
        sheetNames.add("unit 编号");
        sheetNames.add("有效起始日期");
        sheetNames.add("有效终止日期");
        sheetNames.add("unit 链接");


        //告诉导出类数据list中对象的属性，让ExcelExportSXXSSF通过反射获取对象的值
        List<String> fieldCodes=new ArrayList<String>();
        fieldCodes.add("couponBatchId");
        fieldCodes.add("title");
        fieldCodes.add("channelName");
        fieldCodes.add("campaignName");
        fieldCodes.add("couponUnitCode");
        fieldCodes.add("effectStartTimeStr");
        fieldCodes.add("effectEndTimeStr");
        fieldCodes.add("unitUrl");



        //注意：fieldCodes和fieldNames个数必须相同且属性和title顺序一一对应，这样title和内容才一一对应


        try {
            //开始导出，执行一些workbook及sheet等对象的初始创建
            ExcelExportSXXSSF excelExportSXXSSF = ExcelExportSXXSSF.start("./", "./", fileName, sheetNames, fieldCodes, flushRows);

            //执行导出
            excelExportSXXSSF.writeDatasByObject(couponUnitDTOList);
            //输出文件
            excelExportSXXSSF.exportFileWithOutTime(response);
        }catch (Exception e) {
            throw new BusinessException("数据导出异常");
        }
        result.setData("数据导出成功");
        return result;





    }

    @Override
    public JsonResult<PageResult<CouponBatchDTO>> findCouponBatchByParam(List<Long> batchIdList, String title, String couponBatchCode,String couponBatchName,Integer type, Long platformId, Pagination page) {
        PageResult<CouponBatchDTO> couponBatchDTOList=couponBatchFacade.findCouponBatchByParam(batchIdList,title,couponBatchCode,couponBatchName,type,platformId,page);
        //处理时间区间
        List<CouponBatchDTO> list = couponBatchDTOList.getList();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String receivedRange = "";
        String effectRange = "";
        for(CouponBatchDTO dto:list){
            if(EmptyUtil.isNotEmpty(dto.getReceiveStartTime())&&EmptyUtil.isNotEmpty(dto.getReceiveEndTime())){
                String start=format.format(dto.getReceiveStartTime());
                String end = format.format(dto.getReceiveEndTime());
                receivedRange=start+"至"+end;
            }
            if(EmptyUtil.isNotEmpty(dto.getEffectStartTime())&&EmptyUtil.isNotEmpty(dto.getEffectEndTime())){
                String start = format.format(dto.getEffectStartTime());
                String end = format.format(dto.getEffectEndTime());
                effectRange = start + "至" + end;
            }else if(EmptyUtil.isNotEmpty(dto.getEffectDays())&&dto.getEffectDays()>0){
                effectRange = dto.getEffectDays() + "天";
            }else if(EmptyUtil.isEmpty(dto.getEffectStartTime())&&EmptyUtil.isEmpty(dto.getEffectEndTime())&&Integer.valueOf(-1).equals(dto.getEffectDays())){
                effectRange = "不限时间";
            }
            dto.setReceivedRange(receivedRange);
            dto.setEffectRange(effectRange);

            //设置默认的addprice和sort
            dto.setAddPrice(Integer.valueOf(0));
            dto.setSort(Integer.valueOf(0));

            //判断优惠券类型 , 做字符拼接
            Integer couponType = dto.getCouponType();
            if (couponType == 0) {
                //满减券
                String detail = "满"+dto.getTriggerAmount().intValue()+"减"+dto.getDiscountAmount().intValue();
                dto.setTitleStr(detail);
            }else{
                String detail = "价值"+dto.getDiscountAmount().intValue();
                dto.setTitleStr(detail);
            }
            if (dto.getCouponBatchName() == null) {
                dto.setCouponBatchName("");
            }
        }

        return JsonResult.success(couponBatchDTOList);
    }

    @Override
    public JsonResult updateCouponUnitStatus(Long couponBatchId, Long startNum, Long endNum) {
        CouponBatchDTO batchDTO = new CouponBatchDTO();
        batchDTO.setId(couponBatchId);
        batchDTO = couponBatchFacade.findCouponBatchById(batchDTO);
        if(!(batchDTO.getGrantType()==0||(batchDTO.getGrantType()==1&&EmptyUtil.isNotEmpty(batchDTO.getGetType())&&batchDTO.getGetType()==2))){
            return JsonResult.fail("当前批次不可置失效");
        }
        int grantCount=0;
        if(batchDTO.getGrantType()==1&&batchDTO.getGetType()==2){
            //unit领取
            grantCount=batchDTO.getGrantCount();
        }
        if(batchDTO.getGrantType()==0&&EmptyUtil.isEmpty(batchDTO.getGetType())){
            //系统发放
            CouponUnitDTO couponUnitDTO = new CouponUnitDTO();
            couponUnitDTO.setCouponBatchId(couponBatchId);
            Long couponUnitAllCount = couponUnitFacade.findCouponUnitAllCount(couponUnitDTO);
            grantCount = couponUnitAllCount.intValue();
        }
        if(grantCount<=endNum){
            return JsonResult.fail("当前批次可置失效的序号范围为:0-"+(grantCount-1)+"，请重新设置", BusinessExceptionConstant.DATA_ERROE);
        }

        int res=couponUnitFacade.updateCouponUnitStatusByParamsWithTx(couponBatchId,startNum,endNum,new Date());
        int size=(int)(endNum-startNum+1);
        if(res==size){
            return JsonResult.success("已将指定范围优惠券Unit设为失效");
        }else{
            Long freezeCount=couponUnitFacade.findCouponUnitCountOfFreezeByParams(couponBatchId,startNum,endNum);
            Long other = size - res - freezeCount;
            return JsonResult.success("已将指定范围中大部分优惠券设为失效，但其中存在"+freezeCount+"张正在使用中的优惠券、"+other+"张非有效状态的优惠券未能设置为失效");
        }
    }

    @Override
    public JsonResult<Map<String, Object>> importQuitUserWithTx(Long platformId, List<Map<String, Object>> valueList, HttpServletRequest req) {
        if (valueList.size() > 1002)
            return JsonResult.fail("单次导入数据不能超过1000条");

        // *************************************** 检查头文件及内容

        String err = ExcelHeadChecker.chechCouponHeader(valueList, ExcelTmplConstant.CouponUnitUser.getTmplType(), true);
        if (EmptyUtil.isNotBlank(err))
            return JsonResult.fail(err);
        // 模板类型
        String tmplName = ExcelTmplConstant.CouponUnitUser.getTmplName();
        if (!StringUtils.equals(tmplName, valueList.get(0).get("CELL2").toString())) {
            return JsonResult.fail("导入的文件类型错误，请检查后重新选择文件导入");
        }

        CacheUser userCache = RuntimeContext.cacheUser();
        Long userId = userCache.getId();// 用户id
        String userName = userCache.getName();// 用户名称
        String ip= HostUtils.getClientIP(req);
        //根据ip获取mac地址
        String mac;
        try {
            mac = HostUtils.getLocalMac(ip);
        } catch (Exception e) {
            throw new BusinessException("获取mac地址异常" + e.getMessage());
        }

        // 创建时间
        String createTime = valueList.get(0).get("CELL4").toString();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = null;
        try {
            date = sdf.parse(createTime);
        } catch (ParseException e) {
            return JsonResult.fail("创建时间解析错误");
        }

        // 序列号校验
        String sn = valueList.get(0).get("CELL6").toString();
        // 创建导入正式记录的信息
        HeadImportRecordsDTO headImportRecordsDTO = new HeadImportRecordsDTO();

        //headImportRecordsDTO.setGeneratedTime(date);
        headImportRecordsDTO.setTemplateType(tmplName);
        headImportRecordsDTO.setFileSequenceNumber(sn);

        List<HeadImportRecordsDTO> importRecordList = couponBatchFacade.findHeadImportRecordsAll(headImportRecordsDTO);
        if (!EmptyUtil.isEmpty(importRecordList))
            return JsonResult.fail("文件序列已存在,请确认是否已经导入");


        /**校验缓存**/
        String str = jedisUtil.getString("COUPON_USER_IMPORT"+userId + ":" + sn);
        if(EmptyUtil.isNotEmpty(str)){
            return JsonResult.fail("请清除缓存");
        }


        // ************************************

        // 列表文件校验
        List<CouponUnitUserVO> userVOList = new ArrayList<>();
        List<PropblemReportRowVO<CouponUnitUserVO>> problemRep = new ArrayList<>();
        Set<String> mailSet = new HashSet<String>();

        for (int i = 2; i < valueList.size(); i++) {

            // 内部不重复,空值校验

            CouponUnitUserVO vo = rowBean(valueList.get(i), problemRep, i,  mailSet,platformId);
            userVOList.add(vo);
        }

        // 校验完成,封装数据
        if (problemRep.size() == 0) {

            // 说明没有任何错误
            // 1.经表头信息储存到临时导入记录表,并且封装导入记录表的信息
            // 写导入记录表的数据
            ImportRecordsDTO importRecordsDTO = new ImportRecordsDTO();
            importRecordsDTO.setTemplateType(valueList.get(0).get("CELL2").toString());
            importRecordsDTO.setGeneratedTime(date);
            importRecordsDTO.setFileSequenceNumber(sn);

            Map<String, Object> data = couponFacade.insertuserFacadesAndUserViewList(importRecordsDTO);
            //插入缓存(key:userid,value:序列号list集合)
            //jedisUtil.lPush(userId+"",sn);
            jedisUtil.setString("COUPON_USER_IMPORT"+userId+":"+sn,gson.toJson(userVOList));

            // 将表头信心返回
            data.put("headInfo", importRecordsDTO);
            data.put("overView", userVOList);

            return JsonResult.success("success", data);
        } else {

            // 说明有错误
			/*
			 * 结果集包含内容包含内容: 错误报告url(导入失败) 导入预览批次(导入成功用于查询预览)
			 */
            // 出现错误
            // 生成问题报告.xlsx上传至dfs
            // 封装返回值得集合
            //Map<String, Object> data = new HashMap<>();
            String repUrl = genAndUploadRep(problemRep);

            return JsonResult.fail(repUrl, 169);
        }

    }

    @Override
    public List<Long> assureImportUser(Long userId, Long platformId, String serialNum, Long importUserInfo) {
        String string = jedisUtil.getString(userId + ":" + serialNum);
        if(EmptyUtil.isEmpty(string)){
            throw new BusinessException("无缓存信息重新导入");
        }
        List<CouponUnitUserVO> userVOList = gson.fromJson(string, List.class);
        List<Long> userIdList = new ArrayList<>();
        for(CouponUnitUserVO vo:userVOList){
            userIdList.add(vo.getUserId());
        }
//同步记录表
        couponFacade.updateHeadImport(importUserInfo);
        //清除缓存
        jedisUtil.del("COUPON_USER_IMPORT"+userId + ":" + serialNum);

        return userIdList;
    }

    @Override
    public void deleteCache(Long userId) {
        Set<String> keys = jedisUtil.getKeys("COUPON_USER_IMPORT"+userId + ":*");
        if(EmptyUtil.isNotEmpty(keys)&&keys.size()>0){
            String[] arr = new String[keys.size()];
            jedisUtil.delKeys(keys.toArray(arr));
        }


    }

    /**
     * 生成和上传问题报告
     *
     * @param problemRep
     * @return
     */
    private String genAndUploadRep(List<PropblemReportRowVO<CouponUnitUserVO>> problemRep) {
        Workbook wb = new XSSFWorkbook();
        Sheet sh = wb.createSheet("优惠券用户导入报告");

        Row headLine = sh.createRow(0);
        headLine.createCell(0).setCellValue("*邮箱");
        headLine.createCell(1).setCellValue("*公司名称");
        headLine.createCell(2).setCellValue("问题");
        headLine.createCell(3).setCellValue("行号");

        for (int i = 1; i < problemRep.size() + 1; i++) {
            PropblemReportRowVO<CouponUnitUserVO> vo = problemRep.get(i - 1);
            Row r = sh.createRow(i);
            CouponUnitUserVO lineMeta = vo.getLineMeta();
            if(EmptyUtil.isNotEmpty(lineMeta.getMail())){
                r.createCell(0).setCellValue(lineMeta.getMail());
            }
            if(EmptyUtil.isNotEmpty(lineMeta.getCompanyName())){
                r.createCell(1).setCellValue(lineMeta.getCompanyName());
            }
            if(EmptyUtil.isNotEmpty(vo.getProblem())){
                r.createCell(2).setCellValue(vo.getProblem());
            }
            if(EmptyUtil.isNotEmpty(vo.getLineNo())){
                r.createCell(3).setCellValue(String.valueOf(vo.getLineNo()));
            }
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            wb.write(bos);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("问题报告生成失败");
        }
        // 文件上传至文件服务器
        String filePath = uploadService.fastDFSUpload(bos.toByteArray(), "xlsx");
        return filePath;
    }
    private CouponUnitUserVO rowBean(Map<String, Object> row, List<PropblemReportRowVO<CouponUnitUserVO>> problemRep, int rowNo,
                               Set<String> mailSet,Long platformId) {
        // 创建对象
        CouponUnitUserVO userVO = new CouponUnitUserVO();

        // 2 邮箱
        String mail = (String) row.get("CELL1");
        UserDTO userByMail = couponFacade.findUserByMail(mail);
        if(EmptyUtil.isEmpty(userByMail)){
            problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "导入数据邮箱不存在该用户", rowNo + 1, userVO));
        }
        if(EmptyUtil.isNotEmpty(mail)){
            if (!MailCheckUtils.checkEmail(mail)) {
                problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "导入数据邮箱格式不合法", rowNo + 1, userVO));
            }else{
                if (!mailSet.add(mail.toLowerCase())) {
                    problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "导入数据邮箱重复", rowNo + 1, userVO));
                }
                userVO.setMail(mail.toLowerCase());
            }
        }else{
            problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "导入数据邮箱不不能为空", rowNo + 1, userVO));
        }

        String companyName = (String) row.get("CELL2");
        if(EmptyUtil.isNotEmpty(companyName)){
            if(EmptyUtil.isNotEmpty(userByMail)){
                CompanyDTO companyDTO=couponFacade.findCompanyById(userByMail.getCompanyId());
                if(!companyDTO.getCompanyName().equals(companyName)){
                    problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "公司名称有误", rowNo + 1, userVO));
                }
            }
        }else{
            problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "公司名称不能为空", rowNo + 1, userVO));
        }
        userVO.setCompanyName(companyName);
        if(EmptyUtil.isNotEmpty(userByMail)){
            userVO.setUserId(userByMail.getId());
        }
        return userVO;
    }

    private boolean insertCouponUnitWithTx(Long userId, CouponBatchDTO couponBatchDTO_, CouponBatchDTO dto,Integer index) {
        if (couponBatchDTO_.getIsDisplay() == 0 || couponBatchDTO_.getCouponRelType() == 1
                || couponBatchDTO_.getGrantType() == 0) {
            return false;  //优惠卷批次不可领取
        }
        CouponDTO couponDTO = new CouponDTO();
        couponDTO.setId(couponBatchDTO_.getCouponRelId());
        CouponDTO couponDTO_ = couponFacade.findCouponById(couponDTO);
        if (EmptyUtil.isEmpty(couponDTO_)) {
            return false;  //优惠卷信息有误,优惠卷不存在
        }
        // 判断优惠券是否已领完
        CouponUnitDTO couponUnitDTO2 = new CouponUnitDTO();
        couponUnitDTO2.setCouponBatchId(couponBatchDTO_.getId());
        List<CouponUnitDTO> couponUnitDTOList = couponUnitFacade.findCouponUnitAll(couponUnitDTO2);
        if (EmptyUtil.isEmpty(couponBatchDTO_.getGrantCount())) {
            return false;
        }
        if (couponBatchDTO_.getGrantCount().intValue() != -1 && couponBatchDTO_.getGrantCount().intValue() <= couponUnitDTOList.size()) {
            return false;
        }
        // 验证通过,添加优惠卷unit
        CouponUnitDTO couponUnitDTO_ = new CouponUnitDTO();
        couponUnitDTO_.setBatchIndex(Long.valueOf(index));
        couponUnitDTO_.setCouponUnitCode(SequenceUtil.genCouponUnitNo(couponDTO_.getCouponType(),index));
        couponUnitDTO_.setTitle(couponDTO_.getTitle());
        couponUnitDTO_.setPlatformId(dto.getPlatformId());
        if (EmptyUtil.isNotEmpty(couponUnitFacade.findCouponUnitAll(couponUnitDTO_))) {
            return false;   //优惠卷unit编号重复
        }
        couponUnitDTO_.setCouponId(couponBatchDTO_.getCouponRelId());
        couponUnitDTO_.setCouponBatchId(couponBatchDTO_.getId());
        couponUnitDTO_.setUserId(userId);
        //领取时间
        couponUnitDTO_.setReceivedTime(new Date());
        // 设置优惠卷unit的有效时间
        if (couponBatchDTO_.getEffectDays() != null
                && !couponBatchDTO_.getEffectDays().equals(Integer.valueOf(-1))) {
            couponUnitDTO_.setEffectStartTime(new Date());
            couponUnitDTO_.setEffectEndTime(DateUtils.addDays(new Date(), couponBatchDTO_.getEffectDays().intValue()));
        } else {
            couponUnitDTO_.setEffectStartTime(couponBatchDTO_.getEffectStartTime());
            couponUnitDTO_.setEffectEndTime(couponBatchDTO_.getEffectEndTime());
        }
        Long i = couponUnitFacade.insertCouponUnitWithTx(couponUnitDTO_);
        return  i > 0;
    }


    private void avoidParam(CouponUnitDTO couponUnitDTO) {
        if (EmptyUtil.isEmpty(couponUnitDTO.getCouponBatchId())) {
            throw new BusinessException("优惠卷批次的id不能为空");
        }
        if (EmptyUtil.isEmpty(couponUnitDTO.getUserId())) {
            throw new BusinessException("用户的useId不能为空");
        }
        if (EmptyUtil.isEmpty(couponUnitDTO.getCompanyId())) {
            throw new BusinessException("用户的公司id不能为空");
        }
    }

}
