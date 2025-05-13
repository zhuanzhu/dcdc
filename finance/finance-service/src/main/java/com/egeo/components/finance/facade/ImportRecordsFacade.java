package com.egeo.components.finance.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.config.client.HeadImportRecordsClient;
import com.egeo.components.config.client.ImportRecordsClient;
import com.egeo.components.config.dto.HeadImportRecordsDTO;
import com.egeo.components.config.dto.ImportRecordsDTO;

@Component
public class ImportRecordsFacade {

	@Autowired
	private ImportRecordsClient importRecordsReadService;// 草稿

	@Autowired
	private ImportRecordsClient importRecordsWriteService;// 草稿

	@Autowired
	private HeadImportRecordsClient headImportRecordsReadService;// 正式

	@Autowired
	private HeadImportRecordsClient headImportRecordsWriteService;// 正式

	/**
	 * 根据sn查询正式导入记录
	 * 
	 * @param sn
	 * @return
	 */
	public HeadImportRecordsDTO queryRecordBySn(String sn) {
		return headImportRecordsReadService.queryRecordBySn(sn);
	}

	/**
	 * 新增
	 * 
	 * @param ir
	 * @return
	 */
	public Long insert(ImportRecordsDTO ir) {
		return importRecordsWriteService.insertImportRecordsWithTx(ir);
	}

	/**
	 * 新增正式头信息
	 * 
	 * @param impRec
	 * @return
	 */
	public Long insertHeadImportRecords(HeadImportRecordsDTO impRec) {
		return headImportRecordsWriteService.insertHeadImportRecordsWithTx(impRec);
	}

	/**
	 * 查询头信息草稿
	 * @param sn
	 * @return
	 */
	public ImportRecordsDTO queryRecordTempBySn(String sn) {
		return importRecordsReadService.queryRecordTempBySn(sn);
	}

	/**
	 * 根据sn删除头文件草稿,避免sn再头文件中的重复
	 * @param sn
	 */
	public int deleteBySn(String sn) {
		return importRecordsWriteService.deleteBySn(sn);
		
	}
	
	/**
	 * 根据sn查询头记录列表
	 * @param sn
	 * @return
	 */
	public List<HeadImportRecordsDTO> queryRecordsBySn(String sn) {
		
		return headImportRecordsReadService.queryRecordsBySn(sn);
	}

    public List<HeadImportRecordsDTO> queryRecordByParams(HeadImportRecordsDTO recordsDTO) {
		return headImportRecordsReadService.findHeadImportRecordsAll(recordsDTO);
    }
}
