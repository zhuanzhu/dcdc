package com.egeo.components.product.business;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.egeo.components.product.vo.CategoryTreeVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


public interface CategoryTreeManage {

	List<CategoryTreeVO> queryAllCategoryTreeByParam(CategoryTreeVO categoryTreeVO);

	String addCategoryTree(CategoryTreeVO categoryTreeSanVO, HttpServletRequest req);

	List<CategoryTreeVO> queryCategoryTree(Long platformId);

	List<CategoryTreeVO> queryCategoryTreeByType(Long platformId, Integer type);
	/**
	 * 查询前台类目树（目前启用只可能有一棵）
	 */
	Map<String, Object> findCategoryByType(Long companyId,Long enterpriseId,Long clientId, Long platformId,Integer isDefault);
	/**
	 * 分页查询前台类目树
	 * @param req
	 * @return
	 */
	PageResult<Map<String, Object>> findCategoryTreeOfPage(CategoryTreeVO categoryTreeVO, Pagination page);
	/**
	 * 根据类目树id将类目树设为启用
	 */
	boolean categoryTreeStartUsingWithTx(Long categoryTreeId,Integer companyType,Integer clientType, Long platformId);

	boolean categoryTreeStopUsingWithTx(Long categoryTreeId);

	/**
	 * 根据类目树id修改类目树信息
	 * @param vo
	 * @param req
	 * @return
	 */
	boolean updateCategoryTreeWithTx(CategoryTreeVO vo);
	/**
	 * 根据类目树id查询类目树信息
	 */
	Map<String, Object> findByCategoryTreeId(Long categoryTreeId);
	/**
	 * 查询所有类目树信息
	 */
	List<Map<String, Object>> findCategoryTreeAll(Long platformId);

	/**
	 * 	根据type查询目录树,封装返回数据
	 * @param platformId
	 * @param type
	 * @return
	 */
    List<Map<String, Object>> queryCategoryTreeVoByType(Long platformId, Integer type);
}
	