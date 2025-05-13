package com.egeo.components.promotion.facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.config.client.HeadImportRecordsClient;
import com.egeo.components.config.client.ImportRecordsClient;
import com.egeo.components.config.dto.HeadImportRecordsDTO;
import com.egeo.components.config.dto.ImportRecordsDTO;
import com.egeo.components.product.client.MerchantClient;
import com.egeo.components.product.client.StandardUnitClient;
import com.egeo.components.product.client.StandardUnitCombinationClient;
import com.egeo.components.product.client.StoreClient;
import com.egeo.components.product.dto.MerchantDTO;
import com.egeo.components.product.dto.StandardUnitCombinationDTO;
import com.egeo.components.product.dto.StandardUnitDTO;
import com.egeo.components.product.dto.StoreDTO;
import com.egeo.components.promotion.dto.CouponCompanyDTO;
import com.egeo.components.promotion.dto.CouponDTO;
import com.egeo.components.promotion.service.read.CouponCompanyReadService;
import com.egeo.components.promotion.service.read.CouponReadService;
import com.egeo.components.promotion.service.write.CouponWriteService;
import com.egeo.components.user.client.CompanyClient;
import com.egeo.components.user.client.UserClient;
import com.egeo.components.user.client.UserExtendClient;
import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.components.user.dto.UserExtendDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;

@Component
public class CouponFacade {

	@Autowired
	private CouponReadService couponReadService;

	@Autowired
	private CouponWriteService couponWriteService;

	@Autowired
	private CouponCompanyReadService couponCompanyReadService;

	@Autowired
	private UserClient userReadService;
	
	@Autowired
	private UserExtendClient userExtendReadService;

	@Autowired
	private CompanyClient companyReadService;

	@Autowired
	private StandardUnitClient standardUnitReadService;

	@Autowired
	private MerchantClient merchantReadService;

	@Autowired
	private StandardUnitCombinationClient standardUnitCombinationReadService;

	@Autowired
	private StoreClient storeReadService;
	@Autowired
	private ImportRecordsClient importRecordsReadService;
	@Autowired
	private ImportRecordsClient importRecordsWriteService;
	@Autowired
	private HeadImportRecordsClient headImportRecordsWriteService;


	public CouponDTO findCouponById(CouponDTO dto) {

		return couponReadService.findCouponById(dto);
	}

	public PageResult<CouponDTO> findCouponOfPage(CouponDTO dto, Pagination page) {

		return couponReadService.findCouponOfPage(dto, page);

	}

	public List<CouponDTO> findCouponAll(CouponDTO dto) {

		return couponReadService.findCouponAll(dto);

	}

	public Long insertCouponWithTx(CouponDTO dto) {

		return couponWriteService.insertCouponWithTx(dto);
	}

	public int updateCouponWithTx(CouponDTO dto) {

		return couponWriteService.updateCouponWithTx(dto);
	}

	public int deleteCouponWithTx(CouponDTO dto) {

		return couponWriteService.deleteCouponWithTx(dto);

	}

	/**
	 * 模糊查询优惠卷列表
	 * 
	 * @param dto
	 * @param page
	 * @return
	 */
	public PageResult<CouponDTO> findCouponOfPageByBlurry(CouponDTO dto, List<Long> couponIdList, Pagination page) {
		return couponReadService.findCouponOfPageByBlurry(dto, couponIdList,page);
	}

	/**
	 * 查询用户信息 by id
	 * 
	 * @param userId
	 * @return
	 */
	public UserDTO queryUserById(Long userId) {
		return userReadService.findUserByID(userId);
	}

	/**
	 * 查询优惠卷的所属公司
	 * 
	 * @param id
	 * @param page
	 * @return
	 */
	public PageResult<CouponCompanyDTO> findCouponCompanyOfPage(Long id, Pagination page) {
		CouponCompanyDTO dto = new CouponCompanyDTO();
		dto.setCouponId(id);
		return couponCompanyReadService.findCouponCompanyOfPage(dto, page);
	}

	public CompanyDTO findCouponCompanyById(Long companyId) {
		return companyReadService.findCompanyById(companyId);
	}

	/**
	 * 查询优惠卷的所属公司
	 * 
	 * @param id
	 *            优惠卷id
	 * @return
	 */
	public List<CouponCompanyDTO> findCouponCompanyAll(Long id) {
		CouponCompanyDTO dto = new CouponCompanyDTO();
		dto.setCouponId(id);
		return couponCompanyReadService.findCouponCompanyAll(dto);
	}

	public StandardUnitDTO findStandardUnitById(Long id) {

		return standardUnitReadService.findStandardUnitById(id);  
	}

	public StandardUnitCombinationDTO findStandardUnitCombinationById(Long id) {
		StandardUnitCombinationDTO dto = new StandardUnitCombinationDTO();
		dto.setId(id);
		return standardUnitCombinationReadService.findStandardUnitCombinationById(dto);
	}

