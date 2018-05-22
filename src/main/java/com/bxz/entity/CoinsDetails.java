package com.bxz.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户金币变动履历表
 *
 * @author baixiangzhu
 * @date 2018/5/22
 **/
@Data
public class CoinsDetails implements Serializable {
    private static final long serialVersionUID = -6346469804862949869L;

    private Integer id;

    /**
     * 源用户
     */
    private Long fromUserId;

    /**
     * 目标用户
     */
    private Long toUserId;

    /**
     * 变动金额
     */
    private Integer changeCoins;

    /**
     * 创建时间
     */
    private Date createTime;

}
