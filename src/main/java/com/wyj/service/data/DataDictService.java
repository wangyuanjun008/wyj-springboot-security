package com.wyj.service.data;

import java.util.List;
import java.util.Map;

import com.wyj.common.service.BaseService;
import com.wyj.entity.data.DataDict;
/**
 * 数据字典Service
 * 
 * 
 * @author：WangYuanJun
 * @date：2017年11月22日 下午8:25:35
 */
public interface DataDictService extends BaseService<DataDict, Long>{

    /**
     * 数据字典下拉
     * key=dictId value=dictName
     * @param groupCode
     * @return
     */
    List<Map<Long, String>> getDataDictByGroupCode(String groupCode);
    
    /**
     * 数据字典下拉
     * key=dictCode value=dictName
     * @param groupCode
     * @return
     */
    List<Map<Long, String>> getDictCodeNameByGroupCode(String groupCode);
    
    int batchRemoveByDataGroupId(Long[] ids);
}
