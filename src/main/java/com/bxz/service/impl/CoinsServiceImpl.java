package com.bxz.service.impl;

import com.bxz.emuns.ErrorMessageEnum;
import com.bxz.entity.Coins;
import com.bxz.entity.CoinsDetails;
import com.bxz.exception.C400Exception;
import com.bxz.exception.C404Exception;
import com.bxz.mapper.CoinsDetailsMapper;
import com.bxz.mapper.CoinsMapper;
import com.bxz.service.ICoinsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author baixiangzhu
 * @date 2018/5/23
 **/
@Slf4j
@Service
public class CoinsServiceImpl implements ICoinsService{

    @Autowired
    private CoinsMapper coinsMapper;

    @Autowired
    private CoinsDetailsMapper coinsDetailsMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addOrUpdateUserCoins(Long userId, Integer coins) {

        log.info("addOrUpdateUserCoins userId=[{}],coins=[{}]",userId,coins);

        Coins c = coinsMapper.selectByUserId(userId);
        Coins newC = new Coins();
        newC.setCoins(coins);
        if(null == c){
            newC.setUserId(userId);
            coinsMapper.insertSelective(newC);
            log.info("add user coins . userId=[{}]",userId);
        }else{
            newC.setId(c.getId());
            newC.setCoins(coins);
            coinsMapper.updateSelectiveByPrimaryKey(newC);
            log.info("update user coins,userId=[{}]",userId);
        }

    }

    @Override
    public Integer getCoinsByUserId(Long userId) {
        log.info("getCoinsByUserId userId=[{}]",userId);
        Coins coins = coinsMapper.selectByUserId(userId);
        return null == coins ? null : coins.getCoins();
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void transferCoins(Long fromUserId, Long toUserId, Integer coins) {

        log.info("transferCoins formUserId=[{}],toUserId=[{}],coins=[{}]",fromUserId,toUserId,coins);

        //检查用户和金额
        Coins fromC = coinsMapper.selectByUserId(fromUserId);
        if (null == fromC){
            throw new C404Exception(ErrorMessageEnum.USER_NOT_EXIST.getMessage());
        }
        if (fromC.getCoins() < coins){
            log.error("user coins not enough.fromUserCoins = [{}],coins=[{}]",fromC,coins);
            throw new C400Exception(ErrorMessageEnum.COINS_NOT_ENOUGH.getMessage());
        }
        Coins toC = coinsMapper.selectByUserId(toUserId);
        if (null == toC){
            throw new C404Exception(ErrorMessageEnum.USER_NOT_EXIST.getMessage());
        }

        //转账
        //from 减少金币
        coinsMapper.updateCoins(fromC.getUserId(), - coins);
        //to 增加金币
        coinsMapper.updateCoins(toC.getUserId(),coins);

        //记录转账履历
        CoinsDetails detail = new CoinsDetails();
        detail.setFromUserId(fromC.getUserId());
        detail.setToUserId(toC.getUserId());
        detail.setChangeCoins(coins);
        coinsDetailsMapper.insertSelective(detail);

    }
}
