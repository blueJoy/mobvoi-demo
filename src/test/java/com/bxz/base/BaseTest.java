package com.bxz.base;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by baixiangzhu on 2018/5/23.
 */

@TestPropertySource(locations = "classpath:application.yaml")
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class BaseTest {

    @Autowired
    protected WebApplicationContext wac;

    protected MockMvc mockMvc;



    @Before
    public void setup(){

        mockMvc = webAppContextSetup(wac).build();

    }

    @Test
    public void test(){

    }


}
