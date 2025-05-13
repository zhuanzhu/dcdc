package com.egeo.components.promotion.facade;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.egeo.common.PlatformKeyConstant;
import com.egeo.components.cms.client.LinkableButtonClient;
import com.egeo.components.cms.client.LinkableButtonPageClient;
import com.egeo.components.cms.dto.LinkableButtonDTO;
import com.egeo.components.cms.dto.LinkableButtonPageDTO;
import com.egeo.components.config.client.HeadImportRecordsClient;
import com.egeo.components.config.dto.HeadImportRecordsDTO;
import com.egeo.components.finance.constant.AccountConstant;
import com.egeo.components.product.client.StoreClient;
import com.egeo.components.product.dto.StoreDTO;
import com.egeo.components.promotion.dto.CouponBatchCompanyDTO;
import com.egeo.components.promotion.dto.CouponBatchDTO;
import com.egeo.components.promotion.dto.CouponCompanyDTO;
import com.egeo.components.promotion.dto.CouponDTO;
import com.egeo.components.promotion.dto.CouponGroupDTO;
import com.egeo.components.promotion.dto.CouponGroupRelDTO;
import com.egeo.components.promotion.dto.CouponGroupStoreDTO;
import com.egeo.components.promotion.dto.CouponStoreDTO;
import com.egeo.components.promotion.dto.CouponUnitDTO;
import com.egeo.components.promotion.service.read.CouponBatchCompanyReadService;
import com.egeo.components.promotion.service.read.CouponBatchReadService;
import com.egeo.components.promotion.service.read.CouponCompanyReadService;
import com.egeo.components.promotion.service.read.CouponGroupReadService;
import com.egeo.components.promotion.service.read.CouponGroupRelReadService;
import com.egeo.components.promotion.service.read.CouponGroupStoreReadService;
import com.egeo.components.promotion.service.read.CouponReadService;
import com.egeo.components.promotion.service.read.CouponStoreReadService;
import com.egeo.components.promotion.service.read.CouponUnitReadService;
import com.egeo.components.promotion.service.write.CouponBatchWriteService;
import com.egeo.components.promotion.service.write.CouponUnitWriteService;
import com.egeo.components.user.client.ChannelActivityClient;
import com.egeo.components.user.client.CompanyClient;
import com.egeo.components.user.client.QrCodeClient;
import com.egeo.components.user.client.SendInfoClient;
import com.egeo.components.user.client.UserClient;
import com.egeo.components.user.client.UserExtendClient;
import com.egeo.components.user.constant.InfoConstant;
import com.egeo.components.user.dto.ChannelActivityDTO;
import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.components.user.dto.InsertAndSendMessageDTO;
import com.egeo.components.user.dto.QrCodeDTO;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.components.user.dto.UserExtendAllByCompanyOfPageDTO;
import com.egeo.components.user.dto.UserExtendDTO;
import com.egeo.components.user.fo.UpdateQrCodeByCouponUnitIdsFO;
import com.egeo.core.Constant.CmsConstant;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.CodeUtils;
import com.egeo.utils.DateUtils;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.SequenceUtil;


@Component
public class CouponBatchFacade {

    @Autowired
    private UserClient userReadService;

    @Autowired
    private UserExtendClient userExtendReadService;

    @Autowired
    private CompanyClient companyReadService;

    @Autowired
    private CouponBatchReadService couponBatchReadService;

    @Autowired
    private CouponBatchWriteService couponBatchWriteService;

    @Autowired
    private CouponGroupRelReadService couponGroupRelReadService;

    @Autowired
    private CouponCompanyReadService couponCompanyReadService;

    @Autowired
    private CouponBatchCompanyReadService couponBatchCompanyReadService;

    @Autowired
    private CouponUnitReadService couponUnitReadService;

    @Autowired
    private SendInfoClient sendInfoWriteService;

    @Autowired
    private CouponReadService couponReadService;

    @Autowired
    private StoreClient storeReadService;

    @Autowired
    private CouponStoreReadService couponStoreReadService;

    @Autowired
    private CouponGroupStoreReadService couponGroupStoreReadService;

    @Autowired
    private ChannelActivityClient channelActivityReadService;

