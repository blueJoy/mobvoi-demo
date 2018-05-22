package com.bxz.controller;

import com.bxz.entity.Coins;
import com.bxz.entity.CoinsDetails;
import com.bxz.logging.AroundLogging;
import com.bxz.mapper.CoinsDetailsMapper;
import com.bxz.mapper.CoinsMapper;
import com.bxz.vo.AddCoinsVO;
import com.bxz.vo.TransferCoinsVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户金币展示层
 *
 * @author baixiangzhu
 * @date 2018/5/22
 **/
@RestController
@Slf4j
@RequestMapping("/coins")
public class CoinsController {

    @Autowired
    private CoinsDetailsMapper coinsDetailsMapper;

    @PostMapping("/add")
    public void add(@RequestBody AddCoinsVO addCoinsVO){

    }


    @GetMapping("/user/{id}")
    public Integer getCoins(Long userId){

        return 0;
    }


    @PostMapping("/transfer")
    public void transfer(@RequestBody TransferCoinsVO transferCoinsVO){

    }

    @AroundLogging
    @GetMapping("/test/{id}")
    public Object get(@PathVariable Integer id){

        CoinsDetails coins = coinsDetailsMapper.selectByPrimaryKey(id);

        return coins;
    }


}
