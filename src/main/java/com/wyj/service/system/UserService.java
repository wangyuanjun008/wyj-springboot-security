package com.wyj.service.system;

import java.util.HashMap;

import com.wyj.common.entity.Retval;
import com.wyj.common.service.BaseService;
import com.wyj.entity.system.User;
/**
 * 用户Service
 * 
 * 
 * @author：WangYuanJun
 * @date：2017年11月22日 下午8:25:35
 */
public interface UserService extends BaseService<User, Long> {

    int saveUser(User user);

    int updateUser(User user);

    int batchRemoveUser(Long[] userIds);

    User getUserById(Long userId);
    
    User getUserByUserName(String userName);
    
    Retval updatePasswordByUser(HashMap<String, Object> query);
}
