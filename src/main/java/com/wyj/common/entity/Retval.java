package com.wyj.common.entity;

import java.util.HashMap;
import java.util.Map;
/**
 * 返回对象
 * 
 * 
 * @author：WangYuanJun
 * @date：2017年11月22日 下午8:25:35
 */
public class Retval {
    private Boolean success = Boolean.TRUE;
    private String errorMsg;
    private String promptMsg;
    private Exception ex;
    private Map<String, Object> data = new HashMap<String, Object>();

    public static Retval newInstance() {
        return new Retval();
    }

    public Boolean getSuccess() {
        return success;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public Exception getEx() {
        return ex;
    }

    public void put(String key, Object value) {
        this.data.put(key, value);
    }

    public void putAll(Map<String, Object> map) {
        this.data.putAll(map);
    }

    public void fail() {
        this.success = Boolean.FALSE;
    }

    public void fail(String errorMsg) {
        this.success = Boolean.FALSE;
        this.errorMsg = errorMsg;
    }

    public void fail(String errorMsg, Exception ex) {
        this.success = Boolean.FALSE;
        this.errorMsg = errorMsg;
        this.ex = ex;
    }

    public void fail(String errorMsg, Exception ex, String promptMsg) {
        this.success = Boolean.FALSE;
        this.errorMsg = errorMsg;
        this.ex = ex;
        this.promptMsg = promptMsg;
    }

    public void fail(String errorMsg , String promptMsg) {
        this.success = Boolean.FALSE;
        this.errorMsg = errorMsg;
        this.promptMsg = promptMsg;
    }
    
    public String getPromptMsg() {
        return promptMsg;
    }

    public void setPromptMsg(String promptMsg) {
        this.promptMsg = promptMsg;
    }
}
