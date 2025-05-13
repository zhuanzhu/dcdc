package com.egeo.components.order.business.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.egeo.components.order.business.PushOrderManage;
import com.egeo.components.order.business.SoDeliveryManage;
import com.egeo.components.order.common.ExcelCheckHelper;
import com.egeo.components.order.common.ExcelTemplateEnum;
import com.egeo.components.order.enums.ThirdConst;
import com.egeo.components.utils.CakeUtil;
import com.egeo.components.utils.JdUtils2;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.egeo.common.LogConstant;
import com.egeo.common.LogTypeConstant;
import com.egeo.components.config.dto.HeadImportRecordsDTO;
import com.egeo.components.order.business.SoPackageManage;
import com.egeo.components.order.converter.DeliveryImportExcelConverter;
import com.egeo.components.order.converter.SoPackageConverter;
import com.egeo.components.order.dto.DeliveryCompanyDTO;
import com.egeo.components.order.dto.SoChildDTO;
import com.egeo.components.order.dto.SoDTO;
import com.egeo.components.order.dto.SoItemDTO;
import com.egeo.components.order.dto.SoPackageBoxDTO;
import com.egeo.components.order.dto.SoPackageDTO;
import com.egeo.components.order.facade.DeliveryCompanyFacade;
import com.egeo.components.order.facade.SoFacade;
import com.egeo.components.order.facade.SoItemFacade;
import com.egeo.components.order.facade.SoPackageFacade;
import com.egeo.components.order.facade.UserFacade;
import com.egeo.components.order.vo.DeliveryImportExcelVO;
import com.egeo.components.order.vo.OrderConfirmGoodsVO;
import com.egeo.components.order.vo.PackageVO;
import com.egeo.components.order.vo.SoPackageVO;
import com.egeo.components.order.vo.SoPackageView;
import com.egeo.components.order.vo.Steps;
import com.egeo.components.order.vo.jd.JdOrderTrack;
import com.egeo.components.order.vo.jd.JdOrderTrackBody;
import com.egeo.components.order.vo.jd.JdResponse;
import com.egeo.components.order.vo.jd.JdWaybillCode;
import com.egeo.components.pay.enums.OrderConstant;
import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.core.Constant.BusinessExceptionConstant;
import com.egeo.log.EgeoBusinessLogCommon;
import com.egeo.log.EgeoLog;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.ActiveMQUtils;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.Upload;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.delivery.JdUtils;
import com.egeo.utils.excel2.ExcelHeadChecker;
import com.egeo.utils.excel2.ExcelTmplConstant;
import com.egeo.utils.excel2.PropblemReportRowVO;
import com.egeo.utils.log.XLogger;
import com.egeo.utils.str.StringUtils;
import com.egeo.web.JsonResult;
import org.springframework.util.CollectionUtils;

@Service("soPackage")
public class SoPackageManageImpl implements SoPackageManage {

	private static final XLogger logger = XLogger.getLogger(SoPackageManageImpl.class);
	@Resource(name = "soPackageFacade")
	private SoPackageFacade soPackageFacade;

	@Resource(name = "deliveryCompanyFacade")
	private DeliveryCompanyFacade deliveryCompanyFacade;

	@Resource(name = "soFacade")
	private SoFacade soFacade;

	@Resource(name = "soItemFacade")
	private SoItemFacade soItemFacade;

	@Resource(name = "userFacade")
	private UserFacade userFacade;
	@Autowired
	private Upload uploadService;
	@Autowired
	private JdUtils jdUtils;
    @Autowired
    private JedisUtil jedisUtil;
	@Autowired
    private CakeUtil cakeUtil;

	@Resource(name="soDelivery")
	private SoDeliveryManage soDeliveryManage;

	@Autowired
	private JdUtils2 jdUtils2;

	@Override
	public SoPackageDTO findSoPackageById(SoPackageDTO dto) {
		return soPackageFacade.findSoPackageById(dto);
	}

	@Override
	public PageResult<SoPackageDTO> findSoPackageOfPage(SoPackageDTO dto, Pagination page) {
		return soPackageFacade.findSoPackageOfPage(dto, page);
	}

	@Override
	public List<SoPackageDTO> findSoPackageAll(SoPackageDTO dto) {
		// 根据订单id查询订单编号
		SoDTO soDTO = soPackageFacade.querySoById(dto.getId());
		dto.setOrderCode(soDTO.getOrderCode());
		return soPackageFacade.findSoPackageAll(dto);
	}

	@Override
	public Long insertSoPackageWithTx(SoPackageDTO dto, Long soId) {
		// 根据订单id查询订单信息
		SoDTO soDTO = soPackageFacade.querySoById(soId);
		dto.setOrderCode(soDTO.getOrderCode());
		return soPackageFacade.insertSoPackageWithTx(dto);
	}

	@Override
	public int updateSoPackageWithTx(SoPackageDTO dto) {
		return soPackageFacade.updateSoPackageWithTx(dto);
	}

	@Override
	public int deleteSoPackageWithTx(SoPackageDTO dto) {
		return soPackageFacade.deleteSoPackageWithTx(dto);
	}

	@Override
	public List<SoPackageVO> SoPackageAllByOrderCode(SoPackageDTO dto) {
		// 根据订单id查询订单编号
		SoDTO soDTO = soPackageFacade.querySoById(dto.getId());
		dto.setOrderCode(soDTO.getOrderCode());
		List<SoPackageVO> PackageList = new ArrayList<SoPackageVO>();
		List<SoPackageDTO> list = soPackageFacade.findSoPackageAll(dto);
		for (SoPackageDTO soPackageDTO : list) {
			SoPackageVO soPackageVO = new SoPackageVO();
			soPackageVO.setId(soPackageDTO.getId());
			// 根据物流公司id查询物流公司信息
			DeliveryCompanyDTO deliveryCompanyDTO2 = soPackageFacade.findDeliveryCompanyById(soPackageDTO.getDeliveryCompanyId());
			if (deliveryCompanyDTO2 != null) {
				// soPackageVO.setShipCompanyCode(deliveryCompanyDTO2.getCoding());
			}

			soPackageVO.setDeliveryExpressNbr(soPackageDTO.getDeliveryCode());
			soPackageVO.setPlatformId(soPackageDTO.getPlatformId());
			// 根据包裹编号查询包裹商品
			SoItemDTO soItemDTO = new SoItemDTO();
			// soItemDTO.setPackId(soPackageDTO.getId());
			List<SoItemDTO> SoItemList = soPackageFacade.findSoItemAll(soItemDTO);
			List<String> packageItem = new ArrayList<String>();
			for (SoItemDTO soItem : SoItemList) {
				// packageItem.add(soItem.getProductCname());
			}
			// soPackageVO.setPackageItem(packageItem);
			PackageList.add(soPackageVO);
		}
		return PackageList;
	}

	@Override
	public String updateSoPackageByIdWithTxAll(List<SoPackageVO> lists, Long soId, Long platformId) {
		// 根据订单id查询订单编号
		SoDTO soDTO = soPackageFacade.querySoById(soId);
		for (SoPackageVO soPackageVO : lists) {
			// 根据包裹id查询包裹项
			SoItemDTO soItemDTO = new SoItemDTO();
			// soItemDTO.setPackId(soPackageVO.getId());
			List<SoItemDTO> soItemList = soPackageFacade.findSoItemAll(soItemDTO);
			if (soItemList.size() > 0) {
				soPackageVO.setOrderCode(soDTO.getOrderCode());
				soPackageFacade.updateSoPackageWithTx(SoPackageConverter.toDTO(soPackageVO));
			} else {
				soPackageFacade.deleteSoPackageWithTx(SoPackageConverter.toDTO(soPackageVO));
			}
		}
		// 根据订单编号查询包裹信息
		SoPackageDTO soPackageDTO = new SoPackageDTO();
		soPackageDTO.setOrderCode(soDTO.getOrderCode());
		List<SoPackageDTO> findSoPackageAll = soPackageFacade.findSoPackageAll(soPackageDTO);
		int shipmentsSum = 0;
		for (SoPackageDTO soPackageDTO2 : findSoPackageAll) {
			if (EmptyUtil.isNotEmpty(soPackageDTO2.getDeliveryCode())) {
				shipmentsSum = shipmentsSum + 1;
			}
		}
		if (findSoPackageAll.size() > shipmentsSum) {
			soFacade.changeOrderStatus(soDTO.getOrderCode(),
					OrderConstant.ORDER_STATUS_DELIVERED_PART_GOODS.getStatus(), null, null, null);
		} else if (findSoPackageAll.size() == shipmentsSum) {
			soFacade.changeOrderStatus(soDTO.getOrderCode(), OrderConstant.ORDER_STATUS_DELIVERED.getStatus(), null,
					null, null);
		} else if (0 == shipmentsSum) {
			soFacade.changeOrderStatus(soDTO.getOrderCode(), OrderConstant.ORDER_STATUS_PAYED.getStatus(), null, null,
					null);
		}
		return "批量修改包裹信息成功";
	}

