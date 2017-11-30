package com.wyj.service.data.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wyj.common.dao.BaseMapper;
import com.wyj.common.service.impl.BaseServiceImpl;
import com.wyj.dao.data.SysLogMapper;
import com.wyj.entity.data.SysLogEntity;
import com.wyj.service.data.SysLogService;


/**
 * 日志ServiceImpl
 * 
 * 
 * @author：WangYuanJun
 * @date：2017年9月26日 下午9:46:10
 */
@Service("sysLogService")
public class SysLogServiceImpl extends BaseServiceImpl<SysLogEntity, Long> implements SysLogService {

	@Autowired
	private SysLogMapper sysLogMapper;

	@Override
	public BaseMapper<SysLogEntity, Long> getMapper() {
	    return sysLogMapper;
	}
	
    @Override
    public int batchRemoveAll() {
        int count = sysLogMapper.batchRemoveAll();
        return count;
    }
}
