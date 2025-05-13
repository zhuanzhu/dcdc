/**
 * Created By: XI
 * Created On: 2018-10-23 14:30:56
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.egeo.components.user.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.AntPathMatcher;

import com.egeo.components.user.bean.UniAuthMenu;
import com.egeo.components.user.bean.UniAuthRoles;
import com.egeo.components.user.bean.UniAuthUserInfo;
import com.egeo.components.user.bean.UniauthRoleMenu;
import com.egeo.components.user.common.Constants;
import com.egeo.components.user.common.ResponseCodeEnum;
import com.egeo.components.user.dao.write.UniAuthMenuMapper;
import com.egeo.components.user.dao.write.UniAuthRolesMapper;
import com.egeo.components.user.dao.write.UniAuthUserInfoMapper;
import com.egeo.components.user.dao.write.UniauthResourceMapper;
import com.egeo.components.user.dao.write.UniauthRoleMenuMapper;
import com.egeo.components.utils.DateHelper;
import com.egeo.components.utils.UtilHelper;
import com.egeo.utils.log.XLogger;
import com.egeo.vo.BaseResponse;

@Service("uniauthMenuService")
public class UniauthMenuService {

	@Autowired
	private UniAuthMenuMapper uniAuthMenuMapper;
	@Autowired
	private UniAuthUserInfoMapper uniAuthUserInfoMapper;
	@Autowired
	private UniauthDomainService uniauthDomainService;
	@Autowired
	private UniAuthRolesMapper uniAuthRolesMapper;
	@Autowired
	private UniauthRoleMenuMapper uniauthRoleMenuMapper;
	@Autowired
	private UniauthResourceMapper uniauthResourceMapper;
	@Autowired
	private UniauthUserInfoService uniauthUserInfoService;

	private static final XLogger logger = XLogger.getLogger(UniauthMenuService.class);

	/**
	 * 根据查询条件查询所有记录
	 * 
	 * @return
	 */
	public List<UniAuthMenu> listByProperty(UniAuthMenu uniAuthMenu) {
		return uniAuthMenuMapper.listByProperty(uniAuthMenu);
	}

	/**
	 * 获取菜单树接口
	 * 
	 * @return
	 */
	public List<UniAuthMenu> getTree(UniAuthMenu uniAuthMenu) {
		return uniAuthMenuMapper.getTree(uniAuthMenu);
	}

	/**
	 * 插入记录
	 * 
	 * @param uniAuthMenu
	 * @param userUuid
	 * @return
	 */
	@Transactional
	public BaseResponse<String> insert(UniAuthMenu uniAuthMenu, String userUuid) {
		BaseResponse<String> response = new BaseResponse<String>();

		// 验证参数是否为空
		if (UtilHelper.isEmpty(uniAuthMenu) || UtilHelper.isEmpty(uniAuthMenu.getMenuCode())
				|| UtilHelper.isEmpty(uniAuthMenu.getMenuName()) || UtilHelper.isEmpty(uniAuthMenu.getMenuType())) {
			response.setCode(ResponseCodeEnum.PARAM_IS_NULL.getCode());
			response.setMsg(ResponseCodeEnum.PARAM_IS_NULL.getMsg());
			return response;
		}

		String menuCode = uniAuthMenu.getMenuCode();
		// 格式校验
		// MenuCode 限制输入只能是 字母、数字和下划线，其他不允许
		menuCode.matches("[0-9A-Za-z_]*");

		// 查询是否以有存在的菜单code
		UniAuthMenu condition = new UniAuthMenu();
		condition.setMenuCode(uniAuthMenu.getMenuCode());
		int count = uniAuthMenuMapper.findByCount(condition);
		if (count > 0) {
			response.setCode(ResponseCodeEnum.PARAM_HAS_EXIST.getCode());
			response.setMsg(String.format(ResponseCodeEnum.PARAM_HAS_EXIST.getMsg(), uniAuthMenu.getMenuCode()));

			return response;
		}

		// 设置操作时间
		String crrTime = DateHelper.nowString(DateHelper.TIME_PATTERN_DEFAULT);
		uniAuthMenu.setCreateTime(crrTime);
		uniAuthMenu.setUpdateTime(crrTime);

		// 设置操作人
		UniAuthUserInfo uniAuthUserInfo = uniAuthUserInfoMapper.getByUuid(userUuid);
		if (!UtilHelper.isEmpty(uniAuthUserInfo)) {
			uniAuthMenu.setCreateBy(uniAuthUserInfo.getUserId());
			uniAuthMenu.setUpdateBy(uniAuthUserInfo.getUserId());
		}

		// 默认状态为启用
		uniAuthMenu.setMenuState(UniAuthMenu.MENU_STATE_ENABLE);
		// 设置部门的所有父级
		if (UtilHelper.isEmpty(uniAuthMenu.getMenuParentCode())) {
			uniAuthMenu.setMenuParentCode("$");
			uniAuthMenu.setMenuParentCodes("$-");
		} else {
			condition = new UniAuthMenu();
			condition.setMenuCode(uniAuthMenu.getMenuParentCode());

			List<UniAuthMenu> list = uniAuthMenuMapper.getByUniAuthMenu(condition);
			if (!UtilHelper.isEmpty(list)) {
				uniAuthMenu
						.setMenuParentCodes(list.get(0).getMenuParentCodes() + uniAuthMenu.getMenuParentCode() + "-");
			}
		}

		uniAuthMenuMapper.insert(uniAuthMenu);

		if (UniAuthMenu.MENU_TYPE_SYS == uniAuthMenu.getMenuType()) {
			uniauthDomainService.insert(uniAuthMenu.getMid(), uniAuthMenu.getDomain());
		}

		return response;
	}

	/**
	 * 更新记录
	 * 
	 * @param uniAuthMenu
	 * @param userUuid
	 * @return
	 */
	@Transactional
	public BaseResponse<String> update(UniAuthMenu uniAuthMenu, String userUuid) {
		BaseResponse<String> response = new BaseResponse<String>();

		// 验证参数是否为空
		if (UtilHelper.isEmpty(uniAuthMenu) || UtilHelper.isEmpty(uniAuthMenu.getMid())
				|| UtilHelper.isEmpty(uniAuthMenu.getMenuName()) || UtilHelper.isEmpty(uniAuthMenu.getMenuType())) {
			response.setCode(ResponseCodeEnum.PARAM_IS_NULL.getCode());
			response.setMsg(ResponseCodeEnum.PARAM_IS_NULL.getMsg());

			return response;
		}

		// 设置操作时间
		String crrTime = DateHelper.nowString(DateHelper.TIME_PATTERN_DEFAULT);
		uniAuthMenu.setUpdateTime(crrTime);

		// 设置操作人
		UniAuthUserInfo uniAuthUserInfo = uniAuthUserInfoMapper.getByUuid(userUuid);
		if (!UtilHelper.isEmpty(uniAuthUserInfo)) {
			uniAuthMenu.setUpdateBy(uniAuthUserInfo.getUserId());
		}

		uniAuthMenu.setId(null);
		uniAuthMenu.setMenuCode(null);
		// 设置部门的所有父级
		if (UtilHelper.isEmpty(uniAuthMenu.getMenuParentCode())) {
			uniAuthMenu.setMenuParentCode("$");
			uniAuthMenu.setMenuParentCodes("$");
		} else {
			UniAuthMenu condition = new UniAuthMenu();
			condition.setMenuCode(uniAuthMenu.getMenuParentCode());

			List<UniAuthMenu> list = uniAuthMenuMapper.getByUniAuthMenu(condition);
			if (!UtilHelper.isEmpty(list)) {
				uniAuthMenu
						.setMenuParentCodes(list.get(0).getMenuParentCodes() + uniAuthMenu.getMenuParentCode() + "-");
			}
		}

		uniAuthMenuMapper.updatePart(uniAuthMenu);

		if (UniAuthMenu.MENU_TYPE_SYS == uniAuthMenu.getMenuType()) {
			uniauthDomainService.insert(uniAuthMenu.getMid(), uniAuthMenu.getDomain());
		}

		// 获取拥有该菜单权限的用户，强制下线
		logger.info("修改某菜单时，强制下线用户处理。菜单ID:{}", uniAuthMenu.getMid());
		List<UniAuthUserInfo> userList = uniAuthUserInfoMapper.getUserByMenuId(uniAuthMenu.getMid());
		for (UniAuthUserInfo user : userList) {
			logger.info("用户ID:{},用户UUID:{}", user.getUserId(), user.getUuId());
			uniauthUserInfoService.logout(new String[] { Constants.SYS_CODE_BI, Constants.SYS_CODE_CST }, user);
		}

		return response;
	}

	/**
	 * 根据主键删除记录
	 * 
	 * @param mid
	 * @param userUuid
	 * @return
	 */
	@Transactional
	public int deleteByPK(Integer mid, String userUuid) {
		UniAuthMenu uniAuthMenu = uniAuthMenuMapper.getByPK(mid);

		UniAuthMenu condition = new UniAuthMenu();
		condition.setMenuParentCodes(uniAuthMenu.getMenuParentCodes() + uniAuthMenu.getMenuCode() + "-");
		condition.setMenuState(UniAuthMenu.MENU_STATE_DELETE);

		// 设置操作时间
		String crrTime = DateHelper.nowString(DateHelper.TIME_PATTERN_DEFAULT);
		condition.setUpdateTime(crrTime);

		// 设置操作人
		UniAuthUserInfo uniAuthUserInfo = uniAuthUserInfoMapper.getByUuid(userUuid);
		if (!UtilHelper.isEmpty(uniAuthUserInfo)) {
			condition.setUpdateBy(uniAuthUserInfo.getUserId());
			uniAuthMenu.setUpdateBy(uniAuthUserInfo.getUserId());
		}

		int count = uniAuthMenuMapper.updateStateByParentCodes(condition);

		uniAuthMenu.setUpdateTime(crrTime);
		uniAuthMenu.setMenuState(UniAuthMenu.MENU_STATE_DELETE);
		count += uniAuthMenuMapper.updatePart(uniAuthMenu);

		return count;
	}

	/**
	 * 根据ID查询资源详情
	 * 
	 * @param mid
	 * @return
	 */
	public UniAuthMenu getDetails(Integer mid) {
		UniAuthMenu uniAuthMenu = uniAuthMenuMapper.getByPK(mid);

		if (!UtilHelper.isEmpty(uniAuthMenu) && UniAuthMenu.MENU_TYPE_SYS == uniAuthMenu.getMenuType()) {
			String domain = uniauthDomainService.getDomain(uniAuthMenu.getMid());
			uniAuthMenu.setDomain(domain);
		}

		return uniAuthMenu;
	}

	/**
	 * 查询设置权限菜单
	 * 
	 * @param userUuid
	 * @param rid
	 * @return
	 */
	public Map<String, List<?>> getTreeByUuidAndRid(String userUuid, Integer rid) {
		List<UniAuthRoles> roles = uniAuthRolesMapper.getRoleByUuid(userUuid);
		if (UtilHelper.isEmpty(roles))
			return null;

		List<Integer> rids = null;
		Set<String> rcodes = roles.stream().map(UniAuthRoles::getRcode).collect(Collectors.toSet());
		if (!rcodes.contains(UniAuthRoles.ADMIN_ROLE_CODE)) {
			rids = roles.stream().map(UniAuthRoles::getRid).collect(Collectors.toList());
		}

		List<UniAuthMenu> allList = uniAuthMenuMapper.getListByRids(rids);
		if (UtilHelper.isEmpty(allList)) {
			return null;
		}

		Map<String, UniAuthMenu> allMap = allList.stream()
				.collect(Collectors.toMap(val -> val.getMenuCode(), val -> val));

		List<UniAuthMenu> selectedList = uniAuthMenuMapper.getListByRids(Arrays.asList(new Integer[] { rid }));
		Map<String, UniAuthMenu> selectedMap = new HashMap<>();
		if (!UtilHelper.isEmpty(selectedList)) {
			selectedMap = selectedList.stream().collect(Collectors.toMap(val -> val.getMenuCode(), val -> val));
		}

		List<UniAuthMenu> allTree = new ArrayList<>();
		for (UniAuthMenu o : allList) {
			UniAuthMenu parent = allMap.get(o.getMenuParentCode());
			UniAuthMenu uniAuthMenu = allMap.get(o.getMenuCode());
			if (selectedMap.keySet().contains(uniAuthMenu.getMenuCode())) {
				uniAuthMenu.setSelected(true);
			} else {
				uniAuthMenu.setSelected(false);
			}

			if (UtilHelper.isEmpty(parent)) {
				allTree.add(uniAuthMenu);
			} else {
				List<UniAuthMenu> children = parent.getChildren();
				if (UtilHelper.isEmpty(children)) {
					children = new ArrayList<>();
					parent.setChildren(children);
				}

				children.add(uniAuthMenu);
			}
		}

		List<UniAuthMenu> selectedTree = new ArrayList<>();
		if (!UtilHelper.isEmpty(selectedList)) {
			for (UniAuthMenu o : selectedList) {
				UniAuthMenu parent = selectedMap.get(o.getMenuParentCode());
				UniAuthMenu uniAuthMenu = selectedMap.get(o.getMenuCode());
				uniAuthMenu.setSelected(true);

				if (UtilHelper.isEmpty(parent)) {
					selectedTree.add(uniAuthMenu);
				} else {
					List<UniAuthMenu> children = parent.getChildren();
					if (UtilHelper.isEmpty(children)) {
						children = new ArrayList<>();
						parent.setChildren(children);
					}

					children.add(uniAuthMenu);
				}
			}
		}

		List<String> selected = null;
		if (!selectedMap.isEmpty()) {
			selected = new ArrayList<>();
			for (UniAuthMenu o : selectedMap.values()) {
				selected.add(o.getMenuCode());
//				if (UtilHelper.isEmpty(o.getChildren())){
//					selected.add(o.getMenuCode());
//				}
			}
		}

		Map<String, List<?>> results = new HashMap<>();
		results.put("allTree", allTree);
		results.put("selectedTree", selected);

		return results;
	}

	/**
	 * 保存权限
	 * 
	 * @param rid
	 * @param menuCodes
	 */
	public void insertRoleMenu(Integer rid, List<String> menuCodes) {
		logger.info("方法名:insertRoleMenu \t参数：rid={},menuCodes={}", rid, StringUtils.join(menuCodes, ","));
		UniauthRoleMenu uniauthRoleMenu = new UniauthRoleMenu();
		uniauthRoleMenu.setRid(rid);
		uniauthRoleMenuMapper.deleteByProperty(uniauthRoleMenu);

		if (!UtilHelper.isEmpty(menuCodes)) {
			List<UniauthRoleMenu> list = new ArrayList<>();

			for (String menuCode : menuCodes) {
				uniauthRoleMenu = new UniauthRoleMenu();
				uniauthRoleMenu.setRid(rid);
				uniauthRoleMenu.setMenuCode(menuCode);

				list.add(uniauthRoleMenu);
			}

			uniauthRoleMenuMapper.insertBatch(list);

			// 该角色下的用户强制登出
			logger.info("修改角色权限时，强制下线用户处理。角色ID:{}", rid);
			List<UniAuthUserInfo> userList = uniAuthUserInfoMapper.getUserByRole(rid);
			for (UniAuthUserInfo user : userList) {
				logger.info("用户ID:{},用户UUID:{}", user.getUserId(), user.getUuId());
				uniauthUserInfoService.logout(Constants.SYS_CODES, user);
			}
		}
	}

	/**
	 * 查询后台菜单
	 * 
	 * @param userUuid
	 * @return
	 */