    @Autowired
    private QrCodeClient qrCodeWriteService;
    @Autowired
    private QrCodeClient qrCodeReadService;
    @Autowired
    private CouponUnitWriteService couponUnitWriteService;
    
    @Autowired
    private LinkableButtonClient linkableButtonReadService;
    
    @Autowired
    private LinkableButtonClient linkableButtonWriteService;
    
    @Autowired
    private LinkableButtonPageClient linkableButtonPageWriteService;
    
    @Autowired
    private LinkableButtonPageClient linkableButtonPageReadService;
    
    @Autowired
    private HeadImportRecordsClient headImportRecordsReadService;
    
    @Autowired
    private CouponGroupReadService couponGroupReadService;

    public CouponBatchDTO findCouponBatchById(CouponBatchDTO dto) {

        return couponBatchReadService.findCouponBatchById(dto);
    }

    public PageResult<CouponBatchDTO> findCouponBatchOfPage(CouponBatchDTO dto, Pagination page) {

        return couponBatchReadService.findCouponBatchOfPage(dto, page);

    }

    public List<CouponBatchDTO> findCouponBatchAll(CouponBatchDTO dto) {

        return couponBatchReadService.findCouponBatchAll(dto);

    }

    public List<CouponBatchDTO> findCouponBatchByCouponIds(CouponBatchDTO dto, List<Long> couponIdList, Map<String, Object> key) {
        return couponBatchReadService.findCouponBatchByCouponIds(dto, couponIdList, key);
    }


    public Long insertCouponBatchAndUnitWithTx(CouponBatchDTO dto, Long channelActivityId, Long channelId) {
        //1.查询coupon获取优惠卷的store集合,确定branchId参数
        //如果优惠卷所属门店中不包含平台对应的总店则已选择的第一个门店id为branchid,如果包含则以该总店id作为branchid
        Long branchId=null;
        CouponStoreDTO couponStoreDTO = new CouponStoreDTO();
        couponStoreDTO.setCouponId(dto.getCouponRelId());
        List<CouponStoreDTO> couponStoreAll = couponStoreReadService.findCouponStoreAll(couponStoreDTO);
        List<Long> couponStoreList = new ArrayList<>();
        for(CouponStoreDTO storeDTO:couponStoreAll){
            couponStoreList.add(storeDTO.getStoreId());
        }
        //couponStoreAll.stream().map(couponStore -> couponStore.getStoreId()).collect(Collectors.toList());
        Long store=null;
        if(dto.getPlatformId().equals(7L)){
            store=1L;
        }else if(dto.getPlatformId().equals(2L)){
            store=2L;
        }
        if(couponStoreList.contains(store)){
            branchId = store;
        }else{
            branchId = couponStoreList.get(0);
        }
        
        //TODO
        Long linkableId = insertLinkable(dto);     
        dto.setLinkableId(linkableId);
        //2.插入coupon_unit和coupon_batch
        Long couponBatchId=couponBatchWriteService.insertCouponBatchAndUnitWithTx(dto);
        
        dto.setId(couponBatchId);
        //插入coupon_unit
        // 优惠卷批次为用户领取,且领取方式为unit领取时生成coupon_unit
        if (dto.getGrantType() == 1&&dto.getGetType()==2) {
            int size=100;
            int page =dto.getGrantCount()%size==0?dto.getGrantCount()/size:dto.getGrantCount()/size+1;
           // int page = dto.getGrantCount() / size+1;//总次数
            Pagination pagination = new Pagination();
            pagination.setPageSize(size);
            for(int i=0;i<page;i++){
                int count=size;
                if(i==(page-1)&&dto.getGrantCount()%size>0){
                    count=dto.getGrantCount()%size;
                }
                pagination.setPageNo(i+1);
                pagination.setOrderBy("id");
                int startIndex = i * size;
                insertCouponUnitAndRdid(startIndex,dto,branchId,channelActivityId,channelId,count,pagination);
            }


        }
        
        
        return couponBatchId;
    }
    private void insertCouponUnitAndRdid(int startIndex, CouponBatchDTO dto, Long branchId, Long channelActivityId, Long channelId, Integer size, Pagination pagination){
        List<CouponUnitDTO> couponUnitDTOList = new ArrayList<>();
        // 1.优惠卷unit
        insertCouponUnitByCouponBatchAndCount(startIndex,dto,size);
        //查询刚刚插入的couponunit
        CouponUnitDTO couponUnitDTO = new CouponUnitDTO();
        couponUnitDTO.setCouponBatchId(dto.getId());
        couponUnitDTOList=couponUnitReadService.findCouponUnitOfPage(couponUnitDTO,pagination).getList();

        //3.为每一个coupon_unit生成唯一的rdid
        List<QrCodeDTO> qrCodeDTOList = new ArrayList<>();
        //查询活动信息
        ChannelActivityDTO activityDTO = new ChannelActivityDTO();
        activityDTO.setId(channelActivityId);
        ChannelActivityDTO channelActivityDTO = channelActivityReadService.findChannelActivityById(activityDTO);
        for(CouponUnitDTO unitDTO:couponUnitDTOList){
            //生成10位随机码
            String code = CodeUtils.generateCode(10);
            QrCodeDTO qrCodeDTO = new QrCodeDTO();
            qrCodeDTO.setRdid(code);
            qrCodeDTO.setTypeId(unitDTO.getId());
            qrCodeDTO.setBranchId(branchId);
            qrCodeDTO.setCampaignCode(channelActivityDTO.getShortCode());
            qrCodeDTO.setCampaignId(channelActivityDTO.getId());
            qrCodeDTO.setPlatformId(dto.getPlatformId());
            qrCodeDTO.setChannelId(channelId);
            qrCodeDTOList.add(qrCodeDTO);
        }
        //批量插入qrcode
        qrCodeWriteService.insertQrCodeListWithTx(qrCodeDTOList);
    }

