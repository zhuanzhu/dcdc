package com.egeo.components.user.facade;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.common.QrCodeConstant;
import com.egeo.components.product.client.StandardUnitClient;
import com.egeo.components.product.client.StoreClient;
import com.egeo.components.product.dto.StandardUnitDTO;
import com.egeo.components.product.dto.StoreDTO;
import com.egeo.components.promotion.client.CouponBatchClient;
import com.egeo.components.promotion.client.CouponClient;
import com.egeo.components.promotion.client.CouponGroupClient;
import com.egeo.components.promotion.client.CouponUnitClient;
import com.egeo.components.promotion.dto.CouponBatchDTO;
import com.egeo.components.promotion.dto.CouponDTO;
import com.egeo.components.promotion.dto.CouponGroupDTO;
import com.egeo.components.promotion.dto.CouponUnitDTO;
import com.egeo.components.user.dto.ChannelActivityDTO;
import com.egeo.components.user.dto.ChannelDTO;
import com.egeo.components.user.dto.ClientDTO;
import com.egeo.components.user.dto.PlatformDTO;
import com.egeo.components.user.dto.QrCodeDTO;
import com.egeo.components.user.service.read.ChannelActivityReadService;
import com.egeo.components.user.service.read.ChannelReadService;
import com.egeo.components.user.service.read.ClientReadService;
import com.egeo.components.user.service.read.PlatformReadService;
import com.egeo.components.user.service.read.QrCodeReadService;
import com.egeo.components.user.service.write.QrCodeWriteService;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;


@Component
public class QrCodeFacade {
	
	@Resource
	private QrCodeReadService qrCodeReadService;
	
	@Resource
	private QrCodeWriteService qrCodeWriteService;
	
	@Autowired
	private StoreClient storeReadService;
	
	@Resource
    private PlatformReadService platformReadService;
	
	@Resource
	private ClientReadService clientReadService;
	
	@Resource
	private ChannelReadService channelReadService;
	
	@Resource
	private ChannelActivityReadService channelActivityReadService;
	
	@Autowired
	private StandardUnitClient standardUnitReadService;
	
	@Autowired
	private CouponClient couponReadService;
	
	@Autowired
	private CouponGroupClient couponGroupReadService;
	@Autowired
	private CouponUnitClient couponUnitReadService;
	@Autowired
	private CouponBatchClient couponBatchReadService;
	
	
	public QrCodeDTO findQrCodeById(QrCodeDTO dto){
		
		return qrCodeReadService.findQrCodeById(dto);
	}

	public PageResult<QrCodeDTO> findQrCodeOfPage(QrCodeDTO dto,Pagination page){
		
		return qrCodeReadService.findQrCodeOfPage(dto, page);
		
	}

	public List<QrCodeDTO> findQrCodeAll(QrCodeDTO dto){
		
		return qrCodeReadService.findQrCodeAll(dto);
		
	}

	public Long insertQrCodeWithTx(QrCodeDTO dto){
		
		return qrCodeWriteService.insertQrCodeWithTx(dto);
	}

	public int updateQrCodeWithTx(QrCodeDTO dto){
		
		return qrCodeWriteService.updateQrCodeWithTx(dto);
	}

	public int deleteQrCodeWithTx(QrCodeDTO dto){
		
		return qrCodeWriteService.deleteQrCodeWithTx(dto);
		
	}

	public String findRdidByChannelIdCampaignId(
			Integer type,Integer typeId,Long storeId, Long channelId, String campaignCode,Long platformId) {
		// 根据门店id查询门店信息
		StoreDTO storeDTO = new StoreDTO();
		storeDTO.setId(storeId);
		StoreDTO storeDTO2 = storeReadService.findStoreById(storeDTO);
		return qrCodeReadService.findRdidByChannelIdCampaignId(type,typeId,storeId,storeDTO2.getName(),channelId, campaignCode,platformId);
	}

	public Long updateRdidByChannelIdCampaignId(
			Integer type,Integer typeId,Long storeId, Long channelId, String campaignCode,Long platformId) {
		return qrCodeWriteService.updateRdidByChannelIdCampaignId(channelId, campaignCode);
	}

