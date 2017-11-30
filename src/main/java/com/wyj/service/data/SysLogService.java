package com.wyj.service.data;

import com.wyj.common.service.BaseService;
import com.wyj.entity.data.SysLogEntity;

/**
 * 日志Service
 * 
 * 
 * @author：WangYuanJun
 * @date：2017年11月22日 下午8:25:35
 */
public interface SysLogService extends BaseService<SysLogEntity, Long>{

	int batchRemoveAll();
}
