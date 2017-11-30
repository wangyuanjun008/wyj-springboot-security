package com.wyj.entity.system;

import com.wyj.common.entity.BaseEntity;

/**
 * 用户关联角色
 * 
 * 
 * @author：WangYuanJun
 * @date：2017年11月22日 下午8:25:35
 */
public class UserRelRole extends BaseEntity {

    private Long userRelRoleId;

    private Long userId;

    private Long roleId;

    public Long getUserRelRoleId() {
        return userRelRoleId;
    }

    public void setUserRelRoleId(Long userRelRoleId) {
        this.userRelRoleId = userRelRoleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

}
