package com.wyj.dao.system;

import java.util.HashMap;

import com.wyj.common.dao.BaseMapper;
import com.wyj.entity.system.User;
/**
 * 用户Mapper
 * 
 * 
 * @author：WangYuanJun
 * @date：2017年11月22日 下午8:25:35
 */
public interface UserMapper extends BaseMapper<User, Long> {

    User getUserByUserName(String userName);
    
    int updatePasswordByUser(HashMap<String, Object> query);
}
