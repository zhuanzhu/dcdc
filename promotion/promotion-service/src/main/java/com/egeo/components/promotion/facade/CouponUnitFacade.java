package com.egeo.components.promotion.facade;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.common.NormalConstant;
import com.egeo.components.cms.client.LinkableButtonPageClient;
import com.egeo.components.cms.dto.LinkableButtonPageDTO;
import com.egeo.components.order.client.SoClient;
import com.egeo.components.order.dto.SoDTO;
import com.egeo.components.product.client.StandardUnitClient;
import com.egeo.components.promotion.common.DateUtils;
import com.egeo.components.promotion.dto.CouponBatchDTO;
import com.egeo.components.promotion.dto.CouponStoreDTO;
import com.egeo.components.promotion.dto.CouponUnitDTO;
import com.egeo.components.promotion.dto.ExchangeActivityDTO;
import com.egeo.components.promotion.dto.ExchangeBatchDTO;
import com.egeo.components.promotion.service.read.CouponBatchReadService;
import com.egeo.components.promotion.service.read.CouponStoreReadService;
import com.egeo.components.promotion.service.read.CouponUnitReadService;
import com.egeo.components.promotion.service.read.ExchangeActivityReadService;
import com.egeo.components.promotion.service.read.ExchangeBatchReadService;
import com.egeo.components.promotion.service.write.CouponUnitWriteService;
import com.egeo.components.user.client.CompanyClient;
import com.egeo.components.user.client.QrCodeClient;
import com.egeo.components.user.client.SendInfoClient;
import com.egeo.components.user.client.UserClient;
import com.egeo.components.user.constant.InfoConstant;
import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.components.user.dto.InsertAndSendMessageDTO;
import com.egeo.components.user.dto.QrCodeDTO;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.core.Constant.CmsConstant;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;


@Component
public class CouponUnitFacade {
	
	@Autowired
	private CouponUnitReadService couponUnitReadService;
	@Autowired
	private CouponBatchReadService couponBatchReadService;
	
	@Autowired
	private CouponUnitWriteService couponUnitWriteService;
	
	@Autowired
	private SoClient soReadService;
	
	@Autowired
	private UserClient userReadService;
	
	@Autowired
	private CompanyClient companyReadService;
	
	@Autowired
	private StandardUnitClient standardUnitReadService;
	
	@Autowired
	private SendInfoClient sendInfoWriteService;

	@Autowired
	private CouponStoreReadService couponStoreReadService;

	@Autowired
	private QrCodeClient qrCodeWriteService;
	@Autowired
	private QrCodeClient qrCodeReadService;

	@Autowired
	private ExchangeBatchReadService exchangeBatchReadService;

	@Autowired
	private ExchangeActivityReadService exchangeActivityReadService;
	
	@Autowired
	private LinkableButtonPageClient linkableButtonPageReadService;

	public CouponUnitDTO findCouponUnitById(CouponUnitDTO dto){
		
		return couponUnitReadService.findCouponUnitById(dto);
	}

	public PageResult<CouponUnitDTO> findCouponUnitOfPage(CouponUnitDTO dto,Pagination page){
		
		return couponUnitReadService.findCouponUnitOfPage(dto, page);
		
	}

	public List<CouponUnitDTO> findCouponUnitAll(CouponUnitDTO dto){
		
		return couponUnitReadService.findCouponUnitAll(dto);
		
	}
	public List<CouponStoreDTO> findCouponStoreAll(CouponStoreDTO dto){
		return couponStoreReadService.findCouponStoreAll(dto);
	}


	public Long insertCouponUnitWithTx(CouponUnitDTO dto){
		Long couponUnitId = couponUnitWriteService.insertCouponUnitWithTx(dto);
		
		String startTime = DateUtils.getDefaultDate(new Date());
		String stopTime = "无限";
		CompanyDTO companyDTO = companyReadService.queryCompanyByUserId(dto.getUserId());
		if(EmptyUtil.isNotEmpty(dto.getEffectStartTime()))
			startTime = DateUtils.getDefaultDate(dto.getEffectStartTime());
		if(EmptyUtil.isNotEmpty(dto.getEffectEndTime()))
			stopTime = DateUtils.getDefaultDate(dto.getEffectEndTime());
		InsertAndSendMessageDTO infoDto = new InsertAndSendMessageDTO();
		Map<String,String> infoMap = new HashMap<String,String>();
		infoDto.setInfoTemplateId(InfoConstant.SEND_COUPON_INFO_ID.getStatus());
		infoDto.setUserId( dto.getUserId());
		infoMap.put("issuingParty", companyDTO.getCompanyName());
		infoMap.put("couponName", dto.getTitle());
		infoMap.put("couponQuantity","1");
		infoMap.put("startTime", startTime);
		infoMap.put("stopTime", stopTime);
		infoDto.setParams(infoMap);
		sendInfoWriteService.insertSendCouponInfoAndSend(infoDto);
		return couponUnitId;
	}

