package com.egeo.components.user.service.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.service.read.RoleUrlReadService;
import com.egeo.utils.StringUtils;
import com.egeo.components.user.dao.read.RoleUrlReadDAO;
import com.egeo.components.user.dao.read.UrlReadDAO;
import com.egeo.components.user.converter.RoleUrlConverter;
import com.egeo.components.user.converter.UrlConverter;
import com.egeo.components.user.dto.RoleUrlDTO;
import com.egeo.components.user.dto.UrlDTO;
import com.egeo.components.user.po.UrlPO;

import com.egeo.web.JsonResult;

@Service("roleUrlReadService")
public class RoleUrlReadServiceImpl implements RoleUrlReadService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
	private RoleUrlReadDAO roleUrlReadDAO ;

    @Autowired
    private UrlReadDAO urlReadDAO;

    @Override
    public List<String> getUrlListByUserId(Long userId, Long platformId) {
        
		List<String> userUrlList = new ArrayList<>();
		
		List<String> roleUrlList = urlReadDAO.getUrlListByUserId(userId, platformId);
		List<String> funcUrlList = urlReadDAO.getFunctionUrlListByUserId(userId, platformId);
		if (StringUtils.isNotEmpty(roleUrlList)) {
			logger.info("roleUrl不为空");
			if (roleUrlList.indexOf("userList") > -1) {
				logger.info("有userList");
			}else {
				logger.info("没有userList");
			}
			userUrlList.addAll(roleUrlList);
		}
		if (StringUtils.isNotEmpty(funcUrlList)) {
			userUrlList.addAll(funcUrlList);
		}
		return userUrlList;
	
    }

    @Override
    public JsonResult<List<UrlDTO>> showUrl(Long roleId,Long platformId) {
        //加载所有的url
        UrlPO po = new UrlPO();
//        po.setPlatformId(platformId);

        List<UrlPO> list = urlReadDAO.findAll(po,null);
        List<UrlDTO> dto = UrlConverter.toDTO(list);
        //加载该角色能够访问到的url
        List<UrlPO> urlByRoleId = urlReadDAO.getUrlByRoleId(roleId);
        for (UrlDTO urlDTO : dto) {//遍历所有的权限
            for (UrlPO urlPO : urlByRoleId) {//遍历角色的权限
              //如果当前正在遍历的所有的权限中的该项是角色能够访问到的
                if(urlDTO.getId()==urlPO.getId()){
                    urlDTO.setChecked(true);
                }
            }
        }
        
        JsonResult<List<UrlDTO>> jsonResult = new JsonResult<List<UrlDTO>>();
        jsonResult.setData(dto);
        return jsonResult;
    }
    
    @Override
    public List<RoleUrlDTO> findAll(RoleUrlDTO dto) {
    	return RoleUrlConverter.toDTO(roleUrlReadDAO.findAll(RoleUrlConverter.toPO(dto),null));
    } 
}
