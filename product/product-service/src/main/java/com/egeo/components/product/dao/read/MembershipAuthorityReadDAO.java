package com.egeo.components.product.dao.read;

import java.util.List;

import com.egeo.components.product.condition.MembershipAuthorityCondition;
import com.egeo.components.product.po.MembershipAuthorityPO;
import com.egeo.orm.BaseReadDAO;

public interface MembershipAuthorityReadDAO extends BaseReadDAO<MembershipAuthorityPO>{
	
	List<MembershipAuthorityCondition> findModifyYesterday();
	
}
	