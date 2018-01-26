package com.github.trang.dynamic;

import com.github.trang.dynamic.service.BaseCodeService;
import com.google.gson.Gson;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author trang
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class SpringBootBaseTest {

    @Autowired
    protected BaseCodeService baseCodeService;
    @Autowired
    protected Gson gson;

}