package com.wyj.controller.log;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wyj.common.entity.Retval;
import com.wyj.entity.data.SysLogEntity;
import com.wyj.service.data.SysLogService;
/**
 * 日志
 * 
 * 
 * @author：WangYuanJun
 * @date：2017年11月22日 下午8:25:35
 */
@RestController
@RequestMapping(value = "/remote/log")
public class LogController {
    
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private SysLogService sysLogService;
    
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String query(@RequestParam(value = "offset", required = true, defaultValue = "1") Integer page, @RequestParam(value = "limit", required = false, defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<SysLogEntity> sysLogEntities = sysLogService.list();
        PageInfo<SysLogEntity> pageInfo = new PageInfo<SysLogEntity>(sysLogEntities);
        return JSON.toJSONString(pageInfo.getList());
    }
    
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public Retval remove(@RequestParam Long[] ids) {
        Retval retval = Retval.newInstance();
        try {
            sysLogService.batchRemove(ids);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            retval.fail(e.getMessage());
        }
        return retval;
    }
    
}
