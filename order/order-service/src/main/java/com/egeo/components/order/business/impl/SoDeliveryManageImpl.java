package com.egeo.components.order.business.impl;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;

import com.egeo.components.order.enums.ThirdConst;
import com.egeo.components.utils.CakeUtil;
import com.egeo.components.utils.JdUtils2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.egeo.components.order.business.SoDeliveryManage;
import com.egeo.components.order.dto.DeliveryCompanyDTO;
import com.egeo.components.order.dto.SoChildDTO;
import com.egeo.components.order.dto.SoDeliveryDTO;
import com.egeo.components.order.dto.SoPackageDTO;
import com.egeo.components.order.facade.DeliveryCompanyFacade;
import com.egeo.components.order.facade.SoDeliveryFacade;
import com.egeo.components.order.facade.SoFacade;
import com.egeo.components.order.facade.SoPackageFacade;
import com.egeo.components.order.vo.Steps;
import com.egeo.components.order.vo.Traces;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.delivery.DepponTimelyQuery;
import com.egeo.utils.delivery.JdUtils;
import com.egeo.utils.delivery.KdniaoTrackQueryAPI;
import com.egeo.utils.delivery.SfDeliveryQueryUtil;
import com.egeo.utils.delivery.ZTOTimelyQuery;
import com.egeo.web.JsonResult;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.CollectionUtils;

@Service("soDelivery")
public class SoDeliveryManageImpl implements SoDeliveryManage {
	public Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	// 定义jackson对象
	private static final ObjectMapper MAPPER = new ObjectMapper();

	@Autowired
	private JdUtils jdUtils;

	@Autowired
	private JdUtils2 jdUtils2;

	@Resource(name = "soDeliveryFacade")
	private SoDeliveryFacade soDeliveryFacade;

	@Resource(name = "soFacade")
	private SoFacade soFacade;

	@Resource(name = "soPackageFacade")
	private SoPackageFacade soPackageFacade;

	@Resource(name = "deliveryCompanyFacade")
	private DeliveryCompanyFacade deliveryCompanyFacade;

	@Resource
	private CakeUtil cakeUtil;

	@Override
	public SoDeliveryDTO findSoDeliveryById(SoDeliveryDTO dto) {
		return soDeliveryFacade.findSoDeliveryById(dto);
	}

	@Override
	public PageResult<SoDeliveryDTO> findSoDeliveryOfPage(SoDeliveryDTO dto, Pagination page) {
		return soDeliveryFacade.findSoDeliveryOfPage(dto, page);
	}

	@Override
	public List<SoDeliveryDTO> findSoDeliveryAll(SoDeliveryDTO dto) {
		return soDeliveryFacade.findSoDeliveryAll(dto);
	}

	@Override
	public int insertSoDeliveryWithTx(SoDeliveryDTO dto) {
		return soDeliveryFacade.insertSoDeliveryWithTx(dto);
	}

	@Override
	public int updateSoDeliveryWithTx(SoDeliveryDTO dto) {
		return soDeliveryFacade.updateSoDeliveryWithTx(dto);
	}

	@Override
	public int deleteSoDeliveryWithTx(SoDeliveryDTO dto) {
		return soDeliveryFacade.deleteSoDeliveryWithTx(dto);
	}

	@Autowired
	private JedisUtil jedisUtil;