	public int updateCouponUnitWithTx(CouponUnitDTO dto){
		
		return couponUnitWriteService.updateCouponUnitWithTx(dto);
	}

	public int deleteCouponUnitWithTx(CouponUnitDTO dto){
		
		return couponUnitWriteService.deleteCouponUnitWithTx(dto);
		
	}

	/**
	 * 通过订单编号查询订单信息
	 * @param orderCode
	 */
	public SoDTO querySoByOrderCode(String orderCode) {
		
		return soReadService.querySoByOrderCode(orderCode);
	}

	/**
	 * 查询用户信息
	 * @param userDTO
	 * @return
	 */
	public List<UserDTO> findUser(UserDTO userDTO) {
		return userReadService.findUser(userDTO);
	}

	/**
	 * 查询优惠卷unit列表
	 * @param dto
	 * @param userList
	 * @param page
	 * @return
	 */
	public PageResult<CouponUnitDTO> findCouponUnitOfPageByBlurry(CouponUnitDTO dto, List<Long> userList, Pagination page) {
		
		return couponUnitReadService.findCouponUnitOfPageByBlurry(dto, userList, page);
	}

	public UserDTO findUserById(Long userId) {
		
		return userReadService.findUserByID(userId);
	}

	public SoDTO querySoById(Long orderId) {
		
		return soReadService.querySoById(orderId==null?-999:orderId);
	}

	/**
	 * 优惠卷unit列表(客户端)
	 * @param dto
	 * @param page
	 * @return
	 */
	public PageResult<CouponUnitDTO> findCouponUnitOfPageByUser(CouponUnitDTO dto, Pagination page) {
		PageResult<CouponUnitDTO> result = couponUnitReadService.findCouponUnitOfPageByUser(dto, page);
		if (EmptyUtil.isNotEmpty(dto.getStoreId())) {
			CompanyDTO companyDTO = companyReadService.findCompanyById(dto.getCompanyId());
			List<CouponUnitDTO> list = result.getList();
			for (CouponUnitDTO couponUnitDTO : list) {
				List<Long> storeIds = couponUnitDTO.getStoreIds();
				if (EmptyUtil.isNotEmpty(storeIds) && storeIds.contains(dto.getStoreId())) {
					if (!hasAvailableSu(couponUnitDTO, companyDTO, dto.getStoreId(), dto.getPlatformId())) {
						couponUnitDTO.setStoreIds(new ArrayList<>());
					}
				}
			}
		}

		return result;
	}
	
	private boolean hasAvailableSu(CouponUnitDTO couponUnitDTO, CompanyDTO companyDTO, Long storeId, Long platformId) {
		int cnt = 0;
		if (couponUnitDTO.getGoodsType() == 0) {
			// 单个SU
			cnt = standardUnitReadService.countCouponSuBySuId(couponUnitDTO.getGoodsId(), storeId,
					companyDTO.getId(), companyDTO.getCompanyType(), platformId);
		} else if (couponUnitDTO.getGoodsType() == 1) {
			// SU商品组
			cnt = standardUnitReadService.countCouponSuBySuCombinationId(couponUnitDTO.getGoodsId(), storeId,
					companyDTO.getId(), companyDTO.getCompanyType(), platformId);
		}
		return cnt > 0;
	}