	@Override
	public JsonResult<Map<String,Object>> packageBySoId(String orderCode) {
		if (EmptyUtil.isBlank(orderCode)) {
			return JsonResult.fail("请选择订单");
		}
		SoDTO so=soFacade.querySoByOrderCode(orderCode);
		if(so==null) {
			return JsonResult.fail("订单不存在");
		}
		//查询所有子订单信息
		List<SoChildDTO> scList=soFacade.querySoChildListBySoId(so.getId());
		List<PackageVO> voList=new ArrayList<>();
		for(SoChildDTO sc:scList) {
			Long scId=sc.getId();



			if(sc.getSource()!=null && sc.getSource().intValue()==3) {
				Long jdOrderId = sc.getThirdpartySoChildId();
                //String token = jdUtils.getAccessToken(jedisUtil);
				String token = jdUtils2.getAccessToken(jedisUtil);
                logger.info("查询京东物流接口");
                logger.info("订单号："+sc.getOrderCode());

    			List<SoItemDTO> items=soItemFacade.querySoItemsBySoChildId(scId);
    			List<OrderConfirmGoodsVO> goodsList=new ArrayList<>();
    			for(SoItemDTO item:items) {
    				OrderConfirmGoodsVO gvo=new OrderConfirmGoodsVO();
    				gvo.setNum(item.getPuCount());
    				gvo.setPrice(item.getPrice().doubleValue());
    				gvo.setPuId(item.getPuId());
    				gvo.setPuName(item.getPuName());
    				gvo.setPuImg(item.getPuPicUrl());
    				gvo.setChannelProductId(item.getExternalProductId());
    				gvo.setSource(item.getSource());
    				gvo.setIsOwnMerchant(sc.getPerformingParty().equals(1L) ? 1 : 0);
    				goodsList.add(gvo);
    			}
				JSONObject jdOrderDetail = jdUtils.getJdOrderDetail(token, jdOrderId.longValue()+"");
                if(EmptyUtil.isNotEmpty(jdOrderDetail)){
					logger.info("京东订单信息->"+ JSON.toJSONString(jdOrderDetail));
					String orderTrack = jdUtils.getJDOrderTrack(token, jdOrderId.longValue()+"");
					 JdResponse jdResponse1 = JSON.parseObject(orderTrack, JdResponse.class);
	                 if(jdResponse1.isSuccess()&&jdResponse1.getResultCode().equals("0000")){
	                     String json = jdResponse1.getResult();
	                     logger.info("查询京东物流接口成功："+json);
	                     JdOrderTrack jdOrderTrackInfo = JSON.parseObject(json, JdOrderTrack.class);
	                     if(jdOrderTrackInfo.hasInfo()){
	                    	for(JdWaybillCode waybillCode : jdOrderTrackInfo.getWaybillCode()) {
	                    		if(waybillCode.getDeliveryOrderId()==null || waybillCode.getDeliveryOrderId().length()<2) {
	                    			continue;
	                    		}
	                    		PackageVO vo=new PackageVO();
	                			vo.setGoodsList(goodsList);
	                			vo.setOrderCode(orderCode);
	                			voList.add(vo);
	                			List<JdOrderTrackBody> trackBodys = null;
	                			if(jdOrderTrackInfo.getWaybillCode().size()==1) {
	                				trackBodys = jdOrderTrackInfo.getOrderTrack();
	                			}else {
	                				String orderTrackItem = jdUtils.getJDOrderTrack(token, waybillCode.getOrderId());
	           					 	JdResponse jdResponseItem = JSON.parseObject(orderTrackItem, JdResponse.class);
		           					 if(jdResponseItem.isSuccess()&&jdResponseItem.getResultCode().equals("0000")){
		           						String jsonItem = jdResponseItem.getResult();
		           						logger.info("查询京东物流接口(子订单)成功："+jsonItem);
		           						JdOrderTrack jdOrderTrackItemInfo = JSON.parseObject(jsonItem, JdOrderTrack.class);
		           						if(!jdOrderTrackItemInfo.hasInfo()){
		           							continue;
		           						}
		           						trackBodys = jdOrderTrackItemInfo.getOrderTrack();
		           					 }
	                			}

		         				vo.setDeliveryCompanyName(waybillCode.getCarrier());
		         				//vo.setShipCompanyCode(dc.getCoding());
		         				vo.setWaybillNum(waybillCode.getDeliveryOrderId());
		         				//vo.setDeliveryStatus(sc.getDeliveryStatus());
		         				if(trackBodys!=null && trackBodys.size()>0) {
		         					List<Steps> stepsList = new ArrayList<Steps>();
		         					vo.setStepsList(stepsList);
		         					for(JdOrderTrackBody one : trackBodys) {
		         						Steps oneStep = new Steps();
		         						oneStep.setAcceptTime(one.getMsgTime());
		         						oneStep.setRemark(one.getContent());
		         						stepsList.add(oneStep);
		         					}
		         				}
	                    	}
	                     }
	                 }
                }

			}else {
				PackageVO vo=new PackageVO();
				List<SoPackageDTO> ps=soPackageFacade.queryPackageBySoChildId(scId);
				if(ps!=null && ps.size()>0) {
					SoPackageDTO p = ps.get(0);
				Long deliveryCompanyId=p.getDeliveryCompanyId();
				DeliveryCompanyDTO dc=deliveryCompanyFacade.findDeliveryCompanyById(deliveryCompanyId);
				if(dc!=null) {
					vo.setDeliveryCompanyName(dc.getName());
					vo.setShipCompanyCode(dc.getCoding());
				}else {
					if(sc.getPerformingParty().equals(6L) || sc.getPerformingParty().equals(ThirdConst.Merchant.CAKE) || sc.getPerformingParty().equals(ThirdConst.Merchant.WORLD)){
						vo.setDeliveryCompanyName(p.getDeliveryCompanyName());
					}

				}
				vo.setWaybillNum(p.getDeliveryCode());
				}
				vo.setDeliveryStatus(sc.getDeliveryStatus());

				List<SoItemDTO> items=soItemFacade.querySoItemsBySoChildId(scId);
				List<OrderConfirmGoodsVO> goodsList=new ArrayList<>();
				for(SoItemDTO item:items) {
					OrderConfirmGoodsVO gvo=new OrderConfirmGoodsVO();
					gvo.setNum(item.getPuCount());
					gvo.setPrice(item.getPrice().doubleValue());
					gvo.setPuId(item.getPuId());
					gvo.setPuName(item.getPuName());
					gvo.setPuImg(item.getPuPicUrl());
					gvo.setIsOwnMerchant(sc.getPerformingParty().equals(1L) ? 1 : 0);
					gvo.setChannelProductId(item.getExternalProductId());
					gvo.setSource(item.getSource());
					goodsList.add(gvo);
				}
				vo.setGoodsList(goodsList);
				vo.setOrderCode(orderCode);
				voList.add(vo);
			}
		}
		return JsonResult.success("packageList", voList);
	}



