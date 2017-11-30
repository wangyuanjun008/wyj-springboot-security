package com.wyj.service.system;

import java.util.List;

import com.wyj.common.service.BaseService;
import com.wyj.entity.system.RoleRelMenu;

/**
 * 角色关联菜单Service
 * 
 * 
 * @author：WangYuanJun
 * @date：2017年11月22日 下午8:25:35
 */
public interface RoleRelMenuService extends BaseService<RoleRelMenu, Long>{
    
    int batchRemoveByMenuId(Long[] id);
    
    int batchRemoveByRoleId(Long[] id);
    
    List<Long> listMenuIdByRoleId(Long id);
}
