package com.egeo.components.order.controller.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.egeo.common.LogConstant;
import com.egeo.common.LogTypeConstant;
import com.egeo.components.order.business.SoPackageManage;
import com.egeo.components.order.converter.SoPackageConverter;
import com.egeo.components.order.dto.SoPackageBoxDTO;
import com.egeo.components.order.dto.SoPackageDTO;
import com.egeo.components.order.vo.SoPackageVO;
import com.egeo.components.order.vo.SoPackageView;
import com.egeo.entity.CacheUser;
import com.egeo.log.EgeoBusinessLogCommon;
import com.egeo.log.EgeoLog;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.ActiveMQUtils;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.excel2.ExcelUtil;
import com.egeo.utils.str.StringUtils;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/api/order/soPackage")
public class SoPackageAction extends BaseSpringController {

	// 定义jackson对象
	private static final ObjectMapper MAPPER = new ObjectMapper();

	@Resource(name = "soPackage")
	private SoPackageManage soPackageManage;

	// 业务方法：
	@RequestMapping(value = "/findSoPackageById")
	@ResponseBody
	public JsonResult<SoPackageVO> findSoPackageById(Long id) {

		SoPackageDTO dto = new SoPackageDTO();
		dto.setId(id);
		SoPackageDTO rt = soPackageManage.findSoPackageById(dto);
		return JsonResult.success(SoPackageConverter.toVO(rt));

	}

