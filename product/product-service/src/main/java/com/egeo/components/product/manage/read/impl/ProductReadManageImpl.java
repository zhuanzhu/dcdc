package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.ProductReadManage;
import com.egeo.components.product.condition.ProductCondition;
import com.egeo.components.product.dao.read.ProductReadDAO;
import com.egeo.components.product.po.ProductPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service
public class ProductReadManageImpl implements ProductReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ProductReadDAO productReadDAO;
    @Override
    public PageResult<ProductPO> findPage(Pagination page, ProductPO po, List<String> nameList) {
        int cnt = productReadDAO.countOfPageByNameList(po,nameList);
        List<ProductPO> list = new ArrayList<ProductPO>();
        if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
            page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
            list = productReadDAO.findOfPageByNameList(po, page,nameList);
        }
        PageResult<ProductPO> pageResult = new PageResult<ProductPO>();
        pageResult.setList(list);
        pageResult.setTotalSize(cnt);
        pageResult.setPageNo(page.getPageNo());
        pageResult.setPageSize(page.getPageSize());
        return pageResult;
    }
    @Override
    public ProductPO findById(ProductPO po) {
        
        return productReadDAO.findById(po);
    }
    @Override
    public List<ProductPO> findAll(ProductPO po) {
        
        return productReadDAO.findAll(po,null);
    }
	@Override
	public PageResult<ProductPO> productByActivityId(Pagination page, ProductPO po) {
		int cnt = productReadDAO.productByActivityIdCount(po);
        List<ProductPO> list = new ArrayList<ProductPO>();
        if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
            page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
            list = productReadDAO.productByActivityId(po, page);
        }
        PageResult<ProductPO> pageResult = new PageResult<ProductPO>();
        pageResult.setList(list);
        pageResult.setTotalSize(cnt);
        pageResult.setPageNo(page.getPageNo());
        pageResult.setPageSize(page.getPageSize());
        return pageResult;
	}
	@Override
	public List<ProductPO> queryProductsByIds(List<Long> productIds) {
		return productReadDAO.queryProductsByIds(productIds);
	}
	/**
	 * 根据产品id查询产品信息及产品详情
	 * @param
	 * @return
	 */
	@Override
	public ProductCondition productAndProductDescriptionById(Long productId) {
		
		return productReadDAO.productAndProductDescriptionById(productId);
	}
	/**
	 * 根据以通过的spu草稿id集合查询spu信息
	 * @param ids
	 * @return
	 */
	@Override
	public List<ProductPO> findProductByIds(List<Long> ids) {
		// TODO Auto-generated method stub
		return productReadDAO.findProductByIds(ids);
	}
	/**
	 * 根据类目节点id查询spu草稿信息、有为true、没有为false
	 * @param
	 * @return
	 */
	@Override
	public boolean findByCategoryTreeNodeId(Long categoryTreeNodeId) {
		int i = productReadDAO.findByCategoryTreeNodeId(categoryTreeNodeId);
		if(i > 0){
			return true;
		}
		return false;
	}
	/**
	 * 获取product表中所有的id
	 * @param po
	 * 参数作为后期扩展，本次未使用（2018.11.7）
	 */
	public  List<Long> getProductIdsByCondition(ProductPO po){
		return productReadDAO.getProductIdsByCondition(po);
	}

	@Override
	public List<String> findPrecautiousLines(List<Long> precautiousLineIds, Long id) {
		return productReadDAO.findPrecautiousLines(precautiousLineIds,id);
	}

	@Override
	public Long findLastId() {
		return productReadDAO.findLastId();
	}

	@Override
	public List<Long> findAllCategoryTreeNodeId() {
		return productReadDAO.findAllCategoryTreeNodeId();
	}
}
	