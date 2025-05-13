/**
 * Created By: XI
 * Created On: 2018-10-16 10:47:21
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.egeo.components.user.dao.write;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.user.bean.UniauthDepartment;


public interface UniauthDepartmentMapper {
    int insert(UniauthDepartment uniauthDepartment);

    int updatePart(UniauthDepartment uniauthDepartment);

    int updateParentCodes(@Param("oldParentCodes") String oldParentCodes,
                          @Param("newParentCodes") String newParentCodes,
                          @Param("updateTime") String updateTime,
                          @Param("updateBy") String updateBy);

    List<UniauthDepartment> getByUniauthDepartment(UniauthDepartment uniauthDepartment);

    List<UniauthDepartment> listByPageInfo(UniauthDepartment uniauthDepartment);

    int findByCount(UniauthDepartment uniauthDepartment);

    List<UniauthDepartment> getByParentCode(@Param("parentCode") String parentCode);
}
