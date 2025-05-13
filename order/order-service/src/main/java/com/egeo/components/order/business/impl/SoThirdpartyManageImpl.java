package com.egeo.components.order.business.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.order.business.SoThirdpartyManage;
import com.egeo.components.order.common.DateUtils;
import com.egeo.components.order.dto.SoChildDTO;
import com.egeo.components.order.dto.SoThirdpartyDTO;
import com.egeo.components.order.facade.SoThirdpartyFacade;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.JsonResult;

@Service("soThirdparty")
public class SoThirdpartyManageImpl implements SoThirdpartyManage{

	// 第三方订单类型
	public static Integer THIRDPARTY_TYPE_NONE = 0;	// 非第三方订单
	
	public static Integer THIRDPARTY_TYPE_PHONE = 1;
	public static Integer THIRDPARTY_TYPE_QC = 2;
	public static Integer THIRDPARTY_TYPE_JD = 3;
	public static Integer THIRDPARTY_TYPE_CAKE = 4;
	public static Integer THIRDPARTY_TYPE_WORLD = 5;

	public static String THIRDPARTY_TYPE_QC_STR = "券仓卡券";
	public static String THIRDPARTY_TYPE_PHONE_STR = "话费充值";
	public static String THIRDPARTY_TYPE_JD_STR = "京东商品";
	public static String THIRDPARTY_TYPE_QM_STR = "清美商品";


	// 第三方订单状态
	public static Integer THIRDPARTY_STATUS_NEW = -1;	// 0:新增状态(未支付)
	
	public static Integer THIRDPARTY_STATUS_HANDLING = 0;
	public static String THIRDPARTY_STATUS_HANDLING_STR = "处理中";
	
	public static Integer THIRDPARTY_STATUS_SUCCESS = 1;
	public static String THIRDPARTY_STATUS_SUCCESS_STR = "成功";
	
	public static Integer THIRDPARTY_STATUS_FAILED = 2;
	public static String THIRDPARTY_STATUS_FAILED_STR = "失败";
	
	@Resource(name="soThirdpartyFacade")
	private SoThirdpartyFacade soThirdpartyFacade;

	@Override
	public SoThirdpartyDTO findSoThirdpartyById(SoThirdpartyDTO dto) {
		return soThirdpartyFacade.findSoThirdpartyById(dto);
	}

	@Override
	public PageResult<SoThirdpartyDTO> findSoThirdpartyOfPage(SoThirdpartyDTO dto, Pagination page) {
		return soThirdpartyFacade.findSoThirdpartyOfPage(dto, page);
	}

	@Override
	public List<SoThirdpartyDTO> findSoThirdpartyAll(SoThirdpartyDTO dto) {
		return soThirdpartyFacade.findSoThirdpartyAll(dto);
	}

	@Override
	public Long insertSoThirdpartyWithTx(SoThirdpartyDTO dto) {
		return soThirdpartyFacade.insertSoThirdpartyWithTx(dto);
	}

	@Override
	public int updateSoThirdpartyWithTx(SoThirdpartyDTO dto) {
		return soThirdpartyFacade.updateSoThirdpartyWithTx(dto);
	}

	@Override
	public int deleteSoThirdpartyWithTx(SoThirdpartyDTO dto) {
		return soThirdpartyFacade.deleteSoThirdpartyWithTx(dto);
	}
	
