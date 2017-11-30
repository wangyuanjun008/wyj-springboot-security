package com.wyj.common.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * 公共mapper
 * 
 * 
 * @author：WangYuanJun
 * @date：2017年11月22日 下午8:25:35
 */
public interface BaseMapper<T,ID extends Serializable> {
    /**
     * 新增
     * @param entity
     * @return
     */
    int save(T entity);
    
    /**
     * 新增
     * @param query
     * @return
     */
    int save(HashMap<String, Object> query);
    
    /**
     * 批量新增
     * @param items
     * @return
     */
    int batchSave(List<T> items);
    
    /**
     * 查询详情
     * @param t
     * @return
     */
    T getObject(T entity);
    
    /**
     * 根据id查询详情
     * @param id
     * @return
     */
    T getObjectById(Object id);
    
    /**
     * 更新
     * @param t
     * @return
     */
    int update(T entity);
    
    /**
     * 更新
     * @param query
     * @return
     */
    int update(HashMap<String, Object> query);
    
    /**
     * 批量更新
     * @param query
     * @return
     */
    int batchUpdate(HashMap<String, Object> query);
    
    /**
     * 删除
     * @param id
     * @return
     */
    int remove(Object id);
    
    /**
     * 逻辑删除
     * @param id
     * @return
     */
    int removeLogic(Object id);
    
    /**
     * 批量删除
     * @param id
     * @return
     */
    int batchRemove(Object[] id);
    
    /**
     * 批量逻辑删除
     * @param id
     * @return
     */
    int batchRemoveLogic(Object[] id);
    
    
    /**
     * 查询列表
     * @param query
     * @return
     */
    List<T> list(HashMap<String, Object> query);
    
    /**
     * 查询列表
     * @return
     */
    List<T> list();
    
    /**
     * 查询列表
     * @return
     */
    List<T> list(T entity);
    
    /**
     * 统计
     * @return
     */
    int countTotal();
    
    /**
     * 统计
     * @param query
     * @return
     */
    int countTotal(HashMap<String, Object> query);
    
    /**
     * 查询一级节点
     * @return
     */
    List<T> listOneNodes();
}
