package com.wyj.dao.system;

import java.util.List;

import com.wyj.common.dao.BaseMapper;
import com.wyj.entity.system.Role;
/**
 * 角色Mapper
 * 
 * 
 * @author：WangYuanJun
 * @date：2017年11月22日 下午8:25:35
 */
public interface RoleMapper extends BaseMapper<Role, Long>{
    
    List<String> listRoleSignByUserId(Long userId);
}
