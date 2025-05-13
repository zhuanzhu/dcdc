package com.egeo.components.cms.manage.write.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.egeo.components.cms.constant.ElementTypeConstant;
import com.egeo.components.cms.dao.read.ElementDictReadDAO;
import com.egeo.components.cms.dao.read.IconGroupReadDAO;
import com.egeo.components.cms.dao.read.IconReadDAO;
import com.egeo.components.cms.dao.read.ImagetextGroupReadDAO;
import com.egeo.components.cms.dao.read.ImagetextReadDAO;
import com.egeo.components.cms.dao.read.ShoppingLabelGroupReadDAO;
import com.egeo.components.cms.dao.read.SuListReadDAO;
import com.egeo.components.cms.dao.write.BannerInstWriteDAO;
import com.egeo.components.cms.dao.write.ElementWriteDAO;
import com.egeo.components.cms.dao.write.IconCompanyWriteDAO;
import com.egeo.components.cms.dao.write.IconGroupWriteDAO;
import com.egeo.components.cms.dao.write.IconWriteDAO;
import com.egeo.components.cms.dao.write.ImagetextGroupWriteDAO;
import com.egeo.components.cms.dao.write.ImagetextWriteDAO;
import com.egeo.components.cms.dao.write.InstCompanyWriteDAO;
import com.egeo.components.cms.dao.write.InstWriteDAO;
import com.egeo.components.cms.dao.write.LinkableButtonPageWriteDAO;
import com.egeo.components.cms.dao.write.LinkableButtonWriteDAO;
import com.egeo.components.cms.dao.write.ShoppingLabelGroupWriteDAO;
import com.egeo.components.cms.dao.write.ShoppingLabelWriteDAO;
import com.egeo.components.cms.dao.write.SuListWriteDAO;
import com.egeo.components.cms.manage.write.InstWriteManage;
import com.egeo.components.cms.po.BannerInstPO;
import com.egeo.components.cms.po.ElementDictPO;
import com.egeo.components.cms.po.ElementPO;
import com.egeo.components.cms.po.IconCompanyPO;
import com.egeo.components.cms.po.IconGroupPO;
import com.egeo.components.cms.po.IconPO;
import com.egeo.components.cms.po.ImagetextGroupPO;
import com.egeo.components.cms.po.ImagetextPO;
import com.egeo.components.cms.po.InstCompanyPO;
import com.egeo.components.cms.po.InstPO;
import com.egeo.components.cms.po.LinkableButtonPO;
import com.egeo.components.cms.po.LinkableButtonPagePO;
import com.egeo.components.cms.po.ShoppingLabelGroupPO;
import com.egeo.components.cms.po.ShoppingLabelPO;
import com.egeo.components.cms.po.SuListPO;
import com.egeo.core.Constant.CmsConstant;
import com.egeo.exception.BusinessException;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.str.StringUtils;

@Service
public class InstWriteManageImpl implements InstWriteManage {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private InstWriteDAO instWriteDAO;

	@Autowired
	private InstCompanyWriteDAO instCompanyWriteDAO;

	@Autowired
	private ElementWriteDAO elementWriteDAO;

	@Autowired
	private ElementDictReadDAO elementDictReadDAO;

	@Autowired
	private BannerInstWriteDAO bannerInstWriteDAO;

	@Autowired
	private LinkableButtonWriteDAO linkableButtonWriteDAO;

	@Autowired
	private SuListWriteDAO suListWriteDAO;

	@Autowired
	private SuListReadDAO suListReadDAO;

	@Autowired
	private IconGroupWriteDAO iconGroupWriteDAO;

	@Autowired
	private IconGroupReadDAO iconGroupReadDAO;

	@Autowired
	private IconReadDAO iconReadDAO;

	@Autowired
	private IconWriteDAO iconWriteDAO;

	@Autowired
	private ImagetextWriteDAO imagetextWriteDAO;

	@Autowired
	private ImagetextGroupWriteDAO imagetextGroupWriteDAO;

	@Autowired
	private ImagetextReadDAO imagetextReadDAO;

	@Autowired
	private ImagetextGroupReadDAO imagetextGroupReadDAO;

