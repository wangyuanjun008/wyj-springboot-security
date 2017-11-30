package com.wyj.dao.system;

import java.util.List;

import com.wyj.common.dao.BaseMapper;
import com.wyj.entity.system.UserRelRole;

/**
 * 用户关联角色Mapper
 * 
 * 
 * @author：WangYuanJun
 * @date：2017年11月22日 下午8:25:35
 */
public interface UserRelRoleMapper extends BaseMapper<UserRelRole, Long> {

    void removeByUserId(Long userId);

    void batchRemoveByUserId(Long[] userIds);

    void batchRemoveByRoleId(Long[] roleIds);

    List<Long> listRoleIdByUserId(Long userId);
}
