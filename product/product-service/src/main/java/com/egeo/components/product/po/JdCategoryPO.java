package com.egeo.components.product.po;



/**
 * 
 * @author tan
 * @date 2019-03-26 10:43:15
 */
public class JdCategoryPO {


	private Integer catClass;

	public Integer getCatClass() {
		return catClass;
	}

	public void setCatClass(Integer catClass) {
		this.catClass = catClass;
	}

	private Long id;

	/**
	 * 分类名
	 */
	private String name;	

	/**
	 * 上级ID
	 */
	private Long parentId;	

	/**
	 * 对应福管加类目ID
	 */
	private Long innerCategoryId;	

	public Long getId() {
		return id;
	}

	/**
	 * 
	 * @param id 
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 分类名
	 * @return 分类名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 分类名
	 * @param name 分类名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 上级ID
	 * @return 上级ID
	 */
	public Long getParentId() {
		return parentId;
	}

	/**
	 * 上级ID
	 * @param parentId 上级ID
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/**
	 * 对应福管加类目ID
	 * @return 对应福管加类目ID
	 */
	public Long getInnerCategoryId() {
		return innerCategoryId;
	}

	/**
	 * 对应福管加类目ID
	 * @param innerCategoryId 对应福管加类目ID
	 */
	public void setInnerCategoryId(Long innerCategoryId) {
		this.innerCategoryId = innerCategoryId;
	}
}
	