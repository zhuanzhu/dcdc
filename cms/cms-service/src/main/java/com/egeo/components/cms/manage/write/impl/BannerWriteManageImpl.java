package com.egeo.components.cms.manage.write.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.egeo.components.cms.dao.read.BannerReadDAO;
import com.egeo.components.cms.dao.read.LinkableButtonPageReadDAO;
import com.egeo.components.cms.dao.read.LinkableButtonReadDAO;
import com.egeo.components.cms.dao.read.LinkableParamReadDAO;
import com.egeo.components.cms.dao.write.BannerCompanyWriteDAO;
import com.egeo.components.cms.dao.write.BannerWriteDAO;
import com.egeo.components.cms.dao.write.LinkableButtonPageWriteDAO;
import com.egeo.components.cms.dao.write.LinkableButtonWriteDAO;
import com.egeo.components.cms.dao.write.LinkableParamWriteDAO;
import com.egeo.components.cms.manage.write.BannerWriteManage;
import com.egeo.components.cms.po.BannerCompanyPO;
import com.egeo.components.cms.po.BannerPO;
import com.egeo.components.cms.po.LinkableButtonPO;
import com.egeo.components.cms.po.LinkableButtonPagePO;
import com.egeo.components.cms.po.LinkableParamPO;
import com.egeo.core.Constant.CmsConstant;
import com.egeo.exception.BusinessException;

@Service
public class BannerWriteManageImpl implements BannerWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private BannerWriteDAO bannerWriteDAO;
	
	@Autowired
	private BannerReadDAO bannerReadDAO;
	
	@Autowired
	private LinkableButtonWriteDAO linkableButtonWriteDAO;
	
	@Autowired
	private LinkableButtonReadDAO linkableButtonReadDAO;
	
	@Autowired
	private BannerCompanyWriteDAO bannerCompanyWriteDAO;
	
	@Autowired
	private LinkableButtonPageWriteDAO linkableButtonPageWriteDAO;
	
	@Autowired
	private LinkableButtonPageReadDAO linkableButtonPageReadDAO;
	@Autowired
	private LinkableParamReadDAO linkableParamReadDAO;
	@Autowired
	private LinkableParamWriteDAO linkableParamWriteDAO;

	@Override
	public Long insertBannerWithTx(BannerPO po) {
		
		int i ;
		try {
				i = bannerWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateBannerWithTx(BannerPO po) {
		int i;
		i = bannerWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteBannerWithTx(BannerPO po) {
		int i;
		i = bannerWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}

	@Override
	public boolean saveBannerWithTx(Long bannerId, BannerPO banner, LinkableButtonPO lb,List<Long> companyIdList,List<LinkableButtonPagePO> lbpPOs,String extParam) {
		
		Long lbId ;
		if(bannerId==null) {
			//新增
			//首先新增跳转信息
			linkableButtonWriteDAO.insert(lb);
			lbId = lb.getId();
					
			banner.setLinkableId(lbId);
			bannerWriteDAO.insert(banner);
			bannerId=banner.getId();
		}else {
			//修改
			banner.setId(bannerId);
			//判断原banner是否已有链接
			BannerPO cond=new BannerPO();
			cond.setId(bannerId);
			BannerPO bannerOld = bannerReadDAO.findById(cond);
			lbId = bannerOld.getLinkableId();
			boolean lbExist=false;
			if(lbId!=null) {
				LinkableButtonPO lbCond=new LinkableButtonPO();
				lbCond.setId(lbId);
				LinkableButtonPO lb_=linkableButtonReadDAO.findById(lbCond);
				if(lb_!=null) {
					//修改lb
					lbExist=true;
					lb.setId(lbId);
					linkableButtonWriteDAO.update(lb);
				}
				
			}
			if(!lbExist) {
				//新增lb
				linkableButtonWriteDAO.insert(lb);
				lbId=lb.getId();
			}
			banner.setLinkableId(lbId);
			bannerWriteDAO.update(banner);
		}
		
		//保存linkparam
		saveLinkParam(lbId,extParam);
		
		//如果链接类型不为 CMS_LINK_TYPE_SU_LIST 直接删除关联关系
		linkableButtonPageWriteDAO.deleteByPara(new LinkableButtonPagePO(lbId));
		
		//如果链接类型为商品组合 删除并新建关联关系
		if(lb.getLinkType() == CmsConstant.CMS_LINK_TYPE_SU_LIST && lbpPOs != null && lbpPOs.size() > 0) {
			for (LinkableButtonPagePO linkableButtonPagePO : lbpPOs) {
				linkableButtonPagePO.setLinkableId(lbId);
			}
			linkableButtonPageWriteDAO.insertBatch(lbpPOs);
		}
		
		//删除并新建与公司的关系
		BannerCompanyPO deleteCond=new BannerCompanyPO();
		deleteCond.setBannerId(bannerId);
		bannerCompanyWriteDAO.deleteByPara(deleteCond);
		for(Long cid:companyIdList) {
			BannerCompanyPO bc=new BannerCompanyPO();
			bc.setBannerId(bannerId);
			bc.setCompanyId(cid);
			bannerCompanyWriteDAO.insert(bc);
		}
		return true;
	}
	
	public void saveLinkParam(Long linkId,String extParam) {
		LinkableParamPO linkableParam = new LinkableParamPO();
		linkableParam.setLinkButtonId(linkId);
		List<LinkableParamPO> list = linkableParamReadDAO.findAll(linkableParam,null);
		if(list != null && list.size() >0) {
			linkableParamWriteDAO.deleteByPara(linkableParam);
		}
		if(StringUtils.isNotBlank(extParam)) {
			JSONArray array = JSON.parseArray(extParam);
			if(array != null && array.size() > 0) {
				for(int i = 0 ; i<array.size() ; i++) {
					JSONObject object = array.getJSONObject(i);
					LinkableParamPO po = new LinkableParamPO();
					po.setLinkButtonId(linkId);
					po.setName(object.getString("name"));
					po.setValue(object.getString("value"));
					linkableParamWriteDAO.insert(po);
				}
				
			}
		}
	}
}
	