package com.egeo.components.product.controller.api;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.MerchantProductFeedbackManage;
import com.egeo.components.product.converter.MerchantProductFeedbackConverter;
import com.egeo.components.product.converter.SellPlatformConverter;
import com.egeo.components.product.dto.MerchantProductFeedbackDTO;
import com.egeo.components.product.dto.SellPlatformDTO;
import com.egeo.components.product.facade.MerchantProductFacade;
import com.egeo.components.product.facade.MerchantProductFeedbackFacade;
import com.egeo.components.product.facade.SellPlatformFacade;
import com.egeo.components.product.vo.MerchantProductFeedbackVO;
import com.egeo.components.product.vo.SellPlatformVO;
import com.egeo.components.user.dto.UserExtendDTO;
import com.egeo.entity.CacheUser;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/merchantProductFeedback")
public class MerchantProductFeedbackAction extends BaseSpringController {
	
	@Resource(name="merchantProductFeedback")
	private MerchantProductFeedbackManage merchantProductFeedbackManage;

    @Resource(name = "merchantProductFacade")
    private MerchantProductFacade merchantProductFacade;

    @Resource(name = "sellPlatformFacade")
    private SellPlatformFacade sellPlatformFacade;

    @Resource(name = "merchantProductFeedbackFacade")
    private MerchantProductFeedbackFacade merchantProductFeedbackFacade;

