package com.egeo.components.product.service.write.impl;

import com.alibaba.fastjson.JSONObject;
import com.egeo.utils.cache.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.MerchantProdAttValueWriteService;
import com.egeo.components.product.manage.write.MerchantProdAttValueWriteManage;
import com.egeo.components.product.converter.MerchantProdAttValueConverter;
import com.egeo.components.product.dto.MerchantProdAttValueDTO;
import com.egeo.components.product.po.MerchantProdAttValuePO;

import javax.annotation.Resource;
import java.util.List;

@Service("merchantProdAttValueWriteService")
public class MerchantProdAttValueWriteServiceImpl  implements MerchantProdAttValueWriteService {
	@Autowired
	private MerchantProdAttValueWriteManage merchantProdAttValueWriteManage;

	@Resource
	private JedisUtil jedisUtil;
	@Override
	public Long insertMerchantProdAttValueWithTx(MerchantProdAttValueDTO dto) {
		MerchantProdAttValuePO po = MerchantProdAttValueConverter.toPO(dto);
		Long rt = merchantProdAttValueWriteManage.insertMerchantProdAttValueWithTx(po);		
		return rt;
	}

	@Override
	public int updateMerchantProdAttValueWithTx(MerchantProdAttValueDTO dto) {
		MerchantProdAttValuePO po = MerchantProdAttValueConverter.toPO(dto);
		int rt = merchantProdAttValueWriteManage.updateMerchantProdAttValueWithTx(po);		
		return rt;
	}

	@Override
	public int deleteMerchantProdAttValueWithTx(MerchantProdAttValueDTO dto) {
		MerchantProdAttValuePO po = MerchantProdAttValueConverter.toPO(dto);
		int rt = merchantProdAttValueWriteManage.deleteMerchantProdAttValueWithTx(po);		
		return rt;
	}

	@Override
	public void insertList(List<String> my) {
		/*String  o = jedisUtil.getString(my);
		List<String> strings = JSONObject.parseArray(o, String.class);*/
		merchantProdAttValueWriteManage.insertList(my);
	}
}
	