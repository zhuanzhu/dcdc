package com.egeo.components.product.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.condition.MembershipCondition;
import com.egeo.components.product.po.MembershipPO;
import com.egeo.orm.BaseReadDAO;
import com.egeo.orm.Pagination;

public interface MembershipReadDAO extends BaseReadDAO<MembershipPO>{
    int countOfPageByParam(@Param("categoryId") Long categoryId, @Param("membershipName") String membershipName, @Param("skuIdList") List<Long> skuIdList, @Param("platformId") Long platformId);

    List<MembershipPO> findOfPageByParam(@Param("categoryId") Long categoryId,@Param("membershipName")  String membershipName, @Param("skuIdList") List<Long> skuIdList, @Param("platformId") Long platformId,@Param("page")  Pagination page);

    int countOfPageByParams(@Param("categoryId") Long categoryId, @Param("membershipName") String membershipName,@Param("platformId") Long platformId);

    List<MembershipPO> findOfPageByParams(@Param("categoryId")Long categoryId,@Param("membershipName") String membershipName, @Param("platformId")Long platformId, @Param("page") Pagination page);

    List<MembershipPO> findMembershipBySkuId(@Param("skuId")Long skuId, @Param("platformId")Long platformId);
    
    List<MembershipCondition> findNotifyMembership(@Param("remainDays")Integer remainDays);
}
	