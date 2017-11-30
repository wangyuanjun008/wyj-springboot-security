package com.wyj.service.system;

import java.util.Set;

import com.wyj.common.service.BaseService;
import com.wyj.entity.system.Role;

/**
 * 角色Sercice
 * 
 * 
 * @author：WangYuanJun
 * @date：2017年11月22日 下午8:25:35
 */
public interface RoleService extends BaseService<Role, Long> {

    int batchRemoveRole(Long[] ids);

    Role getRoleById(Long roleId);

    int updateRoleAuthorization(Role role);
    
    Set<String> listRoleSignByUserId(Long userId);
}
