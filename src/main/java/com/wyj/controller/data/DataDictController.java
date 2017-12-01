package com.wyj.controller.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.wyj.entity.data.DataDict;
import com.wyj.entity.data.DataGroup;
import com.wyj.service.data.DataDictService;
import com.wyj.service.data.DataGroupService;

/**
 * 字典表Controller
 * 
 * 
 * @author：WangYuanJun
 * @date：2017年11月22日 下午8:25:35
 */
@Controller
@RequestMapping(value = "/dataDict")
public class DataDictController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DataDictService dataDictService;

    @Autowired
    private DataGroupService dataGroupService;

    @Resource
    private RedisTemplate<String, DataDict> redisTemplate;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    private String index() {
        return "/data/dataDict";
    }

    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String query(@RequestParam(value = "offset", required = true, defaultValue = "1") Integer page, @RequestParam(value = "limit", required = false, defaultValue = "10") Integer pageSize, Long dataGroupId) {
        if (dataGroupId == null) {
            return null;
        }
        PageHelper.startPage(page, pageSize);
        DataDict dataDict = new DataDict();
        DataGroup dataGroup = new DataGroup();
        dataGroup.setGroupId(dataGroupId);
        dataDict.setDataGroup(dataGroup);
        List<DataDict> dataGroups = dataDictService.list(dataDict);
        PageInfo<DataDict> pageInfo = new PageInfo<DataDict>(dataGroups);
        return JSON.toJSONString(pageInfo.getList());
    }

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Retval save(DataDict dataDict) {
        Retval retval = Retval.newInstance();
        try {
            if (dataDict.getDictId() == null) {
                dataDictService.save(dataDict);
            } else {
                dataDictService.update(dataDict);
            }
            ValueOperations<String, DataDict> valueOperations = redisTemplate.opsForValue();
            valueOperations.set("dataDict" + dataDict.getDictId(), dataDict);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return retval;
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Retval edit(@PathVariable String id) {
        Retval retval = Retval.newInstance();
        if (redisTemplate.hasKey("dataDict" + id)) {
            DataDict dataDict = redisTemplate.opsForValue().get("dataGroup" + id);
            retval.put("obj", dataDict);
        } else {
            DataDict dataDict = dataDictService.getObjectById(Long.valueOf(id));
            retval.put("obj", dataDict);
        }
        return retval;
    }

    @ResponseBody
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public Retval remove(@RequestParam Long[] ids) {
        Retval retval = Retval.newInstance();
        try {
            dataDictService.batchRemove(ids);
            dataGroupService.batchRemoveDataGroup(ids);
            for (Long long1 : ids) {
                redisTemplate.delete("dataDict" + long1.toString());
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            retval.fail(e.getMessage());
        }
        return retval;
    }

    /**
     * ztree树
     * 
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/renderTree", method = RequestMethod.GET)
    public List<Map<String, Object>> renderTree() {
        List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
        List<DataGroup> dataGroups = dataGroupService.list();
        for (DataGroup dataGroup : dataGroups) {
            Map<String, Object> node = new HashMap<String, Object>();
            node.put("id", dataGroup.getGroupId());
            node.put("name", dataGroup.getGroupName());
            node.put("parentId", dataGroup.getParentId());
            returnList.add(node);
        }
        return returnList;
    }

    /**
     * 数据字典数据源
     * 
     * @param groupCode
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getData", method = RequestMethod.GET)
    public String getDictCodeNameByGroupCode(@RequestParam String groupCode) {

        List<Map<Long, String>> map = dataDictService.getDictCodeNameByGroupCode(groupCode);
        return JSON.toJSONString(map);
    }
}
