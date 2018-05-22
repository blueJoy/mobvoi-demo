package com.bxz.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户金币信息实体
 *
 * @author baixiangzhu
 * @date 2018/5/22
 **/
@Data
public class Coins implements Serializable {
    private static final long serialVersionUID = -3093825524878402789L;

    /**
     * 主键
     */
    private Integer id;
    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 金币数
     */
    private Integer coins;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
}