	@Override
	public JsonResult<Map<String,Object>> packageBySoChildCode(String soChildCode) {
		if (EmptyUtil.isBlank(soChildCode)) {
			return JsonResult.fail("请选择订单");
		}
		SoChildDTO sc=soFacade.querySoChildByChildCode(soChildCode);
		if(sc==null) {
			return JsonResult.fail("子订单不存在");
		}
		//查询所有子订单信息
		List<PackageVO> voList=new ArrayList<>();
		List<SoItemDTO> items=soItemFacade.querySoItemsBySoChildId(sc.getId());
		Long scId=sc.getId();
		if(sc.getSource()!=null && sc.getSource().intValue()==3) {
			List<SoPackageDTO> ps=soPackageFacade.queryPackageBySoChildId(scId);
			SoPackageDTO p = null;
			if(ps!=null && ps.size()>0) {
				 p= ps.get(0);
			}
			Long jdOrderId = sc.getThirdpartySoChildId();
            //String token = jdUtils.getAccessToken(jedisUtil);
			String token = jdUtils2.getAccessToken(jedisUtil);
            logger.info("查询京东物流接口");
            logger.info("订单号："+sc.getOrderCode());


			List<OrderConfirmGoodsVO> goodsList=new ArrayList<>();
			for(SoItemDTO item:items) {
				OrderConfirmGoodsVO gvo=new OrderConfirmGoodsVO();
				gvo.setNum(item.getPuCount());
				gvo.setPrice(item.getPrice().doubleValue());
				gvo.setPuId(item.getPuId());
				gvo.setPuName(item.getPuName());
				gvo.setPuImg(item.getPuPicUrl());
				gvo.setIsOwnMerchant(sc.getPerformingParty().equals(1L) ? 1 : 0);
				gvo.setChannelProductId(item.getExternalProductId());
				gvo.setSource(item.getSource());
				goodsList.add(gvo);
			}
			JSONObject jdOrderDetail = jdUtils.getJdOrderDetail(token, jdOrderId.longValue()+"");
            if(EmptyUtil.isNotEmpty(jdOrderDetail)){
				logger.info("京东订单信息->"+ JSON.toJSONString(jdOrderDetail));
				String orderTrack = jdUtils.getJDOrderTrack(token, jdOrderId.longValue()+"");
				 JdResponse jdResponse1 = JSON.parseObject(orderTrack, JdResponse.class);
                 if(jdResponse1.isSuccess()&&jdResponse1.getResultCode().equals("0000")){
                     String json = jdResponse1.getResult();
                     logger.info("查询京东物流接口成功："+json);
                     JdOrderTrack jdOrderTrackInfo = JSON.parseObject(json, JdOrderTrack.class);
                     if(jdOrderTrackInfo.hasInfo()){
                    	for(JdWaybillCode waybillCode : jdOrderTrackInfo.getWaybillCode()) {
                    		if(waybillCode.getDeliveryOrderId()==null || waybillCode.getDeliveryOrderId().length()<2) {
                    			continue;
                    		}
                    		PackageVO vo=new PackageVO();
                    		if(p !=null){
								vo.setGoodReceiverAddress(p.getGoodReceiverAddress());
								vo.setGoodReceiverMobile(p.getGoodReceiverMobile());
								vo.setProCityArea(p.getProCityArea());
								vo.setGoodReceiverName(p.getGoodReceiverName());
								vo.setReceiverAddressId(p.getReceiverAddressId());
							}
                			vo.setGoodsList(goodsList);
                			vo.setOrderCode(soChildCode);
                			voList.add(vo);
                			List<JdOrderTrackBody> trackBodys = null;
                			if(jdOrderTrackInfo.getWaybillCode().size()==1) {
                				trackBodys = jdOrderTrackInfo.getOrderTrack();
                			}else {
                				String orderTrackItem = jdUtils.getJDOrderTrack(token, waybillCode.getOrderId());
           					 	JdResponse jdResponseItem = JSON.parseObject(orderTrackItem, JdResponse.class);
	           					 if(jdResponseItem.isSuccess()&&jdResponseItem.getResultCode().equals("0000")){
	           						String jsonItem = jdResponseItem.getResult();
	           						logger.info("查询京东物流接口(子订单)成功："+jsonItem);
	           						JdOrderTrack jdOrderTrackItemInfo = JSON.parseObject(jsonItem, JdOrderTrack.class);
	           						if(!jdOrderTrackItemInfo.hasInfo()){
	           							continue;
	           						}
	           						trackBodys = jdOrderTrackItemInfo.getOrderTrack();
	           					 }
                			}

	         				vo.setDeliveryCompanyName(waybillCode.getCarrier());
	         				//vo.setShipCompanyCode(dc.getCoding());
	         				vo.setWaybillNum(waybillCode.getDeliveryOrderId());
	         				//vo.setDeliveryStatus(sc.getDeliveryStatus());
	         				if(trackBodys!=null && trackBodys.size()>0) {
	         					List<Steps> stepsList = new ArrayList<Steps>();
	         					vo.setStepsList(stepsList);
	         					for(JdOrderTrackBody one : trackBodys) {
	         						Steps oneStep = new Steps();
	         						oneStep.setAcceptTime(one.getMsgTime());
	         						oneStep.setRemark(one.getContent());
	         						stepsList.add(oneStep);
	         					}
	         				}
                    	}
                     }
                 }
            }

		}else {
			PackageVO vo = new PackageVO();
			List<SoPackageDTO> ps = soPackageFacade.queryPackageBySoChildId(scId);
			if (ps != null && ps.size() > 0) {
				SoPackageDTO p = ps.get(0);
				vo.setGoodReceiverAddress(p.getGoodReceiverAddress());
				vo.setGoodReceiverMobile(p.getGoodReceiverMobile());
				vo.setProCityArea(p.getProCityArea());
				vo.setGoodReceiverName(p.getGoodReceiverName());
				vo.setReceiverAddressId(p.getReceiverAddressId());
				Long deliveryCompanyId = p.getDeliveryCompanyId();
				DeliveryCompanyDTO dc = deliveryCompanyFacade.findDeliveryCompanyById(deliveryCompanyId);
				if (dc != null) {
					vo.setDeliveryCompanyName(dc.getName());
					vo.setShipCompanyCode(dc.getCoding());
				} else {
					if(sc.getPerformingParty().equals(6L) || sc.getPerformingParty().equals(ThirdConst.Merchant.CAKE) || sc.getPerformingParty().equals(ThirdConst.Merchant.WORLD)){
						vo.setDeliveryCompanyName(p.getDeliveryCompanyName());
					}
				}
				vo.setWaybillNum(p.getDeliveryCode());
				if (EmptyUtil.isNotEmpty(p.getDeliveryMessage())){
					List<Steps> steps=JSONObject.parseObject(p.getDeliveryMessage(),List.class);
					if (EmptyUtil.isNotEmpty(steps)){
						vo.setStepsList(steps);
					}
				}else if(dc !=null && EmptyUtil.isNotEmpty(p.getDeliveryCode())){
					List<Steps> steps = soDeliveryManage.openDeliveryTrace(p.getDeliveryCode(), dc.getCoding());
					if (EmptyUtil.isNotEmpty(steps)){
						vo.setStepsList(steps);
					}
				}
			}
			vo.setDeliveryStatus(sc.getDeliveryStatus());

			//List<SoItemDTO> items=soItemFacade.querySoItemsBySoChildId(scId);
			List<OrderConfirmGoodsVO> goodsList = new ArrayList<>();
			for (SoItemDTO item : items) {
				OrderConfirmGoodsVO gvo = new OrderConfirmGoodsVO();
				gvo.setNum(item.getPuCount());
				gvo.setPrice(item.getPrice().doubleValue());
				gvo.setPuId(item.getPuId());
				gvo.setPuName(item.getPuName());
				gvo.setPuImg(item.getPuPicUrl());
				gvo.setIsOwnMerchant(sc.getPerformingParty().equals(1L) ? 1 : 0);
				gvo.setChannelProductId(item.getExternalProductId());
				gvo.setSource(item.getSource());
				goodsList.add(gvo);
			}
			vo.setGoodsList(goodsList);
			vo.setOrderCode(soChildCode);
			voList.add(vo);
		}

		return JsonResult.success("packageList", voList);
	}