	// 业务方法：
	@RequestMapping(value = "/findMerchantProductFeedbackById")
	@ResponseBody
	public JsonResult<MerchantProductFeedbackVO> findMerchantProductFeedbackById(Long id ) {
		
		MerchantProductFeedbackDTO dto = new MerchantProductFeedbackDTO();
		dto.setId(id);
		MerchantProductFeedbackDTO rt = merchantProductFeedbackManage.findMerchantProductFeedbackById(dto);		
		return JsonResult.success(MerchantProductFeedbackConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findMerchantProductFeedbackAll")
	@ResponseBody
	public JsonResult<List<MerchantProductFeedbackVO>> findMerchantProductFeedbackAll(MerchantProductFeedbackVO vo,HttpServletRequest req ) {
		MerchantProductFeedbackDTO dto = MerchantProductFeedbackConverter.toDTO(vo);
		List<MerchantProductFeedbackDTO> rt = merchantProductFeedbackManage.findMerchantProductFeedbackAll(dto);	
		return JsonResult.success(MerchantProductFeedbackConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findMerchantProductFeedbackOfPage")
	@ResponseBody
	public JsonResult<PageResult<Map<String, Object>>> findMerchantProductFeedbackOfPage(MerchantProductFeedbackVO paramVo,
                                                                            Pagination page,HttpServletRequest req ) {
		String str = req.getHeader("platformId");
        if (EmptyUtil.isNotEmpty(str)) {
            Long platformId = Long.valueOf(str);
            paramVo.setPlatformId(platformId);
        } else {
        	return JsonResult.fail("platformId不能为空");
        }
        // 按照用户反馈时间的倒序排列
        page.setOrderBy(" create_time desc ");
		MerchantProductFeedbackDTO dto = MerchantProductFeedbackConverter.toDTO(paramVo);
		PageResult<MerchantProductFeedbackDTO> rt = merchantProductFeedbackManage.findMerchantProductFeedbackOfPage(dto, page);
        List<Map<String, Object>> list = new ArrayList<>();
        PageResult<Map<String, Object>> result = new PageResult<>();
        for (MerchantProductFeedbackDTO mpf : rt.getList()) {
            Map<String ,Object> temMap = new HashMap<>();
            // 通过平台id查询平台信息
            SellPlatformDTO sfDto = new SellPlatformDTO();
            String receiver ="";
            List<String> imgSrcList = new ArrayList<>();
            sfDto.setId(mpf.getSellPlatformId());
            SellPlatformDTO sfDto_ = sellPlatformFacade.findSellPlatformById(sfDto);

            // 通过受理人id查询受理人信息
            if (EmptyUtil.isNotEmpty(mpf.getReceiverId())) {
                UserExtendDTO user = merchantProductFeedbackFacade.queryUserByUserId(mpf.getReceiverId());
                receiver = user.getMail();
            }
            temMap.put("id", mpf.getId());
            temMap.put("merchantProductSerialNumber", mpf.getMerchantProductSerialNumber());
            temMap.put("merchantProductName", mpf.getMerchantProductName());
            temMap.put("attributeName", mpf.getAttributeName());
            temMap.put("salePrice", mpf.getSalePrice());
            if (EmptyUtil.isNotEmpty(sfDto_)) {
                temMap.put("platformName", sfDto_.getName());
            }
            temMap.put("userName", mpf.getUserName());
            temMap.put("account", mpf.getAccount());
            temMap.put("phone", mpf.getPhone());
            temMap.put("content", mpf.getContent());
            temMap.put("createTime", mpf.getCreateTime());
            temMap.put("feedbackStatus", mpf.getFeedbackStatus());
            temMap.put("receiverId", receiver);
            temMap.put("updateTime", mpf.getUpdateTime());
            temMap.put("createTime", mpf.getCreateTime());
            temMap.put("acceptResult", mpf.getAcceptResult());
            if (EmptyUtil.isNotEmpty(mpf.getCertificateUrl())) {
                imgSrcList = Arrays.asList(mpf.getCertificateUrl().split(","));
            }
            temMap.put("imgSrcList", imgSrcList);
            list.add(temMap);
        }
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		return JsonResult.success(result);
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertMerchantProductFeedbackWithTx")
	@ResponseBody
	public JsonResult<Long> insertMerchantProductFeedbackWithTx(MerchantProductFeedbackVO vo,HttpServletRequest req ) {
		if (vo.getPlatformId() == null) {
			String str = req.getHeader("platformId");
	        if (EmptyUtil.isNotEmpty(str)) {
	            Long platformId = Long.valueOf(str);
	            vo.setPlatformId(platformId);
	        } else {
	        	return JsonResult.fail("platformId不能为空");
	        }
		}
		MerchantProductFeedbackDTO dto = MerchantProductFeedbackConverter.toDTO(vo);
		Long rt = merchantProductFeedbackManage.insertMerchantProductFeedbackWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateMerchantProductFeedbackByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateMerchantProductFeedbackByIdWithTx(MerchantProductFeedbackVO vo,HttpServletRequest req ) {
		MerchantProductFeedbackDTO dto = MerchantProductFeedbackConverter.toDTO(vo);
		int rt = merchantProductFeedbackManage.updateMerchantProductFeedbackWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteMerchantProductFeedbackWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteMerchantProductFeedbackWithTx(MerchantProductFeedbackVO vo,HttpServletRequest req ) {
		MerchantProductFeedbackDTO dto = MerchantProductFeedbackConverter.toDTO(vo);
		int rt = merchantProductFeedbackManage.deleteMerchantProductFeedbackWithTx(dto);	
		return JsonResult.success(rt);					 
	}
    /**
     *查询比价平台信息
     * @param req
     * @return
     */
    @RequestMapping(value = "/findComparePrice")
    @ResponseBody
    public JsonResult<Map<String, Object>> findComparePrice(SellPlatformVO vo, HttpServletRequest req) {
        SellPlatformDTO dto = SellPlatformConverter.toDTO(vo);
        String str = req.getHeader("platformId");
        if(EmptyUtil.isNotEmpty(str)){
            Long platformId = Long.valueOf(str);
            dto.setPlatformId(platformId);
        }
        dto.setStatus(1); // 默认查询有效
        return JsonResult.success(merchantProductFeedbackManage.findComparePrice(dto));
    }

    /**
     * 记录比价反馈信息接口
     * @param puId
     * @param id 比价平台标识
     * @param content
     * @param phone
     * @param picUrl
     * @param req
     * @return
     */
    @RequestMapping(value = "/insertComparePriceInfo")
    @ResponseBody
    public JsonResult<Boolean> insertComparePriceInfo(Long puId, Long id, String content, String phone, String picUrl,
                                                   HttpServletRequest req) throws BusinessException {
        MerchantProductFeedbackDTO dto = new MerchantProductFeedbackDTO();
        if (EmptyUtil.isEmpty(id) || EmptyUtil.isEmpty(puId)) {
            throw new BusinessException("request param is null");
        }
        String str = req.getHeader("platformId");
        if(EmptyUtil.isEmpty(str)){
            return JsonResult.fail("platformId不能为空");
        }
        Long platformId = Long.valueOf(str);
        CacheUser userCache = this.getCacheUser();
        dto.setPlatformId(platformId);
        dto.setMerchantProductId(puId);
        dto.setCertificateUrl(picUrl);
        dto.setContent(content);
        dto.setSellPlatformId(id);
        dto.setPhone(phone);
        dto.setCertificateUrl(picUrl);
        dto.setFeedbackStatus(0); //受理状态 0:未受理 1:已受理
        dto.setUserName(userCache.getName());
        dto.setAccount(userCache.getMail());
        return JsonResult.success(merchantProductFeedbackManage.insertComparePriceInfo(dto));
    }

    /**
     * 受理操作
     * @param id
     * @param acceptResult
     * @param req
     * @return
     */
    @RequestMapping(value = "/doFeedbackOperator")
    @ResponseBody
    public JsonResult<Object> doFeedbackOperator(Long id, String acceptResult, HttpServletRequest req) {
        if (EmptyUtil.isEmpty(id)) {
            throw new BusinessException("request param is null");
        }
        CacheUser userCache = this.getCacheUser();
        if (acceptResult.length() > 100) {
            throw new BusinessException( "受理反馈内容长度不能超过100个字符！");
        }
        MerchantProductFeedbackDTO dto = new MerchantProductFeedbackDTO();
        dto.setId(id);
        dto.setAcceptResult(acceptResult);
        dto.setReceiverId(userCache.getId());
        dto.setUpdateTime(new Date());
        dto.setFeedbackStatus(1); //受理状态 0:未受理(默认值) 1:已受理
        return JsonResult.success(merchantProductFeedbackManage.doFeedbackOperator(dto));
    }



}
	