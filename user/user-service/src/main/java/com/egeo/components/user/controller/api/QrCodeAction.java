package com.egeo.components.user.controller.api;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.user.vo.QrCodeVO;
import com.egeo.components.user.business.QrCodeManage;
import com.egeo.components.user.converter.QrCodeConverter;
import com.egeo.components.user.dto.QrCodeDTO;
import com.egeo.orm.PageResult;
import com.egeo.exception.BusinessException;
import com.egeo.orm.Pagination;
import com.egeo.utils.StringUtils;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/user/qrCode")
public class QrCodeAction extends BaseSpringController {
	
	@Resource(name="qrCode")
	private QrCodeManage qrCodeManage;


	// 业务方法：
	@RequestMapping(value = "/findQrCodeById")
	@ResponseBody
	public JsonResult<QrCodeVO> findQrCodeById(Long id ) {
		
		QrCodeDTO dto = new QrCodeDTO();
		dto.setId(id);
		QrCodeDTO rt = qrCodeManage.findQrCodeById(dto);		
		return JsonResult.success(QrCodeConverter.toVO(rt));
					 
	}



	@RequestMapping(value = "/findQrCodeAll")
	@ResponseBody
	public JsonResult<List<QrCodeVO>> findQrCodeAll(QrCodeVO vo,HttpServletRequest req ) {
		QrCodeDTO dto = QrCodeConverter.toDTO(vo);
		List<QrCodeDTO> rt = qrCodeManage.findQrCodeAll(dto);	
		return JsonResult.success(QrCodeConverter.toVO(rt));
					 
	}	
	/**
	 * 验证参数的有效性
	 * @param vo
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/qrCodeVerifyValid")
	@ResponseBody
	public JsonResult<Boolean> qrCodeVerifyValid(QrCodeVO vo,HttpServletRequest req ) {
		logger.info("*******验证二维码是否有效接口入口*****");
		logger.info("获取参数:"+vo.getCampaignCode());
		logger.info("获取参数:"+vo.getRdid());
		logger.info("参数:"+vo.getTypeId());
		String str=req.getHeader("platformId");
		if(StringUtils.isEmpty(str)){
			return JsonResult.fail("platformId不能为空");
		}
		Long platform = Long.valueOf(str);
		if(StringUtils.isEmpty(vo.getPlatformId())){
			return JsonResult.fail("平台ID不能为空");
		}
		if(!platform.equals(vo.getPlatformId())){
			logger.info("platform111:"+vo.getPlatformId());
			return JsonResult.fail("当前二维码不属于该平台");
		}
		if(StringUtils.isEmpty(vo.getClientId())){
			return JsonResult.fail("客户端ID不能为空");
		}
		if(StringUtils.isEmpty(vo.getChannelId())){
			return JsonResult.fail("渠道ID不能为空");
		}
		if(StringUtils.isEmpty(vo.getCampaignCode())){
			return JsonResult.fail("活动短码不能为空");
		}
		if(StringUtils.isEmpty(vo.getType())){
			return JsonResult.fail("二维码类型不能为空");
		}
		if(StringUtils.isEmpty(vo.getTypeId())){
			return JsonResult.fail("二维码类型对应的对象ID不能为空");
		}
		if(StringUtils.isEmpty(vo.getBranchId())){
			return JsonResult.fail("二维码所属门店ID不能为空");
		}
		if(StringUtils.isEmpty(vo.getRdid())){
			return JsonResult.fail("随机码不能为空");
		}
		QrCodeDTO dto = QrCodeConverter.toDTO(vo);
		Boolean rt = qrCodeManage.qrCodeVerifyValid(dto);	
		return JsonResult.success(rt);
					 
	}	
	/**
	 * 根据渠道id和活动id查询rdid
	 * @param type 类型：1、门店 2、商品 3、优惠卷,4:商品购物车二维码,5:商品订单二维码
	 * @param typeId 类型为商品则是商品id 门店为门店id 优惠卷为优惠卷id,4,5时传递puid
	 * @param storeId
	 * @param channelId
	 * @param campaignCode
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/findRdidByChannelIdCampaignId")
	@ResponseBody
	public JsonResult<Map<String, Object>> findRdidByChannelIdCampaignId(
			Integer type,Integer typeId,Long storeId, Long channelId, String campaignCode,HttpServletRequest req) {
		String str = req.getHeader("platformId");	
		Long platformId = null;
		if(StringUtils.isNotEmpty(str)){
			platformId = Long.valueOf(str);
		}else{
			throw new BusinessException("平台id不能为空");
		}
		if(StringUtils.isEmpty(type)){
			throw new BusinessException("类型不能为空");
		}
		if(StringUtils.isEmpty(typeId)){
			throw new BusinessException("类型Id不能为空");
		}
		if(StringUtils.isEmpty(storeId)){
			throw new BusinessException("门店Id不能为空");
		}
		if(StringUtils.isEmpty(channelId)){
			throw new BusinessException("渠道id不能为空");
		}
		if(StringUtils.isEmpty(campaignCode)){
			throw new BusinessException("活动短码不能为空");
		}
		String rdid = qrCodeManage.findRdidByChannelIdCampaignId(type,typeId,storeId,channelId, campaignCode,platformId);
		Map<String, Object> data = new HashMap<>();
		data.put("rdid", rdid);
		return JsonResult.success(data);
	}

	/**
	 * 根据渠道id和活动id更新rdid
	 * @param channelId
	 * @param campaignId
	 * @return
	 */
	@RequestMapping(value = "/updateRdidByChannelIdCampaignId")
	@ResponseBody
	public JsonResult<Map<String, Object>> updateRdidByChannelIdCampaignId(
			Integer type,Integer typeId,Long storeId, Long channelId, String campaignCode,HttpServletRequest req) {
		String str = req.getHeader("platformId");	
		Long platformId = null;
		if(StringUtils.isNotEmpty(str)){
			platformId = Long.valueOf(str);
		}else{
			throw new BusinessException("平台id不能为空");
		}
		if(StringUtils.isEmpty(channelId)){
			throw new BusinessException("渠道id不能为空");
		}
		if(StringUtils.isEmpty(campaignCode)){
			throw new BusinessException("活动短码不能为空");
		}
		Long rdid = qrCodeManage.updateRdidByChannelIdCampaignId(type,typeId,storeId,channelId, campaignCode,platformId);
		Map<String, Object> data = new HashMap<>();
		data.put("rdid", rdid);
		return JsonResult.success(data);
	}	
	