	@Override
	public JsonResult<Map<String, Object>> deliveryImport(Long operatorId,
			Long platformId,
			List<Map<String, Object>> valueList,HttpServletRequest req) {

		if (valueList.size() > 1002)
			return JsonResult.fail("单次导入数据不能超过1000条");

		// *************************************** 检查头文件及内容
		String err = ExcelHeadChecker.chechHeader(valueList, ExcelTmplConstant.orderDelivery.getTmplType(), true);
		if (EmptyUtil.isNotBlank(err))
			return JsonResult.fail(err);

		// 查询公司
		String companyName = valueList.get(0).get("CELL2").toString();
		CompanyDTO company = userFacade.queryCompanyByName(companyName);
		if (company == null) {
			return JsonResult.fail("公司不存在");
		}

		// 模板类型
		String tmplName = ExcelTmplConstant.orderDelivery.getTmplName();
		if (!StringUtils.equals(tmplName, valueList.get(0).get("CELL4").toString())) {
			return JsonResult.fail("导入的文件类型错误，请检查后重新选择文件导入");
		}

		// 创建时间
		String createTime = valueList.get(0).get("CELL6").toString();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = null;
		try {
			date = sdf.parse(createTime);
		} catch (ParseException e) {
			return JsonResult.fail("创建时间格式错误");
		}

		// 序列号校验
		String sn = valueList.get(0).get("CELL8").toString();
		// 创建导入正式记录的信息
		HeadImportRecordsDTO headImportRecordsDTO = new HeadImportRecordsDTO();

		headImportRecordsDTO.setGeneratedTime(date);
		headImportRecordsDTO.setTemplateType(tmplName);
		headImportRecordsDTO.setFileSequenceNumber(sn);

		List<HeadImportRecordsDTO> importRecordList = soPackageFacade.findHeadImportRecordsAll(headImportRecordsDTO);
		if (importRecordList.size() > 0)
			return JsonResult.fail("文件序列已存在,请确认是否已经导入");

		// ************************************

		// 列表文件校验
		// 封装错误的集合
		List<PropblemReportRowVO<DeliveryImportExcelVO>> problemRep = new ArrayList<>();
		// 查重集合
		Set<DeliveryImportExcelVO> repeatSet = new HashSet<>();
		List<DeliveryImportExcelVO> voList=new ArrayList<>();
		//Set<String> deliveryCodeSet=new HashSet<>();
		List<Long> soChildIdList = new ArrayList<Long>();
		for (int i = 2; i < valueList.size(); i++) {
			// 内部不重复,空值校验
			DeliveryImportExcelVO vo = row2bean(valueList.get(i), problemRep, i);
			voList.add(vo);
			soChildIdList.add(vo.getSoChildId());
			SoChildDTO soChild = soFacade.findSoChildById(vo.getSoChildId());
			if (Long.valueOf(3L).equals(soChild.getPerformingParty())) {
				problemRep.add(new PropblemReportRowVO<>("网店管家订单不支持发货导入",i,vo));
			}
			if (Long.valueOf(6L).equals(soChild.getPerformingParty())) {
				problemRep.add(new PropblemReportRowVO<>("京东订单不支持发货导入",i,vo));
			}
			if(!repeatSet.add(vo)) {
				problemRep.add(new PropblemReportRowVO<>("该行与前面的记录有重复",i,vo));
			}
//			if(!deliveryCodeSet.add(vo.getDeliveryCode())) {
//				problemRep.add(new PropblemReportRowVO<>("运单编号需要唯一",i,vo));
//			}
		}

		// 校验完成,封装数据
		if (problemRep.size() == 0) {
			writeImportHistory(req,soChildIdList,voList,operatorId,platformId);
			return JsonResult.success();
		} else {
			//有错误,将excel上传至fastDFS
			//返回url
			String repUrl = genAndUploadRepDelievery(problemRep);
			return JsonResult.fail(repUrl, BusinessExceptionConstant.IMPORTFILEERROR);
		}
	}

