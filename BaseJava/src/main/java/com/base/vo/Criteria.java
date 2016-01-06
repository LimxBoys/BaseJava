package com.base.vo;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.base.util.TypeCaseHelper;



/**
 * 公用条件查询类 也可以用于MVC层之间的参数传递
 * 
 * @author limingxing
 * 
 */ 
public class Criteria {

    /**
     * 存放条件查询值
     */
    private Map<String, Object> condition;
    /**
     * 是否相异 为什么是protected类型?
     */
    private boolean distinct;
    /**
     * 排序字段
     */
    private String orderByClause;

    private int start;
    private int limit;

    private Integer oracleStart;
    private Integer oracleEnd;

    public Integer getOracleStart() {

        return oracleStart;
    }

    public void setOracleStart(Integer oracleStart) {
        this.oracleStart = oracleStart;
    }

    public Integer getOracleEnd() {
        return oracleEnd;
    }

    public void setOracleEnd(Integer oracleEnd) {
        this.oracleEnd = oracleEnd;
    }

    public Map<String, Object> getCondition() {
        return condition;
    }

    public void setCondition(Map<String, Object> condition) {
        this.condition = condition;
    }

    public Criteria() {
        condition = new HashMap<String, Object>();
    }

    public void clear() {
        this.condition.clear();
        this.orderByClause = null;
        this.distinct = false;
        this.start = 0;
        this.limit = 0;
    }

    /**
     * 添加查询条件
     * 
     * @param conditionKey
     * 查询条件的名称
     * @param contidionValue
     * 查询条件的值
     * @return
     */
    public Criteria put(String key, Object value) {
        this.condition.put(key, value);
        return (Criteria) this;
    }

    /**
     * 得到键值，C层和S层的参数传递时取值所用 自行转换对象
     * 
     * @param contionKey
     * 条件键值
     * @return 返回指定键所映射的值
     */
    public Object get(String contionKey) {
        return this.condition.get(contionKey);
    }

    public Map<String, Object> getConditionMap() {
        return condition;
    }

    public void setConditionMap(Map<String, Object> conditionMap) {
        this.condition = conditionMap;
    }

    /**
     * 是否相异
     * 
     * @return
     */
    public boolean isDistinct() {
        return distinct;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * 获取排序字段
     * 
     * @return
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * 设置排序字段
     * 
     * @param orderByClause
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * 以BigDecimal返回键值
     * 
     * @param key
     * 键名
     * @return Date 键值
     */
    public BigDecimal getAsBigDecimal(String key) {
        Object obj = TypeCaseHelper.convert(get(key), "BigDecimal", null);
        if (obj != null) {
            return (BigDecimal) obj;
        } else {
            return null;
        }
    }

    /**
     * 以Date类型返回键值
     * 
     * @param key
     * 键名
     * @return Date 键值
     */
    public Date getAsDate(String key) {
        Object obj = TypeCaseHelper.convert(get(key), "Date", "yyyy-MM-dd");
        if (obj == null) {
            return (Date) obj;
        } else {
            return null;
        }
    }

    /**
     * 以Integer类型返回键值
     * 
     * @param key
     * 键名
     * @return Integer 键值
     */
    public Integer getAsInteger(String key) {
        Object obj = TypeCaseHelper.convert(get(key), "Integer", null);
        if (obj != null) {
            return (Integer) obj;
        } else {
            return null;
        }
    }

    /**
     * 以Long类型返回键值
     * 
     * @param key
     * 键名
     * @return Long 键值
     */
    public Long getAsLong(String key) {
        Object obj = TypeCaseHelper.convert(get(key), "Long", null);
        if (obj != null) {
            return (Long) obj;
        } else {
            return null;
        }
    }

    /**
     * 以字符串类型返回键值
     * 
     * @param key
     * 键名
     * @return 键值
     */
    public String getAsString(String key) {
        Object obj = TypeCaseHelper.convert(get(key), "String", null);
        if (obj != null) {
            return (String) obj;
        } else {
            return null;
        }
    }

    /**
     * 以Timestamp类型返回键值
     * 
     * @param key
     * 键名
     * @return Timestamp键值
     */
    public Timestamp getAsTimestamp(String key) {
        Object obj = TypeCaseHelper.convert(get(key), "Timestamp", null);
        if (obj != null) {
            return (Timestamp) obj;
        } else {
            return null;
        }
    }

    /**
     * 以Boolean类型返回键值
     * 
     * @param key
     * 键名
     * @return Boolean 键值
     */
    public Boolean getAsBoolean(String key) {
        Object obj = TypeCaseHelper.convert(get(key), "Boolean", null);
        if (obj != null) {
            return (Boolean) obj;
        } else {
            return null;
        }
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
