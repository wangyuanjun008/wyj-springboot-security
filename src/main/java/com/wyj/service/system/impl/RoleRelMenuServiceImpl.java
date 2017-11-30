package com.wyj.service.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wyj.common.dao.BaseMapper;
import com.wyj.common.service.impl.BaseServiceImpl;
import com.wyj.dao.system.RoleRelMenuMapper;
import com.wyj.entity.system.RoleRelMenu;
import com.wyj.service.system.RoleRelMenuService;

/**
 * 角色关联菜单ServiceImpl
 * 
 * 
 * @author：WangYuanJun
 * @date：2017年11月22日 下午8:25:35
 */
@Service(value="RoleRelMenuService")
public class RoleRelMenuServiceImpl extends BaseServiceImpl<RoleRelMenu, Long> implements RoleRelMenuService {

    @Autowired
    private RoleRelMenuMapper roleRelMenuMapper;

    @Override
    public BaseMapper<RoleRelMenu, Long> getMapper() {
        return roleRelMenuMapper;
    }

    @Override
    public int batchRemoveByMenuId(Long[] id) {
        return roleRelMenuMapper.batchRemoveByMenuId(id);
    }

    @Override
    public int batchRemoveByRoleId(Long[] id) {
        return roleRelMenuMapper.batchRemoveByRoleId(id);
    }

    @Override
    public List<Long> listMenuIdByRoleId(Long id) {
        return roleRelMenuMapper.listMenuIdByRoleId(id);
    }

}