	@Override
	public JsonResult<Map<String, Object>> deliveryImportChild(Long operatorId,
			Long platformId,
			List<Map<String, Object>> valueList,HttpServletRequest req) {

		if (valueList.size() > 1002)
			return JsonResult.fail("单次导入数据不能超过1000条");

		// *************************************** 检查头文件及内容
		String err = ExcelHeadChecker.chechHeader(valueList, ExcelTmplConstant.orderDelivery.getTmplType(), true);
		if (EmptyUtil.isNotBlank(err))
			return JsonResult.fail(err);

		// 查询公司
		String companyName = valueList.get(0).get("CELL2").toString();
		CompanyDTO company = userFacade.queryCompanyByName(companyName);
		if (company == null) {
			return JsonResult.fail("公司不存在");
		}

		// 模板类型
		String tmplName = ExcelTmplConstant.orderDelivery.getTmplName();
		if (!StringUtils.equals(tmplName, valueList.get(0).get("CELL4").toString())) {
			return JsonResult.fail("导入的文件类型错误，请检查后重新选择文件导入");
		}

		// 创建时间
		String createTime = valueList.get(0).get("CELL6").toString();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = null;
		try {
			date = sdf.parse(createTime);
		} catch (ParseException e) {
			return JsonResult.fail("创建时间格式错误");
		}

		// 序列号校验
		String sn = valueList.get(0).get("CELL8").toString();
		// 创建导入正式记录的信息
		HeadImportRecordsDTO headImportRecordsDTO = new HeadImportRecordsDTO();

		headImportRecordsDTO.setGeneratedTime(date);
		headImportRecordsDTO.setTemplateType(tmplName);
		headImportRecordsDTO.setFileSequenceNumber(sn);

		List<HeadImportRecordsDTO> importRecordList = soPackageFacade.findHeadImportRecordsAll(headImportRecordsDTO);
		if (importRecordList.size() > 0)
			return JsonResult.fail("文件序列已存在,请确认是否已经导入");

		// ************************************

		// 列表文件校验
		// 封装错误的集合
		List<PropblemReportRowVO<DeliveryImportExcelVO>> problemRep = new ArrayList<>();
		// 查重集合
		Set<DeliveryImportExcelVO> repeatSet = new HashSet<>();
		List<DeliveryImportExcelVO> voList=new ArrayList<>();
		//Set<String> deliveryCodeSet=new HashSet<>();
		List<Long> soChildIdList = new ArrayList<Long>();
		for (int i = 2; i < valueList.size(); i++) {
			// 内部不重复,空值校验
			DeliveryImportExcelVO vo = row2bean(valueList.get(i), problemRep, i);
			voList.add(vo);
			soChildIdList.add(vo.getSoChildId());
			SoChildDTO soChild = soFacade.findSoChildById(vo.getSoChildId());
			if (Long.valueOf(3L).equals(soChild.getPerformingParty())) {
				problemRep.add(new PropblemReportRowVO<>("网店管家订单不支持发货导入",i,vo));
			}
			if (Long.valueOf(6L).equals(soChild.getPerformingParty())) {
				problemRep.add(new PropblemReportRowVO<>("京东订单不支持发货导入",i,vo));
			}
			if(!repeatSet.add(vo)) {
				problemRep.add(new PropblemReportRowVO<>("该行与前面的记录有重复",i,vo));
			}
//			if(!deliveryCodeSet.add(vo.getDeliveryCode())) {
//				problemRep.add(new PropblemReportRowVO<>("运单编号需要唯一",i,vo));
//			}
		}

		// 校验完成,封装数据
		if (problemRep.size() == 0) {
			writeImportHistory(req,soChildIdList,voList,operatorId,platformId);
			return JsonResult.success();
		} else {
			//有错误,将excel上传至fastDFS
			//返回url
			String repUrl = genAndUploadRepDelievery(problemRep);
			return JsonResult.fail(repUrl, BusinessExceptionConstant.IMPORTFILEERROR);
		}
	}
	public void writeImportHistory(HttpServletRequest req, List<Long> soChildIdList, List<DeliveryImportExcelVO> voList, Long operatorId, Long platformId){
		// 发货导入日志: 1.更新子订单状态   2.更新物流信息
		// 1)查询变更前数据
		Map<String,Object> oldObjects = soPackageFacade.getSoChildListByPackageIds(soChildIdList);
		List<SoPackageDTO> soPackageDTOList = (List<SoPackageDTO>) oldObjects.get("soPackageDTOList");
		List<SoChildDTO> soChildDTOList = (List<SoChildDTO>) oldObjects.get("soChildDTOList");


		// 没有任何错误
		soPackageFacade.completeDeliveryImport(DeliveryImportExcelConverter.toDTOs(voList),operatorId,platformId);

		// 发货信息导入成功则记录日志信息
		// 2)查询变更后数据
		Map<String,Object> newObjects = soPackageFacade.getSoChildListByPackageIds(soChildIdList);
		List<SoPackageDTO> soPackageDTOList_ = (List<SoPackageDTO>) newObjects.get("soPackageDTOList");
		List<SoChildDTO> soChildDTOList_ = (List<SoChildDTO>) newObjects.get("soChildDTOList");

		// 3)插入操作日志
		for (SoChildDTO soChildDTO : soChildDTOList) {
			for (SoChildDTO soChildDTO_ : soChildDTOList_) {
				if (soChildDTO.getId().equals(soChildDTO_.getId())) {
					// 发货导入(子订单更新日志)
//						logger.info("日志操作,发货导入,子订单更新日志: " + soChildDTO.getId());
					EgeoLog log = new EgeoLog();
					log.setModuleName(LogConstant.ORDER_MANAGEMENT.getComment());
					log.setOperObject("SoPackageAction_assureImport");
					log.setMsgId(LogConstant.SHIPMENTS_IMPORT_CHILDSO.getStatus());
					log.setType(LogTypeConstant.SO_CHILD.getStatus());
					log.setOperatorObjId(soChildDTO_.getId());
					log.setOperatorObjCode(soChildDTO_.getChildCode());
					log.setOldObj(soChildDTO);
					log.setNewObj(soChildDTO_);
					EgeoBusinessLogCommon.fillLogValue(log, req);
					try {
						ActiveMQUtils.recordBusinessLog(log);
					}catch (Exception e) {
						// TODO: handle exception
						logger.error("发送日志消息失败："+ JSON.toJSONString(log));
					}
				}
			}
		}

		for (SoPackageDTO soPackageDTO_ : soPackageDTOList_) {
			// 发货导入(物流更新日志)
			//			logger.info("日志操作,发货导入,物流更新日志: " + soPackageDTO.getId());
			EgeoLog log = new EgeoLog();
			log.setModuleName(LogConstant.ORDER_MANAGEMENT.getComment());
			log.setOperObject("SoPackageAction_assureImport");
			log.setMsgId(LogConstant.SHIPMENTS_IMPORT_LOGISTICS.getStatus());
			log.setType(LogTypeConstant.SO_PACKAGE.getStatus());
			log.setOperatorObjId(soPackageDTO_.getId());
			log.setOperatorObjCode(soPackageDTO_.getDeliveryCode());
			log.setOperatorObjName(soPackageDTO_.getDeliveryName());
			//			log.setOldObj(soPackageDTO);
			log.setNewObj(soPackageDTO_);
			EgeoBusinessLogCommon.fillLogValue(log, req);

			try {
				ActiveMQUtils.recordBusinessLog(log);
			}catch (Exception e) {
				// TODO: handle exception
				logger.error("发送日志消息失败："+ JSON.toJSONString(log));
			}
		}
	}
	@Override
	public JsonResult<Map<String, Object>> signImport(Integer tempType, Long platformId,
			List<Map<String, Object>> valueList) {
		return null;

//		if (valueList.size() > 1002)
//			return JsonResult.fail("单次导入数据不能超过1000条");
//
//		// *************************************** 检查头文件及内容
//		String err = ExcelHeadChecker.chechHeader(valueList, ExcelTmplConstant.orderSign.getTmplType(), false);
//		if (EmptyUtil.isNotBlank(err))
//			return JsonResult.fail(err);
//
//		// 模板类型
//		String tmplName = ExcelTmplConstant.orderSign.getTmplName();
//		if (!StringUtils.equals(tmplName, valueList.get(0).get("CELL4").toString())) {
//			return JsonResult.fail("导入的文件类型错误，请检查后重新选择文件导入");
//		}
//
//		// 创建时间
//		String createTime = valueList.get(0).get("CELL6").toString();
//
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//		Date date = null;
//		try {
//			date = sdf.parse(createTime);
//		} catch (ParseException e) {
//			return JsonResult.fail("创建时间解析错误");
//		}
//
//		// 序列号校验
//		String sn = valueList.get(0).get("CELL8").toString();
//		// 创建导入正式记录的信息
//		HeadImportRecordsDTO headImportRecordsDTO = new HeadImportRecordsDTO();
//
//		headImportRecordsDTO.setGeneratedTime(date);
//		headImportRecordsDTO.setTemplateType(tmplName);
//		headImportRecordsDTO.setFileSequenceNumber(sn);
//
//		List<HeadImportRecordsDTO> importRecordList = soPackageFacade.findHeadImportRecordsAll(headImportRecordsDTO);
//		if (!EmptyUtil.isEmpty(importRecordList))
//			return JsonResult.fail("文件序列已存在,请确认是否已经导入");
//
//		// ************************************
//
//		// 列表文件校验
//		// 封装错误的集合
//		List<PropblemReportRowVO<SoPackageTempVO>> problemRep = new ArrayList<>();
//
//		// 预览的集合
//		List<SoPackageTempVO> soPackageViewList = new ArrayList<>();
//
//		// 对子订单编号创建一个集合
//		Set<Long> soChildCodeSet = new HashSet<>();
//		SoChildDTO soChildDTO = new SoChildDTO();
//		// 查询已发货的子订单
//		soChildDTO.setDeliveryStatus(2);
//		List<SoChildDTO> soChildDTOList = soPackageFacade.findAllSochildByCondition(soChildDTO);
//
//		if (EmptyUtil.isNotEmpty(soChildDTOList)) {
//			for (SoChildDTO dto : soChildDTOList) {
//				soChildCodeSet.add(dto.getId());
//			}
//		}
//
//		for (int i = 2; i < valueList.size(); i++) {
//
//			// 内部不重复,空值校验
//
//			SoPackageTempVO vo = row2bean(valueList.get(i), problemRep, i, soChildCodeSet, 2);
//
//			soPackageViewList.add(vo);
//		}
//
//		// 校验完成,封装数据
//		if (problemRep.size() == 0) {
//
//			// 说明没有任何错误
//			// 1.经表头信息储存到临时导入记录表,并且封装导入记录表的信息
//			// 写导入记录表的数据
//			ImportRecordsDTO importRecordsDTO = new ImportRecordsDTO();
//			importRecordsDTO.setCompanyName(valueList.get(0).get("CELL2").toString());
//			importRecordsDTO.setTemplateType(valueList.get(0).get("CELL4").toString());
//			importRecordsDTO.setGeneratedTime(date);
//			importRecordsDTO.setFileSequenceNumber(sn);
//
//			// 2.将读到的数据存储在临时表里面。封装要预览的数据
//			for (SoPackageTempVO soPackageTempVO : soPackageViewList) {
//				// 将时间转换成date类型
//				soPackageTempVO.setPlatformId(platformId);
//			}
//			List<SoPackageTempDTO> dtoList = SoPackageTempConverter.toDTO(soPackageViewList);
//
//			Map<String, Object> data = soPackageFacade.insertimportTempRecordsViewList(importRecordsDTO, dtoList);
//
//			// 将表头信心返回
//			data.put("headInfo", importRecordsDTO);
//			data.put("overView", soPackageViewList);
//
//			return JsonResult.success("success", data);
//		} else {
//			// 说明有错误
//			/*
//			 * 结果集包含内容包含内容: 错误报告url(导入失败) 导入预览批次(导入成功用于查询预览)
//			 */
//			// 出现错误
//			// 生成问题报告.xlsx上传至dfs
//			// 封装返回值得集合
//			String repUrl = genAndUploadRepDelievery(problemRep);
//			return JsonResult.fail(repUrl, 169);
//		}

	}
	@Override
	public JsonResult<Map<String, Object>> addDeliveryInfo(Long boxCode, String orderCode, String childCode, String deliveryCompany, String deliveryCode, Long operatorId, Long platformId,Boolean force) {

		boolean forceUpdate = false;
		if(force!=null) {
			forceUpdate = force;
		}

		DeliveryImportExcelVO vo = new DeliveryImportExcelVO();
		vo.setChildCode(childCode);
		vo.setDeliveryCode(deliveryCode);
		vo.setDeliveryCompany(deliveryCompany);
		vo.setOrderCode(orderCode);
		vo.setBoxCode(boxCode);
		// 根据子订单编号查询子订单
		SoChildDTO sc = soFacade.querySoChildByChildCode(childCode);
		if (sc == null) {
			return JsonResult.fail("编号为" + childCode + "的子订单不存在");
		} else {
			if (Objects.equals(1,sc.getCancelStatus())){
				return JsonResult.fail("编号为"+childCode + "的子订单已退款取消");
			}
			//检查子订单状态是否是分拣中
			Integer scDeliveryStatus=sc.getDeliveryStatus();
			if(!forceUpdate) {
				if(scDeliveryStatus!=1) {
					return JsonResult.fail("编号为"+childCode + "的子订单不是分拣中状态");
				}
			}
			// 检查各列与子订单在数据库中的信息是否对等
			SoDTO so = soFacade.querySoById(sc.getSoId());
			if (so == null) {
				return JsonResult.fail("编号为" + childCode + "的子订单没有对应的母订单");
			} else {
				vo.setSoId(so.getId());
				vo.setSoChildId(sc.getId());
				vo.setReceiverAddressId(sc.getReceiverAddressId());
				vo.setUserId(so.getUserId());
				if (!orderCode.equals(so.getOrderCode())) {
					return JsonResult.fail("母订单编号不正确,应该是" + so.getOrderCode());
				}
				if(!forceUpdate) {
					//订单状态校验
					if(so.getOrderConfirmStatus()==2|| so.getOrderConfirmStatus()==3) {
						return JsonResult.fail("订单号为" + so.getOrderCode()+"的订单状态不正确");
					}
				}
				// 查询物流公司
				DeliveryCompanyDTO dc = deliveryCompanyFacade.queryDeliveryCompanyByName(deliveryCompany);
				if (dc == null) {
					return JsonResult.fail("名称为" + deliveryCompany + "的物流公司不存在");
				}else {
					vo.setDeliveryCompanyId(dc.getId());
				}
				if (Objects.equals(so.getOrderStatus(),1)){
					SoDTO newSo = new SoDTO();
					newSo.setId(so.getId());
					newSo.setOrderStatus(Integer.valueOf(2));
					soFacade.updateOrderByOrderId(newSo);
				}
			}
		}
		SoChildDTO soChild = soFacade.findSoChildById(vo.getSoChildId());
		if (Long.valueOf(3L).equals(soChild.getPerformingParty())) {
			return JsonResult.fail("网店管家订单不支持发货导入");
		}
		if (Long.valueOf(6L).equals(soChild.getPerformingParty())) {
			return JsonResult.fail("京东订单不支持发货导入");
		}
		soPackageFacade.completeDeliveryImport(DeliveryImportExcelConverter.toDTOs(Arrays.asList(vo)),operatorId,platformId);
		//发货推送订单
		pushOrderManage.pushOrderInfo(soChild.getSoId(),null,null);
		return JsonResult.success();
	}

