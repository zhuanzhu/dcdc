package com.egeo.components.stock.business.impl;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.egeo.components.product.dto.CommodityProductUnitDTO;
import com.egeo.components.product.dto.MerchantDTO;
import com.egeo.components.product.dto.SkuDTO;
import com.egeo.components.product.dto.StandardProductUnitDTO;
import com.egeo.components.product.dto.StandardUnitClientDTO;
import com.egeo.components.product.dto.StandardUnitCompanyDTO;
import com.egeo.components.product.dto.StandardUnitDTO;
import com.egeo.components.product.dto.StoreDTO;
import com.egeo.components.stock.business.ContactGroupStockManage;
import com.egeo.components.stock.dto.ContactGroupPuStockDTO;
import com.egeo.components.stock.dto.ContactGroupSkuStockDTO;
import com.egeo.components.stock.dto.ContactGroupStockDTO;
import com.egeo.components.stock.facade.CommodityProductUnitVirtualStockFacade;
import com.egeo.components.stock.facade.ContactGroupStockFacade;
import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.entity.CacheUser;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.cache.JedisUtil;

@Service("contactGroupStock")
public class ContactGroupStockManageImpl implements ContactGroupStockManage{

	private static final Logger logger = LoggerFactory.getLogger(ContactGroupStockManageImpl.class);
	
	@Resource(name="contactGroupStockFacade")
	private ContactGroupStockFacade contactGroupStockFacade;
	
	@Resource(name="commodityProductUnitVirtualStockFacade")
	private CommodityProductUnitVirtualStockFacade commodityProductUnitVirtualStockFacade;
	
	
	@Resource
	private JedisUtil jedisUtil;

	@Override
	public ContactGroupStockDTO findContactGroupStockById(ContactGroupStockDTO dto) {
		return contactGroupStockFacade.findContactGroupStockById(dto);
	}

	@Override
	public PageResult<ContactGroupStockDTO> findContactGroupStockOfPage(ContactGroupStockDTO dto, Pagination page) {
		return contactGroupStockFacade.findContactGroupStockOfPage(dto, page);
	}

	@Override
	public List<ContactGroupStockDTO> findContactGroupStockAll(ContactGroupStockDTO dto) {
		return contactGroupStockFacade.findContactGroupStockAll(dto);
	}

	@Override
	public Long insertContactGroupStockWithTx(ContactGroupStockDTO dto) {
		return contactGroupStockFacade.insertContactGroupStockWithTx(dto);
	}



	@Override
	public int updateContactGroupStockWithTx(ContactGroupStockDTO dto) {
		return contactGroupStockFacade.updateContactGroupStockWithTx(dto);
	}

	@Override
	public int deleteContactGroupStockWithTx(ContactGroupStockDTO dto) {
		return contactGroupStockFacade.deleteContactGroupStockWithTx(dto);
	}

	@Override
	public Map<String, Object> findContactGroupById(ContactGroupStockDTO dto) {
		Map<String, Object> map = new HashMap<>();
		//关联组
		ContactGroupStockDTO contactGroupStockById = contactGroupStockFacade.findContactGroupStockById(dto);
		map.put("id",contactGroupStockById.getId());
		map.put("name",contactGroupStockById.getName());
		//运营方
		MerchantDTO merchantDTO = new MerchantDTO();
		merchantDTO.setId(contactGroupStockById.getMerchantId());
		MerchantDTO merchantById = contactGroupStockFacade.findMerchantById(merchantDTO);
		map.put("merchantId",merchantById.getId());
		map.put("merchantName",merchantById.getName());
		//spu
		StandardProductUnitDTO standardProductUnitDTO = new StandardProductUnitDTO();
		standardProductUnitDTO.setId(contactGroupStockById.getSpuId());
		StandardProductUnitDTO standardProductUnitById = contactGroupStockFacade.findStandardProductUnitById(standardProductUnitDTO);
		map.put("spuId",standardProductUnitById.getId());
		map.put("spuName",standardProductUnitById.getName());
		return map;
	}