	public Boolean qrCodeVerifyValid(QrCodeDTO dto) {
		// 根据平台id查询平台信息
		PlatformDTO platformDTO = platformReadService.find(dto.getPlatformId());
		if(EmptyUtil.isEmpty(platformDTO)){
			throw new BusinessException("该二维码已失效");
		}
		
		// 根据客户端id查询客户端信息
		ClientDTO clientDTO = new ClientDTO();
		clientDTO.setId(dto.getClientId());
		ClientDTO clientDTO2 = clientReadService.findClientById(clientDTO);
		if(EmptyUtil.isEmpty(clientDTO2)){
			throw new BusinessException("该二维码已失效");
		}
		
		// 根据渠道id查询渠道信息
		ChannelDTO channelDTO = new ChannelDTO();
		channelDTO.setId(dto.getChannelId());
		ChannelDTO channelDTO2 = channelReadService.findChannelById(channelDTO);
		if(EmptyUtil.isEmpty(channelDTO2)){
			throw new BusinessException("该二维码已失效");
		}
		
		// 根据活动短码查询活动信息
		ChannelActivityDTO channelActivityDTO2 = channelActivityReadService.findByShortCode(dto.getCampaignCode());
		if(EmptyUtil.isEmpty(channelActivityDTO2)){
			throw new BusinessException("该二维码已失效");
		}
		
		// 根据门店id查询门店信息
		StoreDTO storeDTO = new StoreDTO();
		storeDTO.setId(dto.getBranchId());
		StoreDTO storeDTO2 = storeReadService.findStoreById(storeDTO);
		if(EmptyUtil.isEmpty(storeDTO2)){
			throw new BusinessException("该二维码已失效");
		}
		
		// 查询rdid是否失效
		QrCodeDTO qrCodeDTO = new QrCodeDTO();
		qrCodeDTO.setChannelId(dto.getChannelId());
		qrCodeDTO.setCampaignId(channelActivityDTO2.getId());
		qrCodeDTO.setRdid(dto.getRdid());
		List<QrCodeDTO> qrCodeDTOList = qrCodeReadService.findQrCodeAll(qrCodeDTO);
		if(EmptyUtil.isEmpty(qrCodeDTOList)){
			throw new BusinessException("该二维码已失效");
		}
		
		/**
		 * su : 商品
		 * coupon : 优惠券
		 * coupon_group : 优惠券组
		 * main_stor : 总店
		 * branch : 分店
		 * main_page : 平台主页
		 * membership : 会籍
		 */
		// 根据类型判断二维码是否失效
		isQrCodeFailure(dto);
		
		return true;
	}
	/**
	 * 根据类型判断二维码是否失效
	 * @param dto
	 */
	private void isQrCodeFailure(QrCodeDTO dto) {
		if(dto.getType().equals(QrCodeConstant.SU)){
			//  根据su商品id查询商品信息
			StandardUnitDTO standardUnitDTO = new StandardUnitDTO();
			standardUnitDTO.setId(dto.getTypeId());
			StandardUnitDTO standardUnitDTO2 = standardUnitReadService.findStandardUnitById(standardUnitDTO);
			if(EmptyUtil.isEmpty(standardUnitDTO2)){
				throw new BusinessException("该二维码已失效");
			}
		}
		
		if(dto.getType().equals(QrCodeConstant.COUPON)){
			//  根据优惠券id查询优惠券信息
			CouponBatchDTO couponBatchDTO = new CouponBatchDTO();
			couponBatchDTO.setId(dto.getTypeId());
			CouponBatchDTO couponBatchById = couponBatchReadService.findCouponBatchById(couponBatchDTO);
			if(EmptyUtil.isEmpty(couponBatchById)){
				throw new BusinessException("该二维码已失效");
			}
			CouponDTO couponDTO = new CouponDTO();
			couponDTO.setId(couponBatchById.getCouponRelId());
			CouponDTO couponById = couponReadService.findCouponById(couponDTO);
			if(EmptyUtil.isEmpty(couponById)){
				throw new BusinessException("该二维码已失效");
			}
		}
		
		if(dto.getType().equals(QrCodeConstant.COUPON_GROUP)){
			// 根据优惠券组查询优惠券组信息
			CouponBatchDTO batchDTO = new CouponBatchDTO();
			batchDTO.setId(dto.getTypeId());
			batchDTO=couponBatchReadService.findCouponBatchById(batchDTO);
			if(EmptyUtil.isEmpty(batchDTO)){
				throw new BusinessException("该二维码已失效");
			}
			CouponGroupDTO couponGroupDTO = new CouponGroupDTO();
			couponGroupDTO.setId(batchDTO.getCouponRelId());
			CouponGroupDTO couponGroupDTO2 = couponGroupReadService.findCouponGroupById(couponGroupDTO);
			if(EmptyUtil.isEmpty(couponGroupDTO2)){
				throw new BusinessException("该二维码已失效");
			}
		}
		
		if(dto.getType().equals(QrCodeConstant.MAIN_STOR) || dto.getType().equals(QrCodeConstant.BRANCH)){
			// 根据门店id查询门店信息
			StoreDTO storeDTO = new StoreDTO();
			storeDTO.setId(dto.getTypeId());
			StoreDTO storeDTO2 = storeReadService.findStoreById(storeDTO);
			if(EmptyUtil.isEmpty(storeDTO2)){
				throw new BusinessException("该二维码已失效");
			}
		}
		if(dto.getType().equals(QrCodeConstant.COUPON_UNIT)){
			CouponUnitDTO couponUnitDTO = new CouponUnitDTO();
			couponUnitDTO.setId(dto.getTypeId());
			CouponUnitDTO couponUnitAll = couponUnitReadService.findCouponUnitById(couponUnitDTO);
			if(EmptyUtil.isEmpty(couponUnitAll)){
				throw new BusinessException("该二维码已失效");

			}
		}
		
	}

}
	