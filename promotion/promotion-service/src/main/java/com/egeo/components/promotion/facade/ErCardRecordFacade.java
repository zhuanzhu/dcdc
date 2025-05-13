package com.egeo.components.promotion.facade;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.config.client.HeadImportRecordsClient;
import com.egeo.components.config.client.ImportRecordsClient;
import com.egeo.components.config.dto.HeadImportRecordsDTO;
import com.egeo.components.config.dto.ImportRecordsDTO;
import com.egeo.components.promotion.dto.ErCardRecordDTO;
import com.egeo.components.promotion.service.read.ErCardRecordReadService;
import com.egeo.components.promotion.service.write.ErCardRecordWriteService;
import com.egeo.components.stock.client.MerchantProductVirtualStockClient;
import com.egeo.components.stock.client.SkuVirtualStockWarehouseStockClient;
import com.egeo.components.stock.dto.MerchantProductVirtualStockDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class ErCardRecordFacade {
	
	@Autowired
	private ErCardRecordReadService erCardRecordReadService;
	
	@Autowired
	private ErCardRecordWriteService erCardRecordWriteService;
	
	@Autowired
	private HeadImportRecordsClient headImportRecordsWriteService;
	
	@Autowired
	private ImportRecordsClient importRecordsReadService;
	
	@Autowired
	private SkuVirtualStockWarehouseStockClient skuVirtualStockWarehouseStockService;
	
	@Autowired
	private MerchantProductVirtualStockClient merchantProductVirtualStockWriteService;
	
	public ErCardRecordDTO findErCardRecordById(ErCardRecordDTO dto){
		
		return erCardRecordReadService.findErCardRecordById(dto);
	}

	public PageResult<ErCardRecordDTO> findErCardRecordOfPage(ErCardRecordDTO dto,Pagination page){
		
		return erCardRecordReadService.findErCardRecordOfPage(dto, page);
		
	}

	public List<ErCardRecordDTO> findErCardRecordAll(ErCardRecordDTO dto){
		
		return erCardRecordReadService.findErCardRecordAll(dto);
		
	}

	public Long insertErCardRecordWithTx(ErCardRecordDTO dto){
		
		return erCardRecordWriteService.insertErCardRecordWithTx(dto);
	}

	public int updateErCardRecordWithTx(ErCardRecordDTO dto){
		
		return erCardRecordWriteService.updateErCardRecordWithTx(dto);
	}

	public int deleteErCardRecordWithTx(ErCardRecordDTO dto){
		
		return erCardRecordWriteService.deleteErCardRecordWithTx(dto);
		
	}
	/**
	 * 确认导入
	 * @param addStock 是否增加库存：0否1是
	 * @return
	 */
	public int confirmTheImport(ErCardRecordDTO dto,Integer addStock) {
		
		List<ErCardRecordDTO> confirmTheImport = erCardRecordReadService.confirmTheImport(dto);
		if(addStock == 1){
			// 计算sku新增库存数
			Map<Long, Long> skuVirtualStocks = new HashMap<>();
			for (ErCardRecordDTO erCardRecordDTO : confirmTheImport) {
				// 根据spuId分组卡密信息
				if(skuVirtualStocks.containsKey(erCardRecordDTO.getSkuId())){
					Long stock = skuVirtualStocks.get(erCardRecordDTO.getSkuId());
					skuVirtualStocks.put(erCardRecordDTO.getSkuId(), stock + 1L);
				}else{
					skuVirtualStocks.put(erCardRecordDTO.getSkuId(), 1L);
				}
			}
			// 循环添加库存
			for (Map.Entry<Long, Long> entry : skuVirtualStocks.entrySet()) {
				MerchantProductVirtualStockDTO merchantProductVirtualStockDTO = new MerchantProductVirtualStockDTO();
				merchantProductVirtualStockDTO.setSkuId(entry.getKey());
				merchantProductVirtualStockDTO.setRealStockNum(entry.getValue());
				//1、增加 2、减少
				merchantProductVirtualStockDTO.setType(1);
				skuVirtualStockWarehouseStockService.updateMerchantProductVirtualStockDTO(merchantProductVirtualStockDTO);
			}
		}
		
		//根据头部记录草稿id查询头部记录草稿信息
		ImportRecordsDTO importRecordsDTO = new ImportRecordsDTO();
		importRecordsDTO.setId(dto.getImportRecordsId());
		ImportRecordsDTO importRecordsDTO2 = importRecordsReadService.findImportRecordsById(importRecordsDTO);
		//同步头部记录信息
		HeadImportRecordsDTO tar = new HeadImportRecordsDTO();
		tar.setId(importRecordsDTO2.getId());
		tar.setCompanyName(importRecordsDTO2.getCompanyName());
		tar.setTemplateType(importRecordsDTO2.getTemplateType());
		tar.setGeneratedTime(importRecordsDTO2.getGeneratedTime());
		tar.setFileSequenceNumber(importRecordsDTO2.getFileSequenceNumber());
		tar.setCreateTime(importRecordsDTO2.getCreateTime());
		headImportRecordsWriteService.insertHeadImportRecordsWithTx(tar);
		return 1;
	}
	/**
	 * 根据记录id删除unit
	 * @param dto
	 * @return
	 */
	public int findErCardRecordByImportRecordsId(ErCardRecordDTO dto) {
		// TODO Auto-generated method stub
		return erCardRecordWriteService.findErCardRecordByImportRecordsId(dto);
	}

}
	