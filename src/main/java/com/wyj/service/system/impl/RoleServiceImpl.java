package com.wyj.service.system.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wyj.common.dao.BaseMapper;
import com.wyj.common.service.impl.BaseServiceImpl;
import com.wyj.dao.system.RoleMapper;
import com.wyj.entity.system.Role;
import com.wyj.service.system.RoleRelMenuService;
import com.wyj.service.system.RoleService;
import com.wyj.service.system.UserRelRoleService;

/**
 * 角色SerciceImpl
 * 
 * 
 * @author：WangYuanJun
 * @date：2017年11月22日 下午8:25:35
 */
@Service(value = "RoleService")
public class RoleServiceImpl extends BaseServiceImpl<Role, Long> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRelRoleService userRelRoleService;

    @Autowired
    private RoleRelMenuService roleRelMenuService;
    
    @Override
    public BaseMapper<Role, Long> getMapper() {
        return roleMapper;
    }

    @Override
    public int batchRemoveRole(Long[] ids) {
        int count = roleMapper.batchRemove(ids);
        userRelRoleService.batchRemoveByRoleId(ids);
        roleRelMenuService.batchRemoveByRoleId(ids);
        return count;
    }

    @Override
    public Role getRoleById(Long roleId) {
        Role role = roleMapper.getObjectById(roleId);
        List<Long> menuIds = roleRelMenuService.listMenuIdByRoleId(roleId);
        role.setMenus(menuIds);
        return role;
    }

    @Override
    public int updateRoleAuthorization(Role role) {
        Long roleId = role.getRoleId();
        int count = roleRelMenuService.remove(roleId);
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("roleId", roleId);
        List<Long> menuIds = role.getMenus();
        if(menuIds.size() > 0) {
            hashMap.put("menus", menuIds);
            count = roleRelMenuService.save(hashMap);
        }
        return count;
    }

    @Override
    public Set<String> listRoleSignByUserId(Long userId) {
        List<String> roles = roleMapper.listRoleSignByUserId(userId);
        Set<String> rolesSet = new HashSet<String>();
        for(String role : roles) {
            if(StringUtils.isNotBlank(role)) {
                rolesSet.addAll(Arrays.asList(role.trim().split(",")));
            }
        }
        return rolesSet;
    }

//    @Override
//    public int saveRole(Role role) {
//        // TODO Auto-generated method stub
//        return 0;
//    }
//
//    @Override
//    public int updateRole(Role role) {
//        // TODO Auto-generated method stub
//        return 0;
//    }

}
