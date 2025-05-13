package com.egeo.components.user.manage.write;

import java.util.List;

import com.egeo.components.user.po.RolePO;

public interface RoleWriteManage {

    Long saveRoleWithTx(RolePO po, List<Long> menuIdList, List<Long> urlIdList, List<Long> functionIdList);

    int removeRoleWithTx(String name);

    int updateRoleWithTx(RolePO po, List<Long> menuIdList, List<Long> urlIdList, List<Long> functionIdList);

    int upDate(RolePO po);

    int delMenu(StringBuffer delUrl, Long roleId);

    int setMenu(List<String> setMenu, Long roleId);

    int deleteByIdWithTx(RolePO po);

    void updateRoleStatus(RolePO toPO, Integer useable);
}
