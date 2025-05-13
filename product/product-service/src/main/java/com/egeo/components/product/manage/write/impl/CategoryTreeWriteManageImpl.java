package com.egeo.components.product.manage.write.impl;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.CategoryTreeWriteManage;
import com.egeo.components.product.dao.read.CategoryTreeReadDAO;
import com.egeo.components.product.dao.write.CategoryTreeNodeWriteDAO;
import com.egeo.components.product.dao.write.CategoryTreeWriteDAO;
import com.egeo.components.product.dao.write.CategoryWriteDAO;
import com.egeo.components.product.po.CategoryTreePO;
import com.egeo.exception.BusinessException;
import com.egeo.utils.EmptyUtil;

@Service
public class CategoryTreeWriteManageImpl implements CategoryTreeWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CategoryTreeWriteDAO categoryTreeWriteDAO;
	
	@Autowired
	private CategoryWriteDAO categoryWriteDAO;	
	
	@Autowired
	private CategoryTreeNodeWriteDAO categoryTreeNodeWriteDAO;	
	
	@Autowired
	private CategoryTreeReadDAO categoryTreeReadDAO;
	@Override
	public String addCategoryTreeWithTx(CategoryTreePO po) {
		//判断是否类目树名称重复
		CategoryTreePO categoryTreePO = new CategoryTreePO();
		categoryTreePO.setName(po.getName());
		categoryTreePO.setCompanyType(po.getCompanyType());
		categoryTreePO.setEnterpriseId(po.getEnterpriseId());
		List<CategoryTreePO> list = categoryTreeReadDAO.findAll(categoryTreePO,null);
		if(EmptyUtil.isNotEmpty(list)){
			throw new BusinessException("已存在该名称的类目树，请重新填写类目树名称");
		}
		//查询app所有启用的类目树
		CategoryTreePO categoryTree = new CategoryTreePO();
		categoryTree.setType(po.getType());
		//是否启用：0否1是
		categoryTree.setStartUsing(1);
		categoryTree.setEnterpriseId(po.getEnterpriseId());
		categoryTree.setCompanyId(po.getCompanyId());
		categoryTree.setIsDefault(po.getIsDefault());
		categoryTree.setCompanyType(po.getCompanyType());
		List<CategoryTreePO> categoryTreeList = categoryTreeReadDAO.findAll(categoryTree,null);
		//如果没有则启用
		if(EmptyUtil.isEmpty(categoryTreeList)){
			po.setStartUsing(1);
		}
		/*CategoryPO tar =  new CategoryPO();
		tar.setName("ROOT");
		tar.setCategoryLable("ROOT");
		tar.setDescription("根节点");

		tar.setPlatformId(po.getPlatformId());
		categoryWriteDAO.insert(tar);	
		
		
		CategoryTreeNodePO ctnPo = new CategoryTreeNodePO();
		//类目树的根节点规定为0
		ctnPo.setParentId((long) 0);
		ctnPo.setCategoryTreeId(po.getId());
		ctnPo.setCategoryId(tar.getId());
		ctnPo.setListSort(0);
		ctnPo.setPlatformId(po.getPlatformId());
		categoryTreeNodeWriteDAO.insert(ctnPo)+"";*/
		return categoryTreeWriteDAO.insert(po) + "";
		 

	}
	private void stopCategory(CategoryTreePO po) {
		//如果是启用，将所有app启用前台类目树设为停用
		if(po.getStartUsing() != null && po.getStartUsing() == 1){
			logger.info("查询所有app启用的前台类目树，将其设其为停用");
			//查询所有启用的类目树
			CategoryTreePO categoryTreePO = new CategoryTreePO();
			categoryTreePO.setType(po.getType());
			//是否启用：0否1是
			categoryTreePO.setStartUsing(1);
			categoryTreePO.setCompanyType(po.getCompanyType());
			categoryTreePO.setEnterpriseId(po.getEnterpriseId());
			categoryTreePO.setCompanyId(po.getCompanyId());
			categoryTreePO.setIsDefault(po.getIsDefault());
			categoryTreePO.setPlatformId(po.getPlatformId());
			List<CategoryTreePO> categoryTreeList = categoryTreeReadDAO.findAll(categoryTreePO,null);
			for (CategoryTreePO categoryTreePO2 : categoryTreeList) {
				//设为停用
				categoryTreePO2.setStartUsing(0);
				categoryTreeWriteDAO.update(categoryTreePO2);
			}
		}
		
		//如果是启用，将所有web启用前台类目树设为停用
		if(po.getWebStart() != null && po.getWebStart() == 1){
			logger.info("查询所有web启用的前台类目树，将其设其为停用");
			//查询所有启用的类目树
			CategoryTreePO categoryTreePO = new CategoryTreePO();
			categoryTreePO.setType(po.getType());
			//是否启用：0否1是
			categoryTreePO.setWebStart(1);
			categoryTreePO.setCompanyType(po.getCompanyType());
			categoryTreePO.setEnterpriseId(po.getEnterpriseId());
			categoryTreePO.setCompanyId(po.getCompanyId());
			categoryTreePO.setIsDefault(po.getIsDefault());
			categoryTreePO.setPlatformId(po.getPlatformId());
			List<CategoryTreePO> categoryTreeList = categoryTreeReadDAO.findAll(categoryTreePO,null);
			for (CategoryTreePO categoryTreePO2 : categoryTreeList) {
				//设为停用
				categoryTreePO2.setWebStart(0);
				categoryTreeWriteDAO.update(categoryTreePO2);
			}
		}
		if(po.getIsDefault() != null && po.getIsDefault() == 1){
			logger.info("查询所有app启用的前台类目树，将其设其为非默认配置");
			//查询所有启用的类目树
			CategoryTreePO categoryTreePO = new CategoryTreePO();
			categoryTreePO.setType(po.getType());
			//是否启用：0否1是
			categoryTreePO.setIsDefault(1);
			categoryTreePO.setCompanyType(po.getCompanyType());
			categoryTreePO.setEnterpriseId(po.getEnterpriseId());
			categoryTreePO.setPlatformId(po.getPlatformId());
			List<CategoryTreePO> categoryTreeList = categoryTreeReadDAO.findAll(categoryTreePO,null);
			for (CategoryTreePO categoryTreePO2 : categoryTreeList) {
				//设为非默认配置
				categoryTreePO2.setIsDefault(0);
				categoryTreeWriteDAO.update(categoryTreePO2);
			}
		}
	}
	/**
	 * 根据类目树id将类目树设为启用
	 */
	@Override
	public boolean categoryTreeStartUsingWithTx(Long categoryTreeId,Integer companyType,Integer clientType, Long platformId) {
		boolean isTrue = false;
		CategoryTreePO categoryTreePO = new CategoryTreePO();
		categoryTreePO.setId(categoryTreeId);
		categoryTreePO=categoryTreeReadDAO.findById(categoryTreePO);
		if (Objects.isNull(categoryTreePO)){
			throw new BusinessException("类目树id:"+categoryTreeId+"不存在");
		}
		categoryTreePO.setType(2);
		//app是否启用：0否1是
		if(clientType == 1)
			categoryTreePO.setStartUsing(1);
		categoryTreePO.setId(categoryTreeId);
		categoryTreePO.setCompanyType(companyType);
		categoryTreePO.setPlatformId(platformId);
		//web是否启用：0否1是 默认0停用
		if(clientType == 3)
			categoryTreePO.setWebStart(1);
		//根据是否停用来停用前台类目树
		stopCategory(categoryTreePO);
		logger.info("修改类目树id"+categoryTreeId+"为启用");
		categoryTreeWriteDAO.update(categoryTreePO);
		isTrue = true;
		return isTrue;
	}

	@Override
	public boolean categoryTreeStopUsingWithTx(Long categoryTreeId) {
		CategoryTreePO categoryTreePO = new CategoryTreePO();
		categoryTreePO.setId(categoryTreeId);
		CategoryTreePO tree=categoryTreeReadDAO.findById(categoryTreePO);
		if (Objects.isNull(tree)){
			throw new BusinessException("该名称的类目树不存在，请重新查询后再操作");
		}
		categoryTreePO.setStartUsing(0);
		categoryTreeWriteDAO.update(categoryTreePO);
		return true;
	}

	/**
	 * 根据类目树id修改类目树信息
	 * @param vo
	 * @param req
	 * @return
	 */
	@Override
	public boolean updateCategoryTreeWithTx(CategoryTreePO po) {
		//判断是否类目树名称重复
		CategoryTreePO categoryTreePO = new CategoryTreePO();
		categoryTreePO.setName(po.getName());
		categoryTreePO.setEnterpriseId(po.getEnterpriseId());
		categoryTreePO.setCompanyType(po.getCompanyType());
		List<CategoryTreePO> list = categoryTreeReadDAO.findAll(categoryTreePO,null);
		if(EmptyUtil.isNotEmpty(list)){
			if(!po.getId().equals(list.get(0).getId())){
				throw new BusinessException("已存在该名称的类目树，请重新填写类目树名称");
			}
		}
		boolean isTrue = false;
		//根据是否停用来停用前台类目树
		stopCategory(po);
		categoryTreeWriteDAO.update(po);
		isTrue = true;
		return isTrue;
	}
}
	