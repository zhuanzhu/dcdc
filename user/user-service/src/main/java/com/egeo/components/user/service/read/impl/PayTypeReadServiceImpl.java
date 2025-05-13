package com.egeo.components.user.service.read.impl;

import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.egeo.components.user.service.read.PayTypeReadService;
import com.egeo.components.user.dto.PayTypeDTO;
import com.egeo.components.user.po.PayTypePO;
import com.egeo.components.user.dao.read.PayTypeReadDAO;
import com.egeo.components.user.converter.PayTypeConverter;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("payTypeReadService")
public class PayTypeReadServiceImpl implements PayTypeReadService {
	@Autowired
	private PayTypeReadDAO payTypeReadDAO ;

	@Override
	public PayTypeDTO findPayTypeById(PayTypeDTO dto){
		PayTypePO po = PayTypeConverter.toPO(dto);
		PayTypePO payTypepo = new PayTypePO();
		payTypepo.setId(po.getId());
		PayTypePO list = payTypeReadDAO.findById(payTypepo);
		return PayTypeConverter.toDTO(list);
	}
	@Override
	public PageResult<PayTypeDTO> findPayTypeOfPage(PayTypeDTO dto, Pagination page) {
		PayTypePO po = PayTypeConverter.toPO(dto);
		PageResult<PayTypePO> pageResult = new PageResult<PayTypePO>();
		List<PayTypePO> listT = null;
		int cnt = payTypeReadDAO.countOfPage(po);
		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			listT = payTypeReadDAO.findOfPage(po, page);
		} else {
			listT = new ArrayList<PayTypePO>();
		}
		pageResult.setList(listT);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		
        List<PayTypeDTO> list = PayTypeConverter.toDTO(pageResult.getList());
        PageResult<PayTypeDTO> result = new PageResult<PayTypeDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<PayTypeDTO> findPayTypeAll(PayTypeDTO dto) {
		PayTypePO po = PayTypeConverter.toPO(dto);
		List<PayTypePO> list = payTypeReadDAO.findAll(po,null);
		return PayTypeConverter.toDTO(list);
	}

	public PayTypeDTO findPayTypeByCode(Integer code){
		return PayTypeConverter.toDTO(payTypeReadDAO.findByCode(code));
	}

	@Override
	public List<PayTypeDTO> findPayTypeByCodes(List<Integer> codes) {
		return PayTypeConverter.toDTO(payTypeReadDAO.findByCodes(codes));
	}

}
	