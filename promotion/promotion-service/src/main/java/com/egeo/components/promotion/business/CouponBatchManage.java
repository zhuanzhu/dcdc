package com.egeo.components.promotion.business;

import java.util.List;
import java.util.Map;

import com.egeo.components.promotion.dto.CouponBatchDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.JsonResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CouponBatchManage {

	public CouponBatchDTO findCouponBatchById(CouponBatchDTO dto);

	public PageResult<CouponBatchDTO> findCouponBatchOfPage(CouponBatchDTO dto, Pagination page);

	public List<CouponBatchDTO> findCouponBatchAll(CouponBatchDTO dto);

	Long insertCouponBatchWithTx(CouponBatchDTO dto);

	/**
	 * 显示/隐藏优惠卷批次
	 * 
	 * @param dto
	 * @return
	 */
	JsonResult<Integer> updateCouponBatchWithTx(CouponBatchDTO dto);

	int deleteCouponBatchWithTx(CouponBatchDTO dto);

	/**
	 * 新建或编辑优惠卷批次
	 *  @param dto
	 * @param channelActivityId
     * @param channelId @return
     */
	public JsonResult<Map<String, Object>> insertOrUpdateCouponBatchWithTx(CouponBatchDTO dto, Long channelActivityId, Long channelId);

	/**
	 * 将优惠卷批次设置为失效
	 * 
	 * @param dto
	 * @return
	 */
	public JsonResult<Integer> invalidCouponBatchWithTx(CouponBatchDTO dto);

	/**
	 * 查询优惠卷批次详情通过id
	 * 
	 * @param dto
	 * @return
	 */
	public Map<String, Object> findCouponBatchInfoById(CouponBatchDTO dto);

	/**
	 * 通过优惠卷批次查询公司信息
	 * 
	 * @param dto
	 * @return
	 */
	public List<Map<String, Object>> findCompanyByCouponBatch(CouponBatchDTO dto);

	/**
	 * 查询优惠卷批次已选择的员工
	 * 
	 * @param dto
	 * @return
	 */
	public PageResult<Map<String, Object>> findEmployeeByCouponBatch(CouponBatchDTO dto, Pagination page);

	/**
	 * 查询优惠卷批次的所有员工
	 * 
	 * @param dto
	 * @param dto2
	 * @param page
	 * @return
	 */
	public PageResult<Map<String, Object>> findAllEmployeeByCouponBatch(CouponBatchDTO dto, Pagination page);

	/**
	 * 优惠卷批次列表
	 * @param userId
	 * @param dto
	 * @param page
	 * @return
	 */
	public PageResult<Map<String, Object>> findCouponBatchOfPageByBlurry(Long userId, CouponBatchDTO dto, Pagination page);

	/**
	 *用户注册自动领取优惠券
	 * @param userId
	 * @param dto
	 * @return
	 */
    public Map<String, Object> registerGetCoupon(Long userId, CouponBatchDTO dto);

    JsonResult<String> exportCouponUnit(Long platformId, List<Long> couponBatchList, HttpServletResponse response);

	JsonResult<PageResult<CouponBatchDTO>> findCouponBatchByParam(List<Long> batchIdList, String title, String couponBatchCode,String couponBatchName, Integer type, Long platformId, Pagination page);

    JsonResult updateCouponUnitStatus(Long couponBatchId, Long startNum, Long endNum);

    JsonResult<Map<String,Object>> importQuitUserWithTx(Long platformId, List<Map<String, Object>> valueList, HttpServletRequest req);

	List<Long> assureImportUser(Long userId, Long platformId, String serialNum, Long importUserInfo);

    void deleteCache(Long userId);
}
