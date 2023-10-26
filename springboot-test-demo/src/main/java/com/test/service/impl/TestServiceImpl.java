package com.test.service.impl;

import com.test.dao.TestDao;
import com.test.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestDao testDao;

    @Override
    public void add(String userName) {
        testDao.add(userName);
    }
}
