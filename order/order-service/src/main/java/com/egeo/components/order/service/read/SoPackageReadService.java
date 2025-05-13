package com.egeo.components.order.service.read;


import java.util.List;
	
import com.egeo.components.order.dto.SoPackageDTO;
import com.egeo.components.order.po.SoPackagePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface SoPackageReadService {

	public SoPackageDTO findSoPackageById(SoPackageDTO dto);

	public PageResult<SoPackageDTO> findSoPackageOfPage(SoPackageDTO dto,Pagination page);

	public List<SoPackageDTO> findSoPackageAll(SoPackageDTO dto);

	public List<SoPackageDTO> packageByOrderCode(String orderCode);

	public PageResult<SoPackageDTO> findSoPackageAndBoxOfPage(SoPackageDTO dto, Pagination page);

	/**
	 * 根据子订单id查询包裹
	 * @param id
	 * @return
	 */
	public List<SoPackageDTO> queryPackageBySoChildId(Long id);

    String findDeliveryCompanyNameBySoChildId(Long soChildId);

	List<SoPackageDTO> findUnReceive(SoPackageDTO dto);
}
	