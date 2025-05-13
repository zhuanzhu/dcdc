package com.egeo.components.user.service.write.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.dto.UrlDTO;
import com.egeo.components.user.manage.write.RoleUrlWriteManage;
import com.egeo.components.user.service.read.UrlReadService;
import com.egeo.components.user.service.write.RoleUrlWriteService;


@Service("roleUrlWriteService")
public class RoleUrlWriteServiceImpl implements RoleUrlWriteService {
	@Autowired
	private RoleUrlWriteManage roleUrlWriteManage;
	
	@Autowired
        private UrlReadService urlReadService;
	
	@Override
	    public int upDateWithTx(String urls, Long roleId) {
	        int row = 0;
	        List<String> urlList = null;
	        StringBuffer delUrl = new StringBuffer();
	        //根据角色id查找url集合
                List<UrlDTO> list = urlReadService.getUrlByRoleId(roleId);
	        String substring = urls.substring(0);
	        if(substring.equals("")){
	            for (UrlDTO urlDTO : list) {
                         delUrl.append(urlDTO.getId()+",");
                    }
	        }else{
    	            //根据字符串截取list
    	            urlList = Arrays.asList(urls.split(",")); 
	                
	                //查找需要删除的url关系
	                for (UrlDTO urlDTO : list) {
	                    if(!urlList.contains(urlDTO.getId()+"")){
	                        delUrl.append(urlDTO.getId()+",");
	                    }
	                }
	        }
	        
	           //查找需要添加的trl关系
	           List<String> ids = new ArrayList<String>();
	           for (UrlDTO urlDTO : list) {
	               ids.add(urlDTO.getId()+"");
                   }
	           List<String> setUrl = new ArrayList<String>();
	           if(urlList != null){
	               for (String string : urlList) {
	                       if(!ids.contains(string)){
	                           setUrl.add(string);
	                       }
	                   }
	           }
	           if(delUrl.length() > 0){
	               delUrl.deleteCharAt(delUrl.length() - 1);
	           }
	           
	           if(delUrl.length() > 0){
	             //调用批量删除的方法
	               row = roleUrlWriteManage.delUrl(delUrl,roleId);
	           }
	           
	           if(setUrl.size() != 0){
	               //调用批量添加的方法
	               row =  roleUrlWriteManage.setUrl(setUrl,roleId);
	           }
	           
	        return row;
	    }

	@Override
	public String delRoleUrlByUrlId(Long urlId) {
		
		return roleUrlWriteManage.delRoleUrlByUrlId(urlId);
	}
	/**
	 * 根据类型和角色id添加角色和URL的关系
	 * @param roleId
	 * @param type
	 * @param platformId
	 * @return
	 */
	@Override
	public Integer saveUrlByTypeWithTx(Long roleId, Integer type, Long platformId) {
		// TODO Auto-generated method stub
		return roleUrlWriteManage.saveUrlByTypeWithTx(roleId, type, platformId);
	}
}
	