    private void insertCouponUnitByCouponBatchAndCount(int startIndex, CouponBatchDTO dto, Integer size) {
        CouponDTO couponDTO = new CouponDTO();
        couponDTO.setId(dto.getCouponRelId());
        couponDTO = couponReadService.findCouponById(couponDTO);
        List<CouponUnitDTO> list = new ArrayList<>();
        for(int i=0;i<size;i++){
            CouponUnitDTO couponUnitDTO= new CouponUnitDTO();
            couponUnitDTO.setBatchIndex(Long.valueOf(startIndex+i));
            couponUnitDTO.setCouponUnitCode(SequenceUtil.genCouponUnitNo(couponDTO.getCouponType(),i));
           /*List<CouponUnitDTO> couponUnitPOList = couponUnitReadService.findCouponUnitAll(couponUnitDTO);
            if (EmptyUtil.isNotEmpty(couponUnitPOList))
                throw new BusinessException("新增优惠卷unit失败: 优惠卷unit编号重复");
*/
            couponUnitDTO.setCouponId(couponDTO.getId());
            couponUnitDTO.setCouponBatchId(dto.getId());
            couponUnitDTO.setPlatformId(dto.getPlatformId());
            couponUnitDTO.setCouponUnitStatus(Integer.valueOf(0));
            // 设置有效期
            if (dto.getEffectDays() != null
                    && !dto.getEffectDays().equals(Integer.valueOf(-1))) {
                couponUnitDTO.setEffectStartTime(null);
                couponUnitDTO.setEffectEndTime(null);
            } else {
                couponUnitDTO.setEffectStartTime(dto.getEffectStartTime());
                couponUnitDTO.setEffectEndTime(dto.getEffectEndTime());
            }
            list.add(couponUnitDTO);

        }
        Integer res=couponUnitWriteService.insertCouponUnitListWithTx(list);
        if(EmptyUtil.isEmpty(res)){
            throw new BusinessException("插入失败");
        }


    }
    public Long insertCouponBatchWithTx(CouponBatchDTO dto) {
    	List<UserExtendDTO> userExtendDTOList = null;
        if (dto.getGrantType() == 0 && dto.getChooseWay() == 0) {
            // 优惠卷批次为系统发放,直接生成优惠卷unit
            // 且选择员工方式为企业
            List<Long> empList = new ArrayList<Long>();
            for (Long companyId : dto.getCompList()) {
                UserExtendDTO userExtendDTO = new UserExtendDTO();
                // 员工筛选条件:在职,有效,未删除,公司
                userExtendDTO.setCompanyId(companyId);
                userExtendDTO.setIsAvailable(Integer.valueOf(1));
                userExtendDTO.setAccountStatus(Integer.valueOf(0));
                userExtendDTO.setIsDeleted(Integer.valueOf(0));
                userExtendDTOList = userExtendReadService.queryUserByCondition(userExtendDTO);
                for (UserExtendDTO user : userExtendDTOList) {
                    empList.remove(user.getId());
                    empList.add(user.getId());
                }
            }

            dto.setEmpList(empList);
        }
        //插入跳转链接配置
        Long linkableId = insertLinkable(dto);
        dto.setLinkableId(linkableId);
        Long couponBatchId = couponBatchWriteService.insertCouponBatchWithTx(dto);
        // 发送优惠卷消息发送
//        sendCouponInfo(dto);
        
        /***********发送消息*********/
        Long infoTemplateId = null;
		Map<String, String> params = new HashMap<>();
		if (dto.getCouponRelType() == 0) {
			// 优惠卷
			CouponDTO couponDTO = new CouponDTO();
			couponDTO.setId(dto.getCouponRelId());
			CouponDTO couponDTO1 = couponReadService.findCouponById(couponDTO);
			params.put("优惠券名称", couponDTO1.getTitle());
			params.put("优惠券数量", "1");
			if (PlatformKeyConstant.MYY_PLATFORM_ID.equals(dto.getPlatformId())) {
				infoTemplateId = InfoConstant.MYY_GIVE_COUPON.getStatus();
			} else {
				infoTemplateId = InfoConstant.FGJ_GIVE_COUPON.getStatus();
			}
		} else if (dto.getCouponRelType() == 1) {
			// 优惠卷分组
			CouponGroupDTO couponGroupDTO = new CouponGroupDTO();
			couponGroupDTO.setId(dto.getCouponRelId());
			CouponGroupDTO couponGroupDTO1 = couponGroupReadService.findCouponGroupById(couponGroupDTO);
			params.put("优惠券组名称", couponGroupDTO1.getGroupName());
			CouponGroupRelDTO groupRel = new CouponGroupRelDTO();
			List<CouponGroupRelDTO> groupRelList = couponGroupRelReadService.findCouponGroupRelAll(groupRel);
			params.put("券数量", groupRelList == null ? "0" : groupRelList.size() + "");
			if (PlatformKeyConstant.MYY_PLATFORM_ID.equals(dto.getPlatformId())) {
				infoTemplateId = InfoConstant.MYY_GIVE_COUPON_GROUP.getStatus();
			} else {
				infoTemplateId = InfoConstant.FGJ_GIVE_COUPON_GROUP.getStatus();
			}
		}
		
		params.put("有效起始日期", dto.getEffectStartTime() == null ? "不限" : DateUtils.convert2String(dto.getEffectStartTime().getTime()));
		params.put("有效截止日期", dto.getEffectEndTime() == null ? "不限" : DateUtils.convert2String(dto.getEffectEndTime().getTime()));
		
		if (EmptyUtil.isNotEmpty(dto.getEmpList())) {
			for (Long uId : dto.getEmpList()) {
				sendInfoWriteService.insertAndSendMessage(new InsertAndSendMessageDTO(infoTemplateId, params, uId, null));
			}
		}
		/***********发送消息*********/

        return couponBatchId;
    }