//	public List<UniAuthMenu> getMenuListByUuid(String userUuid) {
//		List<UniAuthRoles> roles = uniAuthRolesMapper.getRoleByUuid(userUuid);
//		if (UtilHelper.isEmpty(roles))
//			return null;
//
//		List<Integer> rids = null;
//		Set<String> rcodes = roles.stream().map(UniAuthRoles::getRcode).collect(Collectors.toSet());
//		if (!rcodes.contains(UniAuthRoles.ADMIN_ROLE_CODE)){
//			rids = roles.stream().map(UniAuthRoles::getRid).collect(Collectors.toList());
//		}
//
//		List<UniAuthMenu> list = uniAuthMenuMapper.getMenuByRids(rids, null);
//		return list;
//	}

	/**
	 * 查询后台菜单
	 * 
	 * @param userUuid
	 * @param sysCode
	 * @return
	 */
	public List<UniAuthMenu> getMenuByUuid(String userUuid, String sysCode) {
		List<UniAuthRoles> roles = uniAuthRolesMapper.getRoleByUuid(userUuid);
		if (UtilHelper.isEmpty(roles))
			return null;

		List<Integer> rids = null;
		Set<String> rcodes = roles.stream().map(UniAuthRoles::getRcode).collect(Collectors.toSet());
		if (!rcodes.contains(UniAuthRoles.ADMIN_ROLE_CODE)) {
			rids = roles.stream().map(UniAuthRoles::getRid).collect(Collectors.toList());
		}

		List<UniAuthMenu> list = uniAuthMenuMapper.getMenuByRids(rids, "$-" + sysCode + "-");
		if (UtilHelper.isEmpty(list)) {
			return null;
		}

		Map<String, UniAuthMenu> map = list.stream().collect(Collectors.toMap(val -> val.getMenuCode(), val -> val));

		List<UniAuthMenu> tree = new ArrayList<>();
		for (UniAuthMenu o : list) {
			UniAuthMenu parent = map.get(o.getMenuParentCode());
			UniAuthMenu uniAuthMenu = map.get(o.getMenuCode());
			if (uniAuthMenu.getMenuState() != 1) {
				// 非启用的菜单直接跳过
				continue;
			}
			if (UtilHelper.isEmpty(parent)) {
				tree.add(uniAuthMenu);
			} else {
				List<UniAuthMenu> children = parent.getChildren();
				if (UtilHelper.isEmpty(children)) {
					children = new ArrayList<>();
					parent.setChildren(children);
				}
				children.add(uniAuthMenu);
			}
		}
		return tree;
	}

	/**
	 * 查询访问资源
	 * 
	 * @param userUuid
	 * @param domain
	 * @param url
	 * @return
	 */
	public Boolean getResourceByUuidAndDomain(String userUuid, String domain, String url) {
		if (!url.startsWith("/")) {
			url = "/" + url;
		}

		List<String> commonResources = uniauthResourceMapper.getCommonResource();
		if (!UtilHelper.isEmpty(commonResources)) {
			for (String resource : commonResources) {
				if (!resource.startsWith("/")) {
					resource = "/" + resource;
				}

				if (url.startsWith(resource)) {
					return true;
				} else {
					// 字符串是否与正则表达式相匹配
					if (new AntPathMatcher().match(resource, url)) {
						return true;
					}
				}

			}
		}

		List<Integer> rids = null;
		if (!UtilHelper.isEmpty(userUuid)) {
			List<UniAuthRoles> roles = uniAuthRolesMapper.getRoleByUuid(userUuid);

			if (!UtilHelper.isEmpty(roles)) {
				Set<String> rcodes = roles.stream().map(UniAuthRoles::getRcode).collect(Collectors.toSet());
				if (rcodes.contains(UniAuthRoles.ADMIN_ROLE_CODE)) {
					return true;
				}

				rids = roles.stream().map(UniAuthRoles::getRid).collect(Collectors.toList());
			} else {
				return false;
			}
		}

		List<String> resources = uniAuthMenuMapper.getResourceByRidsAndDomain(domain, rids);
		if (UtilHelper.isEmpty(resources)) {
			return false;
		}

		for (String resource : resources) {
			if (!resource.startsWith("/")) {
				resource = "/" + resource;
			}

			if (url.startsWith(resource)) {
				return true;
			} else {
				// 字符串是否与正则表达式相匹配
				if (new AntPathMatcher().match(resource, url)) {
					return true;
				}
			}

		}

		return false;
	}

	/**
	 * 拖动菜单
	 * 
	 * @param mid
	 * @param newIndex
	 */
	public void updateIndex(Integer mid, Integer newIndex) {
		UniAuthMenu menu = uniAuthMenuMapper.getByPK(mid);
		if (UtilHelper.isEmpty(menu) || menu.getMenuIndex() == newIndex)
			return;

		int index = -1;
		int minIndex = menu.getMenuIndex();
		int maxIndex = newIndex + 1;
		if (menu.getMenuIndex() > newIndex) {
			index = 1;
			minIndex = newIndex - 1;
			maxIndex = menu.getMenuIndex();
		}

		uniAuthMenuMapper.updateIndex(index, menu.getMenuParentCodes(), minIndex, maxIndex);

		menu = new UniAuthMenu();
		menu.setMid(mid);
		menu.setMenuIndex(newIndex);

		uniAuthMenuMapper.updatePart(menu);
	}
}