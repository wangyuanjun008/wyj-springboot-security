package com.wyj.service.system;

import java.util.List;
import java.util.Set;

import com.wyj.common.entity.Retval;
import com.wyj.common.service.BaseService;
import com.wyj.entity.system.Menu;
/**
 * 菜单Service
 * 
 * 
 * @author：WangYuanJun
 * @date：2017年11月22日 下午8:25:35
 */
public interface MenuService extends BaseService<Menu, Long> {

    Retval batchRemoveMenu(Long[] ids);
    
    /**
     * 是否有子节点
     * @param parentIds
     * @return
     */
    Boolean isSubNodeByParentId(Long[] parentIds);
    
    
    Set<String> listMenuPermsByUserId(Long userId);
    
    List<Menu> listAllMenuIdByUserId(Long userId);
    
    List<Menu> listNotButton(Menu menu);
}
