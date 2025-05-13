package com.egeo.components.order.controller.api;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.egeo.components.order.dto.ReceiverAddressDTO;
import com.egeo.components.order.facade.ReceiverAddressFacade;
import com.egeo.config.RuntimeContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.egeo.components.order.business.CartItemManage;
import com.egeo.components.order.converter.CartItemConverter;
import com.egeo.components.order.dto.CartItemDTO;
import com.egeo.components.order.vo.CartItemVO;
import com.egeo.entity.CacheUser;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/order/cartItem")
public class CartItemAction extends BaseSpringController {

	@Resource(name="cartItem")
	private CartItemManage cartItemManage;


	// 业务方法：
	@RequestMapping(value = "/findCartItemById")
	@ResponseBody
	public JsonResult<CartItemVO> findCartItemById(Long id ) {
		CartItemDTO rt = cartItemManage.findCartItemById(id);
		return JsonResult.success(CartItemConverter.toVO(rt));

	}



	// 业务方法：
	@RequestMapping(value = "/findCartItemAll")
	@ResponseBody
	public JsonResult<List<CartItemVO>> findCartItemAll(CartItemVO vo,HttpServletRequest req ) {
		CartItemDTO dto = CartItemConverter.toDTO(vo);
		String str = req.getHeader("platformId");
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<CartItemDTO> rt = cartItemManage.findCartItemAll(dto);
		return JsonResult.success(CartItemConverter.toVO(rt));

	}

	// 业务方法：
	@RequestMapping(value = "/findCartItemOfPage")
	@ResponseBody
	public JsonResult<PageResult<CartItemVO>> findCartItemOfPage(CartItemVO vo,Pagination page,HttpServletRequest req ) {
		CartItemDTO dto = CartItemConverter.toDTO(vo);
		String str = req.getHeader("platformId");
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<CartItemDTO> rt = cartItemManage.findCartItemOfPage(dto, page);
        List<CartItemVO> list = CartItemConverter.toVO(rt.getList());
        PageResult<CartItemVO> result = new PageResult<CartItemVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());

