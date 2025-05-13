package com.egeo.components.product.manage.read.impl;

import java.util.*;

import com.egeo.components.product.po.*;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.SkuReadManage;
import com.egeo.components.product.condition.SkuCondition;
import com.egeo.components.product.dao.read.AttributeNameReadDAO;
import com.egeo.components.product.dao.read.AttributeValueReadDAO;
import com.egeo.components.product.dao.read.SkuAttNameReadDAO;
import com.egeo.components.product.dao.read.SkuAttValueReadDAO;
import com.egeo.components.product.dao.read.SkuReadDAO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class SkuReadManageImpl implements SkuReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SkuReadDAO skuReadDAO;
	
	@Autowired
	private SkuAttNameReadDAO skuAttNameReadDAO;
	
	@Autowired
	private AttributeNameReadDAO attributeNameReadDAO;
	
	@Autowired
	private SkuAttValueReadDAO skuAttValueReadDAO;
	
	@Autowired
	private AttributeValueReadDAO attributeValueReadDAO;
	
	public SkuPO findSkuById(SkuPO po) {
		SkuPO skupo = new SkuPO();
		skupo.setId(po.getId());
		return skuReadDAO.findById(skupo);
	}

	public PageResult<SkuCondition> findSkuOfPage(SkuPO po, Pagination page) {
		
		PageResult<SkuCondition> pageResult = new PageResult<SkuCondition>();
		List<SkuCondition> list = null;

		int cnt = skuReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = skuReadDAO.findSkuOfPage(po, page);
		} else {
			list = new ArrayList<SkuCondition>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<SkuCondition> findSkuAll(SkuPO po) {

		return skuReadDAO.findSkuAll(po,null);
	}
	/**
	 * 分页查询所有电子卡券sku
	 * @return
	 */
	@Override
	public List<SkuCondition> findSkuECardOfPage(SkuPO po) {
		List<SkuCondition> list = skuReadDAO.findSkuECardOfPage(po);
		return list;

	}
	/**
	 * 根据sku编号查询sku电子卡券信息
	 * @param skuSerialNumber
	 * @return
	 */
	@Override
	public SkuPO findSkuECardBySkuSerialNumber(String skuSerialNumber) {
		// TODO Auto-generated method stub
		return skuReadDAO.findSkuECardBySkuSerialNumber(skuSerialNumber);
	}
	/**
	 * 查询pu序列号
	 * @param skuId
	 * @return
	 */
	@Override
	public String productUnitSerialNumberBySkuId(Long skuId) {
		//根据skuid查询sku序列号
		String skuSerialNumber = skuReadDAO.skuSerialNumberBySkuId(skuId);
		//根据skuid查询sku下面的所有pu数量
		int pumax = skuReadDAO.puMaxBySkuId(skuId);
		return skuSerialNumber+(pumax+1);
	}

	@Override
	public Map<String, String> queryStandardBySkuId(Long skuId) {
		//逻辑具体实现
		Map<String, String> res=new HashMap<>();
		//查询skuAttName列表
		List<SkuAttNamePO> attNames=skuAttNameReadDAO.querySkuAttNamesBySkuId(skuId);
		for(SkuAttNamePO attName:attNames) {
			//根据att_name_id查询Name值
			Long attNameId=attName.getAttNameId();
			AttributeNamePO nameCond=new AttributeNamePO();
			nameCond.setId(attNameId);
			AttributeNamePO namePo=attributeNameReadDAO.findById(nameCond);
			//根据id查询sku_att_value
			Long id=attName.getId();
			SkuAttValuePO attValue=skuAttValueReadDAO.querySkuAttValueBySkuAttNameId(id);
			Long attValueId=attValue.getAttValueId();
			//根据att_value_id查询value值
			AttributeValuePO valueCond=new AttributeValuePO();
			valueCond.setId(attValueId);
			AttributeValuePO valuePo=attributeValueReadDAO.findById(valueCond);
			res.put(namePo==null?"":namePo.getName(), valuePo==null?"":valuePo.getValue());
		}
		return res;
	}

	@Override
	public List<SkuPO> findSkuLikeName(String linkedSkuName,Long platformId) {
		List<SkuPO> skuPOList=skuReadDAO.findSkuLikeName(linkedSkuName,platformId);
		return skuPOList;
	}

	@Override
	public List<Long> getMembershipSku(Long platformId) {
		List<MapResultPO> membershipSku = skuReadDAO.getMembershipSku(platformId);
		List<Long> list = new ArrayList<>();
		for(MapResultPO po:membershipSku){
			if((Long)po.getValue()==3){
				list.add(po.getKey());
			}
		}
		return list;
	}

	@Override
	public SkuPO findSkuByPuId(Long puId) {
		return skuReadDAO.findSkuByPuId(puId);

	}

	/**
	 * 根据预警属性级别id查询sku集合的id和预警天数
	 */
	@Override
	public Map<Long, String> findSkuIdAndPreDaysByPreAttNameId(Long precautiousAttNameId) {
		Map<Long,String> map = new HashMap();
		List<ProductAttValuePO> predays = skuReadDAO.findSkuIdAndPreDaysByPreAttNameId(precautiousAttNameId);
		for ( ProductAttValuePO pro : predays ) {
			map.put(pro.getId(),pro.getAttValueCustom());
		}
		return map;
	}

	@Override
	public Long findLastId() {
		return skuReadDAO.findLastId();
	}

	@Override
	public List<SkuCondition> findSkuListConByIdList(List<Long> idList) {
		return skuReadDAO.findSkuListConByIdList(idList);
	}

	@Override
	public List<SkuPO> findSkuBySkuSerialNos(List<String> skuSerialNos) {
		return skuReadDAO.findSkuBySkuSerialNo(skuSerialNos);
	}

	@Override
	public List<SkuPO> findSkuBySkuIds(List<Long> skuIds) {
		return skuReadDAO.findSkuBySkuIds(skuIds);
	}
}
	