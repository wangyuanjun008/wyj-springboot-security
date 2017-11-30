package com.wyj.service.system.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wyj.common.dao.BaseMapper;
import com.wyj.common.entity.Retval;
import com.wyj.common.service.impl.BaseServiceImpl;
import com.wyj.dao.system.UserMapper;
import com.wyj.entity.system.User;
import com.wyj.service.system.UserRelRoleService;
import com.wyj.service.system.UserService;
/**
 * 用户ServiceImpl
 * 
 * 
 * @author：WangYuanJun
 * @date：2017年11月22日 下午8:25:35
 */
@Service(value = "UserService")
@Transactional
public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public BaseMapper<User, Long> getMapper() {
        return userMapper;
    }

    @Autowired
    private UserRelRoleService userRelRoleService;

    @Override
    public int saveUser(User user) {
        int count = userMapper.save(user);
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("userId", user.getUserId());
        hashMap.put("roles", user.getRoles());
        userRelRoleService.save(hashMap);
        return count;
    }

    @Override
    public int updateUser(User user) {
        int count = userMapper.update(user);
        Long userId = user.getUserId();
        userRelRoleService.removeByUserId(userId);
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("userId", userId);
        hashMap.put("roles", user.getRoles());
        userRelRoleService.save(hashMap);
        return count;
    }

    @Override
    public int batchRemoveUser(Long[] userIds) {
        int count = userMapper.batchRemove(userIds);
        userRelRoleService.batchRemoveByUserId(userIds);
        return count;
    }

    @Override
    public User getUserById(Long userId) {
        User user = userMapper.getObjectById(userId);
        user.setRoles(userRelRoleService.listRoleIdByUserId(userId));
        return user;
    }

    @Override
    public User getUserByUserName(String userName) {
        return userMapper.getUserByUserName(userName);
    }

    @Override
    public Retval updatePasswordByUser(HashMap<String, Object> query) {
        Retval retval = Retval.newInstance();
        int count = userMapper.updatePasswordByUser(query);
        if (count <= 0) {
            retval.fail("原密码输入不正确,请重新输入!");
        }
        return retval;
    }

}
