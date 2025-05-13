package com.egeo.components.promotion.controller.api;


import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.promotion.business.ExchangeActivityManage;
import com.egeo.components.promotion.converter.ExchangeActivityConverter;
import com.egeo.components.promotion.dto.ExchangeActivityDTO;
import com.egeo.components.promotion.dto.ExchangeBatchDTO;
import com.egeo.components.promotion.vo.ExchangeActivityVO;
import com.egeo.components.utils.JsonUtils;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/promotion/exchangeActivity")
public class ExchangeActivityAction extends BaseSpringController {
	
	@Resource(name="exchangeActivity")
	private ExchangeActivityManage exchangeActivityManage;


	// 业务方法：
	@RequestMapping(value = "/findExchangeActivityById")
	@ResponseBody
	public JsonResult<ExchangeActivityVO> findExchangeActivityById(Long id ) {
		
		ExchangeActivityDTO dto = new ExchangeActivityDTO();
		dto.setId(id);
		ExchangeActivityDTO rt = exchangeActivityManage.findExchangeActivityById(dto);		
		return success(ExchangeActivityConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findExchangeActivityAll")
	@ResponseBody
	public JsonResult<List<ExchangeActivityVO>> findExchangeActivityAll(ExchangeActivityVO vo,HttpServletRequest req ) {
		ExchangeActivityDTO dto = ExchangeActivityConverter.toDTO(vo);
		List<ExchangeActivityDTO> rt = exchangeActivityManage.findExchangeActivityAll(dto);	
		return success(ExchangeActivityConverter.toVO(rt));
					 
	}	

	// 业务方法：分页查询以旧换新活动列表
	@RequestMapping(value = "/findExchangeActivityOfPage")
	@ResponseBody
	public JsonResult<PageResult<ExchangeActivityVO>> findExchangeActivityOfPage(ExchangeActivityVO vo,Pagination page,HttpServletRequest req ) {
		logger.info("开始分页查询以旧换新活动列表");
		String str = req.getHeader("platformId");
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			vo.setPlatformId(platformId);
		}
		ExchangeActivityDTO dto = ExchangeActivityConverter.toDTO(vo);
		PageResult<ExchangeActivityDTO> rt = exchangeActivityManage.findExchangeActivityOfPage(dto, page);
        List<ExchangeActivityVO> list = ExchangeActivityConverter.toVO(rt.getList());
        PageResult<ExchangeActivityVO> result = new PageResult<ExchangeActivityVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return success(result);
	}

    /**
     * 模糊查询以旧换新活动列表
     * @param vo
     * @param req
     * @return
     */
    @RequestMapping(value = "/fuzzQueryExchangeActivityOfPage")
    @ResponseBody
    public JsonResult<PageResult<ExchangeActivityVO>> fuzzQueryExchangeActivityOfPage(ExchangeActivityVO vo,Pagination page,HttpServletRequest req ) {
		logger.info("开始模糊查询以旧换新活动列表");
		String str = req.getHeader("platformId");
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			vo.setPlatformId(platformId);
		}
        ExchangeActivityDTO dto = ExchangeActivityConverter.toDTO(vo);
		logger.info("voID:"+vo.getId());
		logger.info("dtoID:"+dto.getId());
        PageResult<ExchangeActivityDTO> rt = exchangeActivityManage.fuzzQueryExchangeActivityOfPage(dto, page);
        List<ExchangeActivityVO> list = ExchangeActivityConverter.toVO(rt.getList());
        PageResult<ExchangeActivityVO> result = new PageResult<ExchangeActivityVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());

