package com.wyj.dao.data;

import org.mybatis.spring.annotation.MapperScan;

import com.wyj.common.dao.BaseMapper;
import com.wyj.entity.data.SysLogEntity;


/**
 * 日志Mapper
 * 
 * 
 * @author：WangYuanJun
 * @date：2017年11月22日 下午8:25:35
 */
@MapperScan
public interface SysLogMapper extends BaseMapper<SysLogEntity,Long> {

	int batchRemoveAll();
	
}
