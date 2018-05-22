package com.bxz.mapper;

import com.bxz.entity.Coins;
import com.bxz.mapper.base.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author baixiangzhu
 * @date 2018/5/22
 **/
@Mapper
public interface CoinsMapper extends BaseMapper<Coins> {

    Coins selectByUserId(@Param("userId") Long userId);

}
