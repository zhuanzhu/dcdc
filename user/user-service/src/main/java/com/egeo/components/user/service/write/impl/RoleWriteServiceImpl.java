package com.egeo.components.user.service.write.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.egeo.components.user.converter.RoleConverter;
import com.egeo.components.user.dto.RoleDTO;
import com.egeo.components.user.manage.read.MenuReadManage;
import com.egeo.components.user.manage.read.UrlReadManage;
import com.egeo.components.user.manage.write.MenuWriteManage;
import com.egeo.components.user.manage.write.RoleUrlWriteManage;
import com.egeo.components.user.manage.write.RoleWriteManage;
import com.egeo.components.user.po.MenuPO;
import com.egeo.components.user.service.write.RoleWriteService;


@Service("roleWriteService")
public class RoleWriteServiceImpl implements RoleWriteService {

	Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RoleWriteManage roleWriteManage;

    @Autowired
    private MenuReadManage menuReadManage;
    
    @Autowired
    private MenuWriteManage menuWriteManage;
    
    @Autowired
    private UrlReadManage urlReadManage;
    
    @Autowired
    private RoleUrlWriteManage roleUrlWriteManage;
    
    @Override
    public Long saveRoleWithTx(RoleDTO roleDTO) {
        List<Long> menuIdList = JSONArray.parseArray(roleDTO.getMenus(), Long.class);
        List<Long> urlIdList =JSONArray.parseArray(roleDTO.getUrls(), Long.class);
        List<Long> functionIdList =JSONArray.parseArray(roleDTO.getFunctions(), Long.class);
        Long roleId = roleWriteManage.saveRoleWithTx(RoleConverter.toPO(roleDTO), menuIdList, urlIdList, functionIdList);
        if(menuIdList!=null && menuIdList.size()>0) {
            for(Long menu : menuIdList) {
                logger.info("menuIdList:"+menuIdList);
            }
        }
        if(urlIdList!=null && urlIdList.size()>0) {
            for(Long url : urlIdList) {
                logger.info("url:"+urlIdList);
            }
        }
        if(functionIdList!=null && functionIdList.size()>0) {
            for(Long functions : functionIdList) {
                logger.info("functions:"+functionIdList);
            }
        }
        return Long.valueOf(roleId);
    }

    @Override
    public int removeRoleWithTx(String name) {
        
        return roleWriteManage.removeRoleWithTx(name);

    }

    @Override
    public int updateRoleWithTx(RoleDTO dto) {
    	List<Long> menuIdList = JSONArray.parseArray(dto.getMenus(), Long.class);
        List<Long> urlIdList =  JSONArray.parseArray(dto.getUrls(), Long.class);
        List<Long> functionIdList =  JSONArray.parseArray(dto.getFunctions(), Long.class);
        return roleWriteManage.updateRoleWithTx(RoleConverter.toPO(dto), menuIdList, urlIdList, functionIdList);
    }

    @Override
    public int upDateUserWithTx(RoleDTO roleDTO, String userName) {


        return roleWriteManage.upDate(RoleConverter.toPO(roleDTO));
    }

    @Override
    public int upDateRoleWithTx(String menus, Long roleId) {
        int row = 0;
        List<String> menuList = null;
        StringBuffer delMenu = new StringBuffer();
        // 根据角色id查找menu集合
        List<MenuPO> list = menuReadManage.menuListByroleId(roleId);
        String substring = menus.substring(0);
        if(substring.equals("")){
            for (MenuPO menuDTO : list) {
                delMenu.append(menuDTO.getId()+",");
            }
        }else{
                // 根据字符串截取list
                menuList = Arrays.asList(menus.split(","));
                
                // 查找需要删除的menu关系
                for (MenuPO menuPO : list) {
                    if (!menuList.contains(menuPO.getId() + "")) {
                        delMenu.append(menuPO.getId() + ",");
                    }
                }
        }
        // 查找需要添加的menu关系
        List<String> ids = new ArrayList<String>();
        for (MenuPO menuPO : list) {
            ids.add(menuPO.getId() + "");
        }
        List<String> setMenu = new ArrayList<String>();
        //判断是否有需要添加的数据
        if(menuList != null){
            for (String string : menuList) {
                if (!ids.contains(string)) {
                    setMenu.add(string);
                }
            }
        }
        
        if (delMenu.length() > 0) {
            delMenu.deleteCharAt(delMenu.length() - 1);
        }

        if (delMenu.length() > 0) {
            // 调用批量删除的方法
            row = roleWriteManage.delMenu(delMenu, roleId);
        }

        if (setMenu.size() != 0) {
            // 调用批量添加的方法
            row = roleWriteManage.setMenu(setMenu, roleId);
        }

        return row;
    }

    @Override
    public int deleteByIdWithTx(RoleDTO roleDto) {
        return roleWriteManage.deleteByIdWithTx(RoleConverter.toPO(roleDto));
    }

    @Override
    public void updateRoleStatus(RoleDTO roleDto, Integer useable) {
        roleWriteManage.updateRoleStatus(RoleConverter.toPO(roleDto),useable);
    }

}
