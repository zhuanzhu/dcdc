package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.CommodityProductUnitReadManage;
import com.egeo.components.product.condition.CommodityProductUnitCondition;
import com.egeo.components.product.condition.StandardProductUnitAttValueCondition;
import com.egeo.components.product.dao.read.CommodityProductUnitReadDAO;
import com.egeo.components.product.dao.read.PictureReadDAO;
import com.egeo.components.product.dao.read.SkuAttValueReadDAO;
import com.egeo.components.product.dao.read.SkuReadDAO;
import com.egeo.components.product.dao.read.StandardProductUnitAttValueReadDAO;
import com.egeo.components.product.po.CommodityProductUnitPO;
import com.egeo.components.product.po.QueryProductUnitPO;
import com.egeo.components.product.po.SkuPO;
import com.egeo.orm.PageResult;
import com.egeo.exception.BusinessException;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;


@Service
public class CommodityProductUnitReadManageImpl implements CommodityProductUnitReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CommodityProductUnitReadDAO commodityProductUnitReadDAO;
	
	@Autowired
	private SkuReadDAO skuReadDAO;
	
	@Autowired
	private PictureReadDAO pictureReadDAO;
	
	@Autowired
	private StandardProductUnitAttValueReadDAO standardProductUnitAttValueReadDAO;
	
	@Autowired
	private SkuAttValueReadDAO skuAttValueReadDAO;
	
	public CommodityProductUnitPO findCommodityProductUnitById(CommodityProductUnitPO po) {
		CommodityProductUnitPO commodityProductUnitpo = new CommodityProductUnitPO();
		commodityProductUnitpo.setId(po.getId());
		return commodityProductUnitReadDAO.findById(commodityProductUnitpo);
	}

	public PageResult<CommodityProductUnitCondition> findCommodityProductUnitOfPage(CommodityProductUnitPO po, Pagination page) {
		
		PageResult<CommodityProductUnitCondition> pageResult = new PageResult<CommodityProductUnitCondition>();
		List<CommodityProductUnitCondition> list = null;

		int cnt = commodityProductUnitReadDAO.countCommodityProductUnitOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = commodityProductUnitReadDAO.findCommodityProductUnitOfPage(po, page);
		} else {
			list = new ArrayList<CommodityProductUnitCondition>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<CommodityProductUnitPO> findCommodityProductUnitAll(CommodityProductUnitPO po) {
		List<CommodityProductUnitPO> list = commodityProductUnitReadDAO.findAll(po,null);
		//pu图片赋值
		assignmentPicUrl(list);
		return list;
	}
	/**
	 * pu图片赋值
	 * @param list
	 */
	private void assignmentPicUrl(List<CommodityProductUnitPO> list) {
		for (CommodityProductUnitPO commodityProductUnitPO : list) {
			if(EmptyUtil.isEmpty(commodityProductUnitPO.getPuPicUrl())){
				// po图片赋值
				getPuPicUrl(commodityProductUnitPO);
			}
		}
		
	}

	private void getPuPicUrl(CommodityProductUnitPO commodityProductUnitPO) {
		//根据suid查询规格值图片信息
		List<StandardProductUnitAttValueCondition> PictureList = standardProductUnitAttValueReadDAO.findAttNamePicture(commodityProductUnitPO.getStandardUnitId());
		if(EmptyUtil.isEmpty(PictureList)){
			throw new BusinessException("商品规格图片异常，商品id："+commodityProductUnitPO.getStandardUnitId());
		}
		//如果pu图片为空则查询sku图片赋值
		SkuPO skuPO = new SkuPO();
		skuPO.setId(commodityProductUnitPO.getSkuId());
		SkuPO skuPO2 = skuReadDAO.findById(skuPO);
		if(EmptyUtil.isEmpty(skuPO2.getSkuPicUrl())){
			//根据属性id和skuid查询属性值id
			Long attValueId = skuAttValueReadDAO.findAttValueIdByAttNameIdSkuId(PictureList.get(0).getAttNameId(), commodityProductUnitPO.getSkuId());
			//根据属性值id匹配规格图片信息
			for (StandardProductUnitAttValueCondition standardProductUnitAttValueCondition : PictureList) {
				if(attValueId.equals(standardProductUnitAttValueCondition.getAttValueId())){
					commodityProductUnitPO.setPuPicUrl(standardProductUnitAttValueCondition.getPictureUrl());
					break;
				}
			}
			//图片信息为空赋值spu封面图片
			if(EmptyUtil.isEmpty(commodityProductUnitPO.getPuPicUrl())){
				//根据spuid查询sku默认图片
				String skuPicUrl = pictureReadDAO.skuPicUrlByStandardProductUnitId(skuPO2.getStandardProductUnitId());
				commodityProductUnitPO.setPuPicUrl(skuPicUrl);
			}
		}else{
			commodityProductUnitPO.setPuPicUrl(skuPO2.getSkuPicUrl());
		}
		
	}

	/**
	 * 根据skuid查询pu总条数
	 * @param id
	 * @return
	 */
	@Override
	public int countRecord(Long skuId) {
		// TODO Auto-generated method stub
		return commodityProductUnitReadDAO.countRecord(skuId);
	}
	/**
	 * 根据puid集合查询pu信息
	 * @param puIds
	 * @return
	 */
	@Override
	public List<CommodityProductUnitCondition> findByPUIds(List<Long> puIds) {
		List<CommodityProductUnitCondition> list = commodityProductUnitReadDAO.findByPUIds(puIds);
		List<CommodityProductUnitPO> commodityProductUnitList = new ArrayList<>();
		for (CommodityProductUnitCondition src : list) {
			CommodityProductUnitPO tar = new CommodityProductUnitPO();
			tar.setId(src.getId());
			tar.setProductUnitSerialNumber(src.getProductUnitSerialNumber());
			tar.setProductUnitId(src.getProductUnitId());
			tar.setSkuId(src.getSkuId());
			tar.setStandardUnitId(src.getStandardUnitId());
			tar.setName(src.getName());
			tar.setRemark(src.getRemark());
			tar.setSalePrice(src.getSalePrice());
			tar.setStatus(src.getStatus());
			tar.setIsVendible(src.getIsVendible());
			tar.setCode(src.getCode());
			tar.setPuPicUrl(src.getPuPicUrl());
			tar.setCreateTime(src.getCreateTime());
			tar.setUpdateTime(src.getUpdateTime());
			tar.setPlatformId(src.getPlatformId());
			tar.setJdSpuId(src.getJdSpuId());
			commodityProductUnitList.add(tar);
		}
		//pu图片赋值
		assignmentPicUrl(commodityProductUnitList);
		return list;
	}
	/**
	 * 根据puid查询pu属性值id集合
	 * @param id
	 * @return
	 */
	@Override
	public List<Long> attValueIdByProductUnitId(Long productUnitId) {
		// TODO Auto-generated method stub
		return commodityProductUnitReadDAO.attValueIdByProductUnitId(productUnitId);
	}

	/**
	 * 根据puid查询pu扩展信息
	 * @param puId
	 * @return
	 */
	@Override
	public CommodityProductUnitCondition findSUSPUByPUId(Long puId) {
		return commodityProductUnitReadDAO.findSUSPUByPUId(puId);
	}

	@Override
	public List<CommodityProductUnitPO> queryPuByName(String puName) {
		return commodityProductUnitReadDAO.queryPuByName(puName);
	}

	@Override
	public String queryPuNullImgUrl(Long skuId) {
		SkuPO skuCond=new SkuPO();
		skuCond.setId(skuId);
		SkuPO sku=skuReadDAO.findById(skuCond);
		if(sku!=null) {
			String skuPicUrl=sku.getSkuPicUrl();
			if(EmptyUtil.isBlank(skuPicUrl)) {
				//查询spu封面图
				return pictureReadDAO.skuPicUrlByStandardProductUnitId(sku.getStandardProductUnitId());
			}else {
				return skuPicUrl;
			}
		}
		return null;
	}
	/**
	 * 根据skuId查询puid集合
	 * @param skuId
	 * @return
	 */
	@Override
	public List<Long> puIdsBySkuId(Long skuId) {
		// TODO Auto-generated method stub
		return commodityProductUnitReadDAO.puIdsBySkuId(skuId);
	}
	/**
	 * 根据suid查询所有puid集合
	 * @param merchantProductId
	 * @return
	 */
	@Override
	public List<Long> findByStandardUnitId(Long standardUnitId) {
		// TODO Auto-generated method stub
		return commodityProductUnitReadDAO.findByStandardUnitId(standardUnitId);
	}

	@Override
	public Long queryCommodityTemplateIdByPuId(Long puId) {
		
		return commodityProductUnitReadDAO.queryCommodityTemplateIdByPuId(puId);
	}

	@Override
	public List<Long> findIdsByPUIds(List<Long> puIdlist) {
		// TODO Auto-generated method stub
		return commodityProductUnitReadDAO.findIdsByPUIds(puIdlist);
	}

	@Override
	public PageResult<CommodityProductUnitPO> findPUByStoreNameSUNameOfPage(QueryProductUnitPO queryProductUnitPO,
			Pagination page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findPictureByStorePUId(Long storeProductUnitId) {
		// 根据门店PUid查询图片信息
		CommodityProductUnitPO commodityProductUnitPO = commodityProductUnitReadDAO.findPictureByStorePUId(storeProductUnitId);
		if(EmptyUtil.isEmpty(commodityProductUnitPO.getPuPicUrl())){
			// po图片赋值
			getPuPicUrl(commodityProductUnitPO);
		}
		return commodityProductUnitPO.getPuPicUrl();
	}

	@Override
	public List<CommodityProductUnitPO> findPuListBySuId(Long suId) {
		return commodityProductUnitReadDAO.findPuListBySuId(suId);

	}
	
	@Override
	public PageResult<CommodityProductUnitCondition> findMerchantPUOfPage(CommodityProductUnitCondition po,
			Pagination page) {
		PageResult<CommodityProductUnitCondition> pageResult = new PageResult<CommodityProductUnitCondition>();
		List<CommodityProductUnitCondition> list = null;

		int cnt = commodityProductUnitReadDAO.countMerchantPUOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = commodityProductUnitReadDAO.findMerchantPUOfPage(po, page);
		} else {
			list = new ArrayList<CommodityProductUnitCondition>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;
	}

	@Override
	public Long findSkuIdByPuId(Long puId) {
		return commodityProductUnitReadDAO.findSkuIdByPuId(puId);
	}

	@Override
	public List<CommodityProductUnitCondition> findPuInfoBySuIdList(Integer companyType, List<Long> suIds) {
		return commodityProductUnitReadDAO.findPuInfoBySuIdList(companyType, suIds);
	}

	@Override
	public List<CommodityProductUnitPO> findByPUIdSkuId(List<Long> puIdList, Long skuId) {
		
		return commodityProductUnitReadDAO.findByPUIdSkuId(puIdList,skuId);
	}

	@Override
	public List<CommodityProductUnitPO> findCommodityProductUnitLimit(CommodityProductUnitPO po) {

		return commodityProductUnitReadDAO.findCommodityProductUnitLimit(po,null);
	}

	@Override
	public Long findLastId() {
		return commodityProductUnitReadDAO.findLastId();
	}

	@Override
	public Long findPuIdByExtendSkuId(Long skuId) {
		return commodityProductUnitReadDAO.findPuIdByExtendSkuId(skuId);
	}
}
	