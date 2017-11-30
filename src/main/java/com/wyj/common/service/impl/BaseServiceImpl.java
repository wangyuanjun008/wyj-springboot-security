package com.wyj.common.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import com.wyj.common.dao.BaseMapper;
import com.wyj.common.service.BaseService;
/**
 * 公共serviceimpl
 * 
 * 
 * @author：WangYuanJun
 * @date：2017年11月22日 下午8:25:35
 */
public abstract class BaseServiceImpl<T, ID extends Serializable> implements BaseService<T, ID> {

    public abstract BaseMapper<T, ID> getMapper();

    @Override
    public int save(T entity) {
        return getMapper().save(entity);
    }

    @Override
    public int save(HashMap<String, Object> query) {
        return getMapper().save(query);
    }

    @Override
    public int batchSave(List<T> items) {
        return getMapper().batchSave(items);
    }

    @Override
    public T getObject(T entity) {
        return getMapper().getObject(entity);
    }

    @Override
    public T getObjectById(Object id) {
        return getMapper().getObjectById(id);
    }

    @Override
    public int update(T entity) {
        return getMapper().update(entity);
    }

    @Override
    public int update(HashMap<String, Object> query) {
        return getMapper().update(query);
    }

    @Override
    public int batchUpdate(HashMap<String, Object> query) {
        return getMapper().batchUpdate(query);
    }

    @Override
    public int remove(Object id) {
        return getMapper().remove(id);
    }

    @Override
    public int removeLogic(Object id) {
        return getMapper().removeLogic(id);
    }

    @Override
    public int batchRemove(Object[] id) {
        return getMapper().batchRemove(id);
    }

    @Override
    public int batchRemoveLogic(Object[] id) {
        return getMapper().batchRemoveLogic(id);
    }

    @Override
    public List<T> list(HashMap<String, Object> query) {
        return getMapper().list(query);
    }

    @Override
    public List<T> list() {
        return getMapper().list();
    }

    @Override
    public List<T> list(T entity) {
        return getMapper().list(entity);
    }
    
    @Override
    public int countTotal() {
        return getMapper().countTotal();
    }

    @Override
    public int countTotal(HashMap<String, Object> query) {
        return getMapper().countTotal(query);
    }

    @Override
    public List<T> listOneNodes() {
        return getMapper().listOneNodes();
    }
}
