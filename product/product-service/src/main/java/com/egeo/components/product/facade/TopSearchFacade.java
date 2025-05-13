package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.TopSearchReadService;
import com.egeo.components.product.service.write.TopSearchWriteService;
import com.egeo.components.product.dto.TopSearchDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class TopSearchFacade {
	
	@Resource
	private TopSearchReadService topSearchReadService;
	
	@Resource
	private TopSearchWriteService topSearchWriteService;
	
	
	public TopSearchDTO findTopSearchById(TopSearchDTO dto){
		
		return topSearchReadService.findTopSearchById(dto);
	}

	public PageResult<TopSearchDTO> findTopSearchOfPage(TopSearchDTO dto,Pagination page){
		
		return topSearchReadService.findTopSearchOfPage(dto, page);
		
	}

	public List<TopSearchDTO> findTopSearchAll(TopSearchDTO dto){
		
		return topSearchReadService.findTopSearchAll(dto);
		
	}

	public Long insertTopSearchWithTx(TopSearchDTO dto){
		
		return topSearchWriteService.insertTopSearchWithTx(dto);
	}

	public int updateTopSearchWithTx(TopSearchDTO dto){
		
		return topSearchWriteService.updateTopSearchWithTx(dto);
	}

	public int deleteTopSearchWithTx(TopSearchDTO dto){
		
		return topSearchWriteService.deleteTopSearchWithTx(dto);
		
	}
	/**
	 * 查询排序最大值
	 * @return
	 */
	public int sortValueMax(){
		
		return topSearchReadService.sortValueMax();
		
	}
	/**
	 * 查询启用的热搜条数
	 * @return
	 */
	public int findStartTopSearchNum(Long platformId) {
		// TODO Auto-generated method stub
		return topSearchReadService.findStartTopSearchNum(platformId);
	}
	/**
	 * 根据id启用停用热搜
	 * @param topSearchId
	 * @return
	 */
	public int startStopTopSearchWithTx(Long topSearchId) {
		// TODO Auto-generated method stub
		return topSearchWriteService.startStopTopSearchWithTx(topSearchId);
	}

}
	