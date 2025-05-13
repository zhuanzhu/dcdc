package com.egeo.components.user.service.read.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.service.read.PlatformReadService;
import com.egeo.components.user.service.read.RoleReadService;
import com.egeo.components.user.service.write.UserPlatformWriteService;
import com.egeo.exception.BusinessException;
import com.egeo.components.user.converter.RoleConverter;
import com.egeo.components.user.dto.PlatformDTO;
import com.egeo.components.user.dto.PlatformRole;
import com.egeo.components.user.dto.RoleDTO;
import com.egeo.components.user.dto.UserPlatformDTO;
import com.egeo.components.user.po.RolePO;
import com.egeo.components.user.dao.read.RoleReadDAO;
import com.egeo.components.user.converter.RoleConverter;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("roleReadService")
public class RoleReadServiceImpl implements RoleReadService {
    private static final Long MENUPID = 1L;
    
    @Autowired
	private RoleReadDAO roleReadDAO ;

    @Autowired
    private PlatformReadService platformReadService;
    
    @Autowired
    private UserPlatformWriteService userPlatformWriteService;

    @Override
    public List<RoleDTO> findRoleListByUserId(Long id) {
		RolePO rolepo = new RolePO();
		rolepo.setId(id);		
        List<RolePO> poList = roleReadDAO.findRoleListByUserId(rolepo);
        if (poList != null && poList.size() > 0) {
            return RoleConverter.toDTO(poList);
        }
        return null;
    }

    @Override
    public RoleDTO findRoleByRoleName(String name) {
		RolePO rolePO = null;
        RolePO po = new RolePO();
        po.setName(name);
        List<RolePO> poList = roleReadDAO.findAll(po,null);
        if(poList.size() == 1){
            rolePO = poList.get(0);
        }else if(poList.size() > 1){
            throw new BusinessException("role的名字不唯一");
        }

        if (rolePO != null) {
            return RoleConverter.toDTO(rolePO);
        }
        return null;
    }

    @Override
    public RoleDTO findRoleByRoleNamePlatformId(String name,Long platformId) {

		RolePO po = new RolePO();
		po.setPlatformId(platformId);
		po.setName(name);
		List<RolePO> poList = roleReadDAO.findAll(po,null);
		if(poList.size() == 1){
			return RoleConverter.toDTO(poList.get(0));
		}else if(poList.size() > 1){
			throw new BusinessException("role的名字不唯一");
		}else{
			return null;
		}
    }

    @Override
    public List<RoleDTO> findAll(RoleDTO dto) {
        List<RolePO> findAll = roleReadDAO.findAll(RoleConverter.toPO(dto),null);

        return RoleConverter.toDTO(findAll);
    }

    @Override
    public List<PlatformRole> roleByPlatformIdWithTx(String platformId,Long id) {
        List<String> menuList = null;
        int row = 0;
        if(!platformId.equals("")){
            menuList = Arrays.asList(platformId.split(","));
        }
        // 根据用户id查找Platform集合
        List<PlatformDTO> userList = platformReadService.PlatformByUId(id);

        // 查找需要删除的UserPlatform关系,如果复选框集合一个没选就把与用户相关的所有平台删除
        StringBuffer delUserPlatform = new StringBuffer();
        if(menuList != null){
            for (PlatformDTO platformDTO : userList) {
                if (!menuList.contains(platformDTO.getId() + "")) {
                    delUserPlatform.append(platformDTO.getId() + ",");
                }
            }
        }else{
            // 调用批量删除的方法
            UserPlatformDTO platformDTO = new UserPlatformDTO();
            platformDTO.setUserId(id);
            row = userPlatformWriteService.delUserPlatformWithTx(platformDTO);
        }
        // 查找需要添加的UserPlatform关系
        List<String> ids = new ArrayList<String>();
        for (PlatformDTO platformDTO : userList) {
            ids.add(platformDTO.getId() + "");
        }
        List<String> setUserPlatform = new ArrayList<String>();
        if(menuList != null){
            for (String string : menuList) {
                if (!ids.contains(string)) {
                    setUserPlatform.add(string);
                }
            }
        }
        if (delUserPlatform.length() > 0) {
            delUserPlatform.deleteCharAt(delUserPlatform.length() - 1);
        }

        if (delUserPlatform.length() > 0) {
            // 调用批量删除的方法
            UserPlatformDTO platformDTO = new UserPlatformDTO();
            platformDTO.setUserId(id);
            platformDTO.setPlatformIds(delUserPlatform.toString());
            row = userPlatformWriteService.delUserPlatformWithTx(platformDTO);
        }

        if (setUserPlatform.size() > 0) {
            // 调用批量添加的方法
            row = userPlatformWriteService.setUserPlatformWithTx(setUserPlatform, id);
        }

        List<PlatformRole> list = new ArrayList<PlatformRole>();
        if(menuList != null){
            for (String string : menuList) {
                PlatformRole platformRole = new PlatformRole();
                Long long1 = Long.valueOf(string);
                PlatformDTO platformDTO = platformReadService.find(long1);
                RolePO po = new RolePO();
                po.setPlatformId(long1);
                List<RolePO> list2 = roleReadDAO.findAll(po,null);
                List<RoleDTO> dto = RoleConverter.toDTO(list2);
                List<RolePO> listByUserId = roleReadDAO.roleListByUserId(id);
                for (RoleDTO roleDTO : dto) {
                    for (RolePO rolePO : listByUserId) {
                        if(roleDTO.getId().equals(rolePO.getId())){
                            roleDTO.setChecked(true);
                        }
                    }
                }
                platformRole.setPlatformDTO(platformDTO);
                platformRole.setList(dto);
                list.add(platformRole);
            }
        }
        return list;
    }
    
    public List<RoleDTO> roleListByUserId(Long id){
        List<RolePO> list = roleReadDAO.roleListByUserId(id);
        return RoleConverter.toDTO(list);
    }

    @Override
    public PageResult<RoleDTO> roleListPage(Pagination page, RoleDTO dto) {
        PlatformDTO platformDTO = null;
        if(!dto.getPlatformId().equals(this.MENUPID)){
            platformDTO = platformReadService.find(dto.getPlatformId());
        }else{
            platformDTO = new PlatformDTO();
            platformDTO.setName("迩格");
        }
        
        RolePO po = RoleConverter.toPO(dto);
		
		
        
        int cnt = roleReadDAO.cntRoleListByParam(po, dto.getMaxcreateTime(), dto.getMincreateTime());
        List<RolePO> listT = new ArrayList<RolePO>();
        if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
            page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
            listT = roleReadDAO.findRoleListByParam(po, dto.getMaxcreateTime(), dto.getMincreateTime());
        }else {
			listT = new ArrayList<RolePO>();
		}
        PageResult<RolePO> pageResult = new PageResult<RolePO>();
        pageResult.setList(listT);
        pageResult.setTotalSize(cnt);
        pageResult.setPageNo(page.getPageNo());
        pageResult.setPageSize(page.getPageSize());
		
        
        List<RoleDTO> list = new ArrayList<RoleDTO>();
        for (RolePO tmp : pageResult.getList()) {
            RoleDTO roleDTO = RoleConverter.toDTO(tmp);
                list.add(roleDTO);
        }
        PageResult<RoleDTO> result = new PageResult<RoleDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
    }

    @Override
    public RoleDTO findById(RoleDTO roleDTO) {
        RolePO po = roleReadDAO.findById(RoleConverter.toPO(roleDTO));
        if(po != null){
            return RoleConverter.toDTO(po);
        }
        return null;
    }
}
