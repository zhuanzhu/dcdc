/**
 * Created By: XI
 * Created On: 2018-10-25 10:15:10
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.egeo.components.user.dao.write;


import java.util.List;

import com.egeo.components.user.bean.UniauthRoleMenu;


public interface UniauthRoleMenuMapper {
    int insertBatch(List<UniauthRoleMenu> list);

    int deleteByProperty(UniauthRoleMenu uniauthRoleMenu);
}
