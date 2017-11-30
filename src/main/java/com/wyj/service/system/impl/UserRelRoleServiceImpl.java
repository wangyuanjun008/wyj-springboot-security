package com.wyj.service.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wyj.common.dao.BaseMapper;
import com.wyj.common.service.impl.BaseServiceImpl;
import com.wyj.dao.system.UserRelRoleMapper;
import com.wyj.entity.system.UserRelRole;
import com.wyj.service.system.UserRelRoleService;
/**
 * 用户关联角色ServiceImpl
 * 
 * 
 * @author：WangYuanJun
 * @date：2017年11月22日 下午8:25:35
 */
@Service(value = "UserRelRoleService")
public class UserRelRoleServiceImpl extends BaseServiceImpl<UserRelRole, Long> implements UserRelRoleService {

    @Autowired
    private UserRelRoleMapper userRelRoleMapper;

    @Override
    public BaseMapper<UserRelRole, Long> getMapper() {
        return userRelRoleMapper;
    }

    @Override
    public void removeByUserId(Long userId) {
        userRelRoleMapper.removeByUserId(userId);
    }

    @Override
    public void batchRemoveByUserId(Long[] userIds) {
        userRelRoleMapper.batchRemoveByUserId(userIds);
    }

    @Override
    public void batchRemoveByRoleId(Long[] roleIds) {
        userRelRoleMapper.batchRemoveByRoleId(roleIds);
    }

    @Override
    public List<Long> listRoleIdByUserId(Long userId) {
        return userRelRoleMapper.listRoleIdByUserId(userId);
    }

    @Override
    public int save(UserRelRole entity) {
        return userRelRoleMapper.save(entity);
    }

}
