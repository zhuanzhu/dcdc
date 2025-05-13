package com.egeo.components.product.dao.read;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.po.MembershipUserPO;
import com.egeo.orm.BaseReadDAO;
import com.egeo.orm.Pagination;

public interface MembershipUserReadDAO extends BaseReadDAO<MembershipUserPO>{
    int countOfPageByParam(@Param("membershipId") Long membershipId,@Param("startTime") Date startTime,@Param("endTime") Date endTime, @Param("statusCode") Integer statusCode , @Param("page") Pagination page);


    List<MembershipUserPO> findOfPageByParam(@Param("membershipId") Long membershipId, @Param("startTime") Date startTime,@Param("endTime") Date endTime,@Param("statusCode") Integer statusCode , @Param("page") Pagination page);

    int countOfPageByParams(@Param("membershipId")Long membershipId,@Param("startTime") Date startTime,@Param("endTime") Date endTime,@Param("statusCode") Integer statusCode,@Param("result") List<Long> result , @Param("page") Pagination page);

    List<MembershipUserPO> findOfPageByParams(@Param("membershipId")Long membershipId,@Param("startTime") Date startTime,@Param("endTime") Date endTime, @Param("statusCode")Integer statusCode,@Param("result") List<Long> result , @Param("page") Pagination page);
}
	
