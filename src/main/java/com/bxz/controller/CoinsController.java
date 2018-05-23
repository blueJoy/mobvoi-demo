package com.bxz.controller;

import com.bxz.emuns.ErrorMessageEnum;
import com.bxz.exception.C404Exception;
import com.bxz.exception.C422Exception;
import com.bxz.logging.AroundLogging;
import com.bxz.service.ICoinsService;
import com.bxz.vo.request.AddCoinsVO;
import com.bxz.vo.request.TransferCoinsVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 用户金币展示层
 *
 *   TODO:1.涉及到金额，应该通过验签的形式校验值是否被篡改过。一般在两端使用相同的秘钥进行加密比较，或者单向加密服务端解密的形式。
 *
 * @author baixiangzhu
 * @date 2018/5/22
 **/
@RestController
@Slf4j
@RequestMapping("/coins")
public class CoinsController {

    @Autowired
    private ICoinsService coinsService;

    /**
     * 新建用户和金币
     *  ps:如果已存在，更新金币
     * @param addCoinsVO
     */
    @AroundLogging
    @PostMapping("/add")
    public void add(@RequestBody AddCoinsVO addCoinsVO){

        if (null == addCoinsVO || null == addCoinsVO.getUserId() || null == addCoinsVO.getCoins() ){
            throw new C422Exception(ErrorMessageEnum.PARAM_ERROR.getMessage());
        }

        coinsService.addOrUpdateUserCoins(addCoinsVO.getUserId(), addCoinsVO.getCoins());

    }


    /**
     * 获取用户金币数
     * @param userId
     * @return
     */
    @AroundLogging
    @GetMapping("/user/{userId}")
    public Integer getCoins(@PathVariable Long userId){

        if (null == userId ){
            throw new C422Exception(ErrorMessageEnum.PARAM_ERROR.getMessage());
        }

        Integer coins = coinsService.getCoinsByUserId(userId);
        if (null == coins){
            throw new C404Exception(ErrorMessageEnum.USER_NOT_EXIST.getMessage());
        }

        return coins;
    }


    /**
     * 转账金币
     * @param transferCoinsVO
     */
    @AroundLogging
    @PostMapping("/transfer")
    public void transfer(@RequestBody TransferCoinsVO transferCoinsVO){

        if (null == transferCoinsVO || null == transferCoinsVO.getToUserId()
                || null == transferCoinsVO.getFromUserId() || null == transferCoinsVO.getCoins()){
            throw new C422Exception(ErrorMessageEnum.PARAM_ERROR.getMessage());
        }

        coinsService.transferCoins(transferCoinsVO.getFromUserId(), transferCoinsVO.getToUserId(), transferCoinsVO.getCoins());

    }
}
