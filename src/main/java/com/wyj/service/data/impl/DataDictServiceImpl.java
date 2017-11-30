package com.wyj.service.data.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wyj.common.dao.BaseMapper;
import com.wyj.common.service.impl.BaseServiceImpl;
import com.wyj.dao.data.DataDictMapper;
import com.wyj.entity.data.DataDict;
import com.wyj.service.data.DataDictService;

/**
 * 数据字典ServiceImpl
 * 
 * 
 * @author：WangYuanJun
 * @date：2017年11月22日 下午8:25:35
 */
@Service(value = "DataDictServic")
public class DataDictServiceImpl extends BaseServiceImpl<DataDict, Long> implements DataDictService {

    @Autowired
    private DataDictMapper dataDictMapper;

    @Override
    public BaseMapper<DataDict, Long> getMapper() {
        return dataDictMapper;
    }

    @Override
    public List<Map<Long, String>> getDataDictByGroupCode(String groupCode) {
        return dataDictMapper.getDataDictByGroupCode(groupCode);
    }

    @Override
    public List<Map<Long, String>> getDictCodeNameByGroupCode(String groupCode) {
        return dataDictMapper.getDictCodeNameByGroupCode(groupCode);
    }

    @Override
    public int batchRemoveByDataGroupId(Long[] ids) {
        return dataDictMapper.batchRemoveByDataGroupId(ids);
    }

}
