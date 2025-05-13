/**
 * Created By: XI
 * Created On: 2018-10-23 17:53:41
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.egeo.components.user.dao.write;


import java.util.List;

import com.egeo.components.user.bean.UniAuthDomain;


public interface UniauthDomainMapper {
    int insert(UniAuthDomain uniAuthDomain);

    List<UniAuthDomain> listByProperty(UniAuthDomain uniAuthDomain);

    UniAuthDomain getByPK(Integer id);
}