	/**
	 * 客户端领卷中心
	 * @return
	 */
	public PageResult<CouponUnitDTO> findCouponUnitCenterOfPage(CouponUnitDTO dto, Pagination page) {
		Long companyIdByType = null;
		CompanyDTO companyDTO = companyReadService.findCompanyById(dto.getCompanyId());
		if (companyDTO != null) {
			companyIdByType = NormalConstant.getCompanyIdByCompanyType(companyDTO.getCompanyType());
		}

		PageResult<CouponUnitDTO> result = couponUnitReadService.findCouponUnitCenterOfPage(dto, companyIdByType, page);
		if (EmptyUtil.isNotEmpty(dto.getStoreId())) {
			List<CouponUnitDTO> list = result.getList();
			for (CouponUnitDTO couponUnitDTO : list) {
				List<Long> storeIds = couponUnitDTO.getStoreIds();
				if (EmptyUtil.isNotEmpty(storeIds) && storeIds.contains(dto.getStoreId())) {
					if (!hasAvailableSu(couponUnitDTO, companyDTO, dto.getStoreId(), dto.getPlatformId())) {
						couponUnitDTO.setStoreIds(new ArrayList<>());
					}
				}
			}
		}

	/*	//以旧换新类型的优惠券批次不在领券中心显示
		List<CouponUnitDTO> couponUnitDTOList = result.getList();
		List<CouponUnitDTO> couponUnitDTOList1 = new ArrayList<CouponUnitDTO>();
		if (EmptyUtil.isNotEmpty(couponUnitDTOList)){
			for (CouponUnitDTO couponUnitDTO : couponUnitDTOList) {
				if (EmptyUtil.isNotEmpty(couponUnitDTO.getGetType())&&couponUnitDTO.getGetType() !=3 ) {
					couponUnitDTOList1.add(couponUnitDTO);
				}
			}
		}
		result.setList(couponUnitDTOList1);*/
		return result;
	}

	/**
	 * 根据用户和su的id查询优惠卷列表
	 * @param dto
	 * @param page
	 * @return
	 */
	public List<CouponUnitDTO> findSUCouponBatchOfPage(CouponUnitDTO dto, Pagination page) {
		Long companyIdByType = null;
		CompanyDTO companyDTO = companyReadService.findCompanyById(dto.getCompanyId());
		if (companyDTO != null) 
			companyIdByType = NormalConstant.getCompanyIdByCompanyType(companyDTO.getCompanyType());
		
		// 通过su的id查询所有的商品组合的id
		List<Long> goodIdList = com.egeo.utils.StringUtils.stringsToLongs(standardUnitReadService.querySuCombinationBySuId(dto.getGoodsId()));
		//return couponUnitReadService.findSUCouponBatchOfPage(dto,goodIdList, companyIdByType, page);

        List<CouponUnitDTO> couponUnitDTOList = couponUnitReadService.findSUCouponBatchOfPage(dto, goodIdList, companyIdByType, page);

       //以旧换新类型的优惠券批次不在商品详情中显示
        List<CouponUnitDTO> couponUnitDTOList1 = new ArrayList<CouponUnitDTO>();
        if (EmptyUtil.isNotEmpty(couponUnitDTOList)){
            for (CouponUnitDTO couponUnitDTO : couponUnitDTOList) {
                if ((EmptyUtil.isNotEmpty(couponUnitDTO.getGetType())&&couponUnitDTO.getGetType() !=3)||EmptyUtil.isNotEmpty(couponUnitDTO.getId())) {
                    couponUnitDTOList1.add(couponUnitDTO);
                }
            }
        }
        return couponUnitDTOList1;
	}

    public void updateQrCodeByCouponUnitId(QrCodeDTO qrCodeDTO) {
		qrCodeWriteService.updateQrCodeWithTx(qrCodeDTO);
	}

	public QrCodeDTO findQrCodeByCouponUnitId(QrCodeDTO qrCodeDTO) {
		List<QrCodeDTO> qrCodeAll = qrCodeReadService.findQrCodeAll(qrCodeDTO);
		if(EmptyUtil.isEmpty(qrCodeAll)||qrCodeAll.size()>1){
			throw new BusinessException("qrcode数据有误,联系管理员");
		}
		return qrCodeAll.get(0);
	}

    public Long findCouponBatchCount(CouponBatchDTO couponBatchDTO) {
		return couponBatchReadService.findCouponBatchCount(couponBatchDTO);
    }

    public Long findCouponUnitAllCount(CouponUnitDTO couponUnitDTO) {
		return couponUnitReadService.findCouponUnitAllCount(couponUnitDTO);
    }

    public List<Long> checkIsShowExchange(Long batchId, Integer unitStatus) {
		return exchangeBatchReadService.checkIsShowExchange(batchId,unitStatus);
    }

