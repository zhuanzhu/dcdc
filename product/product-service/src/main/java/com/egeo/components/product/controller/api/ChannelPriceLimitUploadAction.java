package com.egeo.components.product.controller.api;


import com.egeo.components.product.business.ChannelPriceLimitUploadManage;
import com.egeo.components.product.converter.ChannelPriceLimitUploadConverter;
import com.egeo.components.product.dto.ChannelPriceLimitUploadDTO;
import com.egeo.components.product.vo.ChannelPriceLimitUploadVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;


@Controller
@RequestMapping("/api/product/channelPriceLimitUpload")
public class ChannelPriceLimitUploadAction extends BaseSpringController {

	@Resource(name="channelPriceLimitUpload")
	private ChannelPriceLimitUploadManage channelPriceLimitUploadManage;


	// 业务方法：
	@RequestMapping(value = "/findChannelPriceLimitUploadById")
	@ResponseBody
	public JsonResult<ChannelPriceLimitUploadVO> findChannelPriceLimitUploadById(ChannelPriceLimitUploadVO vo) {
		if(EmptyUtil.isEmpty(vo.getChannelCode())){
			return JsonResult.fail("channelCode必填");
		}
		ChannelPriceLimitUploadDTO dto = getSingleDTO(vo);
		return JsonResult.success(ChannelPriceLimitUploadConverter.toVO(dto));
	}

	private ChannelPriceLimitUploadDTO getSingleDTO(ChannelPriceLimitUploadVO vo){
		ChannelPriceLimitUploadDTO dto =ChannelPriceLimitUploadConverter.toDTO(vo);
		if(Objects.nonNull(dto) && EmptyUtil.isNotEmpty(dto.getId())){
			return channelPriceLimitUploadManage.findChannelPriceLimitUploadById(dto);
		}
		List<ChannelPriceLimitUploadDTO> rt = channelPriceLimitUploadManage.findChannelPriceLimitUploadAll(dto);
		if(CollectionUtils.isEmpty(rt)){
			return null;
		}
		return rt.get(0);
	}

	// 业务方法：
	@RequestMapping(value = "/findChannelPriceLimitUploadAll")
	@ResponseBody
	public JsonResult<List<ChannelPriceLimitUploadVO>> findChannelPriceLimitUploadAll(ChannelPriceLimitUploadVO vo,HttpServletRequest req ) {
		ChannelPriceLimitUploadDTO dto = ChannelPriceLimitUploadConverter.toDTO(vo);
		List<ChannelPriceLimitUploadDTO> rt = channelPriceLimitUploadManage.findChannelPriceLimitUploadAll(dto);
		return JsonResult.success(ChannelPriceLimitUploadConverter.toVO(rt));

	}

	// 业务方法：
	@RequestMapping(value = "/findChannelPriceLimitUploadOfPage")
	@ResponseBody
	public JsonResult<PageResult<ChannelPriceLimitUploadVO>> findChannelPriceLimitUploadOfPage(ChannelPriceLimitUploadVO vo,Pagination page,HttpServletRequest req ) {
		ChannelPriceLimitUploadDTO dto = ChannelPriceLimitUploadConverter.toDTO(vo);
		PageResult<ChannelPriceLimitUploadDTO> rt = channelPriceLimitUploadManage.findChannelPriceLimitUploadOfPage(dto, page);
		List<ChannelPriceLimitUploadVO> list = ChannelPriceLimitUploadConverter.toVO(rt.getList());
		PageResult<ChannelPriceLimitUploadVO> result = new PageResult<ChannelPriceLimitUploadVO>();
		result.setList(list);
		result.setPageNo(rt.getPageNo());
		result.setPageSize(rt.getPageSize());
		result.setTotalSize(rt.getTotalSize());

		return JsonResult.success(result);

	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertChannelPriceLimitUploadWithTx")
	@ResponseBody
	public JsonResult<Long> insertChannelPriceLimitUploadWithTx(ChannelPriceLimitUploadVO vo,HttpServletRequest req ) {
		if(EmptyUtil.isEmpty(vo.getChannelCode())){
			return JsonResult.fail("渠道channelCode不能为空");
		}
		ChannelPriceLimitUploadDTO dto = ChannelPriceLimitUploadConverter.toDTO(vo);
		Long rt = channelPriceLimitUploadManage.insertChannelPriceLimitUploadWithTx(dto);
		return JsonResult.success(rt);
	}

	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateChannelPriceLimitUploadByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateChannelPriceLimitUploadByIdWithTx(ChannelPriceLimitUploadVO vo, HttpServletRequest req ) {
		if(EmptyUtil.isEmpty(vo.getChannelCode()) && EmptyUtil.isEmpty(vo.getId())){
			return JsonResult.fail("渠道channelCode,id两者其一不能为空");
		}
		if(EmptyUtil.isEmpty(vo.getJdPriceLimitEnd())||EmptyUtil.isEmpty(vo.getJdPriceLimitStart())){
			return JsonResult.fail("设置的值不能为空");
		}
		ChannelPriceLimitUploadDTO dto = getSingleDTO(vo);
		if(Objects.isNull(dto)){
			return JsonResult.fail("设置的值不存在");
		}
		int rt = channelPriceLimitUploadManage.updateChannelPriceLimitUploadWithTx(dto);
		return JsonResult.success(rt);
	}

	// 业务方法：
	@RequestMapping(value = "/deleteChannelPriceLimitUploadWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteChannelPriceLimitUploadWithTx(ChannelPriceLimitUploadVO vo,HttpServletRequest req ) {
		if(EmptyUtil.isEmpty(vo.getChannelCode()) && EmptyUtil.isEmpty(vo.getId())){
			return JsonResult.fail("渠道channelCode,id两者其一不能为空");
		}
		ChannelPriceLimitUploadDTO dto = getSingleDTO(vo);
		if(Objects.isNull(dto)){
			return JsonResult.success(0);
		}
		int rt = channelPriceLimitUploadManage.deleteChannelPriceLimitUploadWithTx(dto);
		return JsonResult.success(rt);
	}

}
