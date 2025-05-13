package com.egeo.components.product.manage.write.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.egeo.common.PlatformKeyConstant;
import com.egeo.components.product.condition.CategoryTreeNodeCondition;
import com.egeo.components.product.dao.read.CategoryAttNameReadDAO;
import com.egeo.components.product.dao.read.CategoryReadDAO;
import com.egeo.components.product.dao.read.CategoryTreeNodeReadDAO;
import com.egeo.components.product.dao.write.CategoryAttNameWriteDAO;
import com.egeo.components.product.dao.write.CategoryBannerWriteDAO;
import com.egeo.components.product.dao.write.CategoryTagWriteDAO;
import com.egeo.components.product.dao.write.CategoryTreeNodeCategoryWriteDAO;
import com.egeo.components.product.dao.write.CategoryTreeNodeWriteDAO;
import com.egeo.components.product.dao.write.CategoryWriteDAO;
import com.egeo.components.product.manage.write.CategoryTreeNodeWriteManage;
import com.egeo.components.product.po.CategoryAttNamePO;
import com.egeo.components.product.po.CategoryBannerPO;
import com.egeo.components.product.po.CategoryPO;
import com.egeo.components.product.po.CategoryTagPO;
import com.egeo.components.product.po.CategoryTreeNodeCategoryPO;
import com.egeo.components.product.po.CategoryTreeNodePO;
import com.egeo.exception.BusinessException;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.log.XLogger;

@Service
public class CategoryTreeNodeWriteManageImpl implements CategoryTreeNodeWriteManage {
	private static final XLogger logger = XLogger.getLogger(CategoryTreeNodeWriteManageImpl.class);
	@Autowired
	private CategoryTreeNodeWriteDAO categoryTreeNodeWriteDAO;
	
	@Autowired
	private CategoryTreeNodeReadDAO categoryTreeNodeReadDAO;
	
	@Autowired
	private CategoryWriteDAO categoryWriteDAO;
	
	@Autowired
	private CategoryReadDAO categoryReadDAO;
	
	@Autowired
	private CategoryAttNameReadDAO categoryAttNameReadDAO;
	
	@Autowired
	private CategoryBannerWriteDAO categoryBannerWriteDAO;
	
	@Autowired
	private CategoryTreeNodeCategoryWriteDAO categoryTreeNodeCategoryWriteDAO;
	
	@Autowired
	private CategoryAttNameWriteDAO categoryAttNameWriteDAO;
	