	public List<ExchangeActivityDTO> findCouponUnitByIds(List<Long> exchangeList) {
		return exchangeActivityReadService.findExchangeActivityByIds(exchangeList);
	}

	public List<CouponBatchDTO> findCouponBatchByExchange(Long exchangeId) {
		return couponBatchReadService.findCouponBatchByExchange(exchangeId);
	}


    public CouponBatchDTO findCouponBatchById(CouponBatchDTO batchDTO) {
		return couponBatchReadService.findCouponBatchById(batchDTO);
	}


	
	/**
	 *  根据couponBatchId 查询su组 跳转时的pageId
	 * @param couponBatchId
	 * @return
	 */
	public List<LinkableButtonPageDTO> findLinkableButtonPageByCouponBatchId(Long couponBatchId){
		List<LinkableButtonPageDTO> linkableButtonPageList = null;
		CouponBatchDTO couponBatchDto = new CouponBatchDTO(couponBatchId);
		couponBatchDto = couponBatchReadService.findCouponBatchById(couponBatchDto);
		
		if(couponBatchDto != null && couponBatchDto.getLinkableId() != null) {
			linkableButtonPageList = linkableButtonPageReadService.findLinkableButtonPageAll(new LinkableButtonPageDTO(couponBatchDto.getLinkableId()));
		}
		
		return linkableButtonPageList;
	}
	
	/**
	 * 前端获取cmsPageId
	 * @param couponBatchId
	 * @param clientId
	 * @return
	 */
	public Long findCmsPageIdByCouponBatchId(Long couponBatchId,Long clientId) {
		
		Long cmsPageId = null;
		
		Integer clientType = clientId == 3 ? CmsConstant.CMS_CLINTE_TYPE_PC : CmsConstant.CMS_CLINTE_TYPE_MOBILE;
		
		List<LinkableButtonPageDTO> list = findLinkableButtonPageByCouponBatchId(couponBatchId);
		
		if(list != null && list.size() > 0) {
			for (LinkableButtonPageDTO linkableButtonPageDTO : list) {
				
				if(clientType == linkableButtonPageDTO.getClientType()) {
					cmsPageId = linkableButtonPageDTO.getCmsPageId();
				}
			}
		}			
		
		return cmsPageId;
	}

    public List<ExchangeBatchDTO> findExchangeBatch(ExchangeBatchDTO exchangeBatchDTO) {
		return exchangeBatchReadService.findExchangeBatchAll(exchangeBatchDTO);
	}

	public List<CouponUnitDTO> findCouponUnitAndBatchExchange(CouponUnitDTO dto) {
		Long companyIdByType = null;
		CompanyDTO companyDTO = companyReadService.findCompanyById(dto.getCompanyId());
		if (companyDTO != null) {
			companyIdByType = NormalConstant.getCompanyIdByCompanyType(companyDTO.getCompanyType());
		}
		List<CouponUnitDTO> list = couponUnitReadService.findCouponUnitAndBatchExchange(dto, companyIdByType);
		if (EmptyUtil.isNotEmpty(dto.getStoreId())) {
			for (CouponUnitDTO couponUnitDTO : list) {
				List<Long> storeIds = couponUnitDTO.getStoreIds();
				if (EmptyUtil.isNotEmpty(storeIds) && storeIds.contains(dto.getStoreId())) {
					if (!hasAvailableSu(couponUnitDTO, companyDTO, dto.getStoreId(), dto.getPlatformId())) {
						couponUnitDTO.setStoreIds(new ArrayList<>());
					}
				}
			}
		}
		return list;
	}

    public int updateCouponUnitStatusByParamsWithTx(Long couponBatchId, Long startNum, Long endNum, Date date) {
		return couponUnitWriteService.updateCouponUnitStatusByParamsWithTx(couponBatchId,startNum,endNum,date);
    }

	public Long findCouponUnitCountOfFreezeByParams(Long couponBatchId, Long startNum, Long endNum) {
		return couponUnitReadService.findCouponUnitCountOfFreezeByParams(couponBatchId,startNum,endNum);
	}
	
	public Integer countUnreadCouponUnit(Long userId, Integer couponType) {
		return couponUnitReadService.countUnreadCouponUnit(userId, couponType);
	}
	
	public void updateCouponUnitReadStatus(List<Long> ids) {
		couponUnitWriteService.updateCouponUnitReadStatus(ids);
	}
}
	