	// 业务方法：
	@RequestMapping(value = "/findQrCodeOfPage")
	@ResponseBody
	public JsonResult<PageResult<QrCodeVO>> findQrCodeOfPage(QrCodeVO vo,Pagination page,HttpServletRequest req ) {
		QrCodeDTO dto = QrCodeConverter.toDTO(vo);
		PageResult<QrCodeDTO> rt = qrCodeManage.findQrCodeOfPage(dto, page);
        List<QrCodeVO> list = QrCodeConverter.toVO(rt.getList());
        PageResult<QrCodeVO> result = new PageResult<QrCodeVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertQrCodeWithTx")
	@ResponseBody
	public JsonResult<Long> insertQrCodeWithTx(QrCodeVO vo,HttpServletRequest req ) {
		QrCodeDTO dto = QrCodeConverter.toDTO(vo);
		Long rt = qrCodeManage.insertQrCodeWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateQrCodeByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateQrCodeByIdWithTx(QrCodeVO vo,HttpServletRequest req ) {
		QrCodeDTO dto = QrCodeConverter.toDTO(vo);
		int rt = qrCodeManage.updateQrCodeWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteQrCodeWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteQrCodeWithTx(QrCodeVO vo,HttpServletRequest req ) {
		QrCodeDTO dto = QrCodeConverter.toDTO(vo);
		int rt = qrCodeManage.deleteQrCodeWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	