	@Autowired
	private ShoppingLabelWriteDAO shoppingLabelWriteDAO;

	@Autowired
	private ShoppingLabelGroupWriteDAO shoppingLabelGroupWriteDAO;

	@Autowired
	private ShoppingLabelGroupReadDAO shoppingLabelGroupReadDAO;

	@Autowired
	private IconCompanyWriteDAO iconCompanyWriteDAO;
	
	@Autowired
	private LinkableButtonPageWriteDAO linkableButtonPageWriteDAO;

	@Override
	public Long insertInstWithTx(InstPO po) {

		int i;
		try {
			i = instWriteDAO.insert(po);
			if (i == 0)
				throw new BusinessException("未能成功插入数据!");
		} catch (DuplicateKeyException e) {
			logger.error("", e);
			throw new BusinessException("路径必须唯一!");
		}
		return po.getId();
	}

	@Override
	public int updateInstWithTx(InstPO po) {
		int i;
		i = instWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteInstWithTx(InstPO po) {
		int i;
		i = instWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}

	@Override
	public boolean saveEleAndInstWithTx(
			// 通用参数
			Long templateId, String name, Integer margin, Long elementDictId, Long elementId, Long instId,
			List<Long> cids, Long pageTabId,
			// banner参数
			String bannerIds,
			// su列表参数
			Long titleColor, String titleName, Integer maxShow, Long sucId, String bannerUrl, Integer linkType, Long linkId,
			String linkUrl,
			// icon参数
			Integer count, String iconArr, String iconTitle,
			// 图文参数
			String imagetextTitle, String imagetextArr, String imagetextBannerArr,
			// 标签参数
			String lableIconUrl, String lableArr,
			List<LinkableButtonPagePO> linkableButtonPagePOs) {
		// 查询字典信息
		ElementDictPO cond = new ElementDictPO();
		cond.setId(elementDictId);
		ElementDictPO dict = elementDictReadDAO.findById(cond);
		if (dict == null) {
			throw new BusinessException("该字典信息不存在");
		}
		if (dict.getType() == 3 && pageTabId == null) {
			throw new BusinessException("分页tab的id不能为空");
		}
		Integer configType = dict.getConfigType();
		// 新增/保存组件
		ElementPO ele = new ElementPO();
		ele.setConfigType(configType);
		ele.setName(name);
		ele.setTemplateId(templateId);
		ele.setType(0);
		if (elementId == null) {
			// 新增组件
			ele.setSort(99999);
			elementWriteDAO.insert(ele);
			elementId = ele.getId();
		} else {
			// 保存组件
			ele.setId(elementId);
			elementWriteDAO.update(ele);
		}
		// 新增/保存实例
		InstPO inst = new InstPO();
		inst.setConfigType(configType);
		inst.setElementId(elementId);
		inst.setInstMargin(margin);
		inst.setName(name);
		inst.setPageTabId(pageTabId);
		if (instId == null) {
			// 新增实例
			instWriteDAO.insert(inst);
			instId = inst.getId();
		} else {
			// 保存实例
			inst.setId(instId);
			instWriteDAO.update(inst);
		}
		// 修改实例与公司的关联
		editInstCompanyWithTx(instId, cids);
		// 视情况保存具体实例数据
		int ct = configType / 100;
		switch (ct) {
		case 0:
			// bannerList
			// 参数校验
			if (StringUtils.isEmpty(bannerIds)) {
				throw new BusinessException("至少选择一个轮播图");
			}
			List<Long> bids = StringUtils.stringWithBorder2IdList(bannerIds, ",");
			if (bids.size() == 0) {
				throw new BusinessException("至少选择一个轮播图");
			}
			editBannerInstWithTx(instId, bids);
			break;
		case 1:
			// suList
			// 参数校验
			if (StringUtils.isEmpty(titleName)) {
				throw new BusinessException("请输入商品列表标题");
			}
			if (titleName.length() > 6) {
				throw new BusinessException("商品列表标题过长");
			}
			if (configType.intValue() == ElementTypeConstant.SULIST_1ROW_WEB) {
				maxShow = 6;
			}
			if (configType.intValue() == ElementTypeConstant.SULIST_2ROW_WEB) {
				maxShow = 10;
			}
			if (configType.intValue() == ElementTypeConstant.SULIST_1ROW_M_WEB) {
				maxShow = 30;
			}
			
			if (maxShow == null || maxShow == 0) {
				throw new BusinessException("请输入最大显示数量");
			}
			if (sucId == null) {
				throw new BusinessException("请选择商品组");
			}
			if (configType == 102 || configType == 103 || configType == 106 || configType == 107 || configType == 110
					|| configType == 111 || configType == 113) {
				// 校验banner
				if (EmptyUtil.isBlank(bannerUrl)) {
					throw new BusinessException("请上传banner图");
				}
			}
			insertSuListWithTx(titleColor, titleName, maxShow, sucId, bannerUrl, linkType, linkId, linkUrl, instId, configType,linkableButtonPagePOs);
			break;
		case 2:
			// iconGroup
			if (EmptyUtil.isBlank(iconArr)) {
				throw new BusinessException("请至少配置一个icon信息");
			}
			JSONArray iconJarr = JSONArray.parseArray(iconArr);
			if (iconJarr.size() == 0) {
				throw new BusinessException("请至少配置一个icon信息");
			}
			if (configType.intValue() == ElementTypeConstant.ICON_SHOP_TITLE_WEB) {
				count = 5;
				if (iconJarr.size() != 5)
					throw new BusinessException("需配置5个元件");
			}
			if (count == null) {
				throw new BusinessException("请填写数量");
			}
			insertIconGroupWithTx(count, iconJarr, iconTitle, instId, configType);
			break;
		case 3:
			// imagetextGroup
			if (EmptyUtil.isEmpty(imagetextArr)) {
				throw new BusinessException("请至少配置一个图文实例信息");
			}
			if(EmptyUtil.isNotBlank(imagetextTitle) && imagetextTitle.length()>6) {
				throw new BusinessException("标题名称不超过6个字");
			}
			JSONArray imgtxtJarr = JSONArray.parseArray(imagetextArr);
			if (configType.intValue() == ElementTypeConstant.IMGTXT_19_WEB) {
				if (imgtxtJarr.size() == 0)  
					throw new BusinessException("请添加品牌商品左侧banner图");
				if (imgtxtJarr.size() == 1)  
					throw new BusinessException("最少添加1个品牌icon");
				if (imgtxtJarr.size() > 19)  
					throw new BusinessException("最多添加18个品牌icon");
			}
			if (imgtxtJarr.size() == 0) {
				throw new BusinessException("请至少配置一个图文实例信息");
			}
			insertImagetextGroupWithTx(imagetextTitle, imgtxtJarr, instId, configType, null);
			break;
		case 4:
			// shoppingLabelGroup
			if (EmptyUtil.isBlank(lableIconUrl)) {
				throw new BusinessException("请上传标签小图");
			}
			if (EmptyUtil.isBlank(lableArr)) {
				throw new BusinessException("请至少编辑一个商城标签");
			}
			JSONArray labelJarr = JSONArray.parseArray(lableArr);
			insertShoppingLabelGroupWithTx(labelJarr, lableIconUrl, instId, configType);
			break;
		case 5:
			// elementList(imagetextGroup,suList)
			insertElementList(templateId,name, margin, elementDictId, elementId, instId, cids,configType,
			bannerIds, titleColor, titleName, maxShow, sucId, bannerUrl, linkType, linkId, linkUrl, count, iconArr, iconTitle,
			imagetextTitle, imagetextArr, imagetextBannerArr,
			lableIconUrl, lableArr,linkableButtonPagePOs);
			break;
		case 6:
			// elementList(imagetextGroup,imagetextGroup,imagetextGroup,suList)
			insertElementList(templateId,name, margin, elementDictId, elementId, instId, cids,configType,
					bannerIds, titleColor, titleName, maxShow, sucId, bannerUrl, linkType, linkId, linkUrl, count, iconArr, iconTitle,
					imagetextTitle, imagetextArr, imagetextBannerArr,
					lableIconUrl, lableArr,linkableButtonPagePOs);
		}
		return true;
	}
	
	private void insertElementList(// 通用参数
			Long templateId, String name, Integer margin, Long elementDictId, Long elementId, Long instId,
			List<Long> cids,Integer configType,
			// banner参数
			String bannerIds,
			// su列表参数
			Long titleColor, String titleName, Integer maxShow, Long sucId, String bannerUrl, Integer linkType, Long linkId,
			String linkUrl,
			// icon参数
			Integer count, String iconArr, String iconTitle,
			// 图文参数
			String imagetextTitle, String imagetextArr, String imagetextBannerArr,
			// 标签参数
			String lableIconUrl, String lableArr,
			
			List<LinkableButtonPagePO> linkableButtonPagePOs) {

		if (configType.intValue() == ElementTypeConstant.ELEMENT_LIST_601_WEB) {
			// 左上1imagetextGroup,左下2imagetextGroup,右1suList
			// 插入1imagetextGroup
			if (EmptyUtil.isEmpty(imagetextBannerArr)) {
				throw new BusinessException("请至少添加1个轮播图");
			}
			JSONArray imgtxtBannerJarr = JSONArray.parseArray(imagetextBannerArr);
			if (imgtxtBannerJarr.size() > 5) {
				throw new BusinessException("请最多添加5个轮播图");
			}
			insertImagetextGroupWithTx(null, imgtxtBannerJarr, instId, configType, Integer.valueOf(1));
			
		}
		
		// 插入2imagetextGroup
		if (EmptyUtil.isEmpty(imagetextArr)) {
			throw new BusinessException("请配置2个图文实例信息");
		}
		JSONArray imgtxtJarr_ = JSONArray.parseArray(imagetextArr);
		if (imgtxtJarr_.size() != 2) {
			throw new BusinessException("请配置2个图文实例信息");
		}
		insertImagetextGroupWithTx(null, imgtxtJarr_, instId, configType, null);
		
		// 插入1suList
		if (StringUtils.isEmpty(titleName)) {
			throw new BusinessException("请输入商品列表标题");
		}
		maxShow = 6;
		if (sucId == null) {
			throw new BusinessException("请选择商品组");
		}
		if (titleColor == null) {
			throw new BusinessException("请选择标题颜色");
		}
		insertSuListWithTx(titleColor, titleName, maxShow, sucId, bannerUrl, linkType, linkId, linkUrl, instId, configType,linkableButtonPagePOs);
	}

	/**
	 * 新增商城标签实例
	 * 
	 * @param labelJarr
	 * @param lableIconUrl
	 * @param instId
	 * @param configType
	 */
	private void insertShoppingLabelGroupWithTx(JSONArray labelJarr, String lableIconUrl, Long instId,
			Integer configType) {
		deleteInstRelatedInfo_shoppingLabelGroupWithTx(instId);
		ShoppingLabelGroupPO slgPO = new ShoppingLabelGroupPO();
		slgPO.setImgUrl(lableIconUrl);
		slgPO.setInstId(instId);
		slgPO.setType(configType);
		shoppingLabelGroupWriteDAO.insert(slgPO);
		Long gid = slgPO.getId();
		for (int i = 0; i < labelJarr.size(); i++) {
			JSONObject jobj = labelJarr.getJSONObject(i);
			ShoppingLabelPO slPO = new ShoppingLabelPO();
			slPO.setGroupId(gid);
			String labelName=jobj.getString("labelName");
			if(EmptyUtil.isEmpty(labelName)) {
				throw new BusinessException("请填写标签名称");
			}
			if(labelName.length()>4) {
				throw new BusinessException("标签名称不能超过4个字符");
			}
			slPO.setName(labelName);
			slPO.setSort(i);
			shoppingLabelWriteDAO.insert(slPO);
		}
	}

	/**
	 * 删除商城标签组 删除商城标签组件
	 * 
	 * @param instId
	 */
	private void deleteInstRelatedInfo_shoppingLabelGroupWithTx(Long instId) {
		ShoppingLabelGroupPO slg = shoppingLabelGroupReadDAO.queryShoppingLabelGroupByInstId(instId);
		if (slg != null) {
			// 删除商城标签组件
			ShoppingLabelPO slCond = new ShoppingLabelPO();
			slCond.setGroupId(slg.getId());
			shoppingLabelWriteDAO.delete(slCond);
			// 删除标签组
			ShoppingLabelGroupPO slgCond = new ShoppingLabelGroupPO();
			slgCond.setId(slg.getId());
			shoppingLabelGroupWriteDAO.delete(slgCond);
		}
	}

	/**
	 * 新增图文实例
	 * 
	 * @param imgtxtJarr
	 * @param instId
	 * @param configType
	 */
	private void insertImagetextGroupWithTx(String imagetextTitle, JSONArray imgtxtJarr, Long instId,
			Integer configType, Integer groupType) {
		if (groupType == null) 
			groupType = Integer.valueOf(0);
		deleteInstRelatedInfo_ImagetextGroupWithTx(instId, groupType);
		ImagetextGroupPO ig = new ImagetextGroupPO();
		ig.setTitle(imagetextTitle);
		ig.setInstId(instId);
		ig.setType(configType);
		ig.setGroupType(groupType);
		imagetextGroupWriteDAO.insert(ig);
		Long igId = ig.getId();
		for (int i = 0; i < imgtxtJarr.size(); i++) {
			JSONObject jobj = imgtxtJarr.getJSONObject(i);
			ImagetextPO ipo = new ImagetextPO();
			ipo.setGroupId(igId);
			String imagetextUrl = jobj.getString("imagetextUrl");
			if (EmptyUtil.isBlank(imagetextUrl) || imagetextUrl.length()<4) {
				throw new BusinessException("请上传图片");
			}
			ipo.setImgUrl(imagetextUrl);
			ipo.setSort(i);
			String imagetextName = jobj.getString("imagetextName");
			if (configType.intValue() != ElementTypeConstant.ELEMENT_LIST_501_WEB 
					&& configType.intValue() != ElementTypeConstant.ELEMENT_LIST_601_WEB) {
				
				if (EmptyUtil.isBlank(imagetextName)) {
					throw new BusinessException("请填写图文名称");
				}
				if(imagetextName.length()>4) {
					throw new BusinessException("图文名称不能大于4个字符");
				}
			}
			ipo.setName(imagetextName);
			Integer linkType = jobj.getInteger("linkType");
			if (linkType != null) {
				Long linkId = jobj.getLong("linkId");
				if(linkType==5 && linkId==null) {
					//单个商品非空
					throw new BusinessException("必填项未填写");
				}
				LinkableButtonPO lbPO = new LinkableButtonPO();
				lbPO.setLinkType(linkType);
				String linkParam = jobj.getString("linkParam");
				String linkUrl = jobj.getString("linkUrl");
				checkLinkInfo(linkType, linkId, linkUrl, linkParam);
				lbPO.setLinkId(linkId);
				lbPO.setLinkParam(linkParam);
				lbPO.setLinkUrl(linkUrl);
				linkableButtonWriteDAO.insert(lbPO);
				if(linkType == CmsConstant.CMS_LINK_TYPE_SU_LIST) {
					JSONArray pages = jobj.getJSONArray("linkableButtonPageList");
					if(pages != null && pages.size() > 0) {
						List<LinkableButtonPagePO> linkableButtonPagePOs = JSONArray.parseArray(pages.toJSONString(), LinkableButtonPagePO.class);
						insertLinkButtonPageWithTx(linkableButtonPagePOs,linkType,lbPO.getId());
					}
				}
				
				ipo.setLinkableId(lbPO.getId());
			} else {
				throw new BusinessException("请填写图文链接信息");
			}
			imagetextWriteDAO.insert(ipo);
		}
	}

	/**
	 * 根据实例id删除图文组件相关内容
	 * 
	 * @param instId
	 */
	private void deleteInstRelatedInfo_ImagetextGroupWithTx(Long instId, Integer groupType) {
		ImagetextGroupPO ig = imagetextGroupReadDAO.queryImagetextGroupByInstId(instId, groupType);
		if (ig != null) {
			ImagetextPO itCond = new ImagetextPO();
			itCond.setGroupId(ig.getId());
			List<ImagetextPO> itList = imagetextReadDAO.findAll(itCond,null);
			for (ImagetextPO it : itList) {
				Long linkableId = it.getLinkableId();
//				if (linkableId != null) {
//					LinkableButtonPO lbCond = new LinkableButtonPO();
//					lbCond.setId(linkableId);
//					linkableButtonWriteDAO.delete(lbCond);
//				}
				deleteLinkButtonAndPageWithTx(linkableId);
			}
			imagetextWriteDAO.deleteByPara(itCond);
			ImagetextGroupPO igCond = new ImagetextGroupPO();
			igCond.setId(ig.getId());
			imagetextGroupWriteDAO.delete(igCond);
		}

	}

	/**
	 * 新增iconGroup实例
	 * 
	 * @param count
	 * @param iconJArr
	 * @param iconTitle
	 * @param instId
	 */
	private void insertIconGroupWithTx(Integer count, JSONArray iconJArr, String iconTitle, Long instId,
			Integer configType) {
		// 删除与instid相关的iconGroup
		// 删除与iconGroup相关的icon
		// 删除与icon相关的linkableButton
		deleteInstRelatedInfo_iconGroupWithTx(instId);
		IconGroupPO ig = new IconGroupPO();
		ig.setCount(count);
		ig.setInstId(instId);
		ig.setType(configType);
		if(configType!=200 && configType != 203) {
			//有标题icon组件检查标题非空和长度
			if(EmptyUtil.isBlank(iconTitle)) {
				throw new BusinessException("请填写标题名称");
			}
			if(iconTitle.length()>6) {
				throw new BusinessException("标题信息最多不超出6个字");
			}
		}
		ig.setTitle(iconTitle);
		iconGroupWriteDAO.insert(ig);
		Long igId = ig.getId();
		for (int i = 0; i < iconJArr.size(); i++) {
			JSONObject iconJobj = iconJArr.getJSONObject(i);
			IconPO ic = new IconPO();
			ic.setGroupId(igId);
			String iconName = iconJobj.getString("iconName");
			if (EmptyUtil.isBlank(iconName)) {
				throw new BusinessException("请填写icon名称");
			}
			//字数限制
			if(iconName.length()>4) {
				throw new BusinessException("显示名称最多不超过4个字");
			}
			ic.setName(iconName);
			ic.setSort(i);
			String imgUrl = iconJobj.getString("imgUrl");
			if (EmptyUtil.isBlank(imgUrl) || imgUrl.length()<4) {
				//imgUrl.length()<4逻辑:客户端图片未上传时,回传数字过来,最大是12
				throw new BusinessException("请上传图片");
			}
			ic.setUrl(imgUrl);
			
			// icon简介
			String summary = iconJobj.getString("summary");
			if (configType.intValue() == ElementTypeConstant.ICON_SHOP_TITLE_WEB) {
				if (EmptyUtil.isBlank(summary)) {
					throw new BusinessException("请填写简介");
				}
			}
			ic.setSummary(summary);
			
			Integer linkType = iconJobj.getInteger("linkType");
			List<Long> companyIdList = null;
			if (configType == 202) {
				String companyIds = iconJobj.getString("companyIds");
				if (EmptyUtil.isBlank(companyIds)) {
					throw new BusinessException("每个应用icon都需要配置所属公司");
				}
				companyIdList = StringUtils.stringWithBorder2IdList(companyIds, ",");
				if (companyIdList.size() == 0) {
					throw new BusinessException("每个应用icon都需要配置所属公司");
				}
			}
			if (linkType != null) {
				String linkParam = iconJobj.getString("linkParam");
				Long linkId = iconJobj.getLong("linkId");
				if((linkType==5 || linkType== 7) && linkId==null) {
					//商品限制
					throw new BusinessException("必填项未填写");
				}
				String linkUrl = iconJobj.getString("linkUrl");
				checkLinkInfo(linkType, linkId, linkUrl, linkParam);
				LinkableButtonPO po = new LinkableButtonPO();
				po.setLinkId(linkId);
				po.setLinkParam(linkParam);
				po.setLinkType(linkType);
				po.setLinkUrl(linkUrl);
				linkableButtonWriteDAO.insert(po);
				
				if(linkType == CmsConstant.CMS_LINK_TYPE_SU_LIST) {
					JSONArray pages = iconJobj.getJSONArray("linkableButtonPageList");
					if(pages != null && pages.size() > 0) {
						List<LinkableButtonPagePO> linkableButtonPagePOs = JSONArray.parseArray(pages.toJSONString(), LinkableButtonPagePO.class);
						insertLinkButtonPageWithTx(linkableButtonPagePOs,linkType,po.getId());
					}
				}
				
				ic.setLinkableId(po.getId());
			} else {
				throw new BusinessException("请填写icon链接信息");
			}
			iconWriteDAO.insert(ic);
			// 删除旧关系,建立新关系
			if (companyIdList != null) {
				Long iconId = ic.getId();
				IconCompanyPO deleteCond = new IconCompanyPO();
				deleteCond.setIconId(iconId);
				iconCompanyWriteDAO.deleteByPara(deleteCond);
				for (Long companyId : companyIdList) {
					IconCompanyPO icpo = new IconCompanyPO();
					icpo.setCompanyId(companyId);
					icpo.setIconId(iconId);
					iconCompanyWriteDAO.insert(icpo);
				}
			}
		}
	}

	/**
	 * 检查链接信息
	 * 
	 * @param linkType
	 * @param linkId
	 * @param linkUrl
	 * @param linkParam
	 */
	private void checkLinkInfo(Integer linkType, Long linkId, String linkUrl, String linkParam) {
		switch (linkType) {
		case 1:
			// 1.本地参数
		case 3:
			// 3.H5链接（外部）
		case 4:
			// 4.商品池组(商品列表)
		case 5:
			// 5.商品
			if (linkId == null) {
				throw new BusinessException("请选择跳转目标对象");
			}
			break;
		case 2:
			// 2.H5链接（内部）
			if (EmptyUtil.isBlank(linkUrl)) {
				throw new BusinessException("请填写跳转链接");
			}
			break;
		case 6:
			// 6.无效果
			break;
		case 7:
			// 7.分页tab
			if (linkId == null) {
				throw new BusinessException("请选择跳转目标对象");
			}
			break;
		default:
			throw new BusinessException("无效的链接类型");
		}
	}

	/**
	 * 删除与instid相关的iconGroup 删除与iconGroup相关的icon 删除与icon相关的linkableButton
	 * 
	 * @param instId
	 */
	private void deleteInstRelatedInfo_iconGroupWithTx(Long instId) {
		// 查询iconGroup
		IconGroupPO ig = iconGroupReadDAO.queryIconGroupByInstId(instId);
		if (ig != null) {
			IconPO iconCond = new IconPO();
			iconCond.setGroupId(ig.getId());
			List<IconPO> icList = iconReadDAO.findAll(iconCond,null);
			for (IconPO po : icList) {
				Long linkableId = po.getLinkableId();
				
				deleteLinkButtonAndPageWithTx(linkableId);
			}
			iconWriteDAO.deleteByPara(iconCond);
			IconGroupPO igCond = new IconGroupPO();
			igCond.setId(ig.getId());
			iconGroupWriteDAO.delete(igCond);
		}
	}

	/**
	 * 新增商品列表实例
	 * 
	 * @param titleName
	 * @param sortType
	 * @param maxShow
	 * @param sucId
	 * @param bannerUrl
	 * @param linkType
	 * @param linkId
	 * @param linkUrl
	 * @param linkParam
	 * @param instId
	 * @param configType
	 */
	private void insertSuListWithTx(Long titleColor, String titleName, Integer maxShow, Long sucId, String bannerUrl, Integer linkType,
			Long linkId, String linkUrl, Long instId, Integer configType ,List<LinkableButtonPagePO> linkableButtonPagePOs) {
		// 删除原有商品列表
		deleteInstRelatedInfo_suListWithTx(instId);

		// 如果有banner,插入linkableButton
		Long lbId = null;
		if (!EmptyUtil.isBlank(bannerUrl)) {
			if(linkType!=null && (linkType==5 || linkType==7) && linkId==null) {
				throw new BusinessException("必填项未填写");
			}
			if(linkType == null) {
				throw new BusinessException("请填写跳转链接");
			}else {
				checkLinkInfo(linkType,linkId,linkUrl,null);
			}
			LinkableButtonPO lb = new LinkableButtonPO();
			lb.setLinkId(linkId);
			lb.setLinkType(linkType);
			lb.setLinkUrl(linkUrl);
			linkableButtonWriteDAO.insert(lb);
			lbId = lb.getId();
			insertLinkButtonPageWithTx(linkableButtonPagePOs,linkType,lbId);
		}
		// 插入suList
		SuListPO suList = new SuListPO();
		suList.setBannerUrl(bannerUrl);
		suList.setInstId(instId);
		suList.setLinkableId(lbId);
		suList.setMaxShow(maxShow);
		suList.setShowType(configType);
		suList.setSucId(sucId);
		suList.setTitleName(titleName);
		suList.setTitleColor(titleColor);
		suListWriteDAO.insert(suList);
	}

	/**
	 * 删除商品列表及其相关信息
	 * 
	 * @param instId
	 */
	private void deleteInstRelatedInfo_suListWithTx(Long instId) {
		SuListPO sl = suListReadDAO.querySuListByInstId(instId);
		if (sl != null) {
			Long linkableId = sl.getLinkableId();
			
			deleteLinkButtonAndPageWithTx(linkableId);
			
			SuListPO slCond = new SuListPO();
			slCond.setId(sl.getId());
			suListWriteDAO.delete(slCond);
		}
	}

	/**
	 * 更改轮播图与实例的关系
	 * 
	 * @param instId
	 * @param bids
	 */
	private void editBannerInstWithTx(Long instId, List<Long> bids) {
		// 根据instId删除轮播图实例关系
		bannerInstWriteDAO.deleteBannerInstByinstId(instId);
		// 插入关系
		for (Long bid : bids) {
			BannerInstPO po = new BannerInstPO();
			po.setBannerId(bid);
			po.setInstId(instId);
			bannerInstWriteDAO.insert(po);
		}
	}

	/**
	 * 更改实例与公司的关系
	 * 
	 * @param instId
	 * @param cids
	 */
	private void editInstCompanyWithTx(Long instId, List<Long> cids) {
		// 根据instId删除实例公司关系
		instCompanyWriteDAO.deleteInstCompanyByinstId(instId);
		// 插入关系
		for (Long cid : cids) {
			InstCompanyPO po = new InstCompanyPO();
			po.setCompanyId(cid);
			po.setInstId(instId);
			instCompanyWriteDAO.insert(po);
		}
	}
	
	/**
	 *  删除 LinkButton表记录的 同时 删除 linkButtonPage 表记录
	 * @param linkableId
	 */
	private void deleteLinkButtonAndPageWithTx(Long linkableId) {
		
		if (linkableId != null) {
			
			LinkableButtonPO lbCond = new LinkableButtonPO();
			lbCond.setId(linkableId);
			linkableButtonWriteDAO.delete(lbCond);
			//删除 商品组合 跳转页配置
			linkableButtonPageWriteDAO.deleteByPara(new LinkableButtonPagePO(linkableId));
			
		}
	}
	
	/**
	 *  linkType 为 商品组合的时候 新增 linkbuttonpage 记录
	 * @param linkableButtonPagePO
	 */
	private void insertLinkButtonPageWithTx(List<LinkableButtonPagePO> linkableButtonPagePOs,Integer linkType,Long linkableId) {
		
		if(linkableId != null  && linkType != null && linkType == CmsConstant.CMS_LINK_TYPE_SU_LIST) {
			
			if(linkableButtonPagePOs != null && linkableButtonPagePOs.size() > 0) {
				
				for (LinkableButtonPagePO linkableButtonPagePO : linkableButtonPagePOs) {
					linkableButtonPagePO.setLinkableId(linkableId);
				}
				
				linkableButtonPageWriteDAO.insertBatch(linkableButtonPagePOs);
				
			}
			
		}
		
	}
}
