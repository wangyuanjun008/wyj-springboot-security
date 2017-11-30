package com.wyj.service.data.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wyj.common.dao.BaseMapper;
import com.wyj.common.service.impl.BaseServiceImpl;
import com.wyj.dao.data.DataGroupMapper;
import com.wyj.entity.data.DataGroup;
import com.wyj.service.data.DataDictService;
import com.wyj.service.data.DataGroupService;

/**
 * 数据分组ServiceImpl
 * 
 * 
 * @author：WangYuanJun
 * @date：2017年11月22日 下午8:25:35
 */
@Service(value = "DataGroupService")
public class DataGroupServiceImpl extends BaseServiceImpl<DataGroup, Long> implements DataGroupService {

    @Autowired
    private DataGroupMapper dataGroupMapper;
    
    @Autowired
    private DataDictService dataDictService;

    @Override
    public BaseMapper<DataGroup, Long> getMapper() {
        return dataGroupMapper;
    }

    @Override
    public int batchRemoveDataGroup(Long[] ids) {
        int count = dataGroupMapper.batchRemove(ids);
        dataDictService.batchRemoveByDataGroupId(ids);
        return count;
    }

}