	// 业务方法：
	@RequestMapping(value = "/findSoPackageAll")
	@ResponseBody
	public JsonResult<List<SoPackageVO>> findSoPackageAll(SoPackageVO vo, HttpServletRequest req) {
		SoPackageDTO dto = SoPackageConverter.toDTO(vo);
		String str = req.getHeader("platformId");
		if (EmptyUtil.isNotEmpty(str)) {
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<SoPackageDTO> rt = soPackageManage.findSoPackageAll(dto);
		return JsonResult.success(SoPackageConverter.toVO(rt));

	}

	// 业务方法：
	@RequestMapping(value = "/findSoPackageOfPage")
	@ResponseBody
	public JsonResult<PageResult<SoPackageVO>> findSoPackageOfPage(SoPackageVO vo, Pagination page,
			HttpServletRequest req) {
		SoPackageDTO dto = SoPackageConverter.toDTO(vo);
		String str = req.getHeader("platformId");
		if (EmptyUtil.isNotEmpty(str)) {
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<SoPackageDTO> rt = soPackageManage.findSoPackageOfPage(dto, page);
		List<SoPackageVO> list = SoPackageConverter.toVO(rt.getList());
		PageResult<SoPackageVO> result = new PageResult<SoPackageVO>();
		result.setList(list);
		result.setPageNo(rt.getPageNo());
		result.setPageSize(rt.getPageSize());
		result.setTotalSize(rt.getTotalSize());

		return JsonResult.success(result);

	}

	/**
	 * 订单物流信息列表
	 *
	 * @param orderId
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/soDeliveryInfoList")
	@ResponseBody
	public JsonResult<Map<String, Object>> soDeliveryInfoList(Long orderId, HttpServletRequest req) {
		return soPackageManage.soDeliveryInfoList(orderId);
	}

	/**
	 * 订单物流信息新增箱号
	 *
	 * @param orderId
	 * @param boxCode
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/addBox")
	@ResponseBody
	public JsonResult<Map<String, Object>> addBox(Long soChildId, Long boxCode, HttpServletRequest req) {


		JsonResult<Map<String, Object>> result = soPackageManage.addBox(soChildId, boxCode);

		if (result.getCode() == 0) {
			// 新增箱号日志记录
			SoPackageBoxDTO soPackageBoxDTO = soPackageManage.querySoPackageBoxByBoxCodeAndChildId(boxCode,soChildId);
			EgeoLog log = new EgeoLog();
			log.setModuleName(LogConstant.ORDER_MANAGEMENT.getComment());
			log.setOperObject("SoPackageAction_addBox");
			log.setMsgId(LogConstant.SOPACKAGE_BOX_NEW.getStatus());
			log.setType(LogTypeConstant.SOPACKAGE_BOX.getStatus());
			log.setOperatorObjId(soPackageBoxDTO.getId());
			log.setOperatorObjCode(soPackageBoxDTO.getSoBoxCode().toString());
			log.setNewObj(soPackageBoxDTO);

			EgeoBusinessLogCommon.fillLogValue(log, req);

			try {
				ActiveMQUtils.recordBusinessLog(log);
			}catch (Exception e) {
				// TODO: handle exception
				logger.error("发送日志消息失败："+ JSON.toJSONString(log));
			}
		}



		return result;
	}

	/**
	 * 分页查询子单运单信息
	 *
	 * @param page
	 * @param req
	 * @return
	 */
	@Deprecated
	@RequestMapping(value = "/findSoPackageAndBoxOfPage")
	@ResponseBody
	public JsonResult<PageResult<SoPackageView>> findSoPackageAndBoxOfPage(Pagination page, HttpServletRequest req) {
		SoPackageDTO dto = new SoPackageDTO();
		String str = req.getHeader("platformId");
		if (EmptyUtil.isNotEmpty(str)) {
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<SoPackageDTO> rt = soPackageManage.findSoPackageAndBoxOfPage(dto, page);
		List<SoPackageView> list = SoPackageConverter.toView(rt.getList());
		PageResult<SoPackageView> result = new PageResult<SoPackageView>();
		result.setList(list);
		result.setPageNo(rt.getPageNo());
		result.setPageSize(rt.getPageSize());
		result.setTotalSize(rt.getTotalSize());

		return JsonResult.success(result);

	}

	// 业务方法：
	@RequestMapping(value = "/insertSoPackageWithTx")
	@ResponseBody
	public JsonResult<Long> insertSoPackageWithTx(SoPackageVO vo, Long soId, HttpServletRequest req) {
		SoPackageDTO dto = SoPackageConverter.toDTO(vo);
		String str = req.getHeader("platformId");
		if (EmptyUtil.isNotEmpty(str)) {
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = soPackageManage.insertSoPackageWithTx(dto, soId);
		return JsonResult.success(rt);
	}

	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateSoPackageByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateSoPackageByIdWithTx(SoPackageVO vo, HttpServletRequest req) {
		SoPackageDTO dto = SoPackageConverter.toDTO(vo);
		String str = req.getHeader("platformId");
		if (EmptyUtil.isNotEmpty(str)) {
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = soPackageManage.updateSoPackageWithTx(dto);
		return JsonResult.success(rt);
	}

	// 业务方法：
	@RequestMapping(value = "/deleteSoPackageWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteSoPackageWithTx(SoPackageVO vo, HttpServletRequest req) {
		SoPackageDTO dto = SoPackageConverter.toDTO(vo);
		String str = req.getHeader("platformId");
		if (EmptyUtil.isNotEmpty(str)) {
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = soPackageManage.deleteSoPackageWithTx(dto);
		return JsonResult.success(rt);
	}

	// 业务方法：根据订单编号查询包裹信息
	@RequestMapping(value = "/SoPackageAllByOrderCode")
	@ResponseBody
	public JsonResult<List<SoPackageVO>> SoPackageAllByOrderCode(SoPackageVO vo, HttpServletRequest req) {
		logger.info("根据订单编号查询包裹信息");
		SoPackageDTO dto = SoPackageConverter.toDTO(vo);
		String str = req.getHeader("platformId");
		if (EmptyUtil.isNotEmpty(str)) {
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<SoPackageVO> rt = soPackageManage.SoPackageAllByOrderCode(dto);
		return JsonResult.success(rt);

	}

	// 业务方法：批量修改包裹信息
	@RequestMapping(value = "/updateSoPackageByIdWithTxAll")
	@ResponseBody
	public JsonResult<String> updateSoPackageByIdWithTxAll(String list, Long soId, HttpServletRequest req) {
		logger.info("批量修改包裹信息");
		Long platformId = null;
		List<SoPackageVO> lists = new ArrayList<SoPackageVO>();
		String str = req.getHeader("platformId");
		if (EmptyUtil.isNotEmpty(str)) {
			platformId = Long.valueOf(str);
		}
		try {
			if (list != null) {
				JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, SoPackageVO.class);
				lists = MAPPER.readValue(list, javaType);
			}
			String rt = soPackageManage.updateSoPackageByIdWithTxAll(lists, soId, platformId);
			return JsonResult.success(rt);
		} catch (Exception e) {
			return JsonResult.fail("批量修改包裹信息异常:" + e.getMessage());
		}
	}

	/**
	 * 根据订单Code查询包裹信息
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/packageBySoId")
	@ResponseBody
	public JsonResult<Map<String,Object>> packageBySoId(String orderCode) {
		logger.info("根据订单id查询包裹信息");
		return soPackageManage.packageBySoId(orderCode);
	}

	/**
	 * 根据子订单订单Code查询包裹信息
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/packageBySoChildId")
	@ResponseBody
	public JsonResult<Map<String,Object>> packageBySoChildId(String soChildCode) {
		logger.info("根据订单id查询包裹信息");
		return soPackageManage.packageBySoChildCode(soChildCode);
	}
	/**
	 * 发货信息导入
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "deliveryImport")
	@ResponseBody
	public JsonResult<Map<String, Object>> deliveryImport(HttpServletRequest req) {
		logger.info("发货信息导入");
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) req;
		String platformId_ = req.getHeader("platformId");
		if (EmptyUtil.isEmpty(platformId_))
			return JsonResult.fail("platformId为空");
		Long platformId = Long.parseLong(platformId_);
		CacheUser userCache = this.getCacheUser();
		Long operatorId = userCache.getId();

		// 从请求体中获取文件
		Iterator<String> iter = multiRequest.getFileNames();
		if(!iter.hasNext()) {
			return JsonResult.fail("请上传文件");
		}
		MultipartFile file = multiRequest.getFile(iter.next());
		if (file == null)
			return JsonResult.fail("未发现Excel文件");

		String originalFilename = file.getOriginalFilename();
		// 获取文件后缀
		String suffix = "";
		suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
		if (!StringUtils.equals(suffix, ".xls") && !StringUtils.equals(suffix, ".xlsx")) {
			return JsonResult.fail("导入的文件类型错误，请选择后重新选择文件导入");
		}

		List<Map<String, Object>> valueList = null;
		try {
			valueList = ExcelUtil.readExcelData(0, 0, file.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
			return JsonResult.fail("Excel文件读取发生异常");
		}

		//JsonResult<Map<String, Object>> result = soPackageManage.deliveryImport(operatorId, platformId, valueList, req);
		JsonResult<Map<String, Object>> result = soPackageManage.deliveryPlatformImport(operatorId, platformId, valueList, req);
		return result;
//		logger.info("签收信息导入");
//		return soPackageManage.signImport(tempType, platformId, valueList);
	}

	@RequestMapping(value = "deliveryImportChild")
	@ResponseBody
	public JsonResult<Map<String, Object>> deliveryImportChild(HttpServletRequest req) {
		logger.info("发货信息导入");
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) req;
		String platformId_ = req.getHeader("platformId");
		if (EmptyUtil.isEmpty(platformId_))
			return JsonResult.fail("platformId为空");
		Long platformId = Long.parseLong(platformId_);
		CacheUser userCache = this.getCacheUser();
		Long operatorId = userCache.getId();

		// 从请求体中获取文件
		Iterator<String> iter = multiRequest.getFileNames();
		if(!iter.hasNext()) {
			return JsonResult.fail("请上传文件");
		}
		MultipartFile file = multiRequest.getFile(iter.next());
		if (file == null)
			return JsonResult.fail("未发现Excel文件");

		String originalFilename = file.getOriginalFilename();
		// 获取文件后缀
		String suffix = "";
		suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
		if (!StringUtils.equals(suffix, ".xls") && !StringUtils.equals(suffix, ".xlsx")) {
			return JsonResult.fail("导入的文件类型错误，请选择后重新选择文件导入");
		}

		List<Map<String, Object>> valueList = null;
		try {
			valueList = ExcelUtil.readExcelData(0, 0, file.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
			return JsonResult.fail("Excel文件读取发生异常");
		}

		JsonResult<Map<String, Object>> result = soPackageManage.deliveryImportChild(operatorId, platformId, valueList, req);

		return result;
//		logger.info("签收信息导入");
//		return soPackageManage.signImport(tempType, platformId, valueList);
	}

	/**
	 * 添加发货信息
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "addDeliveryInfo")
	@ResponseBody
	public JsonResult<Map<String, Object>> addDeliveryInfo(HttpServletRequest req,String childCode,String orderCode,String deliveryCompany,String deliveryCode,Long boxCode) {
		logger.info("添加发货信息");
		String platformId_ = req.getHeader("platformId");
		if (EmptyUtil.isEmpty(platformId_)) {
			return JsonResult.fail("platformId为空");
		}
		Long platformId = Long.parseLong(platformId_);
		CacheUser userCache = this.getCacheUser();
		Long operatorId = userCache.getId();
		if(EmptyUtil.isEmpty(childCode)){
			return JsonResult.fail("childCode为空");
		}
		if(EmptyUtil.isEmpty(orderCode)){
			return JsonResult.fail("orderCode为空");
		}
		if(EmptyUtil.isEmpty(deliveryCompany)){
			return JsonResult.fail("deliveryCompany为空");
		}
		if(EmptyUtil.isEmpty(deliveryCode)){
			return JsonResult.fail("deliveryCode为空");
		}


		JsonResult<Map<String, Object>> result = soPackageManage.addDeliveryInfo(boxCode,orderCode,childCode,deliveryCompany,deliveryCode,operatorId,platformId,false);

		return result;
//		logger.info("签收信息导入");
//		return soPackageManage.signImport(tempType, platformId, valueList);
	}


	@RequestMapping(value = "addDeliveryInfoPlatform")
	@ResponseBody
	public JsonResult<Map<String, Object>> addDeliveryInfoPlatform(HttpServletRequest req,String childCode,String orderCode,String deliveryCompany,String deliveryCode,Long boxCode) {
		logger.info("添加发货信息");
		String platformId_ = req.getHeader("platformId");
		if (EmptyUtil.isEmpty(platformId_)) {
			return JsonResult.fail("platformId为空");
		}
		Long platformId = Long.parseLong(platformId_);
		CacheUser userCache = this.getCacheUser();
		Long operatorId = userCache.getId();
		if(EmptyUtil.isEmpty(childCode)){
			return JsonResult.fail("childCode为空");
		}
		if(EmptyUtil.isEmpty(orderCode)){
			return JsonResult.fail("orderCode为空");
		}
		if(EmptyUtil.isEmpty(deliveryCompany)){
			return JsonResult.fail("deliveryCompany为空");
		}
		if(EmptyUtil.isEmpty(deliveryCode)){
			return JsonResult.fail("deliveryCode为空");
		}


		JsonResult<Map<String, Object>> result = soPackageManage.addDeliveryInfo(boxCode,orderCode,childCode,deliveryCompany,deliveryCode,operatorId,platformId,true);

		return result;
//		logger.info("签收信息导入");
//		return soPackageManage.signImport(tempType, platformId, valueList);
	}

	/**
	 * 确认发货或者签收信息的导入
	 * 该方法已废弃,发货导入是不存在确认过程的
	 * @param templateType
	 * @param req
	 * @param importSoPackageInfo
	 * @param soPackageInfo
	 * @return
	 */
	@Deprecated
	@RequestMapping(value = "/assureImportAboutSoPackage")
	@ResponseBody
	public JsonResult<String> assureImport(String templateType, HttpServletRequest req, String importSoPackageInfo,
			String soPackageInfo) {
		Long platformId;
		String str = req.getHeader("platformId");
		if (EmptyUtil.isNotEmpty(str)) {
			platformId = Long.valueOf(str);
		} else {
			return JsonResult.fail("平台id不能为空");
		}

		Long parseLong = null;

		if (templateType == null) {
			return JsonResult.fail("确定导入类型不能为空");
		}
		if (importSoPackageInfo == null) {
			return JsonResult.fail("导入记录信息为空");
		} else {
			try {
				parseLong = Long.parseLong(importSoPackageInfo);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		// ************************************第二部分
		String[] userIdArr = soPackageInfo.substring(1).split(",");
		Long insertCount = null;
		if (templateType.equals("发货信息导入")) {
			logger.info("确认导入发货信息");
			insertCount = soPackageManage.assureImportDelivery(platformId, parseLong, soPackageInfo);

		}
		if (templateType.equals("签收信息导入")) {
			logger.info("确认导入签收信息");
			insertCount = soPackageManage.assureImportSign(platformId, parseLong, soPackageInfo);

		}

		if (userIdArr.length == insertCount) {
			return JsonResult.success("导入成功！");
		}

		return JsonResult.fail("导入失败");
	}


	@RequestMapping(value = "deliveryImportSoChild")
	@ResponseBody
	public JsonResult<Map<String, Object>> deliveryImportSoChild(HttpServletRequest req) {
		logger.info("发货信息导入");
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) req;
		String platformId_ = req.getHeader("platformId");
		if (EmptyUtil.isEmpty(platformId_))
			return JsonResult.fail("platformId为空");
		Long platformId = Long.parseLong(platformId_);
		CacheUser userCache = this.getCacheUser();
		Long operatorId = userCache.getId();

		// 从请求体中获取文件
		Iterator<String> iter = multiRequest.getFileNames();
		if(!iter.hasNext()) {
			return JsonResult.fail("请上传文件");
		}
		MultipartFile file = multiRequest.getFile(iter.next());
		if (file == null)
			return JsonResult.fail("未发现Excel文件");

		String originalFilename = file.getOriginalFilename();
		// 获取文件后缀
		String suffix = "";
		suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
		if (!StringUtils.equals(suffix, ".xls") && !StringUtils.equals(suffix, ".xlsx")) {
			return JsonResult.fail("导入的文件类型错误，请选择后重新选择文件导入");
		}

		List<Map<String, Object>> valueList = null;
		try {
			valueList = ExcelUtil.readExcelData(0, 0, file.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
			return JsonResult.fail("Excel文件读取发生异常");
		}

		JsonResult<Map<String, Object>> result = soPackageManage.deliveryImportSoChild(operatorId, platformId, valueList, req);

		return result;
	}
}
