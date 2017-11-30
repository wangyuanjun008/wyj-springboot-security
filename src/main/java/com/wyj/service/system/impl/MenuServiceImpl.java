package com.wyj.service.system.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.wyj.common.constant.MsgConstant;
import com.wyj.common.dao.BaseMapper;
import com.wyj.common.entity.Retval;
import com.wyj.common.enums.MenuTypeEnum;
import com.wyj.common.service.impl.BaseServiceImpl;
import com.wyj.dao.system.MenuMapper;
import com.wyj.entity.system.Menu;
import com.wyj.service.system.MenuService;
import com.wyj.service.system.RoleRelMenuService;
/**
 * 菜单ServiceImpl
 * 
 * 
 * @author：WangYuanJun
 * @date：2017年11月22日 下午8:25:35
 */
@Service(value = "MenuService")
public class MenuServiceImpl extends BaseServiceImpl<Menu, Long> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RoleRelMenuService roleRelMenuService;

    @Override
    public BaseMapper<Menu, Long> getMapper() {
        return menuMapper;
    }

    @Override
    public Retval batchRemoveMenu(Long[] ids) {
        Retval retval = Retval.newInstance();
        Boolean boolean1 = this.isSubNodeByParentId(ids);
        if (boolean1) {
            retval.fail(MsgConstant.MSG_HAS_CHILD);
            return retval;
        }
        int count = menuMapper.batchRemove(ids);
        roleRelMenuService.batchRemoveByMenuId(ids);
        return retval;
    }

    @Override
    public Boolean isSubNodeByParentId(Long[] parentIds) {
        for (Long parentId : parentIds) {
            int count = menuMapper.countMenuChildren(parentId);
            if (count > 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Set<String> listMenuPermsByUserId(Long userId) {
        List<String> perms = menuMapper.listMenuPermsByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms) {
            if (StringUtils.isNotBlank(perm)) {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }

    @Override
    public List<Menu> listAllMenuIdByUserId(Long userId) {
        List<Long> menuIds = menuMapper.listAllMenuIdByUserId(userId);

        // 查询根菜单列表(该用户的一级菜单 )
        List<Menu> menuList = listMenuByParentId(0L, menuIds);
        // 递归获取子菜单
        getMenuTreeList(menuList, menuIds);
        return menuList;
    }

    /**
     * 递归
     */
    private List<Menu> getMenuTreeList(List<Menu> menuList, List<Long> menuIdList) {
        List<Menu> subMenuList = new ArrayList<Menu>();

        for (Menu entity : menuList) {
            if (entity.getType() == MenuTypeEnum.CATALOG.ordinal()) {// 目录
                entity.setList(getMenuTreeList(listMenuByParentId(entity.getMenuId(), menuIdList), menuIdList));
            }
            subMenuList.add(entity);
        }
        return subMenuList;
    }

    /**
     * 
     * @param parentId
     * @param menuIds
     * @return
     */
    private List<Menu> listMenuByParentId(Long parentId, List<Long> menuIds) {
        Menu menu = new Menu();
        menu.setParentId(parentId);
        List<Menu> rootMenus = menuMapper.list(menu);
        if (CollectionUtils.isEmpty(menuIds)) {
            return rootMenus;
        }

        List<Menu> userMenuList = new ArrayList<>();
        for (Menu menu1 : rootMenus) {
            if (menuIds.contains(menu1.getMenuId())) {
                userMenuList.add(menu1);
            }
        }
        return userMenuList;

    }

    @Override
    public List<Menu> listNotButton(Menu menu) {
        return menuMapper.listNotButton(menu);
    }
}
