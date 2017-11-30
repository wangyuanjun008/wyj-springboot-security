package com.wyj.dao.system;

import java.util.List;

import com.wyj.common.dao.BaseMapper;
import com.wyj.entity.system.Menu;

/**
 * 菜单Mapper
 * 
 * 
 * @author：WangYuanJun
 * @date：2017年11月22日 下午8:25:35
 */
public interface MenuMapper extends BaseMapper<Menu, Long> {

    int countMenuChildren(Long parentId);
    
    List<String> listMenuPermsByUserId(Long userId);

    List<Long> listAllMenuIdByUserId(Long userId);
    
    List<Menu> listNotButton(Menu menu);
}
