package com.wyj.service.data;

import com.wyj.common.service.BaseService;
import com.wyj.entity.data.DataGroup;
/**
 * 数据分组Service
 * 
 * 
 * @author：WangYuanJun
 * @date：2017年11月22日 下午8:25:35
 */
public interface DataGroupService extends BaseService<DataGroup, Long>{

    int batchRemoveDataGroup(Long[] ids);
}