	@Resource
	private PushOrderManage pushOrderManage;


	/**
	 * 将excel表格数据转化为java对象 同时检查数据有效性,进行空值校验
	 *
	 * @param row
	 * @param problemRep
	 * @param rowNo
	 * @return
	 */
	private DeliveryImportExcelVO row2bean(Map<String, Object> row,
			List<PropblemReportRowVO<DeliveryImportExcelVO>> problemRep, int rowNo) {
		String orderCode = row.get("CELL1").toString();
		String childCode = row.get("CELL2").toString();
		String deliveryCompany = row.get("CELL3").toString();
		String deliveryCode = row.get("CELL4").toString();
		DeliveryImportExcelVO vo = new DeliveryImportExcelVO();
		vo.setChildCode(childCode);
		vo.setDeliveryCode(deliveryCode);
		vo.setDeliveryCompany(deliveryCompany);
		vo.setOrderCode(orderCode);
		//箱号可以为空
		Object boxCode_=row.get("CELL5");
		Long boxCode = null;
		try{
			boxCode = EmptyUtil.isBlank(boxCode_.toString()) ? null : Long.parseLong(row.get("CELL5").toString());
		} catch (NumberFormatException e){
			problemRep.add(new PropblemReportRowVO<>("箱号必须是整数", rowNo, vo));
		}
		vo.setBoxCode(boxCode);

		// 空值标志
		boolean blankFlag = false;
		// 空值校验
		if (EmptyUtil.isBlank(orderCode)) {
			blankFlag = true;
			problemRep.add(new PropblemReportRowVO<>("订单编号为空", rowNo, vo));
		}
		if (EmptyUtil.isBlank(childCode)) {
			blankFlag = true;
			problemRep.add(new PropblemReportRowVO<>("子订单编号为空", rowNo, vo));
		}
		if (EmptyUtil.isBlank(deliveryCompany)) {
			blankFlag = true;
			problemRep.add(new PropblemReportRowVO<>("物流公司为空", rowNo, vo));
		}
		if (EmptyUtil.isBlank(deliveryCode)) {
			blankFlag = true;
			problemRep.add(new PropblemReportRowVO<>("运单号为空", rowNo, vo));
		}
		if (!blankFlag) {
			// 根据子订单编号查询子订单
			SoChildDTO sc = soFacade.querySoChildByChildCode(childCode);
			if (sc == null) {
				problemRep.add(new PropblemReportRowVO<>("编号为" + childCode + "的子订单不存在", rowNo, vo));
			} else {
				//检查子订单状态是否是分拣中
				Integer scDeliveryStatus=sc.getDeliveryStatus();
				if(scDeliveryStatus!=1) {
					problemRep.add(new PropblemReportRowVO<>("编号为"+childCode + "的子订单不是分拣中状态", rowNo, vo));
				}
				// 检查各列与子订单在数据库中的信息是否对等
				SoDTO so = soFacade.querySoById(sc.getSoId());
				if (so == null) {
					problemRep.add(new PropblemReportRowVO<>("编号为" + childCode + "的子订单没有对应的母订单", rowNo, vo));
				} else {
					vo.setSoId(so.getId());
					vo.setSoChildId(sc.getId());
					vo.setReceiverAddressId(sc.getReceiverAddressId());
					vo.setUserId(so.getUserId());
					if (!orderCode.equals(so.getOrderCode())) {
						problemRep.add(new PropblemReportRowVO<>("母订单编号不正确,应该是" + so.getOrderCode(), rowNo, vo));
					}
					//订单状态校验
					if(so.getOrderConfirmStatus()==2|| so.getOrderConfirmStatus()==3) {
						problemRep.add(new PropblemReportRowVO<>("订单号为" + so.getOrderCode()+"的订单状态不正确", rowNo, vo));
					}
					// 查询物流公司
					DeliveryCompanyDTO dc = deliveryCompanyFacade.queryDeliveryCompanyByName(deliveryCompany);
					if (dc == null) {
						problemRep.add(new PropblemReportRowVO<>("名称为" + deliveryCompany + "的物流公司不存在", rowNo, vo));
					}else {
						vo.setDeliveryCompanyId(dc.getId());
					}
				}
			}
		}
		return vo;
	}

