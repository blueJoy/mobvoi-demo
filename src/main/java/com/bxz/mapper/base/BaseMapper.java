package com.bxz.mapper.base;

/**
 * Created by baixiangzhu on 2018/5/22.
 */
public interface BaseMapper<T> {

    int insertSelective(T model);

    T selectByPrimaryKey(Object id);

    int updateSelectiveByPrimaryKey(T model);
}