	@Override
	public PageResult<Map<String, Object>> findContactGroupStockMapOfPage(ContactGroupStockDTO dto, String productName, String merchantName, Pagination page) {
		//productName --->  spuIds
		List<Long> spuIds = null;
		Map<Long,Object> spuMap = new HashMap<>();

		if (EmptyUtil.isNotBlank(productName)) {
			StandardProductUnitDTO standardProductUnitDTO = new StandardProductUnitDTO();
			standardProductUnitDTO.setName(productName);
			List<StandardProductUnitDTO> unitAll = contactGroupStockFacade.findStandardProductUnitAll(standardProductUnitDTO);
			if (EmptyUtil.isNotEmpty(unitAll)) {
				spuIds = new ArrayList<>();
				for (StandardProductUnitDTO spu : unitAll) {
					spuIds.add(spu.getId());
					spuMap.put(spu.getId(),spu.getName());
				}
			}
		}
		//merchantName  ---> merchantIds，运营方精确查询，merchantIds废弃
		List<Long> merchantIds = null;
		/*MerchantDTO merchantDTO = new MerchantDTO();
		merchantDTO.setName(merchantName);
		List<MerchantDTO> merchantAll = contactGroupStockFacade.findMerchantAll(merchantDTO);*/

		/*Map<Long,Object> merchantMap = new HashMap<>();
		for (MerchantDTO mc : merchantAll) {
			merchantIds.add(mc.getId());
			merchantMap.put(mc.getId(),mc.getName());
		}*/
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//dto
		PageResult<ContactGroupStockDTO> pageResult = contactGroupStockFacade.findContactGroupStockMapOfPage(dto, page, spuIds, merchantIds);
		List<ContactGroupStockDTO> list = pageResult.getList();
		List<Map<String, Object>> maps = new ArrayList<>();

		for ( ContactGroupStockDTO contact : list ) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", contact.getId());
			map.put("name",contact.getName());
			//产品名
			map.put("spuId",contact.getSpuId());
			if (EmptyUtil.isEmpty(spuMap.get(contact.getSpuId()))) {
				StandardProductUnitDTO standardProductUnitDTO1 = new StandardProductUnitDTO();
				standardProductUnitDTO1.setId(contact.getSpuId());
				map.put("spuName",contactGroupStockFacade.findStandardProductUnitById(standardProductUnitDTO1).getName());
			} else {
				map.put("spuName",spuMap.get(contact.getSpuId()));
			}
			//运营方
			if (EmptyUtil.isNotEmpty(dto.getMerchantId()) && EmptyUtil.isNotBlank(merchantName) ) {
				map.put("merchantId",dto.getMerchantId());
				map.put("merchantName",merchantName);
			} else {
				map.put("merchantId",contact.getMerchantId());
				MerchantDTO merchantDTO = new MerchantDTO();
				merchantDTO.setId(contact.getMerchantId());
				map.put("merchantName",contactGroupStockFacade.findMerchantById(merchantDTO).getName());
			}
/*			map.put("merchantId",contact.getMerchantId());
			if (EmptyUtil.isEmpty(merchantMap.get(contact.getMerchantId()))) {
				MerchantDTO merchantDTO1 = new MerchantDTO();
				merchantDTO1.setId(contact.getMerchantId());
				map.put("merchantName",contactGroupStockFacade.findMerchantById(merchantDTO1).getName());
			} else {
				map.put("merchantName",merchantMap.get(contact.getMerchantId()));
			}*/
			//关联su数量
			map.put("contactCount",contact.getCount());
			//最后更新时间
			map.put("lastUpdateTime",sdf.format(contact.getUpdateTime()));
			//最后编辑人
			map.put("lastOperator",contact.getCreateUserName());
			maps.add(map);
		}
		PageResult<Map<String, Object>> result = new PageResult<Map<String, Object>>();
		result.setList(maps);
		result.setPageNo(pageResult.getPageNo());
		result.setPageSize(pageResult.getPageSize());
		result.setTotalSize(pageResult.getTotalSize());
		return result;
	}

	/**
	 * merchantId 查询运营方下所有su
	 * @param dto
	 * @param page
	 * @return
	 */
	@Override
	public PageResult<Map<String, Object>> findSuListOfPageByMerchantId(ContactGroupStockDTO dto,  Pagination page) {

		List<Map<String, Object>> maps = new ArrayList<>();
		//当前操作的关联组
		ContactGroupStockDTO stockDTO = new ContactGroupStockDTO();
		stockDTO.setId(dto.getId());
		ContactGroupStockDTO contactGroupStockById = contactGroupStockFacade.findContactGroupStockById(stockDTO);
		Map<String, Object> group = new HashMap<>();
		group.put("groupId",contactGroupStockById.getId());
		group.put("groupName",contactGroupStockById.getName());
		maps.add(group);
		//运营方
		MerchantDTO merchantDTO = new MerchantDTO();
		merchantDTO.setId(dto.getMerchantId());
		MerchantDTO merchantById = contactGroupStockFacade.findMerchantById(merchantDTO);
		//suList
		StandardUnitDTO standardUnitDTO = new StandardUnitDTO();
		standardUnitDTO.setMerchantId(dto.getMerchantId());
		standardUnitDTO.setStandardProductUnitId(contactGroupStockById.getSpuId());
		PageResult<StandardUnitDTO> pageResult = contactGroupStockFacade.findStandardUnitOfPage(standardUnitDTO, page);

		for ( StandardUnitDTO su : pageResult.getList() ) {
			Map<String, Object> map = new HashMap<>();
			//suId
			map.put("suId",su.getId());
			//关联组
			ContactGroupStockDTO temp = new ContactGroupStockDTO();
			temp.setMerchantId(su.getMerchantId());
			ContactGroupStockDTO contactGroupStockByMerchantIdAndSuId = contactGroupStockFacade.findContactGroupStockByMerchantIdAndSuId(temp, su.getId());
			map.put("id",contactGroupStockByMerchantIdAndSuId.getId());
			map.put("name",contactGroupStockByMerchantIdAndSuId.getName());
			if (EmptyUtil.isEmpty(map.get("name"))) {
				map.put("checked",false);
			} else {
				map.put("checked",true);
			}
			//商品编号
			map.put("productSerialNumber",su.getMerchantProductSerialNumber());
			//销售名
			map.put("productSaleName",su.getName());
			//购买方式
			map.put("buyType",su.getBuyType());
			//销售方式
			map.put("saleWay",su.getSaleWay());
			//运营方
			map.put("merchantId",merchantById.getId());
			map.put("merchantName",merchantById.getName());
			//客户端
			StandardUnitClientDTO standardUnitClientDTO = new StandardUnitClientDTO();
			standardUnitClientDTO.setStandardUnitId(su.getId());
			List<StandardUnitClientDTO> standardUnitClientAll = contactGroupStockFacade.findStandardUnitClientAll(standardUnitClientDTO);
			List<Long> clientIds = new ArrayList<>();
			for (StandardUnitClientDTO suc : standardUnitClientAll) {
				clientIds.add(suc.getClientId());
			}
			List<String> clientNameList = contactGroupStockFacade.clientNameByClientIds(clientIds);
			StringBuffer clients = new StringBuffer();
			for (int i = 0; i < clientNameList.size(); i++) {
				clients.append(clientNameList.get(i));
				if (i + 1 < clientNameList.size()) {
					clients.append(",");
				}
			}
			map.put("clientNames",clients.toString());
			//总店
			StoreDTO storeDTO = new StoreDTO();
			storeDTO.setId(su.getStoreId());
			StoreDTO storeById = contactGroupStockFacade.findStoreById(storeDTO);
			map.put("storeName",storeById.getName() );
			//福利企业
			//根据suid查询可以显示的福利企业信息
			StandardUnitCompanyDTO standardUnitCompanyDTO = new StandardUnitCompanyDTO();
			standardUnitCompanyDTO.setStandardUnitId(su.getId());
			List<StandardUnitCompanyDTO> standardUnitCompanyList = contactGroupStockFacade.findStandardUnitCompanyAll(standardUnitCompanyDTO);
			//拼接福利企业id集合
			List<Long> companyIds = new ArrayList<>();
			for (StandardUnitCompanyDTO standardUnitCompanyDTO2 : standardUnitCompanyList) {
				companyIds.add(standardUnitCompanyDTO2.getCompanyId());
			}
			//根据福利公司id集合查询福利公司信息
			if (EmptyUtil.isNotEmpty(companyIds)) {
				List<CompanyDTO> companyList = contactGroupStockFacade.findCompanyByCompanyIds(companyIds);
				//拼接福利公司名称字符串
				StringBuffer companys = new StringBuffer();
				for (int i = 0; i < companyList.size(); i++) {
					companys.append(companyList.get(i).getCompanyName());
					if (i + 1 < companyList.size()) {
						companys.append(",");
					}
				}
				map.put("companys",companys.toString());
			}

			//状态
			map.put("status",su.getStatus());
			//显示状态
			//是否可见：默认0是;1否
			map.put("isVisible",su.getIsVisible());

			maps.add(map);


		}
		PageResult<Map<String, Object>> result = new PageResult<Map<String, Object>>();
		result.setList(maps);
		result.setPageNo(pageResult.getPageNo());
		result.setPageSize(pageResult.getPageSize());
		result.setTotalSize(pageResult.getTotalSize());
		return result;

	}

	/**
	 * 同步关联组spu下的sku
	 * @param dto
	 */
	@Override
	public void synchronizeContactGroupSkuStock(ContactGroupStockDTO dto) {
		SkuDTO skuDTO = new SkuDTO();
		skuDTO.setStandardProductUnitId(dto.getSpuId());
		List<SkuDTO> skuAll = contactGroupStockFacade.findSkuAll(skuDTO);
		List<ContactGroupSkuStockDTO> skuList = new ArrayList<>();
		for ( SkuDTO sku :  skuAll ) {
			ContactGroupSkuStockDTO contactGroupSkuStockDTO = new ContactGroupSkuStockDTO();
			contactGroupSkuStockDTO.setSkuId(sku.getId());
			contactGroupSkuStockDTO.setContactGroupStockId(dto.getId());
//			contactGroupStockFacade.insertContactGroupSkuStockWithTx(contactGroupSkuStockDTO);
			skuList.add(contactGroupSkuStockDTO);
		}
		contactGroupStockFacade.insertContactGroupSkuStockListWithTx(skuList);

	}

	/**
	 *  关联商品
	 * @param suId
	 * @param dto
	 * @return
	 */
	@Override
	public Long confirmContactWithTx(Long suId, ContactGroupStockDTO dto,CacheUser user,String ip,String mac) {

		Long rt = null;
		Map<String, Object> map = tryLock(dto,user);
		map.get("isSuccess");
		
		if(!(boolean)map.get("isSuccess")) {
			throw new BusinessException("当前其他用户正在操作该关联组！");
		}
		//groupId --> contact_group_sku_stock
		List<ContactGroupSkuStockDTO> contactGroupSkuStockAll = getContactGroupSkuStockDTOS(dto);
		//suid ---> pus
		List<CommodityProductUnitDTO> commodityProductUnitAll = getCommodityProductUnitDTOS(suId);

		checkConfirm(contactGroupSkuStockAll,commodityProductUnitAll);
		
		//插入 contact_group_pu_stock
		for ( ContactGroupSkuStockDTO skuStockDTO : contactGroupSkuStockAll ) {
			ContactGroupPuStockDTO contactGroupPuStockDTO = new ContactGroupPuStockDTO();
			//contact_group_sku_id
			contactGroupPuStockDTO.setContactGroupSkuId(skuStockDTO.getId());
			//su_id
			contactGroupPuStockDTO.setSuId(suId);
			for ( CommodityProductUnitDTO commodityDTO : commodityProductUnitAll ) {
				//pu_id
				if(commodityDTO.getSkuId().equals(skuStockDTO.getSkuId())) {
					contactGroupPuStockDTO.setPuId(commodityDTO.getId());
					contactGroupStockFacade.confirmContactWithTx(contactGroupPuStockDTO, skuStockDTO,user,ip, mac);
				}
				
			}
		}
		
		contactGroupStockFacade.increaseOneCountWithTx(dto);

		return rt;
	}

	/**
	 * 校验是否可关联
	 * @param contactGroupSkuStockAll
	 * @param commodityProductUnitAll
	 */
	private void checkConfirm(List<ContactGroupSkuStockDTO> contactGroupSkuStockAll,
			List<CommodityProductUnitDTO> commodityProductUnitAll) {

		for ( ContactGroupSkuStockDTO skuStockDTO : contactGroupSkuStockAll ) {
			for ( CommodityProductUnitDTO commodityDTO : commodityProductUnitAll ) {
				if(commodityDTO.getSkuId().equals(skuStockDTO.getSkuId())) {
					
					contactGroupStockFacade.checkConfirm(commodityDTO.getId(),skuStockDTO,0);
					
					contactGroupStockFacade.checkConfirm(commodityDTO.getId(),skuStockDTO,1);
				}
				
			}
		}
	}

	/**
	 * 解散关联
	 * @param dto
	 * @return
	 */
	@Override
	public Long cancelContactWithTx(ContactGroupStockDTO dto) {

		Long rt = null;
		//groupId --> contact_group_sku_stock
		List<ContactGroupSkuStockDTO> contactGroupSkuStockAll = getContactGroupSkuStockDTOS(dto);

		//删除 contact_group_pu_stock
		for ( ContactGroupSkuStockDTO skuStockDTO : contactGroupSkuStockAll ) {
			ContactGroupPuStockDTO contactGroupPuStockDTO = new ContactGroupPuStockDTO();
			contactGroupPuStockDTO.setContactGroupSkuId(skuStockDTO.getId());
			contactGroupStockFacade.deleteContactGroupPuStockByParaWithTx(contactGroupPuStockDTO);
		}

		contactGroupStockFacade.clearCountWithTx(dto);

		return rt;
	}

	/**
	 * 解散su关联
	 * @param suId
	 * @param dto
	 */
	@Override
	public void cancelSuContactWithTx(Long suId, ContactGroupStockDTO dto,CacheUser user) {
		Map<String, Object> map = tryLock(dto,user);
		map.get("isSuccess");
		
		if(!(boolean)map.get("isSuccess")) {
			throw new BusinessException("当前其他用户正在操作该关联组！");
		}
		//删除 contact_group_pu_stock
		ContactGroupPuStockDTO contactGroupPuStockDTO = new ContactGroupPuStockDTO();
		contactGroupPuStockDTO.setSuId(suId);
		ContactGroupStockDTO contactGroupStockDTO = contactGroupStockFacade.findContactGroupStockByMerchantIdAndSuId(dto,suId);
		//当前id 和 关联id不一致 不能做取消操作
		if(!dto.getId().equals(contactGroupStockDTO.getId())) {
			throw new BusinessException("不能对非当前关联组内的商品操作！");
		}
		contactGroupStockFacade.deleteContactGroupPuStockByParaWithTx(contactGroupPuStockDTO);

		contactGroupStockFacade.reduceOneCountWithTx(dto);

	}



	/**
	 * //groupId --> contact_group_sku_stock
	 * 根据groupID 获取sku集合
	 * @param dto
	 * @return
	 */
	private List<ContactGroupSkuStockDTO> getContactGroupSkuStockDTOS(ContactGroupStockDTO dto) {

		ContactGroupSkuStockDTO contactGroupSkuStockDTO = new ContactGroupSkuStockDTO();
		contactGroupSkuStockDTO.setContactGroupStockId(dto.getId());
		return contactGroupStockFacade.findContactGroupSkuStockAll(contactGroupSkuStockDTO);
	}

	/**
	 * 根据suid获取pu集合
	 * @param suId
	 * @return
	 */
	private List<CommodityProductUnitDTO> getCommodityProductUnitDTOS(Long suId) {

		CommodityProductUnitDTO commodityProductUnitDTO = new CommodityProductUnitDTO();
		commodityProductUnitDTO.setStandardUnitId(suId);
		return contactGroupStockFacade.findCommodityProductUnitAll(commodityProductUnitDTO);
	}

	@Override
	public void releaseDistributedLock(ContactGroupStockDTO dto,CacheUser user) {
		dto = contactGroupStockFacade.findContactGroupStockById(dto);
		String key = dto.getMerchantId() + "-" + dto.getSpuId();
		String currentUser = jedisUtil.getString(key);
		
		if(StringUtils.isBlank(currentUser)) {
			return;
		}
		
		if(user.getLoginName().equals(currentUser)) {
			jedisUtil.del(key);
			return;
		}
		boolean role = contactGroupStockFacade.checkUserByUserId(user);
		
		if(role) {
			jedisUtil.del(key);
			
		}
		
	}

	@Override
	public Map<String, Object> tryLock(ContactGroupStockDTO dto,CacheUser user) {
		
		Map<String, Object> map = new HashMap<>();
		dto = contactGroupStockFacade.findContactGroupStockById(dto);
		if(dto == null) {
			throw new BusinessException("关联组不存在！");
		}
		String key = dto.getMerchantId() + "-" + dto.getSpuId();
		
		long i = jedisUtil.setnx(key, user.getLoginName());
		
		String currentUser = jedisUtil.getString(key);
		//如果获取锁失败，并不是本人持有锁，判断当前用户是否有权限解锁
		//如果有权限 role 为true 返回当前持锁人
		//如果没有权限 role 为false 返回当前持锁人
		//根据userId 判断该用户是否为管理员
		
		if(i < 1 && !user.getLoginName().equals(currentUser)) {
			
			boolean role = contactGroupStockFacade.checkUserByUserId(user);
			
			map.put("isSuccess", false);
			map.put("data", currentUser);
			map.put("role", role);
			logger.info("获取用户锁失败：当前持有锁用户：{}",currentUser);
		} else {
			map.put("isSuccess", true);
			map.put("data", "");
			map.put("role", true);
			logger.info("获取用户锁成功：当前持有锁用户：{}",currentUser);
		}
		
		return map;
	}

	@Override
	public List<ContactGroupStockDTO> findAllByName(ContactGroupStockDTO contactDto) {
		
		return contactGroupStockFacade.findAllByName(contactDto);
	}


}
	