	/**
	 * 生成和上传问题报告
	 *
	 * @param problemRep
	 * @return
	 */
	private String genAndUploadRepDelievery(List<PropblemReportRowVO<DeliveryImportExcelVO>> problemRep) {
		Workbook wb = new XSSFWorkbook();
		Sheet sh = wb.createSheet("员工充值导入问题报告");
		for (int i = 0; i < problemRep.size(); i++) {
			PropblemReportRowVO<DeliveryImportExcelVO> vo = problemRep.get(i);
			Row r = sh.createRow(i);
			DeliveryImportExcelVO lineMeta = vo.getLineMeta();
			if (lineMeta != null) {
				r.createCell(0).setCellValue(lineMeta.getOrderCode());
				r.createCell(1).setCellValue(lineMeta.getChildCode());
				r.createCell(2).setCellValue(lineMeta.getDeliveryCompany());
				r.createCell(3).setCellValue(lineMeta.getDeliveryCode());
				r.createCell(4).setCellValue(lineMeta.getBoxCode());
			}
			r.createCell(5).setCellValue(vo.getProblem());
			r.createCell(6).setCellValue("在第" + vo.getLineNo() + "行");
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
		return filePath+"?attname=发货导入问题报告.xlsx";
	}

	@Override
	public Long assureImportDelivery(Long platformId, Long parseLong, String soPackageInfo) {
		// 确认导入发货信息
		return soPackageFacade.assureImportDeliveryOrSign(platformId, parseLong, soPackageInfo, 2);
	}

	@Override
	public Long assureImportSign(Long platformId, Long parseLong, String soPackageInfo) {
		// 确认导入签收信息
		return soPackageFacade.assureImportDeliveryOrSign(platformId, parseLong, soPackageInfo, 3);
	}

	@Override
	public PageResult<SoPackageDTO> findSoPackageAndBoxOfPage(SoPackageDTO dto, Pagination page) {
		return soPackageFacade.findSoPackageAndBoxOfPage(dto, page);
	}

	@Override
	public JsonResult<Map<String, Object>> sortImport(Integer tempType, Long platformId,
			List<Map<String, Object>> valueList) {

		return null;
	}

	@Override
	public JsonResult<Map<String, Object>> soDeliveryInfoList(Long orderId) {
		if (orderId == null)
			return JsonResult.fail("请选择订单");
		// 查询子订单列表
		List<SoChildDTO> scs = soFacade.querySoChildListBySoId(orderId);
		List<SoPackageView> res = new ArrayList<>();
		for (SoChildDTO sc : scs) {
			SoPackageView view = new SoPackageView();
			Long soChildId = sc.getId();
			view.setSoChildId(soChildId);
			view.setChildCode(sc.getChildCode());
			view.setDeliveryStatus(sc.getDeliveryStatus());
			// 查询物流信息
			SoPackageDTO pack = soPackageFacade.queryPackageBySoChildIdDistince(soChildId);
			if (pack != null) {
				view.setId(pack.getId());
				view.setUpdateTime(pack.getUpdateTime());
				view.setDeliveryCode(pack.getDeliveryCode());
				// 查询所有箱子
				List<SoPackageBoxDTO> boxes = soPackageFacade.queryBoxListBySoChildId(soChildId);
				StringBuilder sb = new StringBuilder();
				if (boxes.size() > 0) {
					for (SoPackageBoxDTO b : boxes) {
						sb.append(b.getSoBoxCode()).append(";");
					}
				}
				view.setSoBoxCode(sb.toString());
				view.setPackageExist(true);
				view.setDeliveryCompanyName(pack.getDeliveryCompanyName());
			} else {
				view.setPackageExist(false);
			}
			res.add(view);
		}
		return JsonResult.success("list", res);
	}

	@Override
	public JsonResult<Map<String, Object>> addBox(Long soChildId, Long boxCode) {
		if (soChildId == null) {
			return JsonResult.fail("请选择子订单");
		}
		if (boxCode == null) {
			return JsonResult.fail("请输入箱号");
		}
		if (boxCode > 1000000000000000l) {
			return JsonResult.fail("箱号太大");
		}
		// 查询子订单是否有物流信息
		SoPackageDTO pack = soPackageFacade.queryPackageBySoChildIdDistince(soChildId);
		if (pack == null)
			return JsonResult.fail("该子订单尚无物流信息,无法新增箱号");

		// 检查箱号是否已经使用
		SoChildDTO soChildDTO = soFacade.findSoChildById(soChildId);
		List<SoChildDTO> soChildList = soFacade.querySoChildListBySoId(soChildDTO.getSoId());
		for (SoChildDTO soChild : soChildList) {
			SoPackageBoxDTO cond=new SoPackageBoxDTO();
			cond.setSoChildId(soChild.getId());
			cond.setSoBoxCode(boxCode);
			List<SoPackageBoxDTO> soPackageBoxDTOList =  soPackageFacade.findSoPackageBox(cond);
			if (EmptyUtil.isNotEmpty(soPackageBoxDTOList))
				return JsonResult.fail("箱号已经存在");
		}

		SoPackageBoxDTO insertBox = new SoPackageBoxDTO();
		insertBox.setSoChildId(soChildId);
		insertBox.setSoBoxCode(boxCode);
		soPackageFacade.insertSoPackageBox(insertBox);
		return JsonResult.success();
	}

	@Override
	public SoPackageBoxDTO querySoPackageBoxByBoxCodeAndChildId(Long boxCode,Long soChildId) {
		return soPackageFacade.queryBoxByBoxCodeAndChildId(boxCode,soChildId);
	}

	@Override
	public JsonResult<Map<String, Object>> deliveryImportSoChild(Long operatorId,
															   Long platformId,
															   List<Map<String, Object>> valueList,HttpServletRequest req) {

		if(valueList.size()<2){
			return JsonResult.fail("导入数据不能为空");
		}
		if (valueList.size() > 1002)
			return JsonResult.fail("单次导入数据不能超过1000条");

		// *************************************** 检查头文件及内容
		String err = ExcelCheckHelper.chechHeader(valueList, ExcelTemplateEnum.SO_CHILD_DELIVERY.getTmplType(), false);
		if (EmptyUtil.isNotBlank(err))
			return JsonResult.fail(err);

		// 创建导入正式记录的信息



		// ************************************

		// 列表文件校验
		// 封装错误的集合
		List<PropblemReportRowVO<DeliveryImportExcelVO>> problemRep = new ArrayList<>();
		// 查重集合
		Set<DeliveryImportExcelVO> repeatSet = new HashSet<>();
		List<DeliveryImportExcelVO> voList=new ArrayList<>();
		//Set<String> deliveryCodeSet=new HashSet<>();
		List<Long> soChildIdList = new ArrayList<Long>();
		for (int i = 1; i < valueList.size(); i++) {
			// 内部不重复,空值校验
			DeliveryImportExcelVO vo = row2beanDeliveryImport(valueList.get(i), problemRep, i);
			voList.add(vo);
			soChildIdList.add(vo.getSoChildId());
			if(!repeatSet.add(vo)) {
				problemRep.add(new PropblemReportRowVO<>("该行与前面的记录有重复",i,vo));
			}
		}

		// 校验完成,封装数据
		if (problemRep.size() == 0) {
			writeSoChildDeliveryImport(req,soChildIdList,voList,operatorId,platformId);
			return JsonResult.success();
		} else {
			//有错误,将excel上传至fastDFS
			//返回url
			String repUrl = genAndUploadRepDelievery(problemRep,"供应商发货");
			return JsonResult.fail(repUrl, BusinessExceptionConstant.IMPORTFILEERROR);
		}
	}



	public void writeSoChildDeliveryImport(HttpServletRequest req, List<Long> soChildIdList, List<DeliveryImportExcelVO> voList, Long operatorId, Long platformId){
		// 发货导入日志: 1.更新子订单状态   2.更新物流信息
		// 1)查询变更前数据
		Map<String,Object> oldObjects = soPackageFacade.getSoChildListByPackageIds(soChildIdList);
		List<SoPackageDTO> soPackageDTOList = (List<SoPackageDTO>) oldObjects.get("soPackageDTOList");
		List<SoChildDTO> soChildDTOList = (List<SoChildDTO>) oldObjects.get("soChildDTOList");


		// 没有任何错误
		soPackageFacade.completeChildDeliveryImport(DeliveryImportExcelConverter.toDTOs(voList),operatorId,platformId);

		// 发货信息导入成功则记录日志信息
		// 2)查询变更后数据
		Map<String,Object> newObjects = soPackageFacade.getSoChildListByPackageIds(soChildIdList);
		List<SoPackageDTO> soPackageDTOList_ = (List<SoPackageDTO>) newObjects.get("soPackageDTOList");
		List<SoChildDTO> soChildDTOList_ = (List<SoChildDTO>) newObjects.get("soChildDTOList");

		// 3)插入操作日志
		for (SoChildDTO soChildDTO : soChildDTOList) {
			for (SoChildDTO soChildDTO_ : soChildDTOList_) {
				if (soChildDTO.getId().equals(soChildDTO_.getId())) {
					// 发货导入(子订单更新日志)
//						logger.info("日志操作,发货导入,子订单更新日志: " + soChildDTO.getId());
					EgeoLog log = new EgeoLog();
					log.setModuleName(LogConstant.ORDER_MANAGEMENT.getComment());
					log.setOperObject("SoPackageAction_assureImport");
					log.setMsgId(LogConstant.SHIPMENTS_IMPORT_CHILDSO.getStatus());
					log.setType(LogTypeConstant.SO_CHILD.getStatus());
					log.setOperatorObjId(soChildDTO_.getId());
					log.setOperatorObjCode(soChildDTO_.getChildCode());
					log.setOldObj(soChildDTO);
					log.setNewObj(soChildDTO_);
					EgeoBusinessLogCommon.fillLogValue(log, req);
					try {
						ActiveMQUtils.recordBusinessLog(log);
					}catch (Exception e) {
						// TODO: handle exception
						logger.error("发送日志消息失败："+ JSON.toJSONString(log));
					}
				}
			}
		}

		for (SoPackageDTO soPackageDTO_ : soPackageDTOList_) {
			// 发货导入(物流更新日志)
			//			logger.info("日志操作,发货导入,物流更新日志: " + soPackageDTO.getId());
			EgeoLog log = new EgeoLog();
			log.setModuleName(LogConstant.ORDER_MANAGEMENT.getComment());
			log.setOperObject("SoPackageAction_assureImport");
			log.setMsgId(LogConstant.SHIPMENTS_IMPORT_LOGISTICS.getStatus());
			log.setType(LogTypeConstant.SO_PACKAGE.getStatus());
			log.setOperatorObjId(soPackageDTO_.getId());
			log.setOperatorObjCode(soPackageDTO_.getDeliveryCode());
			log.setOperatorObjName(soPackageDTO_.getDeliveryName());
			log.setNewObj(soPackageDTO_);
			EgeoBusinessLogCommon.fillLogValue(log, req);

			try {
				ActiveMQUtils.recordBusinessLog(log);
			}catch (Exception e) {
				// TODO: handle exception
				logger.error("发送日志消息失败："+ JSON.toJSONString(log));
			}
		}
		pushOrder(soChildDTOList_);
	}

	private void pushOrder(List<SoChildDTO> soChildDTOList_){
		try {
			if(CollectionUtils.isEmpty(soChildDTOList_)){
				return;
			}

			for (SoChildDTO soChildDTO : soChildDTOList_) {
				if(soChildDTO.getSoId()==null){
					continue;
				}
				pushOrderManage.pushOrderInfo(soChildDTO.getSoId(),null,null);
			}
		}catch (Exception e){
			logger.error("批量发货时推送订单发生异常:{}",e);
		}

	}

	/**
	 * 将excel表格数据转化为java对象 同时检查数据有效性,进行空值校验
	 *
	 * @param row
	 * @param problemRep
	 * @param rowNo
	 * @return
	 */
	private DeliveryImportExcelVO row2beanDeliveryImport(Map<String, Object> row,
										   List<PropblemReportRowVO<DeliveryImportExcelVO>> problemRep, int rowNo) {
		String orderCode = row.get("CELL1").toString();
		String childCode = row.get("CELL2").toString();
		String deliveryCode = row.get("CELL3").toString();
		String deliveryCompany = row.get("CELL4").toString();
		DeliveryImportExcelVO vo = new DeliveryImportExcelVO();
		vo.setChildCode(childCode);
		vo.setDeliveryCode(deliveryCode);
		vo.setDeliveryCompany(deliveryCompany);
		vo.setOrderCode(orderCode);


		// 空值标志
		boolean blankFlag = false;
		// 空值校验
		if (EmptyUtil.isBlank(orderCode)) {
			blankFlag = true;
			problemRep.add(new PropblemReportRowVO<>("订单编号为空", rowNo, vo));
		}
		if (EmptyUtil.isBlank(childCode)) {
			blankFlag = true;
			problemRep.add(new PropblemReportRowVO<>("子订单编号为空", rowNo, vo));
		}
		if (EmptyUtil.isBlank(deliveryCompany)) {
			blankFlag = true;
			problemRep.add(new PropblemReportRowVO<>("物流公司为空", rowNo, vo));
		}
		if (EmptyUtil.isBlank(deliveryCode)) {
			blankFlag = true;
			problemRep.add(new PropblemReportRowVO<>("运单号为空", rowNo, vo));
		}
		if (!blankFlag) {
			// 根据子订单编号查询子订单
			SoChildDTO sc = soFacade.querySoChildByChildCode(childCode);
			if (sc == null) {
				problemRep.add(new PropblemReportRowVO<>("编号为" + childCode + "的子订单不存在", rowNo, vo));
			} else {
				//检查子订单状态是否是分拣中
				Integer scDeliveryStatus=sc.getDeliveryStatus();
				if(scDeliveryStatus!=1) {
					problemRep.add(new PropblemReportRowVO<>("编号为"+childCode + "的子订单不是分拣中状态", rowNo, vo));
				}
				if(EmptyUtil.isNotEmpty(sc.getSource()) &&(Objects.equals(sc.getSource(),ThirdConst.Source.JD)
						|| Objects.equals(sc.getSource(),ThirdConst.Source.CAKE)
						|| Objects.equals(sc.getSource(),ThirdConst.Source.WORLD)
						|| Objects.equals(sc.getSource(),ThirdConst.Source.QM))){
					problemRep.add(new PropblemReportRowVO<>("编号为"+childCode + "的子订单不是为第三方订单不能发货", rowNo, vo));
				}
				// 检查各列与子订单在数据库中的信息是否对等
				SoDTO so = soFacade.querySoById(sc.getSoId());
				if (so == null) {
					problemRep.add(new PropblemReportRowVO<>("编号为" + childCode + "的子订单没有对应的母订单", rowNo, vo));
				} else {
					vo.setSoId(so.getId());
					vo.setSoChildId(sc.getId());
					vo.setReceiverAddressId(sc.getReceiverAddressId());
					vo.setUserId(so.getUserId());
					if (!orderCode.equals(so.getOrderCode())) {
						problemRep.add(new PropblemReportRowVO<>("母订单编号不正确,应该是" + so.getOrderCode(), rowNo, vo));
					}
					//订单状态校验
					if(Objects.equals(so.getOrderStatus(),OrderConstant.ORDER_STATUS_UNPAY.getStatus()) || so.getOrderConfirmStatus()==2|| so.getOrderConfirmStatus()==3) {
						problemRep.add(new PropblemReportRowVO<>("订单号为" + so.getOrderCode()+"的订单状态不正确", rowNo, vo));
					}
					// 查询物流公司
					DeliveryCompanyDTO dc = deliveryCompanyFacade.queryDeliveryCompanyByName(deliveryCompany);
					if (dc == null) {
						problemRep.add(new PropblemReportRowVO<>("名称为" + deliveryCompany + "的物流公司不存在", rowNo, vo));
					}else {
						vo.setDeliveryCompanyId(dc.getId());
					}
				}
			}
		}
		return vo;
	}


	/**
	 * 生成和上传问题报告
	 *
	 * @param problemRep
	 * @return
	 */
	private String genAndUploadRepDelievery(List<PropblemReportRowVO<DeliveryImportExcelVO>> problemRep,String sheetName) {
		Workbook wb = new XSSFWorkbook();
		Sheet sh = wb.createSheet(EmptyUtil.isNotEmpty(sheetName)?sheetName+"导入问题报告":"导入问题报告");
		for (int i = 0; i < problemRep.size(); i++) {
			PropblemReportRowVO<DeliveryImportExcelVO> vo = problemRep.get(i);
			Row r = sh.createRow(i);
			DeliveryImportExcelVO lineMeta = vo.getLineMeta();
			if (lineMeta != null) {
				r.createCell(0).setCellValue(lineMeta.getOrderCode());
				r.createCell(1).setCellValue(lineMeta.getChildCode());
				r.createCell(2).setCellValue(lineMeta.getDeliveryCode());
				r.createCell(3).setCellValue(lineMeta.getDeliveryCompany());
			}
			r.createCell(4).setCellValue(vo.getProblem());
			r.createCell(5).setCellValue("在第" + vo.getLineNo() + "行");
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
		return filePath+"?attname=发货导入问题报告.xlsx";
	}

	@Override
	public JsonResult<Map<String, Object>> deliveryPlatformImport(Long operatorId,
														  Long platformId,
														  List<Map<String, Object>> valueList,HttpServletRequest req) {

		if (valueList.size() > 1002)
			return JsonResult.fail("单次导入数据不能超过1000条");

		// *************************************** 检查头文件及内容
		String err = ExcelCheckHelper.chechHeader(valueList, ExcelTemplateEnum.SO_CHILD_DELIVERY.getTmplType(), false);
		if (EmptyUtil.isNotBlank(err))
			return JsonResult.fail(err);

		// 列表文件校验
		// 封装错误的集合
		List<PropblemReportRowVO<DeliveryImportExcelVO>> problemRep = new ArrayList<>();
		// 查重集合
		Set<DeliveryImportExcelVO> repeatSet = new HashSet<>();
		List<DeliveryImportExcelVO> voList=new ArrayList<>();
		//Set<String> deliveryCodeSet=new HashSet<>();
		List<Long> soChildIdList = new ArrayList<Long>();
		for (int i = 1; i < valueList.size(); i++) {
			// 内部不重复,空值校验
			DeliveryImportExcelVO vo = row2beanDeliveryImport(valueList.get(i), problemRep, i);
			voList.add(vo);
			soChildIdList.add(vo.getSoChildId());
			if(!repeatSet.add(vo)) {
				problemRep.add(new PropblemReportRowVO<>("该行与前面的记录有重复",i,vo));
			}
		}

		// 校验完成,封装数据
		if (problemRep.size() == 0) {
			writeSoChildDeliveryImport(req,soChildIdList,voList,operatorId,platformId);
			return JsonResult.success();
		} else {
			//有错误,将excel上传至fastDFS
			//返回url
			String repUrl = genAndUploadRepDelievery(problemRep,"平台发货");
			return JsonResult.fail(repUrl, BusinessExceptionConstant.IMPORTFILEERROR);
		}
	}
}
