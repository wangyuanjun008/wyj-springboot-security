package com.wyj.dao.data;

import java.util.List;
import java.util.Map;

import com.wyj.common.dao.BaseMapper;
import com.wyj.entity.data.DataDict;

/**
 * 字典表Mapper
 * 
 * 
 * @author：WangYuanJun
 * @date：2017年11月22日 下午8:25:35
 */
public interface DataDictMapper extends BaseMapper<DataDict, Long> {

    /**
     * key=dictId value=dictName
     * @param groupCode
     * @return
     */
    List<Map<Long, String>> getDataDictByGroupCode(String groupCode);
    
    /**
     * key=dictCode value=dictName
     * @param groupCode
     * @return
     */
    List<Map<Long, String>> getDictCodeNameByGroupCode(String groupCode);
    
    int batchRemoveByDataGroupId(Long[] ids);
}