	 @Override
     public JsonResult<List<Map<String,Object>>> findSoThirdpartyBySoId(Long soId) {
           if (EmptyUtil.isEmpty(soId))
                return JsonResult.fail("订单id不能为空");
           
           // 通过订单id查询子订单信息
           List<SoChildDTO> SoChildList = soThirdpartyFacade.querySoChildBySoId(soId);
           
           List<Map<String,Object>> rtList = new ArrayList<Map<String,Object>>();
		 Long jdSoChildIdOld=0L;
		 for(SoChildDTO soChild : SoChildList){
		 	if(soChild.getThirdpartyType()==SoThirdpartyManageImpl.THIRDPARTY_TYPE_JD&&EmptyUtil.isEmpty(soChild.getOrdinaryId())){
		 		//获取到京东子订单原始子订单单号
				jdSoChildIdOld = soChild.getId();
			}
		 }
		 for (SoChildDTO soChild : SoChildList) {
                // 通过子订单类型和id 查询第三方订单信息
                if (soChild.getThirdpartyType() != SoThirdpartyManageImpl.THIRDPARTY_TYPE_NONE&&soChild.getThirdpartyType() !=SoThirdpartyManageImpl.THIRDPARTY_TYPE_JD) {
                     SoThirdpartyDTO dto = new SoThirdpartyDTO();
                     dto.setSoChildId(soChild.getId());
                     List<SoThirdpartyDTO> soThirdpartyList = soThirdpartyFacade.findSoThirdpartyAll(dto);
                     
                     // 一个子订单对应一个第三方订单
                     if (EmptyUtil.isNotEmpty(soThirdpartyList)) {
                           Map<String,Object> map = new HashMap<String,Object>();
                           // 设置第三方订单类型
                           if (soChild.getThirdpartyType() == SoThirdpartyManageImpl.THIRDPARTY_TYPE_PHONE) {
                                map.put("thirdpartyType", SoThirdpartyManageImpl.THIRDPARTY_TYPE_PHONE_STR);
                           }else if(soChild.getThirdpartyType() ==SoThirdpartyManageImpl.THIRDPARTY_TYPE_QC){
							   map.put("thirdpartyType", SoThirdpartyManageImpl.THIRDPARTY_TYPE_QC_STR);

						   }
                           // 设置第三方订单状态
                           if (soThirdpartyList.get(0).getThirdpartyStatus() == SoThirdpartyManageImpl.THIRDPARTY_STATUS_HANDLING) {
                        	   map.put("thirdpartyStatus", SoThirdpartyManageImpl.THIRDPARTY_STATUS_HANDLING_STR);
                           } else if (soThirdpartyList.get(0).getThirdpartyStatus() == SoThirdpartyManageImpl.THIRDPARTY_STATUS_SUCCESS) {
                        	   map.put("thirdpartyStatus", SoThirdpartyManageImpl.THIRDPARTY_STATUS_SUCCESS_STR);
                           } else if (soThirdpartyList.get(0).getThirdpartyStatus() == SoThirdpartyManageImpl.THIRDPARTY_STATUS_FAILED) {
                        	   map.put("thirdpartyStatus", SoThirdpartyManageImpl.THIRDPARTY_STATUS_FAILED_STR);
                           }
                           
                           map.put("id", soThirdpartyList.get(0).getId());
                           map.put("thirdpartyId", soThirdpartyList.get(0).getThirdpartyId());
                           map.put("childCode", soChild.getChildCode());
                           map.put("updateTime", DateUtils.format(DateUtils.DATE_TIME_FORMAT, soThirdpartyList.get(0).getUpdateTime()));
                           map.put("userName", soThirdpartyList.get(0).getUserName());
                           map.put("idCardNum", soThirdpartyList.get(0).getIdCardNum());
                           map.put("phone", soThirdpartyList.get(0).getPhone());
                           map.put("oidCardNum", soThirdpartyList.get(0).getOidCardNum());
                           map.put("comment", soThirdpartyList.get(0).getComment());
                           
                           if (!soThirdpartyList.get(0).getThirdpartyStatus().equals(THIRDPARTY_STATUS_NEW)) 
                        	   // 非新增状态的第三方订单
                        	   rtList.add(map);
                     }
                }else if(soChild.getThirdpartyType() ==SoThirdpartyManageImpl.THIRDPARTY_TYPE_JD){
					SoThirdpartyDTO dto = new SoThirdpartyDTO();
					dto.setSoChildId(jdSoChildIdOld);
					List<SoThirdpartyDTO> soThirdpartyList = soThirdpartyFacade.findSoThirdpartyAll(dto);

					// 京东子订单会是多个子订单对应一个第三方订单
					if (EmptyUtil.isNotEmpty(soThirdpartyList)) {
						Map<String,Object> map = new HashMap<String,Object>();
						// 设置第三方订单类型
						if (soChild.getThirdpartyType() == SoThirdpartyManageImpl.THIRDPARTY_TYPE_JD) {
							map.put("thirdpartyType", SoThirdpartyManageImpl.THIRDPARTY_TYPE_JD_STR);
						}

						// 设置第三方订单状态
						map.put("thirdpartyStatus", soThirdpartyList.get(0).getThirdpartyStatus());


						map.put("id", soThirdpartyList.get(0).getId());
						map.put("thirdpartyId", soChild.getThirdpartySoChildId());
						map.put("childCode", soChild.getChildCode());
						map.put("updateTime", DateUtils.format(DateUtils.DATE_TIME_FORMAT, soThirdpartyList.get(0).getUpdateTime()));
						map.put("userName", soThirdpartyList.get(0).getUserName());
						map.put("idCardNum", soThirdpartyList.get(0).getIdCardNum());
						map.put("phone", soThirdpartyList.get(0).getPhone());
						map.put("oidCardNum", soThirdpartyList.get(0).getOidCardNum());
						map.put("comment", soThirdpartyList.get(0).getComment());

						if (!soThirdpartyList.get(0).getThirdpartyStatus().equals(THIRDPARTY_STATUS_NEW))
							// 非新增状态的第三方订单
							rtList.add(map);
					}

				}
           }
           
           return JsonResult.success(rtList);
     }


}
	