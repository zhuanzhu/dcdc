package com.egeo.components.user.facade;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.egeo.components.user.dto.DownloadDTO;
import com.egeo.components.user.service.read.DownloadReadService;
import com.egeo.components.user.service.write.DownloadWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class DownloadFacade {
	
	@Resource
	private DownloadReadService downloadReadService;
	
	@Resource
	private DownloadWriteService downloadWriteService;
	
	
	public DownloadDTO findDownloadById(DownloadDTO dto){
		
		return downloadReadService.findDownloadById(dto);
	}

	public PageResult<DownloadDTO> findDownloadOfPage(DownloadDTO dto,Pagination page){
		
		return downloadReadService.findDownloadOfPage(dto, page);
		
	}

	public List<DownloadDTO> findDownloadAll(DownloadDTO dto){
		
		return downloadReadService.findDownloadAll(dto);
		
	}

	public Long insertDownloadWithTx(DownloadDTO dto){
		
		return downloadWriteService.insertDownloadWithTx(dto);
	}

	public int updateDownloadWithTx(DownloadDTO dto){
		
		return downloadWriteService.updateDownloadWithTx(dto);
	}

	public int deleteDownloadWithTx(DownloadDTO dto){
		
		return downloadWriteService.deleteDownloadWithTx(dto);
		
	}

	public PageResult<DownloadDTO> findDownloadOfPageByBlurry(DownloadDTO dto, Pagination page) {
		return downloadReadService.findDownloadOfPageByBlurry(dto,page);
	}

}
	