		return JsonResult.success(result);

	}

	/**
	 * 根据用户id查询购物车pu信息
	 * @param page
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/findCartItemOfPageByUserId")
	@ResponseBody
	public JsonResult<PageResult<Map<String, Object>>> findCartItemOfPageByUserId(Long storeId,Pagination page,HttpServletRequest req) {
		logger.info("根据用户id查询购物车pu信息");
		String platformIdStr = req.getHeader("platformId");
		if(EmptyUtil.isEmpty(platformIdStr)){
			throw new BusinessException("平台id不能为空");
		}
		Long platformId = Long.valueOf(platformIdStr);
		if(EmptyUtil.isEmpty(storeId)){
			if(platformId==2){
				logger.info("富宏云采购物车");
				storeId=2L;
			}
			if(platformId==7){
				logger.info("大厨管家购物车");
				storeId = 1L;
			}
		}

		String clientIdStr = req.getHeader("clientId");
		if(EmptyUtil.isEmpty(clientIdStr)){
			throw new BusinessException("客户端id不能为空");
		}
		Long clientId = Long.valueOf(clientIdStr);
		CacheUser userCache = this.getCacheUser();
		Long userId = userCache.getId(); // 用户id
		Long companyId = userCache.getCompanyId(); // 公司id
		PageResult<Map<String, Object>> rt = cartItemManage.findCartItemOfPageByUserId(clientId,userId,companyId,platformId,storeId, page);
		return JsonResult.success(rt);

	}

	/**
	 * 根据用户id查询购物车pu数量
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/findCartItemSumByUserId")
	@ResponseBody
	public JsonResult<Integer> findCartItemSumByUserId(HttpServletRequest req,Long storeId ) {
		logger.info("根据用户id查询购物车pu信息");
		String platformIdStr = req.getHeader("platformId");
		if(EmptyUtil.isEmpty(platformIdStr)){
			throw new BusinessException("平台id不能为空");
		}
		Long platformId = Long.valueOf(platformIdStr);
		String clientIdStr = req.getHeader("clientId");
		if(EmptyUtil.isEmpty(clientIdStr)){
			throw new BusinessException("客户端id不能为空");
		}
		Long clientId = Long.valueOf(clientIdStr);
		CacheUser userCache = this.getCacheUser();
		if(EmptyUtil.isEmpty(storeId)||storeId==0){
			if(platformId==7){
				storeId=1L;
			}
			if(platformId==2){
				storeId=2L;

			}

		}
		Long userId = userCache.getId();
		Integer rt = cartItemManage.findCartItemSumByUserId(storeId,userId,platformId,clientId);
		return JsonResult.success(rt);

	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertCartItemWithTx")
	@ResponseBody
	public JsonResult<Long> insertCartItemWithTx(CartItemVO vo,HttpServletRequest req ) {
		CartItemDTO dto = CartItemConverter.toDTO(vo);
		String str = req.getHeader("platformId");
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = cartItemManage.insertCartItemWithTx(dto);
		return JsonResult.success(rt);
	}

	/**
	 * 加入购物车(传递puid和num)
	 * @param vo
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/saveCartItemWithTx",method = RequestMethod.POST)
	@ResponseBody
	public JsonResult<String> saveCartItemWithTx(CartItemVO vo,HttpServletRequest req, Long storeId, Integer source) {
		logger.info("加入购物车");
		logger.info("参数puId"+vo.getProductUnitId());
		logger.info("参数num"+vo.getNum());

		CartItemDTO dto = CartItemConverter.toDTO(vo);
		String str = req.getHeader("platformId");
		Long platformId ;
		if(EmptyUtil.isNotEmpty(str)){
			 platformId=Long.valueOf(str);
			dto.setPlatformId(platformId);
		}else {
			return JsonResult.fail("platformId不能为空");
		}
		if(EmptyUtil.isEmpty(storeId)){
			if(platformId==7){
				logger.info("大厨管家购物车");
				storeId = 1L;
			}
			if(platformId==2){
				logger.info("富宏云采购物车");
				storeId=2L;
			}

		}
		String clientIdStr = req.getHeader("clientId");
		Long clientId = Long.valueOf(clientIdStr);
		CacheUser userCache = this.getCacheUser();
		Long userId = userCache.getId();
		if(source!=null && (source.intValue()==3 || source.intValue() ==4 || source.intValue()==5)) {

		}else {
			//校验商品是否属于该门店
			cartItemManage.checkPUStore(vo.getProductUnitId(),storeId);
		}


		// 根据用户id查询用户购物车pu种类数量(不区分销售方式)
		Integer sum = cartItemManage.findCartItemPUSumByUserId(userId,dto.getPlatformId(),storeId);
		if(EmptyUtil.isNotEmpty(sum)){
			if(sum > 50){
				throw new BusinessException("购物车pu种类数量不能超过50");
			}
		}

		return cartItemManage.saveCartItemWithTx(dto,userId,platformId,storeId,clientId,source);


	}

	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateCartItemByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateCartItemByIdWithTx(CartItemVO vo,HttpServletRequest req ) {
		CartItemDTO dto = CartItemConverter.toDTO(vo);
		String str = req.getHeader("platformId");
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = cartItemManage.updateCartItemWithTx(dto);
		return JsonResult.success(rt);
	}

	/**
	 * 根据购物车id集合批量删除购物车pu商品关系
	 * @param id 兼容上版本 按id删除购物车项
	 * @param cartItemIds 购物车项id集合
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/deleteCartItemWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteCartItemWithTx(Long id,String cartItemIds,HttpServletRequest req ) {
		logger.info("根据购物车id集合批量删除购物车pu商品关系");
		List<Long> cartItemIdList = new ArrayList<>();
		if(EmptyUtil.isNotEmpty(cartItemIds))
			cartItemIdList = new ArrayList<>(JSONArray.parseArray(cartItemIds, Long.class));
		cartItemIdList.add(id);
		if(EmptyUtil.isEmpty(cartItemIdList)){
			throw new BusinessException("请选择购物车商品");
		}
		int rt = cartItemManage.deleteCartItemByIdsWithTx(cartItemIdList);
		return JsonResult.success(rt);
	}

	@Autowired
	ReceiverAddressFacade receiverAddressService;

	/**
	 * 根据购物车pu商品关系id把购物车数量加一
	 * @param cartItemId
	 * @return
	 */
	@RequestMapping(value = "/addNum")
	@ResponseBody
	public JsonResult<String> addNumWithTx(Long cartItemId) {
		logger.info("根据购物车pu商品关系id把购物车数量加一");
		CartItemDTO cartItemDTO2 = cartItemManage.findCartItemById(cartItemId);
		if(EmptyUtil.isNotEmpty(cartItemDTO2) && cartItemDTO2.getSource()!=null && (cartItemDTO2.getSource().intValue()==3 || cartItemDTO2.getSource().intValue()==4 || cartItemDTO2.getSource().intValue()==5)) {
			ReceiverAddressDTO receiver = receiverAddressService.queryDefaultReceiverAddress(RuntimeContext.cacheUser().getId(), 7L);
			if (receiver == null) {
				return JsonResult.fail("请先设置收货地址", 504);
			}
		}
		int rt = cartItemManage.addNumWithTx(cartItemId);
		if(rt != 0){
			return JsonResult.success("购物车数量加一成功");
		}else{
			return JsonResult.success("购物车数量加一失败");
		}

	}

	/**
	 * 根据购物车pu商品关系id把购物车数量减一
	 * @param cartItemId
	 * @return
	 */
	@RequestMapping(value = "/minusNum")
	@ResponseBody
	public JsonResult<String> minusNumWithTx(Long cartItemId) {
		logger.info("根据购物车pu商品关系id把购物车数量减一");
		int rt = cartItemManage.minusNumWithTx(cartItemId);
		if(rt != 0){
			return JsonResult.success("购物车数量减一成功");
		}else{
			return JsonResult.success("购物车数量减一失败");
		}
	}

}