	/**
	 * 查询优惠卷的相关商品
	 * 
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> findCouponGoodsAll(CouponDTO dto) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (dto.getGoodsType() == 0) {
			// 单su
			StandardUnitDTO standardUnitDTO = new StandardUnitDTO();
			standardUnitDTO.setId(dto.getGoodsId());
			standardUnitDTO.setPlatformId(dto.getPlatformId());
			StandardUnitDTO standardUnitDTO_ = standardUnitReadService.findStandardUnitById(standardUnitDTO);

			if (EmptyUtil.isNotEmpty(standardUnitDTO_))
				// 添加商品到商品集合
				addCouponGoods(standardUnitDTO_, list);

		} else if (dto.getGoodsType() == 1) {
			// 商品组
			List<StandardUnitDTO> standardUnitDTOList = standardUnitReadService
					.findByStandardUnitCombinationId(dto.getGoodsId(), dto.getPlatformId());
			for (StandardUnitDTO standardUnitDTO_ : standardUnitDTOList) {
				// 添加商品到商品集合
				addCouponGoods(standardUnitDTO_, list);
			}
		}
		return list;
	}
	
	/**
	 * 查询优惠卷的相关商品id集合
	 * 
	 * @param dto
	 * @return
	 */
	public List<Long> findCouponGoodsIdAll(CouponDTO dto) {
		List<Long> list = new ArrayList<Long>();
		if (dto.getGoodsType() == 0) {
			// 单su
			/*StandardUnitDTO standardUnitDTO = new StandardUnitDTO();
			standardUnitDTO.setId(dto.getGoodsId());
			StandardUnitDTO standardUnitDTO_ = standardUnitReadService.findStandardUnitById(standardUnitDTO);

			if (EmptyUtil.isNotEmpty(standardUnitDTO_))*/
				// 添加商品到商品集合
				list.add(dto.getGoodsId());
		} else if (dto.getGoodsType() == 1) {
			// 商品组
			List<StandardUnitDTO> standardUnitDTOList = standardUnitReadService
					.findByStandardUnitCombinationId(dto.getGoodsId(), null);
			for (StandardUnitDTO standardUnitDTO_ : standardUnitDTOList) {
				// 添加商品到商品集合
				list.add(standardUnitDTO_.getId());
			}
		}
		return list;
	}

	/**
	 * 添加商品到商品集合
	 * 
	 * @param standardUnitDTO
	 * @param list
	 */
	private void addCouponGoods(StandardUnitDTO standardUnitDTO, List<Map<String, Object>> list) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id", standardUnitDTO.getId());
		map.put("merchantProductSerialNumber", standardUnitDTO.getMerchantProductSerialNumber());
		map.put("name", standardUnitDTO.getName());
		map.put("salePrice", standardUnitDTO.getSalePrice());
		map.put("saleWay", standardUnitDTO.getSaleWay());

		// 查询商家信息
		MerchantDTO merchantDTO = new MerchantDTO();
		merchantDTO.setId(standardUnitDTO.getMerchantId());
		MerchantDTO merchantDTO_ = merchantReadService.findMerchantById(merchantDTO);
		map.put("merchantName", merchantDTO_.getName());

		list.add(map);
	}

	public List<CompanyDTO> findCouponCompanyAll(Long platformId, Integer companyType) {
		CompanyDTO dto = new CompanyDTO();
		dto.setPlatformId(platformId);
		dto.setCompanyType(companyType);
		return companyReadService.findCompanyAll(dto);
	}
	
	public int findUserCountByCompanyId(Long companyId){
		UserExtendDTO userExtendDTO = new UserExtendDTO();
		// 员工筛选条件:在职,有效,未删除
		userExtendDTO.setCompanyId(companyId);
		userExtendDTO.setIsAvailable(Integer.valueOf(1));
		userExtendDTO.setAccountStatus(Integer.valueOf(0));	
		userExtendDTO.setIsDeleted(Integer.valueOf(0));
		List<UserExtendDTO> userExtendDTOList = userExtendReadService.queryUserByCondition(userExtendDTO);
		return userExtendDTOList.size();
	}
	
	/**
	 * 通过su的id查询commodityTemplateId
	 * @param suId
	 * @return
	 */
	public Long queryTempIdBySuId(Long suId){
		
		StandardUnitDTO standardUnitDTO = standardUnitReadService.findStandardUnitById(suId);
		return standardUnitDTO != null ? standardUnitDTO.getCommodityTemplateId() : null;
	}

	/**
	 * 查询门店列表
	 * @param dto
	 * @return
	 */
	public List<StoreDTO> findRootStoreAll(StoreDTO dto) {
		return storeReadService.findStoreAll(dto);
	}

    public Map<String,Object> insertuserFacadesAndUserViewList(ImportRecordsDTO importRecordsDTO) {
		Map<String, Object> data = new HashMap<>();

		// 将导入信息，插入到记录表
		Long importRecordId = importRecordsWriteService.insertImportRecordsWithTx(importRecordsDTO);
		data.put("importUserInfo", importRecordId);
		return data;
	}

    public UserDTO findUserByMail(String mail) {
		UserDTO userDTO = userReadService.findByMail(mail);
			return userDTO;

	}

	public CompanyDTO findCompanyById(Long companyId) {
		return companyReadService.findCompanyById(companyId);
	}

	public void updateHeadImport(Long importUserInfo) {
		// 2.将临时记录表的数据同步到正式表
		ImportRecordsDTO importRecordsDTO = new ImportRecordsDTO();
		importRecordsDTO.setId(importUserInfo);
		ImportRecordsDTO findImportRecord = importRecordsReadService.findImportRecordsById(importRecordsDTO);

		if (EmptyUtil.isNotEmpty(findImportRecord)) {
			HeadImportRecordsDTO headImportRecordsDTO = new HeadImportRecordsDTO();

			headImportRecordsDTO.setId(findImportRecord.getId());
			headImportRecordsDTO.setTemplateType(findImportRecord.getTemplateType());
			headImportRecordsDTO.setCompanyName(findImportRecord.getCompanyName());
			headImportRecordsDTO.setFileSequenceNumber(findImportRecord.getFileSequenceNumber());
			headImportRecordsDTO.setGeneratedTime(findImportRecord.getGeneratedTime());

			headImportRecordsWriteService.insertHeadImportRecordsWithTx(headImportRecordsDTO);
		}

	}
}