    /**
     * 发送优惠卷消息发送
     *
     * @param dto
     */
    private void sendCouponInfo(CouponBatchDTO dto) {
        if (dto.getGrantType() == 0 && (dto.getChooseWay() == 0 || dto.getChooseWay() == 1)) {
            String couponName = null;
            Integer couponQuantity = null;
            // 默认值
            String startTime = DateUtils.getDefaultDate(new Date());
            String stopTime = "无限";
            if (dto.getCouponRelType() == 0) {
                // 根据优惠卷id查询优惠卷信息
                CouponDTO couponDTO = new CouponDTO();
                couponDTO.setId(dto.getCouponRelId());
                CouponDTO couponDTO2 = couponReadService.findCouponById(couponDTO);
                couponName = couponDTO2.getTitle();
                couponQuantity = 1;
                // 设置有效期
                if (dto.getEffectDays() != null
                        && !dto.getEffectDays().equals(Integer.valueOf(-1))) {
                    startTime = DateUtils.getDefaultDate(new Date());
                    stopTime = DateUtils.getDefaultDate(DateUtils.addDays(new Date(), dto.getEffectDays().intValue()));
                } else {
                    if (EmptyUtil.isNotEmpty(dto.getEffectStartTime()))
                        startTime = DateUtils.getDefaultDate(dto.getEffectStartTime());
                    if (EmptyUtil.isNotEmpty(dto.getEffectEndTime()))
                        stopTime = DateUtils.getDefaultDate(dto.getEffectEndTime());
                }
            }
            if (dto.getCouponRelType() == 1) {
                // 根据优惠卷组id查询优惠卷信息
                List<CouponDTO> couponList = couponReadService.findCouponByCouponGroupId(dto.getCouponRelId());
                couponName = couponList.get(0).getTitle() + "等";
                couponQuantity = couponList.size();
                // 设置有效期
                if (dto.getEffectDays() != null
                        && !dto.getEffectDays().equals(Integer.valueOf(-1))) {
                    startTime = DateUtils.getDefaultDate(new Date());
                    stopTime = DateUtils.getDefaultDate(DateUtils.addDays(new Date(), dto.getEffectDays().intValue()));
                } else {
                    if (EmptyUtil.isNotEmpty(dto.getEffectStartTime()))
                        startTime = DateUtils.getDefaultDate(dto.getEffectStartTime());
                    if (EmptyUtil.isNotEmpty(dto.getEffectEndTime()))
                        stopTime = DateUtils.getDefaultDate(dto.getEffectEndTime());
                }
            }

            for (Long userId : dto.getEmpList()) {
                CompanyDTO companyDTO = companyReadService.queryCompanyByUserId(userId);
    			InsertAndSendMessageDTO infoDto = new InsertAndSendMessageDTO();
    			Map<String,String> infoMap = new HashMap<String,String>();
    			infoDto.setInfoTemplateId(InfoConstant.SEND_COUPON_INFO_ID.getStatus());
    			infoDto.setUserId(userId);
    			infoMap.put("issuingParty", companyDTO.getCompanyName());
    			infoMap.put("couponName", couponName);
    			infoMap.put("couponQuantity",couponQuantity.intValue()+"");
    			infoMap.put("startTime", startTime);
    			infoMap.put("stopTime", stopTime);
    			infoDto.setParams(infoMap);
                sendInfoWriteService.insertSendCouponInfoAndSend(infoDto);
            }
        }

    }

