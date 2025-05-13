package com.egeo.components.user.manage.write.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.write.RoleUrlWriteManage;
import com.egeo.components.user.dao.read.UrlReadDAO;
import com.egeo.components.user.dao.write.RoleUrlWriteDAO;
import com.egeo.components.user.po.RoleUrl;
import com.egeo.components.user.po.RoleUrlPO;
import com.egeo.components.user.po.UrlPO;
import com.egeo.exception.BusinessException;

@Service
public class RoleUrlWriteManageImpl implements RoleUrlWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private RoleUrlWriteDAO roleUrlWriteDAO;
	
	@Autowired
    private UrlReadDAO urlReadDAO;
	
    @Override
    public int delUrl(StringBuffer delUrl, Long roleId) {
        String urls = delUrl.toString();
        RoleUrl roleUrl = new RoleUrl();
        roleUrl.setRoleId(roleId);
        roleUrl.setUrls(urls);
        int url = roleUrlWriteDAO.delUrl(roleUrl);
        return url;
    }
    @Override
    public int setUrl(List<String> setUrl, Long roleId) {
        
        int row = 0;
        for (String string : setUrl) {
            RoleUrlPO po = new RoleUrlPO();
            po.setRoleId(roleId);
            //long result = Long.parseLong(s); 返回long基本数据类型
            Long result = Long.valueOf(string);//返回Long包装类型
            po.setUrlId(result);
            row += roleUrlWriteDAO.insert(po);
        }
        return row;
    }
	@Override
	public String delRoleUrlByUrlId(Long urlId) {
		RoleUrlPO po = new RoleUrlPO();
		po.setUrlId(urlId);
		int i = roleUrlWriteDAO.deleteByPara(po);
		if(i != 0){
			return "根据urlId删除角色url关系成功";
		}else{
			throw new BusinessException("根据urlId删除角色url关系失败");
		}
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
		//根据类型查询url信息
		List<UrlPO> urlList = urlReadDAO.findByType(type,platformId);
		new Thread(new SaveRoleUrl(roleId, urlList)).start();
		return 1;
	}
	
	private class SaveRoleUrl implements Runnable{
		private Long roleId;
		private List<UrlPO> urlList;
		public SaveRoleUrl(Long roleId,List<UrlPO> urlList){
			this.roleId = roleId;
			this.urlList = urlList;
		}
		@Override
		public void run() {
			for (UrlPO urlPO : urlList) {
				RoleUrlPO roleUrlPO = new RoleUrlPO();
				roleUrlPO.setRoleId(roleId);
				roleUrlPO.setUrlId(urlPO.getId());
				roleUrlWriteDAO.insert(roleUrlPO);
			}
			
		}
		
	}
}
	