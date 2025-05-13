package com.egeo.components.stock.business;

import java.util.List;
import java.util.Map;

import com.egeo.components.stock.dto.ContactGroupStockDTO;
import com.egeo.entity.CacheUser;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface ContactGroupStockManage {

	public ContactGroupStockDTO findContactGroupStockById(ContactGroupStockDTO dto);	

	public PageResult<ContactGroupStockDTO> findContactGroupStockOfPage(ContactGroupStockDTO dto,Pagination page);

	public List<ContactGroupStockDTO> findContactGroupStockAll(ContactGroupStockDTO dto);

	Long insertContactGroupStockWithTx(ContactGroupStockDTO dto);

	int updateContactGroupStockWithTx(ContactGroupStockDTO dto);

	int deleteContactGroupStockWithTx(ContactGroupStockDTO dto);

	public PageResult<Map<String, Object>> findContactGroupStockMapOfPage(ContactGroupStockDTO  dto, String productName ,String merchantName, Pagination page);

	public PageResult<Map<String, Object>> findSuListOfPageByMerchantId(ContactGroupStockDTO dto,  Pagination page);

	public void synchronizeContactGroupSkuStock(ContactGroupStockDTO contactGroupStockDTO);


	Long cancelContactWithTx(ContactGroupStockDTO dto);

	Long confirmContactWithTx(Long suId, ContactGroupStockDTO dto, CacheUser user, String ip, String mac);

	public void releaseDistributedLock(ContactGroupStockDTO dto, CacheUser user);

    void cancelSuContactWithTx(Long suId, ContactGroupStockDTO dto,CacheUser user);

	Map<String, Object> findContactGroupById(ContactGroupStockDTO dto);

	/**
	 * 查询是否有锁
	 * @param dto
	 * @param user
	 * @return
	 */
	Map<String, Object> tryLock(ContactGroupStockDTO dto, CacheUser user);

	public List<ContactGroupStockDTO> findAllByName(ContactGroupStockDTO contactDto);
	
}
	