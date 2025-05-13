package com.egeo.components.product.facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.AttributeValueReadService;
import com.egeo.components.product.service.read.ProductUnitReadService;
import com.egeo.components.product.service.read.StandardProductUnitAttNameReadService;
import com.egeo.components.product.service.read.StandardProductUnitAttValueReadService;
import com.egeo.components.product.service.write.ProductUnitWriteService;
import com.egeo.components.product.dto.AttributeValueDTO;
import com.egeo.components.product.dto.ProductUnitDTO;
import com.egeo.components.product.dto.PuIdAndPuAttName;
import com.egeo.components.product.dto.StandardProductUnitAttNameDTO;
import com.egeo.components.product.dto.StandardProductUnitAttValueDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class ProductUnitFacade {
	
	@Resource
	private ProductUnitReadService productUnitReadService;
	
	@Resource
	private ProductUnitWriteService productUnitWriteService;
	
	@Resource
	private AttributeValueReadService attributeValueReadService;
	
	@Resource
	private StandardProductUnitAttNameReadService standardProductUnitAttNameReadService;

	@Resource
	private StandardProductUnitAttValueReadService standardProductUnitAttValueReadService;
	
	
	public ProductUnitDTO findProductUnitById(ProductUnitDTO dto){
		
		return productUnitReadService.findProductUnitById(dto);
	}

	public PageResult<ProductUnitDTO> findProductUnitOfPage(ProductUnitDTO dto,Pagination page){
		
		return productUnitReadService.findProductUnitOfPage(dto, page);
		
	}

	public List<ProductUnitDTO> findProductUnitAll(ProductUnitDTO dto){
		
		return productUnitReadService.findProductUnitAll(dto);
		
	}

	public Long insertProductUnitWithTx(ProductUnitDTO dto){
		
		return productUnitWriteService.insertProductUnitWithTx(dto);
	}

	public int updateProductUnitWithTx(ProductUnitDTO dto){
		
		return productUnitWriteService.updateProductUnitWithTx(dto);
	}

	public int deleteProductUnitWithTx(ProductUnitDTO dto){
		
		return productUnitWriteService.deleteProductUnitWithTx(dto);
		
	}
	/**
	 * 根据puid查询pu所有pu信息
	 * @return
	 */
	public Map<String, Object> findCommodityProductUnitAllByStandardUnitId(Long merchantProductId, Integer f) {
		Map<String, Object> maps = new HashMap<>();
		List<PuIdAndPuAttName> puIdAndPuAttNames = new ArrayList<>();
		Map<String, Object> puMaps = new HashMap<>();
		List<Map<String, Object>> puList = new ArrayList<>();
		
		ProductUnitDTO productUnitDTO = new ProductUnitDTO();
		productUnitDTO.setMerchantProductId(merchantProductId);
		List<ProductUnitDTO> productUnitList = productUnitReadService.findProductUnitAll(productUnitDTO);
		for (ProductUnitDTO productUnit : productUnitList) {
				Map<String, Object> map = new HashMap<>();
				map.put("productUnitId", productUnit.getId());
				map.put("productUnitName", productUnit.getName());
				map.put("salePrice", productUnit.getSalePrice());
				map.put("puPicUrl", productUnit.getPuPicUrl());
				map.put("status", productUnit.getStatus());
				map.put("isVendible", productUnit.getIsVendible());

				// 根据puid查询pu属性值id集合
				StringBuffer stringBuffer = new StringBuffer();
				List<Long> puids = productUnitReadService
						.attValueByProductUnitId(productUnit.getId());
				// 根据pu属性值id集合查询pu属性值信息
				List<AttributeValueDTO> attributeValueDTOs = attributeValueReadService.findByAttributeValueIds(puids);
				if (productUnit.getIsVendible() == 1) {
					PuIdAndPuAttName puIdAndPuAttName = new PuIdAndPuAttName();
					puIdAndPuAttName.setProductUnitId(productUnit.getId());
					puIdAndPuAttName.setPuAttNameIds(puids);
					puIdAndPuAttName.setStatus(productUnit.getStatus());
					puIdAndPuAttNames.add(puIdAndPuAttName);
				}
				StringBuffer keyName = new StringBuffer();
				for (int i = 0; i < attributeValueDTOs.size(); i++) {
					if (i >= attributeValueDTOs.size() - 1) {
						stringBuffer.append(attributeValueDTOs.get(i).getId());
						keyName.append(attributeValueDTOs.get(i).getValue());
					} else {
						stringBuffer.append(attributeValueDTOs.get(i).getId());
						stringBuffer.append(";");
						keyName.append(attributeValueDTOs.get(i).getValue());
						keyName.append(",");
					}

				}
			//请求来源：0、安卓 1、ios 2、web端
			if(f == 2 ){
				map.put("keyName", keyName);
				puMaps.put(stringBuffer.toString(), map);
			}else if(f == 0 || f == 1){
				List<Map<String, Object>> atNameMapList = new ArrayList<>();
				for (int i = 0; i < attributeValueDTOs.size(); i++) {
					Map<String, Object> atNameMap = new HashMap<>();
					atNameMap.put("attValueId", attributeValueDTOs.get(i).getId());
					atNameMap.put("attNameId", attributeValueDTOs.get(i).getAttributeNameId());
					atNameMap.put("attValue", attributeValueDTOs.get(i).getValue());
					atNameMapList.add(atNameMap);
				}
				map.put("attNameIds", atNameMapList);
				map.put("specGroup", stringBuffer);
				puList.add(map);
			}
			
		}

		List<Map<String, Object>> attValueMaps = new ArrayList<>();
		List<StandardProductUnitAttNameDTO> standardProductUnitAttNameDTOs = standardProductUnitAttNameReadService
				.findByStandardUnitId(merchantProductId);
		for (StandardProductUnitAttNameDTO standardProductUnitAttNameDTO : standardProductUnitAttNameDTOs) {
			Map<String, Object> map = new HashMap<>();
			map.put("attNameId", standardProductUnitAttNameDTO.getAttNameId());
			map.put("attName", standardProductUnitAttNameDTO.getAttName());
			List<Map<String, Object>> attList = new ArrayList<>();

			// 根据spu属性id查询spu属性值信息
			List<StandardProductUnitAttValueDTO> list = standardProductUnitAttValueReadService
					.findByStandardProductUnitAttNameId(standardProductUnitAttNameDTO.getId());
			for (StandardProductUnitAttValueDTO standardProductUnitAttValueDTO : list) {
				Integer status = 4;
				Map<String, Object> strings = new HashMap<>();
				strings.put("attValue", standardProductUnitAttValueDTO.getAttValue());
				strings.put("attValueId", standardProductUnitAttValueDTO.getAttValueId());
				boolean isSave = false;
				// 循环所有有效的pu信息
				for (PuIdAndPuAttName puIdAndPuAttName : puIdAndPuAttNames) {
					List<Long> puAttNameIds = puIdAndPuAttName.getPuAttNameIds();
					// 根据pu所有属性值id查询是否存在该规格值下
					if (puAttNameIds.contains(standardProductUnitAttValueDTO.getAttValueId())) {
						isSave = true;
						if (puIdAndPuAttName.getStatus() == 3) {
							status = 3;
						}
						
					}
				}
				strings.put("status", status);
				if (isSave) {
					attList.add(strings);
				}

			}
			map.put("attValue", attList);
			attValueMaps.add(map);
		}
		//请求来源：0、安卓 1、ios 2、web端
		if(f == 2){
			maps.put("pu", puMaps);
		}else if(f == 0 || f == 1){
			maps.put("pu", puList);
		}
		maps.put("puAttNames", attValueMaps);
		return maps;
	}

}
	