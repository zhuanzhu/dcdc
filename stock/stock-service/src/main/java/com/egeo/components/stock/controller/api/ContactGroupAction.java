package com.egeo.components.stock.controller.api;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.stock.business.ContactGroupPuStockManage;
import com.egeo.components.stock.business.ContactGroupStockManage;
import com.egeo.components.stock.converter.ContactGroupStockConverter;
import com.egeo.components.stock.dto.ContactGroupPuStockDTO;
import com.egeo.components.stock.dto.ContactGroupStockDTO;
import com.egeo.components.stock.vo.ContactGroupStockVO;
import com.egeo.entity.CacheUser;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;


@Controller
@RequestMapping("/api/stock/contactGroup")
public class ContactGroupAction extends BaseSpringController {

	@Resource(name = "contactGroupStock")
	private ContactGroupStockManage contactGroupStockManage;

	@Resource(name = "contactGroupPuStock")
	private ContactGroupPuStockManage contactGroupPuStockManage;

	/**
	 * 添加 merchantId; name; spuId;
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/addContactGroup")
	@ResponseBody
	public JsonResult<Long> addContactGroup(ContactGroupStockVO vo,HttpServletRequest req) {

		logger.info("添加关联组start...");
		logger.info("关联组名："+ vo.getName());
		logger.info("关联spuId："+ vo.getSpuId());
		logger.info("运营方id："+ vo.getMerchantId());

		ContactGroupStockDTO contactGroupStockDTO = ContactGroupStockConverter.toDTO(vo);

		//关联组名的定义（唯一排它）
		if (EmptyUtil.isBlank(contactGroupStockDTO.getName())) {
			return JsonResult.fail("请输入关联组名");
		}
		if (nameIsExist(contactGroupStockDTO)) {
			return JsonResult.fail("关联组名重复");
		}
		if ((contactGroupStockDTO.getMerchantId() == null )) {
			return JsonResult.fail("请选择运营方!");
		}
		if (contactGroupStockDTO.getSpuId() == null) {
			return JsonResult.fail("请选择产品！");
		}
		String platformStr = req.getHeader("platformId");
		Long platformId = Long.valueOf(platformStr);
		
		contactGroupStockDTO.setCount(0);
		contactGroupStockDTO.setPlatformId(platformId);
		CacheUser cacheUser = this.getCacheUser();
		logger.info("当前编辑人...：" + cacheUser.getName());
		//新增不用编辑人信息
//		contactGroupStockDTO.setCreateUserId(cacheUser.getId());
//		contactGroupStockDTO.setCreateUserName(cacheUser.getLoginName());
		Long id = contactGroupStockManage.insertContactGroupStockWithTx(contactGroupStockDTO);

		return JsonResult.success(id);

	}

	/**
	 * id 查询关联组
	 */
	@RequestMapping(value = "/findContactGroupById")
	@ResponseBody
	public JsonResult<Map<String, Object>> findContactGroupById(ContactGroupStockVO vo ) {

		logger.info("根据id查询关联组start...");
		logger.info("关联组id"+ vo.getId());

		ContactGroupStockDTO dto = ContactGroupStockConverter.toDTO(vo);
		Map<String, Object> contactGroupById =contactGroupStockManage.findContactGroupById(dto);
		return JsonResult.success(contactGroupById);
	}


	/**
	 * 关联组名重复校验 name;
	 * @param contactGroupStockDTO
	 * @return
	 */
	private boolean nameIsExist(ContactGroupStockDTO contactGroupStockDTO) {
		ContactGroupStockDTO contactDto = new ContactGroupStockDTO();
		contactDto.setName(contactGroupStockDTO.getName());
		List<ContactGroupStockDTO> contactGroupStockDTOList = contactGroupStockManage.findAllByName(contactDto);
		if (EmptyUtil.isEmpty(contactGroupStockDTOList)) {
			return false;
		}else {
			return true;
		}
	}


	/**
	 * id
	 * name
	 * 修改，关联组名 name;
	 */
	@RequestMapping(value = "/updateContactGroup")
	@ResponseBody
	public  JsonResult<Integer> updateContactGroup(ContactGroupStockVO vo) {

		logger.info("修改关联组名start...");
		logger.info("修改关联组id："+ vo.getId());
		logger.info("修改关联组name："+vo.getName());

		ContactGroupStockDTO dto = ContactGroupStockConverter.toDTO(vo);
		//关联组名的定义（唯一排它）
		if (EmptyUtil.isBlank(dto.getName())) {
			return JsonResult.fail("请输入关联组名");
		}
		Map<String, Object> map = contactGroupStockManage.findContactGroupById(dto);
		if(map.get("name") != null && !map.get("name").toString().equals(dto.getName())) {
			if (nameIsExist(dto)) {
				return JsonResult.fail("关联组名重复");
			}
		}
		
		CacheUser cacheUser = this.getCacheUser();
		logger.info("当前编辑人...：" + cacheUser.getLoginName());
		dto.setCreateUserName(cacheUser.getLoginName());
		dto.setCreateUserId(cacheUser.getId());
		int i = contactGroupStockManage.updateContactGroupStockWithTx(dto);
		return JsonResult.success(i);
	}

