package com.bxz.coins.controller;

import com.alibaba.fastjson.JSONObject;
import com.bxz.base.BaseTest;
import com.bxz.controller.CoinsController;
import com.bxz.service.ICoinsService;
import com.bxz.vo.request.AddCoinsVO;
import com.bxz.vo.request.TransferCoinsVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * view层测试
 *
 * @author baixiangzhu
 * @date 2018/5/23
 **/
@Slf4j
public class CoinsControllerTest extends BaseTest{

    @MockBean
    private ICoinsService coinsService;


    @Test
    public void getCoinsTest() throws Exception {

        //1.参数校验失败
        validParamFailForget();

        //2.用户不存在
        getUserNotExit();

        //3.成功获取值
        successGetData();

    }

    @Test
    public void addCoinsTest() throws Exception {

        //1.参数校验失败
        validParamFailForAdd();

        //添加或修改成功
        addSuccess();

    }

    @Test
    public void transferCoinsTest() throws Exception {

        //1.参数校验失败
        validParamFailFortransfer();

        //添加或修改成功
        transferSuccess();

    }

    private void transferSuccess() throws Exception {

        log.info("transferSuccess ............");
       TransferCoinsVO transferCoinsVO = new TransferCoinsVO();
       transferCoinsVO.setFromUserId(123L);
       transferCoinsVO.setToUserId(456L);
       transferCoinsVO.setCoins(10);

        mockMvc.perform((post("/coins/transfer")
                .contentType("application/json")
                .content(JSONObject.toJSON(transferCoinsVO).toString())))
                .andDo(print())
                .andExpect(status().isOk());

    }

    private void validParamFailFortransfer() throws Exception {

        log.info("validParamFailFortransfer ............");

        mockMvc.perform((post("/coins/transfer")
                .contentType("application/json")
                .content(JSONObject.toJSON(new TransferCoinsVO()).toString())))
                .andDo(print())
                .andExpect(status().is4xxClientError());

    }


    private void addSuccess() throws Exception {
        log.info("addSuccess ............");
        AddCoinsVO addCoinsVO = new AddCoinsVO();
        addCoinsVO.setUserId(123L);
        addCoinsVO.setCoins(100);

        mockMvc.perform((post("/coins/add")
                .contentType("application/json")
                .content(JSONObject.toJSON(addCoinsVO).toString())))
                .andDo(print())
                .andExpect(status().isOk());
    }

    private void validParamFailForAdd() throws Exception {

        log.info("validParamFailForAdd ............");

        mockMvc.perform((post("/coins/add")
                .contentType("application/json")
                .content(JSONObject.toJSON(new AddCoinsVO()).toString())))
                .andDo(print())
                .andExpect(status().is4xxClientError());

    }



    private void successGetData() throws Exception {

        log.info("successGetData ............");

        when(coinsService.getCoinsByUserId(1231L)).thenReturn(100);

        mockMvc.perform((get("/coins/user/{userId}",1231L)))
                .andDo(print())
                .andExpect(status().isOk());

    }

    private void validParamFailForget() throws Exception {

        log.info("validParamFail ............");

        mockMvc.perform((get("/coins/user/")))
                .andDo(print())
                .andExpect(status().is4xxClientError());

    }


    public void getUserNotExit() throws Exception {
        log.info("getUserNotExit ............");

        when(coinsService.getCoinsByUserId(888L)).thenReturn(null);

        mockMvc.perform((get("/coins/user/{userId}",888L)))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }




    @TestConfiguration
    @EnableWebMvc
    static class WebConfig extends WebMvcConfigurerAdapter {

        @Bean
        public CoinsController coinsController() {
            return new CoinsController();
        }
    }
}
