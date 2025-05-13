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
package com.egeo.components.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.bean.UniAuthDomain;
import com.egeo.components.user.bean.UniauthMenuDomain;
import com.egeo.components.user.dao.write.UniauthDomainMapper;
import com.egeo.components.user.dao.write.UniauthMenuDomainMapper;
import com.egeo.components.utils.UtilHelper;

@Service("uniauthDomainService")
public class UniauthDomainService {

	@Autowired
	private UniauthDomainMapper uniauthDomainMapper;
	@Autowired
	private UniauthMenuDomainMapper uniauthMenuDomainMapper;

	/**
	 * 保存记录
	 * @return
	 */
	public void insert(Integer mid, String domain){
		UniauthMenuDomain uniauthMenuDomain = new UniauthMenuDomain();
		uniauthMenuDomain.setMid(mid);
		uniauthMenuDomainMapper.deleteByProperty(uniauthMenuDomain);

		if (!UtilHelper.isEmpty(domain)){
			String[] arr = domain.split(",");
			for (int i = 0; i < arr.length; i++) {
				String domainUrl = arr[i];
				UniAuthDomain uniauthDomain = new UniAuthDomain();
				uniauthDomain.setDomainUrl(domainUrl);
				List<UniAuthDomain> uniAuthDomains = uniauthDomainMapper.listByProperty(uniauthDomain);
				if (UtilHelper.isEmpty(uniAuthDomains)){
					uniauthDomain.setCode(domainUrl.substring(0, domainUrl.indexOf(".")));
					uniauthDomain.setName(domainUrl);
					uniauthDomain.setTenancyId(0l);
					uniauthDomain.setStatus(0);
					uniauthDomainMapper.insert(uniauthDomain);
				}else {
					uniauthDomain = uniAuthDomains.get(0);
				}

				uniauthMenuDomain.setDomainId(uniauthDomain.getId());
				uniauthMenuDomainMapper.insert(uniauthMenuDomain);
			}
		}
	}

	public String getDomain(Integer mid){
		UniauthMenuDomain uniauthMenuDomain = new UniauthMenuDomain();
		uniauthMenuDomain.setMid(mid);

		List<UniauthMenuDomain> uniauthMenuDomains = uniauthMenuDomainMapper.listByProperty(uniauthMenuDomain);
		if (!UtilHelper.isEmpty(uniauthMenuDomains)){
			UniAuthDomain uniAuthDomain = uniauthDomainMapper.getByPK(uniauthMenuDomains.get(0).getDomainId());
			if (!UtilHelper.isEmpty(uniAuthDomain)){
				return uniAuthDomain.getDomainUrl();
			}
		}

		return null;
	}
}