	@Autowired
	private CategoryTagWriteDAO categoryTagWriteDAO;
	
	
	@Override
	public Long addCategoryTreeNodeWithTx(CategoryTreeNodeCondition ctcc,List<Long> tagIdList) {
		//父节点不能带有属性，如果有属性，不允许添加
		if(checkParentNOdeAttributeIsExist(ctcc.getCategoryTreeId(),ctcc.getParentId())){
			throw new BusinessException("父节点有属性值，不能添加子节点");
		}
		// 同一个父节点下，不允许有2个相同名字的类目
		if(checkNodeNameIsExist(ctcc.getCategoryTreeId(),ctcc.getParentId(),ctcc.getName(),ctcc.getPlatformId())){
			throw new BusinessException("同一节点下，不能有2个相同名字的类目");
		}
		
		//同一个父节点下，不允许有2个相同的类目序列号
		if(serialNumberIsExist(ctcc.getCategoryTreeId(),ctcc.getParentId(),ctcc.getSerialNumber())){
			throw new BusinessException("同一个父节点下，不允许有2个相同的类目编号");
		}
		
		
		CategoryPO tar =  new CategoryPO();
		
		/*if(!ctcc.getParentId().equals(0L)){
			//根据父类id查询类目信息及该类目子集条数
			CategoryCondition categoryCondition = categoryReadDAO.findCategoryByPId(ctcc.getParentId());
			int i = categoryCondition.getCategoryCnt();
			if(String.valueOf(i).length()==1){
				tar.setSerialNumber(categoryCondition.getSerialNumber()+"-0"+i);
			}else{
				tar.setSerialNumber(categoryCondition.getSerialNumber()+"-"+i);
			}
			
		}else{
			CategoryCondition categoryCondition = categoryReadDAO.findCategoryCntByPId(ctcc.getParentId());
			int i = categoryCondition.getCategoryCnt();
			if(String.valueOf(i).length()==1){
				tar.setSerialNumber("0"+i);
			}else{
				tar.setSerialNumber(""+i);
			}
		}*/
		tar.setSerialNumber(ctcc.getSerialNumber());
		tar.setName(ctcc.getName());
		tar.setCategoryLable(ctcc.getCategoryLable());
		tar.setDescription(ctcc.getDescription());
		tar.setSerialNumber(ctcc.getSerialNumber());
		tar.setPlatformId(ctcc.getPlatformId());
		categoryWriteDAO.insert(tar);
		
		CategoryTreeNodePO ctnPo = new CategoryTreeNodePO();
		ctnPo.setParentId(ctcc.getParentId());
		ctnPo.setCategoryTreeId(ctcc.getCategoryTreeId());
		ctnPo.setCategoryId(tar.getId());
		ctnPo.setListSort(ctcc.getListSort());
		ctnPo.setPids(ctcc.getPids());
		ctnPo.setPlatformId(ctcc.getPlatformId());
		categoryTreeNodeWriteDAO.insert(ctnPo);
		
		if(EmptyUtil.isNotEmpty(tagIdList)){
			for (Long tagId : tagIdList) {
				CategoryTagPO categoryTagPO = new CategoryTagPO();
				categoryTagPO.setTagId(tagId);
				categoryTagPO.setCategoryId(tar.getId());
				categoryTagWriteDAO.insert(categoryTagPO);
			}
			
		}
		return tar.getId();
	}
	private boolean checkParentNOdeAttributeIsExist(Long categoryTreeId, Long parentId) {
		boolean rt = false ;
		CategoryTreeNodePO po = new CategoryTreeNodePO();
		po.setParentId(parentId);
		po.setCategoryTreeId(categoryTreeId);
		List<CategoryAttNamePO> list =categoryAttNameReadDAO.getcategoryAttNameListByNodeId(po);
		if(EmptyUtil.isNotEmpty(list)){
			rt = true;
		}
		return rt;		
	}
	private boolean checkNodeNameIsExist(Long categoryTreeId,Long parentId, String name,Long platformId) {
		boolean rt = false ;
		//没有子节点
		if(!hasChildNode(categoryTreeId,parentId)){
			return false;
		}
		CategoryTreeNodeCondition po = new CategoryTreeNodeCondition();
		po.setParentId(parentId);
		po.setName(name);
		po.setCategoryTreeId(categoryTreeId);
		po.setPlatformId(platformId);
		List<CategoryTreeNodeCondition> list = categoryTreeNodeReadDAO.findChildNodebyCategoryName(po);
		if(EmptyUtil.isNotEmpty(list)){
			rt = true;
		}
		
		return rt;
	}
	/**
	 * 同一个父节点下，不允许有2个相同的类目序列号
	 * @param categoryTreeId
	 * @param parentId
	 * @param serialNumber
	 * @return
	 */
	private boolean serialNumberIsExist(Long categoryTreeId,Long parentId, String serialNumber){
		boolean rt = false ;
		//没有子节点
		if(!hasChildNode(categoryTreeId,parentId)){
			return false;
		}
		CategoryTreeNodeCondition po = new CategoryTreeNodeCondition();
		po.setParentId(parentId);
		po.setSerialNumber(serialNumber);
		po.setCategoryTreeId(categoryTreeId);
		List<CategoryTreeNodeCondition> list = categoryTreeNodeReadDAO.findChildNodebyCategorySerialNumber(po);
		if(EmptyUtil.isNotEmpty(list)){
			rt = true;
		}
		
		return rt;
	}
	private boolean hasChildNode(Long categoryTreeId,Long parentId) {
		boolean rt = false ;
		CategoryTreeNodePO po = new CategoryTreeNodePO();
		po.setParentId(parentId);
		po.setCategoryTreeId(categoryTreeId);
		List<CategoryTreeNodePO> list = categoryTreeNodeReadDAO.findAll(po,null);
		if(EmptyUtil.isNotEmpty(list)){
			rt = true;
		}
		return rt;
	}
	@Override
	public int updateCategoryTreeNodeWithTx(CategoryTreeNodePO po) {
		
		return categoryTreeNodeWriteDAO.update(po);
	}
	@Override
	public int deleteByIdWithTx(CategoryTreeNodePO po) {
		
		return categoryTreeNodeWriteDAO.delete(po);
	}
	@Override
	public Long saveCategoryTreeNodeWithTx(CategoryTreeNodeCondition ctcc) {
		//父节点不能带有属性，如果有属性，不允许添加
		if(checkParentNOdeAttributeIsExist(ctcc.getCategoryTreeId(),ctcc.getParentId())){
			throw new BusinessException("父节点有属性值，不能添加子节点");
		}
		// 同一个父节点下，不允许有2个相同名字的类目
		if(checkNodeNameIsExist(ctcc.getCategoryTreeId(),ctcc.getParentId(),ctcc.getName(),ctcc.getPlatformId())){
			throw new BusinessException("同一节点下，不能有2个相同名字的类目");
		}
		CategoryBannerPO categoryBannerPO = new CategoryBannerPO();
		categoryBannerPO.setBannerImg(ctcc.getBannerImg());;
		categoryBannerPO.setLinkType(ctcc.getLinkType());
		categoryBannerPO.setLinkUrl(ctcc.getLinkUrl());
		categoryBannerPO.setLinkParam(ctcc.getLinkParam());
		categoryBannerPO.setExternalLinkId(ctcc.getExternalLinkId());
		categoryBannerPO.setStandardUnitCombinationId(ctcc.getStandardUnitCombinationId());
		categoryBannerPO.setStandardUnitId(ctcc.getStandardUnitId());
		categoryBannerPO.setLocalParamId(ctcc.getLocalParamId());
		categoryBannerPO.setLinkableId(ctcc.getLinkableId());
		categoryBannerWriteDAO.insert(categoryBannerPO);
		
		CategoryPO tar =  new CategoryPO();
		tar.setSerialNumber(ctcc.getSerialNumber());
		tar.setName(ctcc.getName());
		tar.setCategoryLable(ctcc.getCategoryLable());
		tar.setDescription(ctcc.getDescription());
		tar.setSerialNumber(ctcc.getSerialNumber());
		tar.setCategoryBannerId(categoryBannerPO.getId());
		tar.setPlatformId(ctcc.getPlatformId());
		categoryWriteDAO.insert(tar);
		
		CategoryTreeNodePO ctnPo = new CategoryTreeNodePO();
		ctnPo.setParentId(ctcc.getParentId());
		ctnPo.setCategoryTreeId(ctcc.getCategoryTreeId());
		ctnPo.setCategoryId(tar.getId());
		ctnPo.setListSort(ctcc.getListSort());
		ctnPo.setPids(ctcc.getPids());
		ctnPo.setPlatformId(ctcc.getPlatformId());
		categoryTreeNodeWriteDAO.insert(ctnPo);
		
		List<Long> list = new ArrayList<Long>();
		
		/*CategoryTreeNodePO categoryTreeNodePO = new CategoryTreeNodePO();
		categoryTreeNodePO.setId(ctnPo.getId());
		CategoryTreeNodePO nodePO = categoryTreeNodeReadDAO.findById(categoryTreeNodePO);
		if(EmptyUtil.isNotEmpty(nodePO)){
			list.add(nodePO.getId());
			list = PId(nodePO,list);
		}*/


		list.add(ctnPo.getId());
		list = PId(ctnPo,list);
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			if(i < list.size() - 1){
				buffer.append(list.get(i));
				buffer.append(",");
			}else{
				buffer.append(list.get(i));
			}
		}
		ctnPo.setPids(buffer.toString());
		categoryTreeNodeWriteDAO.update(ctnPo);
		return ctnPo.getId();
	}
	// 循环获取所有父类id
    public List<Long> PId(CategoryTreeNodePO dto,List<Long> pid) {
        if(!dto.getParentId().equals(PlatformKeyConstant.PRODUCT_PLATFORMID)){
        	pid.add(0,dto.getParentId());
        	CategoryTreeNodePO categoryTreeNodePO = new CategoryTreeNodePO();
        	categoryTreeNodePO.setId(dto.getParentId());
            CategoryTreeNodePO categoryTreeNodeDTO = categoryTreeNodeReadDAO.findById(categoryTreeNodePO);
            if(EmptyUtil.isNotEmpty(categoryTreeNodeDTO)){
				PId(categoryTreeNodeDTO,pid);
			}
        }
        return pid;
    }
	/**
	 * 根据类目节点id修改前台类目节点信息
	 * @param categoryTreeNodeVO
	 * @param req
	 * @return
	 */
	@Override
	public boolean updateCategoryTreeNodeCategoryWithTx(CategoryTreeNodeCondition ctcc) {
		boolean isTrue = false;
		// 同一个父节点下，不允许有2个相同名字的类目
		if(updateCheckNodeNameIsExist(ctcc.getId(),ctcc.getCategoryTreeId(),ctcc.getParentId(),ctcc.getName(),ctcc.getPlatformId())){
			throw new BusinessException("同一节点下，不能有2个相同名字的类目");
		}
		//根据类目节点id查询类目节点信息
		CategoryTreeNodePO ctnPo = new CategoryTreeNodePO();
		ctnPo.setId(ctcc.getId());
		CategoryTreeNodePO categoryTreeNodePO = categoryTreeNodeReadDAO.findById(ctnPo);
		
		CategoryPO tar =  new CategoryPO();
		tar.setId(categoryTreeNodePO.getCategoryId());
		//根据类目id查询类目信息
		CategoryPO categoryPO = categoryReadDAO.findById(tar);
		
		logger.info("根据类目bannerId修改类目banner信息，类目bannerId："+categoryPO.getCategoryBannerId());
		CategoryBannerPO categoryBannerPO = new CategoryBannerPO();
		categoryBannerPO.setId(categoryPO.getCategoryBannerId());
		categoryBannerPO.setBannerImg(ctcc.getBannerImg());;
		categoryBannerPO.setLinkType(ctcc.getLinkType());
		categoryBannerPO.setLinkUrl(ctcc.getLinkUrl());
		categoryBannerPO.setLinkParam(ctcc.getLinkParam());
		categoryBannerPO.setExternalLinkId(ctcc.getExternalLinkId());
		categoryBannerPO.setStandardUnitCombinationId(ctcc.getStandardUnitCombinationId());
		categoryBannerPO.setStandardUnitId(ctcc.getStandardUnitId());
		categoryBannerPO.setLocalParamId(ctcc.getLocalParamId());
		categoryBannerPO.setLinkableId(ctcc.getLinkableId());
		categoryBannerWriteDAO.update(categoryBannerPO);
		
		logger.info("修改类目信息，id:"+categoryTreeNodePO.getCategoryId());
		tar.setSerialNumber(ctcc.getSerialNumber());
		tar.setName(ctcc.getName());
		tar.setCategoryLable(ctcc.getCategoryLable());
		tar.setDescription(ctcc.getDescription());
		tar.setSerialNumber(ctcc.getSerialNumber());
		tar.setPlatformId(ctcc.getPlatformId());
		categoryWriteDAO.update(tar);
		
		logger.info("修改类目节点信息，id:"+ctcc.getId());
		ctnPo.setParentId(ctcc.getParentId());
		ctnPo.setCategoryTreeId(ctcc.getCategoryTreeId());
		ctnPo.setCategoryId(tar.getId());
		ctnPo.setListSort(ctcc.getListSort());
		ctnPo.setPids(ctcc.getPids());
		ctnPo.setPlatformId(ctcc.getPlatformId());
		categoryTreeNodeWriteDAO.update(ctnPo);
		isTrue = true;
		return isTrue;
	}
	private boolean updateCheckNodeNameIsExist(Long categoryTreeNodeId,Long categoryTreeId, Long parentId, String name, Long platformId) {
		boolean rt = false ;
		//没有子节点
		if(!hasChildNode(categoryTreeId,parentId)){
			return false;
		}
		CategoryTreeNodeCondition po = new CategoryTreeNodeCondition();
		po.setParentId(parentId);
		po.setName(name);
		po.setCategoryTreeId(categoryTreeId);
		po.setPlatformId(platformId);
		List<CategoryTreeNodeCondition> list = categoryTreeNodeReadDAO.findChildNodebyCategoryName(po);
		if(EmptyUtil.isNotEmpty(list)){
			if(!categoryTreeNodeId.equals(list.get(0).getId())){
				rt = true;
			}
		}
		
		return rt;
	}
	/**
	 * 根据前台类目节点id删除前台类目节点及子节点信息
	 * @param req
	 * @return
	 */
	@Override
	public boolean delByCategoryTreeNodeIdWithTx(Long categoryTreeNodeId) {
		boolean ifTrue = false;
		logger.info("根据前台类目节点id递归删除前台类目节点及子节点信息");
		recursionDelByCategoryTreeNodeIdWithTx(categoryTreeNodeId);
		ifTrue = true;
		return ifTrue;
	}
	/**
	 * 根据前台类目节点id递归删除前台类目节点及子节点信息
	 * @param categoryTreeNodeId
	 */
	private void recursionDelByCategoryTreeNodeIdWithTx(Long categoryTreeNodeId){
		CategoryTreeNodePO categoryTreeNodePO = new CategoryTreeNodePO();
		categoryTreeNodePO.setParentId(categoryTreeNodeId);
		List<CategoryTreeNodePO> categoryTreeNodeList = categoryTreeNodeReadDAO.findAll(categoryTreeNodePO,null);
		if(EmptyUtil.isNotEmpty(categoryTreeNodeList)){
			for (CategoryTreeNodePO categoryTreeNodePO2 : categoryTreeNodeList) {
				delByCategoryTreeNodeIdWithTx(categoryTreeNodePO2.getId());
			}
			//根据类目节点id删除类目节点、类目、类目banner、类目属性等一系列信息
			delCategoryTreeNode(categoryTreeNodeId);
		}else{
			//根据类目节点id删除类目节点、类目、类目banner、类目属性等一系列信息
			delCategoryTreeNode(categoryTreeNodeId);
			
			
		}
	}
	/**
	 * 根据类目节点id删除类目节点、类目、类目banner、类目属性等一系列信息
	 * @param categoryTreeNodeId
	 */
	private void delCategoryTreeNode(Long categoryTreeNodeId) {
		//根据前台类目节点id查询前台类目节点信息
		CategoryTreeNodeCondition categoryTreeNodeCondition = categoryTreeNodeReadDAO.findByCategoryTreeNodeId(categoryTreeNodeId);
		
		//根据类目id删除类目属性信息
		CategoryAttNamePO categoryAttNamePO = new CategoryAttNamePO();
		categoryAttNamePO.setCategoryId(categoryTreeNodeCondition.getCategoryId());
		categoryAttNameWriteDAO.deleteByPara(categoryAttNamePO);
		
		//根据类目id删除类目信息
		CategoryPO categoryPO = new CategoryPO();
		categoryPO.setId(categoryTreeNodeCondition.getCategoryId());
		categoryWriteDAO.delete(categoryPO);
		
		//根据类目bannerId删除类目banner信息
		CategoryBannerPO categoryBannerPO = new CategoryBannerPO();
		categoryBannerPO.setId(categoryTreeNodeCondition.getCategoryBannerId());
		categoryBannerWriteDAO.delete(categoryBannerPO);
		
		//根据类目节点id删除类目节点信息
		categoryTreeNodeWriteDAO.delete(categoryTreeNodeCondition);
		
		//根据类目节点id删除类目节点聚合信息
		CategoryTreeNodeCategoryPO categoryTreeNodeCategoryPO = new CategoryTreeNodeCategoryPO();
		categoryTreeNodeCategoryPO.setFrontCategoryTreeNodeId(categoryTreeNodeId);
		categoryTreeNodeCategoryWriteDAO.deleteByPara(categoryTreeNodeCategoryPO);
		
	}
	
	@Override
	public Long insertCategoryTreeNodeWithTx(CategoryTreeNodePO ctnPo, String name, String catId) {
		//父节点不能带有属性，如果有属性，不允许添加
		if(checkParentNOdeAttributeIsExist(ctnPo.getCategoryTreeId(),ctnPo.getParentId())){
			throw new BusinessException("父节点有属性值，不能添加子节点");
		}
		// 同一个父节点下，不允许有2个相同名字的类目
		if(checkNodeNameIsExist(ctnPo.getCategoryTreeId(),ctnPo.getParentId(), name, ctnPo.getPlatformId())){
			logger.info("ctnPo:"+ JSON.toJSONString(ctnPo));
			logger.info("name:"+ name);
			name = name +"-"+catId;
		}
		categoryTreeNodeWriteDAO.insert(ctnPo);
		
		List<Long> list = new ArrayList<Long>();
		
		CategoryTreeNodePO categoryTreeNodePO = new CategoryTreeNodePO();
		categoryTreeNodePO.setParentId(ctnPo.getId());
		CategoryTreeNodePO ctnpo = categoryTreeNodeReadDAO.findById(categoryTreeNodePO);
		if(ctnpo != null){
			list.add(ctnpo.getId());
			list = PId(ctnpo,list);
		}
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			if(i < list.size() - 1){
				buffer.append(list.get(i));
				buffer.append(",");
			}else{
				buffer.append(list.get(i));
			}
		}
		categoryTreeNodePO.setPids(buffer.toString());
		categoryTreeNodeWriteDAO.update(categoryTreeNodePO);
		return ctnPo.getId();
	}


}
	