    public int updateCouponBatchWithTx(CouponBatchDTO dto) {
    	
    	CouponBatchDTO dtoTemp = couponBatchReadService.findCouponBatchById(dto);
    	
    	//前期 直接删除所有跳转 再添加
    	if(dtoTemp.getCouponRelType() == 0) {
    		dto.setLinkableId(dtoTemp.getLinkableId());
    		updateLinkable(dto);
        	
    	}
    	
        return couponBatchWriteService.updateCouponBatchWithTx(dto);
    }

    public int deleteCouponBatchWithTx(CouponBatchDTO dto) {

        return couponBatchWriteService.deleteCouponBatchWithTx(dto);

    }

    public int invalidCouponBatchWithTx(CouponBatchDTO dto) {
        return couponBatchWriteService.invalidCouponBatchWithTx(dto);
    }

    /**
     * 通过优惠卷批次查询所以公司信息
     *
     * @param dto
     * @return
     */
    public List<Map<String, Object>> findCompanyByCouponBatch(CouponBatchDTO dto) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        if (dto.getCouponRelType() == 0) {
            // 优惠卷
            findCompanyInfoByCouponId(dto.getCouponRelId(), list);
        } else if (dto.getCouponRelType() == 1) {
            // 优惠卷分组
            CouponGroupRelDTO couponGroupRelDTO = new CouponGroupRelDTO();
            List<CouponGroupRelDTO> couponGroupRelDTOList = couponGroupRelReadService.findCouponGroupRelAll(couponGroupRelDTO);

            for (CouponGroupRelDTO couponGroupRelDTO_ : couponGroupRelDTOList) {
                findCompanyInfoByCouponId(couponGroupRelDTO_.getCouponId(), list);
            }
        }

