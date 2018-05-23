package com.bxz.service;

/**
 * @author baixiangzhu
 * @date 2018/5/22
 **/
public interface ICoinsService {

    /**
     * 新建or更新用户金币
     * @param userId
     * @param coins
     * @return
     */
    void addOrUpdateUserCoins(Long userId,Integer coins);

    /**
     * 根据用户ID获取用户金币数
     * @param userId
     * @return
     */
    Integer getCoinsByUserId(Long userId);

    /**
     * 转账金额
     * @param fromUserId
     * @param toUserId
     * @param coins
     * @return
     */
    void transferCoins(Long fromUserId,Long toUserId,Integer coins);
}
