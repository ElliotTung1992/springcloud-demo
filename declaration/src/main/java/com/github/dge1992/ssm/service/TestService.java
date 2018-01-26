package com.github.dge1992.ssm.service;

import com.github.dge1992.ssm.dao.TestMapper;
import com.github.dge1992.ssm.domain.Cat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    @Autowired
    private TestMapper testMapper;

    public List<Cat> queryCat() {
        List<Cat> cats = testMapper.queryCat();
        return cats;
    }

    public void insertCat(Cat cat) {
        testMapper.insertCat(cat);
//        int a = 10 / 0;
    }
}