	@Override
	public List<Steps> openDeliveryTrace(SoPackageDTO soPackageDTO, String shipCompanyCode) {

		logger.info("物流单号:"+soPackageDTO.getDeliveryCode()+",公司:"+soPackageDTO.getDeliveryCompanyName());
		List<Steps> stepsList = null;
		/*
		 * 德邦,中通,快递鸟的运单轨迹返回均为从早到晚的顺序
		 * 对于未签收的订单
		 * 查询出结果后,如果是已签收的单,反转后保存轨迹并返回至客户端
		 * 查询出结果后,如果是未签收的订单,则反转后返回至客户端
		 * 对于轨迹已被保存(已签收)的运单
		 * 从数据库中查询数据,返回至客户端
		 */
		try {

			if (soPackageDTO.getDeliveryMessage() != null && !soPackageDTO.getDeliveryMessage().equals("")) {
				JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, Steps.class);
				List<Steps> list = MAPPER.readValue(soPackageDTO.getDeliveryMessage(), javaType);
				return list;
			} else {
				if(Long.valueOf(6L).equals(soPackageDTO.getMerchantId())){
					return getJdSteps(soPackageDTO);
				}
				/*if(Long.valueOf(7L).equals(soPackageDTO.getMerchantId())){
					return getCakeSteps(soPackageDTO);
				}*/

				if (EmptyUtil.isNotEmpty(shipCompanyCode)) {
					// 大写转化处理
					shipCompanyCode = shipCompanyCode.toUpperCase();
					if ("old-SF".equals(shipCompanyCode)) {
						stepsList = new ArrayList<>();
						List<Map<String, String>> sfRsp = SfDeliveryQueryUtil.queryByTrackingNumber(soPackageDTO.getDeliveryCode());
						if (EmptyUtil.isNotEmpty(sfRsp)) {
							for (Map<String, String> s : sfRsp) {
								Steps step = new Steps(s.get("acceptTime"), s.get("remark"));
								stepsList.add(step);
							}
							String string = MAPPER.writeValueAsString(stepsList);
							if (string.contains("签收人")) {
								soDeliveryFacade.expressSignIn(soPackageDTO.getSoId(),string, soPackageDTO.getId(),
										soPackageDTO.getSoChildId());
							}
						}
					} else if (shipCompanyCode.equals("old-ZTO")) {
						// 备注:中通快递走快递鸟
						List<Steps> stepss = new ArrayList<Steps>();
						String json = ZTOTimelyQuery.getOrderTraces(shipCompanyCode, soPackageDTO.getDeliveryCode());
						JSONObject createJsonObject = JSON.parseObject(json.toString());
						if (createJsonObject.getString("msg").equals("")) {
							String data = createJsonObject.getString("data");
							JSONObject jsonData = JSON.parseObject(data.substring(1, data.length() - 1));
							String traces = jsonData.getString("traces");
							JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class,
									Traces.class);
							List<Traces> list = MAPPER.readValue(traces, javaType);

							for (Traces traces2 : list) {
								Steps steps = new Steps();
								steps.setAcceptTime(traces2.getScanDate());
								steps.setRemark(traces2.getDesc());
								stepss.add(steps);
							}

							Collections.reverse(stepss);
							if (list.size() > 0) {
								//获取最新的轨迹信息
								String finalScanType = list.get(0).getScanType();
								if ("签收".equals(finalScanType) ||
										"SIGNED".equals(finalScanType) ||
										"第三方签收".equals(finalScanType) ||
										"退件签收".equals(finalScanType)) {
									//最终签收,存入数据库
									String string = MAPPER.writeValueAsString(stepss);
									soDeliveryFacade.expressSignIn(soPackageDTO.getSoId(),string, soPackageDTO.getId(),
											soPackageDTO.getSoChildId());
								}
							}
						}

						stepsList = stepss;
					} else if (shipCompanyCode.equals("old-DEPPON") || shipCompanyCode.equals("old-DBL")) {
						String json = DepponTimelyQuery.getOrderTraces(shipCompanyCode, soPackageDTO.getDeliveryCode());
						JSONObject createJsonObject = JSON.parseObject(json.toString());
						String responseParam = createJsonObject.getString("responseParam");
						JSONObject jsonResponseParam = JSON.parseObject(responseParam);
						String orders = jsonResponseParam.getString("orders");
						JSONObject jsonOroduct = JSON.parseObject(orders.substring(1, orders.length() - 1));
						String steps = jsonOroduct.getString("steps");
						if (steps == null) {
							throw new BusinessException("该单号暂无物流进展，请稍后再试，或检查公司和单号是否有误。");
						}

						JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class,
								Steps.class);
						stepsList = MAPPER.readValue(steps, javaType);
						//反转
						Collections.reverse(stepsList);
						if (stepsList != null) {
							for (Steps steps2 : stepsList) {
								Locale aLocale = Locale.getDefault();
								DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", aLocale);
								Date date = df.parse(steps2.getAcceptTime());
								DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								steps2.setAcceptTime(df2.format(date));
							}

							if ("SIGNSUCCESS".equals(jsonOroduct.getString("orderStatus"))) {
								String string = MAPPER.writeValueAsString(stepsList);
								soDeliveryFacade.expressSignIn(soPackageDTO.getSoId(),string, soPackageDTO.getId(),
										soPackageDTO.getSoChildId());
							}
						}
					} else {
						String name = null;
						if(EmptyUtil.isNotEmpty(soPackageDTO.getGoodReceiverMobile()) && soPackageDTO.getGoodReceiverMobile().length()>4) {
							name = soPackageDTO.getGoodReceiverMobile().substring(soPackageDTO.getGoodReceiverMobile().length() - 4);
						}
						String json = KdniaoTrackQueryAPI.getOrderTracesByJson(shipCompanyCode, soPackageDTO.getDeliveryCode(),name);
						List<Steps> stepss = new ArrayList<Steps>();
						JSONObject createJsonObject = JSON.parseObject(json.toString());
						String state = createJsonObject.getString("State");
						if (EmptyUtil.isNotBlank(state)) {
							/*
							 * 有明确结果 2-在途中,3-签收,4-问题件 参考文档 http://www.kdniao.com/api-track
							 */
							JSONArray traces = createJsonObject.getJSONArray("Traces");
							for (int i = 0; i < traces.size(); i++) {
								JSONObject jobj = traces.getJSONObject(i);
								Steps steps = new Steps();
								steps.setAcceptTime(jobj.getString("AcceptTime"));
								steps.setRemark(jobj.getString("AcceptStation"));
								stepss.add(steps);
							}
							//反转
							Collections.reverse(stepss);
							if ("3".equals(state)) {
								// 对于已签收的,直接存进数据库
								String string = MAPPER.writeValueAsString(stepss);
								soDeliveryFacade.expressSignIn(soPackageDTO.getSoId(),string, soPackageDTO.getId(),
										soPackageDTO.getSoChildId());
							}
						}
						stepsList = stepss;
					}

				} /*else {
					throw new BusinessException("找不到该公司名称对应的公司编号");
				}*/

			}


			return stepsList;
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}


	}

	@Override
	public List<Steps> openDeliveryTrace(String waybillNum, String shipCompanyCode) {
		logger.info("物流单号:"+waybillNum+",公司:"+shipCompanyCode);
		List<Steps> stepsList = null;
		if (EmptyUtil.isEmpty(waybillNum)){
			return stepsList;
		}
		// 根据物流单号查询包裹信息
		List<SoPackageDTO> soPackageList = soDeliveryFacade.packageByWaybillNum(waybillNum);
		/*
		 * 德邦,中通,快递鸟的运单轨迹返回均为从早到晚的顺序
		 * 对于未签收的订单
		 * 查询出结果后,如果是已签收的单,反转后保存轨迹并返回至客户端
		 * 查询出结果后,如果是未签收的订单,则反转后返回至客户端
		 * 对于轨迹已被保存(已签收)的运单
		 * 从数据库中查询数据,返回至客户端
		 */
		try {
			if (soPackageList.size() > 0) {
				SoPackageDTO soPackageDTO = soPackageList.get(0);
				if (soPackageDTO.getDeliveryMessage() != null && !soPackageDTO.getDeliveryMessage().equals("")) {
					JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, Steps.class);
					List<Steps> list = MAPPER.readValue(soPackageDTO.getDeliveryMessage(), javaType);
					return list;
				} else {
					if(EmptyUtil.isNotEmpty(soPackageDTO.getMerchantId()) && Long.valueOf(6L).equals(soPackageDTO.getMerchantId())){
						List<Steps> stepsJDList = getJdSteps(soPackageDTO);
						jdExpressTime(soPackageDTO, stepsJDList);
						return stepsJDList;
					}

					/*if(Long.valueOf(7L).equals(soPackageDTO.getMerchantId())){
						return getCakeSteps(soPackageDTO);
					}*/

					if (EmptyUtil.isNotEmpty(shipCompanyCode)) {
						// 大写转化处理
						shipCompanyCode = shipCompanyCode.toUpperCase();
						if ("old-SF".equals(shipCompanyCode)) {
							stepsList = new ArrayList<>();
							List<Map<String, String>> sfRsp = SfDeliveryQueryUtil.queryByTrackingNumber(waybillNum);
							if (EmptyUtil.isNotEmpty(sfRsp)) {
								for (Map<String, String> s : sfRsp) {
									Steps step = new Steps(s.get("acceptTime"), s.get("remark"));
									stepsList.add(step);
								}
								if((soPackageDTO.getDeliveryStatus()!=null && soPackageDTO.getDeliveryStatus()<3)) {

									String string = MAPPER.writeValueAsString(stepsList);
									if (string.contains("签收人")) {
										soDeliveryFacade.expressSignIn(soPackageDTO.getSoId(),string, soPackageDTO.getId(),
												soPackageDTO.getSoChildId());
									}
								}
							}
						} else if (shipCompanyCode.equals("old-ZTO")) {
							// 备注:中通快递走快递鸟
							List<Steps> stepss = new ArrayList<Steps>();
							String json = ZTOTimelyQuery.getOrderTraces(shipCompanyCode, waybillNum);
							JSONObject createJsonObject = JSON.parseObject(json.toString());
							if (createJsonObject.getString("msg").equals("")) {
								String data = createJsonObject.getString("data");
								JSONObject jsonData = JSON.parseObject(data.substring(1, data.length() - 1));
								String traces = jsonData.getString("traces");
								JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class,
										Traces.class);
								List<Traces> list = MAPPER.readValue(traces, javaType);

								for (Traces traces2 : list) {
									Steps steps = new Steps();
									steps.setAcceptTime(traces2.getScanDate());
									steps.setRemark(traces2.getDesc());
									stepss.add(steps);
								}

								Collections.reverse(stepss);
								if (list.size() > 0) {
									//获取最新的轨迹信息
									String finalScanType = list.get(0).getScanType();
									if ("签收".equals(finalScanType) ||
											"SIGNED".equals(finalScanType) ||
											"第三方签收".equals(finalScanType) ||
											"退件签收".equals(finalScanType)) {
										if((soPackageDTO.getDeliveryStatus()!=null && soPackageDTO.getDeliveryStatus()<3)) {

											//最终签收,存入数据库
											String string = MAPPER.writeValueAsString(stepss);
											soDeliveryFacade.expressSignIn(soPackageDTO.getSoId(),string, soPackageDTO.getId(),
													soPackageDTO.getSoChildId());
										}
									}
								}
							}

							stepsList = stepss;
						} else if (shipCompanyCode.equals("old-DEPPON") || shipCompanyCode.equals("old-DBL")) {
							String json = DepponTimelyQuery.getOrderTraces(shipCompanyCode, waybillNum);
							JSONObject createJsonObject = JSON.parseObject(json.toString());
							String responseParam = createJsonObject.getString("responseParam");
							JSONObject jsonResponseParam = JSON.parseObject(responseParam);
							String orders = jsonResponseParam.getString("orders");
							JSONObject jsonOroduct = JSON.parseObject(orders.substring(1, orders.length() - 1));
							String steps = jsonOroduct.getString("steps");
							if (steps == null) {
								throw new BusinessException("该单号暂无物流进展，请稍后再试，或检查公司和单号是否有误。");
							}

							JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class,
									Steps.class);
							stepsList = MAPPER.readValue(steps, javaType);
							//反转
							Collections.reverse(stepsList);
							if (stepsList != null) {
								for (Steps steps2 : stepsList) {
									Locale aLocale = Locale.getDefault();
									DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", aLocale);
									Date date = df.parse(steps2.getAcceptTime());
									DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
									steps2.setAcceptTime(df2.format(date));
								}

								if ("SIGNSUCCESS".equals(jsonOroduct.getString("orderStatus"))) {
									if((soPackageDTO.getDeliveryStatus()!=null && soPackageDTO.getDeliveryStatus()<3)) {

										String string = MAPPER.writeValueAsString(stepsList);
										soDeliveryFacade.expressSignIn(soPackageDTO.getSoId(),string, soPackageDTO.getId(),
												soPackageDTO.getSoChildId());
									}
								}
							}
						} else {
							String name = null;
							if(EmptyUtil.isNotEmpty(soPackageDTO.getGoodReceiverMobile()) && soPackageDTO.getGoodReceiverMobile().length()>4) {
								name = soPackageDTO.getGoodReceiverMobile().substring(soPackageDTO.getGoodReceiverMobile().length() - 4);
							}
							String json = KdniaoTrackQueryAPI.getOrderTracesByJson(shipCompanyCode, waybillNum,name);
							List<Steps> stepss = new ArrayList<Steps>();
							JSONObject createJsonObject = JSON.parseObject(json.toString());
							String state = createJsonObject.getString("State");
							if (EmptyUtil.isNotBlank(state)) {
								/*
								 * 有明确结果 2-在途中,3-签收,4-问题件 参考文档 http://www.kdniao.com/api-track
								 */
								JSONArray traces = createJsonObject.getJSONArray("Traces");
								for (int i = 0; i < traces.size(); i++) {
									JSONObject jobj = traces.getJSONObject(i);
									Steps steps = new Steps();
									steps.setAcceptTime(jobj.getString("AcceptTime"));
									steps.setRemark(jobj.getString("AcceptStation"));
									stepss.add(steps);
								}
								//反转
								Collections.reverse(stepss);
								if ("3".equals(state)) {
									if((soPackageDTO.getDeliveryStatus()!=null && soPackageDTO.getDeliveryStatus()<3)) {
										// 对于已签收的,直接存进数据库
										String string = MAPPER.writeValueAsString(stepss);
										soDeliveryFacade.expressSignIn(soPackageDTO.getSoId(),string, soPackageDTO.getId(),
												soPackageDTO.getSoChildId());
									}
								}
								if ("2".equals(state)) {
									// 对于在途的,直接存进数据库
									if((soPackageDTO.getDeliveryStatus()!=null && soPackageDTO.getDeliveryStatus()<2)) {
										soDeliveryFacade.expressInway(soPackageDTO.getSoId(), soPackageDTO.getId(),soPackageDTO.getSoChildId());
									}
								}
							}
							stepsList = stepss;
						}

					} /*else {
						throw new BusinessException("找不到该公司名称对应的公司编号");
					}*/

				}

			}
			return stepsList;
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}

	}

	private void jdExpressTime(SoPackageDTO soPackageDTO, List<Steps> stepsJDList) {
		try {
			if(EmptyUtil.isNotEmpty(soPackageDTO.getDeliveryDate())){
				return;
			}
			if(!CollectionUtils.isEmpty(stepsJDList)){
				Steps steps = stepsJDList.get(0);
				if(EmptyUtil.isEmpty(steps.getAcceptTime())){
					return;
				}
				soDeliveryFacade.expressTimeWithTx(soPackageDTO.getSoId(), soPackageDTO.getId(), soPackageDTO.getSoChildId(),steps.getAcceptTime());
			}
		}catch (Exception e){
			logger.error("packageId:{},更新京东的物流发货时间失败，发生异常:{}",soPackageDTO.getId(),e);
		}

	}

	/**
	 * 物流路径
	 * @param soPackageDTO
	 * @return
	 */
	private List<Steps> getJdSteps(SoPackageDTO soPackageDTO) {
		logger.info("京东物流:"+JSON.toJSONString(soPackageDTO));
		List<Steps> result=new ArrayList<>();
		Long soChildId = soPackageDTO.getSoChildId();
		SoChildDTO soChildById = soFacade.findSoChildById(soChildId);
		Long thirdpartySoChildId = soChildById.getThirdpartySoChildId();
		//String token = jdUtils.getAccessToken(jedisUtil);
		String token = jdUtils2.getAccessToken(jedisUtil);
		JSONObject jdOrderDelivery = jdUtils.getJdOrderDelivery(token, thirdpartySoChildId + "");
		if(EmptyUtil.isNotEmpty(jdOrderDelivery)){
			JSONArray orderTrack = jdOrderDelivery.getJSONArray("orderTrack");
			if(EmptyUtil.isNotEmpty(orderTrack)){
				for(Object ob:orderTrack){
					Steps step=new Steps();
					JSONObject json=(JSONObject)ob;
					String content = json.getString("content");
					String msgTime = json.getString("msgTime");
					step.setAcceptTime(msgTime);
					step.setRemark(content);
					result.add(step);
				}

			}
		}
		return result;
	}



	/**
	 * 物流路径
	 * @param soPackageDTO
	 * @return
	 */
	private List<Steps> getCakeSteps(SoPackageDTO soPackageDTO) {
		logger.info("蛋糕叔叔物流:"+JSON.toJSONString(soPackageDTO));
		List<Steps> result=new ArrayList<>();
		Long soChildId = soPackageDTO.getSoChildId();
		SoChildDTO soChildById = soFacade.findSoChildById(soChildId);
		JSONObject jsonObject = cakeUtil.orderDetail(null,soChildById.getChildCode());
		JsonResult jsonResult = cakeUtil.checkResult(jsonObject);
		if(Objects.isNull(jsonResult)){
			String express_note = jsonObject.getString("express_note");
			Steps step=new Steps();
			//step.setAcceptTime(msgTime);
			step.setRemark(express_note);
			result.add(step);
		}
		return result;
	}

	@Override
	public JsonResult<Map<String, Object>> queryWebOpenDeliveryTrace(String childCode) {
		if (EmptyUtil.isEmpty(childCode))
			return JsonResult.fail("子订单编号不能为空");
		Map<String, Object> map = new HashMap<>();
		SoChildDTO soChild = soFacade.querySoChildByChildCode(childCode);
		if (soChild == null)
			return JsonResult.fail("子订单编号错误");

		List<SoPackageDTO> soPackage = soPackageFacade.queryPackageBySoChildId(soChild.getId());
		List<Steps> stepsList = null;
		String deliveryCompanyName = null;
		String waybillNum = null;
		if (soPackage != null && soPackage.size()>0) {
			DeliveryCompanyDTO deliveryCompany=deliveryCompanyFacade.findDeliveryCompanyById(soPackage.get(0).getDeliveryCompanyId());
			SoPackageDTO soPackageDTO = soPackage.get(0);
			waybillNum = soPackageDTO.getDeliveryCode();

			// 查询物流轨迹信息
			if (deliveryCompany != null) {

				deliveryCompanyName = deliveryCompany.getName();
				if(EmptyUtil.isNotEmpty(soChild.getSource()) && Objects.equals(soChild.getSource(), ThirdConst.Source.CAKE) && EmptyUtil.isNotEmpty(soPackageDTO.getDeliveryMode()) && soPackageDTO.getDeliveryMode() ==2){
					//蛋糕叔叔商家配送就不查物流信息了
				}else{
					stepsList = openDeliveryTrace(soPackage.get(0).getDeliveryCode(),deliveryCompany.getCoding());
				}

			}else if(EmptyUtil.isNotEmpty(soChild.getSource()) && Objects.equals(soChild.getSource(), ThirdConst.Source.CAKE) && EmptyUtil.isNotEmpty(soPackageDTO.getDeliveryMode()) && soPackageDTO.getDeliveryMode() ==2){
				deliveryCompanyName = soPackageDTO.getDeliveryName();
			}
		}
		map.put("deliveryStatus", soChild.getDeliveryStatus());
		map.put("deliveryCompanyName", deliveryCompanyName);
		map.put("stepsList", stepsList);
		map.put("waybillNum",waybillNum);

		return JsonResult.success(map);
	}
}
