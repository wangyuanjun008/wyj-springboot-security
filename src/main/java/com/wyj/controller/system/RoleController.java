package com.wyj.controller.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wyj.common.entity.Retval;
import com.wyj.entity.system.Role;
import com.wyj.service.system.RoleService;

/**
 * 角色Controller
 * 
 * 
 * @author：WangYuanJun
 * @date：2017年11月22日 下午8:25:35
 */
@RestController
@RequestMapping(value = "/remote/role")
public class RoleController {
    
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String query(@RequestParam(value = "offset", required = true, defaultValue = "1") Integer page, @RequestParam(value = "limit", required = false, defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Role> Roles = roleService.list(new Role());
        PageInfo<Role> pageInfo = new PageInfo<Role>(Roles);
        return JSON.toJSONString(pageInfo.getList());
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Retval save(Role Role) {
        Retval retval = Retval.newInstance();
        try {
            if (Role.getRoleId() == null) {
                Role.setCreateTime(new Date());
                roleService.save(Role);
            } else {
                roleService.update(Role);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return retval;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Retval edit(@PathVariable String id) {
        Retval retval = Retval.newInstance();
        Role Role = roleService.getRoleById(Long.valueOf(id));
        retval.put("obj", Role);
        return retval;
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public Retval remove(@RequestParam Long[] ids) {
        Retval retval = Retval.newInstance();
        try {
            roleService.batchRemoveRole(ids);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            retval.fail(e.getMessage());
        }
        return retval;
    }

    /**
     * 展示权限树
     * 
     * @return
     */
    @RequestMapping(value = "/getAllRoles", method = RequestMethod.GET)
    public String list() {
        List<Role> Roles = roleService.list();
        List<Map<String, Object>> map1 = new ArrayList<Map<String, Object>>();
        for (Role role : Roles) {
            String id = role.getRoleId().toString();
            String text = role.getRoleName();
            Map<String, Object> map3 = new HashMap<String, Object>();
            map3.put("id", id);
            map3.put("text", text);
            map1.add(map3);
        }
        return JSON.toJSONString(map1);
    }

    /**
     * 保存操作权限
     * 
     * @param menus
     * @param roleId
     * @return
     */
    @RequestMapping("/authorize")
    public int updateRoleAuthorization(@RequestParam Long[] menus, @RequestParam Long roleId) {
        List<Long> menuIds = new ArrayList<>();
        for (int i = 0; i < menus.length; i++) {
            menuIds.add(menus[i]);
        }
        Role role = new Role();
        role.setMenus(menuIds);
        role.setRoleId(roleId);
        return roleService.updateRoleAuthorization(role);
    }
}