        return list;
    }

    /**
     * 通过优惠卷id,查询公司信息
     *
     * @param couponId
     * @param list
     */
    private void findCompanyInfoByCouponId(Long couponId, List<Map<String, Object>> list) {
        CouponCompanyDTO couponCompanyDTO = new CouponCompanyDTO();
        couponCompanyDTO.setCouponId(couponId);
        List<CouponCompanyDTO> couponCompanyDTOList = couponCompanyReadService.findCouponCompanyAll(couponCompanyDTO);
        for (CouponCompanyDTO couponCompanyDTO_ : couponCompanyDTOList) {
            CompanyDTO companyDTO = companyReadService.findCompanyById(couponCompanyDTO_.getCompanyId());
            if (companyDTO != null) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id", companyDTO.getId());
                map.put("name", companyDTO.getCompanyName());

                // 该企业的员工数量
                UserDTO userDTO = new UserDTO();
                userDTO.setCompanyId(companyDTO.getId());
                List<UserDTO> userDTOList = userReadService.findUser(userDTO);
                map.put("empCount", userDTOList.size());

                list.add(list.contains(map) ? null : map);
            }
        }
    }

    /**
     * 查询优惠卷批次的选中公司信息
     *
     * @param dto
     * @return
     */
    public List<Long> queryCouponBatchCompany(CouponBatchDTO dto) {
        List<Long> list = new ArrayList<Long>();
        CouponBatchCompanyDTO couponBatchCompanyDTO = new CouponBatchCompanyDTO();
        couponBatchCompanyDTO.setCouponBatchId(dto.getId());
        List<CouponBatchCompanyDTO> couponBatchCompanyDTOList = couponBatchCompanyReadService.findCouponBatchCompanyAll(couponBatchCompanyDTO);
        for (CouponBatchCompanyDTO couponBatchCompanyDTO_ : couponBatchCompanyDTOList) {
            list.add(couponBatchCompanyDTO_.getCompanyId());
        }

        return list;
    }

    /**
     * 查询优惠卷批次下的选中的用户信息
     *
     * @param dto
     * @return
     */
    public PageResult<Map<String, Object>> queryCouponBatchEmployee(CouponBatchDTO dto, Pagination page) {

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        CouponUnitDTO couponUnitDTO = new CouponUnitDTO();
        couponUnitDTO.setCouponBatchId(dto.getId());
        PageResult<CouponUnitDTO> couponUnitDTOPageResult = couponUnitReadService.findCouponUnitOfPage(couponUnitDTO, page);

        for (CouponUnitDTO couponUnitDTO_ : couponUnitDTOPageResult.getList()) {
            UserExtendDTO userExtendDTO = userExtendReadService.userByUserId(couponUnitDTO_.getUserId());
            if (userExtendDTO == null)
                throw new BusinessException(-1, "用户不存在,id: " + couponUnitDTO_.getUserId());
            addCouponBatchEmployee(userExtendDTO, list);
        }

        PageResult<Map<String, Object>> result = new PageResult<Map<String, Object>>();
        result.setList(list);
        result.setPageNo(couponUnitDTOPageResult.getPageNo());
        result.setPageSize(couponUnitDTOPageResult.getPageSize());
        result.setTotalSize(couponUnitDTOPageResult.getTotalSize());

        return result;
    }

    /**
     * 查询优惠卷批次下的用户信息
     *
     * @param dto
     * @return
     */
    public PageResult<Map<String, Object>> queryAllCouponBatchEmployee(CouponBatchDTO dto, Pagination page) {

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        List<Long> companyList = new ArrayList<Long>();
        if (dto.getCouponRelType() == 0) {
            // 优惠卷
            // 查询优惠卷下的公司信息
            getCouponCompanyIdList(companyList, dto.getCouponRelId());

        } else if (dto.getCouponRelType() == 1) {
            // 优惠卷分组
            // 查询优惠卷分组下的优惠卷信息
            CouponGroupRelDTO couponGroupRelDTO = new CouponGroupRelDTO();
            couponGroupRelDTO.setCouponGroupId(dto.getCouponRelId());
            List<CouponGroupRelDTO> couponGroupRelDTOList = couponGroupRelReadService.findCouponGroupRelAll(couponGroupRelDTO);

            for (CouponGroupRelDTO couponGroupRelDTO_ : couponGroupRelDTOList) {
                // 查询优惠卷下的公司信息
                getCouponCompanyIdList(companyList, couponGroupRelDTO_.getCouponId());
            }
        }

        // 通过公司查询员工信息
        PageResult<UserExtendDTO> rt = userExtendReadService.userExtendAllByCompanyOfPage(new UserExtendAllByCompanyOfPageDTO(companyList, page));
        for (UserExtendDTO userExtendDTO : rt.getList()) {

            addCouponBatchEmployee(userExtendDTO, list);
        }

        PageResult<Map<String, Object>> result = new PageResult<Map<String, Object>>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());

        return result;
    }

    private void addCouponBatchEmployee(UserExtendDTO userExtendDTO, List<Map<String, Object>> list) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", userExtendDTO.getId());
        map.put("name", userExtendDTO.getName());
        map.put("memberCode", userExtendDTO.getMemberCode());
        map.put("mail", userExtendDTO.getMail());
        map.put("mobile", userExtendDTO.getMobile());
        map.put("sex", userExtendDTO.getSex());
        map.put("birthday", userExtendDTO.getBirthday() != null
                ? DateUtils.getDefaultDate(userExtendDTO.getBirthday()) : null);
        map.put("companyName", userExtendDTO.getCompanyName());
        map.put("departmentName", userExtendDTO.getDepartmentName());
        map.put("status", userExtendDTO.getStatus());

        list.add(map);
    }

    /**
     * 获取优惠卷下的公司id集合
     *
     * @param companyList
     * @param couponId
     */
    private void getCouponCompanyIdList(List<Long> companyList, Long couponId) {
        CouponCompanyDTO couponCompanyDTO = new CouponCompanyDTO();
        couponCompanyDTO.setCouponId(couponId);
        List<CouponCompanyDTO> couponCompanyDTOList = couponCompanyReadService.findCouponCompanyAll(couponCompanyDTO);

        for (CouponCompanyDTO couponCompanyDTO_ : couponCompanyDTOList) {
            // 通过公司查询员工信息
            companyList.add(couponCompanyDTO_.getCompanyId());
        }
    }

    /**
     * 查询优惠卷批次列表
     *
     * @param dto
     * @param page
     * @return
     */
    public PageResult<CouponBatchDTO> findCouponBatchOfPageByBlurry(CouponBatchDTO dto, Pagination page) {
    	
        return couponBatchReadService.findCouponBatchOfPageByBlurry(dto, page);
    }
    
    /**
     * 根据linkableId查询cmspage
     * @param linkableId
     * @return
     */
    public List<LinkableButtonPageDTO> findCmsPageByLinkableId(Long linkableId){
    	LinkableButtonPageDTO pageDTO = new LinkableButtonPageDTO();
    	pageDTO.setLinkableId(linkableId);
    	List<LinkableButtonPageDTO> list = linkableButtonPageReadService.findCmsPageByLinkableId(linkableId);
    	return list;
    }
    
    /**
       *   插入跳转相关表
     * @param dto
     */
    private Long insertLinkable(CouponBatchDTO dto) {
    	
    	LinkableButtonDTO linkableButtonDTO = new LinkableButtonDTO();
    	linkableButtonDTO.setLinkType(CmsConstant.CMS_LINK_TYPE_SU_LIST);
    	
    	Long linkableId = linkableButtonWriteService.insertLinkableButtonWithTx(linkableButtonDTO);
        
        if(linkableId != null && StringUtils.isNotBlank(dto.getLinkableButtonPageList())) {
        	
        	List<LinkableButtonPageDTO> listDto = JSONArray.parseArray(dto.getLinkableButtonPageList(), LinkableButtonPageDTO.class);
            if(listDto != null && listDto.size() > 0) {
            	for (LinkableButtonPageDTO linkableButtonPageDTO : listDto) {
            		linkableButtonPageDTO.setLinkableId(linkableId);
				}
            	linkableButtonPageWriteService.insertBatchLinkableButtonPageWithTx(listDto);
            }
        }
        
        return linkableId;
    }
    
    private void updateLinkable(CouponBatchDTO dto) {
    	
    	if(dto.getLinkableId() != null && StringUtils.isNotBlank(dto.getLinkableButtonPageList())) {
    		
    		linkableButtonPageWriteService.deleteLinkableButtonPageByLinkableIdWithTx(new LinkableButtonPageDTO(dto.getLinkableId()));
    		
    		//如果 是商品组
    			
			List<LinkableButtonPageDTO> listDto = JSONArray.parseArray(dto.getLinkableButtonPageList(), LinkableButtonPageDTO.class);
            if(listDto != null && listDto.size() > 0) {
            	for (LinkableButtonPageDTO linkableButtonPageDTO : listDto) {
            		linkableButtonPageDTO.setLinkableId(dto.getLinkableId());
				}
            	linkableButtonPageWriteService.insertBatchLinkableButtonPageWithTx(listDto);
            }
        	
        }
    	
    }

    public String findUserNameById(Long creator) {
        UserExtendDTO userExtendDTO = userExtendReadService.findById(creator);
        return userExtendDTO == null ? null : userExtendDTO.getName();
    }
    public Long findUserChannelById(Long userId) {
        UserExtendDTO userExtendDTO = userExtendReadService.findById(userId);
        return userExtendDTO == null ? null : userExtendDTO.getChannelId();
    }
    public List<Long> queryCouponBatchEmployeeId(CouponBatchDTO dto) {
        CouponUnitDTO couponUnitDTO = new CouponUnitDTO();
        couponUnitDTO.setCouponBatchId(dto.getId());
        List<CouponUnitDTO> couponUnitDTOList = couponUnitReadService.findCouponUnitAll(couponUnitDTO);
        List<Long> list = new ArrayList<Long>();
        for (CouponUnitDTO couponUnitDTO_ : couponUnitDTOList) {
            list.add(couponUnitDTO_.getUserId());
        }
        return list;
    }

    public UserExtendDTO findUserInfo(Long userId) {
        return userExtendReadService.findById(userId);
    }

    public StoreDTO findStoreDTO(Long storeId) {
        StoreDTO dto = new StoreDTO();
        dto.setId(storeId);

        return storeReadService.findStoreById(dto);
    }

    public List<CouponStoreDTO> findCouponStore(Long storeId) {
        CouponStoreDTO dto = new CouponStoreDTO();
        dto.setStoreId(storeId);
        return couponStoreReadService.findCouponStoreAll(dto);
    }

    public List<CouponStoreDTO> findCouponStore(CouponStoreDTO dto) {
        return couponStoreReadService.findCouponStoreAll(dto);
    }

    public List<CouponGroupStoreDTO> findCouponGroupStoreAll(CouponGroupStoreDTO dto) {
        return couponGroupStoreReadService.findCouponGroupStoreAll(dto);
    }


    public List<CouponUnitDTO> findCouponUnitListByBatchIdList(List<Long> couponBatchList) {
        return couponUnitReadService.findCouponUnitListByBatchIdList(couponBatchList);
    }

    public List<QrCodeDTO> findQrCodeListByCouponUnitIds(List<Long> couponUnitIds) {
        return qrCodeReadService.findQrCodeListByCouponUnitIds(com.egeo.utils.StringUtils.longsToStrings(couponUnitIds));
    }

    public QrCodeDTO findQrCodeByCouponUnitId(Long id) {

        return qrCodeReadService.findQrCodeByCouponUnitId(id);
    }

    public void updateQrCodeByCouponUnitIds(List<Long> couponUnitIds, Long channelId, Long channelActivityId) {
        qrCodeWriteService.updateQrCodeByCouponUnitIds(new UpdateQrCodeByCouponUnitIdsFO(couponUnitIds,channelActivityId,channelId));
    }

    public PageResult<CouponBatchDTO> findCouponBatchByParam(List<Long> batchIdList, String title, String couponBatchCode,String couponBatchName,Integer type, Long platformId, Pagination page) {
        return couponBatchReadService.findCouponBatchByParam(batchIdList,title,couponBatchCode,couponBatchName,type,platformId,page);
    }
    
    public List<LinkableButtonPageDTO> findLinkableButtonPageByLinkableId(Long linkableId){
    	
    	return linkableButtonPageReadService.findLinkableButtonPageAll(new LinkableButtonPageDTO(linkableId));
    }

    public List<HeadImportRecordsDTO> findHeadImportRecordsAll(HeadImportRecordsDTO headImportRecordsDTO) {
        return headImportRecordsReadService.findHeadImportRecordsAll(headImportRecordsDTO);
    }
}
	