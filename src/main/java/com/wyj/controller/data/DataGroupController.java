package com.wyj.controller.data;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wyj.common.entity.Retval;
import com.wyj.entity.data.DataGroup;
import com.wyj.service.data.DataGroupService;

/**
 * 字典表分组Controller
 * 
 * 
 * @author：WangYuanJun
 * @date：2017年11月22日 下午8:25:35
 */
@Controller
@RequestMapping(value = "/dataGroup")
public class DataGroupController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DataGroupService dataGroupService;

    @Resource
    private RedisTemplate<String, DataGroup> redisTemplate;
    
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    private String index() {
        return "/data/dataGroup";
    }

    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String query(@RequestParam(value = "offset", required = true, defaultValue = "1") Integer page, @RequestParam(value = "limit", required = false, defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<DataGroup> dataGroups = dataGroupService.list();
        PageInfo<DataGroup> pageInfo = new PageInfo<DataGroup>(dataGroups);
        return JSON.toJSONString(pageInfo.getList());
    }

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Retval save(DataGroup dataGroup) {
        Retval retval = Retval.newInstance();
        try {
            if(dataGroup.getGroupId() == null){
                dataGroup.setParentId(0L);
                dataGroupService.save(dataGroup);
            }else{
                dataGroupService.update(dataGroup);
            }
            ValueOperations<String, DataGroup> valueOperations = redisTemplate.opsForValue();
            valueOperations.set("dataGroup"+dataGroup.getGroupId(), dataGroup);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return retval;
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Retval edit(@PathVariable String id) {
        Retval retval = Retval.newInstance();
        if(redisTemplate.hasKey("dataGroup"+id)){
            DataGroup dataGroup = redisTemplate.opsForValue().get("dataGroup"+id);
            retval.put("obj", dataGroup);
        }else{
            DataGroup dataGroup = dataGroupService.getObjectById(Long.valueOf(id));
            retval.put("obj", dataGroup);
        }
        return retval;
    }

    @ResponseBody
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public Retval remove(@RequestParam Long[] ids) {
        Retval retval = Retval.newInstance();
        try {
            dataGroupService.batchRemoveDataGroup(ids);
            for (Long long1 : ids) {
                redisTemplate.delete("dataGroup"+long1.toString());
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            retval.fail(e.getMessage());
        }
        return retval;
    }
}
