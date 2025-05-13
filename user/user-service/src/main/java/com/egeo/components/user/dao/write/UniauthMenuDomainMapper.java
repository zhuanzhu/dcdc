/**
 * Created By: XI
 * Created On: 2018-10-23 17:53:42
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.egeo.components.user.dao.write;


import java.util.List;

import com.egeo.components.user.bean.UniauthMenuDomain;


public interface UniauthMenuDomainMapper {
    int insert(UniauthMenuDomain uniauthMenuDomain);

    int deleteByProperty(UniauthMenuDomain uniauthMenuDomain);

    List<UniauthMenuDomain> listByProperty(UniauthMenuDomain uniauthMenuDomain);
}
