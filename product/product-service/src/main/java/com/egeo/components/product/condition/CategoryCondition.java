package com.egeo.components.product.condition;

import java.util.List;

import com.egeo.components.product.po.CategoryPO;

/**
 * 
 * @author xiaping
 * @date 2017-07-17 11:21:22
 */
public class CategoryCondition extends CategoryPO {
	private static final long serialVersionUID = 1L;
	
	private Long parentId;
	
	private int categoryCnt;
	/**
	 * 类目节点id
	 */
	private Long categoryTreeNodeId;
	
	private List<CategoryCondition> lists;

    public Long getCategoryTreeNodeId() {
		return categoryTreeNodeId;
	}

	public void setCategoryTreeNodeId(Long categoryTreeNodeId) {
		this.categoryTreeNodeId = categoryTreeNodeId;
	}

	public Long getParentId() {
        return parentId;
    }
    
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public List<CategoryCondition> getLists() {
        return lists;
    }

    public void setLists(List<CategoryCondition> lists) {
        this.lists = lists;
    }

	public int getCategoryCnt() {
		return categoryCnt;
	}

	public void setCategoryCnt(int categoryCnt) {
		this.categoryCnt = categoryCnt;
	}
    
        
}
	