	/**
	 * 关联组列表
	 * productName;
	 * merchantName;
	 * merchantId；
	 * name;
	 */
	@RequestMapping(value = "/findContactGroupStockMapOfPage")
	@ResponseBody
	public JsonResult<PageResult<Map<String, Object>>> findContactGroupStockMapOfPage(ContactGroupStockVO vo, String productName, 
			String merchantName, Pagination page,HttpServletRequest req ) {

		logger.info("关联组列表start...");
		logger.info("产品名："+ productName);
		logger.info("运营方id："+ vo.getMerchantId());
		logger.info("运营方name："+ merchantName);
		logger.info("关联组名：" + vo.getName());
		String platformIdStr = req.getHeader("platformId");
		Long platformId = Long.valueOf(platformIdStr);
		ContactGroupStockDTO dto = ContactGroupStockConverter.toDTO(vo);
		dto.setPlatformId(platformId);
		PageResult<Map<String, Object>> rt = contactGroupStockManage.findContactGroupStockMapOfPage(dto, productName, merchantName, page);

		return JsonResult.success(rt);
	}

	/**
	 * 根据merchantId 查询运营方下所有su
	 *  id
	 *  merchantId
	 */
	@RequestMapping(value = "/findSuListOfPageByMerchantId")
	@ResponseBody
	public JsonResult<PageResult<Map<String, Object>>> findSuListOfPageByMerchantId(ContactGroupStockVO vo, String merchantName, Pagination page) {

		logger.info("根据merchantId 查询该运营方下所有su...");
		logger.info("关联组id：" + vo.getId());
		logger.info("merchantId：" + vo.getMerchantId());
		
		ContactGroupStockDTO dto = ContactGroupStockConverter.toDTO(vo);

		PageResult<Map<String, Object>> rt = contactGroupStockManage.findSuListOfPageByMerchantId(dto, page);

		return JsonResult.success(rt);
	}


	/**
	 *确认su关联
	 * suId
	 * id:关联组id
	 * name:关联组名
	 */
	@RequestMapping(value = "/confirmContact")
	@ResponseBody
	public JsonResult<Long> confirmContact(Long suId,ContactGroupStockVO vo,HttpServletRequest req) {

		logger.info("关联start...");
		logger.info("关联suId："+ suId);
		logger.info("关联组id："+ vo.getId());
		logger.info("关联组name："+ vo.getName() );

		//能选择的 SU 必须未被选择于其他关联组
		ContactGroupStockDTO dto = ContactGroupStockConverter.toDTO(vo);
		ContactGroupPuStockDTO contactGroupPuStockDTO = new ContactGroupPuStockDTO();
		contactGroupPuStockDTO.setSuId(suId);
		
		List<ContactGroupPuStockDTO> dtos = contactGroupPuStockManage.findContactGroupPuStockAll(contactGroupPuStockDTO);
		
		if(dtos != null && dtos.size() > 0) {
			return JsonResult.fail("选择的 SU 必须未被选择于其他关联组");
		}
		
		String ip = req.getRemoteAddr();
		
		CacheUser user = this.getCacheUser();
		Long i = contactGroupStockManage.confirmContactWithTx(suId, dto,user,ip,null);

		return JsonResult.success(i);
	}

	/**
	 * 取消su关联
	 * suId
	 * id:关联组id
	 *
	 */
	@RequestMapping(value = "/cancelSuContact")
	@ResponseBody
	public JsonResult<Long> cancelSuContact(Long suId,ContactGroupStockVO vo ) {

		logger.info("解散su关联start...");
		logger.info("解散关联组id："+ vo.getId());
		ContactGroupStockDTO dto = ContactGroupStockConverter.toDTO(vo);
		CacheUser user = this.getCacheUser();
		contactGroupStockManage.cancelSuContactWithTx(suId,dto,user);
		return JsonResult.success(suId);

	}


	/**
	 * 解散关联（组）
	 *
	 * id:关联组id
	 *
	 */
	@RequestMapping(value = "/cancelContact")
	@ResponseBody
	public JsonResult<Long> cancelContact(ContactGroupStockVO vo ) {

		logger.info("解散关联start...");
		logger.info("解散关联组id："+ vo.getId());

		ContactGroupStockDTO dto = ContactGroupStockConverter.toDTO(vo);

		Long i = contactGroupStockManage.cancelContactWithTx(dto);
		return JsonResult.success(i);
	}
	
	/**
	 * 释放账户锁
	 * @return
	 */
	@RequestMapping(value = "/releaseDistributedLock")
	@ResponseBody
	public JsonResult<Integer> ReleaseDistributedLock(ContactGroupStockVO vo,HttpServletRequest req){
		
		CacheUser user = this.getCacheUser();
		user.getLoginName();
			
		ContactGroupStockDTO dto = ContactGroupStockConverter.toDTO(vo);
		contactGroupStockManage.releaseDistributedLock(dto,user);
		return JsonResult.success(1);
	}

	/**
	 * 校验当前是否有锁
	 * merchantId
	 * spuId
	 * @return
	 */
	@RequestMapping(value = "/tryLock")
	@ResponseBody
	public JsonResult<Map<String, Object>> distributedLock(ContactGroupStockVO vo,HttpServletRequest req){
		
		CacheUser user = this.getCacheUser();
		user.getLoginName();
			
		ContactGroupStockDTO dto = ContactGroupStockConverter.toDTO(vo);
		Map<String, Object> map = contactGroupStockManage.tryLock(dto,user);
		
		return JsonResult.success(map);
	}
}

	