        return success(result);
    }

	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertExchangeActivityWithTx")
	@ResponseBody
	public JsonResult<Long> insertExchangeActivityWithTx(ExchangeActivityVO vo,HttpServletRequest req ) {
		ExchangeActivityDTO dto = ExchangeActivityConverter.toDTO(vo);
		Long rt = exchangeActivityManage.insertExchangeActivityWithTx(dto);	
		return success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateExchangeActivityByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateExchangeActivityByIdWithTx(ExchangeActivityVO vo,HttpServletRequest req ) {
		ExchangeActivityDTO dto = ExchangeActivityConverter.toDTO(vo);
		int rt = exchangeActivityManage.updateExchangeActivityWithTx(dto);	
		return success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteExchangeActivityWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteExchangeActivityWithTx(ExchangeActivityVO vo,HttpServletRequest req ) {
		ExchangeActivityDTO dto = ExchangeActivityConverter.toDTO(vo);
		int rt = exchangeActivityManage.deleteExchangeActivityWithTx(dto);	
		return success(rt);					 
	}


	/**
	 * 新建/编辑以旧换新
	 */
	@RequestMapping("/insertOrUpdateExchangeActivityWithTx")
	@ResponseBody
	public JsonResult<String> insertOrUpdateExchangeActivityWithTx(Long exchangeId,String exchangeName,Long endDay,String oldBatchIds,String newBatch,String unitStatus,HttpServletRequest req){
		logger.info("[新建/编辑以旧换新]参数:exchangeId="+exchangeId);
		logger.info("[新建/编辑以旧换新]参数:exchangeName="+exchangeName);
		logger.info("[新建/编辑以旧换新]参数:endDay="+endDay);
		logger.info("[新建/编辑以旧换新]参数:oldBatchIds="+oldBatchIds);
		logger.info("[新建/编辑以旧换新]参数:newBatch="+newBatch);
		logger.info("[新建/编辑以旧换新]参数:unitStatus="+unitStatus);
		String str = req.getHeader("platformId");
		if(EmptyUtil.isEmpty(str)){
			return fail("platformId不能为空");
		}
		Long platformId = Long.valueOf(str);
		if(EmptyUtil.isEmpty(exchangeName)){
			return fail("活动名缺失");
		}
		if(EmptyUtil.isEmpty(endDay)){
			return fail("截止时间缺失");
		}
		//截止时间处理(截止时间为当天的23:59:59)
		Date endTime = new Date(endDay+(24*60*60-1)*1000);
		if(EmptyUtil.isEmpty(oldBatchIds)){
			return fail("旧批次id缺失");
		}
		List<Long> oldBatchIdList = JsonUtils.jsonToList(oldBatchIds, Long.class);
		if(EmptyUtil.isEmpty(newBatch)){
			return fail("新批次缺失");
		}
		if(oldBatchIdList.size()>50){
			return fail("新批次数量必须小于等于50");
		}
		List<ExchangeBatchDTO> newBatchList = JsonUtils.jsonToList(newBatch,ExchangeBatchDTO.class);
		if(EmptyUtil.isEmpty(unitStatus)){
			return fail("可兑换的unit状态缺失");
		}
		List<Integer> unitStatusList = JsonUtils.jsonToList(unitStatus,Integer.class);
		if(EmptyUtil.isEmpty(newBatchList)||EmptyUtil.isEmpty(unitStatusList)||EmptyUtil.isEmpty(oldBatchIdList)){
			return fail("json数据有误");
		}
		if(newBatchList.size()>50){
			return fail("新批次数量必须小于等于50");
		}
		Set<Long> set = new HashSet<>();
		for(ExchangeBatchDTO dto:newBatchList){
			set.add(dto.getSort());
		}
		if(set.size()!=newBatchList.size()){
			return fail("sort排序值不能重复");
		}
		ExchangeActivityDTO exchangeActivityDTO = new ExchangeActivityDTO();
		exchangeActivityDTO.setId(exchangeId);
		exchangeActivityDTO.setEndTime(endTime);
		exchangeActivityDTO.setExchangeName(exchangeName);
		exchangeActivityDTO.setPlatformId(platformId);
		exchangeActivityDTO.setNewBatchList(newBatchList);
		exchangeActivityDTO.setOldBatchIdList(oldBatchIdList);
		exchangeActivityDTO.setUnitStatus(unitStatusList);

		int rt=exchangeActivityManage.insertOrUpdateExchangeActivityWithTx(exchangeActivityDTO);
		return success("添加/更新成功");

	}

	/**
	 * 查询以旧换新活动
	 * @param vo
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/findExchangeActivity")
	@ResponseBody
	public JsonResult<Map<String, Object>> findExchangeActivity(ExchangeActivityVO vo, HttpServletRequest req ) {
		logger.info("查询以旧换新活动");
		String str = req.getHeader("platformId");
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			vo.setPlatformId(platformId);
		}
		ExchangeActivityDTO dto = ExchangeActivityConverter.toDTO(vo);
		logger.info("id:"+dto.getId());
		logger.info("platformId:"+dto.getPlatformId());
		JsonResult<Map<String, Object>> result = exchangeActivityManage.findExchangeActivity(dto);
		return result;

	}


	/**
	 * 停用或启用以旧换新活动
	 * @param status
	 * @param exchangeId
	 * @return
	 */
	@RequestMapping("/updateExchangeActivityStatus")
	@ResponseBody
	public JsonResult updateExchangeActivityStatus(Integer status,Long exchangeId){
		logger.info("[停用或启用以旧换新活动]参数:status="+status);
		logger.info("[停用或启用以旧换新活动]参数:exchangeId="+exchangeId);
		if(EmptyUtil.isEmpty(status)){
			return fail("状态参数缺失");
		}

		if(EmptyUtil.isEmpty(exchangeId)){
			return fail("以旧换新活动缺失");
		}
		exchangeActivityManage.updateExchangeActivityStatus(status,exchangeId);
		return success("更新成功");
	}

}
	