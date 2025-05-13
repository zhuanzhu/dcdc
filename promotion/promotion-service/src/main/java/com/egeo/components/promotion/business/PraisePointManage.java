package com.egeo.components.promotion.business;

import java.util.List;
	
import com.egeo.components.promotion.dto.PraisePointDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface PraisePointManage {




	Long insertPraisePointWithTx(PraisePointDTO dto);

	int updatePraisePointWithTx(PraisePointDTO dto);

	int deletePraisePointWithTx(PraisePointDTO dto);
}
	