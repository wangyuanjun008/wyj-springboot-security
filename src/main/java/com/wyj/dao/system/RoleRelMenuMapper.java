package com.wyj.dao.system;

import java.util.List;

import com.wyj.common.dao.BaseMapper;
import com.wyj.entity.system.RoleRelMenu;

/**
 * 角色关联菜单Mapper
 * 
 * 
 * @author：WangYuanJun
 * @date：2017年11月22日 下午8:25:35
 */
public interface RoleRelMenuMapper extends BaseMapper<RoleRelMenu, Long>{

    int batchRemoveByMenuId(Long[] id);
    
    int batchRemoveByRoleId(Long[] id);
    
    List<Long> listMenuIdByRoleId(Long id);
}
