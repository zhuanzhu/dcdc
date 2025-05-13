package com.egeo.components.product.business;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.egeo.components.product.vo.CategoryTreeNodeVO;
import com.egeo.components.product.dto.CategoryTreeDTO;
import com.egeo.components.product.dto.CategoryTreeNodeDTO;
import com.egeo.orm.Pagination;



public interface CategoryTreeNodeManage {
	
	List<Map<String, Object>> getCategoryTree(Long categoryId,Long platformId);

	CategoryTreeNodeDTO CategoryTreeNodeByCategoryId(Long parentId);

	String addCategoryTreeNode(CategoryTreeNodeVO categoryTreeNodeVO,String listSort,List<Long> tagIdList, HttpServletRequest req);

	CategoryTreeNodeDTO findById(CategoryTreeNodeVO categoryTreeNodeVO);

	List<CategoryTreeNodeDTO> findAll(CategoryTreeNodeVO categoryTreeNodeVO);

	CategoryTreeNodeDTO findCategoryTreeNodeInofByNodeId(Long ctnId);

	int listSortMax();

	int updateCategoryTreeNodeWithTx(CategoryTreeNodeDTO categoryTreeNodeDTO);

	int deleteByIdWithTx(CategoryTreeNodeDTO categoryTreeNodeDTO);
	/**
	 * 根据类目节点id查询子类目节点信息
	 * @param categoryTreeNodeId
	 * @return
	 */
	List<Map<String, Object>> findSeedCategoryTreeNodeAll(Long categoryTreeNodeId);
	/**
	 * 添加前台类目节点信息
	 * @param categoryTreeNodeVO
	 * @return
	 */
	Long saveCategoryTreeNodeWithTx(CategoryTreeNodeVO categoryTreeNodeVO,String listSort,
			String linkableButtonPageList,String extParam);

	/**
	 * 根据类目节点id修改前台类目节点信息
	 * @param categoryTreeNodeVO
	 * @param listSort
	 * @return
	 */
	boolean updateCategoryTreeNodeCategoryWithTx(CategoryTreeNodeVO categoryTreeNodeVO, String listSort,
			String linkableButtonPageList,String extParam);

	/**
	 * 根据前台类目节点id查询前台类目节点信息
	 * @param categoryTreeNodeId
	 * @return
	 */
	Map<String, Object> findByCategoryTreeNodeId(Long categoryTreeNodeId);

	/**
	 * 根据前台类目节点id删除前台类目节点及子节点信息
	 * @param categoryTreeNodeId
	 * @return
	 */
	boolean delByCategoryTreeNodeIdWithTx(Long categoryTreeNodeId);

	/**
	 * 根据类目树id查询前台类目节点信息
	 * @param categoryTreeId
	 * @return
	 */
	List<Map<String, Object>> findByCategoryTreeId(Long categoryTreeId);
	/**
	 * 根据类目树id查询类目树信息
	 * @param categoryTreeId
	 * @return
	 */
	CategoryTreeDTO findCategoryTreeByCategoryTreeNodeId(Long categoryTreeId);
	
	/**
	 * 根据用户id查询web前台类目树
	 * @param userId
	 * @return
	 */
	Map<String, Object> findWebCategoryByUserId(Long userId,Pagination page,Long clientId, Long platformId);

	/**
	 * 根据type查询后台类目树节点信息,封装返回VO
	 * @param categoryTreeId
	 * @param platformId
	 * @return
	 */
	List<Map<String, Object>> queryAllCategoryTreeNodeListVoByType(Long categoryTreeId, Long platformId);
	
	/**
     * 查找子类目中的最大序列号
     * @param categoryTreeId
     * @param parentId
     * @return
     */
	String findMaxSubSerialNumber(Long categoryTreeId, Long parentId);

	/**
	 * 查找最大序列号
	 * @param categoryTreeId
	 * @param parentId
	 * @return
	 */
	String getMaxSubSerialNumber(Long categoryTreeId, Long parentId);
}
	